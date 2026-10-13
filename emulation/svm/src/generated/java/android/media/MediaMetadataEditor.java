package android.media;

/**
 * An abstract class for editing and storing metadata that can be published by
 * {@link RemoteControlClient}. See the {@link RemoteControlClient#editMetadata(boolean)}
 * method to instantiate a {@link RemoteControlClient.MetadataEditor} object.
 *
 * @deprecated Use {@link MediaMetadata} instead together with {@link MediaSession}.
 */
@Deprecated
public final class MediaMetadataEditor {
    /**
     * The metadata key for the content artwork / album art.
     */
    @Deprecated
    public static final int BITMAP_KEY_ARTWORK = 100;

    /**
     * The metadata key for the content's average rating, not the user's rating.
     * The value associated with this key is a {@link Rating} instance.
     * @see #RATING_KEY_BY_USER
     */
    @Deprecated
    public static final int RATING_KEY_BY_OTHERS = 101;

    /**
     * The metadata key for the content's user rating.
     * The value associated with this key is a {@link Rating} instance.
     * This key can be flagged as "editable" (with {@link #addEditableKey(int)}) to enable
     * receiving user rating values through the
     * {@link android.media.RemoteControlClient.OnMetadataUpdateListener} interface.
     */
    @Deprecated
    public static final int RATING_KEY_BY_USER = 268435457;

    private MediaMetadataEditor() {
    }
}
