package android.webkit;

/**
 * Manages settings state for a WebView. When a WebView is first created, it
 * obtains a set of default settings. These default settings will be returned
 * from any getter call. A {@code WebSettings} object obtained from
 * {@link WebView#getSettings()} is tied to the life of the WebView. If a WebView has
 * been destroyed, any method call on {@code WebSettings} will throw an
 * {@link IllegalStateException}.
 */
public final class WebSettings {
    /**
     * Enable force dark dependent on the state of the WebView parent view. If the WebView parent
     * view is being automatically force darkened
     * (see: {@link android.view.View#setForceDarkAllowed}), then WebView content will be rendered
     * so as to emulate a dark theme. WebViews that are not attached to the view hierarchy will not
     * be inverted.
     *
     * @see #setForceDark
     * @deprecated refer to {@link #setForceDark}
     */
    @Deprecated
    public static final int FORCE_DARK_AUTO = 1;

    /**
     * Disable force dark, irrespective of the force dark mode of the WebView parent. In this mode,
     * WebView content will always be rendered as-is, regardless of whether native views are being
     * automatically darkened.
     *
     * @see #setForceDark
     * @deprecated refer to {@link #setForceDark}
     */
    @Deprecated
    public static final int FORCE_DARK_OFF = 0;

    /**
     * Unconditionally enable force dark. In this mode WebView content will always be rendered so
     * as to emulate a dark theme.
     *
     * @see #setForceDark
     * @deprecated refer to {@link #setForceDark}
     */
    @Deprecated
    public static final int FORCE_DARK_ON = 2;

    /**
     * Use cached resources when they are available, even if they have expired.
     * Otherwise load resources from the network.
     * Use with {@link #setCacheMode}.
     */
    public static final int LOAD_CACHE_ELSE_NETWORK = 1;

    /**
     * Don't use the network, load from the cache.
     * Use with {@link #setCacheMode}.
     */
    public static final int LOAD_CACHE_ONLY = 3;

    /**
     * Default cache usage mode. If the navigation type doesn't impose any
     * specific behavior, use cached resources when they are available
     * and not expired, otherwise load resources from the network.
     * Use with {@link #setCacheMode}.
     */
    public static final int LOAD_DEFAULT = -1;

    /**
     * Normal cache usage mode. Use with {@link #setCacheMode}.
     *
     * @deprecated This value is obsolete, as from API level
     * {@link android.os.Build.VERSION_CODES#HONEYCOMB} and onwards it has the
     * same effect as {@link #LOAD_DEFAULT}.
     */
    @Deprecated
    public static final int LOAD_NORMAL = 0;

    /**
     * Don't use the cache, load from the network.
     * Use with {@link #setCacheMode}.
     */
    public static final int LOAD_NO_CACHE = 2;

    /**
     * No menu items should be disabled.
     *
     * @see #setDisabledActionModeMenuItems
     */
    public static final int MENU_ITEM_NONE = 0;

    /**
     * Disable all the action mode menu items for text processing.
     * By default WebView searches for activities that are able to handle
     * {@link android.content.Intent#ACTION_PROCESS_TEXT} and show them in the
     * action mode menu. If this flag is set via {@link
     * #setDisabledActionModeMenuItems}, these menu items will be disabled.
     *
     * @see #setDisabledActionModeMenuItems
     */
    public static final int MENU_ITEM_PROCESS_TEXT = 4;

    /**
     * Disable menu item "Share".
     *
     * @see #setDisabledActionModeMenuItems
     */
    public static final int MENU_ITEM_SHARE = 1;

    /**
     * Disable menu item "Web Search".
     *
     * @see #setDisabledActionModeMenuItems
     */
    public static final int MENU_ITEM_WEB_SEARCH = 2;

    /**
     * In this mode, the WebView will allow a secure origin to load content from any other origin,
     * even if that origin is insecure. This is the least secure mode of operation for the WebView,
     * and where possible apps should not set this mode.
     *
     * @see #setMixedContentMode
     */
    public static final int MIXED_CONTENT_ALWAYS_ALLOW = 0;

    /**
     * In this mode, the WebView will attempt to be compatible with the approach of a modern web
     * browser with regard to mixed content. Some insecure content may be allowed to be loaded by
     * a secure origin and other types of content will be blocked. The types of content are allowed
     * or blocked may change release to release and are not explicitly defined.
     *
     * This mode is intended to be used by apps that are not in control of the content that they
     * render but desire to operate in a reasonably secure environment. For highest security, apps
     * are recommended to use {@link #MIXED_CONTENT_NEVER_ALLOW}.
     *
     * @see #setMixedContentMode
     */
    public static final int MIXED_CONTENT_COMPATIBILITY_MODE = 2;

    /**
     * In this mode, the WebView will not allow a secure origin to load content from an insecure
     * origin. This is the preferred and most secure mode of operation for the WebView and apps are
     * strongly advised to use this mode.
     *
     * @see #setMixedContentMode
     */
    public static final int MIXED_CONTENT_NEVER_ALLOW = 1;

    private WebSettings() {
    }
}
