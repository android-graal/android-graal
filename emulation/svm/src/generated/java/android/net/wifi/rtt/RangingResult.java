package android.net.wifi.rtt;

import android.os.Parcelable;

/**
 * Ranging result for a request started by
 * {@link WifiRttManager#startRanging(RangingRequest, java.util.concurrent.Executor, RangingResultCallback)}.
 * Results are returned in {@link RangingResultCallback#onRangingResults(List)}.
 * <p>
 * A ranging result is the distance measurement result for a single device specified in the
 * {@link RangingRequest}.
 */
public final class RangingResult implements Parcelable {
    /**
     * Individual range request status, {@link #getStatus()}. Indicates ranging operation failed
     * and the distance value is invalid.
     */
    public static final int STATUS_FAIL = 1;

    /**
     * Individual range request status, {@link #getStatus()}. Indicates that the ranging operation
     * failed because the specified peer does not support IEEE 802.11mc RTT operations. Support by
     * an Access Point can be confirmed using
     * {@link android.net.wifi.ScanResult#is80211mcResponder()}.
     * <p>
     * On such a failure, the individual result fields of {@link RangingResult} such as
     * {@link RangingResult#getDistanceMm()} are invalid.
     */
    public static final int STATUS_RESPONDER_DOES_NOT_SUPPORT_IEEE80211MC = 2;

    /**
     * Individual range request status, {@link #getStatus()}. Indicates ranging operation was
     * successful and distance value is valid.
     */
    public static final int STATUS_SUCCESS = 0;

    /**
     * The unspecified value.
     */
    public static final int UNSPECIFIED = -1;

    private RangingResult() {
    }
}
