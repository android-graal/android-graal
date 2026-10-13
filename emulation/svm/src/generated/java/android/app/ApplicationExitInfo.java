package android.app;

import android.os.Parcelable;

/**
 * Describes the information of an application process's death.
 *
 * <p>
 * Application process could die for many reasons, for example {@link #REASON_LOW_MEMORY}
 * when it was killed by the system because it was running low on memory. Reason
 * of the death can be retrieved via {@link #getReason}. Besides the reason, there are a few other
 * auxiliary APIs like {@link #getStatus} and {@link #getImportance} to help the caller with
 * additional diagnostic information.
 * </p>
 */
public final class ApplicationExitInfo implements Parcelable {
    /**
     * Application process was killed due to being unresponsive (ANR).
     */
    public static final int REASON_ANR = 6;

    /**
     * Application process died because of an unhandled exception in Java code.
     */
    public static final int REASON_CRASH = 4;

    /**
     * Application process died because of a native code crash.
     */
    public static final int REASON_CRASH_NATIVE = 5;

    /**
     * Application process was killed because its dependency was going away, for example,
     * a stable content provider connection's client will be killed if the provider is killed.
     */
    public static final int REASON_DEPENDENCY_DIED = 12;

    /**
     * Application process was killed by the system due to excessive resource usage.
     */
    public static final int REASON_EXCESSIVE_RESOURCE_USAGE = 9;

    /**
     * Application process exit normally by itself, for example,
     * via {@link java.lang.System#exit}; {@link #getStatus} will specify the exit code.
     *
     * <p>Applications should normally not do this, as the system has a better knowledge
     * in terms of process management.</p>
     */
    public static final int REASON_EXIT_SELF = 1;

    /**
     * Application process was killed by App Freezer, for example, because it receives
     * sync binder transactions while being frozen.
     */
    public static final int REASON_FREEZER = 14;

    /**
     * Application process was killed because of initialization failure,
     * for example, it took too long to attach to the system during the start,
     * or there was an error during initialization.
     */
    public static final int REASON_INITIALIZATION_FAILURE = 7;

    /**
     * Application process was killed by the system low memory killer, meaning the system was
     * under memory pressure at the time of kill.
     *
     * <p class="note">
     * Not all devices support reporting {@link #REASON_LOW_MEMORY}; on a device with no such
     * support, when a process is killed due to memory pressure, the {@link #getReason} will return
     * {@link #REASON_SIGNALED} and {@link #getStatus} will return
     * the value {@link android.system.OsConstants#SIGKILL}.
     *
     * Application should use {@link android.app.ActivityManager#isLowMemoryKillReportSupported()
     * ActivityManager.isLowMemoryKillReportSupported()} to check
     * if the device supports reporting {@link #REASON_LOW_MEMORY} or not.
     * </p>
     */
    public static final int REASON_LOW_MEMORY = 3;

    /**
     * Application process was killed by the system for various other reasons which are
     * not by problems in apps and not actionable by apps, for example, the system just
     * finished updates; {@link #getDescription} will specify the cause given by the system.
     */
    public static final int REASON_OTHER = 13;

    /**
     * Application process was killed because the app was disabled, or any of its
     * component states have changed without {@link android.content.pm.PackageManager#DONT_KILL_APP}
     * <p>
     * Prior to {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE},
     * {@link #REASON_USER_REQUESTED} was used to indicate that an app was updated.
     */
    public static final int REASON_PACKAGE_STATE_CHANGE = 15;

    /**
     * Application process was killed because it was updated.
     * <p>
     * Prior to {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE},
     * {@link #REASON_USER_REQUESTED} was used to indicate that an app was updated.
     */
    public static final int REASON_PACKAGE_UPDATED = 16;

    /**
     * Application process was killed due to a runtime permission change.
     */
    public static final int REASON_PERMISSION_CHANGE = 8;

    /**
     * Application process died due to the result of an OS signal; for example,
     * {@link android.system.OsConstants#SIGKILL}; {@link #getStatus} will specify the signal
     * number.
     */
    public static final int REASON_SIGNALED = 2;

    /**
     * Application process died due to unknown reason.
     */
    public static final int REASON_UNKNOWN = 0;

    /**
     * Application process was killed because of the user request, for example,
     * user clicked the "Force stop" button of the application in the Settings,
     * or removed the application away from Recents.
     * <p>
     * Prior to {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE}, one of the uses of this
     * reason was to indicate that an app was killed due to it being updated or any of its component
     * states have changed without {@link android.content.pm.PackageManager#DONT_KILL_APP}
     */
    public static final int REASON_USER_REQUESTED = 10;

    /**
     * Application process was killed, because the user it is running as on devices
     * with mutlple users, was stopped.
     */
    public static final int REASON_USER_STOPPED = 11;

    private ApplicationExitInfo() {
    }
}
