package android.service.quickaccesswallet;

/**
 * A {@code QuickAccessWalletService} provides a list of {@code WalletCard}s shown in the Quick
 * Access Wallet. The Quick Access Wallet allows the user to change their selected payment method
 * and access other important passes, such as tickets and transit passes, without leaving the
 * context of their current app.
 *
 * <p>An {@code QuickAccessWalletService} is only bound to the Android System for the purposes of
 * showing wallet cards if:
 * <ol>
 *   <li>The application hosting the QuickAccessWalletService is also the default NFC payment
 *   application. This means that the same application must also have a
 *   {@link android.nfc.cardemulation.HostApduService} or
 *   {@link android.nfc.cardemulation.OffHostApduService} that requires the
 *   android.permission.BIND_NFC_SERVICE permission.
 *   <li>The user explicitly selected the application as the default payment application in
 *   the Tap &amp; pay settings screen.
 *   <li>The QuickAccessWalletService requires that the binding application hold the
 *   {@code android.permission.BIND_QUICK_ACCESS_WALLET_SERVICE} permission, which only the System
 *   Service can hold.
 *   <li>The user explicitly enables it using Android Settings (the
 *       {@link Settings#ACTION_QUICK_ACCESS_WALLET_SETTINGS} intent can be used to launch it).
 * </ol>
 *
 * <a name="BasicUsage"></a>
 * <h3>Basic usage</h3>
 *
 * <p>The basic Quick Access Wallet process is defined by the workflow below:
 * <ol>
 *   <li>User performs a gesture to bring up the Quick Access Wallet, which is displayed by the
 *   Android System.
 *   <li>The Android System creates a {@link GetWalletCardsRequest}, binds to the
 *   {@link QuickAccessWalletService}, and delivers the request.
 *   <li>The service receives the request through {@link #onWalletCardsRequested}
 *   <li>The service responds by calling {@link GetWalletCardsCallback#onSuccess} with a
 *   {@link GetWalletCardsResponse response} that contains between 1 and
 *   {@link GetWalletCardsRequest#getMaxCards() maxCards} cards.
 *   <li>The Android System displays the Quick Access Wallet containing the provided cards. The
 *   card at the {@link GetWalletCardsResponse#getSelectedIndex() selectedIndex} will initially
 *   be presented as the 'selected' card.
 *   <li>As soon as the cards are displayed, the Android System will notify the service that the
 *   card at the selected index has been selected through {@link #onWalletCardSelected}.
 *   <li>The user interacts with the wallet and may select one or more cards in sequence. Each time
 *   a new card is selected, the Android System will notify the service through
 *   {@link #onWalletCardSelected} and will provide the {@link WalletCard#getCardId() cardId} of the
 *   card that is now selected.
 *   <li>If the user commences an NFC payment, the service may send a {@link WalletServiceEvent}
 *   to the System indicating that the wallet application now needs to show the activity associated
 *   with making a payment. Sending a {@link WalletServiceEvent} of type
 *   {@link WalletServiceEvent#TYPE_NFC_PAYMENT_STARTED} should cause the quick access wallet UI
 *   to be dismissed.
 *   <li>When the wallet is dismissed, the Android System will notify the service through
 *   {@link #onWalletDismissed}.
 * </ol>
 *
 * <p>The workflow is designed to minimize the time that the Android System is bound to the
 * service, but connections may be cached and reused to improve performance and conserve memory.
 * All calls should be considered stateless: if the service needs to keep state between calls, it
 * must do its own state management (keeping in mind that the service's process might be killed
 * by the Android System when unbound; for example, if the device is running low in memory).
 *
 * <p> The service also provides pending intents to override the system's Quick Access activities
 * via the {@link #getTargetActivityPendingIntent} and the
 * {@link #getGestureTargetActivityPendingIntent} method.
 *
 * <p>
 * <a name="ErrorHandling"></a>
 * <h3>Error handling</h3>
 * <p>If the service encountered an error processing the request, it should call
 * {@link GetWalletCardsCallback#onFailure}.
 * For performance reasons, it's paramount that the service calls either
 * {@link GetWalletCardsCallback#onSuccess} or
 * {@link GetWalletCardsCallback#onFailure} for each
 * {@link #onWalletCardsRequested} received - if it doesn't, the request will eventually time out
 * and be discarded by the Android System.
 *
 * <p>
 * <a name="ManifestEntry"></a>
 * <h3>Manifest entry</h3>
 *
 * <p>QuickAccessWalletService must require the permission
 * "android.permission.BIND_QUICK_ACCESS_WALLET_SERVICE".
 *
 * <pre class="prettyprint">
 * {@literal
 * <service
 *     android:name=".MyQuickAccessWalletService"
 *     android:label="@string/my_default_tile_label"
 *     android:icon="@drawable/my_default_icon_label"
 *     android:logo="@drawable/my_wallet_logo"
 *     android:permission="android.permission.BIND_QUICK_ACCESS_WALLET_SERVICE">
 *     <intent-filter>
 *         <action android:name="android.service.quickaccesswallet.QuickAccessWalletService" />
 *         <category android:name="android.intent.category.DEFAULT"/>
 *     </intent-filter>
 *     <meta-data android:name="android.quickaccesswallet"
 *          android:resource="@xml/quickaccesswallet_configuration" />;
 * </service>}
 * </pre>
 * <p>
 * The {@literal <meta-data>} element includes an android:resource attribute that points to an
 * XML resource with further details about the service. The {@code quickaccesswallet_configuration}
 * in the example above specifies an activity that allows the users to view the entire wallet.
 * The following example shows the quickaccesswallet_configuration XML resource:
 * <p>
 * <pre class="prettyprint">
 * {@literal
 * <quickaccesswallet-service
 *   xmlns:android="http://schemas.android.com/apk/res/android"
 *   android:settingsActivity="com.example.android.SettingsActivity"
 *   android:shortcutLongLabel="@string/my_wallet_empty_state_text"
 *   android:shortcutShortLabel="@string/my_wallet_button_text"
 *   android:targetActivity="com.example.android.WalletActivity"/>
 * }
 * </pre>
 *
 * <p>The entry for {@code settingsActivity} should contain the fully qualified class name of an
 * activity that allows the user to modify the settings for this service. The {@code targetActivity}
 * entry should contain the fully qualified class name of an activity that allows the user to view
 * their entire wallet. The {@code targetActivity} will be started with the Intent action
 * {@link #ACTION_VIEW_WALLET} and the {@code settingsActivity} will be started with the Intent
 * action {@link #ACTION_VIEW_WALLET_SETTINGS}.
 *
 * <p>The {@code shortcutShortLabel} and {@code shortcutLongLabel} are used by the QuickAccessWallet
 * in the buttons that navigate to the wallet app. The {@code shortcutShortLabel} is displayed next
 * to the cards that are returned by the service and should be no more than 20 characters. The
 * {@code shortcutLongLabel} is displayed when no cards are returned. This 'empty state' view also
 * displays the service logo, specified by the {@code android:logo} manifest entry. If the logo is
 * not specified, the empty state view will show the app icon instead.
 */
