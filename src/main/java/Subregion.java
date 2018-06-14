import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Subregion {
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private Mat original;
    private Mat subregion;

    public Subregion(){
        x1 = 0;
        x2 = 0;
        y1 = 0;
        y2 = 0;
    }

    public Subregion (Mat orig){
        Mat cropped = new Mat();
        cropped = orig.clone();
        cropped.copyTo(original);

        x1 = 0;
        x2 = orig.rows();
        y1 = 0;
        y2 = orig.cols();

        constructSubregion();
    }

    public Subregion(int x1_, int x2_, int y1_, int y2_){
        setSubregion(x1_, x2_, y1_,y2_);
    }

    public Subregion(Mat original_, int x1_, int x2_,int y1_, int y2_){
        Mat cropped = original_.clone();
        cropped.copyTo(original);

        x1 = x1_;
        x2 = x2_;
        y1 = y1_;
        y2 = y2_;

        constructSubregion();
    }

    public Subregion(Subregion obj){
        this.x1 = obj.x1;
        this.x2 = obj.x2;
        this.y1 = obj.y1;
        this.y2 = obj.y2;

        this.original = obj.original;
        this.subregion = obj.subregion;
    }

    public final void setOriginal (Mat original_){
        original = original_.clone();
    }

    public final void setSubregion(Mat original_, int x1_, int x2_, int y1_, int y2_){
        setSubregion(x1_, x2_, y1_, y2_);
        constructSubregion();
    }

    public final void setSubregion(int x1_, int x2_, int y1_, int y2_){
        x1 = x1_;
        x2 = x2_;
        y1 = y1_;
        y2 = y2_;
    }

    public final Mat getSubregion(){
        constructSubregion();
        return subregion;
    }

    public final int[] getSubregionVal(){
        int[] subregionVal = new int[4];

        subregionVal[0] = x1;
        subregionVal[1] = x2;
        subregionVal[2] = y1;
        subregionVal[3] = y2;

        return subregionVal;
    }

    public final int getArea(){
        Rect crop = new Rect(x2 - x1, y2 - y1, x1, y1);
        return (int)crop.area();
    }

    public final Rect getRect(){
        Rect rect = new Rect(x2 - x1, y2 - y1, x1, y1);
        return rect;
    }

    public final String toString(){
        return Arrays.toString(getSubregionVal());
    }

    private void constructSubregion(){
        Rect crop = new Rect (x2 -x1,y2 - y1, x1, y1); // ROI

        Mat cropped = new Mat(original, crop);
        cropped.copyTo(subregion);
    }
}
