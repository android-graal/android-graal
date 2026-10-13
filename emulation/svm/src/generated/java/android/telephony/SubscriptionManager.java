package android.telephony;

/**
 * Subscription manager provides the mobile subscription information.
 */
public final class SubscriptionManager {
    /**
     * Broadcast Action: The default sms subscription has changed.  This has the following
     * extra values:</p>
     * {@link #EXTRA_SUBSCRIPTION_INDEX} extra indicates the current default sms
     * subscription index
     */
    public static final String ACTION_DEFAULT_SMS_SUBSCRIPTION_CHANGED = "android.telephony.action.DEFAULT_SMS_SUBSCRIPTION_CHANGED";

    /**
     * Broadcast Action: The default subscription has changed.  This has the following
     * extra values:</p>
     * The {@link #EXTRA_SUBSCRIPTION_INDEX} extra indicates the current default subscription index
     */
    public static final String ACTION_DEFAULT_SUBSCRIPTION_CHANGED = "android.telephony.action.DEFAULT_SUBSCRIPTION_CHANGED";

    /**
     * Activity Action: Display UI for managing the billing relationship plans
     * between a carrier and a specific subscriber.
     * <p>
     * Carrier apps are encouraged to implement this activity, and the OS will
     * provide an affordance to quickly enter this activity, typically via
     * Settings. This affordance will only be shown when the carrier app is
     * actively providing subscription plan information via
     * {@link #setSubscriptionPlans(int, List)}.
     * <p>
     * Contains {@link #EXTRA_SUBSCRIPTION_INDEX} to indicate which subscription
     * the user is interested in.
     */
    public static final String ACTION_MANAGE_SUBSCRIPTION_PLANS = "android.telephony.action.MANAGE_SUBSCRIPTION_PLANS";

    /**
     * Broadcast Action: Request a refresh of the billing relationship plans
     * between a carrier and a specific subscriber.
     * <p>
     * Carrier apps are encouraged to implement this receiver, and the OS will
     * provide an affordance to request a refresh. This affordance will only be
     * shown when the carrier app is actively providing subscription plan
     * information via {@link #setSubscriptionPlans(int, List)}.
     * <p>
     * Contains {@link #EXTRA_SUBSCRIPTION_INDEX} to indicate which subscription
     * the user is interested in.
     * <p>
     * Receivers should protect themselves by checking that the sender holds the
     * {@code android.permission.MANAGE_SUBSCRIPTION_PLANS} permission.
     */
    public static final String ACTION_REFRESH_SUBSCRIPTION_PLANS = "android.telephony.action.REFRESH_SUBSCRIPTION_PLANS";

    /**
     * Device status is shared whenever possible.
     */
    public static final int D2D_SHARING_ALL = 3;

    /**
     * Device status is shared with all numbers in the user's contacts.
     */
    public static final int D2D_SHARING_ALL_CONTACTS = 1;

    /**
     * Device status is not shared to a remote party.
     */
    public static final int D2D_SHARING_DISABLED = 0;

    /**
     * Device status is shared with all selected contacts.
     */
    public static final int D2D_SHARING_SELECTED_CONTACTS = 2;

    /**
     * TelephonyProvider column name for device to device sharing status.
     * <P>Type: INTEGER (int)</P>
     */
    public static final String D2D_STATUS_SHARING = "d2d_sharing_status";

    /**
     * TelephonyProvider column name for contacts information that allow device to device sharing.
     * <P>Type: TEXT (String)</P>
     */
    public static final String D2D_STATUS_SHARING_SELECTED_CONTACTS = "d2d_sharing_contacts";

    /**
     * Indicates that data roaming is disabled for a subscription
     */
    public static final int DATA_ROAMING_DISABLE = 0;

    /**
     * Indicates that data roaming is enabled for a subscription
     */
    public static final int DATA_ROAMING_ENABLE = 1;

    /**
     * Indicates the default subscription ID in Telephony.
     */
    public static final int DEFAULT_SUBSCRIPTION_ID = 2147483647;

