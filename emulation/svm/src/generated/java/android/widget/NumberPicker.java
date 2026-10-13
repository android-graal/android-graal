package android.widget;

/**
 * A widget that enables the user to select a number from a predefined range.
 * There are two flavors of this widget and which one is presented to the user
 * depends on the current theme.
 * <ul>
 * <li>
 * If the current theme is derived from {@link android.R.style#Theme} the widget
 * presents the current value as an editable input field with an increment button
 * above and a decrement button below. Long pressing the buttons allows for a quick
 * change of the current value. Tapping on the input field allows to type in
 * a desired value.
 * </li>
 * <li>
 * If the current theme is derived from {@link android.R.style#Theme_Holo} or
 * {@link android.R.style#Theme_Holo_Light} the widget presents the current
 * value as an editable input field with a lesser value above and a greater
 * value below. Tapping on the lesser or greater value selects it by animating
 * the number axis up or down to make the chosen value current. Flinging up
 * or down allows for multiple increments or decrements of the current value.
 * Long pressing on the lesser and greater values also allows for a quick change
 * of the current value. Tapping on the current value allows to type in a
 * desired value.
 * </li>
 * <li>
 * If the current theme is derived from {@link android.R.style#Theme_Material}
 * the widget presents the current value as a scrolling vertical selector with
 * the selected value in the center and the previous and following numbers above
 * and below, separated by a divider. The value is changed by flinging vertically.
 * The thickness of the divider can be changed by using the
 * {@link android.R.attr#selectionDividerHeight} attribute and the color of the
 * divider can be changed by using the
 * {@link android.R.attr#colorControlNormal} attribute.
 * </li>
 * </ul>
 * <p>
 * For an example of using this widget, see {@link android.widget.TimePicker}.
 * </p>
 */
public final class NumberPicker {
    private NumberPicker() {
    }

    /**
     * Interface to listen for the picker scroll state.
     */
    public interface OnScrollListener {
        /**
         * The user had previously been scrolling using touch and performed a fling.
         */
        int SCROLL_STATE_FLING = 2;

        /**
         * The view is not scrolling.
         */
        int SCROLL_STATE_IDLE = 0;

        /**
         * The user is scrolling using touch, and their finger is still on the screen.
         */
        int SCROLL_STATE_TOUCH_SCROLL = 1;
    }
}
