package android.graphics;

/**
 * The Paint class holds the style and color information about how to draw
 * geometries, text and bitmaps.
 */
public final class Paint {
    /**
     * Paint flag that enables antialiasing when drawing.
     *
     * <p>Enabling this flag will cause all draw operations that support
     * antialiasing to use it.</p>
     *
     * <p>Notable draw operations that do <b>not</b> support antialiasing include:</p>
     * <ul>
     *      <li>{@link android.graphics.Canvas#drawBitmapMesh}</li>
     *      <li>{@link android.graphics.Canvas#drawPatch}</li>
     *      <li>{@link android.graphics.Canvas#drawVertices}</li>
     * </ul>
     *
     * @see #Paint(int)
     * @see #setFlags(int)
     */
    public static final int ANTI_ALIAS_FLAG = 1;

    /**
     * Option for getTextRunCursor.
     *
     * Compute the valid cursor after offset or the limit of the context, whichever is less.
     */
    public static final int CURSOR_AFTER = 0;

    /**
     * Option for getTextRunCursor.
     *
     * Return offset if the cursor at offset is valid, or -1 if it isn't.
     */
    public static final int CURSOR_AT = 4;

    /**
     * Option for getTextRunCursor.
     *
     * Compute the valid cursor at or after the offset or the limit of the context, whichever is
     * less.
     */
    public static final int CURSOR_AT_OR_AFTER = 1;

    /**
     * Option for getTextRunCursor.
     *
     * Compute the valid cursor at or before offset or the start of the context, whichever is
     * greater.
     */
    public static final int CURSOR_AT_OR_BEFORE = 3;

    /**
     * Option for getTextRunCursor.
     *
     * Compute the valid cursor before offset or the start of the context, whichever is greater.
     */
    public static final int CURSOR_BEFORE = 2;

    /**
     * Legacy Paint flag, no longer used.
     */
    public static final int DEV_KERN_TEXT_FLAG = 256;

    /**
     * Paint flag that enables dithering when blitting.
     *
     * <p>Enabling this flag applies a dither to any blit operation where the
     * target's colour space is more constrained than the source.
     *
     * @see #Paint(int)
     * @see #setFlags(int)
     */
    public static final int DITHER_FLAG = 4;

    /**
     * Paint flag that enables the use of bitmap fonts when drawing text.
     *
     * <p>Disabling this flag will prevent text draw operations from using
     * embedded bitmap strikes in fonts, causing fonts with both scalable
     * outlines and bitmap strikes to draw only the scalable outlines, and
     * fonts with only bitmap strikes to not draw at all.</p>
     *
     * @see #Paint(int)
     * @see #setFlags(int)
     */
    public static final int EMBEDDED_BITMAP_TEXT_FLAG = 1024;

    /**
     * An integer representing the end of the line has Armentian hyphen (U+058A).
     */
    public static final int END_HYPHEN_EDIT_INSERT_ARMENIAN_HYPHEN = 3;

    /**
     * An integer representing the end of the line has normal hyphen character (U+002D).
     */
    public static final int END_HYPHEN_EDIT_INSERT_HYPHEN = 2;

    /**
     * An integer representing the end of the line has maqaf (Hebrew hyphen, U+05BE).
     */
    public static final int END_HYPHEN_EDIT_INSERT_MAQAF = 4;

    /**
     * An integer representing the end of the line has Canadian Syllabics hyphen (U+1400).
     */
    public static final int END_HYPHEN_EDIT_INSERT_UCAS_HYPHEN = 5;

    /**
     * An integer representing the end of the line has Zero-Width-Joiner (U+200D) followed by normal
     * hyphen character (U+002D).
     */
    public static final int END_HYPHEN_EDIT_INSERT_ZWJ_AND_HYPHEN = 6;

    /**
     * An integer representing the end of the line has no modification for hyphenation.
     */
    public static final int END_HYPHEN_EDIT_NO_EDIT = 0;

    /**
     * An integer representing the character at the end of the line is replaced with hyphen
     * character (U+002D).
     */
    public static final int END_HYPHEN_EDIT_REPLACE_WITH_HYPHEN = 1;

