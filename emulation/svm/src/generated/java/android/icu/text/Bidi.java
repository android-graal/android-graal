package android.icu.text;

/**
 *
 * <h2>Bidi algorithm for ICU</h2>
 *
 * This is an implementation of the Unicode Bidirectional Algorithm. The
 * algorithm is defined in the <a
 * href="https://www.unicode.org/reports/tr9/">Unicode Standard Annex #9</a>.
 * <p>
 *
 * Note: Libraries that perform a bidirectional algorithm and reorder strings
 * accordingly are sometimes called "Storage Layout Engines". ICU's Bidi and
 * shaping (ArabicShaping) classes can be used at the core of such "Storage
 * Layout Engines".
 *
 * <h3>General remarks about the API:</h3>
 *
 * The &quot;limit&quot; of a sequence of characters is the position just after
 * their last character, i.e., one more than that position.
 * <p>
 *
 * Some of the API methods provide access to &quot;runs&quot;. Such a
 * &quot;run&quot; is defined as a sequence of characters that are at the same
 * embedding level after performing the Bidi algorithm.
 *
 * <h3>Basic concept: paragraph</h3>
 * A piece of text can be divided into several paragraphs by characters
 * with the Bidi class <code>Block Separator</code>. For handling of
 * paragraphs, see:
 * <ul>
 * <li>{@link #countParagraphs}
 * <li>{@link #getParaLevel}
 * <li>{@link #getParagraph}
 * <li>{@link #getParagraphByIndex}
 * </ul>
 *
 * <h3>Basic concept: text direction</h3>
 * The direction of a piece of text may be:
 * <ul>
 * <li>{@link #LTR}
 * <li>{@link #RTL}
 * <li>{@link #MIXED}
 * <li>{@link #NEUTRAL}
 * </ul>
 *
 * <h3>Basic concept: levels</h3>
 *
 * Levels in this API represent embedding levels according to the Unicode
 * Bidirectional Algorithm.
 * Their low-order bit (even/odd value) indicates the visual direction.<p>
 *
 * Levels can be abstract values when used for the
 * <code>paraLevel</code> and <code>embeddingLevels</code>
 * arguments of <code>setPara()</code>; there:
 * <ul>
 * <li>the high-order bit of an <code>embeddingLevels[]</code>
 * value indicates whether the using application is
 * specifying the level of a character to <i>override</i> whatever the
 * Bidi implementation would resolve it to.</li>
 * <li><code>paraLevel</code> can be set to the
 * pseudo-level values <code>LEVEL_DEFAULT_LTR</code>
 * and <code>LEVEL_DEFAULT_RTL</code>.</li>
 * </ul>
 *
 * <p>The related constants are not real, valid level values.
 * <code>DEFAULT_XXX</code> can be used to specify
 * a default for the paragraph level for
 * when the <code>setPara()</code> method
 * shall determine it but there is no
 * strongly typed character in the input.<p>
 *
 * Note that the value for <code>LEVEL_DEFAULT_LTR</code> is even
 * and the one for <code>LEVEL_DEFAULT_RTL</code> is odd,
 * just like with normal LTR and RTL level values -
 * these special values are designed that way. Also, the implementation
 * assumes that MAX_EXPLICIT_LEVEL is odd.
 *
 * <p>Note: The numeric values of the related constants will not change:
 * They are tied to the use of 7-bit byte values (plus the override bit)
 * and of the byte data type in this API.
 *
 * <p><b>See Also:</b>
 * <ul>
 * <li>{@link #LEVEL_DEFAULT_LTR}
 * <li>{@link #LEVEL_DEFAULT_RTL}
 * <li>{@link #LEVEL_OVERRIDE}
 * <li>{@link #MAX_EXPLICIT_LEVEL}
 * <li>{@link #setPara}
 * </ul>
 *
 * <h3>Basic concept: Reordering Mode</h3>
 * Reordering mode values indicate which variant of the Bidi algorithm to
 * use.
 *
 * <b>See Also:</b>
 * <ul>
 * <li>{@link #setReorderingMode}
 * <li>{@link #REORDER_DEFAULT}
 * <li>{@link #REORDER_NUMBERS_SPECIAL}
 * <li>{@link #REORDER_GROUP_NUMBERS_WITH_R}
 * <li>{@link #REORDER_RUNS_ONLY}
 * <li>{@link #REORDER_INVERSE_NUMBERS_AS_L}
 * <li>{@link #REORDER_INVERSE_LIKE_DIRECT}
 * <li>{@link #REORDER_INVERSE_FOR_NUMBERS_SPECIAL}
 * </ul>
 *
 * <h3>Basic concept: Reordering Options</h3>
 * Reordering options can be applied during Bidi text transformations.
 *
 * <b>See Also:</b>
 * <ul>
 * <li>{@link #setReorderingOptions}
 * <li>{@link #OPTION_DEFAULT}
 * <li>{@link #OPTION_INSERT_MARKS}
 * <li>{@link #OPTION_REMOVE_CONTROLS}
 * <li>{@link #OPTION_STREAMING}
 * </ul>
 *
 * <h4> Sample code for the ICU Bidi API </h4>
 *
 * <h5>Rendering a paragraph with the ICU Bidi API</h5>
 *
 * This is (hypothetical) sample code that illustrates how the ICU Bidi API
 * could be used to render a paragraph of text. Rendering code depends highly on
 * the graphics system, therefore this sample code must make a lot of
 * assumptions, which may or may not match any existing graphics system's
 * properties.
 *
 * <p>
 * The basic assumptions are:
 *
 * <ul>
 * <li>Rendering is done from left to right on a horizontal line.</li>
 * <li>A run of single-style, unidirectional text can be rendered at once.
 * </li>
 * <li>Such a run of text is passed to the graphics system with characters
 * (code units) in logical order.</li>
 * <li>The line-breaking algorithm is very complicated and Locale-dependent -
 * and therefore its implementation omitted from this sample code.</li>
 * </ul>
 *
 * <pre>
 *
 *  package android.icu.dev.test.bidi;
 *
 *  import android.icu.text.Bidi;
 *  import android.icu.text.BidiRun;
 *
 *  public class Sample {
 *
 *      static final int styleNormal = 0;
 *      static final int styleSelected = 1;
 *      static final int styleBold = 2;
 *      static final int styleItalics = 4;
 *      static final int styleSuper=8;
 *      static final int styleSub = 16;
 *
 *      static class StyleRun {
 *          int limit;
 *          int style;
 *
 *          public StyleRun(int limit, int style) {
 *              this.limit = limit;
 *              this.style = style;
 *          }
 *      }
 *
 *      static class Bounds {
 *          int start;
 *          int limit;
 *
 *          public Bounds(int start, int limit) {
 *              this.start = start;
 *              this.limit = limit;
 *          }
 *      }
 *
 *      static int getTextWidth(String text, int start, int limit,
 *                              StyleRun[] styleRuns, int styleRunCount) {
 *          // simplistic way to compute the width
 *          return limit - start;
 *      }
 *
 *      // set limit and StyleRun limit for a line
 *      // from text[start] and from styleRuns[styleRunStart]
 *      // using Bidi.getLogicalRun(...)
 *      // returns line width
 *      static int getLineBreak(String text, Bounds line, Bidi para,
 *                              StyleRun styleRuns[], Bounds styleRun) {
 *          // dummy return
 *          return 0;
 *      }
 *
 *      // render runs on a line sequentially, always from left to right
 *
 *      // prepare rendering a new line
 *      static void startLine(byte textDirection, int lineWidth) {
 *          System.out.println();
 *      }
 *
 *      // render a run of text and advance to the right by the run width
 *      // the text[start..limit-1] is always in logical order
 *      static void renderRun(String text, int start, int limit,
 *                            byte textDirection, int style) {
 *      }
 *
 *      // We could compute a cross-product
 *      // from the style runs with the directional runs
 *      // and then reorder it.
 *      // Instead, here we iterate over each run type
 *      // and render the intersections -
 *      // with shortcuts in simple (and common) cases.
 *      // renderParagraph() is the main function.
 *
 *      // render a directional run with
 *      // (possibly) multiple style runs intersecting with it
 *      static void renderDirectionalRun(String text, int start, int limit,
 *                                       byte direction, StyleRun styleRuns[],
 *                                       int styleRunCount) {
 *          int i;
 *
 *          // iterate over style runs
 *          if (direction == Bidi.LTR) {
 *              int styleLimit;
 *              for (i = 0; i &lt; styleRunCount; ++i) {
 *                  styleLimit = styleRuns[i].limit;
 *                  if (start &lt; styleLimit) {
 *                      if (styleLimit &gt; limit) {
 *                          styleLimit = limit;
 *                      }
 *                      renderRun(text, start, styleLimit,
 *                                direction, styleRuns[i].style);
 *                      if (styleLimit == limit) {
 *                          break;
 *                      }
 *                      start = styleLimit;
 *                  }
 *              }
 *          } else {
 *              int styleStart;
 *
 *              for (i = styleRunCount-1; i &gt;= 0; --i) {
 *                  if (i &gt; 0) {
 *                      styleStart = styleRuns[i-1].limit;
 *                  } else {
 *                      styleStart = 0;
 *                  }
 *                  if (limit &gt;= styleStart) {
 *                      if (styleStart &lt; start) {
 *                          styleStart = start;
 *                      }
 *                      renderRun(text, styleStart, limit, direction,
 *                                styleRuns[i].style);
 *                      if (styleStart == start) {
 *                          break;
 *                      }
 *                      limit = styleStart;
 *                  }
 *              }
 *          }
 *      }
 *
 *      // the line object represents text[start..limit-1]
 *      static void renderLine(Bidi line, String text, int start, int limit,
 *                             StyleRun styleRuns[], int styleRunCount) {
 *          byte direction = line.getDirection();
 *          if (direction != Bidi.MIXED) {
 *              // unidirectional
 *              if (styleRunCount &lt;= 1) {
 *                  renderRun(text, start, limit, direction, styleRuns[0].style);
 *              } else {
 *                  renderDirectionalRun(text, start, limit, direction,
 *                                       styleRuns, styleRunCount);
 *              }
 *          } else {
 *              // mixed-directional
 *              int count, i;
 *              BidiRun run;
 *
 *              try {
 *                  count = line.countRuns();
 *              } catch (IllegalStateException e) {
 *                  e.printStackTrace();
 *                  return;
 *              }
 *              if (styleRunCount &lt;= 1) {
 *                  int style = styleRuns[0].style;
 *
 *                  // iterate over directional runs
 *                  for (i = 0; i &lt; count; ++i) {
 *                      run = line.getVisualRun(i);
 *                      renderRun(text, run.getStart(), run.getLimit(),
 *                                run.getDirection(), style);
 *                  }
 *              } else {
 *                  // iterate over both directional and style runs
 *                  for (i = 0; i &lt; count; ++i) {
 *                      run = line.getVisualRun(i);
 *                      renderDirectionalRun(text, run.getStart(),
 *                                           run.getLimit(), run.getDirection(),
 *                                           styleRuns, styleRunCount);
 *                  }
 *              }
 *          }
 *      }
 *
 *      static void renderParagraph(String text, byte textDirection,
 *                                  StyleRun styleRuns[], int styleRunCount,
 *                                  int lineWidth) {
 *          int length = text.length();
 *          Bidi para = new Bidi();
 *          try {
 *              para.setPara(text,
 *                           textDirection != 0 ? Bidi.LEVEL_DEFAULT_RTL
 *                                              : Bidi.LEVEL_DEFAULT_LTR,
 *                           null);
 *          } catch (Exception e) {
 *              e.printStackTrace();
 *              return;
 *          }
 *          byte paraLevel = (byte)(1 &amp; para.getParaLevel());
 *          StyleRun styleRun = new StyleRun(length, styleNormal);
 *
 *          if (styleRuns == null || styleRunCount &lt;= 0) {
 *              styleRuns = new StyleRun[1];
 *              styleRunCount = 1;
 *              styleRuns[0] = styleRun;
 *          }
 *          // assume styleRuns[styleRunCount-1].limit&gt;=length
 *
 *          int width = getTextWidth(text, 0, length, styleRuns, styleRunCount);
 *          if (width &lt;= lineWidth) {
 *              // everything fits onto one line
 *
 *              // prepare rendering a new line from either left or right
 *              startLine(paraLevel, width);
 *
 *              renderLine(para, text, 0, length, styleRuns, styleRunCount);
 *          } else {
 *              // we need to render several lines
 *              Bidi line = new Bidi(length, 0);
 *              int start = 0, limit;
 *              int styleRunStart = 0, styleRunLimit;
 *
 *              for (;;) {
 *                  limit = length;
 *                  styleRunLimit = styleRunCount;
 *                  width = getLineBreak(text, new Bounds(start, limit),
 *                                       para, styleRuns,
 *                                       new Bounds(styleRunStart, styleRunLimit));
 *                  try {
 *                      line = para.setLine(start, limit);
 *                  } catch (Exception e) {
 *                      e.printStackTrace();
 *                      return;
 *                  }
 *                  // prepare rendering a new line
 *                  // from either left or right
 *                  startLine(paraLevel, width);
 *
 *                  if (styleRunStart &gt; 0) {
 *                      int newRunCount = styleRuns.length - styleRunStart;
 *                      StyleRun[] newRuns = new StyleRun[newRunCount];
 *                      System.arraycopy(styleRuns, styleRunStart, newRuns, 0,
 *                                       newRunCount);
 *                      renderLine(line, text, start, limit, newRuns,
 *                                 styleRunLimit - styleRunStart);
 *                  } else {
 *                      renderLine(line, text, start, limit, styleRuns,
 *                                 styleRunLimit - styleRunStart);
 *                  }
 *                  if (limit == length) {
 *                      break;
 *                  }
 *                  start = limit;
 *                  styleRunStart = styleRunLimit - 1;
 *                  if (start &gt;= styleRuns[styleRunStart].limit) {
 *                      ++styleRunStart;
 *                  }
 *              }
 *          }
 *      }
 *
 *      public static void main(String[] args)
 *      {
 *          renderParagraph("Some Latin text...", Bidi.LTR, null, 0, 80);
 *          renderParagraph("Some Hebrew text...", Bidi.RTL, null, 0, 60);
 *      }
 *  }
 *
 * </pre>
 *
 * @author Simon Montagu, Matitiahu Allouche (ported from C code written by Markus W. Scherer)
 */
