package android.bluetooth;

import android.os.Parcelable;

/**
 * Represents the Quality of Service (QoS) settings for a Bluetooth HID Device application.
 *
 * <p>The BluetoothHidDevice framework will update the L2CAP QoS settings for the app during
 * registration.
 *
 * @see BluetoothHidDevice
 */
public final class BluetoothHidDeviceAppQosSettings implements Parcelable {
    public static final int MAX = -1;

    public static final int SERVICE_BEST_EFFORT = 1;

    public static final int SERVICE_GUARANTEED = 2;

    public static final int SERVICE_NO_TRAFFIC = 0;

    private BluetoothHidDeviceAppQosSettings() {
    }
}
