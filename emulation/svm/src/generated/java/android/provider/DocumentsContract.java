package android.provider;

/**
 * Defines the contract between a documents provider and the platform.
 * <p>
 * To create a document provider, extend {@link DocumentsProvider}, which
 * provides a foundational implementation of this contract.
 * <p>
 * All client apps must hold a valid URI permission grant to access documents,
 * typically issued when a user makes a selection through
 * {@link Intent#ACTION_OPEN_DOCUMENT}, {@link Intent#ACTION_CREATE_DOCUMENT},
 * or {@link Intent#ACTION_OPEN_DOCUMENT_TREE}.
 *
 * @see DocumentsProvider
 */
public final class DocumentsContract {
    /**
     * Action of intent issued by DocumentsUI when user wishes to open/configure/manage a particular
     * document in the provider application.
     *
     * <p>When issued, the intent will include the URI of the document as the intent data.
     *
     * <p>A provider wishing to provide support for this action should do two things.
     * <li>Add an {@code <intent-filter>} matching this action.
     * <li>When supplying information in {@link DocumentsProvider#queryChildDocuments}, include
     * {@link Document#FLAG_SUPPORTS_SETTINGS} in the flags for each document that supports
     * settings.
     */
    public static final String ACTION_DOCUMENT_SETTINGS = "android.provider.action.DOCUMENT_SETTINGS";

    /**
     * Optional string included in a directory {@link Cursor#getExtras()}
     * providing an error message that should be shown to a user. For example, a
     * provider may wish to indicate that a network error occurred. The user may
     * choose to retry, resulting in a new query.
     */
    public static final String EXTRA_ERROR = "error";

    /**
     * Set this in a DocumentsUI intent to cause a package's own roots to be
     * excluded from the roots list.
     */
    public static final String EXTRA_EXCLUDE_SELF = "android.provider.extra.EXCLUDE_SELF";

    /**
     * Optional string included in a directory {@link Cursor#getExtras()}
     * providing an informational message that should be shown to a user. For
     * example, a provider may wish to indicate that not all documents are
     * available.
     */
    public static final String EXTRA_INFO = "info";

    /**
     * Sets the desired initial location visible to user when file chooser is shown.
     *
     * <p>Applicable to {@link Intent} with actions:
     * <ul>
     *      <li>{@link Intent#ACTION_OPEN_DOCUMENT}</li>
     *      <li>{@link Intent#ACTION_CREATE_DOCUMENT}</li>
     *      <li>{@link Intent#ACTION_OPEN_DOCUMENT_TREE}</li>
     * </ul>
     *
     * <p>Location should specify a document URI or a tree URI with document ID. If
     * this URI identifies a non-directory, document navigator will attempt to use the parent
     * of the document as the initial location.
     *
     * <p>The initial location is system specific if this extra is missing or document navigator
     * failed to locate the desired initial location.
     */
    public static final String EXTRA_INITIAL_URI = "android.provider.extra.INITIAL_URI";

    /**
     * Optional boolean flag included in a directory {@link Cursor#getExtras()}
     * indicating that a document provider is still loading data. For example, a
     * provider has returned some results, but is still waiting on an
     * outstanding network request. The provider must send a content changed
     * notification when loading is finished.
     *
     * @see ContentResolver#notifyChange(Uri, android.database.ContentObserver,
     *      boolean)
     */
    public static final String EXTRA_LOADING = "loading";

    /**
     * An extra number of degrees that an image should be rotated during the
     * decode process to be presented correctly.
     *
     * @see AssetFileDescriptor#getExtras()
     * @see android.provider.MediaStore.Images.ImageColumns#ORIENTATION
     */
    public static final String EXTRA_ORIENTATION = "android.provider.extra.ORIENTATION";

    /**
     * Overrides the default prompt text in DocumentsUI when set in an intent.
     */
    public static final String EXTRA_PROMPT = "android.provider.extra.PROMPT";

    /**
     * Get Exif information using DocumentsContract#getDocumentMetadata.
     *
     * @see #getDocumentMetadata(ContentResolver, Uri)
     */
    public static final String METADATA_EXIF = "android:documentExif";

    /**
     * Get total count of all documents currently stored under the given
     * directory tree. Only valid for {@link Document#MIME_TYPE_DIR} documents.
     *
     * @see #getDocumentMetadata(ContentResolver, Uri)
     */
    public static final String METADATA_TREE_COUNT = "android:metadataTreeCount";

