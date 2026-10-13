package android.app;

/**
 * Interface for interacting with {@link Fragment} objects inside of an
 * {@link Activity}
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about using fragments, read the
 * <a href="{@docRoot}guide/components/fragments.html">Fragments</a> developer guide.</p>
 * </div>
 *
 * While the FragmentManager API was introduced in
 * {@link android.os.Build.VERSION_CODES#HONEYCOMB}, a version of the API
 * at is also available for use on older platforms through
 * {@link androidx.fragment.app.FragmentActivity}.  See the blog post
 * <a href="http://android-developers.blogspot.com/2011/03/fragments-for-all.html">
 * Fragments For All</a> for more details.
 *
 * @deprecated Use the <a href="{@docRoot}tools/extras/support-library.html">Support Library</a>
 *      {@link androidx.fragment.app.FragmentManager} for consistent behavior across all devices
 *      and access to <a href="{@docRoot}topic/libraries/architecture/lifecycle.html">Lifecycle</a>.
 */
@Deprecated
public final class FragmentManager {
    /**
     * Flag for {@link #popBackStack(String, int)}
     * and {@link #popBackStack(int, int)}: If set, and the name or ID of
     * a back stack entry has been supplied, then all matching entries will
     * be consumed until one that doesn't match is found or the bottom of
     * the stack is reached.  Otherwise, all entries up to but not including that entry
     * will be removed.
     */
    @Deprecated
    public static final int POP_BACK_STACK_INCLUSIVE = 1;

    private FragmentManager() {
    }
}
