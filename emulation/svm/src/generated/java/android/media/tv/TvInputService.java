package android.media.tv;

/**
 * The TvInputService class represents a TV input or source such as HDMI or built-in tuner which
 * provides pass-through video or broadcast TV programs.
 *
 * <p>Applications will not normally use this service themselves, instead relying on the standard
 * interaction provided by {@link TvView}. Those implementing TV input services should normally do
 * so by deriving from this class and providing their own session implementation based on
 * {@link TvInputService.Session}. All TV input services must require that clients hold the
 * {@link android.Manifest.permission#BIND_TV_INPUT} in order to interact with the service; if this
 * permission is not specified in the manifest, the system will refuse to bind to that TV input
 * service.
 */
public final class TvInputService {
    /**
     * Use case of priority hint for {@link android.media.MediaCas#MediaCas(Context, int, String,
     * int)}: Background. TODO Link: Tuner#Tuner(Context, string, int).
     */
    public static final int PRIORITY_HINT_USE_CASE_TYPE_BACKGROUND = 100;

    /**
     * Use case of priority hint for {@link android.media.MediaCas#MediaCas(Context, int, String,
     * int)}: Live. TODO Link: Tuner#Tuner(Context, string, int).
     */
    public static final int PRIORITY_HINT_USE_CASE_TYPE_LIVE = 400;

    /**
     * Use case of priority hint for {@link android.media.MediaCas#MediaCas(Context, int, String,
     * int)}: Playback. TODO Link: Tuner#Tuner(Context, string, int).
     */
    public static final int PRIORITY_HINT_USE_CASE_TYPE_PLAYBACK = 300;

    /**
     * Use case of priority hint for {@link android.media.MediaCas#MediaCas(Context, int, String,
     * int)}: Record. TODO Link: Tuner#Tuner(Context, string, int).
     */
    public static final int PRIORITY_HINT_USE_CASE_TYPE_RECORD = 500;

    /**
     * Use case of priority hint for {@link android.media.MediaCas#MediaCas(Context, int, String,
     * int)}: Scan. TODO Link: Tuner#Tuner(Context, string, int).
     */
    public static final int PRIORITY_HINT_USE_CASE_TYPE_SCAN = 200;

    /**
     * This is the interface name that a service implementing a TV input should say that it support
     * -- that is, this is the action it uses for its intent filter. To be supported, the service
     * must also require the {@link android.Manifest.permission#BIND_TV_INPUT} permission so that
     * other applications cannot abuse it.
     */
    public static final String SERVICE_INTERFACE = "android.media.tv.TvInputService";

    /**
     * Name under which a TvInputService component publishes information about itself.
     * This meta-data must reference an XML resource containing an
     * <code>&lt;{@link android.R.styleable#TvInputService tv-input}&gt;</code>
     * tag.
     */
    public static final String SERVICE_META_DATA = "android.media.tv.input";

    private TvInputService() {
    }
}
