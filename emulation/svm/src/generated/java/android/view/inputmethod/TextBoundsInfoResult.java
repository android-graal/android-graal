package android.view.inputmethod;

/**
 * The object that holds the result of the
 * {@link InputConnection#requestTextBoundsInfo(RectF, Executor, Consumer)} call.
 *
 * @see InputConnection#requestTextBoundsInfo(RectF, Executor, Consumer)
 */
public final class TextBoundsInfoResult {
    /**
     * Result for {@link InputConnection#requestTextBoundsInfo(RectF, Executor, Consumer)} when the
     * request is cancelled. This happens when the {@link InputConnection} is or becomes
     * invalidated while requesting the
     * {@link TextBoundsInfo}, for example because a new {@code InputConnection} was started, or
     * due to {@link InputMethodManager#invalidateInput}.
     */
    public static final int CODE_CANCELLED = 3;

    /**
     * Result for {@link InputConnection#requestTextBoundsInfo(RectF, Executor, Consumer)} when the
     * request failed. This result code is returned when the editor can't provide a valid
     * {@link TextBoundsInfo}. (e.g. The editor view is not laid out.)
     */
    public static final int CODE_FAILED = 2;

    /**
     * Result for {@link InputConnection#requestTextBoundsInfo(RectF, Executor, Consumer)} when the
     * editor successfully returns a {@link TextBoundsInfo}.
     */
    public static final int CODE_SUCCESS = 1;

    /**
     * Result for {@link InputConnection#requestTextBoundsInfo(RectF, Executor, Consumer)} when the
     * editor doesn't implement the method.
     */
    public static final int CODE_UNSUPPORTED = 0;

    private TextBoundsInfoResult() {
    }
}
