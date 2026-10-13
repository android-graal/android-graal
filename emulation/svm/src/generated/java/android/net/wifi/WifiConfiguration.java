package android.net.wifi;

import android.os.Parcelable;

/**
 * A class representing a configured Wi-Fi network, including the
 * security configuration.
 *
 * @deprecated Use {@link WifiNetworkSpecifier.Builder} to create {@link NetworkSpecifier} and
 * {@link WifiNetworkSuggestion.Builder} to create {@link WifiNetworkSuggestion}. This class can
 * still be used with privileged APIs such as
 * {@link WifiManager#addNetwork(WifiConfiguration)}.
 */
@Deprecated
public final class WifiConfiguration implements Parcelable {
    /**
     * Let the wifi framework automatically decide the MAC randomization strategy.
     */
    @Deprecated
    public static final int RANDOMIZATION_AUTO = 3;

    /**
     * Use factory MAC when connecting to this network
     */
    @Deprecated
    public static final int RANDOMIZATION_NONE = 0;

    /**
     * Use a randomly generated MAC address for connections to this network.
     * This option does not persist the randomized MAC address.
     */
    @Deprecated
    public static final int RANDOMIZATION_NON_PERSISTENT = 2;

    /**
     * Generate a randomized MAC once and reuse it for all connections to this network
     */
    @Deprecated
    public static final int RANDOMIZATION_PERSISTENT = 1;

    /**
     * Security type for Easy Connect (DPP) network
     */
    @Deprecated
    public static final int SECURITY_TYPE_DPP = 13;

    /**
     * Security type for an EAP network.
     */
    @Deprecated
    public static final int SECURITY_TYPE_EAP = 3;

    /**
     * Security type for a WPA3-Enterprise in 192-bit security network.
     * @deprecated Use the {@link #SECURITY_TYPE_EAP_WPA3_ENTERPRISE_192_BIT} constant
     * (which is the same value).
     */
    @Deprecated
    public static final int SECURITY_TYPE_EAP_SUITE_B = 5;

    /**
     * Security type for a WPA3-Enterprise network.
     */
    @Deprecated
    public static final int SECURITY_TYPE_EAP_WPA3_ENTERPRISE = 9;

    /**
     * Security type for a WPA3-Enterprise in 192-bit security network.
     * This is the same as {@link #SECURITY_TYPE_EAP_SUITE_B} and uses the same value.
     */
    @Deprecated
    public static final int SECURITY_TYPE_EAP_WPA3_ENTERPRISE_192_BIT = 5;

    /**
     * Security type for an open network.
     */
    @Deprecated
    public static final int SECURITY_TYPE_OPEN = 0;

    /**
     * Security type for an OWE network.
     */
    @Deprecated
    public static final int SECURITY_TYPE_OWE = 6;

    /**
     * Security type for a PSK network.
     */
    @Deprecated
    public static final int SECURITY_TYPE_PSK = 2;

    /**
     * Security type for an SAE network.
     */
    @Deprecated
    public static final int SECURITY_TYPE_SAE = 4;

    /**
     * Security type for a WAPI Certificate network.
     */
    @Deprecated
    public static final int SECURITY_TYPE_WAPI_CERT = 8;

    /**
     * Security type for a WAPI PSK network.
     */
    @Deprecated
    public static final int SECURITY_TYPE_WAPI_PSK = 7;

    /**
     * Security type for a WEP network.
     */
    @Deprecated
    public static final int SECURITY_TYPE_WEP = 1;

    private WifiConfiguration() {
    }

    /**
     * Recognized IEEE 802.11 authentication algorithms.
     */
    @Deprecated
    public static final class AuthAlgorithm {
        /**
         * LEAP/Network EAP (only used with LEAP)
         */
        @Deprecated
        public static final int LEAP = 2;

        /**
         * Open System authentication (required for WPA/WPA2)
         */
        @Deprecated
        public static final int OPEN = 0;

        /**
         * SAE (Used only for WPA3-Personal)
         */
        @Deprecated
        public static final int SAE = 3;

        /**
         * Shared Key authentication (requires static WEP keys)
         *  @deprecated Due to security and performance limitations, use of WEP networks
         *  is discouraged.
         */
        @Deprecated
        public static final int SHARED = 1;

