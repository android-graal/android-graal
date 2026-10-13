package android.accessibilityservice;

import android.os.Parcelable;

/**
 * This class describes the magnification config for {@link AccessibilityService} to control the
 * magnification.
 *
 * <p>
 * When the magnification config uses {@link #MAGNIFICATION_MODE_DEFAULT},
 * {@link AccessibilityService} will be able to control the activated magnifier on the display.
 * If there is no magnifier activated, it controls the last-activated magnification mode.
 * If there is no magnifier activated before, it controls full-screen magnifier by default.
 * </p>
 *
 * <p>
 * When the magnification config uses {@link #MAGNIFICATION_MODE_FULLSCREEN}.
 * {@link AccessibilityService} will be able to control full-screen magnifier on the display.
 * </p>
 *
 * <p>
 * When the magnification config uses {@link #MAGNIFICATION_MODE_WINDOW} and the platform
 * supports {@link android.content.pm.PackageManager#FEATURE_WINDOW_MAGNIFICATION} feature.
 * {@link AccessibilityService} will be able to control window magnifier on the display.
 * </p>
 *
 * <p>
 * If the other magnification configs, scale centerX and centerY, are not set by the
 * {@link Builder}, the configs should be current values or default values. And the center
 * position ordinarily is the center of the screen.
 * </p>
 */
public final class MagnificationConfig implements Parcelable {
    /**
     * The controlling magnification mode. It controls the activated magnifier.
     */
    public static final int MAGNIFICATION_MODE_DEFAULT = 0;

    /**
     * The controlling magnification mode. It controls full-screen magnifier.
     */
    public static final int MAGNIFICATION_MODE_FULLSCREEN = 1;

    /**
     * The controlling magnification mode. It is valid if the platform supports
     * {@link android.content.pm.PackageManager#FEATURE_WINDOW_MAGNIFICATION} feature.
     */
    public static final int MAGNIFICATION_MODE_WINDOW = 2;

    private MagnificationConfig() {
    }
}
