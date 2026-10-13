package android.text;

/**
 * Bit definitions for an integer defining the basic content type of text
 * held in an {@link Editable} object. Supported classes may be combined
 * with variations and flags to indicate desired behaviors.
 *
 * <h3>Examples</h3>
 *
 * <dl>
 * <dt>A password field with the password visible to the user:
 * <dd>inputType = TYPE_CLASS_TEXT |
 *     TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
 *
 * <dt>A multi-line postal address with automatic capitalization:
 * <dd>inputType = TYPE_CLASS_TEXT |
 *     TYPE_TEXT_VARIATION_POSTAL_ADDRESS |
 *     TYPE_TEXT_FLAG_MULTI_LINE
 *
 * <dt>A time field:
 * <dd>inputType = TYPE_CLASS_DATETIME |
 *     TYPE_DATETIME_VARIATION_TIME
 * </dl>
 */
public interface InputType {
    /**
     * Class for dates and times.  It supports the
     * following variations:
     * {@link #TYPE_DATETIME_VARIATION_NORMAL}
     * {@link #TYPE_DATETIME_VARIATION_DATE}, and
     * {@link #TYPE_DATETIME_VARIATION_TIME}.
     */
    int TYPE_CLASS_DATETIME = 4;

    /**
     * Class for numeric text.  This class supports the following flags:
     * {@link #TYPE_NUMBER_FLAG_SIGNED} and
     * {@link #TYPE_NUMBER_FLAG_DECIMAL}.  It also supports the following
     * variations: {@link #TYPE_NUMBER_VARIATION_NORMAL} and
     * {@link #TYPE_NUMBER_VARIATION_PASSWORD}.
     * <p>IME authors: If you do not recognize
     * the variation, normal should be assumed.</p>
     */
    int TYPE_CLASS_NUMBER = 2;

    /**
     * Class for a phone number.  This class currently supports no variations
     * or flags.
     */
    int TYPE_CLASS_PHONE = 3;

    /**
     * Class for normal text.  This class supports the following flags (only
     * one of which should be set):
     * {@link #TYPE_TEXT_FLAG_CAP_CHARACTERS},
     * {@link #TYPE_TEXT_FLAG_CAP_WORDS}, and.
     * {@link #TYPE_TEXT_FLAG_CAP_SENTENCES}.  It also supports the
     * following variations:
     * {@link #TYPE_TEXT_VARIATION_NORMAL}, and
     * {@link #TYPE_TEXT_VARIATION_URI}.  If you do not recognize the
     * variation, normal should be assumed.
     */
    int TYPE_CLASS_TEXT = 1;

    /**
     * Default variation of {@link #TYPE_CLASS_DATETIME}: allows entering
     * only a date.
     */
    int TYPE_DATETIME_VARIATION_DATE = 16;

    /**
     * Default variation of {@link #TYPE_CLASS_DATETIME}: allows entering
     * both a date and time.
     */
    int TYPE_DATETIME_VARIATION_NORMAL = 0;

    /**
     * Default variation of {@link #TYPE_CLASS_DATETIME}: allows entering
     * only a time.
     */
    int TYPE_DATETIME_VARIATION_TIME = 32;

    /**
     * Mask of bits that determine the overall class
     * of text being given.  Currently supported classes are:
     * {@link #TYPE_CLASS_TEXT}, {@link #TYPE_CLASS_NUMBER},
     * {@link #TYPE_CLASS_PHONE}, {@link #TYPE_CLASS_DATETIME}.
     * <p>IME authors: If the class is not one you
     * understand, assume {@link #TYPE_CLASS_TEXT} with NO variation
     * or flags.<p>
     */
    int TYPE_MASK_CLASS = 15;

    /**
     * Mask of bits that provide addition bit flags
     * of options.
     */
    int TYPE_MASK_FLAGS = 16773120;

    /**
     * Mask of bits that determine the variation of
     * the base content class.
     */
    int TYPE_MASK_VARIATION = 4080;

