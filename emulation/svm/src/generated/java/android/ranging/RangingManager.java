package android.ranging;

/**
 * This class provides a way to perform ranging operations such as querying the
 * device's capabilities and determining the distance and angle between the local device and a
 * remote device.
 *
 * <p>To get a {@link RangingManager}, call the
 * <code>Context.getSystemService(RangingManager.class)</code>.
 */
public final class RangingManager {
    /**
     * Bluetooth Channel Sounding (BT-CS) technology.
     */
    public static final int BLE_CS = 1;

    /**
     * Bluetooth Low Energy (BLE) RSSI-based ranging technology.
     */
    public static final int BLE_RSSI = 3;

    /**
     * Ultra-Wideband (UWB) technology.
     */
    public static final int UWB = 0;

    /**
     * WiFi Round Trip Time (WiFi-RTT) technology.
     */
    public static final int WIFI_NAN_RTT = 2;

    private RangingManager() {
    }
}
