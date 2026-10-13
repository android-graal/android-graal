package android.hardware.camera2.params;

/**
 * Immutable class to store a 4-element vector of floats indexable by a bayer RAW 2x2 pixel block.
 */
public final class RggbChannelVector {
    /**
     * Blue color channel in a bayer Raw pattern.
     */
    public static final int BLUE = 3;

    /**
     * The number of color channels in this vector.
     */
    public static final int COUNT = 4;

    /**
     * Green color channel in a bayer Raw pattern used by the even rows.
     */
    public static final int GREEN_EVEN = 1;

    /**
     * Green color channel in a bayer Raw pattern used by the odd rows.
     */
    public static final int GREEN_ODD = 2;

    /**
     * Red color channel in a bayer Raw pattern.
     */
    public static final int RED = 0;

    private RggbChannelVector() {
    }
}
