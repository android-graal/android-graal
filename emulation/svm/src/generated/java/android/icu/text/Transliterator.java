package android.icu.text;

/**
 *  <code>Transliterator</code> is an abstract class that transliterates text from one format to another. The most common
 *  kind of transliterator is a script, or alphabet, transliterator. For example, a Russian to Latin transliterator
 *  changes Russian text written in Cyrillic characters to phonetically equivalent Latin characters. It does not
 *  <em>translate</em> Russian to English! Transliteration, unlike translation, operates on characters, without reference
 *  to the meanings of words and sentences.
 *
 *  <p>
 *  Although script conversion is its most common use, a transliterator can actually perform a more general class of
 *  tasks. In fact, <code>Transliterator</code> defines a very general API which specifies only that a segment of the
 *  input text is replaced by new text. The particulars of this conversion are determined entirely by subclasses of
 *  <code>Transliterator</code>.
 *
 *  <p>
 *  <b>Transliterators are stateless</b>
 *
 *  <p>
 *  <code>Transliterator</code> objects are <em>stateless</em>; they retain no information between calls to
 *  <code>transliterate()</code>. As a result, threads may share transliterators without synchronizing them. This might
 *  seem to limit the complexity of the transliteration operation. In practice, subclasses perform complex
 *  transliterations by delaying the replacement of text until it is known that no other replacements are possible. In
 *  other words, although the <code>Transliterator</code> objects are stateless, the source text itself embodies all the
 *  needed information, and delayed operation allows arbitrary complexity.
 *
 *  <p>
 *  <b>Batch transliteration</b>
 *
 *  <p>
 *  The simplest way to perform transliteration is all at once, on a string of existing text. This is referred to as
 *  <em>batch</em> transliteration. For example, given a string <code>input</code> and a transliterator <code>t</code>,
 *  the call
 *
 *  <blockquote><code>String result = t.transliterate(input);
 *  </code></blockquote>
 *
 *  will transliterate it and return the result. Other methods allow the client to specify a substring to be
 *  transliterated and to use {@link Replaceable} objects instead of strings, in order to preserve out-of-band
 *  information (such as text styles).
 *
 *  <p>
 *  <b>Keyboard transliteration</b>
 *
 *  <p>
 *  Somewhat more involved is <em>keyboard</em>, or incremental transliteration. This is the transliteration of text that
 *  is arriving from some source (typically the user's keyboard) one character at a time, or in some other piecemeal
 *  fashion.
 *
 *  <p>
 *  In keyboard transliteration, a <code>Replaceable</code> buffer stores the text. As text is inserted, as much as
 *  possible is transliterated on the fly. This means a GUI that displays the contents of the buffer may show text being
 *  modified as each new character arrives.
 *
 *  <p>
 *  Consider the simple rule-based Transliterator:
 *
 *  <blockquote><code>
 *  th&gt;{theta}<br>
 *  t&gt;{tau}
 *  </code></blockquote>
 *
 *  When the user types 't', nothing will happen, since the transliterator is waiting to see if the next character is
 *  'h'. To remedy this, we introduce the notion of a cursor, marked by a '|' in the output string:
 *
 *  <blockquote><code>
 *  t&gt;|{tau}<br>
 *  {tau}h&gt;{theta}
 *  </code></blockquote>
 *
 *  Now when the user types 't', tau appears, and if the next character is 'h', the tau changes to a theta. This is
 *  accomplished by maintaining a cursor position (independent of the insertion point, and invisible in the GUI) across
 *  calls to <code>transliterate()</code>. Typically, the cursor will be coincident with the insertion point, but in a
 *  case like the one above, it will precede the insertion point.
 *
 *  <p>
 *  Keyboard transliteration methods maintain a set of three indices that are updated with each call to
 *  <code>transliterate()</code>, including the cursor, start, and limit. These indices are changed by the method, and
 *  they are passed in and out via a Position object. The <code>start</code> index marks the beginning of the substring
 *  that the transliterator will look at. It is advanced as text becomes committed (but it is not the committed index;
 *  that's the <code>cursor</code>). The <code>cursor</code> index, described above, marks the point at which the
 *  transliterator last stopped, either because it reached the end, or because it required more characters to
 *  disambiguate between possible inputs. The <code>cursor</code> can also be explicitly set by rules.
 *  Any characters before the <code>cursor</code> index are frozen; future keyboard
 *  transliteration calls within this input sequence will not change them. New text is inserted at the <code>limit</code>
 *  index, which marks the end of the substring that the transliterator looks at.
 *
 *  <p>
 *  Because keyboard transliteration assumes that more characters are to arrive, it is conservative in its operation. It
 *  only transliterates when it can do so unambiguously. Otherwise it waits for more characters to arrive. When the
 *  client code knows that no more characters are forthcoming, perhaps because the user has performed some input
 *  termination operation, then it should call <code>finishTransliteration()</code> to complete any pending
 *  transliterations.
 *
 *  <p>
 *  <b>Inverses</b>
 *
 *  <p>
 *  Pairs of transliterators may be inverses of one another. For example, if transliterator <b>A</b> transliterates
 *  characters by incrementing their Unicode value (so "abc" -&gt; "def"), and transliterator <b>B</b> decrements character
 *  values, then <b>A</b> is an inverse of <b>B</b> and vice versa. If we compose <b>A</b> with <b>B</b> in a compound
 *  transliterator, the result is the identity transliterator, that is, a transliterator that does not change its input
 *  text.
 *
 *  The <code>Transliterator</code> method <code>getInverse()</code> returns a transliterator's inverse, if one exists,
 *  or <code>null</code> otherwise. However, the result of <code>getInverse()</code> usually will <em>not</em> be a true
 *  mathematical inverse. This is because true inverse transliterators are difficult to formulate. For example, consider
 *  two transliterators: <b>AB</b>, which transliterates the character 'A' to 'B', and <b>BA</b>, which transliterates
 *  'B' to 'A'. It might seem that these are exact inverses, since
 *
 *  <blockquote>"A" x <b>AB</b> -&gt; "B"<br>
 *  "B" x <b>BA</b> -&gt; "A"</blockquote>
 *
 *  where 'x' represents transliteration. However,
 *
 *  <blockquote>"ABCD" x <b>AB</b> -&gt; "BBCD"<br>
 *  "BBCD" x <b>BA</b> -&gt; "AACD"</blockquote>
 *
 *  so <b>AB</b> composed with <b>BA</b> is not the identity. Nonetheless, <b>BA</b> may be usefully considered to be
 *  <b>AB</b>'s inverse, and it is on this basis that <b>AB</b><code>.getInverse()</code> could legitimately return
 *  <b>BA</b>.
 *
 *  <p>
 *  <b>Filtering</b>
 *  <p>Each transliterator has a filter, which restricts changes to those characters selected by the filter. The
 *  filter affects just the characters that are changed -- the characters outside of the filter are still part of the
 *  context for the filter. For example, in the following even though 'x' is filtered out, and doesn't convert to y, it does affect the conversion of 'a'.
 *
 *  <pre>
 *  String rules = &quot;x &gt; y; x{a} &gt; b; &quot;;
 *  Transliterator tempTrans = Transliterator.createFromRules(&quot;temp&quot;, rules, Transliterator.FORWARD);
 *  tempTrans.setFilter(new UnicodeSet(&quot;[a]&quot;));
 *  String tempResult = tempTrans.transform(&quot;xa&quot;);
 *  // results in &quot;xb&quot;
 * </pre>
 *  <p>
 *  <b>IDs and display names</b>
 *
 *  <p>
 *  A transliterator is designated by a short identifier string or <em>ID</em>. IDs follow the format
 *  <em>source-destination</em>, where <em>source</em> describes the entity being replaced, and <em>destination</em>
 *  describes the entity replacing <em>source</em>. The entities may be the names of scripts, particular sequences of
 *  characters, or whatever else it is that the transliterator converts to or from. For example, a transliterator from
 *  Russian to Latin might be named "Russian-Latin". A transliterator from keyboard escape sequences to Latin-1
 *  characters might be named "KeyboardEscape-Latin1". By convention, system entity names are in English, with the
 *  initial letters of words capitalized; user entity names may follow any format so long as they do not contain dashes.
 *
 *  <p>
 *  In addition to programmatic IDs, transliterator objects have display names for presentation in user interfaces,
 *  returned by {@link #getDisplayName}.
 *
 *  <p>
 *  <b>Composed transliterators</b>
 *
 *  <p>
 *  In addition to built-in system transliterators like "Latin-Greek", there are also built-in <em>composed</em>
 *  transliterators. These are implemented by composing two or more component transliterators. For example, if we have
 *  scripts "A", "B", "C", and "D", and we want to transliterate between all pairs of them, then we need to write 12
 *  transliterators: "A-B", "A-C", "A-D", "B-A",..., "D-A", "D-B", "D-C". If it is possible to convert all scripts to an
 *  intermediate script "M", then instead of writing 12 rule sets, we only need to write 8: "A~M", "B~M", "C~M", "D~M",
 *  "M~A", "M~B", "M~C", "M~D". (This might not seem like a big win, but it's really 2<em>n</em> vs. <em>n</em>
 *  <sup>2</sup> - <em>n</em>, so as <em>n</em> gets larger the gain becomes significant. With 9 scripts, it's 18 vs. 72
 *  rule sets, a big difference.) Note the use of "~" rather than "-" for the script separator here; this indicates that
 *  the given transliterator is intended to be composed with others, rather than be used as is.
 *
 *  <p>
 *  Composed transliterators can be instantiated as usual. For example, the system transliterator "Devanagari-Gujarati"
 *  is a composed transliterator built internally as "Devanagari~InterIndic;InterIndic~Gujarati". When this
 *  transliterator is instantiated, it appears externally to be a standard transliterator (e.g., getID() returns
 *  "Devanagari-Gujarati").
 *
 *  <p><b>Rule syntax</b>
 *
 *  <p>A set of rules determines how to perform translations.
 *  Rules within a rule set are separated by semicolons (';').
 *  To include a literal semicolon, prefix it with a backslash ('\').
 *  Unicode Pattern_White_Space is ignored.
 *  If the first non-blank character on a line is '#',
 *  the entire line is ignored as a comment.
 *
 *  <p>Each set of rules consists of two groups, one forward, and one
 *  reverse. This is a convention that is not enforced; rules for one
 *  direction may be omitted, with the result that translations in
 *  that direction will not modify the source text. In addition,
 *  bidirectional forward-reverse rules may be specified for
 *  symmetrical transformations.
 *
 *  <p>Note: Another description of the Transliterator rule syntax is available in
 *  <a href="https://www.unicode.org/reports/tr35/tr35-general.html#Transform_Rules_Syntax">section
 *  Transform Rules Syntax of UTS #35: Unicode LDML</a>.
 *  The rules are shown there using arrow symbols ← and → and ↔.
 *  ICU supports both those and the equivalent ASCII symbols &lt; and &gt; and &lt;&gt;.
 *
 *  <p>Rule statements take one of the following forms:
 *
 *  <dl>
 *      <dt><code>$alefmadda=\\u0622;</code></dt>
 *      <dd><strong>Variable definition.</strong> The name on the
 *          left is assigned the text on the right. In this example,
 *          after this statement, instances of the left hand name,
 *          &quot;<code>$alefmadda</code>&quot;, will be replaced by
 *          the Unicode character U+0622. Variable names must begin
 *          with a letter and consist only of letters, digits, and
 *          underscores. Case is significant. Duplicate names cause
 *          an exception to be thrown, that is, variables cannot be
 *          redefined. The right hand side may contain well-formed
 *          text of any length, including no text at all (&quot;<code>$empty=;</code>&quot;).
 *          The right hand side may contain embedded <code>UnicodeSet</code>
 *          patterns, for example, &quot;<code>$softvowel=[eiyEIY]</code>&quot;.</dd>
 *      <dt><code>ai&gt;$alefmadda;</code></dt>
 *      <dd><strong>Forward translation rule.</strong> This rule
 *          states that the string on the left will be changed to the
 *          string on the right when performing forward
 *          transliteration.</dd>
 *      <dt><code>ai&lt;$alefmadda;</code></dt>
 *      <dd><strong>Reverse translation rule.</strong> This rule
 *          states that the string on the right will be changed to
 *          the string on the left when performing reverse
 *          transliteration.</dd>
 *  </dl>
 *
 *  <dl>
 *      <dt><code>ai&lt;&gt;$alefmadda;</code></dt>
 *      <dd><strong>Bidirectional translation rule.</strong> This
 *          rule states that the string on the right will be changed
 *          to the string on the left when performing forward
 *          transliteration, and vice versa when performing reverse
 *          transliteration.</dd>
 *  </dl>
 *
 *  <p>Translation rules consist of a <em>match pattern</em> and an <em>output
 *  string</em>. The match pattern consists of literal characters,
 *  optionally preceded by context, and optionally followed by
 *  context. Context characters, like literal pattern characters,
 *  must be matched in the text being transliterated. However, unlike
 *  literal pattern characters, they are not replaced by the output
 *  text. For example, the pattern &quot;<code>abc{def}</code>&quot;
 *  indicates the characters &quot;<code>def</code>&quot; must be
 *  preceded by &quot;<code>abc</code>&quot; for a successful match.
 *  If there is a successful match, &quot;<code>def</code>&quot; will
 *  be replaced, but not &quot;<code>abc</code>&quot;. The final '<code>}</code>'
 *  is optional, so &quot;<code>abc{def</code>&quot; is equivalent to
 *  &quot;<code>abc{def}</code>&quot;. Another example is &quot;<code>{123}456</code>&quot;
 *  (or &quot;<code>123}456</code>&quot;) in which the literal
 *  pattern &quot;<code>123</code>&quot; must be followed by &quot;<code>456</code>&quot;.
 *
 *  <p>The output string of a forward or reverse rule consists of
 *  characters to replace the literal pattern characters. If the
 *  output string contains the character '<code>|</code>', this is
 *  taken to indicate the location of the <em>cursor</em> after
 *  replacement. The cursor is the point in the text at which the
 *  next replacement, if any, will be applied. The cursor is usually
 *  placed within the replacement text; however, it can actually be
 *  placed into the precending or following context by using the
 *  special character '@'. Examples:
 *
 *  <pre>
 *      a {foo} z &gt; | @ bar; # foo -&gt; bar, move cursor before a
 *      {foo} xyz &gt; bar @@|; #&nbsp;foo -&gt; bar, cursor between y and z
 *  </pre>
 *
 *  <p><b>UnicodeSet</b>
 *
 *  <p><code>UnicodeSet</code> patterns may appear anywhere that
 *  makes sense. They may appear in variable definitions.
 *  Contrariwise, <code>UnicodeSet</code> patterns may themselves
 *  contain variable references, such as &quot;<code>$a=[a-z];$not_a=[^$a]</code>&quot;,
 *  or &quot;<code>$range=a-z;$ll=[$range]</code>&quot;.
 *
 *  <p><code>UnicodeSet</code> patterns may also be embedded directly
 *  into rule strings. Thus, the following two rules are equivalent:
 *
 *  <pre>
 *      $vowel=[aeiou]; $vowel&gt;'*'; # One way to do this
 *      [aeiou]&gt;'*'; # Another way
 *  </pre>
 *
 *  <p>See {@link UnicodeSet} for more documentation and examples.
 *
 *  <p><b>Segments</b>
 *
 *  <p>Segments of the input string can be matched and copied to the
 *  output string. This makes certain sets of rules simpler and more
 *  general, and makes reordering possible. For example:
 *
 *  <pre>
 *      ([a-z]) &gt; $1 $1; # double lowercase letters
 *      ([:Lu:]) ([:Ll:]) &gt; $2 $1; # reverse order of Lu-Ll pairs
 *  </pre>
 *
 *  <p>The segment of the input string to be copied is delimited by
 *  &quot;<code>(</code>&quot; and &quot;<code>)</code>&quot;. Up to
 *  nine segments may be defined. Segments may not overlap. In the
 *  output string, &quot;<code>$1</code>&quot; through &quot;<code>$9</code>&quot;
 *  represent the input string segments, in left-to-right order of
 *  definition.
 *
 *  <p><b>Anchors</b>
 *
 *  <p>Patterns can be anchored to the beginning or the end of the text. This is done with the
 *  special characters '<code>^</code>' and '<code>$</code>'. For example:
 *
 *  <pre>
 *    ^ a&nbsp;&nbsp; &gt; 'BEG_A'; &nbsp;&nbsp;# match 'a' at start of text
 *    &nbsp; a&nbsp;&nbsp; &gt; 'A'; # match other instances of 'a'
 *    &nbsp; z $ &gt; 'END_Z'; &nbsp;&nbsp;# match 'z' at end of text
 *    &nbsp; z&nbsp;&nbsp; &gt; 'Z';&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; # match other instances of 'z'
 *  </pre>
 *
 *  <p>It is also possible to match the beginning or the end of the text using a <code>UnicodeSet</code>.
 *  This is done by including a virtual anchor character '<code>$</code>' at the end of the
 *  set pattern. Although this is usually the match character for the end anchor, the set will
 *  match either the beginning or the end of the text, depending on its placement. For
 *  example:
 *
 *  <pre>
 *    $x = [a-z$]; &nbsp;&nbsp;# match 'a' through 'z' OR anchor
 *    $x 1&nbsp;&nbsp;&nbsp; &gt; 2;&nbsp;&nbsp; # match '1' after a-z or at the start
 *    &nbsp;&nbsp; 3 $x &gt; 4; &nbsp;&nbsp;# match '3' before a-z or at the end
 *  </pre>
 *
 *  <p><b>Example</b>
 *
 *  <p>The following example rules illustrate many of the features of
 *  the rule language.
 *
 *  <table border="0" cellpadding="4">
 *      <tr>
 *          <td style="vertical-align: top;">Rule 1.</td>
 *          <td style="vertical-align: top; write-space: nowrap;"><code>abc{def}&gt;x|y</code></td>
 *      </tr>
 *      <tr>
 *          <td style="vertical-align: top;">Rule 2.</td>
 *          <td style="vertical-align: top; write-space: nowrap;"><code>xyz&gt;r</code></td>
 *      </tr>
 *      <tr>
 *          <td style="vertical-align: top;">Rule 3.</td>
 *          <td style="vertical-align: top; write-space: nowrap;"><code>yz&gt;q</code></td>
 *      </tr>
 *  </table>
 *
 *  <p>Applying these rules to the string &quot;<code>adefabcdefz</code>&quot;
 *  yields the following results:
 *
 *  <table border="0" cellpadding="4">
 *      <tr>
 *          <td style="vertical-align: top; write-space: nowrap;"><code>|adefabcdefz</code></td>
 *          <td style="vertical-align: top;">Initial state, no rules match. Advance
 *          cursor.</td>
 *      </tr>
 *      <tr>
 *          <td style="vertical-align: top; write-space: nowrap;"><code>a|defabcdefz</code></td>
 *          <td style="vertical-align: top;">Still no match. Rule 1 does not match
 *          because the preceding context is not present.</td>
 *      </tr>
 *      <tr>
 *          <td style="vertical-align: top; write-space: nowrap;"><code>ad|efabcdefz</code></td>
 *          <td style="vertical-align: top;">Still no match. Keep advancing until
 *          there is a match...</td>
 *      </tr>
 *      <tr>
 *          <td style="vertical-align: top; write-space: nowrap;"><code>ade|fabcdefz</code></td>
 *          <td style="vertical-align: top;">...</td>
 *      </tr>
 *      <tr>
 *          <td style="vertical-align: top; write-space: nowrap;"><code>adef|abcdefz</code></td>
 *          <td style="vertical-align: top;">...</td>
 *      </tr>
 *      <tr>
 *          <td style="vertical-align: top; write-space: nowrap;"><code>adefa|bcdefz</code></td>
 *          <td style="vertical-align: top;">...</td>
 *      </tr>
 *      <tr>
 *          <td style="vertical-align: top; write-space: nowrap;"><code>adefab|cdefz</code></td>
 *          <td style="vertical-align: top;">...</td>
 *      </tr>
 *      <tr>
 *          <td style="vertical-align: top; write-space: nowrap;"><code>adefabc|defz</code></td>
 *          <td style="vertical-align: top;">Rule 1 matches; replace &quot;<code>def</code>&quot;
 *          with &quot;<code>xy</code>&quot; and back up the cursor
 *          to before the '<code>y</code>'.</td>
 *      </tr>
 *      <tr>
 *          <td style="vertical-align: top; write-space: nowrap;"><code>adefabcx|yz</code></td>
 *          <td style="vertical-align: top;">Although &quot;<code>xyz</code>&quot; is
 *          present, rule 2 does not match because the cursor is
 *          before the '<code>y</code>', not before the '<code>x</code>'.
 *          Rule 3 does match. Replace &quot;<code>yz</code>&quot;
 *          with &quot;<code>q</code>&quot;.</td>
 *      </tr>
 *      <tr>
 *          <td style="vertical-align: top; write-space: nowrap;"><code>adefabcxq|</code></td>
 *          <td style="vertical-align: top;">The cursor is at the end;
 *          transliteration is complete.</td>
 *      </tr>
 *  </table>
 *
 *  <p>The order of rules is significant. If multiple rules may match
 *  at some point, the first matching rule is applied.
 *
 *  <p>Forward and reverse rules may have an empty output string.
 *  Otherwise, an empty left or right hand side of any statement is a
 *  syntax error.
 *
 *  <p>Single quotes are used to quote any character other than a
 *  digit or letter. To specify a single quote itself, inside or
 *  outside of quotes, use two single quotes in a row. For example,
 *  the rule &quot;<code>'&gt;'&gt;o''clock</code>&quot; changes the
 *  string &quot;<code>&gt;</code>&quot; to the string &quot;<code>o'clock</code>&quot;.
 *
 *  <p><b>Notes</b>
 *
 *  <p>While a Transliterator is being built from rules, it checks that
 *  the rules are added in proper order. For example, if the rule
 *  &quot;a&gt;x&quot; is followed by the rule &quot;ab&gt;y&quot;,
 *  then the second rule will throw an exception. The reason is that
 *  the second rule can never be triggered, since the first rule
 *  always matches anything it matches. In other words, the first
 *  rule <em>masks</em> the second rule.
 *
 *  @author Alan Liu
 */
public final class Transliterator {
    /**
     * Direction constant indicating the forward direction in a transliterator,
     * e.g., the forward rules of a rule-based Transliterator.  An "A-B"
     * transliterator transliterates A to B when operating in the forward
     * direction, and B to A when operating in the reverse direction.
     */
    public static final int FORWARD = 0;

    /**
     * Direction constant indicating the reverse direction in a transliterator,
     * e.g., the reverse rules of a rule-based Transliterator.  An "A-B"
     * transliterator transliterates A to B when operating in the forward
     * direction, and B to A when operating in the reverse direction.
     */
    public static final int REVERSE = 1;

    private Transliterator() {
    }
}