        @Deprecated
        public static final String varName = "auth_alg";

        private AuthAlgorithm() {
        }
    }

    /**
     * Recognized group ciphers.
     * <pre>
     * CCMP = AES in Counter mode with CBC-MAC [RFC 3610, IEEE 802.11i/D7.0]
     * TKIP = Temporal Key Integrity Protocol [IEEE 802.11i/D7.0]
     * WEP104 = WEP (Wired Equivalent Privacy) with 104-bit key
     * WEP40 = WEP (Wired Equivalent Privacy) with 40-bit key (original 802.11)
     * GCMP_256 = AES in Galois/Counter Mode
     * </pre>
     */
    @Deprecated
    public static final class GroupCipher {
        /**
         * AES in Counter mode with CBC-MAC [RFC 3610, IEEE 802.11i/D7.0]
         */
        @Deprecated
        public static final int CCMP = 3;

        /**
         * AES in Galois/Counter Mode with a 128-bit integrity key
         */
        @Deprecated
        public static final int GCMP_128 = 7;

        /**
         * AES in Galois/Counter Mode
         */
        @Deprecated
        public static final int GCMP_256 = 5;

        /**
         * SMS4 cipher for WAPI
         */
        @Deprecated
        public static final int SMS4 = 6;

        /**
         * Temporal Key Integrity Protocol [IEEE 802.11i/D7.0]
         */
        @Deprecated
        public static final int TKIP = 2;

        /**
         * WEP104 = WEP (Wired Equivalent Privacy) with 104-bit key
         *  @deprecated Due to security and performance limitations, use of WEP networks
         *  is discouraged.
         */
        @Deprecated
        public static final int WEP104 = 1;

        /**
         * WEP40 = WEP (Wired Equivalent Privacy) with 40-bit key (original 802.11)
         *  @deprecated Due to security and performance limitations, use of WEP networks
         *  is discouraged.
         */
        @Deprecated
        public static final int WEP40 = 0;

        @Deprecated
        public static final String varName = "group";

        private GroupCipher() {
        }
    }

    /**
     * Recognized group management ciphers.
     * <pre>
     * BIP_CMAC_256 = Cipher-based Message Authentication Code 256 bits
     * BIP_GMAC_128 = Galois Message Authentication Code 128 bits
     * BIP_GMAC_256 = Galois Message Authentication Code 256 bits
     * </pre>
     */
    @Deprecated
    public static final class GroupMgmtCipher {
        /**
         * CMAC-256 = Cipher-based Message Authentication Code
         */
        @Deprecated
        public static final int BIP_CMAC_256 = 0;

        /**
         * GMAC-128 = Galois Message Authentication Code
         */
        @Deprecated
        public static final int BIP_GMAC_128 = 1;

        /**
         * GMAC-256 = Galois Message Authentication Code
         */
        @Deprecated
        public static final int BIP_GMAC_256 = 2;

        private GroupMgmtCipher() {
        }
    }

    /**
     * Recognized key management schemes.
     */
    @Deprecated
    public static final class KeyMgmt {
        /**
         * Easy Connect - AKA Device Provisioning Protocol (DPP)
         * For more details, visit <a href="https://www.wi-fi.org/">https://www.wi-fi.org/</a> and
         * search for "Easy Connect" or "Device Provisioning Protocol specification".
         */
        @Deprecated
        public static final int DPP = 17;

        /**
         * IEEE 802.11ai FILS SK with SHA256
         */
        @Deprecated
        public static final int FILS_SHA256 = 15;

        /**
         * IEEE 802.11ai FILS SK with SHA384:
         */
        @Deprecated
        public static final int FILS_SHA384 = 16;

        /**
         * IEEE 802.11r Fast BSS Transition with EAP authentication.
         */
        @Deprecated
        public static final int FT_EAP = 7;

        /**
         * IEEE 802.11r Fast BSS Transition with PSK authentication.
         */
        @Deprecated
        public static final int FT_PSK = 6;

        /**
         * IEEE 802.1X using EAP authentication and (optionally) dynamically
         * generated WEP keys.
         */
        @Deprecated
        public static final int IEEE8021X = 3;

