package android.view;

/**
 * <p>
 * This class represents the basic building block for user interface components. A View
 * occupies a rectangular area on the screen and is responsible for drawing and
 * event handling. View is the base class for <em>widgets</em>, which are
 * used to create interactive UI components (buttons, text fields, etc.). The
 * {@link android.view.ViewGroup} subclass is the base class for <em>layouts</em>, which
 * are invisible containers that hold other Views (or other ViewGroups) and define
 * their layout properties.
 * </p>
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For information about using this class to develop your application's user interface,
 * read the <a href="{@docRoot}guide/topics/ui/index.html">User Interface</a> developer guide.
 * </div>
 *
 * <a name="Using"></a>
 * <h3>Using Views</h3>
 * <p>
 * All of the views in a window are arranged in a single tree. You can add views
 * either from code or by specifying a tree of views in one or more XML layout
 * files. There are many specialized subclasses of views that act as controls or
 * are capable of displaying text, images, or other content.
 * </p>
 * <p>
 * Once you have created a tree of views, there are typically a few types of
 * common operations you may wish to perform:
 * <ul>
 * <li><strong>Set properties:</strong> for example setting the text of a
 * {@link android.widget.TextView}. The available properties and the methods
 * that set them will vary among the different subclasses of views. Note that
 * properties that are known at build time can be set in the XML layout
 * files.</li>
 * <li><strong>Set focus:</strong> The framework will handle moving focus in
 * response to user input. To force focus to a specific view, call
 * {@link #requestFocus}.</li>
 * <li><strong>Set up listeners:</strong> Views allow clients to set listeners
 * that will be notified when something interesting happens to the view. For
 * example, all views will let you set a listener to be notified when the view
 * gains or loses focus. You can register such a listener using
 * {@link #setOnFocusChangeListener(android.view.View.OnFocusChangeListener)}.
 * Other view subclasses offer more specialized listeners. For example, a Button
 * exposes a listener to notify clients when the button is clicked.</li>
 * <li><strong>Set visibility:</strong> You can hide or show views using
 * {@link #setVisibility(int)}.</li>
 * </ul>
 * </p>
 * <p><em>
 * Note: The Android framework is responsible for measuring, laying out and
 * drawing views. You should not call methods that perform these actions on
 * views yourself unless you are actually implementing a
 * {@link android.view.ViewGroup}.
 * </em></p>
 *
 * <a name="Lifecycle"></a>
 * <h3>Implementing a Custom View</h3>
 *
 * <p>
 * To implement a custom view, you will usually begin by providing overrides for
 * some of the standard methods that the framework calls on all views. You do
 * not need to override all of these methods. In fact, you can start by just
 * overriding {@link #onDraw(android.graphics.Canvas)}.
 * <table border="2" width="85%" align="center" cellpadding="5">
 *     <thead>
 *         <tr><th>Category</th> <th>Methods</th> <th>Description</th></tr>
 *     </thead>
 *
 *     <tbody>
 *     <tr>
 *         <td rowspan="2">Creation</td>
 *         <td>Constructors</td>
 *         <td>There is a form of the constructor that are called when the view
 *         is created from code and a form that is called when the view is
 *         inflated from a layout file. The second form should parse and apply
 *         any attributes defined in the layout file.
 *         </td>
 *     </tr>
 *     <tr>
 *         <td><code>{@link #onFinishInflate()}</code></td>
 *         <td>Called after a view and all of its children has been inflated
 *         from XML.</td>
 *     </tr>
 *
 *     <tr>
 *         <td rowspan="3">Layout</td>
 *         <td><code>{@link #onMeasure(int, int)}</code></td>
 *         <td>Called to determine the size requirements for this view and all
 *         of its children.
 *         </td>
 *     </tr>
 *     <tr>
 *         <td><code>{@link #onLayout(boolean, int, int, int, int)}</code></td>
 *         <td>Called when this view should assign a size and position to all
 *         of its children.
 *         </td>
 *     </tr>
 *     <tr>
 *         <td><code>{@link #onSizeChanged(int, int, int, int)}</code></td>
 *         <td>Called when the size of this view has changed.
 *         </td>
 *     </tr>
 *
 *     <tr>
 *         <td>Drawing</td>
 *         <td><code>{@link #onDraw(android.graphics.Canvas)}</code></td>
 *         <td>Called when the view should render its content.
 *         </td>
 *     </tr>
 *
 *     <tr>
 *         <td rowspan="6">Event processing</td>
 *         <td><code>{@link #onKeyDown(int, KeyEvent)}</code></td>
 *         <td>Called when a new hardware key event occurs.
 *         </td>
 *     </tr>
 *     <tr>
 *         <td><code>{@link #onKeyUp(int, KeyEvent)}</code></td>
 *         <td>Called when a hardware key up event occurs.
 *         </td>
 *     </tr>
 *     <tr>
 *         <td><code>{@link #onTrackballEvent(MotionEvent)}</code></td>
 *         <td>Called when a trackball motion event occurs.
 *         </td>
 *     </tr>
 *     <tr>
 *         <td><code>{@link #onTouchEvent(MotionEvent)}</code></td>
 *         <td>Called when a motion event occurs with pointers down on the view.
 *         </td>
 *     </tr>
 *     <tr>
 *         <td><code>{@link #onGenericMotionEvent(MotionEvent)}</code></td>
 *         <td>Called when a generic motion event occurs.
 *         </td>
 *     </tr>
 *     <tr>
 *         <td><code>{@link #onHoverEvent(MotionEvent)}</code></td>
 *         <td>Called when a hover motion event occurs.
 *         </td>
 *     </tr>
 *
 *     <tr>
 *         <td rowspan="2">Focus</td>
 *         <td><code>{@link #onFocusChanged(boolean, int, android.graphics.Rect)}</code></td>
 *         <td>Called when the view gains or loses focus.
 *         </td>
 *     </tr>
 *
 *     <tr>
 *         <td><code>{@link #onWindowFocusChanged(boolean)}</code></td>
 *         <td>Called when the window containing the view gains or loses focus.
 *         </td>
 *     </tr>
 *
 *     <tr>
 *         <td rowspan="3">Attaching</td>
 *         <td><code>{@link #onAttachedToWindow()}</code></td>
 *         <td>Called when the view is attached to a window.
 *         </td>
 *     </tr>
 *
 *     <tr>
 *         <td><code>{@link #onDetachedFromWindow}</code></td>
 *         <td>Called when the view is detached from its window.
 *         </td>
 *     </tr>
 *
 *     <tr>
 *         <td><code>{@link #onWindowVisibilityChanged(int)}</code></td>
 *         <td>Called when the visibility of the window containing the view
 *         has changed.
 *         </td>
 *     </tr>
 *     </tbody>
 *
 * </table>
 * </p>
 *
 * <a name="IDs"></a>
 * <h3>IDs</h3>
 * Views may have an integer id associated with them. These ids are typically
 * assigned in the layout XML files, and are used to find specific views within
 * the view tree. A common pattern is to:
 * <ul>
 * <li>Define a Button in the layout file and assign it a unique ID.
 * <pre>
 * &lt;Button
 *     android:id="@+id/my_button"
 *     android:layout_width="wrap_content"
 *     android:layout_height="wrap_content"
 *     android:text="@string/my_button_text"/&gt;
 * </pre></li>
 * <li>From the onCreate method of an Activity, find the Button
 * <pre class="prettyprint">
 *      Button myButton = findViewById(R.id.my_button);
 * </pre></li>
 * </ul>
 * <p>
 * View IDs need not be unique throughout the tree, but it is good practice to
 * ensure that they are at least unique within the part of the tree you are
 * searching.
 * </p>
 *
 * <a name="Position"></a>
 * <h3>Position</h3>
 * <p>
 * The geometry of a view is that of a rectangle. A view has a location,
 * expressed as a pair of <em>left</em> and <em>top</em> coordinates, and
 * two dimensions, expressed as a width and a height. The unit for location
 * and dimensions is the pixel.
 * </p>
 *
 * <p>
 * It is possible to retrieve the location of a view by invoking the methods
 * {@link #getLeft()} and {@link #getTop()}. The former returns the left, or X,
 * coordinate of the rectangle representing the view. The latter returns the
 * top, or Y, coordinate of the rectangle representing the view. These methods
 * both return the location of the view relative to its parent. For instance,
 * when getLeft() returns 20, that means the view is located 20 pixels to the
 * right of the left edge of its direct parent.
 * </p>
 *
 * <p>
 * In addition, several convenience methods are offered to avoid unnecessary
 * computations, namely {@link #getRight()} and {@link #getBottom()}.
 * These methods return the coordinates of the right and bottom edges of the
 * rectangle representing the view. For instance, calling {@link #getRight()}
 * is similar to the following computation: <code>getLeft() + getWidth()</code>
 * (see <a href="#SizePaddingMargins">Size</a> for more information about the width.)
 * </p>
 *
 * <a name="SizePaddingMargins"></a>
 * <h3>Size, padding and margins</h3>
 * <p>
 * The size of a view is expressed with a width and a height. A view actually
 * possess two pairs of width and height values.
 * </p>
 *
 * <p>
 * The first pair is known as <em>measured width</em> and
 * <em>measured height</em>. These dimensions define how big a view wants to be
 * within its parent (see <a href="#Layout">Layout</a> for more details.) The
 * measured dimensions can be obtained by calling {@link #getMeasuredWidth()}
 * and {@link #getMeasuredHeight()}.
 * </p>
 *
 * <p>
 * The second pair is simply known as <em>width</em> and <em>height</em>, or
 * sometimes <em>drawing width</em> and <em>drawing height</em>. These
 * dimensions define the actual size of the view on screen, at drawing time and
 * after layout. These values may, but do not have to, be different from the
 * measured width and height. The width and height can be obtained by calling
 * {@link #getWidth()} and {@link #getHeight()}.
 * </p>
 *
 * <p>
 * To measure its dimensions, a view takes into account its padding. The padding
 * is expressed in pixels for the left, top, right and bottom parts of the view.
 * Padding can be used to offset the content of the view by a specific amount of
 * pixels. For instance, a left padding of 2 will push the view's content by
 * 2 pixels to the right of the left edge. Padding can be set using the
 * {@link #setPadding(int, int, int, int)} or {@link #setPaddingRelative(int, int, int, int)}
 * method and queried by calling {@link #getPaddingLeft()}, {@link #getPaddingTop()},
 * {@link #getPaddingRight()}, {@link #getPaddingBottom()}, {@link #getPaddingStart()},
 * {@link #getPaddingEnd()}.
 * </p>
 *
 * <p>
 * Even though a view can define a padding, it does not provide any support for
 * margins. However, view groups provide such a support. Refer to
 * {@link android.view.ViewGroup} and
 * {@link android.view.ViewGroup.MarginLayoutParams} for further information.
 * </p>
 *
 * <a name="Layout"></a>
 * <h3>Layout</h3>
 * <p>
 * Layout is a two pass process: a measure pass and a layout pass. The measuring
 * pass is implemented in {@link #measure(int, int)} and is a top-down traversal
 * of the view tree. Each view pushes dimension specifications down the tree
 * during the recursion. At the end of the measure pass, every view has stored
 * its measurements. The second pass happens in
 * {@link #layout(int,int,int,int)} and is also top-down. During
 * this pass each parent is responsible for positioning all of its children
 * using the sizes computed in the measure pass.
 * </p>
 *
 * <p>
 * When a view's measure() method returns, its {@link #getMeasuredWidth()} and
 * {@link #getMeasuredHeight()} values must be set, along with those for all of
 * that view's descendants. A view's measured width and measured height values
 * must respect the constraints imposed by the view's parents. This guarantees
 * that at the end of the measure pass, all parents accept all of their
 * children's measurements. A parent view may call measure() more than once on
 * its children. For example, the parent may measure each child once with
 * unspecified dimensions to find out how big they want to be, then call
 * measure() on them again with actual numbers if the sum of all the children's
 * unconstrained sizes is too big or too small.
 * </p>
 *
 * <p>
 * The measure pass uses two classes to communicate dimensions. The
 * {@link MeasureSpec} class is used by views to tell their parents how they
 * want to be measured and positioned. The base LayoutParams class just
 * describes how big the view wants to be for both width and height. For each
 * dimension, it can specify one of:
 * <ul>
 * <li> an exact number
 * <li>MATCH_PARENT, which means the view wants to be as big as its parent
 * (minus padding)
 * <li> WRAP_CONTENT, which means that the view wants to be just big enough to
 * enclose its content (plus padding).
 * </ul>
 * There are subclasses of LayoutParams for different subclasses of ViewGroup.
 * For example, AbsoluteLayout has its own subclass of LayoutParams which adds
 * an X and Y value.
 * </p>
 *
 * <p>
 * MeasureSpecs are used to push requirements down the tree from parent to
 * child. A MeasureSpec can be in one of three modes:
 * <ul>
 * <li>UNSPECIFIED: This is used by a parent to determine the desired dimension
 * of a child view. For example, a LinearLayout may call measure() on its child
 * with the height set to UNSPECIFIED and a width of EXACTLY 240 to find out how
 * tall the child view wants to be given a width of 240 pixels.
 * <li>EXACTLY: This is used by the parent to impose an exact size on the
 * child. The child must use this size, and guarantee that all of its
 * descendants will fit within this size.
 * <li>AT_MOST: This is used by the parent to impose a maximum size on the
 * child. The child must guarantee that it and all of its descendants will fit
 * within this size.
 * </ul>
 * </p>
 *
 * <p>
 * To initiate a layout, call {@link #requestLayout}. This method is typically
 * called by a view on itself when it believes that it can no longer fit within
 * its current bounds.
 * </p>
 *
 * <a name="Drawing"></a>
 * <h3>Drawing</h3>
 * <p>
 * Drawing is handled by walking the tree and recording the drawing commands of
 * any View that needs to update. After this, the drawing commands of the
 * entire tree are issued to screen, clipped to the newly damaged area.
 * </p>
 *
 * <p>
 * The tree is largely recorded and drawn in order, with parents drawn before
 * (i.e., behind) their children, with siblings drawn in the order they appear
 * in the tree. If you set a background drawable for a View, then the View will
 * draw it before calling back to its <code>onDraw()</code> method. The child
 * drawing order can be overridden with
 * {@link ViewGroup#setChildrenDrawingOrderEnabled(boolean) custom child drawing order}
 * in a ViewGroup, and with {@link #setZ(float)} custom Z values} set on Views.
 * </p>
 *
 * <p>
 * To force a view to draw, call {@link #invalidate()}.
 * </p>
 *
 * <a name="EventHandlingThreading"></a>
 * <h3>Event Handling and Threading</h3>
 * <p>
 * The basic cycle of a view is as follows:
 * <ol>
 * <li>An event comes in and is dispatched to the appropriate view. The view
 * handles the event and notifies any listeners.</li>
 * <li>If in the course of processing the event, the view's bounds may need
 * to be changed, the view will call {@link #requestLayout()}.</li>
 * <li>Similarly, if in the course of processing the event the view's appearance
 * may need to be changed, the view will call {@link #invalidate()}.</li>
 * <li>If either {@link #requestLayout()} or {@link #invalidate()} were called,
 * the framework will take care of measuring, laying out, and drawing the tree
 * as appropriate.</li>
 * </ol>
 * </p>
 *
 * <p><em>Note: The entire view tree is single threaded. You must always be on
 * the UI thread when calling any method on any view.</em>
 * If you are doing work on other threads and want to update the state of a view
 * from that thread, you should use a {@link Handler}.
 * </p>
 *
 * <a name="FocusHandling"></a>
 * <h3>Focus Handling</h3>
 * <p>
 * The framework will handle routine focus movement in response to user input.
 * This includes changing the focus as views are removed or hidden, or as new
 * views become available. Views indicate their willingness to take focus
 * through the {@link #isFocusable} method. To change whether a view can take
 * focus, call {@link #setFocusable(boolean)}.  When in touch mode (see notes below)
 * views indicate whether they still would like focus via {@link #isFocusableInTouchMode}
 * and can change this via {@link #setFocusableInTouchMode(boolean)}.
 * </p>
 * <p>
 * Focus movement is based on an algorithm which finds the nearest neighbor in a
 * given direction. In rare cases, the default algorithm may not match the
 * intended behavior of the developer. In these situations, you can provide
 * explicit overrides by using these XML attributes in the layout file:
 * <pre>
 * nextFocusDown
 * nextFocusLeft
 * nextFocusRight
 * nextFocusUp
 * </pre>
 * </p>
 *
 *
 * <p>
 * To get a particular view to take focus, call {@link #requestFocus()}.
 * </p>
 *
 * <a name="TouchMode"></a>
 * <h3>Touch Mode</h3>
 * <p>
 * When a user is navigating a user interface via directional keys such as a D-pad, it is
 * necessary to give focus to actionable items such as buttons so the user can see
 * what will take input.  If the device has touch capabilities, however, and the user
 * begins interacting with the interface by touching it, it is no longer necessary to
 * always highlight, or give focus to, a particular view.  This motivates a mode
 * for interaction named 'touch mode'.
 * </p>
 * <p>
 * For a touch capable device, once the user touches the screen, the device
 * will enter touch mode.  From this point onward, only views for which
 * {@link #isFocusableInTouchMode} is true will be focusable, such as text editing widgets.
 * Other views that are touchable, like buttons, will not take focus when touched; they will
 * only fire the on click listeners.
 * </p>
 * <p>
 * Any time a user hits a directional key, such as a D-pad direction, the view device will
 * exit touch mode, and find a view to take focus, so that the user may resume interacting
 * with the user interface without touching the screen again.
 * </p>
 * <p>
 * The touch mode state is maintained across {@link android.app.Activity}s.  Call
 * {@link #isInTouchMode} to see whether the device is currently in touch mode.
 * </p>
 *
 * <a name="Scrolling"></a>
 * <h3>Scrolling</h3>
 * <p>
 * The framework provides basic support for views that wish to internally
 * scroll their content. This includes keeping track of the X and Y scroll
 * offset as well as mechanisms for drawing scrollbars. See
 * {@link #scrollBy(int, int)}, {@link #scrollTo(int, int)}, and
 * {@link #awakenScrollBars()} for more details.
 * </p>
 *
 * <a name="Tags"></a>
 * <h3>Tags</h3>
 * <p>
 * Unlike IDs, tags are not used to identify views. Tags are essentially an
 * extra piece of information that can be associated with a view. They are most
 * often used as a convenience to store data related to views in the views
 * themselves rather than by putting them in a separate structure.
 * </p>
 * <p>
 * Tags may be specified with character sequence values in layout XML as either
 * a single tag using the {@link android.R.styleable#View_tag android:tag}
 * attribute or multiple tags using the {@code <tag>} child element:
 * <pre>
 *     &lt;View ...
 *           android:tag="@string/mytag_value" /&gt;
 *     &lt;View ...&gt;
 *         &lt;tag android:id="@+id/mytag"
 *              android:value="@string/mytag_value" /&gt;
 *     &lt;/View>
 * </pre>
 * </p>
 * <p>
 * Tags may also be specified with arbitrary objects from code using
 * {@link #setTag(Object)} or {@link #setTag(int, Object)}.
 * </p>
 *
 * <a name="Themes"></a>
 * <h3>Themes</h3>
 * <p>
 * By default, Views are created using the theme of the Context object supplied
 * to their constructor; however, a different theme may be specified by using
 * the {@link android.R.styleable#View_theme android:theme} attribute in layout
 * XML or by passing a {@link ContextThemeWrapper} to the constructor from
 * code.
 * </p>
 * <p>
 * When the {@link android.R.styleable#View_theme android:theme} attribute is
 * used in XML, the specified theme is applied on top of the inflation
 * context's theme (see {@link LayoutInflater}) and used for the view itself as
 * well as any child elements.
 * </p>
 * <p>
 * In the following example, both views will be created using the Material dark
 * color scheme; however, because an overlay theme is used which only defines a
 * subset of attributes, the value of
 * {@link android.R.styleable#Theme_colorAccent android:colorAccent} defined on
 * the inflation context's theme (e.g. the Activity theme) will be preserved.
 * <pre>
 *     &lt;LinearLayout
 *             ...
 *             android:theme="@android:theme/ThemeOverlay.Material.Dark"&gt;
 *         &lt;View ...&gt;
 *     &lt;/LinearLayout&gt;
 * </pre>
 * </p>
 *
 * <a name="Properties"></a>
 * <h3>Properties</h3>
 * <p>
 * The View class exposes an {@link #ALPHA} property, as well as several transform-related
 * properties, such as {@link #TRANSLATION_X} and {@link #TRANSLATION_Y}. These properties are
 * available both in the {@link Property} form as well as in similarly-named setter/getter
 * methods (such as {@link #setAlpha(float)} for {@link #ALPHA}). These properties can
 * be used to set persistent state associated with these rendering-related properties on the view.
 * The properties and methods can also be used in conjunction with
 * {@link android.animation.Animator Animator}-based animations, described more in the
 * <a href="#Animation">Animation</a> section.
 * </p>
 *
 * <a name="Animation"></a>
 * <h3>Animation</h3>
 * <p>
 * Starting with Android 3.0, the preferred way of animating views is to use the
 * {@link android.animation} package APIs. These {@link android.animation.Animator Animator}-based
 * classes change actual properties of the View object, such as {@link #setAlpha(float) alpha} and
 * {@link #setTranslationX(float) translationX}. This behavior is contrasted to that of the pre-3.0
 * {@link android.view.animation.Animation Animation}-based classes, which instead animate only
 * how the view is drawn on the display. In particular, the {@link ViewPropertyAnimator} class
 * makes animating these View properties particularly easy and efficient.
 * </p>
 * <p>
 * Alternatively, you can use the pre-3.0 animation classes to animate how Views are rendered.
 * You can attach an {@link Animation} object to a view using
 * {@link #setAnimation(Animation)} or
 * {@link #startAnimation(Animation)}. The animation can alter the scale,
 * rotation, translation and alpha of a view over time. If the animation is
 * attached to a view that has children, the animation will affect the entire
 * subtree rooted by that node. When an animation is started, the framework will
 * take care of redrawing the appropriate views until the animation completes.
 * </p>
 *
 * <a name="Security"></a>
 * <h3>Security</h3>
 * <p>
 * Sometimes it is essential that an application be able to verify that an action
 * is being performed with the full knowledge and consent of the user, such as
 * granting a permission request, making a purchase or clicking on an advertisement.
 * Unfortunately, a malicious application could try to spoof the user into
 * performing these actions, unaware, by concealing the intended purpose of the view.
 * As a remedy, the framework offers a touch filtering mechanism that can be used to
 * improve the security of views that provide access to sensitive functionality.
 * </p><p>
 * To enable touch filtering, call {@link #setFilterTouchesWhenObscured(boolean)} or set the
 * android:filterTouchesWhenObscured layout attribute to true.  When enabled, the framework
 * will discard touches that are received whenever the view's window is obscured by
 * another visible window at the touched location.  As a result, the view will not receive touches
 * whenever the touch passed through a toast, dialog or other window that appears above the view's
 * window.
 * </p><p>
 * For more fine-grained control over security, consider overriding the
 * {@link #onFilterTouchEventForSecurity(MotionEvent)} method to implement your own
 * security policy. See also {@link MotionEvent#FLAG_WINDOW_IS_OBSCURED}.
 * </p>
 *
 * @attr ref android.R.styleable#View_accessibilityHeading
 * @attr ref android.R.styleable#View_allowClickWhenDisabled
 * @attr ref android.R.styleable#View_alpha
 * @attr ref android.R.styleable#View_background
 * @attr ref android.R.styleable#View_clickable
 * @attr ref android.R.styleable#View_clipToOutline
 * @attr ref android.R.styleable#View_contentDescription
 * @attr ref android.R.styleable#View_drawingCacheQuality
 * @attr ref android.R.styleable#View_duplicateParentState
 * @attr ref android.R.styleable#View_id
 * @attr ref android.R.styleable#View_requiresFadingEdge
 * @attr ref android.R.styleable#View_fadeScrollbars
 * @attr ref android.R.styleable#View_fadingEdgeLength
 * @attr ref android.R.styleable#View_filterTouchesWhenObscured
 * @attr ref android.R.styleable#View_fitsSystemWindows
 * @attr ref android.R.styleable#View_isScrollContainer
 * @attr ref android.R.styleable#View_focusable
 * @attr ref android.R.styleable#View_focusableInTouchMode
 * @attr ref android.R.styleable#View_focusedByDefault
 * @attr ref android.R.styleable#View_hapticFeedbackEnabled
 * @attr ref android.R.styleable#View_keepScreenOn
 * @attr ref android.R.styleable#View_keyboardNavigationCluster
 * @attr ref android.R.styleable#View_layerType
 * @attr ref android.R.styleable#View_layoutDirection
 * @attr ref android.R.styleable#View_longClickable
 * @attr ref android.R.styleable#View_minHeight
 * @attr ref android.R.styleable#View_minWidth
 * @attr ref android.R.styleable#View_nextClusterForward
 * @attr ref android.R.styleable#View_nextFocusDown
 * @attr ref android.R.styleable#View_nextFocusLeft
 * @attr ref android.R.styleable#View_nextFocusRight
 * @attr ref android.R.styleable#View_nextFocusUp
 * @attr ref android.R.styleable#View_onClick
 * @attr ref android.R.styleable#View_outlineSpotShadowColor
 * @attr ref android.R.styleable#View_outlineAmbientShadowColor
 * @attr ref android.R.styleable#View_padding
 * @attr ref android.R.styleable#View_paddingHorizontal
 * @attr ref android.R.styleable#View_paddingVertical
 * @attr ref android.R.styleable#View_paddingBottom
 * @attr ref android.R.styleable#View_paddingLeft
 * @attr ref android.R.styleable#View_paddingRight
 * @attr ref android.R.styleable#View_paddingTop
 * @attr ref android.R.styleable#View_paddingStart
 * @attr ref android.R.styleable#View_paddingEnd
 * @attr ref android.R.styleable#View_saveEnabled
 * @attr ref android.R.styleable#View_rotation
 * @attr ref android.R.styleable#View_rotationX
 * @attr ref android.R.styleable#View_rotationY
 * @attr ref android.R.styleable#View_scaleX
 * @attr ref android.R.styleable#View_scaleY
 * @attr ref android.R.styleable#View_scrollX
 * @attr ref android.R.styleable#View_scrollY
 * @attr ref android.R.styleable#View_scrollbarSize
 * @attr ref android.R.styleable#View_scrollbarStyle
 * @attr ref android.R.styleable#View_scrollbars
 * @attr ref android.R.styleable#View_scrollbarDefaultDelayBeforeFade
 * @attr ref android.R.styleable#View_scrollbarFadeDuration
 * @attr ref android.R.styleable#View_scrollbarTrackHorizontal
 * @attr ref android.R.styleable#View_scrollbarThumbHorizontal
 * @attr ref android.R.styleable#View_scrollbarThumbVertical
 * @attr ref android.R.styleable#View_scrollbarTrackVertical
 * @attr ref android.R.styleable#View_scrollbarAlwaysDrawHorizontalTrack
 * @attr ref android.R.styleable#View_scrollbarAlwaysDrawVerticalTrack
 * @attr ref android.R.styleable#View_stateListAnimator
 * @attr ref android.R.styleable#View_transitionName
 * @attr ref android.R.styleable#View_soundEffectsEnabled
 * @attr ref android.R.styleable#View_tag
 * @attr ref android.R.styleable#View_textAlignment
 * @attr ref android.R.styleable#View_textDirection
 * @attr ref android.R.styleable#View_transformPivotX
 * @attr ref android.R.styleable#View_transformPivotY
 * @attr ref android.R.styleable#View_translationX
 * @attr ref android.R.styleable#View_translationY
 * @attr ref android.R.styleable#View_translationZ
 * @attr ref android.R.styleable#View_visibility
 * @attr ref android.R.styleable#View_theme
 *
 * @see android.view.ViewGroup
 */
