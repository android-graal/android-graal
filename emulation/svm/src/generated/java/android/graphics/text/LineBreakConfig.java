package android.graphics.text;

import android.os.Parcelable;

/**
 * Specifies the line-break strategies for text wrapping.
 *
 * <p>See the
 * <a href="https://www.w3.org/TR/css-text-3/#line-break-property" class="external">
 * line-break property</a> for more information.
 */
public final class LineBreakConfig implements Parcelable {
    /**
     * The hyphenation is disabled.
     */
    public static final int HYPHENATION_DISABLED = 0;

    /**
     * The hyphenation is enabled.
     *
     * Note: Even if the hyphenation is enabled with a line break strategy
     * {@link LineBreaker#BREAK_STRATEGY_SIMPLE}, the hyphenation will not be performed unless a
     * single word cannot meet width constraints.
     */
    public static final int HYPHENATION_ENABLED = 1;

    /**
     * No hyphenation preference is specified.
     *
     * <p>
     * This is a special value of hyphenation preference indicating no hyphenation preference is
     * specified. When overriding a {@link LineBreakConfig} with another {@link LineBreakConfig}
     * with {@link Builder#merge(LineBreakConfig)} function, the hyphenation preference of
     * overridden config will be kept if the hyphenation preference of overriding config is
     * {@link #HYPHENATION_UNSPECIFIED}.
     *
     * <p>
     * <pre>
     *     val override = LineBreakConfig.Builder()
     *          .setLineBreakWordStyle(LineBreakConfig.LINE_BREAK_WORD_STYLE_PHRASE)
     *          .build();  // UNSPECIFIED if no setHyphenation is called.
     *     val config = LineBreakConfig.Builder()
     *          .setHyphenation(LineBreakConfig.HYPHENATION_DISABLED)
     *          .merge(override)
     *          .build()
     *     // Here, config has HYPHENATION_DISABLED for line break config and
     *     // LINE_BREAK_WORD_STYLE_PHRASE for line break word style.
     * </pre>
     *
     * <p>
     * This value is resolved to {@link #HYPHENATION_ENABLED} if this value is used for text
     * layout/rendering.
     */
    public static final int HYPHENATION_UNSPECIFIED = -1;

    /**
     * A special value for the line breaking style option.
     *
     * <p>
     * The auto option for the line break style set the line break style based on the locale of the
     * text rendering context. You can specify the context locale by
     * {@link android.widget.TextView#setTextLocales(LocaleList)} or
     * {@link android.graphics.Paint#setTextLocales(LocaleList)}.
     *
     * <p>
     * In the API {@link Build.VERSION_CODES#VANILLA_ICE_CREAM}, auto option does followings:
     * - If at least one locale in the locale list contains Japanese script, this option is
     * equivalent to {@link #LINE_BREAK_STYLE_STRICT}.
     * - Otherwise, this option is equivalent to {@link #LINE_BREAK_STYLE_NONE}.
     *
     * <p>
     * Note: future versions may have special line breaking style rules for other locales.
     */
    public static final int LINE_BREAK_STYLE_AUTO = 5;

    /**
     * The least restrictive line-break rules are used for line breaking. This
     * setting is typically used for short lines.
     */
    public static final int LINE_BREAK_STYLE_LOOSE = 1;

    /**
     * No line-break rules are used for line breaking.
     */
    public static final int LINE_BREAK_STYLE_NONE = 0;

    /**
     * The most common line-break rules are used for line breaking.
     */
    public static final int LINE_BREAK_STYLE_NORMAL = 2;

    /**
     * The line break style that used for preventing automatic line breaking.
     *
     * This is useful when you want to preserve some words in the same line by using
     * {@link android.text.style.LineBreakConfigSpan} or
     * {@link android.text.style.LineBreakConfigSpan#createNoBreakSpan()} as a shorthand.
     * Note that even if this style is specified, the grapheme based line break is still performed
     * for preventing clipping text.
     *
     * @see android.text.style.LineBreakConfigSpan
     * @see android.text.style.LineBreakConfigSpan#createNoBreakSpan()
     */
    public static final int LINE_BREAK_STYLE_NO_BREAK = 4;

    /**
     * The most strict line-break rules are used for line breaking.
     */
    public static final int LINE_BREAK_STYLE_STRICT = 3;

