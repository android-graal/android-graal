package android.app;

/**
 * This class provides access to the system alarm services.  These allow you
 * to schedule your application to be run at some point in the future.  When
 * an alarm goes off, the {@link Intent} that had been registered for it
 * is broadcast by the system, automatically starting the target application
 * if it is not already running.  Registered alarms are retained while the
 * device is asleep (and can optionally wake the device up if they go off
 * during that time), but will be cleared if it is turned off and rebooted.
 *
 * <p>The Alarm Manager holds a CPU wake lock as long as the alarm receiver's
 * onReceive() method is executing. This guarantees that the phone will not sleep
 * until you have finished handling the broadcast. Once onReceive() returns, the
 * Alarm Manager releases this wake lock. This means that the phone will in some
 * cases sleep as soon as your onReceive() method completes.  If your alarm receiver
 * called {@link android.content.Context#startService Context.startService()}, it
 * is possible that the phone will sleep before the requested service is launched.
 * To prevent this, your BroadcastReceiver and Service will need to implement a
 * separate wake lock policy to ensure that the phone continues running until the
 * service becomes available.
 *
 * <p><b>Note: The Alarm Manager is intended for cases where you want to have
 * your application code run at a specific time, even if your application is
 * not currently running.  For normal timing operations (ticks, timeouts,
 * etc) it is easier and much more efficient to use
 * {@link android.os.Handler}.</b>
 *
 * <p class="caution"><strong>Note:</strong> Beginning with API 19
 * ({@link android.os.Build.VERSION_CODES#KITKAT}) alarm delivery is inexact:
 * the OS will shift alarms in order to minimize wakeups and battery use.  There are
 * new APIs to support applications which need strict delivery guarantees; see
 * {@link #setWindow(int, long, long, PendingIntent)} and
 * {@link #setExact(int, long, PendingIntent)}.  Applications whose {@code targetSdkVersion}
 * is earlier than API 19 will continue to see the previous behavior in which all
 * alarms are delivered exactly when requested.
 */
public final class AlarmManager {
    /**
     * Broadcast Action: Sent after the value returned by
     * {@link #getNextAlarmClock()} has changed.
     *
     * <p class="note">This is a protected intent that can only be sent by the system.
     * It is only sent to registered receivers.</p>
     */
    public static final String ACTION_NEXT_ALARM_CLOCK_CHANGED = "android.app.action.NEXT_ALARM_CLOCK_CHANGED";

    /**
     * Broadcast Action: An app is granted the
     * {@link android.Manifest.permission#SCHEDULE_EXACT_ALARM} permission.
     *
     * <p>When the user revokes the {@link android.Manifest.permission#SCHEDULE_EXACT_ALARM}
     * permission, all alarms scheduled with
     * {@link #setExact}, {@link #setExactAndAllowWhileIdle} and
     * {@link #setAlarmClock(AlarmClockInfo, PendingIntent)} will be deleted.
     *
     * <p>When the user grants the {@link android.Manifest.permission#SCHEDULE_EXACT_ALARM},
     * this broadcast will be sent. Applications can reschedule all the necessary alarms when
     * receiving it.
     *
     * <p>This broadcast will <em>not</em> be sent when the user revokes the permission.
     *
     * <p><em>Note:</em>
     * Applications are still required to check {@link #canScheduleExactAlarms()}
     * before using the above APIs after receiving this broadcast,
     * because it's possible that the permission is already revoked again by the time
     * applications receive this broadcast.
     *
     * <p>This broadcast will be sent to both runtime receivers and manifest receivers.
     *
     * <p>This broadcast is sent as a foreground broadcast.
     * See {@link android.content.Intent#FLAG_RECEIVER_FOREGROUND}.
     *
     * <p>When an application receives this broadcast, it's allowed to start a foreground service.
     */
    public static final String ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED = "android.app.action.SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED";

    /**
     * Alarm time in {@link android.os.SystemClock#elapsedRealtime
     * SystemClock.elapsedRealtime()} (time since boot, including sleep).
     * This alarm does not wake the device up; if it goes off while the device
     * is asleep, it will not be delivered until the next time the device
     * wakes up.
     */
    public static final int ELAPSED_REALTIME = 3;

    /**
     * Alarm time in {@link android.os.SystemClock#elapsedRealtime
     * SystemClock.elapsedRealtime()} (time since boot, including sleep),
     * which will wake up the device when it goes off.
     */
    public static final int ELAPSED_REALTIME_WAKEUP = 2;

    /**
     * Available inexact recurrence interval recognized by
     * {@link #setInexactRepeating(int, long, long, PendingIntent)}
     * when running on Android prior to API 19.
     */
    public static final long INTERVAL_DAY = 86400000L;

    /**
     * Available inexact recurrence interval recognized by
     * {@link #setInexactRepeating(int, long, long, PendingIntent)}
     * when running on Android prior to API 19.
     */
    public static final long INTERVAL_FIFTEEN_MINUTES = 900000L;

    /**
     * Available inexact recurrence interval recognized by
     * {@link #setInexactRepeating(int, long, long, PendingIntent)}
     * when running on Android prior to API 19.
     */
    public static final long INTERVAL_HALF_DAY = 43200000L;

    /**
     * Available inexact recurrence interval recognized by
     * {@link #setInexactRepeating(int, long, long, PendingIntent)}
     * when running on Android prior to API 19.
     */
    public static final long INTERVAL_HALF_HOUR = 1800000L;

    /**
     * Available inexact recurrence interval recognized by
     * {@link #setInexactRepeating(int, long, long, PendingIntent)}
     * when running on Android prior to API 19.
     */
    public static final long INTERVAL_HOUR = 3600000L;

    /**
     * Alarm time in {@link System#currentTimeMillis System.currentTimeMillis()}
     * (wall clock time in UTC).  This alarm does not wake the
     * device up; if it goes off while the device is asleep, it will not be
     * delivered until the next time the device wakes up.
     */
    public static final int RTC = 1;

    /**
     * Alarm time in {@link System#currentTimeMillis System.currentTimeMillis()}
     * (wall clock time in UTC), which will wake up the device when
     * it goes off.
     */
    public static final int RTC_WAKEUP = 0;

    private AlarmManager() {
    }
}
