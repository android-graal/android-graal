package android.widget;

/**
 * <p>
 * This class represents a popup window that can be used to display an
 * arbitrary view. The popup window is a floating container that appears on top
 * of the current activity.
 * </p>
 * <a name="Animation"></a>
 * <h3>Animation</h3>
 * <p>
 * On all versions of Android, popup window enter and exit animations may be
 * specified by calling {@link #setAnimationStyle(int)} and passing the
 * resource ID for an animation style that defines {@code windowEnterAnimation}
 * and {@code windowExitAnimation}. For example, passing
 * {@link android.R.style#Animation_Dialog} will give a scale and alpha
 * animation.
 * </br>
 * A window animation style may also be specified in the popup window's style
 * XML via the {@link android.R.styleable#PopupWindow_popupAnimationStyle popupAnimationStyle}
 * attribute.
 * </p>
 * <p>
 * Starting with API 23, more complex popup window enter and exit transitions
 * may be specified by calling either {@link #setEnterTransition(Transition)}
 * or {@link #setExitTransition(Transition)} and passing a  {@link Transition}.
 * </br>
 * Popup enter and exit transitions may also be specified in the popup window's
 * style XML via the {@link android.R.styleable#PopupWindow_popupEnterTransition popupEnterTransition}
 * and {@link android.R.styleable#PopupWindow_popupExitTransition popupExitTransition}
 * attributes, respectively.
 * </p>
 *
 * @attr ref android.R.styleable#PopupWindow_overlapAnchor
 * @attr ref android.R.styleable#PopupWindow_popupAnimationStyle
 * @attr ref android.R.styleable#PopupWindow_popupBackground
 * @attr ref android.R.styleable#PopupWindow_popupElevation
 * @attr ref android.R.styleable#PopupWindow_popupEnterTransition
 * @attr ref android.R.styleable#PopupWindow_popupExitTransition
 *
 * @see android.widget.AutoCompleteTextView
 * @see android.widget.Spinner
 */
public final class PopupWindow {
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

    private PopupWindow() {
    }
}
