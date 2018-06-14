import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import java.util.ArrayList;

public class Image {
    private String name;
    private Mat image;
    private ArrayList<Integer> roi;
    private int label;

    public Image(String nm, Mat im, ArrayList<Integer> roi1)
    {
        name = nm;
        image = im;
        roi = roi1;
    }

    public Image(String nm, Mat im, int labl){
        name = nm;
        image = im;
        label = labl;
    }

    public Image(Image obj){
        name = obj.name;
        image = obj.image;
        roi = obj.roi;
        label = obj.label;
    }


    public final void setImage(Mat image_){
        image = image_;
    }

    public final Mat getImage(){
        return image;
    }

    public final void setRoi (ArrayList<Integer> roi_){
        roi = roi_;
    }

    public final ArrayList<Integer> getRoi(){
        return roi;
    }

    public final void setName(String name_){
        name = name_;
    }

    public final String getName(){
        return name;
    }

    public void setLabel (int label_){
        label = label_;
    }

    public final int getLabel(){
        return label;
    }
}