public final class View {
    /**
     * Automatically determine whether the view should only allow interactions from
     * {@link android.accessibilityservice.AccessibilityService}s with the
     * {@link android.accessibilityservice.AccessibilityServiceInfo#isAccessibilityTool} property
     * set to true.
     *
     * <p>
     * Accessibility interactions from services without {@code isAccessibilityTool} set to true are
     * disallowed for any of the following conditions:
     * <li>this view sets {@link #getFilterTouchesWhenObscured()}.</li>
     * <li>any parent of this view returns true from {@link #isAccessibilityDataSensitive()}.</li>
     * </p>
     */
    public static final int ACCESSIBILITY_DATA_SENSITIVE_AUTO = 0;

    /**
     * Allow interactions from all {@link android.accessibilityservice.AccessibilityService}s,
     * regardless of their
     * {@link android.accessibilityservice.AccessibilityServiceInfo#isAccessibilityTool} property.
     */
    public static final int ACCESSIBILITY_DATA_SENSITIVE_NO = 2;

    /**
     * Only allow interactions from {@link android.accessibilityservice.AccessibilityService}s
     * with the {@link android.accessibilityservice.AccessibilityServiceInfo#isAccessibilityTool}
     * property set to true.
     */
    public static final int ACCESSIBILITY_DATA_SENSITIVE_YES = 1;

