package android.health.connect.datatypes;

/**
 * A single step within an {@link PlannedExerciseBlock} e.g. 8x 60kg barbell squats.
 */
public final class PlannedExerciseStep {
    /**
     * Active exercise.
     */
    public static final int EXERCISE_CATEGORY_ACTIVE = 3;

    /**
     * Cooldown exercise, typically at the end of a workout.
     */
    public static final int EXERCISE_CATEGORY_COOLDOWN = 4;

    /**
     * Lower intensity, active exercise.
     */
    public static final int EXERCISE_CATEGORY_RECOVERY = 5;

    /**
     * A rest.
     */
    public static final int EXERCISE_CATEGORY_REST = 2;

    /**
     * An unknown category of exercise.
     */
    public static final int EXERCISE_CATEGORY_UNKNOWN = 0;

    /**
     * A warmup.
     */
    public static final int EXERCISE_CATEGORY_WARMUP = 1;

    private PlannedExerciseStep() {
    }
}
