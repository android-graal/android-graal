package android.animation;

/**
 * This is the superclass for classes which provide basic support for animations which can be
 * started, ended, and have <code>AnimatorListeners</code> added to them.
 */
public final class Animator {
    /**
     * The value used to indicate infinite duration (e.g. when Animators repeat infinitely).
     */
    public static final long DURATION_INFINITE = -1L;

    private Animator() {
    }
}