public final class Bidi {
    /**
     * Constant indicating that the base direction depends on the first strong
     * directional character in the text according to the Unicode Bidirectional
     * Algorithm. If no strong directional character is present, the base
     * direction is left-to-right.
     */
    public static final int DIRECTION_DEFAULT_LEFT_TO_RIGHT = 126;

    /**
     * Constant indicating that the base direction depends on the first strong
     * directional character in the text according to the Unicode Bidirectional
     * Algorithm. If no strong directional character is present, the base
     * direction is right-to-left.
     */
    public static final int DIRECTION_DEFAULT_RIGHT_TO_LEFT = 127;

    /**
     * Constant indicating base direction is left-to-right.
     */
    public static final int DIRECTION_LEFT_TO_RIGHT = 0;

    /**
     * Constant indicating base direction is right-to-left.
     */
    public static final int DIRECTION_RIGHT_TO_LEFT = 1;

    /**
     * option bit for writeReordered():
     * replace characters with the "mirrored" property in RTL runs
     * by their mirror-image mappings
     *
     * @see #writeReordered
     */
    public static final short DO_MIRRORING = 2;

    /**
     * option bit for writeReordered():
     * surround the run with LRMs if necessary;
     * this is part of the approximate "inverse Bidi" algorithm
     *
     * <p>This option does not imply corresponding adjustment of the index
     * mappings.
     *
     * @see #setInverse
     * @see #writeReordered
     */
    public static final short INSERT_LRM_FOR_NUMERIC = 4;

