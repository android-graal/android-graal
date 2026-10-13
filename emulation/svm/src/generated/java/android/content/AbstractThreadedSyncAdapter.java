package android.content;

/**
 * An abstract implementation of a SyncAdapter that spawns a thread to invoke a sync operation.
 * If a sync operation is already in progress when a sync request is received, an error will be
 * returned to the new request and the existing request will be allowed to continue.
 * However if there is no sync in progress then a thread will be spawned and {@link #onPerformSync}
 * will be invoked on that thread.
 * <p>
 * Syncs can be cancelled at any time by the framework. For example a sync that was not
 * user-initiated and lasts longer than 10 minutes will be considered timed-out and cancelled.
 * Similarly the framework will attempt to determine whether or not an adapter is making progress
 * by monitoring its network activity over the course of a minute. If the network traffic over this
 * window is close enough to zero the sync will be cancelled. You can also request the sync be
 * cancelled via {@link ContentResolver#cancelSync(Account, String)} or
 * {@link ContentResolver#cancelSync(SyncRequest)}.
 * <p>
 * A sync is cancelled by issuing a {@link Thread#interrupt()} on the syncing thread. <strong>Either
 * your code in {@link #onPerformSync(Account, Bundle, String, ContentProviderClient, SyncResult)}
 * must check {@link Thread#interrupted()}, or you you must override one of
 * {@link #onSyncCanceled(Thread)}/{@link #onSyncCanceled()}</strong> (depending on whether or not
 * your adapter supports syncing of multiple accounts in parallel). If your adapter does not
 * respect the cancel issued by the framework you run the risk of your app's entire process being
 * killed.
 * <p>
 * In order to be a sync adapter one must extend this class, provide implementations for the
 * abstract methods and write a service that returns the result of {@link #getSyncAdapterBinder()}
 * in the service's {@link android.app.Service#onBind(android.content.Intent)} when invoked
 * with an intent with action <code>android.content.SyncAdapter</code>. This service
 * must specify the following intent filter and metadata tags in its AndroidManifest.xml file
 * <pre>
 *   &lt;intent-filter&gt;
 *     &lt;action android:name="android.content.SyncAdapter" /&gt;
 *   &lt;/intent-filter&gt;
 *   &lt;meta-data android:name="android.content.SyncAdapter"
 *             android:resource="@xml/syncadapter" /&gt;
 * </pre>
 * The <code>android:resource</code> attribute must point to a resource that looks like:
 * <pre>
 * &lt;sync-adapter xmlns:android="http://schemas.android.com/apk/res/android"
 *    android:contentAuthority="authority"
 *    android:accountType="accountType"
 *    android:userVisible="true|false"
 *    android:supportsUploading="true|false"
 *    android:allowParallelSyncs="true|false"
 *    android:isAlwaysSyncable="true|false"
 *    android:syncAdapterSettingsAction="ACTION_OF_SETTINGS_ACTIVITY"
 * /&gt;
 * </pre>
 * <ul>
 * <li>The <code>android:contentAuthority</code> and <code>android:accountType</code> attributes
 * indicate which content authority and for which account types this sync adapter serves.
 * <li><code>android:userVisible</code> defaults to true and controls whether or not this sync
 * adapter shows up in the Sync Settings screen.
 * <li><code>android:supportsUploading</code> defaults
 * to true and if true an upload-only sync will be requested for all syncadapters associated
 * with an authority whenever that authority's content provider does a
 * {@link ContentResolver#notifyChange(android.net.Uri, android.database.ContentObserver, boolean)}
 * with syncToNetwork set to true.
 * <li><code>android:allowParallelSyncs</code> defaults to false and if true indicates that
 * the sync adapter can handle syncs for multiple accounts at the same time. Otherwise
 * the SyncManager will wait until the sync adapter is not in use before requesting that
 * it sync an account's data.
 * <li><code>android:isAlwaysSyncable</code> defaults to false and if true tells the SyncManager
 * to initialize the isSyncable state to 1 for that sync adapter for each account that is added.
 * <li><code>android:syncAdapterSettingsAction</code> defaults to null and if supplied it
 * specifies an Intent action of an activity that can be used to adjust the sync adapter's
 * sync settings. The activity must live in the same package as the sync adapter.
 * </ul>
 */
public final class AbstractThreadedSyncAdapter {
    /**
     * Kernel event log tag.  Also listed in data/etc/event-log-tags.
     * @deprecated Private constant.  May go away in the next release.
     */
    @Deprecated
    public static final int LOG_SYNC_DETAILS = 2743;

    private AbstractThreadedSyncAdapter() {
    }
}
