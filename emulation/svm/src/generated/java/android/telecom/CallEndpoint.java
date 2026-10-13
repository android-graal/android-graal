package android.telecom;

import android.os.Parcelable;

/**
 * Encapsulates the endpoint where call media can flow
 */
public final class CallEndpoint implements Parcelable {
    /**
     * Indicates that the type of endpoint through which call media flows is a Bluetooth.
     */
    public static final int TYPE_BLUETOOTH = 2;

    /**
     * Indicates that the type of endpoint through which call media flows is an earpiece.
     */
    public static final int TYPE_EARPIECE = 1;

    /**
     * Indicates that the type of endpoint through which call media flows is a speakerphone.
     */
    public static final int TYPE_SPEAKER = 4;

    /**
     * Indicates that the type of endpoint through which call media flows is an external.
     */
    public static final int TYPE_STREAMING = 5;

    /**
     * Indicates that the type of endpoint through which call media flows is unknown type.
     */
    public static final int TYPE_UNKNOWN = -1;

    /**
     * Indicates that the type of endpoint through which call media flows is a wired headset.
     */
    public static final int TYPE_WIRED_HEADSET = 3;

    private CallEndpoint() {
    }
}
