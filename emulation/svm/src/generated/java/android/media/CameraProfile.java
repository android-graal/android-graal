package android.media;

/**
 * The CameraProfile class is used to retrieve the pre-defined still image
 * capture (jpeg) quality levels (0-100) used for low, medium, and high
 * quality settings in the Camera application.
 */
public final class CameraProfile {
    public static final int QUALITY_HIGH = 2;

    /**
     * Define three quality levels for JPEG image encoding.
     */
    public static final int QUALITY_LOW = 0;

    public static final int QUALITY_MEDIUM = 1;

    private CameraProfile() {
    }
}
