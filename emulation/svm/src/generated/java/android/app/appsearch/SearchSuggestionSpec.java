package android.app.appsearch;

import android.os.Parcelable;

/**
 * This class represents the specification logic for AppSearch. It can be used to set the filter and
 * settings of search a suggestions.
 *
 * @see AppSearchSession#searchSuggestion
 */
public final class SearchSuggestionSpec implements Parcelable {
    /**
     * Ranked by the document count that contains the term.
     *
     * <p>Suppose the following document is in the index.
     *
     * <pre>Doc1 contains: term1 term2 term2 term2</pre>
     *
     * <pre>Doc2 contains: term1</pre>
     *
     * <p>Then, suppose that a search suggestion for "t" is issued with the DOCUMENT_COUNT, the
     * returned {@link SearchSuggestionResult}s will be: term1, term2. The term1 will have higher
     * score and appear in the results first.
     */
    public static final int SUGGESTION_RANKING_STRATEGY_DOCUMENT_COUNT = 0;

    /**
     * No Ranking, results are returned in arbitrary order.
     */
    public static final int SUGGESTION_RANKING_STRATEGY_NONE = 2;

    /**
     * Ranked by the term appear frequency.
     *
     * <p>Suppose the following document is in the index.
     *
     * <pre>Doc1 contains: term1 term2 term2 term2</pre>
     *
     * <pre>Doc2 contains: term1</pre>
     *
     * <p>Then, suppose that a search suggestion for "t" is issued with the TERM_FREQUENCY, the
     * returned {@link SearchSuggestionResult}s will be: term2, term1. The term2 will have higher
     * score and appear in the results first.
     */
    public static final int SUGGESTION_RANKING_STRATEGY_TERM_FREQUENCY = 1;

    private SearchSuggestionSpec() {
    }
}
