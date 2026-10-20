package android.view.inputmethod;

import android.os.Parcelable;
import android.text.InputType;

/**
 * An EditorInfo describes several attributes of a text editing object
 * that an input method is communicating with (typically an EditText), most
 * importantly the type of text content it contains and the current cursor position.
 */
public final class EditorInfo implements InputType, Parcelable {
    /**
     * Bits of {@link #IME_MASK_ACTION}: the action key performs a "done"
     * operation, typically meaning there is nothing more to input and the
     * IME will be closed.
     */
    public static final int IME_ACTION_DONE = 6;

    /**
     * Bits of {@link #IME_MASK_ACTION}: the action key performs a "go"
     * operation to take the user to the target of the text they typed.
     * Typically used, for example, when entering a URL.
     */
    public static final int IME_ACTION_GO = 2;

    /**
     * Bits of {@link #IME_MASK_ACTION}: the action key performs a "next"
     * operation, taking the user to the next field that will accept text.
     */
    public static final int IME_ACTION_NEXT = 5;

    /**
     * Bits of {@link #IME_MASK_ACTION}: there is no available action.
     */
    public static final int IME_ACTION_NONE = 1;

    /**
     * Bits of {@link #IME_MASK_ACTION}: like {@link #IME_ACTION_NEXT}, but
     * for moving to the previous field.  This will normally not be used to
     * specify an action (since it precludes {@link #IME_ACTION_NEXT}), but
     * can be returned to the app if it sets {@link #IME_FLAG_NAVIGATE_PREVIOUS}.
     */
    public static final int IME_ACTION_PREVIOUS = 7;

    /**
     * Bits of {@link #IME_MASK_ACTION}: the action key performs a "search"
     * operation, taking the user to the results of searching for the text
     * they have typed (in whatever context is appropriate).
     */
    public static final int IME_ACTION_SEARCH = 3;

    /**
     * Bits of {@link #IME_MASK_ACTION}: the action key performs a "send"
     * operation, delivering the text to its target.  This is typically used
     * when composing a message in IM or SMS where sending is immediate.
     */
    public static final int IME_ACTION_SEND = 4;

    /**
     * Bits of {@link #IME_MASK_ACTION}: no specific action has been
     * associated with this editor, let the editor come up with its own if
     * it can.
     */
    public static final int IME_ACTION_UNSPECIFIED = 0;

    /**
     * Flag of {@link #imeOptions}: used to request an IME that is capable of
     * inputting ASCII characters.  The intention of this flag is to ensure that
     * the user can type Roman alphabet characters in a {@link android.widget.TextView}.
     * It is typically used for an account ID or password input. A lot of the time,
     * IMEs are already able to input ASCII even without being told so (such IMEs
     * already respect this flag in a sense), but there are cases when this is not
     * the default. For instance, users of languages using a different script like
     * Arabic, Greek, Hebrew or Russian typically have a keyboard that can't
     * input ASCII characters by default. Applications need to be
     * aware that the flag is not a guarantee, and some IMEs may not respect it.
     * However, it is strongly recommended for IME authors to respect this flag
     * especially when their IME could end up with a state where only languages
     * using non-ASCII are enabled.
     */
    public static final int IME_FLAG_FORCE_ASCII = -2147483648;

    /**
     * Flag of {@link #imeOptions}: used to specify that there is something
     * interesting that a forward navigation can focus on. This is like using
     * {@link #IME_ACTION_NEXT}, except allows the IME to be multiline (with
     * an enter key) as well as provide forward navigation.  Note that some
     * IMEs may not be able to do this, especially when running on a small
     * screen where there is little space.  In that case it does not need to
     * present a UI for this option.  Like {@link #IME_ACTION_NEXT}, if the
     * user selects the IME's facility to forward navigate, this will show up
     * in the application at {@link InputConnection#performEditorAction(int)
     * InputConnection.performEditorAction(int)}.
     */
    public static final int IME_FLAG_NAVIGATE_NEXT = 134217728;

    /**
     * Flag of {@link #imeOptions}: like {@link #IME_FLAG_NAVIGATE_NEXT}, but
     * specifies there is something interesting that a backward navigation
     * can focus on.  If the user selects the IME's facility to backward
     * navigate, this will show up in the application as an {@link #IME_ACTION_PREVIOUS}
     * at {@link InputConnection#performEditorAction(int)
     * InputConnection.performEditorAction(int)}.
     */
    public static final int IME_FLAG_NAVIGATE_PREVIOUS = 67108864;