    /**
     * Live region mode specifying that accessibility services should immediately notify users of
     * changes to this view. For example, a screen reader may interrupt ongoing speech to
     * immediately announce these changes.
     * <p>
     * Use with {@link #setAccessibilityLiveRegion(int)}.
     */
    public static final int ACCESSIBILITY_LIVE_REGION_ASSERTIVE = 2;

    /**
     * Live region mode specifying that accessibility services should not
     * automatically announce changes to this view. This is the default live
     * region mode for most views.
     * <p>
     * Use with {@link #setAccessibilityLiveRegion(int)}.
     */
    public static final int ACCESSIBILITY_LIVE_REGION_NONE = 0;

    /**
     * Live region mode specifying that accessibility services should notify users of changes to
     * this view.
     * <p>
     * Use with {@link #setAccessibilityLiveRegion(int)}.
     */
    public static final int ACCESSIBILITY_LIVE_REGION_POLITE = 1;

    /**
     * Flag requesting you to add views that are marked as not important for autofill
     * (see {@link #setImportantForAutofill(int)}) to a {@link ViewStructure}.
     */
    public static final int AUTOFILL_FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 1;

    /**
     * Hint indicating that this view can be autofilled with a credit card expiration date.
     *
     * <p>It should be used when the credit card expiration date is represented by just one view;
     * if it is represented by more than one (for example, one view for the month and another view
     * for the year), then each of these views should use the hint specific for the unit
     * ({@link #AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_DAY},
     * {@link #AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_MONTH},
     * or {@link #AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_YEAR}).
     *
     * <p>Can be used with either {@link #setAutofillHints(String[])} or
     * <a href="#attr_android:autofillHint"> {@code android:autofillHint}</a> (in which case the
     * value should be <code>{@value #AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_DATE}</code>).
     *
     * <p>When annotating a view with this hint, it's recommended to use a date autofill value to
     * avoid ambiguity when the autofill service provides a value for it. To understand why a
     * value can be ambiguous, consider "April of 2020", which could be represented as either of
     * the following options:
     *
     * <ul>
     *   <li>{@code "04/2020"}
     *   <li>{@code "4/2020"}
     *   <li>{@code "2020/04"}
     *   <li>{@code "2020/4"}
     *   <li>{@code "April/2020"}
     *   <li>{@code "Apr/2020"}
     * </ul>
     *
     * <p>You define a date autofill value for the view by overriding the following methods:
     *
     * <ol>
     *   <li>{@link #getAutofillType()} to return {@link #AUTOFILL_TYPE_DATE}.
     *   <li>{@link #getAutofillValue()} to return a
     *       {@link AutofillValue#forDate(long) date autofillvalue}.
     *   <li>{@link #autofill(AutofillValue)} to expect a data autofillvalue.
     * </ol>
     *
     * <p>See {@link #setAutofillHints(String...)} for more info about autofill hints.
     */
    public static final String AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_DATE = "creditCardExpirationDate";

