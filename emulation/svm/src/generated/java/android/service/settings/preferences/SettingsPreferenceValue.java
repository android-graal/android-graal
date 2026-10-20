package android.service.settings.preferences;

import android.os.Parcelable;

/**
 * This objects represents a value that can be used for a particular settings preference.
 * <p>The data type for the value will correspond to {@link #getType}. For possible types, see
 * constants below, such as {@link #TYPE_BOOLEAN} and {@link #TYPE_STRING}.
 * Depending on the type, the corresponding getter will contain its value.
 * <p>See documentation on the constants for which getter method should be used.
 */
public final class SettingsPreferenceValue implements Parcelable {
    /**
     * Value is of type boolean. Access via {@link #getBooleanValue}.
     */
    public static final int TYPE_BOOLEAN = 0;

    /**
     * Value is of type double. Access via {@link #getDoubleValue()}.
     */
    public static final int TYPE_DOUBLE = 2;

    /**
     * Value is of type int. Access via {@link #getIntValue}.
     */
    public static final int TYPE_INT = 4;

    /**
     * Value is of type long. Access via {@link #getLongValue()}.
     */
    public static final int TYPE_LONG = 1;

    /**
     * Value is of type string. Access via {@link #getStringValue}.
     */
    public static final int TYPE_STRING = 3;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private SettingsPreferenceValue() {
    }
}
