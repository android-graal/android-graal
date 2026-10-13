package android.security;

/**
 * The {@code KeyChain} class provides access to private keys and
 * their corresponding certificate chains in credential storage.
 *
 * <p>Applications accessing the {@code KeyChain} normally go through
 * these steps:
 *
 * <ol>
 *
 * <li>Receive a callback from an {@link javax.net.ssl.X509KeyManager
 * X509KeyManager} that a private key is requested.
 *
 * <li>Call {@link #choosePrivateKeyAlias
 * choosePrivateKeyAlias} to allow the user to select from a
 * list of currently available private keys and corresponding
 * certificate chains. The chosen alias will be returned by the
 * callback {@link KeyChainAliasCallback#alias}, or null if no private
 * key is available or the user cancels the request.
 *
 * <li>Call {@link #getPrivateKey} and {@link #getCertificateChain} to
 * retrieve the credentials to return to the corresponding {@link
 * javax.net.ssl.X509KeyManager} callbacks.
 *
 * </ol>
 *
 * <p>An application may remember the value of a selected alias to
 * avoid prompting the user with {@link #choosePrivateKeyAlias
 * choosePrivateKeyAlias} on subsequent connections. If the alias is
 * no longer valid, null will be returned on lookups using that value
 *
 * <p>An application can request the installation of private keys and
 * certificates via the {@code Intent} provided by {@link
 * #createInstallIntent}. Private keys installed via this {@code
 * Intent} will be accessible via {@link #choosePrivateKeyAlias} while
 * Certificate Authority (CA) certificates will be trusted by all
 * applications through the default {@code X509TrustManager}.
 */
public final class KeyChain {
    /**
     * Broadcast Action: Indicates the contents of the keychain has changed. Sent when a KeyChain
     * entry is added, modified or removed.
     */
    public static final String ACTION_KEYCHAIN_CHANGED = "android.security.action.KEYCHAIN_CHANGED";

    /**
     * Broadcast Action: Indicates that the access permissions for a private key have changed.
     */
    public static final String ACTION_KEY_ACCESS_CHANGED = "android.security.action.KEY_ACCESS_CHANGED";

    /**
     * Broadcast Action: Indicates the trusted storage has changed. Sent when
     * one of this happens:
     *
     * <ul>
     * <li>a new CA is added,
     * <li>an existing CA is removed or disabled,
     * <li>a disabled CA is enabled,
     * <li>trusted storage is reset (all user certs are cleared),
     * <li>when permission to access a private key is changed.
     * </ul>
     *
     * @deprecated Use {@link #ACTION_KEYCHAIN_CHANGED}, {@link #ACTION_TRUST_STORE_CHANGED} or
     * {@link #ACTION_KEY_ACCESS_CHANGED}. Apps that target a version higher than
     * {@link android.os.Build.VERSION_CODES#N_MR1} will only receive this broadcast if they
     * register for it at runtime.
     */
    @Deprecated
    public static final String ACTION_STORAGE_CHANGED = "android.security.STORAGE_CHANGED";

    /**
     * Broadcast Action: Indicates the contents of the trusted certificate store has changed. Sent
     * when one the following occurs:
     *
     * <ul>
     * <li>A pre-installed CA is disabled or re-enabled</li>
     * <li>A CA is added or removed from the trust store</li>
     * </ul>
     */
    public static final String ACTION_TRUST_STORE_CHANGED = "android.security.action.TRUST_STORE_CHANGED";

    /**
     * Optional extra to specify an X.509 certificate to install on
     * the {@code Intent} returned by {@link #createInstallIntent}.
     * The extra value should be a PEM or ASN.1 DER encoded {@code
     * byte[]}. An {@link X509Certificate} can be converted to DER
     * encoded bytes with {@link X509Certificate#getEncoded}.
     *
     * <p>{@link #EXTRA_NAME} may be used to provide a default alias
     * name for the installed certificate.
     */
    public static final String EXTRA_CERTIFICATE = "CERT";

    /**
     * Used as a boolean extra field in {@link #ACTION_KEY_ACCESS_CHANGED} to supply if the key is
     * accessible to the application.
     */
    public static final String EXTRA_KEY_ACCESSIBLE = "android.security.extra.KEY_ACCESSIBLE";

    /**
     * Used as a String extra field in {@link #ACTION_KEY_ACCESS_CHANGED} to supply the alias of
     * the key.
     */
    public static final String EXTRA_KEY_ALIAS = "android.security.extra.KEY_ALIAS";

    /**
     * Optional extra to specify a {@code String} credential name on
     * the {@code Intent} returned by {@link #createInstallIntent}.
     */
    public static final String EXTRA_NAME = "name";

    /**
     * Optional extra for use with the {@code Intent} returned by
     * {@link #createInstallIntent} to specify a PKCS#12 key store to
     * install. The extra value should be a {@code byte[]}. The bytes
     * may come from an external source or be generated with {@link
     * java.security.KeyStore#store} on a "PKCS12" instance.
     *
     * <p>The user will be prompted for the password to load the key store.
     *
     * <p>The key store will be scanned for {@link
     * java.security.KeyStore.PrivateKeyEntry} entries and both the
     * private key and associated certificate chain will be installed.
     *
     * <p>{@link #EXTRA_NAME} may be used to provide a default alias
     * name for the installed credentials.
     */
    public static final String EXTRA_PKCS12 = "PKCS12";

    /**
     * Used by DPC or delegated app in
     * {@link android.app.admin.DeviceAdminReceiver#onChoosePrivateKeyAlias} or
     * {@link android.app.admin.DelegatedAdminReceiver#onChoosePrivateKeyAlias} to identify that
     * the requesting app is not granted access to any key, and nor will the user be able to grant
     * access manually.
     */
    public static final String KEY_ALIAS_SELECTION_DENIED = "android:alias-selection-denied";

    private KeyChain() {
    }
}
