package android.bluetooth;

import android.os.Parcelable;

/**
 * Represents a Bluetooth GATT Descriptor
 *
 * <p>GATT Descriptors contain additional information and attributes of a GATT characteristic,
 * {@link BluetoothGattCharacteristic}. They can be used to describe the characteristic's features
 * or to control certain behaviours of the characteristic.
 */
public final class BluetoothGattDescriptor implements Parcelable {
    /**
     * Descriptor read permission
     */
    public static final int PERMISSION_READ = 1;

    /**
     * Descriptor permission: Allow encrypted read operations
     */
    public static final int PERMISSION_READ_ENCRYPTED = 2;

    /**
     * Descriptor permission: Allow reading with person-in-the-middle protection
     */
    public static final int PERMISSION_READ_ENCRYPTED_MITM = 4;

    /**
     * Descriptor write permission
     */
    public static final int PERMISSION_WRITE = 16;

    /**
     * Descriptor permission: Allow encrypted writes
     */
    public static final int PERMISSION_WRITE_ENCRYPTED = 32;

    /**
     * Descriptor permission: Allow encrypted writes with person-in-the-middle protection
     */
    public static final int PERMISSION_WRITE_ENCRYPTED_MITM = 64;

    /**
     * Descriptor permission: Allow signed write operations
     */
    public static final int PERMISSION_WRITE_SIGNED = 128;

    /**
     * Descriptor permission: Allow signed write operations with person-in-the-middle protection
     */
    public static final int PERMISSION_WRITE_SIGNED_MITM = 256;

    private BluetoothGattDescriptor() {
    }
}
