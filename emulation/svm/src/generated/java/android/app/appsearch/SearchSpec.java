package android.app.appsearch;

import android.os.Parcelable;

/**
 * This class represents the specification logic for AppSearch. It can be used to set the type of
 * search, like prefix or exact only or apply filters to search for a specific schema type only etc.
 */
public final class SearchSpec implements Parcelable {
    /**
     * Cosine similarity as metric for embedding search and ranking.
     */
    public static final int EMBEDDING_SEARCH_METRIC_TYPE_COSINE = 1;

    /**
     * Use the default metric set in {@link SearchSpec#getDefaultEmbeddingSearchMetricType()} for
     * embedding search and ranking.
     */
    public static final int EMBEDDING_SEARCH_METRIC_TYPE_DEFAULT = 0;

    /**
     * Dot product similarity as metric for embedding search and ranking.
     */
    public static final int EMBEDDING_SEARCH_METRIC_TYPE_DOT_PRODUCT = 2;

    /**
     * Euclidean distance as metric for embedding search and ranking.
     */
    public static final int EMBEDDING_SEARCH_METRIC_TYPE_EUCLIDEAN = 3;

    /**
     * Results should be grouped together by namespace for the purpose of enforcing a limit on the
     * number of results returned per namespace.
     */
    public static final int GROUPING_TYPE_PER_NAMESPACE = 2;

    /**
     * Results should be grouped together by package for the purpose of enforcing a limit on the
     * number of results returned per package.
     */
    public static final int GROUPING_TYPE_PER_PACKAGE = 1;

    /**
     * Results should be grouped together by schema type for the purpose of enforcing a limit on the
     * number of results returned per schema type.
     */
    public static final int GROUPING_TYPE_PER_SCHEMA = 4;

    /**
     * Search results will be returned in an ascending order.
     */
    public static final int ORDER_ASCENDING = 1;

    /**
     * Search results will be returned in a descending order.
     */
    public static final int ORDER_DESCENDING = 0;

    /**
     * Schema type to be used in {@link SearchSpec.Builder#addProjection} to apply property paths to
     * all results, excepting any types that have had their own, specific property paths set.
     *
     * @deprecated use {@link #SCHEMA_TYPE_WILDCARD} instead.
     */
    @Deprecated
    public static final String PROJECTION_SCHEMA_TYPE_WILDCARD = "*";

    /**
     * Ranked by the advanced ranking expression provided.
     */
    public static final int RANKING_STRATEGY_ADVANCED_RANKING_EXPRESSION = 9;

    /**
     * Ranked by document creation timestamps.
     */
    public static final int RANKING_STRATEGY_CREATION_TIMESTAMP = 2;

    /**
     * Ranked by app-provided document scores.
     */
    public static final int RANKING_STRATEGY_DOCUMENT_SCORE = 1;

    /**
     * Ranked by the aggregated ranking signal of the joined documents.
     *
     * <p>Which aggregation strategy is used to determine a ranking signal is specified in the
     * {@link JoinSpec} set by {@link Builder#setJoinSpec}. This ranking strategy may not be used if
     * no {@link JoinSpec} is provided.
     *
     * @see Builder#build
     */
    public static final int RANKING_STRATEGY_JOIN_AGGREGATE_SCORE = 8;

    /**
     * No Ranking, results are returned in arbitrary order.
     */
    public static final int RANKING_STRATEGY_NONE = 0;

    /**
     * Ranked by document relevance score.
     */
    public static final int RANKING_STRATEGY_RELEVANCE_SCORE = 3;

    /**
     * Ranked by number of usages from a system UI surface.
     */
    public static final int RANKING_STRATEGY_SYSTEM_USAGE_COUNT = 6;

    /**
     * Ranked by timestamp of last usage from a system UI surface.
     */
    public static final int RANKING_STRATEGY_SYSTEM_USAGE_LAST_USED_TIMESTAMP = 7;

    /**
     * Ranked by number of usages, as reported by the app.
     */
    public static final int RANKING_STRATEGY_USAGE_COUNT = 4;

    /**
     * Ranked by timestamp of last usage, as reported by the app.
     */
    public static final int RANKING_STRATEGY_USAGE_LAST_USED_TIMESTAMP = 5;

    /**
     * Schema type to be used in {@link SearchSpec.Builder#addFilterProperties(String, Collection)}
     * and {@link SearchSpec.Builder#addProjection} to apply property paths to all results,
     * excepting any types that have had their own, specific property paths set.
     */
    public static final String SCHEMA_TYPE_WILDCARD = "*";

    /**
     * Query terms will only match exact tokens in the index.
     *
     * <p>For example, a query term "foo" will only match indexed token "foo", and not "foot" or
     * "football".
     */
    public static final int TERM_MATCH_EXACT_ONLY = 1;

    /**
     * Query terms will match indexed tokens when the query term is a prefix of the token.
     *
     * <p>For example, a query term "foo" will match indexed tokens like "foo", "foot", and
     * "football".
     */
    public static final int TERM_MATCH_PREFIX = 2;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private SearchSpec() {
    }
}