    /**
     * Hint indicating that this view can be autofilled with a credit card expiration day.
     *
     * <p>Can be used with either {@link #setAutofillHints(String[])} or
     * <a href="#attr_android:autofillHint"> {@code android:autofillHint}</a> (in which case the
     * value should be <code>{@value #AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_DAY}</code>).
     *
     * <p>See {@link #setAutofillHints(String...)} for more info about autofill hints.
     */
    public static final String AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_DAY = "creditCardExpirationDay";

    /**
     * Hint indicating that this view can be autofilled with a credit card expiration month.
     *
     * <p>Can be used with either {@link #setAutofillHints(String[])} or
     * <a href="#attr_android:autofillHint"> {@code android:autofillHint}</a> (in which case the
     * value should be <code>{@value #AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_MONTH}</code>).
     *
     * <p>When annotating a view with this hint, it's recommended to use a text autofill value
     * whose value is the numerical representation of the month, starting on {@code 1} to avoid
     * ambiguity when the autofill service provides a value for it. To understand why a
     * value can be ambiguous, consider "January", which could be represented as either of
     *
     * <ul>
     *   <li>{@code "1"}: recommended way.
     *   <li>{@code "0"}: if following the {@link Calendar#MONTH} convention.
     *   <li>{@code "January"}: full name, in English.
     *   <li>{@code "jan"}: abbreviated name, in English.
     *   <li>{@code "Janeiro"}: full name, in another language.
     * </ul>
     *
     * <p>Another recommended approach is to use a date autofill value - see
     * {@link #AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_DATE} for more details.
     *
     * <p>See {@link #setAutofillHints(String...)} for more info about autofill hints.
     */
    public static final String AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_MONTH = "creditCardExpirationMonth";

    /**
     * Hint indicating that this view can be autofilled with a credit card expiration year.
     *
     * <p>Can be used with either {@link #setAutofillHints(String[])} or
     * <a href="#attr_android:autofillHint"> {@code android:autofillHint}</a> (in which case the
     * value should be <code>{@value #AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_YEAR}</code>).
     *
     * <p>See {@link #setAutofillHints(String...)} for more info about autofill hints.
     */
    public static final String AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_YEAR = "creditCardExpirationYear";

    /**
     * Hint indicating that this view can be autofilled with a credit card number.
     *
     * <p>Can be used with either {@link #setAutofillHints(String[])} or
     * <a href="#attr_android:autofillHint"> {@code android:autofillHint}</a> (in which case the
     * value should be <code>{@value #AUTOFILL_HINT_CREDIT_CARD_NUMBER}</code>).
     *
     * <p>See {@link #setAutofillHints(String...)} for more info about autofill hints.
     */
    public static final String AUTOFILL_HINT_CREDIT_CARD_NUMBER = "creditCardNumber";

    /**
     * Hint indicating that this view can be autofilled with a credit card security code.
     *
     * <p>Can be used with either {@link #setAutofillHints(String[])} or
     * <a href="#attr_android:autofillHint"> {@code android:autofillHint}</a> (in which case the
     * value should be <code>{@value #AUTOFILL_HINT_CREDIT_CARD_SECURITY_CODE}</code>).
     *
     * <p>See {@link #setAutofillHints(String...)} for more info about autofill hints.
     */
    public static final String AUTOFILL_HINT_CREDIT_CARD_SECURITY_CODE = "creditCardSecurityCode";

    /**
     * Hint indicating that this view can be autofilled with an email address.
     *
     * <p>Can be used with either {@link #setAutofillHints(String[])} or
     * <a href="#attr_android:autofillHint"> {@code android:autofillHint}</a> (in which case the
     * value should be <code>{@value #AUTOFILL_HINT_EMAIL_ADDRESS}</code>).
     *
     * <p>See {@link #setAutofillHints(String...)} for more info about autofill hints.
     */
    public static final String AUTOFILL_HINT_EMAIL_ADDRESS = "emailAddress";

    /**
     * Hint indicating that this view can be autofilled with a user's real name.
     *
     * <p>Can be used with either {@link #setAutofillHints(String[])} or
     * <a href="#attr_android:autofillHint"> {@code android:autofillHint}</a> (in which case the
     * value should be <code>{@value #AUTOFILL_HINT_NAME}</code>).
     *
     * <p>See {@link #setAutofillHints(String...)} for more info about autofill hints.
     */
    public static final String AUTOFILL_HINT_NAME = "name";

    /**
     * Hint indicating that this view can be autofilled with a password.
     *
     * <p>Can be used with either {@link #setAutofillHints(String[])} or
     * <a href="#attr_android:autofillHint"> {@code android:autofillHint}</a> (in which case the
     * value should be <code>{@value #AUTOFILL_HINT_PASSWORD}</code>).
     *
     * <p>See {@link #setAutofillHints(String...)} for more info about autofill hints.
     */
    public static final String AUTOFILL_HINT_PASSWORD = "password";

    /**
     * Hint indicating that this view can be autofilled with a phone number.
     *
     * <p>Can be used with either {@link #setAutofillHints(String[])} or
     * <a href="#attr_android:autofillHint"> {@code android:autofillHint}</a> (in which case the
     * value should be <code>{@value #AUTOFILL_HINT_PHONE}</code>).
     *
     * <p>See {@link #setAutofillHints(String...)} for more info about autofill hints.
     */
    public static final String AUTOFILL_HINT_PHONE = "phone";

    /**
     * Hint indicating that this view can be autofilled with a postal address.
     *
     * <p>Can be used with either {@link #setAutofillHints(String[])} or
     * <a href="#attr_android:autofillHint"> {@code android:autofillHint}</a> (in which case the
     * value should be <code>{@value #AUTOFILL_HINT_POSTAL_ADDRESS}</code>).
     *
     * <p>See {@link #setAutofillHints(String...)} for more info about autofill hints.
     */
    public static final String AUTOFILL_HINT_POSTAL_ADDRESS = "postalAddress";

    /**
     * Hint indicating that this view can be autofilled with a postal code.
     *
     * <p>Can be used with either {@link #setAutofillHints(String[])} or
     * <a href="#attr_android:autofillHint"> {@code android:autofillHint}</a> (in which case the
     * value should be <code>{@value #AUTOFILL_HINT_POSTAL_CODE}</code>).
     *
     * <p>See {@link #setAutofillHints(String...)} for more info about autofill hints.
     */
    public static final String AUTOFILL_HINT_POSTAL_CODE = "postalCode";

    /**
     * Hint indicating that this view can be autofilled with a username.
     *
     * <p>Can be used with either {@link #setAutofillHints(String[])} or
     * <a href="#attr_android:autofillHint"> {@code android:autofillHint}</a> (in which case the
     * value should be <code>{@value #AUTOFILL_HINT_USERNAME}</code>).
     *
     * <p>See {@link #setAutofillHints(String...)} for more info about autofill hints.
     */
    public static final String AUTOFILL_HINT_USERNAME = "username";

    /**
     * Autofill type for a field that contains a date, which is represented by a long representing
     * the number of milliseconds since the standard base time known as "the epoch", namely
     * January 1, 1970, 00:00:00 GMT (see {@link java.util.Date#getTime()}.
     *
     * <p>{@link AutofillValue} instances for autofilling a {@link View} can be obtained through
     * {@link AutofillValue#forDate(long)}, and the values passed to
     * autofill a {@link View} can be fetched through {@link AutofillValue#getDateValue()}.
     *
     * @see #getAutofillType()
     */
    public static final int AUTOFILL_TYPE_DATE = 4;

    /**
     * Autofill type for a selection list field, which is filled by an {@code int}
     * representing the element index inside the list (starting at {@code 0}).
     *
     * <p>{@link AutofillValue} instances for autofilling a {@link View} can be obtained through
     * {@link AutofillValue#forList(int)}, and the value passed to autofill a
     * {@link View} can be fetched through {@link AutofillValue#getListValue()}.
     *
     * <p>The available options in the selection list are typically provided by
     * {@link android.app.assist.AssistStructure.ViewNode#getAutofillOptions()}.
     *
     * @see #getAutofillType()
     */
    public static final int AUTOFILL_TYPE_LIST = 3;

    /**
     * Autofill type for views that cannot be autofilled.
     *
     * <p>Typically used when the view is read-only; for example, a text label.
     *
     * @see #getAutofillType()
     */
    public static final int AUTOFILL_TYPE_NONE = 0;

    /**
     * Autofill type for a text field, which is filled by a {@link CharSequence}.
     *
     * <p>{@link AutofillValue} instances for autofilling a {@link View} can be obtained through
     * {@link AutofillValue#forText(CharSequence)}, and the value passed to autofill a
     * {@link View} can be fetched through {@link AutofillValue#getTextValue()}.
     *
     * @see #getAutofillType()
     */
    public static final int AUTOFILL_TYPE_TEXT = 1;

    /**
     * Autofill type for a togglable field, which is filled by a {@code boolean}.
     *
     * <p>{@link AutofillValue} instances for autofilling a {@link View} can be obtained through
     * {@link AutofillValue#forToggle(boolean)}, and the value passed to autofill a
     * {@link View} can be fetched through {@link AutofillValue#getToggleValue()}.
     *
     * @see #getAutofillType()
     */
    public static final int AUTOFILL_TYPE_TOGGLE = 2;

    /**
     * Content sensitivity is determined by the framework. The framework uses a heuristic to
     * determine if this view displays sensitive content.
     * Autofill hints i.e. {@link #getAutofillHints()}  are used in the heuristic
     * to determine if this view should be considered as a sensitive view.
     * <p>
     * {@link #AUTOFILL_HINT_USERNAME},
     * {@link #AUTOFILL_HINT_PASSWORD},
     * {@link #AUTOFILL_HINT_CREDIT_CARD_NUMBER},
     * {@link #AUTOFILL_HINT_CREDIT_CARD_SECURITY_CODE},
     * {@link #AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_DATE},
     * {@link #AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_DAY},
     * {@link #AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_MONTH},
     * {@link #AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_YEAR}
     * are considered sensitive hints by the framework, and the list may include more hints
     * in the future.
     *
     * <p> The window hosting a sensitive view will be marked as secure during an active media
     * projection session. This would be equivalent to applying
     * {@link android.view.WindowManager.LayoutParams#FLAG_SECURE} to the window.
     *
     * @see #getContentSensitivity()
     */
    public static final int CONTENT_SENSITIVITY_AUTO = 0;

