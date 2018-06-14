import java.util.ArrayList;

public class Perceptron {
    private double bias;
    private double learningRate;
    private java.util.ArrayList<Double> weights = new ArrayList<Double>();
    private java.util.ArrayList<Double> inputs = new ArrayList<Double>();
    private double output;
    private boolean initialized;
    private double alpha;

    public Perceptron (){
        initialized = false;
        alpha = 0.1;
    }

    public Perceptron (Perceptron obj)
    {
        this.bias = obj.bias;
        this.learningRate = obj.learningRate;
        this.weights = obj.weights;
        this.inputs = obj.inputs;
        this.output = obj.output;
        this.initialized = obj.initialized;
        this.alpha = obj.alpha;
    }


    public final void train (int goal)
    {
        computeOutput();
        for (int i = 0; i < weights.size(); i++)
        {
            weights.set(i, weights.get(i) + learningRate*(goal-alpha)*inputs.get(i));
        }
    }

    public final void setWeights(ArrayList<Double> w){
        weights = w;
        initialized = true;
    }

    public final ArrayList<Double> getInputs(){
        return inputs;
    }

    public final void setInputs(ArrayList<Double> in){
        inputs = in;
    }

    public final double getOutput ()
    {
        return alpha;
    }

    public final void setOutput (double out)
    {
        output = out;
    }

    public final double getBias ()
    {
        return bias;
    }

    public final void setBias (double bi)
    {
        bias = bi;
    }

    public final double getLearningRate ()
    {
        return learningRate;
    }

    public final void setLearningRate (double lr)
    {
        learningRate = lr;
    }

    public final boolean isInitalized ()
    {
        return initialized;
    }

    public final java.util.ArrayList<Double> getWeights ()
    {
        return weights;
    }

    public final void computeOutput ()
    {
        output = 0;

        if (weights.size() != inputs.size())
        {
            return;
        }

        for (int i = 0; i < inputs.size(); i++)
        {
            output += weights.get(i)*inputs.get(i);
        }

        output += bias;

        output = Math.tanh(output);

        if (output > 0)
        {
            alpha = 1;
        }
        else
        {
            alpha = 0;
        }
    }

}
