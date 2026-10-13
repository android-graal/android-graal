package android.inputmethodservice;

/**
 * Loads an XML description of a keyboard and stores the attributes of the keys. A keyboard
 * consists of rows of keys.
 * <p>The layout file for a keyboard contains XML that looks like the following snippet:</p>
 * <pre>
 * &lt;Keyboard
 *         android:keyWidth="%10p"
 *         android:keyHeight="50px"
 *         android:horizontalGap="2px"
 *         android:verticalGap="2px" &gt;
 *     &lt;Row android:keyWidth="32px" &gt;
 *         &lt;Key android:keyLabel="A" /&gt;
 *         ...
 *     &lt;/Row&gt;
 *     ...
 * &lt;/Keyboard&gt;
 * </pre>
 * @attr ref android.R.styleable#Keyboard_keyWidth
 * @attr ref android.R.styleable#Keyboard_keyHeight
 * @attr ref android.R.styleable#Keyboard_horizontalGap
 * @attr ref android.R.styleable#Keyboard_verticalGap
 * @deprecated This class is deprecated because this is just a convenient UI widget class that
 *             application developers can re-implement on top of existing public APIs.  If you have
 *             already depended on this class, consider copying the implementation from AOSP into
 *             your project or re-implementing a similar widget by yourselves
 */
@Deprecated
public final class Keyboard {
    @Deprecated
    public static final int EDGE_BOTTOM = 8;

    @Deprecated
    public static final int EDGE_LEFT = 1;

    @Deprecated
    public static final int EDGE_RIGHT = 2;

    @Deprecated
    public static final int EDGE_TOP = 4;

    @Deprecated
    public static final int KEYCODE_ALT = -6;

    @Deprecated
    public static final int KEYCODE_CANCEL = -3;

    @Deprecated
    public static final int KEYCODE_DELETE = -5;

    @Deprecated
    public static final int KEYCODE_DONE = -4;

    @Deprecated
    public static final int KEYCODE_MODE_CHANGE = -2;

    @Deprecated
    public static final int KEYCODE_SHIFT = -1;

    private Keyboard() {
    }
}
