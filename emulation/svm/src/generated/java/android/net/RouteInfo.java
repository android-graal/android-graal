package android.net;

import android.os.Parcelable;

/**
 * Represents a network route.
 * <p>
 * This is used both to describe static network configuration and live network
 * configuration information.
 *
 * A route contains three pieces of information:
 * <ul>
 * <li>a destination {@link IpPrefix} specifying the network destinations covered by this route.
 *     If this is {@code null} it indicates a default route of the address family (IPv4 or IPv6)
 *     implied by the gateway IP address.
 * <li>a gateway {@link InetAddress} indicating the next hop to use.  If this is {@code null} it
 *     indicates a directly-connected route.
 * <li>an interface (which may be unspecified).
 * </ul>
 * Either the destination or the gateway may be {@code null}, but not both.  If the
 * destination and gateway are both specified, they must be of the same address family
 * (IPv4 or IPv6).
 */
public final class RouteInfo implements Parcelable {
    /**
     * Throw route.
     *
     * Indicates that routing information about this destination is not in this table.
     * Routing lookup should continue in another table.
     */
    public static final int RTN_THROW = 9;

    /**
     * Unicast route.
     *
     * Indicates that destination is reachable directly or via gateway.
     */
    public static final int RTN_UNICAST = 1;

    /**
     * Unreachable route.
     *
     * Indicates that destination is unreachable.
     */
    public static final int RTN_UNREACHABLE = 7;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private RouteInfo() {
    }
}
