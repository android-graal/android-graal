package android.net.wifi.rtt;

import android.os.Parcelable;

/**
 * Defines the ranging request to other devices. The ranging request is built using
 * {@link RangingRequest.Builder}.
 * A ranging request is executed using
 * {@link WifiRttManager#startRanging(RangingRequest, java.util.concurrent.Executor, RangingResultCallback)}.
 * <p>
 * The ranging request is a batch request - specifying a set of devices (specified using
 * {@link RangingRequest.Builder#addAccessPoint(ScanResult)} and
 * {@link RangingRequest.Builder#addAccessPoints(List)}).
 */
public final class RangingRequest implements Parcelable {
    /**
     *  In this mode, the ranging is performed with all available responders in open mode. If a
     *  responder does not allow open mode ranging, the responder will be skipped from the
     *  ranging request.
     * <p>
     *  Note: If {@link ScanResult#isRangingFrameProtectionRequired()} is {@code true}, then open
     *  mode ranging is not supported by the AP.
     */
    public static final int SECURITY_MODE_OPEN = 0;

    /**
     * In this mode, secure ranging is enabled automatically for compatible responders,
     * simplifying the user experience and requiring no configuration. If the secure ranging is not
     * possible for any of the responders, open mode ranging is used instead as in
     * {@link #SECURITY_MODE_OPEN}. This mode is backward compatible with existing applications.
     *
     * Note: This is the default mode
     */
    public static final int SECURITY_MODE_OPPORTUNISTIC = 1;

    /**
     * To ensure maximum security, this mode only ranges with responders using PASN with base AKM
     * (Authenticated). This necessitates an authenticated PASN handshake with a shared key
     * between the initiator and responder. Consequently, all responders in the ranging request
     * must support secure authentication. If not supported, the responder will be skipped from the
     * ranging request.
     */
    public static final int SECURITY_MODE_SECURE_AUTH = 2;

    private RangingRequest() {
    }
}
