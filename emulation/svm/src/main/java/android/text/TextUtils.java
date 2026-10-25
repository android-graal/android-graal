/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.text;

import static java.lang.annotation.RetentionPolicy.SOURCE;

import android.annotation.FloatRange;
import android.annotation.IntDef;
import android.annotation.IntRange;
import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.PluralsRes;
import android.compat.annotation.UnsupportedAppUsage;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.ravenwood.annotation.RavenwoodKeepWholeClass;
import android.text.style.ParagraphStyle;
import android.text.style.UpdateAppearance;
import android.util.EmptyArray;
import android.util.Log;
import android.util.Printer;

import com.android.internal.util.ArrayUtils;
import com.android.internal.util.Preconditions;

import org.androidgraal.hosted.annotate.Adapted;
import org.androidgraal.hosted.annotate.Emulated;

import java.lang.annotation.Retention;
import java.lang.reflect.Array;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@RavenwoodKeepWholeClass
@Emulated
public class TextUtils {
    private static final String TAG = "TextUtils";

    // Zero-width character used to fill ellipsized strings when codepoint length must be preserved.
    /* package */ static final char ELLIPSIS_FILLER = '\uFEFF'; // ZERO WIDTH NO-BREAK SPACE

    // TODO: Based on CLDR data, these need to be localized for Dzongkha (dz) and perhaps
    // Hong Kong Traditional Chinese (zh-Hant-HK), but that may need to depend on the actual word
    // being ellipsized and not the locale.
    private static final String ELLIPSIS_NORMAL = "\u2026"; // HORIZONTAL ELLIPSIS (…)
    private static final String ELLIPSIS_TWO_DOTS = "\u2025"; // TWO DOT LEADER (‥)

    /** @hide */
    public static final int LINE_FEED_CODE_POINT = 10;

    private static final int NBSP_CODE_POINT = 160;

    /**
     * Flags for {@link #makeSafeForPresentation(String, int, float, int)}
     *
     * @hide
     */
    @Retention(SOURCE)
    @IntDef(flag = true, prefix = "CLEAN_STRING_FLAG_",
            value = {SAFE_STRING_FLAG_TRIM, SAFE_STRING_FLAG_SINGLE_LINE,
                    SAFE_STRING_FLAG_FIRST_LINE})
    public @interface SafeStringFlags {}

    /**
     * Remove {@link Character#isWhitespace(int) whitespace} and non-breaking spaces from the edges
     * of the label.
     *
     * @see #makeSafeForPresentation(String, int, float, int)
     */
    public static final int SAFE_STRING_FLAG_TRIM = 0x1;

    /**
     * Force entire string into single line of text (no newlines). Cannot be set at the same time as
     * {@link #SAFE_STRING_FLAG_FIRST_LINE}.
     *
     * @see #makeSafeForPresentation(String, int, float, int)
     */
    public static final int SAFE_STRING_FLAG_SINGLE_LINE = 0x2;

    /**
     * Return only first line of text (truncate at first newline). Cannot be set at the same time as
     * {@link #SAFE_STRING_FLAG_SINGLE_LINE}.
     *
     * @see #makeSafeForPresentation(String, int, float, int)
     */
    public static final int SAFE_STRING_FLAG_FIRST_LINE = 0x4;

    /** {@hide} */
    @NonNull
    public static String getEllipsisString(@NonNull TextUtils.TruncateAt method) {
        return (method == TextUtils.TruncateAt.END_SMALL) ? ELLIPSIS_TWO_DOTS : ELLIPSIS_NORMAL;
    }

    private TextUtils() { /* cannot be instantiated */ }

    public static void getChars(CharSequence s, int start, int end,
                                char[] dest, int destoff) {
        Class<? extends CharSequence> c = s.getClass();

        if (c == String.class)
            ((String) s).getChars(start, end, dest, destoff);
        else if (c == StringBuffer.class)
            ((StringBuffer) s).getChars(start, end, dest, destoff);
        else if (c == StringBuilder.class)
            ((StringBuilder) s).getChars(start, end, dest, destoff);
        else if (s instanceof GetChars)
            ((GetChars) s).getChars(start, end, dest, destoff);
        else {
            for (int i = start; i < end; i++)
                dest[destoff++] = s.charAt(i);
        }
    }


