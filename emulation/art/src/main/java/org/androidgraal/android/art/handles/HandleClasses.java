// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.art.handles;

import android.view.Display;
import java.util.IdentityHashMap;
import org.androidgraal.art.Image;
import org.androidgraal.shared.Handles;

public final class HandleClasses {
    private static final Handles HANDLES = Image.handles();

    private static final IdentityHashMap<Class<?>, Integer> ORDINALS = new IdentityHashMap<>();

    public static final long FINGERPRINT = 0x682eac6d8c4f48a1L;

    static {
        ORDINALS.put(Display.class, 0);
    }

    private HandleClasses() {
    }

    public static long tagOf(Object object) {
        if (object == null) {
            return 0;
        }
        return Handles.tagOf(HANDLES.send(object), ordinalOf(object.getClass()));
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
