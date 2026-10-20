package android.net;

import android.os.Parcelable;

/**
 * Representation of the capabilities of an active network.
 *
 * <p>@see <a href="https://developer.android.com/training/basics/network-ops/reading-network-state>
 * this general guide</a> on how to use NetworkCapabilities and related classes.
 *
 * <p>NetworkCapabilities represent what a network can do and what its
 * characteristics are like. The principal attribute of NetworkCapabilities
 * is in the capabilities bits, which are checked with
 * {@link #hasCapability(int)}. See the list of capabilities and each
 * capability for a description of what it means.
 *
 * <p>Some prime examples include {@code NET_CAPABILITY_MMS}, which means that the
 * network is capable of sending MMS. A network without this capability
 * is not capable of sending MMS.
 * <p>The {@code NET_CAPABILITY_INTERNET} capability means that the network is
 * configured to reach the general Internet. It may or may not actually
 * provide connectivity ; the {@code NET_CAPABILITY_VALIDATED} bit indicates that
 * the system found actual connectivity to the general Internet the last
 * time it checked. Apps interested in actual connectivity should usually
 * look at both these capabilities.
 * <p>The {@code NET_CAPABILITY_NOT_METERED} capability is set for networks that
 * do not bill the user for consumption of bytes. Applications are
 * encouraged to consult this to determine appropriate usage, and to
 * limit usage of metered network where possible, including deferring
 * big downloads until such a time that an unmetered network is connected.
 * Also see {@link android.app.job.JobScheduler} to help with scheduling such
 * downloads, in particular
 * {@link android.app.job.JobInfo.Builder#setRequiredNetwork(NetworkRequest)}.
 * <p>NetworkCapabilities contain a number of other capabilities that
 * represent what modern networks can and can't do. Look up the individual
 * capabilities in this class to learn about each of them.
 *
 * <p>NetworkCapabilities typically represent attributes that can apply to
 * any network. The attributes that apply only to specific transports like
 * cellular or Wi-Fi can be found in the specifier (for requestable attributes)
 * or in the transport info (for non-requestable ones). See
 * {@link #getNetworkSpecifier} and {@link #getTransportInfo}. An app would
 * downcast these to the specific class for the transport they need if they
 * are interested in transport-specific attributes. Also see
 * {@link android.net.wifi.WifiNetworkSpecifier} or
 * {@link android.net.wifi.WifiInfo} for some examples of each of these.
 *
 * <p>NetworkCapabilities also contains other attributes like the estimated
 * upstream and downstream bandwidth and the specific transport of that
 * network (e.g. {@link #TRANSPORT_CELLULAR}). Generally, apps should normally
 * have little reason to check for the type of transport ; for example, to
 * query whether a network costs money to the user, do not look at the
 * transport, but instead look at the absence or presence of
 * {@link #NET_CAPABILITY_NOT_METERED} which will correctly account for
 * metered Wi-Fis and free of charge cell connections.
 *
 * <p>The system communicates with apps about connected networks and uses
 * NetworkCapabilities to express these capabilities about these networks.
 * Apps should register callbacks with the {@link ConnectivityManager#requestNetwork}
 * or {@link ConnectivityManager#registerNetworkCallback} family of methods
 * to learn about the capabilities of a network on a continuous basis
 * and be able to react to changes to capabilities. For quick debugging Android also
 * provides {@link ConnectivityManager#getNetworkCapabilities(Network)},
 * but the dynamic nature of networking makes this ill-suited to production
 * code since capabilities obtained in this way can go stale immediately.
 *
 * <p>Also see {@link NetworkRequest} which uses the same capabilities
 * together with {@link ConnectivityManager#requestNetwork} for how to
 * request the system brings up the kind of network your application needs.
 */
public final class NetworkCapabilities implements Parcelable {
    /**
     * Indicates that this network was found to have a captive portal in place last time it was
     * probed.
     */
    public static final int NET_CAPABILITY_CAPTIVE_PORTAL = 17;

    /**
     * Indicates this is a network that has the ability to reach the carrier's
     * CBS servers, used for carrier specific services.
     */
    public static final int NET_CAPABILITY_CBS = 5;

    /**
     * Indicates this is a network that has the ability to reach the carrier's
     * DUN or tethering gateway.
     */
    public static final int NET_CAPABILITY_DUN = 2;

    /**
     * Indicates this is a network that has the ability to reach a carrier's
     * Emergency IMS servers or other services, used for network signaling
     * during emergency calls.
     */
    public static final int NET_CAPABILITY_EIMS = 10;

    /**
     * Indicates that this network is intended for enterprise use.
     * <p>
     * 5G URSP rules may indicate that all data should use a connection dedicated for enterprise
     * use. If the enterprise capability is requested, all enterprise traffic will be routed over
     * the connection with this capability.
     */
    public static final int NET_CAPABILITY_ENTERPRISE = 29;

    /**
     * Indicates that this network is available for use by apps, and not a network that is being
     * kept up in the background to facilitate fast network switching.
     */
    public static final int NET_CAPABILITY_FOREGROUND = 19;

