package android.media;

import android.os.Parcelable;

/**
 * Describes the properties of a route.
 */
public final class MediaRoute2Info implements Parcelable {
    /**
     * A connection state indicating the route is connected.
     *
     * @see #getConnectionState
     */
    public static final int CONNECTION_STATE_CONNECTED = 2;

    /**
     * A connection state indicating the route is in the process of connecting and is not yet
     * ready for use.
     *
     * @see #getConnectionState
     */
    public static final int CONNECTION_STATE_CONNECTING = 1;

    /**
     * The default connection state indicating the route is disconnected.
     *
     * @see #getConnectionState
     */
    public static final int CONNECTION_STATE_DISCONNECTED = 0;

    /**
     * Route feature: Live audio.
     * <p>
     * A route that supports live audio routing will allow the media audio stream
     * to be sent to supported destinations.  This can include internal speakers or
     * audio jacks on the device itself, A2DP devices, and more.
     * </p><p>
     * When a live audio route is selected, audio routing is transparent to the application.
     * All audio played on the media stream will be routed to the selected destination.
     * </p><p>
     * Refer to the class documentation for details about live audio routes.
     * </p>
     */
    public static final String FEATURE_LIVE_AUDIO = "android.media.route.feature.LIVE_AUDIO";

    /**
     * Route feature: Live video.
     * <p>
     * A route that supports live video routing will allow a mirrored version
     * of the device's primary display or a customized
     * {@link android.app.Presentation Presentation} to be sent to supported
     * destinations.
     * </p><p>
     * When a live video route is selected, audio and video routing is transparent
     * to the application.  By default, audio and video is routed to the selected
     * destination.  For certain live video routes, the application may also use a
     * {@link android.app.Presentation Presentation} to replace the mirrored view
     * on the external display with different content.
     * </p><p>
     * Refer to the class documentation for details about live video routes.
     * </p>
     *
     * @see android.app.Presentation
     */
    public static final String FEATURE_LIVE_VIDEO = "android.media.route.feature.LIVE_VIDEO";

    /**
     * Route feature: Remote audio playback.
     * <p>
     * A route that supports remote audio playback routing will allow an application to send
     * requests to play audio content remotely to supported destinations.
     *
     * @see #FEATURE_REMOTE_PLAYBACK
     * @see #FEATURE_REMOTE_VIDEO_PLAYBACK
     */
    public static final String FEATURE_REMOTE_AUDIO_PLAYBACK = "android.media.route.feature.REMOTE_AUDIO_PLAYBACK";

    /**
     * Route feature: Remote playback.
     * <p>
     * A route that supports remote playback routing will allow an application to send
     * requests to play content remotely to supported destinations.
     * A route may only support {@link #FEATURE_REMOTE_AUDIO_PLAYBACK audio playback} or
     * {@link #FEATURE_REMOTE_VIDEO_PLAYBACK video playback}.
     * </p><p>
     * Remote playback routes destinations operate independently of the local device.
     * When a remote playback route is selected, the application can control the content
     * playing on the destination using {@link MediaRouter2.RoutingController#getControlHints()}.
     * The application may also receive status updates from the route regarding remote playback.
     * </p><p>
     * Refer to the class documentation for details about remote playback routes.
     * </p>
     * @see #FEATURE_REMOTE_AUDIO_PLAYBACK
     * @see #FEATURE_REMOTE_VIDEO_PLAYBACK
     */
    public static final String FEATURE_REMOTE_PLAYBACK = "android.media.route.feature.REMOTE_PLAYBACK";

    /**
     * Route feature: Remote video playback.
     * <p>
     * A route that supports remote video playback routing will allow an application to send
     * requests to play video content remotely to supported destinations.
     *
     * @see #FEATURE_REMOTE_PLAYBACK
     * @see #FEATURE_REMOTE_AUDIO_PLAYBACK
     */
    public static final String FEATURE_REMOTE_VIDEO_PLAYBACK = "android.media.route.feature.REMOTE_VIDEO_PLAYBACK";

    /**
     * Playback information indicating the playback volume is fixed, i&#46;e&#46; it cannot be
     * controlled from this object. An example of fixed playback volume is a remote player,
     * playing over HDMI where the user prefers to control the volume on the HDMI sink, rather
     * than attenuate at the source.
     *
     * @see #getVolumeHandling()
     */
    public static final int PLAYBACK_VOLUME_FIXED = 0;

