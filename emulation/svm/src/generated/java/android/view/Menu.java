package android.view;

/**
 * Interface for managing the items in a menu.
 * <p>
 * By default, every Activity supports an options menu of actions or options.
 * You can add items to this menu and handle clicks on your additions. The
 * easiest way of adding menu items is inflating an XML file into the
 * {@link Menu} via {@link MenuInflater}. The easiest way of attaching code to
 * clicks is via {@link Activity#onOptionsItemSelected(MenuItem)} and
 * {@link Activity#onContextItemSelected(MenuItem)}.
 * <p>
 * Different menu types support different features:
 * <ol>
 * <li><b>Context menus</b>: Do not support item shortcuts and item icons.
 * <li><b>Options menus</b>: The <b>icon menus</b> do not support item check
 * marks and only show the item's
 * {@link MenuItem#setTitleCondensed(CharSequence) condensed title}. The
 * <b>expanded menus</b> (only available if six or more menu items are visible,
 * reached via the 'More' item in the icon menu) do not show item icons, and
 * item check marks are discouraged.
 * <li><b>Sub menus</b>: Do not support item icons, or nested sub menus.
 * </ol>
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about creating menus, read the
 * <a href="{@docRoot}guide/topics/ui/menus.html">Menus</a> developer guide.</p>
 * </div>
 */
public interface Menu {
    /**
     * Category code for the order integer for items/groups that are 
     * alternative actions on the data that is currently displayed -- or/add
     * this with your base value.
     */
    int CATEGORY_ALTERNATIVE = 262144;

    /**
     * Category code for the order integer for items/groups that are part of a
     * container -- or/add this with your base value.
     */
    int CATEGORY_CONTAINER = 65536;

    /**
     * Category code for the order integer for items/groups that are
     * user-supplied secondary (infrequently used) options -- or/add this with
     * your base value.
     */
    int CATEGORY_SECONDARY = 196608;

    /**
     * Category code for the order integer for items/groups that are provided by
     * the system -- or/add this with your base value.
     */
    int CATEGORY_SYSTEM = 131072;

    /**
     * First value for group and item identifier integers.
     */
    int FIRST = 1;

    /**
     * Flag for {@link #performShortcut(int, KeyEvent, int)}: if set, always
     * close the menu after executing the shortcut. Closing the menu also resets
     * the prepared state.
     */
    int FLAG_ALWAYS_PERFORM_CLOSE = 2;

    /**
     * Flag for {@link #addIntentOptions}: if set, do not automatically remove
     * any existing menu items in the same group.
     */
    int FLAG_APPEND_TO_GROUP = 1;

    /**
     * Flag for {@link #performShortcut}: if set, do not close the menu after
     * executing the shortcut.
     */
    int FLAG_PERFORM_NO_CLOSE = 1;

    /**
     * Value to use for group and item identifier integers when you don't care
     * about them.
     */
    int NONE = 0;

    /**
     * A mask of all supported modifiers for MenuItem's keyboard shortcuts
     */
    int SUPPORTED_MODIFIERS_MASK = 69647;
}
