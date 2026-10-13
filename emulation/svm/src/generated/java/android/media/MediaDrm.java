package android.media;

/**
 * MediaDrm can be used to obtain keys for decrypting protected media streams, in
 * conjunction with {@link android.media.MediaCrypto}.  The MediaDrm APIs
 * are designed to support the ISO/IEC 23001-7: Common Encryption standard, but
 * may also be used to implement other encryption schemes.
 * <p>
 * Encrypted content is prepared using an encryption server and stored in a content
 * library. The encrypted content is streamed or downloaded from the content library to
 * client devices via content servers.  Licenses to view the content are obtained from
 * a License Server.
 * <p>
 * <p><img src="../../../images/mediadrm_overview.png"
 *      alt="MediaDrm Overview diagram"
 *      border="0" /></p>
 * <p>
 * Keys are requested from the license server using a key request. The key
 * response is delivered to the client app, which provides the response to the
 * MediaDrm API.
 * <p>
 * A Provisioning server may be required to distribute device-unique credentials to
 * the devices.
 * <p>
 * Enforcing requirements related to the number of devices that may play content
 * simultaneously can be performed either through key renewal or using the secure
 * stop methods.
 * <p>
 * The following sequence diagram shows the interactions between the objects
 * involved while playing back encrypted content:
 * <p>
 * <p><img src="../../../images/mediadrm_decryption_sequence.png"
 *         alt="MediaDrm Overview diagram"
 *         border="0" /></p>
 * <p>
 * The app first constructs {@link android.media.MediaExtractor} and
 * {@link android.media.MediaCodec} objects. It accesses the DRM-scheme-identifying UUID,
 * typically from metadata in the content, and uses this UUID to construct an instance
 * of a MediaDrm object that is able to support the DRM scheme required by the content.
 * Crypto schemes are assigned 16 byte UUIDs.  The method {@link #isCryptoSchemeSupported}
 * can be used to query if a given scheme is supported on the device.
 * <p>
 * The app calls {@link #openSession} to generate a sessionId that will uniquely identify
 * the session in subsequent interactions. The app next uses the MediaDrm object to
 * obtain a key request message and send it to the license server, then provide
 * the server's response to the MediaDrm object.
 * <p>
 * Once the app has a sessionId, it can construct a MediaCrypto object from the UUID and
 * sessionId.  The MediaCrypto object is registered with the MediaCodec in the
 * {@link MediaCodec#configure} method to enable the codec to decrypt content.
 * <p>
 * When the app has constructed {@link android.media.MediaExtractor},
 * {@link android.media.MediaCodec} and {@link android.media.MediaCrypto} objects,
 * it proceeds to pull samples from the extractor and queue them into the decoder.  For
 * encrypted content, the samples returned from the extractor remain encrypted, they
 * are only decrypted when the samples are delivered to the decoder.
 * <p>
 * MediaDrm methods throw {@link android.media.MediaDrm.MediaDrmStateException}
 * when a method is called on a MediaDrm object that has had an unrecoverable failure
 * in the DRM plugin or security hardware.
 * {@link android.media.MediaDrm.MediaDrmStateException} extends
 * {@link java.lang.IllegalStateException} with the addition of a developer-readable
 * diagnostic information string associated with the exception.
 * <p>
 * In the event of a mediaserver process crash or restart while a MediaDrm object
 * is active, MediaDrm methods may throw {@link android.media.MediaDrmResetException}.
 * To recover, the app must release the MediaDrm object, then create and initialize
 * a new one.
 * <p>
 * As {@link android.media.MediaDrmResetException} and
 * {@link android.media.MediaDrm.MediaDrmStateException} both extend
 * {@link java.lang.IllegalStateException}, they should be in an earlier catch()
 * block than {@link java.lang.IllegalStateException} if handled separately.
 * <p>
 * <a name="Callbacks"></a>
 * <h3>Callbacks</h3>
 * <p>Applications should register for informational events in order
 * to be informed of key state updates during playback or streaming.
 * Registration for these events is done via a call to
 * {@link #setOnEventListener}. In order to receive the respective
 * callback associated with this listener, applications are required to create
 * MediaDrm objects on a thread with its own Looper running (main UI
 * thread by default has a Looper running).
 */
public final class MediaDrm {
    /**
     * This event type indicates that the licensed usage duration for keys in a session
     * has expired.  The keys are no longer valid.
     * @deprecated Use {@link OnKeyStatusChangeListener#onKeyStatusChange}
     * and check for {@link MediaDrm.KeyStatus#STATUS_EXPIRED} in the {@link MediaDrm.KeyStatus}
     * instead.
     */
    @Deprecated
    public static final int EVENT_KEY_EXPIRED = 3;

