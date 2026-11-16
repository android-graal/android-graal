// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.handles;

import android.content.Context;
import android.os.Build;
import android.view.Display;
import java.lang.ref.Reference;
import org.androidgraal.shared.Blocks;
import org.androidgraal.svm.Art;
import org.androidgraal.svm.Transport;
import org.androidgraal.svm.jni.JNI;
import org.graalvm.nativeimage.StackValue;

public final class ContextBinding {
    private static JNI.JClass endPoints;

    private static JNI.JClass endPoints23;

    private static JNI.JClass endPoints24;

    private static JNI.JClass endPoints29;

    private static JNI.JClass endPoints30;

    private static JNI.JClass endPoints31;

    private static JNI.JClass endPoints33;

    private static JNI.JClass endPoints34;

    private static JNI.JMethodID checkCallingOrSelfPermissionMethod;

    private static JNI.JMethodID checkCallingPermissionMethod;

    private static JNI.JMethodID checkPermissionMethod;

    private static JNI.JMethodID checkSelfPermissionMethod;

    private static JNI.JMethodID clearWallpaperMethod;

    private static JNI.JMethodID createAttributionContextMethod;

    private static JNI.JMethodID createDeviceContextMethod;

    private static JNI.JMethodID createDeviceProtectedStorageContextMethod;

    private static JNI.JMethodID createDisplayContextMethod;

    private static JNI.JMethodID deleteDatabaseMethod;

    private static JNI.JMethodID deleteFileMethod;

    private static JNI.JMethodID deleteSharedPreferencesMethod;

    private static JNI.JMethodID enforceCallingOrSelfPermissionMethod;

    private static JNI.JMethodID enforceCallingPermissionMethod;

    private static JNI.JMethodID enforcePermissionMethod;

    private static JNI.JMethodID getApplicationContextMethod;

    private static JNI.JMethodID getAttributionTagMethod;

    private static JNI.JMethodID getColorMethod;

    private static JNI.JMethodID getDeviceIdMethod;

    private static JNI.JMethodID getDisplayMethod;

    private static JNI.JMethodID getOpPackageNameMethod;

    private static JNI.JMethodID getPackageCodePathMethod;

    private static JNI.JMethodID getPackageNameMethod;

    private static JNI.JMethodID getPackageResourcePathMethod;

    private static JNI.JMethodID getStringMethod;

    private static JNI.JMethodID getWallpaperDesiredMinimumHeightMethod;

    private static JNI.JMethodID getWallpaperDesiredMinimumWidthMethod;

    private static JNI.JMethodID isDeviceProtectedStorageMethod;

    private static JNI.JMethodID isRestrictedMethod;

    private static JNI.JMethodID isUiContextMethod;

    private static JNI.JMethodID moveDatabaseFromMethod;

    private static JNI.JMethodID moveSharedPreferencesFromMethod;

    private static JNI.JMethodID revokeSelfPermissionOnKillMethod;

    private static JNI.JMethodID setThemeMethod;

    private ContextBinding() {
    }

    public static void initialize(JNI.JNIEnv env) {
        endPoints = Art.findClass(env, "org/androidgraal/android/art/handles/ContextEndPoints");
        if (Build.VERSION.SDK_INT >= 23) {
            endPoints23 = Art.findClass(env, "org/androidgraal/android/art/handles/ContextEndPoints23");
        }
        if (Build.VERSION.SDK_INT >= 24) {
            endPoints24 = Art.findClass(env, "org/androidgraal/android/art/handles/ContextEndPoints24");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            endPoints29 = Art.findClass(env, "org/androidgraal/android/art/handles/ContextEndPoints29");
        }
        if (Build.VERSION.SDK_INT >= 30) {
            endPoints30 = Art.findClass(env, "org/androidgraal/android/art/handles/ContextEndPoints30");
        }
        if (Build.VERSION.SDK_INT >= 31) {
            endPoints31 = Art.findClass(env, "org/androidgraal/android/art/handles/ContextEndPoints31");
        }
        if (Build.VERSION.SDK_INT >= 33) {
            endPoints33 = Art.findClass(env, "org/androidgraal/android/art/handles/ContextEndPoints33");
        }
        if (Build.VERSION.SDK_INT >= 34) {
            endPoints34 = Art.findClass(env, "org/androidgraal/android/art/handles/ContextEndPoints34");
        }
    }

