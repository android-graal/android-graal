package android.telephony;

/**
 * Contains access network related constants.
 */
public final class AccessNetworkConstants {
    /**
     * Transport type for Wireless Local Area Networks (i.e. Wifi)
     */
    public static final int TRANSPORT_TYPE_WLAN = 2;

    /**
     * Transport type for Wireless Wide Area Networks (i.e. Cellular)
     */
    public static final int TRANSPORT_TYPE_WWAN = 1;

    private AccessNetworkConstants() {
    }

    public static final class AccessNetworkType {
        public static final int CDMA2000 = 4;

        public static final int EUTRAN = 3;

        public static final int GERAN = 1;

        public static final int IWLAN = 5;

        public static final int NGRAN = 6;

        public static final int UNKNOWN = 0;

        public static final int UTRAN = 2;

        private AccessNetworkType() {
        }
    }

    /**
     * Frequency bands for EUTRAN.
     * 3GPP TS 36.101, Version 16.4.0, Table 5.5: Operating bands
     * https://www.etsi.org/deliver/etsi_ts/136100_136199/136101/15.09.00_60/ts_136101v150900p.pdf
     */
    public static final class EutranBand {
        public static final int BAND_1 = 1;

        public static final int BAND_10 = 10;

        public static final int BAND_11 = 11;

        public static final int BAND_12 = 12;

        public static final int BAND_13 = 13;

        public static final int BAND_14 = 14;

        public static final int BAND_17 = 17;

        public static final int BAND_18 = 18;

        public static final int BAND_19 = 19;

        public static final int BAND_2 = 2;

        public static final int BAND_20 = 20;

        public static final int BAND_21 = 21;

        public static final int BAND_22 = 22;

        public static final int BAND_23 = 23;

        public static final int BAND_24 = 24;

        public static final int BAND_25 = 25;

        public static final int BAND_26 = 26;

        public static final int BAND_27 = 27;

        public static final int BAND_28 = 28;

        public static final int BAND_3 = 3;

        public static final int BAND_30 = 30;

        public static final int BAND_31 = 31;

        public static final int BAND_33 = 33;

        public static final int BAND_34 = 34;

        public static final int BAND_35 = 35;

        public static final int BAND_36 = 36;

        public static final int BAND_37 = 37;

        public static final int BAND_38 = 38;

        public static final int BAND_39 = 39;

        public static final int BAND_4 = 4;

        public static final int BAND_40 = 40;

        public static final int BAND_41 = 41;

        public static final int BAND_42 = 42;

        public static final int BAND_43 = 43;

        public static final int BAND_44 = 44;

        public static final int BAND_45 = 45;

        public static final int BAND_46 = 46;

        public static final int BAND_47 = 47;

        public static final int BAND_48 = 48;

        public static final int BAND_49 = 49;

        public static final int BAND_5 = 5;

        public static final int BAND_50 = 50;

        public static final int BAND_51 = 51;

        public static final int BAND_52 = 52;

        public static final int BAND_53 = 53;

        public static final int BAND_6 = 6;

        public static final int BAND_65 = 65;

        public static final int BAND_66 = 66;

        public static final int BAND_68 = 68;

        public static final int BAND_7 = 7;

        public static final int BAND_70 = 70;

        public static final int BAND_71 = 71;

        public static final int BAND_72 = 72;

        public static final int BAND_73 = 73;

        public static final int BAND_74 = 74;

        public static final int BAND_8 = 8;

        public static final int BAND_85 = 85;

        public static final int BAND_87 = 87;

        public static final int BAND_88 = 88;

        public static final int BAND_9 = 9;

        private EutranBand() {
        }
    }

    /**
     * Frequency bands for GERAN.
     * http://www.etsi.org/deliver/etsi_ts/145000_145099/145005/14.00.00_60/ts_145005v140000p.pdf
     */
    public static final class GeranBand {
        public static final int BAND_450 = 3;

        public static final int BAND_480 = 4;

        public static final int BAND_710 = 5;

        public static final int BAND_750 = 6;

        public static final int BAND_850 = 8;

        public static final int BAND_DCS1800 = 12;

        public static final int BAND_E900 = 10;

        public static final int BAND_ER900 = 14;

        public static final int BAND_P900 = 9;

        public static final int BAND_PCS1900 = 13;

        public static final int BAND_R900 = 11;

        public static final int BAND_T380 = 1;

        public static final int BAND_T410 = 2;

        public static final int BAND_T810 = 7;

        private GeranBand() {
        }
    }

