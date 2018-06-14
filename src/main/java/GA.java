import com.sun.deploy.net.protocol.chrome.ChromeURLConnection;
import org.apache.commons.math3.distribution.UniformIntegerDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.opencv.core.Mat;

import java.lang.ref.Reference;
import java.util.ArrayList;
import javax.jmi.*;
import javax.swing.undo.CannotUndoException;

public class GA {
    private int popSize;
    private int numGen;
    private double fitPen;
    private double fitThresh;
    private ArrayList<Creature> population = new ArrayList<Creature>();
    private int learningLabel;
    private double mutationRate;
    private int populationTotal;
    private double growthRate;
    private int creatureIterations;

    public GA (int populationSize, double growthRate1, int numberOfGenerations, double fitnessPenalty, double mutationRate1, double fitnessThreshold, int learningLabel1)
    {
        popSize = populationSize;
        numGen = numberOfGenerations;
        growthRate = growthRate1;
        fitPen = fitnessPenalty;
        fitThresh = fitnessThreshold;
        learningLabel = learningLabel1;
        mutationRate = mutationRate1;
    }

    public GA (GA obj){
        popSize = obj.popSize;
        numGen = obj.numGen;
        fitPen = obj.fitPen;
        fitThresh = obj.fitThresh;
        population = obj.population;
        learningLabel = obj.learningLabel;
        mutationRate = obj.mutationRate;
        populationTotal = obj.populationTotal;
        growthRate = obj.growthRate;
        creatureIterations = obj.creatureIterations;
    }

    public final Creature crossover(Creature a, Creature b){
        Creature child = new Creature();
        UniformIntegerDistribution choice = new UniformIntegerDistribution(0,1);

        Subregion subregion = new Subregion();
        ArrayList<Transform> aTransofrms = a.getTransforms();
        ArrayList<Transform> bTransforms = b.getTransforms();
        ArrayList<Transform> transforms = new ArrayList<Transform>();
        Perceptron perceptron = new Perceptron();

        if (choice.sample() == 0){
            subregion = a.getSubregion();
            perceptron = a.getPerception();
            RefObject<Perceptron> refPerceptron = new RefObject<Perceptron>(perceptron);
            child.setPerceptron(refPerceptron);
            perceptron = refPerceptron;
        }
        else
        {
            subregion = b.getSubregion();
            perceptron = b.getPerception();
        }

        RefObject<Perceptron> refPerceptron2 = new RefObject<Perceptron>(perceptron);
        child.setPerceptron(refPerceptron2);
        perceptron = refPerceptron2;

        choice = new UniformIntegerDistribution (0, (int)aTransofrms.size()-1);
        for (int i = 0; i < choice.sample(); i++){
            transforms.add(aTransofrms.get(i));
        }

        choice = new UniformIntegerDistribution (0, (int)bTransforms.size()-1);
        for (int i = 0; i < choice.sample(); i++){
            transforms.add(bTransforms.get(i));
        }

        RefObject<Subregion> refSubregion = new RefObject<Subregion>(subregion);
        child.setSubregion(refSubregion);
        subregion = refSubregion;
        child.setTransforms(transforms);

        child.setId(populationTotal);
        populationTotal++;

        System.out.println("crossing ");
        System.out.print(a.getId());
        System.out.print(" (x) ");
        System.out.print(b.getId());
        System.out.print(" = ");
        System.out.print(child.getId());
        System.out.print("\n");
        return child;
    }

    public void mutate (Creature a){
        ArrayList<Transform> aTransforms =a.getTransforms();
        UniformIntegerDistribution choice = new UniformIntegerDistribution(0, (int) aTransforms.size() - 1);
        int transformChoice = choice.sample();
        Transform aTransform = aTransforms.get(transformChoice);
        if (aTransform.getParamaterSize() == 0)
            return;

        Transform transform = new Transform(aTransform.getTransformType());
        transform.randomizeParameters();

        Double[] originalParameters = aTransforms.get(transformChoice).getParameters();
        Double[] parameters = transform.getParameters();

        choice = new UniformIntegerDistribution(0, aTransform.getParamaterSize() - 1);
        int parametersChoice = choice.sample();

        originalParameters[parametersChoice] = parameters[parametersChoice];
        System.out.print("Creature ");
        System.out.print(a.getId());
        System.out.print(" mutated");
        System.out.print("\n");

    }

