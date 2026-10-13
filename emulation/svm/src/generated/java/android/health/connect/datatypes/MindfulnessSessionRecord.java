package android.health.connect.datatypes;

/**
 * Captures a mindfulness session.
 *
 * <p>For example: yoga, meditation, guided breathing, etc.
 *
 * <p>Each record needs a start time, end time and a mindfulness session type. In addition, each
 * record has an optional title and notes.
 */
public final class MindfulnessSessionRecord {
    /**
     * Guided breathing mindfulness session.
     */
    public static final int MINDFULNESS_SESSION_TYPE_BREATHING = 3;

    /**
     * Meditation mindfulness session.
     */
    public static final int MINDFULNESS_SESSION_TYPE_MEDITATION = 1;

    /**
     * Stretches/movement mindfulness session.
     */
    public static final int MINDFULNESS_SESSION_TYPE_MOVEMENT = 5;

    /**
     * Music/soundscapes mindfulness session.
     */
    public static final int MINDFULNESS_SESSION_TYPE_MUSIC = 4;

    /**
     * Other mindfulness session.
     */
    public static final int MINDFULNESS_SESSION_TYPE_OTHER = 2;

    /**
     * Unguided mindfulness session.
     */
    public static final int MINDFULNESS_SESSION_TYPE_UNGUIDED = 6;

    /**
     * Use this type if the mindfulness session type is unknown.
     */
    public static final int MINDFULNESS_SESSION_TYPE_UNKNOWN = 0;

    private MindfulnessSessionRecord() {
    }
}
