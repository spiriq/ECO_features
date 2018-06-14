import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    public static void main(){
        GA ga = new GA(10, 0.1, 5, 0.95, 0.25, 690, 2);

        AdaBoost ab = new AdaBoost(10, 2);

        DataList traininglist = new DataList();
        traininglist.loadImages("pathToTrainingData");

        DataList holdingList = new DataList();
        holdingList.loadImages("pathToTestData");

        ga.initialize();
        ga.setCreatureIterations(10);

        ArrayList<Creature> population = ga.getPopulation();

        if (population.isEmpty()) {
            System.out.println("No creatures in population");
        }
        else {
            if (population.size() < ab.classifierSize()){
                System.out.println("Population size is smaller than initial boosted classifier size, using total creatures...");
                ab.setClassifierSize(population.size());
            }
        }

        ab.train(population, traininglist);
        ab.predict(holdingList, 0.5);

        System.out.println("Done");

    }
}
