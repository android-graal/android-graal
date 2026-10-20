package android.appwidget;

import android.os.Parcelable;

/**
 * Describes the meta data for an installed AppWidget provider.  The fields in this class
 * correspond to the fields in the <code>&lt;appwidget-provider&gt;</code> xml tag.
 */
public final class AppWidgetProviderInfo implements Parcelable {
    /**
     * Widget is resizable in both the horizontal and vertical axes.
     */
    public static final int RESIZE_BOTH = 3;

    /**
     * Widget is resizable in the horizontal axis only.
     */
    public static final int RESIZE_HORIZONTAL = 1;

    /**
     * Widget is not resizable.
     */
    public static final int RESIZE_NONE = 0;

    /**
     * Widget is resizable in the vertical axis only.
     */
    public static final int RESIZE_VERTICAL = 2;

    /**
     * Indicates that the widget can be displayed on the home screen. This is the default value.
     */
    public static final int WIDGET_CATEGORY_HOME_SCREEN = 1;

    /**
     * Indicates that the widget can be displayed on the keyguard.
     */
    public static final int WIDGET_CATEGORY_KEYGUARD = 2;

    /**
     * Indicates that the widget should never be shown on the keyguard.
     *
     * <p>Some keyguard style features may decide that {@link #WIDGET_CATEGORY_KEYGUARD} isn't
     * required to be added by an app to show on the feature when chosen by a user.
     * This category allows for a stronger statement about placement of the widget that, even in the
     * above case, this widget should not be offered on the keyguard.
     *
     * <p>Setting this category doesn't change the behavior of AppWidgetManager queries, it is the
     * responsibility of the widget surface to respect this value.
     */
    public static final int WIDGET_CATEGORY_NOT_KEYGUARD = 8;

    /**
     * Indicates that the widget can be displayed within a space reserved for the search box.
     */
    public static final int WIDGET_CATEGORY_SEARCHBOX = 4;

    /**
     * The widget provides a default configuration. The host may choose not to launch the provided
     * configuration activity.
     *
     * @see #widgetFeatures
     */
    public static final int WIDGET_FEATURE_CONFIGURATION_OPTIONAL = 4;

    /**
     * The widget is added directly by the app, and the host may hide this widget when providing
     * the user with the list of available widgets to choose from.
     *
     * @see AppWidgetManager#requestPinAppWidget(ComponentName, Bundle, PendingIntent)
     * @see #widgetFeatures
     */
    public static final int WIDGET_FEATURE_HIDE_FROM_PICKER = 2;

    /**
     * The widget can be reconfigured anytime after it is bound by starting the
     * {@link #configure} activity.
     *
     * @see #widgetFeatures
     */
    public static final int WIDGET_FEATURE_RECONFIGURABLE = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private AppWidgetProviderInfo() {
    }
}
