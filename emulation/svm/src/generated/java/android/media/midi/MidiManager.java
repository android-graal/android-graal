package android.media.midi;

/**
 * This class is the public application interface to the MIDI service.
 */
public final class MidiManager {
    /**
     * Constant representing MIDI devices.
     * These devices do NOT support Universal MIDI Packets by default.
     * These support the original MIDI 1.0 byte stream.
     * When communicating to a USB device, a raw byte stream will be padded for USB.
     * Likewise, for a Bluetooth device, the raw bytes will be converted for Bluetooth.
     * For virtual devices, the byte stream will be passed directly.
     * If Universal MIDI Packets are needed, please use MIDI-CI.
     * @see MidiManager#getDevicesForTransport
     */
    public static final int TRANSPORT_MIDI_BYTE_STREAM = 1;

    /**
     * Constant representing Universal MIDI devices.
     * These devices do support Universal MIDI Packets (UMP) by default.
     * When sending data to these devices, please send UMP.
     * Packets should always be a multiple of 4 bytes.
     * UMP is defined in the USB MIDI 2.0 spec. Please read the standard for more info.
     * @see MidiManager#getDevicesForTransport
     */
    public static final int TRANSPORT_UNIVERSAL_MIDI_PACKETS = 2;

    private MidiManager() {
    }
}