    /**
     * Integer extra to specify SIM slot index.
     */
    public static final String EXTRA_SLOT_INDEX = "android.telephony.extra.SLOT_INDEX";

    /**
     * Integer extra used with {@link #ACTION_DEFAULT_SUBSCRIPTION_CHANGED} and
     * {@link #ACTION_DEFAULT_SMS_SUBSCRIPTION_CHANGED} to indicate the subscription
     * which has changed.
     */
    public static final String EXTRA_SUBSCRIPTION_INDEX = "android.telephony.extra.SUBSCRIPTION_INDEX";

    /**
     * Indicates invalid sim slot. This can be returned by {@link #getSlotIndex(int)}.
     */
    public static final int INVALID_SIM_SLOT_INDEX = -1;

    /**
     * An invalid subscription identifier
     */
    public static final int INVALID_SUBSCRIPTION_ID = -1;

    /**
     * A source of phone number: provided by an app that has carrier privilege.
     *
     * <p>The number is intended to be set by a carrier app knowing the correct number
     * which is, for example, different from the number in {@link #PHONE_NUMBER_SOURCE_UICC UICC}
     * for some reason.
     * The number is not available until a carrier app sets one via
     * {@link #setCarrierPhoneNumber(int, String)}.
     * The app can update the number with the same API should the number change.
     */
    public static final int PHONE_NUMBER_SOURCE_CARRIER = 2;

    /**
     * A source of phone number: provided by IMS (IP Multimedia Subsystem) implementation.
     * When IMS service is registered (as indicated by
     * {@link android.telephony.ims.RegistrationManager.RegistrationCallback#onRegistered(int)})
     * the IMS implementation may return P-Associated-Uri SIP headers (RFC 3455). The URIs
     * are the user’s public user identities known to the network (see 3GPP TS 24.229 5.4.1.2),
     * and the phone number is typically one of them (see “global number” in 3GPP TS 23.003 13.4).
     *
     * <p>This source provides the phone number from the last IMS registration.
     * IMS registration may happen on every device reboot or other network condition changes.
     * The number will be updated should the associated URI change after an IMS registration.
     */
    public static final int PHONE_NUMBER_SOURCE_IMS = 3;

    /**
     * A source of phone number: the EF-MSISDN (see 3GPP TS 31.102),
     * or EF-MDN for CDMA (see 3GPP2 C.P0065-B), from UICC application.
     *
     * <p>The availability and accuracy of the number depends on the carrier.
     * The number may be updated by over-the-air update to UICC applications
     * from the carrier, or by other means with physical access to the SIM.
     */
    public static final int PHONE_NUMBER_SOURCE_UICC = 1;

    /**
     * Represents a value indicating the data calling capabilities of a subscription.
     */
    public static final int SERVICE_CAPABILITY_DATA = 3;

    /**
     * Represents a value indicating the SMS capabilities of a subscription.
     *
     * <p>This value identifies whether the subscription supports various sms services.
     * These services can include circuit-switched (CS) SMS, packet-switched (PS) IMS (IP
     * Multimedia Subsystem) SMS, and over-the-top (OTT) SMS options.
     *
     * <p>Note: The availability of emergency SMS services is not solely dependent on this
     * sms capability. Emergency services may be accessible even if the subscription lacks
     * standard sms capabilities. However, the device's ability to support emergency sms
     * can be influenced by its inherent sms capabilities, as determined by
     * {@link TelephonyManager#isDeviceSmsCapable()}.
     *
     * @see TelephonyManager#isDeviceSmsCapable()
     */
    public static final int SERVICE_CAPABILITY_SMS = 2;

