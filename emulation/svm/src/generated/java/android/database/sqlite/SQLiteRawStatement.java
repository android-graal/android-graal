package android.database.sqlite;

/**
 * A {@link SQLiteRawStatement} represents a SQLite prepared statement. The methods correspond very
 * closely to SQLite APIs that operate on a sqlite_stmt object.  In general, each API in this class
 * corresponds to a single SQLite API.
 *
 * <p>
 * A {@link SQLiteRawStatement} must be created through a database, and there must be a
 * transaction open at the time. Statements are implicitly closed when the outermost transaction
 * ends, or if the current transaction is marked successful. Statements may be explicitly
 * closed at any time with {@link #close}.  The {@link #close} operation is idempotent and may be
 * called multiple times without harm.
 * <p>
 * Multiple {@link SQLiteRawStatement}s may be open simultaneously.  They are independent of each
 * other.  Closing one statement does not affect any other statement nor does it have any effect
 * on the enclosing transaction.
 * <p>
 * Once a {@link SQLiteRawStatement} has been closed, no further database operations are
 * permitted on that statement. An {@link IllegalStateException} will be thrown if a database
 * operation is attempted on a closed statement.
 * <p>
 * All operations on a {@link SQLiteRawStatement} must be invoked from the thread that created
 * it. A {@link IllegalStateException} will be thrown if cross-thread use is detected.
 * <p>
 * A common pattern for statements is try-with-resources.
 * <code><pre>
 * // Begin a transaction.
 * database.beginTransaction();
 * try (SQLiteRawStatement statement = database.createRawStatement("SELECT * FROM ...")) {
 *     while (statement.step()) {
 *         // Fetch columns from the result rows.
 *     }
 *     database.setTransactionSuccessful();
 * } finally {
 *     database.endTransaction();
 * }
 * </pre></code>
 * Note that {@link SQLiteRawStatement} is unrelated to {@link SQLiteStatement}.
 *
 * @see <a href="http://sqlite.org/c3ref/stmt.html">sqlite3_stmt</a>
 */
public final class SQLiteRawStatement {
    /**
     * The constant returned by {@link #getColumnType} when the column value is SQLITE_BLOB.
     */
    public static final int SQLITE_DATA_TYPE_BLOB = 4;

    /**
     * The constant returned by {@link #getColumnType} when the column value is SQLITE_FLOAT.
     */
    public static final int SQLITE_DATA_TYPE_FLOAT = 2;

    /**
     * The constant returned by {@link #getColumnType} when the column value is SQLITE_INTEGER.
     */
    public static final int SQLITE_DATA_TYPE_INTEGER = 1;

    /**
     * The constant returned by {@link #getColumnType} when the column value is SQLITE_NULL.
     */
    public static final int SQLITE_DATA_TYPE_NULL = 5;

    /**
     * The constant returned by {@link #getColumnType} when the column value is SQLITE_TEXT.
     */
    public static final int SQLITE_DATA_TYPE_TEXT = 3;

    private SQLiteRawStatement() {
    }
}
