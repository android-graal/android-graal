package android.os.storage;

import android.os.Parcelable;

/**
 * Information about a shared/external storage volume for a specific user.
 *
 * <p>
 * A device always has one (and one only) primary storage volume, but it could have extra volumes,
 * like SD cards and USB drives. This object represents the logical view of a storage
 * volume for a specific user: different users might have different views for the same physical
 * volume (for example, if the volume is a built-in emulated storage).
 *
 * <p>
 * The storage volume is not necessarily mounted, applications should use {@link #getState()} to
 * verify its state.
 *
 * <p>
 * Applications willing to read or write to this storage volume needs to get a permission from the
 * user first, which can be achieved in the following ways:
 *
 * <ul>
 * <li>To get access to standard directories (like the {@link Environment#DIRECTORY_PICTURES}), they
 * can use the {@link #createAccessIntent(String)}. This is the recommend way, since it provides a
 * simpler API and narrows the access to the given directory (and its descendants).
 * <li>To get access to any directory (and its descendants), they can use the Storage Access
 * Framework APIs (such as {@link Intent#ACTION_OPEN_DOCUMENT} and
 * {@link Intent#ACTION_OPEN_DOCUMENT_TREE}, although these APIs do not guarantee the user will
 * select this specific volume.
 * <li>To get read and write access to the primary storage volume, applications can declare the
 * {@link android.Manifest.permission#READ_EXTERNAL_STORAGE} and
 * {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE} permissions respectively, with the
 * latter including the former. This approach is discouraged, since users may be hesitant to grant
 * broad access to all files contained on a storage device.
 * </ul>
 *
 * <p>It can be obtained through {@link StorageManager#getStorageVolumes()} and
 * {@link StorageManager#getPrimaryStorageVolume()} and also as an extra in some broadcasts
 * (see {@link #EXTRA_STORAGE_VOLUME}).
 *
 * <p>
 * See {@link Environment#getExternalStorageDirectory()} for more info about shared/external
 * storage semantics.
 */
public final class StorageVolume implements Parcelable {
    /**
     * Name of the {@link Parcelable} extra in the {@link Intent#ACTION_MEDIA_REMOVED},
     * {@link Intent#ACTION_MEDIA_UNMOUNTED}, {@link Intent#ACTION_MEDIA_CHECKING},
     * {@link Intent#ACTION_MEDIA_NOFS}, {@link Intent#ACTION_MEDIA_MOUNTED},
     * {@link Intent#ACTION_MEDIA_SHARED}, {@link Intent#ACTION_MEDIA_BAD_REMOVAL},
     * {@link Intent#ACTION_MEDIA_UNMOUNTABLE}, and {@link Intent#ACTION_MEDIA_EJECT} broadcast that
     * contains a {@link StorageVolume}.
     */
    public static final String EXTRA_STORAGE_VOLUME = "android.os.storage.extra.STORAGE_VOLUME";

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private StorageVolume() {
    }
}