    /**
     * Returns the longest prefix of a string for which the UTF-8 encoding fits into the given
     * number of bytes, with the additional guarantee that the string is not truncated in the middle
     * of a valid surrogate pair.
     *
     * <p>Unpaired surrogates are counted as taking 3 bytes of storage. However, a subsequent
     * attempt to actually encode a string containing unpaired surrogates is likely to be rejected
     * by the UTF-8 implementation.
     *
     * (copied from google/thirdparty)
     *
     * @param str a string
     * @param maxbytes the maximum number of UTF-8 encoded bytes
     * @return the beginning of the string, so that it uses at most maxbytes bytes in UTF-8
     * @throws IndexOutOfBoundsException if maxbytes is negative
     *
     * @hide
     */
    public static String truncateStringForUtf8Storage(String str, int maxbytes) {
        if (maxbytes < 0) {
            throw new IndexOutOfBoundsException();
        }

        int bytes = 0;
        for (int i = 0, len = str.length(); i < len; i++) {
            char c = str.charAt(i);
            if (c < 0x80) {
                bytes += 1;
            } else if (c < 0x800) {
                bytes += 2;
            } else if (c < Character.MIN_SURROGATE
                    || c > Character.MAX_SURROGATE
                    || str.codePointAt(i) < Character.MIN_SUPPLEMENTARY_CODE_POINT) {
                bytes += 3;
            } else {
                bytes += 4;
                i += (bytes > maxbytes) ? 0 : 1;
            }
            if (bytes > maxbytes) {
                return str.substring(0, i);
            }
        }
        return str;
    }


    /**
     * Returns a string containing the tokens joined by delimiters.
     *
     * @param delimiter a CharSequence that will be inserted between the tokens. If null, the string
     *     "null" will be used as the delimiter.
     * @param tokens an array objects to be joined. Strings will be formed from the objects by
     *     calling object.toString(). If tokens is null, a NullPointerException will be thrown. If
     *     tokens is an empty array, an empty string will be returned.
     */
    public static String join(@NonNull CharSequence delimiter, @NonNull Object[] tokens) {
        final int length = tokens.length;
        if (length == 0) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(tokens[0]);
        for (int i = 1; i < length; i++) {
            sb.append(delimiter);
            sb.append(tokens[i]);
        }
        return sb.toString();
    }

    /**
     * Returns a string containing the tokens joined by delimiters.
     *
     * @param delimiter a CharSequence that will be inserted between the tokens. If null, the string
     *     "null" will be used as the delimiter.
     * @param tokens an array objects to be joined. Strings will be formed from the objects by
     *     calling object.toString(). If tokens is null, a NullPointerException will be thrown. If
     *     tokens is empty, an empty string will be returned.
     */
    public static String join(@NonNull CharSequence delimiter, @NonNull Iterable tokens) {
        final Iterator<?> it = tokens.iterator();
        if (!it.hasNext()) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(it.next());
        while (it.hasNext()) {
            sb.append(delimiter);
            sb.append(it.next());
        }
        return sb.toString();
    }

    /**
     *
     * This method yields the same result as {@code text.split(expression, -1)} except that if
     * {@code text.isEmpty()} then this method returns an empty array whereas
     * {@code "".split(expression, -1)} would have returned an array with a single {@code ""}.
     *
     * The {@code -1} means that trailing empty Strings are not removed from the result; for
     * example split("a,", ","  ) returns {"a", ""}. Note that whether a leading zero-width match
     * can result in a leading {@code ""} depends on whether your app
     * {@link android.content.pm.ApplicationInfo#targetSdkVersion targets an SDK version}
     * {@code <= 28}; see {@link Pattern#split(CharSequence, int)}.
     *
     * @param text the string to split
     * @param expression the regular expression to match
     * @return an array of strings. The array will be empty if text is empty
     *
     * @throws NullPointerException if expression or text is null
     */
    public static String[] split(String text, String expression) {
        if (text.length() == 0) {
            return EmptyArray.STRING;
        } else {
            return text.split(expression, -1);
        }
    }

    /**
     * Splits a string on a pattern. This method yields the same result as
     * {@code pattern.split(text, -1)} except that if {@code text.isEmpty()} then this method
     * returns an empty array whereas {@code pattern.split("", -1)} would have returned an array
     * with a single {@code ""}.
     *
     * The {@code -1} means that trailing empty Strings are not removed from the result;
     * Note that whether a leading zero-width match can result in a leading {@code ""} depends
     * on whether your app {@link android.content.pm.ApplicationInfo#targetSdkVersion targets
     * an SDK version} {@code <= 28}; see {@link Pattern#split(CharSequence, int)}.
     *
     * @param text the string to split
     * @param pattern the regular expression to match
     * @return an array of strings. The array will be empty if text is empty
     *
     * @throws NullPointerException if expression or text is null
     */
    public static String[] split(String text, Pattern pattern) {
        if (text.length() == 0) {
            return EmptyArray.STRING;
        } else {
            return pattern.split(text, -1);
        }
    }

    /**
     * An interface for splitting strings according to rules that are opaque to the user of this
     * interface. This also has less overhead than split, which uses regular expressions and
     * allocates an array to hold the results.
     *
     * <p>The most efficient way to use this class is:
     *
     * <pre>
     * // Once
     * TextUtils.StringSplitter splitter = new TextUtils.SimpleStringSplitter(delimiter);
     *
     * // Once per string to split
     * splitter.setString(string);
     * for (String s : splitter) {
     *     ...
     * }
     * </pre>
     */
    public interface StringSplitter extends Iterable<String> {
        public void setString(String string);
    }

