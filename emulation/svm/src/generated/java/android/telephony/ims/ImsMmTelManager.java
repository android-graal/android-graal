package android.telephony.ims;

/**
 * A manager for the MmTel (Multimedia Telephony) feature of an IMS network, given an associated
 * subscription.
 *
 * Allows a user to query the IMS MmTel feature information for a subscription, register for
 * registration and MmTel capability status callbacks, as well as query/modify user settings for the
 * associated subscription.
 *
 * Use {@link android.telephony.ims.ImsManager#getImsMmTelManager(int)} to get an instance of this
 * manager.
 */
public final class ImsMmTelManager implements RegistrationManager {
    /**
     * Prefer registering for IMS over LTE if LTE signal quality is high enough.
     */
    public static final int WIFI_MODE_CELLULAR_PREFERRED = 1;

    /**
     * Register for IMS over IWLAN if WiFi signal quality is high enough. Do not hand over to LTE
     * registration if signal quality degrades.
     */
    public static final int WIFI_MODE_WIFI_ONLY = 0;

    /**
     * Prefer registering for IMS over IWLAN if possible if WiFi signal quality is high enough.
     */
    public static final int WIFI_MODE_WIFI_PREFERRED = 2;

    private ImsMmTelManager() {
    }
}
