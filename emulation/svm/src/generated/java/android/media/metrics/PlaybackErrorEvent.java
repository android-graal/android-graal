package android.media.metrics;

import android.os.Parcelable;

/**
 * Playback error event.
 */
public final class PlaybackErrorEvent implements Parcelable {
    /**
     * Error code for an AudioTrack initialization failure
     */
    public static final int ERROR_AUDIO_TRACK_INIT_FAILED = 17;

    /**
     * Error code for other AudioTrack errors
     */
    public static final int ERROR_AUDIO_TRACK_OTHER = 19;

    /**
     * Error code for an AudioTrack write operation failure
     */
    public static final int ERROR_AUDIO_TRACK_WRITE_FAILED = 18;

    /**
     * Error code for a decoder initialization failure
     */
    public static final int ERROR_DECODER_INIT_FAILED = 13;

    /**
     * Error code for a failure while trying to decode media samples
     */
    public static final int ERROR_DECODING_FAILED = 14;

    /**
     * Error code for trying to decode content whose format exceeds the capabilities of the device.
     */
    public static final int ERROR_DECODING_FORMAT_EXCEEDS_CAPABILITIES = 15;

    /**
     * Error code for trying to decode content whose format is not supported
     */
    public static final int ERROR_DECODING_FORMAT_UNSUPPORTED = 35;

    /**
     * Error code for other decoding errors
     */
    public static final int ERROR_DECODING_OTHER = 16;

    /**
     * Error code for attempting to play incompatible DRM-protected content
     */
    public static final int ERROR_DRM_CONTENT_ERROR = 28;

    /**
     * Error code for the device having revoked DRM privileges
     */
    public static final int ERROR_DRM_DEVICE_REVOKED = 29;

    /**
     * Error code an operation being disallowed by a license policy
     */
    public static final int ERROR_DRM_DISALLOWED_OPERATION = 26;

    /**
     * Error code for a failure while trying to obtain a license
     */
    public static final int ERROR_DRM_LICENSE_ACQUISITION_FAILED = 25;

    /**
     * Error code for other DRM errors
     */
    public static final int ERROR_DRM_OTHER = 30;

    /**
     * Error code for a failure while provisioning the device
     */
    public static final int ERROR_DRM_PROVISIONING_FAILED = 24;

    /**
     * Error code for a chosen DRM protection scheme not being supported by the device
     */
    public static final int ERROR_DRM_SCHEME_UNSUPPORTED = 23;

    /**
     * Error code for an error in the DRM system
     */
    public static final int ERROR_DRM_SYSTEM_ERROR = 27;

    /**
     * Error code for an HTTP server returning an unexpected HTTP response status code
     */
    public static final int ERROR_IO_BAD_HTTP_STATUS = 5;

    /**
     * Error code for an existing network connection being unexpectedly closed
     */
    public static final int ERROR_IO_CONNECTION_CLOSED = 8;

    /**
     * Error code for a network timeout, meaning the server is taking too long to fulfill
     * a request
     */
    public static final int ERROR_IO_CONNECTION_TIMEOUT = 7;

    /**
     * Error code for failing to resolve a hostname
     */
    public static final int ERROR_IO_DNS_FAILED = 6;

    /**
     * Error code for a non-existent file
     */
    public static final int ERROR_IO_FILE_NOT_FOUND = 31;

    /**
     * Error code for a failure while establishing a network connection
     */
    public static final int ERROR_IO_NETWORK_CONNECTION_FAILED = 4;

    /**
     * Error code for lack of network connectivity while trying to access a network resource
     */
    public static final int ERROR_IO_NETWORK_UNAVAILABLE = 3;

    /**
     * Error code for lack of permission to perform an IO operation, for example, lack of permission
     * to access internet or external storage.
     */
    public static final int ERROR_IO_NO_PERMISSION = 32;

    /**
     * Error code for other Input/Output errors
     */
    public static final int ERROR_IO_OTHER = 9;

    /**
     * Error code for other errors
     */
    public static final int ERROR_OTHER = 1;

    /**
     * Error code for a parsing error associated to a media container format bitstream
     */
    public static final int ERROR_PARSING_CONTAINER_MALFORMED = 11;

    /**
     * Error code for attempting to extract a file with an unsupported media container format, or an
     * unsupported media container feature
     */
    public static final int ERROR_PARSING_CONTAINER_UNSUPPORTED = 34;

    /**
     * Error code for a parsing error associated to a media manifest
     */
    public static final int ERROR_PARSING_MANIFEST_MALFORMED = 10;

    /**
     * Error code for an unsupported feature in a media manifest
     */
    public static final int ERROR_PARSING_MANIFEST_UNSUPPORTED = 33;

    /**
     * Error code for other media parsing errors
     */
    public static final int ERROR_PARSING_OTHER = 12;

    /**
     * Error code for the loading position falling behind the sliding window of available live
     * content.
     */
    public static final int ERROR_PLAYER_BEHIND_LIVE_WINDOW = 21;

    /**
     * Error code for other player errors
     */
    public static final int ERROR_PLAYER_OTHER = 22;

    /**
     * Error code for an unidentified error in a remote controller or player
     */
    public static final int ERROR_PLAYER_REMOTE = 20;

    /**
     * Error code for runtime errors
     */
    public static final int ERROR_RUNTIME = 2;

    /**
     * Unknown error code.
     */
    public static final int ERROR_UNKNOWN = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private PlaybackErrorEvent() {
    }
}
