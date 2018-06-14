import com.sun.javafx.geom.Vec3f;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.shape.Circle;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.commons.math3.distribution.UniformIntegerDistribution;
import org.apache.commons.math3.random.MersenneTwister;
import org.omg.CORBA.IMP_LIMIT;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class Transform {

    private Double [] parameters;
    private TransformType transformType;
    private Mat transformedImage = new Mat();

    public Transform()
    {
        transformType = TransformType.NIL;
    }

    public Transform(TransformType transformTypee)
    {
        transformType = transformTypee;
    }

    public Transform (Transform obj)
    {
        this.parameters = obj.parameters;
        this.transformType = obj.transformType;
        this.transformedImage = obj.transformedImage;
    }

    public void dispose()
    {
        //parameters.subList(0,parameters.size()).clear();
        parameters = new Double[0];
    }


    public final void performTransform(Mat src){
        switch(transformType){
            case GABOR_FILTER:
            {
                gaborFilter(src);
            }
            break;
            case MORPHOLOGICAL_ERODE:
            {
                morphologicalErode(src);
            }
            break;
            case GAUSSIAN_BLUR:
            {
                gaussianBlur(src);
            }
            break;
            case HISTOGRAM:
            {
                histogram(src);
            }
            break;
            case HOUGH_CIRCLES:
            {
                houghCircles(src);
            }
            break;
            case NORMALIZE:
            {
                normalize(src);
            }
            break;
            case SQUARE_ROOT:
            {
                squareRoot(src);
            }
            break;
            case CANNY_EDGE:
            {
                cannyEdge(src);
            }
            break;
            case INTEGRAL_IMAGE:
            {
                integralImage(src);
            }
            break;
            case DIFFERENCE_GAUSSIANS:
            {
                differenceGaussians(src);
            }
            break;
            case CENSUS_TRANSFORM:
            {
                censusTransform(src);
            }
            break;
            case SOBEL_OPERATOR:
            {
                sobelOperator(src);
            }
            break;
            case MORPHOLOGICAL_DILATE:
            {
                morphologicalDilate(src);
            }
            break;
            case ADAPTIVE_THRESHOLDING:
            {
                adaptiveThresholding(src);
            }
            break;
            case HOUGH_LINES:
            {
                houghLines(src);
            }
            break;
            case HARRIS_CORNER_STRENGTH:
            {
                harrisCornerStrength(src);
            }
            break;
            case HISTOGRAM_EQUALIZATION:
            {
                histogramEqualization(src);
            }
            break;
            case LOG:
            {
                log(src);
            }
            break;
            case MEDIAN_BLUR:
            {
                medianBlur(src);
            }
            break;
            case DISTANCE_TRANSFORM:
            {
                distanceTranform(src);
            }
            break;
            case LAPLACIAN_EDGED_ETECTION:
            {
                laplacianEdgedDetection(src);
            }
            break;
            default:
            {
                transformedImage = src;
            }
            break;
        }
    }

    public final void performTransform(Mat src, TransformType transformType){
        switch(transformType){
            case GABOR_FILTER:
            {
                gaborFilter(src);
            }
            break;
            case MORPHOLOGICAL_ERODE:
            {
                morphologicalErode(src);
            }
            break;
            case GAUSSIAN_BLUR:
            {
                gaussianBlur(src);
            }
            break;
            case HISTOGRAM:
            {
                histogram(src);
            }
            break;
            case HOUGH_CIRCLES:
            {
                houghCircles(src);
            }
            break;
            case NORMALIZE:
            {
                normalize(src);
            }
            break;
            case SQUARE_ROOT:
            {
                squareRoot(src);
            }
            break;
            case CANNY_EDGE:
            {
                cannyEdge(src);
            }
            break;
            case INTEGRAL_IMAGE:
            {
                integralImage(src);
            }
            break;
            case DIFFERENCE_GAUSSIANS:
            {
                differenceGaussians(src);
            }
            break;
            case CENSUS_TRANSFORM:
            {
                censusTransform(src);
            }
            break;
            case SOBEL_OPERATOR:
            {
                sobelOperator(src);
            }
            break;
            case MORPHOLOGICAL_DILATE:
            {
                morphologicalDilate(src);
            }
            break;
            case ADAPTIVE_THRESHOLDING:
            {
                adaptiveThresholding(src);
            }
            break;
            case HOUGH_LINES:
            {
                houghLines(src);
            }
            break;
            case HARRIS_CORNER_STRENGTH:
            {
                harrisCornerStrength(src);
            }
            break;
            case HISTOGRAM_EQUALIZATION:
            {
                histogramEqualization(src);
            }
            break;
            case LOG:
            {
                log(src);
            }
            break;
            case MEDIAN_BLUR:
            {
                medianBlur(src);
            }
            break;
            case DISTANCE_TRANSFORM:
            {
                distanceTranform(src);
            }
            break;
            case LAPLACIAN_EDGED_ETECTION:
            {
                laplacianEdgedDetection(src);
            }
            break;
            default:
            {
                transformedImage = src;
            }
            break;
        }
    }

    public final Mat getTransform(){
        return transformedImage;
    }

    public final void setTransformType (TransformType transformType){
        transformType = transformType;
    }

    public final TransformType getTransformType(){
        return transformType;
    }

    public final String toString ()
    {
        String transformStr;
        switch (transformType)
        {
            case GABOR_FILTER:
            {
                transformStr = "GABOR_FILTER";
            }
            break;
            case MORPHOLOGICAL_ERODE:
            {
                transformStr = "MORPHOLOGICAL_ERODE";
            }
            break;
            case GAUSSIAN_BLUR:
            {
                transformStr = "GAUSSIAN_BLUR";
            }
            break;
            case HISTOGRAM:
            {
                transformStr = "HISTOGRAM";
            }
            break;
            case HOUGH_CIRCLES:
            {
                transformStr = "HOUGH_CIRCLES";
            }
            break;
            case NORMALIZE:
            {
                transformStr = "NORMALIZE";
            }
            break;
            case SQUARE_ROOT:
            {
                transformStr = "SQUARE_ROOT";
            }
            break;
            case CANNY_EDGE:
            {
                transformStr = "CANNY_EDGE";
            }
            break;
            case INTEGRAL_IMAGE:
            {
                transformStr = "INTEGRAL_IMAGE";
            }
            break;
            case DIFFERENCE_GAUSSIANS:
            {
                transformStr = "DIFFERENCE_GAUSSIANS";
            }
            break;
            case CENSUS_TRANSFORM:
            {
                transformStr = "CENSUS_TRANSFORM";
            }
            break;
            case SOBEL_OPERATOR:
            {
                transformStr = "SOBEL_OPERATOR";
            }
            break;
            case MORPHOLOGICAL_DILATE:
            {
                transformStr = "MORPHOLOGICAL_DILATE";
            }
            break;
            case ADAPTIVE_THRESHOLDING:
            {
                transformStr = "ADAPTIVE_THRESHOLDING";
            }
            break;
            case HOUGH_LINES:
            {
                transformStr = "HOUGH_LINES";
            }
            break;
            case HARRIS_CORNER_STRENGTH:
            {
                transformStr = "HARRIS_CORNER_STRENGTH";
            }
            break;
            case HISTOGRAM_EQUALIZATION:
            {
                transformStr = "HISTOGRAM_EQUALIZATION";
            }
            break;
            case LOG:
            {
                transformStr = "LOG";
            }
            break;
            case MEDIAN_BLUR:
            {
                transformStr = "MEDIAN_BLUR";
            }
            break;
            case DISTANCE_TRANSFORM:
            {
                transformStr = "DISTANCE_TRANSFORM";
            }
            break;
            case LAPLACIAN_EDGED_ETECTION:
            {
                transformStr = "LAPLACIAN_EDGED_ETECTION";
            }
            break;
            default:
            {
                transformStr = "none";
            }
        }
        return transformStr;
    }

    public final void setParameter(int i, double value){
        if (i < 0 || i > parameters.length)
            return;
        parameters[i] = value;
    }

    public final void setParameters(Double[] parameters1){
        parameters = parameters1;
    }

    public final Double[] getParameters(){
        return parameters;
    }

    public final void randomizeParameters ()
    {
        switch (transformType)
        {
            case GABOR_FILTER:
            {
                randomizeGaborFilterParameters();
            }
            break;
            case MORPHOLOGICAL_ERODE:
            {
                randomizeMorphologicalErodeParameters();
            }
            break;
            case GAUSSIAN_BLUR:
            {
                randomizeGaussianBlurParameters();
            }
            break;
            case HISTOGRAM:
            {
                randomizeHistogramParameters();
            }
            break;
            case HOUGH_CIRCLES:
            {
                randomizeHoughCirclesParameters();
            }
            break;
            case NORMALIZE:
            {
                randomizeNormalizeParameters();
            }
            break;
            case CANNY_EDGE:
            {
                randomizeCannyEdgeParameters();
            }
            break;
            case INTEGRAL_IMAGE:
            {
                randomizeIntegralImageParameters();
            }
            break;
            case DIFFERENCE_GAUSSIANS:
            {
                randomizeDifferenceGaussiansParameters();
            }
            break;
            case CENSUS_TRANSFORM:
            {
                randomizeCensusTransformParameters();
            }
            break;
            case SOBEL_OPERATOR:
            {
                randomizeSobelOperatorParameters();
            }
            break;
            case MORPHOLOGICAL_DILATE:
            {
                randomizeMorphologicalDilateParameters();
            }
            break;
            case ADAPTIVE_THRESHOLDING:
            {
                randomizeAdaptiveThresholdingParameters();
            }
            break;
            case HOUGH_LINES:
            {
                randomizeHoughLinesParameters();
            }
            break;
            case HARRIS_CORNER_STRENGTH:
            {
                randomizeHarrisCornerStrengthParameters();
            }
            break;
            case MEDIAN_BLUR:
            {
                randomizeMedianBlur();
            }
            break;
            case DISTANCE_TRANSFORM:
            {
                randomizeDistanceTransformParameters();
            }
            break;
            case LAPLACIAN_EDGED_ETECTION:
            {
                randomizeLaplacianEdgedDetectionParameters();
            }
            break;
            default:
            {
                ;
            }
            break;
        }
    }

    public final int getParamaterSize ()
    {
        switch (transformType)
        {
            case GABOR_FILTER:
            {
                return TransformType.GABOR_FILTER_PARAMETER_NUMBER;
            }
            case MORPHOLOGICAL_ERODE:
            {
                return TransformType.MORPHOLOGIAL_ERODE_PARAMETER_NUMBER;
            }
            case GAUSSIAN_BLUR:
            {
                return TransformType.GAUSSIAN_BLUR_PARAMETER_NUMBER;
            }
            case HISTOGRAM:
            {
                return TransformType.HISTOGRAM_PARAMETER_NUMBER;
            }
            case HOUGH_CIRCLES:
            {
                return TransformType.HOUGH_CIRCLES_PARAMETER_NUMBER;
            }
            case NORMALIZE:
            {
                return TransformType.NORMALIZE_PARAMETER_NUMBER;
            }
            case SQUARE_ROOT:
            {
                return TransformType.SQUARE_ROOT_PARAMETER_NUMBER;
            }
            case CANNY_EDGE:
            {
                return TransformType.CANNY_EDGE_PARAMETER_NUMBER;
            }
            case INTEGRAL_IMAGE:
            {
                return TransformType.INTEGRAL_IMAGE_PARAMETER_NUMBER;
            }
            case DIFFERENCE_GAUSSIANS:
            {
                return TransformType.DIFFERENCE_GAUSSIANS_PARAMETER_NUMBER;
            }
            case CENSUS_TRANSFORM:
            {
                return TransformType.CENSUS_TRANSFORM_PARAMETER_NUMBER;
            }
            case SOBEL_OPERATOR:
            {
                return TransformType.SOBEL_OPERATOR_PARAMETER_NUMBER;
            }
            case MORPHOLOGICAL_DILATE:
            {
                return TransformType.MORPHOLOGICAL_DILATE_PARAMETER_NUMBER;
            }
            case ADAPTIVE_THRESHOLDING:
            {
                return TransformType.ADAPTIVE_THRESHOLDING_PARAMETER_NUMBER;
            }
            case HOUGH_LINES:
            {
                return TransformType.HOUGH_LINES_PARAMETER_NUMBER;
            }
            case HARRIS_CORNER_STRENGTH:
            {
                return TransformType.HARRIS_CORNER_STRENGTH_PARAMETER_NUMBER;
            }
            case HISTOGRAM_EQUALIZATION:
            {
                return TransformType.HISTOGRAM_EQUALIZATION_PARAMETER_NUMBER;
            }
            case LOG:
            {
                return TransformType.LOG_PARAMETER_NUMBER;
            }
            case MEDIAN_BLUR:
            {
                return TransformType.MEDIAN_BLUR_PARAMETER_NUMBER;
            }
            case DISTANCE_TRANSFORM:
            {
                return TransformType.DISTANCE_TRANSFORM_PARAMETER_NUMBER;
            }
            case LAPLACIAN_EDGED_ETECTION:
            {
                return TransformType.LAPLACIAN_EDGED_ETECTION_PARAMETER_NUMBER;
            }
            default:
            {
                return 0;
            }
        }
    }

    public void randomizeGaborFilterParameters(){
        parameters = new Double[TransformType.GABOR_FILTER_PARAMETER_NUMBER];
        UniformIntegerDistribution sizeDist = new UniformIntegerDistribution(0, 20);
        UniformIntegerDistribution sigmaDist = new UniformIntegerDistribution(0, 180);
        UniformIntegerDistribution thetaDist = new UniformIntegerDistribution(0, 180);
        UniformIntegerDistribution lambdaDist = new UniformIntegerDistribution(0, 100);
        UniformIntegerDistribution gammaDist = new UniformIntegerDistribution(0, 100);
        UniformIntegerDistribution psiDist = new UniformIntegerDistribution(0, 360);
        int size = sizeDist.sample();
        if (size % 2 == 0)
        {
            size++;
        }

        parameters[0] = (double)size;
        parameters[1] = (double)sigmaDist.sample();
        parameters[2] = (double)thetaDist.sample();
        parameters[3] = (double)lambdaDist.sample();
        parameters[4] = (double)gammaDist.sample();
        parameters[5] = (double)psiDist.sample();

    }

    public void gaborFilter(Mat src){
        Mat tmp = src.clone();
        if (parameters.length != TransformType.GABOR_FILTER_PARAMETER_NUMBER)
            return;

        Size size = new Size(parameters[0],parameters[0]);
        double sigma = parameters[1]/((parameters[0]==0)?1:parameters[0]);
        double theta = parameters[2]*Math.PI/180;
        double lambda = parameters[3];
        double gamma = parameters[4];
        double psi = parameters[5]*Math.PI/180;

        Imgproc.filter2D(tmp, tmp, CvType.CV_32F, Imgproc.getGaborKernel(size, sigma, theta, lambda, gamma, psi, CvType.CV_32F ));
        tmp.convertTo(tmp, CvType.CV_8UC1,255);
        transformedImage = tmp.clone();
    }

    public void randomizeMorphologicalErodeParameters(){
        parameters = new Double[TransformType.MORPHOLOGIAL_ERODE_PARAMETER_NUMBER];

        UniformIntegerDistribution elementDist = new UniformIntegerDistribution(0,2);
        UniformIntegerDistribution kernelSizeDist = new UniformIntegerDistribution(1,20);

        int kernelSize = kernelSizeDist.sample();
        if (kernelSize % 2 == 0){
            kernelSize++;
        }
        parameters[0] = (double)elementDist.sample();
        parameters[1] = (double)kernelSize;
    }

    public void morphologicalErode(Mat src){
        Mat tmp = src.clone();
        if (parameters.length != TransformType.MORPHOLOGIAL_ERODE_PARAMETER_NUMBER){
            return;
        }

        double erosionType = parameters[0];
        Size size = new Size(2 * parameters[1] + 1, 2 *parameters[1] +1);
        Point point = new Point (parameters[1], parameters[1]);

        Imgproc.erode(tmp, tmp, Imgproc.getStructuringElement((int) erosionType, size, point));
        transformedImage = tmp.clone();
    }

    public void randomizeGaussianBlurParameters()
    {
        parameters = new Double[TransformType.GAUSSIAN_BLUR_PARAMETER_NUMBER];

        UniformIntegerDistribution sigmaXdist = new UniformIntegerDistribution(0,10);
        UniformIntegerDistribution sigmaYdist = new UniformIntegerDistribution(0,10);
        UniformIntegerDistribution kernelSizeDist = new UniformIntegerDistribution(0,20);

        int kernelSize = kernelSizeDist.sample();
        if (kernelSize % 2 == 0){
            kernelSize++;
        }
        parameters[0] = (double)kernelSize;
        parameters[1] = (double)sigmaXdist.sample();
        parameters[2] = (double)sigmaYdist.sample();
    }

    public void gaussianBlur(Mat src){
        Mat tmp = src.clone();

        if (parameters.length != TransformType.GAUSSIAN_BLUR_PARAMETER_NUMBER){
            return;
        }

        Size size = new Size (parameters[0], parameters[0]);
        double sigmaX = parameters[1];
        double sigmaY = parameters[2];

        Imgproc.GaussianBlur(tmp, tmp, size, sigmaX, sigmaY);
        transformedImage = tmp.clone();
    }

    public void randomizeHistogramParameters(){

        parameters = new Double[TransformType.HISTOGRAM_PARAMETER_NUMBER];

        UniformIntegerDistribution hbinsDist = new UniformIntegerDistribution(1, 100);
        UniformIntegerDistribution sbinsDist = new UniformIntegerDistribution(10,100);

        parameters[0] = (double) hbinsDist.sample();
        parameters[1] = (double) sbinsDist.sample();
    }

    public void histogram(Mat src){
        Mat tmp = src.clone();

        if (parameters.length != TransformType.HISTOGRAM_PARAMETER_NUMBER){
            return;
        }

        double hbins = parameters[0];
        double sbins = parameters[1];
        int[] histSize = {(int)hbins, (int)sbins};
        float[] hranges = {0, 180};
        float[] sranges = {0, 256};
        float[][] ranges = {hranges, sranges};
        Mat hist = new Mat();
        ArrayList<Mat> tmpList = new ArrayList<Mat>();
        tmpList.add(tmp);
        //computing from 0th and 1th channels
        int[] channels = {0,1};
        Imgproc.calcHist(tmpList, new MatOfInt(channels), new Mat(), hist, new MatOfInt(histSize), new MatOfFloat(), false);
        double maxValue = Core.minMaxLoc(hist).maxVal;

        int scale = 10;
        Mat histimig = Mat.zeros((int)sbins * scale, (int)hbins * 10, CvType.CV_8UC3);

        for (int h = 0; h < hbins; h++){
            for (int s = 0; s < sbins; s++)
            {
                double binVal = hist.get(h,s)[0];
                int intensity = Math.round((int)binVal * 255/(int)maxValue);
                Imgproc.rectangle(histimig, new Point(h * scale, s * scale), new Point((h + 1) * scale - 1, (s + 1) * scale - 1), Scalar.all(intensity), Core.FILLED);
            }
        }
        Imgproc.cvtColor(histimig, histimig, Imgproc.COLOR_BGR2GRAY);
        Imgproc.resize(histimig, tmp, tmp.size());
        transformedImage = tmp.clone();
    }

    public void randomizeHoughCirclesParameters(){
        parameters = new Double[TransformType.HOUGH_CIRCLES_PARAMETER_NUMBER];

        UniformIntegerDistribution threshold1Dist = new UniformIntegerDistribution(50, 85);
        UniformIntegerDistribution threshold2Dist = new UniformIntegerDistribution(5, 20);

        parameters[0] = (double) threshold1Dist.sample();
        parameters[1] = (double) threshold2Dist.sample();
        while (parameters[0] - parameters[1] > 40)
        {
            parameters[0] = (double)threshold1Dist.sample();
            parameters[1] = (double)threshold2Dist.sample();
        }
    }

    public void houghCircles(Mat src){
        Mat tmp = src.clone();

        if (parameters.length != TransformType.HOUGH_CIRCLES_PARAMETER_NUMBER){
            return;
        }

        Imgproc.medianBlur(tmp, tmp ,3);
        double threshold1 = parameters[0];
        double threshold2 = parameters[1];
        int dist = 8;
        int minRad = 1;
        int maxRad = 30;

        Mat circles = new Mat();
        Imgproc.HoughCircles(tmp, circles, Imgproc.CV_HOUGH_GRADIENT, 1, tmp.rows()/dist, threshold1, threshold2, minRad, maxRad);
        Mat tmp1  = new Mat(tmp.rows(), tmp.cols(), CvType.CV_8UC3, new Scalar(0,0,0));
        for(int h = 0; h < circles.height(); h++)
        {
            for(int w = 0; w < circles.width(); w ++){
                Point center = new Point (Math.round(circles.step1(w)),Math.round(circles.step1(h)));
                int radius = Math.round(circles.step1(w));
                Imgproc.circle(tmp1, center, 3, new Scalar(255,255,255), -1, 8, 0);
                Imgproc.circle(tmp1, center, radius, new Scalar(255,255,255), 3,8,0);
            }
        }
        Mat tmp2 = new Mat();
        Imgproc.cvtColor(tmp1, tmp2,  Imgproc.COLOR_BGR2GRAY);
        transformedImage = tmp2.clone();

    }

    public void randomizeNormalizeParameters(){
        parameters = new Double[TransformType.NORMALIZE_PARAMETER_NUMBER];

        UniformIntegerDistribution alphaDist = new UniformIntegerDistribution(0,255);
        UniformIntegerDistribution betaDist = new UniformIntegerDistribution(0,255);
        UniformIntegerDistribution normDist = new UniformIntegerDistribution(1,3);

        parameters[0] = (double) alphaDist.sample();
        parameters[1] = (double) betaDist.sample();
        parameters[2] = (double) normDist.sample();

        if (parameters[2] == 3){
            parameters[2] = (double)4;
        }
    }

    public void normalize(Mat src){
        Mat tmp = src.clone();
        if (parameters.length != TransformType.NORMALIZE_PARAMETER_NUMBER)
            return;
        double alpha = parameters[0];
        double beta = parameters[1];
        double norm = parameters[2];

        Core.normalize(tmp, tmp, alpha, beta, (int)norm);
        transformedImage = tmp.clone();
    }

    public void squareRoot(Mat src){
        Mat tmp = src.clone();
        tmp.convertTo(tmp, CvType.CV_32F, 1.0/255.0,0);

        if (parameters.length !=TransformType.SQUARE_ROOT_PARAMETER_NUMBER)
            return;

        Core.sqrt(tmp,tmp);
        tmp.convertTo(tmp, CvType.CV_8UC1, 255, 0);
        transformedImage = tmp.clone();
    }

    public void randomizeCannyEdgeParameters(){
        parameters = new Double[TransformType.CANNY_EDGE_PARAMETER_NUMBER];

        UniformIntegerDistribution thresholdDist = new UniformIntegerDistribution(0,100);
        UniformIntegerDistribution ratioDist = new UniformIntegerDistribution(0,5);

        parameters[0] = (double)thresholdDist.sample();
        parameters[1] = (double)ratioDist.sample();
    }

    public void cannyEdge(Mat src){
        Mat tmp = src.clone();
        if (parameters.length != TransformType.CANNY_EDGE_PARAMETER_NUMBER)
            return;

        double threshold = parameters[0];
        double ratio = parameters[1];

        Imgproc.blur(tmp, tmp, new Size(3,3));
        Imgproc.Canny(tmp, tmp, (int)threshold, threshold*ratio);
        transformedImage = tmp.clone();
    }

    public void randomizeIntegralImageParameters(){
        parameters = new Double [TransformType.INTEGRAL_IMAGE_PARAMETER_NUMBER];
        UniformIntegerDistribution typeDist = new UniformIntegerDistribution(1,3);
        parameters[0] = (double)typeDist.sample();
    }

    public void integralImage(Mat src){
        Mat tmp = src.clone();
        tmp.convertTo(tmp,CvType.CV_32F, 1.0/255.0);

        if (parameters.length != TransformType.INTEGRAL_IMAGE_PARAMETER_NUMBER)
            return;

        double type = parameters[0];

        Mat tmp1 = new Mat();
        Mat tmp2 = new Mat();
        Mat tmp3 = new Mat();

        Imgproc.integral3(tmp,tmp1,tmp2,tmp3);

        switch ((int)type)
        {
            case 1:
            {
                Imgproc.resize(tmp2,tmp,tmp.size());
                tmp.convertTo(tmp,CvType.CV_8UC1, 255);
                transformedImage = tmp.clone();
            }
            break;
            case 2:
            {
                Imgproc.resize(tmp2,tmp,tmp.size());
                tmp.convertTo(tmp,CvType.CV_8UC1, 255);
                transformedImage = tmp.clone();
            }
            break;
            case 3:
            {
                Imgproc.resize(tmp3,tmp,tmp.size());
                tmp.convertTo(tmp,CvType.CV_8UC1, 255);
                transformedImage = tmp.clone();
            }
            break;
            default:
            {
                transformedImage = src.clone();
            }
            break;
        }
    }

    public void randomizeDifferenceGaussiansParameters(){

        parameters = new Double[TransformType.DIFFERENCE_GAUSSIANS_PARAMETER_NUMBER];

        UniformIntegerDistribution kern1Dist = new UniformIntegerDistribution(1,20);
        UniformIntegerDistribution kern2Dist = new UniformIntegerDistribution(1,20);
        parameters[0] = (double) kern1Dist.sample();
        parameters[1] = (double) kern2Dist.sample();

        if (parameters[0] % 2 ==0)
            parameters[0]++;

        if (parameters[1] % 2 ==0)
            parameters[1]++;
    }

    public void differenceGaussians(Mat src){
        Mat tmp = src.clone();
        tmp.convertTo(tmp,CvType.CV_32F, 1.0/255.0);

        if (parameters.length != TransformType.DIFFERENCE_GAUSSIANS_PARAMETER_NUMBER)
            return;

        Mat g1 = new Mat();
        Mat g2 = new Mat();
        double kern1 = parameters[0];
        double kern2 = parameters[1];

        Imgproc.GaussianBlur(tmp, g1, new Size(kern1, kern2), 0);
        Imgproc.GaussianBlur(tmp, g2, new Size(kern2, kern1), 0);

        Core.subtract(g1,g2,tmp);
        tmp.convertTo(tmp, CvType.CV_8U, 255);
        transformedImage = tmp.clone();
    }

    public void randomizeCensusTransformParameters(){
        parameters = new Double[TransformType.CENSUS_TRANSFORM_PARAMETER_NUMBER];

        UniformIntegerDistribution nDist = new UniformIntegerDistribution(1,15);
        UniformIntegerDistribution mDist = new UniformIntegerDistribution(1,15);
        parameters[0] = (double) nDist.sample();
        parameters[1] = (double) mDist.sample();

        if (parameters[0] % 2 ==0)
            parameters[0]++;

        if (parameters[1] % 2 ==0)
            parameters[1]++;
    }

    public void censusTransform(Mat src){
        Mat tmp = src.clone();
        Mat tmp1 = new Mat();

        if (parameters.length != TransformType.CENSUS_TRANSFORM_PARAMETER_NUMBER)
            return;

        Size imgSize = tmp.size();
        tmp1 = Mat.zeros(imgSize, CvType.CV_8U);

        int census = 0;
        int bit = 0;
        int[] data = new int[]{0};
        int[] data2 = new int[]{0};
        double m = parameters[0];
        double n = parameters[1];
        double i;
        double j;
        double x;
        double y;
        double shiftCount = 0;

        for (x = m/2; x < imgSize.height - m/2; x++)
        {
            for(y = n/2; y < imgSize.width - n/2; y++)
            {
                census = 0;
                shiftCount = 0;
                for (i = x - m/2; i <= x + m/2; i++)
                {
                    for (j = y - n/2; j <= y + n/2; j++)
                    {

                        if(shiftCount != m *n/2) //skip the center pixel
                        {
                            census <<= 1;
                            if(tmp.get((int)i,(int)j, data) < tmp.get((int)x,(int)y, data2)) //compare pixel values in the neighborhood
                                bit = 1;
                            else
                                bit = 0;

                            census = census + bit;
                        }
                        shiftCount ++;
                    }
                }

                //tmp1.ptr<uchar>(x,y) = census;
                tmp1.put((int)x,(int)y, (byte)census);
            }
        }
        transformedImage = tmp1.clone();
    }

    public void randomizeSobelOperatorParameters(){
        parameters = new Double[TransformType.SOBEL_OPERATOR_PARAMETER_NUMBER];

        UniformIntegerDistribution scaleDist = new UniformIntegerDistribution(1,10);
        UniformIntegerDistribution deltaDist = new UniformIntegerDistribution(1,10);
        UniformIntegerDistribution weightDist = new UniformIntegerDistribution(0,1);

        parameters[0] = (double) scaleDist.sample();
        parameters[1] = (double) deltaDist.sample();
        parameters[2] = (double) weightDist.sample();
        parameters[3] = (double) weightDist.sample();
    }

    public void sobelOperator(Mat src){
        Mat tmp = src.clone();

        if (parameters.length != TransformType.SOBEL_OPERATOR_PARAMETER_NUMBER)
            return;

        double scale = parameters[0];
        double delta = parameters[1];
        double xWeight = parameters[2];
        double yWeight = parameters[3];

        Imgproc.GaussianBlur(tmp, tmp, new Size(3,3), 0,0, Core.BORDER_DEFAULT);

        Mat gradX = new Mat();
        Mat gradY = new Mat();
        Mat absGradX = new Mat();
        Mat absGradY = new Mat();

        Imgproc.Sobel(tmp, gradX, CvType.CV_16S, 1,0,3, scale, delta, Core.BORDER_DEFAULT);
        Core.convertScaleAbs(gradX, absGradX);

        Imgproc.Sobel(tmp, gradY, CvType.CV_16S, 0,1,3, scale, delta, Core.BORDER_DEFAULT);
        Core.convertScaleAbs(gradY, absGradY);

        Core.addWeighted(absGradX, xWeight, absGradY, yWeight, 0, tmp);

        tmp.convertTo(tmp, CvType.CV_8UC1);
        transformedImage = tmp.clone();
    }

    public void randomizeMorphologicalDilateParameters (){
        parameters = new Double[TransformType.MORPHOLOGICAL_DILATE_PARAMETER_NUMBER];

        UniformIntegerDistribution elementDist = new UniformIntegerDistribution(0,2);
        UniformIntegerDistribution kernelSizeDist = new UniformIntegerDistribution(0,20);

        int size = kernelSizeDist.sample();
        if (size % 2 == 0)
            size++;

        parameters[0] = (double) elementDist.sample();
        parameters[1] = (double )size;
    }

    public void morphologicalDilate(Mat src){
        Mat tmp = src.clone();

        if (parameters.length != TransformType.MORPHOLOGIAL_ERODE_PARAMETER_NUMBER)
            return;

        double dilateType = parameters[0];
        Size size = new Size(2 * parameters[1] + 1, 2 * parameters[1] + 1);
        Point point = new Point (parameters[1], parameters[1]);

        Imgproc.dilate(tmp, tmp, Imgproc.getStructuringElement((int)dilateType, size, point));

        tmp.convertTo(tmp, CvType.CV_8UC1);
        transformedImage = tmp.clone();
    }

    public void randomizeAdaptiveThresholdingParameters(){
        parameters = new Double[TransformType.ADAPTIVE_THRESHOLDING_PARAMETER_NUMBER];

        UniformIntegerDistribution thresholdDist = new UniformIntegerDistribution(0,255);
        UniformIntegerDistribution methodDist = new UniformIntegerDistribution(0,1);
        UniformIntegerDistribution typeDist = new UniformIntegerDistribution(0,1);
        UniformIntegerDistribution blocksizeDist = new UniformIntegerDistribution(3,20);
        UniformIntegerDistribution cDist = new UniformIntegerDistribution(1,10);

        parameters[0] = (double) thresholdDist.sample();
        parameters[1] = (double) methodDist.sample();
        parameters[2] = (double) typeDist.sample();
        parameters[3] = (double) blocksizeDist.sample();
        parameters[4] = (double) cDist.sample();

        if (parameters[3] % 2 == 0)
            parameters[3]++;
    }

    public void adaptiveThresholding(Mat src){
        Mat tmp = src.clone();

        if (parameters.length != TransformType.ADAPTIVE_THRESHOLDING_PARAMETER_NUMBER)
            return;
        double threshold = parameters[0];
        double method = parameters[1];
        double type = parameters[2];
        double blocksize = parameters[3];
        double c = parameters[4];

        Imgproc.adaptiveThreshold(tmp, tmp, threshold, (int)method, (int)type, (int)blocksize, c);
        transformedImage = tmp.clone();
    }

    public void randomizeHoughLinesParameters (){
        parameters = new Double[TransformType.HOUGH_LINES_PARAMETER_NUMBER];

        UniformIntegerDistribution thresholdDist = new UniformIntegerDistribution(1,100);
        UniformIntegerDistribution minlinelengthDist = new UniformIntegerDistribution(0,100);
        UniformIntegerDistribution maxlinegapDist = new UniformIntegerDistribution(0,200);

        parameters[0] = (double) thresholdDist.sample();
        parameters[1] = (double) minlinelengthDist.sample();
        parameters[2] = (double) maxlinegapDist.sample();
    }

    public void houghLines(Mat src){
        Mat tmp = src.clone();
        Mat tmp1 = src.clone();
        if (parameters.length != TransformType.HOUGH_LINES_PARAMETER_NUMBER)
        return;

        double threshold = parameters[0];
        double minlinelength = parameters[1];
        double maxlinegap = parameters[2];

        Imgproc.Canny(tmp, tmp1, threshold, threshold*3);
        Mat lines = new Mat();
        Imgproc.HoughLinesP(tmp, lines, 1, Math.PI/180, (int)threshold, minlinelength,maxlinegap);

        Mat tmp2 = new Mat(tmp.rows(), tmp.cols(), CvType.CV_8UC1, new Scalar(0));

        transformedImage = tmp2.clone();
    }

    public void randomizeHarrisCornerStrengthParameters(){

        parameters = new Double[TransformType.HARRIS_CORNER_STRENGTH_PARAMETER_NUMBER];

        UniformIntegerDistribution thresholdDist = new UniformIntegerDistribution(3,10);
        UniformIntegerDistribution minlinelengthDist = new UniformIntegerDistribution(10,20);
        UniformIntegerDistribution kDist = new UniformIntegerDistribution(0,1);

        parameters[0] = (double) thresholdDist.sample();
        parameters[1] = (double) minlinelengthDist.sample();
        parameters[2] = (double) kDist.sample();

        if (parameters[1] % 2 == 0)
            parameters[1]++;
    }

    public void harrisCornerStrength(Mat src){
        Mat tmp = src.clone();
        Mat tmp1= src.clone();

        if (parameters.length != TransformType.HARRIS_CORNER_STRENGTH_PARAMETER_NUMBER)
            return;

        double blockSize = parameters[0];
        double apertureSize = parameters[1];
        double k = parameters[2];

        Imgproc.cornerHarris(tmp, tmp, (int)blockSize, (int)apertureSize, k, Core.BORDER_DEFAULT);
        Core.normalize(tmp, tmp, 0, 25, Core.NORM_MINMAX, CvType.CV_32FC1, new Mat());
        Core.convertScaleAbs(tmp,tmp);

        tmp.convertTo(tmp1, CvType.CV_8U, 255);
        transformedImage = tmp1.clone();
    }

    public void histogramEqualization(Mat src)
    {
        Mat tmp = src.clone();
        tmp.convertTo(tmp,CvType.CV_8UC1);

        if (parameters.length != TransformType.HISTOGRAM_EQUALIZATION_PARAMETER_NUMBER)
            return;

        Imgproc.equalizeHist(tmp, tmp);

        tmp.convertTo(tmp,CvType.CV_8UC1);
        transformedImage = tmp.clone();
    }
    public void log(Mat src)
    {
        Mat tmp = src.clone();

        if (parameters.length != TransformType.LOG_PARAMETER_NUMBER)
            return;

        src.convertTo(tmp,CvType.CV_32F);
        Core.log(tmp, tmp);

        tmp.convertTo(tmp,CvType.CV_8UC1);
        transformedImage = tmp.clone();
    }

    public void randomizeMedianBlur(){
        parameters = new Double[TransformType.MEDIAN_BLUR_PARAMETER_NUMBER];

        UniformIntegerDistribution ksizeDist = new UniformIntegerDistribution(0,21);

        parameters[0] = (double) ksizeDist.sample();

        if (parameters[0] % 2 == 0)
            parameters[0]++;
    }

    public void medianBlur(Mat src)
    {
        Mat tmp = src.clone();

        if (parameters.length != TransformType.MEDIAN_BLUR_PARAMETER_NUMBER)
            return;


        double ksize = parameters[0];

        src.convertTo(tmp,CvType.CV_8UC1);
        Imgproc.medianBlur(tmp, tmp, (int)ksize);

        transformedImage = tmp.clone();
    }

    public void randomizeDistanceTransformParameters(){
        parameters = new Double[TransformType.DISTANCE_TRANSFORM_PARAMETER_NUMBER];

        UniformIntegerDistribution distanceDist = new UniformIntegerDistribution(1,3);
        UniformIntegerDistribution maskDist = new UniformIntegerDistribution(0,2);
        UniformIntegerDistribution threshDist = new UniformIntegerDistribution(0,255);

        parameters[0] = (double) distanceDist.sample();
        parameters[1] = (double) maskDist.sample();
        parameters[2] =(double) threshDist.sample();

        if (parameters[1] == 1)
        {
            parameters[1] = 3.0;
        }
        else if (parameters[1] == 2)
        {
            parameters[1] = 5.0;
        }
    }

    public void distanceTranform(Mat src){
        Mat tmp1 = src.clone();
        Mat tmp = src.clone();

        if (parameters.length != TransformType.DISTANCE_TRANSFORM_PARAMETER_NUMBER)
            return;

        double dist = parameters[0];
        double mask = parameters[1];
        double thresh = parameters[2];

        tmp.convertTo(tmp1, CvType.CV_32FC1, 1.0/255.0);
        Imgproc.threshold(tmp, tmp, thresh, 255, Imgproc.THRESH_BINARY);
        Imgproc.distanceTransform(tmp, tmp1, (int)dist, (int)mask);
        Core.normalize(tmp1, tmp, 0, 1.0, Core.NORM_MINMAX);

        tmp1.convertTo(tmp1, CvType.CV_8UC1, 255);
        transformedImage = tmp1.clone();
    }

    public void randomizeLaplacianEdgedDetectionParameters()
    {
        parameters = new Double[TransformType.LAPLACIAN_EDGED_ETECTION_PARAMETER_NUMBER];

        UniformIntegerDistribution ksizeDist = new UniformIntegerDistribution(1,21);
        UniformIntegerDistribution scaleDist = new UniformIntegerDistribution(0,10);
        UniformIntegerDistribution deltaDist = new UniformIntegerDistribution(0,10);

        parameters[0] = (double) ksizeDist.sample();
        parameters[1] = (double) scaleDist.sample();
        parameters[2] = (double) deltaDist.sample();

        if (parameters[0] % 2 == 0)
            parameters[0]++;
    }

    public void laplacianEdgedDetection (Mat src){
        Mat tmp = src.clone();

        if (parameters.length != TransformType.LAPLACIAN_EDGED_ETECTION_PARAMETER_NUMBER)
        return;

        Imgproc.GaussianBlur(tmp, tmp, new Size(3,3), 0,0,Core.BORDER_DEFAULT);

        double kernelSize = parameters[0];
        double scale = parameters[1];
        double delta = parameters[2];

        Imgproc.Laplacian(tmp, tmp, CvType.CV_16S, (int)kernelSize, scale, delta, Core.BORDER_DEFAULT);
        Core.convertScaleAbs(tmp,tmp);

        tmp.convertTo(tmp, CvType.CV_8UC1);
        transformedImage = tmp.clone();
    }

}
