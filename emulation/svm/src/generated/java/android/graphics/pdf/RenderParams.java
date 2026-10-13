package android.graphics.pdf;

/**
 * Represents a set of parameters that will be used to render a page of the PDF document.
 */
public final class RenderParams {
    /**
     * Flag to enable rendering of highlight annotation on the page.
     *
     * @see RenderParams#getRenderFlags()
     * @see RenderParams.Builder#setRenderFlags(int)
     */
    public static final int FLAG_RENDER_HIGHLIGHT_ANNOTATIONS = 4;

    /**
     * Flag to enable rendering of text annotation on the page.
     *
     * @see RenderParams#getRenderFlags()
     * @see RenderParams.Builder#setRenderFlags(int)
     */
    public static final int FLAG_RENDER_TEXT_ANNOTATIONS = 2;

    /**
     * Mode to render the content for display on a screen.
     */
    public static final int RENDER_MODE_FOR_DISPLAY = 1;

    /**
     * Mode to render the content for printing.
     */
    public static final int RENDER_MODE_FOR_PRINT = 2;

    private RenderParams() {
    }
}
