package android.net.ipsec.ike.exceptions;

/**
 * IkeProtocolException is an abstract class that represents the common information for all IKE
 * protocol errors.
 *
 * <p>Error types are as defined by RFC 7296.
 *
 * @see <a href="https://tools.ietf.org/html/rfc7296#section-3.10.1">RFC 7296, Internet Key Exchange
 *     Protocol Version 2 (IKEv2)</a>
 */
public final class IkeProtocolException {
    /**
     * IKE authentication failed
     */
    public static final int ERROR_TYPE_AUTHENTICATION_FAILED = 24;

    /**
     * Child SA in the received packet does not exist
     */
    public static final int ERROR_TYPE_CHILD_SA_NOT_FOUND = 44;

    /**
     * Configuration Payload required but not found in IKE setup
     */
    public static final int ERROR_TYPE_FAILED_CP_REQUIRED = 37;

    /**
     * No internal addresses can be assigned
     */
    public static final int ERROR_TYPE_INTERNAL_ADDRESS_FAILURE = 36;

    /**
     * Unrecognized destination IKE SPI
     */
    public static final int ERROR_TYPE_INVALID_IKE_SPI = 4;

    /**
     * Invalid Key Exchange Payload
     */
    public static final int ERROR_TYPE_INVALID_KE_PAYLOAD = 17;

    /**
     * Invalid major version
     */
    public static final int ERROR_TYPE_INVALID_MAJOR_VERSION = 5;

    /**
     * Invalid message ID
     */
    public static final int ERROR_TYPE_INVALID_MESSAGE_ID = 9;

    /**
     * An IPsec Packet was found to have mismatched Traffic Selectors of the IPsec SA on which it
     * was delivered
     */
    public static final int ERROR_TYPE_INVALID_SELECTORS = 39;

    /**
     * Invalid syntax
     */
    public static final int ERROR_TYPE_INVALID_SYNTAX = 7;

    /**
     * No additional SAa are acceptable
     */
    public static final int ERROR_TYPE_NO_ADDITIONAL_SAS = 35;

    /**
     * No SA Proposal Chosen is acceptable
     */
    public static final int ERROR_TYPE_NO_PROPOSAL_CHOSEN = 14;

    /**
     * Only Traffic Selectors specifying a single pair of addresses are acceptable
     */
    public static final int ERROR_TYPE_SINGLE_PAIR_REQUIRED = 34;

    /**
     * Temporary failure
     */
    public static final int ERROR_TYPE_TEMPORARY_FAILURE = 43;

    /**
     * No Traffic Selectors are acceptable
     */
    public static final int ERROR_TYPE_TS_UNACCEPTABLE = 38;

    /**
     * Unsupported critical payload
     */
    public static final int ERROR_TYPE_UNSUPPORTED_CRITICAL_PAYLOAD = 1;

    private IkeProtocolException() {
    }
}
