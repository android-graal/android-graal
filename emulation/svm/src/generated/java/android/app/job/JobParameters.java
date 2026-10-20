package android.app.job;

import android.os.Parcelable;

/**
 * Contains the parameters used to configure/identify your job. You do not create this object
 * yourself, instead it is handed in to your application by the System.
 */
public final class JobParameters implements Parcelable {
    /**
     * The current standby bucket requires that the job stop now.
     *
     * @see UsageStatsManager#STANDBY_BUCKET_RESTRICTED
     */
    public static final int STOP_REASON_APP_STANDBY = 12;

    /**
     * The app is restricted from running in the background.
     *
     * @see ActivityManager#isBackgroundRestricted()
     * @see PackageManager#isInstantApp()
     */
    public static final int STOP_REASON_BACKGROUND_RESTRICTION = 11;

    /**
     * The job was cancelled directly by the app, either by calling
     * {@link JobScheduler#cancel(int)}, {@link JobScheduler#cancelAll()}, or by scheduling a
     * new job with the same job ID.
     */
    public static final int STOP_REASON_CANCELLED_BY_APP = 1;

    /**
     * The requested battery-not-low constraint is no longer satisfied.
     *
     * @see JobInfo.Builder#setRequiresBatteryNotLow(boolean)
     */
    public static final int STOP_REASON_CONSTRAINT_BATTERY_NOT_LOW = 5;

    /**
     * The requested charging constraint is no longer satisfied.
     *
     * @see JobInfo.Builder#setRequiresCharging(boolean)
     */
    public static final int STOP_REASON_CONSTRAINT_CHARGING = 6;

    /**
     * The requested connectivity constraint is no longer satisfied.
     *
     * @see JobInfo.Builder#setRequiredNetwork(NetworkRequest)
     * @see JobInfo.Builder#setRequiredNetworkType(int)
     */
    public static final int STOP_REASON_CONSTRAINT_CONNECTIVITY = 7;

    /**
     * The requested idle constraint is no longer satisfied.
     *
     * @see JobInfo.Builder#setRequiresDeviceIdle(boolean)
     */
    public static final int STOP_REASON_CONSTRAINT_DEVICE_IDLE = 8;

    /**
     * The requested storage-not-low constraint is no longer satisfied.
     *
     * @see JobInfo.Builder#setRequiresStorageNotLow(boolean)
     */
    public static final int STOP_REASON_CONSTRAINT_STORAGE_NOT_LOW = 9;

    /**
     * The device state (eg. Doze, battery saver, memory usage, etc) requires JobScheduler stop this
     * job.
     */
    public static final int STOP_REASON_DEVICE_STATE = 4;

    /**
     * The system's estimate of when the app will be launched changed significantly enough to
     * decide this job shouldn't be running right now. This will mostly apply to prefetch jobs.
     *
     * @see JobInfo#isPrefetch()
     * @see JobInfo.Builder#setPrefetch(boolean)
     */
    public static final int STOP_REASON_ESTIMATED_APP_LAUNCH_TIME_CHANGED = 15;

    /**
     * The job was stopped to run a higher priority job of the app.
     */
    public static final int STOP_REASON_PREEMPT = 2;

    /**
     * The app has consumed all of its current quota. Each app is assigned a quota of how much
     * it can run jobs within a certain time frame. The quota is informed, in part, by app standby
     * buckets. Once an app has used up all of its quota, it won't be able to start jobs until
     * quota is replenished, is changed, or is temporarily not applied.
     *
     * @see UsageStatsManager#getAppStandbyBucket()
     */
    public static final int STOP_REASON_QUOTA = 10;

    /**
     * The system is doing some processing that requires stopping this job.
     */
    public static final int STOP_REASON_SYSTEM_PROCESSING = 14;

    /**
     * The job used up its maximum execution time and timed out. Each individual job has a maximum
     * execution time limit, regardless of how much total quota the app has. See the note on
     * {@link JobScheduler} and {@link JobInfo} for the execution time limits.
     */
    public static final int STOP_REASON_TIMEOUT = 3;

    /**
     * The job used up its maximum execution time and timed out. The system also detected that the
     * app can no longer call {@link JobService#jobFinished(JobParameters, boolean)} for this job,
     * likely because the strong reference to the job handle ({@link JobParameters}) passed to it
     * via {@link JobService#onStartJob(JobParameters)} was lost. This can occur even if the app
     * called {@link JobScheduler#cancel(int)}, {@link JobScheduler#cancelAll()}, or
     * {@link JobScheduler#cancelInAllNamespaces()} to stop an active job while losing strong
     * references to the job handle. In this case, the job is not necessarily abandoned. However,
     * the system cannot distinguish such cases from truly abandoned jobs.
     * <p>
     * It is recommended that you use {@link JobService#jobFinished(JobParameters, boolean)} or
     * return false from {@link JobService#onStartJob(JobParameters)} to stop an active job. This
     * will prevent the occurrence of this stop reason and the need to handle it. The primary use
     * case for this stop reason is to report a probable case of a job being abandoned.
     * <p>
     */
    public static final int STOP_REASON_TIMEOUT_ABANDONED = 16;

    /**
     * There is no reason the job is stopped. This is the value returned from the JobParameters
     * object passed to {@link JobService#onStartJob(JobParameters)}.
     */
    public static final int STOP_REASON_UNDEFINED = 0;

    /**
     * The user stopped the job. This can happen either through force-stop, adb shell commands,
     * uninstalling, or some other UI.
     */
    public static final int STOP_REASON_USER = 13;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private JobParameters() {
    }
}