    /**
     * Get total size of all documents currently stored under the given
     * directory tree. Only valid for {@link Document#MIME_TYPE_DIR} documents.
     *
     * @see #getDocumentMetadata(ContentResolver, Uri)
     */
    public static final String METADATA_TREE_SIZE = "android:metadataTreeSize";

    /**
     * Get string array identifies the type or types of metadata returned
     * using DocumentsContract#getDocumentMetadata.
     *
     * @see #getDocumentMetadata(ContentResolver, Uri)
     */
    public static final String METADATA_TYPES = "android:documentMetadataTypes";

    /**
     * Intent action used to identify {@link DocumentsProvider} instances. This
     * is used in the {@code <intent-filter>} of a {@code <provider>}.
     */
    public static final String PROVIDER_INTERFACE = "android.content.action.DOCUMENTS_PROVIDER";

    /**
     * Key for {@link DocumentsProvider} to query display name is matched.
     * The match of display name is partial matching and case-insensitive.
     * Ex: The value is "o", the display name of the results will contain
     * both "foo" and "Open".
     *
     * @see DocumentsProvider#querySearchDocuments(String, String[],
     *      Bundle)
     */
    public static final String QUERY_ARG_DISPLAY_NAME = "android:query-arg-display-name";

    /**
     * Key for {@link DocumentsProvider} to decide whether the files that
     * have been added to MediaStore should be excluded. If the value is
     * true, exclude them. Otherwise, include them.
     *
     * @see DocumentsProvider#querySearchDocuments(String, String[],
     *      Bundle)
     */
    public static final String QUERY_ARG_EXCLUDE_MEDIA = "android:query-arg-exclude-media";

    /**
     * Key for {@link DocumentsProvider} to query the file size in bytes is
     * larger than the value.
     *
     * @see DocumentsProvider#querySearchDocuments(String, String[],
     *      Bundle)
     */
    public static final String QUERY_ARG_FILE_SIZE_OVER = "android:query-arg-file-size-over";

    /**
     * Key for {@link DocumentsProvider} to query the last modified time
     * is newer than the value. The unit is in milliseconds since
     * January 1, 1970 00:00:00.0 UTC.
     *
     * @see DocumentsProvider#querySearchDocuments(String, String[],
     *      Bundle)
     * @see Document#COLUMN_LAST_MODIFIED
     */
    public static final String QUERY_ARG_LAST_MODIFIED_AFTER = "android:query-arg-last-modified-after";

    /**
     * Key for {@link DocumentsProvider} to query mime types is matched.
     * The value is a string array, it can support different mime types.
     * Each items will be treated as "OR" condition. Ex: {"image/*" ,
     * "video/*"}. The mime types of the results will contain both image
     * type and video type.
     *
     * @see DocumentsProvider#querySearchDocuments(String, String[],
     *      Bundle)
     */
    public static final String QUERY_ARG_MIME_TYPES = "android:query-arg-mime-types";

    private DocumentsContract() {
    }

    /**
     * Constants related to a document, including {@link Cursor} column names
     * and flags.
     * <p>
     * A document can be either an openable stream (with a specific MIME type),
     * or a directory containing additional documents (with the
     * {@link #MIME_TYPE_DIR} MIME type). A directory represents the top of a
     * subtree containing zero or more documents, which can recursively contain
     * even more documents and directories.
     * <p>
     * All columns are <em>read-only</em> to client applications.
     */
    public static final class Document {
        /**
         * Display name of a document, used as the primary title displayed to a
         * user. This column is required.
         * <p>
         * Type: STRING
         */
        public static final String COLUMN_DISPLAY_NAME = "_display_name";

        /**
         * Unique ID of a document. This ID is both provided by and interpreted
         * by a {@link DocumentsProvider}, and should be treated as an opaque
         * value by client applications. This column is required.
         * <p>
         * Each document must have a unique ID within a provider, but that
         * single document may be included as a child of multiple directories.
         * <p>
         * A provider must always return durable IDs, since they will be used to
         * issue long-term URI permission grants when an application interacts
         * with {@link Intent#ACTION_OPEN_DOCUMENT} and
         * {@link Intent#ACTION_CREATE_DOCUMENT}.
         * <p>
         * Type: STRING
         */
        public static final String COLUMN_DOCUMENT_ID = "document_id";

