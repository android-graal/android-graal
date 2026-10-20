package android.content.pm;

import android.os.Parcelable;

/**
 * Definition of a single optional hardware or software feature of an Android
 * device.
 * <p>
 * This object is used to represent both features supported by a device and
 * features requested by an app. Apps can request that certain features be
 * available as a prerequisite to being installed through the
 * {@code uses-feature} tag in their manifests.
 * <p>
 * Starting in {@link android.os.Build.VERSION_CODES#N}, features can have a
 * version, which must always be backwards compatible. That is, a device
 * claiming to support version 3 of a specific feature must support apps
 * requesting version 1 of that feature.
 */
public final class FeatureInfo implements Parcelable {
    /**
     * Set on {@link #flags} if this feature has been required by the application.
     */
    public static final int FLAG_REQUIRED = 1;

    /**
     * Default value for {@link #reqGlEsVersion};
     */
    public static final int GL_ES_VERSION_UNDEFINED = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private FeatureInfo() {
    }
}
