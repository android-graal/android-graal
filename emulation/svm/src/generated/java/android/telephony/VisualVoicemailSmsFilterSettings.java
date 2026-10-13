package android.telephony;

import android.os.Parcelable;

/**
 * Class to represent various settings for the visual voicemail SMS filter. When the filter is
 * enabled, incoming SMS matching the generalized OMTP format:
 *
 * <p>[clientPrefix]:[prefix]:([key]=[value];)*
 *
 * <p>will be regarded as a visual voicemail SMS, and removed before reaching the SMS provider. The
 * {@link VisualVoicemailService} in the current default dialer will be bound and
 * {@link VisualVoicemailService#onSmsReceived(VisualVoicemailTask, VisualVoicemailSms)}
 * will called with the information extracted from the SMS.
 *
 * <p>Use {@link android.telephony.VisualVoicemailSmsFilterSettings.Builder} to construct this
 * class.
 *
 * @see TelephonyManager#setVisualVoicemailSmsFilterSettings(VisualVoicemailSmsFilterSettings)
 */
public final class VisualVoicemailSmsFilterSettings implements Parcelable {
    /**
     * The visual voicemail SMS message does not have to be a data SMS, and can be directed to any
     * port.
     */
    public static final int DESTINATION_PORT_ANY = -1;

    /**
     * The visual voicemail SMS message can be directed to any port, but must be a data SMS.
     */
    public static final int DESTINATION_PORT_DATA_SMS = -2;

    private VisualVoicemailSmsFilterSettings() {
    }
}