    /**
     * A simple string splitter.
     *
     * <p>If the final character in the string to split is the delimiter then no empty string will
     * be returned for the empty string after that delimeter. That is, splitting <tt>"a,b,"</tt> on
     * comma will return <tt>"a", "b"</tt>, not <tt>"a", "b", ""</tt>.
     */
    public static class SimpleStringSplitter implements StringSplitter, Iterator<String> {
        private String mString;
        private char mDelimiter;
        private int mPosition;
        private int mLength;

        /**
         * Initializes the splitter. setString may be called later.
         * @param delimiter the delimeter on which to split
         */
        public SimpleStringSplitter(char delimiter) {
            mDelimiter = delimiter;
        }

        /**
         * Sets the string to split
         * @param string the string to split
         */
        public void setString(String string) {
            mString = string;
            mPosition = 0;
            mLength = mString.length();
        }

        public Iterator<String> iterator() {
            return this;
        }

        public boolean hasNext() {
            return mPosition < mLength;
        }

        public String next() {
            int end = mString.indexOf(mDelimiter, mPosition);
            if (end == -1) {
                end = mLength;
            }
            String nextString = mString.substring(mPosition, end);
            mPosition = end + 1; // Skip the delimiter.
            return nextString;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Returns true if the string is null or 0-length.
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }

    /** {@hide} */
    public static String nullIfEmpty(@Nullable String str) {
        return isEmpty(str) ? null : str;
    }

    /** {@hide} */
    public static String emptyIfNull(@Nullable String str) {
        return str == null ? "" : str;
    }

    /** {@hide} */
    public static String firstNotEmpty(@Nullable String a, @NonNull String b) {
        return !isEmpty(a) ? a : Preconditions.checkStringNotEmpty(b);
    }

    /** {@hide} */
    public static int length(@Nullable String s) {
        return s != null ? s.length() : 0;
    }

    /**
     * @return interned string if it's null.
     * @hide
     */
    public static String safeIntern(String s) {
        return (s != null) ? s.intern() : null;
    }

    /**
     * Returns the length that the specified CharSequence would have if
     * spaces and ASCII control characters were trimmed from the start and end,
     * as by {@link String#trim}.
     */
    public static int getTrimmedLength(CharSequence s) {
        int len = s.length();

        int start = 0;
        while (start < len && s.charAt(start) <= ' ') {
            start++;
        }

        int end = len;
        while (end > start && s.charAt(end - 1) <= ' ') {
            end--;
        }

        return end - start;
    }

    /**
     * Returns true if a and b are equal, including if they are both null.
     * <p><i>Note: In platform versions 1.1 and earlier, this method only worked well if
     * both the arguments were instances of String.</i></p>
     * @param a first CharSequence to check
     * @param b second CharSequence to check
     * @return true if a and b are equal
     */
    public static boolean equals(@Nullable CharSequence a, @Nullable CharSequence b) {
        if (a == b) return true;
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }

    /** @hide */
    public static final int ALIGNMENT_SPAN = 1;
    /** @hide */
    public static final int FIRST_SPAN = ALIGNMENT_SPAN;
    /** @hide */
    public static final int FOREGROUND_COLOR_SPAN = 2;
    /** @hide */
    public static final int RELATIVE_SIZE_SPAN = 3;
    /** @hide */
    public static final int SCALE_X_SPAN = 4;
    /** @hide */
    public static final int STRIKETHROUGH_SPAN = 5;
    /** @hide */
    public static final int UNDERLINE_SPAN = 6;
    /** @hide */
    public static final int STYLE_SPAN = 7;
    /** @hide */
    public static final int BULLET_SPAN = 8;
    /** @hide */
    public static final int QUOTE_SPAN = 9;
    /** @hide */
    public static final int LEADING_MARGIN_SPAN = 10;
    /** @hide */
    public static final int URL_SPAN = 11;
    /** @hide */
    public static final int BACKGROUND_COLOR_SPAN = 12;
    /** @hide */
    public static final int TYPEFACE_SPAN = 13;
    /** @hide */
    public static final int SUPERSCRIPT_SPAN = 14;
    /** @hide */
    public static final int SUBSCRIPT_SPAN = 15;
    /** @hide */
    public static final int ABSOLUTE_SIZE_SPAN = 16;
    /** @hide */
    public static final int TEXT_APPEARANCE_SPAN = 17;
    /** @hide */
    public static final int ANNOTATION = 18;
    /** @hide */
    public static final int SUGGESTION_SPAN = 19;
    /** @hide */
    public static final int SPELL_CHECK_SPAN = 20;
    /** @hide */
    public static final int SUGGESTION_RANGE_SPAN = 21;
    /** @hide */
    public static final int EASY_EDIT_SPAN = 22;
    /** @hide */
    public static final int LOCALE_SPAN = 23;
    /** @hide */
    public static final int TTS_SPAN = 24;
    /** @hide */
    public static final int ACCESSIBILITY_CLICKABLE_SPAN = 25;
    /** @hide */
    public static final int ACCESSIBILITY_URL_SPAN = 26;
    /** @hide */
    public static final int LINE_BACKGROUND_SPAN = 27;
    /** @hide */
    public static final int LINE_HEIGHT_SPAN = 28;
    /** @hide */
    public static final int ACCESSIBILITY_REPLACEMENT_SPAN = 29;
    /** @hide */
    public static final int LINE_BREAK_CONFIG_SPAN = 30;
    /** @hide */
    public static final int NO_WRITING_TOOLS_SPAN = 31;
    /** @hide */
    public static final int LAST_SPAN = NO_WRITING_TOOLS_SPAN;