    /**
     * Playback information indicating the playback volume is variable and can be controlled
     * from this object.
     *
     * @see #getVolumeHandling()
     */
    public static final int PLAYBACK_VOLUME_VARIABLE = 1;

    /**
     * Indicates that the route is never suitable for media playback.
     */
    public static final int SUITABILITY_STATUS_NOT_SUITABLE_FOR_TRANSFER = 2;

    /**
     * Indicates the route is always suitable for media playback.
     */
    public static final int SUITABILITY_STATUS_SUITABLE_FOR_DEFAULT_TRANSFER = 0;

    /**
     * Indicates that the route is suitable for media playback only after explicit user selection.
     */
    public static final int SUITABILITY_STATUS_SUITABLE_FOR_MANUAL_TRANSFER = 1;

    /**
     * Indicates the route is using the auxiliary line-level connectors.
     *
     * @see #getType
     * @see AudioDeviceInfo#TYPE_AUX_LINE
     */
    public static final int TYPE_AUX_LINE = 19;

    /**
     * Indicates the route is a Bluetooth Low Energy (BLE) HEADSET.
     *
     * @see #getType
     * @see AudioDeviceInfo#TYPE_BLE_HEADSET
     */
    public static final int TYPE_BLE_HEADSET = 26;

    /**
     * Indicates the route is a bluetooth device, such as a bluetooth speaker or headphones.
     *
     * @see #getType
     * @see AudioDeviceInfo#TYPE_BLUETOOTH_A2DP
     */
    public static final int TYPE_BLUETOOTH_A2DP = 8;

    /**
     * Indicates the route is the speaker system (i.e. a mono speaker or stereo speakers) built into
     * the device.
     *
     * @see #getType
     * @see AudioDeviceInfo#TYPE_BUILTIN_SPEAKER
     */
    public static final int TYPE_BUILTIN_SPEAKER = 2;

    /**
     * Indicates the route is the audio device associated with a dock.
     *
     * @see #getType
     * @see AudioDeviceInfo#TYPE_DOCK
     */
    public static final int TYPE_DOCK = 13;

    /**
     * Indicates the route is a group of devices.
     *
     * @see #getType
     */
    public static final int TYPE_GROUP = 2000;

    /**
     * Indicates the route is an HDMI connection.
     *
     * @see #getType
     * @see AudioDeviceInfo#TYPE_HDMI
     */
    public static final int TYPE_HDMI = 9;

    /**
     * Indicates the route is an Audio Return Channel of an HDMI connection.
     *
     * @see #getType
     * @see AudioDeviceInfo#TYPE_HDMI_ARC
     */
    public static final int TYPE_HDMI_ARC = 10;

    /**
     * Indicates the route is an Enhanced Audio Return Channel of an HDMI connection.
     *
     * @see #getType
     * @see AudioDeviceInfo#TYPE_HDMI_EARC
     */
    public static final int TYPE_HDMI_EARC = 29;

    /**
     * Indicates the route is a hearing aid.
     *
     * @see #getType
     * @see AudioDeviceInfo#TYPE_HEARING_AID
     */
    public static final int TYPE_HEARING_AID = 23;

    /**
     * Indicates the route is an analog line-level connection.
     *
     * @see #getType
     * @see AudioDeviceInfo#TYPE_LINE_ANALOG
     */
    public static final int TYPE_LINE_ANALOG = 5;

    /**
     * Indicates the route is a digital line connection (for example S/PDIF).
     *
     * @see #getType
     * @see AudioDeviceInfo#TYPE_LINE_DIGITAL
     */
    public static final int TYPE_LINE_DIGITAL = 6;

    /**
     * Indicates the route is a speaker group supporting multichannel contents.
     *
     * <p>The speakers in the group are connected together using local network based protocols. The
     * speaker group requires additional input of the physical positions of each individual speaker
     * to provide a better experience on multichannel contents.
     *
     * @see #getType
     * @see AudioDeviceInfo#TYPE_MULTICHANNEL_GROUP
     */
    public static final int TYPE_MULTICHANNEL_SPEAKER_GROUP = 32;

