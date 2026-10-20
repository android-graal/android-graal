package android.media;

import android.os.Parcelable;

/**
 * Contains metadata about an item, such as the title, artist, etc.
 */
public final class MediaMetadata implements Parcelable {
    /**
     * The album title for the media.
     */
    public static final String METADATA_KEY_ALBUM = "android.media.metadata.ALBUM";

    /**
     * The artwork for the album of the media's original source as a
     * {@link Bitmap}.
     * <p>
     * The artwork should be relatively small and may be scaled down by the
     * system if it is too large. For higher resolution artwork
     * {@link #METADATA_KEY_ALBUM_ART_URI} should be used instead.
     */
    public static final String METADATA_KEY_ALBUM_ART = "android.media.metadata.ALBUM_ART";

    /**
     * The artist for the album of the media's original source.
     */
    public static final String METADATA_KEY_ALBUM_ARTIST = "android.media.metadata.ALBUM_ARTIST";

    /**
     * The artwork for the album of the media's original source as a Uri
     * formatted String. The artwork can be loaded using a combination of
     * {@link ContentResolver#openInputStream} and
     * {@link BitmapFactory#decodeStream}.
     * <p>
     * For the best results, Uris should use the content:// style and support
     * {@link ContentResolver#EXTRA_SIZE} for retrieving scaled artwork through
     * {@link ContentResolver#openTypedAssetFileDescriptor(Uri, String, Bundle)}.
     */
    public static final String METADATA_KEY_ALBUM_ART_URI = "android.media.metadata.ALBUM_ART_URI";

    /**
     * The artwork for the media as a {@link Bitmap}.
     * <p>
     * The artwork should be relatively small and may be scaled down by the
     * system if it is too large. For higher resolution artwork
     * {@link #METADATA_KEY_ART_URI} should be used instead.
     */
    public static final String METADATA_KEY_ART = "android.media.metadata.ART";

    /**
     * The artist of the media.
     */
    public static final String METADATA_KEY_ARTIST = "android.media.metadata.ARTIST";

    /**
     * The artwork for the media as a Uri formatted String. The artwork can be
     * loaded using a combination of {@link ContentResolver#openInputStream} and
     * {@link BitmapFactory#decodeStream}.
     * <p>
     * For the best results, Uris should use the content:// style and support
     * {@link ContentResolver#EXTRA_SIZE} for retrieving scaled artwork through
     * {@link ContentResolver#openTypedAssetFileDescriptor(Uri, String, Bundle)}.
     */
    public static final String METADATA_KEY_ART_URI = "android.media.metadata.ART_URI";

    /**
     * The author of the media.
     */
    public static final String METADATA_KEY_AUTHOR = "android.media.metadata.AUTHOR";

    /**
     * The bluetooth folder type of the media specified in the section 6.10.2.2 of the Bluetooth
     * AVRCP 1.5. It should be one of the following:
     * <ul>
     * <li>{@link MediaDescription#BT_FOLDER_TYPE_MIXED}</li>
     * <li>{@link MediaDescription#BT_FOLDER_TYPE_TITLES}</li>
     * <li>{@link MediaDescription#BT_FOLDER_TYPE_ALBUMS}</li>
     * <li>{@link MediaDescription#BT_FOLDER_TYPE_ARTISTS}</li>
     * <li>{@link MediaDescription#BT_FOLDER_TYPE_GENRES}</li>
     * <li>{@link MediaDescription#BT_FOLDER_TYPE_PLAYLISTS}</li>
     * <li>{@link MediaDescription#BT_FOLDER_TYPE_YEARS}</li>
     * </ul>
     */
    public static final String METADATA_KEY_BT_FOLDER_TYPE = "android.media.metadata.BT_FOLDER_TYPE";

    /**
     * The compilation status of the media.
     */
    public static final String METADATA_KEY_COMPILATION = "android.media.metadata.COMPILATION";

    /**
     * The composer of the media.
     */
    public static final String METADATA_KEY_COMPOSER = "android.media.metadata.COMPOSER";

    /**
     * The date the media was created or published. The format is unspecified
     * but RFC 3339 is recommended.
     */
    public static final String METADATA_KEY_DATE = "android.media.metadata.DATE";

    /**
     * The disc number for the media's original source.
     */
    public static final String METADATA_KEY_DISC_NUMBER = "android.media.metadata.DISC_NUMBER";

