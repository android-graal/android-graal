package android.net.sip;

/**
 * Represents a SIP session that is associated with a SIP dialog or a standalone
 * transaction not within a dialog.
 * <p>You can get a {@link SipSession} from {@link SipManager} with {@link
 * SipManager#createSipSession createSipSession()} (when initiating calls) or {@link
 * SipManager#getSessionFor getSessionFor()} (when receiving calls).</p>
 * @deprecated {@link android.net.sip.SipManager} and associated classes are no longer supported and
 * should not be used as the basis of future VOIP apps.
 */
@Deprecated
public final class SipSession {
    private SipSession() {
    }

    /**
     * Defines SIP session states, such as "registering", "outgoing call", and "in call".
     */
    @Deprecated
    public static final class State {
        /**
         * When the unregistration request is sent out.
         */
        @Deprecated
        public static final int DEREGISTERING = 2;

        /**
         * When an INVITE request is received.
         */
        @Deprecated
        public static final int INCOMING_CALL = 3;

        /**
         * When an OK response is sent for the INVITE request received.
         */
        @Deprecated
        public static final int INCOMING_CALL_ANSWERING = 4;

        /**
         * When a call is established.
         */
        @Deprecated
        public static final int IN_CALL = 8;

        /**
         * Not defined.
         */
        @Deprecated
        public static final int NOT_DEFINED = 101;

        /**
         * When an INVITE request is sent.
         */
        @Deprecated
        public static final int OUTGOING_CALL = 5;

        /**
         * When a CANCEL request is sent for the INVITE request sent.
         */
        @Deprecated
        public static final int OUTGOING_CALL_CANCELING = 7;

        /**
         * When a RINGING response is received for the INVITE request sent.
         */
        @Deprecated
        public static final int OUTGOING_CALL_RING_BACK = 6;

        /**
         * When an OPTIONS request is sent.
         */
        @Deprecated
        public static final int PINGING = 9;

        /**
         * When session is ready to initiate a call or transaction.
         */
        @Deprecated
        public static final int READY_TO_CALL = 0;

        /**
         * When the registration request is sent out.
         */
        @Deprecated
        public static final int REGISTERING = 1;

        private State() {
        }
    }
}