    /**
     * option bit for writeReordered():
     * keep combining characters after their base characters in RTL runs
     *
     * @see #writeReordered
     */
    public static final short KEEP_BASE_COMBINING = 1;

    /**
     * Paragraph level setting<p>
     *
     *  Constant indicating that the base direction depends on the first strong
     *  directional character in the text according to the Unicode Bidirectional
     *  Algorithm. If no strong directional character is present,
     *  then set the paragraph level to 0 (left-to-right).<p>
     *
     *  If this value is used in conjunction with reordering modes
     *  <code>REORDER_INVERSE_LIKE_DIRECT</code> or
     *  <code>REORDER_INVERSE_FOR_NUMBERS_SPECIAL</code>, the text to reorder
     *  is assumed to be visual LTR, and the text after reordering is required
     *  to be the corresponding logical string with appropriate contextual
     *  direction. The direction of the result string will be RTL if either
     *  the rightmost or leftmost strong character of the source text is RTL
     *  or Arabic Letter, the direction will be LTR otherwise.<p>
     *
     *  If reordering option <code>OPTION_INSERT_MARKS</code> is set, an RLM may
     *  be added at the beginning of the result string to ensure round trip
     *  (that the result string, when reordered back to visual, will produce
     *  the original source text).
     *  @see #REORDER_INVERSE_LIKE_DIRECT
     *  @see #REORDER_INVERSE_FOR_NUMBERS_SPECIAL
     */
    public static final byte LEVEL_DEFAULT_LTR = 126;

