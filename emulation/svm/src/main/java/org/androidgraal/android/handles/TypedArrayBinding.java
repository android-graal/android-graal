// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.handles;

import android.content.res.TypedArray;
import android.os.Build;
import java.lang.ref.Reference;
import org.androidgraal.shared.Blocks;
import org.androidgraal.svm.Art;
import org.androidgraal.svm.Transport;
import org.androidgraal.svm.jni.JNI;
import org.graalvm.nativeimage.StackValue;

public final class TypedArrayBinding {
    private static JNI.JClass endPoints;

    private static JNI.JClass endPoints22;

    private static JNI.JClass endPoints29;

    private static JNI.JClass endPoints31;

    private static JNI.JMethodID closeMethod;

    private static JNI.JMethodID getBooleanMethod;

    private static JNI.JMethodID getChangingConfigurationsMethod;

    private static JNI.JMethodID getColorMethod;

    private static JNI.JMethodID getDimensionMethod;

    private static JNI.JMethodID getDimensionPixelOffsetMethod;

    private static JNI.JMethodID getDimensionPixelSizeMethod;

    private static JNI.JMethodID getFloatMethod;

    private static JNI.JMethodID getFractionMethod;

    private static JNI.JMethodID getIndexMethod;

    private static JNI.JMethodID getIndexCountMethod;

    private static JNI.JMethodID getIntMethod;

    private static JNI.JMethodID getIntegerMethod;

    private static JNI.JMethodID getLayoutDimension$0Method;

    private static JNI.JMethodID getLayoutDimension$1Method;

    private static JNI.JMethodID getNonResourceStringMethod;

    private static JNI.JMethodID getPositionDescriptionMethod;

    private static JNI.JMethodID getResourceIdMethod;

    private static JNI.JMethodID getSourceResourceIdMethod;

    private static JNI.JMethodID getStringMethod;

    private static JNI.JMethodID getTypeMethod;

    private static JNI.JMethodID hasValueMethod;

    private static JNI.JMethodID hasValueOrEmptyMethod;

    private static JNI.JMethodID lengthMethod;

    private static JNI.JMethodID recycleMethod;

    private static JNI.JMethodID toStringMethod;

    private TypedArrayBinding() {
    }

    public static void initialize(JNI.JNIEnv env) {
        endPoints = Art.findClass(env, "org/androidgraal/android/art/handles/TypedArrayEndPoints");
        if (Build.VERSION.SDK_INT >= 22) {
            endPoints22 = Art.findClass(env, "org/androidgraal/android/art/handles/TypedArrayEndPoints22");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            endPoints29 = Art.findClass(env, "org/androidgraal/android/art/handles/TypedArrayEndPoints29");
        }
        if (Build.VERSION.SDK_INT >= 31) {
            endPoints31 = Art.findClass(env, "org/androidgraal/android/art/handles/TypedArrayEndPoints31");
        }
    }

