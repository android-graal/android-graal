package android.net.http;

/**
 * This class represents a set of one or more SSL errors and the associated SSL
 * certificate.
 */
public final class SslError {
    /**
     * The date of the certificate is invalid
     */
    public static final int SSL_DATE_INVALID = 4;

    /**
     * The certificate has expired
     */
    public static final int SSL_EXPIRED = 1;

    /**
     * Hostname mismatch
     */
    public static final int SSL_IDMISMATCH = 2;

    /**
     * A generic error occurred
     */
    public static final int SSL_INVALID = 5;

    /**
     * The number of different SSL errors.
     * @deprecated This constant is not necessary for using the SslError API and
     *             can change from release to release.
     */
    @Deprecated
    public static final int SSL_MAX_ERROR = 6;

    /**
     * The certificate is not yet valid
     */
    public static final int SSL_NOTYETVALID = 0;

    /**
     * The certificate authority is not trusted
     */
    public static final int SSL_UNTRUSTED = 3;

    private SslError() {
    }
}