    /**
     * Special content type for when no explicit type has been specified.
     * This should be interpreted to mean that the target input connection
     * is not rich, it can not process and show things like candidate text nor
     * retrieve the current text, so the input method will need to run in a
     * limited "generate key events" mode, if it supports it. Note that some
     * input methods may not support it, for example a voice-based input
     * method will likely not be able to generate key events even if this
     * flag is set.
     */
    int TYPE_NULL = 0;

    /**
     * Flag of {@link #TYPE_CLASS_NUMBER}: the number is decimal, allowing
     * a decimal point to provide fractional values.
     */
    int TYPE_NUMBER_FLAG_DECIMAL = 8192;

    /**
     * Flag of {@link #TYPE_CLASS_NUMBER}: the number is signed, allowing
     * a positive or negative sign at the start.
     */
    int TYPE_NUMBER_FLAG_SIGNED = 4096;

    /**
     * Default variation of {@link #TYPE_CLASS_NUMBER}: plain normal
     * numeric text.  This was added in
     * {@link android.os.Build.VERSION_CODES#HONEYCOMB}.  An IME must target
     * this API version or later to see this input type; if it doesn't, a request
     * for this type will be dropped when passed through
     * {@link android.view.inputmethod.EditorInfo#makeCompatible(int)
     * EditorInfo.makeCompatible(int)}.
     */
    int TYPE_NUMBER_VARIATION_NORMAL = 0;

    /**
     * Variation of {@link #TYPE_CLASS_NUMBER}: entering a numeric password.
     * This was added in {@link android.os.Build.VERSION_CODES#HONEYCOMB}.  An
     * IME must target this API version or later to see this input type; if it
     * doesn't, a request for this type will be dropped when passed
     * through {@link android.view.inputmethod.EditorInfo#makeCompatible(int)
     * EditorInfo.makeCompatible(int)}.
     */
    int TYPE_NUMBER_VARIATION_PASSWORD = 16;

    /**
     * Flag for {@link #TYPE_CLASS_TEXT}: the text editor (which means
     * the application) is performing auto-completion of the text being entered
     * based on its own semantics, which it will present to the user as they type.
     * This generally means that the input method should not be showing
     * candidates itself, but can expect the editor to supply its own
     * completions/candidates from
     * {@link android.view.inputmethod.InputMethodSession#displayCompletions
     * InputMethodSession.displayCompletions()} as a result of the editor calling
     * {@link android.view.inputmethod.InputMethodManager#displayCompletions
     * InputMethodManager.displayCompletions()}.
     * Note the contrast with {@link #TYPE_TEXT_FLAG_AUTO_CORRECT} and
     * {@link #TYPE_TEXT_FLAG_NO_SUGGESTIONS}:
     * {@code TYPE_TEXT_FLAG_AUTO_COMPLETE} means the editor should show an
     * interface for displaying suggestions, but instead of supplying its own
     * it will rely on the Editor to pass completions/corrections.
     */
    int TYPE_TEXT_FLAG_AUTO_COMPLETE = 65536;

    /**
     * Flag for {@link #TYPE_CLASS_TEXT}: the user is entering free-form
     * text that should have auto-correction applied to it. Without this flag,
     * the IME will not try to correct typos. You should always set this flag
     * unless you really expect users to type non-words in this field, for
     * example to choose a name for a character in a game.
     * Contrast this with {@link #TYPE_TEXT_FLAG_AUTO_COMPLETE} and
     * {@link #TYPE_TEXT_FLAG_NO_SUGGESTIONS}:
     * {@code TYPE_TEXT_FLAG_AUTO_CORRECT} means that the IME will try to
     * auto-correct typos as the user is typing, but does not define whether
     * the IME offers an interface to show suggestions.
     */
    int TYPE_TEXT_FLAG_AUTO_CORRECT = 32768;

