// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.art.handles;

import android.content.res.TypedArray;
import java.nio.ByteBuffer;
import org.androidgraal.art.EndPoint;
import org.androidgraal.art.Image;
import org.androidgraal.art.Transport;
import org.androidgraal.shared.Blocks;
import org.androidgraal.shared.Handles;

final class TypedArrayEndPoints {
    private static final Handles HANDLES = Image.handles();

    private TypedArrayEndPoints() {
    }

    @EndPoint
    private static boolean getBoolean(long handle, int a0, boolean a1) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            return receiver.getBoolean(a0, a1);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return false;
        }
    }

    @EndPoint
    private static int getChangingConfigurations(long handle) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            return receiver.getChangingConfigurations();
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static int getColor(long handle, int a0, int a1) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            return receiver.getColor(a0, a1);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static float getDimension(long handle, int a0, float a1) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            return receiver.getDimension(a0, a1);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static int getDimensionPixelOffset(long handle, int a0, int a1) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            return receiver.getDimensionPixelOffset(a0, a1);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static int getDimensionPixelSize(long handle, int a0, int a1) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            return receiver.getDimensionPixelSize(a0, a1);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static float getFloat(long handle, int a0, float a1) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            return receiver.getFloat(a0, a1);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static float getFraction(long handle, int a0, int a1, int a2, float a3) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            return receiver.getFraction(a0, a1, a2, a3);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static int getIndex(long handle, int a0) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            return receiver.getIndex(a0);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static int getIndexCount(long handle) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            return receiver.getIndexCount();
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static int getInt(long handle, int a0, int a1) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            return receiver.getInt(a0, a1);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static int getInteger(long handle, int a0, int a1) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            return receiver.getInteger(a0, a1);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static int getLayoutDimension$0(long handle, int a0, int a1) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            return receiver.getLayoutDimension(a0, a1);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static int getLayoutDimension$1(long handle, int a0, long transport, int bytes) {
        try {
            Image.ThreadData thread = Image.thread();
            ByteBuffer view = transport == 0 ? null : Transport.view(thread, transport, bytes);
            String v1 = view == null ? null : Transport.readStringBlock(view, 0);
            TypedArray receiver = HANDLES.object(handle);
            return receiver.getLayoutDimension(a0, v1);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static ByteBuffer getNonResourceString(long handle, int a0) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            Image.ThreadData thread = Image.thread();
            String result = receiver.getNonResourceString(a0);
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

    @EndPoint
    private static ByteBuffer getPositionDescription(long handle) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            Image.ThreadData thread = Image.thread();
            String result = receiver.getPositionDescription();
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

    @EndPoint
    private static int getResourceId(long handle, int a0, int a1) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            return receiver.getResourceId(a0, a1);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static ByteBuffer getString(long handle, int a0) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            Image.ThreadData thread = Image.thread();
            String result = receiver.getString(a0);
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

    @EndPoint
    private static int getType(long handle, int a0) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            return receiver.getType(a0);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static boolean hasValue(long handle, int a0) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            return receiver.hasValue(a0);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return false;
        }
    }

    @EndPoint
    private static int length(long handle) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            return receiver.length();
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static void recycle(long handle) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            receiver.recycle();
        } catch (Throwable t) {
            Image.raiseImageException(t);
        }
    }

    @EndPoint
    private static ByteBuffer toString(long handle) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            Image.ThreadData thread = Image.thread();
            String result = receiver.toString();
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
