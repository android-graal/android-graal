package android.database;

/**
 * Static utility methods for dealing with databases and {@link Cursor}s.
 */
public final class DatabaseUtils {
    /**
     * One of the values returned by {@link #getSqlStatementType(String)}.
     */
    public static final int STATEMENT_ABORT = 6;

    /**
     * One of the values returned by {@link #getSqlStatementType(String)}.
     */
    public static final int STATEMENT_ATTACH = 3;

    /**
     * One of the values returned by {@link #getSqlStatementType(String)}.
     */
    public static final int STATEMENT_BEGIN = 4;

    /**
     * One of the values returned by {@link #getSqlStatementType(String)}.
     */
    public static final int STATEMENT_COMMIT = 5;

    /**
     * One of the values returned by {@link #getSqlStatementType(String)}.
     */
    public static final int STATEMENT_DDL = 8;

    /**
     * One of the values returned by {@link #getSqlStatementType(String)}.
     */
    public static final int STATEMENT_OTHER = 99;

    /**
     * One of the values returned by {@link #getSqlStatementType(String)}.
     */
    public static final int STATEMENT_PRAGMA = 7;

    /**
     * One of the values returned by {@link #getSqlStatementType(String)}.
     */
    public static final int STATEMENT_SELECT = 1;

    /**
     * One of the values returned by {@link #getSqlStatementType(String)}.
     */
    public static final int STATEMENT_UNPREPARED = 9;

    /**
     * One of the values returned by {@link #getSqlStatementType(String)}.
     */
    public static final int STATEMENT_UPDATE = 2;

    private DatabaseUtils() {
    }
}
