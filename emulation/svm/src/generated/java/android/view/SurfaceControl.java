package android.view;

import android.os.Parcelable;

/**
 * Handle to an on-screen Surface managed by the system compositor. The SurfaceControl is
 * a combination of a buffer source, and metadata about how to display the buffers.
 * By constructing a {@link Surface} from this SurfaceControl you can submit buffers to be
 * composited. Using {@link SurfaceControl.Transaction} you can manipulate various
 * properties of how the buffer will be displayed on-screen. SurfaceControl's are
 * arranged into a scene-graph like hierarchy, and as such any SurfaceControl may have
 * a parent. Geometric properties like transform, crop, and Z-ordering will be inherited
 * from the parent, as if the child were content in the parents buffer stream.
 */
public final class SurfaceControl implements Parcelable {
    /**
     * Identity transform.
     *
     * These transforms that can be applied to buffers as they are displayed to a window.
     * @see HardwareBuffer
     *
     * Supported transforms are any combination of horizontal mirror, vertical mirror, and
     * clock-wise 90 degree rotation, in that order. Rotations of 180 and 270 degrees are
     * made up of those basic transforms.
     */
    public static final int BUFFER_TRANSFORM_IDENTITY = 0;

    /**
     * Mirror horizontally. Can be combined with {@link #BUFFER_TRANSFORM_MIRROR_VERTICAL}
     * and {@link #BUFFER_TRANSFORM_ROTATE_90}.
     */
    public static final int BUFFER_TRANSFORM_MIRROR_HORIZONTAL = 1;

    /**
     * Mirror vertically. Can be combined with {@link #BUFFER_TRANSFORM_MIRROR_HORIZONTAL}
     * and {@link #BUFFER_TRANSFORM_ROTATE_90}.
     */
    public static final int BUFFER_TRANSFORM_MIRROR_VERTICAL = 2;

    /**
     * Rotate 180 degrees clock-wise. Cannot be combined with other transforms.
     */
    public static final int BUFFER_TRANSFORM_ROTATE_180 = 3;

    /**
     * Rotate 270 degrees clock-wise. Cannot be combined with other transforms.
     */
    public static final int BUFFER_TRANSFORM_ROTATE_270 = 7;

    /**
     * Rotate 90 degrees clock-wise. Can be combined with {@link
     * #BUFFER_TRANSFORM_MIRROR_HORIZONTAL} and {@link #BUFFER_TRANSFORM_MIRROR_VERTICAL}.
     */
    public static final int BUFFER_TRANSFORM_ROTATE_90 = 4;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private SurfaceControl() {
    }

    /**
     * Jank information to be fed back via {@link OnJankDataListener}.
     * <p>
     * Apps may register a {@link OnJankDataListener} to get periodic batches of jank classification
     * data from the (<a
     * href="https://source.android.com/docs/core/graphics/surfaceflinger-windowmanagersystem">
     * composer</a> regarding rendered frames. A frame is considered janky if it did not reach the
     * display at the intended time, typically due to missing a rendering deadline. This API
     * provides information that can be used to identify the root cause of the scheduling misses
     * and provides overall frame scheduling statistics.
     * <p>
     * This API can be used in conjunction with the {@link FrameMetrics} API by associating jank
     * classification data with {@link FrameMetrics} data via the frame VSync id.
     */
    public static final class JankData {
        /**
         * Bitmask for jank due to deadlines missed by the application.
         */
        public static final int JANK_APPLICATION = 2;

        /**
         * Bitmask for jank due to deadlines missed by the composer.
         */
        public static final int JANK_COMPOSER = 1;

        /**
         * No jank detected, the frame was on time.
         */
        public static final int JANK_NONE = 0;

        /**
         * Bitmask for jank due to deadlines missed by other system components.
         */
        public static final int JANK_OTHER = 4;

        private JankData() {
        }
    }
}
