// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.handles;

import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.hardware.OverlayProperties;
import android.view.ContextThemeWrapper;
import android.view.Display;
import org.androidgraal.shared.Handles;
import org.androidgraal.svm.Art;
import org.androidgraal.svm.jni.JNI;

public final class HandleClasses {
    private static final Handles HANDLES = Art.handles();

    private static final Handles.Factory<?>[] FACTORIES = {Display::new, TypedArray::new, OverlayProperties::new,
            $Context::new, ContextWrapper::new, ContextThemeWrapper::new};

    public static final long FINGERPRINT = 0x4aefa1ea9b96886eL;

    private HandleClasses() {
    }

    public static void initialize(JNI.JNIEnv env) {
        DisplayBinding.initialize(env);
        TypedArrayBinding.initialize(env);
        OverlayPropertiesBinding.initialize(env);
        ContextBinding.initialize(env);
        ContextWrapperBinding.initialize(env);
    }

    public static Object receive(long tag) {
        return HANDLES.receive(Handles.idOf(tag), FACTORIES[Handles.ordinalOf(tag)]);
    }

    public static long send(Object object) {
        return HANDLES.send(object);
    }
}
