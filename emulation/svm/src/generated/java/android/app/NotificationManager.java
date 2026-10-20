package android.app;

import android.os.Parcelable;

/**
 * Class to notify the user of events that happen.  This is how you tell
 * the user that something has happened in the background.
 *
 * <p>Notifications can take different forms:
 * <ul>
 *      <li>A persistent icon that goes in the status bar and is accessible
 *          through the launcher, (when the user selects it, a designated Intent
 *          can be launched),</li>
 *      <li>Turning on or flashing LEDs on the device, or</li>
 *      <li>Alerting the user by flashing the backlight, playing a sound,
 *          or vibrating.</li>
 * </ul>
 *
 * <p>
 * Each of the notify methods takes an int id parameter and optionally a
 * {@link String} tag parameter, which may be {@code null}.  These parameters
 * are used to form a pair (tag, id), or ({@code null}, id) if tag is
 * unspecified.  This pair identifies this notification from your app to the
 * system, so that pair should be unique within your app.  If you call one
 * of the notify methods with a (tag, id) pair that is currently active and
 * a new set of notification parameters, it will be updated.  For example,
 * if you pass a new status bar icon, the old icon in the status bar will
 * be replaced with the new one.  This is also the same tag and id you pass
 * to the {@link #cancel(int)} or {@link #cancel(String, int)} method to clear
 * this notification.
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For a guide to creating notifications, read the
 * <a href="{@docRoot}guide/topics/ui/notifiers/notifications.html">Status Bar Notifications</a>
 * developer guide.</p>
 * </div>
 *
 * @see android.app.Notification
 */
public final class NotificationManager {
    /**
     * Intent that is broadcast when an application is blocked or unblocked.
     *
     * This broadcast is only sent to the app whose block state has changed.
     *
     * Input: nothing
     * Output: {@link #EXTRA_BLOCKED_STATE}
     */
    public static final String ACTION_APP_BLOCK_STATE_CHANGED = "android.app.action.APP_BLOCK_STATE_CHANGED";

    /**
     * Activity Action: Launch an Automatic Zen Rule configuration screen
     *
     * <p> Input: Optionally, {@link #EXTRA_AUTOMATIC_RULE_ID}, if the configuration screen for an
     * existing rule should be displayed. If the rule id is missing or null, apps should display
     * a configuration screen where users can create a new instance of the rule.
     *
     * <p> Output: Nothing
     *
     * <p> You can have multiple activities handling this intent, if you support multiple
     * {@link AutomaticZenRule rules}. In order for the system to properly display all of your
     * rule types so that users can create new instances or configure existing ones, you need
     * to add some extra metadata ({@link #META_DATA_AUTOMATIC_RULE_TYPE})
     * to your activity tag in your manifest. If you'd like to limit the number of rules a user
     * can create from this flow, you can additionally optionally include
     * {@link #META_DATA_RULE_INSTANCE_LIMIT}. For example,
     *
     * <pre>
     *     {@code
     *     <meta-data
     *         android:name="android.service.zen.automatic.ruleType"
     *         android:value="@string/my_condition_rule" />
     *     <meta-data
     *         android:name="android.service.zen.automatic.ruleInstanceLimit"
     *         android:value="1" />
     *     }
     * </pre>
     *
     * @see #addAutomaticZenRule(AutomaticZenRule)
     */
    public static final String ACTION_AUTOMATIC_ZEN_RULE = "android.app.action.AUTOMATIC_ZEN_RULE";

    /**
     * Intent that is broadcast when the status of an {@link AutomaticZenRule} has changed.
     *
     * <p>Use this to know whether you need to continue monitor to device state in order to
     * provide up-to-date states (with {@link #setAutomaticZenRuleState(String, Condition)}) for
     * this rule.</p>
     *
     * Input: nothing
     * Output: {@link #EXTRA_AUTOMATIC_ZEN_RULE_ID}
     * Output: {@link #EXTRA_AUTOMATIC_ZEN_RULE_STATUS}
     */
    public static final String ACTION_AUTOMATIC_ZEN_RULE_STATUS_CHANGED = "android.app.action.AUTOMATIC_ZEN_RULE_STATUS_CHANGED";

