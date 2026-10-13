package android.view.inputmethod;

/**
 * Central system API to the overall input method framework (IMF) architecture,
 * which arbitrates interaction between applications and the current input method.
 *
 * <p>Topics covered here:
 * <ol>
 * <li><a href="#ArchitectureOverview">Architecture Overview</a>
 * <li><a href="#Applications">Applications</a>
 * <li><a href="#InputMethods">Input Methods</a>
 * <li><a href="#Security">Security</a>
 * </ol>
 *
 * <a name="ArchitectureOverview"></a>
 * <h3>Architecture Overview</h3>
 *
 * <p>There are three primary parties involved in the input method
 * framework (IMF) architecture:</p>
 *
 * <ul>
 * <li> The <strong>input method manager</strong> as expressed by this class
 * is the central point of the system that manages interaction between all
 * other parts.  It is expressed as the client-side API here which exists
 * in each application context and communicates with a global system service
 * that manages the interaction across all processes.
 * <li> An <strong>input method (IME)</strong> implements a particular
 * interaction model allowing the user to generate text.  The system binds
 * to the current input method that is in use, causing it to be created and run,
 * and tells it when to hide and show its UI.  Only one IME is running at a time.
 * <li> Multiple <strong>client applications</strong> arbitrate with the input
 * method manager for input focus and control over the state of the IME.  Only
 * one such client is ever active (working with the IME) at a time.
 * </ul>
 *
 *
 * <a name="Applications"></a>
 * <h3>Applications</h3>
 *
 * <p>In most cases, applications that are using the standard
 * {@link android.widget.TextView} or its subclasses will have little they need
 * to do to work well with soft input methods.  The main things you need to
 * be aware of are:</p>
 *
 * <ul>
 * <li> Properly set the {@link android.R.attr#inputType} in your editable
 * text views, so that the input method will have enough context to help the
 * user in entering text into them.
 * <li> Deal well with losing screen space when the input method is
 * displayed.  Ideally an application should handle its window being resized
 * smaller, but it can rely on the system performing panning of the window
 * if needed.  You should set the {@link android.R.attr#windowSoftInputMode}
 * attribute on your activity or the corresponding values on windows you
 * create to help the system determine whether to pan or resize (it will
 * try to determine this automatically but may get it wrong).
 * <li> You can also control the preferred soft input state (open, closed, etc)
 * for your window using the same {@link android.R.attr#windowSoftInputMode}
 * attribute.
 * </ul>
 *
 * <p>More finer-grained control is available through the APIs here to directly
 * interact with the IMF and its IME -- either showing or hiding the input
 * area, letting the user pick an input method, etc.</p>
 *
 * <p>For the rare people amongst us writing their own text editors, you
 * will need to implement {@link android.view.View#onCreateInputConnection}
 * to return a new instance of your own {@link InputConnection} interface
 * allowing the IME to interact with your editor.</p>
 *
 *
 * <a name="InputMethods"></a>
 * <h3>Input Methods</h3>
 *
 * <p>An input method (IME) is implemented
 * as a {@link android.app.Service}, typically deriving from
 * {@link android.inputmethodservice.InputMethodService}.  It must provide
 * the core {@link InputMethod} interface, though this is normally handled by
 * {@link android.inputmethodservice.InputMethodService} and implementors will
 * only need to deal with the higher-level API there.</p>
 *
 * See the {@link android.inputmethodservice.InputMethodService} class for
 * more information on implementing IMEs.
 *
 *
 * <a name="Security"></a>
 * <h3>Security</h3>
 *
 * <p>There are a lot of security issues associated with input methods,
 * since they essentially have freedom to completely drive the UI and monitor
 * everything the user enters.  The Android input method framework also allows
 * arbitrary third party IMEs, so care must be taken to restrict their
 * selection and interactions.</p>
 *
 * <p>Here are some key points about the security architecture behind the
 * IMF:</p>
 *
 * <ul>
 * <li> <p>Only the system is allowed to directly access an IME's
 * {@link InputMethod} interface, via the
 * {@link android.Manifest.permission#BIND_INPUT_METHOD} permission.  This is
 * enforced in the system by not binding to an input method service that does
 * not require this permission, so the system can guarantee no other untrusted
 * clients are accessing the current input method outside of its control.</p>
 *
 * <li> <p>There may be many client processes of the IMF, but only one may
 * be active at a time.  The inactive clients can not interact with key
 * parts of the IMF through the mechanisms described below.</p>
 *
 * <li> <p>Clients of an input method are only given access to its
 * {@link InputMethodSession} interface.  One instance of this interface is
 * created for each client, and only calls from the session associated with
 * the active client will be processed by the current IME.  This is enforced
 * by {@link android.inputmethodservice.AbstractInputMethodService} for normal
 * IMEs, but must be explicitly handled by an IME that is customizing the
 * raw {@link InputMethodSession} implementation.</p>
 *
 * <li> <p>Only the active client's {@link InputConnection} will accept
 * operations.  The IMF tells each client process whether it is active, and
 * the framework enforces that in inactive processes calls on to the current
 * InputConnection will be ignored.  This ensures that the current IME can
 * only deliver events and text edits to the UI that the user sees as
 * being in focus.</p>
 *
 * <li> <p>An IME can never interact with an {@link InputConnection} while
 * the screen is off.  This is enforced by making all clients inactive while
 * the screen is off, and prevents bad IMEs from driving the UI when the user
 * can not be aware of its behavior.</p>
 *
 * <li> <p>A client application can ask that the system let the user pick a
 * new IME, but can not programmatically switch to one itself.  This avoids
 * malicious applications from switching the user to their own IME, which
 * remains running when the user navigates away to another application.  An
 * IME, on the other hand, <em>is</em> allowed to programmatically switch
 * the system to another IME, since it already has full control of user
 * input.</p>
 *
 * <li> <p>The user must explicitly enable a new IME in settings before
 * they can switch to it, to confirm with the system that they know about it
 * and want to make it available for use.</p>
 * </ul>
 *
 * <p>If your app targets Android 11 (API level 30) or higher, the methods in
 * this class each return a filtered result by the rules of
 * <a href="/training/basics/intents/package-visibility">package visibility</a>,
 * except for the currently connected IME. Apps having a query for the
 * {@link InputMethod#SERVICE_INTERFACE} see all IMEs.</p>
 */