    /**
     * This event type indicates that the app needs to request keys from a license
     * server.  The request message data is obtained using {@link #getKeyRequest}.
     */
    public static final int EVENT_KEY_REQUIRED = 2;

    /**
     * This event type indicates that the app needs to request a certificate from
     * the provisioning server.  The request message data is obtained using
     * {@link #getProvisionRequest}
     *
     * @deprecated Handle provisioning via {@link android.media.NotProvisionedException}
     * instead.
     */
    @Deprecated
    public static final int EVENT_PROVISION_REQUIRED = 1;

    /**
     * This event indicates that a session opened by the app has been reclaimed by the resource
     * manager.
     */
    public static final int EVENT_SESSION_RECLAIMED = 5;

    /**
     * This event may indicate some specific vendor-defined condition, see your
     * DRM provider documentation for details
     */
    public static final int EVENT_VENDOR_DEFINED = 4;

    /**
     * The DRM plugin did not report an HDCP level, or an error
     * occurred accessing it
     */
    public static final int HDCP_LEVEL_UNKNOWN = 0;

    /**
     * HDCP is not supported on this device, content is unprotected
     */
    public static final int HDCP_NONE = 1;

    /**
     * No digital output, implicitly secure
     */
    public static final int HDCP_NO_DIGITAL_OUTPUT = 2147483647;

    /**
     * HDCP version 1.0
     */
    public static final int HDCP_V1 = 2;

    /**
     * HDCP version 2.0 Type 1.
     */
    public static final int HDCP_V2 = 3;

    /**
     * HDCP version 2.1 Type 1.
     */
    public static final int HDCP_V2_1 = 4;

    /**
     *  HDCP version 2.2 Type 1.
     */
    public static final int HDCP_V2_2 = 5;

    /**
     *  HDCP version 2.3 Type 1.
     */
    public static final int HDCP_V2_3 = 6;

    /**
     * This key request type specifies that the keys will be for offline use, they
     * will be saved to the device for use when the device is not connected to a network.
     */
    public static final int KEY_TYPE_OFFLINE = 2;

    /**
     * This key request type specifies that previously saved offline keys should be released.
     */
    public static final int KEY_TYPE_RELEASE = 3;

    /**
     * This key request type species that the keys will be for online use, they will
     * not be saved to the device for subsequent use when the device is not connected
     * to a network.
     */
    public static final int KEY_TYPE_STREAMING = 1;

    /**
     * Offline license is released, the keys have been marked for
     * release using {@link #getKeyRequest} with KEY_TYPE_RELEASE but
     * the key response has not been received.
     */
    public static final int OFFLINE_LICENSE_STATE_RELEASED = 2;

    /**
     * Offline license state is unknown, an error occurred while trying
     * to access it.
     */
    public static final int OFFLINE_LICENSE_STATE_UNKNOWN = 0;

    /**
     * Offline license is usable, the keys may be used for decryption.
     */
    public static final int OFFLINE_LICENSE_STATE_USABLE = 1;

    /**
     * String property name: a comma-separated list of cipher and mac algorithms
     * supported by CryptoSession.  The list may be empty if the DRM
     * plugin does not support CryptoSession operations.
     */
    public static final String PROPERTY_ALGORITHMS = "algorithms";

    /**
     * String property name: describes the DRM plugin
     */
    public static final String PROPERTY_DESCRIPTION = "description";

    /**
     * Byte array property name: the device unique identifier is established during
     * device provisioning and provides a means of uniquely identifying each device.
     */
    public static final String PROPERTY_DEVICE_UNIQUE_ID = "deviceUniqueId";

    /**
     * String property name: identifies the maker of the DRM plugin
     */
    public static final String PROPERTY_VENDOR = "vendor";

    /**
     * String property name: identifies the version of the DRM plugin
     */
    public static final String PROPERTY_VERSION = "version";

    /**
     * DRM key management, crypto operations, decoding of content and all
     * handling of the media (compressed and uncompressed) is handled within a
     * hardware backed trusted execution environment.
     */
    public static final int SECURITY_LEVEL_HW_SECURE_ALL = 5;

    /**
     * DRM key management and crypto operations are performed within a hardware
     * backed trusted execution environment.
     */
    public static final int SECURITY_LEVEL_HW_SECURE_CRYPTO = 3;

