package android.content.pm;

import android.os.Parcelable;

/**
 * Class for retrieving a list of launchable activities for the current user and any associated
 * managed profiles that are visible to the current user, which can be retrieved with
 * {@link #getProfiles}. This is mainly for use by launchers.
 *
 * Apps can be queried for each user profile.
 * Since the PackageManager will not deliver package broadcasts for other profiles, you can register
 * for package changes here.
 * <p>
 * To watch for managed profiles being added or removed, register for the following broadcasts:
 * {@link Intent#ACTION_MANAGED_PROFILE_ADDED} and {@link Intent#ACTION_MANAGED_PROFILE_REMOVED}.
 * <p>
 * Note as of Android O, apps on a managed profile are no longer allowed to access apps on the
 * main profile.  Apps can only access profiles returned by {@link #getProfiles()}.
 */
public final class LauncherApps {
    /**
     * Activity Action: For the default launcher to show the confirmation dialog to create
     * a pinned app widget.
     *
     * <p>See the {@link android.appwidget.AppWidgetManager#requestPinAppWidget} javadoc for
     * details.
     *
     * <p>
     * Use {@link #getPinItemRequest(Intent)} to get a {@link PinItemRequest} object,
     * and call {@link PinItemRequest#accept(Bundle)}
     * if the user accepts.  If the user doesn't accept, no further action is required.
     *
     * @see #EXTRA_PIN_ITEM_REQUEST
     */
    public static final String ACTION_CONFIRM_PIN_APPWIDGET = "android.content.pm.action.CONFIRM_PIN_APPWIDGET";

    /**
     * Activity Action: For the default launcher to show the confirmation dialog to create
     * a pinned shortcut.
     *
     * <p>See the {@link ShortcutManager} javadoc for details.
     *
     * <p>
     * Use {@link #getPinItemRequest(Intent)} to get a {@link PinItemRequest} object,
     * and call {@link PinItemRequest#accept(Bundle)}
     * if the user accepts.  If the user doesn't accept, no further action is required.
     *
     * @see #EXTRA_PIN_ITEM_REQUEST
     */
    public static final String ACTION_CONFIRM_PIN_SHORTCUT = "android.content.pm.action.CONFIRM_PIN_SHORTCUT";

    /**
     * An extra for {@link #ACTION_CONFIRM_PIN_SHORTCUT} &amp; {@link #ACTION_CONFIRM_PIN_APPWIDGET}
     * containing a {@link PinItemRequest} of appropriate type asked to pin.
     *
     * <p>A helper function {@link #getPinItemRequest(Intent)} can be used
     * instead of using this constant directly.
     *
     * @see #ACTION_CONFIRM_PIN_SHORTCUT
     * @see #ACTION_CONFIRM_PIN_APPWIDGET
     */
    public static final String EXTRA_PIN_ITEM_REQUEST = "android.content.pm.extra.PIN_ITEM_REQUEST";

    private LauncherApps() {
    }

    /**
     * Represents a "pin shortcut" or a "pin appwidget" request made by an app, which is sent with
     * an {@link #ACTION_CONFIRM_PIN_SHORTCUT} or {@link #ACTION_CONFIRM_PIN_APPWIDGET} intent
     * respectively to the default launcher app.
     *
     * <h3>Request of the {@link #REQUEST_TYPE_SHORTCUT} type.</h3>
     *
     * <p>A {@link #REQUEST_TYPE_SHORTCUT} request represents a request to pin a
     * {@link ShortcutInfo}.  If the launcher accepts a request, call {@link #accept()},
     * or {@link #accept(Bundle)} with a null or empty Bundle.  No options are defined for
     * pin-shortcuts requests.
     *
     * <p>{@link #getShortcutInfo()} always returns a non-null {@link ShortcutInfo} for this type.
     *
     * <p>The launcher may receive a request with a {@link ShortcutInfo} that is already pinned, in
     * which case {@link ShortcutInfo#isPinned()} returns true.  This means the user wants to create
     * another pinned shortcut for a shortcut that's already pinned.  If the launcher accepts it,
     * {@link #accept()} must still be called even though the shortcut is already pinned, and
     * create a new pinned shortcut icon for it.
     *
     * <p>See also {@link ShortcutManager} for more details.
     *
     * <h3>Request of the {@link #REQUEST_TYPE_APPWIDGET} type.</h3>
     *
     * <p>A {@link #REQUEST_TYPE_SHORTCUT} request represents a request to pin a
     * an AppWidget.  If the launcher accepts a request, call {@link #accept(Bundle)} with
     * the appwidget integer ID set to the
     * {@link android.appwidget.AppWidgetManager#EXTRA_APPWIDGET_ID} extra.
     *
     * <p>{@link #getAppWidgetProviderInfo(Context)} always returns a non-null
     * {@link AppWidgetProviderInfo} for this type.
     *
     * <p>See also {@link AppWidgetManager} for more details.
     *
     * @see #EXTRA_PIN_ITEM_REQUEST
     * @see #getPinItemRequest(Intent)
     */
    public static final class PinItemRequest implements Parcelable {
        /**
         * This is a request to pin app widget.
         */
        public static final int REQUEST_TYPE_APPWIDGET = 2;

