package android.media;

import android.os.Parcelable;

/**
 * A simple set of metadata for a media item suitable for display. This can be
 * created using the Builder or retrieved from existing metadata using
 * {@link MediaMetadata#getDescription()}.
 */
public final class MediaDescription implements Parcelable {
    /**
     * The type of folder that contains folders categorized by album as specified in the section
     * 6.10.2.2 of the Bluetooth AVRCP 1.5.
     */
    public static final long BT_FOLDER_TYPE_ALBUMS = 2L;

    /**
     * The type of folder that contains folders categorized by artist as specified in the section
     * 6.10.2.2 of the Bluetooth AVRCP 1.5.
     */
    public static final long BT_FOLDER_TYPE_ARTISTS = 3L;

    /**
     * The type of folder that contains folders categorized by genre as specified in the section
     * 6.10.2.2 of the Bluetooth AVRCP 1.5.
     */
    public static final long BT_FOLDER_TYPE_GENRES = 4L;

    /**
     * The type of folder that is unknown or contains media elements of mixed types as specified in
     * the section 6.10.2.2 of the Bluetooth AVRCP 1.5.
     */
    public static final long BT_FOLDER_TYPE_MIXED = 0L;

    /**
     * The type of folder that contains folders categorized by playlist as specified in the section
     * 6.10.2.2 of the Bluetooth AVRCP 1.5.
     */
    public static final long BT_FOLDER_TYPE_PLAYLISTS = 5L;

    /**
     * The type of folder that contains media elements only as specified in the section 6.10.2.2 of
     * the Bluetooth AVRCP 1.5.
     */
    public static final long BT_FOLDER_TYPE_TITLES = 1L;

    /**
     * The type of folder that contains folders categorized by year as specified in the section
     * 6.10.2.2 of the Bluetooth AVRCP 1.5.
     */
    public static final long BT_FOLDER_TYPE_YEARS = 6L;

    /**
     * Used as a long extra field to indicate the bluetooth folder type of the media item as
     * specified in the section 6.10.2.2 of the Bluetooth AVRCP 1.5. This is valid only for
     * {@link MediaBrowser.MediaItem} with {@link MediaBrowser.MediaItem#FLAG_BROWSABLE}. The value
     * should be one of the following:
     * <ul>
     * <li>{@link #BT_FOLDER_TYPE_MIXED}</li>
     * <li>{@link #BT_FOLDER_TYPE_TITLES}</li>
     * <li>{@link #BT_FOLDER_TYPE_ALBUMS}</li>
     * <li>{@link #BT_FOLDER_TYPE_ARTISTS}</li>
     * <li>{@link #BT_FOLDER_TYPE_GENRES}</li>
     * <li>{@link #BT_FOLDER_TYPE_PLAYLISTS}</li>
     * <li>{@link #BT_FOLDER_TYPE_YEARS}</li>
     * </ul>
     *
     * @see #getExtras()
     */
    public static final String EXTRA_BT_FOLDER_TYPE = "android.media.extra.BT_FOLDER_TYPE";

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private MediaDescription() {
    }
}