        /**
         * Flags that apply to a document. This column is required.
         * <p>
         * Type: INTEGER (int)
         *
         * @see #FLAG_DIR_BLOCKS_OPEN_DOCUMENT_TREE
         * @see #FLAG_DIR_PREFERS_GRID
         * @see #FLAG_DIR_PREFERS_LAST_MODIFIED
         * @see #FLAG_DIR_SUPPORTS_CREATE
         * @see #FLAG_PARTIAL
         * @see #FLAG_SUPPORTS_COPY
         * @see #FLAG_SUPPORTS_DELETE
         * @see #FLAG_SUPPORTS_METADATA
         * @see #FLAG_SUPPORTS_MOVE
         * @see #FLAG_SUPPORTS_REMOVE
         * @see #FLAG_SUPPORTS_RENAME
         * @see #FLAG_SUPPORTS_SETTINGS
         * @see #FLAG_SUPPORTS_THUMBNAIL
         * @see #FLAG_SUPPORTS_WRITE
         * @see #FLAG_VIRTUAL_DOCUMENT
         * @see #FLAG_WEB_LINKABLE
         */
        public static final String COLUMN_FLAGS = "flags";

        /**
         * Specific icon resource ID for a document. This column is optional,
         * and may be {@code null} to use a platform-provided default icon based
         * on {@link #COLUMN_MIME_TYPE}.
         * <p>
         * Type: INTEGER (int)
         */
        public static final String COLUMN_ICON = "icon";

        /**
         * Timestamp when a document was last modified, in milliseconds since
         * January 1, 1970 00:00:00.0 UTC. This column is required, and may be
         * {@code null} if unknown. A {@link DocumentsProvider} can update this
         * field using events from {@link OnCloseListener} or other reliable
         * {@link ParcelFileDescriptor} transports.
         * <p>
         * Type: INTEGER (long)
         *
         * @see System#currentTimeMillis()
         */
        public static final String COLUMN_LAST_MODIFIED = "last_modified";

        /**
         * Concrete MIME type of a document. For example, "image/png" or
         * "application/pdf" for openable files. A document can also be a
         * directory containing additional documents, which is represented with
         * the {@link #MIME_TYPE_DIR} MIME type. This column is required.
         * <p>
         * Type: STRING
         *
         * @see #MIME_TYPE_DIR
         */
        public static final String COLUMN_MIME_TYPE = "mime_type";

        /**
         * Size of a document, in bytes, or {@code null} if unknown. This column
         * is required.
         * <p>
         * Type: INTEGER (long)
         */
        public static final String COLUMN_SIZE = "_size";

        /**
         * Summary of a document, which may be shown to a user. This column is
         * optional, and may be {@code null}.
         * <p>
         * Type: STRING
         */
        public static final String COLUMN_SUMMARY = "summary";

        /**
         * Flag indicating that a document is a directory that wants to block itself
         * from being selected when the user launches an {@link Intent#ACTION_OPEN_DOCUMENT_TREE}
         * intent. Individual files can still be selected when launched via other intents
         * like {@link Intent#ACTION_OPEN_DOCUMENT} and {@link Intent#ACTION_GET_CONTENT}.
         * Only valid when {@link #COLUMN_MIME_TYPE} is {@link #MIME_TYPE_DIR}.
         * <p>
         * Note that this flag <em>only</em> applies to the single directory to which it is
         * applied. It does <em>not</em> block the user from selecting either a parent or
         * child directory during an {@link Intent#ACTION_OPEN_DOCUMENT_TREE} request.
         * In particular, the only way to guarantee that a specific directory can never
         * be granted via an {@link Intent#ACTION_OPEN_DOCUMENT_TREE} request is to ensure
         * that both it and <em>all of its parent directories</em> have set this flag.
         *
         * @see Intent#ACTION_OPEN_DOCUMENT_TREE
         * @see #COLUMN_FLAGS
         */
        public static final int FLAG_DIR_BLOCKS_OPEN_DOCUMENT_TREE = 32768;

        /**
         * Flag indicating that a directory prefers its contents be shown in a
         * larger format grid. Usually suitable when a directory contains mostly
         * pictures. Only valid when {@link #COLUMN_MIME_TYPE} is
         * {@link #MIME_TYPE_DIR}.
         *
         * @see #COLUMN_FLAGS
         */
        public static final int FLAG_DIR_PREFERS_GRID = 16;

