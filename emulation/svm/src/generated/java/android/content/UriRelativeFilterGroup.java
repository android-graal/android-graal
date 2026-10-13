package android.content;

/**
 * An intent data matching group based on a URI's relative reference which
 * includes the path, query and fragment.  The group is only considered as
 * matching if <em>all</em> UriRelativeFilters in the group match.  Each
 * UriRelativeFilter defines a matching rule for a URI path, query or fragment.
 * A group must contain one or more UriRelativeFilters to match but does not need to
 * contain UriRelativeFilters for all existing parts of a URI to match.
 *
 * <p>For example, given a URI that contains path, query and fragment parts,
 * a group containing only a path filter will match the URI if the path
 * filter matches the URI path.  If the group contains a path and query
 * filter, then the group will only match if both path and query filters
 * match.  If a URI contains only a path with no query or fragment then a
 * group can only match if it contains only a matching path filter. If the
 * group also contained additional query or fragment filters then it will
 * not match.</p>
 */
public final class UriRelativeFilterGroup {
    /**
     * Value to indicate that the group match is allowed.
     */
    public static final int ACTION_ALLOW = 0;

    /**
     * Value to indicate that the group match is blocked.
     */
    public static final int ACTION_BLOCK = 1;

    private UriRelativeFilterGroup() {
    }
}
