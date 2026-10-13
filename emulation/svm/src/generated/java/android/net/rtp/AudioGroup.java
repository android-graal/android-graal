package android.net.rtp;

/**
 * An AudioGroup is an audio hub for the speaker, the microphone, and
 * {@link AudioStream}s. Each of these components can be logically turned on
 * or off by calling {@link #setMode(int)} or {@link RtpStream#setMode(int)}.
 * The AudioGroup will go through these components and process them one by one
 * within its execution loop. The loop consists of four steps. First, for each
 * AudioStream not in {@link RtpStream#MODE_SEND_ONLY}, decodes its incoming
 * packets and stores in its buffer. Then, if the microphone is enabled,
 * processes the recorded audio and stores in its buffer. Third, if the speaker
 * is enabled, mixes all AudioStream buffers and plays back. Finally, for each
 * AudioStream not in {@link RtpStream#MODE_RECEIVE_ONLY}, mixes all other
 * buffers and sends back the encoded packets. An AudioGroup does nothing if
 * there is no AudioStream in it.
 *
 * <p>Few things must be noticed before using these classes. The performance is
 * highly related to the system load and the network bandwidth. Usually a
 * simpler {@link AudioCodec} costs fewer CPU cycles but requires more network
 * bandwidth, and vise versa. Using two AudioStreams at the same time doubles
 * not only the load but also the bandwidth. The condition varies from one
 * device to another, and developers should choose the right combination in
 * order to get the best result.</p>
 *
 * <p>It is sometimes useful to keep multiple AudioGroups at the same time. For
 * example, a Voice over IP (VoIP) application might want to put a conference
 * call on hold in order to make a new call but still allow people in the
 * conference call talking to each other. This can be done easily using two
 * AudioGroups, but there are some limitations. Since the speaker and the
 * microphone are globally shared resources, only one AudioGroup at a time is
 * allowed to run in a mode other than {@link #MODE_ON_HOLD}. The others will
 * be unable to acquire these resources and fail silently.</p>
 *
 * <p class="note">Using this class requires
 * {@link android.Manifest.permission#RECORD_AUDIO} permission. Developers
 * should set the audio mode to {@link AudioManager#MODE_IN_COMMUNICATION}
 * using {@link AudioManager#setMode(int)} and change it back when none of
 * the AudioGroups is in use.</p>
 *
 * @see AudioStream
 *
 * @deprecated {@link android.net.sip.SipManager} and associated classes are no longer supported and
 * should not be used as the basis of future VOIP apps.
 */
@Deprecated
public final class AudioGroup {
    /**
     * This mode is similar to {@link #MODE_NORMAL} except the echo suppression
     * is enabled. It should be only used when the speaker phone is on.
     */
    @Deprecated
    public static final int MODE_ECHO_SUPPRESSION = 3;

    /**
     * This mode is similar to {@link #MODE_NORMAL} except the microphone is
     * disabled.
     */
    @Deprecated
    public static final int MODE_MUTED = 1;

    /**
     * This mode indicates that the speaker, the microphone, and all
     * {@link AudioStream}s in the group are enabled. First, the packets
     * received from the streams are decoded and mixed with the audio recorded
     * from the microphone. Then, the results are played back to the speaker,
     * encoded and sent back to each stream.
     */
    @Deprecated
    public static final int MODE_NORMAL = 2;

    /**
     * This mode is similar to {@link #MODE_NORMAL} except the speaker and
     * the microphone are both disabled.
     */
    @Deprecated
    public static final int MODE_ON_HOLD = 0;

    private AudioGroup() {
    }
}
