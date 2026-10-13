package android.inputmethodservice;

/**
 * InputMethodService provides a standard implementation of an InputMethod,
 * which final implementations can derive from and customize.  See the
 * base class {@link AbstractInputMethodService} and the {@link InputMethod}
 * interface for more information on the basics of writing input methods.
 *
 * <p>In addition to the normal Service lifecycle methods, this class
 * introduces some new specific callbacks that most subclasses will want
 * to make use of:</p>
 * <ul>
 * <li> {@link #onInitializeInterface()} for user-interface initialization,
 * in particular to deal with configuration changes while the service is
 * running.
 * <li> {@link #onBindInput} to find out about switching to a new client.
 * <li> {@link #onStartInput} to deal with an input session starting with
 * the client.
 * <li> {@link #onCreateInputView()}, {@link #onCreateCandidatesView()},
 * and {@link #onCreateExtractTextView()} for non-demand generation of the UI.
 * <li> {@link #onStartInputView(EditorInfo, boolean)} to deal with input
 * starting within the input area of the IME.
 * </ul>
 *
 * <p>An input method has significant discretion in how it goes about its
 * work: the {@link android.inputmethodservice.InputMethodService} provides
 * a basic framework for standard UI elements (input view, candidates view,
 * and running in fullscreen mode), but it is up to a particular implementor
 * to decide how to use them.  For example, one input method could implement
 * an input area with a keyboard, another could allow the user to draw text,
 * while a third could have no input area (and thus not be visible to the
 * user) but instead listen to audio and perform text to speech conversion.</p>
 *
 * <p>In the implementation provided here, all of these elements are placed
 * together in a single window managed by the InputMethodService.  It will
 * execute callbacks as it needs information about them, and provides APIs for
 * programmatic control over them.  They layout of these elements is explicitly
 * defined:</p>
 *
 * <ul>
 * <li>The soft input view, if available, is placed at the bottom of the
 * screen.
 * <li>The candidates view, if currently shown, is placed above the soft
 * input view.
 * <li>If not running fullscreen, the application is moved or resized to be
 * above these views; if running fullscreen, the window will completely cover
 * the application and its top part will contain the extract text of what is
 * currently being edited by the application.
 * </ul>
 *
 *
 * <a name="SoftInputView"></a>
 * <h3>Soft Input View</h3>
 *
 * <p>Central to most input methods is the soft input view.  This is where most
 * user interaction occurs: pressing on soft keys, drawing characters, or
 * however else your input method wants to generate text.  Most implementations
 * will simply have their own view doing all of this work, and return a new
 * instance of it when {@link #onCreateInputView()} is called.  At that point,
 * as long as the input view is visible, you will see user interaction in
 * that view and can call back on the InputMethodService to interact with the
 * application as appropriate.</p>
 *
 * <p>There are some situations where you want to decide whether or not your
 * soft input view should be shown to the user.  This is done by implementing
 * the {@link #onEvaluateInputViewShown()} to return true or false based on
 * whether it should be shown in the current environment.  If any of your
 * state has changed that may impact this, call
 * {@link #updateInputViewShown()} to have it re-evaluated.  The default
 * implementation always shows the input view unless there is a hard
 * keyboard available, which is the appropriate behavior for most input
 * methods.</p>
 *
 *
 * <a name="CandidatesView"></a>
 * <h3>Candidates View</h3>
 *
 * <p>Often while the user is generating raw text, an input method wants to
 * provide them with a list of possible interpretations of that text that can
 * be selected for use.  This is accomplished with the candidates view, and
 * like the soft input view you implement {@link #onCreateCandidatesView()}
 * to instantiate your own view implementing your candidates UI.</p>
 *
 * <p>Management of the candidates view is a little different than the input
 * view, because the candidates view tends to be more transient, being shown
 * only when there are possible candidates for the current text being entered
 * by the user.  To control whether the candidates view is shown, you use
 * {@link #setCandidatesViewShown(boolean)}.  Note that because the candidate
 * view tends to be shown and hidden a lot, it does not impact the application
 * UI in the same way as the soft input view: it will never cause application
 * windows to resize, only cause them to be panned if needed for the user to
 * see the current focus.</p>
 *
 *
 * <a name="FullscreenMode"></a>
 * <h3>Fullscreen Mode</h3>
 *
 * <p>Sometimes your input method UI is too large to integrate with the
 * application UI, so you just want to take over the screen.  This is
 * accomplished by switching to full-screen mode, causing the input method
 * window to fill the entire screen and add its own "extracted text" editor
 * showing the user the text that is being typed.  Unlike the other UI elements,
 * there is a standard implementation for the extract editor that you should
 * not need to change.  The editor is placed at the top of the IME, above the
 * input and candidates views.</p>
 *
 * <p>Similar to the input view, you control whether the IME is running in
 * fullscreen mode by implementing {@link #onEvaluateFullscreenMode()}
 * to return true or false based on
 * whether it should be fullscreen in the current environment.  If any of your
 * state has changed that may impact this, call
 * {@link #updateFullscreenMode()} to have it re-evaluated.  The default
 * implementation selects fullscreen mode when the screen is in a landscape
 * orientation, which is appropriate behavior for most input methods that have
 * a significant input area.</p>
 *
 * <p>When in fullscreen mode, you have some special requirements because the
 * user can not see the application UI.  In particular, you should implement
 * {@link #onDisplayCompletions(CompletionInfo[])} to show completions
 * generated by your application, typically in your candidates view like you
 * would normally show candidates.
 *
 *
 * <a name="GeneratingText"></a>
 * <h3>Generating Text</h3>
 *
 * <p>The key part of an IME is of course generating text for the application.
 * This is done through calls to the
 * {@link android.view.inputmethod.InputConnection} interface to the
 * application, which can be retrieved from {@link #getCurrentInputConnection()}.
 * This interface allows you to generate raw key events or, if the target
 * supports it, directly edit in strings of candidates and committed text.</p>
 *
 * <p>Information about what the target is expected and supports can be found
 * through the {@link android.view.inputmethod.EditorInfo} class, which is
 * retrieved with {@link #getCurrentInputEditorInfo()} method.  The most
 * important part of this is {@link android.view.inputmethod.EditorInfo#inputType
 * EditorInfo.inputType}; in particular, if this is
 * {@link android.view.inputmethod.EditorInfo#TYPE_NULL EditorInfo.TYPE_NULL},
 * then the target does not support complex edits and you need to only deliver
 * raw key events to it.  An input method will also want to look at other
 * values here, to for example detect password mode, auto complete text views,
 * phone number entry, etc.</p>
 *
 * <p>When the user switches between input targets, you will receive calls to
 * {@link #onFinishInput()} and {@link #onStartInput(EditorInfo, boolean)}.
 * You can use these to reset and initialize your input state for the current
 * target.  For example, you will often want to clear any input state, and
 * update a soft keyboard to be appropriate for the new inputType.</p>
 *
 * @attr ref android.R.styleable#InputMethodService_imeFullscreenBackground
 * @attr ref android.R.styleable#InputMethodService_imeExtractEnterAnimation
 * @attr ref android.R.styleable#InputMethodService_imeExtractExitAnimation
 */
