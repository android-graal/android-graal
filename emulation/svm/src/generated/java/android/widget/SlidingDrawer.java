package android.widget;

/**
 * SlidingDrawer hides content out of the screen and allows the user to drag a handle
 * to bring the content on screen. SlidingDrawer can be used vertically or horizontally.
 *
 * A special widget composed of two children views: the handle, that the users drags,
 * and the content, attached to the handle and dragged with it.
 *
 * SlidingDrawer should be used as an overlay inside layouts. This means SlidingDrawer
 * should only be used inside of a FrameLayout or a RelativeLayout for instance. The
 * size of the SlidingDrawer defines how much space the content will occupy once slid
 * out so SlidingDrawer should usually use match_parent for both its dimensions.
 *
 * Inside an XML layout, SlidingDrawer must define the id of the handle and of the
 * content:
 *
 * <pre class="prettyprint">
 * &lt;SlidingDrawer
 *     android:id="@+id/drawer"
 *     android:layout_width="match_parent"
 *     android:layout_height="match_parent"
 *
 *     android:handle="@+id/handle"
 *     android:content="@+id/content"&gt;
 *
 *     &lt;ImageView
 *         android:id="@id/handle"
 *         android:layout_width="88dip"
 *         android:layout_height="44dip" /&gt;
 *
 *     &lt;GridView
 *         android:id="@id/content"
 *         android:layout_width="match_parent"
 *         android:layout_height="match_parent" /&gt;
 *
 * &lt;/SlidingDrawer&gt;
 * </pre>
 *
 * @attr ref android.R.styleable#SlidingDrawer_content
 * @attr ref android.R.styleable#SlidingDrawer_handle
 * @attr ref android.R.styleable#SlidingDrawer_topOffset
 * @attr ref android.R.styleable#SlidingDrawer_bottomOffset
 * @attr ref android.R.styleable#SlidingDrawer_orientation
 * @attr ref android.R.styleable#SlidingDrawer_allowSingleTap
 * @attr ref android.R.styleable#SlidingDrawer_animateOnClick
 *
 * @deprecated This class is not supported anymore. It is recommended you
 * base your own implementation on the source code for the Android Open
 * Source Project if you must use it in your application.
 */
@Deprecated
public final class SlidingDrawer {
    @Deprecated
    public static final int ORIENTATION_HORIZONTAL = 0;

    @Deprecated
    public static final int ORIENTATION_VERTICAL = 1;

    private SlidingDrawer() {
    }
}
