package android.hardware.display;

import android.os.Parcelable;

/**
 * Product-specific information about the display or the directly connected device on the
 * display chain. For example, if the display is transitively connected, this field may contain
 * product information about the intermediate device.
 */
public final class DeviceProductInfo implements Parcelable {
    /**
     * The display sink is built-in to the device
     */
    public static final int CONNECTION_TO_SINK_BUILT_IN = 1;

    /**
     * The device is directly connected to the display sink.
     */
    public static final int CONNECTION_TO_SINK_DIRECT = 2;

    /**
     * The device is transitively connected to the display sink.
     */
    public static final int CONNECTION_TO_SINK_TRANSITIVE = 3;

    /**
     * The device connection to the display sink is unknown.
     */
    public static final int CONNECTION_TO_SINK_UNKNOWN = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private DeviceProductInfo() {
    }
}
