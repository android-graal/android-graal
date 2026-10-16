package org.androidgraal.android;

import com.oracle.svm.shared.AlwaysInline;
import org.graalvm.nativeimage.c.struct.SizeOf;
import org.graalvm.nativeimage.c.type.WordPointer;
import org.graalvm.word.PointerBase;
import org.graalvm.word.SignedWord;
import org.graalvm.word.WordFactory;

public final class Words {

    private Words() {
    }

    @AlwaysInline("folds to a build-time constant")
    public static boolean is64Bit() {
        return SizeOf.get(WordPointer.class) == Long.BYTES;
    }

    /** {@code value} sign-extended, where {@code rawValue()} zero-extends a word narrower than a long. */
    public static long signExtended(SignedWord value) {
        return is64Bit() ? value.rawValue() : (int) value.rawValue();
    }

    /** {@link WordFactory#signed(long)}, throwing instead of truncating a value outside a 32-bit word. */
    public static SignedWord checkedSigned(long value) {
        if (!is64Bit() && (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE)) {
            throw new IllegalArgumentException(value + " does not fit in a signed 32-bit word");
        }
        return WordFactory.signed(value);
    }

    /**
     * {@link WordFactory#pointer(long)}, throwing for a value above a 32-bit word. A negative one passes, as on
     * Android.
     */
    public static PointerBase checkedPointer(long value) {
        if (!is64Bit() && value > 0xFFFF_FFFFL) {
            throw new IllegalArgumentException(value + " does not fit in a 32-bit pointer");
        }
        return WordFactory.pointer(value);
    }
}
