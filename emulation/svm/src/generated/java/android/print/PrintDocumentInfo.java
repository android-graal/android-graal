package android.print;

import android.os.Parcelable;

/**
 * This class encapsulates information about a document for printing
 * purposes. This meta-data is used by the platform and print services,
 * components that interact with printers. For example, this class
 * contains the number of pages contained in the document it describes and
 * this number of pages is shown to the user allowing them to select
 * the range to print. Also a print service may optimize the printing
 * process based on the content type, such as document or photo.
 * <p>
 * Instances of this class are created by the printing application and
 * passed to the {@link PrintDocumentAdapter.LayoutResultCallback#onLayoutFinished(
 * PrintDocumentInfo, boolean) PrintDocumentAdapter.LayoutResultCallback.onLayoutFinished(
 * PrintDocumentInfo, boolean)} callback after successfully laying out the
 * content which is performed in {@link PrintDocumentAdapter#onLayout(PrintAttributes,
 * PrintAttributes, android.os.CancellationSignal, PrintDocumentAdapter.LayoutResultCallback,
 * android.os.Bundle) PrintDocumentAdapter.onLayout(PrintAttributes,
 * PrintAttributes, android.os.CancellationSignal,
 * PrintDocumentAdapter.LayoutResultCallback, android.os.Bundle)}.
 * </p>
 * <p>
 * An example usage looks like this:
 * <pre>
 *
 * . . .
 *
 * public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes,
 *         CancellationSignal cancellationSignal, LayoutResultCallback callback,
 *         Bundle metadata) {
 *
 *        // Assume the app defined a LayoutResult class which contains
 *        // the layout result data and that the content is a document.
 *        LayoutResult result = doSomeLayoutWork();
 *
 *        PrintDocumentInfo info = new PrintDocumentInfo
 *                .Builder("printed_file.pdf")
 *                .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
 *                .setPageCount(result.getPageCount())
 *                .build();
 *
 *       callback.onLayoutFinished(info, result.getContentChanged());
 *   }
 *
 *   . . .
 *
 * </pre>
 * </p>
 */
public final class PrintDocumentInfo implements Parcelable {
    /**
     * Content type: document.
     * <p>
     * A print service may use normal paper to print the content instead
     * of dedicated photo paper. Also it may use a lower quality printing
     * process as the content is not as sensitive to print quality variation
     * as a photo is.
     * </p>
     */
    public static final int CONTENT_TYPE_DOCUMENT = 0;

    /**
     * Content type: photo.
     * <p>
     * A print service may use dedicated photo paper to print the content
     * instead of normal paper. Also it may use a higher quality printing
     * process as the content is more sensitive to print quality variation
     * than a document.
     * </p>
     */
    public static final int CONTENT_TYPE_PHOTO = 1;

    /**
     * Content type: unknown.
     */
    public static final int CONTENT_TYPE_UNKNOWN = -1;

    /**
     * Constant for unknown page count.
     */
    public static final int PAGE_COUNT_UNKNOWN = -1;

    private PrintDocumentInfo() {
    }
}
