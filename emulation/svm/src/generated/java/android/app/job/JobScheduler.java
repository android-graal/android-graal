package android.app.job;

/**
 * This is an API for scheduling various types of jobs against the framework that will be executed
 * in your application's own process.
 * <p>
 * See {@link android.app.job.JobInfo} for more description of the types of jobs that can be run
 * and how to construct them. You will construct these JobInfo objects and pass them to the
 * JobScheduler with {@link #schedule(JobInfo)}. When the criteria declared are met, the
 * system will execute this job on your application's {@link android.app.job.JobService}.
 * You identify the service component that implements the logic for your job when you
 * construct the JobInfo using
 * {@link android.app.job.JobInfo.Builder#Builder(int,android.content.ComponentName)}.
 * </p>
 * <p>
 * The framework will be intelligent about when it executes jobs, and attempt to batch
 * and defer them as much as possible. Typically, if you don't specify a deadline on a job, it
 * can be run at any moment depending on the current state of the JobScheduler's internal queue.
 * </p>
 * <p>
 * Starting in Android version {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE},
 * JobScheduler may try to optimize job execution by shifting execution to times with more available
 * system resources in order to lower user impact. Factors in system health include sufficient
 * battery, idle, charging, and access to an un-metered network. Jobs will initially be treated as
 * if they have all these requirements, but as their deadlines approach, restrictions will become
 * less strict. Requested requirements will not be affected by this change.
 * </p>
 *
 * {@see android.app.job.JobInfo.Builder#setRequiresBatteryNotLow(boolean)}
 * {@see android.app.job.JobInfo.Builder#setRequiresDeviceIdle(boolean)}
 * {@see android.app.job.JobInfo.Builder#setRequiresCharging(boolean)}
 * {@see android.app.job.JobInfo.Builder#setRequiredNetworkType(int)}
 *
 * <p>
 * While a job is running, the system holds a wakelock on behalf of your app.  For this reason,
 * you do not need to take any action to guarantee that the device stays awake for the
 * duration of the job.
 * </p>
 * <p>You do not
 * instantiate this class directly; instead, retrieve it through
 * {@link android.content.Context#getSystemService
 * Context.getSystemService(Context.JOB_SCHEDULER_SERVICE)}.
 *
 * <p> Prior to Android version {@link android.os.Build.VERSION_CODES#S}, jobs could only have
 * a maximum of 100 jobs scheduled at a time. Starting with Android version
 * {@link android.os.Build.VERSION_CODES#S}, that limit has been increased to 150.
 * Expedited jobs also count towards the limit.
 *
 * <p> In Android version {@link android.os.Build.VERSION_CODES#LOLLIPOP}, jobs had a maximum
 * execution time of one minute. Starting with Android version
 * {@link android.os.Build.VERSION_CODES#M} and ending with Android version
 * {@link android.os.Build.VERSION_CODES#R}, jobs had a maximum execution time of 10 minutes.
 * Starting from Android version {@link android.os.Build.VERSION_CODES#S}, jobs will still be
 * stopped after 10 minutes if the system is busy or needs the resources, but if not, jobs
 * may continue running longer than 10 minutes.
 *
 * <p class="caution"><strong>Note:</strong> Beginning with API 30
 * ({@link android.os.Build.VERSION_CODES#R}), JobScheduler will throttle runaway applications.
 * Calling {@link #schedule(JobInfo)} and other such methods with very high frequency can have a
 * high cost and so, to make sure the system doesn't get overwhelmed, JobScheduler will begin
 * to throttle apps, regardless of target SDK version.
 */
public final class JobScheduler {
    /**
     * The app is in a state that prevents the job from running
     * (eg. the {@link JobService} component is disabled).
     */
    public static final int PENDING_JOB_REASON_APP = 1;

    /**
     * The current standby bucket prevents the job from running.
     *
     * @see UsageStatsManager#STANDBY_BUCKET_RESTRICTED
     */
    public static final int PENDING_JOB_REASON_APP_STANDBY = 2;

    /**
     * The app is restricted from running in the background.
     *
     * @see ActivityManager#isBackgroundRestricted()
     * @see PackageManager#isInstantApp()
     */
    public static final int PENDING_JOB_REASON_BACKGROUND_RESTRICTION = 3;

    /**
     * The requested battery-not-low constraint is not satisfied.
     *
     * @see JobInfo.Builder#setRequiresBatteryNotLow(boolean)
     */
    public static final int PENDING_JOB_REASON_CONSTRAINT_BATTERY_NOT_LOW = 4;

