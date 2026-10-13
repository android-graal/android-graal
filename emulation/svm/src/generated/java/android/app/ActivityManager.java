package android.app;

import android.os.Parcelable;

/**
 *  <p>
 *  This class gives information about, and interacts
 *  with, activities, services, and the containing
 *  process.
 *  </p>
 *
 *  <p>
 *  A number of the methods in this class are for
 *  debugging or informational purposes and they should
 *  not be used to affect any runtime behavior of
 *  your app. These methods are called out as such in
 *  the method level documentation.
 *  </p>
 *
 * <p>
 *  Most application developers should not have the need to
 *  use this class, most of whose methods are for specialized
 *  use cases. However, a few methods are more broadly applicable.
 *  For instance, {@link android.app.ActivityManager#isLowRamDevice() isLowRamDevice()}
 *  enables your app to detect whether it is running on a low-memory device,
 *  and behave accordingly.
 *  {@link android.app.ActivityManager#clearApplicationUserData() clearApplicationUserData()}
 *  is for apps with reset-data functionality.
 *  </p>
 *
 *  <p>
 *  In some special use cases, where an app interacts with
 *  its Task stack, the app may use the
 *  {@link android.app.ActivityManager.AppTask} and
 *  {@link android.app.ActivityManager.RecentTaskInfo} inner
 *  classes. However, in general, the methods in this class should
 *  be used for testing and debugging purposes only.
 *  </p>
 */
public final class ActivityManager {
    /**
     * Action an app can implement to handle reports from {@link #setWatchHeapLimit(long)}.
     * If your package has an activity handling this action, it will be launched with the
     * heap data provided to it the same way as {@link Intent#ACTION_SEND}.  Note that to
     * match, the activity must support this action and a MIME type of "*&#47;*".
     */
    public static final String ACTION_REPORT_HEAP_LIMIT = "android.app.action.REPORT_HEAP_LIMIT";

    /**
     * Full lock task mode is active.
     */
    public static final int LOCK_TASK_MODE_LOCKED = 1;

    /**
     * Lock task mode is not active.
     */
    public static final int LOCK_TASK_MODE_NONE = 0;

    /**
     * App pinning mode is active.
     */
    public static final int LOCK_TASK_MODE_PINNED = 2;

    /**
     * <a href="{@docRoot}guide/topics/manifest/meta-data-element.html">{@code
     * <meta-data>}</a> name for a 'home' Activity that declares a package that is to be
     * uninstalled in lieu of the declaring one.  The package named here must be
     * signed with the same certificate as the one declaring the {@code <meta-data>}.
     */
    public static final String META_HOME_ALTERNATE = "android.app.home.alternate";

    /**
     * Flag for {@link #moveTaskToFront(int, int)}: don't count this as a
     * user-instigated action, so the current activity will not receive a
     * hint that the user is leaving.
     */
    public static final int MOVE_TASK_NO_USER_ACTION = 2;

    /**
     * Flag for {@link #moveTaskToFront(int, int)}: also move the "home"
     * activity along with the task, so it is positioned immediately behind
     * the task. This flag is ignored if the task's windowing mode is
     * {@link WindowConfiguration#WINDOWING_MODE_MULTI_WINDOW}.
     */
    public static final int MOVE_TASK_WITH_HOME = 1;

    /**
     * Provides a list that does not contain any
     * recent tasks that currently are not available to the user.
     */
    public static final int RECENT_IGNORE_UNAVAILABLE = 2;

    /**
     * Flag for use with {@link #getRecentTasks}: return all tasks, even those
     * that have set their
     * {@link android.content.Intent#FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS} flag.
     */
    public static final int RECENT_WITH_EXCLUDED = 1;

    private ActivityManager() {
    }

    /**
     * Information you can retrieve about any processes that are in an error condition.
     */
    public static final class ProcessErrorStateInfo implements Parcelable {
        public static final int CRASHED = 1;

        public static final int NOT_RESPONDING = 2;

        /**
         * Condition codes
         */
        public static final int NO_ERROR = 0;

        private ProcessErrorStateInfo() {
        }
    }

    /**
     * Information you can retrieve about a running process.
     */
    public static final class RunningAppProcessInfo implements Parcelable {
        /**
         * @deprecated Renamed to {@link #IMPORTANCE_CACHED}.
         */
        @Deprecated
        public static final int IMPORTANCE_BACKGROUND = 400;

        /**
         * Constant for {@link #importance}: This process contains cached code
         * that is expendable, not actively running any app components we care
         * about.
         */
        public static final int IMPORTANCE_CACHED = 400;

        /**
         * Constant for {@link #importance}: This process is running an
         * application that can not save its state, and thus can't be killed
         * while in the background.  This will be used with apps that have
         * {@link android.R.attr#cantSaveState} set on their application tag.
         */
        public static final int IMPORTANCE_CANT_SAVE_STATE = 350;

        /**
         * Constant for {@link #importance}: This process is empty of any
         * actively running code.
         * @deprecated This value is no longer reported, use {@link #IMPORTANCE_CACHED} instead.
         */
        @Deprecated
        public static final int IMPORTANCE_EMPTY = 500;

