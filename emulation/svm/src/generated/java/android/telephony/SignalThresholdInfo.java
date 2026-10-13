package android.telephony;

import android.os.Parcelable;

/**
 * Defines the threshold value of the signal strength.
 */
public final class SignalThresholdInfo implements Parcelable {
    /**
     * The ratio between the received energy from the pilot signal CPICH per chip (Ec) to the
     * noise density (No).
     * Range: -24 dBm to 1 dBm.
     * Used RAN: {@link AccessNetworkConstants.AccessNetworkType#UTRAN}
     * Reference: 3GPP TS 25.215 5.1.5
     */
    public static final int SIGNAL_MEASUREMENT_TYPE_ECNO = 9;

    /**
     * Received Signal Code Power.
     * Range: -120 dBm to -25 dBm;
     * Used RAN: {@link AccessNetworkConstants.AccessNetworkType#UTRAN}
     * Reference: 3GPP TS 25.123, section 9.1.1.1
     */
    public static final int SIGNAL_MEASUREMENT_TYPE_RSCP = 2;

    /**
     * Reference Signal Received Power.
     * Range: -140 dBm to -44 dBm;
     * Used RAN: {@link AccessNetworkConstants.AccessNetworkType#EUTRAN}
     * Reference: 3GPP TS 36.133 9.1.4
     */
    public static final int SIGNAL_MEASUREMENT_TYPE_RSRP = 3;

    /**
     * Reference Signal Received Quality
     * Range: -34 dB to 3 dB;
     * Used RAN: {@link AccessNetworkConstants.AccessNetworkType#EUTRAN}
     * Reference: 3GPP TS 36.133 9.1.7
     */
    public static final int SIGNAL_MEASUREMENT_TYPE_RSRQ = 4;

    /**
     * Received Signal Strength Indication.
     * Range: -113 dBm and -51 dBm
     * Used RAN: {@link AccessNetworkConstants.AccessNetworkType#GERAN},
     *           {@link AccessNetworkConstants.AccessNetworkType#CDMA2000}
     * Reference: 3GPP TS 27.007 section 8.5.
     */
    public static final int SIGNAL_MEASUREMENT_TYPE_RSSI = 1;

    /**
     * Reference Signal Signal to Noise Ratio
     * Range: -20 dB to 30 dB;
     * Used RAN: {@link AccessNetworkConstants.AccessNetworkType#EUTRAN}
     */
    public static final int SIGNAL_MEASUREMENT_TYPE_RSSNR = 5;

    /**
     * 5G SS reference signal received power.
     * Range: -140 dBm to -44 dBm.
     * Used RAN: {@link AccessNetworkConstants.AccessNetworkType#NGRAN}
     * Reference: 3GPP TS 38.215.
     */
    public static final int SIGNAL_MEASUREMENT_TYPE_SSRSRP = 6;

    /**
     * 5G SS reference signal received quality.
     * Range: -43 dB to 20 dB.
     * Used RAN: {@link AccessNetworkConstants.AccessNetworkType#NGRAN}
     * Reference: 3GPP TS 38.133 section 10.1.11.1.
     */
    public static final int SIGNAL_MEASUREMENT_TYPE_SSRSRQ = 7;

    /**
     * 5G SS signal-to-noise and interference ratio.
     * Range: -23 dB to 40 dB
     * Used RAN: {@link AccessNetworkConstants.AccessNetworkType#NGRAN}
     * Reference: 3GPP TS 38.215 section 5.1.*, 3GPP TS 38.133 section 10.1.16.1.
     */
    public static final int SIGNAL_MEASUREMENT_TYPE_SSSINR = 8;

    /**
     * Unknown signal measurement type.
     */
    public static final int SIGNAL_MEASUREMENT_TYPE_UNKNOWN = 0;

    private SignalThresholdInfo() {
    }
}
