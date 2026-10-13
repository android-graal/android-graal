package android.view;

import android.os.Parcelable;

/**
 * Represents a rounded corner of the display.
 * <p>
 * <img src="{@docRoot}reference/android/images/rounded_corner/rounded-corner-info.png" height="120"
 * alt="A figure to describe what the rounded corner radius and the center point are. "/>
 * </p>
 *
 * <p>Note: The rounded corner formed by the radius and the center is an approximation.</p>
 *
 * <p>{@link RoundedCorner} is immutable.</p>
 */
public final class RoundedCorner implements Parcelable {
    /**
     * The rounded corner is at the bottom-left of the screen.
     */
    public static final int POSITION_BOTTOM_LEFT = 3;

    /**
     * The rounded corner is at the bottom-right of the screen.
     */
    public static final int POSITION_BOTTOM_RIGHT = 2;

    /**
     * The rounded corner is at the top-left of the screen.
     */
    public static final int POSITION_TOP_LEFT = 0;

    /**
     * The rounded corner is at the top-right of the screen.
     */
    public static final int POSITION_TOP_RIGHT = 1;

    private RoundedCorner() {
    }
}
