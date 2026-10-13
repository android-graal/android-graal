package android.content;

/**
 * This class provides applications access to the content model.
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about using a ContentResolver with content providers, read the
 * <a href="{@docRoot}guide/topics/providers/content-providers.html">Content Providers</a>
 * developer guide.</p>
 * </div>
 */
public final class ContentResolver {
    /**
     * This is the Android platform's generic MIME type to match any MIME
     * type of the form "{@link #CURSOR_ITEM_BASE_TYPE}/{@code SUB_TYPE}".
     * {@code SUB_TYPE} is the sub-type of the application-dependent
     * content, e.g., "audio", "video", "playlist".
     */
    public static final String ANY_CURSOR_ITEM_TYPE = "vnd.android.cursor.item/*";

    /**
     * This is the Android platform's base MIME type for a content: URI
     * containing a Cursor of zero or more items.  Applications should use this
     * as the base type along with their own sub-type of their content: URIs
     * that represent a directory of items.  For example, hypothetical IMAP email
     * client may have a URI
     * <code>content://com.company.provider.imap/inbox</code> for all of the
     * messages in its inbox, whose MIME type would be reported as
     * <code>CURSOR_DIR_BASE_TYPE + "/vnd.company.imap-msg"</code>
     *
     * <p>Note how the base MIME type varies between this and
     * {@link #CURSOR_ITEM_BASE_TYPE} depending on whether there is
     * one single item or multiple items in the data set, while the sub-type
     * remains the same because in either case the data structure contained
     * in the cursor is the same.
     */
    public static final String CURSOR_DIR_BASE_TYPE = "vnd.android.cursor.dir";

    /**
     * This is the Android platform's base MIME type for a content: URI
     * containing a Cursor of a single item.  Applications should use this
     * as the base type along with their own sub-type of their content: URIs
     * that represent a particular item.  For example, hypothetical IMAP email
     * client may have a URI
     * <code>content://com.company.provider.imap/inbox/1</code> for a particular
     * message in the inbox, whose MIME type would be reported as
     * <code>CURSOR_ITEM_BASE_TYPE + "/vnd.company.imap-msg"</code>
     *
     * <p>Compare with {@link #CURSOR_DIR_BASE_TYPE}.
     */
    public static final String CURSOR_ITEM_BASE_TYPE = "vnd.android.cursor.item";

    /**
     * Allows provider to report back to client which query keys are honored in a Cursor.
     *
     * <p>Key identifying a {@code String[]} containing all QUERY_ARG_SORT* arguments
     * honored by the provider. Include this in {@link Cursor} extras {@link Bundle}
     * when any QUERY_ARG_SORT* value was honored during the preparation of the
     * results {@link Cursor}.
     *
     * <p>If present, ALL honored arguments are enumerated in this extra’s payload.
     *
     * @see #QUERY_ARG_SORT_COLUMNS
     * @see #QUERY_ARG_SORT_DIRECTION
     * @see #QUERY_ARG_SORT_COLLATION
     * @see #QUERY_ARG_SORT_LOCALE
     * @see #QUERY_ARG_GROUP_COLUMNS
     */
    public static final String EXTRA_HONORED_ARGS = "android.content.extra.HONORED_ARGS";

    /**
     * An extra boolean describing whether a particular provider supports refresh
     * or not. If a provider supports refresh, it should include this key in its
     * returned Cursor as part of its query call.
     */
    public static final String EXTRA_REFRESH_SUPPORTED = "android.content.extra.REFRESH_SUPPORTED";

    /**
     * An extra {@link Point} describing the optimal size for a requested image
     * resource, in pixels. If a provider has multiple sizes of the image, it
     * should return the image closest to this size.
     *
     * @see #openTypedAssetFileDescriptor(Uri, String, Bundle)
     * @see #openTypedAssetFileDescriptor(Uri, String, Bundle,
     *      CancellationSignal)
     */
    public static final String EXTRA_SIZE = "android.content.extra.SIZE";

    /**
     * Added to {@link Cursor} extras {@link Bundle} to indicate total row count of
     * recordset when paging is supported. Providers must include this when
     * implementing paging support.
     *
     * <p>A provider may return -1 that row count of the recordset is unknown.
     *
     * <p>Providers having returned -1 in a previous query are recommended to
     * send content change notification once (if) full recordset size becomes
     * known.
     */
    public static final String EXTRA_TOTAL_COUNT = "android.content.extra.TOTAL_COUNT";

