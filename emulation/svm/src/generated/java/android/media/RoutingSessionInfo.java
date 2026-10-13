package android.media;

import android.os.Parcelable;

/**
 * Describes a routing session which is created when a media route is selected.
 */
public final class RoutingSessionInfo implements Parcelable {
    /**
     * Indicates that the transfer happened from a non-privileged app.
     */
    public static final int TRANSFER_REASON_APP = 2;

    /**
     * Indicates that the transfer happened by the default logic without explicit system's or user's
     * request.
     *
     * <p>For example, an automatically connected Bluetooth device will have this transfer reason.
     */
    public static final int TRANSFER_REASON_FALLBACK = 0;

    /**
     * Indicates that the transfer happened from within a privileged application.
     */
    public static final int TRANSFER_REASON_SYSTEM_REQUEST = 1;

    private RoutingSessionInfo() {
    }
}
