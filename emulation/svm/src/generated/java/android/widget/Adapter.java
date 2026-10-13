package android.widget;

/**
 * An Adapter object acts as a bridge between an {@link AdapterView} and the
 * underlying data for that view. The Adapter provides access to the data items.
 * The Adapter is also responsible for making a {@link android.view.View} for
 * each item in the data set.
 *
 * @see android.widget.ArrayAdapter
 * @see android.widget.CursorAdapter
 * @see android.widget.SimpleCursorAdapter
 */
public interface Adapter {
    /**
     * An item view type that causes the {@link AdapterView} to ignore the item
     * view. For example, this can be used if the client does not want a
     * particular view to be given for conversion in
     * {@link #getView(int, View, ViewGroup)}.
     *
     * @see #getItemViewType(int)
     * @see #getViewTypeCount()
     */
    int IGNORE_ITEM_VIEW_TYPE = -1;

    int NO_SELECTION = -2147483648;
}
