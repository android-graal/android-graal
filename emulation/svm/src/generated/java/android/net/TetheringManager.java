package android.net;

/**
 * This class provides the APIs to control the tethering service.
 * <p> The primary responsibilities of this class are to provide the APIs for applications to
 * start tethering, stop tethering, query configuration and query status.
 */
public final class TetheringManager {
    /**
     * Indicates that this tethering connection will provide connectivity beyond this device (e.g.,
     * global Internet access).
     */
    public static final int CONNECTIVITY_SCOPE_GLOBAL = 1;

    /**
     * Wifi tethering type.
     * @see #startTethering.
     */
    public static final int TETHERING_WIFI = 0;

    public static final int TETHER_ERROR_DHCPSERVER_ERROR = 12;

    public static final int TETHER_ERROR_DISABLE_FORWARDING_ERROR = 9;

    public static final int TETHER_ERROR_DUPLICATE_REQUEST = 18;

    public static final int TETHER_ERROR_ENABLE_FORWARDING_ERROR = 8;

    public static final int TETHER_ERROR_ENTITLEMENT_UNKNOWN = 13;

    public static final int TETHER_ERROR_IFACE_CFG_ERROR = 10;

    public static final int TETHER_ERROR_INTERNAL_ERROR = 5;

    public static final int TETHER_ERROR_NO_ACCESS_TETHERING_PERMISSION = 15;

    public static final int TETHER_ERROR_NO_CHANGE_TETHERING_PERMISSION = 14;

    public static final int TETHER_ERROR_NO_ERROR = 0;

    public static final int TETHER_ERROR_PROVISIONING_FAILED = 11;

    public static final int TETHER_ERROR_SERVICE_UNAVAIL = 2;

    public static final int TETHER_ERROR_TETHER_IFACE_ERROR = 6;

    public static final int TETHER_ERROR_UNAVAIL_IFACE = 4;

    public static final int TETHER_ERROR_UNKNOWN_IFACE = 1;

    public static final int TETHER_ERROR_UNKNOWN_REQUEST = 17;

    public static final int TETHER_ERROR_UNKNOWN_TYPE = 16;

    public static final int TETHER_ERROR_UNSUPPORTED = 3;

    public static final int TETHER_ERROR_UNTETHER_IFACE_ERROR = 7;

    private TetheringManager() {
    }
}
