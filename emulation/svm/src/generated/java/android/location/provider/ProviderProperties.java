package android.location.provider;

import android.os.Parcelable;

/**
 * Location provider properties.
 */
public final class ProviderProperties implements Parcelable {
    /**
     * A constant indicating a coarser location accuracy.
     */
    public static final int ACCURACY_COARSE = 2;

    /**
     * A constant indicating a finer location accuracy.
     */
    public static final int ACCURACY_FINE = 1;

    /**
     * A constant indicating high power usage.
     */
    public static final int POWER_USAGE_HIGH = 3;

    /**
     * A constant indicating low power usage.
     */
    public static final int POWER_USAGE_LOW = 1;

    /**
     * A constant indicating a medium power usage.
     */
    public static final int POWER_USAGE_MEDIUM = 2;

    private ProviderProperties() {
    }
}
