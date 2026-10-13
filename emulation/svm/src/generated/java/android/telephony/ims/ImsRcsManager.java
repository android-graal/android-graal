package android.telephony.ims;

/**
 * Manager for interfacing with the framework RCS services, including the User Capability Exchange
 * (UCE) service, as well as managing user settings.
 *
 * Use {@link ImsManager#getImsRcsManager(int)} to create an instance of this manager.
 */
public final class ImsRcsManager {
    /**
     * Activity Action: Show the opt-in dialog for enabling or disabling RCS contact discovery
     * using User Capability Exchange (UCE), which enables a service that periodically shares the
     * phone numbers of all of the contacts in the user's address book with the carrier to refresh
     * the RCS capabilities associated with those contacts as the local cache becomes stale.
     * <p>
     * An application that depends on RCS contact discovery being enabled must send this intent
     * using {@link Context#startActivity(Intent)} to ask the user to opt-in for contacts upload for
     * capability exchange if it is currently disabled. Whether or not RCS contact discovery has
     * been enabled by the user can be queried using {@link RcsUceAdapter#isUceSettingEnabled()}.
     * <p>
     * This intent will always be handled by the system, however the application should only send
     * this Intent if the carrier supports bulk RCS contact exchange, which will be true if either
     * key {@link android.telephony.CarrierConfigManager.Ims#KEY_RCS_BULK_CAPABILITY_EXCHANGE_BOOL}
     * or {@link android.telephony.CarrierConfigManager#KEY_USE_RCS_PRESENCE_BOOL} is set to true.
     * Otherwise, the RCS contact discovery opt-in dialog will not be shown.
     * <p>
     * Input: A mandatory {@link Settings#EXTRA_SUB_ID} extra containing the subscription that the
     * setting will be be shown for.
     * <p>
     * Output: Nothing
     * @see RcsUceAdapter
     */
    public static final String ACTION_SHOW_CAPABILITY_DISCOVERY_OPT_IN = "android.telephony.ims.action.SHOW_CAPABILITY_DISCOVERY_OPT_IN";

    /**
     * Undefined capability type for initialization
     */
    public static final int CAPABILITY_TYPE_NONE = 0;

    /**
     * This carrier supports User Capability Exchange using SIP OPTIONS as defined by the
     * framework. If set, the RcsFeature should support capability exchange using SIP OPTIONS.
     * If not set, this RcsFeature should not service capability requests.
     */
    public static final int CAPABILITY_TYPE_OPTIONS_UCE = 1;

    /**
     * This carrier supports User Capability Exchange using a presence server as defined by the
     * framework. If set, the RcsFeature should support capability exchange using a presence
     * server. If not set, this RcsFeature should not publish capabilities or service capability
     * requests using presence.
     */
    public static final int CAPABILITY_TYPE_PRESENCE_UCE = 2;

    private ImsRcsManager() {
    }
}
