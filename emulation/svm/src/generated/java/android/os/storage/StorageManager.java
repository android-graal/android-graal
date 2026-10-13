package android.os.storage;

/**
 * StorageManager is the interface to the systems storage service. The storage
 * manager handles storage-related items such as Opaque Binary Blobs (OBBs).
 * <p>
 * OBBs contain a filesystem that maybe be encrypted on disk and mounted
 * on-demand from an application. OBBs are a good way of providing large amounts
 * of binary assets without packaging them into APKs as they may be multiple
 * gigabytes in size. However, due to their size, they're most likely stored in
 * a shared storage pool accessible from all programs. The system does not
 * guarantee the security of the OBB file itself: if any program modifies the
 * OBB, there is no guarantee that a read from that OBB will produce the
 * expected output.
 */
public final class StorageManager {
    /**
     * Activity Action: Allows the user to free up space by clearing app external cache directories.
     * The intent doesn't automatically clear cache, but shows a dialog and lets the user decide.
     * <p>
     * This intent should be launched using
     * {@link Activity#startActivityForResult(Intent, int)} so that the user
     * knows which app is requesting to clear cache. The returned result will be:
     * {@link Activity#RESULT_OK} if the activity was launched and all cache was cleared,
     * {@link OsConstants#EIO} if an error occurred while clearing the cache or
     * {@link Activity#RESULT_CANCELED} otherwise.
     */
    public static final String ACTION_CLEAR_APP_CACHE = "android.os.storage.action.CLEAR_APP_CACHE";

    /**
     * Activity Action: Allows the user to manage their storage. This activity
     * provides the ability to free up space on the device by deleting data such
     * as apps.
     * <p>
     * If the sending application has a specific storage device or allocation
     * size in mind, they can optionally define {@link #EXTRA_UUID} or
     * {@link #EXTRA_REQUESTED_BYTES}, respectively.
     * <p>
     * This intent should be launched using
     * {@link Activity#startActivityForResult(Intent, int)} so that the user
     * knows which app is requesting the storage space. The returned result will
     * be {@link Activity#RESULT_OK} if the requested space was made available,
     * or {@link Activity#RESULT_CANCELED} otherwise.
     */
    public static final String ACTION_MANAGE_STORAGE = "android.os.storage.action.MANAGE_STORAGE";

    /**
     * Extra used to indicate the total size (in bytes) that an application is
     * interested in allocating.
     * <p>
     * When defined, the management UI will help guide the user to free up
     * enough disk space to reach this requested value.
     *
     * @see #ACTION_MANAGE_STORAGE
     */
    public static final String EXTRA_REQUESTED_BYTES = "android.os.storage.extra.REQUESTED_BYTES";

    /**
     * Extra {@link UUID} used to indicate the storage volume where an
     * application is interested in allocating or managing disk space.
     *
     * @see #ACTION_MANAGE_STORAGE
     * @see #UUID_DEFAULT
     * @see #getUuidForPath(File)
     * @see Intent#putExtra(String, java.io.Serializable)
     */
    public static final String EXTRA_UUID = "android.os.storage.extra.UUID";

    private StorageManager() {
    }
}
