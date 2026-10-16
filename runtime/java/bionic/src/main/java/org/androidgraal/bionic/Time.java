package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;
import org.graalvm.word.SignedWord;

@CContext(BionicDirectives.class)
public final class Time {

    private Time() {
    }

    @CConstant
    public static native int CLOCK_MONOTONIC();

    @CStruct(addStructKeyword = true)
    public interface timespec extends PointerBase {
        @CField
        SignedWord tv_sec();

        @CField
        SignedWord tv_nsec();
    }

    @CStruct(addStructKeyword = true)
    public interface timeval extends PointerBase {
        @CField
        SignedWord tv_sec();

        @CField
        void tv_sec(SignedWord value);

        @CField
        SignedWord tv_usec();

        @CField
        void tv_usec(SignedWord value);
    }

    @CFunction
    public static native int clock_gettime(int clock, timespec time);
}