public final class InputMethodService {
    /**
     * Asks the system to not adjust the back button affordance even when the software keyboard is
     * shown.
     *
     * <p>This mode is useful for UI modes where IME's main soft input window is used for some
     * supplemental UI, such as floating candidate window for languages such as Chinese and
     * Japanese, where users expect the back button is, or at least looks to be, handled by the
     * target application rather than the UI shown by the IME even while {@link #isInputViewShown()}
     * returns {@code true}.</p>
     *
     * <p>Note that {@link KeyEvent#KEYCODE_BACK} events continue to be sent to
     * {@link #onKeyDown(int, KeyEvent)} even when this mode is specified. The default
     * implementation of {@link #onKeyDown(int, KeyEvent)} for {@link KeyEvent#KEYCODE_BACK} does
     * not take this mode into account.</p>
     *
     * @see #setBackDisposition(int)
     */
    public static final int BACK_DISPOSITION_ADJUST_NOTHING = 3;

    /**
     * Allows the system to optimize the back button affordance based on the presence of software
     * keyboard.
     *
     * <p>For instance, on devices that have navigation bar and software-rendered back button, the
     * system may use a different icon while {@link #isInputViewShown()} returns {@code true}, to
     * indicate that the back button has "dismiss" affordance.</p>
     *
     * <p>Note that {@link KeyEvent#KEYCODE_BACK} events continue to be sent to
     * {@link #onKeyDown(int, KeyEvent)} even when this mode is specified. The default
     * implementation of {@link #onKeyDown(int, KeyEvent)} for {@link KeyEvent#KEYCODE_BACK} does
     * not take this mode into account.</p>
     *
     * <p>For API level {@link android.os.Build.VERSION_CODES#O_MR1} and lower devices, this is the
     * only mode you can safely specify without worrying about the compatibility.</p>
     *
     * @see #setBackDisposition(int)
     */
    public static final int BACK_DISPOSITION_DEFAULT = 0;

