package android.ranging.uwb;

import android.os.Parcelable;

/**
 * A class representing a UWB address
 */
public final class UwbAddress implements Parcelable {
    public static final int EXTENDED_ADDRESS_BYTE_LENGTH = 8;

    public static final int SHORT_ADDRESS_BYTE_LENGTH = 2;

    private UwbAddress() {
    }
}
