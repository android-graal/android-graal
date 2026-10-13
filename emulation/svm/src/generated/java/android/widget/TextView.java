package android.widget;

/**
 * A user interface element that displays text to the user.
 * To provide user-editable text, see {@link EditText}.
 * <p>
 * The following code sample shows a typical use, with an XML layout
 * and code to modify the contents of the text view:
 * </p>
 *
 * <pre>
 * &lt;LinearLayout
 *       xmlns:android="http://schemas.android.com/apk/res/android"
 *       android:layout_width="match_parent"
 *       android:layout_height="match_parent"&gt;
 *    &lt;TextView
 *        android:id="@+id/text_view_id"
 *        android:layout_height="wrap_content"
 *        android:layout_width="wrap_content"
 *        android:text="@string/hello" /&gt;
 * &lt;/LinearLayout&gt;
 * </pre>
 * <p>
 * This code sample demonstrates how to modify the contents of the text view
 * defined in the previous XML layout:
 * </p>
 * <pre>
 * public class MainActivity extends Activity {
 *
 *    protected void onCreate(Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         setContentView(R.layout.activity_main);
 *         final TextView helloTextView = (TextView) findViewById(R.id.text_view_id);
 *         helloTextView.setText(R.string.user_greeting);
 *     }
 * }
 * </pre>
 * <p>
 * To customize the appearance of TextView, see <a href="https://developer.android.com/guide/topics/ui/themes.html">Styles and Themes</a>.
 * </p>
 * <p>
 * <b>XML attributes</b>
 * <p>
 * See {@link android.R.styleable#TextView TextView Attributes},
 * {@link android.R.styleable#View View Attributes}
 *
 * @attr ref android.R.styleable#TextView_text
 * @attr ref android.R.styleable#TextView_bufferType
 * @attr ref android.R.styleable#TextView_hint
 * @attr ref android.R.styleable#TextView_textColor
 * @attr ref android.R.styleable#TextView_textColorHighlight
 * @attr ref android.R.styleable#TextView_textColorHint
 * @attr ref android.R.styleable#TextView_textAppearance
 * @attr ref android.R.styleable#TextView_textColorLink
 * @attr ref android.R.styleable#TextView_textFontWeight
 * @attr ref android.R.styleable#TextView_textSize
 * @attr ref android.R.styleable#TextView_textScaleX
 * @attr ref android.R.styleable#TextView_fontFamily
 * @attr ref android.R.styleable#TextView_typeface
 * @attr ref android.R.styleable#TextView_textStyle
 * @attr ref android.R.styleable#TextView_cursorVisible
 * @attr ref android.R.styleable#TextView_maxLines
 * @attr ref android.R.styleable#TextView_maxHeight
 * @attr ref android.R.styleable#TextView_lines
 * @attr ref android.R.styleable#TextView_height
 * @attr ref android.R.styleable#TextView_minLines
 * @attr ref android.R.styleable#TextView_minHeight
 * @attr ref android.R.styleable#TextView_maxEms
 * @attr ref android.R.styleable#TextView_maxWidth
 * @attr ref android.R.styleable#TextView_ems
 * @attr ref android.R.styleable#TextView_width
 * @attr ref android.R.styleable#TextView_minEms
 * @attr ref android.R.styleable#TextView_minWidth
 * @attr ref android.R.styleable#TextView_gravity
 * @attr ref android.R.styleable#TextView_scrollHorizontally
 * @attr ref android.R.styleable#TextView_password
 * @attr ref android.R.styleable#TextView_singleLine
 * @attr ref android.R.styleable#TextView_selectAllOnFocus
 * @attr ref android.R.styleable#TextView_includeFontPadding
 * @attr ref android.R.styleable#TextView_maxLength
 * @attr ref android.R.styleable#TextView_shadowColor
 * @attr ref android.R.styleable#TextView_shadowDx
 * @attr ref android.R.styleable#TextView_shadowDy
 * @attr ref android.R.styleable#TextView_shadowRadius
 * @attr ref android.R.styleable#TextView_autoLink
 * @attr ref android.R.styleable#TextView_linksClickable
 * @attr ref android.R.styleable#TextView_numeric
 * @attr ref android.R.styleable#TextView_digits
 * @attr ref android.R.styleable#TextView_phoneNumber
 * @attr ref android.R.styleable#TextView_inputMethod
 * @attr ref android.R.styleable#TextView_capitalize
 * @attr ref android.R.styleable#TextView_autoText
 * @attr ref android.R.styleable#TextView_editable
 * @attr ref android.R.styleable#TextView_freezesText
 * @attr ref android.R.styleable#TextView_ellipsize
 * @attr ref android.R.styleable#TextView_drawableTop
 * @attr ref android.R.styleable#TextView_drawableBottom
 * @attr ref android.R.styleable#TextView_drawableRight
 * @attr ref android.R.styleable#TextView_drawableLeft
 * @attr ref android.R.styleable#TextView_drawableStart
 * @attr ref android.R.styleable#TextView_drawableEnd
 * @attr ref android.R.styleable#TextView_drawablePadding
 * @attr ref android.R.styleable#TextView_drawableTint
 * @attr ref android.R.styleable#TextView_drawableTintMode
 * @attr ref android.R.styleable#TextView_lineSpacingExtra
 * @attr ref android.R.styleable#TextView_lineSpacingMultiplier
 * @attr ref android.R.styleable#TextView_justificationMode
 * @attr ref android.R.styleable#TextView_marqueeRepeatLimit
 * @attr ref android.R.styleable#TextView_inputType
 * @attr ref android.R.styleable#TextView_imeOptions
 * @attr ref android.R.styleable#TextView_privateImeOptions
 * @attr ref android.R.styleable#TextView_imeActionLabel
 * @attr ref android.R.styleable#TextView_imeActionId
 * @attr ref android.R.styleable#TextView_editorExtras
 * @attr ref android.R.styleable#TextView_elegantTextHeight
 * @attr ref android.R.styleable#TextView_fallbackLineSpacing
 * @attr ref android.R.styleable#TextView_letterSpacing
 * @attr ref android.R.styleable#TextView_fontFeatureSettings
 * @attr ref android.R.styleable#TextView_fontVariationSettings
 * @attr ref android.R.styleable#TextView_breakStrategy
 * @attr ref android.R.styleable#TextView_hyphenationFrequency
 * @attr ref android.R.styleable#TextView_lineBreakStyle
 * @attr ref android.R.styleable#TextView_lineBreakWordStyle
 * @attr ref android.R.styleable#TextView_autoSizeTextType
 * @attr ref android.R.styleable#TextView_autoSizeMinTextSize
 * @attr ref android.R.styleable#TextView_autoSizeMaxTextSize
 * @attr ref android.R.styleable#TextView_autoSizeStepGranularity
 * @attr ref android.R.styleable#TextView_autoSizePresetSizes
 * @attr ref android.R.styleable#TextView_textCursorDrawable
 * @attr ref android.R.styleable#TextView_textSelectHandle
 * @attr ref android.R.styleable#TextView_textSelectHandleLeft
 * @attr ref android.R.styleable#TextView_textSelectHandleRight
 * @attr ref android.R.styleable#TextView_allowUndo
 * @attr ref android.R.styleable#TextView_enabled
 */
public final class TextView {
    /**
     * The TextView does not auto-size text (default).
     */
    public static final int AUTO_SIZE_TEXT_TYPE_NONE = 0;

    /**
     * The TextView scales text size both horizontally and vertically to fit within the
     * container.
     */
    public static final int AUTO_SIZE_TEXT_TYPE_UNIFORM = 1;

    /**
     * A special index used for {@link #setFocusedSearchResultIndex(int)} and
     * {@link #getFocusedSearchResultIndex()} inidicating there is no focused search result.
     */
    public static final int FOCUSED_SEARCH_RESULT_INDEX_NONE = -1;

    private TextView() {
    }
}
