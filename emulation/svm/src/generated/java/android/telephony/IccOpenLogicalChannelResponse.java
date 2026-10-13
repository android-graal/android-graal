package android.telephony;

import android.os.Parcelable;

/**
 * Response to the {@link TelephonyManager#iccOpenLogicalChannel} command.
 */
public final class IccOpenLogicalChannelResponse implements Parcelable {
    /**
     * Indicates an invalid channel.
     */
    public static final int INVALID_CHANNEL = -1;

    public static final int STATUS_MISSING_RESOURCE = 2;

    /**
     * Possible status values returned by open channel command.
     *
     * STATUS_NO_ERROR: Open channel command returned successfully.
     * STATUS_MISSING_RESOURCE: No logical channels available.
     * STATUS_NO_SUCH_ELEMENT: AID not found on UICC.
     * STATUS_UNKNOWN_ERROR: Unknown error in open channel command.
     */
    public static final int STATUS_NO_ERROR = 1;

    public static final int STATUS_NO_SUCH_ELEMENT = 3;

    public static final int STATUS_UNKNOWN_ERROR = 4;

    private IccOpenLogicalChannelResponse() {
    }
}