    /**
     * A description that is suitable for display to the user. When displaying
     * more information for media described by this metadata this should be
     * preferred to other fields if present.
     */
    public static final String METADATA_KEY_DISPLAY_DESCRIPTION = "android.media.metadata.DISPLAY_DESCRIPTION";

    /**
     * An icon or thumbnail that is suitable for display to the user. When
     * displaying an icon for media described by this metadata this should be
     * preferred to other fields if present. This must be a {@link Bitmap}.
     * <p>
     * The icon should be relatively small and may be scaled down by the system
     * if it is too large. For higher resolution artwork
     * {@link #METADATA_KEY_DISPLAY_ICON_URI} should be used instead.
     */
    public static final String METADATA_KEY_DISPLAY_ICON = "android.media.metadata.DISPLAY_ICON";

    /**
     * A Uri formatted String for an icon or thumbnail that is suitable for
     * display to the user. When displaying more information for media described
     * by this metadata the display description should be preferred to other
     * fields when present. The icon can be loaded using a combination of
     * {@link ContentResolver#openInputStream} and
     * {@link BitmapFactory#decodeStream}.
     * <p>
     * For the best results, Uris should use the content:// style and support
     * {@link ContentResolver#EXTRA_SIZE} for retrieving scaled artwork through
     * {@link ContentResolver#openTypedAssetFileDescriptor(Uri, String, Bundle)}.
     */
    public static final String METADATA_KEY_DISPLAY_ICON_URI = "android.media.metadata.DISPLAY_ICON_URI";

    /**
     * A subtitle that is suitable for display to the user. When displaying a
     * second line for media described by this metadata this should be preferred
     * to other fields if present.
     */
    public static final String METADATA_KEY_DISPLAY_SUBTITLE = "android.media.metadata.DISPLAY_SUBTITLE";

    /**
     * A title that is suitable for display to the user. This will generally be
     * the same as {@link #METADATA_KEY_TITLE} but may differ for some formats.
     * When displaying media described by this metadata this should be preferred
     * if present.
     */
    public static final String METADATA_KEY_DISPLAY_TITLE = "android.media.metadata.DISPLAY_TITLE";

    /**
     * The duration of the media in ms. A negative duration indicates that the
     * duration is unknown (or infinite).
     */
    public static final String METADATA_KEY_DURATION = "android.media.metadata.DURATION";

    /**
     * The genre of the media.
     */
    public static final String METADATA_KEY_GENRE = "android.media.metadata.GENRE";

    /**
     * A String key for identifying the content. This value is specific to the
     * service providing the content. If used, this should be a persistent
     * unique key for the underlying content. It may be used with
     * {@link MediaController.TransportControls#playFromMediaId(String, Bundle)}
     * to initiate playback when provided by a {@link MediaBrowser} connected to
     * the same app.
     */
    public static final String METADATA_KEY_MEDIA_ID = "android.media.metadata.MEDIA_ID";

    /**
     * A Uri formatted String representing the content. This value is specific to the
     * service providing the content. It may be used with
     * {@link MediaController.TransportControls#playFromUri(Uri, Bundle)}
     * to initiate playback when provided by a {@link MediaBrowser} connected to
     * the same app.
     */
    public static final String METADATA_KEY_MEDIA_URI = "android.media.metadata.MEDIA_URI";

    /**
     * The number of tracks in the media's original source.
     */
    public static final String METADATA_KEY_NUM_TRACKS = "android.media.metadata.NUM_TRACKS";

    /**
     * The overall rating for the media.
     *
     * @see Rating
     */
    public static final String METADATA_KEY_RATING = "android.media.metadata.RATING";

    /**
     * The title of the media.
     */
    public static final String METADATA_KEY_TITLE = "android.media.metadata.TITLE";

    /**
     * The track number for the media.
     */
    public static final String METADATA_KEY_TRACK_NUMBER = "android.media.metadata.TRACK_NUMBER";

    /**
     * The user's rating for the media.
     *
     * @see Rating
     */
    public static final String METADATA_KEY_USER_RATING = "android.media.metadata.USER_RATING";

    /**
     * The writer of the media.
     */
    public static final String METADATA_KEY_WRITER = "android.media.metadata.WRITER";

    /**
     * The year the media was created or published as a long.
     */
    public static final String METADATA_KEY_YEAR = "android.media.metadata.YEAR";

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private MediaMetadata() {
    }
}
