package android.telecom;

import android.os.Parcelable;

/**
 *  Encapsulates the telecom audio state, including the current audio routing, supported audio
 *  routing and mute.
 */
public final class CallAudioState implements Parcelable {
    /**
     * Direct the audio stream through Bluetooth.
     */
    public static final int ROUTE_BLUETOOTH = 2;

    /**
     * Direct the audio stream through the device's earpiece.
     */
    public static final int ROUTE_EARPIECE = 1;

    /**
     * Direct the audio stream through the device's speakerphone.
     */
    public static final int ROUTE_SPEAKER = 8;

    /**
     * Direct the audio stream through another device.
     */
    public static final int ROUTE_STREAMING = 16;

    /**
     * Direct the audio stream through a wired headset.
     */
    public static final int ROUTE_WIRED_HEADSET = 4;

    /**
     * Direct the audio stream through the device's earpiece or wired headset if one is
     * connected.
     */
    public static final int ROUTE_WIRED_OR_EARPIECE = 5;

    private CallAudioState() {
    }
}
