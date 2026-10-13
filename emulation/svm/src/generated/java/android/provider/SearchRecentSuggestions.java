package android.provider;

/**
 * This is a utility class providing access to
 * {@link android.content.SearchRecentSuggestionsProvider}.
 *
 * <p>Unlike some utility classes, this one must be instantiated and properly initialized, so that
 * it can be configured to operate with the search suggestions provider that you have created.
 *
 * <p>Typically, you will do this in your searchable activity, each time you receive an incoming
 * {@link android.content.Intent#ACTION_SEARCH ACTION_SEARCH} Intent.  The code to record each
 * incoming query is as follows:
 * <pre class="prettyprint">
 *      SearchSuggestions suggestions = new SearchSuggestions(this,
 *              MySuggestionsProvider.AUTHORITY, MySuggestionsProvider.MODE);
 *      suggestions.saveRecentQuery(queryString, null);
 * </pre>
 *
 * <p>For a working example, see SearchSuggestionSampleProvider and SearchQueryResults in
 * samples/ApiDemos/app.
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For information about using search suggestions in your application, read the
 * <a href="{@docRoot}guide/topics/search/adding-recent-query-suggestions.html">Adding Recent Query
 * Suggestions</a> developer guide.</p>
 * </div>
 */
public final class SearchRecentSuggestions {
    /**
     * Index into the provided query projections.  For use with Cursor.update methods.
     */
    public static final int QUERIES_PROJECTION_DATE_INDEX = 1;

    /**
     * Index into the provided query projections.  For use with Cursor.update methods.
     */
    public static final int QUERIES_PROJECTION_DISPLAY1_INDEX = 3;

    /**
     * Index into the provided query projections.  For use with Cursor.update methods.
     */
    public static final int QUERIES_PROJECTION_DISPLAY2_INDEX = 4;

    /**
     * Index into the provided query projections.  For use with Cursor.update methods.
     */
    public static final int QUERIES_PROJECTION_QUERY_INDEX = 2;

    private SearchRecentSuggestions() {
    }
}