    /**
     * Flag for {@link #TYPE_CLASS_TEXT}: capitalize all characters.  Overrides
     * {@link #TYPE_TEXT_FLAG_CAP_WORDS} and
     * {@link #TYPE_TEXT_FLAG_CAP_SENTENCES}.  This value is explicitly defined
     * to be the same as {@link TextUtils#CAP_MODE_CHARACTERS}. Of course,
     * this only affects languages where there are upper-case and lower-case letters.
     */
    int TYPE_TEXT_FLAG_CAP_CHARACTERS = 4096;

    /**
     * Flag for {@link #TYPE_CLASS_TEXT}: capitalize the first character of
     * each sentence.  This value is explicitly defined
     * to be the same as {@link TextUtils#CAP_MODE_SENTENCES}. For example
     * in English it means to capitalize after a period and a space (note that other
     * languages may have different characters for period, or not use spaces,
     * or use different grammatical rules). Of course,
     * this only affects languages where there are upper-case and lower-case letters.
     */
    int TYPE_TEXT_FLAG_CAP_SENTENCES = 16384;

    /**
     * Flag for {@link #TYPE_CLASS_TEXT}: capitalize the first character of
     * every word.  Overrides {@link #TYPE_TEXT_FLAG_CAP_SENTENCES}.  This
     * value is explicitly defined
     * to be the same as {@link TextUtils#CAP_MODE_WORDS}. Of course,
     * this only affects languages where there are upper-case and lower-case letters.
     */
    int TYPE_TEXT_FLAG_CAP_WORDS = 8192;

    /**
     * Flag for {@link #TYPE_CLASS_TEXT}: Let the IME know the text conversion suggestions are
     * required by the application. Text conversion suggestion is for the transliteration languages
     * which has pronunciation characters and target characters. When the user is typing the
     * pronunciation charactes, the IME could provide the possible target characters to the user.
     * When this flag is set, the IME should insert the text conversion suggestions through
     * {@link Builder#setTextConversionSuggestions(List)} and
     * the {@link TextAttribute} with initialized with the text conversion suggestions is provided
     * by the IME to the application. To receive the additional information, the application needs
     * to implement {@link InputConnection#setComposingText(CharSequence, int, TextAttribute)},
     * {@link InputConnection#setComposingRegion(int, int, TextAttribute)}, and
     * {@link InputConnection#commitText(CharSequence, int, TextAttribute)}.
     */
    int TYPE_TEXT_FLAG_ENABLE_TEXT_CONVERSION_SUGGESTIONS = 1048576;

    /**
     * Flag for {@link #TYPE_CLASS_TEXT}: the regular text view associated
     * with this should not be multi-line, but when a fullscreen input method
     * is providing text it should use multiple lines if it can.
     */
    int TYPE_TEXT_FLAG_IME_MULTI_LINE = 262144;

    /**
     * Flag for {@link #TYPE_CLASS_TEXT}: multiple lines of text can be
     * entered into the field.  If this flag is not set, the text field
     * will be constrained to a single line. The IME may also choose not to
     * display an enter key when this flag is not set, as there should be no
     * need to create new lines.
     */
    int TYPE_TEXT_FLAG_MULTI_LINE = 131072;

    /**
     * Flag for {@link #TYPE_CLASS_TEXT}: the input method does not need to
     * display any dictionary-based candidates. This is useful for text views that
     * do not contain words from the language and do not benefit from any
     * dictionary-based completions or corrections. It overrides the
     * {@link #TYPE_TEXT_FLAG_AUTO_CORRECT} value when set.
     * Please avoid using this unless you are certain this is what you want.
     * Many input methods need suggestions to work well, for example the ones
     * based on gesture typing. Consider clearing
     * {@link #TYPE_TEXT_FLAG_AUTO_CORRECT} instead if you just do not
     * want the IME to correct typos.
     * Note the contrast with {@link #TYPE_TEXT_FLAG_AUTO_CORRECT} and
     * {@link #TYPE_TEXT_FLAG_AUTO_COMPLETE}:
     * {@code TYPE_TEXT_FLAG_NO_SUGGESTIONS} means the IME does not need to
     * show an interface to display suggestions. Most IMEs will also take this to
     * mean they do not need to try to auto-correct what the user is typing.
     */
    int TYPE_TEXT_FLAG_NO_SUGGESTIONS = 524288;

