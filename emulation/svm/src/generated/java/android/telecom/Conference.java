package android.telecom;

/**
 * Represents a conference call which can contain any number of {@link Connection} objects.
 */
public final class Conference {
    /**
     * Used to indicate that the conference connection time is not specified.  If not specified,
     * Telecom will set the connect time.
     */
    public static final long CONNECT_TIME_NOT_SPECIFIED = 0L;

    private Conference() {
    }
}
