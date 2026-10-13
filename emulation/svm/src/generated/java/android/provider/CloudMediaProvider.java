package android.provider;

/**
 *  Base class for a cloud media provider. A cloud media provider offers read-only access to durable
 *  media files, specifically photos and videos stored on a local disk, or files in a cloud storage
 *  service. To create a cloud media provider, extend this class, implement the abstract methods,
 *  and add it to your manifest like this:
 *
 *  <pre class="prettyprint">&lt;manifest&gt;
 *     ...
 *     &lt;application&gt;
 *         ...
 *         &lt;provider
 *             android:name="com.example.MyCloudProvider"
 *             android:authorities="com.example.mycloudprovider"
 *             android:exported="true"
 *             android:permission="com.android.providers.media.permission.MANAGE_CLOUD_MEDIA_PROVIDERS"
 *             &lt;intent-filter&gt;
 *                 &lt;action android:name="android.content.action.CLOUD_MEDIA_PROVIDER" /&gt;
 *             &lt;/intent-filter&gt;
 *         &lt;/provider&gt;
 *         ...
 *     &lt;/application&gt;
 * &lt;/manifest&gt;</pre>
 *  <p>
 *  When defining your provider, you must protect it with the
 *  {@link CloudMediaProviderContract#MANAGE_CLOUD_MEDIA_PROVIDERS_PERMISSION}, which is a permission
 *  only the system can obtain, trying to define an unprotected {@link CloudMediaProvider} will
 *  result in a {@link SecurityException}.
 *  <p>
 *  Applications cannot use a cloud media provider directly; they must go through
 *  {@link MediaStore#ACTION_PICK_IMAGES} which requires a user to actively navigate and select
 *  media items. When a user selects a media item through that UI, the system issues narrow URI
 *  permission grants to the requesting application.
 *  <h3>Media items</h3>
 *  <p>
 *  A media item must be an openable stream (with a specific MIME type). Media items can belong to
 *  zero or more albums. Albums cannot contain other albums.
 *  <p>
 *  Each item under a provider is uniquely referenced by its media or album id, which must not
 *  change which must be unique across all collection IDs as returned by
 *  {@link #onGetMediaCollectionInfo}.
 *
 *  @see MediaStore#ACTION_PICK_IMAGES
 */
public final class CloudMediaProvider {
    private CloudMediaProvider() {
    }

    /**
     * This class is used by {@link CloudMediaProvider} to send {@link Surface} state updates to
     * picker launched via {@link MediaStore#ACTION_PICK_IMAGES}.
     *
     * @see MediaStore#ACTION_PICK_IMAGES
     */
    public static final class CloudMediaSurfaceStateChangedCallback {
        /**
         * Constant to notify that the playback is buffering
         */
        public static final int PLAYBACK_STATE_BUFFERING = 1;

        /**
         * Constant to notify that the playback has completed
         */
        public static final int PLAYBACK_STATE_COMPLETED = 5;

        /**
         * Constant to notify that the playback has failed with a permanent error.
         */
        public static final int PLAYBACK_STATE_ERROR_PERMANENT_FAILURE = 7;

        /**
         * Constant to notify that the playback has failed with a retriable error.
         */
        public static final int PLAYBACK_STATE_ERROR_RETRIABLE_FAILURE = 6;

        /**
         * Constant to notify that the media size is first known or has changed.
         *
         * Pass the width and height of the media as a {@link Point} inside the {@link Bundle} with
         * {@link ContentResolver#EXTRA_SIZE} as the key.
         *
         * @see CloudMediaSurfaceStateChangedCallback#setPlaybackState(int, int, Bundle)
         * @see MediaPlayer.OnVideoSizeChangedListener#onVideoSizeChanged(MediaPlayer, int, int)
         */
        public static final int PLAYBACK_STATE_MEDIA_SIZE_CHANGED = 8;

        /**
         * Constant to notify that the playback is paused.
         */
        public static final int PLAYBACK_STATE_PAUSED = 4;

        /**
         * Constant to notify that the playback is ready to be played
         */
        public static final int PLAYBACK_STATE_READY = 2;

        /**
         * Constant to notify that the playback has started
         */
        public static final int PLAYBACK_STATE_STARTED = 3;

        private CloudMediaSurfaceStateChangedCallback() {
        }
    }
}
