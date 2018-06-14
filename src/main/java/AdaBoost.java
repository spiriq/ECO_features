import org.opencv.*;
import org.opencv.core.Mat;

import java.util.ArrayList;

public class AdaBoost {
    private ArrayList<Creature> alpha = new ArrayList<Creature>();
    private ArrayList<Double> deltaM = new ArrayList<Double>();
    private ArrayList<Double> deltaW = new ArrayList<Double>();
    private ArrayList<Double> rho = new ArrayList<Double>();
    private ArrayList<Double> deltaOmega = new ArrayList<Double>();
    private int X;
    private int learningLabel;

    public AdaBoost(int classifiers, int learnlabel){
        X = classifiers;
        learningLabel =  learnlabel;
    }

    public AdaBoost(AdaBoost obj){
        this.deltaM = obj.deltaM;
        this.deltaW = obj.deltaW;
        this.rho = obj.rho;
        this.alpha = obj.alpha;
        this.X = obj.X;
        this.learningLabel = obj.learningLabel;
    }

    public final void train (ArrayList<Creature> population, DataList dataList){
        System.out.print("Boosting ");
        System.out.print(population.size());
        System.out.print("\n");
        deltaM = new ArrayList<Double>(dataList.getSize());
        deltaW = new ArrayList<Double>(population.size());
        deltaOmega = new ArrayList<Double>(population.size());
        alpha = new ArrayList<Creature>(X);
        rho = new ArrayList<Double>(X);

        ArrayList<Integer> classifications =new ArrayList<Integer>();

        for (int x = 0; x < X; x++){
            int w = 0;
            for (Creature creature: population){
                int m = 0;
                int tp = 0;
                int fp = 0;
                int fn = 0;
                int tn = 0;

                deltaW.set(w, Double.MIN_VALUE);
                for (Image obj: dataList.getList()){
                    Mat image = obj.getImage();
                    creature.performTransforms(image);
                    creature.computeOutput();
                    int output = creature.getOutput();
                    int label = (obj.getLabel() == learningLabel) ? 1 : 0;

                    classifications.set(m, output);

                    if (output != label){
                        deltaW.set(w, deltaW.get(w) + deltaM.get(m));
                    }

                    if (output == 1 && label == 1){
                        tp++;
                    }
                    else if (output == 1 && label == 0){
                        fp++;
                    }

                    else if (output == 0 && label == 1){
                        fn++;
                    }

                    else if (output == 0 && label ==0){
                        tn++;
                    }

                    m++;
                }

                deltaOmega.set(w, (tp+tn)*1./ (tp+tn+fp+fn)*1.);
                w++;
            }

            double minError = Double.MIN_VALUE;
            int omega = 0;
            for (w = 0; w < deltaW.size(); w++){
                if (deltaW.get(w) < minError){
                    minError = deltaW.get(w);
                    omega = w;
                }
            }

            if (1. - deltaOmega.get(omega) >= 0.5 && deltaW.get(omega) > 1.0){
                X = x;
                break;
            }

            rho.set(x, 0.5 * Math.log((1 - deltaW.get(omega)) / deltaW.get(omega)));
            alpha.set(x , population.get(omega));
            population.remove(population.get(omega));
            deltaW.remove(deltaW.get(omega));

            if (population.isEmpty()){
                break;
            }

            int m = 0;

            int N =  0;
            for (Image obj: dataList.getList()){
                int label = (obj.getLabel() == learningLabel) ? 1 : 0;
                int c = (classifications.get(m) == label) ? 1 : -1;
                deltaM.set(m, (deltaM.get(m) * Math.exp(-(rho.get(x) * c))));
                N += deltaM.get(m);
                m++;
            }

            for (m = 0; m < dataList.getSize(); m++){
                deltaM.set(m, deltaM.get(m) / N);
                m++;
            }

        }
    }

    public final void predict (DataList dataList, double tao){
        System.out.print("prediction...");
        System.out.print(X);
        System.out.print("\n");
        int tp = 0;
        int fp = 0;
        int fn = 0;
        int tn = 0;

        for (Image obj: dataList.getList()){
            Mat image = obj.getImage();
            int label = (obj.getLabel() == learningLabel) ? 1 : 0;
            double c1 = 0;

            for (int x = 0; x < X; x++){
                alpha.get(x).performTransforms(image);
                alpha.get(x).computeOutput();
                c1 += rho.get(x) * alpha.get(x).getOutput();
            }

            int c = (c1 > tao) ? 1 : 0;

            System.out.print(obj.getName());
            System.out.print("\n");
            System.out.print(label);
            System.out.print(" ");
            System.out.print(c);
            System.out.print(" : ");
            System.out.print(c1);
            System.out.print("\n");
            System.out.print("\n");

            if (c == 1 && label == 1)
            {
                tp++;
            }
            else if (c == 1 && label == 0)
            {
                fp++;
            }
            else if (c == 0 && label == 1)
            {
                fn++;
            }
            else if (c == 0 && label == 0)
            {
                tn++;
            }
        }

        System.out.print("tp: ");
        System.out.print(tp);
        System.out.print(", ");
        System.out.print("fp: ");
        System.out.print(fp);
        System.out.print(", ");
        System.out.print("fn: ");
        System.out.print(fn);
        System.out.print(", ");
        System.out.print("tn: ");
        System.out.print(tn);
        System.out.print(", ");

        double accuracy = (tp + tn)*1. / (tp + tn + fp + fn) * 1.;

        System.out.print("accuracy: ");
        System.out.print(accuracy *100.);
        System.out.print("\n");
    }

    public final int classifierSize(){
        return X;
    }

    public final void setClassifierSize(int x){
        X = x;
    }
}
