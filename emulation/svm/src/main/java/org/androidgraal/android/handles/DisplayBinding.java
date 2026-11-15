// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.handles;

import android.hardware.OverlayProperties;
import android.os.Build;
import android.view.Display;
import java.lang.ref.Reference;
import org.androidgraal.shared.Blocks;
import org.androidgraal.svm.Art;
import org.androidgraal.svm.Transport;
import org.androidgraal.svm.jni.JNI;
import org.graalvm.nativeimage.StackValue;

public final class DisplayBinding {
    private static JNI.JClass endPoints;

    private static JNI.JClass endPoints26;

    private static JNI.JClass endPoints30;

    private static JNI.JClass endPoints34;

    private static JNI.JClass endPoints35;

    private static JNI.JClass endPoints36;

    private static JNI.JMethodID getAppVsyncOffsetNanosMethod;

    private static JNI.JMethodID getDisplayIdMethod;

    private static JNI.JMethodID getFlagsMethod;

    private static JNI.JMethodID getHdrSdrRatioMethod;

    private static JNI.JMethodID getHeightMethod;

    private static JNI.JMethodID getHighestHdrSdrRatioMethod;

    private static JNI.JMethodID getNameMethod;

    private static JNI.JMethodID getOrientationMethod;

    private static JNI.JMethodID getOverlaySupportMethod;

    private static JNI.JMethodID getPixelFormatMethod;

    private static JNI.JMethodID getPresentationDeadlineNanosMethod;

    private static JNI.JMethodID getRefreshRateMethod;

    private static JNI.JMethodID getRotationMethod;

    private static JNI.JMethodID getStateMethod;

    private static JNI.JMethodID getSuggestedFrameRateMethod;

    private static JNI.JMethodID getSupportedRefreshRatesMethod;

    private static JNI.JMethodID getWidthMethod;

    private static JNI.JMethodID hasArrSupportMethod;

    private static JNI.JMethodID isHdrMethod;

    private static JNI.JMethodID isHdrSdrRatioAvailableMethod;

    private static JNI.JMethodID isMinimalPostProcessingSupportedMethod;

    private static JNI.JMethodID isValidMethod;

    private static JNI.JMethodID isWideColorGamutMethod;

    private static JNI.JMethodID toStringMethod;

    private DisplayBinding() {
    }

    public static void initialize(JNI.JNIEnv env) {
        endPoints = Art.findClass(env, "org/androidgraal/android/art/handles/DisplayEndPoints");
        if (Build.VERSION.SDK_INT >= 26) {
            endPoints26 = Art.findClass(env, "org/androidgraal/android/art/handles/DisplayEndPoints26");
        }
        if (Build.VERSION.SDK_INT >= 30) {
            endPoints30 = Art.findClass(env, "org/androidgraal/android/art/handles/DisplayEndPoints30");
        }
        if (Build.VERSION.SDK_INT >= 34) {
            endPoints34 = Art.findClass(env, "org/androidgraal/android/art/handles/DisplayEndPoints34");
        }
        if (Build.VERSION.SDK_INT >= 35) {
            endPoints35 = Art.findClass(env, "org/androidgraal/android/art/handles/DisplayEndPoints35");
        }
        if (Build.VERSION.SDK_INT >= 36) {
            endPoints36 = Art.findClass(env, "org/androidgraal/android/art/handles/DisplayEndPoints36");
        }
    }

