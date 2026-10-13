package android.net;

import android.os.Parcelable;

/**
 * Describe the state of VPN.
 */
public final class VpnProfileState implements Parcelable {
    /**
     * The VPN was established successfully.
     */
    public static final int STATE_CONNECTED = 2;

    /**
     * The VPN is attempting to connect, potentially after a failure.
     */
    public static final int STATE_CONNECTING = 1;

    /**
     * The VPN has not been started, or some other VPN is active.
     */
    public static final int STATE_DISCONNECTED = 0;

    /**
     * A non-recoverable error has occurred, and will not be retried.
     */
    public static final int STATE_FAILED = 3;

    private VpnProfileState() {
    }
}
