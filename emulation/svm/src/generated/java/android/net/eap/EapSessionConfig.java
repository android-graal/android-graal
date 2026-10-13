package android.net.eap;

/**
 * EapSessionConfig represents a container for EAP method configuration.
 *
 * <p>The EAP authentication server decides which EAP method is used, so clients are encouraged to
 * provide configs for several EAP methods.
 */
public final class EapSessionConfig {
    private EapSessionConfig() {
    }

    /**
     * EapMethodConfig represents a generic EAP method configuration.
     */
    public static final class EapMethodConfig {
        /**
         * EAP-Type value for the EAP-AKA method.
         *
         * <p>To include EAP-AKA as an authentication method, see {@link
         * EapSessionConfig.Builder#setEapAkaConfig(int, int)}.
         *
         * @see <a href="https://tools.ietf.org/html/rfc4187">RFC 4187, Extensible Authentication
         *     Protocol Method for 3rd Generation Authentication and Key Agreement (EAP-AKA)</a>
         */
        public static final int EAP_TYPE_AKA = 23;

        /**
         * EAP-Type value for the EAP-AKA' method.
         *
         * <p>To include EAP-AKA' as an authentication method, see {@link
         * EapSessionConfig.Builder#setEapAkaPrimeConfig(int, int, String, boolean)}.
         *
         * @see <a href="https://tools.ietf.org/html/rfc5448">RFC 5448, Improved Extensible
         *     Authentication Protocol Method for 3rd Generation Authentication and Key Agreement
         *     (EAP-AKA')</a>
         */
        public static final int EAP_TYPE_AKA_PRIME = 50;

        /**
         * EAP-Type value for the EAP-MS-CHAPv2 method.
         *
         * <p>To include EAP-MS-CHAPv2 as an authentication method, see {@link
         * EapSessionConfig.Builder#setEapMsChapV2Config(String, String)}.
         *
         * @see <a href="https://tools.ietf.org/html/draft-kamath-pppext-eap-mschapv2-02">Microsoft
         *     EAP CHAP Extensions Draft (EAP MSCHAPv2)</a>
         */
        public static final int EAP_TYPE_MSCHAP_V2 = 26;

        /**
         * EAP-Type value for the EAP-SIM method.
         *
         * <p>To include EAP-SIM as an authentication method, see {@link
         * EapSessionConfig.Builder#setEapSimConfig(int, int)}.
         *
         * @see <a href="https://tools.ietf.org/html/rfc4186">RFC 4186, Extensible Authentication
         *     Protocol Method for Global System for Mobile Communications (GSM) Subscriber Identity
         *     Modules (EAP-SIM)</a>
         */
        public static final int EAP_TYPE_SIM = 18;

        /**
         * EAP-Type value for the EAP-TTLS method.
         *
         * <p>To include EAP-TTLS as an authentication method, see {@link
         * EapSessionConfig.Builder#setEapTtlsConfig(X509Certificate, EapSessionConfig)}.
         *
         * @see <a href="https://tools.ietf.org/html/rfc5281">RFC 5281, Extensible Authentication
         *     Protocol Tunneled Transport Layer Security Authenticated Protocol Version 0
         *     (EAP-TTLSv0)</a>
         */
        public static final int EAP_TYPE_TTLS = 21;

        private EapMethodConfig() {
        }
    }
}
