package android.media;

import android.os.Parcelable;

/**
 * Allows applications to customize the list of routes used for media routing (for example, in the
 * System UI Output Switcher).
 *
 * @see MediaRouter2#setRouteListingPreference
 * @see Item
 */
public final class RouteListingPreference implements Parcelable {
    /**
     * {@link Intent} action that the system uses to take the user the app when the user selects an
     * {@link Item} whose {@link Item#getSelectionBehavior() selection behavior} is {@link
     * Item#SELECTION_BEHAVIOR_GO_TO_APP}.
     *
     * <p>The launched intent will identify the selected item using the extra identified by {@link
     * #EXTRA_ROUTE_ID}.
     *
     * @see #getLinkedItemComponentName()
     * @see Item#SELECTION_BEHAVIOR_GO_TO_APP
     */
    public static final String ACTION_TRANSFER_MEDIA = "android.media.action.TRANSFER_MEDIA";

    /**
     * {@link Intent} string extra key that contains the {@link Item#getRouteId() id} of the route
     * to transfer to, as part of an {@link #ACTION_TRANSFER_MEDIA} intent.
     *
     * @see #getLinkedItemComponentName()
     * @see Item#SELECTION_BEHAVIOR_GO_TO_APP
     */
    public static final String EXTRA_ROUTE_ID = "android.media.extra.ROUTE_ID";

    private RouteListingPreference() {
    }

    /**
     * Holds preference information for a specific route in a {@link RouteListingPreference}.
     */
    public static final class Item implements Parcelable {
        /**
         * The corresponding route is already hosting a session with the app that owns this listing
         * preference.
         */
        public static final int FLAG_ONGOING_SESSION = 1;

        /**
         * Signals that the ongoing session on the corresponding route is managed by the current
         * user of the app.
         *
         * <p>The system can use this flag to provide visual indication that the route is not only
         * hosting a session, but also that the user has ownership over said session.
         *
         * <p>This flag is ignored if {@link #FLAG_ONGOING_SESSION} is not set, or if the
         * corresponding route is not currently selected.
         *
         * <p>This flag does not affect volume adjustment (see {@link VolumeProvider}, and {@link
         * MediaRoute2Info#getVolumeHandling()}), or any aspect other than the visual representation
         * of the corresponding item.
         */
        public static final int FLAG_ONGOING_SESSION_MANAGED = 2;

        /**
         * The corresponding route is specially likely to be selected by the user.
         *
         * <p>A UI reflecting this preference may reserve a specific space for suggested routes,
         * making it more accessible to the user. If the number of suggested routes exceeds the
         * number supported by the UI, the routes listed first in {@link
         * RouteListingPreference#getItems()} will take priority.
         */
        public static final int FLAG_SUGGESTED = 4;

        /**
         * If the user selects the corresponding route, the system takes the user to the
         * application.
         *
         * <p>The system uses {@link #getLinkedItemComponentName()} in order to navigate to the app.
         */
        public static final int SELECTION_BEHAVIOR_GO_TO_APP = 2;

        /**
         * The corresponding route is not selectable by the user.
         */
        public static final int SELECTION_BEHAVIOR_NONE = 0;

        /**
         * If the user selects the corresponding route, the media transfers to the said route.
         */
        public static final int SELECTION_BEHAVIOR_TRANSFER = 1;

        /**
         * The corresponding route's subtext must indicate that it is not available because an ad is
         * in progress.
         */
        public static final int SUBTEXT_AD_ROUTING_DISALLOWED = 4;

        /**
         * The corresponding route's subtext must be obtained from {@link
         * #getCustomSubtextMessage()}.
         *
         * <p>Applications should strongly prefer one of the other disable reasons (for the full
         * list, see {@link #getSubText()}) in order to guarantee correct localization and rendering
         * across all form factors.
         */
        public static final int SUBTEXT_CUSTOM = 10000;

        /**
         * The corresponding route's subtext must indicate that it is not available because the
         * device is in low-power mode.
         */
        public static final int SUBTEXT_DEVICE_LOW_POWER = 5;

        /**
         * The corresponding route's subtext must indicate that downloaded content cannot be routed
         * to it.
         */
        public static final int SUBTEXT_DOWNLOADED_CONTENT_ROUTING_DISALLOWED = 3;

        /**
         * The corresponding route's subtext must indicate that it is not available because of an
         * unknown error.
         */
        public static final int SUBTEXT_ERROR_UNKNOWN = 1;

        /**
         * The corresponding route has no associated subtext.
         */
        public static final int SUBTEXT_NONE = 0;

        /**
         * The corresponding route's subtext must indicate that it requires a special subscription
         * in order to be available for routing.
         */
        public static final int SUBTEXT_SUBSCRIPTION_REQUIRED = 2;

        /**
         * The corresponding route's subtext must indicate that it is not available because the
         * device does not support the current media track.
         */
        public static final int SUBTEXT_TRACK_UNSUPPORTED = 7;

        /**
         * The corresponding route's subtext must indicate that it is not available because the user
         * is not authorized to route to it.
         */
        public static final int SUBTEXT_UNAUTHORIZED = 6;

        private Item() {
        }
    }
}
