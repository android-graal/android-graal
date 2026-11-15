// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.art.handles;

import android.content.res.TypedArray;
import androidx.annotation.RequiresApi;
import org.androidgraal.art.EndPoint;
import org.androidgraal.art.Image;
import org.androidgraal.shared.Handles;

@RequiresApi(31)
final class TypedArrayEndPoints31 {
    private static final Handles HANDLES = Image.handles();

    private TypedArrayEndPoints31() {
    }

    @EndPoint
    private static void close(long handle) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            receiver.close();
        } catch (Throwable t) {
            Image.raiseImageException(t);
        }
    }
}
