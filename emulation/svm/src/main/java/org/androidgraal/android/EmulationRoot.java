package org.androidgraal.android;

import dalvik.system.CloseGuard;
import dalvik.system.VMRuntime;
import org.androidgraal.android.host.ApplicationHost;
import org.androidgraal.android.host.Emulation;
import org.androidgraal.svm.Art;
import org.androidgraal.svm.jni.JNI.JClass;
import org.androidgraal.svm.jni.JNI.JNIEnv;
import org.androidgraal.svm.jni.JNI.JObject;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.function.CEntryPoint.IsolateThreadContext;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.function.CLibrary;
import org.graalvm.word.PointerBase;

@CLibrary("android")
public final class EmulationRoot implements Emulation {

    private static volatile ApplicationHost host;

    @Override
    public void attach(ApplicationHost applicationHost) {
        CloseGuard.setEnabled(false);
        host = applicationHost;
        VMRuntime.getRuntime().setTargetSdkVersion(applicationHost.targetSdkVersion());
    }

    public static boolean isAttached() {
        return host != null;
    }

    public static ApplicationHost host() {
        return host;
    }

    @CEntryPoint(name = "Java_org_androidgraal_android_art_ArtHost_nativeAssetManager")
    private static long nativeAssetManager(JNIEnv env, JClass clazz, @IsolateThreadContext long thread,
            JObject assets) {
        try {
            return fromJava(env, assets).rawValue();
        } catch (Throwable t) {
            Art.raiseException(env, t);
            return 0;
        }
    }

    @CFunction("AAssetManager_fromJava")
    private static native PointerBase fromJava(JNIEnv env, JObject assetManager);
}
