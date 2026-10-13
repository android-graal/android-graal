package android.view.accessibility;

/**
 * Contains methods for accessing and monitoring preferred video captioning state and visual
 * properties.
 */
public final class CaptioningManager {
    private CaptioningManager() {
    }

    /**
     * Specifies visual properties for video captions, including foreground and
     * background colors, edge properties, and typeface.
     */
    public static final class CaptionStyle {
        /**
         * Edge type value specifying depressed bevel character edges.
         */
        public static final int EDGE_TYPE_DEPRESSED = 4;

        /**
         * Edge type value specifying drop-shadowed character edges.
         */
        public static final int EDGE_TYPE_DROP_SHADOW = 2;

        /**
         * Edge type value specifying no character edges.
         */
        public static final int EDGE_TYPE_NONE = 0;

        /**
         * Edge type value specifying uniformly outlined character edges.
         */
        public static final int EDGE_TYPE_OUTLINE = 1;

        /**
         * Edge type value specifying raised bevel character edges.
         */
        public static final int EDGE_TYPE_RAISED = 3;

        /**
         * Unspecified edge type value.
         */
        public static final int EDGE_TYPE_UNSPECIFIED = -1;

        private CaptionStyle() {
        }
    }
}
