package android.graphics.pdf;

/**
 * <p>
 * This class enables rendering a PDF document and selecting, searching, fast scrolling,
 * annotations, etc. from Android V. This class is thread safe and can be called by
 * multiple threads however only one thread will be executed at a time as the access is
 * synchronized by internal locking.
 * <p>
 * If you want to render a PDF, you create a renderer and for every page you want
 * to render, you open the page, render it, and close the page. After you are done
 * with rendering, you close the renderer. After the renderer is closed it should not
 * be used anymore. Note that the pages are rendered one by one, i.e. you can have
 * only a single page opened at any given time.
 * <p>
 * A typical use of the APIs to render a PDF looks like this:
 * <pre>
 * // create a new renderer
 * PdfRenderer renderer = new PdfRenderer(getSeekableFileDescriptor());
 *
 * // let us just render all pages
 * final int pageCount = renderer.getPageCount();
 * for (int i = 0; i < pageCount; i++) {
 *     Page page = renderer.openPage(i);
 *
 *     // say we render for showing on the screen
 *     page.render(mBitmap, null, null, Page.RENDER_MODE_FOR_DISPLAY);
 *
 *     // do stuff with the bitmap
 *
 *     // close the page
 *     page.close();
 * }
 *
 * // close the renderer
 * renderer.close();
 * </pre>
 *
 * <h3>Print preview and print output</h3>
 * <p>
 * If you are using this class to rasterize a PDF for printing or show a print
 * preview, it is recommended that you respect the following contract in order
 * to provide a consistent user experience when seeing a preview and printing,
 * i.e. the user sees a preview that is the same as the printout.
 * <ul>
 * <li>
 * Respect the property whether the document would like to be scaled for printing
 * as per {@link #shouldScaleForPrinting()}.
 * </li>
 * <li>
 * When scaling a document for printing the aspect ratio should be preserved.
 * </li>
 * <li>
 * Do not inset the content with any margins from the {@link android.print.PrintAttributes}
 * as the application is responsible to render it such that the margins are respected.
 * </li>
 * <li>
 * If document page size is greater than the printed media size the content should
 * be anchored to the upper left corner of the page for left-to-right locales and
 * top right corner for right-to-left locales.
 * </li>
 * </ul>
 *
 * @see #close()
 */
public final class PdfRenderer {
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

    private PdfRenderer() {
    }

    /**
     * This class represents a PDF document page for rendering.
     */
    public static final class Page {
        /**
         * Mode to render the content for display on a screen.
         */
        public static final int RENDER_MODE_FOR_DISPLAY = 1;

        /**
         * Mode to render the content for printing.
         */
        public static final int RENDER_MODE_FOR_PRINT = 2;

        private Page() {
        }
    }
}
