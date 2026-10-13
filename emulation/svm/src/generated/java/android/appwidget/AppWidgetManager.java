package android.appwidget;

/**
 * Updates AppWidget state; gets information about installed AppWidget providers and other
 * AppWidget related state.
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about creating app widgets, read the
 * <a href="{@docRoot}guide/topics/appwidgets/index.html">App Widgets</a> developer guide.</p>
 * </div>
 */
public final class AppWidgetManager {
    /**
     * Activity action to launch from your {@link AppWidgetHost} activity when you want to bind
     * an AppWidget to display and bindAppWidgetIdIfAllowed returns false.
     * <p>
     * You must supply the following extras:
     * <table>
     *   <tr>
     *     <td>{@link #EXTRA_APPWIDGET_ID}</td>
     *     <td>A newly allocated appWidgetId, which will be bound to the AppWidget provider
     *         you provide.</td>
     *  </tr>
     *  <tr>
     *     <td>{@link #EXTRA_APPWIDGET_PROVIDER}</td>
     *     <td>The BroadcastReceiver that will be the AppWidget provider for this AppWidget.
     *     </td>
     *  </tr>
     *  <tr>
     *     <td>{@link #EXTRA_APPWIDGET_PROVIDER_PROFILE}</td>
     *     <td>An optional handle to a user profile under which runs the provider
     *     for this AppWidget.
     *     </td>
     *  </tr>
     * </table>
     *
     * <p>
     * The system will respond with an onActivityResult call with the following extras in
     * the intent:
     * <table>
     *   <tr>
     *     <td>{@link #EXTRA_APPWIDGET_ID}</td>
     *     <td>The appWidgetId that you supplied in the original intent.</td>
     *  </tr>
     * </table>
     * <p>
     * When you receive the result from the AppWidget bind activity, if the resultCode is
     * {@link android.app.Activity#RESULT_OK}, the AppWidget has been bound.  You should then
     * check the AppWidgetProviderInfo for the returned AppWidget, and if it has one, launch its
     * configuration activity.  If {@link android.app.Activity#RESULT_CANCELED} is returned, you
     * should delete the appWidgetId.
     *
     * @see #ACTION_APPWIDGET_CONFIGURE
     */
    public static final String ACTION_APPWIDGET_BIND = "android.appwidget.action.APPWIDGET_BIND";

    /**
     * Sent when it is time to configure your AppWidget while it is being added to a host.
     * This action is not sent as a broadcast to the AppWidget provider, but as a startActivity
     * to the activity specified in the {@link AppWidgetProviderInfo AppWidgetProviderInfo
     * meta-data}.
     *
     * <p>
     * The intent will contain the following extras:
     * <table>
     *   <tr>
     *     <td>{@link #EXTRA_APPWIDGET_ID}</td>
     *     <td>The appWidgetId to configure.</td>
     *  </tr>
     * </table>
     *
     * <p>If you return {@link android.app.Activity#RESULT_OK} using
     * {@link android.app.Activity#setResult Activity.setResult()}, the AppWidget will be added,
     * and you will receive an {@link #ACTION_APPWIDGET_UPDATE} broadcast for this AppWidget.
     * If you return {@link android.app.Activity#RESULT_CANCELED}, the host will cancel the add
     * and not display this AppWidget, and you will receive a {@link #ACTION_APPWIDGET_DELETED}
     * broadcast.
     */
    public static final String ACTION_APPWIDGET_CONFIGURE = "android.appwidget.action.APPWIDGET_CONFIGURE";

    /**
     * Sent when an instance of an AppWidget is deleted from its host.
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     *
     * @see AppWidgetProvider#onDeleted AppWidgetProvider.onDeleted(Context context, int[] appWidgetIds)
     */
    public static final String ACTION_APPWIDGET_DELETED = "android.appwidget.action.APPWIDGET_DELETED";

    /**
     * Sent when the last AppWidget of this provider is removed from the last host.
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     *
     * @see AppWidgetProvider#onEnabled AppWidgetProvider.onDisabled(Context context)
     */
    public static final String ACTION_APPWIDGET_DISABLED = "android.appwidget.action.APPWIDGET_DISABLED";

