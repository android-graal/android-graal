// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.art.handles;

import android.view.Display;
import androidx.annotation.RequiresApi;
import org.androidgraal.art.EndPoint;
import org.androidgraal.art.Image;
import org.androidgraal.shared.Handles;

@RequiresApi(35)
final class DisplayEndPoints35 {
    private static final Handles HANDLES = Image.handles();

    private DisplayEndPoints35() {
    }

    @EndPoint
    private static long getOverlaySupport(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            return HandleClasses.tagOf(receiver.getOverlaySupport());
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }
}
