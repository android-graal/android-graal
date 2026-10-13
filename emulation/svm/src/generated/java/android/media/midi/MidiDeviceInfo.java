package android.media.midi;

import android.os.Parcelable;

/**
 * This class contains information to describe a MIDI device.
 * For now we only have information that can be retrieved easily for USB devices,
 * but we will probably expand this in the future.
 *
 * This class is just an immutable object to encapsulate the MIDI device description.
 * Use the MidiDevice class to actually communicate with devices.
 */
public final class MidiDeviceInfo implements Parcelable {
    /**
     * Bundle key for the device's corresponding Bluetooth device.
     * The value for this property is of type {@link android.bluetooth.BluetoothDevice}.
     * Only set for Bluetooth MIDI devices.
     * Used with the {@link android.os.Bundle} returned by {@link #getProperties}
     */
    public static final String PROPERTY_BLUETOOTH_DEVICE = "bluetooth_device";

    /**
     * Bundle key for the device's manufacturer name property.
     * The value for this property is of type {@link java.lang.String}.
     * Used with the {@link android.os.Bundle} returned by {@link #getProperties}.
     * Matches the USB device manufacturer name string for USB MIDI devices.
     */
    public static final String PROPERTY_MANUFACTURER = "manufacturer";

    /**
     * Bundle key for the device's user visible name property.
     * The value for this property is of type {@link java.lang.String}.
     * Used with the {@link android.os.Bundle} returned by {@link #getProperties}.
     * For USB devices, this is a concatenation of the manufacturer and product names.
     */
    public static final String PROPERTY_NAME = "name";

    /**
     * Bundle key for the device's product name property.
     * The value for this property is of type {@link java.lang.String}.
     * Used with the {@link android.os.Bundle} returned by {@link #getProperties}
     * Matches the USB device product name string for USB MIDI devices.
     */
    public static final String PROPERTY_PRODUCT = "product";

    /**
     * Bundle key for the device's serial number property.
     * The value for this property is of type {@link java.lang.String}.
     * Used with the {@link android.os.Bundle} returned by {@link #getProperties}
     * Matches the USB device serial number for USB MIDI devices.
     */
    public static final String PROPERTY_SERIAL_NUMBER = "serial_number";

    /**
     * Bundle key for the device's corresponding USB device.
     * The value for this property is of type {@link android.hardware.usb.UsbDevice}.
     * Only set for USB MIDI devices.
     * Used with the {@link android.os.Bundle} returned by {@link #getProperties}
     */
    public static final String PROPERTY_USB_DEVICE = "usb_device";

    /**
     * Bundle key for the device's version property.
     * The value for this property is of type {@link java.lang.String}.
     * Used with the {@link android.os.Bundle} returned by {@link #getProperties}
     * Matches the USB device version number for USB MIDI devices.
     */
    public static final String PROPERTY_VERSION = "version";

    /**
     * Constant representing a default protocol with Universal MIDI Packets (UMP).
     * UMP is defined in "Universal MIDI Packet (UMP) Format and MIDI 2.0 Protocol" spec.
     * All UMP data should be a multiple of 4 bytes.
     * Use MIDI 1.0 through UMP with packet sizes up to 128 bits.
     * Call {@link MidiManager#getDevicesForTransport} with parameter
     * {@link MidiManager#TRANSPORT_UNIVERSAL_MIDI_PACKETS} to get devices with this transport.
     * @see MidiDeviceInfo#getDefaultProtocol
     */
    public static final int PROTOCOL_UMP_MIDI_1_0_UP_TO_128_BITS = 3;

    /**
     * Constant representing a default protocol with Universal MIDI Packets (UMP).
     * UMP is defined in "Universal MIDI Packet (UMP) Format and MIDI 2.0 Protocol" spec.
     * All UMP data should be a multiple of 4 bytes.
     * Use MIDI 1.0 through UMP with packet sizes up to 128 bits and jitter reduction timestamps.
     * Call {@link MidiManager#getDevicesForTransport} with parameter
     * {@link MidiManager#TRANSPORT_UNIVERSAL_MIDI_PACKETS} to get devices with this transport.
     * @see MidiDeviceInfo#getDefaultProtocol
     */
    public static final int PROTOCOL_UMP_MIDI_1_0_UP_TO_128_BITS_AND_JRTS = 4;