    /**
     * Deprecated flag.
     *
     * <p>To avoid compatibility issues, IME developers should not use this flag.</p>
     *
     * @deprecated on {@link android.os.Build.VERSION_CODES#P} and later devices, this flag is
     *             handled as a synonym of {@link #BACK_DISPOSITION_DEFAULT}. On
     *             {@link android.os.Build.VERSION_CODES#O_MR1} and prior devices, expected behavior
     *             of this mode had not been well defined. In AOSP implementation running on devices
     *             that have navigation bar, specifying this flag could change the software back
     *             button to "Dismiss" icon no matter whether the software keyboard is shown or not,
     *             but there would be no easy way to restore the icon state even after IME lost the
     *             connection to the application. To avoid user confusions, do not specify this mode
     *             anyway
     * @see #setBackDisposition(int)
     */
    @Deprecated
    public static final int BACK_DISPOSITION_WILL_DISMISS = 2;

    /**
     * Deprecated flag.
     *
     * <p>To avoid compatibility issues, IME developers should not use this flag.</p>
     *
     * @deprecated on {@link android.os.Build.VERSION_CODES#P} and later devices, this flag is
     *             handled as a synonym of {@link #BACK_DISPOSITION_DEFAULT}. On
     *             {@link android.os.Build.VERSION_CODES#O_MR1} and prior devices, expected behavior
     *             of this mode had not been well defined. Most likely the end result would be the
     *             same as {@link #BACK_DISPOSITION_DEFAULT}. Either way it is not recommended to
     *             use this mode
     * @see #setBackDisposition(int)
     */
    @Deprecated
    public static final int BACK_DISPOSITION_WILL_NOT_DISMISS = 1;

    private InputMethodService() {
    }

    /**
     * Information about where interesting parts of the input method UI appear.
     */
    public static final class Insets {
        /**
         * Option for {@link #touchableInsets}: the area inside of
         * the content insets can be touched.
         */
        public static final int TOUCHABLE_INSETS_CONTENT = 1;

        /**
         * Option for {@link #touchableInsets}: the entire window frame
         * can be touched.
         */
        public static final int TOUCHABLE_INSETS_FRAME = 0;

        /**
         * Option for {@link #touchableInsets}: the region specified by
         * {@link #touchableRegion} can be touched.
         */
        public static final int TOUCHABLE_INSETS_REGION = 3;

        /**
         * Option for {@link #touchableInsets}: the area inside of
         * the visible insets can be touched.
         */
        public static final int TOUCHABLE_INSETS_VISIBLE = 2;

        private Insets() {
        }
    }
}