    /**
     * Frequency bands for NGRAN
     * https://www.etsi.org/deliver/etsi_ts/138100_138199/13810101/15.08.02_60/ts_13810101v150802p.pdf
     * https://www.etsi.org/deliver/etsi_ts/138100_138199/13810102/15.08.00_60/ts_13810102v150800p.pdf
     */
    public static final class NgranBands {
        /**
         * 3GPP TS 38.101-1, Version 16.5.0, Table 5.2-1: FR1 bands
         */
        public static final int BAND_1 = 1;

        public static final int BAND_12 = 12;

        public static final int BAND_14 = 14;

        public static final int BAND_18 = 18;

        public static final int BAND_2 = 2;

        public static final int BAND_20 = 20;

        public static final int BAND_25 = 25;

        /**
         * 3GPP TS 38.101-2, Version 16.2.0, Table 5.2-1: FR2 bands
         */
        public static final int BAND_257 = 257;

        public static final int BAND_258 = 258;

        public static final int BAND_26 = 26;

        public static final int BAND_260 = 260;

        public static final int BAND_261 = 261;

        public static final int BAND_28 = 28;

        public static final int BAND_29 = 29;

        public static final int BAND_3 = 3;

        public static final int BAND_30 = 30;

        public static final int BAND_34 = 34;

        public static final int BAND_38 = 38;

        public static final int BAND_39 = 39;

        public static final int BAND_40 = 40;

        public static final int BAND_41 = 41;

        public static final int BAND_46 = 46;

        public static final int BAND_48 = 48;

        public static final int BAND_5 = 5;

        public static final int BAND_50 = 50;

        public static final int BAND_51 = 51;

        public static final int BAND_53 = 53;

        public static final int BAND_65 = 65;

        public static final int BAND_66 = 66;

        public static final int BAND_7 = 7;

        public static final int BAND_70 = 70;

        public static final int BAND_71 = 71;

        public static final int BAND_74 = 74;

        public static final int BAND_75 = 75;

        public static final int BAND_76 = 76;

        public static final int BAND_77 = 77;

        public static final int BAND_78 = 78;

        public static final int BAND_79 = 79;

        public static final int BAND_8 = 8;

        public static final int BAND_80 = 80;

        public static final int BAND_81 = 81;

        public static final int BAND_82 = 82;

        public static final int BAND_83 = 83;

        public static final int BAND_84 = 84;

        public static final int BAND_86 = 86;

        public static final int BAND_89 = 89;

        public static final int BAND_90 = 90;

        public static final int BAND_91 = 91;

        public static final int BAND_92 = 92;

        public static final int BAND_93 = 93;

        public static final int BAND_94 = 94;

        public static final int BAND_95 = 95;

        public static final int BAND_96 = 96;

        private NgranBands() {
        }
    }

    /**
     * Frequency bands for UTRAN.
     * http://www.etsi.org/deliver/etsi_ts/125100_125199/125104/13.03.00_60/ts_125104v130p.pdf
     */
    public static final class UtranBand {
        public static final int BAND_1 = 1;

        public static final int BAND_10 = 10;

        public static final int BAND_11 = 11;

        public static final int BAND_12 = 12;

        public static final int BAND_13 = 13;

        public static final int BAND_14 = 14;

        public static final int BAND_19 = 19;

        public static final int BAND_2 = 2;

        public static final int BAND_20 = 20;

        public static final int BAND_21 = 21;

        public static final int BAND_22 = 22;

        public static final int BAND_25 = 25;

        public static final int BAND_26 = 26;

        public static final int BAND_3 = 3;

        public static final int BAND_4 = 4;

        public static final int BAND_5 = 5;

        public static final int BAND_6 = 6;

        public static final int BAND_7 = 7;

        public static final int BAND_8 = 8;

        public static final int BAND_9 = 9;

        /**
         * Band A
         * 1900 - 1920 MHz: Uplink and downlink transmission
         * 2010 - 2025 MHz: Uplink and downlink transmission
         */
        public static final int BAND_A = 101;

        /**
         * Band B
         * 1850 - 1910 MHz: Uplink and downlink transmission
         * 1930 - 1990 MHz: Uplink and downlink transmission
         */
        public static final int BAND_B = 102;

        /**
         * Band C
         * 1910 - 1930 MHz: Uplink and downlink transmission
         */
        public static final int BAND_C = 103;

        /**
         * Band D
         * 2570 - 2620 MHz: Uplink and downlink transmission
         */
        public static final int BAND_D = 104;

        /**
         * Band E
         * 2300—2400 MHz: Uplink and downlink transmission
         */
        public static final int BAND_E = 105;

        /**
         * Band F
         * 1880 - 1920 MHz: Uplink and downlink transmission
         */
        public static final int BAND_F = 106;

        private UtranBand() {
        }
    }
}
