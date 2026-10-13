package android.webkit;

/**
 * A View that displays web pages.
 *
 * <h3>Basic usage</h3>
 *
 *
 * <p>In most cases, we recommend using a standard web browser, like Chrome, to deliver
 * content to the user. To learn more about web browsers, read the guide on
 * <a href="/guide/components/intents-common#Browser">
 * invoking a browser with an intent</a>.
 *
 * <p>WebView objects allow you to display web content as part of your activity layout, but
 * lack some of the features of fully-developed browsers. A WebView is useful when
 * you need increased control over the UI and advanced configuration options that will allow
 * you to embed web pages in a specially-designed environment for your app.
 *
 * <p>To learn more about WebView and alternatives for serving web content, read the
 * documentation on
 * <a href="/guide/webapps/">
 * Web-based content</a>.
 */
public final class WebView {
    /**
     * This is the medium binding priority for the WebView renderer process. This is equivalent to
     * the standard priority used by the system for calls to {@link
     * Context#bindService(Intent,android.content.ServiceConnection,int)} when no flags are
     * provided. At this priority level {@link WebView} renderers will be slightly more likely
     * targets for being killed when the system is running low on memory.
     *
     * <p>If using this priority, we recommend handling the {@link
     * WebViewClient#onRenderProcessGone} callback to recover from low memory kills as well as other
     * types of renderer crashes.
     *
     * @see #setRendererPriorityPolicy
     * @see #getRendererPriorityPolicy
     * @see RenderProcessGoneDetail#rendererPriorityAtExit
     */
    public static final int RENDERER_PRIORITY_BOUND = 1;

    /**
     * This is the highest binding priority for the WebView renderer process, and the default value
     * WebView uses to bind to the renderer process. This is equivalent to {@link
     * Context#BIND_IMPORTANT}. At this priority level {@link WebView} renderers are less likely to
     * be killed when the system is running low on memory and will have the same priority as your
     * app's main process.
     *
     * <p>It's still possible for the renderer process to be killed when the system is running low
     * on memory, however specifying this priority makes this situation less likely. It's also still
     * possible for the renderer process to crash for other reasons. You may optionally still choose
     * to handle {@link WebViewClient#onRenderProcessGone} in order to recover from a killed or
     * crashed renderer process.
     *
     * @see #setRendererPriorityPolicy
     * @see #getRendererPriorityPolicy
     * @see RenderProcessGoneDetail#rendererPriorityAtExit
     */
    public static final int RENDERER_PRIORITY_IMPORTANT = 2;

    /**
     * This is the lowest binding priority for the WebView renderer process. This is equivalent to
     * {@link Context#BIND_WAIVE_PRIORITY}. At this priority level {@link WebView} renderers will be
     * frequent targets for being killed when the system is running low on memory.
     *
     * <p>If using this priority, we recommend handling the {@link
     * WebViewClient#onRenderProcessGone} callback to recover from low memory kills as well as other
     * types of renderer crashes.
     *
     * @see #setRendererPriorityPolicy
     * @see #getRendererPriorityPolicy
     * @see RenderProcessGoneDetail#rendererPriorityAtExit
     */
    public static final int RENDERER_PRIORITY_WAIVED = 0;

    /**
     * URI scheme for map address.
     */
    public static final String SCHEME_GEO = "geo:0,0?q=";

    /**
     * URI scheme for email address.
     */
    public static final String SCHEME_MAILTO = "mailto:";

    /**
     * URI scheme for telephone number.
     */
    public static final String SCHEME_TEL = "tel:";

    private WebView() {
    }

    public static final class HitTestResult {
        /**
         * @deprecated This type is no longer used.
         */
        @Deprecated
        public static final int ANCHOR_TYPE = 1;

        /**
         * HitTestResult for hitting an edit text area.
         */
        public static final int EDIT_TEXT_TYPE = 9;

        /**
         * HitTestResult for hitting an email address.
         */
        public static final int EMAIL_TYPE = 4;

        /**
         * HitTestResult for hitting a map address.
         */
        public static final int GEO_TYPE = 3;

        /**
         * @deprecated This type is no longer used.
         */
        @Deprecated
        public static final int IMAGE_ANCHOR_TYPE = 6;

        /**
         * HitTestResult for hitting an HTML::img tag.
         */
        public static final int IMAGE_TYPE = 5;

        /**
         * HitTestResult for hitting a phone number.
         */
        public static final int PHONE_TYPE = 2;

        /**
         * HitTestResult for hitting a HTML::a tag with src=http.
         */
        public static final int SRC_ANCHOR_TYPE = 7;

        /**
         * HitTestResult for hitting a HTML::a tag with src=http + HTML::img.
         */
        public static final int SRC_IMAGE_ANCHOR_TYPE = 8;

        /**
         * Default HitTestResult, where the target is unknown.
         */
        public static final int UNKNOWN_TYPE = 0;

        private HitTestResult() {
        }
    }
}