    public static long getAppVsyncOffsetNanos(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getAppVsyncOffsetNanosMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getAppVsyncOffsetNanos", "(J)J");
            getAppVsyncOffsetNanosMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        long r = Art.callLong(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static int getDisplayId(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getDisplayIdMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getDisplayId", "(J)I");
            getDisplayIdMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static int getFlags(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getFlagsMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getFlags", "(J)I");
            getFlagsMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static float getHdrSdrRatio(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getHdrSdrRatioMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints34, "getHdrSdrRatio", "(J)F");
            getHdrSdrRatioMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        float r = Art.callFloat(thread, endPoints34, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static int getHeight(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getHeightMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getHeight", "(J)I");
            getHeightMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static float getHighestHdrSdrRatio(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getHighestHdrSdrRatioMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints36, "getHighestHdrSdrRatio", "(J)F");
            getHighestHdrSdrRatioMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        float r = Art.callFloat(thread, endPoints36, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static String getName(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getNameMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getName", "(J)Ljava/nio/ByteBuffer;");
            getNameMethod = method;
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

    public static int getOrientation(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getOrientationMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getOrientation", "(J)I");
            getOrientationMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static OverlayProperties getOverlaySupport(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getOverlaySupportMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints35, "getOverlaySupport", "(J)J");
            getOverlaySupportMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        long r = Art.callLong(thread, endPoints35, method, args);
        Reference.reachabilityFence(handle);
        return (OverlayProperties) HandleClasses.receive(r);
    }

    public static int getPixelFormat(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getPixelFormatMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getPixelFormat", "(J)I");
            getPixelFormatMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static long getPresentationDeadlineNanos(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getPresentationDeadlineNanosMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getPresentationDeadlineNanos", "(J)J");
            getPresentationDeadlineNanosMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        long r = Art.callLong(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static float getRefreshRate(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getRefreshRateMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getRefreshRate", "(J)F");
            getRefreshRateMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        float r = Art.callFloat(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static int getRotation(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getRotationMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getRotation", "(J)I");
            getRotationMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static int getState(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getStateMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getState", "(J)I");
            getStateMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static float getSuggestedFrameRate(Display handle, int a0) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getSuggestedFrameRateMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints36, "getSuggestedFrameRate", "(JI)F");
            getSuggestedFrameRateMethod = method;
        }
        JNI.JValue args = StackValue.get(2, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        float r = Art.callFloat(thread, endPoints36, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static float[] getSupportedRefreshRates(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getSupportedRefreshRatesMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getSupportedRefreshRates", "(J)Ljava/nio/ByteBuffer;");
            getSupportedRefreshRatesMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        JNI.JObject view = Art.callObject(thread, endPoints, method, args);
        long r = Art.address(thread.env, view);
        Art.deleteLocal(thread.env, view);
        Reference.reachabilityFence(handle);
        float[] result = r == 0 ? null : new float[Transport.getBlockLength(r)];
        Transport.read(r + Blocks.HEADER, result);
        Transport.free(r);
        return result;
    }

    public static int getWidth(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getWidthMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getWidth", "(J)I");
            getWidthMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static boolean hasArrSupport(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = hasArrSupportMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints36, "hasArrSupport", "(J)Z");
            hasArrSupportMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        boolean r = Art.callBoolean(thread, endPoints36, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static boolean isHdr(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = isHdrMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints26, "isHdr", "(J)Z");
            isHdrMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        boolean r = Art.callBoolean(thread, endPoints26, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static boolean isHdrSdrRatioAvailable(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = isHdrSdrRatioAvailableMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints34, "isHdrSdrRatioAvailable", "(J)Z");
            isHdrSdrRatioAvailableMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        boolean r = Art.callBoolean(thread, endPoints34, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static boolean isMinimalPostProcessingSupported(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = isMinimalPostProcessingSupportedMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints30, "isMinimalPostProcessingSupported", "(J)Z");
            isMinimalPostProcessingSupportedMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        boolean r = Art.callBoolean(thread, endPoints30, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static boolean isValid(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = isValidMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "isValid", "(J)Z");
            isValidMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        boolean r = Art.callBoolean(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static boolean isWideColorGamut(Display handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = isWideColorGamutMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints26, "isWideColorGamut", "(J)Z");
            isWideColorGamutMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        boolean r = Art.callBoolean(thread, endPoints26, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static String toString(Display handle) {
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
