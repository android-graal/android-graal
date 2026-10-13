package android.net;

/**
 * Dns resolver class for asynchronous dns querying
 *
 * Note that if a client sends a query with more than 1 record in the question section but
 * the remote dns server does not support this, it may not respond at all, leading to a timeout.
 */
public final class DnsResolver {
    public static final int CLASS_IN = 1;

    /**
     * Indicates that there was an error parsing the response the query.
     * The cause of this error is available via getCause() and is a {@link ParseException}.
     */
    public static final int ERROR_PARSE = 0;

    /**
     * Indicates that there was an error sending the query.
     * The cause of this error is available via getCause() and is an ErrnoException.
     */
    public static final int ERROR_SYSTEM = 1;

    public static final int FLAG_EMPTY = 0;

    public static final int FLAG_NO_CACHE_LOOKUP = 4;

    public static final int FLAG_NO_CACHE_STORE = 2;

    public static final int FLAG_NO_RETRY = 1;

    public static final int TYPE_A = 1;

    public static final int TYPE_AAAA = 28;

    private DnsResolver() {
    }
}
