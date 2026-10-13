package android.accounts;

/**
 * This class provides access to a centralized registry of the user's
 * online accounts.  The user enters credentials (username and password) once
 * per account, granting applications access to online resources with
 * "one-click" approval.
 *
 * <p>Different online services have different ways of handling accounts and
 * authentication, so the account manager uses pluggable <em>authenticator</em>
 * modules for different <em>account types</em>.  Authenticators (which may be
 * written by third parties) handle the actual details of validating account
 * credentials and storing account information.  For example, Google, Facebook,
 * and Microsoft Exchange each have their own authenticator.
 *
 * <p>Many servers support some notion of an <em>authentication token</em>,
 * which can be used to authenticate a request to the server without sending
 * the user's actual password.  (Auth tokens are normally created with a
 * separate request which does include the user's credentials.)  AccountManager
 * can generate auth tokens for applications, so the application doesn't need to
 * handle passwords directly.  Auth tokens are normally reusable and cached by
 * AccountManager, but must be refreshed periodically.  It's the responsibility
 * of applications to <em>invalidate</em> auth tokens when they stop working so
 * the AccountManager knows it needs to regenerate them.
 *
 * <p>Applications accessing a server normally go through these steps:
 *
 * <ul>
 * <li>Get an instance of AccountManager using {@link #get(Context)}.
 *
 * <li>List the available accounts using {@link #getAccountsByType} or
 * {@link #getAccountsByTypeAndFeatures}.  Normally applications will only
 * be interested in accounts with one particular <em>type</em>, which
 * identifies the authenticator.  Account <em>features</em> are used to
 * identify particular account subtypes and capabilities.  Both the account
 * type and features are authenticator-specific strings, and must be known by
 * the application in coordination with its preferred authenticators.
 *
 * <li>Select one or more of the available accounts, possibly by asking the
 * user for their preference.  If no suitable accounts are available,
 * {@link #addAccount} may be called to prompt the user to create an
 * account of the appropriate type.
 *
 * <li><b>Important:</b> If the application is using a previously remembered
 * account selection, it must make sure the account is still in the list
 * of accounts returned by {@link #getAccountsByType}.  Requesting an auth token
 * for an account no longer on the device results in an undefined failure.
 *
 * <li>Request an auth token for the selected account(s) using one of the
 * {@link #getAuthToken} methods or related helpers.  Refer to the description
 * of each method for exact usage and error handling details.
 *
 * <li>Make the request using the auth token.  The form of the auth token,
 * the format of the request, and the protocol used are all specific to the
 * service you are accessing.  The application may use whatever network and
 * protocol libraries are useful.
 *
 * <li><b>Important:</b> If the request fails with an authentication error,
 * it could be that a cached auth token is stale and no longer honored by
 * the server.  The application must call {@link #invalidateAuthToken} to remove
 * the token from the cache, otherwise requests will continue failing!  After
 * invalidating the auth token, immediately go back to the "Request an auth
 * token" step above.  If the process fails the second time, then it can be
 * treated as a "genuine" authentication failure and the user notified or other
 * appropriate actions taken.
 * </ul>
 *
 * <p>Some AccountManager methods may need to interact with the user to
 * prompt for credentials, present options, or ask the user to add an account.
 * The caller may choose whether to allow AccountManager to directly launch the
 * necessary user interface and wait for the user, or to return an Intent which
 * the caller may use to launch the interface, or (in some cases) to install a
 * notification which the user can select at any time to launch the interface.
 * To have AccountManager launch the interface directly, the caller must supply
 * the current foreground {@link Activity} context.
 *
 * <p>Many AccountManager methods take {@link AccountManagerCallback} and
 * {@link Handler} as parameters.  These methods return immediately and
 * run asynchronously. If a callback is provided then
 * {@link AccountManagerCallback#run} will be invoked on the Handler's
 * thread when the request completes, successfully or not.
 * The result is retrieved by calling {@link AccountManagerFuture#getResult()}
 * on the {@link AccountManagerFuture} returned by the method (and also passed
 * to the callback).  This method waits for the operation to complete (if
 * necessary) and either returns the result or throws an exception if an error
 * occurred during the operation.  To make the request synchronously, call
 * {@link AccountManagerFuture#getResult()} immediately on receiving the
 * future from the method; no callback need be supplied.
 *
 * <p>Requests which may block, including
 * {@link AccountManagerFuture#getResult()}, must never be called on
 * the application's main event thread.  These operations throw
 * {@link IllegalStateException} if they are used on the main thread.
 */
