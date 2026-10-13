package android.telephony.ims;

/**
 * Provides access to information about Telephony IMS services on the device.
 */
public final class ImsManager {
    /**
     * An intent action indicating that IMS registration for WiFi calling has resulted in an error.
     * Contains error information that should be displayed to the user.
     * <p>
     * This intent will contain the following extra key/value pairs:
     * {@link #EXTRA_WFC_REGISTRATION_FAILURE_TITLE}
     * and {@link #EXTRA_WFC_REGISTRATION_FAILURE_MESSAGE}, which contain carrier specific
     * error information that should be displayed to the user.
     * <p>
     * Usage: This intent is sent as an ordered broadcast. If the settings application is going
     * to show the error information specified to the user, it should respond to
     * {@link android.content.BroadcastReceiver#setResultCode(int)} with
     * {@link android.app.Activity#RESULT_CANCELED}, which will signal to the framework that the
     * event was handled. If the framework does not receive a response to the ordered broadcast,
     * it will then show a notification to the user indicating that there was a registration
     * failure.
     */
    public static final String ACTION_WFC_IMS_REGISTRATION_ERROR = "android.telephony.ims.action.WFC_IMS_REGISTRATION_ERROR";

    /**
     * An extra key corresponding to a {@link CharSequence} value which contains the carrier
     * specific message to  be displayed as part of the message shown to the user when there is an
     * error registering for WiFi calling.
     */
    public static final String EXTRA_WFC_REGISTRATION_FAILURE_MESSAGE = "android.telephony.ims.extra.WFC_REGISTRATION_FAILURE_MESSAGE";

    /**
     * An extra key corresponding to a {@link CharSequence} value which contains the carrier
     * specific title to be displayed as part of the message shown to the user when there is an
     * error registering for WiFi calling.
     */
    public static final String EXTRA_WFC_REGISTRATION_FAILURE_TITLE = "android.telephony.ims.extra.WFC_REGISTRATION_FAILURE_TITLE";

    private ImsManager() {
    }
}
