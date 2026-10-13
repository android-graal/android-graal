package android.media;

/**
 * This API is not recommended for new applications. Use the
 * <a href="{@docRoot}jetpack/androidx.html">AndroidX</a>
 * <a href="{@docRoot}reference/androidx/mediarouter/media/package-summary.html">Media Router
 * Library</a> for consistent behavior across all devices.
 *
 * <p>MediaRouter allows applications to control the routing of media channels
 * and streams from the current device to external speakers and destination devices.
 *
 * <p>A MediaRouter is retrieved through {@link Context#getSystemService(String)
 * Context.getSystemService()} of a {@link Context#MEDIA_ROUTER_SERVICE
 * Context.MEDIA_ROUTER_SERVICE}.
 *
 * <p>This API is not thread-safe; all interactions with it must be done from the main thread of the
 * process.
 */
public final class MediaRouter {
    /**
     * Flag for {@link #addCallback}: Actively scan for routes while this callback
     * is registered.
     * <p>
     * When this flag is specified, the media router will actively scan for new
     * routes.  Certain routes, such as wifi display routes, may not be discoverable
     * except when actively scanning.  This flag is typically used when the route picker
     * dialog has been opened by the user to ensure that the route information is
     * up to date.
     * </p><p>
     * Active scanning may consume a significant amount of power and may have intrusive
     * effects on wireless connectivity.  Therefore it is important that active scanning
     * only be requested when it is actually needed to satisfy a user request to
     * discover and select a new route.
     * </p>
     */
    public static final int CALLBACK_FLAG_PERFORM_ACTIVE_SCAN = 1;

    /**
     * Flag for {@link #addCallback}: Do not filter route events.
     * <p>
     * When this flag is specified, the callback will be invoked for event that affect any
     * route even if they do not match the callback's filter.
     * </p>
     */
    public static final int CALLBACK_FLAG_UNFILTERED_EVENTS = 2;

    /**
     * Route type flag for live audio.
     *
     * <p>A device that supports live audio routing will allow the media audio stream
     * to be routed to supported destinations. This can include internal speakers or
     * audio jacks on the device itself, A2DP devices, and more.</p>
     *
     * <p>Once initiated this routing is transparent to the application. All audio
     * played on the media stream will be routed to the selected destination.</p>
     */
    public static final int ROUTE_TYPE_LIVE_AUDIO = 1;

    /**
     * Route type flag for live video.
     *
     * <p>A device that supports live video routing will allow a mirrored version
     * of the device's primary display or a customized
     * {@link android.app.Presentation Presentation} to be routed to supported destinations.</p>
     *
     * <p>Once initiated, display mirroring is transparent to the application.
     * While remote routing is active the application may use a
     * {@link android.app.Presentation Presentation} to replace the mirrored view
     * on the external display with different content.</p>
     *
     * @see RouteInfo#getPresentationDisplay()
     * @see android.app.Presentation
     */
    public static final int ROUTE_TYPE_LIVE_VIDEO = 2;

    /**
     * Route type flag for application-specific usage.
     *
     * <p>Unlike other media route types, user routes are managed by the application.
     * The MediaRouter will manage and dispatch events for user routes, but the application
     * is expected to interpret the meaning of these events and perform the requested
     * routing tasks.</p>
     */
    public static final int ROUTE_TYPE_USER = 8388608;

    private MediaRouter() {
    }

    /**
     * Information about a media route.
     */
    public static final class RouteInfo {
        /**
         * A receiver device type of the route indicating the presentation of the media is happening
         * on a bluetooth device such as a bluetooth speaker.
         *
         * @see #getDeviceType
         */
        public static final int DEVICE_TYPE_BLUETOOTH = 3;

        /**
         * A receiver device type of the route indicating the presentation of the media is happening
         * on a speaker.
         *
         * @see #getDeviceType
         */
        public static final int DEVICE_TYPE_SPEAKER = 2;

        /**
         * A receiver device type of the route indicating the presentation of the media is happening
         * on a TV.
         *
         * @see #getDeviceType
         */
        public static final int DEVICE_TYPE_TV = 1;

        /**
         * The default receiver device type of the route indicating the type is unknown.
         *
         * @see #getDeviceType
         */
        public static final int DEVICE_TYPE_UNKNOWN = 0;

        /**
         * The default playback type, "local", indicating the presentation of the media is happening
         * on the same device (e&#46;g&#46; a phone, a tablet) as where it is controlled from.
         * @see #getPlaybackType()
         */
        public static final int PLAYBACK_TYPE_LOCAL = 0;

        /**
         * A playback type indicating the presentation of the media is happening on
         * a different device (i&#46;e&#46; the remote device) than where it is controlled from.
         * @see #getPlaybackType()
         */
        public static final int PLAYBACK_TYPE_REMOTE = 1;

        /**
         * Playback information indicating the playback volume is fixed, i&#46;e&#46; it cannot be
         * controlled from this object. An example of fixed playback volume is a remote player,
         * playing over HDMI where the user prefers to control the volume on the HDMI sink, rather
         * than attenuate at the source.
         * @see #getVolumeHandling()
         */
        public static final int PLAYBACK_VOLUME_FIXED = 0;

        /**
         * Playback information indicating the playback volume is variable and can be controlled
         * from this object.
         * @see #getVolumeHandling()
         */
        public static final int PLAYBACK_VOLUME_VARIABLE = 1;

        private RouteInfo() {
        }
    }
}
