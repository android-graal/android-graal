package android.net.wifi;

import android.os.Parcelable;

/**
 * Enterprise configuration details for Wi-Fi. Stores details about the EAP method
 * and any associated credentials.
 */
public final class WifiEnterpriseConfig implements Parcelable {
    /**
     * Intent extra: data for WAPI AS certificates
     */
    public static final String EXTRA_WAPI_AS_CERTIFICATE_DATA = "android.net.wifi.extra.WAPI_AS_CERTIFICATE_DATA";

    /**
     * Intent extra: name for WAPI AS certificates
     */
    public static final String EXTRA_WAPI_AS_CERTIFICATE_NAME = "android.net.wifi.extra.WAPI_AS_CERTIFICATE_NAME";

    /**
     * Intent extra: data for WAPI USER certificates
     */
    public static final String EXTRA_WAPI_USER_CERTIFICATE_DATA = "android.net.wifi.extra.WAPI_USER_CERTIFICATE_DATA";

    /**
     * Intent extra: name for WAPI USER certificates
     */
    public static final String EXTRA_WAPI_USER_CERTIFICATE_NAME = "android.net.wifi.extra.WAPI_USER_CERTIFICATE_NAME";

    /**
     * Constant definition for TLS v1.0 which is used in {@link #setMinimumTlsVersion(int)}
     */
    public static final int TLS_V1_0 = 0;

    /**
     * Constant definition for TLS v1.1 which is used in {@link #setMinimumTlsVersion(int)}
     */
    public static final int TLS_V1_1 = 1;

    /**
     * Constant definition for TLS v1.2 which is used in {@link #setMinimumTlsVersion(int)}
     */
    public static final int TLS_V1_2 = 2;

    /**
     * Constant definition for TLS v1.3 which is used in {@link #setMinimumTlsVersion(int)}
     */
    public static final int TLS_V1_3 = 3;

    /**
     * Key prefix for WAPI AS certificates.
     */
    public static final String WAPI_AS_CERTIFICATE = "WAPIAS_";

    /**
     * Key prefix for WAPI user certificates.
     */
    public static final String WAPI_USER_CERTIFICATE = "WAPIUSR_";

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private WifiEnterpriseConfig() {
    }

    /**
     * The Extensible Authentication Protocol method used
     */
    public static final class Eap {
        /**
         * EAP-Authentication and Key Agreement [RFC-4187]
         */
        public static final int AKA = 5;

        /**
         * EAP-Authentication and Key Agreement Prime [RFC-5448]
         */
        public static final int AKA_PRIME = 6;

        /**
         * No EAP method used. Represents an empty config
         */
        public static final int NONE = -1;

        /**
         * Protected EAP
         */
        public static final int PEAP = 0;

        /**
         * EAP-Password
         */
        public static final int PWD = 3;

        /**
         * EAP-Subscriber Identity Module [RFC-4186]
         */
        public static final int SIM = 4;

        /**
         * EAP-Transport Layer Security
         */
        public static final int TLS = 1;

        /**
         * EAP-Tunneled Transport Layer Security
         */
        public static final int TTLS = 2;

        /**
         * Hotspot 2.0 r2 OSEN
         */
        public static final int UNAUTH_TLS = 7;

        /**
         * WAPI Certificate
         */
        public static final int WAPI_CERT = 8;

        private Eap() {
        }
    }

    /**
     * The inner authentication method used
     */
    public static final class Phase2 {
        /**
         * EAP-Authentication and Key Agreement [RFC-4187]
         */
        public static final int AKA = 6;

        /**
         * EAP-Authentication and Key Agreement Prime [RFC-5448]
         */
        public static final int AKA_PRIME = 7;

        /**
         * Generic Token Card
         */
        public static final int GTC = 4;

        /**
         * Microsoft Challenge Handshake Authentication Protocol
         */
        public static final int MSCHAP = 2;

        /**
         * Microsoft Challenge Handshake Authentication Protocol v2
         */
        public static final int MSCHAPV2 = 3;

        public static final int NONE = 0;

        /**
         * Password Authentication Protocol
         */
        public static final int PAP = 1;

        /**
         * EAP-Subscriber Identity Module [RFC-4186]
         */
        public static final int SIM = 5;

        private Phase2() {
        }
    }
}