    /**
     * The view doesn't display sensitive content.
     *
     * @see #getContentSensitivity()
     */
    public static final int CONTENT_SENSITIVITY_NOT_SENSITIVE = 2;

    /**
     * The view displays sensitive content.
     *
     * <p> The window hosting a sensitive view will be marked as secure during an active media
     * projection session. This would be equivalent to applying
     * {@link android.view.WindowManager.LayoutParams#FLAG_SECURE} to the window.
     *
     * @see #getContentSensitivity()
     */
    public static final int CONTENT_SENSITIVITY_SENSITIVE = 1;

    /**
     * Flag indicating that the drag was initiated with
     * {@link AccessibilityNodeInfo.AccessibilityAction#ACTION_DRAG_START}. When
     * {@link #startDragAndDrop(ClipData, DragShadowBuilder, Object, int)} is called, this
     * is used by the system to perform a drag without animations.
     */
    public static final int DRAG_FLAG_ACCESSIBILITY_ACTION = 1024;

    /**
     * Flag indicating that a drag can cross window boundaries.  When
     * {@link #startDragAndDrop(ClipData, DragShadowBuilder, Object, int)} is called
     * with this flag set, all visible applications with targetSdkVersion >=
     * {@link android.os.Build.VERSION_CODES#N API 24} will be able to participate
     * in the drag operation and receive the dragged content.
     *
     * <p>If this is the only flag set, then the drag recipient will only have access to text data
     * and intents contained in the {@link ClipData} object. Access to URIs contained in the
     * {@link ClipData} is determined by other DRAG_FLAG_GLOBAL_* flags</p>
     */
    public static final int DRAG_FLAG_GLOBAL = 256;

    /**
     * When this flag is used with {@link #DRAG_FLAG_GLOBAL_URI_READ} and/or {@link
     * #DRAG_FLAG_GLOBAL_URI_WRITE}, the URI permission grant can be persisted across device
     * reboots until explicitly revoked with
     * {@link android.content.Context#revokeUriPermission(Uri, int)} Context.revokeUriPermission}.
     * @see android.content.Intent#FLAG_GRANT_PERSISTABLE_URI_PERMISSION
     */
    public static final int DRAG_FLAG_GLOBAL_PERSISTABLE_URI_PERMISSION = 64;

    /**
     * When this flag is used with {@link #DRAG_FLAG_GLOBAL_URI_READ} and/or {@link
     * #DRAG_FLAG_GLOBAL_URI_WRITE}, the URI permission grant applies to any URI that is a prefix
     * match against the original granted URI.
     * @see android.content.Intent#FLAG_GRANT_PREFIX_URI_PERMISSION
     */
    public static final int DRAG_FLAG_GLOBAL_PREFIX_URI_PERMISSION = 128;

    /**
     * Flag indicating that a drag can cross window boundaries (within the same application).  When
     * {@link #startDragAndDrop(ClipData, DragShadowBuilder, Object, int)} is called
     * with this flag set, only visible windows belonging to the same application (ie. share the
     * same UID) with targetSdkVersion >= {@link android.os.Build.VERSION_CODES#N API 24} will be
     * able to participate in the drag operation and receive the dragged content.
     *
     * If both DRAG_FLAG_GLOBAL_SAME_APPLICATION and DRAG_FLAG_GLOBAL are set, then
     * DRAG_FLAG_GLOBAL_SAME_APPLICATION takes precedence and the drag will only go to visible
     * windows from the same application.
     */
    public static final int DRAG_FLAG_GLOBAL_SAME_APPLICATION = 4096;

    /**
     * When this flag is used with {@link #DRAG_FLAG_GLOBAL}, the drag recipient will be able to
     * request read access to the content URI(s) contained in the {@link ClipData} object.
     * @see android.content.Intent#FLAG_GRANT_READ_URI_PERMISSION
     */
    public static final int DRAG_FLAG_GLOBAL_URI_READ = 1;

    /**
     * When this flag is used with {@link #DRAG_FLAG_GLOBAL}, the drag recipient will be able to
     * request write access to the content URI(s) contained in the {@link ClipData} object.
     * @see android.content.Intent#FLAG_GRANT_WRITE_URI_PERMISSION
     */
    public static final int DRAG_FLAG_GLOBAL_URI_WRITE = 2;

    /**
     * Flag indicating that this drag will result in the caller activity's task to be hidden for the
     * duration of the drag, which means that the source activity will not receive drag events for
     * the current drag gesture. Only the current
     * {@link android.service.voice.VoiceInteractionService} may use this flag.
     */
    public static final int DRAG_FLAG_HIDE_CALLING_TASK_ON_DRAG_START = 16384;

    /**
     * Flag indicating that the drag shadow will be opaque.  When
     * {@link #startDragAndDrop(ClipData, DragShadowBuilder, Object, int)} is called
     * with this flag set, the drag shadow will be opaque, otherwise, it will be semitransparent.
     */
    public static final int DRAG_FLAG_OPAQUE = 512;

    /**
     * Flag indicating that an unhandled drag should be delegated to the system to be started if no
     * visible window wishes to handle the drop. When using this flag, the caller must provide
     * ClipData with an Item that contains an immutable IntentSender to an activity to be launched
     * (not a broadcast, service, etc).  See
     * {@link ClipData.Item.Builder#setIntentSender(IntentSender)}.
     *
     * The system can decide to launch the intent or not based on factors like the current screen
     * size or windowing mode. If the system does not launch the intent, it will be canceled via the
     * normal drag and drop flow.
     */
    public static final int DRAG_FLAG_START_INTENT_SENDER_ON_UNHANDLED_DRAG = 8192;

    /**
     * <p>Enables automatic quality mode for the drawing cache.</p>
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
    public static final int DRAWING_CACHE_QUALITY_AUTO = 0;

    /**
     * <p>Enables high quality mode for the drawing cache.</p>
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
    public static final int DRAWING_CACHE_QUALITY_HIGH = 1048576;

    /**
     * <p>Enables low quality mode for the drawing cache.</p>
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
    public static final int DRAWING_CACHE_QUALITY_LOW = 524288;

    /**
     * Find find views that contain the specified content description.
     *
     * @see #findViewsWithText(ArrayList, CharSequence, int)
     */
    public static final int FIND_VIEWS_WITH_CONTENT_DESCRIPTION = 2;

    /**
     * Find views that render the specified text.
     *
     * @see #findViewsWithText(ArrayList, CharSequence, int)
     */
    public static final int FIND_VIEWS_WITH_TEXT = 1;

    /**
     * This view wants keystrokes.
     * <p>
     * Use with {@link #setFocusable(int)} and <a href="#attr_android:focusable">{@code
     * android:focusable}.
     */
    public static final int FOCUSABLE = 1;

    /**
     * View flag indicating whether {@link #addFocusables(ArrayList, int, int)}
     * should add all focusable Views regardless if they are focusable in touch mode.
     */
    public static final int FOCUSABLES_ALL = 0;

    /**
     * View flag indicating whether {@link #addFocusables(ArrayList, int, int)}
     * should add only Views focusable in touch mode.
     */
    public static final int FOCUSABLES_TOUCH_MODE = 1;

    /**
     * This view determines focusability automatically. This is the default.
     * <p>
     * Use with {@link #setFocusable(int)} and <a href="#attr_android:focusable">{@code
     * android:focusable}.
     */
    public static final int FOCUSABLE_AUTO = 16;

    /**
     * Use with {@link #focusSearch(int)}. Move focus to the previous selectable
     * item.
     */
    public static final int FOCUS_BACKWARD = 1;

    /**
     * Use with {@link #focusSearch(int)}. Move focus down.
     */
    public static final int FOCUS_DOWN = 130;

    /**
     * Use with {@link #focusSearch(int)}. Move focus to the next selectable
     * item.
     */
    public static final int FOCUS_FORWARD = 2;

    /**
     * Use with {@link #focusSearch(int)}. Move focus to the left.
     */
    public static final int FOCUS_LEFT = 17;

    /**
     * Use with {@link #focusSearch(int)}. Move focus to the right.
     */
    public static final int FOCUS_RIGHT = 66;

    /**
     * Use with {@link #focusSearch(int)}. Move focus up.
     */
    public static final int FOCUS_UP = 33;

    /**
     * This view is invisible, and it doesn't take any space for layout
     * purposes. Use with {@link #setVisibility} and <a href="#attr_android:visibility">{@code
     * android:visibility}.
     */
    public static final int GONE = 8;

    /**
     * View flag indicating whether this view should have haptic feedback
     * enabled for events such as long presses.
     */
    public static final int HAPTIC_FEEDBACK_ENABLED = 268435456;

    /**
     * Automatically determine whether a view is important for accessibility.
     */
    public static final int IMPORTANT_FOR_ACCESSIBILITY_AUTO = 0;

    /**
     * The view is not important for accessibility.
     */
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO = 2;

    /**
     * The view is not important for accessibility, nor are any of its
     * descendant views.
     */
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS = 4;

    /**
     * The view is important for accessibility.
     */
    public static final int IMPORTANT_FOR_ACCESSIBILITY_YES = 1;

    /**
     * Automatically determine whether a view is important for autofill.
     *
     * @see #isImportantForAutofill()
     * @see #setImportantForAutofill(int)
     */
    public static final int IMPORTANT_FOR_AUTOFILL_AUTO = 0;

    /**
     * The view is not important for autofill, but its children (if any) will be traversed.
     *
     * @see #isImportantForAutofill()
     * @see #setImportantForAutofill(int)
     */
    public static final int IMPORTANT_FOR_AUTOFILL_NO = 2;

    /**
     * The view is not important for autofill, and its children (if any) will not be traversed.
     *
     * @see #isImportantForAutofill()
     * @see #setImportantForAutofill(int)
     */
    public static final int IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS = 8;

    /**
     * The view is important for autofill, and its children (if any) will be traversed.
     *
     * @see #isImportantForAutofill()
     * @see #setImportantForAutofill(int)
     */
    public static final int IMPORTANT_FOR_AUTOFILL_YES = 1;

    /**
     * The view is important for autofill, but its children (if any) will not be traversed.
     *
     * @see #isImportantForAutofill()
     * @see #setImportantForAutofill(int)
     */
    public static final int IMPORTANT_FOR_AUTOFILL_YES_EXCLUDE_DESCENDANTS = 4;

    /**
     * Automatically determine whether a view is important for content capture.
     *
     * @see #isImportantForContentCapture()
     * @see #setImportantForContentCapture(int)
     */
    public static final int IMPORTANT_FOR_CONTENT_CAPTURE_AUTO = 0;

