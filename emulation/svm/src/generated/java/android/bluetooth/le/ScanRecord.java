package android.bluetooth.le;

/**
 * Represents a scan record from Bluetooth LE scan.
 */
public final class ScanRecord {
    /**
     * Data type is 3D Information Data, see the Bluetooth Generic Access Profile for more details.
     */
    public static final int DATA_TYPE_3D_INFORMATION_DATA = 61;

    /**
     * Data type is Advertising Interval, see the Bluetooth Generic Access Profile for more details.
     */
    public static final int DATA_TYPE_ADVERTISING_INTERVAL = 26;

    /**
     * Data type is Advertising Interval - long, see the Bluetooth Generic Access Profile for more
     * details.
     */
    public static final int DATA_TYPE_ADVERTISING_INTERVAL_LONG = 47;

    /**
     * Data type is Appearance, see the Bluetooth Generic Access Profile for more details.
     */
    public static final int DATA_TYPE_APPEARANCE = 25;

    /**
     * Data type is BIGInfo, see the Bluetooth Generic Access Profile for more details.
     */
    public static final int DATA_TYPE_BIG_INFO = 44;

    /**
     * Data type is Broadcast_Code, see the Bluetooth Generic Access Profile for more details.
     */
    public static final int DATA_TYPE_BROADCAST_CODE = 45;

    /**
     * Data type is Channel Map Update Indication, see the Bluetooth Generic Access Profile for more
     * details.
     */
    public static final int DATA_TYPE_CHANNEL_MAP_UPDATE_INDICATION = 40;

    /**
     * Data type is Class of Device, see the Bluetooth Generic Access Profile for more details.
     */
    public static final int DATA_TYPE_CLASS_OF_DEVICE = 13;

    /**
     * Data type is Device ID, see the Bluetooth Generic Access Profile for more details.
     */
    public static final int DATA_TYPE_DEVICE_ID = 16;

    /**
     * Data type is Flags, see the Bluetooth Generic Access Profile for more details.
     */
    public static final int DATA_TYPE_FLAGS = 1;

    /**
     * Data type is Indoor Positioning, see the Bluetooth Generic Access Profile for more details.
     */
    public static final int DATA_TYPE_INDOOR_POSITIONING = 37;

    /**
     * Data type is LE Bluetooth Device Address, see the Bluetooth Generic Access Profile for more
     * details.
     */
    public static final int DATA_TYPE_LE_BLUETOOTH_DEVICE_ADDRESS = 27;

    /**
     * Data type is LE Role, see the Bluetooth Generic Access Profile for more details.
     */
    public static final int DATA_TYPE_LE_ROLE = 28;

    /**
     * Data type is LE Secure Connections Confirmation Value, see the Bluetooth Generic Access
     * Profile for more details.
     */
    public static final int DATA_TYPE_LE_SECURE_CONNECTIONS_CONFIRMATION_VALUE = 34;

    /**
     * Data type is LE Secure Connections Random Value, see the Bluetooth Generic Access Profile for
     * more details.
     */
    public static final int DATA_TYPE_LE_SECURE_CONNECTIONS_RANDOM_VALUE = 35;

    /**
     * Data type is LE Supported Features, see the Bluetooth Generic Access Profile for more
     * details.
     */
    public static final int DATA_TYPE_LE_SUPPORTED_FEATURES = 39;

    /**
     * Data type is Complete Local Name, see the Bluetooth Generic Access Profile for more details.
     */
    public static final int DATA_TYPE_LOCAL_NAME_COMPLETE = 9;

    /**
     * Data type is Shortened Local Name, see the Bluetooth Generic Access Profile for more details.
     */
    public static final int DATA_TYPE_LOCAL_NAME_SHORT = 8;

    /**
     * Data type is Manufacturer Specific Data, see the Bluetooth Generic Access Profile for more
     * details.
     */
    public static final int DATA_TYPE_MANUFACTURER_SPECIFIC_DATA = 255;

    /**
     * Data type is Mesh Beacon, see the Bluetooth Generic Access Profile for more details.
     */
    public static final int DATA_TYPE_MESH_BEACON = 43;

    /**
     * Data type is Mesh Message, see the Bluetooth Generic Access Profile for more details.
     */
    public static final int DATA_TYPE_MESH_MESSAGE = 42;

    /**
     * Data type is not set for the filter. Will not filter advertising data type.
     */
    public static final int DATA_TYPE_NONE = -1;

    /**
     * Data type is PB-ADV, see the Bluetooth Generic Access Profile for more details.
     */
    public static final int DATA_TYPE_PB_ADV = 41;

    /**
     * Data type is Public Target Address, see the Bluetooth Generic Access Profile for more
     * details.
     */
    public static final int DATA_TYPE_PUBLIC_TARGET_ADDRESS = 23;

    /**
     * Data type is Random Target Address, see the Bluetooth Generic Access Profile for more
     * details.
     */
    public static final int DATA_TYPE_RANDOM_TARGET_ADDRESS = 24;

