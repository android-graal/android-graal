package android.content;

import android.os.Parcelable;

/**
 * This class is used to store a set of values that the {@link ContentResolver}
 * can process.
 */
public final class ContentValues implements Parcelable {
    public static final String TAG = "ContentValues";

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private ContentValues() {
    }
}
