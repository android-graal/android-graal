package android.bluetooth;

import android.os.Parcelable;

/**
 * Represents a Bluetooth GATT Service
 *
 * <p>Gatt Service contains a collection of {@link BluetoothGattCharacteristic}, as well as
 * referenced services.
 */
public final class BluetoothGattService implements Parcelable {
    /**
     * Primary service
     */
    public static final int SERVICE_TYPE_PRIMARY = 0;

    /**
     * Secondary service (included by primary services)
     */
    public static final int SERVICE_TYPE_SECONDARY = 1;

    private BluetoothGattService() {
    }
}