        /**
         * Flag indicating that a directory prefers its contents be sorted by
         * {@link #COLUMN_LAST_MODIFIED}. Only valid when
         * {@link #COLUMN_MIME_TYPE} is {@link #MIME_TYPE_DIR}.
         *
         * @see #COLUMN_FLAGS
         */
        public static final int FLAG_DIR_PREFERS_LAST_MODIFIED = 32;

        /**
         * Flag indicating that a document is a directory that supports creation
         * of new files within it. Only valid when {@link #COLUMN_MIME_TYPE} is
         * {@link #MIME_TYPE_DIR}.
         *
         * @see #COLUMN_FLAGS
         * @see DocumentsProvider#createDocument(String, String, String)
         */
        public static final int FLAG_DIR_SUPPORTS_CREATE = 8;

        /**
         * Flag indicating that a document is not complete, likely its
         * contents are being downloaded. Partial files cannot be opened,
         * copied, moved in the UI. But they can be deleted and retried
         * if they represent a failed download.
         *
         * @see #COLUMN_FLAGS
         */
        public static final int FLAG_PARTIAL = 8192;

        /**
         * Flag indicating that a document can be copied to another location
         * within the same document provider.
         *
         * @see #COLUMN_FLAGS
         * @see DocumentsContract#copyDocument(ContentResolver, Uri, Uri)
         * @see DocumentsProvider#copyDocument(String, String)
         */
        public static final int FLAG_SUPPORTS_COPY = 128;

        /**
         * Flag indicating that a document is deletable.
         *
         * @see #COLUMN_FLAGS
         * @see DocumentsContract#deleteDocument(ContentResolver, Uri)
         * @see DocumentsProvider#deleteDocument(String)
         */
        public static final int FLAG_SUPPORTS_DELETE = 4;

        /**
         * Flag indicating that a document has available metadata that can be read
         * using DocumentsContract#getDocumentMetadata
         *
         * @see #COLUMN_FLAGS
         * @see DocumentsContract#getDocumentMetadata(ContentResolver, Uri)
         */
        public static final int FLAG_SUPPORTS_METADATA = 16384;

        /**
         * Flag indicating that a document can be moved to another location
         * within the same document provider.
         *
         * @see #COLUMN_FLAGS
         * @see DocumentsContract#moveDocument(ContentResolver, Uri, Uri, Uri)
         * @see DocumentsProvider#moveDocument(String, String, String)
         */
        public static final int FLAG_SUPPORTS_MOVE = 256;

        /**
         * Flag indicating that a document can be removed from a parent.
         *
         * @see #COLUMN_FLAGS
         * @see DocumentsContract#removeDocument(ContentResolver, Uri, Uri)
         * @see DocumentsProvider#removeDocument(String, String)
         */
        public static final int FLAG_SUPPORTS_REMOVE = 1024;

        /**
         * Flag indicating that a document can be renamed.
         *
         * @see #COLUMN_FLAGS
         * @see DocumentsContract#renameDocument(ContentResolver, Uri, String)
         * @see DocumentsProvider#renameDocument(String, String)
         */
        public static final int FLAG_SUPPORTS_RENAME = 64;

        /**
         * Flag indicating that a document has settings that can be configured by user.
         *
         * @see #COLUMN_FLAGS
         * @see #ACTION_DOCUMENT_SETTINGS
         */
        public static final int FLAG_SUPPORTS_SETTINGS = 2048;

        /**
         * Flag indicating that a document can be represented as a thumbnail.
         *
         * @see #COLUMN_FLAGS
         * @see DocumentsContract#getDocumentThumbnail(ContentResolver, Uri,
         *      Point, CancellationSignal)
         * @see DocumentsProvider#openDocumentThumbnail(String, Point,
         *      android.os.CancellationSignal)
         */
        public static final int FLAG_SUPPORTS_THUMBNAIL = 1;

