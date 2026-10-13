package android.adservices.ondevicepersonalization;

/**
 * Training interval settings required for federated computation jobs.
 */
public final class TrainingInterval {
    /**
     * The scheduling mode for a one-off task.
     */
    public static final int SCHEDULING_MODE_ONE_TIME = 1;

    /**
     * The scheduling mode for a task that will be rescheduled after each run.
     */
    public static final int SCHEDULING_MODE_RECURRENT = 2;

    private TrainingInterval() {
    }
}