    /**
     * Flag of {@link #imeOptions}: used in conjunction with one of the actions
     * masked by {@link #IME_MASK_ACTION}, this indicates that the action
     * should not be available as an accessory button on the right of the extracted
     * text when the input method is full-screen. Note that by setting this flag,
     * there can be cases where the action is simply never available to the
     * user. Setting this generally means that you think that in fullscreen mode,
     * where there is little space to show the text, it's not worth taking some
     * screen real estate to display the action and it should be used instead
     * to show more text.
     */
    public static final int IME_FLAG_NO_ACCESSORY_ACTION = 536870912;

    /**
     * Flag of {@link #imeOptions}: used in conjunction with one of the actions
     * masked by {@link #IME_MASK_ACTION}. If this flag is not set, IMEs will
     * normally replace the "enter" key with the action supplied. This flag
     * indicates that the action should not be available in-line as a replacement
     * for the "enter" key. Typically this is because the action has such a
     * significant impact or is not recoverable enough that accidentally hitting
     * it should be avoided, such as sending a message. Note that
     * {@link android.widget.TextView} will automatically set this flag for you
     * on multi-line text views.
     */
    public static final int IME_FLAG_NO_ENTER_ACTION = 1073741824;

    /**
     * Flag of {@link #imeOptions}: used to specify that the IME does not need
     * to show its extracted text UI.  For input methods that may be fullscreen,
     * often when in landscape mode, this allows them to be smaller and let part
     * of the application be shown behind, through transparent UI parts in the
     * fullscreen IME. The part of the UI visible to the user may not be responsive
     * to touch because the IME will receive touch events, which may confuse the
     * user; use {@link #IME_FLAG_NO_FULLSCREEN} instead for a better experience.
     * Using this flag is discouraged and it may become deprecated in the future.
     * Its meaning is unclear in some situations and it may not work appropriately
     * on older versions of the platform.
     */
    public static final int IME_FLAG_NO_EXTRACT_UI = 268435456;

    /**
     * Flag of {@link #imeOptions}: used to request that the IME never go
     * into fullscreen mode.
     * By default, IMEs may go into full screen mode when they think
     * it's appropriate, for example on small screens in landscape
     * orientation where displaying a software keyboard may occlude
     * such a large portion of the screen that the remaining part is
     * too small to meaningfully display the application UI.
     * If this flag is set, compliant IMEs will never go into full screen mode,
     * and always leave some space to display the application UI.
     * Applications need to be aware that the flag is not a guarantee, and
     * some IMEs may ignore it.
     */
    public static final int IME_FLAG_NO_FULLSCREEN = 33554432;

    /**
     * Flag of {@link #imeOptions}: used to request that the IME should not update any personalized
     * data such as typing history and personalized language model based on what the user typed on
     * this text editing object.  Typical use cases are:
     * <ul>
     *     <li>When the application is in a special mode, where user's activities are expected to be
     *     not recorded in the application's history.  Some web browsers and chat applications may
     *     have this kind of modes.</li>
     *     <li>When storing typing history does not make much sense.  Specifying this flag in typing
     *     games may help to avoid typing history from being filled up with words that the user is
     *     less likely to type in their daily life.  Another example is that when the application
     *     already knows that the expected input is not a valid word (e.g. a promotion code that is
     *     not a valid word in any natural language).</li>
     * </ul>
     *
     * <p>Applications need to be aware that the flag is not a guarantee, and some IMEs may not
     * respect it.</p>
     */
    public static final int IME_FLAG_NO_PERSONALIZED_LEARNING = 16777216;

    /**
     * Set of bits in {@link #imeOptions} that provide alternative actions
     * associated with the "enter" key.  This both helps the IME provide
     * better feedback about what the enter key will do, and also allows it
     * to provide alternative mechanisms for providing that command.
     */
    public static final int IME_MASK_ACTION = 255;

    /**
     * Generic unspecified type for {@link #imeOptions}.
     */
    public static final int IME_NULL = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private EditorInfo() {
    }
}
