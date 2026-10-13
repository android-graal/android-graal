package android.net.http;

/**
 * An engine to process {@link UrlRequest}s, which uses the best HTTP stack available on the current
 * platform. An instance of this class can be created using {@link Builder}.
 */
public final class HttpEngine {
    private HttpEngine() {
    }

    /**
     * A builder for {@link HttpEngine}s, which allows runtime configuration of
     * {@link HttpEngine}. Configuration options are set on the builder and
     * then {@link #build} is called to create the {@link HttpEngine}.
     */
    public static final class Builder {
        /**
         * Setting to disable HTTP cache. Some data may still be temporarily stored in memory.
         * Passed to {@link #setEnableHttpCache}.
         */
        public static final int HTTP_CACHE_DISABLED = 0;

        /**
         * Setting to enable on-disk cache, including HTTP data.
         * {@link #setStoragePath} must be called prior to passing this constant to
         * {@link #setEnableHttpCache}.
         */
        public static final int HTTP_CACHE_DISK = 3;

        /**
         * Setting to enable on-disk cache, excluding HTTP data.
         * {@link #setStoragePath} must be called prior to passing this constant to
         * {@link #setEnableHttpCache}.
         */
        public static final int HTTP_CACHE_DISK_NO_HTTP = 2;

        /**
         * Setting to enable in-memory HTTP cache, including HTTP data.
         * Passed to {@link #setEnableHttpCache}.
         */
        public static final int HTTP_CACHE_IN_MEMORY = 1;

        private Builder() {
        }
    }
}