    /**
     * Paint flag that applies a synthetic bolding effect to drawn text.
     *
     * <p>Enabling this flag will cause text draw operations to apply a
     * simulated bold effect when drawing a {@link Typeface} that is not
     * already bold.</p>
     *
     * @see #Paint(int)
     * @see #setFlags(int)
     */
    public static final int FAKE_BOLD_TEXT_FLAG = 32;

    /**
     * Paint flag that enables bilinear sampling on scaled bitmaps.
     *
     * <p>If cleared, scaled bitmaps will be drawn with nearest neighbor
     * sampling, likely resulting in artifacts. This should generally be on
     * when drawing bitmaps, unless performance-bound (rendering to software
     * canvas) or preferring pixelation artifacts to blurriness when scaling
     * significantly.</p>
     *
     * <p>If bitmaps are scaled for device density at creation time (as
     * resource bitmaps often are) the filtering will already have been
     * done.</p>
     *
     * <p>On devices running {@link Build.VERSION_CODES#O} and below, hardware
     * accelerated drawing always uses bilinear sampling on scaled bitmaps,
     * regardless of this flag. On devices running {@link Build.VERSION_CODES#Q}
     * and above, this flag defaults to being set on a new {@code Paint}. It can
     * be cleared with {@link #setFlags} or {@link #setFilterBitmap}.</p>
     *
     * @see #Paint()
     * @see #Paint(int)
     * @see #setFlags(int)
     */
    public static final int FILTER_BITMAP_FLAG = 2;

    /**
     * Font hinter option that disables font hinting.
     *
     * @see #setHinting(int)
     */
    public static final int HINTING_OFF = 0;

    /**
     * Font hinter option that enables font hinting.
     *
     * @see #setHinting(int)
     */
    public static final int HINTING_ON = 1;

    /**
     * Paint flag that enables smooth linear scaling of text.
     *
     * <p>Enabling this flag does not actually scale text, but rather adjusts
     * text draw operations to deal gracefully with smooth adjustment of scale.
     * When this flag is enabled, font hinting is disabled to prevent shape
     * deformation between scale factors, and glyph caching is disabled due to
     * the large number of glyph images that will be generated.</p>
     *
     * <p>{@link #SUBPIXEL_TEXT_FLAG} should be used in conjunction with this
     * flag to prevent glyph positions from snapping to whole pixel values as
     * scale factor is adjusted.</p>
     *
     * @see #Paint(int)
     * @see #setFlags(int)
     */
    public static final int LINEAR_TEXT_FLAG = 64;

    /**
     * An integer representing the starting of the line has normal hyphen character (U+002D).
     */
    public static final int START_HYPHEN_EDIT_INSERT_HYPHEN = 1;

    /**
     * An integer representing the starting of the line has Zero-Width-Joiner (U+200D).
     */
    public static final int START_HYPHEN_EDIT_INSERT_ZWJ = 2;

    /**
     * An integer representing the starting of the line has no modification for hyphenation.
     */
    public static final int START_HYPHEN_EDIT_NO_EDIT = 0;

    /**
     * Paint flag that applies a strike-through decoration to drawn text.
     *
     * @see #Paint(int)
     * @see #setFlags(int)
     */
    public static final int STRIKE_THRU_TEXT_FLAG = 16;

    /**
     * Paint flag that enables subpixel positioning of text.
     *
     * <p>Enabling this flag causes glyph advances to be computed with subpixel
     * accuracy.</p>
     *
     * <p>This can be used with {@link #LINEAR_TEXT_FLAG} to prevent text from
     * jittering during smooth scale transitions.</p>
     *
     * @see #Paint(int)
     * @see #setFlags(int)
     */
    public static final int SUBPIXEL_TEXT_FLAG = 128;

