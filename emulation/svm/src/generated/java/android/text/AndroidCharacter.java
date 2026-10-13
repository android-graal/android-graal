package android.text;

/**
 * AndroidCharacter exposes some character properties that used to be not
 * easily accessed from java.lang.Character, but are now available in ICU.
 * @deprecated Use various methods from {@link android.icu.lang.UCharacter}, instead.
 */
@Deprecated
public final class AndroidCharacter {
    @Deprecated
    public static final int EAST_ASIAN_WIDTH_AMBIGUOUS = 1;

    @Deprecated
    public static final int EAST_ASIAN_WIDTH_FULL_WIDTH = 3;

    @Deprecated
    public static final int EAST_ASIAN_WIDTH_HALF_WIDTH = 2;

    @Deprecated
    public static final int EAST_ASIAN_WIDTH_NARROW = 4;

    @Deprecated
    public static final int EAST_ASIAN_WIDTH_NEUTRAL = 0;

    @Deprecated
    public static final int EAST_ASIAN_WIDTH_WIDE = 5;

    private AndroidCharacter() {
    }
}
