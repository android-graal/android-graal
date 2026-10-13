package android.view.contentcapture;

/**
 * <p>Provides additional ways for apps to integrate with the content capture subsystem.
 *
 * <p>Content capture provides real-time, continuous capture of application activity, display and
 * events to an intelligence service that is provided by the Android system. The intelligence
 * service then uses that info to mediate and speed user journey through different apps. For
 * example, when the user receives a restaurant address in a chat app and switches to a map app
 * to search for that restaurant, the intelligence service could offer an autofill dialog to
 * let the user automatically select its address.
 *
 * <p>Content capture was designed with two major concerns in mind: privacy and performance.
 *
 * <ul>
 *   <li><b>Privacy:</b> the intelligence service is a trusted component provided that is provided
 *   by the device manufacturer and that cannot be changed by the user (although the user can
 *   globaly disable content capture using the Android Settings app). This service can only use the
 *   data for in-device machine learning, which is enforced both by process isolation and
 *   <a href="https://source.android.com/compatibility/cdd">CDD requirements</a>.
 *   <li><b>Performance:</b> content capture is highly optimized to minimize its impact in the app
 *   jankiness and overall device system health. For example, its only enabled on apps (or even
 *   specific activities from an app) that were explicitly allowlisted by the intelligence service,
 *   and it buffers the events so they are sent in a batch to the service (see
 *   {@link #isContentCaptureEnabled()} for other cases when its disabled).
 * </ul>
 *
 * <p>In fact, before using this manager, the app developer should check if it's available. Example:
 * <pre><code>
 *  ContentCaptureManager mgr = context.getSystemService(ContentCaptureManager.class);
 *  if (mgr != null && mgr.isContentCaptureEnabled()) {
 *    // ...
 *  }
 *  </code></pre>
 *
 * <p>App developers usually don't need to explicitly interact with content capture, except when the
 * app:
 *
 * <ul>
 *   <li>Can define a contextual {@link android.content.LocusId} to identify unique state (such as a
 *   conversation between 2 chat users).
 *   <li>Can have multiple view hierarchies with different contextual meaning (for example, a
 *   browser app with multiple tabs, each representing a different URL).
 *   <li>Contains custom views (that extend View directly and are not provided by the standard
 *   Android SDK.
 *   <li>Contains views that provide their own virtual hierarchy (like a web browser that render the
 *   HTML elements using a Canvas).
 * </ul>
 *
 * <p>The main integration point with content capture is the {@link ContentCaptureSession}. A "main"
 * session is automatically created by the Android System when content capture is enabled for the
 * activity and its used by the standard Android views to notify the content capture service of
 * events such as views being added, views been removed, and text changed by user input. The session
 * could have a {@link ContentCaptureContext} to provide more contextual info about it, such as
 * the locus associated with the view hierarchy (see {@link android.content.LocusId} for more info
 * about locus). By default, the main session doesn't have a {@code ContentCaptureContext}, but you
 * can change it after its created. Example:
 *
 * <pre><code>
 * protected void onCreate(Bundle savedInstanceState) {
 *   // Initialize view structure
 *   ContentCaptureSession session = rootView.getContentCaptureSession();
 *   if (session != null) {
 *     session.setContentCaptureContext(ContentCaptureContext.forLocusId("chat_UserA_UserB"));
 *   }
 * }
 * </code></pre>
 *
 * <p>If your activity contains view hierarchies with a different contextual meaning, you should
 * created child sessions for each view hierarchy root. For example, if your activity is a browser,
 * you could use the main session for the main URL being rendered, then child sessions for each
 * {@code IFRAME}:
 *
 * <pre><code>
 * ContentCaptureSession mMainSession;
 *
 * protected void onCreate(Bundle savedInstanceState) {
 *    // Initialize view structure...
 *    mMainSession = rootView.getContentCaptureSession();
 *    if (mMainSession != null) {
 *      mMainSession.setContentCaptureContext(
 *          ContentCaptureContext.forLocusId("https://example.com"));
 *    }
 * }
 *
 * private void loadIFrame(View iframeRootView, String url) {
 *   if (mMainSession != null) {
 *      ContentCaptureSession iFrameSession = mMainSession.newChild(
 *          ContentCaptureContext.forLocusId(url));
 *      }
 *      iframeRootView.setContentCaptureSession(iFrameSession);
 *   }
 *   // Load iframe...
 * }
 * </code></pre>
 *
 * <p>If your activity has custom views (i.e., views that extend {@link View} directly and provide
 * just one logical view, not a virtual tree hiearchy) and it provides content that's relevant for
 * content capture (as of {@link android.os.Build.VERSION_CODES#Q Android Q}, the only relevant
 * content is text), then your view implementation should:
 *
 * <ul>
 *   <li>Set it as important for content capture.
 *   <li>Fill {@link ViewStructure} used for content capture.
 *   <li>Notify the {@link ContentCaptureSession} when the text is changed by user input.
 * </ul>
 *
 * <p>Here's an example of the relevant methods for an {@code EditText}-like view:
 *
 * <pre><code>
 * public class MyEditText extends View {
 *
 * public MyEditText(...) {
 *   if (getImportantForContentCapture() == IMPORTANT_FOR_CONTENT_CAPTURE_AUTO) {
 *     setImportantForContentCapture(IMPORTANT_FOR_CONTENT_CAPTURE_YES);
 *   }
 * }
 *
 * public void onProvideContentCaptureStructure(@NonNull ViewStructure structure, int flags) {
 *   super.onProvideContentCaptureStructure(structure, flags);
 *
 *   structure.setText(getText(), getSelectionStart(), getSelectionEnd());
 *   structure.setHint(getHint());
 *   structure.setInputType(getInputType());
 *   // set other properties like setTextIdEntry(), setTextLines(), setTextStyle(),
 *   // setMinTextEms(), setMaxTextEms(), setMaxTextLength()
 * }
 *
 * private void onTextChanged() {
 *   if (isLaidOut() && isImportantForContentCapture() && isTextEditable()) {
 *     ContentCaptureManager mgr = mContext.getSystemService(ContentCaptureManager.class);
 *     if (cm != null && cm.isContentCaptureEnabled()) {
 *        ContentCaptureSession session = getContentCaptureSession();
 *        if (session != null) {
 *          session.notifyViewTextChanged(getAutofillId(), getText());
 *        }
 *   }
 * }
 * </code></pre>
 *
 * <p>If your view provides its own virtual hierarchy (for example, if it's a browser that draws
 * the HTML using {@link Canvas} or native libraries in a different render process), then the view
 * is also responsible to notify the session when the virtual elements appear and disappear - see
 * {@link View#onProvideContentCaptureStructure(ViewStructure, int)} for more info.
 */
public final class ContentCaptureManager {
    /**
     * Request has been rejected, because a concurrent data share sessions is in progress.
     */
    public static final int DATA_SHARE_ERROR_CONCURRENT_REQUEST = 2;

    /**
     * Request has been interrupted because of data share session timeout.
     */
    public static final int DATA_SHARE_ERROR_TIMEOUT_INTERRUPTED = 3;

    /**
     * Error happened during the data sharing session.
     */
    public static final int DATA_SHARE_ERROR_UNKNOWN = 1;

    private ContentCaptureManager() {
    }
}
