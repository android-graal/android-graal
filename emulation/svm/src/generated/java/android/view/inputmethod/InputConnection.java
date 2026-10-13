package android.view.inputmethod;

/**
 * The InputConnection interface is the communication channel from an
 * {@link InputMethod} back to the application that is receiving its
 * input. It is used to perform such things as reading text around the
 * cursor, committing text to the text box, and sending raw key events
 * to the application.
 *
 * <p>Starting from API Level {@link android.os.Build.VERSION_CODES#N},
 * the system can deal with the situation where the application directly
 * implements this class but one or more of the following methods are
 * not implemented.</p>
 * <ul>
 *     <li>{@link #getSelectedText(int)}, which was introduced in
 *     {@link android.os.Build.VERSION_CODES#GINGERBREAD}.</li>
 *     <li>{@link #setComposingRegion(int, int)}, which was introduced
 *     in {@link android.os.Build.VERSION_CODES#GINGERBREAD}.</li>
 *     <li>{@link #commitCorrection(CorrectionInfo)}, which was introduced
 *     in {@link android.os.Build.VERSION_CODES#HONEYCOMB}.</li>
 *     <li>{@link #requestCursorUpdates(int)}, which was introduced in
 *     {@link android.os.Build.VERSION_CODES#LOLLIPOP}.</li>
 *     <li>{@link #deleteSurroundingTextInCodePoints(int, int)}, which
 *     was introduced in {@link android.os.Build.VERSION_CODES#N}.</li>
 *     <li>{@link #getHandler()}, which was introduced in
 *     {@link android.os.Build.VERSION_CODES#N}.</li>
 *     <li>{@link #closeConnection()}, which was introduced in
 *     {@link android.os.Build.VERSION_CODES#N}.</li>
 *     <li>{@link #commitContent(InputContentInfo, int, Bundle)}, which was
 *     introduced in {@link android.os.Build.VERSION_CODES#N_MR1}.</li>
 * </ul>
 *
 * <h3>Implementing an IME or an editor</h3>
 * <p>Text input is the result of the synergy of two essential components:
 * an Input Method Engine (IME) and an editor. The IME can be a
 * software keyboard, a handwriting interface, an emoji palette, a
 * speech-to-text engine, and so on. There are typically several IMEs
 * installed on any given Android device. In Android, IMEs extend
 * {@link android.inputmethodservice.InputMethodService}.
 * For more information about how to create an IME, see the
 * <a href="{@docRoot}guide/topics/text/creating-input-method.html">
 * Creating an input method</a> guide.
 *
 * The editor is the component that receives text and displays it.
 * Typically, this is an {@link android.widget.EditText} instance, but
 * some applications may choose to implement their own editor for
 * various reasons. This is a large and complicated task, and an
 * application that does this needs to make sure the behavior is
 * consistent with standard EditText behavior in Android. An editor
 * needs to interact with the IME, receiving commands through
 * this InputConnection interface, and sending commands through
 * {@link android.view.inputmethod.InputMethodManager}. An editor
 * should start by implementing
 * {@link android.view.View#onCreateInputConnection(EditorInfo)}
 * to return its own input connection.</p>
 *
 * <p>If you are implementing your own IME, you will need to call the
 * methods in this interface to interact with the application. Be sure
 * to test your IME with a wide range of applications, including
 * browsers and rich text editors, as some may have peculiarities you
 * need to deal with. Remember your IME may not be the only source of
 * changes on the text, and try to be as conservative as possible in
 * the data you send and as liberal as possible in the data you
 * receive.</p>
 *
 * <p>If you are implementing your own editor, you will probably need
 * to provide your own subclass of {@link BaseInputConnection} to
 * answer to the commands from IMEs. Please be sure to test your
 * editor with as many IMEs as you can as their behavior can vary a
 * lot. Also be sure to test with various languages, including CJK
 * languages and right-to-left languages like Arabic, as these may
 * have different input requirements. When in doubt about the
 * behavior you should adopt for a particular call, please mimic the
 * default TextView implementation in the latest Android version, and
 * if you decide to drift from it, please consider carefully that
 * inconsistencies in text editor behavior is almost universally felt
 * as a bad thing by users.</p>
 *
 * <h3>Cursors, selections and compositions</h3>
 * <p>In Android, the cursor and the selection are one and the same
 * thing. A "cursor" is just the special case of a zero-sized
 * selection. As such, this documentation uses them
 * interchangeably. Any method acting "before the cursor" would act
 * before the start of the selection if there is one, and any method
 * acting "after the cursor" would act after the end of the
 * selection.</p>
 *
 * <p>An editor needs to be able to keep track of a currently
 * "composing" region, like the standard edition widgets do. The
 * composition is marked in a specific style: see
 * {@link android.text.Spanned#SPAN_COMPOSING}. IMEs use this to help
 * the user keep track of what part of the text they are currently
 * focusing on, and interact with the editor using
 * {@link InputConnection#setComposingText(CharSequence, int)},
 * {@link InputConnection#setComposingRegion(int, int)} and
 * {@link InputConnection#finishComposingText()}.
 * The composing region and the selection are completely independent
 * of each other, and the IME may use them however they see fit.</p>
 */
