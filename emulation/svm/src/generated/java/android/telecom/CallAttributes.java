package android.telecom;

import android.os.Parcelable;

/**
 * CallAttributes represents a set of properties that define a new Call.  Apps should build an
 * instance of this class and use {@link TelecomManager#addCall} to start a new call with Telecom.
 *
 * <p>
 * Apps should first register a {@link PhoneAccount} via {@link TelecomManager#registerPhoneAccount}
 * and use the same {@link PhoneAccountHandle} registered with Telecom when creating an
 * instance of CallAttributes.
 */
public final class CallAttributes implements Parcelable {
    /**
     * Used when answering or dialing a call to indicate that the call does not have a video
     * component
     */
    public static final int AUDIO_CALL = 1;

    /**
     * Indicates that the call is an incoming call.
     */
    public static final int DIRECTION_INCOMING = 1;

    /**
     * Indicates that the call is an outgoing call.
     */
    public static final int DIRECTION_OUTGOING = 2;

    /**
     * The call being created can be set to inactive (traditionally referred to as hold).  This
     * means that once a new call goes active, if the active call needs to be held in order to
     * place or receive an incoming call, the active call will be placed on hold.  otherwise, the
     * active call may be disconnected.
     */
    public static final int SUPPORTS_SET_INACTIVE = 2;

    /**
     * The call can be streamed from a root device to another device to continue the call without
     * completely transferring it.
     */
    public static final int SUPPORTS_STREAM = 4;

    /**
     * The call can be completely transferred from one endpoint to another.
     */
    public static final int SUPPORTS_TRANSFER = 8;

    /**
     * The call supports video calling. This allows clients to gate video calling on a per call
     * basis as opposed to re-registering the phone account.
     */
    public static final int SUPPORTS_VIDEO_CALLING = 16;

    /**
     * Indicates video transmission is supported
     */
    public static final int VIDEO_CALL = 2;

    private CallAttributes() {
    }
}
