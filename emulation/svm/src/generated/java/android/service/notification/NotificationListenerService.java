package android.service.notification;

/**
 * A service that receives calls from the system when new notifications are
 * posted or removed, or their ranking changed.
 * <p>To extend this class, you must declare the service in your manifest file with
 * the {@link android.Manifest.permission#BIND_NOTIFICATION_LISTENER_SERVICE} permission
 * and include an intent filter with the {@link #SERVICE_INTERFACE} action. For example:</p>
 * <pre>
 * &lt;service android:name=".NotificationListener"
 *          android:label="&#64;string/service_name"
 *          android:exported="false"
 *          android:permission="android.permission.BIND_NOTIFICATION_LISTENER_SERVICE">
 *     &lt;intent-filter>
 *         &lt;action android:name="android.service.notification.NotificationListenerService" />
 *     &lt;/intent-filter>
 *     &lt;meta-data
 *               android:name="android.service.notification.default_filter_types"
 *               android:value="conversations|alerting">
 *           &lt;/meta-data>
 *     &lt;meta-data
 *               android:name="android.service.notification.disabled_filter_types"
 *               android:value="ongoing|silent">
 *           &lt;/meta-data>
 * &lt;/service></pre>
 *
 * <p>The service should wait for the {@link #onListenerConnected()} event
 * before performing any operations. The {@link #requestRebind(ComponentName)}
 * method is the <i>only</i> one that is safe to call before {@link #onListenerConnected()}
 * or after {@link #onListenerDisconnected()}.
 * </p>
 * <p> Notification listeners cannot get notification access or be bound by the system on
 * {@linkplain ActivityManager#isLowRamDevice() low-RAM} devices running Android Q (and below).
 * The system also ignores notification listeners running in a work profile. A
 * {@link android.app.admin.DevicePolicyManager} might block notifications originating from a work
 * profile.</p>
 * <p>
 *     From {@link Build.VERSION_CODES#N} onward all callbacks are called on the main thread. Prior
 *     to N, there is no guarantee on what thread the callback will happen.
 * </p>
 */
public final class NotificationListenerService {
    /**
     * An optional activity intent action that shows additional settings for what notifications
     * should be processed by this notification listener service. If defined, the OS may link to
     * this activity from the system notification listener service filter settings page.
     */
    public static final String ACTION_SETTINGS_HOME = "android.service.notification.action.SETTINGS_HOME";

    /**
     * A flag value indicating that this notification listener can see altering type notifications.
     */
    public static final int FLAG_FILTER_TYPE_ALERTING = 2;

    /**
     * A flag value indicating that this notification listener can see conversation type
     * notifications.
     */
    public static final int FLAG_FILTER_TYPE_CONVERSATIONS = 1;

    /**
     * A flag value indicating that this notification listener can see important
     * ( > {@link NotificationManager#IMPORTANCE_MIN}) ongoing type notifications.
     */
    public static final int FLAG_FILTER_TYPE_ONGOING = 8;

    /**
     * A flag value indicating that this notification listener can see silent type notifications.
     */
    public static final int FLAG_FILTER_TYPE_SILENT = 4;

    /**
     * {@link #getCurrentListenerHints() Listener hints} constant - the primary device UI
     *  should disable phone call sounds, but not notification sound.
     *  This does not change the interruption filter, only the effects.
     */
    public static final int HINT_HOST_DISABLE_CALL_EFFECTS = 4;

    /**
     * {@link #getCurrentListenerHints() Listener hints} constant - the primary device UI
     *  should disable notification sound, vibrating and other visual or aural effects.
     *  This does not change the interruption filter, only the effects.
     */
    public static final int HINT_HOST_DISABLE_EFFECTS = 1;

    /**
     * {@link #getCurrentListenerHints() Listener hints} constant - the primary device UI
     *  should disable notification sound, but not phone calls.
     *  This does not change the interruption filter, only the effects.
     */
    public static final int HINT_HOST_DISABLE_NOTIFICATION_EFFECTS = 2;

    /**
     * {@link #getCurrentInterruptionFilter() Interruption filter} constant -
     *     Alarms only interruption filter.
     */
    public static final int INTERRUPTION_FILTER_ALARMS = 4;

    /**
     * {@link #getCurrentInterruptionFilter() Interruption filter} constant -
     *     Normal interruption filter.
     */
    public static final int INTERRUPTION_FILTER_ALL = 1;

