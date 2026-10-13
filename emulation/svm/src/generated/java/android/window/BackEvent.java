package android.window;

/**
 * Object used to report back gesture progress.
 * Holds information about the touch event, swipe direction and the animation progress that
 * predictive back animations should seek to.
 */
public final class BackEvent {
    /**
     * Indicates that the edge swipe starts from the left edge of the screen
     */
    public static final int EDGE_LEFT = 0;

    /**
     * Indicates that the back event was not triggered by an edge swipe back gesture. This applies
     * to cases like using the back button in 3-button navigation or pressing a hardware back
     * button.
     */
    public static final int EDGE_NONE = 2;

    /**
     * Indicates that the edge swipe starts from the right edge of the screen
     */
    public static final int EDGE_RIGHT = 1;

    private BackEvent() {
    }
}
