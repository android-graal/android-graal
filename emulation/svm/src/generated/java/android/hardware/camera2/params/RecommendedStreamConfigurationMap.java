package android.hardware.camera2.params;

/**
 * Immutable class to store the recommended stream configurations to set up
 * {@link android.view.Surface Surfaces} for creating a
 * {@link android.hardware.camera2.CameraCaptureSession capture session} with
 * {@link android.hardware.camera2.CameraDevice#createCaptureSession(SessionConfiguration)}.
 *
 * <p>The recommended list does not replace or deprecate the exhaustive complete list found in
 * {@link StreamConfigurationMap}. It is a suggestion about available power and performance
 * efficient stream configurations for a specific use case. Per definition it is only a subset
 * of {@link StreamConfigurationMap} and can be considered by developers for optimization
 * purposes.</p>
 *
 * <p>This also duplicates the minimum frame durations and stall durations from the
 * {@link StreamConfigurationMap} for each format/size combination that can be used to calculate
 * effective frame rate when submitting multiple captures.
 * </p>
 *
 * <p>An instance of this object is available by invoking
 * {@link CameraCharacteristics#getRecommendedStreamConfigurationMap} and passing a respective
 * usecase id. For more information about supported use case constants see
 * {@link #USECASE_PREVIEW}.</p>
 *
 * <pre><code>{@code
 * CameraCharacteristics characteristics = cameraManager.getCameraCharacteristics(cameraId);
 * RecommendedStreamConfigurationMap configs = characteristics.getRecommendedStreamConfigurationMap(
 *         RecommendedStreamConfigurationMap.USECASE_PREVIEW);
 * }</code></pre>
 *
 * @see CameraCharacteristics#getRecommendedStreamConfigurationMap
 * @see CameraDevice#createCaptureSession(SessionConfiguration)
 */
public final class RecommendedStreamConfigurationMap {
    /**
     * If supported, the recommended 10-bit output stream configurations must include
     * a subset of the advertised {@link android.graphics.ImageFormat#YCBCR_P010} and
     * {@link android.graphics.ImageFormat#PRIVATE} outputs that are optimized for power
     * and performance when registered along with a supported 10-bit dynamic range profile.
     * {@see android.hardware.camera2.params.OutputConfiguration#setDynamicRangeProfile} for
     * details.
     */
    public static final int USECASE_10BIT_OUTPUT = 8;

    /**
     * The recommended stream configuration map for use case low latency snapshot must contain
     * subset of configurations with end-to-end latency that does not exceed 200 ms. under standard
     * operating conditions (reasonable light levels, not loaded system). The expected output format
     * will be primarily {@link android.graphics.ImageFormat#JPEG} however other image formats can
     * be present as well.  Even if available for the camera device, high speed and input
     * configurations will be absent. This suggested configuration map may be absent on some devices
     * that can not support any low latency requests.
     */
    public static final int USECASE_LOW_LATENCY_SNAPSHOT = 6;

    /**
     * The recommended stream configuration map for use case preview must contain a subset of
     * efficient, non-stalling configurations that must include both
     * {@link android.graphics.ImageFormat#PRIVATE} and
     * {@link android.graphics.ImageFormat#YUV_420_888} output formats. Even if available for the
     * camera device, high speed or input configurations will be absent.
     */
    public static final int USECASE_PREVIEW = 0;

    /**
     * In case the device supports
     * {@link android.hardware.camera2.CameraMetadata#REQUEST_AVAILABLE_CAPABILITIES_RAW}, the
     * recommended stream configuration map for use case RAW must contain a subset of efficient
     * configurations that include the {@link android.graphics.ImageFormat#RAW_SENSOR} and other
     * RAW output formats. Even if available for the camera device, high speed and input
     * configurations will be absent.
     */
    public static final int USECASE_RAW = 5;

    /**
     * The recommended stream configuration map for recording must contain a subset of efficient
     * video configurations that include {@link android.graphics.ImageFormat#PRIVATE}
     * output format for at least all supported {@link android.media.CamcorderProfile profiles}.
     * High speed configurations if supported will be available as well. Even if available for the
     * camera device, input configurations will be absent.
     */
    public static final int USECASE_RECORD = 1;

    /**
     * The recommended stream configuration map for use case snapshot must contain a subset of
     * efficient still capture configurations that must include
     * {@link android.graphics.ImageFormat#JPEG} output format and at least one configuration with
     * size approximately equal to the sensor pixel array size
     * {@link CameraCharacteristics#SENSOR_INFO_ACTIVE_ARRAY_SIZE}.
     * Even if available for the camera device, high speed or input configurations will be absent.
     */
    public static final int USECASE_SNAPSHOT = 3;

    /**
     * The recommended stream configuration map for use case video snapshot must only contain a
     * subset of efficient liveshot configurations that include
     * {@link android.graphics.ImageFormat#JPEG} output format. The sizes will match at least
     * the maximum resolution of usecase record and will not cause any preview glitches. Even
     * if available for the camera device, high speed or input configurations will be absent.
     */
    public static final int USECASE_VIDEO_SNAPSHOT = 2;

    /**
     * In case the device supports
     * {@link CameraMetadata#REQUEST_AVAILABLE_CAPABILITIES_YUV_REPROCESSING} and/or
     * {@link CameraMetadata#REQUEST_AVAILABLE_CAPABILITIES_PRIVATE_REPROCESSING},
     * the recommended stream configuration map for use case ZSL must contain a subset of efficient
     * configurations that include the suggested input and output format mappings. Even if
     * available for the camera device, high speed configurations will be absent.
     */
    public static final int USECASE_ZSL = 4;

    private RecommendedStreamConfigurationMap() {
    }
}