    /**
     * Data type is Resolvable Set Identifier, see the Bluetooth Generic Access Profile for more
     * details.
     */
    public static final int DATA_TYPE_RESOLVABLE_SET_IDENTIFIER = 46;

    /**
     * Data type is Security Manager Out of Band Flags, see the Bluetooth Generic Access Profile for
     * more details.
     */
    public static final int DATA_TYPE_SECURITY_MANAGER_OUT_OF_BAND_FLAGS = 17;

    /**
     * Data type is Service Data - 128-bit UUID, see the Bluetooth Generic Access Profile for more
     * details.
     */
    public static final int DATA_TYPE_SERVICE_DATA_128_BIT = 33;

    /**
     * Data type is Service Data - 16-bit UUID, see the Bluetooth Generic Access Profile for more
     * details.
     */
    public static final int DATA_TYPE_SERVICE_DATA_16_BIT = 22;

    /**
     * Data type is Service Data - 32-bit UUID, see the Bluetooth Generic Access Profile for more
     * details.
     */
    public static final int DATA_TYPE_SERVICE_DATA_32_BIT = 32;

    /**
     * Data type is List of 128-bit Service Solicitation UUIDs, see the Bluetooth Generic Access
     * Profile for more details.
     */
    public static final int DATA_TYPE_SERVICE_SOLICITATION_UUIDS_128_BIT = 21;

    /**
     * Data type is List of 16-bit Service Solicitation UUIDs, see the Bluetooth Generic Access
     * Profile for more details.
     */
    public static final int DATA_TYPE_SERVICE_SOLICITATION_UUIDS_16_BIT = 20;

    /**
     * Data type is List of 32-bit Service Solicitation UUIDs, see the Bluetooth Generic Access
     * Profile for more details.
     */
    public static final int DATA_TYPE_SERVICE_SOLICITATION_UUIDS_32_BIT = 31;

    /**
     * Data type is Complete List of 128-bit Service Class UUIDs, see the Bluetooth Generic Access
     * Profile for more details.
     */
    public static final int DATA_TYPE_SERVICE_UUIDS_128_BIT_COMPLETE = 7;

    /**
     * Data type is Incomplete List of 128-bit Service Class UUIDs, see the Bluetooth Generic Access
     * Profile for the details.
     */
    public static final int DATA_TYPE_SERVICE_UUIDS_128_BIT_PARTIAL = 6;

    /**
     * Data type is Complete List of 16-bit Service Class UUIDs, see the Bluetooth Generic Access
     * Profile for more details.
     */
    public static final int DATA_TYPE_SERVICE_UUIDS_16_BIT_COMPLETE = 3;

    /**
     * Data type is Incomplete List of 16-bit Service Class UUIDs, see the Bluetooth Generic Access
     * Profile for the details.
     */
    public static final int DATA_TYPE_SERVICE_UUIDS_16_BIT_PARTIAL = 2;

    /**
     * Data type is Complete List of 32-bit Service Class UUIDs, see the Bluetooth Generic Access
     * Profile for more details.
     */
    public static final int DATA_TYPE_SERVICE_UUIDS_32_BIT_COMPLETE = 5;

    /**
     * Data type is Incomplete List of 32-bit Service Class UUIDs, see the Bluetooth Generic Access
     * Profile for the details.
     */
    public static final int DATA_TYPE_SERVICE_UUIDS_32_BIT_PARTIAL = 4;

    /**
     * Data type is Simple Pairing Hash C, see the Bluetooth Generic Access Profile for more
     * details.
     */
    public static final int DATA_TYPE_SIMPLE_PAIRING_HASH_C = 14;

    /**
     * Data type is Simple Pairing Hash C-256, see the Bluetooth Generic Access Profile for more
     * details.
     */
    public static final int DATA_TYPE_SIMPLE_PAIRING_HASH_C_256 = 29;

    /**
     * Data type is Simple Pairing Randomizer R, see the Bluetooth Generic Access Profile for more
     * details.
     */
    public static final int DATA_TYPE_SIMPLE_PAIRING_RANDOMIZER_R = 15;

    /**
     * Data type is Simple Pairing Randomizer R-256, see the Bluetooth Generic Access Profile for
     * more details.
     */
    public static final int DATA_TYPE_SIMPLE_PAIRING_RANDOMIZER_R_256 = 30;

    /**
     * Data type is Slave Connection Interval Range, see the Bluetooth Generic Access Profile for
     * more details.
     */
    public static final int DATA_TYPE_SLAVE_CONNECTION_INTERVAL_RANGE = 18;

    /**
     * Data type is Transport Discovery Data, see the Bluetooth Generic Access Profile for more
     * details.
     */
    public static final int DATA_TYPE_TRANSPORT_DISCOVERY_DATA = 38;

    /**
     * Data type is Tx Power Level, see the Bluetooth Generic Access Profile for more details.
     */
    public static final int DATA_TYPE_TX_POWER_LEVEL = 10;

    /**
     * Data type is URI, see the Bluetooth Generic Access Profile for more details.
     */
    public static final int DATA_TYPE_URI = 36;

    private ScanRecord() {
    }
}