    /**
     * DRM key management, crypto operations and decoding of content are
     * performed within a hardware backed trusted execution environment.
     */
    public static final int SECURITY_LEVEL_HW_SECURE_DECODE = 4;

    /**
     * DRM key management uses software-based whitebox crypto.
     */
    public static final int SECURITY_LEVEL_SW_SECURE_CRYPTO = 1;

    /**
     * DRM key management and decoding use software-based whitebox crypto.
     */
    public static final int SECURITY_LEVEL_SW_SECURE_DECODE = 2;

    /**
     * The DRM plugin did not report a security level, or an error occurred
     * accessing it
     */
    public static final int SECURITY_LEVEL_UNKNOWN = 0;

    private MediaDrm() {
    }

    /**
     * Error codes that may be returned from {@link
     * MediaDrmStateException#getErrorCode()} and {@link
     * MediaCodec.CryptoException#getErrorCode()}
     * <p>
     * The description of each error code includes steps that may be taken to
     * resolve the error condition. For some errors however, a recovery action
     * cannot be predetermined. The description of those codes refers to a
     * general strategy for handling the error condition programmatically, which
     * is to try the following in listed order until successful:
     * <ol>
     * <li> retry the operation </li>
     * <li> if the operation is related to a session, {@link
     * #closeSession(byte[]) close} the session, {@link #openSession() open} a
     * new session, and retry the operation </li>
     * <li> {@link #close() close} the {@link MediaDrm} instance and any other
     * related components such as the {@link MediaCodec codec} and retry
     * playback, or </li>
     * <li> try using a different configuration of the {@link MediaDrm} plugin,
     * such as a different {@link #openSession(int) security level}. </li>
     * </ol>
     * <p>
     * If the problem still persists after all the aforementioned steps, please
     * report the failure to the {@link MediaDrm} plugin vendor along with the
     * {@link LogMessage log messages} returned by {@link
     * MediaDrm#getLogMessages()}, and a bugreport if possible.
     */
    public static final class ErrorCodes {
        /**
         * Certificate is malformed or is of the wrong type.
         * <p>
         * Ensure the certificate provided by the app or returned from the
         * license server is valid. Check with the {@link MediaDrm} plugin
         * vendor for the expected certificate format.
         */
        public static final int ERROR_CERTIFICATE_MALFORMED = 10;

        /**
         * Certificate has not been set.
         * <p>
         * Ensure the certificate has been provided by the app. Check with the
         * {@link MediaDrm} plugin vendor for the expected method to provide
         * {@link MediaDrm} a certificate.
         */
        public static final int ERROR_CERTIFICATE_MISSING = 11;

        /**
         * An error happened within the crypto library used by the drm plugin.
         */
        public static final int ERROR_CRYPTO_LIBRARY = 12;

        /**
         * The video frame being decrypted exceeds the size of the device's
         * protected output buffers.
         * <p>
         * When encountering this error the app should try playing content
         * of a lower resolution or skipping the problematic frame.
         */
        public static final int ERROR_FRAME_TOO_LARGE = 8;

        /**
         * Unexpected error reported by the device OEM subsystem.
         * <p>
         * Please see the general error handling strategy for unexpected errors
         * described in {@link ErrorCodes}.
         */
        public static final int ERROR_GENERIC_OEM = 13;

        /**
         * Unexpected internal failure in {@link MediaDrm}/{@link MediaCrypto}.
         * <p>
         * Please see the general error handling strategy for unexpected errors
         * described in {@link ErrorCodes}.
         */
        public static final int ERROR_GENERIC_PLUGIN = 14;

        /**
         * The init data parameter passed to {@link MediaDrm#getKeyRequest} is
         * empty or invalid.
         * <p>
         * Init data is typically obtained from {@link
         * MediaExtractor#getPsshInfo()} or {@link
         * MediaExtractor#getDrmInitData()}. Check with the {@link MediaDrm}
         * plugin vendor for the expected init data format.
         */
        public static final int ERROR_INIT_DATA = 15;

        /**
         * The output protection levels supported by the device are not
         * sufficient to meet the requirements set by the content owner in the
         * license policy.
         */
        public static final int ERROR_INSUFFICIENT_OUTPUT_PROTECTION = 4;

        /**
         * The security level of the device is not sufficient to meet the
         * requirements set by the content owner in the license policy.
         */
        public static final int ERROR_INSUFFICIENT_SECURITY = 7;

        /**
         * The key used for decryption is no longer valid due to license term
         * expiration.
         * <p>
         * The operation can be retried after updating the expired keys.
         */
        public static final int ERROR_KEY_EXPIRED = 2;

