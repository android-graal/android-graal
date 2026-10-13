package android.widget;

/**
 * This is a provider for a share action. It is responsible for creating views
 * that enable data sharing and also to show a sub menu with sharing activities
 * if the hosting item is placed on the overflow menu.
 * <p>
 * Here is how to use the action provider with custom backing file in a {@link MenuItem}:
 * </p>
 * <pre>
 * // In Activity#onCreateOptionsMenu
 * public boolean onCreateOptionsMenu(Menu menu) {
 *     // Get the menu item.
 *     MenuItem menuItem = menu.findItem(R.id.my_menu_item);
 *     // Get the provider and hold onto it to set/change the share intent.
 *     mShareActionProvider = (ShareActionProvider) menuItem.getActionProvider();
 *     // Set history different from the default before getting the action
 *     // view since a call to {@link MenuItem#getActionView() MenuItem.getActionView()} calls
 *     // {@link ActionProvider#onCreateActionView()} which uses the backing file name. Omit this
 *     // line if using the default share history file is desired.
 *     mShareActionProvider.setShareHistoryFileName("custom_share_history.xml");
 *     . . .
 * }
 *
 * // Somewhere in the application.
 * public void doShare(Intent shareIntent) {
 *     // When you want to share set the share intent.
 *     mShareActionProvider.setShareIntent(shareIntent);
 * }</pre>
 * <p>
 * <strong>Note:</strong> While the sample snippet demonstrates how to use this provider
 * in the context of a menu item, the use of the provider is not limited to menu items.
 * </p>
 *
 * @see ActionProvider
 */
public final class ShareActionProvider {
    /**
     * The default name for storing share history.
     */
    public static final String DEFAULT_SHARE_HISTORY_FILE_NAME = "share_history.xml";

    private ShareActionProvider() {
    }
}
