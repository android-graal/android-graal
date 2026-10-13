package android.service.notification;

/**
 * A service that provides conditions about boolean state.
 * <p>To extend this class, you must declare the service in your manifest file with
 * the {@link android.Manifest.permission#BIND_CONDITION_PROVIDER_SERVICE} permission
 * and include an intent filter with the {@link #SERVICE_INTERFACE} action. If you want users to be
 * able to create and update conditions for this service to monitor, include the
 * {@link #META_DATA_RULE_TYPE} and {@link #META_DATA_CONFIGURATION_ACTIVITY} tags and request the
 * {@link android.Manifest.permission#ACCESS_NOTIFICATION_POLICY} permission. For example:</p>
 * <pre>
 * &lt;service android:name=".MyConditionProvider"
 *          android:label="&#64;string/service_name"
 *          android:permission="android.permission.BIND_CONDITION_PROVIDER_SERVICE">
 *     &lt;intent-filter>
 *         &lt;action android:name="android.service.notification.ConditionProviderService" />
 *     &lt;/intent-filter>
 *     &lt;meta-data
 *               android:name="android.service.zen.automatic.ruleType"
 *               android:value="@string/my_condition_rule">
 *           &lt;/meta-data>
 *           &lt;meta-data
 *               android:name="android.service.zen.automatic.configurationActivity"
 *               android:value="com.my.package/.MyConditionConfigurationActivity">
 *           &lt;/meta-data>
 * &lt;/service></pre>
 *
 *  <p> Condition providers cannot be bound by the system on
 * {@link ActivityManager#isLowRamDevice() low ram} devices running Android Q (and below)</p>
 *
 * @deprecated Instead of using an automatically bound service, use
 * {@link android.app.NotificationManager#setAutomaticZenRuleState(String, Condition)} to tell the
 * system about the state of your rule. In order to maintain a link from
 * Settings to your rule configuration screens, provide a configuration activity that handles
 * {@link android.app.NotificationManager#ACTION_AUTOMATIC_ZEN_RULE} on your
 * {@link android.app.AutomaticZenRule} via
 * {@link android.app.AutomaticZenRule#setConfigurationActivity(ComponentName)}.
 */
@Deprecated
public final class ConditionProviderService {
    /**
     * A String rule id extra passed to {@link #META_DATA_CONFIGURATION_ACTIVITY}.
     *
     * @deprecated see {@link android.app.NotificationManager#EXTRA_AUTOMATIC_RULE_ID}.
     */
    @Deprecated
    public static final String EXTRA_RULE_ID = "android.service.notification.extra.RULE_ID";

    /**
     * The name of the {@code meta-data} tag containing the {@link ComponentName} of an activity
     * that allows users to configure the conditions provided by this service.
     *
     * @deprecated see {@link android.app.NotificationManager#ACTION_AUTOMATIC_ZEN_RULE}.
     */
    @Deprecated
    public static final String META_DATA_CONFIGURATION_ACTIVITY = "android.service.zen.automatic.configurationActivity";

    /**
     * The name of the {@code meta-data} tag containing the maximum number of rule instances that
     * can be created for this rule type. Omit or enter a value <= 0 to allow unlimited instances.
     *
     * @deprecated see {@link android.app.NotificationManager#META_DATA_RULE_INSTANCE_LIMIT}.
     */
    @Deprecated
    public static final String META_DATA_RULE_INSTANCE_LIMIT = "android.service.zen.automatic.ruleInstanceLimit";

    /**
     * The name of the {@code meta-data} tag containing a localized name of the type of zen rules
     * provided by this service.
     *
     * @deprecated see {@link android.app.NotificationManager#META_DATA_AUTOMATIC_RULE_TYPE}.
     */
    @Deprecated
    public static final String META_DATA_RULE_TYPE = "android.service.zen.automatic.ruleType";

    /**
     * The {@link Intent} that must be declared as handled by the service.
     */
    @Deprecated
    public static final String SERVICE_INTERFACE = "android.service.notification.ConditionProviderService";

    private ConditionProviderService() {
    }
}
