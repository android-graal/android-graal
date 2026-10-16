package org.androidgraal.svm;

import org.androidgraal.svm.jni.JNI.JNIEnv;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.function.CLibrary;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.UnsignedWord;

@CLibrary(value = "androidgraal", requireStatic = true)
final class Threads {

    private Threads() {
    }

    /**
     * The calling thread's {@code JNIEnv}, attached to ART as a daemon named {@code name} if it was not already.
     * Returns null on any failure.
     */
    @CFunction("androidgraal_env")
    public static native JNIEnv env(CCharPointer name);

    /** Called once before anything else here. Returns 0 or {@code pthread_key_create}'s errno. */
    @CFunction("androidgraal_init")
    public static native int init(JNIEnv env);

    /** Detaches {@code thread} from the isolate when the calling thread exits. */
    @CFunction("androidgraal_detach_thread_on_exit")
    public static native void detachThreadOnExit(IsolateThread thread);

    /** {@code bytes} of memory, freed when the calling thread exits. Returns null on failure. */
    @CFunction("androidgraal_thread_alloc")
    public static native VoidPointer threadAlloc(UnsignedWord bytes);
}
