// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.art.handles;

import android.content.res.TypedArray;
import androidx.annotation.RequiresApi;
import org.androidgraal.art.EndPoint;
import org.androidgraal.art.Image;
import org.androidgraal.shared.Handles;

@RequiresApi(29)
final class TypedArrayEndPoints29 {
    private static final Handles HANDLES = Image.handles();

    private TypedArrayEndPoints29() {
    }

    @EndPoint
    private static int getSourceResourceId(long handle, int a0, int a1) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            return receiver.getSourceResourceId(a0, a1);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }
}
