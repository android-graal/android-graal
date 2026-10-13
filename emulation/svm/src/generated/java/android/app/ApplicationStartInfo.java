package android.app;

import android.os.Parcelable;

/**
 * Describes information related to an application process's startup.
 *
 * <p>
 * Many aspects concerning why and how an applications process was started are valuable for apps
 * both for logging and for potential behavior changes. Reason for process start, start type,
 * start times, throttling, and other useful diagnostic data can be obtained from
 * {@link ApplicationStartInfo} records.
 * </p>
 *
 * <p>
 *  ApplicationStartInfo objects can be retrieved via:
 *  - {@link ActivityManager#getHistoricalProcessStartReasons}, which can be called during or after
 *      a application's startup. Using this method, an app can retrieve information about an
 *      in-progress app start.
 *  - {@link ActivityManager#addApplicationStartInfoCompletionListener}, which returns an
 *      ApplicationStartInfo object via a callback when the startup is complete, or immediately
 *      if requested after the startup is complete.
 * </p>
 */
public final class ApplicationStartInfo implements Parcelable {
    /**
     * The system creates the activity at the root of a new task or locates the activity on an
     * existing task with the same affinity. If an instance of the activity already exists and is at
     * the root of the task, the system routes the intent to existing instance through a call to its
     * onNewIntent() method, rather than creating a new one.
     */
    public static final int LAUNCH_MODE_SINGLE_INSTANCE = 2;

    /**
     * The activity can only be running as the root activity of the task, the first activity that
     * created the task, and therefore there will only be one instance of this activity in a task;
     * but activity can be instantiated multiple times in different tasks.
     */
    public static final int LAUNCH_MODE_SINGLE_INSTANCE_PER_TASK = 4;

    /**
     * Same as "singleTask", except that the system doesn't launch any other activities into the
     * task holding the instance. The activity is always the single and only member of its task.
     */
    public static final int LAUNCH_MODE_SINGLE_TASK = 3;

    /**
     * If an instance of the activity already exists at the top of the target task, the system
     * routes the intent to that instance through a call to its onNewIntent() method, rather than
     * creating a new instance of the activity.
     */
    public static final int LAUNCH_MODE_SINGLE_TOP = 1;

    /**
     * Default. The system always creates a new instance of the activity in the target task and
     * routes the intent to it.
     */
    public static final int LAUNCH_MODE_STANDARD = 0;

    /**
     * State indicating process startup has failed. Startup information in
     * {@link ApplicationStartInfo} is incomplete, but no more will be added.
     */
    public static final int STARTUP_STATE_ERROR = 1;

    /**
     * State indicating process startup has made it to first frame draw. Startup
     * information in {@link ApplicationStartInfo} is complete with potential exception
     * of fully drawn timestamp which is not guaranteed to be set.
     */
    public static final int STARTUP_STATE_FIRST_FRAME_DRAWN = 2;

    /**
     * State indicating process startup has started. Some information is available in
     * {@link ApplicationStartInfo} and more will be added.
     */
    public static final int STARTUP_STATE_STARTED = 0;

    /**
     * Process was started for an activity component.
     */
    public static final int START_COMPONENT_ACTIVITY = 1;

    /**
     * Process was started for a broadcast component.
     */
    public static final int START_COMPONENT_BROADCAST = 2;

    /**
     * Process was started for a content provider component.
     */
    public static final int START_COMPONENT_CONTENT_PROVIDER = 3;

    /**
     * Process was started not for one of the four standard components.
     */
    public static final int START_COMPONENT_OTHER = 5;

    /**
     * Process was started for a service component.
     */
    public static final int START_COMPONENT_SERVICE = 4;

    /**
     * Process started due to alarm.
     */
    public static final int START_REASON_ALARM = 0;

    /**
     * Process started to run backup.
     */
    public static final int START_REASON_BACKUP = 1;

    /**
     * Process started due to boot complete.
     */
    public static final int START_REASON_BOOT_COMPLETE = 2;

    /**
     * Process started due to broadcast received for any reason not explicitly listed.
     */
    public static final int START_REASON_BROADCAST = 3;

    /**
     * Process started due to access of ContentProvider for any reason not explicitly listed.
     */
    public static final int START_REASON_CONTENT_PROVIDER = 4;

    /**
     * Process started to run scheduled job.
     */
    public static final int START_REASON_JOB = 5;

    /**
     * Process started due to click app icon or widget from launcher.
     */
    public static final int START_REASON_LAUNCHER = 6;

    /**
     * Process started from launcher recents.
     */
    public static final int START_REASON_LAUNCHER_RECENTS = 7;

    /**
     * Process started not for any of the listed reasons.
     */
    public static final int START_REASON_OTHER = 8;

    /**
     * Process started due to push message.
     */
    public static final int START_REASON_PUSH = 9;

    /**
     * Process started due to Service started for any reason not explicitly listed..
     */
    public static final int START_REASON_SERVICE = 10;

    /**
     * Process started due to Activity started for any reason not explicitly listed.
     */
    public static final int START_REASON_START_ACTIVITY = 11;

    /**
     * Clock monotonic timestamp of Application onCreate called.
     */
    public static final int START_TIMESTAMP_APPLICATION_ONCREATE = 2;

    /**
     * Clock monotonic timestamp of bindApplication called.
     */
    public static final int START_TIMESTAMP_BIND_APPLICATION = 3;

    /**
     * Clock monotonic timestamp of first frame drawn.
     */
    public static final int START_TIMESTAMP_FIRST_FRAME = 4;

    /**
     * Clock monotonic timestamp of process fork.
     */
    public static final int START_TIMESTAMP_FORK = 1;

    /**
     * Clock monotonic timestamp of reportFullyDrawn called by application.
     */
    public static final int START_TIMESTAMP_FULLY_DRAWN = 5;

    /**
     * Clock monotonic timestamp of initial renderthread frame.
     */
    public static final int START_TIMESTAMP_INITIAL_RENDERTHREAD_FRAME = 6;

    /**
     * Clock monotonic timestamp of launch started.
     */
    public static final int START_TIMESTAMP_LAUNCH = 0;

    /**
     * The end of the range reserved for developer supplied timestamps.
     */
    public static final int START_TIMESTAMP_RESERVED_RANGE_DEVELOPER = 30;

    /**
     * The beginning of the range reserved for developer supplied timestamps.
     */
    public static final int START_TIMESTAMP_RESERVED_RANGE_DEVELOPER_START = 21;

    /**
     * The end of the range, beginning with 0, reserved for system timestamps.
     */
    public static final int START_TIMESTAMP_RESERVED_RANGE_SYSTEM = 20;

    /**
     * Clock monotonic timestamp of surfaceflinger composition complete.
     */
    public static final int START_TIMESTAMP_SURFACEFLINGER_COMPOSITION_COMPLETE = 7;

    /**
     * Process started from scratch.
     */
    public static final int START_TYPE_COLD = 1;

    /**
     * Process brought back to foreground.
     */
    public static final int START_TYPE_HOT = 3;

    /**
     * Start type not yet set.
     */
    public static final int START_TYPE_UNSET = 0;

    /**
     * Process retained minimally SavedInstanceState.
     */
    public static final int START_TYPE_WARM = 2;

    private ApplicationStartInfo() {
    }
}
