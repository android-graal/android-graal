package android.widget;

/**
 * An AdapterView is a view whose children are determined by an {@link Adapter}.
 *
 * <p>
 * See {@link ListView}, {@link GridView}, {@link Spinner} and
 *      {@link Gallery} for commonly used subclasses of AdapterView.
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about using AdapterView, read the
 * <a href="{@docRoot}guide/topics/ui/binding.html">Binding to Data with AdapterView</a>
 * developer guide.</p></div>
 */
public final class AdapterView {
    /**
     * Represents an invalid position. All valid positions are in the range 0 to 1 less than the
     * number of items in the current adapter.
     */
    public static final int INVALID_POSITION = -1;

    /**
     * Represents an empty or invalid row id
     */
    public static final long INVALID_ROW_ID = -9223372036854775808L;

    /**
     * The item view type returned by {@link Adapter#getItemViewType(int)} when
     * the item is a header or footer.
     */
    public static final int ITEM_VIEW_TYPE_HEADER_OR_FOOTER = -2;

    /**
     * The item view type returned by {@link Adapter#getItemViewType(int)} when
     * the adapter does not want the item's view recycled.
     */
    public static final int ITEM_VIEW_TYPE_IGNORE = -1;

    private AdapterView() {
    }
}