    /**
     * Flag for {@link #notifyChange(Uri, ContentObserver, int)}: typically set
     * by a {@link ContentProvider} to indicate that this notification is the
     * result of a {@link ContentProvider#delete} call.
     * <p>
     * Sending these detailed flags are optional, but providers are strongly
     * recommended to send them.
     */
    public static final int NOTIFY_DELETE = 16;

    /**
     * Flag for {@link #notifyChange(Uri, ContentObserver, int)}: typically set
     * by a {@link ContentProvider} to indicate that this notification is the
     * result of an {@link ContentProvider#insert} call.
     * <p>
     * Sending these detailed flags are optional, but providers are strongly
     * recommended to send them.
     */
    public static final int NOTIFY_INSERT = 4;

    /**
     * Flag for {@link #notifyChange(Uri, ContentObserver, int)}: if set, this notification
     * will be skipped if it is being delivered to the root URI of a ContentObserver that is
     * using "notify for descendants."  The purpose of this is to allow the provide to send
     * a general notification of "something under X" changed that observers of that specific
     * URI can receive, while also sending a specific URI under X.  It would use this flag
     * when sending the former, so that observers of "X and descendants" only see the latter.
     */
    public static final int NOTIFY_SKIP_NOTIFY_FOR_DESCENDANTS = 2;

    /**
     * Flag for {@link #notifyChange(Uri, ContentObserver, int)}: attempt to sync the change
     * to the network.
     */
    public static final int NOTIFY_SYNC_TO_NETWORK = 1;

    /**
     * Flag for {@link #notifyChange(Uri, ContentObserver, int)}: typically set
     * by a {@link ContentProvider} to indicate that this notification is the
     * result of an {@link ContentProvider#update} call.
     * <p>
     * Sending these detailed flags are optional, but providers are strongly
     * recommended to send them.
     */
    public static final int NOTIFY_UPDATE = 8;

    /**
     * Specifies the list of columns (stored as a {@code String[]}) against
     * which to group results. When column values are identical, multiple
     * records are collapsed together into a single record.
     * <p>
     * Columns present in this list must also be included in the projection
     * supplied to
     * {@link ContentResolver#query(Uri, String[], Bundle, CancellationSignal)}.
     * <p>
     * Apps targeting {@link android.os.Build.VERSION_CODES#R} or higher:
     * <li>{@link ContentProvider} implementations: When preparing data in
     * {@link ContentProvider#query(Uri, String[], Bundle, CancellationSignal)},
     * if group columns is reflected in the returned Cursor, it is strongly
     * recommended that {@link #QUERY_ARG_SORT_COLUMNS} then be included in the
     * array of honored arguments reflected in {@link Cursor} extras
     * {@link Bundle} under {@link #EXTRA_HONORED_ARGS}.
     * <li>When querying a provider, where no QUERY_ARG_SQL* otherwise exists in
     * the arguments {@link Bundle}, the Content framework will attempt to
     * synthesize an QUERY_ARG_SQL* argument using the corresponding
     * QUERY_ARG_SORT* values.
     */
    public static final String QUERY_ARG_GROUP_COLUMNS = "android:query-arg-group-columns";

    /**
     * Specifies the max number of rows to include in a Cursor.
     */
    public static final String QUERY_ARG_LIMIT = "android:query-arg-limit";

    /**
     * Specifies the offset row index within a Cursor.
     */
    public static final String QUERY_ARG_OFFSET = "android:query-arg-offset";

    /**
     * Allows client to specify a hint to the provider declaring which collation
     * to use when sorting values.
     * <p>
     * Providers may support custom collators. When specifying a custom collator
     * the value is determined by the Provider.
     * <p>
     * {@link ContentProvider} implementations: When preparing data in
     * {@link ContentProvider#query(Uri, String[], Bundle, CancellationSignal)},
     * if sort collation is reflected in the returned Cursor, it is strongly
     * recommended that {@link #QUERY_ARG_SORT_COLLATION} then be included in
     * the array of honored arguments reflected in {@link Cursor} extras
     * {@link Bundle} under {@link #EXTRA_HONORED_ARGS}.
     * <p>
     * When querying a provider, where no QUERY_ARG_SQL* otherwise exists in the
     * arguments {@link Bundle}, the Content framework will attempt to
     * synthesize a QUERY_ARG_SQL* argument using the corresponding
     * QUERY_ARG_SORT* values.
     *
     * @see java.text.Collator#PRIMARY
     * @see java.text.Collator#SECONDARY
     * @see java.text.Collator#TERTIARY
     * @see java.text.Collator#IDENTICAL
     */
    public static final String QUERY_ARG_SORT_COLLATION = "android:query-arg-sort-collation";

