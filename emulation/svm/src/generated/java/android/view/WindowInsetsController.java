package android.view;

/**
 * Interface to control windows that generate insets.
 *
 * For guidance, see <a href="https://developer.android.com/develop/ui/views/layout/immersive">
 * Hide system bars for immersive mode</a>.
 */
public interface WindowInsetsController {
    /**
     * When {@link WindowInsetsController#APPEARANCE_TRANSPARENT_CAPTION_BAR_BACKGROUND} is set,
     * changes the foreground color of the caption bars so that the items on the bar can be read
     * clearly on light backgrounds.
     */
    int APPEARANCE_LIGHT_CAPTION_BARS = 256;

    /**
     * Changes the foreground color for light navigation bars so that the items on the bar can be
     * read clearly.
     */
    int APPEARANCE_LIGHT_NAVIGATION_BARS = 16;

    /**
     * Changes the foreground color for light status bars so that the items on the bar can be read
     * clearly.
     */
    int APPEARANCE_LIGHT_STATUS_BARS = 8;

    /**
     * Makes the caption bar transparent.
     */
    int APPEARANCE_TRANSPARENT_CAPTION_BAR_BACKGROUND = 128;

    /**
     * The default option for {@link #setSystemBarsBehavior(int)}: Window would like to remain
     * interactive when hiding navigation bars by calling {@link #hide(int)} or
     * {@link WindowInsetsAnimationController#setInsetsAndAlpha(Insets, float, float)}.
     *
     * <p>When system bars are hidden in this mode, they can be revealed with system gestures, such
     * as swiping from the edge of the screen where the bar is hidden from.</p>
     *
     * <p>When the gesture navigation is enabled, the system gestures can be triggered regardless
     * the visibility of system bars.</p>
     */
    int BEHAVIOR_DEFAULT = 1;

    /**
     * Option for {@link #setSystemBarsBehavior(int)}: Window would like to remain interactive when
     * hiding navigation bars by calling {@link #hide(int)} or
     * {@link WindowInsetsAnimationController#setInsetsAndAlpha(Insets, float, float)}.
     *
     * <p>When system bars are hidden in this mode, they can be revealed with system gestures, such
     * as swiping from the edge of the screen where the bar is hidden from.</p>
     * @deprecated Use {@link #BEHAVIOR_DEFAULT} instead.
     */
    @Deprecated
    int BEHAVIOR_SHOW_BARS_BY_SWIPE = 1;

    /**
     * Option for {@link #setSystemBarsBehavior(int)}. System bars will be forcibly shown on any
     * user interaction on the corresponding display if navigation bars are hidden by
     * {@link #hide(int)} or
     * {@link WindowInsetsAnimationController#setInsetsAndAlpha(Insets, float, float)}.
     * @deprecated This is not supported on Android {@link Build.VERSION_CODES#S} and later. Use
     *             {@link #BEHAVIOR_DEFAULT} or {@link #BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE}
     *             instead.
     */
    @Deprecated
    int BEHAVIOR_SHOW_BARS_BY_TOUCH = 0;

    /**
     * Option for {@link #setSystemBarsBehavior(int)}: Window would like to remain interactive when
     * hiding navigation bars by calling {@link #hide(int)} or
     * {@link WindowInsetsAnimationController#setInsetsAndAlpha(Insets, float, float)}.
     *
     * <p>When system bars are hidden in this mode, they can be revealed temporarily with system
     * gestures, such as swiping from the edge of the screen where the bar is hidden from. These
     * transient system bars will overlay app’s content, may have some degree of transparency, and
     * will automatically hide after a short timeout.</p>
     */
    int BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE = 2;
}
