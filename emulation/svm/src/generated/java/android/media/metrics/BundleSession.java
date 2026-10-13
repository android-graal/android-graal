package android.media.metrics;

/**
 * An instances of this class represents a session with data stored in a bundle.
 */
public final class BundleSession {
    /**
     * A key describing the statsd atom into which to place this bundle's other contents.
     * The associated value is an integer.
     *
     * @see #reportBundleMetrics
     */
    public static final String KEY_STATSD_ATOM = "bundlesession-statsd-atom";

    private BundleSession() {
    }
}