public final class AccountManager {
    /**
     * Action sent as a broadcast Intent by the AccountsService when any account is removed
     * or renamed. Only applications which were able to see the account will receive the intent.
     * Intent extra will include the following fields:
     * <ul>
     * <li> {@link #KEY_ACCOUNT_NAME} - the name of the removed account
     * <li> {@link #KEY_ACCOUNT_TYPE} - the type of the account
     * </ul>
     */
    public static final String ACTION_ACCOUNT_REMOVED = "android.accounts.action.ACCOUNT_REMOVED";

    public static final String ACTION_AUTHENTICATOR_INTENT = "android.accounts.AccountAuthenticator";

    public static final String AUTHENTICATOR_ATTRIBUTES_NAME = "account-authenticator";

    public static final String AUTHENTICATOR_META_DATA_NAME = "android.accounts.AccountAuthenticator";

    public static final int ERROR_CODE_BAD_ARGUMENTS = 7;

    public static final int ERROR_CODE_BAD_AUTHENTICATION = 9;

    public static final int ERROR_CODE_BAD_REQUEST = 8;

    public static final int ERROR_CODE_CANCELED = 4;

    public static final int ERROR_CODE_INVALID_RESPONSE = 5;

    public static final int ERROR_CODE_NETWORK_ERROR = 3;

    public static final int ERROR_CODE_REMOTE_EXCEPTION = 1;

    public static final int ERROR_CODE_UNSUPPORTED_OPERATION = 6;

    public static final String KEY_ACCOUNTS = "accounts";

    public static final String KEY_ACCOUNT_AUTHENTICATOR_RESPONSE = "accountAuthenticatorResponse";

    public static final String KEY_ACCOUNT_MANAGER_RESPONSE = "accountManagerResponse";

    /**
     * Bundle key used for the {@link String} account name in results
     * from methods which return information about a particular account.
     */
    public static final String KEY_ACCOUNT_NAME = "authAccount";

    /**
     * Bundle key used for a {@link Bundle} in result from
     * {@link #startAddAccountSession} and friends which returns session data
     * for installing an account later.
     */
    public static final String KEY_ACCOUNT_SESSION_BUNDLE = "accountSessionBundle";

    /**
     * Bundle key used for the {@link String} account status token in result
     * from {@link #startAddAccountSession} and friends which returns
     * information about a particular account.
     */
    public static final String KEY_ACCOUNT_STATUS_TOKEN = "accountStatusToken";

    /**
     * Bundle key used for the {@link String} account type in results
     * from methods which return information about a particular account.
     */
    public static final String KEY_ACCOUNT_TYPE = "accountType";

    /**
     * The Android package of the caller will be set in the options bundle by the
     * {@link AccountManager} and will be passed to the AccountManagerService and
     * to the AccountAuthenticators. The uid of the caller will be known by the
     * AccountManagerService as well as the AccountAuthenticators so they will be able to
     * verify that the package is consistent with the uid (a uid might be shared by many
     * packages).
     */
    public static final String KEY_ANDROID_PACKAGE_NAME = "androidPackageName";

    public static final String KEY_AUTHENTICATOR_TYPES = "authenticator_types";

    /**
     * Bundle key used for the auth token value in results
     * from {@link #getAuthToken} and friends.
     */
    public static final String KEY_AUTHTOKEN = "authtoken";

    public static final String KEY_AUTH_FAILED_MESSAGE = "authFailedMessage";

    public static final String KEY_AUTH_TOKEN_LABEL = "authTokenLabelKey";

    public static final String KEY_BOOLEAN_RESULT = "booleanResult";

    /**
     * The process id of caller app.
     */
    public static final String KEY_CALLER_PID = "callerPid";

