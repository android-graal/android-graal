package android.media;

/**
 * RemoteControlClient enables exposing information meant to be consumed by remote controls
 * capable of displaying metadata, artwork and media transport control buttons.
 *
 * <p>A remote control client object is associated with a media button event receiver. This
 * event receiver must have been previously registered with
 * {@link AudioManager#registerMediaButtonEventReceiver(ComponentName)} before the
 * RemoteControlClient can be registered through
 * {@link AudioManager#registerRemoteControlClient(RemoteControlClient)}.
 *
 * <p>Here is an example of creating a RemoteControlClient instance after registering a media
 * button event receiver:
 * <pre>ComponentName myEventReceiver = new ComponentName(getPackageName(), MyRemoteControlEventReceiver.class.getName());
 * AudioManager myAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
 * myAudioManager.registerMediaButtonEventReceiver(myEventReceiver);
 * // build the PendingIntent for the remote control client
 * Intent mediaButtonIntent = new Intent(Intent.ACTION_MEDIA_BUTTON);
 * mediaButtonIntent.setComponent(myEventReceiver);
 * PendingIntent mediaPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, mediaButtonIntent, PendingIntent.FLAG_MUTABLE_UNAUDITED);
 * // create and register the remote control client
 * RemoteControlClient myRemoteControlClient = new RemoteControlClient(mediaPendingIntent);
 * myAudioManager.registerRemoteControlClient(myRemoteControlClient);</pre>
 *
 * @deprecated Use {@link MediaSession} instead.
 */
@Deprecated
public final class RemoteControlClient {
    /**
     * Flag indicating a RemoteControlClient makes use of the "fast forward" media key.
     *
     * @see #setTransportControlFlags(int)
     * @see android.view.KeyEvent#KEYCODE_MEDIA_FAST_FORWARD
     */
    @Deprecated
    public static final int FLAG_KEY_MEDIA_FAST_FORWARD = 64;

    /**
     * Flag indicating a RemoteControlClient makes use of the "next" media key.
     *
     * @see #setTransportControlFlags(int)
     * @see android.view.KeyEvent#KEYCODE_MEDIA_NEXT
     */
    @Deprecated
    public static final int FLAG_KEY_MEDIA_NEXT = 128;

    /**
     * Flag indicating a RemoteControlClient makes use of the "pause" media key.
     *
     * @see #setTransportControlFlags(int)
     * @see android.view.KeyEvent#KEYCODE_MEDIA_PAUSE
     */
    @Deprecated
    public static final int FLAG_KEY_MEDIA_PAUSE = 16;

    /**
     * Flag indicating a RemoteControlClient makes use of the "play" media key.
     *
     * @see #setTransportControlFlags(int)
     * @see android.view.KeyEvent#KEYCODE_MEDIA_PLAY
     */
    @Deprecated
    public static final int FLAG_KEY_MEDIA_PLAY = 4;

    /**
     * Flag indicating a RemoteControlClient makes use of the "play/pause" media key.
     *
     * @see #setTransportControlFlags(int)
     * @see android.view.KeyEvent#KEYCODE_MEDIA_PLAY_PAUSE
     */
    @Deprecated
    public static final int FLAG_KEY_MEDIA_PLAY_PAUSE = 8;

    /**
     * Flag indicating a RemoteControlClient can receive changes in the media playback position
     * through the {@link OnPlaybackPositionUpdateListener} interface. This flag must be set
     * in order for components that display the RemoteControlClient information, to display and
     * let the user control media playback position.
     * @see #setTransportControlFlags(int)
     * @see #setOnGetPlaybackPositionListener(OnGetPlaybackPositionListener)
     * @see #setPlaybackPositionUpdateListener(OnPlaybackPositionUpdateListener)
     */
    @Deprecated
    public static final int FLAG_KEY_MEDIA_POSITION_UPDATE = 256;

    /**
     * Flag indicating a RemoteControlClient makes use of the "previous" media key.
     *
     * @see #setTransportControlFlags(int)
     * @see android.view.KeyEvent#KEYCODE_MEDIA_PREVIOUS
     */
    @Deprecated
    public static final int FLAG_KEY_MEDIA_PREVIOUS = 1;

