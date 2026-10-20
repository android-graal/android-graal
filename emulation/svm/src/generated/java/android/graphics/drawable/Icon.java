package android.graphics.drawable;

import android.os.Parcelable;

/**
 * An umbrella container for several serializable graphics representations, including Bitmaps,
 * compressed bitmap images (e.g. JPG or PNG), and drawable resources (including vectors).
 *
 * <a href="https://developer.android.com/training/displaying-bitmaps/index.html">Much ink</a>
 * has been spilled on the best way to load images, and many clients may have different needs when
 * it comes to threading and fetching. This class is therefore focused on encapsulation rather than
 * behavior.
 */
public final class Icon implements Parcelable {
    /**
     * An icon that was created using {@link Icon#createWithAdaptiveBitmap}.
     * @see #getType
     */
    public static final int TYPE_ADAPTIVE_BITMAP = 5;

    /**
     * An icon that was created using {@link Icon#createWithBitmap(Bitmap)}.
     * @see #getType
     */
    public static final int TYPE_BITMAP = 1;

    /**
     * An icon that was created using {@link Icon#createWithData(byte[], int, int)}.
     * @see #getType
     */
    public static final int TYPE_DATA = 3;

    /**
     * An icon that was created using {@link Icon#createWithResource}.
     * @see #getType
     */
    public static final int TYPE_RESOURCE = 2;

    /**
     * An icon that was created using {@link Icon#createWithContentUri}
     * or {@link Icon#createWithFilePath(String)}.
     * @see #getType
     */
    public static final int TYPE_URI = 4;

    /**
     * An icon that was created using {@link Icon#createWithAdaptiveBitmapContentUri}.
     * @see #getType
     */
    public static final int TYPE_URI_ADAPTIVE_BITMAP = 6;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private Icon() {
    }
}
