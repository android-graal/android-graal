package android.app.appsearch;

import android.os.Parcelable;

/**
 * This class represents the specifications for the joining operation in search.
 *
 * <p>Joins are only possible for matching on the qualified id of an outer document and a property
 * value within a subquery document. In the subquery documents, these values may be referred to with
 * a property path such as "email.recipient.id" or "entityId" or a property expression. One such
 * property expression is "this.qualifiedId()", which refers to the document's combined package,
 * database, namespace, and id.
 *
 * <p>Note that in order for perform the join, the property referred to by {@link
 * #getChildPropertyExpression} has to be a property with {@link
 * AppSearchSchema.StringPropertyConfig#getJoinableValueType} set to {@link
 * AppSearchSchema.StringPropertyConfig#JOINABLE_VALUE_TYPE_QUALIFIED_ID}. Otherwise no documents
 * will be joined to any {@link SearchResult}.
 *
 * <p>Take these outer query and subquery results for example:
 *
 * <pre>{@code
 * Outer result {
 *   id: id1
 *   score: 5
 * }
 * Subquery result 1 {
 *   id: id2
 *   score: 2
 *   entityId: pkg$db/ns#id1
 *   notes: This is some doc
 * }
 * Subquery result 2 {
 *   id: id3
 *   score: 3
 *   entityId: pkg$db/ns#id2
 *   notes: This is another doc
 * }
 * }</pre>
 *
 * <p>In this example, subquery result 1 contains a property "entityId" whose value is
 * "pkg$db/ns#id1", referring to the outer result. If you call {@link Builder} with "entityId", we
 * will retrieve the value of the property "entityId" from the child document, which is
 * "pkg$db#ns/id1". Let's say the qualified id of the outer result is "pkg$db#ns/id1". This would
 * mean the subquery result 1 document will be matched to that parent document. This is done by
 * adding a {@link SearchResult} containing the child document to the top-level parent {@link
 * SearchResult#getJoinedResults}.
 *
 * <p>If {@link #getChildPropertyExpression} is "notes", we will check the values of the notes
 * property in the subquery results. In subquery result 1, this values is "This is some doc", which
 * does not equal the qualified id of the outer query result. As such, subquery result 1 will not be
 * joined to the outer query result.
 *
 * <p>It's possible to define an advanced ranking strategy in the nested {@link SearchSpec} and also
 * use {@link SearchSpec#RANKING_STRATEGY_JOIN_AGGREGATE_SCORE} in the outer {@link SearchSpec}. In
 * this case, the parents will be ranked based on an aggregation, such as the sum, of the signals
 * calculated by scoring the joined documents with the advanced ranking strategy.
 *
 * <p>In terms of scoring, if {@link SearchSpec#RANKING_STRATEGY_JOIN_AGGREGATE_SCORE} is set in
 * {@link SearchSpec#getRankingStrategy}, the scores of the outer SearchResults can be influenced by
 * the ranking signals of the subquery results. For example, if the {@link
 * JoinSpec#getAggregationScoringStrategy} is set to:
 *
 * <ul>
 *   <li>{@link JoinSpec#AGGREGATION_SCORING_MIN_RANKING_SIGNAL}, the ranking signal of the outer
 *       {@link SearchResult} will be set to the minimum of the ranking signals of the subquery
 *       results. In this case, it will be the minimum of 2 and 3, which is 2.
 *   <li>{@link JoinSpec#AGGREGATION_SCORING_MAX_RANKING_SIGNAL}, the ranking signal of the outer
 *       {@link SearchResult} will be 3.
 *   <li>{@link JoinSpec#AGGREGATION_SCORING_AVG_RANKING_SIGNAL}, the ranking signal of the outer
 *       {@link SearchResult} will be 2.5.
 *   <li>{@link JoinSpec#AGGREGATION_SCORING_RESULT_COUNT}, the ranking signal of the outer {@link
 *       SearchResult} will be 2 as there are two joined results.
 *   <li>{@link JoinSpec#AGGREGATION_SCORING_SUM_RANKING_SIGNAL}, the ranking signal of the outer
 *       {@link SearchResult} will be 5, the sum of 2 and 3.
 *   <li>{@link JoinSpec#AGGREGATION_SCORING_OUTER_RESULT_RANKING_SIGNAL}, the ranking signal of the
 *       outer {@link SearchResult} will stay as it is.
 * </ul>
 *
 * <p>Referring to "this.childrenRankingSignals()" in the ranking signal of the outer query will
 * return the signals calculated by scoring the joined documents using the scoring strategy in the
 * nested {@link SearchSpec}, as in {@link SearchResult#getRankingSignal}.
 */
public final class JoinSpec implements Parcelable {
    /**
     * Score the aggregation of joined documents using the average ranking signal.
     */
    public static final int AGGREGATION_SCORING_AVG_RANKING_SIGNAL = 3;

    /**
     * Score the aggregation of joined documents using the largest ranking signal.
     */
    public static final int AGGREGATION_SCORING_MAX_RANKING_SIGNAL = 4;

    /**
     * Score the aggregation of joined documents using the smallest ranking signal.
     */
    public static final int AGGREGATION_SCORING_MIN_RANKING_SIGNAL = 2;

    /**
     * Do not score the aggregation of joined documents. This is for the case where we want to
     * perform a join, but keep the parent ranking signal.
     */
    public static final int AGGREGATION_SCORING_OUTER_RESULT_RANKING_SIGNAL = 0;

    /**
     * Score the aggregation of joined documents by counting the number of results.
     */
    public static final int AGGREGATION_SCORING_RESULT_COUNT = 1;

    /**
     * Score the aggregation of joined documents using the sum of ranking signal.
     */
    public static final int AGGREGATION_SCORING_SUM_RANKING_SIGNAL = 5;

    private JoinSpec() {
    }
}