    /**
     * Intent that is broadcast when the state of {@link #getConsolidatedNotificationPolicy()}
     * changes.
     *
     * <p>This broadcast is only sent to registered receivers and receivers in packages that have
     * been granted Notification Policy access (see {@link #isNotificationPolicyAccessGranted()}).
     */
    public static final String ACTION_CONSOLIDATED_NOTIFICATION_POLICY_CHANGED = "android.app.action.CONSOLIDATED_NOTIFICATION_POLICY_CHANGED";

    /**
     * Intent that is broadcast when the state of getCurrentInterruptionFilter() changes.
     *
     * <p>This broadcast is only sent to registered receivers and (starting from
     * {@link Build.VERSION_CODES#Q}) receivers in packages that have been granted Do Not
     * Disturb access (see {@link #isNotificationPolicyAccessGranted()}).
     */
    public static final String ACTION_INTERRUPTION_FILTER_CHANGED = "android.app.action.INTERRUPTION_FILTER_CHANGED";

    /**
     * Intent that is broadcast when a {@link NotificationChannel} is blocked
     * (when {@link NotificationChannel#getImportance()} is {@link #IMPORTANCE_NONE}) or unblocked
     * (when {@link NotificationChannel#getImportance()} is anything other than
     * {@link #IMPORTANCE_NONE}).
     *
     * This broadcast is only sent to the app that owns the channel that has changed.
     *
     * Input: nothing
     * Output: {@link #EXTRA_NOTIFICATION_CHANNEL_ID}
     * Output: {@link #EXTRA_BLOCKED_STATE}
     */
    public static final String ACTION_NOTIFICATION_CHANNEL_BLOCK_STATE_CHANGED = "android.app.action.NOTIFICATION_CHANNEL_BLOCK_STATE_CHANGED";

    /**
     * Intent that is broadcast when a {@link NotificationChannelGroup} is
     * {@link NotificationChannelGroup#isBlocked() blocked} or unblocked.
     *
     * This broadcast is only sent to the app that owns the channel group that has changed.
     *
     * Input: nothing
     * Output: {@link #EXTRA_NOTIFICATION_CHANNEL_GROUP_ID}
     * Output: {@link #EXTRA_BLOCKED_STATE}
     */
    public static final String ACTION_NOTIFICATION_CHANNEL_GROUP_BLOCK_STATE_CHANGED = "android.app.action.NOTIFICATION_CHANNEL_GROUP_BLOCK_STATE_CHANGED";

    /**
     * Intent that is broadcast when the state of {@link #isNotificationPolicyAccessGranted()}
     * changes.
     *
     * This broadcast is only sent to registered receivers, and only to the apps that have changed.
     */
    public static final String ACTION_NOTIFICATION_POLICY_ACCESS_GRANTED_CHANGED = "android.app.action.NOTIFICATION_POLICY_ACCESS_GRANTED_CHANGED";

    /**
     * Intent that is broadcast when the state of {@link #getNotificationPolicy()} changes.
     *
     * <p>This broadcast is only sent to registered receivers and (starting from
     * {@link Build.VERSION_CODES#Q}) receivers in packages that have been granted Do Not
     * Disturb access (see {@link #isNotificationPolicyAccessGranted()}).
     *
     * <p>Starting with {@link Build.VERSION_CODES#VANILLA_ICE_CREAM}, most calls to
     * {@link #setNotificationPolicy(Policy)} will update the app's implicit rule policy instead of
     * the global policy, so this broadcast will be sent much less frequently.
     */
    public static final String ACTION_NOTIFICATION_POLICY_CHANGED = "android.app.action.NOTIFICATION_POLICY_CHANGED";

