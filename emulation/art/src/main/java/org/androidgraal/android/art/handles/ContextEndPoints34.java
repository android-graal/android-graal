// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.art.handles;

import android.content.Context;
import androidx.annotation.RequiresApi;
import org.androidgraal.art.EndPoint;
import org.androidgraal.art.Image;
import org.androidgraal.shared.Handles;

@RequiresApi(34)
final class ContextEndPoints34 {
    private static final Handles HANDLES = Image.handles();

    private ContextEndPoints34() {
    }

    @EndPoint
    private static long createDeviceContext(long handle, int a0) {
        try {
            Context receiver = HANDLES.object(handle);
            return HandleClasses.tagOf(receiver.createDeviceContext(a0));
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static int getDeviceId(long handle) {
        try {
            Context receiver = HANDLES.object(handle);
            return receiver.getDeviceId();
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }
}
