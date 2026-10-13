package android.hardware.camera2.params;

/**
 * An immutable class to represent a rectangle {@code (x, y, width, height)} with an additional
 * weight component.
 * <p>
 * The rectangle is defined to be inclusive of the specified coordinates.
 * </p>
 * <p>
 * When used with a {@link CaptureRequest}, the coordinate system is based on the active pixel
 * array, with {@code (0,0)} being the top-left pixel in the
 * {@link CameraCharacteristics#SENSOR_INFO_ACTIVE_ARRAY_SIZE active pixel array}, and
 * {@code (android.sensor.info.activeArraySize.width - 1,
 * android.sensor.info.activeArraySize.height - 1)} being the bottom-right pixel in the active pixel
 * array.
 * </p>
 * <p>
 * The weight must range from {@value #METERING_WEIGHT_MIN} to {@value #METERING_WEIGHT_MAX}
 * inclusively, and represents a weight for every pixel in the area. This means that a large
 * metering area with the same weight as a smaller area will have more effect in the metering
 * result. Metering areas can partially overlap and the camera device will add the weights in the
 * overlap rectangle.
 * </p>
 * <p>
 * If all rectangles have 0 weight, then no specific metering area needs to be used by the camera
 * device. If the metering rectangle is outside the used android.scaler.cropRegion returned in
 * capture result metadata, the camera device will ignore the sections outside the rectangle and
 * output the used sections in the result metadata.
 * </p>
 */
public final class MeteringRectangle {
    /**
     * Weights set to this value will cause the camera device to ignore this rectangle.
     * If all metering rectangles are weighed with 0, the camera device will choose its own metering
     * rectangles.
     */
    public static final int METERING_WEIGHT_DONT_CARE = 0;

    /**
     * The maximum value of valid metering weight.
     */
    public static final int METERING_WEIGHT_MAX = 1000;

    /**
     * The minimum value of valid metering weight.
     */
    public static final int METERING_WEIGHT_MIN = 0;

    private MeteringRectangle() {
    }
}