    public final void initialize(){
        System.out.println("Initalizing creatures");
        System.out.print("\n");
        UniformIntegerDistribution transformChoice = new UniformIntegerDistribution(1, TransformType.TRANSORM_NUM.getValue() - 1);
        UniformIntegerDistribution transformCount = new UniformIntegerDistribution(TransformType.MINIMUM_TRANFORMS.getValue(), TransformType.MAXIMUM_TRANFORMS.getValue());

        for (int i = 0; i < popSize; i++){
            Creature currentCreature = new Creature(i);
            ArrayList<Transform> transforms = new ArrayList<Transform>();
            int count = transformCount.sample();
            TransformType trt = TransformType.values()[transformChoice.sample()];

            for (int j = 0; j < count; j++){
                Transform transform = new Transform(trt);
                transform.randomizeParameters();
                transforms.add(transform);
            }

            UniformIntegerDistribution x1Dist = new UniformIntegerDistribution(2,TransformType.MAXIMUM_WIDTH.getValue() - 2);
            int x1 = x1Dist.sample();
            UniformIntegerDistribution x2Dist = new UniformIntegerDistribution(x1 + 1, TransformType.MAXIMUM_WIDTH.getValue() - 1);
            int x2 = x2Dist.sample();
            UniformIntegerDistribution y1Dist = new UniformIntegerDistribution(2, TransformType.MAXIMUM_HEIGHT.getValue() - 2);
            int y1 = y1Dist.sample();
            UniformIntegerDistribution y2Dist = new UniformIntegerDistribution(y1 + 1, TransformType.MAXIMUM_HEIGHT.getValue() - 1);
            int y2 = y2Dist.sample();
            Subregion subregion = new Subregion(x1,x2,y1,y2);
            currentCreature.setSubregion(subregion);
            currentCreature.setTransforms(transforms);
            currentCreature.initialize();
            population.add(currentCreature);
        }
        populationTotal = popSize;
    }

    public final void run (ArrayList<DataList> trainingList, ArrayList<DataList> holdingList){
        int gen = 0;

        while(true){
            int i = 0;
            int g = 1;
            for (Creature creature: population){
                i++;
                g++;
                gen++;
                System.out.print("Training creature ");
                System.out.print(creature.getId());
                System.out.print(" ( ");
                System.out.print(i + " " + g + " / ");
                System.out.print(population.size() + ")");
                System.out.print("in generation" + gen);

               for (int j = 0; j < creatureIterations; j++){
                   for (DataList obj: trainingList){
                       for (Image im: obj.getList()){
                           Mat image = im.getImage();
                           creature.performTransforms(image);
                           int label = (im.getLabel() == learningLabel) ? 1 : 0;
                           creature.outputPerceptron(label);
                       }
                   }
               }
               creature.computeFitness(fitPen);
            }
            System.out.println("\n");

            for (Creature creature : population){
                double fitness = creature.getFitness();
                System.out.print("Creature ");
                System.out.print(creature.getId());
                System.out.print(": ");
                System.out.print("fitness");

                if (creature.getFitness() < fitThresh){
                    System.out.print(" (removed)");
                    population.remove(creature);
                    if (population.isEmpty())
                    {
                        System.out.print("\n");
                        System.out.print("population count: ");
                        System.out.print(population.size());
                        System.out.print("\n");
                        return;
                    }
                }
                System.out.print("\n");
                creature.reset();
            }

            if (++gen >=numGen){
                break;
            }

            if (population.size() >= 2){
                UniformIntegerDistribution pickDist = new UniformIntegerDistribution(0, (int) population.size()-1);
                double growth = (population.size() + (double) population.size() * Math.log(1 + growthRate));
                growth -= population.size();

                int originalSize = population.size();
                ArrayList<Creature> newGeneration = new ArrayList<Creature>();

                while (growth != 0){
                    int idx1 = pickDist.sample();
                    int idx2 = pickDist.sample();

                    while (idx2 == idx1){
                        idx2 = pickDist.sample();
                    }

                    Creature a = population.get(idx1);
                    Creature b = population.get(idx2);

                    newGeneration.add(crossover(a,b));
                    growth--;
                }

                for (Creature creature: newGeneration){
                    population.add(creature);
                }

                System.out.print("population growth from");
                System.out.print(originalSize);
                System.out.print(" to ");
                System.out.print(population.size());
                System.out.print("\n");
            }

            UniformRealDistribution mutateDist = new UniformRealDistribution(0,1);
            for (Creature creature : population){
                if (mutateDist.sample() < mutationRate){
                    mutate(creature);
                }
            }
        }
    }

    public final void setCreatureIterations (int crIter){
        creatureIterations = crIter;
    }

    public final int getCreatureIterations(){
        return creatureIterations;
    }

    public final ArrayList<Creature> getPopulation(){
        return population;
    }

}