    /**
     * Sent when an instance of an AppWidget is added to a host for the first time.
     * This broadcast is sent at boot time if there is a AppWidgetHost installed with
     * an instance for this provider.
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     *
     * @see AppWidgetProvider#onEnabled AppWidgetProvider.onEnabled(Context context)
     */
    public static final String ACTION_APPWIDGET_ENABLED = "android.appwidget.action.APPWIDGET_ENABLED";

    /**
     * Sent to widget hosts after AppWidget state related to the host has been restored from
     * backup. The intent contains information about how to translate AppWidget ids from the
     * restored data to their new equivalents.  If an application maintains multiple separate
     * widget host instances, it will receive this broadcast separately for each one.
     *
     * <p>The intent will contain the following extras:
     *
     * <table>
     *   <tr>
     *     <td>{@link #EXTRA_APPWIDGET_OLD_IDS}</td>
     *     <td>The set of appWidgetIds represented in a restored backup that have been successfully
     *     incorporated into the current environment.  This may be all of the AppWidgets known
     *     to this application, or just a subset.  Each entry in this array of appWidgetIds has
     *     a corresponding entry in the {@link #EXTRA_APPWIDGET_IDS} extra.</td>
     *  </tr>
     *   <tr>
     *     <td>{@link #EXTRA_APPWIDGET_IDS}</td>
     *     <td>The set of appWidgetIds now valid for this application.  The app should look at
     *     its restored widget configuration and translate each appWidgetId in the
     *     {@link #EXTRA_APPWIDGET_OLD_IDS} array to its new value found at the corresponding
     *     index within this array.</td>
     *  </tr>
     *  <tr>
     *     <td>{@link #EXTRA_HOST_ID}</td>
     *     <td>The integer ID of the widget host instance whose state has just been restored.</td>
     *  </tr>
     * </table>
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     *
     * @see #ACTION_APPWIDGET_RESTORED
     */
    public static final String ACTION_APPWIDGET_HOST_RESTORED = "android.appwidget.action.APPWIDGET_HOST_RESTORED";

    /**
     * Sent when the custom extras for an AppWidget change.
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     *
     * @see AppWidgetProvider#onAppWidgetOptionsChanged
     *      AppWidgetProvider.onAppWidgetOptionsChanged(Context context,
     *      AppWidgetManager appWidgetManager, int appWidgetId, Bundle newExtras)
     */
    public static final String ACTION_APPWIDGET_OPTIONS_CHANGED = "android.appwidget.action.APPWIDGET_UPDATE_OPTIONS";

    /**
     * Activity action to launch from your {@link AppWidgetHost} activity when you want to
     * pick an AppWidget to display.  The AppWidget picker activity will be launched.
     * <p>
     * You must supply the following extras:
     * <table>
     *   <tr>
     *     <td>{@link #EXTRA_APPWIDGET_ID}</td>
     *     <td>A newly allocated appWidgetId, which will be bound to the AppWidget provider
     *         once the user has selected one.</td>
     *  </tr>
     * </table>
     *
     * <p>
     * The system will respond with an onActivityResult call with the following extras in
     * the intent:
     * <table>
     *   <tr>
     *     <td>{@link #EXTRA_APPWIDGET_ID}</td>
     *     <td>The appWidgetId that you supplied in the original intent.</td>
     *  </tr>
     * </table>
     * <p>
     * When you receive the result from the AppWidget pick activity, if the resultCode is
     * {@link android.app.Activity#RESULT_OK}, an AppWidget has been selected.  You should then
     * check the AppWidgetProviderInfo for the returned AppWidget, and if it has one, launch its
     * configuration activity.  If {@link android.app.Activity#RESULT_CANCELED} is returned, you
     * should delete the appWidgetId.
     *
     * @see #ACTION_APPWIDGET_CONFIGURE
     */
    public static final String ACTION_APPWIDGET_PICK = "android.appwidget.action.APPWIDGET_PICK";

