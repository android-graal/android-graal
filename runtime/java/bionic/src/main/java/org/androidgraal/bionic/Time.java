package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;

@CContext(BionicDirectives.class)
public final class Time {

    private Time() {
    }

    @CConstant
    public static native int CLOCK_MONOTONIC();

    @CStruct(addStructKeyword = true)
    public interface timespec extends PointerBase {
        @CField
        long tv_sec();

        @CField
        long tv_nsec();
    }

    @CStruct(addStructKeyword = true)
    public interface timeval extends PointerBase {
        @CField
        long tv_sec();

        @CField
        void tv_sec(long value);

        @CField
        long tv_usec();

        @CField
        void tv_usec(long value);
    }

    @CFunction
    public static native int clock_gettime(int clock, timespec time);
}
