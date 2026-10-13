package android.hardware.camera2.params;

/**
 * Describes a face detected in an image.
 */
public final class Face {
    /**
     * The ID is {@code -1} when the optional set of fields is unsupported.
     *
     * @see #getId()
     */
    public static final int ID_UNSUPPORTED = -1;

    /**
     * The maximum possible value for the confidence level.
     *
     * @see #getScore()
     */
    public static final int SCORE_MAX = 100;

    /**
     * The minimum possible value for the confidence level.
     *
     * @see #getScore()
     */
    public static final int SCORE_MIN = 1;

    private Face() {
    }
}
