package android.location;

/**
 * Information about the properties of a location provider.
 *
 * @deprecated This class is incapable of representing unknown provider properties and may return
 * incorrect results on the rare occasion when a provider's properties are unknown. Prefer using
 * {@link LocationManager#getProviderProperties(String)} to retrieve {@link ProviderProperties}
 * instead.
 */
@Deprecated
public final class LocationProvider {
    /**
     * @deprecated Location provider statuses are no longer supported.
     */
    @Deprecated
    public static final int AVAILABLE = 2;

    /**
     * @deprecated Location provider statuses are no longer supported.
     */
    @Deprecated
    public static final int OUT_OF_SERVICE = 0;

    /**
     * @deprecated Location provider statuses are no longer supported.
     */
    @Deprecated
    public static final int TEMPORARILY_UNAVAILABLE = 1;

    private LocationProvider() {
    }
}