    /**
     * Paragraph level setting<p>
     *
     *  Constant indicating that the base direction depends on the first strong
     *  directional character in the text according to the Unicode Bidirectional
     *  Algorithm. If no strong directional character is present,
     *  then set the paragraph level to 1 (right-to-left).<p>
     *
     *  If this value is used in conjunction with reordering modes
     *  <code>REORDER_INVERSE_LIKE_DIRECT</code> or
     *  <code>REORDER_INVERSE_FOR_NUMBERS_SPECIAL</code>, the text to reorder
     *  is assumed to be visual LTR, and the text after reordering is required
     *  to be the corresponding logical string with appropriate contextual
     *  direction. The direction of the result string will be RTL if either
     *  the rightmost or leftmost strong character of the source text is RTL
     *  or Arabic Letter, or if the text contains no strong character;
     *  the direction will be LTR otherwise.<p>
     *
     *  If reordering option <code>OPTION_INSERT_MARKS</code> is set, an RLM may
     *  be added at the beginning of the result string to ensure round trip
     *  (that the result string, when reordered back to visual, will produce
     *  the original source text).
     *  @see #REORDER_INVERSE_LIKE_DIRECT
     *  @see #REORDER_INVERSE_FOR_NUMBERS_SPECIAL
     */
    public static final byte LEVEL_DEFAULT_RTL = 127;