    /**
     * {@link #getCurrentInterruptionFilter() Interruption filter} constant -
     *     No interruptions filter.
     */
    public static final int INTERRUPTION_FILTER_NONE = 3;

    /**
     * {@link #getCurrentInterruptionFilter() Interruption filter} constant -
     *     Priority interruption filter.
     */
    public static final int INTERRUPTION_FILTER_PRIORITY = 2;

    /**
     * {@link #getCurrentInterruptionFilter() Interruption filter} constant - returned when
     *  the value is unavailable for any reason.  For example, before the notification listener
     *  is connected.
     *
     *  {@see #onListenerConnected()}
     */
    public static final int INTERRUPTION_FILTER_UNKNOWN = 0;

    /**
     * The name of the {@code meta-data} tag containing a boolean value that is used to decide if
     * this listener should be automatically bound by default.
     * If the value is 'false', the listener can be bound on demand using {@link #requestRebind}
     * <p>An absent value means that the default is 'true'</p>
     */
    public static final String META_DATA_DEFAULT_AUTOBIND = "android.service.notification.default_autobind_listenerservice";

    /**
     * The name of the {@code meta-data} tag containing a pipe separated list of default
     * integer notification types or "ongoing", "conversations", "alerting", or "silent"
     * that should be provided to this listener. See
     * {@link #FLAG_FILTER_TYPE_ONGOING},
     * {@link #FLAG_FILTER_TYPE_CONVERSATIONS}, {@link #FLAG_FILTER_TYPE_ALERTING),
     * and {@link #FLAG_FILTER_TYPE_SILENT}.
     * <p>This value will only be read if the app has not previously specified a default type list,
     * and if the user has not overridden the allowed types.</p>
     * <p>An absent value means 'allow all types'.
     * A present but empty value means 'allow no types'.</p>
     */
    public static final String META_DATA_DEFAULT_FILTER_TYPES = "android.service.notification.default_filter_types";

    /**
     * The name of the {@code meta-data} tag containing a comma separated list of default
     * integer notification types that this listener never wants to receive. See
     * {@link #FLAG_FILTER_TYPE_ONGOING},
     * {@link #FLAG_FILTER_TYPE_CONVERSATIONS}, {@link #FLAG_FILTER_TYPE_ALERTING),
     * and {@link #FLAG_FILTER_TYPE_SILENT}.
     * <p>Types provided in this list will appear as 'off' and 'disabled' in the user interface,
     * so users don't enable a type that the listener will never bridge to their paired devices.</p>
     */
    public static final String META_DATA_DISABLED_FILTER_TYPES = "android.service.notification.disabled_filter_types";

    /**
     * Channel or group modification reason provided to
     * {@link #onNotificationChannelModified(String, UserHandle,NotificationChannel, int)} or
     * {@link #onNotificationChannelGroupModified(String, UserHandle, NotificationChannelGroup,
     * int)}- the provided object was created.
     */
    public static final int NOTIFICATION_CHANNEL_OR_GROUP_ADDED = 1;

    /**
     * Channel or group modification reason provided to
     * {@link #onNotificationChannelModified(String, UserHandle, NotificationChannel, int)} or
     * {@link #onNotificationChannelGroupModified(String, UserHandle, NotificationChannelGroup,
     * int)}- the provided object was deleted.
     */
    public static final int NOTIFICATION_CHANNEL_OR_GROUP_DELETED = 3;

    /**
     * Channel or group modification reason provided to
     * {@link #onNotificationChannelModified(String, UserHandle, NotificationChannel, int)} or
     * {@link #onNotificationChannelGroupModified(String, UserHandle,NotificationChannelGroup, int)}
     * - the provided object was updated.
     */
    public static final int NOTIFICATION_CHANNEL_OR_GROUP_UPDATED = 2;

    /**
     * Notification was canceled by the app canceling this specific notification.
     */
    public static final int REASON_APP_CANCEL = 8;

    /**
     * Notification was canceled by the app cancelling all its notifications.
     */
    public static final int REASON_APP_CANCEL_ALL = 9;

    /**
     * Notification was canceled due to an assistant adjustment update.
     */
    public static final int REASON_ASSISTANT_CANCEL = 22;

    /**
     * Notification was canceled by the status bar reporting a user dismissal.
     */
    public static final int REASON_CANCEL = 2;

    /**
     * Notification was canceled by the status bar reporting a user dismiss all.
     */
    public static final int REASON_CANCEL_ALL = 3;