    /**
     * Specifies the list of columns (stored as a {@code String[]}) against
     * which to sort results. When first column values are identical, records
     * are then sorted based on second column values, and so on.
     * <p>
     * Columns present in this list must also be included in the projection
     * supplied to
     * {@link ContentResolver#query(Uri, String[], Bundle, CancellationSignal)}.
     * <p>
     * Apps targeting {@link android.os.Build.VERSION_CODES#O} or higher:
     * <li>{@link ContentProvider} implementations: When preparing data in
     * {@link ContentProvider#query(Uri, String[], Bundle, CancellationSignal)},
     * if sort columns is reflected in the returned Cursor, it is strongly
     * recommended that {@link #QUERY_ARG_SORT_COLUMNS} then be included in the
     * array of honored arguments reflected in {@link Cursor} extras
     * {@link Bundle} under {@link #EXTRA_HONORED_ARGS}.
     * <li>When querying a provider, where no QUERY_ARG_SQL* otherwise exists in
     * the arguments {@link Bundle}, the Content framework will attempt to
     * synthesize a QUERY_ARG_SQL* argument using the corresponding
     * QUERY_ARG_SORT* values.
     */
    public static final String QUERY_ARG_SORT_COLUMNS = "android:query-arg-sort-columns";

    /**
     * Specifies desired sort order. When unspecified a provider may provide a default
     * sort direction, or choose to return unsorted results.
     *
     * <p>Apps targeting {@link android.os.Build.VERSION_CODES#O} or higher:
     *
     * <li>{@link ContentProvider} implementations: When preparing data in
     * {@link ContentProvider#query(Uri, String[], Bundle, CancellationSignal)}, if sort direction
     * is reflected in the returned Cursor, it is  strongly recommended that
     * {@link #QUERY_ARG_SORT_DIRECTION} then be included in the array of honored arguments
     * reflected in {@link Cursor} extras {@link Bundle} under {@link #EXTRA_HONORED_ARGS}.
     *
     * <li>When querying a provider, where no QUERY_ARG_SQL* otherwise exists in the
     * arguments {@link Bundle}, the Content framework will attempt to synthesize
     * a QUERY_ARG_SQL* argument using the corresponding QUERY_ARG_SORT* values.
     *
     * @see #QUERY_SORT_DIRECTION_ASCENDING
     * @see #QUERY_SORT_DIRECTION_DESCENDING
     */
    public static final String QUERY_ARG_SORT_DIRECTION = "android:query-arg-sort-direction";

    /**
     * Allows client to specify a hint to the provider declaring which locale to
     * use when sorting values.
     * <p>
     * The value is defined as a RFC 3066 locale ID followed by an optional
     * keyword list, which is the locale format used to configure ICU through
     * classes like {@link android.icu.util.ULocale}. This supports requesting
     * advanced sorting options, such as {@code de@collation=phonebook},
     * {@code zh@collation=pinyin}, etc.
     * <p>
     * {@link ContentProvider} implementations: When preparing data in
     * {@link ContentProvider#query(Uri, String[], Bundle, CancellationSignal)},
     * if sort locale is reflected in the returned Cursor, it is strongly
     * recommended that {@link #QUERY_ARG_SORT_LOCALE} then be included in the
     * array of honored arguments reflected in {@link Cursor} extras
     * {@link Bundle} under {@link #EXTRA_HONORED_ARGS}.
     *
     * @see java.util.Locale#Locale(String)
     * @see android.icu.util.ULocale#ULocale(String)
     */
    public static final String QUERY_ARG_SORT_LOCALE = "android:query-arg-sort-locale";

    /**
     * Key for an SQL style {@code GROUP BY} string that may be present in the
     * query Bundle argument passed to
     * {@link ContentProvider#query(Uri, String[], Bundle, CancellationSignal)}.
     *
     * <p><b>Apps targeting {@link android.os.Build.VERSION_CODES#O} or higher are strongly
     * encourage to use structured query arguments in lieu of opaque SQL query clauses.</b>
     *
     * @see #QUERY_ARG_GROUP_COLUMNS
     */
    public static final String QUERY_ARG_SQL_GROUP_BY = "android:query-arg-sql-group-by";

