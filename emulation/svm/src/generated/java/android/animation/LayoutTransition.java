package android.animation;

/**
 * This class enables automatic animations on layout changes in ViewGroup objects. To enable
 * transitions for a layout container, create a LayoutTransition object and set it on any
 * ViewGroup by calling {@link ViewGroup#setLayoutTransition(LayoutTransition)}. This will cause
 * default animations to run whenever items are added to or removed from that container. To specify
 * custom animations, use the {@link LayoutTransition#setAnimator(int, Animator)
 * setAnimator()} method.
 *
 * <p>One of the core concepts of these transition animations is that there are two types of
 * changes that cause the transition and four different animations that run because of
 * those changes. The changes that trigger the transition are items being added to a container
 * (referred to as an "appearing" transition) or removed from a container (also known as
 * "disappearing"). Setting the visibility of views (between GONE and VISIBLE) will trigger
 * the same add/remove logic. The animations that run due to those events are one that animates
 * items being added, one that animates items being removed, and two that animate the other
 * items in the container that change due to the add/remove occurrence. Users of
 * the transition may want different animations for the changing items depending on whether
 * they are changing due to an appearing or disappearing event, so there is one animation for
 * each of these variations of the changing event. Most of the API of this class is concerned
 * with setting up the basic properties of the animations used in these four situations,
 * or with setting up custom animations for any or all of the four.</p>
 *
 * <p>By default, the DISAPPEARING animation begins immediately, as does the CHANGE_APPEARING
 * animation. The other animations begin after a delay that is set to the default duration
 * of the animations. This behavior facilitates a sequence of animations in transitions as
 * follows: when an item is being added to a layout, the other children of that container will
 * move first (thus creating space for the new item), then the appearing animation will run to
 * animate the item being added. Conversely, when an item is removed from a container, the
 * animation to remove it will run first, then the animations of the other children in the
 * layout will run (closing the gap created in the layout when the item was removed). If this
 * default choreography behavior is not desired, the {@link #setDuration(int, long)} and
 * {@link #setStartDelay(int, long)} of any or all of the animations can be changed as
 * appropriate. Keep in mind, however, that if you start an APPEARING animation before a
 * DISAPPEARING animation is completed, the DISAPPEARING animation stops, and any effects from
 * the DISAPPEARING animation are reverted. If you instead start a DISAPPEARING animation
 * before an APPEARING animation is completed, a similar set of effects occurs for the
 * APPEARING animation.</p>
 *
 * <p>The animations specified for the transition, both the defaults and any custom animations
 * set on the transition object, are templates only. That is, these animations exist to hold the
 * basic animation properties, such as the duration, start delay, and properties being animated.
 * But the actual target object, as well as the start and end values for those properties, are
 * set automatically in the process of setting up the transition each time it runs. Each of the
 * animations is cloned from the original copy and the clone is then populated with the dynamic
 * values of the target being animated (such as one of the items in a layout container that is
 * moving as a result of the layout event) as well as the values that are changing (such as the
 * position and size of that object). The actual values that are pushed to each animation
 * depends on what properties are specified for the animation. For example, the default
 * CHANGE_APPEARING animation animates the <code>left</code>, <code>top</code>, <code>right</code>,
 * <code>bottom</code>, <code>scrollX</code>, and <code>scrollY</code> properties.
 * Values for these properties are updated with the pre- and post-layout
 * values when the transition begins. Custom animations will be similarly populated with
 * the target and values being animated, assuming they use ObjectAnimator objects with
 * property names that are known on the target object.</p>
 *
 * <p>This class, and the associated XML flag for containers, animateLayoutChanges="true",
 * provides a simple utility meant for automating changes in straightforward situations.
 * Using LayoutTransition at multiple levels of a nested view hierarchy may not work due to the
 * interrelationship of the various levels of layout. Also, a container that is being scrolled
 * at the same time as items are being added or removed is probably not a good candidate for
 * this utility, because the before/after locations calculated by LayoutTransition
 * may not match the actual locations when the animations finish due to the container
 * being scrolled as the animations are running. You can work around that
 * particular issue by disabling the 'changing' animations by setting the CHANGE_APPEARING
 * and CHANGE_DISAPPEARING animations to null, and setting the startDelay of the
 * other animations appropriately.</p>
 */
public final class LayoutTransition {
    /**
     * A flag indicating the animation that runs on those items that are appearing
     * in the container.
     */
    public static final int APPEARING = 2;

    /**
     * A flag indicating the animation that runs on those items that are changing
     * due to a new item appearing in the container.
     */
    public static final int CHANGE_APPEARING = 0;

    /**
     * A flag indicating the animation that runs on those items that are changing
     * due to an item disappearing from the container.
     */
    public static final int CHANGE_DISAPPEARING = 1;

    /**
     * A flag indicating the animation that runs on those items that are changing
     * due to a layout change not caused by items being added to or removed
     * from the container. This transition type is not enabled by default; it can be
     * enabled via {@link #enableTransitionType(int)}.
     */
    public static final int CHANGING = 4;

    /**
     * A flag indicating the animation that runs on those items that are disappearing
     * from the container.
     */
    public static final int DISAPPEARING = 3;

    private LayoutTransition() {
    }
}
