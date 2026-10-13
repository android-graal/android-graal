package android.media;

/**
 * The RemoteController class is used to control media playback, display and update media metadata
 * and playback status, published by applications using the {@link RemoteControlClient} class.
 * <p>
 * A RemoteController shall be registered through
 * {@link AudioManager#registerRemoteController(RemoteController)} in order for the system to send
 * media event updates to the {@link OnClientUpdateListener} listener set in the class constructor.
 * Implement the methods of the interface to receive the information published by the active
 * {@link RemoteControlClient} instances.
 * <br>By default an {@link OnClientUpdateListener} implementation will not receive bitmaps for
 * album art. Use {@link #setArtworkConfiguration(int, int)} to receive images as well.
 * <p>
 * Registration requires the {@link OnClientUpdateListener} listener to be one of the enabled
 * notification listeners (see {@link android.service.notification.NotificationListenerService}).
 *
 * @deprecated Use {@link MediaController} instead.
 */
@Deprecated
public final class RemoteController {
    /**
     * The playback position synchronization mode where the RemoteControlClient instances which
     * expose their playback position to the framework, will be regularly polled to check
     * whether any drift has been noticed between their estimated position and the one they report.
     * Note that this mode should only ever be used when needing to display very accurate playback
     * position, as regularly polling a RemoteControlClient for its position may have an impact
     * on battery life (if applicable) when this query will trigger network transactions in the
     * case of remote playback.
     */
    @Deprecated
    public static final int POSITION_SYNCHRONIZATION_CHECK = 1;

    /**
     * Default playback position synchronization mode where the RemoteControlClient is not
     * asked regularly for its playback position to see if it has drifted from the estimated
     * position.
     */
    @Deprecated
    public static final int POSITION_SYNCHRONIZATION_NONE = 0;

    private RemoteController() {
    }
}
