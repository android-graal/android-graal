// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.art.handles;

import android.view.Display;
import java.nio.ByteBuffer;
import org.androidgraal.art.EndPoint;
import org.androidgraal.art.Image;
import org.androidgraal.art.Transport;
import org.androidgraal.shared.Blocks;
import org.androidgraal.shared.Handles;

final class DisplayEndPoints {
    private static final Handles HANDLES = Image.handles();

    private DisplayEndPoints() {
    }

    @EndPoint
    private static long getAppVsyncOffsetNanos(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            return receiver.getAppVsyncOffsetNanos();
        } catch (Throwable t) {
            Image.raiseException(t);
            return 0;
        }
    }

    @EndPoint
    private static int getDisplayId(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            return receiver.getDisplayId();
        } catch (Throwable t) {
            Image.raiseException(t);
            return 0;
        }
    }

    @EndPoint
    private static int getFlags(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            return receiver.getFlags();
        } catch (Throwable t) {
            Image.raiseException(t);
            return 0;
        }
    }

    @EndPoint
    private static int getHeight(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            return receiver.getHeight();
        } catch (Throwable t) {
            Image.raiseException(t);
            return 0;
        }
    }

    @EndPoint
    private static ByteBuffer getName(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            Image.ThreadData thread = Image.thread();
            String result = receiver.getName();
            if (result == null) {
                return null;
            }
            ByteBuffer r = Transport.allocBlock(thread, result.length(), 2);
            Transport.write(result, r, Blocks.HEADER);
            return r;
        } catch (Throwable t) {
            Image.raiseException(t);
            return null;
        }
    }

    @EndPoint
    private static int getOrientation(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            return receiver.getOrientation();
        } catch (Throwable t) {
            Image.raiseException(t);
            return 0;
        }
    }

    @EndPoint
    private static int getPixelFormat(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            return receiver.getPixelFormat();
        } catch (Throwable t) {
            Image.raiseException(t);
            return 0;
        }
    }

    @EndPoint
    private static long getPresentationDeadlineNanos(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            return receiver.getPresentationDeadlineNanos();
        } catch (Throwable t) {
            Image.raiseException(t);
            return 0;
        }
    }

    @EndPoint
    private static float getRefreshRate(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            return receiver.getRefreshRate();
        } catch (Throwable t) {
            Image.raiseException(t);
            return 0;
        }
    }

    @EndPoint
    private static int getRotation(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            return receiver.getRotation();
        } catch (Throwable t) {
            Image.raiseException(t);
            return 0;
        }
    }

    @EndPoint
    private static int getState(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            return receiver.getState();
        } catch (Throwable t) {
            Image.raiseException(t);
            return 0;
        }
    }

    @EndPoint
    private static ByteBuffer getSupportedRefreshRates(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            Image.ThreadData thread = Image.thread();
            float[] result = receiver.getSupportedRefreshRates();
            if (result == null) {
                return null;
            }
            ByteBuffer r = Transport.allocBlock(thread, result.length, 4);
            r.position(Blocks.HEADER);
            r.asFloatBuffer().put(result);
            return r;
        } catch (Throwable t) {
            Image.raiseException(t);
            return null;
        }
    }

    @EndPoint
    private static int getWidth(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            return receiver.getWidth();
        } catch (Throwable t) {
            Image.raiseException(t);
            return 0;
        }
    }

    @EndPoint
    private static boolean isValid(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            return receiver.isValid();
        } catch (Throwable t) {
            Image.raiseException(t);
            return false;
        }
    }

    @EndPoint
    private static ByteBuffer toString(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            Image.ThreadData thread = Image.thread();
            String result = receiver.toString();
            if (result == null) {
                return null;
            }
            ByteBuffer r = Transport.allocBlock(thread, result.length(), 2);
            Transport.write(result, r, Blocks.HEADER);
            return r;
        } catch (Throwable t) {
            Image.raiseException(t);
            return null;
        }
    }
}
