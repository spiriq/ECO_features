import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.UniformRandomGenerator;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import javax.jmi.*;


import java.util.ArrayList;
import java.util.stream.Stream;

public class Creature {
    public static final int CREATURE_INIT_SEED = 1;
    public static final int CREATURE_TRAIN_PERCEPTION_SEED = 11;
    private Mat feature = new Mat();
    private double fitness;
    private Subregion subregion = new Subregion();
    private ArrayList<Transform> transforms = new ArrayList<Transform>();
    private Perceptron perceptron = new Perceptron();
    private double tp; // True positive = correctly identified
    private double tn; // True negative = correctly rejected
    private double fn; // False negative = incorrectly rejected
    private double fp; // False positive = incorrectly identified
    private int id;

    public Creature(){
        Perceptron p = new Perceptron();
        perceptron = p;
        id = -1;
        reset();
    }

    public Creature(int id1){
        id = id1;
        Perceptron p = new Perceptron();
        perceptron = p;
    }

    public Creature (Creature obj)
    {
        this.id = obj.id;
        this.subregion = obj.subregion;
        this.transforms = obj.transforms;
        this.perceptron = obj.perceptron;
        this.fitness = obj.fitness;
        this.feature = obj.feature;
        this.tp = obj.tp;
        this.tn = obj.tn;
        this.fn = obj.fn;
        this.fp = obj.fp;
    }

    public final void initialize(){
        MersenneTwister rnd = new MersenneTwister(CREATURE_INIT_SEED);

        UniformRealDistribution dist = new UniformRealDistribution(0.0001,0.9999);
        double lr = dist.sample();
        perceptron.setLearningRate(lr);
        dist = new UniformRealDistribution(-1,1);
        double bias = dist.sample();
        perceptron.setBias(bias);
        reset();
    }

    public final void setFitness (double fit)
    {
        fitness = fit;
    }

    public final double getFitness ()
    {
        return fitness;
    }

    public final void computeFitness (double p)
    {
        fitness = tp *500.0/(fn+tp) + tn *500.0/(p *fp+tn);
    }

    public final void setSubregion (Subregion region)
    {
        subregion = region;
    }

    public final Subregion getSubregion ()
    {
        return subregion;
    }

    public final void setTransforms (ArrayList<Transform> transforms1)
    {
        transforms = transforms1;
    }

    public final ArrayList<Transform> getTransforms ()
    {
        return transforms;
    }

    public final void performTransforms (Mat src)
    {
        Mat image = src.clone();

        Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2GRAY);

        subregion.setOriginal(image);
        Mat subregionImage = subregion.getSubregion();

        for (Transform transform : transforms)
        {
            try
            {
                transform.performTransform(subregionImage);
                subregionImage = transform.getTransform();
            }
            catch (Exception e)
            {
                System.out.print("\n");
                System.out.print("TRANSFORM ");
                System.out.print(transform.to_string());
                for (double parm : transform.getParameters())
                {
                    System.out.print(" ");
                    System.out.print(parm);
                }
                System.out.print(" FAILED :: ");
                System.out.print(e);
                System.out.print("\n");
            }
        }

        feature = subregionImage;
    }

    public final void trainPerceptron (int goal)
    {

        java.util.ArrayList<Double> inputs = getFeature();

        if (!perceptron.isInitalized())
        {
            UniformRealDistribution dist = new UniformRealDistribution(0,Math.sqrt(3./(inputs.size() + 1)));

            ArrayList<Double> weights = new java.util.ArrayList<Double>();
            for (int i = 0; i < inputs.size(); i++)
            {
                weights.add(dist.sample());
            }

            RefObject<Double> refWeights = new RefObject<Double>(weights);
            perceptron.setWeights(refWeights);
            weights = refWeights;
        }

        RefObject<Double> refInputs = new RefObject<Double>(inputs);
        perceptron.setInputs(refInputs);
        inputs = refInputs;

        perceptron.train(goal);
    }

    public final void outputPerceptron (int goal)
    {
        java.util.ArrayList<Double> inputs = getFeature();

        RefObject<Double> refInputs = new RefObject<Double>(inputs);
        perceptron.setInputs(refInputs);
        inputs = refInputs;
        perceptron.computeOutput();
        double output = (int)perceptron.getOutput();
        if (output == 1 && goal == 1)
        {
            tp++; // True positive = correctly identified
        }
        else if (output == 1 && goal == 0)
        {
            fp++; // False positive = incorrectly identified
        }
        else if (output == 0 && goal == 1)
        {
            fn++; // False negative = incorrectly rejected
        }
        else if (output == 0 && goal == 0)
        {
            tn++; // True negative = correctly rejected
        }
    }

    public final Perceptron getPerception ()
    {
        return perceptron;
    }

    public final void setPerceptron (Perceptron pcptr)
    {
        perceptron = pcptr;
    }

    public final ArrayList<Double> getFeature ()
    {
        ArrayList<Double> inputs = new ArrayList<Double>();
        double min_value = Double.MAX_VALUE;
        double max_value = Double.MIN_VALUE;
        int[] data = new int[0];
        for (int i = 0; i < feature.rows(); i++)
        {
            for (int j = 0; j < feature.cols(); j++)
            {
                byte val = (byte)feature.get(i, j , data);
                double intensity = (double)val;
                if (intensity < min_value)
                {
                    min_value = intensity;
                }
                if (intensity < max_value)
                {
                    max_value = intensity;
                }
                inputs.add(intensity);
            }
        }

        double diff = max_value - min_value;
        double N = (diff == 0) ? 1 : diff;
        for (double input : inputs)
        {
            input = 2.* ((input - min_value) / N) - 1;
        }

        return inputs;
    }

    public final String to_string ()
    {
        String ss = new String();
        System.out.print(subregion.to_string());
        System.out.print(" : ");

        for (Transform transform : transforms)
        {
            ss += "[" + transform.to_string() + " (";
            for (double paramater : transform.getParameters())
            {
                ss += paramater + ",";
            }
            ss += ")] ";
        }

        ss += perceptron.getWeights();

        return ss;
    }

    public final int getId ()
    {
        return id;
    }

    public final void setId (int _id)
    {
        id = _id;
    }

    public final void computeOutput()
    {
        ArrayList<Double> inputs = getFeature();
        RefObject<Double> refInputs = new RefObject<Double>(inputs);
        perceptron.setInputs(refInputs);
        inputs = refInputs;
        perceptron.computeOutput();
    }

    public final int getOutput()
    {
        return (int)perceptron.getOutput();
    }

    public final void reset ()
    {
        tp = 0;
        tn = 0;
        fn = 0;
        fp = 0;
    }
}
