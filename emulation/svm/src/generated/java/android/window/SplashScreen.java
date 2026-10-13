package android.window;

/**
 * The interface that apps use to talk to the splash screen.
 * <p>
 * Each splash screen instance is bound to a particular {@link Activity}.
 * To obtain a {@link SplashScreen} for an Activity, use
 * <code>Activity.getSplashScreen()</code> to get the SplashScreen.</p>
 */
public interface SplashScreen {
    /**
     * Flag to be used with {@link ActivityOptions#setSplashScreenStyle}, to show the splash screen
     * icon of the launched activity.
     */
    int SPLASH_SCREEN_STYLE_ICON = 1;

    /**
     * Flag to be used with {@link ActivityOptions#setSplashScreenStyle}, to avoid showing the
     * splash screen icon of the launched activity
     */
    int SPLASH_SCREEN_STYLE_SOLID_COLOR = 0;
}