    /**
     * Constant value for {@link #EXTRA_AUTOMATIC_ZEN_RULE_STATUS} - the given rule has been
     * activated by the user or cross device sync. Sent from
     * {@link Build.VERSION_CODES#VANILLA_ICE_CREAM}. If the rule owner has a mode that includes
     * a DND component, the rule owner should activate any extra behavior that's part of that mode
     * in response to this broadcast.
     */
    public static final int AUTOMATIC_RULE_STATUS_ACTIVATED = 4;

    /**
     * Constant value for {@link #EXTRA_AUTOMATIC_ZEN_RULE_STATUS} - the given rule has been
     * deactivated ("snoozed") by the user. The rule will not return to an activated state until
     * the app calls {@link #setAutomaticZenRuleState(String, Condition)} with
     * {@link Condition#STATE_FALSE} (either immediately or when the trigger criteria is no
     * longer met) and then {@link Condition#STATE_TRUE} when the trigger criteria is freshly met,
     * or when the user re-activates it.
     */
    public static final int AUTOMATIC_RULE_STATUS_DEACTIVATED = 5;

    /**
     * Constant value for {@link #EXTRA_AUTOMATIC_ZEN_RULE_STATUS} - the given rule currently
     * exists but is disabled. You do not need to continue to provide state changes via
     * {@link #setAutomaticZenRuleState(String, Condition)} until the rule is reenabled.
     */
    public static final int AUTOMATIC_RULE_STATUS_DISABLED = 2;

    /**
     * Constant value for {@link #EXTRA_AUTOMATIC_ZEN_RULE_STATUS} - the given rule currently
     * exists and is enabled. You should continue to provide state changes via
     * {@link #setAutomaticZenRuleState(String, Condition)}.
     */
    public static final int AUTOMATIC_RULE_STATUS_ENABLED = 1;

    /**
     * Constant value for {@link #EXTRA_AUTOMATIC_ZEN_RULE_STATUS} - the given rule has been
     * deleted. Further calls to {@link #setAutomaticZenRuleState(String, Condition)} will be
     * ignored.
     */
    public static final int AUTOMATIC_RULE_STATUS_REMOVED = 3;

    /**
     * Constant value for {@link #EXTRA_AUTOMATIC_ZEN_RULE_STATUS} - the current status of the
     * rule is unknown at your target sdk version, and you should continue to provide state changes
     * via {@link #setAutomaticZenRuleState(String, Condition)}.
     */
    public static final int AUTOMATIC_RULE_STATUS_UNKNOWN = -1;

    /**
     * Indicates that all bubbles are allowed from the app. If the app sends bubbles, the bubble
     * will appear along with the notification.
     */
    public static final int BUBBLE_PREFERENCE_ALL = 1;

    /**
     * Indicates that the no bubbles are allowed from the app. If the app sends bubbles, only the
     * notification will appear. The notification will have an affordance allowing the user to
     * bubble it. If the user selects this affordance, that notification is approved to bubble
     * and the apps' bubble preference will be upgraded to {@link #BUBBLE_PREFERENCE_SELECTED}.
     */
    public static final int BUBBLE_PREFERENCE_NONE = 0;

    /**
     * Indicates that only notifications selected by the user will appear as bubbles. If
     * the app sends bubbles that haven't been selected, only the notification appear. If the
     * bubble has been approved by the user, it will appear along with the notification.
     */
    public static final int BUBBLE_PREFERENCE_SELECTED = 2;

    /**
     * Used as an optional string extra on {@link #ACTION_AUTOMATIC_ZEN_RULE} intents. If
     * provided, contains the id of the {@link AutomaticZenRule} (as returned from
     * {@link NotificationManager#addAutomaticZenRule(AutomaticZenRule)}) for which configuration
     * settings should be displayed.
     */
    public static final String EXTRA_AUTOMATIC_RULE_ID = "android.app.extra.AUTOMATIC_RULE_ID";

    /**
     * String extra for {@link #ACTION_AUTOMATIC_ZEN_RULE_STATUS_CHANGED} containing the id of the
     * {@link AutomaticZenRule} (see {@link #addAutomaticZenRule(AutomaticZenRule)}) that has
     * changed.
     */
    public static final String EXTRA_AUTOMATIC_ZEN_RULE_ID = "android.app.extra.AUTOMATIC_ZEN_RULE_ID";

