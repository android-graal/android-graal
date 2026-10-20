package android.telephony;

import android.os.Parcelable;

/**
 * TelephonyDisplayInfo contains telephony-related information used for display purposes only. This
 * information is provided in accordance with carrier policy and branding preferences; it is not
 * necessarily a precise or accurate representation of the current state and should be treated
 * accordingly.
 * To be notified of changes in TelephonyDisplayInfo, use
 * {@link TelephonyManager#registerTelephonyCallback} with a {@link TelephonyCallback}
 * that implements {@link TelephonyCallback.DisplayInfoListener}.
 * Override the onDisplayInfoChanged() method to handle the broadcast.
 */
public final class TelephonyDisplayInfo implements Parcelable {
    /**
     * Override network type when the device is connected to advanced pro
     * {@link TelephonyManager#NETWORK_TYPE_LTE} cellular network.
     */
    public static final int OVERRIDE_NETWORK_TYPE_LTE_ADVANCED_PRO = 2;

    /**
     * Override network type when the device is connected to
     * {@link TelephonyManager#NETWORK_TYPE_LTE} cellular network and is using carrier aggregation.
     */
    public static final int OVERRIDE_NETWORK_TYPE_LTE_CA = 1;

    /**
     * No override. {@link #getNetworkType()} should be used for display network
     * type.
     */
    public static final int OVERRIDE_NETWORK_TYPE_NONE = 0;

    /**
     * Override network type when the device is connected NR cellular network and the data rate is
     * higher than the generic 5G date rate.
     * Including but not limited to
     * <ul>
     *   <li>The device is connected to the NR cellular network on millimeter wave bands. </li>
     *   <li>The device is connected to the specific network which the carrier is using
     *   proprietary means to provide a faster overall data connection than would be otherwise
     *   possible.  This may include using other bands unique to the carrier, or carrier
     *   aggregation, for example.</li>
     * </ul>
     * One of the use case is that UX can show a different icon, for example, "5G+"
     */
    public static final int OVERRIDE_NETWORK_TYPE_NR_ADVANCED = 5;

    /**
     * Override network type when the device is connected to
     * {@link TelephonyManager#NETWORK_TYPE_LTE} network and has E-UTRA-NR Dual Connectivity(EN-DC)
     * capability or is currently connected to the secondary
     * {@link TelephonyManager#NETWORK_TYPE_NR} cellular network.
     */
    public static final int OVERRIDE_NETWORK_TYPE_NR_NSA = 3;

    /**
     * Override network type when the device is connected to
     * {@link TelephonyManager#NETWORK_TYPE_LTE} network and has E-UTRA-NR Dual Connectivity(EN-DC)
     * capability or is currently connected to the secondary
     * {@link TelephonyManager#NETWORK_TYPE_NR} cellular network on millimeter wave bands.
     * @deprecated Use{@link #OVERRIDE_NETWORK_TYPE_NR_ADVANCED} instead.
     */
    @Deprecated
    public static final int OVERRIDE_NETWORK_TYPE_NR_NSA_MMWAVE = 4;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private TelephonyDisplayInfo() {
    }
}
