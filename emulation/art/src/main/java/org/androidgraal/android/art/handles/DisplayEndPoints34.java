// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.art.handles;

import android.view.Display;
import androidx.annotation.RequiresApi;
import org.androidgraal.art.EndPoint;
import org.androidgraal.art.Image;
import org.androidgraal.shared.Handles;

@RequiresApi(34)
final class DisplayEndPoints34 {
    private static final Handles HANDLES = Image.handles();

    private DisplayEndPoints34() {
    }

    @EndPoint
    private static float getHdrSdrRatio(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            return receiver.getHdrSdrRatio();
        } catch (Throwable t) {
            Image.raiseException(t);
            return 0;
        }
    }

    @EndPoint
    private static boolean isHdrSdrRatioAvailable(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            return receiver.isHdrSdrRatioAvailable();
        } catch (Throwable t) {
            Image.raiseException(t);
            return false;
        }
    }
}
