package android.view;

/**
 * <p>
 * A <code>ViewGroup</code> is a special view that can contain other views
 * (called children.) The view group is the base class for layouts and views
 * containers. This class also defines the
 * {@link android.view.ViewGroup.LayoutParams} class which serves as the base
 * class for layouts parameters.
 * </p>
 *
 * <p>
 * Also see {@link LayoutParams} for layout attributes.
 * </p>
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about creating user interface layouts, read the
 * <a href="{@docRoot}guide/topics/ui/declaring-layout.html">XML Layouts</a> developer
 * guide.</p></div>
 *
 * <p>Here is a complete implementation of a custom ViewGroup that implements
 * a simple {@link android.widget.FrameLayout} along with the ability to stack
 * children in left and right gutters.</p>
 *
 * {@sample development/samples/ApiDemos/src/com/example/android/apis/view/CustomLayout.java
 *      Complete}
 *
 * <p>If you are implementing XML layout attributes as shown in the example, this is the
 * corresponding definition for them that would go in <code>res/values/attrs.xml</code>:</p>
 *
 * {@sample development/samples/ApiDemos/res/values/attrs.xml CustomLayout}
 *
 * <p>Finally the layout manager can be used in an XML layout like so:</p>
 *
 * {@sample development/samples/ApiDemos/res/layout/custom_layout.xml Complete}
 *
 * @attr ref android.R.styleable#ViewGroup_clipChildren
 * @attr ref android.R.styleable#ViewGroup_clipToPadding
 * @attr ref android.R.styleable#ViewGroup_layoutAnimation
 * @attr ref android.R.styleable#ViewGroup_animationCache
 * @attr ref android.R.styleable#ViewGroup_persistentDrawingCache
 * @attr ref android.R.styleable#ViewGroup_alwaysDrawnWithCache
 * @attr ref android.R.styleable#ViewGroup_addStatesFromChildren
 * @attr ref android.R.styleable#ViewGroup_descendantFocusability
 * @attr ref android.R.styleable#ViewGroup_animateLayoutChanges
 * @attr ref android.R.styleable#ViewGroup_splitMotionEvents
 * @attr ref android.R.styleable#ViewGroup_layoutMode
 */
public final class ViewGroup {
    /**
     * We clip to padding when FLAG_CLIP_TO_PADDING and FLAG_PADDING_NOT_NULL
     * are set at the same time.
     */
    protected static final int CLIP_TO_PADDING_MASK = 34;

    /**
     * This view will get focus only if none of its descendants want it.
     */
    public static final int FOCUS_AFTER_DESCENDANTS = 262144;

    /**
     * This view will get focus before any of its descendants.
     */
    public static final int FOCUS_BEFORE_DESCENDANTS = 131072;

    /**
     * This view will block any of its descendants from getting focus, even
     * if they are focusable.
     */
    public static final int FOCUS_BLOCK_DESCENDANTS = 393216;

    /**
     * This constant is a {@link #setLayoutMode(int) layoutMode}.
     * Clip bounds are the raw values of {@link #getLeft() left}, {@link #getTop() top},
     * {@link #getRight() right} and {@link #getBottom() bottom}.
     */
    public static final int LAYOUT_MODE_CLIP_BOUNDS = 0;

    /**
     * This constant is a {@link #setLayoutMode(int) layoutMode}.
     * Optical bounds describe where a widget appears to be. They sit inside the clip
     * bounds which need to cover a larger area to allow other effects,
     * such as shadows and glows, to be drawn.
     */
    public static final int LAYOUT_MODE_OPTICAL_BOUNDS = 1;

    /**
     * Used to indicate that all drawing caches should be kept in memory.
     *
     * @deprecated The view drawing cache was largely made obsolete with the introduction of
     * hardware-accelerated rendering in API 11. With hardware-acceleration, intermediate cache
     * layers are largely unnecessary and can easily result in a net loss in performance due to the
     * cost of creating and updating the layer. In the rare cases where caching layers are useful,
     * such as for alpha animations, {@link #setLayerType(int, Paint)} handles this with hardware
     * rendering. For software-rendered snapshots of a small part of the View hierarchy or
     * individual Views it is recommended to create a {@link Canvas} from either a {@link Bitmap} or
     * {@link android.graphics.Picture} and call {@link #draw(Canvas)} on the View. However these
     * software-rendered usages are discouraged and have compatibility issues with hardware-only
     * rendering features such as {@link android.graphics.Bitmap.Config#HARDWARE Config.HARDWARE}
     * bitmaps, real-time shadows, and outline clipping. For screenshots of the UI for feedback
     * reports or unit testing the {@link PixelCopy} API is recommended.
     */
    @Deprecated
    public static final int PERSISTENT_ALL_CACHES = 3;

