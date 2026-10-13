package android.view.inputmethod;

/**
 * The InputMethod interface represents an input method which can generate key
 * events and text, such as digital, email addresses, CJK characters, other
 * language characters, and etc., while handling various input events, and send
 * the text back to the application that requests text input.  See
 * {@link InputMethodManager} for more general information about the
 * architecture.
 *
 * <p>Applications will not normally use this interface themselves, instead
 * relying on the standard interaction provided by
 * {@link android.widget.TextView} and {@link android.widget.EditText}.
 *
 * <p>Those implementing input methods should normally do so by deriving from
 * {@link InputMethodService} or one of its subclasses.  When implementing
 * an input method, the service component containing it must also supply
 * a {@link #SERVICE_META_DATA} meta-data field, referencing an XML resource
 * providing details about the input method.  All input methods also must
 * require that clients hold the
 * {@link android.Manifest.permission#BIND_INPUT_METHOD} in order to interact
 * with the service; if this is not required, the system will not use that
 * input method, because it can not trust that it is not compromised.
 *
 * <p>The InputMethod interface is actually split into two parts: the interface
 * here is the top-level interface to the input method, providing all
 * access to it, which only the system can access (due to the BIND_INPUT_METHOD
 * permission requirement).  In addition its method
 * {@link #createSession(android.view.inputmethod.InputMethod.SessionCallback)}
 * can be called to instantate a secondary {@link InputMethodSession} interface
 * which is what clients use to communicate with the input method.
 */
public interface InputMethod {
    /**
     * This is the interface name that a service implementing an input
     * method should say that it supports -- that is, this is the action it
     * uses for its intent filter.
     * To be supported, the service must also require the
     * {@link android.Manifest.permission#BIND_INPUT_METHOD} permission so
     * that other applications can not abuse it.
     */
    String SERVICE_INTERFACE = "android.view.InputMethod";

    /**
     * Name under which an InputMethod service component publishes information
     * about itself.  This meta-data must reference an XML resource containing
     * an
     * <code>&lt;{@link android.R.styleable#InputMethod input-method}&gt;</code>
     * tag.
     */
    String SERVICE_META_DATA = "android.view.im";

    /**
     * Flag for {@link #showSoftInput}: this show has been explicitly
     * requested by the user.  If not set, the system has decided it may be
     * a good idea to show the input method based on a navigation operation
     * in the UI.
     */
    int SHOW_EXPLICIT = 1;

    /**
     * Flag for {@link #showSoftInput}: this show has been forced to
     * happen by the user.  If set, the input method should remain visible
     * until deliberated dismissed by the user in its UI.
     *
     * @deprecated {@link InputMethodManager#SHOW_FORCED} is deprecated and
     * should no longer be used by apps. IMEs likewise should no longer react to this flag.
     */
    @Deprecated
    int SHOW_FORCED = 2;
}