    /**
     * Indicates this is a network that has the ability to reach the carrier's
     * FOTA portal, used for over the air updates.
     */
    public static final int NET_CAPABILITY_FOTA = 3;

    /**
     * Indicates that this network is connected to an automotive head unit.
     */
    public static final int NET_CAPABILITY_HEAD_UNIT = 32;

    /**
     * Indicates this is a network that has the ability to reach a carrier's
     * Initial Attach servers.
     */
    public static final int NET_CAPABILITY_IA = 7;

    /**
     * Indicates this is a network that has the ability to reach the carrier's
     * IMS servers, used for network registration and signaling.
     */
    public static final int NET_CAPABILITY_IMS = 4;

    /**
     * Indicates that this network should be able to reach the internet.
     */
    public static final int NET_CAPABILITY_INTERNET = 12;

    /**
     * Indicates that this network is a local network.
     *
     * Local networks are networks where the device is not obtaining IP addresses from the
     * network, but advertising IP addresses itself. Examples of local networks are:
     * <ul>
     * <li>USB tethering or Wi-Fi hotspot networks to which the device is sharing its Internet
     * connectivity.
     * <li>Thread networks where the current device is the Thread Border Router.
     * <li>Wi-Fi P2P networks where the current device is the Group Owner.
     * </ul>
     *
     * Networks used to obtain Internet access are never local networks.
     *
     * Apps that target an SDK before {@link Build.VERSION_CODES.VANILLA_ICE_CREAM} will not see
     * networks with this capability unless they explicitly set the NET_CAPABILITY_LOCAL_NETWORK
     * in their NetworkRequests.
     */
    public static final int NET_CAPABILITY_LOCAL_NETWORK = 36;

    /**
     * Indicates this is a network that has the ability to reach a carrier's Mission Critical
     * servers.
     */
    public static final int NET_CAPABILITY_MCX = 23;

    /**
     * Indicates this is a network that has the ability to reach the
     * carrier's MMSC for sending and receiving MMS messages.
     */
    public static final int NET_CAPABILITY_MMS = 0;

    /**
     * Indicates that this network has ability to support MMTEL (Multimedia Telephony service).
     */
    public static final int NET_CAPABILITY_MMTEL = 33;

    /**
     * Indicates that this is not a bandwidth-constrained network.
     *
     * Starting from {@link Build.VERSION_CODES.VANILLA_ICE_CREAM}, this capability is by default
     * set in {@link NetworkRequest}s and true for most networks.
     *
     * If a network lacks this capability, it is bandwidth-constrained. Bandwidth constrained
     * networks cannot support high-bandwidth data transfers and applications that request and use
     * them must ensure that they limit bandwidth usage to below the values returned by
     * {@link #getLinkDownstreamBandwidthKbps()} and {@link #getLinkUpstreamBandwidthKbps()} and
     * limit the frequency of their network usage. If applications perform high-bandwidth data
     * transfers on constrained networks or perform network access too frequently, the system may
     * block the app's access to the network. The system may take other measures to reduce network
     * usage on constrained networks, such as disabling network access to apps that are not in the
     * foreground.
     */
    public static final int NET_CAPABILITY_NOT_BANDWIDTH_CONSTRAINED = 37;

    /**
     * Indicates that this network is not congested.
     * <p>
     * When a network is congested, applications should defer network traffic
     * that can be done at a later time, such as uploading analytics.
     */
    public static final int NET_CAPABILITY_NOT_CONGESTED = 20;

    /**
     * Indicates that this network is unmetered.
     */
    public static final int NET_CAPABILITY_NOT_METERED = 11;

    /**
     * Indicates that this network is available for general use.  If this is not set
     * applications should not attempt to communicate on this network.  Note that this
     * is simply informative and not enforcement - enforcement is handled via other means.
     * Set by default.
     */
    public static final int NET_CAPABILITY_NOT_RESTRICTED = 13;

    /**
     * Indicates that this network is not roaming.
     */
    public static final int NET_CAPABILITY_NOT_ROAMING = 18;

    /**
     * Indicates that this network is not currently suspended.
     * <p>
     * When a network is suspended, the network's IP addresses and any connections
     * established on the network remain valid, but the network is temporarily unable
     * to transfer data. This can happen, for example, if a cellular network experiences
     * a temporary loss of signal, such as when driving through a tunnel, etc.
     * A network with this capability is not suspended, so is expected to be able to
     * transfer data.
     */
    public static final int NET_CAPABILITY_NOT_SUSPENDED = 21;

    /**
     * Indicates that this network is not a VPN.  This capability is set by default and should be
     * explicitly cleared for VPN networks.
     */
    public static final int NET_CAPABILITY_NOT_VPN = 15;

    /**
     * Indicates that this network should be able to prioritize bandwidth for the internet.
     *
     * Starting with {@link Build.VERSION_CODES#UPSIDE_DOWN_CAKE}, requesting this capability with
     * {@link ConnectivityManager#requestNetwork} requires declaration in the self-certified
     * network capabilities. See {@link NetworkRequest} for the self-certification documentation.
     */
    public static final int NET_CAPABILITY_PRIORITIZE_BANDWIDTH = 35;

