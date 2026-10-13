package android.hardware.camera2;

/**
 * A camera capture session that was switched to offline mode via successful call to
 * {@link CameraCaptureSession#switchToOffline}.
 *
 * <p>Offline capture sessions allow clients to select a set of camera registered surfaces that
 * support offline mode.  After a successful offline mode switch all non-repeating pending requests
 * on those surfaces will continue to be processed by the camera stack even if clients close the
 * corresponding camera device.<p>
 *
 * <p>Offline capture session instances will replace the previously active capture session arguments
 * in all capture callbacks after {@link CameraCaptureSession#switchToOffline} completes.</p>
 *
 * <p>Processing of pending offline capture requests will begin only after the offline session
 * moves to ready state which will be indicated by the {@link CameraOfflineSessionCallback#onReady}
 * callback.</p>
 *
 * <p>In contrast to a regular {@link CameraCaptureSession} an offline capture session will
 * not accept any further capture requests. Besides {@link CameraOfflineSession#close} all
 * remaining methods will throw {@link UnsupportedOperationException} and are not supported.</p>
 *
 * @see CameraCaptureSession#supportsOfflineProcessing
 */
public final class CameraOfflineSession {
    private CameraOfflineSession() {
    }

    public static final class CameraOfflineSessionCallback {
        /**
         * This status code indicates unexpected and fatal internal camera error.
         *
         * <p>Pending offline requests will be discarded and the respective registered
         * capture callbacks may not get triggered.</p>
         *
         * @see #onError
         */
        public static final int STATUS_INTERNAL_ERROR = 0;

        private CameraOfflineSessionCallback() {
        }
    }
}
