package android.app.job;

import android.os.Parcelable;

/**
 * Container of data passed to the {@link android.app.job.JobScheduler} fully encapsulating the
 * parameters required to schedule work against the calling application. These are constructed
 * using the {@link JobInfo.Builder}.
 * The goal here is to provide the scheduler with high-level semantics about the work you want to
 * accomplish.
 * <p> Prior to Android version {@link Build.VERSION_CODES#Q}, you had to specify at least one
 * constraint on the JobInfo object that you are creating. Otherwise, the builder would throw an
 * exception when building. From Android version {@link Build.VERSION_CODES#Q} and onwards, it is
 * valid to schedule jobs with no constraints.
 */
public final class JobInfo implements Parcelable {
    /**
     * Exponentially back-off a failed job. See
     * {@link android.app.job.JobInfo.Builder#setBackoffCriteria(long, int)}
     *
     * retry_time(current_time, num_failures) =
     *     current_time + initial_backoff_millis * 2 ^ (num_failures - 1), num_failures >= 1
     */
    public static final int BACKOFF_POLICY_EXPONENTIAL = 1;

    /**
     * Linearly back-off a failed job. See
     * {@link android.app.job.JobInfo.Builder#setBackoffCriteria(long, int)}
     * retry_time(current_time, num_failures) =
     *     current_time + initial_backoff_millis * num_failures, num_failures >= 1
     */
    public static final int BACKOFF_POLICY_LINEAR = 0;

    /**
     * Amount of backoff a job has initially by default, in milliseconds.
     */
    public static final long DEFAULT_INITIAL_BACKOFF_MILLIS = 30000L;

    /**
     * Maximum backoff we allow for a job, in milliseconds.
     */
    public static final long MAX_BACKOFF_DELAY_MILLIS = 18000000L;

    /**
     * Sentinel value indicating that bytes are unknown.
     */
    public static final int NETWORK_BYTES_UNKNOWN = -1;

    /**
     * This job requires network connectivity.
     */
    public static final int NETWORK_TYPE_ANY = 1;

    /**
     * This job requires network connectivity that is a cellular network.
     */
    public static final int NETWORK_TYPE_CELLULAR = 4;

    /**
     * This job requires metered connectivity such as most cellular data
     * networks.
     *
     * @deprecated Cellular networks may be unmetered, or Wi-Fi networks may be
     *             metered, so this isn't a good way of selecting a specific
     *             transport. Instead, use {@link #NETWORK_TYPE_CELLULAR} or
     *             {@link android.net.NetworkRequest.Builder#addTransportType(int)}
     *             if your job requires a specific network transport.
     */
    @Deprecated
    public static final int NETWORK_TYPE_METERED = 4;

    /**
     * Default.
     */
    public static final int NETWORK_TYPE_NONE = 0;

    /**
     * This job requires network connectivity that is not roaming.
     */
    public static final int NETWORK_TYPE_NOT_ROAMING = 3;

    /**
     * This job requires network connectivity that is unmetered.
     */
    public static final int NETWORK_TYPE_UNMETERED = 2;

    /**
     * Default value for all regular jobs. As noted in {@link JobScheduler},
     * these jobs have a general execution time of 10 minutes.
     * Receives the standard job management policy.
     */
    public static final int PRIORITY_DEFAULT = 300;

    /**
     * This task should be ordered ahead of most other tasks. It may be
     * deferred a little, but if it doesn't run at some point, the user may think
     * something is wrong. Assuming all constraints remain satisfied
     * (including ideal system load conditions), these jobs can have an
     * execution time of at least 4 minutes. Setting all of your jobs to high
     * priority will not be beneficial to your app and in fact may hurt its
     * performance in the long run.
     */
    public static final int PRIORITY_HIGH = 400;

    /**
     * Low priority. The task provides some benefit to users, but is not critical
     * and is more of a nice-to-have. This is more important than minimum priority
     * jobs and will be prioritized ahead of them, but may still be deferred in lieu
     * of higher priority jobs. JobScheduler <i>may</i> decide to defer these tasks
     * while there are higher priority tasks in order to ensure there is sufficient
     * quota available for the higher priority tasks.
     * A sample task of low priority: prefetching data the user hasn't requested
     */
    public static final int PRIORITY_LOW = 200;

    /**
     * This task is critical to user experience or functionality
     * and should be run ahead of all other tasks. Only
     * {@link Builder#setExpedited(boolean) expedited jobs} and
     * {@link Builder#setUserInitiated(boolean) user-initiated jobs} can have this priority.
     * <p>
     * Example tasks of max priority:
     * <ul>
     *     <li>Receiving a text message and processing it to show a notification</li>
     *     <li>Downloading or uploading some content the user requested to transfer immediately</li>
     * </ul>
     */
    public static final int PRIORITY_MAX = 500;

    /**
     * Job has minimal value to the user. The user has absolutely no expectation
     * or knowledge of this task and it has no bearing on the user's perception of
     * the app whatsoever. JobScheduler <i>may</i> decide to defer these tasks while
     * there are higher priority tasks in order to ensure there is sufficient quota
     * available for the higher priority tasks.
     * A sample task of min priority: uploading analytics
     */
    public static final int PRIORITY_MIN = 100;

    private JobInfo() {
    }

    /**
     * Information about a content URI modification that a job would like to
     * trigger on.
     */
    public static final class TriggerContentUri implements Parcelable {
        /**
         * Flag for trigger: also trigger if any descendants of the given URI change.
         * Corresponds to the <var>notifyForDescendants</var> of
         * {@link android.content.ContentResolver#registerContentObserver}.
         */
        public static final int FLAG_NOTIFY_FOR_DESCENDANTS = 1;

        private TriggerContentUri() {
        }
    }
}
