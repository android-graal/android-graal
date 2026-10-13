package android.media.tv;

import android.os.Parcelable;

/**
 * A request for the information retrieved from broadcast signal.
 */
public final class BroadcastInfoRequest implements Parcelable {
    /**
     * Request option: auto update.
     * <p>With this option, a response is sent only when broadcast information is detected for the
     * first time, new values are detected.
     */
    public static final int REQUEST_OPTION_AUTO_UPDATE = 1;

    /**
     * Request option: one-shot
     * <p> With this option, only one response will be given per request.
     */
    public static final int REQUEST_OPTION_ONESHOT = 3;

    /**
     * Request option: one-way
     * <p> With this option, no response is expected after sending the request.
     */
    public static final int REQUEST_OPTION_ONEWAY = 2;

    /**
     * Request option: repeat.
     * <p>With this option, a response is sent when related broadcast information is detected,
     * even if the same information has been sent previously.
     */
    public static final int REQUEST_OPTION_REPEAT = 0;

    private BroadcastInfoRequest() {
    }
}
