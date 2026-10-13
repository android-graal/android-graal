package android.view.inputmethod;

/**
 * Base class for stylus handwriting gestures.
 * <p>
 * During a stylus handwriting session, user can perform a stylus gesture operation like
 * {@link SelectGesture}, {@link DeleteGesture}, {@link InsertGesture} on an
 * area of text. IME is responsible for listening to stylus {@link MotionEvent}s using
 * {@link InputMethodService#onStylusHandwritingMotionEvent} and interpret if it can translate to a
 * gesture operation.
 * <p>
 * While creating gesture operations {@link SelectGesture} and {@link DeleteGesture},
 * {@code Granularity} helps pick the correct granular level of text like word level
 * {@link #GRANULARITY_WORD}, or character level {@link #GRANULARITY_CHARACTER}.
 *
 * @see InputConnection#performHandwritingGesture(HandwritingGesture, Executor, IntConsumer)
 * @see InputConnection#previewHandwritingGesture(PreviewableHandwritingGesture, CancellationSignal)
 * @see InputMethodService#onStartStylusHandwriting()
 */
public final class HandwritingGesture {
    /**
     * Operate on text per character basis. i.e. each character is selected based on its
     * intersection with selection rectangle.
     * <p> Strategy of operating at a granular level is maintained in the UI toolkit.
     *     A character/word/line is included if its center is within the gesture rectangle.
     *     e.g. if a selection {@link RectF} with {@link #GRANULARITY_CHARACTER} includes width-wise
     *     center of the character, it should be selected.
     *     Similarly, text in a line should be included in the operation if rectangle includes
     *     line height center.</p>
     */
    public static final int GRANULARITY_CHARACTER = 2;

    /**
     * Operate text per word basis. e.g. if selection includes width-wise center of the word,
     * whole word is selected.
     * <p> Strategy of operating at a granular level is maintained in the UI toolkit.
     *     A character/word/line is included if its center is within the gesture rectangle.
     *     e.g. if a selection {@link RectF} with {@link #GRANULARITY_WORD} includes width-wise
     *     center of the word, it should be selected.
     *     Similarly, text in a line should be included in the operation if rectangle includes
     *     line height center.</p>
     * Refer to https://www.unicode.org/reports/tr29/#Word_Boundaries for more detail on how word
     * breaks are decided.
     */
    public static final int GRANULARITY_WORD = 1;

    private HandwritingGesture() {
    }
}
