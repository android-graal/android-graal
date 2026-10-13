package android.media.midi;

/**
 * A service that implements a virtual MIDI device for Universal MIDI Packets (UMP).
 * Subclasses must implement the {@link #onGetInputPortReceivers} method to provide a
 * list of {@link MidiReceiver}s to receive data sent to the device's input ports.
 * Similarly, subclasses can call {@link #getOutputPortReceivers} to fetch a list
 * of {@link MidiReceiver}s for sending data out the output ports.
 *
 * Unlike traditional MIDI byte streams, only complete UMPs should be sent.
 * Unlike with {@link MidiDeviceService}, the number of input and output ports must be equal.
 *
 * <p>To extend this class, you must declare the service in your manifest file with
 * an intent filter with the {@link #SERVICE_INTERFACE} action
 * and meta-data to describe the virtual device.
 * For example:</p>
 * <pre>
 * &lt;service android:name=".VirtualDeviceService"
 *         android:label="&#64;string/service_name">
 *     &lt;intent-filter>
 *             &lt;action android:name="android.media.midi.MidiUmpDeviceService" />
 *     &lt;/intent-filter>
 *     &lt;property android:name="android.media.midi.MidiUmpDeviceService"
 *             android:resource="@xml/device_info" />
 * &lt;/service></pre>
 */
public final class MidiUmpDeviceService {
    public static final String SERVICE_INTERFACE = "android.media.midi.MidiUmpDeviceService";

    private MidiUmpDeviceService() {
    }
}
