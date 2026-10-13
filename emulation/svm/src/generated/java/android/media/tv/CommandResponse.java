package android.media.tv;

import android.os.Parcelable;

/**
 * A response for command from broadcast signal.
 */
public final class CommandResponse implements Parcelable {
    public static final String RESPONSE_TYPE_JSON = "json";

    public static final String RESPONSE_TYPE_XML = "xml";

    private CommandResponse() {
    }
}
