package android.text.style;

/**
 *  A span which styles paragraphs by adding a vertical stripe at the beginning of the text
 *  (respecting layout direction).
 *  <p>
 *  A <code>QuoteSpan</code> must be attached from the first character to the last character of a
 *  single paragraph, otherwise the span will not be displayed.
 *  <p>
 *  <code>QuoteSpans</code> allow configuring the following elements:
 *  <ul>
 *  <li><b>color</b> - the vertical stripe color. By default, the stripe color is 0xff0000ff</li>
 *  <li><b>gap width</b> - the distance, in pixels, between the stripe and the paragraph.
 *  Default value is 2px.</li>
 *  <li><b>stripe width</b> - the width, in pixels, of the stripe. Default value is
 *  2px.</li>
 *  </ul>
 *  For example, a <code>QuoteSpan</code> using the default values can be constructed like this:
 *  <pre>{@code SpannableString string = new SpannableString("Text with quote span on a long line");
 * string.setSpan(new QuoteSpan(), 0, string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);}</pre>
 *  <img src="{@docRoot}reference/android/images/text/style/defaultquotespan.png" />
 *  <figcaption><code>QuoteSpan</code> constructed with default values.</figcaption>
 *  <p>
 *  <p>
 *  To construct a <code>QuoteSpan</code> with a green stripe, of 20px in width and a gap width of
 *  40px:
 *  <pre>{@code SpannableString string = new SpannableString("Text with quote span on a long line");
 * string.setSpan(new QuoteSpan(Color.GREEN, 20, 40), 0, string.length(),
 * Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);}</pre>
 *  <img src="{@docRoot}reference/android/images/text/style/customquotespan.png" />
 *  <figcaption>Customized <code>QuoteSpan</code>.</figcaption>
 */
public final class QuoteSpan {
    /**
     * Default color for the quote stripe.
     */
    public static final int STANDARD_COLOR = -16776961;

    /**
     * Default gap width in pixels.
     */
    public static final int STANDARD_GAP_WIDTH_PX = 2;

    /**
     * Default stripe width in pixels.
     */
    public static final int STANDARD_STRIPE_WIDTH_PX = 2;

    private QuoteSpan() {
    }
}
