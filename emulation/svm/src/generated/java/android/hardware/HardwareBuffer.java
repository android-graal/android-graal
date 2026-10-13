package android.hardware;

import android.os.Parcelable;

/**
 * HardwareBuffer wraps a native <code>AHardwareBuffer</code> object, which is a low-level object
 * representing a memory buffer accessible by various hardware units. HardwareBuffer allows sharing
 * buffers across different application processes. In particular, HardwareBuffers may be mappable
 * to memory accessible to various hardware systems, such as the GPU, a sensor or context hub, or
 * other auxiliary processing units.
 *
 * For more information, see the NDK documentation for <code>AHardwareBuffer</code>.
 */
public final class HardwareBuffer implements Parcelable {
    /**
     * Format: opaque format used for raw data transfer; must have a height of 1
     */
    public static final int BLOB = 33;

    /**
     * Format: 24 bits depth, 8 bits stencil
     */
    public static final int DS_24UI8 = 50;

    /**
     * Format: 32 bits depth, 8 bits stencil
     */
    public static final int DS_FP32UI8 = 52;

    /**
     * Format: 16 bits depth
     */
    public static final int D_16 = 48;

    /**
     * Format: 24 bits depth
     */
    public static final int D_24 = 49;

    /**
     * Format: 32 bits depth
     */
    public static final int D_FP32 = 51;

    /**
     * Format: 10 bits each red, green, blue, alpha
     */
    public static final int RGBA_10101010 = 59;

    /**
     * Format: 10 bits each red, green, blue, 2 bits alpha
     */
    public static final int RGBA_1010102 = 43;

    public static final int RGBA_8888 = 1;

    /**
     * Format: 16 bits each red, green, blue, alpha
     */
    public static final int RGBA_FP16 = 22;

    /**
     * Format: 8 bits each red, green, blue, alpha, alpha is always 0xFF
     */
    public static final int RGBX_8888 = 2;

    /**
     * Format: 5 bits each red and blue, 6 bits green, no alpha
     */
    public static final int RGB_565 = 4;

    /**
     * Format: 8 bits each red, green, blue, no alpha
     */
    public static final int RGB_888 = 3;

    /**
     * Format: 16 bits each red, green. When sampled on the GPU this is represented
     * as an unsigned integer instead of implicit unsigned normalize.
     * For more information see https://www.khronos.org/opengl/wiki/Normalized_Integer
     */
    public static final int RG_1616 = 58;

    /**
     * Format: 16 bits red. When sampled on the GPU this is represented as an
     * unsigned integer instead of implicit unsigned normalize.
     * For more information see https://www.khronos.org/opengl/wiki/Normalized_Integer
     */
    public static final int R_16 = 57;

    /**
     * Format: 8 bits red
     */
    public static final int R_8 = 56;

    /**
     * Format: 8 bits stencil
     */
    public static final int S_UI8 = 53;

    /**
     * The buffer will be used as a hardware composer overlay layer. That is, it will be displayed
     * using the system compositor via {@link SurfaceControl}
     *
     * This flag is currently only needed when using
     * {@link android.view.SurfaceControl.Transaction#setBuffer(SurfaceControl, HardwareBuffer)}
     * to set a buffer. In all other cases, the framework adds this flag
     * internally to buffers that could be presented in a composer overlay.
     */
    public static final long USAGE_COMPOSER_OVERLAY = 2048L;

    /**
     * Usage: The buffer will often be read by the CPU
     */
    public static final long USAGE_CPU_READ_OFTEN = 3L;

    public static final long USAGE_CPU_READ_RARELY = 2L;

    /**
     * Usage: The buffer will often be written to by the CPU
     */
    public static final long USAGE_CPU_WRITE_OFTEN = 48L;

    /**
     * Usage: The buffer will sometimes be written to by the CPU
     */
    public static final long USAGE_CPU_WRITE_RARELY = 32L;

    /**
     * Usage: The buffer is used for front-buffer rendering. When front-buffering rendering is
     *  specified, different usages may adjust their behavior as a result. For example, when
     *  used as USAGE_GPU_COLOR_OUTPUT the buffer will behave similar to a single-buffered window.
     *  When used with USAGE_COMPOSER_OVERLAY, the system will try to prioritize the buffer
     *  receiving an overlay plane & avoid caching it in intermediate composition buffers.
     */
    public static final long USAGE_FRONT_BUFFER = 4294967296L;

    /**
     * Usage: The buffer will be written to by the GPU
     */
    public static final long USAGE_GPU_COLOR_OUTPUT = 512L;

    /**
     * Usage: The buffer will be used as a cube map texture
     */
    public static final long USAGE_GPU_CUBE_MAP = 33554432L;

    /**
     * Usage: The buffer will be used as a shader storage or uniform buffer object
     */
    public static final long USAGE_GPU_DATA_BUFFER = 16777216L;

    /**
     * Usage: The buffer contains a complete mipmap hierarchy
     */
    public static final long USAGE_GPU_MIPMAP_COMPLETE = 67108864L;

    /**
     * Usage: The buffer will be read from by the GPU
     */
    public static final long USAGE_GPU_SAMPLED_IMAGE = 256L;

    /**
     * Usage: The buffer must not be used outside of a protected hardware path
     */
    public static final long USAGE_PROTECTED_CONTENT = 16384L;

    /**
     * Usage: The buffer will be used for sensor direct data
     */
    public static final long USAGE_SENSOR_DIRECT_DATA = 8388608L;

    /**
     * Usage: The buffer will be read by a hardware video encoder
     */
    public static final long USAGE_VIDEO_ENCODE = 65536L;

    /**
     * Format: Planar YCbCr 420; must have an even width and height
     */
    public static final int YCBCR_420_888 = 35;

    /**
     * <p>Android YUV P010 format.</p>
     *
     * P010 is a 4:2:0 YCbCr semiplanar format comprised of a WxH Y plane
     * followed by a Wx(H/2) CbCr plane. Each sample is represented by a 16-bit
     * little-endian value, with the lower 6 bits set to zero.
     */
    public static final int YCBCR_P010 = 54;

    /**
     * <p>Android YUV P210 format.</p>
     *
     * P210 is a 4:2:2 YCbCr semiplanar format comprised of a WxH Y plane
     * followed by a WxH CbCr plane. Each sample is represented by a 16-bit
     * little-endian value, with the lower 6 bits set to zero.
     */
    public static final int YCBCR_P210 = 60;

    private HardwareBuffer() {
    }
}
