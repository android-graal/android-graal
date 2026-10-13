package android.app;

/**
 * Convenience for implementing an activity that will be implemented
 * purely in native code.  That is, a game (or game-like thing).  There
 * is no need to derive from this class; you can simply declare it in your
 * manifest, and use the NDK APIs from there.
 *
 * <p>A <a href="https://github.com/googlesamples/android-ndk/tree/master/native-activity">sample
 * native activity</a> is available in the NDK samples.
 */
public final class NativeActivity {
    /**
     * Optional meta-that can be in the manifest for this component, specifying
     * the name of the main entry point for this native activity in the
     * {@link #META_DATA_LIB_NAME} native code.  If not specified,
     * "ANativeActivity_onCreate" is used.
     */
    public static final String META_DATA_FUNC_NAME = "android.app.func_name";

    /**
     * Optional meta-that can be in the manifest for this component, specifying
     * the name of the native shared library to load.  If not specified,
     * "main" is used.
     */
    public static final String META_DATA_LIB_NAME = "android.app.lib_name";

    private NativeActivity() {
    }
}
