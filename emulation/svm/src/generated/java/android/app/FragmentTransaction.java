package android.app;

/**
 * API for performing a set of Fragment operations.
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about using fragments, read the
 * <a href="{@docRoot}guide/components/fragments.html">Fragments</a> developer
 * guide.</p>
 * </div>
 *
 * @deprecated Use the <a href="{@docRoot}tools/extras/support-library.html">Support Library</a>
 *      {@link androidx.fragment.app.FragmentTransaction}
 */
@Deprecated
public final class FragmentTransaction {
    /**
     * Bit mask that is set for all enter transitions.
     */
    @Deprecated
    public static final int TRANSIT_ENTER_MASK = 4096;

    /**
     * Bit mask that is set for all exit transitions.
     */
    @Deprecated
    public static final int TRANSIT_EXIT_MASK = 8192;

    /**
     * Fragment is being removed from the stack
     */
    @Deprecated
    public static final int TRANSIT_FRAGMENT_CLOSE = 8194;

    /**
     * Fragment should simply fade in or out; that is, no strong navigation associated
     *  with it except that it is appearing or disappearing for some reason.
     */
    @Deprecated
    public static final int TRANSIT_FRAGMENT_FADE = 4099;

    /**
     * Fragment is being added onto the stack
     */
    @Deprecated
    public static final int TRANSIT_FRAGMENT_OPEN = 4097;

    /**
     * No animation for transition.
     */
    @Deprecated
    public static final int TRANSIT_NONE = 0;

    /**
     * Not set up for a transition.
     */
    @Deprecated
    public static final int TRANSIT_UNSET = -1;

    private FragmentTransaction() {
    }
}
