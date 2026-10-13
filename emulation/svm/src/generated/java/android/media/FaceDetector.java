package android.media;

/**
 * Identifies the faces of people in a 
 * {@link android.graphics.Bitmap} graphic object.
 */
public final class FaceDetector {
    private FaceDetector() {
    }

    /**
     * A Face contains all the information identifying the location
     * of a face in a bitmap.
     */
    public static final class Face {
        /**
         * The minimum confidence factor of good face recognition
         */
        public static final float CONFIDENCE_THRESHOLD = 0.4f;

        /**
         * The x-axis Euler angle of a face.
         */
        public static final int EULER_X = 0;

        /**
         * The y-axis Euler angle of a face.
         */
        public static final int EULER_Y = 1;

        /**
         * The z-axis Euler angle of a face.
         */
        public static final int EULER_Z = 2;

        private Face() {
        }
    }
}
