package android.location;

/**
 * This class provides access to the system location services. These services allow applications to
 * obtain periodic updates of the device's geographical location, or to be notified when the device
 * enters the proximity of a given geographical location.
 *
 * <p class="note">Unless otherwise noted, all Location API methods require the
 * {@link android.Manifest.permission#ACCESS_COARSE_LOCATION} or
 * {@link android.Manifest.permission#ACCESS_FINE_LOCATION} permissions. If your application only
 * has the coarse permission then providers will still return location results, but the exact
 * location will be obfuscated to a coarse level of accuracy.
 */
public final class LocationManager {
    /**
     * Broadcast intent action when GNSS capabilities change. This is most common at boot time as
     * GNSS capabilities are queried from the chipset. Includes an intent extra,
     * {@link #EXTRA_GNSS_CAPABILITIES}, with the new {@link GnssCapabilities}.
     *
     * @see #EXTRA_GNSS_CAPABILITIES
     * @see #getGnssCapabilities()
     */
    public static final String ACTION_GNSS_CAPABILITIES_CHANGED = "android.location.action.GNSS_CAPABILITIES_CHANGED";

    /**
     * Intent extra included with {@link #ACTION_GNSS_CAPABILITIES_CHANGED} broadcasts, containing
     * the new {@link GnssCapabilities}.
     *
     * @see #ACTION_GNSS_CAPABILITIES_CHANGED
     */
    public static final String EXTRA_GNSS_CAPABILITIES = "android.location.extra.GNSS_CAPABILITIES";

    /**
     * Intent extra included with {@link #MODE_CHANGED_ACTION} broadcasts, containing the boolean
     * enabled state of location.
     *
     * @see #MODE_CHANGED_ACTION
     */
    public static final String EXTRA_LOCATION_ENABLED = "android.location.extra.LOCATION_ENABLED";

    /**
     * Intent extra included with {@link #PROVIDERS_CHANGED_ACTION} broadcasts, containing the
     * boolean enabled state of the location provider that has changed.
     *
     * @see #PROVIDERS_CHANGED_ACTION
     * @see #EXTRA_PROVIDER_NAME
     */
    public static final String EXTRA_PROVIDER_ENABLED = "android.location.extra.PROVIDER_ENABLED";

    /**
     * Intent extra included with {@link #PROVIDERS_CHANGED_ACTION} broadcasts, containing the name
     * of the location provider that has changed.
     *
     * @see #PROVIDERS_CHANGED_ACTION
     * @see #EXTRA_PROVIDER_ENABLED
     */
    public static final String EXTRA_PROVIDER_NAME = "android.location.extra.PROVIDER_NAME";

    /**
     * Standard name of the fused location provider.
     *
     * <p>If present, this provider may combine inputs from several other location providers to
     * provide the best possible location fix. It is implicitly used for all requestLocationUpdates
     * APIs that involve a {@link Criteria}.
     */
    public static final String FUSED_PROVIDER = "fused";

    /**
     * Standard name of the GNSS location provider.
     *
     * <p>If present, this provider determines location using GNSS satellites. The responsiveness
     * and accuracy of location fixes may depend on GNSS signal conditions.
     *
     * <p>Locations returned from this provider are with respect to the primary GNSS antenna
     * position within the device. {@link #getGnssAntennaInfos()} may be used to determine the GNSS
     * antenna position with respect to the Android Coordinate System, and convert between them if
     * necessary. This is generally only necessary for high accuracy applications.
     *
     * <p>The extras Bundle for locations derived by this location provider may contain the
     * following key/value pairs:
     * <ul>
     * <li> satellites - the number of satellites used to derive the fix
     * </ul>
     */
    public static final String GPS_PROVIDER = "gps";

    /**
     * Key used for an extra holding an integer request code when location flush completion is sent
     * using a PendingIntent.
     *
     * @see #requestFlush(String, PendingIntent, int)
     */
    public static final String KEY_FLUSH_COMPLETE = "flushComplete";

    /**
     * Key used for an extra holding a array of {@link Location}s when a location change is sent
     * using a PendingIntent. This key will only be present if the location change includes
     * multiple (ie, batched) locations, otherwise only {@link #KEY_LOCATION_CHANGED} will be
     * present. Use {@link Intent#getParcelableArrayExtra(String)} to retrieve the locations.
     *
     * <p>The array of locations will never be empty, and will ordered from earliest location to
     * latest location, the same as with {@link LocationListener#onLocationChanged(List)}.
     *
     * @see #requestLocationUpdates(String, LocationRequest, PendingIntent)
     */
    public static final String KEY_LOCATIONS = "locations";

    /**
     * Key used for an extra holding a {@link Location} value when a location change is sent using
     * a PendingIntent. If the location change includes a list of batched locations via
     * {@link #KEY_LOCATIONS} then this key will still be present, and will hold the last location
     * in the batch. Use {@link Intent#getParcelableExtra(String)} to retrieve the location.
     *
     * @see #requestLocationUpdates(String, LocationRequest, PendingIntent)
     */
    public static final String KEY_LOCATION_CHANGED = "location";

    /**
     * Key used for an extra holding a boolean enabled/disabled status value when a provider
     * enabled/disabled event is broadcast using a PendingIntent.
     *
     * @see #requestLocationUpdates(String, LocationRequest, PendingIntent)
     */
    public static final String KEY_PROVIDER_ENABLED = "providerEnabled";

    /**
     * Key used for the Bundle extra holding a boolean indicating whether
     * a proximity alert is entering (true) or exiting (false)..
     */
    public static final String KEY_PROXIMITY_ENTERING = "entering";

    /**
     * This key is no longer in use.
     *
     * <p>Key used for a Bundle extra holding an Integer status value when a status change is
     * broadcast using a PendingIntent.
     *
     * @deprecated Status changes are deprecated and no longer broadcast from Android Q onwards.
     */
    @Deprecated
    public static final String KEY_STATUS_CHANGED = "status";

    /**
     * Broadcast intent action when the device location enabled state changes. From Android R and
     * above, will include a boolean intent extra, {@link #EXTRA_LOCATION_ENABLED}, with the enabled
     * state of location.
     *
     * @see #EXTRA_LOCATION_ENABLED
     * @see #isLocationEnabled()
     */
    public static final String MODE_CHANGED_ACTION = "android.location.MODE_CHANGED";

    /**
     * Standard name of the network location provider.
     *
     * <p>If present, this provider determines location based on nearby of cell tower and WiFi
     * access points. Operation of this provider may require a data connection.
     */
    public static final String NETWORK_PROVIDER = "network";

    /**
     * A special location provider for receiving locations without actively initiating a location
     * fix. This location provider is always present.
     *
     * <p>This provider can be used to passively receive location updates when other applications or
     * services request them without actually requesting the locations yourself. This provider will
     * only return locations generated by other providers.
     */
    public static final String PASSIVE_PROVIDER = "passive";

    /**
     * Broadcast intent action when the set of enabled location providers changes. To check the
     * status of a provider, use {@link #isProviderEnabled(String)}. From Android Q and above, will
     * include a string intent extra, {@link #EXTRA_PROVIDER_NAME}, with the name of the provider
     * whose state has changed. From Android R and above, will include a boolean intent extra,
     * {@link #EXTRA_PROVIDER_ENABLED}, with the enabled state of the provider.
     *
     * @see #EXTRA_PROVIDER_NAME
     * @see #EXTRA_PROVIDER_ENABLED
     * @see #isProviderEnabled(String)
     */
    public static final String PROVIDERS_CHANGED_ACTION = "android.location.PROVIDERS_CHANGED";

    private LocationManager() {
    }
}
