package android.view.animation;

/**
 * Defines the transformation to be applied at
 * one point in time of an Animation.
 */
public final class Transformation {
    /**
     * Indicates a transformation that applies an alpha only (uses an identity matrix.)
     */
    public static final int TYPE_ALPHA = 1;

    /**
     * Indicates a transformation that applies an alpha and a matrix.
     */
    public static final int TYPE_BOTH = 3;

    /**
     * Indicates a transformation that has no effect (alpha = 1 and identity matrix.)
     */
    public static final int TYPE_IDENTITY = 0;

    /**
     * Indicates a transformation that applies a matrix only (alpha = 1.)
     */
    public static final int TYPE_MATRIX = 2;

    private Transformation() {
    }
}
