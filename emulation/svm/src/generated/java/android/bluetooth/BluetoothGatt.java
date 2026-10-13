package android.bluetooth;

/**
 * Public API for the Bluetooth GATT Profile.
 *
 * <p>This class provides Bluetooth GATT functionality to enable communication with Bluetooth Smart
 * or Smart Ready devices.
 *
 * <p>To connect to a remote peripheral device, create a {@link BluetoothGattCallback} and call
 * {@link BluetoothDevice#connectGatt} to get a instance of this class. GATT capable devices can be
 * discovered using the Bluetooth device discovery or BLE scan process.
 */
public final class BluetoothGatt implements BluetoothProfile {
    /**
     * Connection parameter update - Use the connection parameters recommended by the Bluetooth SIG.
     * This is the default value if no connection parameter update is requested.
     */
    public static final int CONNECTION_PRIORITY_BALANCED = 0;

    /**
     * Connection parameter update - Request the priority preferred for Digital Car Key for a lower
     * latency connection. This connection parameter will consume more power than {@link
     * BluetoothGatt#CONNECTION_PRIORITY_BALANCED}, so it is recommended that apps do not use this
     * unless it specifically fits their use case.
     */
    public static final int CONNECTION_PRIORITY_DCK = 3;

    /**
     * Connection parameter update - Request a high priority, low latency connection. An application
     * should only request high priority connection parameters to transfer large amounts of data
     * over LE quickly. Once the transfer is complete, the application should request {@link
     * BluetoothGatt#CONNECTION_PRIORITY_BALANCED} connection parameters to reduce energy use.
     */
    public static final int CONNECTION_PRIORITY_HIGH = 1;

    /**
     * Connection parameter update - Request low power, reduced data rate connection parameters.
     */
    public static final int CONNECTION_PRIORITY_LOW_POWER = 2;

    /**
     * A remote device connection is congested.
     */
    public static final int GATT_CONNECTION_CONGESTED = 143;

    /**
     * GATT connection timed out, likely due to the remote device being out of range or not
     * advertising as connectable.
     */
    public static final int GATT_CONNECTION_TIMEOUT = 147;

    /**
     * A GATT operation failed, errors other than the above
     */
    public static final int GATT_FAILURE = 257;

    /**
     * Insufficient authentication for a given operation
     */
    public static final int GATT_INSUFFICIENT_AUTHENTICATION = 5;

    /**
     * Insufficient authorization for a given operation
     */
    public static final int GATT_INSUFFICIENT_AUTHORIZATION = 8;

    /**
     * Insufficient encryption for a given operation
     */
    public static final int GATT_INSUFFICIENT_ENCRYPTION = 15;

    /**
     * A write operation exceeds the maximum length of the attribute
     */
    public static final int GATT_INVALID_ATTRIBUTE_LENGTH = 13;

    /**
     * A read or write operation was requested with an invalid offset
     */
    public static final int GATT_INVALID_OFFSET = 7;

    /**
     * GATT read operation is not permitted
     */
    public static final int GATT_READ_NOT_PERMITTED = 2;

    /**
     * The given request is not supported
     */
    public static final int GATT_REQUEST_NOT_SUPPORTED = 6;

    /**
     * A GATT operation completed successfully
     */
    public static final int GATT_SUCCESS = 0;

    /**
     * GATT write operation is not permitted
     */
    public static final int GATT_WRITE_NOT_PERMITTED = 3;

    private BluetoothGatt() {
    }
}
