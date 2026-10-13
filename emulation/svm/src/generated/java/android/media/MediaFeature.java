package android.media;

/**
 * MediaFeature defines various media features, e.g. hdr type.
 */
public final class MediaFeature {
    private MediaFeature() {
    }

    /**
     * Defines the type of HDR (high dynamic range) video.
     */
    public static final class HdrType {
        /**
         * HDR type for dolby-vision.
         */
        public static final String DOLBY_VISION = "android.media.feature.hdr.dolby_vision";

        /**
         * HDR type for hdr10.
         */
        public static final String HDR10 = "android.media.feature.hdr.hdr10";

        /**
         * HDR type for hdr10+.
         */
        public static final String HDR10_PLUS = "android.media.feature.hdr.hdr10_plus";

        /**
         * HDR type for hlg.
         */
        public static final String HLG = "android.media.feature.hdr.hlg";

        private HdrType() {
        }
    }
}
