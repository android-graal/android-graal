package android.service.settings.preferences;

import android.os.Parcelable;

/**
 * Data object representation of a Settings Preference definition and state.
 */
public final class SettingsPreferenceMetadata implements Parcelable {
    /**
     * Indicates preference is write-sensitive.
     * <p>This preference cannot be changed through this API; instead a deeplink to the Preference's
     * page should be used instead, accessible via {@link #getLaunchIntent}.
     */
    public static final int DEEPLINK_ONLY = 2;

    /**
     * Indicates preference is mildly write-sensitive.
     * <p>In addition to necessary permissions, after writing its value the user should be
     * given the option to revert back.
     */
    public static final int EXPECT_POST_CONFIRMATION = 1;

    /**
     * Indicates preference is highly write-sensitivity and carries significant user-risk.
     * <p>This Preference cannot be changed through this API and no direct deeplink is available.
     * Other Metadata is still available.
     */
    public static final int NO_DIRECT_ACCESS = 3;

    /**
     * Indicates preference is not write-sensitive.
     * <p>Its value is writable without explicit consent, assuming all necessary permissions are
     * granted.
     */
    public static final int NO_SENSITIVITY = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private SettingsPreferenceMetadata() {
    }
}
