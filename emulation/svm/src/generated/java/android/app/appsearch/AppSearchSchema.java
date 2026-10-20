package android.app.appsearch;

import android.os.Parcelable;

/**
 * The AppSearch Schema for a particular type of document.
 *
 * <p>For example, an e-mail message or a music recording could be a schema type.
 *
 * <p>The schema consists of type information, properties, and config (like tokenization type).
 *
 * @see AppSearchSession#setSchema
 */
public final class AppSearchSchema implements Parcelable {
    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private AppSearchSchema() {
    }

    /**
     * Configuration for a property of type {@link EmbeddingVector} in a Document.
     */
    public static final class EmbeddingPropertyConfig extends PropertyConfig {
        /**
         * Content in this property will not be indexed.
         */
        public static final int INDEXING_TYPE_NONE = 0;

        /**
         * Embedding vectors in this property will be indexed.
         *
         * <p>The index offers 100% accuracy, but has linear time complexity based on the number of
         * embedding vectors within the index.
         */
        public static final int INDEXING_TYPE_SIMILARITY = 1;

        /**
         * Contents in this property will be quantized to 8 bits.
         */
        public static final int QUANTIZATION_TYPE_8_BIT = 1;

        /**
         * Contents in this property will not be quantized.
         */
        public static final int QUANTIZATION_TYPE_NONE = 0;

        private EmbeddingPropertyConfig() {
        }
    }

    /**
     * Configuration for a property containing a 64-bit integer.
     */
    public static final class LongPropertyConfig extends PropertyConfig {
        /**
         * Content in this property will not be indexed.
         */
        public static final int INDEXING_TYPE_NONE = 0;

        /**
         * Content in this property will be indexed and can be fetched via numeric search range
         * query.
         *
         * <p>For example, a property with 1024 should match numeric search range query [0, 2000].
         */
        public static final int INDEXING_TYPE_RANGE = 1;

        private LongPropertyConfig() {
        }
    }

    /**
     * Common configuration for a single property (field) in a Document.
     *
     * <p>For example, an {@code EmailMessage} would be a type and the {@code subject} would be a
     * property.
     */
    public static class PropertyConfig {
        /**
         * Zero or one value [0,1].
         */
        public static final int CARDINALITY_OPTIONAL = 2;

        /**
         * Any number of items (including zero) [0...*].
         */
        public static final int CARDINALITY_REPEATED = 1;

        /**
         * Exactly one value [1].
         */
        public static final int CARDINALITY_REQUIRED = 3;

        private PropertyConfig() {
        }
    }

    /**
     * Configuration for a property of type String in a Document.
     */
    public static final class StringPropertyConfig extends PropertyConfig {
        /**
         * Content in this property should only be returned for queries matching the exact tokens
         * appearing in this property.
         *
         * <p>For example, a property with "fool" should NOT match a query for "foo".
         */
        public static final int INDEXING_TYPE_EXACT_TERMS = 1;

        /**
         * Content in this property will not be tokenized or indexed.
         */
        public static final int INDEXING_TYPE_NONE = 0;

        /**
         * Content in this property should be returned for queries that are either exact matches or
         * query matches of the tokens appearing in this property.
         *
         * <p>For example, a property with "fool" <b>should</b> match a query for "foo".
         */
        public static final int INDEXING_TYPE_PREFIXES = 2;

        /**
         * Content in this property is not joinable.
         */
        public static final int JOINABLE_VALUE_TYPE_NONE = 0;

        /**
         * Content in this string property will be used as a qualified id to join documents.
         *
         * <ul>
         *   <li>Qualified id: a unique identifier for a document, and this joinable value type is
         *       similar to primary and foreign key in relational database. See {@link
         *       android.app.appsearch.util.DocumentIdUtil} for more details.
         *   <li>Currently we only support single string joining, so it should only be used with
         *       {@link PropertyConfig#CARDINALITY_OPTIONAL} and {@link
         *       PropertyConfig#CARDINALITY_REQUIRED}.
         * </ul>
         */
        public static final int JOINABLE_VALUE_TYPE_QUALIFIED_ID = 1;

        /**
         * This value indicates that no tokens should be extracted from this property.
         *
         * <p>It is only valid for tokenizer_type to be 'NONE' if {@link #getIndexingType} is {@link
         * #INDEXING_TYPE_NONE}.
         */
        public static final int TOKENIZER_TYPE_NONE = 0;

        /**
         * Tokenization for plain text. This value indicates that tokens should be extracted from
         * this property based on word breaks. Segments of whitespace and punctuation are not
         * considered tokens.
         *
         * <p>For example, a property with "foo bar. baz." will produce tokens for "foo", "bar" and
         * "baz". The segments " " and "." will not be considered tokens.
         *
         * <p>It is only valid for tokenizer_type to be 'PLAIN' if {@link #getIndexingType} is
         * {@link #INDEXING_TYPE_EXACT_TERMS} or {@link #INDEXING_TYPE_PREFIXES}.
         */
        public static final int TOKENIZER_TYPE_PLAIN = 1;

        /**
         * Tokenization for emails. This value indicates that tokens should be extracted from this
         * property based on email structure.
         *
         * <p>For example, a property with "alex.sav@google.com" will produce tokens for "alex",
         * "sav", "alex.sav", "google", "com", and "alexsav@google.com"
         *
         * <p>It is only valid for tokenizer_type to be 'RFC822' if {@link #getIndexingType} is
         * {@link #INDEXING_TYPE_EXACT_TERMS} or {@link #INDEXING_TYPE_PREFIXES}.
         */
        public static final int TOKENIZER_TYPE_RFC822 = 3;

        /**
         * This value indicates that no normalization or segmentation should be applied to string
         * values that are tokenized using this type. Therefore, the output token is equivalent to
         * the raw string value.
         *
         * <p>For example, a property with "Hello, world!" will produce the token "Hello, world!",
         * preserving punctuation and capitalization, and not creating separate tokens between the
         * space.
         *
         * <p>It is only valid for tokenizer_type to be 'VERBATIM' if {@link #getIndexingType} is
         * {@link #INDEXING_TYPE_EXACT_TERMS} or {@link #INDEXING_TYPE_PREFIXES}.
         */
        public static final int TOKENIZER_TYPE_VERBATIM = 2;

        private StringPropertyConfig() {
        }
    }
}