    /**
     * The requested charging constraint is not satisfied.
     *
     * @see JobInfo.Builder#setRequiresCharging(boolean)
     */
    public static final int PENDING_JOB_REASON_CONSTRAINT_CHARGING = 5;

    /**
     * The requested connectivity constraint is not satisfied.
     *
     * @see JobInfo.Builder#setRequiredNetwork(NetworkRequest)
     * @see JobInfo.Builder#setRequiredNetworkType(int)
     */
    public static final int PENDING_JOB_REASON_CONSTRAINT_CONNECTIVITY = 6;

    /**
     * The requested content trigger constraint is not satisfied.
     *
     * @see JobInfo.Builder#addTriggerContentUri(JobInfo.TriggerContentUri)
     */
    public static final int PENDING_JOB_REASON_CONSTRAINT_CONTENT_TRIGGER = 7;

    /**
     * The override deadline has not transpired.
     *
     * @see JobInfo.Builder#setOverrideDeadline(long)
     */
    public static final int PENDING_JOB_REASON_CONSTRAINT_DEADLINE = 16;

    /**
     * The requested idle constraint is not satisfied.
     *
     * @see JobInfo.Builder#setRequiresDeviceIdle(boolean)
     */
    public static final int PENDING_JOB_REASON_CONSTRAINT_DEVICE_IDLE = 8;

    /**
     * The minimum latency has not transpired.
     *
     * @see JobInfo.Builder#setMinimumLatency(long)
     */
    public static final int PENDING_JOB_REASON_CONSTRAINT_MINIMUM_LATENCY = 9;

    /**
     * The system's estimate of when the app will be launched is far away enough to warrant delaying
     * this job.
     *
     * @see JobInfo#isPrefetch()
     * @see JobInfo.Builder#setPrefetch(boolean)
     */
    public static final int PENDING_JOB_REASON_CONSTRAINT_PREFETCH = 10;

    /**
     * The requested storage-not-low constraint is not satisfied.
     *
     * @see JobInfo.Builder#setRequiresStorageNotLow(boolean)
     */
    public static final int PENDING_JOB_REASON_CONSTRAINT_STORAGE_NOT_LOW = 11;

    /**
     * The job is being deferred due to the device state (eg. Doze, battery saver, memory usage,
     * thermal status, etc.).
     */
    public static final int PENDING_JOB_REASON_DEVICE_STATE = 12;

    /**
     * The job is currently running and is therefore not pending.
     */
    public static final int PENDING_JOB_REASON_EXECUTING = -1;

    /**
     * The job doesn't exist.
     */
    public static final int PENDING_JOB_REASON_INVALID_JOB_ID = -2;

    /**
     * JobScheduler thinks it can defer this job to a more optimal running time.
     */
    public static final int PENDING_JOB_REASON_JOB_SCHEDULER_OPTIMIZATION = 13;

    /**
     * The app has consumed all of its current quota.
     *
     * @see UsageStatsManager#getAppStandbyBucket()
     * @see JobParameters#STOP_REASON_QUOTA
     */
    public static final int PENDING_JOB_REASON_QUOTA = 14;

    /**
     * There is no known reason why the job is pending.
     * If additional reasons are added on newer Android versions, the system may return this reason
     * to apps whose target SDK is not high enough to expect that reason.
     */
    public static final int PENDING_JOB_REASON_UNDEFINED = 0;

    /**
     * JobScheduler is respecting one of the user's actions (eg. force stop or adb shell commands)
     * to defer this job.
     */
    public static final int PENDING_JOB_REASON_USER = 15;

    /**
     * Returned from {@link #schedule(JobInfo)} if a job wasn't scheduled successfully. Scheduling
     * can fail for a variety of reasons, including, but not limited to:
     * <ul>
     * <li>an invalid parameter was supplied (eg. the run-time for your job is too short, or the
     * system can't resolve the requisite {@link JobService} in your package)</li>
     * <li>the app has too many jobs scheduled</li>
     * <li>the app has tried to schedule too many jobs in a short amount of time</li>
     * </ul>
     * Attempting to schedule the job again immediately after receiving this result will not
     * guarantee a successful schedule.
     */
    public static final int RESULT_FAILURE = 0;

    /**
     * Returned from {@link #schedule(JobInfo)} if this job has been successfully scheduled.
     */
    public static final int RESULT_SUCCESS = 1;

    private JobScheduler() {
    }
}
