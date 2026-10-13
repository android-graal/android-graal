package android.content;

/**
 * A filter for matching Intent URI Data as part of a
 * {@link UriRelativeFilterGroup}. A single filter can only be
 * matched against either a URI path, query or fragment
 */
public final class UriRelativeFilter {
    /**
     * Value to indicate that the filter is to be applied to a URI fragment.
     */
    public static final int FRAGMENT = 2;

    /**
     * Value to indicate that the filter is to be applied to a URI path.
     */
    public static final int PATH = 0;

    /**
     * Value to indicate that the filter is to be applied to a URI query.
     */
    public static final int QUERY = 1;

    private UriRelativeFilter() {
    }
}
