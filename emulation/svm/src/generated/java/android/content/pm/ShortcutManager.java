package android.content.pm;

/**
 * <p><code>ShortcutManager</code> executes operations on an app's set of <i>shortcuts</i>, which
 * represent specific tasks and actions that users can perform within your app. This page lists
 * components of the <code>ShortcutManager</code> class that you can use to create and manage
 * sets of shortcuts.
 *
 * <p>To learn about methods that retrieve information about a single shortcut&mdash;including
 * identifiers, type, and status&mdash;read the <code>
 * <a href="/reference/android/content/pm/ShortcutInfo.html">ShortcutInfo</a></code> reference.
 *
 * <p>For guidance about using shortcuts, see
 * <a href="/guide/topics/ui/shortcuts/index.html">App shortcuts</a>.
 *
 * <h3>Retrieving class instances</h3>
 * <!-- Provides a heading for the content filled in by the @SystemService annotation below -->
 */
public final class ShortcutManager {
    /**
     * Include cached shortcuts in the result.
     *
     * @see #getShortcuts(int)
     */
    public static final int FLAG_MATCH_CACHED = 8;

    /**
     * Include dynamic shortcuts in the result.
     *
     * @see #getShortcuts(int)
     */
    public static final int FLAG_MATCH_DYNAMIC = 2;

    /**
     * Include manifest shortcuts in the result.
     *
     * @see #getShortcuts(int)
     */
    public static final int FLAG_MATCH_MANIFEST = 1;

    /**
     * Include pinned shortcuts in the result.
     *
     * @see #getShortcuts(int)
     */
    public static final int FLAG_MATCH_PINNED = 4;

    private ShortcutManager() {
    }
}