    /**
     * Integer extra for {@link #ACTION_AUTOMATIC_ZEN_RULE_STATUS_CHANGED} containing the state of
     * the {@link AutomaticZenRule}.
     *
     * <p>The value will be one of {@link #AUTOMATIC_RULE_STATUS_ENABLED},
     * {@link #AUTOMATIC_RULE_STATUS_DISABLED}, {@link #AUTOMATIC_RULE_STATUS_REMOVED},
     * {@link #AUTOMATIC_RULE_STATUS_ACTIVATED}, {@link #AUTOMATIC_RULE_STATUS_DEACTIVATED}, or
     * {@link #AUTOMATIC_RULE_STATUS_UNKNOWN}.
     *
     * <p>Note that the {@link #AUTOMATIC_RULE_STATUS_ACTIVATED} and
     * {@link #AUTOMATIC_RULE_STATUS_DEACTIVATED} statuses are only sent to packages targeting
     * {@link Build.VERSION_CODES#VANILLA_ICE_CREAM} and above; apps targeting a lower SDK version
     * will be sent {@link #AUTOMATIC_RULE_STATUS_UNKNOWN} in their place instead.
     */
    public static final String EXTRA_AUTOMATIC_ZEN_RULE_STATUS = "android.app.extra.AUTOMATIC_ZEN_RULE_STATUS";

    /**
     * Extra for {@link #ACTION_NOTIFICATION_CHANNEL_BLOCK_STATE_CHANGED} or
     * {@link #ACTION_NOTIFICATION_CHANNEL_GROUP_BLOCK_STATE_CHANGED} containing the new blocked
     * state as a boolean.
     *
     * The value will be {@code true} if this channel or group is now blocked and {@code false} if
     * this channel or group is now unblocked.
     */
    public static final String EXTRA_BLOCKED_STATE = "android.app.extra.BLOCKED_STATE";

    /**
     * Extra for {@link #ACTION_NOTIFICATION_CHANNEL_GROUP_BLOCK_STATE_CHANGED} containing the id
     * of the {@link NotificationChannelGroup} which has a new blocked state.
     *
     * The value will be the {@link NotificationChannelGroup#getId()} of the group.
     */
    public static final String EXTRA_NOTIFICATION_CHANNEL_GROUP_ID = "android.app.extra.NOTIFICATION_CHANNEL_GROUP_ID";

    /**
     * Extra for {@link #ACTION_NOTIFICATION_CHANNEL_BLOCK_STATE_CHANGED} containing the id of the
     * {@link NotificationChannel} which has a new blocked state.
     *
     * The value will be the {@link NotificationChannel#getId()} of the channel.
     */
    public static final String EXTRA_NOTIFICATION_CHANNEL_ID = "android.app.extra.NOTIFICATION_CHANNEL_ID";

    /**
     * Extra for {@link #ACTION_NOTIFICATION_POLICY_CHANGED} and
     * {@link #ACTION_CONSOLIDATED_NOTIFICATION_POLICY_CHANGED} containing the new
     * {@link Policy} value.
     */
    public static final String EXTRA_NOTIFICATION_POLICY = "android.app.extra.NOTIFICATION_POLICY";

    /**
     * Default notification importance: shows everywhere, makes noise, but does not visually
     * intrude.
     */
    public static final int IMPORTANCE_DEFAULT = 3;

    /**
     * Higher notification importance: shows everywhere, makes noise and peeks. May use full screen
     * intents.
     */
    public static final int IMPORTANCE_HIGH = 4;

    /**
     * Low notification importance: Shows in the shade, and potentially in the status bar
     * (see {@link #shouldHideSilentStatusBarIcons()}), but is not audibly intrusive.
     */
    public static final int IMPORTANCE_LOW = 2;

