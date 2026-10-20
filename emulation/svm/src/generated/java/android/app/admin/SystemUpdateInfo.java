package android.app.admin;

import android.os.Parcelable;

/**
 * A class containing information about a pending system update.
 */
public final class SystemUpdateInfo implements Parcelable {
    /**
     * Represents the system update is not a security patch.
     */
    public static final int SECURITY_PATCH_STATE_FALSE = 1;

    /**
     * Represents the system update is a security patch.
     */
    public static final int SECURITY_PATCH_STATE_TRUE = 2;

    /**
     * Represents it is unknown whether the system update is a security patch.
     */
    public static final int SECURITY_PATCH_STATE_UNKNOWN = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private SystemUpdateInfo() {
    }
}
