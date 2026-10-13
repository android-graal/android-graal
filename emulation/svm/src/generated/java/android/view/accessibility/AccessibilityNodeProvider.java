package android.view.accessibility;

/**
 * This class is the contract a client should implement to enable support of a
 * virtual view hierarchy rooted at a given view for accessibility purposes. A virtual
 * view hierarchy is a tree of imaginary Views that is reported as a part of the view
 * hierarchy when an {@link AccessibilityService} explores the window content.
 * Since the virtual View tree does not exist this class is responsible for
 * managing the {@link AccessibilityNodeInfo}s describing that tree to accessibility
 * services.
 * </p>
 * <p>
 * The main use case of these APIs is to enable a custom view that draws complex content,
 * for example a monthly calendar grid, to be presented as a tree of logical nodes,
 * for example month days each containing events, thus conveying its logical structure.
 * <p>
 * <p>
 * A typical use case is to override {@link View#getAccessibilityNodeProvider()} of the
 * View that is a root of a virtual View hierarchy to return an instance of this class.
 * In such a case this instance is responsible for managing {@link AccessibilityNodeInfo}s
 * describing the virtual sub-tree rooted at the View including the one representing the
 * View itself. Similarly the returned instance is responsible for performing accessibility
 * actions on any virtual view or the root view itself. For example:
 * </p>
 * <aside class="note">
 * <b>Note:</b> Consider using a {@link androidx.customview.widget.ExploreByTouchHelper}, a utility
 * extension of AccessibilityNodeProvider, to simplify many aspects of providing information to
 * accessibility services and managing accessibility focus. </aside>
 * <div>
 * <div class="ds-selector-tabs"><section><h3 id="kotlin">Kotlin</h3>
 * <pre class="prettyprint lang-kotlin">
 * // "view" is the View instance on which this class performs accessibility functions.
 * class MyCalendarViewAccessibilityDelegate(
 *       private var view: MyCalendarView) : AccessibilityDelegate() {
 *     override fun getAccessibilityNodeProvider(host: View): AccessibilityNodeProvider {
 *         return object : AccessibilityNodeProvider() {
 *             override fun createAccessibilityNodeInfo(virtualViewId: Int):
 *                     AccessibilityNodeInfo? {
 *                 when (virtualViewId) {
 *                     <var>host-view-id</var> -&gt; {
 *                         val node = AccessibilityNodeInfo.obtain(view)
 *                         node.addChild(view, <var>child-view-id</var>)
 *                         // Set other attributes like screenReaderFocusable
 *                         // and contentDescription.
 *                         return node
 *                     }
 *                     <var>child-view-id</var> -&gt; {
 *                         val node = AccessibilityNodeInfo
 *                                 .obtain(view, virtualViewId)
 *                         node.setParent(view)
 *                         node.addAction(ACTION_SCROLL_UP)
 *                         node.addAction(ACTION_SCROLL_DOWN)
 *                         // Set other attributes like focusable and visibleToUser.
 *                         node.setBoundsInScreen(
 *                                 Rect(<var>coords-of-edges-relative-to-screen</var>))
 *                         return node
 *                     }
 *                     else -&gt; return null
 *                 }
 *             }
 *
 *             override fun performAction(
 *                 virtualViewId: Int,
 *                 action: Int,
 *                 arguments: Bundle
 *             ): Boolean {
 *                 if (virtualViewId == <var>host-view-id</var>) {
 *                     return view.performAccessibilityAction(action, arguments)
 *                 }
 *                 when (action) {
 *                     ACTION_SCROLL_UP.id -&gt; {
 *                         // Implement logic in a separate method.
 *                         navigateToPreviousMonth()
 *
 *                         return true
 *                     }
 *                     ACTION_SCROLL_DOWN.id -&gt;
 *                         // Implement logic in a separate method.
 *                         navigateToNextMonth()
 *
 *                         return true
 *                     else -&gt; return false
 *                 }
 *             }
 *         }
 *     }
 * }
 * </pre>
 * </section><section><h3 id="java">Java</h3>
 * <pre class="prettyprint lang-java">
 * final class MyCalendarViewAccessibilityDelegate extends AccessibilityDelegate {
 *     // The View instance on which this class performs accessibility functions.
 *     private final MyCalendarView view;
 *
 *     MyCalendarViewAccessibilityDelegate(MyCalendarView view) {
 *         this.view = view;
 *     }
 *
 *     &#64;Override
 *     public AccessibilityNodeProvider getAccessibilityNodeProvider(View host) {
 *         return new AccessibilityNodeProvider() {
 *             &#64;Override
 *             &#64;Nullable
 *             public AccessibilityNodeInfo createAccessibilityNodeInfo(int virtualViewId) {
 *                 if (virtualViewId == <var>host-view-id</var>) {
 *                     AccessibilityNodeInfo node = AccessibilityNodeInfo.obtain(view);
 *                     node.addChild(view, <var>child-view-id</var>);
 *                     // Set other attributes like screenReaderFocusable and contentDescription.
 *                     return node;
 *                 } else if (virtualViewId == <var>child-view-id</var>) {
 *                     AccessibilityNodeInfo node =
 *                         AccessibilityNodeInfo.obtain(view, virtualViewId);
 *                     node.setParent(view);
 *                     node.addAction(ACTION_SCROLL_UP);
 *                     node.addAction(ACTION_SCROLL_DOWN);
 *                     // Set other attributes like focusable and visibleToUser.
 *                     node.setBoundsInScreen(
 *                         new Rect(<var>coordinates-of-edges-relative-to-screen</var>));
 *                     return node;
 *                 } else {
 *                     return null;
 *                 }
 *             }
 *
 *             &#64;Override
 *             public boolean performAction(int virtualViewId, int action, Bundle arguments) {
 *                 if (virtualViewId == <var>host-view-id</var>) {
 *                     return view.performAccessibilityAction(action, arguments);
 *                 }
 *
 *                 if (action == ACTION_SCROLL_UP.getId()) {
 *                     // Implement logic in a separate method.
 *                     navigateToPreviousMonth();
 *
 *                     return true;
 *                 } else if (action == ACTION_SCROLL_DOWN.getId()) {
 *                     // Implement logic in a separate method.
 *                     navigateToNextMonth();
 *
 *                     return true;
 *                 } else {
 *                     return false;
 *                 }
 *             }
 *         };
 *     }
 * }
 * </pre></section></div></div>
 */
public final class AccessibilityNodeProvider {
    /**
     * The virtual id for the hosting View.
     */
    public static final int HOST_VIEW_ID = -1;

    private AccessibilityNodeProvider() {
    }
}
