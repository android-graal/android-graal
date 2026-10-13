package android.security.identity;

/**
 * An interface to a secure store for user identity documents.
 *
 * <p>This interface is deliberately fairly general and abstract.  To the extent possible,
 * specification of the message formats and semantics of communication with credential
 * verification devices and issuing authorities (IAs) is out of scope. It provides the
 * interface with secure storage but a credential-specific Android application will be
 * required to implement the presentation and verification protocols and processes
 * appropriate for the specific credential type.
 *
 * <p>Multiple credentials can be created.  Each credential comprises:</p>
 * <ul>
 * <li>A document type, which is a string.</li>
 *
 * <li>A set of namespaces, which serve to disambiguate value names. It is recommended
 * that namespaces be structured as reverse domain names so that IANA effectively serves
 * as the namespace registrar.</li>
 *
 * <li>For each namespace, a set of name/value pairs, each with an associated set of
 * access control profile IDs.  Names are strings and values are typed and can be any
 * value supported by <a href="http://cbor.io/">CBOR</a>.</li>
 *
 * <li>A set of access control profiles (up to 32), each with a profile ID and a specification
 * of the conditions which satisfy the profile's requirements.</li>
 *
 * <li>An asymmetric key pair which is used to authenticate the credential to the Issuing
 * Authority, called the <em>CredentialKey</em>.</li>
 *
 * <li>A set of zero or more named reader authentication public keys, which are used to
 * authenticate an authorized reader to the credential.</li>
 *
 * <li>A set of named signing keys, which are used to sign collections of values and session
 * transcripts.</li>
 * </ul>
 *
 * <p>Implementing support for user identity documents in secure storage requires dedicated
 * hardware-backed support and may not always be available.
 *
 * <p>Two different credential stores exist - the <em>default</em> store and the
 * <em>direct access</em> store. Most often credentials will be accessed through the default
 * store but that requires that the Android device be powered up and fully functional.
 * It is desirable to allow identity credential usage when the Android device's battery is too
 * low to boot the Android operating system, so direct access to the secure hardware via NFC
 * may allow data retrieval, if the secure hardware chooses to implement it.
 *
 * <p>Credentials provisioned to the direct access store should <strong>always</strong> use reader
 * authentication to protect data elements. The reason for this is user authentication or user
 * approval of data release is not possible when the device is off.
 *
 * <p>The Identity Credential API is designed to be able to evolve and change over time
 * but still provide 100% backwards compatibility. This is complicated by the fact that
 * there may be a version skew between the API used by the application and the version
 * implemented in secure hardware. To solve this problem, the API provides for a way
 * for the application to query which feature version the hardware implements (if any
 * at all) using
 * {@link android.content.pm#FEATURE_IDENTITY_CREDENTIAL_HARDWARE} and
 * {@link android.content.pm#FEATURE_IDENTITY_CREDENTIAL_HARDWARE_DIRECT_ACCESS}.
 * Methods which only work on certain feature versions are clearly documented as
 * such.
 */
public final class IdentityCredentialStore {
    /**
     * Specifies that the cipher suite that will be used to secure communications between the reader
     * and the prover is using the following primitives
     *
     * <ul>
     * <li>ECKA-DH (Elliptic Curve Key Agreement Algorithm - Diffie-Hellman, see BSI TR-03111).</li>
     *
     * <li>HKDF-SHA-256 (see RFC 5869).</li>
     *
     * <li>AES-256-GCM (see NIST SP 800-38D).</li>
     *
     * <li>HMAC-SHA-256 (see RFC 2104).</li>
     * </ul>
     *
     * <p>The exact way these primitives are combined to derive the session key is specified in
     * section 9.2.1.4 of ISO/IEC 18013-5 (see description of cipher suite '1').<p>
     *
     * <p>
     * At present this is the only supported cipher suite.
     */
    public static final int CIPHERSUITE_ECDHE_HKDF_ECDSA_WITH_AES_256_GCM_SHA256 = 1;

    private IdentityCredentialStore() {
    }
}
