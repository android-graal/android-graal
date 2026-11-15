// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.handles;

import android.hardware.OverlayProperties;
import android.os.Build;
import java.lang.ref.Reference;
import org.androidgraal.svm.Art;
import org.androidgraal.svm.jni.JNI;
import org.graalvm.nativeimage.StackValue;

public final class OverlayPropertiesBinding {
    private static JNI.JClass endPoints35;

    private static JNI.JMethodID describeContentsMethod;

    private static JNI.JMethodID isCombinationSupportedMethod;

    private static JNI.JMethodID isMixedColorSpacesSupportedMethod;

    private OverlayPropertiesBinding() {
    }

    public static void initialize(JNI.JNIEnv env) {
        if (Build.VERSION.SDK_INT >= 35) {
            endPoints35 = Art.findClass(env, "org/androidgraal/android/art/handles/OverlayPropertiesEndPoints35");
        }
    }

    public static int describeContents(OverlayProperties handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = describeContentsMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints35, "describeContents", "(J)I");
            describeContentsMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        int r = Art.callInt(thread, endPoints35, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static boolean isCombinationSupported(OverlayProperties handle, int a0, int a1) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = isCombinationSupportedMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints35, "isCombinationSupported", "(JII)Z");
            isCombinationSupportedMethod = method;
        }
        JNI.JValue args = StackValue.get(3, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        args.addressOf(2).setInt(a1);
        boolean r = Art.callBoolean(thread, endPoints35, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static boolean isMixedColorSpacesSupported(OverlayProperties handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = isMixedColorSpacesSupportedMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints35, "isMixedColorSpacesSupported", "(J)Z");
            isMixedColorSpacesSupportedMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        boolean r = Art.callBoolean(thread, endPoints35, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }
}
