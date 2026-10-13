package android.bluetooth;

import android.os.Parcelable;

/**
 * Represents a Bluetooth GATT Characteristic
 *
 * <p>A GATT characteristic is a basic data element used to construct a GATT service, {@link
 * BluetoothGattService}. The characteristic contains a value as well as additional information and
 * optional GATT descriptors, {@link BluetoothGattDescriptor}.
 */
public final class BluetoothGattCharacteristic implements Parcelable {
    /**
     * Characteristic value format type float (32-bit float)
     */
    public static final int FORMAT_FLOAT = 52;

    /**
     * Characteristic value format type sfloat (16-bit float)
     */
    public static final int FORMAT_SFLOAT = 50;

    /**
     * Characteristic value format type sint16
     */
    public static final int FORMAT_SINT16 = 34;

    /**
     * Characteristic value format type sint32
     */
    public static final int FORMAT_SINT32 = 36;

    /**
     * Characteristic value format type sint8
     */
    public static final int FORMAT_SINT8 = 33;

    /**
     * Characteristic value format type uint16
     */
    public static final int FORMAT_UINT16 = 18;

    /**
     * Characteristic value format type uint32
     */
    public static final int FORMAT_UINT32 = 20;

    /**
     * Characteristic value format type uint8
     */
    public static final int FORMAT_UINT8 = 17;

    /**
     * Characteristic read permission
     */
    public static final int PERMISSION_READ = 1;

    /**
     * Characteristic permission: Allow encrypted read operations
     */
    public static final int PERMISSION_READ_ENCRYPTED = 2;

    /**
     * Characteristic permission: Allow reading with person-in-the-middle protection
     */
    public static final int PERMISSION_READ_ENCRYPTED_MITM = 4;

    /**
     * Characteristic write permission
     */
    public static final int PERMISSION_WRITE = 16;

    /**
     * Characteristic permission: Allow encrypted writes
     */
    public static final int PERMISSION_WRITE_ENCRYPTED = 32;

    /**
     * Characteristic permission: Allow encrypted writes with person-in-the-middle protection
     */
    public static final int PERMISSION_WRITE_ENCRYPTED_MITM = 64;

    /**
     * Characteristic permission: Allow signed write operations
     */
    public static final int PERMISSION_WRITE_SIGNED = 128;

    /**
     * Characteristic permission: Allow signed write operations with person-in-the-middle protection
     */
    public static final int PERMISSION_WRITE_SIGNED_MITM = 256;

    /**
     * Characteristic property: Characteristic is broadcastable.
     */
    public static final int PROPERTY_BROADCAST = 1;

    /**
     * Characteristic property: Characteristic has extended properties
     */
    public static final int PROPERTY_EXTENDED_PROPS = 128;

    /**
     * Characteristic property: Characteristic supports indication
     */
    public static final int PROPERTY_INDICATE = 32;

    /**
     * Characteristic property: Characteristic supports notification
     */
    public static final int PROPERTY_NOTIFY = 16;

    /**
     * Characteristic property: Characteristic is readable.
     */
    public static final int PROPERTY_READ = 2;

    /**
     * Characteristic property: Characteristic supports write with signature
     */
    public static final int PROPERTY_SIGNED_WRITE = 64;

    /**
     * Characteristic property: Characteristic can be written.
     */
    public static final int PROPERTY_WRITE = 8;

    /**
     * Characteristic property: Characteristic can be written without response.
     */
    public static final int PROPERTY_WRITE_NO_RESPONSE = 4;

    /**
     * Write characteristic, requesting acknowledgement by the remote device
     */
    public static final int WRITE_TYPE_DEFAULT = 2;

    /**
     * Write characteristic without requiring a response by the remote device
     */
    public static final int WRITE_TYPE_NO_RESPONSE = 1;

    /**
     * Write characteristic including authentication signature
     */
    public static final int WRITE_TYPE_SIGNED = 4;

    private BluetoothGattCharacteristic() {
    }
}
