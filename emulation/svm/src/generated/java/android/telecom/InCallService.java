package android.telecom;

/**
 * This service is implemented by an app that wishes to provide functionality for managing
 * phone calls.
 * <h2>Becoming the Default Phone App</h2>
 * The default dialer/phone app is one which provides the in-call user interface while the device is
 * in a call.  It also provides the user with a means to initiate calls and see a history of calls
 * on their device.  A device is bundled with a system provided default dialer/phone app.  The user
 * may choose a single app to take over this role from the system app.  An app which wishes to
 * fulfill this role uses the {@link android.app.role.RoleManager} to request that they fill the
 * {@link android.app.role.RoleManager#ROLE_DIALER} role.
 * <p>
 * The default phone app provides a user interface while the device is in a call, and the device is
 * not in car mode (i.e. {@link UiModeManager#getCurrentModeType()} is not
 * {@link android.content.res.Configuration#UI_MODE_TYPE_CAR}).
 * <p>
 * In order to fill the {@link android.app.role.RoleManager#ROLE_DIALER} role, an app must meet a
 * number of requirements:
 * <ul>
 *     <li>It must handle the {@link Intent#ACTION_DIAL} intent.  This means the app must provide
 *     a dial pad UI for the user to initiate outgoing calls.</li>
 *     <li>It must fully implement the {@link InCallService} API and provide both an incoming call
 *     UI, as well as an ongoing call UI.</li>
 * </ul>
 * <p>
 * Note: If the app filling the {@link android.app.role.RoleManager#ROLE_DIALER} returns a
 * {@code null} {@link InCallService} during binding, the Telecom framework will automatically fall
 * back to using the dialer app preloaded on the device.  The system will display a notification to
 * the user to let them know that their call was continued using the preloaded dialer app.  Your
 * app should never return a {@code null} binding; doing so means it does not fulfil the
 * requirements of {@link android.app.role.RoleManager#ROLE_DIALER}.
 * <p>
 * Note: If your app fills {@link android.app.role.RoleManager#ROLE_DIALER} and makes changes at
 * runtime which cause it to no longer fulfil the requirements of this role,
 * {@link android.app.role.RoleManager} will automatically remove your app from the role and close
 * your app.  For example, if you use
 * {@link android.content.pm.PackageManager#setComponentEnabledSetting(ComponentName, int, int)} to
 * programmatically disable the {@link InCallService} your app declares in its manifest, your app
 * will no longer fulfil the requirements expected of
 * {@link android.app.role.RoleManager#ROLE_DIALER}.
 * <p>
 * The preloaded dialer will ALWAYS be used when the user places an emergency call, even if your
 * app fills the {@link android.app.role.RoleManager#ROLE_DIALER} role.  To ensure an optimal
 * experience when placing an emergency call, the default dialer should ALWAYS use
 * {@link android.telecom.TelecomManager#placeCall(Uri, Bundle)} to place calls (including
 * emergency calls).  This ensures that the platform is able to verify that the request came from
 * the default dialer.  If a non-preloaded dialer app uses {@link Intent#ACTION_CALL} to place an
 * emergency call, it will be raised to the preloaded dialer app using {@link Intent#ACTION_DIAL}
 * for confirmation; this is a suboptimal user experience.
 * <p>
 * Below is an example manifest registration for an {@code InCallService}. The meta-data
 * {@link TelecomManager#METADATA_IN_CALL_SERVICE_UI} indicates that this particular
 * {@code InCallService} implementation intends to replace the built-in in-call UI.
 * The meta-data {@link TelecomManager#METADATA_IN_CALL_SERVICE_RINGING} indicates that this
 * {@link InCallService} will play the ringtone for incoming calls.  See
 * <a href="#incomingCallNotification">below</a> for more information on showing the incoming call
 * UI and playing the ringtone in your app.
 * <pre>
 * {@code
 * <service android:name="your.package.YourInCallServiceImplementation"
 *          android:permission="android.permission.BIND_INCALL_SERVICE"
 *          android:exported="true">
 *      <meta-data android:name="android.telecom.IN_CALL_SERVICE_UI" android:value="true" />
 *      <meta-data android:name="android.telecom.IN_CALL_SERVICE_RINGING"
 *          android:value="true" />
 *      <intent-filter>
 *          <action android:name="android.telecom.InCallService"/>
 *      </intent-filter>
 * </service>
 * }
 * </pre>
 *
 * <em>Note: You should NOT mark your {@link InCallService} with the attribute
 * {@code android:exported="false"}; doing so can result in a failure to bind to your implementation
 * during calls.</em>
 * <p>
 * In addition to implementing the {@link InCallService} API, you must also declare an activity in
 * your manifest which handles the {@link Intent#ACTION_DIAL} intent.  The example below illustrates
 * how this is done:
 * <pre>
 * {@code
 * <activity android:name="your.package.YourDialerActivity"
 *           android:label="@string/yourDialerActivityLabel">
 *      <intent-filter>
 *           <action android:name="android.intent.action.DIAL" />
 *           <category android:name="android.intent.category.DEFAULT" />
 *      </intent-filter>
 *      <intent-filter>
 *           <action android:name="android.intent.action.DIAL" />
 *           <category android:name="android.intent.category.DEFAULT" />
 *           <data android:scheme="tel" />
 *      </intent-filter>
 * </activity>
 * }
 * </pre>
 * <p>
 * When a user installs your application and runs it for the first time, you should use the
 * {@link android.app.role.RoleManager} to prompt the user to see if they would like your app to
 * be the new default phone app.
 * <p id="requestRole">
 * The code below shows how your app can request to become the default phone/dialer app:
 * <pre>
 * {@code
 * private static final int REQUEST_ID = 1;
 *
 * public void requestRole() {
 *     RoleManager roleManager = (RoleManager) getSystemService(ROLE_SERVICE);
 *     Intent intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_DIALER);
 *     startActivityForResult(intent, REQUEST_ID);
 * }
 *
 * &#64;Override
 * public void onActivityResult(int requestCode, int resultCode, Intent data) {
 *     if (requestCode == REQUEST_ID) {
 *         if (resultCode == android.app.Activity.RESULT_OK) {
 *             // Your app is now the default dialer app
 *         } else {
 *             // Your app is not the default dialer app
 *         }
 *     }
 * }
 * }
 *
 * </pre>
 * <p id="companionInCallService">
 * <h3>Access to InCallService for Wearable Devices</h3>
 * <ol>
 * If your app is a third-party companion app and wants to access InCallService APIs, what your
 * app could do are:
 * <p>
 *   <ol>
 *     <li> Declare MANAGE_ONGOING_CALLS permission in your manifest
 *     <li> Associate with a physical wearable device via the
 *          {@link android.companion.CompanionDeviceManager} API as a companion app. See:
 *          https://developer.android.com/guide/topics/connectivity/companion-device-pairing
 *     <li> Implement this InCallService with BIND_INCALL_SERVICE permission
 *   </ol>
 * </ol>
 * <p>
 *
 * </pre>
 * <p id="incomingCallNotification">
 * <h3>Showing the Incoming Call Notification</h3>
 * When your app receives a new incoming call via {@link InCallService#onCallAdded(Call)}, it is
 * responsible for displaying an incoming call UI for the incoming call.  It should do this using
 * {@link android.app.NotificationManager} APIs to post a new incoming call notification.
 * <p>
 * Where your app declares the meta-data {@link TelecomManager#METADATA_IN_CALL_SERVICE_RINGING}, it
 * is responsible for playing the ringtone for incoming calls.  Your app should create a
 * {@link android.app.NotificationChannel} which specifies the desired ringtone.  For example:
 * <pre><code>
 * NotificationChannel channel = new NotificationChannel(YOUR_CHANNEL_ID, "Incoming Calls",
 *          NotificationManager.IMPORTANCE_MAX);
 * // other channel setup stuff goes here.
 *
 * // We'll use the default system ringtone for our incoming call notification channel.  You can
 * // use your own audio resource here.
 * Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
 * channel.setSound(ringtoneUri, new AudioAttributes.Builder()
 *          // Setting the AudioAttributes is important as it identifies the purpose of your
 *          // notification sound.
 *          .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
 *          .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
 *      .build());
 *
 * NotificationManager mgr = getSystemService(NotificationManager.class);
 * mgr.createNotificationChannel(channel);
 * </code></pre>
 * <p>
 * When your app receives a new incoming call, it creates a {@link android.app.Notification} for the
 * incoming call and associates it with your incoming call notification channel. You can specify a
 * {@link android.app.PendingIntent} on the notification which will launch your full screen
 * incoming call UI.  The notification manager framework will display your notification as a
 * heads-up notification if the user is actively using the phone.  When the user is not using the
 * phone, your full-screen incoming call UI is used instead.
 * For example:
 * <pre><code>{@code
 * // Create an intent which triggers your fullscreen incoming call user interface.
 * Intent intent = new Intent(Intent.ACTION_MAIN, null);
 * intent.setFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION | Intent.FLAG_ACTIVITY_NEW_TASK);
 * intent.setClass(context, YourIncomingCallActivity.class);
 * PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_MUTABLE_UNAUDITED);
 *
 * // Build the notification as an ongoing high priority item; this ensures it will show as
 * // a heads up notification which slides down over top of the current content.
 * final Notification.Builder builder = new Notification.Builder(context);
 * builder.setOngoing(true);
 * builder.setPriority(Notification.PRIORITY_HIGH);
 *
 * // Set notification content intent to take user to the fullscreen UI if user taps on the
 * // notification body.
 * builder.setContentIntent(pendingIntent);
 * // Set full screen intent to trigger display of the fullscreen UI when the notification
 * // manager deems it appropriate.
 * builder.setFullScreenIntent(pendingIntent, true);
 *
 * // Setup notification content.
 * builder.setSmallIcon( yourIconResourceId );
 * builder.setContentTitle("Your notification title");
 * builder.setContentText("Your notification content.");
 *
 * // Use builder.addAction(..) to add buttons to answer or reject the call.
 *
 * NotificationManager notificationManager = mContext.getSystemService(
 *     NotificationManager.class);
 * notificationManager.notify(YOUR_CHANNEL_ID, YOUR_TAG, YOUR_ID, builder.build());
 * }</pre>
 * <p>
 */
public final class InCallService {
    /**
     * The {@link Intent} that must be declared as handled by the service.
     */
    public static final String SERVICE_INTERFACE = "android.telecom.InCallService";

    private InCallService() {
    }
}