    /**
     * Constant representing a default protocol with Universal MIDI Packets (UMP).
     * UMP is defined in "Universal MIDI Packet (UMP) Format and MIDI 2.0 Protocol" spec.
     * All UMP data should be a multiple of 4 bytes.
     * Use MIDI 1.0 through UMP with packet sizes up to 64 bits.
     * Call {@link MidiManager#getDevicesForTransport} with parameter
     * {@link MidiManager#TRANSPORT_UNIVERSAL_MIDI_PACKETS} to get devices with this transport.
     * @see MidiDeviceInfo#getDefaultProtocol
     */
    public static final int PROTOCOL_UMP_MIDI_1_0_UP_TO_64_BITS = 1;

    /**
     * Constant representing a default protocol with Universal MIDI Packets (UMP).
     * UMP is defined in "Universal MIDI Packet (UMP) Format and MIDI 2.0 Protocol" spec.
     * All UMP data should be a multiple of 4 bytes.
     * Use MIDI 1.0 through UMP with packet sizes up to 64 bits and jitter reduction timestamps.
     * Call {@link MidiManager#getDevicesForTransport} with parameter
     * {@link MidiManager#TRANSPORT_UNIVERSAL_MIDI_PACKETS} to get devices with this transport.
     * @see MidiDeviceInfo#getDefaultProtocol
     */
    public static final int PROTOCOL_UMP_MIDI_1_0_UP_TO_64_BITS_AND_JRTS = 2;

    /**
     * Constant representing a default protocol with Universal MIDI Packets (UMP).
     * UMP is defined in "Universal MIDI Packet (UMP) Format and MIDI 2.0 Protocol" spec.
     * All UMP data should be a multiple of 4 bytes.
     * Use MIDI 2.0 through UMP.
     * Call {@link MidiManager#getDevicesForTransport} with parameter
     * {@link MidiManager#TRANSPORT_UNIVERSAL_MIDI_PACKETS} to get devices with this transport.
     * @see MidiDeviceInfo#getDefaultProtocol
     */
    public static final int PROTOCOL_UMP_MIDI_2_0 = 17;

    /**
     * Constant representing a default protocol with Universal MIDI Packets (UMP).
     * UMP is defined in "Universal MIDI Packet (UMP) Format and MIDI 2.0 Protocol" spec.
     * All UMP data should be a multiple of 4 bytes.
     * Use MIDI 2.0 through UMP and jitter reduction timestamps.
     * Call {@link MidiManager#getDevicesForTransport} with parameter
     * {@link MidiManager#TRANSPORT_UNIVERSAL_MIDI_PACKETS} to get devices with this transport.
     * @see MidiDeviceInfo#getDefaultProtocol
     */
    public static final int PROTOCOL_UMP_MIDI_2_0_AND_JRTS = 18;

    /**
     * Constant representing a default protocol with Universal MIDI Packets (UMP).
     * UMP is defined in "Universal MIDI Packet (UMP) Format and MIDI 2.0 Protocol" spec.
     * All UMP data should be a multiple of 4 bytes.
     * Use UMP to negotiate with the device with MIDI-CI.
     * MIDI-CI is defined in "MIDI Capability Inquiry (MIDI-CI)" spec.
     * Call {@link MidiManager#getDevicesForTransport} with parameter
     * {@link MidiManager#TRANSPORT_UNIVERSAL_MIDI_PACKETS} to get devices with this transport.
     * @see MidiDeviceInfo#getDefaultProtocol
     */
    public static final int PROTOCOL_UMP_USE_MIDI_CI = 0;

    /**
     * Constant representing a device with an unknown default protocol.
     * If Universal MIDI Packets (UMP) are needed, use MIDI-CI through MIDI 1.0.
     * UMP is defined in "Universal MIDI Packet (UMP) Format and MIDI 2.0 Protocol" spec.
     * MIDI-CI is defined in "MIDI Capability Inquiry (MIDI-CI)" spec.
     * @see MidiDeviceInfo#getDefaultProtocol
     */
    public static final int PROTOCOL_UNKNOWN = -1;

    /**
     * Constant representing Bluetooth MIDI devices for {@link #getType}
     */
    public static final int TYPE_BLUETOOTH = 3;

    /**
     * Constant representing USB MIDI devices for {@link #getType}
     */
    public static final int TYPE_USB = 1;

    /**
     * Constant representing virtual (software based) MIDI devices for {@link #getType}
     */
    public static final int TYPE_VIRTUAL = 2;

    private MidiDeviceInfo() {
    }

    /**
     * Contains information about an input or output port.
     */
    public static final class PortInfo {
        /**
         * Port type for input ports
         */
        public static final int TYPE_INPUT = 1;

        /**
         * Port type for output ports
         */
        public static final int TYPE_OUTPUT = 2;

        private PortInfo() {
        }
    }
}
