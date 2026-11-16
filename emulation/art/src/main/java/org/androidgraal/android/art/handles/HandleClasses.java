// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.art.handles;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.hardware.OverlayProperties;
import android.os.Build;
import android.view.ContextThemeWrapper;
import android.view.Display;
import java.util.IdentityHashMap;
import org.androidgraal.art.Image;
import org.androidgraal.shared.Handles;

public final class HandleClasses {
    private static final Handles HANDLES = Image.handles();

    private static final IdentityHashMap<Class<?>, Integer> ORDINALS = new IdentityHashMap<>();

    public static final long FINGERPRINT = 0x4aefa1ea9b96886eL;

    static {
        ORDINALS.put(Display.class, 0);
        ORDINALS.put(TypedArray.class, 1);
        ORDINALS.put(Context.class, 3);
        ORDINALS.put(ContextWrapper.class, 4);
        ORDINALS.put(ContextThemeWrapper.class, 5);
        if (Build.VERSION.SDK_INT >= 35) {
            ORDINALS.put(OverlayProperties.class, 2);
        }
    }

    private HandleClasses() {
    }

    public static long tagOf(Object object) {
        if (object == null) {
            return 0;
        }
        int ordinal = ordinalOf(object.getClass());
        return Handles.tagOf(HANDLES.send(object), ordinal);
    }

    private static int ordinalOf(Class<?> type) {
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            Integer known = ORDINALS.get(c);
            if (known != null) {
                return known;
            }
        }
        throw new IllegalArgumentException(type.getName() + " extends no class of the handle table");
    }
}
