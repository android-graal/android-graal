package android.app.usage;

/**
 * Class providing enumeration over buckets of network usage statistics. {@link NetworkStats}
 * objects are returned as results to various queries in {@link NetworkStatsManager}.
 */
public final class NetworkStats {
    private NetworkStats() {
    }

    /**
     * Buckets are the smallest elements of a query result. As some dimensions of a result may be
     * aggregated (e.g. time or state) some values may be equal across all buckets.
     */
    public static final class Bucket {
        /**
         * Combined usage for this network regardless of default network status.
         */
        public static final int DEFAULT_NETWORK_ALL = -1;

        /**
         * Usage that occurs while this network is not a default network.
         *
         * <p>This implies that the app responsible for this usage requested that it occur on a
         * specific network different from the one(s) the system would have selected for it.
         */
        public static final int DEFAULT_NETWORK_NO = 1;

        /**
         * Usage that occurs while this network is a default network.
         *
         * <p>This implies that the app either did not select a specific network for this usage,
         * or it selected a network that the system could have selected for app traffic.
         */
        public static final int DEFAULT_NETWORK_YES = 2;

        /**
         * Combined usage across all metered states. Covers metered and unmetered usage.
         */
        public static final int METERED_ALL = -1;

        /**
         * Usage that occurs on an unmetered network.
         */
        public static final int METERED_NO = 1;

        /**
         * Usage that occurs on a metered network.
         *
         * <p>A network is classified as metered when the user is sensitive to heavy data usage on
         * that connection.
         */
        public static final int METERED_YES = 2;

        /**
         * Combined usage across all roaming states. Covers both roaming and non-roaming usage.
         */
        public static final int ROAMING_ALL = -1;

        /**
         * Usage that occurs on a home, non-roaming network.
         *
         * <p>Any cellular usage in this bucket was incurred while the device was connected to a
         * tower owned or operated by the user's wireless carrier, or a tower that the user's
         * wireless carrier has indicated should be treated as a home network regardless.
         *
         * <p>This is also the default value for network types that do not support roaming.
         */
        public static final int ROAMING_NO = 1;

        /**
         * Usage that occurs on a roaming network.
         *
         * <p>Any cellular usage in this bucket as incurred while the device was roaming on another
         * carrier's network, for which additional charges may apply.
         */
        public static final int ROAMING_YES = 2;

        /**
         * Combined usage across all states.
         */
        public static final int STATE_ALL = -1;

        /**
         * Usage not accounted for in any other state.
         */
        public static final int STATE_DEFAULT = 1;

        /**
         * Foreground usage.
         */
        public static final int STATE_FOREGROUND = 2;

        /**
         * Special TAG value for total data across all tags
         */
        public static final int TAG_NONE = 0;

        /**
         * Special UID value for aggregate/unspecified.
         */
        public static final int UID_ALL = -1;

        /**
         * Special UID value for removed apps.
         */
        public static final int UID_REMOVED = -4;

        /**
         * Special UID value for data usage by tethering.
         */
        public static final int UID_TETHERING = -5;

        private Bucket() {
        }
    }
}
