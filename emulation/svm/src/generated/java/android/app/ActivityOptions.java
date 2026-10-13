package android.app;

/**
 * Helper class for building an options Bundle that can be used with
 * {@link android.content.Context#startActivity(android.content.Intent, android.os.Bundle)
 * Context.startActivity(Intent, Bundle)} and related methods.
 */
public final class ActivityOptions {
    /**
     * A long in the extras delivered by {@link #requestUsageTimeReport} that contains
     * the total time (in ms) the user spent in the app flow.
     */
    public static final String EXTRA_USAGE_TIME_REPORT = "android.activity.usage_time";

    /**
     * A Bundle in the extras delivered by {@link #requestUsageTimeReport} that contains
     * detailed information about the time spent in each package associated with the app;
     * each key is a package name, whose value is a long containing the time (in ms).
     */
    public static final String EXTRA_USAGE_TIME_REPORT_PACKAGES = "android.usage_time_packages";

    /**
     * Grants the {@link PendingIntent} background activity start privileges.
     *
     * This behaves the same as {@link #MODE_BACKGROUND_ACTIVITY_START_ALLOW_ALWAYS}, except it
     * does not grant background activity launch permissions based on the privileged permission
     * <code>START_ACTIVITIES_FROM_BACKGROUND</code>.
     *
     * @deprecated Use {@link #MODE_BACKGROUND_ACTIVITY_START_ALLOW_IF_VISIBLE} to allow starts
     * only when the app is visible or {@link #MODE_BACKGROUND_ACTIVITY_START_ALLOW_ALWAYS} to
     * allow starts at any time (see <a
     * href="https://developer.android.com/guide/components/activities/background-starts">
     * Restrictions on starting activities from the background</a>).
     */
    @Deprecated
    public static final int MODE_BACKGROUND_ACTIVITY_START_ALLOWED = 1;

    /**
     * Grants the {@link PendingIntent} all background activity start privileges, including
     * those normally reserved for privileged contexts (e.g., companion apps or those with the
     * {@code START_ACTIVITIES_FROM_BACKGROUND} permission).
     *
     * <p><b>Caution:</b> This mode should be used sparingly. Most apps should use
     * {@link #MODE_BACKGROUND_ACTIVITY_START_ALLOW_IF_VISIBLE} instead, relying on notifications
     * or foreground services for background interactions to minimize user disruption. However,
     * this mode is necessary for specific use cases, such as companion apps responding to
     * prompts from a connected device.
     *
     * <p>For more information on background activity start restrictions, see:
     * <a href="https://developer.android.com/guide/components/activities/background-starts">
     * Restrictions on starting activities from the background</a>
     */
    public static final int MODE_BACKGROUND_ACTIVITY_START_ALLOW_ALWAYS = 3;

    /**
     * Grants the {@link PendingIntent} background activity start privileges only when the app
     * has a visible window (i.e., is visible to the user). This is the recommended mode for most
     * apps to minimize disruption to the user experience.
     *
     * <p>For more information on background activity start restrictions, see:
     * <a href="https://developer.android.com/guide/components/activities/background-starts">
     * Restrictions on starting activities from the background</a>
     */
    public static final int MODE_BACKGROUND_ACTIVITY_START_ALLOW_IF_VISIBLE = 4;

    /**
     * Denies the {@link PendingIntent} any background activity start privileges.
     */
    public static final int MODE_BACKGROUND_ACTIVITY_START_DENIED = 2;

    /**
     * The system determines whether to grant background activity start privileges. This is the
     * default behavior if no explicit mode is specified.
     */
    public static final int MODE_BACKGROUND_ACTIVITY_START_SYSTEM_DEFINED = 0;

    private ActivityOptions() {
    }
}