    public static int checkCallingOrSelfPermission(Context handle, String a0) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = checkCallingOrSelfPermissionMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "checkCallingOrSelfPermission", "(JJI)I");
            checkCallingOrSelfPermissionMethod = method;
        }
        int n0 = a0 == null ? Blocks.NULL : a0.length();
        int bytes = Blocks.size(n0, 2);
        long transport = a0 == null ? 0 : Transport.alloc(bytes);
        try {
            if (transport != 0) {
                Transport.setBlockHeader(transport, n0, 2);
                Transport.write(a0, transport + Blocks.HEADER);
            }
            JNI.JValue args = StackValue.get(3, JNI.JValue.class);
            args.addressOf(0).setLong(handle.foreignId());
            args.addressOf(1).setLong(transport);
            args.addressOf(2).setInt(bytes);
            int r = Art.callInt(thread, endPoints, method, args);
            Reference.reachabilityFence(handle);
            return r;
        } finally {
            if (transport != 0) {
                Transport.free(transport);
            }
        }
    }

    public static int checkCallingPermission(Context handle, String a0) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = checkCallingPermissionMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "checkCallingPermission", "(JJI)I");
            checkCallingPermissionMethod = method;
        }
        int n0 = a0 == null ? Blocks.NULL : a0.length();
        int bytes = Blocks.size(n0, 2);
        long transport = a0 == null ? 0 : Transport.alloc(bytes);
        try {
            if (transport != 0) {
                Transport.setBlockHeader(transport, n0, 2);
                Transport.write(a0, transport + Blocks.HEADER);
            }
            JNI.JValue args = StackValue.get(3, JNI.JValue.class);
            args.addressOf(0).setLong(handle.foreignId());
            args.addressOf(1).setLong(transport);
            args.addressOf(2).setInt(bytes);
            int r = Art.callInt(thread, endPoints, method, args);
            Reference.reachabilityFence(handle);
            return r;
        } finally {
            if (transport != 0) {
                Transport.free(transport);
            }
        }
    }

    public static int checkPermission(Context handle, String a0, int a1, int a2) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = checkPermissionMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "checkPermission", "(JJIII)I");
            checkPermissionMethod = method;
        }
        int n0 = a0 == null ? Blocks.NULL : a0.length();
        int bytes = Blocks.size(n0, 2);
        long transport = a0 == null ? 0 : Transport.alloc(bytes);
        try {
            if (transport != 0) {
                Transport.setBlockHeader(transport, n0, 2);
                Transport.write(a0, transport + Blocks.HEADER);
            }
            JNI.JValue args = StackValue.get(5, JNI.JValue.class);
            args.addressOf(0).setLong(handle.foreignId());
            args.addressOf(1).setLong(transport);
            args.addressOf(2).setInt(bytes);
            args.addressOf(3).setInt(a1);
            args.addressOf(4).setInt(a2);
            int r = Art.callInt(thread, endPoints, method, args);
            Reference.reachabilityFence(handle);
            return r;
        } finally {
            if (transport != 0) {
                Transport.free(transport);
            }
        }
    }

    public static int checkSelfPermission(Context handle, String a0) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = checkSelfPermissionMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints23, "checkSelfPermission", "(JJI)I");
            checkSelfPermissionMethod = method;
        }
        int n0 = a0 == null ? Blocks.NULL : a0.length();
        int bytes = Blocks.size(n0, 2);
        long transport = a0 == null ? 0 : Transport.alloc(bytes);
        try {
            if (transport != 0) {
                Transport.setBlockHeader(transport, n0, 2);
                Transport.write(a0, transport + Blocks.HEADER);
            }
            JNI.JValue args = StackValue.get(3, JNI.JValue.class);
            args.addressOf(0).setLong(handle.foreignId());
            args.addressOf(1).setLong(transport);
            args.addressOf(2).setInt(bytes);
            int r = Art.callInt(thread, endPoints23, method, args);
            Reference.reachabilityFence(handle);
            return r;
        } finally {
            if (transport != 0) {
                Transport.free(transport);
            }
        }
    }

    public static void clearWallpaper(Context handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = clearWallpaperMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "clearWallpaper", "(J)V");
            clearWallpaperMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        Art.callVoid(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
    }

    public static Context createAttributionContext(Context handle, String a0) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = createAttributionContextMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints30, "createAttributionContext", "(JJI)J");
            createAttributionContextMethod = method;
        }
        int n0 = a0 == null ? Blocks.NULL : a0.length();
        int bytes = Blocks.size(n0, 2);
        long transport = a0 == null ? 0 : Transport.alloc(bytes);
        try {
            if (transport != 0) {
                Transport.setBlockHeader(transport, n0, 2);
                Transport.write(a0, transport + Blocks.HEADER);
            }
            JNI.JValue args = StackValue.get(3, JNI.JValue.class);
            args.addressOf(0).setLong(handle.foreignId());
            args.addressOf(1).setLong(transport);
            args.addressOf(2).setInt(bytes);
            long r = Art.callLong(thread, endPoints30, method, args);
            Reference.reachabilityFence(handle);
            return (Context) HandleClasses.receive(r);
        } finally {
            if (transport != 0) {
                Transport.free(transport);
            }
        }
    }

    public static Context createDeviceContext(Context handle, int a0) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = createDeviceContextMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints34, "createDeviceContext", "(JI)J");
            createDeviceContextMethod = method;
        }
        JNI.JValue args = StackValue.get(2, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        long r = Art.callLong(thread, endPoints34, method, args);
        Reference.reachabilityFence(handle);
        return (Context) HandleClasses.receive(r);
    }

    public static Context createDeviceProtectedStorageContext(Context handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = createDeviceProtectedStorageContextMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints24, "createDeviceProtectedStorageContext", "(J)J");
            createDeviceProtectedStorageContextMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        long r = Art.callLong(thread, endPoints24, method, args);
        Reference.reachabilityFence(handle);
        return (Context) HandleClasses.receive(r);
    }

    public static Context createDisplayContext(Context handle, Display a0) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = createDisplayContextMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "createDisplayContext", "(JJ)J");
            createDisplayContextMethod = method;
        }
        JNI.JValue args = StackValue.get(2, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setLong(HandleClasses.send(a0));
        long r = Art.callLong(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        Reference.reachabilityFence(a0);
        return (Context) HandleClasses.receive(r);
    }

    public static boolean deleteDatabase(Context handle, String a0) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = deleteDatabaseMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "deleteDatabase", "(JJI)Z");
            deleteDatabaseMethod = method;
        }
        int n0 = a0 == null ? Blocks.NULL : a0.length();
        int bytes = Blocks.size(n0, 2);
        long transport = a0 == null ? 0 : Transport.alloc(bytes);
        try {
            if (transport != 0) {
                Transport.setBlockHeader(transport, n0, 2);
                Transport.write(a0, transport + Blocks.HEADER);
            }
            JNI.JValue args = StackValue.get(3, JNI.JValue.class);
            args.addressOf(0).setLong(handle.foreignId());
            args.addressOf(1).setLong(transport);
            args.addressOf(2).setInt(bytes);
            boolean r = Art.callBoolean(thread, endPoints, method, args);
            Reference.reachabilityFence(handle);
            return r;
        } finally {
            if (transport != 0) {
                Transport.free(transport);
            }
        }
    }

    public static boolean deleteFile(Context handle, String a0) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = deleteFileMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "deleteFile", "(JJI)Z");
            deleteFileMethod = method;
        }
        int n0 = a0 == null ? Blocks.NULL : a0.length();
        int bytes = Blocks.size(n0, 2);
        long transport = a0 == null ? 0 : Transport.alloc(bytes);
        try {
            if (transport != 0) {
                Transport.setBlockHeader(transport, n0, 2);
                Transport.write(a0, transport + Blocks.HEADER);
            }
            JNI.JValue args = StackValue.get(3, JNI.JValue.class);
            args.addressOf(0).setLong(handle.foreignId());
            args.addressOf(1).setLong(transport);
            args.addressOf(2).setInt(bytes);
            boolean r = Art.callBoolean(thread, endPoints, method, args);
            Reference.reachabilityFence(handle);
            return r;
        } finally {
            if (transport != 0) {
                Transport.free(transport);
            }
        }
    }

    public static boolean deleteSharedPreferences(Context handle, String a0) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = deleteSharedPreferencesMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints24, "deleteSharedPreferences", "(JJI)Z");
            deleteSharedPreferencesMethod = method;
        }
        int n0 = a0 == null ? Blocks.NULL : a0.length();
        int bytes = Blocks.size(n0, 2);
        long transport = a0 == null ? 0 : Transport.alloc(bytes);
        try {
            if (transport != 0) {
                Transport.setBlockHeader(transport, n0, 2);
                Transport.write(a0, transport + Blocks.HEADER);
            }
            JNI.JValue args = StackValue.get(3, JNI.JValue.class);
            args.addressOf(0).setLong(handle.foreignId());
            args.addressOf(1).setLong(transport);
            args.addressOf(2).setInt(bytes);
            boolean r = Art.callBoolean(thread, endPoints24, method, args);
            Reference.reachabilityFence(handle);
            return r;
        } finally {
            if (transport != 0) {
                Transport.free(transport);
            }
        }
    }

    public static void enforceCallingOrSelfPermission(Context handle, String a0, String a1) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = enforceCallingOrSelfPermissionMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "enforceCallingOrSelfPermission", "(JJIJI)V");
            enforceCallingOrSelfPermissionMethod = method;
        }
        int n0 = a0 == null ? Blocks.NULL : a0.length();
        int n1 = a1 == null ? Blocks.NULL : a1.length();
        int bytes0 = Blocks.size(n0, 2);
        int bytes1 = Blocks.size(n1, 2);
        long transport0 = a0 == null ? 0 : Transport.alloc(bytes0);
        long transport1 = a1 == null ? 0 : Transport.alloc(bytes1);
        try {
            if (transport0 != 0) {
                Transport.setBlockHeader(transport0, n0, 2);
                Transport.write(a0, transport0 + Blocks.HEADER);
            }
            if (transport1 != 0) {
                Transport.setBlockHeader(transport1, n1, 2);
                Transport.write(a1, transport1 + Blocks.HEADER);
            }
            JNI.JValue args = StackValue.get(5, JNI.JValue.class);
            args.addressOf(0).setLong(handle.foreignId());
            args.addressOf(1).setLong(transport0);
            args.addressOf(2).setInt(bytes0);
            args.addressOf(3).setLong(transport1);
            args.addressOf(4).setInt(bytes1);
            Art.callVoid(thread, endPoints, method, args);
            Reference.reachabilityFence(handle);
        } finally {
            if (transport0 != 0) {
                Transport.free(transport0);
            }
            if (transport1 != 0) {
                Transport.free(transport1);
            }
        }
    }

    public static void enforceCallingPermission(Context handle, String a0, String a1) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = enforceCallingPermissionMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "enforceCallingPermission", "(JJIJI)V");
            enforceCallingPermissionMethod = method;
        }
        int n0 = a0 == null ? Blocks.NULL : a0.length();
        int n1 = a1 == null ? Blocks.NULL : a1.length();
        int bytes0 = Blocks.size(n0, 2);
        int bytes1 = Blocks.size(n1, 2);
        long transport0 = a0 == null ? 0 : Transport.alloc(bytes0);
        long transport1 = a1 == null ? 0 : Transport.alloc(bytes1);
        try {
            if (transport0 != 0) {
                Transport.setBlockHeader(transport0, n0, 2);
                Transport.write(a0, transport0 + Blocks.HEADER);
            }
            if (transport1 != 0) {
                Transport.setBlockHeader(transport1, n1, 2);
                Transport.write(a1, transport1 + Blocks.HEADER);
            }
            JNI.JValue args = StackValue.get(5, JNI.JValue.class);
            args.addressOf(0).setLong(handle.foreignId());
            args.addressOf(1).setLong(transport0);
            args.addressOf(2).setInt(bytes0);
            args.addressOf(3).setLong(transport1);
            args.addressOf(4).setInt(bytes1);
            Art.callVoid(thread, endPoints, method, args);
            Reference.reachabilityFence(handle);
        } finally {
            if (transport0 != 0) {
                Transport.free(transport0);
            }
            if (transport1 != 0) {
                Transport.free(transport1);
            }
        }
    }

    public static void enforcePermission(Context handle, String a0, int a1, int a2, String a3) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = enforcePermissionMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "enforcePermission", "(JJIIIJI)V");
            enforcePermissionMethod = method;
        }
        int n0 = a0 == null ? Blocks.NULL : a0.length();
        int n3 = a3 == null ? Blocks.NULL : a3.length();
        int bytes0 = Blocks.size(n0, 2);
        int bytes3 = Blocks.size(n3, 2);
        long transport0 = a0 == null ? 0 : Transport.alloc(bytes0);
        long transport3 = a3 == null ? 0 : Transport.alloc(bytes3);
        try {
            if (transport0 != 0) {
                Transport.setBlockHeader(transport0, n0, 2);
                Transport.write(a0, transport0 + Blocks.HEADER);
            }
            if (transport3 != 0) {
                Transport.setBlockHeader(transport3, n3, 2);
                Transport.write(a3, transport3 + Blocks.HEADER);
            }
            JNI.JValue args = StackValue.get(7, JNI.JValue.class);
            args.addressOf(0).setLong(handle.foreignId());
            args.addressOf(1).setLong(transport0);
            args.addressOf(2).setInt(bytes0);
            args.addressOf(3).setInt(a1);
            args.addressOf(4).setInt(a2);
            args.addressOf(5).setLong(transport3);
            args.addressOf(6).setInt(bytes3);
            Art.callVoid(thread, endPoints, method, args);
            Reference.reachabilityFence(handle);
        } finally {
            if (transport0 != 0) {
                Transport.free(transport0);
            }
            if (transport3 != 0) {
                Transport.free(transport3);
            }
        }
    }

    public static Context getApplicationContext(Context handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getApplicationContextMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getApplicationContext", "(J)J");
            getApplicationContextMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        long r = Art.callLong(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return (Context) HandleClasses.receive(r);
    }

    public static String getAttributionTag(Context handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getAttributionTagMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints30, "getAttributionTag", "(J)Ljava/nio/ByteBuffer;");
            getAttributionTagMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        JNI.JObject view = Art.callObject(thread, endPoints30, method, args);
        long r = Art.address(thread.env, view);
        Art.deleteLocal(thread.env, view);
        Reference.reachabilityFence(handle);
        String result = Transport.readStringBlock(r);
        Transport.free(r);
        return result;
    }

    public static int getColor(Context handle, int a0) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getColorMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints23, "getColor", "(JI)I");
            getColorMethod = method;
        }
        JNI.JValue args = StackValue.get(2, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        int r = Art.callInt(thread, endPoints23, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static int getDeviceId(Context handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getDeviceIdMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints34, "getDeviceId", "(J)I");
            getDeviceIdMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        int r = Art.callInt(thread, endPoints34, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static Display getDisplay(Context handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getDisplayMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints30, "getDisplay", "(J)J");
            getDisplayMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        long r = Art.callLong(thread, endPoints30, method, args);
        Reference.reachabilityFence(handle);
        return (Display) HandleClasses.receive(r);
    }

    public static String getOpPackageName(Context handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getOpPackageNameMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints29, "getOpPackageName", "(J)Ljava/nio/ByteBuffer;");
            getOpPackageNameMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        JNI.JObject view = Art.callObject(thread, endPoints29, method, args);
        long r = Art.address(thread.env, view);
        Art.deleteLocal(thread.env, view);
        Reference.reachabilityFence(handle);
        String result = Transport.readStringBlock(r);
        Transport.free(r);
        return result;
    }

    public static String getPackageCodePath(Context handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getPackageCodePathMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getPackageCodePath", "(J)Ljava/nio/ByteBuffer;");
            getPackageCodePathMethod = method;
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

    public static String getPackageName(Context handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getPackageNameMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getPackageName", "(J)Ljava/nio/ByteBuffer;");
            getPackageNameMethod = method;
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

    public static String getPackageResourcePath(Context handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getPackageResourcePathMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getPackageResourcePath", "(J)Ljava/nio/ByteBuffer;");
            getPackageResourcePathMethod = method;
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

    public static String getString(Context handle, int a0) {
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

    public static int getWallpaperDesiredMinimumHeight(Context handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getWallpaperDesiredMinimumHeightMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getWallpaperDesiredMinimumHeight", "(J)I");
            getWallpaperDesiredMinimumHeightMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static int getWallpaperDesiredMinimumWidth(Context handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = getWallpaperDesiredMinimumWidthMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "getWallpaperDesiredMinimumWidth", "(J)I");
            getWallpaperDesiredMinimumWidthMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        int r = Art.callInt(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static boolean isDeviceProtectedStorage(Context handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = isDeviceProtectedStorageMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints24, "isDeviceProtectedStorage", "(J)Z");
            isDeviceProtectedStorageMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        boolean r = Art.callBoolean(thread, endPoints24, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static boolean isRestricted(Context handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = isRestrictedMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "isRestricted", "(J)Z");
            isRestrictedMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        boolean r = Art.callBoolean(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static boolean isUiContext(Context handle) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = isUiContextMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints31, "isUiContext", "(J)Z");
            isUiContextMethod = method;
        }
        JNI.JValue args = StackValue.get(1, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        boolean r = Art.callBoolean(thread, endPoints31, method, args);
        Reference.reachabilityFence(handle);
        return r;
    }

    public static boolean moveDatabaseFrom(Context handle, Context a0, String a1) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = moveDatabaseFromMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints24, "moveDatabaseFrom", "(JJJI)Z");
            moveDatabaseFromMethod = method;
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
            args.addressOf(1).setLong(HandleClasses.send(a0));
            args.addressOf(2).setLong(transport);
            args.addressOf(3).setInt(bytes);
            boolean r = Art.callBoolean(thread, endPoints24, method, args);
            Reference.reachabilityFence(handle);
            Reference.reachabilityFence(a0);
            return r;
        } finally {
            if (transport != 0) {
                Transport.free(transport);
            }
        }
    }

    public static boolean moveSharedPreferencesFrom(Context handle, Context a0, String a1) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = moveSharedPreferencesFromMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints24, "moveSharedPreferencesFrom", "(JJJI)Z");
            moveSharedPreferencesFromMethod = method;
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
            args.addressOf(1).setLong(HandleClasses.send(a0));
            args.addressOf(2).setLong(transport);
            args.addressOf(3).setInt(bytes);
            boolean r = Art.callBoolean(thread, endPoints24, method, args);
            Reference.reachabilityFence(handle);
            Reference.reachabilityFence(a0);
            return r;
        } finally {
            if (transport != 0) {
                Transport.free(transport);
            }
        }
    }

    public static void revokeSelfPermissionOnKill(Context handle, String a0) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = revokeSelfPermissionOnKillMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints33, "revokeSelfPermissionOnKill", "(JJI)V");
            revokeSelfPermissionOnKillMethod = method;
        }
        int n0 = a0 == null ? Blocks.NULL : a0.length();
        int bytes = Blocks.size(n0, 2);
        long transport = a0 == null ? 0 : Transport.alloc(bytes);
        try {
            if (transport != 0) {
                Transport.setBlockHeader(transport, n0, 2);
                Transport.write(a0, transport + Blocks.HEADER);
            }
            JNI.JValue args = StackValue.get(3, JNI.JValue.class);
            args.addressOf(0).setLong(handle.foreignId());
            args.addressOf(1).setLong(transport);
            args.addressOf(2).setInt(bytes);
            Art.callVoid(thread, endPoints33, method, args);
            Reference.reachabilityFence(handle);
        } finally {
            if (transport != 0) {
                Transport.free(transport);
            }
        }
    }

    public static void setTheme(Context handle, int a0) {
        Art.ThreadData thread = Art.thread();
        JNI.JMethodID method = setThemeMethod;
        if (method.isNull()) {
            method = Art.staticMethod(thread.env, endPoints, "setTheme", "(JI)V");
            setThemeMethod = method;
        }
        JNI.JValue args = StackValue.get(2, JNI.JValue.class);
        args.addressOf(0).setLong(handle.foreignId());
        args.addressOf(1).setInt(a0);
        Art.callVoid(thread, endPoints, method, args);
        Reference.reachabilityFence(handle);
    }
}
