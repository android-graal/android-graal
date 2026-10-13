package android.media.tv.interactive;

import android.os.Parcelable;

/**
 * This class is used to specify meta information of a TV interactive app.
 */
public final class TvInteractiveAppServiceInfo implements Parcelable {
    /**
     * ATSC interactive app type
     */
    public static final int INTERACTIVE_APP_TYPE_ATSC = 2;

    /**
     * Ginga interactive app type
     */
    public static final int INTERACTIVE_APP_TYPE_GINGA = 4;

    /**
     * HbbTV interactive app type
     */
    public static final int INTERACTIVE_APP_TYPE_HBBTV = 1;

    /**
     * Other interactive app type
     */
    public static final int INTERACTIVE_APP_TYPE_OTHER = -2147483648;

    /**
     * Targeted Advertisement interactive app type
     */
    public static final int INTERACTIVE_APP_TYPE_TARGETED_AD = 8;

    private TvInteractiveAppServiceInfo() {
    }
}
