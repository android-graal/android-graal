package android.app.job;

/**
 * <p>Entry point for the callback from the {@link android.app.job.JobScheduler}.</p>
 * <p>This is the base class that handles asynchronous requests that were previously scheduled. You
 * are responsible for overriding {@link JobService#onStartJob(JobParameters)}, which is where
 * you will implement your job logic.</p>
 * <p>This service executes each incoming job on a {@link android.os.Handler} running on your
 * application's main thread. This means that you <b>must</b> offload your execution logic to
 * another thread/handler/{@link android.os.AsyncTask} of your choosing. Not doing so will result
 * in blocking any future callbacks from the JobScheduler - specifically
 * {@link #onStopJob(android.app.job.JobParameters)}, which is meant to inform you that the
 * scheduling requirements are no longer being met.</p>
 *
 * <p class="note">
 * Since the introduction of JobScheduler, if an app did not return from
 * {@link #onStartJob(JobParameters)} within several seconds, JobScheduler would consider the app
 * unresponsive and clean up job execution. In such cases, the app was no longer considered
 * to be running a job and therefore did not have any of the job lifecycle guarantees outlined
 * in {@link JobScheduler}. However, prior to Android version
 * {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE}, the failure and cleanup were silent
 * and apps had no indication that they no longer had job lifecycle guarantees.
 * Starting with Android version {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE},
 * JobScheduler will explicitly trigger an ANR in such cases so that apps and developers
 * can be aware of the issue.
 * Similar behavior applies to the return time from {@link #onStopJob(JobParameters)} as well.
 * <br /> <br />
 * If you see ANRs, then the app may be doing too much work on the UI thread. Ensure that
 * potentially long operations are moved to a worker thread.
 *
 * <p>As a subclass of {@link Service}, there will only be one active instance of any JobService
 * subclasses, regardless of job ID. This means that if you schedule multiple jobs with different
 * job IDs but using the same JobService class, that JobService may receive multiple calls to
 * {@link #onStartJob(JobParameters)} and {@link #onStopJob(JobParameters)}, with each call being
 * for the separate jobs.</p>
 */
public final class JobService {
    /**
     * Detach the notification supplied to
     * {@link #setNotification(JobParameters, int, Notification, int)} when the job ends.
     * The notification will remain shown even after JobScheduler stops the job.
     */
    public static final int JOB_END_NOTIFICATION_POLICY_DETACH = 0;

    /**
     * Cancel and remove the notification supplied to
     * {@link #setNotification(JobParameters, int, Notification, int)} when the job ends.
     * The notification will be removed from the notification shade.
     */
    public static final int JOB_END_NOTIFICATION_POLICY_REMOVE = 1;

    /**
     * Job services must be protected with this permission:
     *
     * <pre class="prettyprint">
     *     &#60;service android:name="MyJobService"
     *              android:permission="android.permission.BIND_JOB_SERVICE" &#62;
     *         ...
     *     &#60;/service&#62;
     * </pre>
     *
     * <p>If a job service is declared in the manifest but not protected with this
     * permission, that service will be ignored by the system.
     */
    public static final String PERMISSION_BIND = "android.permission.BIND_JOB_SERVICE";

    private JobService() {
    }
}
