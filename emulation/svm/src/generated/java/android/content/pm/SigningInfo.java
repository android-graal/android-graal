package android.content.pm;

import android.os.Parcelable;

/**
 * Information pertaining to the signing certificates used to sign a package.
 */
public final class SigningInfo implements Parcelable {
    /**
     * JAR signing (v1 scheme).
     * See https://source.android.com/docs/security/features/apksigning#v1.
     */
    public static final int VERSION_JAR = 1;

    /**
     * APK signature scheme v2.
     * See https://source.android.com/docs/security/features/apksigning/v2.
     */
    public static final int VERSION_SIGNING_BLOCK_V2 = 2;

    /**
     * APK signature scheme v3.
     * See https://source.android.com/docs/security/features/apksigning/v3.
     */
    public static final int VERSION_SIGNING_BLOCK_V3 = 3;

    /**
     * APK signature scheme v4.
     * See https://source.android.com/docs/security/features/apksigning/v4.
     */
    public static final int VERSION_SIGNING_BLOCK_V4 = 4;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private SigningInfo() {
    }
}