    /**
     * The view is not important for content capture, but its children (if any) will be traversed.
     *
     * @see #isImportantForContentCapture()
     * @see #setImportantForContentCapture(int)
     */
    public static final int IMPORTANT_FOR_CONTENT_CAPTURE_NO = 2;

    /**
     * The view is not important for content capture, and its children (if any) will not be
     * traversed.
     *
     * @see #isImportantForContentCapture()
     * @see #setImportantForContentCapture(int)
     */
    public static final int IMPORTANT_FOR_CONTENT_CAPTURE_NO_EXCLUDE_DESCENDANTS = 8;

    /**
     * The view is important for content capture, and its children (if any) will be traversed.
     *
     * @see #isImportantForContentCapture()
     * @see #setImportantForContentCapture(int)
     */
    public static final int IMPORTANT_FOR_CONTENT_CAPTURE_YES = 1;

    /**
     * The view is important for content capture, but its children (if any) will not be traversed.
     *
     * @see #isImportantForContentCapture()
     * @see #setImportantForContentCapture(int)
     */
    public static final int IMPORTANT_FOR_CONTENT_CAPTURE_YES_EXCLUDE_DESCENDANTS = 4;

    /**
     * This view is invisible, but it still takes up space for layout purposes.
     * Use with {@link #setVisibility} and <a href="#attr_android:visibility">{@code
     * android:visibility}.
     */
    public static final int INVISIBLE = 4;

    /**
     * View flag indicating that the screen should remain on while the
     * window containing this view is visible to the user.  This effectively
     * takes care of automatically setting the WindowManager's
     * {@link WindowManager.LayoutParams#FLAG_KEEP_SCREEN_ON}.
     */
    public static final int KEEP_SCREEN_ON = 67108864;

    /**
     * <p>Indicates that the view has a hardware layer. A hardware layer is backed
     * by a hardware specific texture (generally Frame Buffer Objects or FBO on
     * OpenGL hardware) and causes the view to be rendered using Android's hardware
     * rendering pipeline, but only if hardware acceleration is turned on for the
     * view hierarchy. When hardware acceleration is turned off, hardware layers
     * behave exactly as {@link #LAYER_TYPE_SOFTWARE software layers}.</p>
     *
     * <p>A hardware layer is useful to apply a specific color filter and/or
     * blending mode and/or translucency to a view and all its children.</p>
     * <p>A hardware layer can be used to cache a complex view tree into a
     * texture and reduce the complexity of drawing operations. For instance,
     * when animating a complex view tree with a translation, a hardware layer can
     * be used to render the view tree only once.</p>
     * <p>A hardware layer can also be used to increase the rendering quality when
     * rotation transformations are applied on a view. It can also be used to
     * prevent potential clipping issues when applying 3D transforms on a view.</p>
     *
     * @see #getLayerType()
     * @see #setLayerType(int, android.graphics.Paint)
     * @see #LAYER_TYPE_NONE
     * @see #LAYER_TYPE_SOFTWARE
     */
    public static final int LAYER_TYPE_HARDWARE = 2;

    /**
     * Indicates that the view does not have a layer.
     *
     * @see #getLayerType()
     * @see #setLayerType(int, android.graphics.Paint)
     * @see #LAYER_TYPE_SOFTWARE
     * @see #LAYER_TYPE_HARDWARE
     */
    public static final int LAYER_TYPE_NONE = 0;

    /**
     * <p>Indicates that the view has a software layer. A software layer is backed
     * by a bitmap and causes the view to be rendered using Android's software
     * rendering pipeline, even if hardware acceleration is enabled.</p>
     *
     * <p>Software layers have various usages:</p>
     * <p>When the application is not using hardware acceleration, a software layer
     * is useful to apply a specific color filter and/or blending mode and/or
     * translucency to a view and all its children.</p>
     * <p>When the application is using hardware acceleration, a software layer
     * is useful to render drawing primitives not supported by the hardware
     * accelerated pipeline. It can also be used to cache a complex view tree
     * into a texture and reduce the complexity of drawing operations. For instance,
     * when animating a complex view tree with a translation, a software layer can
     * be used to render the view tree only once.</p>
     * <p>Software layers should be avoided when the affected view tree updates
     * often. Every update will require to re-render the software layer, which can
     * potentially be slow (particularly when hardware acceleration is turned on
     * since the layer will have to be uploaded into a hardware texture after every
     * update.)</p>
     *
     * @see #getLayerType()
     * @see #setLayerType(int, android.graphics.Paint)
     * @see #LAYER_TYPE_NONE
     * @see #LAYER_TYPE_HARDWARE
     */
    public static final int LAYER_TYPE_SOFTWARE = 1;

    /**
     * Horizontal layout direction of this view is inherited from its parent.
     * Use with {@link #setLayoutDirection}.
     */
    public static final int LAYOUT_DIRECTION_INHERIT = 2;

    /**
     * Horizontal layout direction of this view is from deduced from the default language
     * script for the locale. Use with {@link #setLayoutDirection}.
     */
    public static final int LAYOUT_DIRECTION_LOCALE = 3;

    /**
     * Horizontal layout direction of this view is from Left to Right.
     * Use with {@link #setLayoutDirection}.
     */
    public static final int LAYOUT_DIRECTION_LTR = 0;

    /**
     * Horizontal layout direction of this view is from Right to Left.
     * Use with {@link #setLayoutDirection}.
     */
    public static final int LAYOUT_DIRECTION_RTL = 1;

    /**
     * Bit shift of {@link #MEASURED_STATE_MASK} to get to the height bits
     * for functions that combine both width and height into a single int,
     * such as {@link #getMeasuredState()} and the childState argument of
     * {@link #resolveSizeAndState(int, int, int)}.
     */
    public static final int MEASURED_HEIGHT_STATE_SHIFT = 16;

    /**
     * Bits of {@link #getMeasuredWidthAndState()} and
     * {@link #getMeasuredWidthAndState()} that provide the actual measured size.
     */
    public static final int MEASURED_SIZE_MASK = 16777215;

    /**
     * Bits of {@link #getMeasuredWidthAndState()} and
     * {@link #getMeasuredWidthAndState()} that provide the additional state bits.
     */
    public static final int MEASURED_STATE_MASK = -16777216;

    /**
     * Bit of {@link #getMeasuredWidthAndState()} and
     * {@link #getMeasuredWidthAndState()} that indicates the measured size
     * is smaller that the space the view would like to have.
     */
    public static final int MEASURED_STATE_TOO_SMALL = 16777216;

    /**
     * This view does not want keystrokes.
     * <p>
     * Use with {@link #setFocusable(int)} and <a href="#attr_android:focusable">{@code
     * android:focusable}.
     */
    public static final int NOT_FOCUSABLE = 0;

    /**
     * Used to mark a View that has no ID.
     */
    public static final int NO_ID = -1;

    /**
     * Always allow a user to over-scroll this view, provided it is a
     * view that can scroll.
     *
     * @see #getOverScrollMode()
     * @see #setOverScrollMode(int)
     */
    public static final int OVER_SCROLL_ALWAYS = 0;

    /**
     * Allow a user to over-scroll this view only if the content is large
     * enough to meaningfully scroll, provided it is a view that can scroll.
     *
     * @see #getOverScrollMode()
     * @see #setOverScrollMode(int)
     */
    public static final int OVER_SCROLL_IF_CONTENT_SCROLLS = 1;

    /**
     * Never allow a user to over-scroll this view.
     *
     * @see #getOverScrollMode()
     * @see #setOverScrollMode(int)
     */
    public static final int OVER_SCROLL_NEVER = 2;

    public static final float REQUESTED_FRAME_RATE_CATEGORY_DEFAULT = Float.NaN;

    public static final float REQUESTED_FRAME_RATE_CATEGORY_HIGH = -4.0f;

    public static final float REQUESTED_FRAME_RATE_CATEGORY_LOW = -2.0f;

    public static final float REQUESTED_FRAME_RATE_CATEGORY_NORMAL = -3.0f;

    public static final float REQUESTED_FRAME_RATE_CATEGORY_NO_PREFERENCE = -1.0f;

    /**
     * Indicates that the screen has changed state and is now off.
     *
     * @see #onScreenStateChanged(int)
     */
    public static final int SCREEN_STATE_OFF = 0;

    /**
     * Indicates that the screen has changed state and is now on.
     *
     * @see #onScreenStateChanged(int)
     */
    public static final int SCREEN_STATE_ON = 1;

    /**
     * The scrollbar style to display the scrollbars inside the padded area,
     * increasing the padding of the view. The scrollbars will not overlap the
     * content area of the view.
     */
    public static final int SCROLLBARS_INSIDE_INSET = 16777216;

    /**
     * The scrollbar style to display the scrollbars inside the content area,
     * without increasing the padding. The scrollbars will be overlaid with
     * translucency on the view's content.
     */
    public static final int SCROLLBARS_INSIDE_OVERLAY = 0;

    /**
     * The scrollbar style to display the scrollbars at the edge of the view,
     * increasing the padding of the view. The scrollbars will only overlap the
     * background, if any.
     */
    public static final int SCROLLBARS_OUTSIDE_INSET = 50331648;

    /**
     * The scrollbar style to display the scrollbars at the edge of the view,
     * without increasing the padding. The scrollbars will be overlaid with
     * translucency.
     */
    public static final int SCROLLBARS_OUTSIDE_OVERLAY = 33554432;

    /**
     * Position the scroll bar at the default position as determined by the system.
     */
    public static final int SCROLLBAR_POSITION_DEFAULT = 0;

    /**
     * Position the scroll bar along the left edge.
     */
    public static final int SCROLLBAR_POSITION_LEFT = 1;

    /**
     * Position the scroll bar along the right edge.
     */
    public static final int SCROLLBAR_POSITION_RIGHT = 2;

    /**
     * Indicates scrolling along the horizontal axis.
     */
    public static final int SCROLL_AXIS_HORIZONTAL = 1;

    /**
     * Indicates no axis of view scrolling.
     */
    public static final int SCROLL_AXIS_NONE = 0;

    /**
     * Indicates scrolling along the vertical axis.
     */
    public static final int SCROLL_AXIS_VERTICAL = 2;

    /**
     * The content of this view will be considered for scroll capture if scrolling is possible.
     *
     * @see #getScrollCaptureHint()
     * @see #setScrollCaptureHint(int)
     */
    public static final int SCROLL_CAPTURE_HINT_AUTO = 0;

    /**
     * Explicitly exclude this view as a potential scroll capture target. The system will not
     * consider it. Mutually exclusive with {@link #SCROLL_CAPTURE_HINT_INCLUDE}, which this flag
     * takes precedence over.
     *
     * @see #getScrollCaptureHint()
     * @see #setScrollCaptureHint(int)
     */
    public static final int SCROLL_CAPTURE_HINT_EXCLUDE = 1;

