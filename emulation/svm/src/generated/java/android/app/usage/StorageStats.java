package android.app.usage;

import android.os.Parcelable;

/**
 * Storage statistics for a UID, package, or {@link UserHandle} on a single
 * storage volume.
 *
 * @see StorageStatsManager
 */
public final class StorageStats implements Parcelable {
    /**
     * Represents all .apk files in application code path.
     * Can be used as an input to {@link #getAppBytesByDataType(int)}
     * to get the sum of sizes for files of this type.
     */
    public static final int APP_DATA_TYPE_FILE_TYPE_APK = 3;

    /**
     * Represents current profile of application.
     *
     * Current profiles may or may not be used during the next profile-guided dexopt.
     *
     * Can be used as an input to {@link #getAppBytesByDataType(int)}
     * to get the size of files of this type. This size fluctuates regularly,
     * it goes up when the user uses more and more classes/methods and comes down when
     * a deamon merges this into the ref profile and does profile-guided dexopt.
     */
    public static final int APP_DATA_TYPE_FILE_TYPE_CURRENT_PROFILE = 2;

    /**
     * Represents all nonstale dexopt and runtime artifacts of application.
     * This includes AOT-compiled code and other data that can speed up app execution.
     * For more detailed information, read the
     * <a href="https://source.android.com/docs/core/runtime/jit-compiler#flow">JIT compiler</a>
     * guide.
     *
     * Dexopt artifacts become stale when one of their dependencies
     * has changed. They may be cleaned up or replaced by ART Services at any time.
     *
     * For a preload app, this type includes dexopt artifacts on readonly partitions
     * if they are up-to-date.
     *
     * Can be used as an input to {@link #getAppBytesByDataType(int)}
     * to get the sum of sizes for files of this type. The sum might include the size of data
     * that is part of appBytes, dataBytes or cacheBytes.
     */
    public static final int APP_DATA_TYPE_FILE_TYPE_DEXOPT_ARTIFACT = 0;

    /**
     * Represents all .dm files in application code path.
     * Can be used as an input to {@link #getAppBytesByDataType(int)}
     * to get the sum of sizes for files of this type.
     */
    public static final int APP_DATA_TYPE_FILE_TYPE_DM = 4;

    /**
     * Represents reference profile of application.
     *
     * Reference profiles are the ones used during the last profile-guided dexopt.
     * If the last dexopt wasn't profile-guided, then these profiles were not used.
     *
     * Can be used as an input to {@link #getAppBytesByDataType(int)}
     * to get the size of files of this type.
     */
    public static final int APP_DATA_TYPE_FILE_TYPE_REFERENCE_PROFILE = 1;

    /**
     * Represents lib/ in application code path.
     * Can be used as an input to {@link #getAppBytesByDataType(int)}
     * to get the size of lib/ directory.
     */
    public static final int APP_DATA_TYPE_LIB = 5;

    private StorageStats() {
    }
}
