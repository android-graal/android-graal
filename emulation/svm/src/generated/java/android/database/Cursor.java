package android.database;

/**
 * This interface provides random read-write access to the result set returned
 * by a database query.
 * <p>
 * Cursor implementations are not required to be synchronized so code using a Cursor from multiple
 * threads should perform its own synchronization when using the Cursor.
 * </p><p>
 * Implementations should subclass {@link AbstractCursor}.
 * </p>
 */
public interface Cursor {
    /**
     * Value returned by {@link #getType(int)} if the specified column type is blob
     */
    int FIELD_TYPE_BLOB = 4;

    /**
     * Value returned by {@link #getType(int)} if the specified column type is float
     */
    int FIELD_TYPE_FLOAT = 2;

    /**
     * Value returned by {@link #getType(int)} if the specified  column type is integer
     */
    int FIELD_TYPE_INTEGER = 1;

    /**
     * Value returned by {@link #getType(int)} if the specified column is null
     */
    int FIELD_TYPE_NULL = 0;

    /**
     * Value returned by {@link #getType(int)} if the specified column type is string
     */
    int FIELD_TYPE_STRING = 3;
}
