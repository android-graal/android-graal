package android.app.appsearch;

/**
 * Encapsulates a request to update the schema of an {@link AppSearchSession} database.
 *
 * <p>The schema is composed of a collection of {@link AppSearchSchema} objects, each of which
 * defines a unique type of data.
 *
 * <p>The first call to SetSchemaRequest will set the provided schema and store it within the {@link
 * AppSearchSession} database.
 *
 * <p>Subsequent calls will compare the provided schema to the previously saved schema, to determine
 * how to treat existing documents.
 *
 * <p>The following types of schema modifications are always safe and are made without deleting any
 * existing documents:
 *
 * <ul>
 *   <li>Addition of new {@link AppSearchSchema} types
 *   <li>Addition of new properties to an existing {@link AppSearchSchema} type
 *   <li>Changing the cardinality of a property to be less restrictive
 * </ul>
 *
 * <p>The following types of schema changes are not backwards compatible:
 *
 * <ul>
 *   <li>Removal of an existing {@link AppSearchSchema} type
 *   <li>Removal of a property from an existing {@link AppSearchSchema} type
 *   <li>Changing the data type of an existing property
 *   <li>Changing the cardinality of a property to be more restrictive
 * </ul>
 *
 * <p>Providing a schema with incompatible changes, will throw an {@link
 * android.app.appsearch.exceptions.AppSearchException}, with a message describing the
 * incompatibility. As a result, the previously set schema will remain unchanged.
 *
 * <p>Backward incompatible changes can be made by :
 *
 * <ul>
 *   <li>setting {@link SetSchemaRequest.Builder#setForceOverride} method to {@code true}. This
 *       deletes all documents that are incompatible with the new schema. The new schema is then
 *       saved and persisted to disk.
 *   <li>Add a {@link Migrator} for each incompatible type and make no deletion. The migrator will
 *       migrate documents from its old schema version to the new version. Migrated types will be
 *       set into both {@link SetSchemaResponse#getIncompatibleTypes()} and {@link
 *       SetSchemaResponse#getMigratedTypes()}. See the migration section below.
 * </ul>
 *
 * @see AppSearchSession#setSchema
 * @see Migrator
 */
public final class SetSchemaRequest {
    /**
     * The {@link android.Manifest.permission#READ_ASSISTANT_APP_SEARCH_DATA} AppSearch supported in
     * {@link SetSchemaRequest.Builder#addRequiredPermissionsForSchemaTypeVisibility}
     */
    public static final int READ_ASSISTANT_APP_SEARCH_DATA = 6;

    /**
     * The {@link android.Manifest.permission#READ_CALENDAR} AppSearch supported in {@link
     * SetSchemaRequest.Builder#addRequiredPermissionsForSchemaTypeVisibility}
     */
    public static final int READ_CALENDAR = 2;

    /**
     * The {@link android.Manifest.permission#READ_CONTACTS} AppSearch supported in {@link
     * SetSchemaRequest.Builder#addRequiredPermissionsForSchemaTypeVisibility}
     */
    public static final int READ_CONTACTS = 3;

    /**
     * The {@link android.Manifest.permission#READ_EXTERNAL_STORAGE} AppSearch supported in {@link
     * SetSchemaRequest.Builder#addRequiredPermissionsForSchemaTypeVisibility}
     */
    public static final int READ_EXTERNAL_STORAGE = 4;

    /**
     * The {@link android.Manifest.permission#READ_HOME_APP_SEARCH_DATA} AppSearch supported in
     * {@link SetSchemaRequest.Builder#addRequiredPermissionsForSchemaTypeVisibility}
     */
    public static final int READ_HOME_APP_SEARCH_DATA = 5;

    /**
     * The {@link android.Manifest.permission#READ_SMS} AppSearch supported in {@link
     * SetSchemaRequest.Builder#addRequiredPermissionsForSchemaTypeVisibility}
     */
    public static final int READ_SMS = 1;

    private SetSchemaRequest() {
    }
}
