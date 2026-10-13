package android.widget;

/**
 * A ListPopupWindow anchors itself to a host view and displays a
 * list of choices.
 *
 * <p>ListPopupWindow contains a number of tricky behaviors surrounding
 * positioning, scrolling parents to fit the dropdown, interacting
 * sanely with the IME if present, and others.
 *
 * @attr ref android.R.styleable#ListPopupWindow_dropDownVerticalOffset
 * @attr ref android.R.styleable#ListPopupWindow_dropDownHorizontalOffset
 *
 * @see android.widget.AutoCompleteTextView
 * @see android.widget.Spinner
 */
public final class ListPopupWindow {
    /**
     * Mode for {@link #setInputMethodMode(int)}: the requirements for the
     * input method should be based on the focusability of the popup.  That is
     * if it is focusable than it needs to work with the input method, else
     * it doesn't.
     */
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;

    /**
     * Mode for {@link #setInputMethodMode(int)}: this popup always needs to
     * work with an input method, regardless of whether it is focusable.  This
     * means that it will always be displayed so that the user can also operate
     * the input method while it is shown.
     */
    public static final int INPUT_METHOD_NEEDED = 1;

    /**
     * Mode for {@link #setInputMethodMode(int)}: this popup never needs to
     * work with an input method, regardless of whether it is focusable.  This
     * means that it will always be displayed to use as much space on the
     * screen as needed, regardless of whether this covers the input method.
     */
    public static final int INPUT_METHOD_NOT_NEEDED = 2;

    /**
     * Alias for {@link ViewGroup.LayoutParams#MATCH_PARENT}.
     * If used to specify a popup width, the popup will match the width of the anchor view.
     * If used to specify a popup height, the popup will fill available space.
     */
    public static final int MATCH_PARENT = -1;

    /**
     * The provided prompt view should appear above list content.
     *
     * @see #setPromptPosition(int)
     * @see #getPromptPosition()
     * @see #setPromptView(View)
     */
    public static final int POSITION_PROMPT_ABOVE = 0;

    /**
     * The provided prompt view should appear below list content.
     *
     * @see #setPromptPosition(int)
     * @see #getPromptPosition()
     * @see #setPromptView(View)
     */
    public static final int POSITION_PROMPT_BELOW = 1;

    /**
     * Alias for {@link ViewGroup.LayoutParams#WRAP_CONTENT}.
     * If used to specify a popup width, the popup will use the width of its content.
     */
    public static final int WRAP_CONTENT = -2;

    private ListPopupWindow() {
    }
}
