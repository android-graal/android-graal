package android.graphics.pdf;

/**
 * <p>
 * This class enables rendering a PDF document and selecting, searching, fast scrolling,
 * annotations, etc from Android R till Android U. This class is thread safe and can be called by
 * multiple threads however only one thread will be executed at a time as the access is
 * synchronized by internal locking.
 * <p>
 * If you want to render a PDF, you will need to create a new instance of renderer for each
 * document. To render each page, you open the page using the renderer instance created earlier,
 * render it, and close the page. After you are done with rendering, you close the renderer. After
 * the renderer is closed it should not be used anymore. Note that the pages are rendered one by
 * one, i.e. you can have only a single page opened at any given time.
 * <p>
 * A typical use of the APIs to render a PDF looks like this:
 * <pre>
 * // create a new renderer
 * try (PdfRendererPreV renderer = new PdfRendererPreV(getSeekableFileDescriptor(), loadParams)) {
 *      // let us just render all pages
 *      final int pageCount = renderer.getPageCount();
 *      for (int i = 0; i < pageCount; i++) {
 *          Page page = renderer.openPage(i);
 *          RenderParams params = new RenderParams.Builder(Page.RENDER_MODE_FOR_DISPLAY).build();
 *
 *          // say we render for showing on the screen
 *          page.render(mBitmap, params, null, null);
 *
 *          // do stuff with the bitmap
 *
 *          // close the page
 *          page.close();
 *      }
 * }
 * </pre>
 * <h3>Print preview and print output</h3>
 * <p>
 * Please refer to {@link PdfRenderer} for fulfilling this usecase.
 */
public final class PdfRendererPreV {
    /**
     * Represents a linearized PDF document.
     */
    public static final int DOCUMENT_LINEARIZED_TYPE_LINEARIZED = 1;

    /**
     * Represents a non-linearized PDF document.
     */
    public static final int DOCUMENT_LINEARIZED_TYPE_NON_LINEARIZED = 0;

    /**
     * Represents a PDF with form fields specified using the AcroForm spec
     */
    public static final int PDF_FORM_TYPE_ACRO_FORM = 1;

    /**
     * Represents a PDF without form fields
     */
    public static final int PDF_FORM_TYPE_NONE = 0;

    /**
     * Represents a PDF with form fields specified using the XFAF subset of the XFA spec
     */
    public static final int PDF_FORM_TYPE_XFA_FOREGROUND = 3;

    /**
     * Represents a PDF with form fields specified using the entire XFA spec
     */
    public static final int PDF_FORM_TYPE_XFA_FULL = 2;

    private PdfRendererPreV() {
    }
}
