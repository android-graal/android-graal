package android.media.tv;

import android.os.Parcelable;

/**
 * This class is used to specify meta information of a TV input.
 */
public final class TvInputInfo implements Parcelable {
    /**
     * Used as a String extra field in setup intents created by {@link #createSetupIntent()} to
     * supply the ID of a specific TV input to set up.
     */
    public static final String EXTRA_INPUT_ID = "android.media.tv.extra.INPUT_ID";

    /**
     * TV input type: the TV input service represents a component port.
     */
    public static final int TYPE_COMPONENT = 1004;

    /**
     * TV input type: the TV input service represents a composite port.
     */
    public static final int TYPE_COMPOSITE = 1001;

    /**
     * TV input type: the TV input service represents a display port.
     */
    public static final int TYPE_DISPLAY_PORT = 1008;

    /**
     * TV input type: the TV input service represents a DVI port.
     */
    public static final int TYPE_DVI = 1006;

    /**
     * TV input type: the TV input service is HDMI. (e.g. HDMI 1)
     */
    public static final int TYPE_HDMI = 1007;

    /**
     * TV input type: a generic hardware TV input type.
     */
    public static final int TYPE_OTHER = 1000;

    /**
     * TV input type: the TV input service represents a SCART port.
     */
    public static final int TYPE_SCART = 1003;

    /**
     * TV input type: the TV input service represents a SVIDEO port.
     */
    public static final int TYPE_SVIDEO = 1002;

    /**
     * TV input type: the TV input service is a tuner which provides channels.
     */
    public static final int TYPE_TUNER = 0;

    /**
     * TV input type: the TV input service represents a VGA port.
     */
    public static final int TYPE_VGA = 1005;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private TvInputInfo() {
    }
}