    /**
     * Variation of {@link #TYPE_CLASS_TEXT}: entering an e-mail address.
     */
    int TYPE_TEXT_VARIATION_EMAIL_ADDRESS = 32;

    /**
     * Variation of {@link #TYPE_CLASS_TEXT}: entering the subject line of
     * an e-mail.
     */
    int TYPE_TEXT_VARIATION_EMAIL_SUBJECT = 48;

    /**
     * Variation of {@link #TYPE_CLASS_TEXT}: entering text to filter contents
     * of a list etc.
     */
    int TYPE_TEXT_VARIATION_FILTER = 176;

    /**
     * Variation of {@link #TYPE_CLASS_TEXT}: entering the content of a long, possibly
     * formal message such as the body of an e-mail.
     */
    int TYPE_TEXT_VARIATION_LONG_MESSAGE = 80;

    /**
     * Default variation of {@link #TYPE_CLASS_TEXT}: plain old normal text.
     */
    int TYPE_TEXT_VARIATION_NORMAL = 0;

    /**
     * Variation of {@link #TYPE_CLASS_TEXT}: entering a password.
     */
    int TYPE_TEXT_VARIATION_PASSWORD = 128;

    /**
     * Variation of {@link #TYPE_CLASS_TEXT}: entering the name of a person.
     */
    int TYPE_TEXT_VARIATION_PERSON_NAME = 96;

    /**
     * Variation of {@link #TYPE_CLASS_TEXT}: entering text for phonetic
     * pronunciation, such as a phonetic name field in contacts. This is mostly
     * useful for languages where one spelling may have several phonetic
     * readings, like Japanese.
     */
    int TYPE_TEXT_VARIATION_PHONETIC = 192;

    /**
     * Variation of {@link #TYPE_CLASS_TEXT}: entering a postal mailing address.
     */
    int TYPE_TEXT_VARIATION_POSTAL_ADDRESS = 112;

    /**
     * Variation of {@link #TYPE_CLASS_TEXT}: entering a short, possibly informal
     * message such as an instant message or a text message.
     */
    int TYPE_TEXT_VARIATION_SHORT_MESSAGE = 64;

    /**
     * Variation of {@link #TYPE_CLASS_TEXT}: entering a URI.
     */
    int TYPE_TEXT_VARIATION_URI = 16;

    /**
     * Variation of {@link #TYPE_CLASS_TEXT}: entering a password, which should
     * be visible to the user.
     */
    int TYPE_TEXT_VARIATION_VISIBLE_PASSWORD = 144;

    /**
     * Variation of {@link #TYPE_CLASS_TEXT}: entering text inside of a web form.
     */
    int TYPE_TEXT_VARIATION_WEB_EDIT_TEXT = 160;

    /**
     * Variation of {@link #TYPE_CLASS_TEXT}: entering e-mail address inside
     * of a web form.  This was added in
     * {@link android.os.Build.VERSION_CODES#HONEYCOMB}.  An IME must target
     * this API version or later to see this input type; if it doesn't, a request
     * for this type will be seen as {@link #TYPE_TEXT_VARIATION_EMAIL_ADDRESS}
     * when passed through {@link android.view.inputmethod.EditorInfo#makeCompatible(int)
     * EditorInfo.makeCompatible(int)}.
     */
    int TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS = 208;

    /**
     * Variation of {@link #TYPE_CLASS_TEXT}: entering password inside
     * of a web form.  This was added in
     * {@link android.os.Build.VERSION_CODES#HONEYCOMB}.  An IME must target
     * this API version or later to see this input type; if it doesn't, a request
     * for this type will be seen as {@link #TYPE_TEXT_VARIATION_PASSWORD}
     * when passed through {@link android.view.inputmethod.EditorInfo#makeCompatible(int)
     * EditorInfo.makeCompatible(int)}.
     */
    int TYPE_TEXT_VARIATION_WEB_PASSWORD = 224;
}