        /**
         * Constant for {@link #importance}: This process is running the
         * foreground UI; that is, it is the thing currently at the top of the screen
         * that the user is interacting with.
         */
        public static final int IMPORTANCE_FOREGROUND = 100;

        /**
         * Constant for {@link #importance}: This process is running a foreground
         * service, for example to perform music playback even while the user is
         * not immediately in the app.  This generally indicates that the process
         * is doing something the user actively cares about.
         */
        public static final int IMPORTANCE_FOREGROUND_SERVICE = 125;

        /**
         * Constant for {@link #importance}: This process does not exist.
         */
        public static final int IMPORTANCE_GONE = 1000;

        /**
         * Constant for {@link #importance}: This process is not something the user
         * is directly aware of, but is otherwise perceptible to them to some degree.
         */
        public static final int IMPORTANCE_PERCEPTIBLE = 230;

        /**
         * Constant for {@link #importance}: {@link #IMPORTANCE_PERCEPTIBLE} had this wrong value
         * before {@link Build.VERSION_CODES#O}.  Since the {@link Build.VERSION_CODES#O} SDK,
         * the value of {@link #IMPORTANCE_PERCEPTIBLE} has been fixed.
         *
         * <p>The system will return this value instead of {@link #IMPORTANCE_PERCEPTIBLE}
         * on Android versions below {@link Build.VERSION_CODES#O}.
         *
         * <p>On Android version {@link Build.VERSION_CODES#O} and later, this value will still be
         * returned for apps with the target API level below {@link Build.VERSION_CODES#O}.
         * For apps targeting version {@link Build.VERSION_CODES#O} and later,
         * the correct value {@link #IMPORTANCE_PERCEPTIBLE} will be returned.
         */
        public static final int IMPORTANCE_PERCEPTIBLE_PRE_26 = 130;

        /**
         * Constant for {@link #importance}: This process contains services
         * that should remain running.  These are background services apps have
         * started, not something the user is aware of, so they may be killed by
         * the system relatively freely (though it is generally desired that they
         * stay running as long as they want to).
         */
        public static final int IMPORTANCE_SERVICE = 300;

        /**
         * Constant for {@link #importance}: This process is running the foreground
         * UI, but the device is asleep so it is not visible to the user.  Though the
         * system will try hard to keep its process from being killed, in all other
         * ways we consider it a kind of cached process, with the limitations that go
         * along with that state: network access, running background services, etc.
         */
        public static final int IMPORTANCE_TOP_SLEEPING = 325;

        /**
         * @deprecated Pre-{@link android.os.Build.VERSION_CODES#P} version of
         * {@link #IMPORTANCE_TOP_SLEEPING}.  As of Android
         * {@link android.os.Build.VERSION_CODES#P}, this is considered much less
         * important since we want to reduce what apps can do when the screen is off.
         */
        @Deprecated
        public static final int IMPORTANCE_TOP_SLEEPING_PRE_28 = 150;

        /**
         * Constant for {@link #importance}: This process is running something
         * that is actively visible to the user, though not in the immediate
         * foreground.  This may be running a window that is behind the current
         * foreground (so paused and with its state saved, not interacting with
         * the user, but visible to them to some degree); it may also be running
         * other services under the system's control that it considers important.
         */
        public static final int IMPORTANCE_VISIBLE = 200;

        /**
         * Constant for {@link #importanceReasonCode}: one of the application's
         * content providers is being used by another process.  The pid of
         * the client process is in {@link #importanceReasonPid} and the
         * target provider in this process is in
         * {@link #importanceReasonComponent}.
         */
        public static final int REASON_PROVIDER_IN_USE = 1;

        /**
         * Constant for {@link #importanceReasonCode}: one of the application's
         * content providers is being used by another process.  The pid of
         * the client process is in {@link #importanceReasonPid} and the
         * target provider in this process is in
         * {@link #importanceReasonComponent}.
         */
        public static final int REASON_SERVICE_IN_USE = 2;

        /**
         * Constant for {@link #importanceReasonCode}: nothing special has
         * been specified for the reason for this level.
         */
        public static final int REASON_UNKNOWN = 0;

        private RunningAppProcessInfo() {
        }
    }

    /**
     * Information you can retrieve about a particular Service that is
     * currently running in the system.
     */
    public static final class RunningServiceInfo implements Parcelable {
        /**
         * Bit for {@link #flags}: set if the service has asked to
         * run as a foreground process.
         */
        public static final int FLAG_FOREGROUND = 2;

        /**
         * Bit for {@link #flags}: set if the service is running in a
         * persistent process.
         */
        public static final int FLAG_PERSISTENT_PROCESS = 8;

        /**
         * Bit for {@link #flags}: set if this service has been
         * explicitly started.
         */
        public static final int FLAG_STARTED = 1;

        /**
         * Bit for {@link #flags}: set if the service is running in a
         * core system process.
         */
        public static final int FLAG_SYSTEM_PROCESS = 4;

        private RunningServiceInfo() {
        }
    }
}