        /**
         * Either the key was not loaded from the license before attempting the
         * operation, or the key ID parameter provided by the app is incorrect.
         * <p>
         * Ensure the proper keys are in the license, and check the key ID
         * parameter provided by the app is correct. Check with the {@link
         * MediaDrm} plugin vendor for the expected license format.
         */
        public static final int ERROR_KEY_NOT_LOADED = 16;

        /**
         * The license response was empty, fields are missing or otherwise
         * unable to be parsed or decrypted.
         * <p>
         * Check for mistakes such as empty or overwritten buffers. Otherwise,
         * check with the {@link MediaDrm} plugin vendor for the expected
         * license format.
         */
        public static final int ERROR_LICENSE_PARSE = 17;

        /**
         * The operation (e.g. to renew or persist a license) is prohibited by
         * the license policy.
         * <p>
         * Check the license policy configuration on the license server.
         */
        public static final int ERROR_LICENSE_POLICY = 18;

        /**
         * Failed to generate a release request because a field in the offline
         * license is empty or malformed.
         * <p>
         * The license can't be released on the server, but the app may remove
         * the offline license explicitly using {@link
         * MediaDrm#removeOfflineLicense}.
         */
        public static final int ERROR_LICENSE_RELEASE = 19;

        /**
         * The license server detected an error in the license request.
         * <p>
         * Check for errors on the license server.
         */
        public static final int ERROR_LICENSE_REQUEST_REJECTED = 20;

        /**
         * Failed to restore an offline license because a field in the offline
         * license is empty or malformed.
         * <p>
         * Try requesting the license again if the device is online.
         */
        public static final int ERROR_LICENSE_RESTORE = 21;

        /**
         * Offline license is in an invalid state for the attempted operation.
         * <p>
         * Check the sequence of API calls made that can affect offline license
         * state. For example, this could happen when the app attempts to
         * restore a license after it has been released.
         */
        public static final int ERROR_LICENSE_STATE = 22;

        /**
         * The session state has been invalidated. This can occur on devices
         * that are not capable of retaining crypto session state across device
         * suspend/resume.
         * <p>
         * The session must be closed and a new session opened to resume
         * operation.
         */
        public static final int ERROR_LOST_STATE = 9;

        /**
         * Failure in the media framework.
         * <p>
         * Try releasing media resources (e.g. {@link MediaCodec}, {@link
         * MediaDrm}), and restarting playback.
         */
        public static final int ERROR_MEDIA_FRAMEWORK = 23;

        /**
         * The requested key was not found when trying to perform a decrypt
         * operation.
         * <p>
         * The operation can be retried after adding the correct decryption key.
         */
        public static final int ERROR_NO_KEY = 1;

        /**
         * Error loading the provisioned certificate.
         * <p>
         * Re-provisioning may resolve the problem; check with the {@link
         * MediaDrm} plugin vendor for re-provisioning instructions. Otherwise,
         * using a different security level may resolve the issue.
         */
        public static final int ERROR_PROVISIONING_CERTIFICATE = 24;

        /**
         * Required steps were not performed before provisioning was attempted.
         * <p>
         * Ask the {@link MediaDrm} plugin vendor for situations where this
         * error may occur.
         */
        public static final int ERROR_PROVISIONING_CONFIG = 25;

        /**
         * The provisioning response was empty, fields are missing or otherwise
         * unable to be parsed.
         * <p>
         * Check for mistakes such as empty or overwritten buffers. Otherwise,
         * check with the {@link MediaDrm} plugin vendor for the expected
         * provisioning response format.
         */
        public static final int ERROR_PROVISIONING_PARSE = 26;

        /**
         * The provisioning server detected an error in the provisioning
         * request.
         * <p>
         * Check for errors on the provisioning server.
         */
        public static final int ERROR_PROVISIONING_REQUEST_REJECTED = 27;

        /**
         * Provisioning failed in a way that is likely to succeed on a
         * subsequent attempt.
         * <p>
         * The app should retry the operation.
         */
        public static final int ERROR_PROVISIONING_RETRY = 28;

        /**
         * A required crypto resource was not able to be allocated while
         * attempting the requested operation.
         * <p>
         * The operation can be retried if the app is able to release resources.
         */
        public static final int ERROR_RESOURCE_BUSY = 3;

        /**
         * This indicates that apps using MediaDrm sessions are
         * temporarily exceeding the capacity of available crypto
         * resources.
         * <p>
         * The app should retry the operation later.
         */
        public static final int ERROR_RESOURCE_CONTENTION = 29;

