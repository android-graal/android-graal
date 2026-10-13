package android.net;

import android.os.Parcelable;

/**
 * Class that provides utilities for collecting network connectivity diagnostics information.
 * Connectivity information is made available through triggerable diagnostics tools and by listening
 * to System validations. Some diagnostics information may be permissions-restricted.
 *
 * <p>ConnectivityDiagnosticsManager is intended for use by applications offering network
 * connectivity on a user device. These tools will provide several mechanisms for these applications
 * to be alerted to network conditions as well as diagnose potential network issues themselves.
 *
 * <p>The primary responsibilities of this class are to:
 *
 * <ul>
 *   <li>Allow permissioned applications to register and unregister callbacks for network event
 *       notifications
 *   <li>Invoke callbacks for network event notifications, including:
 *       <ul>
 *         <li>Network validations
 *         <li>Data stalls
 *         <li>Connectivity reports from applications
 *       </ul>
 * </ul>
 */
public final class ConnectivityDiagnosticsManager {
    private ConnectivityDiagnosticsManager() {
    }

    /**
     * Class that includes connectivity information for a specific Network at a specific time.
     */
    public static final class ConnectivityReport implements Parcelable {
        /**
         * A bitmask of network validation probes that were attempted.
         *
         * <p>These probes may have failed or may be incomplete at the time of this report.
         *
         * <p>The possible bits values reported by this key are:
         * {@link #NETWORK_PROBE_DNS},
         * {@link #NETWORK_PROBE_HTTP},
         * {@link #NETWORK_PROBE_HTTPS},
         * {@link #NETWORK_PROBE_FALLBACK},
         * {@link #NETWORK_PROBE_PRIVATE_DNS}.
         */
        public static final String KEY_NETWORK_PROBES_ATTEMPTED_BITMASK = "networkProbesAttempted";

        /**
         * A bitmask of network validation probes that succeeded.
         *
         * <p>The possible bits values reported by this key are:
         * {@link #NETWORK_PROBE_DNS},
         * {@link #NETWORK_PROBE_HTTP},
         * {@link #NETWORK_PROBE_HTTPS},
         * {@link #NETWORK_PROBE_FALLBACK},
         * {@link #NETWORK_PROBE_PRIVATE_DNS}.
         */
        public static final String KEY_NETWORK_PROBES_SUCCEEDED_BITMASK = "networkProbesSucceeded";

        /**
         * The overall validation result for the Network being reported on.
         *
         * <p>The possible values for this key are:
         * {@link #NETWORK_VALIDATION_RESULT_INVALID},
         * {@link #NETWORK_VALIDATION_RESULT_VALID},
         * {@link #NETWORK_VALIDATION_RESULT_PARTIALLY_VALID},
         * {@link #NETWORK_VALIDATION_RESULT_SKIPPED}.
         *
         * @see android.net.NetworkCapabilities#NET_CAPABILITY_VALIDATED
         */
        public static final String KEY_NETWORK_VALIDATION_RESULT = "networkValidationResult";

        /**
         * DNS probe.
         */
        public static final int NETWORK_PROBE_DNS = 4;

        /**
         * Captive portal fallback probe.
         */
        public static final int NETWORK_PROBE_FALLBACK = 32;

        /**
         * HTTP probe.
         */
        public static final int NETWORK_PROBE_HTTP = 8;

        /**
         * HTTPS probe.
         */
        public static final int NETWORK_PROBE_HTTPS = 16;

        /**
         * Private DNS (DNS over TLS) probd.
         */
        public static final int NETWORK_PROBE_PRIVATE_DNS = 64;

        /**
         * The overall status of the network is that it is invalid; it neither provides
         * connectivity nor has been exempted from validation.
         */
        public static final int NETWORK_VALIDATION_RESULT_INVALID = 0;

        /**
         * The overall status of the network is that it provides partial connectivity; some
         * probed services succeeded but others failed.
         */
        public static final int NETWORK_VALIDATION_RESULT_PARTIALLY_VALID = 2;

        /**
         * Due to the properties of the network, validation was not performed.
         */
        public static final int NETWORK_VALIDATION_RESULT_SKIPPED = 3;

        /**
         * The overall status of the network is that it is valid, this may be because it provides
         * full Internet access (all probes succeeded), or because other properties of the network
         * caused probes not to be run.
         */
        public static final int NETWORK_VALIDATION_RESULT_VALID = 1;

        private ConnectivityReport() {
        }
    }

    /**
     * Class that includes information for a suspected data stall on a specific Network
     */
    public static final class DataStallReport implements Parcelable {
        /**
         * Indicates that the Data Stall was detected using DNS events.
         */
        public static final int DETECTION_METHOD_DNS_EVENTS = 1;

        /**
         * Indicates that the Data Stall was detected using TCP metrics.
         */
        public static final int DETECTION_METHOD_TCP_METRICS = 2;

        /**
         * This key represents the consecutive number of DNS timeouts that have occurred.
         *
         * <p>The consecutive count will be reset any time a DNS response is received.
         *
         * <p>This key will be included if the data stall detection method is
         * {@link #DETECTION_METHOD_DNS_EVENTS}.
         *
         * <p>This value is an int.
         */
        public static final String KEY_DNS_CONSECUTIVE_TIMEOUTS = "dnsConsecutiveTimeouts";

        /**
         * This key represents the period in milliseconds over which other included TCP metrics
         * were measured.
         *
         * <p>This key will be included if the data stall detection method is
         * {@link #DETECTION_METHOD_TCP_METRICS}.
         *
         * <p>This value is an int.
         */
        public static final String KEY_TCP_METRICS_COLLECTION_PERIOD_MILLIS = "tcpMetricsCollectionPeriodMillis";

        /**
         * This key represents the fail rate of TCP packets when the suspected data stall was
         * detected.
         *
         * <p>This key will be included if the data stall detection method is
         * {@link #DETECTION_METHOD_TCP_METRICS}.
         *
         * <p>This value is an int percentage between 0 and 100.
         */
        public static final String KEY_TCP_PACKET_FAIL_RATE = "tcpPacketFailRate";

        private DataStallReport() {
        }
    }
}