    /**
     * Notification was canceled by the user banning the channel.
     */
    public static final int REASON_CHANNEL_BANNED = 17;

    /**
     * Notification was canceled due to the backing channel being deleted
     */
    public static final int REASON_CHANNEL_REMOVED = 20;

    /**
     * Notification was canceled due to the app's storage being cleared
     */
    public static final int REASON_CLEAR_DATA = 21;

    /**
     * Notification was canceled by the status bar reporting a notification click.
     */
    public static final int REASON_CLICK = 1;

    /**
     * Notification was canceled by the status bar reporting an inflation error.
     */
    public static final int REASON_ERROR = 4;

    /**
     * Notification was canceled because it was an invisible member of a group.
     */
    public static final int REASON_GROUP_OPTIMIZATION = 13;

    /**
     * Notification was canceled because it was a member of a canceled group.
     */
    public static final int REASON_GROUP_SUMMARY_CANCELED = 12;

    /**
     * Notification was canceled by a listener reporting a user dismissal.
     */
    public static final int REASON_LISTENER_CANCEL = 10;

    /**
     * Notification was canceled by a listener reporting a user dismiss all.
     */
    public static final int REASON_LISTENER_CANCEL_ALL = 11;

    /**
     * Notification was canceled when entering lockdown mode, which turns off
     * Smart Lock, fingerprint unlocking, and notifications on the lock screen.
     * All the listeners shall ensure the canceled notifications are indeed removed
     * on their end to prevent data leaking.
     * When the user exits the lockdown mode, the removed notifications (due to lockdown)
     * will be restored via NotificationListeners#notifyPostedLocked()
     */
    public static final int REASON_LOCKDOWN = 23;

    /**
     * Notification was canceled by the user banning the package.
     */
    public static final int REASON_PACKAGE_BANNED = 7;

    /**
     * Notification was canceled by the package manager modifying the package.
     */
    public static final int REASON_PACKAGE_CHANGED = 5;

    /**
     * Notification was canceled by the device administrator suspending the package.
     */
    public static final int REASON_PACKAGE_SUSPENDED = 14;

    /**
     * Notification was canceled by the owning managed profile being turned off.
     */
    public static final int REASON_PROFILE_TURNED_OFF = 15;

    /**
     * Notification was snoozed.
     */
    public static final int REASON_SNOOZED = 18;

    /**
     * Notification was canceled due to timeout
     */
    public static final int REASON_TIMEOUT = 19;

    /**
     * Autobundled summary notification was canceled because its group was unbundled
     */
    public static final int REASON_UNAUTOBUNDLED = 16;

    /**
     * Notification was canceled by the owning user context being stopped.
     */
    public static final int REASON_USER_STOPPED = 6;

    /**
     * The {@link Intent} that must be declared as handled by the service.
     */
    public static final String SERVICE_INTERFACE = "android.service.notification.NotificationListenerService";

    /**
     * Whether notification suppressed by DND should not interruption visually when the screen is
     * off.
     *
     * @deprecated Use the more specific visual effects in {@link NotificationManager.Policy}.
     */
    @Deprecated
    public static final int SUPPRESSED_EFFECT_SCREEN_OFF = 1;

    /**
     * Whether notification suppressed by DND should not interruption visually when the screen is
     * on.
     *
     * @deprecated Use the more specific visual effects in {@link NotificationManager.Policy}.
     */
    @Deprecated
    public static final int SUPPRESSED_EFFECT_SCREEN_ON = 2;

    private NotificationListenerService() {
    }

    /**
     * Stores ranking related information on a currently active notification.
     *
     * <p>
     * Ranking objects aren't automatically updated as notification events
     * occur. Instead, ranking information has to be retrieved again via the
     * current {@link RankingMap}.
     */
    public static final class Ranking {
        /**
         * The user is likely to have a negative reaction to this notification.
         */
        public static final int USER_SENTIMENT_NEGATIVE = -1;

        /**
         * It is not known how the user will react to this notification.
         */
        public static final int USER_SENTIMENT_NEUTRAL = 0;

        /**
         * The user is likely to have a positive reaction to this notification.
         */
        public static final int USER_SENTIMENT_POSITIVE = 1;

        /**
         * Value signifying that the user and device policy manager have not expressed a lockscreen
         * visibility override for a notification.
         */
        public static final int VISIBILITY_NO_OVERRIDE = -1000;

        private Ranking() {
        }
    }
}