    /**
     * Represents a value indicating the voice calling capabilities of a subscription.
     *
     * <p>This value identifies whether the subscription supports various voice calling services.
     * These services can include circuit-switched (CS) calling, packet-switched (PS) IMS (IP
     * Multimedia Subsystem) calling, and over-the-top (OTT) calling options.
     *
     * <p>Note: The availability of emergency calling services is not solely dependent on this
     * voice capability. Emergency services may be accessible even if the subscription lacks
     * standard voice capabilities. However, the device's ability to support emergency calls
     * can be influenced by its inherent voice capabilities, as determined by
     * {@link TelephonyManager#isDeviceVoiceCapable()}.
     *
     * @see TelephonyManager#isDeviceVoiceCapable()
     */
    public static final int SERVICE_CAPABILITY_VOICE = 1;

    /**
     * This constant is to designate a subscription as a Local-SIM Subscription.
     * <p> A Local-SIM can be a physical SIM inserted into a sim-slot in the device, or eSIM on the
     * device.
     * </p>
     */
    public static final int SUBSCRIPTION_TYPE_LOCAL_SIM = 0;

    /**
     * This constant is to designate a subscription as a Remote-SIM Subscription.
     * <p>
     * A Remote-SIM subscription is for a SIM on a phone connected to this device via some
     * connectivity mechanism, for example bluetooth. Similar to Local SIM, this subscription can
     * be used for SMS, Voice and data by proxying data through the connected device.
     * Certain data of the SIM, such as IMEI, are not accessible for Remote SIMs.
     * </p>
     *
     * <p>
     * A Remote-SIM is available only as long the phone stays connected to this device.
     * When the phone disconnects, Remote-SIM subscription is removed from this device and is
     * no longer known. All data associated with the subscription, such as stored SMS, call logs,
     * contacts etc, are removed from this device.
     * </p>
     *
     * <p>
     * If the phone re-connects to this device, a new Remote-SIM subscription is created for
     * the phone. The Subscription Id associated with the new subscription is different from
     * the Subscription Id of the previous Remote-SIM subscription created (and removed) for the
     * phone; i.e., new Remote-SIM subscription treats the reconnected phone as a Remote-SIM that
     * was never seen before.
     * </p>
     */
    public static final int SUBSCRIPTION_TYPE_REMOTE_SIM = 1;

    /**
     * This subscription is forced to data-centric mode
     *
     * <p>Refer to data-centric mode in 3gpp 24.301 sec 4.3 and 3gpp 24.501 sec 4.3.
     * Also refer to "UE's usage setting" as defined in 3gpp 24.301 section 3.1 and 3gpp 23.221
     * Annex A.
     *
     * <p>Devices that support {@link PackageManager#FEATURE_TELEPHONY_DATA} and support usage
     * setting configuration must support setting this value via.
     * {@link CarrierConfigManager#KEY_CELLULAR_USAGE_SETTING_INT}.
     */
    public static final int USAGE_SETTING_DATA_CENTRIC = 2;

    /**
     * Subscription uses the default setting.
     *
     * The value is based upon device capability and the other properties of the subscription.
     *
     * Most subscriptions will default to voice-centric when in a phone.
     *
     * An opportunistic subscription will default to data-centric.
     *
     * @see SubscriptionInfo#isOpportunistic
     */
    public static final int USAGE_SETTING_DEFAULT = 0;

    /**
     * The usage setting is unknown.
     *
     * This will be the usage setting returned on devices that do not support querying the
     * or setting the usage setting.
     *
     * It may also be provided by a carrier that wishes to provide a value to avoid making any
     * settings changes.
     */
    public static final int USAGE_SETTING_UNKNOWN = -1;

    /**
     * This subscription is forced to voice-centric mode
     *
     * <p>Refer to voice-centric mode in 3gpp 24.301 sec 4.3 and 3gpp 24.501 sec 4.3.
     * Also refer to "UE's usage setting" as defined in 3gpp 24.301 section 3.1 and 3gpp 23.221
     * Annex A.
     *
     * <p>Devices that support {@link PackageManager#FEATURE_TELEPHONY_CALLING} and support usage
     * setting configuration must support setting this value via
     * {@link CarrierConfigManager#KEY_CELLULAR_USAGE_SETTING_INT}.
     */
    public static final int USAGE_SETTING_VOICE_CENTRIC = 1;

    private SubscriptionManager() {
    }
}
