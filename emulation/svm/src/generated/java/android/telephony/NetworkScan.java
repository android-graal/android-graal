package android.telephony;

/**
 * The caller of
 * {@link TelephonyManager#requestNetworkScan(NetworkScanRequest, Executor, NetworkScanCallback)}
 * will receive an instance of {@link NetworkScan}, which contains a callback method
 * {@link #stopScan()} for stopping the in-progress scan.
 */
public final class NetworkScan {
    /**
     * The scan has been interrupted by another scan with higher priority.
     */
    public static final int ERROR_INTERRUPTED = 10002;

    /**
     * The parameters of the scan is invalid.
     */
    public static final int ERROR_INVALID_SCAN = 2;

    /**
     * The scan ID is invalid. The user is either trying to stop a scan which does not exist
     * or started by others.
     */
    public static final int ERROR_INVALID_SCANID = 10001;

    /**
     * The scan has failed due to some modem errors.
     */
    public static final int ERROR_MODEM_ERROR = 1;

    /**
     * The modem can not perform the scan because it is doing something else.
     */
    public static final int ERROR_MODEM_UNAVAILABLE = 3;

    /**
     * The RIL returns nothing or exceptions.
     */
    public static final int ERROR_RADIO_INTERFACE_ERROR = 10000;

    /**
     * The modem does not support the request scan.
     */
    public static final int ERROR_UNSUPPORTED = 4;

    /**
     * The RIL has successfully performed the network scan.
     */
    public static final int SUCCESS = 0;

    private NetworkScan() {
    }
}
