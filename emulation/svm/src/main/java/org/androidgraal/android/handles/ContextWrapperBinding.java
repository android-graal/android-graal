// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.handles;

import android.content.Context;
import android.content.ContextWrapper;
import java.lang.ref.Reference;
import org.androidgraal.svm.Art;
import org.androidgraal.svm.jni.JNI;
import org.graalvm.nativeimage.StackValue;

public final class ContextWrapperBinding {
    private static JNI.JClass endPoints;

    private static JNI.JMethodID getBaseContextMethod;

    private ContextWrapperBinding() {
    }

    public static void initialize(JNI.JNIEnv env) {
        endPoints = Art.findClass(env, "org/androidgraal/android/art/handles/ContextWrapperEndPoints");
    }

    public static Context getBaseContext(ContextWrapper handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getBaseContextMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getBaseContext", "(J)J");
            getBaseContextMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        long r = Art.callLong(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return (Context) HandleClasses.receive(r);
    }
}