    /**
     * Key for an SQL style {@code HAVING} string that may be present in the
     * query Bundle argument passed to
     * {@link ContentProvider#query(Uri, String[], Bundle, CancellationSignal)}.
     *
     * <p><b>Apps targeting {@link android.os.Build.VERSION_CODES#O} or higher are strongly
     * encourage to use structured query arguments in lieu of opaque SQL query clauses.</b>
     */
    public static final String QUERY_ARG_SQL_HAVING = "android:query-arg-sql-having";

    /**
     * Key for an SQL style {@code LIMIT} string that may be present in the
     * query Bundle argument passed to
     * {@link ContentProvider#query(Uri, String[], Bundle, CancellationSignal)}.
     *
     * <p><b>Apps targeting {@link android.os.Build.VERSION_CODES#O} or higher are strongly
     * encourage to use structured query arguments in lieu of opaque SQL query clauses.</b>
     *
     * @see #QUERY_ARG_LIMIT
     * @see #QUERY_ARG_OFFSET
     */
    public static final String QUERY_ARG_SQL_LIMIT = "android:query-arg-sql-limit";

    /**
     * Key for an SQL style selection string that may be present in the query Bundle argument
     * passed to {@link ContentProvider#query(Uri, String[], Bundle, CancellationSignal)}
     * when called by a legacy client.
     *
     * <p>Clients should never include user supplied values directly in the selection string,
     * as this presents an avenue for SQL injection attacks. In lieu of this, a client
     * should use standard placeholder notation to represent values in a selection string,
     * then supply a corresponding value in {@value #QUERY_ARG_SQL_SELECTION_ARGS}.
     *
     * <p><b>Apps targeting {@link android.os.Build.VERSION_CODES#O} or higher are strongly
     * encourage to use structured query arguments in lieu of opaque SQL query clauses.</b>
     *
     * @see #QUERY_ARG_SORT_COLUMNS
     * @see #QUERY_ARG_SORT_DIRECTION
     * @see #QUERY_ARG_SORT_COLLATION
     * @see #QUERY_ARG_SORT_LOCALE
     */
    public static final String QUERY_ARG_SQL_SELECTION = "android:query-arg-sql-selection";

    /**
     * Key for SQL selection string arguments list.
     *
     * <p>Clients should never include user supplied values directly in the selection string,
     * as this presents an avenue for SQL injection attacks. In lieu of this, a client
     * should use standard placeholder notation to represent values in a selection string,
     * then supply a corresponding value in {@value #QUERY_ARG_SQL_SELECTION_ARGS}.
     *
     * <p><b>Apps targeting {@link android.os.Build.VERSION_CODES#O} or higher are strongly
     * encourage to use structured query arguments in lieu of opaque SQL query clauses.</b>
     *
     * @see #QUERY_ARG_SORT_COLUMNS
     * @see #QUERY_ARG_SORT_DIRECTION
     * @see #QUERY_ARG_SORT_COLLATION
     * @see #QUERY_ARG_SORT_LOCALE
     */
    public static final String QUERY_ARG_SQL_SELECTION_ARGS = "android:query-arg-sql-selection-args";

    /**
     * Key for an SQL style sort string that may be present in the query Bundle argument
     * passed to {@link ContentProvider#query(Uri, String[], Bundle, CancellationSignal)}
     * when called by a legacy client.
     *
     * <p><b>Apps targeting {@link android.os.Build.VERSION_CODES#O} or higher are strongly
     * encourage to use structured query arguments in lieu of opaque SQL query clauses.</b>
     *
     * @see #QUERY_ARG_SORT_COLUMNS
     * @see #QUERY_ARG_SORT_DIRECTION
     * @see #QUERY_ARG_SORT_COLLATION
     * @see #QUERY_ARG_SORT_LOCALE
     */
    public static final String QUERY_ARG_SQL_SORT_ORDER = "android:query-arg-sql-sort-order";

    public static final int QUERY_SORT_DIRECTION_ASCENDING = 0;

    public static final int QUERY_SORT_DIRECTION_DESCENDING = 1;

    public static final String SCHEME_ANDROID_RESOURCE = "android.resource";

    public static final String SCHEME_CONTENT = "content";

    public static final String SCHEME_FILE = "file";

    /**
     * @deprecated instead use
     * {@link #requestSync(android.accounts.Account, String, android.os.Bundle)}
     */
    @Deprecated
    public static final String SYNC_EXTRAS_ACCOUNT = "account";

    /**
     * Indicates that the sync adapter should not proceed with the delete operations,
     * if it determines that there are too many.
     * See {@link SyncResult#tooManyDeletions}
     */
    public static final String SYNC_EXTRAS_DISCARD_LOCAL_DELETIONS = "discard_deletions";

