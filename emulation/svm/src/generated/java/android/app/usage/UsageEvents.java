package android.app.usage;

import android.os.Parcelable;

/**
 * A result returned from {@link android.app.usage.UsageStatsManager#queryEvents(long, long)}
 * from which to read {@link android.app.usage.UsageEvents.Event} objects.
 */
public final class UsageEvents implements Parcelable {
    private UsageEvents() {
    }

    /**
     * An event representing a state change for a component.
     */
    public static final class Event {
        /**
         * An event type denoting that an {@link android.app.Activity} moved to the background.
         * This event has a package name and class name associated with it and can be retrieved
         * using {@link #getPackageName()} and {@link #getClassName()}.
         * If a package has multiple activities, this event is reported for each activity that moves
         * to background.
         * This event is corresponding to {@link android.app.Activity#onPause()} of the activity's
         * lifecycle.
         */
        public static final int ACTIVITY_PAUSED = 2;

        /**
         * An event type denoting that an {@link android.app.Activity} moved to the foreground.
         * This event has a package name and class name associated with it and can be retrieved
         * using {@link #getPackageName()} and {@link #getClassName()}.
         * If a package has multiple activities, this event is reported for each activity that moves
         * to foreground.
         * This event is corresponding to {@link android.app.Activity#onResume()} of the
         * activity's lifecycle.
         */
        public static final int ACTIVITY_RESUMED = 1;

        /**
         * An activity becomes invisible on the UI, corresponding to
         * {@link android.app.Activity#onStop()} of the activity's lifecycle.
         */
        public static final int ACTIVITY_STOPPED = 23;

        /**
         * An event type denoting that the device configuration has changed.
         */
        public static final int CONFIGURATION_CHANGE = 5;

        /**
         * An event type denoting that the Android runtime underwent a shutdown process.
         * A DEVICE_SHUTDOWN event should be treated as if all started activities and foreground
         * services are now stopped and no explicit {@link #ACTIVITY_STOPPED} and
         * {@link #FOREGROUND_SERVICE_STOP} events will be generated for them.
         *
         * <p>The DEVICE_SHUTDOWN timestamp is actually the last time UsageStats database is
         * persisted before the actual shutdown. Events (if there are any) between this timestamp
         * and the actual shutdown is not persisted in the database. So any open events without
         * matching close events between DEVICE_SHUTDOWN and {@link #DEVICE_STARTUP} should be
         * ignored because the closing time is unknown.</p>
         */
        public static final int DEVICE_SHUTDOWN = 26;

        /**
         * An event type denoting that the Android runtime started up. This could be after a
         * shutdown or a runtime restart. Any open events without matching close events between
         * {@link #DEVICE_SHUTDOWN} and DEVICE_STARTUP should be ignored because the closing time is
         * unknown.
         */
        public static final int DEVICE_STARTUP = 27;

        /**
         * An event type denoting start of a foreground service.
         * This event has a package name and class name associated with it and can be retrieved
         * using {@link #getPackageName()} and {@link #getClassName()}.
         * If a package has multiple foreground services, this event is reported for each service
         * that is started.
         */
        public static final int FOREGROUND_SERVICE_START = 19;

        /**
         * An event type denoting stop of a foreground service.
         * This event has a package name and class name associated with it and can be retrieved
         * using {@link #getPackageName()} and {@link #getClassName()}.
         * If a package has multiple foreground services, this event is reported for each service
         * that is stopped.
         */
        public static final int FOREGROUND_SERVICE_STOP = 20;

        /**
         * An event type denoting that the screen's keyguard has been hidden.  This typically
         * happens when the user unlocks their phone after turning it on.
         */
        public static final int KEYGUARD_HIDDEN = 18;

        /**
         * An event type denoting that the screen's keyguard has been shown, whether or not
         * the screen is off.
         */
        public static final int KEYGUARD_SHOWN = 17;

        /**
         * @deprecated by {@link #ACTIVITY_PAUSED}
         */
        @Deprecated
        public static final int MOVE_TO_BACKGROUND = 2;

        /**
         * @deprecated by {@link #ACTIVITY_RESUMED}
         */
        @Deprecated
        public static final int MOVE_TO_FOREGROUND = 1;

        /**
         * No event type.
         */
        public static final int NONE = 0;

        /**
         * An event type denoting that the screen has gone in to an interactive state (turned
         * on for full user interaction, not ambient display or other non-interactive state).
         */
        public static final int SCREEN_INTERACTIVE = 15;

        /**
         * An event type denoting that the screen has gone in to a non-interactive state
         * (completely turned off or turned on only in a non-interactive state like ambient
         * display).
         */
        public static final int SCREEN_NON_INTERACTIVE = 16;

        /**
         * An event type denoting that an action equivalent to a ShortcutInfo is taken by the user.
         *
         * @see android.content.pm.ShortcutManager#reportShortcutUsed(String)
         */
        public static final int SHORTCUT_INVOCATION = 8;

        /**
         * An event type denoting a change in App Standby Bucket. The new bucket can be
         * retrieved by calling {@link #getAppStandbyBucket()}.
         *
         * @see UsageStatsManager#getAppStandbyBucket()
         */
        public static final int STANDBY_BUCKET_CHANGED = 11;

        /**
         * An event type denoting that a package was interacted with in some way by the user.
         */
        public static final int USER_INTERACTION = 7;

        private Event() {
        }
    }
}