    /**
     * Flatten a CharSequence and whatever styles can be copied across processes
     * into the parcel.
     */
    @Adapted(value = {"https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-10.0.0_r1/core/java/android/text/TextUtils.java#L747-L748",
            "https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-10.0.0_r1/core/java/android/text/TextUtils.java#L783-L785"},
            reason = "writeString below R, and a span throws")
    public static void writeToParcel(@Nullable CharSequence cs, @NonNull Parcel p,
            int parcelableFlags) {
        if (cs instanceof Spanned) {
            p.writeInt(0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                p.writeString8(cs.toString());
            } else {
                p.writeString(cs.toString());
            }

            Spanned sp = (Spanned) cs;
            Object[] os = sp.getSpans(0, cs.length(), Object.class);

            // note to people adding to this: check more specific types
            // before more generic types.  also notice that it uses
            // "if" instead of "else if" where there are interfaces
            // so one object can be several.

            for (int i = 0; i < os.length; i++) {
                throw new UnsupportedOperationException();
            }

            p.writeInt(0);
        } else {
            p.writeInt(1);
            if (cs != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    p.writeString8(cs.toString());
                } else {
                    p.writeString(cs.toString());
                }
            } else {
                p.writeString8(null);
            }
        }
    }

    private static void writeWhere(Parcel p, Spanned sp, Object o) {
        p.writeInt(sp.getSpanStart(o));
        p.writeInt(sp.getSpanEnd(o));
        p.writeInt(sp.getSpanFlags(o));
    }

    @Adapted(value = "https://android.googlesource.com/platform/frameworks/base/+/refs/tags/android-10.0.0_r1/core/java/android/text/TextUtils.java#L805-L807",
            reason = "readString below R, and a span throws")
    public static final Parcelable.Creator<CharSequence> CHAR_SEQUENCE_CREATOR
            = new Parcelable.Creator<CharSequence>() {
        /**
         * Read and return a new CharSequence, possibly with styles,
         * from the parcel.
         */
        public CharSequence createFromParcel(Parcel p) {
            int kind = p.readInt();

            String string = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
                    ? p.readString8() : p.readString();
            if (string == null) {
                return null;
            }

            if (kind == 1) {
                return string;
            }

            SpannableString sp = new SpannableString(string);

            while (true) {
                kind = p.readInt();

                if (kind == 0)
                    break;

                throw new UnsupportedOperationException();
            }

            return sp;
        }

        public CharSequence[] newArray(int size)
        {
            return new CharSequence[size];
        }
    };

    public enum TruncateAt {
        START,
        MIDDLE,
        END,
        MARQUEE,
        /**
         * @hide
         */
        @UnsupportedAppUsage
        END_SMALL
    }

    public interface EllipsizeCallback {
        /**
         * This method is called to report that the specified region of
         * text was ellipsized away by a call to {@link #ellipsize}.
         */
        public void ellipsized(int start, int end);
    }

    // Returns true if the character's presence could affect RTL layout.
    //
    // In order to be fast, the code is intentionally rough and quite conservative in its
    // considering inclusion of any non-BMP or surrogate characters or anything in the bidi
    // blocks or any bidi formatting characters with a potential to affect RTL layout.
    /* package */
    static boolean couldAffectRtl(char c) {
        return (0x0590 <= c && c <= 0x08FF) ||  // RTL scripts
                c == 0x200E ||  // Bidi format character
                c == 0x200F ||  // Bidi format character
                (0x202A <= c && c <= 0x202E) ||  // Bidi format characters
                (0x2066 <= c && c <= 0x2069) ||  // Bidi format characters
                (0xD800 <= c && c <= 0xDFFF) ||  // Surrogate pairs
                (0xFB1D <= c && c <= 0xFDFF) ||  // Hebrew and Arabic presentation forms
                (0xFE70 <= c && c <= 0xFEFE);  // Arabic presentation forms
    }