    /**
     * No line break style is specified.
     *
     * <p>
     * This is a special value of line break style indicating no style value is specified.
     * When overriding a {@link LineBreakConfig} with another {@link LineBreakConfig} with
     * {@link Builder#merge(LineBreakConfig)} function, the line break style of overridden config
     * will be kept if the line break style of overriding config is
     * {@link #LINE_BREAK_STYLE_UNSPECIFIED}.
     *
     * <pre>
     *     val override = LineBreakConfig.Builder()
     *          .setLineBreakWordStyle(LineBreakConfig.LINE_BREAK_WORD_STYLE_PHRASE)
     *          .build();  // UNSPECIFIED if no setLineBreakStyle is called.
     *     val config = LineBreakConfig.Builder()
     *          .setLineBreakStyle(LineBreakConfig.LINE_BREAK_STYLE_STRICT)
     *          .merge(override)
     *          .build()
     *     // Here, config has LINE_BREAK_STYLE_STRICT for line break config and
     *     // LINE_BREAK_WORD_STYLE_PHRASE for line break word style.
     * </pre>
     *
     * <p>
     * This value is resolved to {@link #LINE_BREAK_STYLE_NONE} if the target SDK version is API
     * {@link Build.VERSION_CODES#UPSIDE_DOWN_CAKE} or before and this value is used for text
     * layout/rendering. This value is resolved to {@link #LINE_BREAK_STYLE_AUTO} if the target SDK
     * version is API {@link Build.VERSION_CODES#VANILLA_ICE_CREAM} or after and this value is
     * used for text layout/rendering.
     */
    public static final int LINE_BREAK_STYLE_UNSPECIFIED = -1;

    /**
     * A special value for the line breaking word style option.
     *
     * <p>
     * The auto option for the line break word style does some heuristics based on locales and line
     * count.
     *
     * <p>
     * In the API {@link Build.VERSION_CODES#VANILLA_ICE_CREAM}, auto option does followings:
     * - If at least one locale in the locale list contains Korean script, this option is equivalent
     * to {@link #LINE_BREAK_WORD_STYLE_PHRASE}.
     * - If not, then if at least one locale in the locale list contains Japanese script, this
     * option is equivalent to {@link #LINE_BREAK_WORD_STYLE_PHRASE} if the result of its line
     * count is less than 5 lines.
     * - Otherwise, this option is equivalent to {@link #LINE_BREAK_WORD_STYLE_NONE}.
     *
     * <p>
     * Note: future versions may have special line breaking word style rules for other locales.
     */
    public static final int LINE_BREAK_WORD_STYLE_AUTO = 2;

    /**
     * No line-break word style is used for line breaking.
     */
    public static final int LINE_BREAK_WORD_STYLE_NONE = 0;

    /**
     * Line breaking is based on phrases, which results in text wrapping only on
     * meaningful words.
     *
     * <p>Support for this line-break word style depends on locale. If the
     * current locale does not support phrase-based text wrapping, this setting
     * has no effect.
     */
    public static final int LINE_BREAK_WORD_STYLE_PHRASE = 1;

    /**
     * No line break word style is specified.
     *
     * This is a special value of line break word style indicating no style value is specified.
     * When overriding a {@link LineBreakConfig} with another {@link LineBreakConfig} with
     * {@link Builder#merge(LineBreakConfig)} function, the line break word style of overridden
     * config will be kept if the line break word style of overriding config is
     * {@link #LINE_BREAK_WORD_STYLE_UNSPECIFIED}.
     *
     * <pre>
     *     val override = LineBreakConfig.Builder()
     *          .setLineBreakStyle(LineBreakConfig.LINE_BREAK_STYLE_STRICT)
     *          .build();  // UNSPECIFIED if no setLineBreakWordStyle is called.
     *     val config = LineBreakConfig.Builder()
     *          .setLineBreakWordStyle(LineBreakConfig.LINE_BREAK_WORD_STYLE_PHRASE)
     *          .merge(override)
     *          .build()
     *     // Here, config has LINE_BREAK_STYLE_STRICT for line break config and
     *     // LINE_BREAK_WORD_STYLE_PHRASE for line break word style.
     * </pre>
     *
     * This value is resolved to {@link #LINE_BREAK_WORD_STYLE_NONE} if the target SDK version is
     * API {@link Build.VERSION_CODES#UPSIDE_DOWN_CAKE} or before and this value is used for text
     * layout/rendering. This value is resolved to {@link #LINE_BREAK_WORD_STYLE_AUTO} if the target
     * SDK version is API {@link Build.VERSION_CODES#VANILLA_ICE_CREAM} or after and this value is
     * used for text layout/rendering.
     */
    public static final int LINE_BREAK_WORD_STYLE_UNSPECIFIED = -1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private LineBreakConfig() {
    }
}
