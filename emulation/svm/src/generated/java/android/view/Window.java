package android.view;

/**
 * Abstract base class for a top-level window look and behavior policy.  An
 * instance of this class should be used as the top-level view added to the
 * window manager. It provides standard UI policies such as a background, title
 * area, default key processing, etc.
 *
 * <p>The framework will instantiate an implementation of this class on behalf of the application.
 */
public final class Window {
    /**
     * Flag for letting the theme drive the color of the window caption controls. Use with
     * {@link #setDecorCaptionShade(int)}. This is the default value.
     */
    public static final int DECOR_CAPTION_SHADE_AUTO = 0;

    /**
     * Flag for setting dark-color controls on the window caption. Use with
     * {@link #setDecorCaptionShade(int)}.
     */
    public static final int DECOR_CAPTION_SHADE_DARK = 2;

    /**
     * Flag for setting light-color controls on the window caption. Use with
     * {@link #setDecorCaptionShade(int)}.
     */
    public static final int DECOR_CAPTION_SHADE_LIGHT = 1;

    /**
     * The default features enabled.
     * @deprecated use {@link #getDefaultFeatures(android.content.Context)} instead.
     */
    @Deprecated
    protected static final int DEFAULT_FEATURES = 65;

    /**
     * Flag for enabling the Action Bar.
     * This is enabled by default for some devices. The Action Bar
     * replaces the title bar and provides an alternate location
     * for an on-screen menu button on some devices.
     */
    public static final int FEATURE_ACTION_BAR = 8;

    /**
     * Flag for requesting an Action Bar that overlays window content.
     * Normally an Action Bar will sit in the space above window content, but if this
     * feature is requested along with {@link #FEATURE_ACTION_BAR} it will be layered over
     * the window content itself. This is useful if you would like your app to have more control
     * over how the Action Bar is displayed, such as letting application content scroll beneath
     * an Action Bar with a transparent background or otherwise displaying a transparent/translucent
     * Action Bar over application content.
     *
     * <p>This mode is especially useful with {@link View#SYSTEM_UI_FLAG_FULLSCREEN
     * View.SYSTEM_UI_FLAG_FULLSCREEN}, which allows you to seamlessly hide the
     * action bar in conjunction with other screen decorations.
     *
     * <p>As of {@link android.os.Build.VERSION_CODES#JELLY_BEAN}, when an
     * ActionBar is in this mode it will adjust the insets provided to
     * {@link View#fitSystemWindows(android.graphics.Rect) View.fitSystemWindows(Rect)}
     * to include the content covered by the action bar, so you can do layout within
     * that space.
     */
    public static final int FEATURE_ACTION_BAR_OVERLAY = 9;

    /**
     * Flag for specifying the behavior of action modes when an Action Bar is not present.
     * If overlay is enabled, the action mode UI will be allowed to cover existing window content.
     */
    public static final int FEATURE_ACTION_MODE_OVERLAY = 10;

    /**
     * Enables Activities to run Activity Transitions either through sending or receiving
     * ActivityOptions bundle created with
     * {@link android.app.ActivityOptions#makeSceneTransitionAnimation(android.app.Activity,
     * android.util.Pair[])} or {@link android.app.ActivityOptions#makeSceneTransitionAnimation(
     * android.app.Activity, View, String)}.
     */
    public static final int FEATURE_ACTIVITY_TRANSITIONS = 13;

    /**
     * Flag for requesting that window content changes should be animated using a
     * TransitionManager.
     *
     * <p>The TransitionManager is set using
     * {@link #setTransitionManager(android.transition.TransitionManager)}. If none is set,
     * a default TransitionManager will be used.</p>
     *
     * @see #setContentView
     */
    public static final int FEATURE_CONTENT_TRANSITIONS = 12;

    /**
     * Flag for the context menu.  This is enabled by default.
     */
    public static final int FEATURE_CONTEXT_MENU = 6;

    /**
     * Flag for custom title. You cannot combine this feature with other title features.
     */
    public static final int FEATURE_CUSTOM_TITLE = 7;