    /**
     * Sent to an {@link AppWidgetProvider} after AppWidget state related to that provider has
     * been restored from backup. The intent contains information about how to translate AppWidget
     * ids from the restored data to their new equivalents.
     *
     * <p>The intent will contain the following extras:
     *
     * <table>
     *   <tr>
     *     <td>{@link #EXTRA_APPWIDGET_OLD_IDS}</td>
     *     <td>The set of appWidgetIds represented in a restored backup that have been successfully
     *     incorporated into the current environment.  This may be all of the AppWidgets known
     *     to this application, or just a subset.  Each entry in this array of appWidgetIds has
     *     a corresponding entry in the {@link #EXTRA_APPWIDGET_IDS} extra.</td>
     *  </tr>
     *   <tr>
     *     <td>{@link #EXTRA_APPWIDGET_IDS}</td>
     *     <td>The set of appWidgetIds now valid for this application.  The app should look at
     *     its restored widget configuration and translate each appWidgetId in the
     *     {@link #EXTRA_APPWIDGET_OLD_IDS} array to its new value found at the corresponding
     *     index within this array.</td>
     *  </tr>
     * </table>
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     *
     * @see #ACTION_APPWIDGET_HOST_RESTORED
     */
    public static final String ACTION_APPWIDGET_RESTORED = "android.appwidget.action.APPWIDGET_RESTORED";

    /**
     * Sent when it is time to update your AppWidget.
     *
     * <p>This may be sent in response to a new instance for this AppWidget provider having
     * been instantiated, the requested {@link AppWidgetProviderInfo#updatePeriodMillis update interval}
     * having lapsed, or the system booting.
     *
     * <p>
     * The intent will contain the following extras:
     * <table>
     *   <tr>
     *     <td>{@link #EXTRA_APPWIDGET_IDS}</td>
     *     <td>The appWidgetIds to update.  This may be all of the AppWidgets created for this
     *     provider, or just a subset.  The system tries to send updates for as few AppWidget
     *     instances as possible.</td>
     *  </tr>
     * </table>
     *
     * @see AppWidgetProvider#onUpdate AppWidgetProvider.onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
     */
    public static final String ACTION_APPWIDGET_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE";

    /**
     * An intent extra (int) that contains one appWidgetId.
     * <p>
     * The value will be an int that can be retrieved like this:
     * {@sample frameworks/base/tests/appwidgets/AppWidgetHostTest/src/com/android/tests/appwidgethost/AppWidgetHostActivity.java getExtra_EXTRA_APPWIDGET_ID}
     */
    public static final String EXTRA_APPWIDGET_ID = "appWidgetId";

    /**
     * An intent extra that contains multiple appWidgetIds.
     * <p>
     * The value will be an int array that can be retrieved like this:
     * {@sample frameworks/base/tests/appwidgets/AppWidgetHostTest/src/com/android/tests/appwidgethost/TestAppWidgetProvider.java getExtra_EXTRA_APPWIDGET_IDS}
     */
    public static final String EXTRA_APPWIDGET_IDS = "appWidgetIds";

    /**
     * An intent extra that contains multiple appWidgetIds.  These are id values as
     * they were provided to the application during a recent restore from backup.  It is
     * attached to the {@link #ACTION_APPWIDGET_RESTORED} broadcast intent.
     *
     * <p>
     * The value will be an int array that can be retrieved like this:
     * {@sample frameworks/base/tests/appwidgets/AppWidgetHostTest/src/com/android/tests/appwidgethost/TestAppWidgetProvider.java getExtra_EXTRA_APPWIDGET_IDS}
     */
    public static final String EXTRA_APPWIDGET_OLD_IDS = "appWidgetOldIds";

    /**
     * An intent extra which points to a bundle of extra information for a particular widget id.
     * In particular this bundle can contain {@link #OPTION_APPWIDGET_MIN_WIDTH},
     * {@link #OPTION_APPWIDGET_MIN_HEIGHT}, {@link #OPTION_APPWIDGET_MAX_WIDTH},
     * {@link #OPTION_APPWIDGET_MAX_HEIGHT}.
     */
    public static final String EXTRA_APPWIDGET_OPTIONS = "appWidgetOptions";

    /**
     * An extra that can be passed to
     * {@link #requestPinAppWidget(ComponentName, Bundle, PendingIntent)}. This would allow the
     * launcher app to present a custom preview to the user.
     *
     * <p>
     * The value should be a {@link RemoteViews} similar to what is used with
     * {@link #updateAppWidget} calls.
     */
    public static final String EXTRA_APPWIDGET_PREVIEW = "appWidgetPreview";

