package android.opengl;

/**
 * A helper class for debugging OpenGL ES applications.
 *
 * Wraps the supplied GL interface with a new GL interface that adds support for
 * error checking and logging.
 */
public final class GLDebugHelper {
    /**
     * Check glError() after every call.
     */
    public static final int CONFIG_CHECK_GL_ERROR = 1;

    /**
     * Check if all calls are on the same thread.
     */
    public static final int CONFIG_CHECK_THREAD = 2;

    /**
     * Print argument names when logging GL Calls.
     */
    public static final int CONFIG_LOG_ARGUMENT_NAMES = 4;

    /**
     * The Error number used in the GLException that is thrown if
     * CONFIG_CHECK_THREAD is enabled and you call OpenGL ES on the
     * a different thread.
     */
    public static final int ERROR_WRONG_THREAD = 28672;

    private GLDebugHelper() {
    }
}
