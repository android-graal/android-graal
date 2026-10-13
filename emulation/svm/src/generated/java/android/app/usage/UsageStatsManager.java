package android.app.usage;

/**
 * Provides access to device usage history and statistics. Usage data is aggregated into
 * time intervals: days, weeks, months, and years.
 * <p />
 * When requesting usage data since a particular time, the request might look something like this:
 * <pre>
 * PAST                   REQUEST_TIME                    TODAY                   FUTURE
 * ————————————————————————————||———————————————————————————¦-----------------------|
 *                        YEAR ||                           ¦                       |
 * ————————————————————————————||———————————————————————————¦-----------------------|
 *  MONTH            |         ||                MONTH      ¦                       |
 * ——————————————————|—————————||———————————————————————————¦-----------------------|
 *   |      WEEK     |     WEEK||    |     WEEK     |     WE¦EK     |      WEEK     |
 * ————————————————————————————||———————————————————|———————¦-----------------------|
 *                             ||           |DAY|DAY|DAY|DAY¦DAY|DAY|DAY|DAY|DAY|DAY|
 * ————————————————————————————||———————————————————————————¦-----------------------|
 * </pre>
 * A request for data in the middle of a time interval will include that interval.
 * <p/>
 * <b>NOTE:</b> Most methods on this API require the permission
 * android.permission.PACKAGE_USAGE_STATS. However, declaring the permission implies intention to
 * use the API and the user of the device still needs to grant permission through the Settings
 * application.
 * See {@link android.provider.Settings#ACTION_USAGE_ACCESS_SETTINGS}.
 * Methods which only return the information for the calling package do not require this permission.
 * E.g. {@link #getAppStandbyBucket()} and {@link #queryEventsForSelf(long, long)}.
 */
public final class UsageStatsManager {
    /**
     * A String extra, when used with {@link UsageEvents.Event#getExtras}, that indicates
     * the action of the user interaction associated with the event. The action cannot be
     * more than 127 characters, longer value will be truncated to 127 characters.
     */
    public static final String EXTRA_EVENT_ACTION = "android.app.usage.extra.EVENT_ACTION";

    /**
     * A String extra, when used with {@link UsageEvents.Event#getExtras}, that indicates
     * the category of the user interaction associated with the event. The category cannot
     * be more than 127 characters, longer value will be truncated to 127 characters.
     */
    public static final String EXTRA_EVENT_CATEGORY = "android.app.usage.extra.EVENT_CATEGORY";

    /**
     * An interval type that will use the best fit interval for the given time range.
     * See {@link #queryUsageStats(int, long, long)}.
     */
    public static final int INTERVAL_BEST = 4;

    /**
     * An interval type that spans a day. See {@link #queryUsageStats(int, long, long)}.
     */
    public static final int INTERVAL_DAILY = 0;

    /**
     * An interval type that spans a month. See {@link #queryUsageStats(int, long, long)}.
     */
    public static final int INTERVAL_MONTHLY = 2;

    /**
     * An interval type that spans a week. See {@link #queryUsageStats(int, long, long)}.
     */
    public static final int INTERVAL_WEEKLY = 1;

    /**
     * An interval type that spans a year. See {@link #queryUsageStats(int, long, long)}.
     */
    public static final int INTERVAL_YEARLY = 3;

    /**
     * The app was used very recently, currently in use or likely to be used very soon. Standby
     * bucket values that are &le; {@link #STANDBY_BUCKET_ACTIVE} will not be throttled by the
     * system while they are in this bucket. Buckets &gt; {@link #STANDBY_BUCKET_ACTIVE} will most
     * likely be restricted in some way. For instance, jobs and alarms may be deferred.
     * @see #getAppStandbyBucket()
     */
    public static final int STANDBY_BUCKET_ACTIVE = 10;

    /**
     * The app was used in the last few days and/or likely to be used in the next few days.
     * Restrictions will apply to these apps, such as deferral of jobs and alarms. The delays may be
     * greater than for apps in higher buckets (lower bucket value). Bucket values &gt;
     * {@link #STANDBY_BUCKET_FREQUENT} may additionally have network access limited.
     * @see #getAppStandbyBucket()
     */
    public static final int STANDBY_BUCKET_FREQUENT = 30;

    /**
     * The app has not be used for several days and/or is unlikely to be used for several days.
     * Apps in this bucket will have more restrictions, including network restrictions, except
     * during certain short periods (at a minimum, once a day) when they are allowed to execute
     * jobs, access the network, etc.
     * @see #getAppStandbyBucket()
     */
    public static final int STANDBY_BUCKET_RARE = 40;

    /**
     * The app has not be used for several days, is unlikely to be used for several days, and has
     * been misbehaving in some manner.
     * Apps in this bucket will have the most restrictions, including network restrictions and
     * additional restrictions on jobs.
     * <p> Note: this bucket is not enabled in {@link Build.VERSION_CODES#R}.
     * @see #getAppStandbyBucket()
     */
    public static final int STANDBY_BUCKET_RESTRICTED = 45;

    /**
     * The app was used recently and/or likely to be used in the next few hours. Restrictions will
     * apply to these apps, such as deferral of jobs and alarms.
     * @see #getAppStandbyBucket()
     */
    public static final int STANDBY_BUCKET_WORKING_SET = 20;

    private UsageStatsManager() {
    }
}
