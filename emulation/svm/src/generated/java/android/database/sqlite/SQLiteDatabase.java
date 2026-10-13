package android.database.sqlite;

/**
 * Exposes methods to manage a SQLite database.
 *
 * <p>
 * SQLiteDatabase has methods to create, delete, execute SQL commands, and
 * perform other common database management tasks.
 * </p><p>
 * See the Notepad sample application in the SDK for an example of creating
 * and managing a database.
 * </p><p>
 * Database names must be unique within an application, not across all applications.
 * </p>
 *
 * <h3>Localized Collation - ORDER BY</h3>
 * <p>
 * In addition to SQLite's default <code>BINARY</code> collator, Android supplies
 * two more, <code>LOCALIZED</code>, which changes with the system's current locale,
 * and <code>UNICODE</code>, which is the Unicode Collation Algorithm and not tailored
 * to the current locale.
 * </p>
 */
public final class SQLiteDatabase {
    /**
     * When a constraint violation occurs,no ROLLBACK is executed
     * so changes from prior commands within the same transaction
     * are preserved. This is the default behavior.
     */
    public static final int CONFLICT_ABORT = 2;

    /**
     * When a constraint violation occurs, the command aborts with a return
     * code SQLITE_CONSTRAINT. But any changes to the database that
     * the command made prior to encountering the constraint violation
     * are preserved and are not backed out.
     */
    public static final int CONFLICT_FAIL = 3;

    /**
     * When a constraint violation occurs, the one row that contains
     * the constraint violation is not inserted or changed.
     * But the command continues executing normally. Other rows before and
     * after the row that contained the constraint violation continue to be
     * inserted or updated normally. No error is returned.
     */
    public static final int CONFLICT_IGNORE = 4;

    /**
     * Use the following when no conflict action is specified.
     */
    public static final int CONFLICT_NONE = 0;

    /**
     * When a UNIQUE constraint violation occurs, the pre-existing rows that
     * are causing the constraint violation are removed prior to inserting
     * or updating the current row. Thus the insert or update always occurs.
     * The command continues executing normally. No error is returned.
     * If a NOT NULL constraint violation occurs, the NULL value is replaced
     * by the default value for that column. If the column has no default
     * value, then the ABORT algorithm is used. If a CHECK constraint
     * violation occurs then the IGNORE algorithm is used. When this conflict
     * resolution strategy deletes rows in order to satisfy a constraint,
     * it does not invoke delete triggers on those rows.
     * This behavior might change in a future release.
     */
    public static final int CONFLICT_REPLACE = 5;

    /**
     * When a constraint violation occurs, an immediate ROLLBACK occurs,
     * thus ending the current transaction, and the command aborts with a
     * return code of SQLITE_CONSTRAINT. If no transaction is active
     * (other than the implied transaction that is created on every command)
     * then this algorithm works the same as ABORT.
     */
    public static final int CONFLICT_ROLLBACK = 1;

    /**
     * Open flag: Flag for {@link #openDatabase} to create the database file if it does not
     * already exist.
     */
    public static final int CREATE_IF_NECESSARY = 268435456;

    /**
     * Open flag: Flag for {@link #openDatabase} to open the database file with
     * write-ahead logging enabled by default.  Using this flag is more efficient
     * than calling {@link #enableWriteAheadLogging}.
     *
     * Write-ahead logging cannot be used with read-only databases so the value of
     * this flag is ignored if the database is opened read-only.
     *
     * @see #enableWriteAheadLogging
     */
    public static final int ENABLE_WRITE_AHEAD_LOGGING = 536870912;

    /**
     * The {@code DELETE} journaling mode is the normal behavior. In the DELETE mode, the rollback
     * journal is deleted at the conclusion of each transaction.
     *
     * <p> See <a href=https://www.sqlite.org/pragma.html#pragma_journal_mode>here</a> for more
     * details.</p>
     */
    public static final String JOURNAL_MODE_DELETE = "DELETE";

