package android.view.inputmethod;

import android.os.Parcelable;

/**
 * Information about text that has been extracted for use by an input method.
 *
 * This contains information about a portion of the currently edited text,
 * that the IME should display into its own interface while in extracted mode.
 */
public final class ExtractedText implements Parcelable {
    /**
     * Bit for {@link #flags}: set if the editor is currently in selection mode.
     *
     * This happens when a hardware keyboard with latched keys is attached and
     * the shift key is currently latched.
     */
    public static final int FLAG_SELECTING = 2;

    /**
     * Bit for {@link #flags}: set if the text being edited can only be on
     * a single line.
     */
    public static final int FLAG_SINGLE_LINE = 1;

    private ExtractedText() {
    }
}
