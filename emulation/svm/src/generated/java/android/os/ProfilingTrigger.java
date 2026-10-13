package android.os;

/**
 * Encapsulates a single profiling trigger.
 */
public final class ProfilingTrigger {
    /**
     * Trigger occurs after an ANR has been identified, but before the system would attempt to kill
     * the app. The trigger does not necessarily indicate that the app was killed due to the ANR.
     */
    public static final int TRIGGER_TYPE_ANR = 2;

    /**
     * Trigger occurs after {@link Activity#reportFullyDrawn} is called for a cold start.
     */
    public static final int TRIGGER_TYPE_APP_FULLY_DRAWN = 1;

    /**
     * No trigger. Used in {@link ProfilingResult} for non trigger caused results.
     */
    public static final int TRIGGER_TYPE_NONE = 0;

    private ProfilingTrigger() {
    }
}
