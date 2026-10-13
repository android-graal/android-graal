package android.media.audiofx;

/**
 * The Visualizer class enables application to retrieve part of the currently playing audio for
 * visualization purpose. It is not an audio recording interface and only returns partial and low
 * quality audio content. However, to protect privacy of certain audio data (e.g voice mail) the use
 * of the visualizer requires the permission android.permission.RECORD_AUDIO.
 * <p>The audio session ID passed to the constructor indicates which audio content should be
 * visualized:<br>
 * <ul>
 *   <li>If the session is 0, the audio output mix is visualized</li>
 *   <li>If the session is not 0, the audio from a particular {@link android.media.MediaPlayer} or
 *   {@link android.media.AudioTrack}
 *   using this audio session is visualized </li>
 * </ul>
 * <p>Two types of representation of audio content can be captured: <br>
 * <ul>
 *   <li>Waveform data: consecutive 8-bit (unsigned) mono samples by using the
 *   {@link #getWaveForm(byte[])} method</li>
 *   <li>Frequency data: 8-bit magnitude FFT by using the {@link #getFft(byte[])} method</li>
 * </ul>
 * <p>The length of the capture can be retrieved or specified by calling respectively
 * {@link #getCaptureSize()} and {@link #setCaptureSize(int)} methods. The capture size must be a
 * power of 2 in the range returned by {@link #getCaptureSizeRange()}.
 * <p>In addition to the polling capture mode described above with {@link #getWaveForm(byte[])} and
 *  {@link #getFft(byte[])} methods, a callback mode is also available by installing a listener by
 *  use of the {@link #setDataCaptureListener(OnDataCaptureListener, int, boolean, boolean)} method.
 *  The rate at which the listener capture method is called as well as the type of data returned is
 *  specified.
 * <p>Before capturing data, the Visualizer must be enabled by calling the
 * {@link #setEnabled(boolean)} method.
 * When data capture is not needed any more, the Visualizer should be disabled.
 * <p>It is good practice to call the {@link #release()} method when the Visualizer is not used
 * anymore to free up native resources associated to the Visualizer instance.
 * <p>Creating a Visualizer on the output mix (audio session 0) requires permission
 * {@link android.Manifest.permission#MODIFY_AUDIO_SETTINGS}
 * <p>The Visualizer class can also be used to perform measurements on the audio being played back.
 * The measurements to perform are defined by setting a mask of the requested measurement modes with
 * {@link #setMeasurementMode(int)}. Supported values are {@link #MEASUREMENT_MODE_NONE} to cancel
 * any measurement, and {@link #MEASUREMENT_MODE_PEAK_RMS} for peak and RMS monitoring.
 * Measurements can be retrieved through {@link #getMeasurementPeakRms(MeasurementPeakRms)}.
 */
public final class Visualizer {
    /**
     * Internal operation status. Not returned by any method.
     */
    public static final int ALREADY_EXISTS = -2;

    /**
     * Unspecified error.
     */
    public static final int ERROR = -1;

    /**
     * Operation failed due to bad parameter value.
     */
    public static final int ERROR_BAD_VALUE = -4;

    /**
     * Operation failed due to dead remote object.
     */
    public static final int ERROR_DEAD_OBJECT = -7;

    /**
     * Operation failed because it was requested in wrong state.
     */
    public static final int ERROR_INVALID_OPERATION = -5;

    /**
     * Operation failed due to bad object initialization.
     */
    public static final int ERROR_NO_INIT = -3;

    /**
     * Operation failed due to lack of memory.
     */
    public static final int ERROR_NO_MEMORY = -6;

    /**
     * Defines a measurement mode in which no measurements are performed.
     */
    public static final int MEASUREMENT_MODE_NONE = 0;

    /**
     * Defines a measurement mode which computes the peak and RMS value in mB below the
     * "full scale", where 0mB is normally the maximum sample value (but see the note
     * below). Minimum value depends on the resolution of audio samples used by the audio
     * framework. The value of -9600mB is the minimum value for 16-bit audio systems and
     * -14400mB or below for "high resolution" systems. Values for peak and RMS can be
     * retrieved with {@link #getMeasurementPeakRms(MeasurementPeakRms)}.
     *
     * <p class=note><strong>Note:</strong> when Visualizer effect is attached to the
     * global session (with session ID 0), it is possible to observe RMS peaks higher than
     * 0 dBFS, for example in the case when there are multiple audio sources playing
     * simultaneously. In this case {@link #getMeasurementPeakRms(MeasurementPeakRms)}
     * method can return a positive value.
     */
    public static final int MEASUREMENT_MODE_PEAK_RMS = 1;

    /**
     * Defines a capture mode where the playback volume will affect (scale) the range of the
     * captured data. A low playback volume will lead to low sample and fft values, and vice-versa.
     */
    public static final int SCALING_MODE_AS_PLAYED = 1;

    /**
     * Defines a capture mode where amplification is applied based on the content of the captured
     * data. This is the default Visualizer mode, and is suitable for music visualization.
     */
    public static final int SCALING_MODE_NORMALIZED = 0;

    /**
     * State of a Visualizer object that is active.
     */
    public static final int STATE_ENABLED = 2;

    /**
     * State of a Visualizer object that is ready to be used.
     */
    public static final int STATE_INITIALIZED = 1;

    /**
     * State of a Visualizer object that was not successfully initialized upon creation
     */
    public static final int STATE_UNINITIALIZED = 0;

    /**
     * Successful operation.
     */
    public static final int SUCCESS = 0;

    private Visualizer() {
    }
}