    /**
     * Explicitly exclude all children of this view as potential scroll capture targets. This view
     * is unaffected. Note: Excluded children are not considered, regardless of {@link
     * #SCROLL_CAPTURE_HINT_INCLUDE}.
     *
     * @see #getScrollCaptureHint()
     * @see #setScrollCaptureHint(int)
     */
    public static final int SCROLL_CAPTURE_HINT_EXCLUDE_DESCENDANTS = 4;

    /**
     * Explicitly include this view as a potential scroll capture target. When locating a scroll
     * capture target, this view will be prioritized before others without this flag. Mutually
     * exclusive with {@link #SCROLL_CAPTURE_HINT_EXCLUDE}, which takes precedence.
     *
     * @see #getScrollCaptureHint()
     * @see #setScrollCaptureHint(int)
     */
    public static final int SCROLL_CAPTURE_HINT_INCLUDE = 2;

    /**
     * Scroll indicator direction for the bottom edge of the view.
     *
     * @see #setScrollIndicators(int)
     * @see #setScrollIndicators(int, int)
     * @see #getScrollIndicators()
     */
    public static final int SCROLL_INDICATOR_BOTTOM = 2;

    /**
     * Scroll indicator direction for the ending edge of the view.
     * <p>
     * Resolved according to the view's layout direction, see
     * {@link #getLayoutDirection()} for more information.
     *
     * @see #setScrollIndicators(int)
     * @see #setScrollIndicators(int, int)
     * @see #getScrollIndicators()
     */
    public static final int SCROLL_INDICATOR_END = 32;

    /**
     * Scroll indicator direction for the left edge of the view.
     *
     * @see #setScrollIndicators(int)
     * @see #setScrollIndicators(int, int)
     * @see #getScrollIndicators()
     */
    public static final int SCROLL_INDICATOR_LEFT = 4;

    /**
     * Scroll indicator direction for the right edge of the view.
     *
     * @see #setScrollIndicators(int)
     * @see #setScrollIndicators(int, int)
     * @see #getScrollIndicators()
     */
    public static final int SCROLL_INDICATOR_RIGHT = 8;

    /**
     * Scroll indicator direction for the starting edge of the view.
     * <p>
     * Resolved according to the view's layout direction, see
     * {@link #getLayoutDirection()} for more information.
     *
     * @see #setScrollIndicators(int)
     * @see #setScrollIndicators(int, int)
     * @see #getScrollIndicators()
     */
    public static final int SCROLL_INDICATOR_START = 16;

    /**
     * Scroll indicator direction for the top edge of the view.
     *
     * @see #setScrollIndicators(int)
     * @see #setScrollIndicators(int, int)
     * @see #getScrollIndicators()
     */
    public static final int SCROLL_INDICATOR_TOP = 1;

    /**
     * View flag indicating whether this view should have sound effects enabled
     * for events such as clicking and touching.
     */
    public static final int SOUND_EFFECTS_ENABLED = 134217728;

    /**
     * @deprecated Use {@link #SYSTEM_UI_FLAG_LOW_PROFILE} instead.
     */
    @Deprecated
    public static final int STATUS_BAR_HIDDEN = 1;

    /**
     * @deprecated Use {@link #SYSTEM_UI_FLAG_VISIBLE} instead.
     */
    @Deprecated
    public static final int STATUS_BAR_VISIBLE = 0;

    /**
     * Flag for {@link #setSystemUiVisibility(int)}: View has requested to go
     * into the normal fullscreen mode so that its content can take over the screen
     * while still allowing the user to interact with the application.
     *
     * <p>This has the same visual effect as
     * {@link android.view.WindowManager.LayoutParams#FLAG_FULLSCREEN
     * WindowManager.LayoutParams.FLAG_FULLSCREEN},
     * meaning that non-critical screen decorations (such as the status bar) will be
     * hidden while the user is in the View's window, focusing the experience on
     * that content.  Unlike the window flag, if you are using ActionBar in
     * overlay mode with {@link Window#FEATURE_ACTION_BAR_OVERLAY
     * Window.FEATURE_ACTION_BAR_OVERLAY}, then enabling this flag will also
     * hide the action bar.
     *
     * <p>This approach to going fullscreen is best used over the window flag when
     * it is a transient state -- that is, the application does this at certain
     * points in its user interaction where it wants to allow the user to focus
     * on content, but not as a continuous state.  For situations where the application
     * would like to simply stay full screen the entire time (such as a game that
     * wants to take over the screen), the
     * {@link android.view.WindowManager.LayoutParams#FLAG_FULLSCREEN window flag}
     * is usually a better approach.  The state set here will be removed by the system
     * in various situations (such as the user moving to another application) like
     * the other system UI states.
     *
     * <p>When using this flag, the application should provide some easy facility
     * for the user to go out of it.  A common example would be in an e-book
     * reader, where tapping on the screen brings back whatever screen and UI
     * decorations that had been hidden while the user was immersed in reading
     * the book.
     *
     * @see #setSystemUiVisibility(int)
     * @deprecated Use {@link WindowInsetsController#hide(int)} with {@link Type#statusBars()}
     * instead.
     */
    @Deprecated
    public static final int SYSTEM_UI_FLAG_FULLSCREEN = 4;

    /**
     * Flag for {@link #setSystemUiVisibility(int)}: View has requested that the
     * system navigation be temporarily hidden.
     *
     * <p>This is an even less obtrusive state than that called for by
     * {@link #SYSTEM_UI_FLAG_LOW_PROFILE}; on devices that draw essential navigation controls
     * (Home, Back, and the like) on screen, <code>SYSTEM_UI_FLAG_HIDE_NAVIGATION</code> will cause
     * those to disappear. This is useful (in conjunction with the
     * {@link android.view.WindowManager.LayoutParams#FLAG_FULLSCREEN FLAG_FULLSCREEN} and
     * {@link android.view.WindowManager.LayoutParams#FLAG_LAYOUT_IN_SCREEN FLAG_LAYOUT_IN_SCREEN}
     * window flags) for displaying content using every last pixel on the display.
     *
     * <p>There is a limitation: because navigation controls are so important, the least user
     * interaction will cause them to reappear immediately.  When this happens, both
     * this flag and {@link #SYSTEM_UI_FLAG_FULLSCREEN} will be cleared automatically,
     * so that both elements reappear at the same time.
     *
     * @see #setSystemUiVisibility(int)
     * @deprecated Use {@link WindowInsetsController#hide(int)} with {@link Type#navigationBars()}
     * instead.
     */
    @Deprecated
    public static final int SYSTEM_UI_FLAG_HIDE_NAVIGATION = 2;

    /**
     * Flag for {@link #setSystemUiVisibility(int)}: View would like to remain interactive when
     * hiding the navigation bar with {@link #SYSTEM_UI_FLAG_HIDE_NAVIGATION}.  If this flag is
     * not set, {@link #SYSTEM_UI_FLAG_HIDE_NAVIGATION} will be force cleared by the system on any
     * user interaction.
     * <p>Since this flag is a modifier for {@link #SYSTEM_UI_FLAG_HIDE_NAVIGATION}, it only
     * has an effect when used in combination with that flag.</p>
     *
     * @deprecated Use {@link WindowInsetsController#BEHAVIOR_DEFAULT} instead.
     */
    @Deprecated
    public static final int SYSTEM_UI_FLAG_IMMERSIVE = 2048;

    /**
     * Flag for {@link #setSystemUiVisibility(int)}: View would like to remain interactive when
     * hiding the status bar with {@link #SYSTEM_UI_FLAG_FULLSCREEN} and/or hiding the navigation
     * bar with {@link #SYSTEM_UI_FLAG_HIDE_NAVIGATION}.  Use this flag to create an immersive
     * experience while also hiding the system bars.  If this flag is not set,
     * {@link #SYSTEM_UI_FLAG_HIDE_NAVIGATION} will be force cleared by the system on any user
     * interaction, and {@link #SYSTEM_UI_FLAG_FULLSCREEN} will be force-cleared by the system
     * if the user swipes from the top of the screen.
     * <p>When system bars are hidden in immersive mode, they can be revealed temporarily with
     * system gestures, such as swiping from the top of the screen.  These transient system bars
     * will overlay app's content, may have some degree of transparency, and will automatically
     * hide after a short timeout.
     * </p><p>Since this flag is a modifier for {@link #SYSTEM_UI_FLAG_FULLSCREEN} and
     * {@link #SYSTEM_UI_FLAG_HIDE_NAVIGATION}, it only has an effect when used in combination
     * with one or both of those flags.</p>
     *
     * @deprecated Use {@link WindowInsetsController#BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE} instead.
     */
    @Deprecated
    public static final int SYSTEM_UI_FLAG_IMMERSIVE_STICKY = 4096;

    /**
     * Flag for {@link #setSystemUiVisibility(int)}: View would like its window
     * to be laid out as if it has requested
     * {@link #SYSTEM_UI_FLAG_FULLSCREEN}, even if it currently hasn't.  This
     * allows it to avoid artifacts when switching in and out of that mode, at
     * the expense that some of its user interface may be covered by screen
     * decorations when they are shown.  You can perform layout of your inner
     * UI elements to account for non-fullscreen system UI through the
     * {@link #fitSystemWindows(Rect)} method.
     *
     * <p>Note: on displays that have a {@link DisplayCutout}, the window may still be placed
     *  differently than if {@link #SYSTEM_UI_FLAG_FULLSCREEN} was set, if the
     *  window's {@link WindowManager.LayoutParams#layoutInDisplayCutoutMode
     *  layoutInDisplayCutoutMode} is
     *  {@link WindowManager.LayoutParams#LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT
     *  LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT}. To avoid this, use either of the other modes.
     *
     * @see WindowManager.LayoutParams#layoutInDisplayCutoutMode
     * @see WindowManager.LayoutParams#LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT
     * @see WindowManager.LayoutParams#LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
     * @see WindowManager.LayoutParams#LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER
     *
     * @deprecated For floating windows, use {@link LayoutParams#setFitInsetsTypes(int)} with
     * {@link Type#statusBars()} ()}. For non-floating windows that fill the screen, call
     * {@link Window#setDecorFitsSystemWindows(boolean)} with {@code false}.
     */
    @Deprecated
    public static final int SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN = 1024;