    /**
     * Unused.
     */
    public static final int IMPORTANCE_MAX = 5;

    /**
     * Min notification importance: only shows in the shade, below the fold.  This should
     * not be used with {@link Service#startForeground(int, Notification) Service.startForeground}
     * since a foreground service is supposed to be something the user cares about so it does
     * not make semantic sense to mark its notification as minimum importance.  If you do this
     * as of Android version {@link android.os.Build.VERSION_CODES#O}, the system will show
     * a higher-priority notification about your app running in the background.
     */
    public static final int IMPORTANCE_MIN = 1;

    /**
     * A notification with no importance: does not show in the shade.
     */
    public static final int IMPORTANCE_NONE = 0;

    /**
     * Value signifying that the user has not expressed an importance.
     *
     * This value is for persisting preferences, and should never be associated with
     * an actual notification.
     */
    public static final int IMPORTANCE_UNSPECIFIED = -1000;

    /**
     * {@link #getCurrentInterruptionFilter() Interruption filter} constant -
     *     Alarms only interruption filter - all notifications except those of category
     *     {@link Notification#CATEGORY_ALARM} are suppressed. Some audio streams are muted.
     */
    public static final int INTERRUPTION_FILTER_ALARMS = 4;

    /**
     * {@link #getCurrentInterruptionFilter() Interruption filter} constant -
     *     Normal interruption filter - no notifications are suppressed.
     */
    public static final int INTERRUPTION_FILTER_ALL = 1;

    /**
     * {@link #getCurrentInterruptionFilter() Interruption filter} constant -
     *     No interruptions filter - all notifications are suppressed and all audio streams (except
     *     those used for phone calls) and vibrations are muted.
     */
    public static final int INTERRUPTION_FILTER_NONE = 3;

    /**
     * {@link #getCurrentInterruptionFilter() Interruption filter} constant -
     *     Priority interruption filter - all notifications are suppressed except those that match
     *     the priority criteria. Some audio streams are muted. See
     *     {@link Policy#priorityCallSenders}, {@link Policy#priorityCategories},
     *     {@link Policy#priorityMessageSenders} to define or query this criteria. Users can
     *     additionally specify packages that can bypass this interruption filter.
     */
    public static final int INTERRUPTION_FILTER_PRIORITY = 2;

    /**
     * {@link #getCurrentInterruptionFilter() Interruption filter} constant - returned when
     *  the value is unavailable for any reason.
     */
    public static final int INTERRUPTION_FILTER_UNKNOWN = 0;

    /**
     * A required {@code meta-data} tag for activities that handle
     * {@link #ACTION_AUTOMATIC_ZEN_RULE}.
     *
     * This tag should contain a localized name of the type of the zen rule provided by the
     * activity.
     */
    public static final String META_DATA_AUTOMATIC_RULE_TYPE = "android.service.zen.automatic.ruleType";

    /**
     * An optional {@code meta-data} tag for activities that handle
     * {@link #ACTION_AUTOMATIC_ZEN_RULE}.
     *
     * This tag should contain the maximum number of rule instances that
     * can be created for this rule type. Omit or enter a value <= 0 to allow unlimited instances.
     */
    public static final String META_DATA_RULE_INSTANCE_LIMIT = "android.service.zen.automatic.ruleInstanceLimit";

    private NotificationManager() {
    }

    /**
     * Notification policy configuration.  Represents user-preferences for notification
     * filtering.
     */
    public static final class Policy implements Parcelable {
        /**
         * Used to indicate all conversations can bypass dnd.
         */
        public static final int CONVERSATION_SENDERS_ANYONE = 1;

        /**
         * Used to indicate important conversations can bypass dnd.
         */
        public static final int CONVERSATION_SENDERS_IMPORTANT = 2;

        /**
         * Used to indicate no conversations can bypass dnd.
         */
        public static final int CONVERSATION_SENDERS_NONE = 3;

        /**
         * Alarms are prioritized
         */
        public static final int PRIORITY_CATEGORY_ALARMS = 32;

