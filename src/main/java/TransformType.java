import java.util.HashMap;

public enum TransformType {

    NIL(0),
    GABOR_FILTER(1),
    MORPHOLOGICAL_ERODE(2),
    GAUSSIAN_BLUR(3),
    HISTOGRAM(4),
    HOUGH_CIRCLES(5),
    NORMALIZE(6),
    SQUARE_ROOT(7),
    CANNY_EDGE(8),
    INTEGRAL_IMAGE(9),
    DIFFERENCE_GAUSSIANS(10),
    CENSUS_TRANSFORM(11),
    SOBEL_OPERATOR(12),
    MORPHOLOGICAL_DILATE(13),
    ADAPTIVE_THRESHOLDING(14),
    HOUGH_LINES(15),
    HARRIS_CORNER_STRENGTH(16),
    HISTOGRAM_EQUALIZATION(17),
    LOG(18),
    MEDIAN_BLUR(19),
    DISTANCE_TRANSFORM(20),
    LAPLACIAN_EDGED_ETECTION(21),
    TRANSORM_NUM(22),
    MINIMUM_TRANFORMS(2),
    MAXIMUM_TRANFORMS(8),
    MAXIMUM_WIDTH(500),
    MAXIMUM_HEIGHT(400);



    private int intValue;
    private static HashMap<Integer,TransformType> mappings;
    private synchronized static HashMap<Integer, TransformType> getMappings()
    {
        if (mappings == null)
        {
            mappings = new HashMap<Integer, TransformType>();
        }
        return mappings;
    }

    private TransformType(int value)
    {
        intValue = value;
        TransformType.getMappings().put(value, this);
    }

    public int getValue()
    {
        return intValue;
    }

    public static TransformType forValue(int value){
        return getMappings().get(value);
    }
    public static final int GABOR_FILTER_PARAMETER_NUMBER = 6;
    public static final int MORPHOLOGIAL_ERODE_PARAMETER_NUMBER = 2;
    public static final int GAUSSIAN_BLUR_PARAMETER_NUMBER = 3;
    public static final int HISTOGRAM_PARAMETER_NUMBER = 2;
    public static final int HOUGH_CIRCLES_PARAMETER_NUMBER = 2;
    public static final int NORMALIZE_PARAMETER_NUMBER = 3;
    public static final int SQUARE_ROOT_PARAMETER_NUMBER = 0;
    public static final int CANNY_EDGE_PARAMETER_NUMBER = 2;
    public static final int INTEGRAL_IMAGE_PARAMETER_NUMBER = 1;
    public static final int DIFFERENCE_GAUSSIANS_PARAMETER_NUMBER = 2;
    public static final int CENSUS_TRANSFORM_PARAMETER_NUMBER = 2;
    public static final int SOBEL_OPERATOR_PARAMETER_NUMBER = 4;
    public static final int MORPHOLOGICAL_DILATE_PARAMETER_NUMBER = 2;
    public static final int ADAPTIVE_THRESHOLDING_PARAMETER_NUMBER = 5;
    public static final int HOUGH_LINES_PARAMETER_NUMBER = 3;
    public static final int HARRIS_CORNER_STRENGTH_PARAMETER_NUMBER = 3;
    public static final int HISTOGRAM_EQUALIZATION_PARAMETER_NUMBER = 0;
    public static final int LOG_PARAMETER_NUMBER = 0;
    public static final int MEDIAN_BLUR_PARAMETER_NUMBER = 1;
    public static final int DISTANCE_TRANSFORM_PARAMETER_NUMBER = 3;
    public static final int LAPLACIAN_EDGED_ETECTION_PARAMETER_NUMBER = 3;



}
