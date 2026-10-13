package android.hardware.input;

/**
 * Provides information about input devices and available key layouts.
 */
public final class InputManager {
    /**
     * Broadcast Action: Query available keyboard layouts.
     * <p>
     * The input manager service locates available keyboard layouts
     * by querying broadcast receivers that are registered for this action.
     * An application can offer additional keyboard layouts to the user
     * by declaring a suitable broadcast receiver in its manifest.
     * </p><p>
     * Here is an example broadcast receiver declaration that an application
     * might include in its AndroidManifest.xml to advertise keyboard layouts.
     * The meta-data specifies a resource that contains a description of each keyboard
     * layout that is provided by the application.
     * <pre><code>
     * &lt;receiver android:name=".InputDeviceReceiver"
     *         android:label="@string/keyboard_layouts_label">
     *     &lt;intent-filter>
     *         &lt;action android:name="android.hardware.input.action.QUERY_KEYBOARD_LAYOUTS" />
     *     &lt;/intent-filter>
     *     &lt;meta-data android:name="android.hardware.input.metadata.KEYBOARD_LAYOUTS"
     *             android:resource="@xml/keyboard_layouts" />
     * &lt;/receiver>
     * </code></pre>
     * </p><p>
     * In the above example, the <code>@xml/keyboard_layouts</code> resource refers to
     * an XML resource whose root element is <code>&lt;keyboard-layouts></code> that
     * contains zero or more <code>&lt;keyboard-layout></code> elements.
     * Each <code>&lt;keyboard-layout></code> element specifies the name, label, and location
     * of a key character map for a particular keyboard layout.  The label on the receiver
     * is used to name the collection of keyboard layouts provided by this receiver in the
     * keyboard layout settings.
     * <pre><code>
     * &lt;?xml version="1.0" encoding="utf-8"?>
     * &lt;keyboard-layouts xmlns:android="http://schemas.android.com/apk/res/android">
     *     &lt;keyboard-layout android:name="keyboard_layout_english_us"
     *             android:label="@string/keyboard_layout_english_us_label"
     *             android:keyboardLayout="@raw/keyboard_layout_english_us" />
     * &lt;/keyboard-layouts>
     * </pre></code>
     * </p><p>
     * The <code>android:name</code> attribute specifies an identifier by which
     * the keyboard layout will be known in the package.
     * The <code>android:label</code> attribute specifies a human-readable descriptive
     * label to describe the keyboard layout in the user interface, such as "English (US)".
     * The <code>android:keyboardLayout</code> attribute refers to a
     * <a href="https://source.android.com/docs/core/interaction/input/key-character-map-files">
     * key character map</a> resource that defines the keyboard layout.
     * The <code>android:keyboardLocale</code> attribute specifies a comma separated list of BCP 47
     * language tags depicting the locales supported by the keyboard layout. This attribute is
     * optional and will be used for auto layout selection for external physical keyboards.
     * The <code>android:keyboardLayoutType</code> attribute specifies the layoutType for the
     * keyboard layout. This can be either empty or one of the following supported layout types:
     * qwerty, qwertz, azerty, dvorak, colemak, workman, extended, turkish_q, turkish_f. This
     * attribute is optional and will be used for auto layout selection for external physical
     * keyboards.
     * </p>
     */
    public static final String ACTION_QUERY_KEYBOARD_LAYOUTS = "android.hardware.input.action.QUERY_KEYBOARD_LAYOUTS";

    /**
     * Metadata Key: Keyboard layout metadata associated with
     * {@link #ACTION_QUERY_KEYBOARD_LAYOUTS}.
     * <p>
     * Specifies the resource id of a XML resource that describes the keyboard
     * layouts that are provided by the application.
     * </p>
     */
    public static final String META_DATA_KEYBOARD_LAYOUTS = "android.hardware.input.metadata.KEYBOARD_LAYOUTS";

    private InputManager() {
    }
}
