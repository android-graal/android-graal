package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.CLibrary;
import org.graalvm.nativeimage.c.type.CIntPointer;
import org.graalvm.nativeimage.c.type.WordPointer;
import org.graalvm.word.PointerBase;

@CContext(LooperDirectives.class)
@CLibrary("android")
public final class ALooper {

    private ALooper() {
    }

    @CConstant
    public static native int ALOOPER_EVENT_ERROR();

    @CConstant
    public static native int ALOOPER_EVENT_HANGUP();

    @CConstant
    public static native int ALOOPER_EVENT_INPUT();

    @CConstant
    public static native int ALOOPER_EVENT_INVALID();

    @CConstant
    public static native int ALOOPER_EVENT_OUTPUT();

    @CConstant
    public static native int ALOOPER_POLL_CALLBACK();

    @CFunction
    public static native PointerBase ALooper_prepare(int opts);

    @CFunction
    public static native void ALooper_acquire(PointerBase looper);

    @CFunction
    public static native void ALooper_release(PointerBase looper);

    @CFunction
    public static native int ALooper_pollOnce(int timeoutMillis, CIntPointer outFd, CIntPointer outEvents,
            WordPointer outData);

    @CFunction
    public static native void ALooper_wake(PointerBase looper);

    @CFunction
    public static native int ALooper_addFd(PointerBase looper, int fd, int ident, int events, CFunctionPointer callback,
            PointerBase data);

    @CFunction
    public static native int ALooper_removeFd(PointerBase looper, int fd);
}
