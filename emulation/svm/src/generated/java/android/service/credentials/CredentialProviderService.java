package android.service.credentials;

/**
 * Service to be extended by credential providers, in order to return user credentials
 * to the framework.
 */
public final class CredentialProviderService {
    /**
     * Intent extra: The {@link BeginGetCredentialRequest} attached with
     * the {@code pendingIntent} that is invoked when the user selects an
     * authentication entry (intending to unlock the provider app) on the UI.
     *
     * <p>When a provider app receives a {@link BeginGetCredentialRequest} through the
     * {@link CredentialProviderService#onBeginGetCredential} call, it can construct the
     * {@link BeginGetCredentialResponse} with either an authentication {@link Action} (if the app
     * is locked), or a {@link BeginGetCredentialResponse} (if the app is unlocked). In the former
     * case, i.e. the app is locked, user will be shown the authentication action. When selected,
     * the underlying {@link PendingIntent} will be invoked which will lead the user to provider's
     * unlock activity. This pending intent will also contain the original
     * {@link BeginGetCredentialRequest} to be retrieved and processed after the unlock
     * flow is complete.
     *
     * <p>After the app is unlocked, the {@link BeginGetCredentialResponse} must be constructed
     * using a {@link BeginGetCredentialResponse}, which must be set on an {@link Intent} as an
     * intent extra against CredentialProviderService#EXTRA_CREDENTIALS_RESPONSE_CONTENT}.
     * This intent should then be set as a result through {@link android.app.Activity#setResult}
     * before finishing the activity.
     *
     * <p>
     * Type: {@link BeginGetCredentialRequest}
     */
    public static final String EXTRA_BEGIN_GET_CREDENTIAL_REQUEST = "android.service.credentials.extra.BEGIN_GET_CREDENTIAL_REQUEST";

    /**
     * Intent extra: The result of an authentication flow, to be set on finish of the
     * {@link android.app.Activity} invoked through the {@link android.app.PendingIntent} set on
     * an authentication {@link Action}, as part of the original
     * {@link BeginGetCredentialResponse}. This result should contain the actual content,
     * including credential entries and action entries, to be shown on the selector.
     *
     * <p>
     * Type: {@link BeginGetCredentialResponse}
     */
    public static final String EXTRA_BEGIN_GET_CREDENTIAL_RESPONSE = "android.service.credentials.extra.BEGIN_GET_CREDENTIAL_RESPONSE";

    /**
     * Intent extra: The failure exception set at the final stage of a create flow.
     * This exception is set at the finishing result of the {@link android.app.Activity}
     * invoked by the {@link PendingIntent} , when a user selects the {@link CreateEntry}
     * that contained the {@link PendingIntent} in question.
     *
     * <p>
     * Type: {@link android.credentials.CreateCredentialException}
     */
    public static final String EXTRA_CREATE_CREDENTIAL_EXCEPTION = "android.service.credentials.extra.CREATE_CREDENTIAL_EXCEPTION";

    /**
     * Intent extra: The {@link android.credentials.CreateCredentialRequest} attached with
     * the {@code pendingIntent} that is invoked when the user selects a {@link CreateEntry}
     * returned as part of the {@link BeginCreateCredentialResponse}
     *
     * <p>
     * Type: {@link android.service.credentials.CreateCredentialRequest}
     */
    public static final String EXTRA_CREATE_CREDENTIAL_REQUEST = "android.service.credentials.extra.CREATE_CREDENTIAL_REQUEST";

    /**
     * Intent extra: The result of a create flow operation, to be set on finish of the
     * {@link android.app.Activity} invoked through the {@code pendingIntent} set on
     * a {@link CreateEntry}.
     *
     * <p>
     * Type: {@link android.credentials.CreateCredentialResponse}
     */
    public static final String EXTRA_CREATE_CREDENTIAL_RESPONSE = "android.service.credentials.extra.CREATE_CREDENTIAL_RESPONSE";

    /**
     * Intent extra: The failure exception set at the final stage of a get flow.
     * This exception is set at the finishing result of the {@link android.app.Activity}
     * invoked by the {@link PendingIntent} , when a user selects the {@link CredentialEntry}
     * that contained the {@link PendingIntent} in question.
     *
     * <p>The result must be set through {@link android.app.Activity#setResult} as an intent extra
     *
     * <p>
     * Type: {@link android.credentials.GetCredentialException}
     */
    public static final String EXTRA_GET_CREDENTIAL_EXCEPTION = "android.service.credentials.extra.GET_CREDENTIAL_EXCEPTION";

    /**
     * Intent extra: The {@link GetCredentialRequest} attached with
     * the {@code pendingIntent} that is invoked when the user selects a {@link CredentialEntry}
     * returned as part of the {@link BeginGetCredentialResponse}
     *
     * <p>
     * Type: {@link GetCredentialRequest}
     */
    public static final String EXTRA_GET_CREDENTIAL_REQUEST = "android.service.credentials.extra.GET_CREDENTIAL_REQUEST";

    /**
     * Intent extra: The result of a get credential flow operation, to be set on finish of the
     * {@link android.app.Activity} invoked through the {@code pendingIntent} set on
     * a {@link CredentialEntry}.
     *
     * <p>
     * Type: {@link android.credentials.GetCredentialResponse}
     */
    public static final String EXTRA_GET_CREDENTIAL_RESPONSE = "android.service.credentials.extra.GET_CREDENTIAL_RESPONSE";

    /**
     * The {@link Intent} that must be declared as handled by the service. The service must also
     * require the {android.Manifest.permission#BIND_CREDENTIAL_PROVIDER_SERVICE} permission
     * so that only the system can bind to it.
     */
    public static final String SERVICE_INTERFACE = "android.service.credentials.CredentialProviderService";

    /**
     * Name under which a Credential Provider service component publishes information
     * about itself.  This meta-data must reference an XML resource containing
     * an
     * <code>&lt;{@link android.R.styleable#CredentialProvider credential-provider}&gt;</code>
     * tag.
     *
     * For example (AndroidManifest.xml):
     * <code>
     * <meta-data
     *         android:name="android.credentials.provider"
     *          android:resource="@xml/provider"/>
     * </code>
     *
     * For example (xml/provider.xml):
     * <code>
     * <credential-provider xmlns:android="http://schemas.android.com/apk/res/android"
     *       android:settingsSubtitle="@string/providerSubtitle">
     *      <capabilities>
     *          <capability>@string/passwords</capability>
     *          <capability>@string/passkeys</capability>
     *      </capabilities>
     *      <capability name="android.credentials.TYPE_PASSWORD_CREDENTIAL" />
     *      <capability name="android.credentials.TYPE_PUBLIC_KEY_CREDENTIAL" />
     *  </credential-provider>
     * </code>
     */
    public static final String SERVICE_META_DATA = "android.credentials.provider";

    private CredentialProviderService() {
    }
}