        /**
         * WPA is not used; plaintext or static WEP could be used.
         */
        @Deprecated
        public static final int NONE = 0;

        /**
         * Hotspot 2.0 r2 OSEN:
         */
        @Deprecated
        public static final int OSEN = 5;

        /**
         * Opportunististic Wireless Encryption
         */
        @Deprecated
        public static final int OWE = 9;

        /**
         * Simultaneous Authentication of Equals
         */
        @Deprecated
        public static final int SAE = 8;

        /**
         * SUITE_B_192 192 bit level
         */
        @Deprecated
        public static final int SUITE_B_192 = 10;

        /**
         * WAPI certificate to be specified.
         */
        @Deprecated
        public static final int WAPI_CERT = 14;

        /**
         * WAPI pre-shared key (requires {@code preSharedKey} to be specified).
         */
        @Deprecated
        public static final int WAPI_PSK = 13;

        /**
         * WPA2 pre-shared key for use with soft access point
         * (requires {@code preSharedKey} to be specified).
         */
        @Deprecated
        public static final int WPA2_PSK = 4;

        /**
         * WPA using EAP authentication. Generally used with an external authentication server.
         */
        @Deprecated
        public static final int WPA_EAP = 2;

        /**
         * WPA using EAP authentication with stronger SHA256-based algorithms.
         */
        @Deprecated
        public static final int WPA_EAP_SHA256 = 12;

        /**
         * WPA pre-shared key (requires {@code preSharedKey} to be specified).
         */
        @Deprecated
        public static final int WPA_PSK = 1;

        /**
         * WPA pre-shared key with stronger SHA256-based algorithms.
         */
        @Deprecated
        public static final int WPA_PSK_SHA256 = 11;

        @Deprecated
        public static final String varName = "key_mgmt";

        private KeyMgmt() {
        }
    }

    /**
     * Recognized pairwise ciphers for WPA.
     */
    @Deprecated
    public static final class PairwiseCipher {
        /**
         * AES in Counter mode with CBC-MAC [RFC 3610, IEEE 802.11i/D7.0]
         */
        @Deprecated
        public static final int CCMP = 2;

        /**
         * AES in Galois/Counter Mode with a 128-bit integrity key
         */
        @Deprecated
        public static final int GCMP_128 = 5;

        /**
         * AES in Galois/Counter Mode
         */
        @Deprecated
        public static final int GCMP_256 = 3;

        /**
         * Use only Group keys (deprecated)
         */
        @Deprecated
        public static final int NONE = 0;

        /**
         * SMS4 cipher for WAPI
         */
        @Deprecated
        public static final int SMS4 = 4;

        /**
         * Temporal Key Integrity Protocol [IEEE 802.11i/D7.0]
         *  @deprecated Due to security and performance limitations, use of WPA-1 networks
         *  is discouraged. WPA-2 (RSN) should be used instead.
         */
        @Deprecated
        public static final int TKIP = 1;

        @Deprecated
        public static final String varName = "pairwise";

        private PairwiseCipher() {
        }
    }

    /**
     * Recognized security protocols.
     */
    @Deprecated
    public static final class Protocol {
        /**
         * RSN WPA2/WPA3/IEEE 802.11i
         */
        @Deprecated
        public static final int RSN = 1;

        /**
         * WAPI Protocol
         */
        @Deprecated
        public static final int WAPI = 3;

        /**
         * WPA/IEEE 802.11i/D3.0
         *  @deprecated Due to security and performance limitations, use of WPA-1 networks
         *  is discouraged. WPA-2 (RSN) should be used instead.
         */
        @Deprecated
        public static final int WPA = 0;

        @Deprecated
        public static final String varName = "proto";

        private Protocol() {
        }
    }

    /**
     * Possible status of a network configuration.
     */
    @Deprecated
    public static final class Status {
        /**
         * this is the network we are currently connected to
         */
        @Deprecated
        public static final int CURRENT = 0;

        /**
         * supplicant will not attempt to use this network
         */
        @Deprecated
        public static final int DISABLED = 1;

        /**
         * supplicant will consider this network available for association
         */
        @Deprecated
        public static final int ENABLED = 2;

        private Status() {
        }
    }
}