public interface InputConnection {
    /**
     * The editor is requested to call
     * {@link InputMethodManager#updateCursorAnchorInfo(android.view.View, CursorAnchorInfo)}
     * with new character bounds {@link CursorAnchorInfo#getCharacterBounds(int)} whenever
     * cursor/anchor position is changed. To disable
     * monitoring, call {@link InputConnection#requestCursorUpdates(int)} again with this flag off.
     * <p>
     * This flag can be combined with other filters: {@link #CURSOR_UPDATE_FILTER_EDITOR_BOUNDS},
     * {@link #CURSOR_UPDATE_FILTER_VISIBLE_LINE_BOUNDS},
     * {@link #CURSOR_UPDATE_FILTER_TEXT_APPEARANCE}, {@link #CURSOR_UPDATE_FILTER_INSERTION_MARKER}
     * and update flags {@link #CURSOR_UPDATE_IMMEDIATE} and {@link #CURSOR_UPDATE_MONITOR}.
     * </p>
     */
    int CURSOR_UPDATE_FILTER_CHARACTER_BOUNDS = 8;

    /**
     * The editor is requested to call
     * {@link InputMethodManager#updateCursorAnchorInfo(android.view.View, CursorAnchorInfo)}
     * with new {@link EditorBoundsInfo} whenever cursor/anchor position is changed. To disable
     * monitoring, call {@link InputConnection#requestCursorUpdates(int)} again with this flag off.
     * <p>
     * This flag can be used together with filters: {@link #CURSOR_UPDATE_FILTER_CHARACTER_BOUNDS},
     * {@link #CURSOR_UPDATE_FILTER_VISIBLE_LINE_BOUNDS},
     * {@link #CURSOR_UPDATE_FILTER_TEXT_APPEARANCE},
     * {@link #CURSOR_UPDATE_FILTER_INSERTION_MARKER} and update flags
     * {@link #CURSOR_UPDATE_IMMEDIATE} and {@link #CURSOR_UPDATE_MONITOR}.
     * </p>
     */
    int CURSOR_UPDATE_FILTER_EDITOR_BOUNDS = 4;

    /**
     * The editor is requested to call
     * {@link InputMethodManager#updateCursorAnchorInfo(android.view.View, CursorAnchorInfo)}
     * with new Insertion marker info {@link CursorAnchorInfo#getInsertionMarkerFlags()},
     * {@link CursorAnchorInfo#getInsertionMarkerBaseline()}, etc whenever cursor/anchor position is
     * changed. To disable monitoring, call {@link InputConnection#requestCursorUpdates(int)} again
     * with this flag off.
     * <p>
     * This flag can be combined with other filters: {@link #CURSOR_UPDATE_FILTER_CHARACTER_BOUNDS},
     * {@link #CURSOR_UPDATE_FILTER_VISIBLE_LINE_BOUNDS},
     * {@link #CURSOR_UPDATE_FILTER_TEXT_APPEARANCE}, {@link #CURSOR_UPDATE_FILTER_EDITOR_BOUNDS}
     * and update flags {@link #CURSOR_UPDATE_IMMEDIATE} and {@link #CURSOR_UPDATE_MONITOR}.
     * </p>
     */
    int CURSOR_UPDATE_FILTER_INSERTION_MARKER = 16;