    /**
     * Indicates the route is a remote Audio/Video Receiver (AVR).
     *
     * <p>A remote device uses a routing protocol managed by the application, as opposed to the
     * routing being done by the system.
     *
     * @see #getType
     */
    public static final int TYPE_REMOTE_AUDIO_VIDEO_RECEIVER = 1003;

    /**
     * Indicates the route is a remote car.
     *
     * <p>A remote device uses a routing protocol managed by the application, as opposed to the
     * routing being done by the system.
     *
     * @see #getType
     */
    public static final int TYPE_REMOTE_CAR = 1008;

    /**
     * Indicates the route is a remote computer.
     *
     * <p>A remote device uses a routing protocol managed by the application, as opposed to the
     * routing being done by the system.
     *
     * @see #getType
     */
    public static final int TYPE_REMOTE_COMPUTER = 1006;

    /**
     * Indicates the route is a remote gaming console.
     *
     * <p>A remote device uses a routing protocol managed by the application, as opposed to the
     * routing being done by the system.
     *
     * @see #getType
     */
    public static final int TYPE_REMOTE_GAME_CONSOLE = 1007;

    /**
     * Indicates the route is a remote smartphone.
     *
     * <p>A remote device uses a routing protocol managed by the application, as opposed to the
     * routing being done by the system.
     *
     * @see #getType
     */
    public static final int TYPE_REMOTE_SMARTPHONE = 1010;

    /**
     * Indicates the route is a remote smartwatch.
     *
     * <p>A remote device uses a routing protocol managed by the application, as opposed to the
     * routing being done by the system.
     *
     * @see #getType
     */
    public static final int TYPE_REMOTE_SMARTWATCH = 1009;

    /**
     * Indicates the route is a remote speaker.
     *
     * <p>A remote device uses a routing protocol managed by the application, as opposed to the
     * routing being done by the system.
     *
     * @see #getType
     */
    public static final int TYPE_REMOTE_SPEAKER = 1002;

    /**
     * Indicates the route is a remote tablet.
     *
     * <p>A remote device uses a routing protocol managed by the application, as opposed to the
     * routing being done by the system.
     *
     * @see #getType
     */
    public static final int TYPE_REMOTE_TABLET = 1004;

    /**
     * Indicates the route is a remote docked tablet.
     *
     * <p>A remote device uses a routing protocol managed by the application, as opposed to the
     * routing being done by the system.
     *
     * @see #getType
     */
    public static final int TYPE_REMOTE_TABLET_DOCKED = 1005;

    /**
     * Indicates the route is a remote TV.
     *
     * <p>A remote device uses a routing protocol managed by the application, as opposed to the
     * routing being done by the system.
     *
     * @see #getType
     */
    public static final int TYPE_REMOTE_TV = 1001;

    /**
     * Indicates the route's type is unknown or undefined.
     *
     * @see #getType
     */
    public static final int TYPE_UNKNOWN = 0;

    /**
     * Indicates the route is a USB audio device in accessory mode.
     *
     * @see #getType
     * @see AudioDeviceInfo#TYPE_USB_ACCESSORY
     */
    public static final int TYPE_USB_ACCESSORY = 12;

    /**
     * Indicates the route is a USB audio device.
     *
     * @see #getType
     * @see AudioDeviceInfo#TYPE_USB_DEVICE
     */
    public static final int TYPE_USB_DEVICE = 11;

    /**
     * Indicates the route is a USB audio headset.
     *
     * @see #getType
     * @see AudioDeviceInfo#TYPE_USB_HEADSET
     */
    public static final int TYPE_USB_HEADSET = 22;

    /**
     * Indicates the route is a pair of wired headphones.
     *
     * @see #getType
     * @see AudioDeviceInfo#TYPE_WIRED_HEADPHONES
     */
    public static final int TYPE_WIRED_HEADPHONES = 4;

    /**
     * Indicates the route is a headset, which is the combination of a headphones and a microphone.
     *
     * @see #getType
     * @see AudioDeviceInfo#TYPE_WIRED_HEADSET
     */
    public static final int TYPE_WIRED_HEADSET = 3;

    private MediaRoute2Info() {
    }
}
