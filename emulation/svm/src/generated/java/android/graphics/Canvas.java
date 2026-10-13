package android.graphics;

/**
 * The Canvas class holds the "draw" calls. To draw something, you need
 * 4 basic components: A Bitmap to hold the pixels, a Canvas to host
 * the draw calls (writing into the bitmap), a drawing primitive (e.g. Rect,
 * Path, text, Bitmap), and a paint (to describe the colors and styles for the
 * drawing).
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about how to use Canvas, read the
 * <a href="{@docRoot}guide/topics/graphics/2d-graphics.html">
 * Canvas and Drawables</a> developer guide.</p></div>
 */
public final class Canvas {
    /**
     * Restore everything when restore() is called (standard save flags).
     * <p class="note"><strong>Note:</strong> for performance reasons, it is
     * strongly recommended to pass this - the complete set of flags - to any
     * call to <code>saveLayer()</code> and <code>saveLayerAlpha()</code>
     * variants.
     *
     * <p class="note"><strong>Note:</strong> all methods that accept this flag
     * have flagless versions that are equivalent to passing this flag.
     */
    public static final int ALL_SAVE_FLAG = 31;

    private Canvas() {
    }
}