    /**
     * Bit flag for level input.
     * Overrides directional properties.
     */
    public static final byte LEVEL_OVERRIDE = -128;

    /**
     * Left-to-right text.
     * <ul>
     * <li>As return value for <code>getDirection()</code>, it means
     *     that the source string contains no right-to-left characters, or
     *     that the source string is empty and the paragraph level is even.
     * <li>As return value for <code>getBaseDirection()</code>, it
     *     means that the first strong character of the source string has
     *     a left-to-right direction.
     * </ul>
     */
    public static final byte LTR = 0;

    /**
     * Special value which can be returned by the mapping methods when a
     * logical index has no corresponding visual index or vice-versa. This may
     * happen for the logical-to-visual mapping of a Bidi control when option
     * <code>OPTION_REMOVE_CONTROLS</code> is
     * specified. This can also happen for the visual-to-logical mapping of a
     * Bidi mark (LRM or RLM) inserted by option
     * <code>OPTION_INSERT_MARKS</code>.
     * @see #getVisualIndex
     * @see #getVisualMap
     * @see #getLogicalIndex
     * @see #getLogicalMap
     * @see #OPTION_INSERT_MARKS
     * @see #OPTION_REMOVE_CONTROLS
     */
    public static final int MAP_NOWHERE = -1;

    /**
     * Maximum explicit embedding level.
     * Same as the max_depth value in the
     * <a href="http://www.unicode.org/reports/tr9/#BD2">Unicode Bidirectional Algorithm</a>.
     * (The maximum resolved level can be up to <code>MAX_EXPLICIT_LEVEL+1</code>).
     */
    public static final byte MAX_EXPLICIT_LEVEL = 125;

    /**
     * Mixed-directional text.
     * <p>As return value for <code>getDirection()</code>, it means
     *    that the source string contains both left-to-right and
     *    right-to-left characters.
     */
    public static final byte MIXED = 2;

    /**
     * No strongly directional text.
     * <p>As return value for <code>getBaseDirection()</code>, it means
     *    that the source string is missing or empty, or contains neither
     *    left-to-right nor right-to-left characters.
     */
    public static final byte NEUTRAL = 3;

    /**
     * Option value for <code>setReorderingOptions</code>:
     * disable all the options which can be set with this method
     * @see #setReorderingOptions
     */
    public static final int OPTION_DEFAULT = 0;

