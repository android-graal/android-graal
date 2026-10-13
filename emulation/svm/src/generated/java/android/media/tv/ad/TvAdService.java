package android.media.tv.ad;

/**
 * The TvAdService class represents a TV client-side advertisement service.
 */
public final class TvAdService {
    /**
     * This is the interface name that a service implementing a TV AD service should
     * say that it supports -- that is, this is the action it uses for its intent filter. To be
     * supported, the service must also require the
     * android.Manifest.permission#BIND_TV_AD_SERVICE permission so that other
     * applications cannot abuse it.
     */
    public static final String SERVICE_INTERFACE = "android.media.tv.ad.TvAdService";

    /**
     * Name under which a TvAdService component publishes information about itself. This meta-data
     * must reference an XML resource containing an
     * <code>&lt;{@link android.R.styleable#TvAdService tv-ad-service}&gt;</code> tag.
     */
    public static final String SERVICE_META_DATA = "android.media.tv.ad.service";

    private TvAdService() {
    }
}
