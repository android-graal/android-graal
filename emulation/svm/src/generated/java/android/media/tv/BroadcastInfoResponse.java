package android.media.tv;

import android.os.Parcelable;

/**
 * A response of {@link BroadcastInfoRequest} for information retrieved from broadcast signal.
 */
public final class BroadcastInfoResponse implements Parcelable {
    /**
     * Response result: cancel. This means the request has been cancelled.
     */
    public static final int RESPONSE_RESULT_CANCEL = 3;

    /**
     * Response result: error. This means the request can not be set up successfully.
     */
    public static final int RESPONSE_RESULT_ERROR = 1;

    /**
     * Response result: OK. This means the request is set up successfully and the related responses
     * are normal responses.
     */
    public static final int RESPONSE_RESULT_OK = 2;

    private BroadcastInfoResponse() {
    }
}
