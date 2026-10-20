package android.content;

import android.os.Parcelable;

/**
 * Description of a single Uri permission grant. This grants may have been
 * created via {@link Intent#FLAG_GRANT_READ_URI_PERMISSION}, etc when sending
 * an {@link Intent}, or explicitly through
 * {@link Context#grantUriPermission(String, android.net.Uri, int)}.
 *
 * @see ContentResolver#getPersistedUriPermissions()
 */
public final class UriPermission implements Parcelable {
    /**
     * Value returned when a permission has not been persisted.
     */
    public static final long INVALID_TIME = -9223372036854775808L;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private UriPermission() {
    }
}