    /**
     * The {@code MEMORY} journaling mode stores the rollback journal in volatile RAM.
     * This saves disk I/O but at the expense of database safety and integrity. If the application
     * using SQLite crashes in the middle of a transaction when the MEMORY journaling mode is set,
     * then the database file will very likely go corrupt.
     *
     * <p> See <a href=https://www.sqlite.org/pragma.html#pragma_journal_mode>here</a> for more
     * details.</p>
     */
    public static final String JOURNAL_MODE_MEMORY = "MEMORY";

    /**
     * The {@code OFF} journaling mode disables the rollback journal completely. No rollback journal
     * is ever created and hence there is never a rollback journal to delete. The OFF journaling
     * mode disables the atomic commit and rollback capabilities of SQLite. The ROLLBACK command
     * behaves in an undefined way thus applications must avoid using the ROLLBACK command.
     * If the application crashes in the middle of a transaction, then the database file will very
     * likely go corrupt.
     *
     * <p> See <a href=https://www.sqlite.org/pragma.html#pragma_journal_mode>here</a> for more
     * details.</p>
     */
    public static final String JOURNAL_MODE_OFF = "OFF";

    /**
     * The {@code PERSIST} journaling mode prevents the rollback journal from being deleted at the
     * end of each transaction. Instead, the header of the journal is overwritten with zeros.
     * This will prevent other database connections from rolling the journal back.
     *
     * This mode is useful as an optimization on platforms where deleting or truncating a file is
     * much more expensive than overwriting the first block of a file with zeros.
     *
     * <p> See <a href=https://www.sqlite.org/pragma.html#pragma_journal_mode>here</a> for more
     * details.</p>
     */
    public static final String JOURNAL_MODE_PERSIST = "PERSIST";

    /**
     * The {@code TRUNCATE} journaling mode commits transactions by truncating the rollback journal
     * to zero-length instead of deleting it. On many systems, truncating a file is much faster than
     * deleting the file since the containing directory does not need to be changed.
     *
     * <p> See <a href=https://www.sqlite.org/pragma.html#pragma_journal_mode>here</a> for more
     * details.</p>
     */
    public static final String JOURNAL_MODE_TRUNCATE = "TRUNCATE";

    /**
     * The {@code WAL} journaling mode uses a write-ahead log instead of a rollback journal to
     * implement transactions. The WAL journaling mode is persistent; after being set it stays
     * in effect across multiple database connections and after closing and reopening the database.
     *
     * Performance Considerations:
     * This mode is recommended when the goal is to improve write performance or parallel read/write
     * performance. However, it is important to note that WAL introduces checkpoints which commit
     * all transactions that have not been synced to the database thus to maximize read performance
     * and lower checkpointing cost a small journal size is recommended. However, other modes such
     * as {@code DELETE} will not perform checkpoints, so it is a trade off that needs to be
     * considered as part of the decision of which journal mode to use.
     *
     * <p> See <a href=https://www.sqlite.org/pragma.html#pragma_journal_mode>here</a> for more
     * details.</p>
     */
    public static final String JOURNAL_MODE_WAL = "WAL";

    /**
     * Absolute max value that can be set by {@link #setMaxSqlCacheSize(int)}.
     *
     * Each prepared-statement is between 1K - 6K, depending on the complexity of the
     * SQL statement & schema.  A large SQL cache may use a significant amount of memory.
     */
    public static final int MAX_SQL_CACHE_SIZE = 100;

    /**
     * Open flag: Flag for {@link #openDatabase} to open the database without support for
     * localized collators.
     *
     * {@more} This causes the collator <code>LOCALIZED</code> not to be created.
     * You must be consistent when using this flag to use the setting the database was
     * created with.  If this is set, {@link #setLocale} will do nothing.
     */
    public static final int NO_LOCALIZED_COLLATORS = 16;

    /**
     * Open flag: Flag for {@link #openDatabase} to open the database for reading only.
     * This is the only reliable way to open a database if the disk may be full.
     */
    public static final int OPEN_READONLY = 1;

    /**
     * Open flag: Flag for {@link #openDatabase} to open the database for reading and writing.
     * If the disk is full, this may fail even before you actually write anything.
     *
     * {@more} Note that the value of this flag is 0, so it is the default.
     */
    public static final int OPEN_READWRITE = 0;

