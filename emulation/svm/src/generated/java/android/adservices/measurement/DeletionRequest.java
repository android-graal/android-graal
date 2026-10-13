package android.adservices.measurement;

/**
 * Deletion Request.
 */
public final class DeletionRequest {
    /**
     * Deletion mode to delete all data associated with the selected records.
     */
    public static final int DELETION_MODE_ALL = 0;

    /**
     * Deletion mode to delete all data except the internal data (e.g. rate limits) for the selected
     * records.
     */
    public static final int DELETION_MODE_EXCLUDE_INTERNAL_DATA = 1;

    /**
     * Match behavior option to delete the supplied params (Origin/Domains).
     */
    public static final int MATCH_BEHAVIOR_DELETE = 0;

    /**
     * Match behavior option to preserve the supplied params (Origin/Domains) and delete everything
     * else.
     */
    public static final int MATCH_BEHAVIOR_PRESERVE = 1;

    private DeletionRequest() {
    }
}
