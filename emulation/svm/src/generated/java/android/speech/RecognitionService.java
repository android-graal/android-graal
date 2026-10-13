package android.speech;

/**
 * This class provides a base class for recognition service implementations. This class should be
 * extended only in case you wish to implement a new speech recognizer. Please note that the
 * implementation of this service is stateless.
 */
public final class RecognitionService {
    /**
     * The {@link Intent} that must be declared as handled by the service.
     */
    public static final String SERVICE_INTERFACE = "android.speech.RecognitionService";

    /**
     * Name under which a RecognitionService component publishes information about itself.
     * This meta-data should reference an XML resource containing a
     * <code>&lt;{@link android.R.styleable#RecognitionService recognition-service}&gt;</code> or
     * <code>&lt;{@link android.R.styleable#RecognitionService on-device-recognition-service}
     * &gt;</code> tag.
     */
    public static final String SERVICE_META_DATA = "android.speech";

    private RecognitionService() {
    }
}
