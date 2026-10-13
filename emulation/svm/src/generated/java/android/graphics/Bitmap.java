package android.graphics;

import android.os.Parcelable;

public final class Bitmap implements Parcelable {
    /**
     * Indicates that the bitmap was created for an unknown pixel density.
     *
     * @see Bitmap#getDensity()
     * @see Bitmap#setDensity(int)
     */
    public static final int DENSITY_NONE = 0;

    private Bitmap() {
    }
}
