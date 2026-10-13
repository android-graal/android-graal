package android.text.method;

/**
 * This base class encapsulates the behavior for tracking the state of
 * meta keys such as SHIFT, ALT and SYM as well as the pseudo-meta state of selecting text.
 * <p>
 * Key listeners that care about meta state should inherit from this class;
 * you should not instantiate this class directly in a client.
 * </p><p>
 * This class provides two mechanisms for tracking meta state that can be used
 * together or independently.
 * </p>
 * <ul>
 * <li>Methods such as {@link #handleKeyDown(long, int, KeyEvent)} and
 * {@link #getMetaState(long)} operate on a meta key state bit mask.</li>
 * <li>Methods such as {@link #onKeyDown(View, Editable, int, KeyEvent)} and
 * {@link #getMetaState(CharSequence, int)} operate on meta key state flags stored
 * as spans in an {@link Editable} text buffer.  The spans only describe the current
 * meta key state of the text editor; they do not carry any positional information.</li>
 * </ul>
 * <p>
 * The behavior of this class varies according to the keyboard capabilities
 * described by the {@link KeyCharacterMap} of the keyboard device such as
 * the {@link KeyCharacterMap#getModifierBehavior() key modifier behavior}.
 * </p><p>
 * {@link MetaKeyKeyListener} implements chorded and toggled key modifiers.
 * When key modifiers are toggled into a latched or locked state, the state
 * of the modifier is stored in the {@link Editable} text buffer or in a
 * meta state integer managed by the client.  These latched or locked modifiers
 * should be considered to be held <b>in addition to</b> those that the
 * keyboard already reported as being pressed in {@link KeyEvent#getMetaState()}.
 * In other words, the {@link MetaKeyKeyListener} augments the meta state
 * provided by the keyboard; it does not replace it.  This distinction is important
 * to ensure that meta keys not handled by {@link MetaKeyKeyListener} such as
 * {@link KeyEvent#KEYCODE_CAPS_LOCK} or {@link KeyEvent#KEYCODE_NUM_LOCK} are
 * taken into consideration.
 * </p><p>
 * To ensure correct meta key behavior, the following pattern should be used
 * when mapping key codes to characters:
 * </p>
 * <code>
 * private char getUnicodeChar(TextKeyListener listener, KeyEvent event, Editable textBuffer) {
 *     // Use the combined meta states from the event and the key listener.
 *     int metaState = event.getMetaState() | listener.getMetaState(textBuffer);
 *     return event.getUnicodeChar(metaState);
 * }
 * </code>
 */
public final class MetaKeyKeyListener {
    /**
     * Flag that indicates that the ALT key is locked.
     */
    public static final int META_ALT_LOCKED = 512;

    /**
     * Flag that indicates that the ALT key is on.
     * Value equals {@link KeyEvent#META_ALT_ON}.
     */
    public static final int META_ALT_ON = 2;

    /**
     * Flag that indicates that the SHIFT key is locked in CAPS mode.
     */
    public static final int META_CAP_LOCKED = 256;

    /**
     * Flag that indicates that the SHIFT key is on.
     * Value equals {@link KeyEvent#META_SHIFT_ON}.
     */
    public static final int META_SHIFT_ON = 1;

    /**
     * Flag that indicates that the SYM key is locked.
     */
    public static final int META_SYM_LOCKED = 1024;

    /**
     * Flag that indicates that the SYM key is on.
     * Value equals {@link KeyEvent#META_SYM_ON}.
     */
    public static final int META_SYM_ON = 4;

    private MetaKeyKeyListener() {
    }
}
