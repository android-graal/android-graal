package org.androidgraal.svm;

import org.androidgraal.svm.jni.JNI.JClass;
import org.androidgraal.svm.jni.JNI.JNIEnv;
import org.androidgraal.svm.jni.JNI.JObject;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.function.CEntryPoint.Builtin;
import org.graalvm.nativeimage.c.function.CEntryPoint.IsolateContext;
import org.graalvm.nativeimage.c.function.CEntryPoint.IsolateThreadContext;
import org.graalvm.word.WordFactory;

final class Entry {

    private Entry() {
    }

    @CEntryPoint(name = "Java_org_androidgraal_art_Image_createIsolate", builtin = Builtin.CREATE_ISOLATE)
    private static native long createIsolate(JNIEnv env, JClass clazz);

    @CEntryPoint(name = "Java_org_androidgraal_art_Image_getIsolate", builtin = Builtin.GET_ISOLATE)
    private static native long getIsolate(JNIEnv env, JClass clazz, @IsolateThreadContext long thread);

    @CEntryPoint(name = "Java_org_androidgraal_art_Image_attachThread", builtin = Builtin.ATTACH_THREAD)
    private static native long attachThread(JNIEnv env, JClass clazz, @IsolateContext long isolate);

    @CEntryPoint(name = "Java_org_androidgraal_art_Image_initializeIsolate")
    private static void initializeIsolate(JNIEnv env, JClass clazz, @IsolateThreadContext long thread) {
        Art.initialize(env);
        try {
            int code = Threads.init(env);
            if (code != 0) {
                throw new IllegalStateException("pthread_key_create failed with " + code);
            }
            Art.installUncaughtExceptionHandler();
        } catch (Throwable t) {
            Art.raiseException(env, t);
        }
    }

    @CEntryPoint(name = "Java_org_androidgraal_art_Image_currentThread", builtin = Builtin.GET_CURRENT_THREAD)
    private static native long currentThread(JNIEnv env, JClass clazz, @IsolateContext long isolate);

    @CEntryPoint(name = "Java_org_androidgraal_art_Image_detachThreadOnExit")
    private static void detachThreadOnExit(JNIEnv env, JClass clazz, @IsolateThreadContext long thread) {
        try {
            Threads.detachThreadOnExit(WordFactory.pointer(thread));
        } catch (Throwable t) {
            Art.raiseException(env, t);
        }
    }

    @CEntryPoint(name = "Java_org_androidgraal_art_Image_dropExport")
    private static void dropExport(JNIEnv env, JClass clazz, @IsolateThreadContext long thread, long id) {
        try {
            Art.handles().drop(id);
        } catch (Throwable t) {
            Art.raiseException(env, t);
        }
    }

    @CEntryPoint(name = "Java_org_androidgraal_art_Transport_alloc")
    private static JObject alloc(JNIEnv env, JClass clazz, @IsolateThreadContext long thread, int bytes) {
        try {
            return Art.view(env, Transport.alloc(bytes), bytes);
        } catch (Throwable t) {
            Art.raiseException(env, t);
            return WordFactory.nullPointer();
        }
    }

    @CEntryPoint(name = "Java_org_androidgraal_art_Transport_free")
    private static void free(JNIEnv env, JClass clazz, @IsolateThreadContext long thread, JObject view) {
        try {
            Transport.free(Art.address(env, view));
        } catch (Throwable t) {
            Art.raiseException(env, t);
        }
    }

    @CEntryPoint(name = "Java_org_androidgraal_art_Transport_threadAlloc")
    private static long threadAlloc(JNIEnv env, JClass clazz, @IsolateThreadContext long thread, int bytes) {
        try {
            return Transport.threadAlloc(bytes);
        } catch (Throwable t) {
            Art.raiseException(env, t);
            return 0;
        }
    }

    @CEntryPoint(name = "Java_org_androidgraal_art_Transport_view")
    private static JObject view(JNIEnv env, JClass clazz, @IsolateThreadContext long thread, long address, int bytes) {
        try {
            return Art.view(env, address, bytes);
        } catch (Throwable t) {
            Art.raiseException(env, t);
            return WordFactory.nullPointer();
        }
    }

    @CEntryPoint(name = "Java_org_androidgraal_art_Transport_viewBlock")
    private static JObject viewBlock(JNIEnv env, JClass clazz, @IsolateThreadContext long thread, long address) {
        try {
            return Art.view(env, address, Transport.getBlockSize(address));
        } catch (Throwable t) {
            Art.raiseException(env, t);
            return WordFactory.nullPointer();
        }
    }

    @CEntryPoint(name = "Java_org_androidgraal_art_Image_setPendingException")
    private static void setPendingException(JNIEnv env, JClass clazz, @IsolateThreadContext long thread,
            JObject block) {
        try {
            Art.thread().pendingException = Art.address(env, block);
        } catch (Throwable t) {
            Art.raiseException(env, t);
        }
    }
}
