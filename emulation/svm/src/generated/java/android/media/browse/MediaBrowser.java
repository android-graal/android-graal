package android.media.browse;

import android.os.Parcelable;

/**
 * Browses media content offered by a link MediaBrowserService.
 * <p>
 * This object is not thread-safe. All calls should happen on the thread on which the browser
 * was constructed.
 * </p>
 * <h3>Standard Extra Data</h3>
 *
 * <p>These are the current standard fields that can be used as extra data via
 * {@link #subscribe(String, Bundle, SubscriptionCallback)},
 * {@link #unsubscribe(String, SubscriptionCallback)}, and
 * {@link SubscriptionCallback#onChildrenLoaded(String, List, Bundle)}.
 *
 * <ul>
 *     <li> {@link #EXTRA_PAGE}
 *     <li> {@link #EXTRA_PAGE_SIZE}
 * </ul>
 */
public final class MediaBrowser {
    /**
     * Used as an int extra field to denote the page number to subscribe.
     * The value of {@code EXTRA_PAGE} should be greater than or equal to 0.
     *
     * @see #EXTRA_PAGE_SIZE
     */
    public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";

    /**
     * Used as an int extra field to denote the number of media items in a page.
     * The value of {@code EXTRA_PAGE_SIZE} should be greater than or equal to 1.
     *
     * @see #EXTRA_PAGE
     */
    public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";

    private MediaBrowser() {
    }

    /**
     * A class with information on a single media item for use in browsing/searching media.
     * MediaItems are application dependent so we cannot guarantee that they contain the
     * right values.
     */
    public static final class MediaItem implements Parcelable {
        /**
         * Flag: Indicates that the item has children of its own.
         */
        public static final int FLAG_BROWSABLE = 1;

        /**
         * Flag: Indicates that the item is playable.
         * <p>
         * The id of this item may be passed to
         * {@link android.media.session.MediaController.TransportControls
         * #playFromMediaId(String, Bundle)} to start playing it.
         * </p>
         */
        public static final int FLAG_PLAYABLE = 2;

        private MediaItem() {
        }
    }
}
