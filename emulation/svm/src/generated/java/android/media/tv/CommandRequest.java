package android.media.tv;

import android.os.Parcelable;

/**
 * A request for command from broadcast signal.
 */
public final class CommandRequest implements Parcelable {
    public static final String ARGUMENT_TYPE_JSON = "json";

    public static final String ARGUMENT_TYPE_XML = "xml";

    private CommandRequest() {
    }
}
