package android.os;

/**
 * A simple pattern matcher, which is safe to use on untrusted data: it does
 * not provide full reg-exp support, only simple globbing that can not be
 * used maliciously.
 */
public final class PatternMatcher implements Parcelable {
    /**
     * Pattern type: the given pattern is interpreted with a regular
     * expression-like syntax for matching against the string it is tested
     * against. Supported tokens include dot ({@code .}) and sets ({@code [...]})
     * with full support for character ranges and the not ({@code ^}) modifier.
     * Supported modifiers include star ({@code *}) for zero-or-more, plus ({@code +})
     * for one-or-more and full range ({@code {...}}) support. This is a simple
     * evaluation implementation in which matching is done against the pattern in
     * real time with no backtracking support.
     */
    public static final int PATTERN_ADVANCED_GLOB = 3;

    /**
     * Pattern type: the given pattern must exactly match the string it is
     * tested against.
     */
    public static final int PATTERN_LITERAL = 0;

    /**
     * Pattern type: the given pattern must match the
     * beginning of the string it is tested against.
     */
    public static final int PATTERN_PREFIX = 1;

    /**
     * Pattern type: the given pattern is interpreted with a
     * simple glob syntax for matching against the string it is tested against.
     * In this syntax, you can use the '*' character to match against zero or
     * more occurrences of the character immediately before.  If the
     * character before it is '.' it will match any character.  The character
     * '\' can be used as an escape.  This essentially provides only the '*'
     * wildcard part of a normal regexp.
     */
    public static final int PATTERN_SIMPLE_GLOB = 2;

    /**
     * Pattern type: the given pattern must match the
     * end of the string it is tested against.
     */
    public static final int PATTERN_SUFFIX = 4;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private PatternMatcher() {
    }
}