        /**
         * Failed to generate a secure stop request because a field in the
         * stored license is empty or malformed.
         * <p>
         * The secure stop can't be released on the server, but the app may
         * remove it explicitly using {@link MediaDrm#removeSecureStop}.
         */
        public static final int ERROR_SECURE_STOP_RELEASE = 30;

        /**
         * Decryption was attempted on a session that is not opened, which could
         * be due to a failure to open the session, closing the session
         * prematurely, the session being reclaimed by the resource manager, or
         * a non-existent session id.
         */
        public static final int ERROR_SESSION_NOT_OPENED = 5;

        /**
         * The plugin was unable to read data from the filesystem.
         * <p>
         * Please see the general error handling strategy for unexpected errors
         * described in {@link ErrorCodes}.
         */
        public static final int ERROR_STORAGE_READ = 31;

        /**
         * The plugin was unable to write data to the filesystem.
         * <p>
         * Please see the general error handling strategy for unexpected errors
         * described in {@link ErrorCodes}.
         */
        public static final int ERROR_STORAGE_WRITE = 32;

        /**
         * ERROR_UNKNOWN is used where no other defined error code is applicable
         * to the current failure.
         * <p>
         * Please see the general error handling strategy for unexpected errors
         * described in {@link ErrorCodes}.
         */
        public static final int ERROR_UNKNOWN = 0;

        /**
         * An operation was attempted that could not be supported by the crypto
         * system of the device in its current configuration.
         * <p>
         * This may occur when the license policy requires device security
         * features that aren't supported by the device, or due to an internal
         * error in the crypto system that prevents the specified security
         * policy from being met.
         */
        public static final int ERROR_UNSUPPORTED_OPERATION = 6;

        /**
         * {@link MediaCodec#queueSecureInputBuffer} called with 0 subsamples.
         * <p>
         * Check the {@link MediaCodec.CryptoInfo} object passed to {@link
         * MediaCodec#queueSecureInputBuffer}.
         */
        public static final int ERROR_ZERO_SUBSAMPLES = 33;

        private ErrorCodes() {
        }
    }

    /**
     * Contains the opaque data an app uses to request keys from a license server.
     * These request types may or may not be generated by a given plugin. Refer
     * to plugin vendor documentation for more information.
     */
    public static final class KeyRequest {
        /**
         * Key request type is initial license request. A license request
         * is necessary to load keys.
         */
        public static final int REQUEST_TYPE_INITIAL = 0;

        /**
         * Keys are already loaded and are available for use. No license request is necessary, and
         * no key request data is returned.
         */
        public static final int REQUEST_TYPE_NONE = 3;

        /**
         * Key request type is license release
         */
        public static final int REQUEST_TYPE_RELEASE = 2;

        /**
         * Key request type is license renewal. A license request is
         * necessary to prevent the keys from expiring.
         */
        public static final int REQUEST_TYPE_RENEWAL = 1;

        /**
         * Keys have been loaded but an additional license request is needed
         * to update their values.
         */
        public static final int REQUEST_TYPE_UPDATE = 4;

        private KeyRequest() {
        }
    }

    /**
     * Defines the status of a key.
     * A KeyStatus for each key in a session is provided to the
     * {@link OnKeyStatusChangeListener#onKeyStatusChange}
     * listener.
     */
    public static final class KeyStatus {
        /**
         * The key is no longer usable to decrypt media data because its
         * expiration time has passed.
         */
        public static final int STATUS_EXPIRED = 1;

        /**
         * The key is not currently usable to decrypt media data because of an
         * internal error in processing unrelated to input parameters.  This error
         * is not actionable by an app.
         */
        public static final int STATUS_INTERNAL_ERROR = 4;

        /**
         * The key is not currently usable to decrypt media data because its
         * output requirements cannot currently be met.
         */
        public static final int STATUS_OUTPUT_NOT_ALLOWED = 2;

        /**
         * The status of the key is not yet known and is being determined.
         * The status will be updated with the actual status when it has
         * been determined.
         */
        public static final int STATUS_PENDING = 3;

        /**
         * The key is currently usable to decrypt media data
         */
        public static final int STATUS_USABLE = 0;

        /**
         * The key is not yet usable to decrypt media because the start
         * time is in the future. The key will become usable when
         * its start time is reached.
         */
        public static final int STATUS_USABLE_IN_FUTURE = 5;

        private KeyStatus() {
        }
    }

