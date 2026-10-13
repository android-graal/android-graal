package android.service.chooser;

/**
 * A service that receives calls from the system when the user is asked to choose
 * a target for an intent explicitly by another app. The calling app must have invoked
 * {@link android.content.Intent#ACTION_CHOOSER ACTION_CHOOSER} as handled by the system;
 * applications do not have the ability to query a ChooserTargetService directly.
 *
 * <p>Which ChooserTargetServices are queried depends on a system-level policy decision
 * made at the moment the chooser is invoked, including but not limited to user time
 * spent with the app package or associated components in the foreground, recency of usage
 * or frequency of usage. These will generally correlate with the order that app targets
 * are shown in the list of intent handlers shown in the system chooser or resolver.</p>
 *
 * <p>To extend this class, you must declare the service in your manifest file with
 * the {@link android.Manifest.permission#BIND_CHOOSER_TARGET_SERVICE} permission
 * and include an intent filter with the {@link #SERVICE_INTERFACE} action. For example:</p>
 * <pre>
 *     &lt;service android:name=".MyChooserTargetService"
 *             android:label="&#64;string/service_name"
 *             android:permission="android.permission.BIND_CHOOSER_TARGET_SERVICE">
 *         &lt;intent-filter>
 *             &lt;action android:name="android.service.chooser.ChooserTargetService" />
 *         &lt;/intent-filter>
 *     &lt;/service>
 * </pre>
 *
 * <p>For the system to query your service, you must add a &lt;meta-data> element to the
 * Activity in your manifest that can handle Intents that you would also like to provide
 * optional deep links for. For example, a chat app might offer deep links to recent active
 * conversations instead of invoking a generic picker after the app itself is chosen as a target.
 * </p>
 *
 * <p>The meta-data element should have the name
 * <code>android.service.chooser.chooser_target_service</code> and a value corresponding to
 * the component name of your service. Example:</p>
 * <pre>
 *     &lt;activity android:name=".MyShareActivity"
 *             android:label="&#64;string/share_activity_label">
 *         &lt;intent-filter>
 *             &lt;action android:name="android.intent.action.SEND" />
 *         &lt;/intent-filter>
 *         &lt;meta-data android:name="android.service.chooser.chooser_target_service"
 *                 android:value=".MyChooserTargetService" />
 *     &lt;/activity>
 * </pre>
 *
 * @deprecated For publishing direct share targets, please follow the instructions in
 * https://developer.android.com/training/sharing/receive.html#providing-direct-share-targets
 * instead.
 */
@Deprecated
public final class ChooserTargetService {
    /**
     * The permission that a ChooserTargetService must require in order to bind to it.
     * If this permission is not enforced the system will skip that ChooserTargetService.
     */
    @Deprecated
    public static final String BIND_PERMISSION = "android.permission.BIND_CHOOSER_TARGET_SERVICE";

    /**
     * The name of the <code>meta-data</code> element that must be present on an
     * <code>activity</code> element in a manifest to link it to a ChooserTargetService
     */
    @Deprecated
    public static final String META_DATA_NAME = "android.service.chooser.chooser_target_service";

    /**
     * The Intent action that a ChooserTargetService must respond to
     */
    @Deprecated
    public static final String SERVICE_INTERFACE = "android.service.chooser.ChooserTargetService";

    private ChooserTargetService() {
    }
}