        /**
         * Flag indicating that a document supports writing.
         * <p>
         * When a document is opened with {@link Intent#ACTION_OPEN_DOCUMENT},
         * the calling application is granted both
         * {@link Intent#FLAG_GRANT_READ_URI_PERMISSION} and
         * {@link Intent#FLAG_GRANT_WRITE_URI_PERMISSION}. However, the actual
         * writability of a document may change over time, for example due to
         * remote access changes. This flag indicates that a document client can
         * expect {@link ContentResolver#openOutputStream(Uri)} to succeed.
         *
         * @see #COLUMN_FLAGS
         */
        public static final int FLAG_SUPPORTS_WRITE = 2;

        /**
         * Flag indicating that a document is virtual, and doesn't have byte
         * representation in the MIME type specified as {@link #COLUMN_MIME_TYPE}.
         *
         * <p><em>Virtual documents must have at least one alternative streamable
         * format via {@link DocumentsProvider#openTypedDocument}</em>
         *
         * @see #COLUMN_FLAGS
         * @see #COLUMN_MIME_TYPE
         * @see DocumentsProvider#openTypedDocument(String, String, Bundle,
         *      android.os.CancellationSignal)
         * @see DocumentsProvider#getDocumentStreamTypes(String, String)
         */
        public static final int FLAG_VIRTUAL_DOCUMENT = 512;

        /**
         * Flag indicating that a Web link can be obtained for the document.
         *
         * @see #COLUMN_FLAGS
         * @see DocumentsProvider#createWebLinkIntent(String, Bundle)
         */
        public static final int FLAG_WEB_LINKABLE = 4096;

        /**
         * MIME type of a document which is a directory that may contain
         * additional documents.
         *
         * @see #COLUMN_MIME_TYPE
         */
        public static final String MIME_TYPE_DIR = "vnd.android.document/directory";

        private Document() {
        }
    }

    /**
     * Constants related to a root of documents, including {@link Cursor} column
     * names and flags. A root is the start of a tree of documents, such as a
     * physical storage device, or an account. Each root starts at the directory
     * referenced by {@link Root#COLUMN_DOCUMENT_ID}, which can recursively
     * contain both documents and directories.
     * <p>
     * All columns are <em>read-only</em> to client applications.
     */
    public static final class Root {
        /**
         * Number of bytes available in this root. This column is optional, and
         * may be {@code null} if unknown or unbounded.
         * <p>
         * Type: INTEGER (long)
         */
        public static final String COLUMN_AVAILABLE_BYTES = "available_bytes";

        /**
         * Capacity of a root in bytes. This column is optional, and may be
         * {@code null} if unknown or unbounded.
         * <p>
         * Type: INTEGER (long)
         */
        public static final String COLUMN_CAPACITY_BYTES = "capacity_bytes";

        /**
         * Document which is a directory that represents the top directory of
         * this root. This column is required.
         * <p>
         * Type: STRING
         *
         * @see Document#COLUMN_DOCUMENT_ID
         */
        public static final String COLUMN_DOCUMENT_ID = "document_id";

        /**
         * Flags that apply to a root. This column is required.
         * <p>
         * Type: INTEGER (int)
         *
         * @see #FLAG_LOCAL_ONLY
         * @see #FLAG_SUPPORTS_CREATE
         * @see #FLAG_SUPPORTS_RECENTS
         * @see #FLAG_SUPPORTS_SEARCH
         */
        public static final String COLUMN_FLAGS = "flags";

        /**
         * Icon resource ID for a root. This column is required.
         * <p>
         * Type: INTEGER (int)
         */
        public static final String COLUMN_ICON = "icon";

        /**
         * MIME types supported by this root. This column is optional, and if
         * {@code null} the root is assumed to support all MIME types. Multiple
         * MIME types can be separated by a newline. For example, a root
         * supporting audio might return "audio/*\napplication/x-flac".
         * <p>
         * Type: STRING
         */
        public static final String COLUMN_MIME_TYPES = "mime_types";

