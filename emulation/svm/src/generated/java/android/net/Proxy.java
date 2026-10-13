package android.net;

/**
 * A convenience class for accessing the user and default proxy
 * settings.
 */
public final class Proxy {
    /**
     * Used to notify an app that's caching the proxy that either the default
     * connection has changed or any connection's proxy has changed. The new
     * proxy should be queried using {@link ConnectivityManager#getDefaultProxy()}.
     *
     * <p class="note">This is a protected intent that can only be sent by the system
     */
    public static final String PROXY_CHANGE_ACTION = "android.intent.action.PROXY_CHANGE";

    private Proxy() {
    }
}
