package android.media.metrics;

import android.os.Parcelable;

/**
 * This class is used to store playback data.
 */
public final class PlaybackMetrics implements Parcelable {
    /**
     * Advertisement contents.
     */
    public static final int CONTENT_TYPE_AD = 2;

    /**
     * Main contents.
     */
    public static final int CONTENT_TYPE_MAIN = 1;

    /**
     * Other contents.
     */
    public static final int CONTENT_TYPE_OTHER = 3;

    /**
     * Unknown content type.
     */
    public static final int CONTENT_TYPE_UNKNOWN = 0;

    /**
     * Clear key DRM type.
     */
    public static final int DRM_TYPE_CLEARKEY = 6;

    /**
     * DRM is not used.
     */
    public static final int DRM_TYPE_NONE = 0;

    /**
     * Other DRM type.
     */
    public static final int DRM_TYPE_OTHER = 1;

    /**
     * Play ready DRM type.
     */
    public static final int DRM_TYPE_PLAY_READY = 2;

    /**
     * Widevine L1 DRM type.
     */
    public static final int DRM_TYPE_WIDEVINE_L1 = 3;

    /**
     * Widevine L3 DRM type.
     */
    public static final int DRM_TYPE_WIDEVINE_L3 = 4;

    /**
     * Widevine L3 fallback DRM type.
     */
    public static final int DRM_TYPE_WV_L3_FALLBACK = 5;

    /**
     * Live playback type.
     */
    public static final int PLAYBACK_TYPE_LIVE = 2;

    /**
     * Other playback type.
     */
    public static final int PLAYBACK_TYPE_OTHER = 3;

    /**
     * Unknown playback type.
     */
    public static final int PLAYBACK_TYPE_UNKNOWN = 0;

    /**
     * VOD (Video on Demand) playback type.
     */
    public static final int PLAYBACK_TYPE_VOD = 1;

    /**
     * Stream from device.
     */
    public static final int STREAM_SOURCE_DEVICE = 2;

    /**
     * Stream from more than one sources.
     */
    public static final int STREAM_SOURCE_MIXED = 3;

    /**
     * Stream from network.
     */
    public static final int STREAM_SOURCE_NETWORK = 1;

    /**
     * Unknown stream source.
     */
    public static final int STREAM_SOURCE_UNKNOWN = 0;

    /**
     * DASH (Dynamic Adaptive Streaming over HTTP) stream type.
     */
    public static final int STREAM_TYPE_DASH = 3;

    /**
     * HLS (HTTP Live Streaming) stream type.
     */
    public static final int STREAM_TYPE_HLS = 4;

    /**
     * Other stream type.
     */
    public static final int STREAM_TYPE_OTHER = 1;

    /**
     * Progressive stream type.
     */
    public static final int STREAM_TYPE_PROGRESSIVE = 2;

    /**
     * SS (HTTP Smooth Streaming) stream type.
     */
    public static final int STREAM_TYPE_SS = 5;

    /**
     * Unknown stream type.
     */
    public static final int STREAM_TYPE_UNKNOWN = 0;

    private PlaybackMetrics() {
    }
}
