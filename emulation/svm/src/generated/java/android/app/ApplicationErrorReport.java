package android.app;

import android.os.Parcelable;

/**
 * Describes an application error.
 *
 * A report has a type, which is one of
 * <ul>
 * <li> {@link #TYPE_NONE} uninitialized instance of {@link ApplicationErrorReport}.
 * <li> {@link #TYPE_CRASH} application crash. Information about the crash
 * is stored in {@link #crashInfo}.
 * <li> {@link #TYPE_ANR} application not responding. Information about the
 * ANR is stored in {@link #anrInfo}.
 * <li> {@link #TYPE_BATTERY} user reported application is using too much
 * battery. Information about the battery use is stored in {@link #batteryInfo}.
 * <li> {@link #TYPE_RUNNING_SERVICE} user reported application is leaving an
 * unneeded serive running. Information about the battery use is stored in
 * {@link #runningServiceInfo}.
 * </ul>
 */
public final class ApplicationErrorReport implements Parcelable {
    /**
     * An error report about an application that's not responding.
     */
    public static final int TYPE_ANR = 2;

    /**
     * An error report about an application that's consuming too much battery.
     */
    public static final int TYPE_BATTERY = 3;

    /**
     * An error report about an application crash.
     */
    public static final int TYPE_CRASH = 1;

    /**
     * Uninitialized error report.
     */
    public static final int TYPE_NONE = 0;

    /**
     * A report from a user to a developer about a running service that the
     * user doesn't think should be running.
     */
    public static final int TYPE_RUNNING_SERVICE = 5;

    private ApplicationErrorReport() {
    }
}