    /**
     * Maximum Length Of A LIKE Or GLOB Pattern
     * The pattern matching algorithm used in the default LIKE and GLOB implementation
     * of SQLite can exhibit O(N^2) performance (where N is the number of characters in
     * the pattern) for certain pathological cases. To avoid denial-of-service attacks
     * the length of the LIKE or GLOB pattern is limited to SQLITE_MAX_LIKE_PATTERN_LENGTH bytes.
     * The default value of this limit is 50000. A modern workstation can evaluate
     * even a pathological LIKE or GLOB pattern of 50000 bytes relatively quickly.
     * The denial of service problem only comes into play when the pattern length gets
     * into millions of bytes. Nevertheless, since most useful LIKE or GLOB patterns
     * are at most a few dozen bytes in length, cautious application developers may
     * want to reduce this parameter to something in the range of a few hundred
     * if they know that external users are able to generate arbitrary patterns.
     */
    public static final int SQLITE_MAX_LIKE_PATTERN_LENGTH = 50000;

    /**
     * The {@code EXTRA} sync mode is like {@code FULL} sync mode with the addition that the
     * directory containing a rollback journal is synced after that journal is unlinked to commit a
     * transaction in {@code DELETE} journal mode.
     *
     * {@code EXTRA} provides additional durability if the commit is followed closely by a
     * power loss.
     *
     * <p> See <a href=https://www.sqlite.org/pragma.html#pragma_synchronous>here</a> for more
     * details.</p>
     */
    public static final String SYNC_MODE_EXTRA = "EXTRA";

    /**
     * In {@code FULL} sync mode the SQLite database engine will use the xSync method of the VFS
     * to ensure that all content is safely written to the disk surface prior to continuing.
     * This ensures that an operating system crash or power failure will not corrupt the database.
     * {@code FULL} is very safe, but it is also slower.
     *
     * {@code FULL} is the most commonly used synchronous setting when not in WAL mode.
     *
     * <p> See <a href=https://www.sqlite.org/pragma.html#pragma_synchronous>here</a> for more
     * details.</p>
     */
    public static final String SYNC_MODE_FULL = "FULL";

    /**
     * The {@code NORMAL} sync mode, the SQLite database engine will still sync at the most critical
     * moments, but less often than in {@code FULL} mode. There is a very small chance that a
     * power failure at the wrong time could corrupt the database in {@code DELETE} journal mode on
     * an older filesystem.
     *
     * {@code WAL} journal mode is safe from corruption with {@code NORMAL} sync mode, and probably
     * {@code DELETE} sync mode is safe too on modern filesystems. WAL mode is always consistent
     * with {@code NORMAL} sync mode, but WAL mode does lose durability. A transaction committed in
     * WAL mode with {@code NORMAL} might roll back following a power loss or system crash.
     * Transactions are durable across application crashes regardless of the synchronous setting
     * or journal mode.
     *
     * The {@code NORMAL} sync mode is a good choice for most applications running in WAL mode.
     *
     * <p>Caveat: Even though this sync mode is safe Be careful when using {@code NORMAL} sync mode
     * when dealing with data dependencies between multiple databases, unless those databases use
     * the same durability or are somehow synced, there could be corruption.</p>
     *
     * <p> See <a href=https://www.sqlite.org/pragma.html#pragma_synchronous>here</a> for more
     * details.</p>
     */
    public static final String SYNC_MODE_NORMAL = "NORMAL";

    /**
     * In {@code OFF} sync mode SQLite continues without syncing as soon as it has handed data off
     * to the operating system. If the application running SQLite crashes, the data will be safe,
     * but the database might become corrupted if the operating system crashes or the computer loses
     * power before that data has been written to the disk surface. On the other hand, commits can
     * be orders of magnitude faster with synchronous {@code OFF}.
     *
     * <p> See <a href=https://www.sqlite.org/pragma.html#pragma_synchronous>here</a> for more
     * details.</p>
     */
    public static final String SYNC_MODE_OFF = "OFF";

    private SQLiteDatabase() {
    }
}