    /**
     * Flag indicating a RemoteControlClient supports ratings.
     * This flag must be set in order for components that display the RemoteControlClient
     * information, to display ratings information, and, if ratings are declared editable
     * (by calling {@link MediaMetadataEditor#addEditableKey(int)} with the
     * {@link MediaMetadataEditor#RATING_KEY_BY_USER} key), it will enable the user to rate
     * the media, with values being received through the interface set with
     * {@link #setMetadataUpdateListener(OnMetadataUpdateListener)}.
     * @see #setTransportControlFlags(int)
     */
    @Deprecated
    public static final int FLAG_KEY_MEDIA_RATING = 512;

    /**
     * Flag indicating a RemoteControlClient makes use of the "rewind" media key.
     *
     * @see #setTransportControlFlags(int)
     * @see android.view.KeyEvent#KEYCODE_MEDIA_REWIND
     */
    @Deprecated
    public static final int FLAG_KEY_MEDIA_REWIND = 2;

    /**
     * Flag indicating a RemoteControlClient makes use of the "stop" media key.
     *
     * @see #setTransportControlFlags(int)
     * @see android.view.KeyEvent#KEYCODE_MEDIA_STOP
     */
    @Deprecated
    public static final int FLAG_KEY_MEDIA_STOP = 32;

    /**
     * Playback state of a RemoteControlClient which is buffering data to play before it can
     *    start or resume playback.
     *
     * @see #setPlaybackState(int)
     */
    @Deprecated
    public static final int PLAYSTATE_BUFFERING = 8;

    /**
     * Playback state of a RemoteControlClient which cannot perform any playback related
     *    operation because of an internal error. Examples of such situations are no network
     *    connectivity when attempting to stream data from a server, or expired user credentials
     *    when trying to play subscription-based content.
     *
     * @see #setPlaybackState(int)
     */
    @Deprecated
    public static final int PLAYSTATE_ERROR = 9;

    /**
     * Playback state of a RemoteControlClient which is fast forwarding in the media
     *    it is currently playing.
     *
     * @see #setPlaybackState(int)
     */
    @Deprecated
    public static final int PLAYSTATE_FAST_FORWARDING = 4;

    /**
     * Playback state of a RemoteControlClient which is paused.
     *
     * @see #setPlaybackState(int)
     */
    @Deprecated
    public static final int PLAYSTATE_PAUSED = 2;

    /**
     * Playback state of a RemoteControlClient which is playing media.
     *
     * @see #setPlaybackState(int)
     */
    @Deprecated
    public static final int PLAYSTATE_PLAYING = 3;

    /**
     * Playback state of a RemoteControlClient which is fast rewinding in the media
     *    it is currently playing.
     *
     * @see #setPlaybackState(int)
     */
    @Deprecated
    public static final int PLAYSTATE_REWINDING = 5;

    /**
     * Playback state of a RemoteControlClient which is skipping back to the previous
     *    logical chapter (such as a song in a playlist) in the media it is currently playing.
     *
     * @see #setPlaybackState(int)
     */
    @Deprecated
    public static final int PLAYSTATE_SKIPPING_BACKWARDS = 7;

    /**
     * Playback state of a RemoteControlClient which is skipping to the next
     *    logical chapter (such as a song in a playlist) in the media it is currently playing.
     *
     * @see #setPlaybackState(int)
     */
    @Deprecated
    public static final int PLAYSTATE_SKIPPING_FORWARDS = 6;

    /**
     * Playback state of a RemoteControlClient which is stopped.
     *
     * @see #setPlaybackState(int)
     */
    @Deprecated
    public static final int PLAYSTATE_STOPPED = 1;

    private RemoteControlClient() {
    }

    /**
     * Class used to modify metadata in a {@link RemoteControlClient} object.
     * Use {@link RemoteControlClient#editMetadata(boolean)} to create an instance of an editor,
     * on which you set the metadata for the RemoteControlClient instance. Once all the information
     * has been set, use {@link #apply()} to make it the new metadata that should be displayed
     * for the associated client. Once the metadata has been "applied", you cannot reuse this
     * instance of the MetadataEditor.
     *
     * @deprecated Use {@link MediaMetadata} and {@link MediaSession} instead.
     */
    @Deprecated
    public static final class MetadataEditor {
        /**
         * The metadata key for the content artwork / album art.
         */
        @Deprecated
        public static final int BITMAP_KEY_ARTWORK = 100;

        private MetadataEditor() {
        }
    }
}
