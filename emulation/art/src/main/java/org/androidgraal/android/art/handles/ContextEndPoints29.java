// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.art.handles;

import android.content.Context;
import androidx.annotation.RequiresApi;
import java.nio.ByteBuffer;
import org.androidgraal.art.EndPoint;
import org.androidgraal.art.Image;
import org.androidgraal.art.Transport;
import org.androidgraal.shared.Blocks;
import org.androidgraal.shared.Handles;

@RequiresApi(29)
final class ContextEndPoints29 {
    private static final Handles HANDLES = Image.handles();

    private ContextEndPoints29() {
    }

    @EndPoint
    private static ByteBuffer getOpPackageName(long handle) {
        try {
            Context receiver = HANDLES.object(handle);
            Image.ThreadData thread = Image.thread();
            String result = receiver.getOpPackageName();
            if (result == null) {
                return null;
            }
            ByteBuffer r = Transport.allocBlock(thread, result.length(), 2);
            Transport.write(result, r, Blocks.HEADER);
            return r;
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return null;
        }
    }
}