    /**
     * An intent extra that contains the component name of a AppWidget provider.
     * <p>
     * The value will be an {@link android.content.ComponentName}.
     */
    public static final String EXTRA_APPWIDGET_PROVIDER = "appWidgetProvider";

    /**
     * An intent extra that contains the user handle of the profile under
     * which an AppWidget provider is registered.
     * <p>
     * The value will be a {@link android.os.UserHandle}.
     */
    public static final String EXTRA_APPWIDGET_PROVIDER_PROFILE = "appWidgetProviderProfile";

    /**
     * An intent extra to pass to the AppWidget picker containing a {@link java.util.List} of
     * {@link android.os.Bundle} objects to mix in to the list of AppWidgets that are
     * installed.  It will be added to the extras object on the {@link android.content.Intent}
     * that is returned from the picker activity.
     *
     * {@more}
     */
    public static final String EXTRA_CUSTOM_EXTRAS = "customExtras";

    /**
     * An intent extra to pass to the AppWidget picker containing a {@link java.util.List} of
     * {@link AppWidgetProviderInfo} objects to mix in to the list of AppWidgets that are
     * installed.  (This is how the launcher shows the search widget).
     */
    public static final String EXTRA_CUSTOM_INFO = "customInfo";

    /**
     * An intent extra attached to the {@link #ACTION_APPWIDGET_HOST_RESTORED} broadcast,
     * indicating the integer ID of the host whose widgets have just been restored.
     */
    public static final String EXTRA_HOST_ID = "hostId";

    /**
     * A sentinel value that the AppWidget manager will never return as a appWidgetId.
     */
    public static final int INVALID_APPWIDGET_ID = 0;

    /**
     * Field for the manifest meta-data tag.
     *
     * @see AppWidgetProviderInfo
     */
    public static final String META_DATA_APPWIDGET_PROVIDER = "android.appwidget.provider";

    /**
     * A bundle extra that hints to the AppWidgetProvider the category of host that owns this
     * this widget. Can have the value {@link
     * AppWidgetProviderInfo#WIDGET_CATEGORY_HOME_SCREEN} or {@link
     * AppWidgetProviderInfo#WIDGET_CATEGORY_KEYGUARD} or {@link
     * AppWidgetProviderInfo#WIDGET_CATEGORY_SEARCHBOX} or {@link
     * AppWidgetProviderInfo#WIDGET_CATEGORY_NOT_KEYGUARD}.
     */
    public static final String OPTION_APPWIDGET_HOST_CATEGORY = "appWidgetCategory";

    /**
     * A bundle extra (int) that contains the upper bound on the current width, in dips, of a
     * widget instance.
     */
    public static final String OPTION_APPWIDGET_MAX_HEIGHT = "appWidgetMaxHeight";

    /**
     * A bundle extra (int) that contains the upper bound on the current width, in dips, of a
     * widget instance.
     */
    public static final String OPTION_APPWIDGET_MAX_WIDTH = "appWidgetMaxWidth";

    /**
     * A bundle extra (int) that contains the lower bound on the current height, in dips, of a
     * widget instance.
     */
    public static final String OPTION_APPWIDGET_MIN_HEIGHT = "appWidgetMinHeight";

    /**
     * A bundle extra (int) that contains the lower bound on the current width, in dips, of a
     * widget instance.
     */
    public static final String OPTION_APPWIDGET_MIN_WIDTH = "appWidgetMinWidth";

    /**
     * A bundle extra (boolean) that contains whether or not an app has finished restoring a widget.
     * <p> After restore, the app should set OPTION_APPWIDGET_RESTORE_COMPLETED to true on its
     * widgets followed by calling {@link #updateAppWidget} to update the views.
     *
     * @see #updateAppWidgetOptions(int, Bundle)
     */
    public static final String OPTION_APPWIDGET_RESTORE_COMPLETED = "appWidgetRestoreCompleted";

    /**
     * A bundle extra ({@code List<SizeF>}) that contains the list of possible sizes, in dips, a
     * widget instance can take.
     */
    public static final String OPTION_APPWIDGET_SIZES = "appWidgetSizes";

    private AppWidgetManager() {
    }
}
