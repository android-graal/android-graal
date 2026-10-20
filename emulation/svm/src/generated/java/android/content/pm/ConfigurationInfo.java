package android.content.pm;

import android.os.Parcelable;

/**
 * Information you can retrieve about hardware configuration preferences
 * declared by an application. This corresponds to information collected from the
 * AndroidManifest.xml's &lt;uses-configuration&gt; and &lt;uses-feature&gt; tags.
 */
public final class ConfigurationInfo implements Parcelable {
    /**
     * Default value for {@link #reqGlEsVersion};
     */
    public static final int GL_ES_VERSION_UNDEFINED = 0;

    /**
     * Value for {@link #reqInputFeatures}: if set, indicates that the application
     * requires a five way navigation device
     */
    public static final int INPUT_FEATURE_FIVE_WAY_NAV = 2;

    /**
     * Value for {@link #reqInputFeatures}: if set, indicates that the application
     * requires a hard keyboard
     */
    public static final int INPUT_FEATURE_HARD_KEYBOARD = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private ConfigurationInfo() {
    }
}