public final class InputMethodManager {
    /**
     * Flag indicating that views from the default home screen ({@link Intent#CATEGORY_HOME}) may
     * act as a handwriting delegator for the delegate editor view. If set, views from the home
     * screen package will be trusted for handwriting delegation, in addition to views in the {@code
     * delegatorPackageName} passed to
     * {@link #acceptStylusHandwritingDelegation(View, String, int, Executor, Consumer)} .
     */
    public static final int HANDWRITING_DELEGATE_FLAG_HOME_DELEGATOR_ALLOWED = 1;

    /**
     * Flag for {@link #hideSoftInputFromWindow} and {@link InputMethodService#requestHideSelf(int)}
     * to indicate that the soft input window should only be hidden if it was not explicitly shown
     * by the user.
     */
    public static final int HIDE_IMPLICIT_ONLY = 1;

    /**
     * Flag for {@link #hideSoftInputFromWindow} and {@link InputMethodService#requestShowSelf(int)}
     * to indicate that the soft input window should normally be hidden, unless it was originally
     * shown with {@link #SHOW_FORCED}.
     */
    public static final int HIDE_NOT_ALWAYS = 2;

    /**
     * Flag for the {@link ResultReceiver} result code from
     * {@link #showSoftInput(View, int, ResultReceiver)} and
     * {@link #hideSoftInputFromWindow(IBinder, int, ResultReceiver)}: the
     * state of the soft input window changed from shown to hidden.
     */
    public static final int RESULT_HIDDEN = 3;

    /**
     * Flag for the {@link ResultReceiver} result code from
     * {@link #showSoftInput(View, int, ResultReceiver)} and
     * {@link #hideSoftInputFromWindow(IBinder, int, ResultReceiver)}: the
     * state of the soft input window changed from hidden to shown.
     */
    public static final int RESULT_SHOWN = 2;

    /**
     * Flag for the {@link ResultReceiver} result code from
     * {@link #showSoftInput(View, int, ResultReceiver)} and
     * {@link #hideSoftInputFromWindow(IBinder, int, ResultReceiver)}: the
     * state of the soft input window was unchanged and remains hidden.
     */
    public static final int RESULT_UNCHANGED_HIDDEN = 1;

    /**
     * Flag for the {@link ResultReceiver} result code from
     * {@link #showSoftInput(View, int, ResultReceiver)} and
     * {@link #hideSoftInputFromWindow(IBinder, int, ResultReceiver)}: the
     * state of the soft input window was unchanged and remains shown.
     */
    public static final int RESULT_UNCHANGED_SHOWN = 0;

    /**
     * Flag for {@link #showSoftInput} to indicate that the user has forced
     * the input method open (such as by long-pressing menu) so it should
     * not be closed until they explicitly do so.
     *
     * @deprecated Use {@link #showSoftInput} without this flag instead. Using this flag can lead
     * to the soft input remaining visible even when the calling application is closed. The
     * use of this flag can make the soft input remain visible globally. Starting in
     * {@link Build.VERSION_CODES#TIRAMISU Android T}, this flag only has an effect while the
     * caller is currently focused.
     */
    @Deprecated
    public static final int SHOW_FORCED = 2;

    /**
     * Flag for {@link #showSoftInput} to indicate that this is an implicit
     * request to show the input window, not as the result of a direct request
     * by the user.  The window may not be shown in this case.
     */
    public static final int SHOW_IMPLICIT = 1;

    private InputMethodManager() {
    }
}