    /**
     * The editor is requested to call
     * {@link InputMethodManager#updateCursorAnchorInfo(android.view.View, CursorAnchorInfo)}
     * with new text appearance info {@link CursorAnchorInfo#getTextAppearanceInfo()}}
     * whenever cursor/anchor position is changed. To disable monitoring, call
     * {@link InputConnection#requestCursorUpdates(int)} again with this flag off.
     * <p>
     * This flag can be combined with other filters: {@link #CURSOR_UPDATE_FILTER_CHARACTER_BOUNDS},
     * {@link #CURSOR_UPDATE_FILTER_EDITOR_BOUNDS}, {@link #CURSOR_UPDATE_FILTER_INSERTION_MARKER},
     * {@link #CURSOR_UPDATE_FILTER_VISIBLE_LINE_BOUNDS} and update flags
     * {@link #CURSOR_UPDATE_IMMEDIATE} and {@link #CURSOR_UPDATE_MONITOR}.
     * </p>
     */
    int CURSOR_UPDATE_FILTER_TEXT_APPEARANCE = 64;

    /**
     * The editor is requested to call
     * {@link InputMethodManager#updateCursorAnchorInfo(android.view.View, CursorAnchorInfo)}
     * with new visible line bounds {@link CursorAnchorInfo#getVisibleLineBounds()} whenever
     * cursor/anchor position is changed, the editor or its parent is scrolled or the line bounds
     * changed due to text updates. To disable monitoring, call
     * {@link InputConnection#requestCursorUpdates(int)} again with this flag off.
     * <p>
     * This flag can be combined with other filters: {@link #CURSOR_UPDATE_FILTER_CHARACTER_BOUNDS},
     * {@link #CURSOR_UPDATE_FILTER_EDITOR_BOUNDS}, {@link #CURSOR_UPDATE_FILTER_INSERTION_MARKER},
     * {@link #CURSOR_UPDATE_FILTER_TEXT_APPEARANCE} and update flags
     * {@link #CURSOR_UPDATE_IMMEDIATE} and {@link #CURSOR_UPDATE_MONITOR}.
     * </p>
     */
    int CURSOR_UPDATE_FILTER_VISIBLE_LINE_BOUNDS = 32;

    /**
     * The editor is requested to call
     * {@link InputMethodManager#updateCursorAnchorInfo(android.view.View, CursorAnchorInfo)} at
     * once, as soon as possible, regardless of cursor/anchor position changes. This flag can be
     * used together with {@link #CURSOR_UPDATE_MONITOR}.
     * <p>
     * Note by default all of {@link #CURSOR_UPDATE_FILTER_EDITOR_BOUNDS},
     * {@link #CURSOR_UPDATE_FILTER_CHARACTER_BOUNDS},
     * {@link #CURSOR_UPDATE_FILTER_VISIBLE_LINE_BOUNDS},
     * {@link #CURSOR_UPDATE_FILTER_TEXT_APPEARANCE}, and
     * {@link #CURSOR_UPDATE_FILTER_INSERTION_MARKER}, are included but specifying them can
     * filter-out others.
     * It can be CPU intensive to include all, filtering specific info is recommended.
     * </p>
     */
    int CURSOR_UPDATE_IMMEDIATE = 1;