    /**
     * Option bit for <code>setReorderingOptions</code>:
     * insert Bidi marks (LRM or RLM) when needed to ensure correct result of
     * a reordering to a Logical order
     *
     * <p>This option must be set or reset before calling
     * <code>setPara</code>.
     *
     * <p>This option is significant only with reordering modes which generate
     * a result with Logical order, specifically.
     * <ul>
     *   <li><code>REORDER_RUNS_ONLY</code></li>
     *   <li><code>REORDER_INVERSE_NUMBERS_AS_L</code></li>
     *   <li><code>REORDER_INVERSE_LIKE_DIRECT</code></li>
     *   <li><code>REORDER_INVERSE_FOR_NUMBERS_SPECIAL</code></li>
     * </ul>
     *
     * <p>If this option is set in conjunction with reordering mode
     * <code>REORDER_INVERSE_NUMBERS_AS_L</code> or with calling
     * <code>setInverse(true)</code>, it implies option
     * <code>INSERT_LRM_FOR_NUMERIC</code> in calls to method
     * <code>writeReordered()</code>.
     *
     * <p>For other reordering modes, a minimum number of LRM or RLM characters
     * will be added to the source text after reordering it so as to ensure
     * round trip, i.e. when applying the inverse reordering mode on the
     * resulting logical text with removal of Bidi marks
     * (option <code>OPTION_REMOVE_CONTROLS</code> set before calling
     * <code>setPara()</code> or option
     * <code>REMOVE_BIDI_CONTROLS</code> in
     * <code>writeReordered</code>), the result will be identical to the
     * source text in the first transformation.
     *
     * <p>This option will be ignored if specified together with option
     * <code>OPTION_REMOVE_CONTROLS</code>. It inhibits option
     * <code>REMOVE_BIDI_CONTROLS</code> in calls to method
     * <code>writeReordered()</code> and it implies option
     * <code>INSERT_LRM_FOR_NUMERIC</code> in calls to method
     * <code>writeReordered()</code> if the reordering mode is
     * <code>REORDER_INVERSE_NUMBERS_AS_L</code>.
     *
     * @see #setReorderingMode
     * @see #setReorderingOptions
     * @see #INSERT_LRM_FOR_NUMERIC
     * @see #REMOVE_BIDI_CONTROLS
     * @see #OPTION_REMOVE_CONTROLS
     * @see #REORDER_RUNS_ONLY
     * @see #REORDER_INVERSE_NUMBERS_AS_L
     * @see #REORDER_INVERSE_LIKE_DIRECT
     * @see #REORDER_INVERSE_FOR_NUMBERS_SPECIAL
     */
    public static final int OPTION_INSERT_MARKS = 1;

    /**
     * Option bit for <code>setReorderingOptions</code>:
     * remove Bidi control characters
     *
     * <p>This option must be set or reset before calling
     * <code>setPara</code>.
     *
     * <p>This option nullifies option
     * <code>OPTION_INSERT_MARKS</code>. It inhibits option
     * <code>INSERT_LRM_FOR_NUMERIC</code> in calls to method
     * <code>writeReordered()</code> and it implies option
     * <code>REMOVE_BIDI_CONTROLS</code> in calls to that method.
     *
     * @see #setReorderingMode
     * @see #setReorderingOptions
     * @see #OPTION_INSERT_MARKS
     * @see #INSERT_LRM_FOR_NUMERIC
     * @see #REMOVE_BIDI_CONTROLS
     */
    public static final int OPTION_REMOVE_CONTROLS = 2;

    /**
     * Option bit for <code>setReorderingOptions</code>:
     * process the output as part of a stream to be continued
     *
     * <p>This option must be set or reset before calling
     * <code>setPara</code>.
     *
     * <p>This option specifies that the caller is interested in processing
     * large text object in parts. The results of the successive calls are
     * expected to be concatenated by the caller. Only the call for the last
     * part will have this option bit off.
     *
     * <p>When this option bit is on, <code>setPara()</code> may process
     * less than the full source text in order to truncate the text at a
     * meaningful boundary. The caller should call
     * <code>getProcessedLength()</code> immediately after calling
     * <code>setPara()</code> in order to determine how much of the source
     * text has been processed. Source text beyond that length should be
     * resubmitted in following calls to <code>setPara</code>. The
     * processed length may be less than the length of the source text if a
     * character preceding the last character of the source text constitutes a
     * reasonable boundary (like a block separator) for text to be continued.<br>
     * If the last character of the source text constitutes a reasonable
     * boundary, the whole text will be processed at once.<br>
     * If nowhere in the source text there exists
     * such a reasonable boundary, the processed length will be zero.<br>
     * The caller should check for such an occurrence and do one of the following:
     * <ul><li>submit a larger amount of text with a better chance to include
     *         a reasonable boundary.</li>
     *     <li>resubmit the same text after turning off option
     *         <code>OPTION_STREAMING</code>.</li></ul>
     * In all cases, this option should be turned off before processing the last
     * part of the text.
     *
     * <p>When the <code>OPTION_STREAMING</code> option is used, it is
     * recommended to call <code>orderParagraphsLTR(true)</code> before calling
     * <code>setPara()</code> so that later paragraphs may be concatenated to
     * previous paragraphs on the right.
     *
     * @see #setReorderingMode
     * @see #setReorderingOptions
     * @see #getProcessedLength
     */
    public static final int OPTION_STREAMING = 4;

