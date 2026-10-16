package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.function.CLibrary;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;
import org.graalvm.word.UnsignedWord;

@CContext(BionicDirectives.class)
@CLibrary(value = "androidgraal", requireStatic = true)
public final class Malloc {

    private Malloc() {
    }

    @CStruct(addStructKeyword = true)
    public interface mallinfo extends PointerBase {
        @CField
        UnsignedWord usmblks();

        @CField
        UnsignedWord uordblks();

        @CField
        UnsignedWord fordblks();
    }

    /** A {@code @CFunction} cannot return a struct by value. */
    @CFunction("androidgraal_mallinfo")
    public static native void mallinfo(mallinfo info);
}