public final class QuickAccessWalletService {
    /**
     * Intent action to launch an activity to display the wallet.
     */
    public static final String ACTION_VIEW_WALLET = "android.service.quickaccesswallet.action.VIEW_WALLET";

    /**
     * Intent action to launch an activity to display quick access wallet settings.
     */
    public static final String ACTION_VIEW_WALLET_SETTINGS = "android.service.quickaccesswallet.action.VIEW_WALLET_SETTINGS";

    /**
     * The {@link Intent} that must be declared as handled by the service. To be supported, the
     * service must also require the
     * {@link android.Manifest.permission#BIND_QUICK_ACCESS_WALLET_SERVICE}
     * permission so that other applications can not abuse it.
     */
    public static final String SERVICE_INTERFACE = "android.service.quickaccesswallet.QuickAccessWalletService";

    /**
     * Name under which a QuickAccessWalletService component publishes information about itself.
     * This meta-data should reference an XML resource containing a
     * <code>&lt;{@link
     * android.R.styleable#QuickAccessWalletService quickaccesswallet-service}&gt;</code> tag. This
     * is a a sample XML file configuring an QuickAccessWalletService:
     * <pre> &lt;quickaccesswallet-service
     *     android:walletActivity="foo.bar.WalletActivity"
     *     . . .
     * /&gt;</pre>
     */
    public static final String SERVICE_META_DATA = "android.quickaccesswallet";

    private QuickAccessWalletService() {
    }
}