    /**
     * Flag for indeterminate progress.
     *
     * @deprecated No longer supported starting in API 21.
     */
    @Deprecated
    public static final int FEATURE_INDETERMINATE_PROGRESS = 5;

    /**
     * Flag for having an icon on the left side of the title bar
     */
    public static final int FEATURE_LEFT_ICON = 3;

    /**
     * Flag for the "no title" feature, turning off the title at the top
     *   of the screen.
     */
    public static final int FEATURE_NO_TITLE = 1;

    /**
     * Flag for the "options panel" feature.  This is enabled by default.
     */
    public static final int FEATURE_OPTIONS_PANEL = 0;

    /**
     * Flag for the progress indicator feature.
     *
     * @deprecated No longer supported starting in API 21.
     */
    @Deprecated
    public static final int FEATURE_PROGRESS = 2;

    /**
     * Flag for having an icon on the right side of the title bar
     */
    public static final int FEATURE_RIGHT_ICON = 4;

    /**
     * Flag for requesting a decoration-free window that is dismissed by swiping from the left.
     *
     * @deprecated Swipe-to-dismiss isn't functional anymore.
     */
    @Deprecated
    public static final int FEATURE_SWIPE_TO_DISMISS = 11;

    /**
     * The ID that the main layout in the XML layout file should have.
     */
    public static final int ID_ANDROID_CONTENT = 16908290;

    /**
     * The transitionName for the navigation bar background View when a custom background is used.
     * @see android.view.Window#setNavigationBarColor(int)
     */
    public static final String NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME = "android:navigation:background";

    /**
     * Ending value for the (primary) progress.
     *
     * @deprecated {@link #FEATURE_PROGRESS} and related methods are no longer
     *             supported starting in API 21.
     */
    @Deprecated
    public static final int PROGRESS_END = 10000;

    /**
     * Flag for setting the progress bar's indeterminate mode off.
     *
     * @deprecated {@link #FEATURE_INDETERMINATE_PROGRESS} and related methods
     *             are no longer supported starting in API 21.
     */
    @Deprecated
    public static final int PROGRESS_INDETERMINATE_OFF = -4;

    /**
     * Flag for setting the progress bar's indeterminate mode on.
     *
     * @deprecated {@link #FEATURE_INDETERMINATE_PROGRESS} and related methods
     *             are no longer supported starting in API 21.
     */
    @Deprecated
    public static final int PROGRESS_INDETERMINATE_ON = -3;

    /**
     * Highest possible value for the secondary progress.
     *
     * @deprecated {@link #FEATURE_PROGRESS} and related methods are no longer
     *             supported starting in API 21.
     */
    @Deprecated
    public static final int PROGRESS_SECONDARY_END = 30000;

    /**
     * Lowest possible value for the secondary progress.
     *
     * @deprecated {@link #FEATURE_PROGRESS} and related methods are no longer
     *             supported starting in API 21.
     */
    @Deprecated
    public static final int PROGRESS_SECONDARY_START = 20000;

    /**
     * Starting value for the (primary) progress.
     *
     * @deprecated {@link #FEATURE_PROGRESS} and related methods are no longer
     *             supported starting in API 21.
     */
    @Deprecated
    public static final int PROGRESS_START = 0;

    /**
     * Flag for setting the progress bar's visibility to GONE.
     *
     * @deprecated {@link #FEATURE_PROGRESS} and related methods are no longer
     *             supported starting in API 21.
     */
    @Deprecated
    public static final int PROGRESS_VISIBILITY_OFF = -2;

    /**
     * Flag for setting the progress bar's visibility to VISIBLE.
     *
     * @deprecated {@link #FEATURE_PROGRESS} and related methods are no longer
     *             supported starting in API 21.
     */
    @Deprecated
    public static final int PROGRESS_VISIBILITY_ON = -1;

    /**
     * The transitionName for the status bar background View when a custom background is used.
     * @see android.view.Window#setStatusBarColor(int)
     */
    public static final String STATUS_BAR_BACKGROUND_TRANSITION_NAME = "android:status:background";

    private Window() {
    }
}