    /**
     * If this extra is set to true then the request will not be retried if it fails.
     */
    public static final String SYNC_EXTRAS_DO_NOT_RETRY = "do_not_retry";

    /**
     * If this extra is set to true, the sync request will be scheduled at the front of the
     * sync request queue, but it is still subject to JobScheduler quota and throttling due to
     * App Standby buckets.
     *
     * <p>This is different from {@link #SYNC_EXTRAS_SCHEDULE_AS_EXPEDITED_JOB}.
     */
    public static final String SYNC_EXTRAS_EXPEDITED = "expedited";

    /**
     * @deprecated instead use
     * {@link #SYNC_EXTRAS_MANUAL}
     */
    @Deprecated
    public static final String SYNC_EXTRAS_FORCE = "force";

    /**
     * If this extra is set to true then any backoffs for the initial attempt (e.g. due to retries)
     * are ignored by the sync scheduler. If this request fails and gets rescheduled then the
     * retries will still honor the backoff.
     */
    public static final String SYNC_EXTRAS_IGNORE_BACKOFF = "ignore_backoff";

    /**
     * If this extra is set to true then the sync settings (like getSyncAutomatically())
     * are ignored by the sync scheduler.
     */
    public static final String SYNC_EXTRAS_IGNORE_SETTINGS = "ignore_settings";

    /**
     * Set by the SyncManager to request that the SyncAdapter initialize itself for
     * the given account/authority pair. One required initialization step is to
     * ensure that {@link #setIsSyncable(android.accounts.Account, String, int)} has been
     * called with a >= 0 value. When this flag is set the SyncAdapter does not need to
     * do a full sync, though it is allowed to do so.
     */
    public static final String SYNC_EXTRAS_INITIALIZE = "initialize";

    /**
     * Setting this extra is the equivalent of setting both {@link #SYNC_EXTRAS_IGNORE_SETTINGS}
     * and {@link #SYNC_EXTRAS_IGNORE_BACKOFF}
     */
    public static final String SYNC_EXTRAS_MANUAL = "force";

    /**
     * Indicates that the sync adapter should proceed with the delete operations,
     * even if it determines that there are too many.
     * See {@link SyncResult#tooManyDeletions}
     */
    public static final String SYNC_EXTRAS_OVERRIDE_TOO_MANY_DELETIONS = "deletions_override";

    /**
     * If this extra is set to true, the sync request will be scheduled
     * only when the device is plugged in. This is equivalent to calling
     * setRequiresCharging(true) on {@link SyncRequest}.
     */
    public static final String SYNC_EXTRAS_REQUIRE_CHARGING = "require_charging";

    /**
     * Run this sync operation as an "expedited job"
     * (see {@link android.app.job.JobInfo.Builder#setExpedited(boolean)}).
     * Normally (if this flag isn't specified), sync operations are executed as regular
     * {@link android.app.job.JobService} jobs.
     *
     * <p> Because Expedited Jobs have various restrictions compared to regular jobs, this flag
     * cannot be combined with certain other flags, otherwise an
     * <code>IllegalArgumentException</code> will be thrown. Notably, because Expedited Jobs do not
     * support various constraints, the following restriction apply:
     * <ul>
     *  <li>Can't be used with {@link #SYNC_EXTRAS_REQUIRE_CHARGING}
     *  <li>Can't be used with {@link #SYNC_EXTRAS_EXPEDITED}
     *  <li>Can't be used on periodic syncs.
     *  <li>When an expedited-job-sync fails and a retry is scheduled, the retried sync will be
     *  scheduled as a regular job unless {@link #SYNC_EXTRAS_IGNORE_BACKOFF} is set.
     * </ul>
     *
     * <p>This is different from {@link #SYNC_EXTRAS_EXPEDITED}.
     */
    public static final String SYNC_EXTRAS_SCHEDULE_AS_EXPEDITED_JOB = "schedule_as_expedited_job";

    /**
     * Indicates that this sync is intended to only upload local changes to the server.
     * For example, this will be set to true if the sync is initiated by a call to
     * {@link ContentResolver#notifyChange(android.net.Uri, android.database.ContentObserver, boolean)}
     */
    public static final String SYNC_EXTRAS_UPLOAD = "upload";

    public static final int SYNC_OBSERVER_TYPE_ACTIVE = 4;

    public static final int SYNC_OBSERVER_TYPE_PENDING = 2;

    public static final int SYNC_OBSERVER_TYPE_SETTINGS = 1;

    private ContentResolver() {
    }
}
