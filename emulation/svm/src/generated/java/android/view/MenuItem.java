package android.view;

/**
 * Interface for direct access to a previously created menu item.
 * <p>
 * An Item is returned by calling one of the {@link android.view.Menu#add}
 * methods.
 * <p>
 * For a feature set of specific menu types, see {@link Menu}.
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For information about creating menus, read the
 * <a href="{@docRoot}guide/topics/ui/menus.html">Menus</a> developer guide.</p>
 * </div>
 */
public interface MenuItem {
    /**
     * Always show this item as a button in an Action Bar.
     * Use sparingly! If too many items are set to always show in the Action Bar it can
     * crowd the Action Bar and degrade the user experience on devices with smaller screens.
     * A good rule of thumb is to have no more than 2 items set to always show at a time.
     */
    int SHOW_AS_ACTION_ALWAYS = 2;

    /**
     * This item's action view collapses to a normal menu item.
     * When expanded, the action view temporarily takes over
     * a larger segment of its container.
     */
    int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;

    /**
     * Show this item as a button in an Action Bar if the system decides there is room for it.
     */
    int SHOW_AS_ACTION_IF_ROOM = 1;

    /**
     * Never show this item as a button in an Action Bar.
     */
    int SHOW_AS_ACTION_NEVER = 0;

    /**
     * When this item is in the action bar, always show it with a text label even if
     * it also has an icon specified.
     */
    int SHOW_AS_ACTION_WITH_TEXT = 4;
}