    /**
     * Definitions for the metrics that are reported via the
     * {@link #getMetrics} call.
     */
    public static final class MetricsConstants {
        /**
         * Key to extract the number of failed {@link #closeSession} calls
         * from the {@link PersistableBundle} returned by a
         * {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String CLOSE_SESSION_ERROR_COUNT = "drm.mediadrm.close_session.error.count";

        /**
         * Key to extract the list of error codes that were returned from
         * {@link #closeSession} calls. The key is used to lookup the list
         * in the {@link PersistableBundle} returned by a {@link #getMetrics}
         * call.
         * The list is an array of Long values
         * ({@link android.os.BaseBundle#getLongArray}).
         */
        public static final String CLOSE_SESSION_ERROR_LIST = "drm.mediadrm.close_session.error.list";

        /**
         * Key to extract the number of successful {@link #closeSession} calls
         * from the {@link PersistableBundle} returned by a
         * {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String CLOSE_SESSION_OK_COUNT = "drm.mediadrm.close_session.ok.count";

        /**
         * Key to extract the count of {@link OnEventListener#onEvent}
         * calls of type KEY_EXPIRED occured. The count is
         * extracted from the {@link PersistableBundle} returned from a
         * {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String EVENT_KEY_EXPIRED_COUNT = "drm.mediadrm.event.KEY_EXPIRED.count";

        /**
         * Key to extract the count of {@link OnEventListener#onEvent}
         * calls of type KEY_NEEDED occured. The count is
         * extracted from the {@link PersistableBundle} returned from a
         * {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String EVENT_KEY_NEEDED_COUNT = "drm.mediadrm.event.KEY_NEEDED.count";

        /**
         * Key to extract the count of {@link OnEventListener#onEvent}
         * calls of type PROVISION_REQUIRED occured. The count is
         * extracted from the {@link PersistableBundle} returned from a
         * {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String EVENT_PROVISION_REQUIRED_COUNT = "drm.mediadrm.event.PROVISION_REQUIRED.count";

        /**
         * Key to extract the count of {@link OnEventListener#onEvent}
         * calls of type SESSION_RECLAIMED. The count is
         * extracted from the {@link PersistableBundle} returned from a
         * {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String EVENT_SESSION_RECLAIMED_COUNT = "drm.mediadrm.event.SESSION_RECLAIMED.count";

        /**
         * Key to extract the count of {@link OnEventListener#onEvent}
         * calls of type VENDOR_DEFINED. The count is
         * extracted from the {@link PersistableBundle} returned from a
         * {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String EVENT_VENDOR_DEFINED_COUNT = "drm.mediadrm.event.VENDOR_DEFINED.count";

        /**
         * Key to extract the number of failed
         * {@link #getPropertyByteArray} calls were made with the
         * {@link #PROPERTY_DEVICE_UNIQUE_ID} value. The key is used to lookup
         * the value in the {@link PersistableBundle} returned by a
         * {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String GET_DEVICE_UNIQUE_ID_ERROR_COUNT = "drm.mediadrm.get_device_unique_id.error.count";

        /**
         * Key to extract the list of error codes that were returned from
         * {@link #getPropertyByteArray} calls with the
         * {@link #PROPERTY_DEVICE_UNIQUE_ID} value. The key is used to lookup
         * the list in the {@link PersistableBundle} returned by a
         * {@link #getMetrics} call.
         * The list is an array of Long values
         * ({@link android.os.BaseBundle#getLongArray}).
         */
        public static final String GET_DEVICE_UNIQUE_ID_ERROR_LIST = "drm.mediadrm.get_device_unique_id.error.list";

        /**
         * Key to extract the number of successful
         * {@link #getPropertyByteArray} calls were made with the
         * {@link #PROPERTY_DEVICE_UNIQUE_ID} value. The key is used to lookup
         * the value in the {@link PersistableBundle} returned by a
         * {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String GET_DEVICE_UNIQUE_ID_OK_COUNT = "drm.mediadrm.get_device_unique_id.ok.count";

        /**
         * Key to extract the number of failed {@link #getKeyRequest}
         * calls from the {@link PersistableBundle} returned by a
         * {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String GET_KEY_REQUEST_ERROR_COUNT = "drm.mediadrm.get_key_request.error.count";

        /**
         * Key to extract the list of error codes that were returned from
         * {@link #getKeyRequest} calls. The key is used to lookup the list
         * in the {@link PersistableBundle} returned by a {@link #getMetrics}
         * call.
         * The list is an array of Long values
         * ({@link android.os.BaseBundle#getLongArray}).
         */
        public static final String GET_KEY_REQUEST_ERROR_LIST = "drm.mediadrm.get_key_request.error.list";