    /**
     * Indicates that this network should be able to prioritize latency for the internet.
     *
     * Starting with {@link Build.VERSION_CODES#UPSIDE_DOWN_CAKE}, requesting this capability with
     * {@link ConnectivityManager#requestNetwork} requires declaration in the self-certified
     * network capabilities. See {@link NetworkRequest} for the self-certification documentation.
     */
    public static final int NET_CAPABILITY_PRIORITIZE_LATENCY = 34;

    /**
     * Indicates this is a network that has the ability to reach a carrier's
     * RCS servers, used for Rich Communication Services.
     */
    public static final int NET_CAPABILITY_RCS = 8;

    /**
     * Indicates this is a network that has the ability to reach the carrier's
     * SUPL server, used to retrieve GPS information.
     */
    public static final int NET_CAPABILITY_SUPL = 1;

    /**
     * Indicates that this network is temporarily unmetered.
     * <p>
     * This capability will be set for networks that are generally metered, but are currently
     * unmetered, e.g., because the user is in a particular area. This capability can be changed at
     * any time. When it is removed, applications are responsible for stopping any data transfer
     * that should not occur on a metered network.
     * Note that most apps should use {@link #NET_CAPABILITY_NOT_METERED} instead. For more
     * information, see https://developer.android.com/about/versions/11/features/5g#meteredness.
     */
    public static final int NET_CAPABILITY_TEMPORARILY_NOT_METERED = 25;

    /**
     * Indicates that the user has indicated implicit trust of this network.  This
     * generally means it's a sim-selected carrier, a plugged in ethernet, a paired
     * BT device or a wifi the user asked to connect to.  Untrusted networks
     * are probably limited to unknown wifi AP.  Set by default.
     */
    public static final int NET_CAPABILITY_TRUSTED = 14;

    /**
     * Indicates that connectivity on this network was successfully validated. For example, for a
     * network with NET_CAPABILITY_INTERNET, it means that Internet connectivity was successfully
     * detected.
     */
    public static final int NET_CAPABILITY_VALIDATED = 16;

    /**
     * Indicates this is a network that has the ability to reach a Wi-Fi direct
     * peer.
     */
    public static final int NET_CAPABILITY_WIFI_P2P = 6;

    /**
     * Indicates this is a network that has the ability to reach a carrier's
     * XCAP servers, used for configuration and control.
     */
    public static final int NET_CAPABILITY_XCAP = 9;

    /**
     * Enterprise capability identifier 1. It will be used to uniquely identify specific
     * enterprise network.
     */
    public static final int NET_ENTERPRISE_ID_1 = 1;

    /**
     * Enterprise capability identifier 2. It will be used to uniquely identify specific
     * enterprise network.
     */
    public static final int NET_ENTERPRISE_ID_2 = 2;

    /**
     * Enterprise capability identifier 3. It will be used to uniquely identify specific
     * enterprise network.
     */
    public static final int NET_ENTERPRISE_ID_3 = 3;

    /**
     * Enterprise capability identifier 4. It will be used to uniquely identify specific
     * enterprise network.
     */
    public static final int NET_ENTERPRISE_ID_4 = 4;

    /**
     * Enterprise capability identifier 5. It will be used to uniquely identify specific
     * enterprise network.
     */
    public static final int NET_ENTERPRISE_ID_5 = 5;

    /**
     * Magic value that indicates no signal strength provided. A request specifying this value is
     * always satisfied.
     */
    public static final int SIGNAL_STRENGTH_UNSPECIFIED = -2147483648;

    /**
     * Indicates this network uses a Bluetooth transport.
     */
    public static final int TRANSPORT_BLUETOOTH = 2;

    /**
     * Indicates this network uses a Cellular transport.
     */
    public static final int TRANSPORT_CELLULAR = 0;

    /**
     * Indicates this network uses an Ethernet transport.
     */
    public static final int TRANSPORT_ETHERNET = 3;

    /**
     * Indicates this network uses a LoWPAN transport.
     */
    public static final int TRANSPORT_LOWPAN = 6;

    /**
     * Indicates this network uses a Satellite transport.
     */
    public static final int TRANSPORT_SATELLITE = 10;

    /**
     * Indicates this network uses a Thread transport.
     */
    public static final int TRANSPORT_THREAD = 9;

    /**
     * Indicates this network uses a USB transport.
     */
    public static final int TRANSPORT_USB = 8;

    /**
     * Indicates this network uses a VPN transport.
     */
    public static final int TRANSPORT_VPN = 4;

    /**
     * Indicates this network uses a Wi-Fi transport.
     */
    public static final int TRANSPORT_WIFI = 1;

    /**
     * Indicates this network uses a Wi-Fi Aware transport.
     */
    public static final int TRANSPORT_WIFI_AWARE = 5;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private NetworkCapabilities() {
    }
}
