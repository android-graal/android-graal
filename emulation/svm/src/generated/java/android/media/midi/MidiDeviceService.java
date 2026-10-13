package android.media.midi;

/**
 * A service that implements a virtual MIDI device.
 * Subclasses must implement the {@link #onGetInputPortReceivers} method to provide a
 * list of {@link MidiReceiver}s to receive data sent to the device's input ports.
 * Similarly, subclasses can call {@link #getOutputPortReceivers} to fetch a list
 * of {@link MidiReceiver}s for sending data out the output ports.
 *
 * <p>To extend this class, you must declare the service in your manifest file with
 * an intent filter with the {@link #SERVICE_INTERFACE} action
 * and meta-data to describe the virtual device.
 * For example:</p>
 * <pre>
 * &lt;service android:name=".VirtualDeviceService"
 *         android:label="&#64;string/service_name">
 *     &lt;intent-filter>
 *             &lt;action android:name="android.media.midi.MidiDeviceService" />
 *     &lt;/intent-filter>
 *     &lt;meta-data android:name="android.media.midi.MidiDeviceService"
 *             android:resource="@xml/device_info" />
 * &lt;/service></pre>
 */
public final class MidiDeviceService {
    public static final String SERVICE_INTERFACE = "android.media.midi.MidiDeviceService";

    private MidiDeviceService() {
    }
}
