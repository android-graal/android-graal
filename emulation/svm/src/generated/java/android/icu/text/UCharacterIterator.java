package android.icu.text;

/**
 * Abstract class that defines an API for iteration on text objects.This is an interface for forward and backward
 * iteration and random access into a text object. Forward iteration is done with post-increment and backward iteration
 * is done with pre-decrement semantics, while the <code>java.text.CharacterIterator</code> interface methods provided
 * forward iteration with "pre-increment" and backward iteration with pre-decrement semantics. This API is more
 * efficient for forward iteration over code points. The other major difference is that this API can do both code unit
 * and code point iteration, <code>java.text.CharacterIterator</code> can only iterate over code units and is limited to
 * BMP (0 - 0xFFFF)
 *
 * @author Ram
 */
public final class UCharacterIterator {
    public static final int DONE = -1;

    private UCharacterIterator() {
    }
}
