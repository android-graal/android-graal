package android.ranging.raw;

import android.os.Parcelable;

/**
 * Represents a device participating in ranging operations.
 * This class supports multiple ranging technologies, including UWB, BLE CS, BLE RSSI and Wi-Fi
 * NAN-RTT. The configuration for each technology is provided through corresponding parameter
 * objects.
 */
public final class RawRangingDevice implements Parcelable {
    /**
     * Frequent ranging interval.
     * <ul>
     *     <li> UWB - 96 milliseconds for config id
     *     {@link UwbRangingParams#CONFIG_PROVISIONED_UNICAST_DS_TWR_VERY_FAST}, 120 milliseconds
     *     otherwise. See {@link UwbRangingCapabilities#getSupportedRangingUpdateRates()} to verify
     *     Frequent update rate is supported. </li>
     *     <li> BLE RSSI - 500 milliseconds. </li>
     *     <li> BLE CS - 100 milliseconds. </li>
     *     <li> WiFi Rtt - 128 milliseconds if
     *     {@link RttRangingCapabilities#hasPeriodicRangingHardwareFeature()} is true, 256
     *     milliseconds otherwise. </li>
     * </ul>
     */
    public static final int UPDATE_RATE_FREQUENT = 3;

    /**
     * Infrequent ranging interval.
     * <ul>
     *     <li> UWB - 600 milliseconds. </li>
     *     <li> BLE RSSI - 3 seconds. </li>
     *     <li> BLE CS - 5 seconds. </li>
     *     <li> WiFi Rtt - 8192 milliseconds otherwise. </li>
     * </ul>
     */
    public static final int UPDATE_RATE_INFREQUENT = 2;

    /**
     * Normal ranging interval (Default).
     * <ul>
     *     <li> UWB - 200 milliseconds for config ids {@link UwbRangingParams#getConfigId()} with
     *     multicast ranging, 240 milliseconds for unicast. </li>
     *     <li> BLE RSSI - 1 second. </li>
     *     <li> BLE CS - 200 ms. </li>
     *     <li> WiFi Rtt - 256 milliseconds if
     *     {@link RttRangingCapabilities#hasPeriodicRangingHardwareFeature()} is true, 512
     *     milliseconds otherwise. </li>
     * </ul>
     */
    public static final int UPDATE_RATE_NORMAL = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private RawRangingDevice() {
    }
}