    /**
     * The editor is requested to call
     * {@link InputMethodManager#updateCursorAnchorInfo(android.view.View, CursorAnchorInfo)}
     * whenever cursor/anchor position is changed. To disable monitoring, call
     * {@link InputConnection#requestCursorUpdates(int)} again with this flag off.
     * <p>
     * This flag can be used together with {@link #CURSOR_UPDATE_IMMEDIATE}.
     * </p>
     * <p>
     * Note by default all of {@link #CURSOR_UPDATE_FILTER_EDITOR_BOUNDS},
     * {@link #CURSOR_UPDATE_FILTER_CHARACTER_BOUNDS},
     * {@link #CURSOR_UPDATE_FILTER_VISIBLE_LINE_BOUNDS},
     * {@link #CURSOR_UPDATE_FILTER_TEXT_APPEARANCE}, and
     * {@link #CURSOR_UPDATE_FILTER_INSERTION_MARKER}, are included but specifying them can
     * filter-out others.
     * It can be CPU intensive to include all, filtering specific info is recommended.
     * </p>
     */
    int CURSOR_UPDATE_MONITOR = 2;

    /**
     * Flag for use with {@link #getExtractedText} to indicate you
     * would like to receive updates when the extracted text changes.
     */
    int GET_EXTRACTED_TEXT_MONITOR = 1;

    /**
     * Flag for use with {@link #getTextAfterCursor}, {@link #getTextBeforeCursor} and
     * {@link #getSurroundingText} to have style information returned along with the text. If not
     * set, {@link #getTextAfterCursor} sends only the raw text, without style or other spans. If
     * set, it may return a complex CharSequence of both text and style spans.
     * <strong>Editor authors</strong>: you should strive to send text with styles if possible, but
     * it is not required.
     */
    int GET_TEXT_WITH_STYLES = 1;

    /**
     * Result for {@link #performHandwritingGesture(HandwritingGesture, Executor, IntConsumer)} when
     * {@link HandwritingGesture} was cancelled. This happens when the {@link InputConnection} is
     * or becomes invalidated while performing the gesture, for example because a new
     * {@code InputConnection} was started, or due to {@link InputMethodManager#invalidateInput}.
     */
    int HANDWRITING_GESTURE_RESULT_CANCELLED = 4;

    /**
     * Result for {@link #performHandwritingGesture(HandwritingGesture, Executor, IntConsumer)} when
     * {@link HandwritingGesture} failed and there was no applicable
     * {@link HandwritingGesture#getFallbackText()} or it couldn't
     * be applied for any other reason.
     */
    int HANDWRITING_GESTURE_RESULT_FAILED = 3;

    /**
     * Result for {@link #performHandwritingGesture(HandwritingGesture, Executor, IntConsumer)} when
     * {@link HandwritingGesture} failed but {@link HandwritingGesture#getFallbackText()} was
     * committed.
     */
    int HANDWRITING_GESTURE_RESULT_FALLBACK = 5;

    /**
     * Result for {@link #performHandwritingGesture(HandwritingGesture, Executor, IntConsumer)} when
     * {@link HandwritingGesture} is successfully executed on text.
     */
    int HANDWRITING_GESTURE_RESULT_SUCCESS = 1;

    /**
     * Result for {@link #performHandwritingGesture(HandwritingGesture, Executor, IntConsumer)} when
     * editor didn't provide any result.
     */
    int HANDWRITING_GESTURE_RESULT_UNKNOWN = 0;

    /**
     * Result for {@link #performHandwritingGesture(HandwritingGesture, Executor, IntConsumer)} when
     * {@link HandwritingGesture} is unsupported by the current editor.
     */
    int HANDWRITING_GESTURE_RESULT_UNSUPPORTED = 2;

    /**
     * When this flag is used, the editor will be able to request read access to the content URI
     * contained in the {@link InputContentInfo} object.
     *
     * <p>Make sure that the content provider owning the Uri sets the
     * {@link android.R.styleable#AndroidManifestProvider_grantUriPermissions
     * grantUriPermissions} attribute in its manifest or included the
     * {@link android.R.styleable#AndroidManifestGrantUriPermission
     * &lt;grant-uri-permissions&gt;} tag. Otherwise {@link InputContentInfo#requestPermission()}
     * can fail.</p>
     *
     * <p>Although calling this API is allowed only for the IME that is currently selected, the
     * client is able to request a temporary read-only access even after the current IME is switched
     * to any other IME as long as the client keeps {@link InputContentInfo} object.</p>
     */
    int INPUT_CONTENT_GRANT_READ_URI_PERMISSION = 1;
}