        /**
         * Calls are prioritized.
         */
        public static final int PRIORITY_CATEGORY_CALLS = 8;

        /**
         * Conversations are allowed through DND.
         */
        public static final int PRIORITY_CATEGORY_CONVERSATIONS = 256;

        /**
         * Event notifications are prioritized.
         */
        public static final int PRIORITY_CATEGORY_EVENTS = 2;

        /**
         * Media, game, voice navigation are prioritized
         */
        public static final int PRIORITY_CATEGORY_MEDIA = 64;

        /**
         * Message notifications are prioritized.
         */
        public static final int PRIORITY_CATEGORY_MESSAGES = 4;

        /**
         * Reminder notifications are prioritized.
         */
        public static final int PRIORITY_CATEGORY_REMINDERS = 1;

        /**
         * Calls from repeat callers are prioritized.
         */
        public static final int PRIORITY_CATEGORY_REPEAT_CALLERS = 16;

        /**
         * System (catch-all for non-never suppressible sounds) are prioritized
         */
        public static final int PRIORITY_CATEGORY_SYSTEM = 128;

        /**
         * Any sender is prioritized.
         */
        public static final int PRIORITY_SENDERS_ANY = 0;

        /**
         * Saved contacts are prioritized.
         */
        public static final int PRIORITY_SENDERS_CONTACTS = 1;

        /**
         * Only starred contacts are prioritized.
         */
        public static final int PRIORITY_SENDERS_STARRED = 2;

        /**
         * Whether notification intercepted by DND are prevented from appearing on ambient displays
         * on devices that support ambient display.
         */
        public static final int SUPPRESSED_EFFECT_AMBIENT = 128;

        /**
         * Whether {@link NotificationChannel#canShowBadge() badges} from
         * notifications intercepted by DND are blocked on devices that support badging.
         */
        public static final int SUPPRESSED_EFFECT_BADGE = 64;

        /**
         * Whether {@link Notification#fullScreenIntent full screen intents} from
         * notifications intercepted by DND are blocked.
         */
        public static final int SUPPRESSED_EFFECT_FULL_SCREEN_INTENT = 4;

        /**
         * Whether {@link NotificationChannel#shouldShowLights() notification lights} from
         * notifications intercepted by DND are blocked.
         */
        public static final int SUPPRESSED_EFFECT_LIGHTS = 8;

        /**
         * Whether notification intercepted by DND are prevented from appearing in notification
         * list views like the notification shade or lockscreen on devices that support those
         * views.
         */
        public static final int SUPPRESSED_EFFECT_NOTIFICATION_LIST = 256;

        /**
         * Whether notifications intercepted by DND are prevented from peeking.
         */
        public static final int SUPPRESSED_EFFECT_PEEK = 16;

        /**
         * Whether notifications suppressed by DND should not interrupt visually (e.g. with
         * notification lights or by turning the screen on) when the screen is off.
         *
         * @deprecated use {@link #SUPPRESSED_EFFECT_FULL_SCREEN_INTENT} and
         * {@link #SUPPRESSED_EFFECT_AMBIENT} and {@link #SUPPRESSED_EFFECT_LIGHTS} individually.
         */
        @Deprecated
        public static final int SUPPRESSED_EFFECT_SCREEN_OFF = 1;

        /**
         * Whether notifications suppressed by DND should not interrupt visually when the screen
         * is on (e.g. by peeking onto the screen).
         *
         * @deprecated use {@link #SUPPRESSED_EFFECT_PEEK}.
         */
        @Deprecated
        public static final int SUPPRESSED_EFFECT_SCREEN_ON = 2;

        /**
         * Whether notifications intercepted by DND are prevented from appearing in the status bar,
         * on devices that support status bars.
         */
        public static final int SUPPRESSED_EFFECT_STATUS_BAR = 32;

        @Override
        public int describeContents() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void writeToParcel(android.os.Parcel dest, int flags) {
            throw new UnsupportedOperationException();
        }

        private Policy() {
        }
    }
}
