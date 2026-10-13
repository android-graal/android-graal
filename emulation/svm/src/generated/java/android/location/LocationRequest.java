package android.location;

import android.os.Parcelable;

/**
 * An encapsulation of various parameters for requesting location via {@link LocationManager}.
 */
public final class LocationRequest implements Parcelable {
    /**
     * Represents a passive only request. Such a request will not trigger any active locations or
     * power usage itself, but may receive locations generated in response to other requests.
     *
     * @see LocationRequest#getIntervalMillis()
     */
    public static final long PASSIVE_INTERVAL = 9223372036854775807L;

    /**
     * A quality constant indicating a location provider may choose to satisfy this request by
     * equally balancing power and accuracy constraints. Each location provider may interpret this
     * field differently, but location providers will generally use their default behavior when this
     * flag is present.
     */
    public static final int QUALITY_BALANCED_POWER_ACCURACY = 102;

    /**
     * A quality constant indicating a location provider may choose to satisfy this request by
     * providing very accurate locations at the expense of potentially increased power usage. Each
     * location provider may interpret this field differently, but as an example, the network
     * provider may choose to return only wifi based locations rather than cell based locations in
     * order to have greater accuracy when this flag is present.
     */
    public static final int QUALITY_HIGH_ACCURACY = 100;

    /**
     * A quality constant indicating a location provider may choose to satisfy this request by
     * providing less accurate locations in order to save power. Each location provider may
     * interpret this field differently, but as an example, the network provider may choose to
     * return cell based locations rather than wifi based locations in order to save power when this
     * flag is present.
     */
    public static final int QUALITY_LOW_POWER = 104;

    private LocationRequest() {
    }
}
