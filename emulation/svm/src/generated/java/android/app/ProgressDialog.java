package android.app;

/**
 * A dialog showing a progress indicator and an optional text message or view.
 * Only a text message or a view can be used at the same time.
 *
 * <p>The dialog can be made cancelable on back key press.</p>
 *
 * <p>The progress range is 0 to {@link #getMax() max}.</p>
 *
 * @deprecated <code>ProgressDialog</code> is a modal dialog, which prevents the
 * user from interacting with the app. Instead of using this class, you should
 * use a progress indicator like {@link android.widget.ProgressBar}, which can
 * be embedded in your app's UI. Alternatively, you can use a
 * <a href="/guide/topics/ui/notifiers/notifications.html">notification</a>
 * to inform the user of the task's progress.
 */
@Deprecated
public final class ProgressDialog {
    /**
     * Creates a ProgressDialog with a horizontal progress bar.
     */
    @Deprecated
    public static final int STYLE_HORIZONTAL = 1;

    /**
     * Creates a ProgressDialog with a circular, spinning progress
     * bar. This is the default.
     */
    @Deprecated
    public static final int STYLE_SPINNER = 0;

    private ProgressDialog() {
    }
}
