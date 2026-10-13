package android.graphics.drawable;

/**
 * Drawable that shows a ripple effect in response to state changes. The
 * anchoring position of the ripple for a given state may be specified by
 * calling {@link #setHotspot(float, float)} with the corresponding state
 * attribute identifier.
 * <p>
 * A touch feedback drawable may contain multiple child layers, including a
 * special mask layer that is not drawn to the screen. A single layer may be
 * set as the mask from XML by specifying its {@code android:id} value as
 * {@link android.R.id#mask}. At run time, a single layer may be set as the
 * mask using {@code setId(..., android.R.id.mask)} or an existing mask layer
 * may be replaced using {@code setDrawableByLayerId(android.R.id.mask, ...)}.
 * <pre>
 * <code>&lt;!-- A red ripple masked against an opaque rectangle. -->
 * &lt;ripple android:color="#ffff0000">
 *   &lt;item android:id="@android:id/mask"
 *         android:drawable="@android:color/white" />
 * &lt;/ripple></code>
 * </pre>
 * <p>
 * If a mask layer is set, the ripple effect will be masked against that layer
 * before it is drawn over the composite of the remaining child layers.
 * <p>
 * If no mask layer is set, the ripple effect is masked against the composite
 * of the child layers.
 * <pre>
 * <code>&lt;!-- A green ripple drawn atop a black rectangle. -->
 * &lt;ripple android:color="#ff00ff00">
 *   &lt;item android:drawable="@android:color/black" />
 * &lt;/ripple>
 *
 * &lt;!-- A blue ripple drawn atop a drawable resource. -->
 * &lt;ripple android:color="#ff0000ff">
 *   &lt;item android:drawable="@drawable/my_drawable" />
 * &lt;/ripple></code>
 * </pre>
 * <p>
 * If no child layers or mask is specified and the ripple is set as a View
 * background, the ripple will be drawn atop the first available parent
 * background within the View's hierarchy. In this case, the drawing region
 * may extend outside of the Drawable bounds.
 * <pre>
 * <code>&lt;!-- An unbounded red ripple. -->
 * &lt;ripple android:color="#ffff0000" /></code>
 * </pre>
 *
 * @attr ref android.R.styleable#RippleDrawable_color
 */
public final class RippleDrawable {
    /**
     * Radius value that specifies the ripple radius should be computed based
     * on the size of the ripple's container.
     */
    public static final int RADIUS_AUTO = -1;

    private RippleDrawable() {
    }
}