    public static void close(TypedArray handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = closeMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints31, "close", "(J)V");
            closeMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        Art.callVoid(thread, endPoints31, method, args);
        Reference.reachabilityFence(handle);
    }

    public static boolean getBoolean(TypedArray handle, int a0, boolean a1) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getBooleanMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getBoolean", "(JIZ)Z");
            getBooleanMethod = method;
        }
        JNI.JValue args = StackValue.get(3, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        args.addressOf(2).setBoolean(a1);
        boolean r = Art.callBoolean(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static int getChangingConfigurations(TypedArray handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getChangingConfigurationsMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getChangingConfigurations", "(J)I");
            getChangingConfigurationsMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static int getColor(TypedArray handle, int a0, int a1) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getColorMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getColor", "(JII)I");
            getColorMethod = method;
        }
        JNI.JValue args = StackValue.get(3, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        args.addressOf(2).setInt(a1);
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static float getDimension(TypedArray handle, int a0, float a1) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getDimensionMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getDimension", "(JIF)F");
            getDimensionMethod = method;
        }
        JNI.JValue args = StackValue.get(3, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        args.addressOf(2).setFloat(a1);
        float r = Art.callFloat(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static int getDimensionPixelOffset(TypedArray handle, int a0, int a1) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getDimensionPixelOffsetMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getDimensionPixelOffset", "(JII)I");
            getDimensionPixelOffsetMethod = method;
        }
        JNI.JValue args = StackValue.get(3, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        args.addressOf(2).setInt(a1);
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static int getDimensionPixelSize(TypedArray handle, int a0, int a1) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getDimensionPixelSizeMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getDimensionPixelSize", "(JII)I");
            getDimensionPixelSizeMethod = method;
        }
        JNI.JValue args = StackValue.get(3, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        args.addressOf(2).setInt(a1);
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static float getFloat(TypedArray handle, int a0, float a1) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getFloatMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getFloat", "(JIF)F");
            getFloatMethod = method;
        }
        JNI.JValue args = StackValue.get(3, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        args.addressOf(2).setFloat(a1);
        float r = Art.callFloat(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static float getFraction(TypedArray handle, int a0, int a1, int a2, float a3) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getFractionMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getFraction", "(JIIIF)F");
            getFractionMethod = method;
        }
        JNI.JValue args = StackValue.get(5, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        args.addressOf(2).setInt(a1);
        args.addressOf(3).setInt(a2);
        args.addressOf(4).setFloat(a3);
        float r = Art.callFloat(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static int getIndex(TypedArray handle, int a0) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getIndexMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getIndex", "(JI)I");
            getIndexMethod = method;
        }
        JNI.JValue args = StackValue.get(2, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static int getIndexCount(TypedArray handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getIndexCountMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getIndexCount", "(J)I");
            getIndexCountMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static int getInt(TypedArray handle, int a0, int a1) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getIntMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getInt", "(JII)I");
            getIntMethod = method;
        }
        JNI.JValue args = StackValue.get(3, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        args.addressOf(2).setInt(a1);
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static int getInteger(TypedArray handle, int a0, int a1) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getIntegerMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getInteger", "(JII)I");
            getIntegerMethod = method;
        }
        JNI.JValue args = StackValue.get(3, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        args.addressOf(2).setInt(a1);
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static int getLayoutDimension$0(TypedArray handle, int a0, int a1) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getLayoutDimension$0Method;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getLayoutDimension$0", "(JII)I");
            getLayoutDimension$0Method = method;
        }
        JNI.JValue args = StackValue.get(3, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        args.addressOf(2).setInt(a1);
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static int getLayoutDimension$1(TypedArray handle, int a0, String a1) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getLayoutDimension$1Method;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getLayoutDimension$1", "(JIJI)I");
            getLayoutDimension$1Method = method;
        }
        int n1 = a1 == null ? Blocks.NULL : a1.length();
        int bytes = Blocks.size(n1, 2);
        long transport = a1 == null ? 0 : Transport.alloc(bytes);
        try {
            if (transport != 0) {
                Transport.setBlockHeader(transport, n1, 2);
                Transport.write(a1, transport + Blocks.HEADER);
            }
            JNI.JValue args = StackValue.get(4, JNI.JValue.class);
            args.addressOf(0).setLong(handle.foreignId());
            args.addressOf(1).setInt(a0);
            args.addressOf(2).setLong(transport);
            args.addressOf(3).setInt(bytes);
            int r = Art.callInt(thread, endPoints, method, args);
            Reference.reachabilityFence(handle);
            return r;
        } finally {
            if (transport != 0) {
                Transport.free(transport);
            }
        }
    }

    public static String getNonResourceString(TypedArray handle, int a0) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getNonResourceStringMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getNonResourceString", "(JI)Ljava/nio/ByteBuffer;");
            getNonResourceStringMethod = method;
        }
        JNI.JValue args = StackValue.get(2, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        JNI.JObject view = Art.callObject(thread, endPoints, method, args);
        long r = Art.address(thread.env, view);
        Art.deleteLocal(thread.env, view);
        Reference.reachabilityFence(handle);
        String result = Transport.readStringBlock(r);
        Transport.free(r);
        return result;
    }

    public static String getPositionDescription(TypedArray handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getPositionDescriptionMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getPositionDescription", "(J)Ljava/nio/ByteBuffer;");
            getPositionDescriptionMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        JNI.JObject view = Art.callObject(thread, endPoints, method, args);
        long r = Art.address(thread.env, view);
        Art.deleteLocal(thread.env, view);
        Reference.reachabilityFence(handle);
        String result = Transport.readStringBlock(r);
        Transport.free(r);
        return result;
    }

    public static int getResourceId(TypedArray handle, int a0, int a1) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getResourceIdMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getResourceId", "(JII)I");
            getResourceIdMethod = method;
        }
        JNI.JValue args = StackValue.get(3, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        args.addressOf(2).setInt(a1);
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static int getSourceResourceId(TypedArray handle, int a0, int a1) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getSourceResourceIdMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints29, "getSourceResourceId", "(JII)I");
            getSourceResourceIdMethod = method;
        }
        JNI.JValue args = StackValue.get(3, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        args.addressOf(2).setInt(a1);
        int r = Art.callInt(thread, endPoints29, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static String getString(TypedArray handle, int a0) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getStringMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getString", "(JI)Ljava/nio/ByteBuffer;");
            getStringMethod = method;
        }
        JNI.JValue args = StackValue.get(2, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        JNI.JObject view = Art.callObject(thread, endPoints, method, args);
        long r = Art.address(thread.env, view);
        Art.deleteLocal(thread.env, view);
        Reference.reachabilityFence(handle);
        String result = Transport.readStringBlock(r);
        Transport.free(r);
        return result;
    }

    public static int getType(TypedArray handle, int a0) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getTypeMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getType", "(JI)I");
            getTypeMethod = method;
        }
        JNI.JValue args = StackValue.get(2, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static boolean hasValue(TypedArray handle, int a0) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = hasValueMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "hasValue", "(JI)Z");
            hasValueMethod = method;
        }
        JNI.JValue args = StackValue.get(2, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        boolean r = Art.callBoolean(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static boolean hasValueOrEmpty(TypedArray handle, int a0) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = hasValueOrEmptyMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints22, "hasValueOrEmpty", "(JI)Z");
            hasValueOrEmptyMethod = method;
        }
        JNI.JValue args = StackValue.get(2, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        boolean r = Art.callBoolean(thread, endPoints22, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static int length(TypedArray handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = lengthMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "length", "(J)I");
            lengthMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static void recycle(TypedArray handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = recycleMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "recycle", "(J)V");
            recycleMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        Art.callVoid(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
    }

    public static String toString(TypedArray handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = toStringMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "toString", "(J)Ljava/nio/ByteBuffer;");
            toStringMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        JNI.JObject view = Art.callObject(thread, endPoints, method, args);
        long r = Art.address(thread.env, view);
        Art.deleteLocal(thread.env, view);
        Reference.reachabilityFence(handle);
        String result = Transport.readStringBlock(r);
        Transport.free(r);
        return result;
    }
}
