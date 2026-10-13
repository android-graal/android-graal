package android.print;

/**
 * Base class that provides the content of a document to be printed.
 *
 * <h3>Lifecycle</h3>
 * <p>
 * <ul>
 * <li>
 * Initially, you will receive a call to {@link #onStart()}. This callback
 * can be used to allocate resources.
 * </li>
 * <li>
 * Next, you will get one or more calls to {@link #onLayout(PrintAttributes,
 * PrintAttributes, CancellationSignal, LayoutResultCallback, Bundle)} to
 * inform you that the print attributes (page size, density, etc) changed
 * giving you an opportunity to layout the content to match the new constraints.
 * </li>
 * <li>
 * After every call to {@link #onLayout(PrintAttributes, PrintAttributes,
 * CancellationSignal, LayoutResultCallback, Bundle)}, you <strong>may</strong> get
 * a call to {@link #onWrite(PageRange[], ParcelFileDescriptor, CancellationSignal,
 * WriteResultCallback)} asking you to write a PDF file with the content for
 * specific pages.
 * </li>
 * <li>
 * Finally, you will receive a call to {@link #onFinish()}. You can use this
 * callback to release resources allocated in {@link #onStart()}.
 * </li>
 * </ul>
 * <p>
 * The {@link #onStart()} callback is always the first call you will receive and
 * is useful for doing one time setup or resource allocation before printing. You
 * will not receive a subsequent call here.
 * </p>
 * <p>
 * The {@link #onLayout(PrintAttributes, PrintAttributes, CancellationSignal,
 * LayoutResultCallback, Bundle)} callback requires that you layout the content
 * based on the current {@link PrintAttributes}. The execution of this method is
 * not considered completed until you invoke one of the methods on the passed in
 * callback instance. Hence, you will not receive a subsequent call to any other
 * method of this class until the execution of this method is complete by invoking
 * one of the callback methods.
 * </p>
 * <p>
 * The {@link #onWrite(PageRange[], ParcelFileDescriptor, CancellationSignal,
 * WriteResultCallback)} requires that you render and write the content of some
 * pages to the provided destination. The execution of this method is not
 * considered complete until you invoke one of the methods on the passed in
 * callback instance. Hence, you will not receive a subsequent call to any other
 * method of this class until the execution of this method is complete by invoking
 * one of the callback methods. You will never receive a sequence of one or more
 * calls to this method without a previous call to {@link #onLayout(PrintAttributes,
 * PrintAttributes, CancellationSignal, LayoutResultCallback, Bundle)}.
 * </p>
 * <p>
 * The {@link #onFinish()} callback is always the last call you will receive and
 * is useful for doing one time cleanup or resource deallocation after printing.
 * You will not receive a subsequent call here.
 * </p>
 * </p>
 * <h3>Implementation</h3>
 * <p>
 * The APIs defined in this class are designed to enable doing part or all
 * of the work on an arbitrary thread. For example, if the printed content
 * does not depend on the UI state, i.e. on what is shown on the screen, then
 * you can offload the entire work on a dedicated thread, thus making your
 * application interactive while the print work is being performed. Note that
 * while your activity is covered by the system print UI and a user cannot
 * interact with it, doing the printing work on the main application thread
 * may affect the performance of your other application components as they
 * are also executed on that thread.
 * </p>
 * <p>
 * You can also do work on different threads, for example if you print UI
 * content, you can handle {@link #onStart()} and {@link #onLayout(PrintAttributes,
 * PrintAttributes, CancellationSignal, LayoutResultCallback, Bundle)} on
 * the UI thread (assuming onStart initializes resources needed for layout).
 * This will ensure that the UI does not change while you are laying out the
 * printed content. Then you can handle {@link #onWrite(PageRange[], ParcelFileDescriptor,
 * CancellationSignal, WriteResultCallback)} and {@link #onFinish()} on another
 * thread. This will ensure that the main thread is busy for a minimal amount of
 * time. Also this assumes that you will generate the printed content in
 * {@link #onLayout(PrintAttributes, PrintAttributes, CancellationSignal,
 * LayoutResultCallback, Bundle)} which is not mandatory. If you use multiple
 * threads, you are responsible for proper synchronization.
 * </p>
 */
public final class PrintDocumentAdapter {
    /**
     * Extra: mapped to a boolean value that is <code>true</code> if
     * the current layout is for a print preview, <code>false</code> otherwise.
     * This extra is provided in the {@link Bundle} argument of the {@link
     * #onLayout(PrintAttributes, PrintAttributes, CancellationSignal,
     * LayoutResultCallback, Bundle)} callback.
     *
     * @see #onLayout(PrintAttributes, PrintAttributes, CancellationSignal,
     * LayoutResultCallback, Bundle)
     */
    public static final String EXTRA_PRINT_PREVIEW = "EXTRA_PRINT_PREVIEW";

    private PrintDocumentAdapter() {
    }
}
