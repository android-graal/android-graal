// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.art.handles;

import android.content.Context;
import androidx.annotation.RequiresApi;
import org.androidgraal.art.EndPoint;
import org.androidgraal.art.Image;
import org.androidgraal.shared.Handles;

@RequiresApi(31)
final class ContextEndPoints31 {
    private static final Handles HANDLES = Image.handles();

    private ContextEndPoints31() {
    }

    @EndPoint
    private static boolean isUiContext(long handle) {
        try {
            Context receiver = HANDLES.object(handle);
            return receiver.isUiContext();
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return false;
        }
    }
}