        /**
         * Key to extract the number of successful {@link #getKeyRequest} calls
         * from the {@link PersistableBundle} returned by a
         * {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String GET_KEY_REQUEST_OK_COUNT = "drm.mediadrm.get_key_request.ok.count";

        /**
         * Key to extract the average time in microseconds of calls to
         * {@link #getKeyRequest}. The value is retrieved from the
         * {@link PersistableBundle} returned from {@link #getMetrics}.
         * The time is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String GET_KEY_REQUEST_OK_TIME_MICROS = "drm.mediadrm.get_key_request.ok.average_time_micros";

        /**
         * Key to extract the number of failed {@link #getProvisionRequest}
         * calls from the {@link PersistableBundle} returned by a
         * {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String GET_PROVISION_REQUEST_ERROR_COUNT = "drm.mediadrm.get_provision_request.error.count";

        /**
         * Key to extract the list of error codes that were returned from
         * {@link #getProvisionRequest} calls. The key is used to lookup the
         * list in the {@link PersistableBundle} returned by a
         * {@link #getMetrics} call.
         * The list is an array of Long values
         * ({@link android.os.BaseBundle#getLongArray}).
         */
        public static final String GET_PROVISION_REQUEST_ERROR_LIST = "drm.mediadrm.get_provision_request.error.list";

        /**
         * Key to extract the number of successful {@link #getProvisionRequest}
         * calls from the {@link PersistableBundle} returned by a
         * {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String GET_PROVISION_REQUEST_OK_COUNT = "drm.mediadrm.get_provision_request.ok.count";

        /**
         * Key to extract the count of {@link KeyStatus#STATUS_EXPIRED} events
         * that occured. The count is extracted from the
         * {@link PersistableBundle} returned from a {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String KEY_STATUS_EXPIRED_COUNT = "drm.mediadrm.key_status.EXPIRED.count";

        /**
         * Key to extract the count of {@link KeyStatus#STATUS_INTERNAL_ERROR}
         * events that occured. The count is extracted from the
         * {@link PersistableBundle} returned from a {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String KEY_STATUS_INTERNAL_ERROR_COUNT = "drm.mediadrm.key_status.INTERNAL_ERROR.count";

        /**
         * Key to extract the count of
         * {@link KeyStatus#STATUS_OUTPUT_NOT_ALLOWED} events that occured.
         * The count is extracted from the
         * {@link PersistableBundle} returned from a {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String KEY_STATUS_OUTPUT_NOT_ALLOWED_COUNT = "drm.mediadrm.key_status_change.OUTPUT_NOT_ALLOWED.count";

        /**
         * Key to extract the count of {@link KeyStatus#STATUS_PENDING}
         * events that occured. The count is extracted from the
         * {@link PersistableBundle} returned from a {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String KEY_STATUS_PENDING_COUNT = "drm.mediadrm.key_status_change.PENDING.count";

        /**
         * Key to extract the count of {@link KeyStatus#STATUS_USABLE}
         * events that occured. The count is extracted from the
         * {@link PersistableBundle} returned from a {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String KEY_STATUS_USABLE_COUNT = "drm.mediadrm.key_status_change.USABLE.count";

        /**
         * Key to extract the number of failed {@link #openSession} calls
         * from the {@link PersistableBundle} returned by a
         * {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String OPEN_SESSION_ERROR_COUNT = "drm.mediadrm.open_session.error.count";

        /**
         * Key to extract the list of error codes that were returned from
         * {@link #openSession} calls. The key is used to lookup the list
         * in the {@link PersistableBundle} returned by a {@link #getMetrics}
         * call.
         * The list is an array of Long values
         * ({@link android.os.BaseBundle#getLongArray}).
         */
        public static final String OPEN_SESSION_ERROR_LIST = "drm.mediadrm.open_session.error.list";

        /**
         * Key to extract the number of successful {@link #openSession} calls
         * from the {@link PersistableBundle} returned by a
         * {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String OPEN_SESSION_OK_COUNT = "drm.mediadrm.open_session.ok.count";

        /**
         * Key to extract the number of failed {@link #provideKeyResponse}
         * calls from the {@link PersistableBundle} returned by a
         * {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String PROVIDE_KEY_RESPONSE_ERROR_COUNT = "drm.mediadrm.provide_key_response.error.count";

        /**
         * Key to extract the list of error codes that were returned from
         * {@link #provideKeyResponse} calls. The key is used to lookup the
         * list in the {@link PersistableBundle} returned by a
         * {@link #getMetrics} call.
         * The list is an array of Long values
         * ({@link android.os.BaseBundle#getLongArray}).
         */
        public static final String PROVIDE_KEY_RESPONSE_ERROR_LIST = "drm.mediadrm.provide_key_response.error.list";