    /**
     * Flag for {@link #setSystemUiVisibility(int)}: View would like its window
     * to be laid out as if it has requested
     * {@link #SYSTEM_UI_FLAG_HIDE_NAVIGATION}, even if it currently hasn't.  This
     * allows it to avoid artifacts when switching in and out of that mode, at
     * the expense that some of its user interface may be covered by screen
     * decorations when they are shown.  You can perform layout of your inner
     * UI elements to account for the navigation system UI through the
     * {@link #fitSystemWindows(Rect)} method.
     *
     * @deprecated For floating windows, use {@link LayoutParams#setFitInsetsTypes(int)} with
     * {@link Type#navigationBars()}. For non-floating windows that fill the screen, call
     * {@link Window#setDecorFitsSystemWindows(boolean)} with {@code false}.
     */
    @Deprecated
    public static final int SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION = 512;

    /**
     * Flag for {@link #setSystemUiVisibility(int)}: When using other layout
     * flags, we would like a stable view of the content insets given to
     * {@link #fitSystemWindows(Rect)}.  This means that the insets seen there
     * will always represent the worst case that the application can expect
     * as a continuous state.  In the stock Android UI this is the space for
     * the system bar, nav bar, and status bar, but not more transient elements
     * such as an input method.
     *
     * The stable layout your UI sees is based on the system UI modes you can
     * switch to.  That is, if you specify {@link #SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN}
     * then you will get a stable layout for changes of the
     * {@link #SYSTEM_UI_FLAG_FULLSCREEN} mode; if you specify
     * {@link #SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN} and
     * {@link #SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION}, then you can transition
     * to {@link #SYSTEM_UI_FLAG_FULLSCREEN} and {@link #SYSTEM_UI_FLAG_HIDE_NAVIGATION}
     * with a stable layout.  (Note that you should avoid using
     * {@link #SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION} by itself.)
     *
     * If you have set the window flag {@link WindowManager.LayoutParams#FLAG_FULLSCREEN}
     * to hide the status bar (instead of using {@link #SYSTEM_UI_FLAG_FULLSCREEN}),
     * then a hidden status bar will be considered a "stable" state for purposes
     * here.  This allows your UI to continually hide the status bar, while still
     * using the system UI flags to hide the action bar while still retaining
     * a stable layout.  Note that changing the window fullscreen flag will never
     * provide a stable layout for a clean transition.
     *
     * <p>If you are using ActionBar in
     * overlay mode with {@link Window#FEATURE_ACTION_BAR_OVERLAY
     * Window.FEATURE_ACTION_BAR_OVERLAY}, this flag will also impact the
     * insets it adds to those given to the application.
     *
     * @deprecated Use {@link WindowInsets#getInsetsIgnoringVisibility(int)} instead to retrieve
     * insets that don't change when system bars change visibility state.
     */
    @Deprecated
    public static final int SYSTEM_UI_FLAG_LAYOUT_STABLE = 256;

    /**
     * Flag for {@link #setSystemUiVisibility(int)}: Requests the navigation bar to draw in a mode
     * that is compatible with light navigation bar backgrounds.
     *
     * <p>For this to take effect, the window must request
     * {@link android.view.WindowManager.LayoutParams#FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
     *         FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS} but not
     * {@link android.view.WindowManager.LayoutParams#FLAG_TRANSLUCENT_NAVIGATION
     *         FLAG_TRANSLUCENT_NAVIGATION}.
     *
     * @see android.R.attr#windowLightNavigationBar
     * @deprecated Use {@link WindowInsetsController#APPEARANCE_LIGHT_NAVIGATION_BARS} instead.
     */
    @Deprecated
    public static final int SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR = 16;

    /**
     * Flag for {@link #setSystemUiVisibility(int)}: Requests the status bar to draw in a mode that
     * is compatible with light status bar backgrounds.
     *
     * <p>For this to take effect, the window must request
     * {@link android.view.WindowManager.LayoutParams#FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
     *         FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS} but not
     * {@link android.view.WindowManager.LayoutParams#FLAG_TRANSLUCENT_STATUS
     *         FLAG_TRANSLUCENT_STATUS}.
     *
     * @see android.R.attr#windowLightStatusBar
     * @deprecated Use {@link WindowInsetsController#APPEARANCE_LIGHT_STATUS_BARS} instead.
     */
    @Deprecated
    public static final int SYSTEM_UI_FLAG_LIGHT_STATUS_BAR = 8192;

    /**
     * Flag for {@link #setSystemUiVisibility(int)}: View has requested the
     * system UI to enter an unobtrusive "low profile" mode.
     *
     * <p>This is for use in games, book readers, video players, or any other
     * "immersive" application where the usual system chrome is deemed too distracting.
     *
     * <p>In low profile mode, the status bar and/or navigation icons may dim.
     *
     * @see #setSystemUiVisibility(int)
     * @deprecated Low profile mode is deprecated. Hide the system bars instead if the application
     * needs to be in a unobtrusive mode. Use {@link WindowInsetsController#hide(int)} with
     * {@link Type#systemBars()}.
     */
    @Deprecated
    public static final int SYSTEM_UI_FLAG_LOW_PROFILE = 1;

    /**
     * Special constant for {@link #setSystemUiVisibility(int)}: View has
     * requested the system UI (status bar) to be visible (the default).
     *
     * @see #setSystemUiVisibility(int)
     * @deprecated SystemUiVisibility flags are deprecated. Use {@link WindowInsetsController}
     * instead.
     */
    @Deprecated
    public static final int SYSTEM_UI_FLAG_VISIBLE = 0;

    /**
     * Flags that can impact the layout in relation to system UI.
     *
     * @deprecated System UI layout flags are deprecated.
     */
    @Deprecated
    public static final int SYSTEM_UI_LAYOUT_FLAGS = 1536;

    /**
     * Center the paragraph, e.g. ALIGN_CENTER.
     *
     * Use with {@link #setTextAlignment(int)}
     */
    public static final int TEXT_ALIGNMENT_CENTER = 4;

    /**
     * Default for the root view. The gravity determines the text alignment, ALIGN_NORMAL,
     * ALIGN_CENTER, or ALIGN_OPPOSITE, which are relative to each paragraph's text direction.
     *
     * Use with {@link #setTextAlignment(int)}
     */
    public static final int TEXT_ALIGNMENT_GRAVITY = 1;

    /**
     * Default text alignment. The text alignment of this View is inherited from its parent.
     * Use with {@link #setTextAlignment(int)}
     */
    public static final int TEXT_ALIGNMENT_INHERIT = 0;

    /**
     * Align to the end of the paragraph, e.g. ALIGN_OPPOSITE.
     *
     * Use with {@link #setTextAlignment(int)}
     */
    public static final int TEXT_ALIGNMENT_TEXT_END = 3;

    /**
     * Align to the start of the paragraph, e.g. ALIGN_NORMAL.
     *
     * Use with {@link #setTextAlignment(int)}
     */
    public static final int TEXT_ALIGNMENT_TEXT_START = 2;

    /**
     * Align to the end of the view, which is ALIGN_RIGHT if the view's resolved
     * layoutDirection is LTR, and ALIGN_LEFT otherwise.
     *
     * Use with {@link #setTextAlignment(int)}
     */
    public static final int TEXT_ALIGNMENT_VIEW_END = 6;

    /**
     * Align to the start of the view, which is ALIGN_LEFT if the view's resolved
     * layoutDirection is LTR, and ALIGN_RIGHT otherwise.
     *
     * Use with {@link #setTextAlignment(int)}
     */
    public static final int TEXT_ALIGNMENT_VIEW_START = 5;

    /**
     * Text direction is using "any-RTL" algorithm. The paragraph direction is RTL if it contains
     * any strong RTL character, otherwise it is LTR if it contains any strong LTR characters.
     * If there are neither, the paragraph direction is the view's resolved layout direction.
     */
    public static final int TEXT_DIRECTION_ANY_RTL = 2;

    /**
     * Text direction is using "first strong algorithm". The first strong directional character
     * determines the paragraph direction. If there is no strong directional character, the
     * paragraph direction is the view's resolved layout direction.
     */
    public static final int TEXT_DIRECTION_FIRST_STRONG = 1;

    /**
     * Text direction is using "first strong algorithm". The first strong directional character
     * determines the paragraph direction. If there is no strong directional character, the
     * paragraph direction is LTR.
     */
    public static final int TEXT_DIRECTION_FIRST_STRONG_LTR = 6;

    /**
     * Text direction is using "first strong algorithm". The first strong directional character
     * determines the paragraph direction. If there is no strong directional character, the
     * paragraph direction is RTL.
     */
    public static final int TEXT_DIRECTION_FIRST_STRONG_RTL = 7;

    /**
     * Text direction is inherited through {@link ViewGroup}
     */
    public static final int TEXT_DIRECTION_INHERIT = 0;

    /**
     * Text direction is coming from the system Locale.
     */
    public static final int TEXT_DIRECTION_LOCALE = 5;

    /**
     * Text direction is forced to LTR.
     */
    public static final int TEXT_DIRECTION_LTR = 3;

    /**
     * Text direction is forced to RTL.
     */
    public static final int TEXT_DIRECTION_RTL = 4;

    /**
     * The logging tag used by this class with android.util.Log.
     */
    protected static final String VIEW_LOG_TAG = "View";

    /**
     * This view is visible.
     * Use with {@link #setVisibility} and <a href="#attr_android:visibility">{@code
     * android:visibility}.
     */
    public static final int VISIBLE = 0;

    private View() {
    }

    /**
     * A MeasureSpec encapsulates the layout requirements passed from parent to child.
     * Each MeasureSpec represents a requirement for either the width or the height.
     * A MeasureSpec is comprised of a size and a mode. There are three possible
     * modes:
     * <dl>
     * <dt>UNSPECIFIED</dt>
     * <dd>
     * The parent has not imposed any constraint on the child. It can be whatever size
     * it wants.
     * </dd>
     *
     * <dt>EXACTLY</dt>
     * <dd>
     * The parent has determined an exact size for the child. The child is going to be
     * given those bounds regardless of how big it wants to be.
     * </dd>
     *
     * <dt>AT_MOST</dt>
     * <dd>
     * The child can be as large as it wants up to the specified size.
     * </dd>
     * </dl>
     *
     * MeasureSpecs are implemented as ints to reduce object allocation. This class
     * is provided to pack and unpack the &lt;size, mode&gt; tuple into the int.
     */
    public static final class MeasureSpec {
        /**
         * Measure specification mode: The child can be as large as it wants up
         * to the specified size.
         */
        public static final int AT_MOST = -2147483648;

        /**
         * Measure specification mode: The parent has determined an exact size
         * for the child. The child is going to be given those bounds regardless
         * of how big it wants to be.
         */
        public static final int EXACTLY = 1073741824;

        /**
         * Measure specification mode: The parent has not imposed any constraint
         * on the child. It can be whatever size it wants.
         */
        public static final int UNSPECIFIED = 0;

        private MeasureSpec() {
        }
    }
}
