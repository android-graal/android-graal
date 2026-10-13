package android.accounts;

/**
 * Abstract base class for creating AccountAuthenticators.
 * In order to be an authenticator one must extend this class, provide implementations for the
 * abstract methods, and write a service that returns the result of {@link #getIBinder()}
 * in the service's {@link android.app.Service#onBind(android.content.Intent)} when invoked
 * with an intent with action {@link AccountManager#ACTION_AUTHENTICATOR_INTENT}. This service
 * must specify the following intent filter and metadata tags in its AndroidManifest.xml file
 * <pre>
 *   &lt;intent-filter&gt;
 *     &lt;action android:name="android.accounts.AccountAuthenticator" /&gt;
 *   &lt;/intent-filter&gt;
 *   &lt;meta-data android:name="android.accounts.AccountAuthenticator"
 *             android:resource="@xml/authenticator" /&gt;
 * </pre>
 * The <code>android:resource</code> attribute must point to a resource that looks like:
 * <pre>
 * &lt;account-authenticator xmlns:android="http://schemas.android.com/apk/res/android"
 *    android:accountType="typeOfAuthenticator"
 *    android:icon="@drawable/icon"
 *    android:smallIcon="@drawable/miniIcon"
 *    android:label="@string/label"
 *    android:accountPreferences="@xml/account_preferences"
 * /&gt;
 * </pre>
 * Replace the icons and labels with your own resources. The <code>android:accountType</code>
 * attribute must be a string that uniquely identifies your authenticator and will be the same
 * string that user will use when making calls on the {@link AccountManager} and it also
 * corresponds to {@link Account#type} for your accounts. One user of the android:icon is the
 * "Account & Sync" settings page and one user of the android:smallIcon is the Contact Application's
 * tab panels.
 * <p>
 * The preferences attribute points to a PreferenceScreen xml hierarchy that contains
 * a list of PreferenceScreens that can be invoked to manage the authenticator. An example is:
 * <pre>
 * &lt;PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android"&gt;
 *    &lt;PreferenceCategory android:title="@string/title_fmt" /&gt;
 *    &lt;PreferenceScreen
 *         android:key="key1"
 *         android:title="@string/key1_action"
 *         android:summary="@string/key1_summary"&gt;
 *         &lt;intent
 *             android:action="key1.ACTION"
 *             android:targetPackage="key1.package"
 *             android:targetClass="key1.class" /&gt;
 *     &lt;/PreferenceScreen&gt;
 * &lt;/PreferenceScreen&gt;
 * </pre>
 *
 * <p>
 * The standard pattern for implementing any of the abstract methods is the following:
 * <ul>
 * <li> If the supplied arguments are enough for the authenticator to fully satisfy the request
 * then it will do so and return a {@link Bundle} that contains the results.
 * <li> If the authenticator needs information from the user to satisfy the request then it
 * will create an {@link Intent} to an activity that will prompt the user for the information
 * and then carry out the request. This intent must be returned in a Bundle as key
 * {@link AccountManager#KEY_INTENT}.
 * <p>
 * The activity needs to return the final result when it is complete so the Intent should contain
 * the {@link AccountAuthenticatorResponse} as
 * {@link AccountManager#KEY_ACCOUNT_AUTHENTICATOR_RESPONSE}.
 * The activity must then call {@link AccountAuthenticatorResponse#onResult} or
 * {@link AccountAuthenticatorResponse#onError} when it is complete.
 * <li> If the authenticator cannot synchronously process the request and return a result then it
 * may choose to return null and then use the AccountManagerResponse to send the result
 * when it has completed the request. This asynchronous option is not available for the
 * {@link #addAccount} method, which must complete synchronously.
 * </ul>
 * <p>
 * The following descriptions of each of the abstract authenticator methods will not describe the
 * possible asynchronous nature of the request handling and will instead just describe the input
 * parameters and the expected result.
 * <p>
 * When writing an activity to satisfy these requests one must pass in the AccountManagerResponse
 * and return the result via that response when the activity finishes (or whenever else the
 * activity author deems it is the correct time to respond).
 */
public final class AbstractAccountAuthenticator {
    /**
     * Bundle key used for the {@code long} expiration time (in millis from the unix epoch) of the
     * associated auth token.
     *
     * @see #getAuthToken
     */
    public static final String KEY_CUSTOM_TOKEN_EXPIRY = "android.accounts.expiry";

    private AbstractAccountAuthenticator() {
    }
}