    /**
     * option bit for writeReordered():
     * write the output in reverse order
     *
     * <p>This has the same effect as calling <code>writeReordered()</code>
     * first without this option, and then calling
     * <code>writeReverse()</code> without mirroring.
     * Doing this in the same step is faster and avoids a temporary buffer.
     * An example for using this option is output to a character terminal that
     * is designed for RTL scripts and stores text in reverse order.
     *
     * @see #writeReordered
     */
    public static final short OUTPUT_REVERSE = 16;

    /**
     * option bit for writeReordered():
     * remove Bidi control characters
     * (this does not affect INSERT_LRM_FOR_NUMERIC)
     *
     * <p>This option does not imply corresponding adjustment of the index
     * mappings.
     *
     * @see #writeReordered
     * @see #INSERT_LRM_FOR_NUMERIC
     */
    public static final short REMOVE_BIDI_CONTROLS = 8;

    /**
     * Reordering mode: Regular Logical to Visual Bidi algorithm according to Unicode.
     *  @see #setReorderingMode
     */
    public static final short REORDER_DEFAULT = 0;

    /**
     * Reordering mode: Logical to Visual algorithm grouping numbers with
     *  adjacent R characters (reversible algorithm).
     *  @see #setReorderingMode
     */
    public static final short REORDER_GROUP_NUMBERS_WITH_R = 2;

    /**
     * Reordering mode: Inverse Bidi (Visual to Logical) algorithm for the
     *  <code>REORDER_NUMBERS_SPECIAL</code> Bidi algorithm.
     *  @see #setReorderingMode
     */
    public static final short REORDER_INVERSE_FOR_NUMBERS_SPECIAL = 6;

    /**
     * Reordering mode: Visual to Logical algorithm equivalent to the regular
     *  Logical to Visual algorithm.
     *  @see #setReorderingMode
     */
    public static final short REORDER_INVERSE_LIKE_DIRECT = 5;

    /**
     * Reordering mode: Visual to Logical algorithm which handles numbers
     *  like L (same algorithm as selected by <code>setInverse(true)</code>.
     *  @see #setInverse
     *  @see #setReorderingMode
     */
    public static final short REORDER_INVERSE_NUMBERS_AS_L = 4;

    /**
     * Reordering mode: Logical to Visual algorithm which handles numbers in
     *  a way which mimicks the behavior of Windows XP.
     *  @see #setReorderingMode
     */
    public static final short REORDER_NUMBERS_SPECIAL = 1;

    /**
     * Reordering mode: Reorder runs only to transform a Logical LTR string
     *  to the logical RTL string with the same display, or vice-versa.<br>
     *  If this mode is set together with option
     *  <code>OPTION_INSERT_MARKS</code>, some Bidi controls in the source
     *  text may be removed and other controls may be added to produce the
     *  minimum combination which has the required display.
     *  @see #OPTION_INSERT_MARKS
     *  @see #setReorderingMode
     */
    public static final short REORDER_RUNS_ONLY = 3;

    /**
     * Right-to-left text.
     * <ul>
     * <li>As return value for <code>getDirection()</code>, it means
     *     that the source string contains no left-to-right characters, or
     *     that the source string is empty and the paragraph level is odd.
     * <li>As return value for <code>getBaseDirection()</code>, it
     *     means that the first strong character of the source string has
     *     a right-to-left direction.
     * </ul>
     */
    public static final byte RTL = 1;

    private Bidi() {
    }
}
