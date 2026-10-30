package org.androidgraal.svm;

import org.androidgraal.shared.Blocks;
import org.androidgraal.shared.Handles;
import org.androidgraal.shared.MarshalledException;
import org.androidgraal.shared.ThrowableMarshaller;
import org.androidgraal.svm.jni.JNI.JClass;
import org.androidgraal.svm.jni.JNI.JMethodID;
import org.androidgraal.svm.jni.JNI.JNIEnv;
import org.androidgraal.svm.jni.JNI.JObject;
import org.androidgraal.svm.jni.JNI.JValue;
import org.graalvm.nativeimage.StackValue;
import org.graalvm.nativeimage.c.type.CTypeConversion;
import org.graalvm.word.WordFactory;

public final class Art {

    private static final ThreadLocal<ThreadData> THREADS = new ThreadLocal<>();
    private static final Handles HANDLES = new Handles(1, Art::onProxyReleased);

    private static JClass image;
    private static JMethodID setPendingException;
    private static JMethodID handleUncaughtException;
    private static JMethodID dropExport;

    private Art() {
    }

    public static void initialize(JNIEnv env) {
        image = findClass(env, "org/androidgraal/art/Image");
        setPendingException = staticMethod(env, image, "setPendingException", "(Ljava/nio/ByteBuffer;)V");
        handleUncaughtException = staticMethod(env, image, "handleUncaughtException", "(Ljava/nio/ByteBuffer;)V");
        dropExport = staticMethod(env, image, "dropExport", "(J)V");
    }

    /**
     * The calling thread's data. A thread ART did not start is attached to ART as a daemon on the first call, until it
     * exits.
     */
    public static ThreadData thread() {
        ThreadData known = THREADS.get();
        if (known != null) {
            return known;
        }
        JNIEnv env;
        try (CTypeConversion.CCharPointerHolder name = CTypeConversion.toCString(Thread.currentThread().getName())) {
            env = Threads.env(name.get());
        }
        if (env.isNull()) {
            throw new IllegalStateException("attaching " + Thread.currentThread().getName() + " to ART failed");
        }
        ThreadData thread = new ThreadData(env);
        THREADS.set(thread);
        return thread;
    }

    public static Handles handles() {
        return HANDLES;
    }

    public static void callVoid(ThreadData thread, JClass clazz, JMethodID method, JValue args) {
        thread.env.getFunctions().getCallStaticVoidMethodA().call(thread.env, clazz, method, args);
        thread.throwPendingException();
    }

    public static boolean callBoolean(ThreadData thread, JClass clazz, JMethodID method, JValue args) {
        boolean result = thread.env.getFunctions().getCallStaticBooleanMethodA().call(thread.env, clazz, method, args);
        thread.throwPendingException();
        return result;
    }

    public static byte callByte(ThreadData thread, JClass clazz, JMethodID method, JValue args) {
        byte result = thread.env.getFunctions().getCallStaticByteMethodA().call(thread.env, clazz, method, args);
        thread.throwPendingException();
        return result;
    }

    public static char callChar(ThreadData thread, JClass clazz, JMethodID method, JValue args) {
        char result = thread.env.getFunctions().getCallStaticCharMethodA().call(thread.env, clazz, method, args);
        thread.throwPendingException();
        return result;
    }

    public static short callShort(ThreadData thread, JClass clazz, JMethodID method, JValue args) {
        short result = thread.env.getFunctions().getCallStaticShortMethodA().call(thread.env, clazz, method, args);
        thread.throwPendingException();
        return result;
    }

    public static int callInt(ThreadData thread, JClass clazz, JMethodID method, JValue args) {
        int result = thread.env.getFunctions().getCallStaticIntMethodA().call(thread.env, clazz, method, args);
        thread.throwPendingException();
        return result;
    }

    public static long callLong(ThreadData thread, JClass clazz, JMethodID method, JValue args) {
        long result = thread.env.getFunctions().getCallStaticLongMethodA().call(thread.env, clazz, method, args);
        thread.throwPendingException();
        return result;
    }

    public static float callFloat(ThreadData thread, JClass clazz, JMethodID method, JValue args) {
        float result = thread.env.getFunctions().getCallStaticFloatMethodA().call(thread.env, clazz, method, args);
        thread.throwPendingException();
        return result;
    }

    public static double callDouble(ThreadData thread, JClass clazz, JMethodID method, JValue args) {
        double result = thread.env.getFunctions().getCallStaticDoubleMethodA().call(thread.env, clazz, method, args);
        thread.throwPendingException();
        return result;
    }

    public static JObject callObject(ThreadData thread, JClass clazz, JMethodID method, JValue args) {
        JObject result = thread.env.getFunctions().getCallStaticObjectMethodA().call(thread.env, clazz, method, args);
        thread.throwPendingException();
        return result;
    }

