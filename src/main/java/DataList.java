import javafx.scene.chart.PieChart;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

public class DataList {
    private java.util.ArrayList<Image> list = new java.util.ArrayList<Image>();

    public DataList(){
        ;
    }

    public DataList(DataList obj){
        this.list = obj.list;
    }

    public final void loadImages(String pthAnnotations){
        Mat image = new Mat();
        String line;
        try {
            FileReader fileReader = new FileReader(pthAnnotations);
            BufferedReader infile = new BufferedReader(fileReader);
            ArrayList<String> strs = new ArrayList<String>();
            Iterator<String> sItr;

            while ((line = infile.readLine()) !=  null){
                String[] parts = line.split(",");
                String name = parts[0];
                int label = Integer.parseInt(parts[1]);
                image = Imgcodecs.imread(name, Imgcodecs.CV_LOAD_IMAGE_COLOR);
                Imgproc.resize(image,image,
                        new Size(TransformType.MAXIMUM_WIDTH.getValue(), TransformType.MAXIMUM_HEIGHT.getValue()));
                list.add(new Image(name,image,label));

                ArrayList<Integer> roi = new ArrayList<Integer>();
                while(strs.iterator().hasNext()){
                    int i = Integer.parseInt(strs.iterator().next());
                    roi.add(i);
                }
                list.add(new Image(name, image, roi));
            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }

    public final int getSize(){
        return list.size();
    }

    public ArrayList<Image> getList(){
        return list;
    }
}
