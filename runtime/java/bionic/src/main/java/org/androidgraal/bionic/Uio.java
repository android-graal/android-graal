package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;
import org.graalvm.word.SignedWord;
import org.graalvm.word.UnsignedWord;

@CContext(BionicDirectives.class)
public final class Uio {

    private Uio() {
    }

    @CStruct(addStructKeyword = true)
    public interface iovec extends PointerBase {
        @CField
        PointerBase iov_base();

        @CField
        void iov_base(PointerBase value);

        @CField
        UnsignedWord iov_len();

        @CField
        void iov_len(UnsignedWord value);

        iovec addressOf(int index);
    }

    @CFunction
    public static native SignedWord readv(int fd, iovec vector, int count);

    @CFunction
    public static native SignedWord writev(int fd, iovec vector, int count);
}
