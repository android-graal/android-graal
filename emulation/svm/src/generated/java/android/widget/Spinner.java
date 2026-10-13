package android.widget;

/**
 * A view that displays one child at a time and lets the user pick among them.
 * The items in the Spinner come from the {@link Adapter} associated with
 * this view.
 *
 * <p>See the <a href="{@docRoot}guide/topics/ui/controls/spinner.html">Spinners</a> guide.</p>
 *
 * @attr ref android.R.styleable#Spinner_dropDownSelector
 * @attr ref android.R.styleable#Spinner_dropDownWidth
 * @attr ref android.R.styleable#Spinner_gravity
 * @attr ref android.R.styleable#Spinner_popupBackground
 * @attr ref android.R.styleable#Spinner_prompt
 * @attr ref android.R.styleable#Spinner_spinnerMode
 * @attr ref android.R.styleable#ListPopupWindow_dropDownVerticalOffset
 * @attr ref android.R.styleable#ListPopupWindow_dropDownHorizontalOffset
 */
public final class Spinner {
    /**
     * Use a dialog window for selecting spinner options.
     */
    public static final int MODE_DIALOG = 0;

    /**
     * Use a dropdown anchored to the Spinner for selecting spinner options.
     */
    public static final int MODE_DROPDOWN = 1;

    private Spinner() {
    }
}
