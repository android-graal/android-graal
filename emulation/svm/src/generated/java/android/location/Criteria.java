package android.location;

import android.os.Parcelable;

/**
 * A class indicating the application criteria for selecting a location provider. Providers may be
 * ordered according to accuracy, power usage, ability to report altitude, speed, bearing, and
 * monetary cost.
 *
 * @deprecated Criteria based APIs are deprecated because they cannot fully capture the complexity
 *             of location providers, and encourage bad development practices. Prefer to explicit
 *             select the location provider of interest ({@link LocationManager#FUSED_PROVIDER},
 *             {@link LocationManager#GPS_PROVIDER, etc}), rather than relying on Criteria.
 */
@Deprecated
public final class Criteria implements Parcelable {
    /**
     * A constant indicating an approximate accuracy requirement
     */
    @Deprecated
    public static final int ACCURACY_COARSE = 2;

    /**
     * A constant indicating a finer location accuracy requirement
     */
    @Deprecated
    public static final int ACCURACY_FINE = 1;

    /**
     * a constant indicating a high accuracy requirement
     * - may be used for horizontal, altitude, speed or bearing accuracy.
     * For horizontal and vertical position this corresponds roughly to
     * an accuracy of less than 100 meters.
     */
    @Deprecated
    public static final int ACCURACY_HIGH = 3;

    /**
     * A constant indicating a low location accuracy requirement
     * - may be used for horizontal, altitude, speed or bearing accuracy.
     * For horizontal and vertical position this corresponds roughly to
     * an accuracy of greater than 500 meters.
     */
    @Deprecated
    public static final int ACCURACY_LOW = 1;

    /**
     * A constant indicating a medium accuracy requirement
     * - currently used only for horizontal accuracy.
     * For horizontal position this corresponds roughly to to an accuracy
     * of between 100 and 500 meters.
     */
    @Deprecated
    public static final int ACCURACY_MEDIUM = 2;

    /**
     * A constant indicating that the application does not choose to
     * place requirement on a particular feature.
     */
    @Deprecated
    public static final int NO_REQUIREMENT = 0;

    /**
     * A constant indicating a high power requirement.
     */
    @Deprecated
    public static final int POWER_HIGH = 3;

    /**
     * A constant indicating a low power requirement.
     */
    @Deprecated
    public static final int POWER_LOW = 1;

    /**
     * A constant indicating a medium power requirement.
     */
    @Deprecated
    public static final int POWER_MEDIUM = 2;

    private Criteria() {
    }
}