        /**
         * This is a request to pin shortcut.
         */
        public static final int REQUEST_TYPE_SHORTCUT = 1;

        private PinItemRequest() {
        }
    }

    /**
     * Represents a query passed to {@link #getShortcuts(ShortcutQuery, UserHandle)}.
     */
    public static final class ShortcutQuery {
        /**
         * Requests "key" fields only.  See {@link ShortcutInfo#hasKeyFieldsOnly()}'s javadoc to
         * see which fields fields "key".
         * This allows quicker access to shortcut information in order to
         * determine whether the caller's in-memory cache needs to be updated.
         *
         * <p>Typically, launcher applications cache all or most shortcut information
         * in memory in order to show shortcuts without a delay.
         *
         * When a given launcher application wants to update its cache, such as when its process
         * restarts, it can fetch shortcut information with this flag.
         * The application can then check {@link ShortcutInfo#getLastChangedTimestamp()} for each
         * shortcut, fetching a shortcut's non-key information only if that shortcut has been
         * updated.
         *
         * @see ShortcutManager
         */
        public static final int FLAG_GET_KEY_FIELDS_ONLY = 4;

        /**
         * Include cached shortcuts in the result.
         */
        public static final int FLAG_MATCH_CACHED = 16;

        /**
         * Include dynamic shortcuts in the result.
         */
        public static final int FLAG_MATCH_DYNAMIC = 1;

        /**
         * Include manifest shortcuts in the result.
         */
        public static final int FLAG_MATCH_MANIFEST = 8;

        /**
         * Include pinned shortcuts in the result.
         *
         * <p>If you are the selected assistant app, and wishes to fetch all shortcuts that the
         * user owns on the launcher (or by other launchers, in case the user has multiple), use
         * {@link #FLAG_MATCH_PINNED_BY_ANY_LAUNCHER} instead.
         *
         * <p>If you're a regular launcher app, there's no way to get shortcuts pinned by other
         * launchers, and {@link #FLAG_MATCH_PINNED_BY_ANY_LAUNCHER} will be ignored. So use this
         * flag to get own pinned shortcuts.
         */
        public static final int FLAG_MATCH_PINNED = 2;

        /**
         * Include all pinned shortcuts by any launchers, not just by the caller,
         * in the result.
         *
         * <p>The caller must be the selected assistant app to use this flag, or have the system
         * {@code ACCESS_SHORTCUTS} permission.
         *
         * <p>If you are the selected assistant app, and wishes to fetch all shortcuts that the
         * user owns on the launcher (or by other launchers, in case the user has multiple), use
         * {@link #FLAG_MATCH_PINNED_BY_ANY_LAUNCHER} instead.
         *
         * <p>If you're a regular launcher app (or any app that's not the selected assistant app)
         * then this flag will be ignored.
         */
        public static final int FLAG_MATCH_PINNED_BY_ANY_LAUNCHER = 1024;

        private ShortcutQuery() {
        }
    }
}
