package android.media.quality;

import android.os.Parcelable;

/**
 * Profile for sound quality.
 */
public final class SoundProfile implements Parcelable {
    /**
     * Error code for creating a profile with existing profile type and name.
     *
     * @see #getProfileType()
     * @see #getName()
     */
    public static final int ERROR_DUPLICATE = 2;

    /**
     * Error code for invalid argument.
     */
    public static final int ERROR_INVALID_ARGUMENT = 3;

    /**
     * Error code for the case when an operation requires an allowlist but the caller is not in the
     * list.
     *
     * @see MediaQualityManager#getSoundProfileAllowList()
     */
    public static final int ERROR_NOT_ALLOWLISTED = 4;

    /**
     * Error code for missing necessary permission to handle the profiles.
     */
    public static final int ERROR_NO_PERMISSION = 1;

    /**
     * Error code for unknown errors.
     */
    public static final int ERROR_UNKNOWN = 0;

    /**
     * Application profile type.
     *
     * <p>A profile of application type is managed by the package returned by
     * {@link #getPackageName()}.
     */
    public static final int TYPE_APPLICATION = 2;

    /**
     * System profile type.
     *
     * <p>A profile of system type is managed by the system, and readable to the package returned by
     * {@link #getPackageName()}.
     */
    public static final int TYPE_SYSTEM = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private SoundProfile() {
    }
}
