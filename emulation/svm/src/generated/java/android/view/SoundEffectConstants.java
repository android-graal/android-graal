package android.view;

/**
 * Constants to be used to play sound effects via {@link View#playSoundEffect(int)}
 */
public final class SoundEffectConstants {
    public static final int CLICK = 0;

    /**
     * Effect id for a navigation down
     */
    public static final int NAVIGATION_DOWN = 4;

    /**
     * Effect id for a navigation left
     */
    public static final int NAVIGATION_LEFT = 1;

    /**
     * Effect id for a repeatedly triggered navigation down, e.g. due to long pressing a button
     */
    public static final int NAVIGATION_REPEAT_DOWN = 8;

    /**
     * Effect id for a repeatedly triggered navigation left, e.g. due to long pressing a button
     */
    public static final int NAVIGATION_REPEAT_LEFT = 5;

    /**
     * Effect id for a repeatedly triggered navigation right, e.g. due to long pressing a button
     */
    public static final int NAVIGATION_REPEAT_RIGHT = 7;

    /**
     * Effect id for a repeatedly triggered navigation up, e.g. due to long pressing a button
     */
    public static final int NAVIGATION_REPEAT_UP = 6;

    /**
     * Effect id for a navigation right
     */
    public static final int NAVIGATION_RIGHT = 3;

    /**
     * Effect id for a navigation up
     */
    public static final int NAVIGATION_UP = 2;

    private SoundEffectConstants() {
    }
}
