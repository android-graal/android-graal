package android.print;

import android.os.Parcelable;

/**
 * This class represents the attributes of a print job. These attributes
 * describe how the printed content should be laid out. For example, the
 * print attributes may state that the content should be laid out on a
 * letter size with 300 DPI (dots per inch) resolution, have a margin of
 * 10 mills (thousand of an inch) on all sides, and be black and white.
 */
public final class PrintAttributes implements Parcelable {
    /**
     * Color mode: Color color scheme, for example many colors are used.
     */
    public static final int COLOR_MODE_COLOR = 2;

    /**
     * Color mode: Monochrome color scheme, for example one color is used.
     */
    public static final int COLOR_MODE_MONOCHROME = 1;

    /**
     * Duplex mode: Pages are turned sideways along the long edge - like a book.
     */
    public static final int DUPLEX_MODE_LONG_EDGE = 2;

    /**
     * Duplex mode: No duplexing.
     */
    public static final int DUPLEX_MODE_NONE = 1;

    /**
     * Duplex mode: Pages are turned upwards along the short edge - like a notpad.
     */
    public static final int DUPLEX_MODE_SHORT_EDGE = 4;

    private PrintAttributes() {
    }
}