        /**
         * Query arguments supported by this root. This column is optional
         * and related to {@link #COLUMN_FLAGS} and {@link #FLAG_SUPPORTS_SEARCH}.
         * If the flags include {@link #FLAG_SUPPORTS_SEARCH}, and the column is
         * {@code null}, the root is assumed to support {@link #QUERY_ARG_DISPLAY_NAME}
         * search of {@link Document#COLUMN_DISPLAY_NAME}. Multiple query arguments
         * can be separated by a newline. For example, a root supporting
         * {@link #QUERY_ARG_MIME_TYPES} and {@link #QUERY_ARG_DISPLAY_NAME} might
         * return "android:query-arg-mime-types\nandroid:query-arg-display-name".
         * <p>
         * Type: STRING
         * @see #COLUMN_FLAGS
         * @see #FLAG_SUPPORTS_SEARCH
         * @see #QUERY_ARG_DISPLAY_NAME
         * @see #QUERY_ARG_FILE_SIZE_OVER
         * @see #QUERY_ARG_LAST_MODIFIED_AFTER
         * @see #QUERY_ARG_MIME_TYPES
         * @see DocumentsProvider#querySearchDocuments(String, String[],
         *      Bundle)
         */
        public static final String COLUMN_QUERY_ARGS = "query_args";

        /**
         * Unique ID of a root. This ID is both provided by and interpreted by a
         * {@link DocumentsProvider}, and should be treated as an opaque value
         * by client applications. This column is required.
         * <p>
         * Type: STRING
         */
        public static final String COLUMN_ROOT_ID = "root_id";

        /**
         * Summary for this root, which may be shown to a user. This column is
         * optional, and may be {@code null}. For a single storage service
         * surfacing multiple accounts as different roots, this summary should
         * be the name of the account.
         * <p>
         * Type: STRING
         */
        public static final String COLUMN_SUMMARY = "summary";

        /**
         * Title for a root, which will be shown to a user. This column is
         * required. For a single storage service surfacing multiple accounts as
         * different roots, this title should be the name of the service.
         * <p>
         * Type: STRING
         */
        public static final String COLUMN_TITLE = "title";

        /**
         * Flag indicating that this root is currently empty. This may be used
         * to hide the root when opening documents, but the root will still be
         * shown when creating documents and {@link #FLAG_SUPPORTS_CREATE} is
         * also set. If the value of this flag changes, such as when a root
         * becomes non-empty, you must send a content changed notification for
         * {@link DocumentsContract#buildRootsUri(String)}.
         *
         * @see #COLUMN_FLAGS
         * @see ContentResolver#notifyChange(Uri,
         *      android.database.ContentObserver, boolean)
         */
        public static final int FLAG_EMPTY = 64;

        /**
         * Flag indicating that this root offers content that is strictly local
         * on the device. That is, no network requests are made for the content.
         *
         * @see #COLUMN_FLAGS
         * @see Intent#EXTRA_LOCAL_ONLY
         */
        public static final int FLAG_LOCAL_ONLY = 2;

        /**
         * Flag indicating that at least one directory under this root supports
         * creating content. Roots with this flag will be shown when an
         * application interacts with {@link Intent#ACTION_CREATE_DOCUMENT}.
         *
         * @see #COLUMN_FLAGS
         */
        public static final int FLAG_SUPPORTS_CREATE = 1;

        /**
         * Flag indicating that this root can be ejected.
         *
         * @see #COLUMN_FLAGS
         * @see DocumentsContract#ejectRoot(ContentResolver, Uri)
         * @see DocumentsProvider#ejectRoot(String)
         */
        public static final int FLAG_SUPPORTS_EJECT = 32;

        /**
         * Flag indicating that this root supports testing parent child
         * relationships.
         *
         * @see #COLUMN_FLAGS
         * @see DocumentsProvider#isChildDocument(String, String)
         */
        public static final int FLAG_SUPPORTS_IS_CHILD = 16;

        /**
         * Flag indicating that this root can be queried to provide recently
         * modified documents.
         *
         * @see #COLUMN_FLAGS
         * @see DocumentsContract#buildRecentDocumentsUri(String, String)
         * @see DocumentsProvider#queryRecentDocuments(String, String[])
         */
        public static final int FLAG_SUPPORTS_RECENTS = 4;

        /**
         * Flag indicating that this root supports search.
         *
         * @see #COLUMN_FLAGS
         * @see DocumentsContract#buildSearchDocumentsUri(String, String,
         *      String)
         * @see DocumentsProvider#querySearchDocuments(String, String,
         *      String[])
         * @see DocumentsProvider#querySearchDocuments(String, String[],
         *      Bundle)
         */
        public static final int FLAG_SUPPORTS_SEARCH = 8;

        /**
         * MIME type for a root.
         */
        public static final String MIME_TYPE_ITEM = "vnd.android.document/root";

        private Root() {
        }
    }
}
