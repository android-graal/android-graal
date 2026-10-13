package android.content;

/**
 * Interface that defines a dialog-type class that can be shown, dismissed, or
 * canceled, and may have buttons that can be clicked.
 */
public interface DialogInterface {
    /**
     * @deprecated Use {@link #BUTTON_POSITIVE}
     */
    @Deprecated
    int BUTTON1 = -1;

    /**
     * @deprecated Use {@link #BUTTON_NEGATIVE}
     */
    @Deprecated
    int BUTTON2 = -2;

    /**
     * @deprecated Use {@link #BUTTON_NEUTRAL}
     */
    @Deprecated
    int BUTTON3 = -3;

    /**
     * The identifier for the negative button.
     */
    int BUTTON_NEGATIVE = -2;

    /**
     * The identifier for the neutral button.
     */
    int BUTTON_NEUTRAL = -3;

    /**
     * The identifier for the positive button.
     */
    int BUTTON_POSITIVE = -1;
}