        /**
         * Key to extract the number of successful {@link #provideKeyResponse}
         * calls from the {@link PersistableBundle} returned by a
         * {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String PROVIDE_KEY_RESPONSE_OK_COUNT = "drm.mediadrm.provide_key_response.ok.count";

        /**
         * Key to extract the average time in microseconds of calls to
         * {@link #provideKeyResponse}. The valus is retrieved from the
         * {@link PersistableBundle} returned from {@link #getMetrics}.
         * The time is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String PROVIDE_KEY_RESPONSE_OK_TIME_MICROS = "drm.mediadrm.provide_key_response.ok.average_time_micros";

        /**
         * Key to extract the number of failed
         * {@link #provideProvisionResponse} calls from the
         * {@link PersistableBundle} returned by a {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String PROVIDE_PROVISION_RESPONSE_ERROR_COUNT = "drm.mediadrm.provide_provision_response.error.count";

        /**
         * Key to extract the list of error codes that were returned from
         * {@link #provideProvisionResponse} calls. The key is used to lookup
         * the list in the {@link PersistableBundle} returned by a
         * {@link #getMetrics} call.
         * The list is an array of Long values
         * ({@link android.os.BaseBundle#getLongArray}).
         */
        public static final String PROVIDE_PROVISION_RESPONSE_ERROR_LIST = "drm.mediadrm.provide_provision_response.error.list";

        /**
         * Key to extract the number of successful
         * {@link #provideProvisionResponse} calls from the
         * {@link PersistableBundle} returned by a {@link #getMetrics} call.
         * The count is a Long value ({@link android.os.BaseBundle#getLong}).
         */
        public static final String PROVIDE_PROVISION_RESPONSE_OK_COUNT = "drm.mediadrm.provide_provision_response.ok.count";

        /**
         * Key to extract the end times of sessions. Times are
         * represented as milliseconds since epoch (1970-01-01T00:00:00Z).
         * The end times are returned from the {@link PersistableBundle}
         * from a {@link #getMetrics} call.
         * The end times are returned as another {@link PersistableBundle}
         * containing the session ids as keys and the end times as long
         * values. Use {@link android.os.BaseBundle#keySet} to get the list of
         * session ids, and then {@link android.os.BaseBundle#getLong} to get
         * the end time for each session.
         */
        public static final String SESSION_END_TIMES_MS = "drm.mediadrm.session_end_times_ms";

        /**
         * Key to extract the start times of sessions. Times are
         * represented as milliseconds since epoch (1970-01-01T00:00:00Z).
         * The start times are returned from the {@link PersistableBundle}
         * from a {@link #getMetrics} call.
         * The start times are returned as another {@link PersistableBundle}
         * containing the session ids as keys and the start times as long
         * values. Use {@link android.os.BaseBundle#keySet} to get the list of
         * session ids, and then {@link android.os.BaseBundle#getLong} to get
         * the start time for each session.
         */
        public static final String SESSION_START_TIMES_MS = "drm.mediadrm.session_start_times_ms";

        private MetricsConstants() {
        }
    }

    /**
     * {@link SessionException} is a misnomer because it may occur in methods
     * <b>without</b> a session context.
     * <p>
     * A {@link SessionException} is most likely to be thrown when an operation
     * failed in a way that is likely to succeed on a subsequent attempt; call
     * {@link #isTransient()} to determine whether the app should retry the
     * failing operation.
     */
    public static final class SessionException implements MediaDrmThrowable {
        /**
         * This indicates that apps using MediaDrm sessions are
         * temporarily exceeding the capacity of available crypto
         * resources. The app should retry the operation later.
         *
         * @deprecated Please use {@link #isTransient()} instead of comparing
         * the return value of {@link #getErrorCode()} against
         * {@link SessionException#ERROR_RESOURCE_CONTENTION}.
         */
        @Deprecated
        public static final int ERROR_RESOURCE_CONTENTION = 1;

        /**
         * The SessionException has an unknown error code.
         * @deprecated Unused.
         */
        @Deprecated
        public static final int ERROR_UNKNOWN = 0;

        private SessionException() {
        }
    }
}
