// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.art.handles;

import android.content.ContextWrapper;
import org.androidgraal.art.EndPoint;
import org.androidgraal.art.Image;
import org.androidgraal.shared.Handles;

final class ContextWrapperEndPoints {
    private static final Handles HANDLES = Image.handles();

    private ContextWrapperEndPoints() {
    }

    @EndPoint
    private static long getBaseContext(long handle) {
        try {
            ContextWrapper receiver = HANDLES.object(handle);
            return HandleClasses.tagOf(receiver.getBaseContext());
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }
}