    /**
     * A text run flag that indicates the run is located the visually most left segment of the line.
     * <p>
     * This flag is used for telling the underlying text layout engine that the text is located at
     * the most left of the line. This flag is used for controlling the amount letter spacing
     * added. If the text is in the middle of the line, the text layout engine assigns additional
     * letter spacing to the both side of each letter. On the other hand, the letter spacing should
     * not be added to the visually most left and right of the line. By setting this flag, text
     * layout engine calculates the layout as it is located at the most visually left of the line
     * and doesn't add letter spacing to the left of this run.
     * <p>
     * Note that the caller must resolve BiDi runs and reorder them visually and set this flag only
     * if the target run is located visually most left position. This left does not always mean the
     * beginning of the text.
     * <p>
     * If the run covers entire line, caller should set {@link #TEXT_RUN_FLAG_RIGHT_EDGE} as well.
     * <p>
     * Note that this flag is only effective for run based APIs. For example, this flag works for
     * {@link Canvas#drawTextRun(CharSequence, int, int, int, int, float, float, boolean, Paint)}
     * and
     * {@link Paint#getRunCharacterAdvance(char[], int, int, int, int, boolean, int, float[], int)}.
     * However, this doesn't work for
     * {@link Canvas#drawText(CharSequence, int, int, float, float, Paint)} or
     * {@link Paint#measureText(CharSequence, int, int)}. The non-run based APIs works as both
     * {@link #TEXT_RUN_FLAG_LEFT_EDGE} and {@link #TEXT_RUN_FLAG_RIGHT_EDGE} are specified.
     */
    public static final int TEXT_RUN_FLAG_LEFT_EDGE = 8192;

    /**
     * A text run flag that indicates the run is located the visually most right segment of the
     * line.
     * <p>
     * This flag is used for telling the underlying text layout engine that the text is located at
     * the most right of the line. This flag is used for controlling the amount letter spacing
     * added. If the text is in the middle of the line, the text layout engine assigns additional
     * letter spacing to the both side of each letter. On the other hand, the letter spacing should
     * not be added to the visually most left and right of the line. By setting this flag, text
     * layout engine calculates the layout as it is located at the most visually left of the line
     * and doesn't add letter spacing to the left of this run.
     * <p>
     * Note that the caller must resolve BiDi runs and reorder them visually and set this flag only
     * if the target run is located visually most right position. This right does not always mean
     * the end of the text.
     * <p>
     * If the run covers entire line, caller should set {@link #TEXT_RUN_FLAG_LEFT_EDGE} as well.
     * <p>
     * Note that this flag is only effective for run based APIs. For example, this flag works for
     * {@link Canvas#drawTextRun(CharSequence, int, int, int, int, float, float, boolean, Paint)}
     * and
     * {@link Paint#getRunCharacterAdvance(char[], int, int, int, int, boolean, int, float[], int)}.
     * However, this doesn't work for
     * {@link Canvas#drawText(CharSequence, int, int, float, float, Paint)} or
     * {@link Paint#measureText(CharSequence, int, int)}. The non-run based APIs works as both
     * {@link #TEXT_RUN_FLAG_LEFT_EDGE} and {@link #TEXT_RUN_FLAG_RIGHT_EDGE} are specified.
     */
    public static final int TEXT_RUN_FLAG_RIGHT_EDGE = 16384;

    /**
     * Paint flag that applies an underline decoration to drawn text.
     *
     * @see #Paint(int)
     * @see #setFlags(int)
     */
    public static final int UNDERLINE_TEXT_FLAG = 8;

    /**
     * A flat that controls text to be written in vertical orientation
     *
     * <p>
     * This flag is used for telling the underlying text layout engine that the text is for vertical
     * direction. By enabling this flag, text measurement, drawing and shaping APIs works for
     * vertical text layout. For example, {@link Canvas#drawText(String, float, float, Paint)} draws
     * text from top to bottom. {@link Paint#measureText(String)} returns vertical advances instead
     * of horizontal advances. {@link TextRunShaper} shapes text vertically and report glyph IDs for
     * vertical layout.
     *
     * <p>
     * Do not set this flag for making {@link android.text.Layout}. The {@link android.text.Layout}
     * class and its subclasses are designed for horizontal text only and does not work for vertical
     * text.
     */
    public static final int VERTICAL_TEXT_FLAG = 4096;

    private Paint() {
    }
}
