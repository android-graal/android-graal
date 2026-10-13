package android.text.style;

/**
 *  A span which styles paragraphs as bullet points (respecting layout direction).
 *  <p>
 *  BulletSpans must be attached from the first character to the last character of a single
 *  paragraph, otherwise the bullet point will not be displayed but the first paragraph encountered
 *  will have a leading margin.
 *  <p>
 *  BulletSpans allow configuring the following elements:
 *  <ul>
 *  <li><b>gap width</b> - the distance, in pixels, between the bullet point and the paragraph.
 *  Default value is 2px.</li>
 *  <li><b>color</b> - the bullet point color. By default, the bullet point color is 0 - no color,
 *  so it uses the TextView's text color.</li>
 *  <li><b>bullet radius</b> - the radius, in pixels, of the bullet point. Default value is
 *  4px.</li>
 *  </ul>
 *  For example, a BulletSpan using the default values can be constructed like this:
 *  <pre>{@code
 *   SpannableString string = new SpannableString("Text with\nBullet point");
 * string.setSpan(new BulletSpan(), 10, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);}</pre>
 *  <img src="{@docRoot}reference/android/images/text/style/defaultbulletspan.png" />
 *  <figcaption>BulletSpan constructed with default values.</figcaption>
 *  <p>
 *  <p>
 *  To construct a BulletSpan with a gap width of 40px, green bullet point and bullet radius of
 *  20px:
 *  <pre>{@code
 *   SpannableString string = new SpannableString("Text with\nBullet point");
 * string.setSpan(new BulletSpan(40, color, 20), 10, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);}</pre>
 *  <img src="{@docRoot}reference/android/images/text/style/custombulletspan.png" />
 *  <figcaption>Customized BulletSpan.</figcaption>
 */
public final class BulletSpan {
    public static final int STANDARD_GAP_WIDTH = 2;

    private BulletSpan() {
    }
}
