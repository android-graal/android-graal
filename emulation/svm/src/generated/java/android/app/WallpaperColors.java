package android.app;

import android.os.Parcelable;

/**
 * Provides information about the colors of a wallpaper.
 * <p>
 * Exposes the 3 most visually representative colors of a wallpaper. Can be either
 * {@link WallpaperColors#getPrimaryColor()}, {@link WallpaperColors#getSecondaryColor()}
 * or {@link WallpaperColors#getTertiaryColor()}.
 */
public final class WallpaperColors implements Parcelable {
    /**
     * Specifies that dark text is preferred over the current wallpaper for best presentation.
     * <p>
     * eg. A launcher may set its text color to black if this flag is specified.
     */
    public static final int HINT_SUPPORTS_DARK_TEXT = 1;

    /**
     * Specifies that dark theme is preferred over the current wallpaper for best presentation.
     * <p>
     * eg. A launcher may set its drawer color to black if this flag is specified.
     */
    public static final int HINT_SUPPORTS_DARK_THEME = 2;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private WallpaperColors() {
    }
}
