// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.art.handles;

import android.view.Display;
import androidx.annotation.RequiresApi;
import org.androidgraal.art.EndPoint;
import org.androidgraal.art.Image;
import org.androidgraal.shared.Handles;

@RequiresApi(26)
final class DisplayEndPoints26 {
    private static final Handles HANDLES = Image.handles();

    private DisplayEndPoints26() {
    }

    @EndPoint
    private static boolean isHdr(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            return receiver.isHdr();
        } catch (Throwable t) {
            Image.raiseException(t);
            return false;
        }
    }

    @EndPoint
    private static boolean isWideColorGamut(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            return receiver.isWideColorGamut();
        } catch (Throwable t) {
            Image.raiseException(t);
            return false;
        }
    }
}
