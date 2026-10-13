package android.net;

import android.os.Parcelable;

/**
 * A {@link NetworkSpecifier} used to identify an L2CAP network over BLE.
 *
 * An L2CAP network is not symmetrical, meaning there exists both a server (Bluetooth peripheral)
 * and a client (Bluetooth central) node. This specifier contains the information required to
 * request a client L2CAP network using {@link ConnectivityManager#requestNetwork} while specifying
 * the remote MAC address, and Protocol/Service Multiplexer (PSM). It can also contain information
 * allocated by the system when reserving a server network using {@link
 * ConnectivityManager#reserveNetwork} such as the Protocol/Service Multiplexer (PSM). In both
 * cases, the header compression option must be specified.
 *
 * An L2CAP server network allocates a Protocol/Service Multiplexer (PSM) to be advertised to the
 * client. A new server network must always be reserved using {@code
 * ConnectivityManager#reserveNetwork}. The subsequent {@link
 * ConnectivityManager.NetworkCallback#onReserved(NetworkCapabilities)} callback includes an {@code
 * L2CapNetworkSpecifier}. The {@link getPsm()} method will return the Protocol/Service Multiplexer
 * (PSM) of the reserved network so that the server can advertise it to the client and the client
 * can connect.
 * An L2CAP server network is backed by a {@link android.bluetooth.BluetoothServerSocket} which can,
 * in theory, accept many connections. However, before SDK version {@link
 * Build.VERSION_CODES.VANILLA_ICE_CREAM} Bluetooth APIs do not expose the channel ID, so these
 * connections are indistinguishable. In practice, this means that the network matching semantics in
 * ConnectivityService will tear down all but the first connection.
 *
 * When the connection between client and server completes, a {@link Network} whose capabilities
 * satisfy this {@code L2capNetworkSpecifier} will connect and the usual callbacks, such as {@link
 * NetworkCallback#onAvailable}, will be called on the callback object passed to {@code
 * ConnectivityManager#reserveNetwork} or {@code ConnectivityManager#requestNetwork}.
 */
public final class L2capNetworkSpecifier implements Parcelable {
    /**
     * Use 6lowpan header compression as specified in rfc6282.
     */
    public static final int HEADER_COMPRESSION_6LOWPAN = 2;

    /**
     * Accept any form of header compression.
     *
     * This option is only meaningful in {@link NetworkRequest}s. Specifiers for actual L2CAP
     * networks never have this option set.
     */
    public static final int HEADER_COMPRESSION_ANY = 0;

    /**
     * Do not compress packets on this network.
     */
    public static final int HEADER_COMPRESSION_NONE = 1;

    /**
     * Match any Protocol/Service Multiplexer (PSM).
     *
     * This PSM value is only meaningful in {@link NetworkRequest}s. Specifiers for actual L2CAP
     * networks never have this value set.
     */
    public static final int PSM_ANY = 0;

    /**
     * Match any role.
     *
     * This role is only meaningful in {@link NetworkRequest}s. Specifiers for actual L2CAP
     * networks never have this role set.
     */
    public static final int ROLE_ANY = 0;

    /**
     * Specifier describes a client network, i.e., the device is the Bluetooth central.
     */
    public static final int ROLE_CLIENT = 1;

    /**
     * Specifier describes a server network, i.e., the device is the Bluetooth peripheral.
     */
    public static final int ROLE_SERVER = 2;

    private L2capNetworkSpecifier() {
    }
}
