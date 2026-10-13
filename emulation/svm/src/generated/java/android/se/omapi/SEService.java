package android.se.omapi;

/**
 * The SEService realises the communication to available Secure Elements on the
 * device. This is the entry point of this API. It is used to connect to the
 * infrastructure and get access to a list of Secure Element Readers.
 *
 * @see <a href="http://simalliance.org">SIMalliance Open Mobile API  v3.0</a>
 */
public final class SEService {
    /**
     * Broadcast Action: Intent to notify if the secure element state is changed.
     */
    public static final String ACTION_SECURE_ELEMENT_STATE_CHANGED = "android.se.omapi.action.SECURE_ELEMENT_STATE_CHANGED";

    /**
     * Mandatory extra containing the reader name of the state changed secure element.
     *
     * @see Reader#getName()
     */
    public static final String EXTRA_READER_NAME = "android.se.omapi.extra.READER_NAME";

    /**
     * Mandatory extra containing the connected state of the state changed secure element.
     *
     * True if the secure element is connected correctly, false otherwise.
     */
    public static final String EXTRA_READER_STATE = "android.se.omapi.extra.READER_STATE";

    private SEService() {
    }
}
