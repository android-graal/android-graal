package android.gesture;

/**
 * GestureLibrary maintains gesture examples and makes predictions on a new
 * gesture
 */
public final class GestureStore {
    public static final int ORIENTATION_INVARIANT = 1;

    public static final int ORIENTATION_SENSITIVE = 2;

    public static final int SEQUENCE_INVARIANT = 1;

    public static final int SEQUENCE_SENSITIVE = 2;

    private GestureStore() {
    }
}