    // Returns true if there is no character present that may potentially affect RTL layout.
    // Since this calls couldAffectRtl() above, it's also quite conservative, in the way that
    // it may return 'false' (needs bidi) although careful consideration may tell us it should
    // return 'true' (does not need bidi).
    /* package */
    static boolean doesNotNeedBidi(char[] text, int start, int len) {
        final int end = start + len;
        for (int i = start; i < end; i++) {
            if (couldAffectRtl(text[i])) {
                return false;
            }
        }
        return true;
    }

    /* package */ static void recycle(char[] temp) {
        if (temp.length > 1000)
            return;

        synchronized (sLock) {
            sTemp = temp;
        }
    }

    /**
     * Html-encode the string.
     * @param s the string to be encoded
     * @return the encoded string
     */
    public static String htmlEncode(String s) {
        StringBuilder sb = new StringBuilder();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            switch (c) {
            case '<':
                sb.append("&lt;"); //$NON-NLS-1$
                break;
            case '>':
                sb.append("&gt;"); //$NON-NLS-1$
                break;
            case '&':
                sb.append("&amp;"); //$NON-NLS-1$
                break;
            case '\'':
                //http://www.w3.org/TR/xhtml1
                // The named character reference &apos; (the apostrophe, U+0027) was introduced in
                // XML 1.0 but does not appear in HTML. Authors should therefore use &#39; instead
                // of &apos; to work as expected in HTML 4 user agents.
                sb.append("&#39;"); //$NON-NLS-1$
                break;
            case '"':
                sb.append("&quot;"); //$NON-NLS-1$
                break;
            default:
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Returns whether the given CharSequence contains any printable characters.
     */
    public static boolean isGraphic(CharSequence str) {
        final int len = str.length();
        for (int cp, i=0; i<len; i+=Character.charCount(cp)) {
            cp = Character.codePointAt(str, i);
            int gc = Character.getType(cp);
            if (gc != Character.CONTROL
                    && gc != Character.FORMAT
                    && gc != Character.SURROGATE
                    && gc != Character.UNASSIGNED
                    && gc != Character.LINE_SEPARATOR
                    && gc != Character.PARAGRAPH_SEPARATOR
                    && gc != Character.SPACE_SEPARATOR) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns whether this character is a printable character.
     *
     * This does not support non-BMP characters and should not be used.
     *
     * @deprecated Use {@link #isGraphic(CharSequence)} instead.
     */
    @Deprecated
    public static boolean isGraphic(char c) {
        int gc = Character.getType(c);
        return     gc != Character.CONTROL
                && gc != Character.FORMAT
                && gc != Character.SURROGATE
                && gc != Character.UNASSIGNED
                && gc != Character.LINE_SEPARATOR
                && gc != Character.PARAGRAPH_SEPARATOR
                && gc != Character.SPACE_SEPARATOR;
    }

    /**
     * Returns whether the given CharSequence contains only digits.
     */
    public static boolean isDigitsOnly(CharSequence str) {
        final int len = str.length();
        for (int cp, i = 0; i < len; i += Character.charCount(cp)) {
            cp = Character.codePointAt(str, i);
            if (!Character.isDigit(cp)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @hide
     */
    public static boolean isPrintableAscii(final char c) {
        final int asciiFirst = 0x20;
        final int asciiLast = 0x7E;  // included
        return (asciiFirst <= c && c <= asciiLast) || c == '\r' || c == '\n';
    }

    /**
     * @hide
     */
    @UnsupportedAppUsage
    public static boolean isPrintableAsciiOnly(final CharSequence str) {
        final int len = str.length();
        for (int i = 0; i < len; i++) {
            if (!isPrintableAscii(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Capitalization mode for {@link #getCapsMode}: capitalize all
     * characters.  This value is explicitly defined to be the same as
     * {@link InputType#TYPE_TEXT_FLAG_CAP_CHARACTERS}.
     */
    public static final int CAP_MODE_CHARACTERS
            = InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS;

    /**
     * Capitalization mode for {@link #getCapsMode}: capitalize the first
     * character of all words.  This value is explicitly defined to be the same as
     * {@link InputType#TYPE_TEXT_FLAG_CAP_WORDS}.
     */
    public static final int CAP_MODE_WORDS
            = InputType.TYPE_TEXT_FLAG_CAP_WORDS;

    /**
     * Capitalization mode for {@link #getCapsMode}: capitalize the first
     * character of each sentence.  This value is explicitly defined to be the same as
     * {@link InputType#TYPE_TEXT_FLAG_CAP_SENTENCES}.
     */
    public static final int CAP_MODE_SENTENCES
            = InputType.TYPE_TEXT_FLAG_CAP_SENTENCES;

    /**
     * Determine what caps mode should be in effect at the current offset in
     * the text.  Only the mode bits set in <var>reqModes</var> will be
     * checked.  Note that the caps mode flags here are explicitly defined
     * to match those in {@link InputType}.
     *
     * @param cs The text that should be checked for caps modes.
     * @param off Location in the text at which to check.
     * @param reqModes The modes to be checked: may be any combination of
     * {@link #CAP_MODE_CHARACTERS}, {@link #CAP_MODE_WORDS}, and
     * {@link #CAP_MODE_SENTENCES}.
     *
     * @return Returns the actual capitalization modes that can be in effect
     * at the current position, which is any combination of
     * {@link #CAP_MODE_CHARACTERS}, {@link #CAP_MODE_WORDS}, and
     * {@link #CAP_MODE_SENTENCES}.
     */
    public static int getCapsMode(CharSequence cs, int off, int reqModes) {
        if (off < 0) {
            return 0;
        }

        int i;
        char c;
        int mode = 0;

        if ((reqModes&CAP_MODE_CHARACTERS) != 0) {
            mode |= CAP_MODE_CHARACTERS;
        }
        if ((reqModes&(CAP_MODE_WORDS|CAP_MODE_SENTENCES)) == 0) {
            return mode;
        }

        // Back over allowed opening punctuation.

        for (i = off; i > 0; i--) {
            c = cs.charAt(i - 1);

            if (c != '"' && c != '\'' &&
                Character.getType(c) != Character.START_PUNCTUATION) {
                break;
            }
        }

        // Start of paragraph, with optional whitespace.

        int j = i;
        while (j > 0 && ((c = cs.charAt(j - 1)) == ' ' || c == '\t')) {
            j--;
        }
        if (j == 0 || cs.charAt(j - 1) == '\n') {
            return mode | CAP_MODE_WORDS;
        }

        // Or start of word if we are that style.

        if ((reqModes&CAP_MODE_SENTENCES) == 0) {
            if (i != j) mode |= CAP_MODE_WORDS;
            return mode;
        }

        // There must be a space if not the start of paragraph.

        if (i == j) {
            return mode;
        }

        // Back over allowed closing punctuation.

        for (; j > 0; j--) {
            c = cs.charAt(j - 1);

            if (c != '"' && c != '\'' &&
                Character.getType(c) != Character.END_PUNCTUATION) {
                break;
            }
        }

        if (j > 0) {
            c = cs.charAt(j - 1);

            if (c == '.' || c == '?' || c == '!') {
                // Do not capitalize if the word ends with a period but
                // also contains a period, in which case it is an abbreviation.

                if (c == '.') {
                    for (int k = j - 2; k >= 0; k--) {
                        c = cs.charAt(k);

                        if (c == '.') {
                            return mode;
                        }

                        if (!Character.isLetter(c)) {
                            break;
                        }
                    }
                }

                return mode | CAP_MODE_SENTENCES;
            }
        }

        return mode;
    }

    /**
     * Does a comma-delimited list 'delimitedString' contain a certain item?
     * (without allocating memory)
     *
     * @hide
     */
    public static boolean delimitedStringContains(
            String delimitedString, char delimiter, String item) {
        if (isEmpty(delimitedString) || isEmpty(item)) {
            return false;
        }
        int pos = -1;
        int length = delimitedString.length();
        while ((pos = delimitedString.indexOf(item, pos + 1)) != -1) {
            if (pos > 0 && delimitedString.charAt(pos - 1) != delimiter) {
                continue;
            }
            int expectedDelimiterPos = pos + item.length();
            if (expectedDelimiterPos == length) {
                // Match at end of string.
                return true;
            }
            if (delimitedString.charAt(expectedDelimiterPos) == delimiter) {
                return true;
            }
        }
        return false;
    }

    /**
     * Pack 2 int values into a long, useful as a return value for a range
     * @see #unpackRangeStartFromLong(long)
     * @see #unpackRangeEndFromLong(long)
     * @hide
     */
    @UnsupportedAppUsage
    public static long packRangeInLong(int start, int end) {
        return (((long) start) << 32) | end;
    }

    /**
     * Get the start value from a range packed in a long by {@link #packRangeInLong(int, int)}
     * @see #unpackRangeEndFromLong(long)
     * @see #packRangeInLong(int, int)
     * @hide
     */
    @UnsupportedAppUsage
    public static int unpackRangeStartFromLong(long range) {
        return (int) (range >>> 32);
    }

    /**
     * Get the end value from a range packed in a long by {@link #packRangeInLong(int, int)}
     * @see #unpackRangeStartFromLong(long)
     * @see #packRangeInLong(int, int)
     * @hide
     */
    @UnsupportedAppUsage
    public static int unpackRangeEndFromLong(long range) {
        return (int) (range & 0x00000000FFFFFFFFL);
    }

    /**
     * Simple alternative to {@link String#format} which purposefully supports
     * only a small handful of substitutions to improve execution speed.
     * Benchmarking reveals this optimized alternative performs 6.5x faster for
     * a typical format string.
     * <p>
     * Below is a summary of the limited grammar supported by this method; if
     * you need advanced features, please continue using {@link String#format}.
     * <ul>
     * <li>{@code %b} for {@code boolean}
     * <li>{@code %c} for {@code char}
     * <li>{@code %d} for {@code int} or {@code long}
     * <li>{@code %f} for {@code float} or {@code double}
     * <li>{@code %s} for {@code String}
     * <li>{@code %x} for hex representation of {@code int} or {@code long}
     * <li>{@code %%} for literal {@code %}
     * <li>{@code %04d} style grammar to specify the argument width, such as
     * {@code %04d} to prefix an {@code int} with zeros or {@code %10b} to
     * prefix a {@code boolean} with spaces
     * </ul>
     *
     * @throws IllegalArgumentException if the format string or arguments don't
     *             match the supported grammar described above.
     * @hide
     */
    public static @NonNull String formatSimple(@NonNull String format, Object... args) {
        final StringBuilder sb = new StringBuilder(format);
        int j = 0;
        for (int i = 0; i < sb.length(); ) {
            if (sb.charAt(i) == '%') {
                char code = sb.charAt(i + 1);

                // Decode any argument width request
                char prefixChar = '\0';
                int prefixLen = 0;
                int consume = 2;
                while ('0' <= code && code <= '9') {
                    if (prefixChar == '\0') {
                        prefixChar = (code == '0') ? '0' : ' ';
                    }
                    prefixLen *= 10;
                    prefixLen += Character.digit(code, 10);
                    consume += 1;
                    code = sb.charAt(i + consume - 1);
                }

                final String repl;
                switch (code) {
                    case 'b': {
                        if (j == args.length) {
                            throw new IllegalArgumentException("Too few arguments");
                        }
                        final Object arg = args[j++];
                        if (arg instanceof Boolean) {
                            repl = Boolean.toString((boolean) arg);
                        } else {
                            repl = Boolean.toString(arg != null);
                        }
                        break;
                    }
                    case 'c':
                    case 'd':
                    case 'f':
                    case 's': {
                        if (j == args.length) {
                            throw new IllegalArgumentException("Too few arguments");
                        }
                        final Object arg = args[j++];
                        repl = String.valueOf(arg);
                        break;
                    }
                    case 'x': {
                        if (j == args.length) {
                            throw new IllegalArgumentException("Too few arguments");
                        }
                        final Object arg = args[j++];
                        if (arg instanceof Integer) {
                            repl = Integer.toHexString((int) arg);
                        } else if (arg instanceof Long) {
                            repl = Long.toHexString((long) arg);
                        } else {
                            throw new IllegalArgumentException(
                                    "Unsupported hex type " + arg.getClass());
                        }
                        break;
                    }
                    case '%': {
                        repl = "%";
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException("Unsupported format code " + code);
                    }
                }

                sb.replace(i, i + consume, repl);

                // Apply any argument width request
                final int prefixInsert = (prefixChar == '0' && repl.charAt(0) == '-') ? 1 : 0;
                for (int k = repl.length(); k < prefixLen; k++) {
                    sb.insert(i + prefixInsert, prefixChar);
                }
                i += Math.max(repl.length(), prefixLen);
            } else {
                i++;
            }
        }
        if (j != args.length) {
            throw new IllegalArgumentException("Too many arguments");
        }
        return sb.toString();
    }

    /**
     * Prepends {@code start} and appends {@code end} to a given {@link StringBuilder}
     *
     * @hide
     */
    public static void wrap(StringBuilder builder, String start, String end) {
        builder.insert(0, start);
        builder.append(end);
    }

    /**
     * Intent size limitations prevent sending over a megabyte of data. Limit
     * text length to 100K characters - 200KB.
     */
    private static final int PARCEL_SAFE_TEXT_LENGTH = 100000;

    /**
     * Trims the text to {@link #PARCEL_SAFE_TEXT_LENGTH} length. Returns the string as it is if
     * the length() is smaller than {@link #PARCEL_SAFE_TEXT_LENGTH}. Used for text that is parceled
     * into a {@link Parcelable}.
     *
     * @hide
     */
    @Nullable
    public static <T extends CharSequence> T trimToParcelableSize(@Nullable T text) {
        return trimToSize(text, PARCEL_SAFE_TEXT_LENGTH);
    }

    /**
     * Trims the text to {@code size} length. Returns the string as it is if the length() is
     * smaller than {@code size}. If chars at {@code size-1} and {@code size} is a surrogate
     * pair, returns a CharSequence of length {@code size-1}.
     *
     * @param size length of the result, should be greater than 0
     *
     * @hide
     */
    @Nullable
    public static <T extends CharSequence> T trimToSize(@Nullable T text,
            @IntRange(from = 1) int size) {
        Preconditions.checkArgument(size > 0);
        if (TextUtils.isEmpty(text) || text.length() <= size) return text;
        if (Character.isHighSurrogate(text.charAt(size - 1))
                && Character.isLowSurrogate(text.charAt(size))) {
            size = size - 1;
        }
        return (T) text.subSequence(0, size);
    }

    /**
     * Trims the {@code text} to the first {@code size} characters and adds an ellipsis if the
     * resulting string is shorter than the input. This will result in an output string which is
     * longer than {@code size} for most inputs.
     *
     * @param size length of the result, should be greater than 0
     *
     * @hide
     */
    @Nullable
    public static <T extends CharSequence> T trimToLengthWithEllipsis(@Nullable T text,
            @IntRange(from = 1) int size) {
        T trimmed = trimToSize(text, size);
        if (text != null && trimmed.length() < text.length()) {
            trimmed = (T) (trimmed.toString() + "...");
        }
        return trimmed;
    }

    /** @hide */
    public static boolean isNewline(int codePoint) {
        int type = Character.getType(codePoint);
        return type == Character.PARAGRAPH_SEPARATOR || type == Character.LINE_SEPARATOR
                || codePoint == LINE_FEED_CODE_POINT;
    }

    /** @hide */
    public static boolean isWhitespace(int codePoint) {
        return Character.isWhitespace(codePoint) || codePoint == NBSP_CODE_POINT;
    }

    /** @hide */
    public static boolean isWhitespaceExceptNewline(int codePoint) {
        return isWhitespace(codePoint) && !isNewline(codePoint);
    }

    /** @hide */
    public static boolean isPunctuation(int codePoint) {
        int type = Character.getType(codePoint);
        return type == Character.CONNECTOR_PUNCTUATION
                || type == Character.DASH_PUNCTUATION
                || type == Character.END_PUNCTUATION
                || type == Character.FINAL_QUOTE_PUNCTUATION
                || type == Character.INITIAL_QUOTE_PUNCTUATION
                || type == Character.OTHER_PUNCTUATION
                || type == Character.START_PUNCTUATION;
    }

    /** @hide */
    @Nullable
    public static String withoutPrefix(@Nullable String prefix, @Nullable String str) {
        if (prefix == null || str == null) return str;
        return str.startsWith(prefix) ? str.substring(prefix.length()) : str;
    }

    /**
     * A special string manipulation class. Just records removals and executes the when onString()
     * is called.
     */
    private static class StringWithRemovedChars {
        /** The original string */
        private final String mOriginal;

        /**
         * One bit per char in string. If bit is set, character needs to be removed. If whole
         * bit field is not initialized nothing needs to be removed.
         */
        private BitSet mRemovedChars;

        StringWithRemovedChars(@NonNull String original) {
            mOriginal = original;
        }

        /**
         * Mark all chars in a range {@code [firstRemoved - firstNonRemoved[} (not including
         * firstNonRemoved) as removed.
         */
        void removeRange(int firstRemoved, int firstNonRemoved) {
            if (mRemovedChars == null) {
                mRemovedChars = new BitSet(mOriginal.length());
            }

            mRemovedChars.set(firstRemoved, firstNonRemoved);
        }

        /**
         * Remove all characters before {@code firstNonRemoved}.
         */
        void removeAllCharBefore(int firstNonRemoved) {
            if (mRemovedChars == null) {
                mRemovedChars = new BitSet(mOriginal.length());
            }

            mRemovedChars.set(0, firstNonRemoved);
        }

        /**
         * Remove all characters after and including {@code firstRemoved}.
         */
        void removeAllCharAfter(int firstRemoved) {
            if (mRemovedChars == null) {
                mRemovedChars = new BitSet(mOriginal.length());
            }

            mRemovedChars.set(firstRemoved, mOriginal.length());
        }

        @Override
        public String toString() {
            // Common case, no chars removed
            if (mRemovedChars == null) {
                return mOriginal;
            }

            StringBuilder sb = new StringBuilder(mOriginal.length());
            for (int i = 0; i < mOriginal.length(); i++) {
                if (!mRemovedChars.get(i)) {
                    sb.append(mOriginal.charAt(i));
                }
            }

            return sb.toString();
        }

        /**
         * Return length or the original string
         */
        int length() {
            return mOriginal.length();
        }

        /**
         * Return codePoint of original string at a certain {@code offset}
         */
        int codePointAt(int offset) {
            return mOriginal.codePointAt(offset);
        }
    }

    private static Object sLock = new Object();

    private static char[] sTemp = null;
}
