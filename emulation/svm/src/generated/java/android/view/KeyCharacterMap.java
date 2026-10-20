package android.view;

import android.os.Parcelable;

/**
 * Describes the keys provided by a keyboard device and their associated labels.
 */
public final class KeyCharacterMap implements Parcelable {
    /**
     * A keyboard with all the letters, and maybe some numbers.
     * <p>
     * An alphabetic keyboard supports text entry directly but may have a condensed
     * layout with a small form factor.  In contrast to a {@link #FULL full keyboard}, some
     * symbols may only be accessible using special on-screen character pickers.
     * In addition, to improve typing speed and accuracy, the framework provides
     * special affordances for alphabetic keyboards such as auto-capitalization
     * and toggled / locked shift and alt keys.
     * </p><p>
     * This type of keyboard is generally designed for thumb typing.
     * </p>
     */
    public static final int ALPHA = 3;

    /**
     * The id of the device's primary built in keyboard is always 0.
     *
     * @deprecated This constant should no longer be used because there is no
     * guarantee that a device has a built-in keyboard that can be used for
     * typing text.  There might not be a built-in keyboard, the built-in keyboard
     * might be a {@link #NUMERIC} or {@link #SPECIAL_FUNCTION} keyboard, or there
     * might be multiple keyboards installed including external keyboards.
     * When interpreting key presses received from the framework, applications should
     * use the device id specified in the {@link KeyEvent} received.
     * When synthesizing key presses for delivery elsewhere or when translating key presses
     * from unknown keyboards, applications should use the special {@link #VIRTUAL_KEYBOARD}
     * device id.
     */
    @Deprecated
    public static final int BUILT_IN_KEYBOARD = 0;

    public static final int COMBINING_ACCENT = -2147483648;

    /**
     * Mask the return value from {@link #get(int, int)} with this value to get
     * a printable representation of the accent character of a "dead key."
     */
    public static final int COMBINING_ACCENT_MASK = 2147483647;

    /**
     * A full PC-style keyboard.
     * <p>
     * A full keyboard behaves like a PC keyboard.  All symbols are accessed directly
     * by pressing keys on the keyboard without on-screen support or affordances such
     * as auto-capitalization.
     * </p><p>
     * This type of keyboard is generally designed for full two hand typing.
     * </p>
     */
    public static final int FULL = 4;

    /**
     * This private-use character is used to trigger Unicode character
     * input by hex digits.
     */
    public static final char HEX_INPUT = '\uef00';

    /**
     * Modifier keys may be chorded with character keys.
     *
     * @see #getModifierBehavior()
     */
    public static final int MODIFIER_BEHAVIOR_CHORDED = 0;

    /**
     * Modifier keys may be chorded with character keys or they may toggle
     * into latched or locked states when pressed independently.
     *
     * @see #getModifierBehavior()
     */
    public static final int MODIFIER_BEHAVIOR_CHORDED_OR_TOGGLED = 1;

    /**
     * A numeric (12-key) keyboard.
     * <p>
     * A numeric keyboard supports text entry using a multi-tap approach.
     * It may be necessary to tap a key multiple times to generate the desired letter
     * or symbol.
     * </p><p>
     * This type of keyboard is generally designed for thumb typing.
     * </p>
     */
    public static final int NUMERIC = 1;

    /**
     * This private-use character is used to bring up a character picker for
     * miscellaneous symbols.
     */
    public static final char PICKER_DIALOG_INPUT = '\uef01';

    /**
     * A keyboard with all the letters, but with more than one letter per key.
     * <p>
     * This type of keyboard is generally designed for thumb typing.
     * </p>
     */
    public static final int PREDICTIVE = 2;

    /**
     * A keyboard that is only used to control special functions rather than for typing.
     * <p>
     * A special function keyboard consists only of non-printing keys such as
     * HOME and POWER that are not actually used for typing.
     * </p>
     */
    public static final int SPECIAL_FUNCTION = 5;

    /**
     * The id of a generic virtual keyboard with a full layout that can be used to
     * synthesize key events.  Typically used with {@link #getEvents}.
     */
    public static final int VIRTUAL_KEYBOARD = -1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private KeyCharacterMap() {
    }

    /**
     * Describes the character mappings associated with a key.
     *
     * @deprecated instead use {@link KeyCharacterMap#getDisplayLabel(int)},
     * {@link KeyCharacterMap#getNumber(int)} and {@link KeyCharacterMap#get(int, int)}.
     */
    @Deprecated
    public static final class KeyData {
        @Deprecated
        public static final int META_LENGTH = 4;

        private KeyData() {
        }
    }
}