    /**
     * Used to indicate that the animation drawing cache should be kept in memory.
     *
     * @deprecated The view drawing cache was largely made obsolete with the introduction of
     * hardware-accelerated rendering in API 11. With hardware-acceleration, intermediate cache
     * layers are largely unnecessary and can easily result in a net loss in performance due to the
     * cost of creating and updating the layer. In the rare cases where caching layers are useful,
     * such as for alpha animations, {@link #setLayerType(int, Paint)} handles this with hardware
     * rendering. For software-rendered snapshots of a small part of the View hierarchy or
     * individual Views it is recommended to create a {@link Canvas} from either a {@link Bitmap} or
     * {@link android.graphics.Picture} and call {@link #draw(Canvas)} on the View. However these
     * software-rendered usages are discouraged and have compatibility issues with hardware-only
     * rendering features such as {@link android.graphics.Bitmap.Config#HARDWARE Config.HARDWARE}
     * bitmaps, real-time shadows, and outline clipping. For screenshots of the UI for feedback
     * reports or unit testing the {@link PixelCopy} API is recommended.
     */
    @Deprecated
    public static final int PERSISTENT_ANIMATION_CACHE = 1;

    /**
     * Used to indicate that no drawing cache should be kept in memory.
     *
     * @deprecated The view drawing cache was largely made obsolete with the introduction of
     * hardware-accelerated rendering in API 11. With hardware-acceleration, intermediate cache
     * layers are largely unnecessary and can easily result in a net loss in performance due to the
     * cost of creating and updating the layer. In the rare cases where caching layers are useful,
     * such as for alpha animations, {@link #setLayerType(int, Paint)} handles this with hardware
     * rendering. For software-rendered snapshots of a small part of the View hierarchy or
     * individual Views it is recommended to create a {@link Canvas} from either a {@link Bitmap} or
     * {@link android.graphics.Picture} and call {@link #draw(Canvas)} on the View. However these
     * software-rendered usages are discouraged and have compatibility issues with hardware-only
     * rendering features such as {@link android.graphics.Bitmap.Config#HARDWARE Config.HARDWARE}
     * bitmaps, real-time shadows, and outline clipping. For screenshots of the UI for feedback
     * reports or unit testing the {@link PixelCopy} API is recommended.
     */
    @Deprecated
    public static final int PERSISTENT_NO_CACHE = 0;

    /**
     * Used to indicate that the scrolling drawing cache should be kept in memory.
     *
     * @deprecated The view drawing cache was largely made obsolete with the introduction of
     * hardware-accelerated rendering in API 11. With hardware-acceleration, intermediate cache
     * layers are largely unnecessary and can easily result in a net loss in performance due to the
     * cost of creating and updating the layer. In the rare cases where caching layers are useful,
     * such as for alpha animations, {@link #setLayerType(int, Paint)} handles this with hardware
     * rendering. For software-rendered snapshots of a small part of the View hierarchy or
     * individual Views it is recommended to create a {@link Canvas} from either a {@link Bitmap} or
     * {@link android.graphics.Picture} and call {@link #draw(Canvas)} on the View. However these
     * software-rendered usages are discouraged and have compatibility issues with hardware-only
     * rendering features such as {@link android.graphics.Bitmap.Config#HARDWARE Config.HARDWARE}
     * bitmaps, real-time shadows, and outline clipping. For screenshots of the UI for feedback
     * reports or unit testing the {@link PixelCopy} API is recommended.
     */
    @Deprecated
    public static final int PERSISTENT_SCROLLING_CACHE = 2;

    private ViewGroup() {
    }

    /**
     * LayoutParams are used by views to tell their parents how they want to be
     * laid out. See
     * {@link android.R.styleable#ViewGroup_Layout ViewGroup Layout Attributes}
     * for a list of all child view attributes that this class supports.
     *
     * <p>
     * The base LayoutParams class just describes how big the view wants to be
     * for both width and height. For each dimension, it can specify one of:
     * <ul>
     * <li>FILL_PARENT (renamed MATCH_PARENT in API Level 8 and higher), which
     * means that the view wants to be as big as its parent (minus padding)
     * <li> WRAP_CONTENT, which means that the view wants to be just big enough
     * to enclose its content (plus padding)
     * <li> an exact number
     * </ul>
     * There are subclasses of LayoutParams for different subclasses of
     * ViewGroup. For example, AbsoluteLayout has its own subclass of
     * LayoutParams which adds an X and Y value.</p>
     *
     * <div class="special reference">
     * <h3>Developer Guides</h3>
     * <p>For more information about creating user interface layouts, read the
     * <a href="{@docRoot}guide/topics/ui/declaring-layout.html">XML Layouts</a> developer
     * guide.</p></div>
     *
     * @attr ref android.R.styleable#ViewGroup_Layout_layout_height
     * @attr ref android.R.styleable#ViewGroup_Layout_layout_width
     */
    public static final class LayoutParams {
        /**
         * Special value for the height or width requested by a View.
         * FILL_PARENT means that the view wants to be as big as its parent,
         * minus the parent's padding, if any. This value is deprecated
         * starting in API Level 8 and replaced by {@link #MATCH_PARENT}.
         */
        @Deprecated
        public static final int FILL_PARENT = -1;

        /**
         * Special value for the height or width requested by a View.
         * MATCH_PARENT means that the view wants to be as big as its parent,
         * minus the parent's padding, if any. Introduced in API Level 8.
         */
        public static final int MATCH_PARENT = -1;

        /**
         * Special value for the height or width requested by a View.
         * WRAP_CONTENT means that the view wants to be just large enough to fit
         * its own internal content, taking its own padding into account.
         */
        public static final int WRAP_CONTENT = -2;

        private LayoutParams() {
        }
    }
}
