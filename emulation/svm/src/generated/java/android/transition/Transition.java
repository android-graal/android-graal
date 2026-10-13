package android.transition;

/**
 * A Transition holds information about animations that will be run on its
 * targets during a scene change. Subclasses of this abstract class may
 * choreograph several child transitions ({@link TransitionSet} or they may
 * perform custom animations themselves. Any Transition has two main jobs:
 * (1) capture property values, and (2) play animations based on changes to
 * captured property values. A custom transition knows what property values
 * on View objects are of interest to it, and also knows how to animate
 * changes to those values. For example, the {@link Fade} transition tracks
 * changes to visibility-related properties and is able to construct and run
 * animations that fade items in or out based on changes to those properties.
 *
 * <p>Note: Transitions may not work correctly with either {@link SurfaceView}
 * or {@link TextureView}, due to the way that these views are displayed
 * on the screen. For SurfaceView, the problem is that the view is updated from
 * a non-UI thread, so changes to the view due to transitions (such as moving
 * and resizing the view) may be out of sync with the display inside those bounds.
 * TextureView is more compatible with transitions in general, but some
 * specific transitions (such as {@link Fade}) may not be compatible
 * with TextureView because they rely on {@link ViewOverlay} functionality,
 * which does not currently work with TextureView.</p>
 *
 * <p>Transitions can be declared in XML resource files inside the <code>res/transition</code>
 * directory. Transition resources consist of a tag name for one of the Transition
 * subclasses along with attributes to define some of the attributes of that transition.
 * For example, here is a minimal resource file that declares a {@link ChangeBounds} transition:
 *
 * {@sample development/samples/ApiDemos/res/transition/changebounds.xml ChangeBounds}
 *
 * <p>This TransitionSet contains {@link android.transition.Explode} for visibility,
 * {@link android.transition.ChangeBounds}, {@link android.transition.ChangeTransform},
 * and {@link android.transition.ChangeClipBounds} and
 * {@link android.transition.ChangeImageTransform}:</p>
 *
 * {@sample development/samples/ApiDemos/res/transition/explode_move_together.xml MultipleTransform}
 *
 * <p>Custom transition classes may be instantiated with a <code>transition</code> tag:</p>
 * <pre>&lt;transition class="my.app.transition.CustomTransition"/></pre>
 * <p>Custom transition classes loaded from XML should have a public constructor taking
 * a {@link android.content.Context} and {@link android.util.AttributeSet}.</p>
 *
 * <p>Note that attributes for the transition are not required, just as they are
 * optional when declared in code; Transitions created from XML resources will use
 * the same defaults as their code-created equivalents. Here is a slightly more
 * elaborate example which declares a {@link TransitionSet} transition with
 * {@link ChangeBounds} and {@link Fade} child transitions:</p>
 *
 * {@sample
 * development/samples/ApiDemos/res/transition/changebounds_fadeout_sequential.xml TransitionSet}
 *
 * <p>In this example, the transitionOrdering attribute is used on the TransitionSet
 * object to change from the default {@link TransitionSet#ORDERING_TOGETHER} behavior
 * to be {@link TransitionSet#ORDERING_SEQUENTIAL} instead. Also, the {@link Fade}
 * transition uses a fadingMode of {@link Fade#OUT} instead of the default
 * out-in behavior. Finally, note the use of the <code>targets</code> sub-tag, which
 * takes a set of {@link android.R.styleable#TransitionTarget target} tags, each
 * of which lists a specific <code>targetId</code>, <code>targetClass</code>,
 * <code>targetName</code>, <code>excludeId</code>, <code>excludeClass</code>, or
 * <code>excludeName</code>, which this transition acts upon.
 * Use of targets is optional, but can be used to either limit the time spent checking
 * attributes on unchanging views, or limiting the types of animations run on specific views.
 * In this case, we know that only the <code>grayscaleContainer</code> will be
 * disappearing, so we choose to limit the {@link Fade} transition to only that view.</p>
 *
 * Further information on XML resource descriptions for transitions can be found for
 * {@link android.R.styleable#Transition}, {@link android.R.styleable#TransitionSet},
 * {@link android.R.styleable#TransitionTarget}, {@link android.R.styleable#Fade},
 * {@link android.R.styleable#Slide}, and {@link android.R.styleable#ChangeTransform}.
 */
public final class Transition {
    /**
     * With {@link #setMatchOrder(int...)}, chooses to match by
     * {@link android.view.View#getId()}. Negative IDs will not be matched.
     */
    public static final int MATCH_ID = 3;

    /**
     * With {@link #setMatchOrder(int...)}, chooses to match by View instance.
     */
    public static final int MATCH_INSTANCE = 1;

    /**
     * With {@link #setMatchOrder(int...)}, chooses to match by the {@link android.widget.Adapter}
     * item id. When {@link android.widget.Adapter#hasStableIds()} returns false, no match
     * will be made for items.
     */
    public static final int MATCH_ITEM_ID = 4;

    /**
     * With {@link #setMatchOrder(int...)}, chooses to match by
     * {@link android.view.View#getTransitionName()}. Null names will not be matched.
     */
    public static final int MATCH_NAME = 2;

    private Transition() {
    }
}
