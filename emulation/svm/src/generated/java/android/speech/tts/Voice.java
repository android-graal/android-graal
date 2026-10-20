package android.speech.tts;

import android.os.Parcelable;

/**
 * Characteristics and features of a Text-To-Speech Voice. Each TTS Engine can expose
 * multiple voices for each locale, with different set of features.
 */
public final class Voice implements Parcelable {
    /**
     * Network based expected synthesizer latency (~200ms)
     */
    public static final int LATENCY_HIGH = 400;

    /**
     * Low expected synthesizer latency (~20ms)
     */
    public static final int LATENCY_LOW = 200;

    /**
     * Normal expected synthesizer latency (~50ms)
     */
    public static final int LATENCY_NORMAL = 300;

    /**
     * Very slow network based expected synthesizer latency (> 200ms)
     */
    public static final int LATENCY_VERY_HIGH = 500;

    /**
     * Very low expected synthesizer latency (< 20ms)
     */
    public static final int LATENCY_VERY_LOW = 100;

    /**
     * High, human-like quality of speech synthesis
     */
    public static final int QUALITY_HIGH = 400;

    /**
     * Low, not human-like quality of speech synthesis
     */
    public static final int QUALITY_LOW = 200;

    /**
     * Normal quality of speech synthesis
     */
    public static final int QUALITY_NORMAL = 300;

    /**
     * Very high, almost human-indistinguishable quality of speech synthesis
     */
    public static final int QUALITY_VERY_HIGH = 500;

    /**
     * Very low, but still intelligible quality of speech synthesis
     */
    public static final int QUALITY_VERY_LOW = 100;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private Voice() {
    }
}