    /**
     * Raises {@code t} on the ART side, once the image side returns, or tries to hand it to ART's uncaught exception
     * handler when it cannot. Never throws.
     */
    public static void raiseException(JNIEnv env, Throwable t) {
        try {
            long address = writeBlock(marshalException(t));
            JObject view = view(env, address, Transport.getBlockSize(address));
            JValue args = StackValue.get(1, JValue.class);
            args.addressOf(0).setJObject(view);
            env.getFunctions().getCallStaticVoidMethodA().call(env, image, setPendingException, args);
            deleteLocal(env, view);
        } catch (Throwable e) {
            handleUncaughtException(env, t);
        }
    }

    private static void handleUncaughtException(JNIEnv env, Throwable t) {
        t.printStackTrace();
        try {
            long address = writeBlock(marshalException(t));
            JObject view = view(env, address, Transport.getBlockSize(address));
            JValue args = StackValue.get(1, JValue.class);
            args.addressOf(0).setJObject(view);
            env.getFunctions().getCallStaticVoidMethodA().call(env, image, handleUncaughtException, args);
            deleteLocal(env, view);
            Transport.free(address);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * Installs a {@code UncaughtExceptionHandler} that forwards all uncaught exceptions to ART, if none is already
     * installed
     */
    public static void installUncaughtExceptionHandler() {
        if (Thread.getDefaultUncaughtExceptionHandler() == null) {
            Thread.setDefaultUncaughtExceptionHandler((_, t) -> handleUncaughtException(thread().env, t));
        }
    }

    /** A global reference to ART's class {@code name}. Only a native method of an app class finds app classes. */
    public static JClass findClass(JNIEnv env, String name) {
        JClass local;
        try (CTypeConversion.CCharPointerHolder n = CTypeConversion.toCString(name)) {
            local = env.getFunctions().getFindClass().call(env, n.get());
        }
        if (local.isNull()) {
            clearException(env);
            throw new IllegalStateException("no class " + name + " on ART");
        }
        JClass global = (JClass) newGlobal(env, local);
        deleteLocal(env, local);
        return global;
    }

    public static JMethodID staticMethod(JNIEnv env, JClass clazz, String name, String signature) {
        JMethodID method;
        try (CTypeConversion.CCharPointerHolder n = CTypeConversion.toCString(name);
                CTypeConversion.CCharPointerHolder s = CTypeConversion.toCString(signature)) {
            method = env.getFunctions().getGetStaticMethodID().call(env, clazz, n.get(), s.get());
        }
        if (method.isNull()) {
            clearException(env);
            throw new IllegalStateException("no static method " + name + signature + " on ART");
        }
        return method;
    }

    public static void deleteLocal(JNIEnv env, JObject reference) {
        env.getFunctions().getDeleteLocalRef().call(env, reference);
    }

    public static JObject newGlobal(JNIEnv env, JObject reference) {
        return env.getFunctions().getNewGlobalRef().call(env, reference);
    }

    public static void deleteGlobal(JNIEnv env, JObject reference) {
        env.getFunctions().getDeleteGlobalRef().call(env, reference);
    }

    public static long address(JNIEnv env, JObject view) {
        if (view.isNull()) {
            return 0;
        }
        return env.getFunctions().getGetDirectBufferAddress().call(env, view).rawValue();
    }

    /** A new local reference to a direct {@code ByteBuffer}. */
    public static JObject view(JNIEnv env, long address, int bytes) {
        JObject view = env.getFunctions().getNewDirectByteBuffer().call(env, WordFactory.pointer(address), bytes);
        if (view.isNull()) {
            clearException(env);
            throw new OutOfMemoryError("NewDirectByteBuffer of " + bytes + " bytes failed");
        }
        return view;
    }

    private static void onProxyReleased(long id) {
        JValue args = StackValue.get(1, JValue.class);
        args.addressOf(0).setLong(id);
        callVoid(thread(), image, dropExport, args);
    }

    private static void clearException(JNIEnv env) {
        env.getFunctions().getExceptionClear().call(env);
    }

    private static long writeBlock(byte[] bytes) {
        long address = Transport.allocBlock(bytes.length, 1);
        Transport.write(bytes, address + Blocks.HEADER);
        return address;
    }

    private static byte[] marshalException(Throwable t) {
        try {
            return ThrowableMarshaller.write(t);
        } catch (Throwable e) {
            String name = t.getClass().getName();
            return ThrowableMarshaller.write(new IllegalStateException(name + " could not be marshalled: " + e));
        }
    }

    public static final class ThreadData {

        public final JNIEnv env;

        /** The serialized exception ART threw on this thread, 0 when none is pending. */
        public long pendingException;

        private ThreadData(JNIEnv env) {
            this.env = env;
        }

        /** Throws what ART threw during the last crossing of this thread, as a {@link MarshalledException}. */
        public void throwPendingException() {
            long block = pendingException;
            if (block != 0) {
                pendingException = 0;
                byte[] bytes = Transport.readBlock(block);
                Transport.free(block);
                throw ThrowableMarshaller.read(bytes, Art.class, ThreadData.class);
            }
        }
    }
}