    /**
     * The UID of caller app.
     */
    public static final String KEY_CALLER_UID = "callerUid";

    public static final String KEY_ERROR_CODE = "errorCode";

    public static final String KEY_ERROR_MESSAGE = "errorMessage";

    /**
     * Bundle key used for an {@link Intent} in results from methods that
     * may require the caller to interact with the user.  The Intent can
     * be used to start the corresponding user interface activity.
     */
    public static final String KEY_INTENT = "intent";

    /**
     * Bundle key used to supply the last time the credentials of the account
     * were authenticated successfully. Time is specified in milliseconds since
     * epoch. Associated time is updated on successful authentication of account
     * on adding account, confirming credentials, or updating credentials.
     */
    public static final String KEY_LAST_AUTHENTICATED_TIME = "lastAuthenticatedTime";

    /**
     * Bundle key used to supply the password directly in options to
     * {@link #confirmCredentials}, rather than prompting the user with
     * the standard password prompt.
     */
    public static final String KEY_PASSWORD = "password";

    public static final String KEY_USERDATA = "userdata";

    /**
     * Action sent as a broadcast Intent by the AccountsService when accounts are added, accounts
     * are removed, or an account's credentials (saved password, etc) are changed.
     *
     * @see #addOnAccountsUpdatedListener
     * @see #ACTION_ACCOUNT_REMOVED
     *
     * @deprecated use {@link #addOnAccountsUpdatedListener} to get account updates in runtime.
     */
    @Deprecated
    public static final String LOGIN_ACCOUNTS_CHANGED_ACTION = "android.accounts.LOGIN_ACCOUNTS_CHANGED";

    /**
     * Key to set default visibility for applications which don't satisfy conditions in
     * {@link #PACKAGE_NAME_KEY_LEGACY_VISIBLE}. If the value was not set by authenticator
     * {@link #VISIBILITY_USER_MANAGED_NOT_VISIBLE} is used.
     */
    public static final String PACKAGE_NAME_KEY_LEGACY_NOT_VISIBLE = "android:accounts:key_legacy_not_visible";

    /**
     * Key to set visibility for applications which satisfy one of the following conditions:
     * <ul>
     * <li>Target API level below {@link android.os.Build.VERSION_CODES#O} and have
     * deprecated {@link android.Manifest.permission#GET_ACCOUNTS} permission.
     * </li>
     * <li> Have {@link android.Manifest.permission#GET_ACCOUNTS_PRIVILEGED} permission. </li>
     * <li> Have the same signature as authenticator. </li>
     * <li> Have {@link android.Manifest.permission#READ_CONTACTS} permission and
     * account type may be associated with contacts data - (verified by
     * {@link android.Manifest.permission#WRITE_CONTACTS} permission check for the authenticator).
     * </li>
     * </ul>
     * See {@link #getAccountVisibility}. If the value was not set by authenticator
     * {@link #VISIBILITY_USER_MANAGED_VISIBLE} is used.
     */
    public static final String PACKAGE_NAME_KEY_LEGACY_VISIBLE = "android:accounts:key_legacy_visible";

    /**
     * Account is not visible to given application and only authenticator can grant visibility.
     */
    public static final int VISIBILITY_NOT_VISIBLE = 3;

    /**
     * Account visibility was not set. Default visibility value will be used.
     * See {@link #PACKAGE_NAME_KEY_LEGACY_VISIBLE}, {@link #PACKAGE_NAME_KEY_LEGACY_NOT_VISIBLE}
     */
    public static final int VISIBILITY_UNDEFINED = 0;

    /**
     * Account is not visible to given application, but user can reveal it, for example, using
     * {@link #newChooseAccountIntent(Account, List, String[], String, String, String[], Bundle)}
     */
    public static final int VISIBILITY_USER_MANAGED_NOT_VISIBLE = 4;

    /**
     * Account is visible to given application, but user can revoke visibility.
     */
    public static final int VISIBILITY_USER_MANAGED_VISIBLE = 2;

    /**
     * Account is always visible to given application and only authenticator can revoke visibility.
     */
    public static final int VISIBILITY_VISIBLE = 1;

    private AccountManager() {
    }
}
