package org.graalvm.android.bridges.core;

import org.androidgraal.hosted.annotate.In;
import org.androidgraal.hosted.annotate.Out;
import org.androidgraal.hosted.annotate.StaticBuffer;
import org.androidgraal.hosted.annotate.Svm;

@Svm
public interface Frame {
    int step(int value);

    Mode mode(Mode mode);

    Frame self();

    int probe(Probe probe);

    float sum(@In float[] values);

    void fill(@Out int[] values);

    float sum16(@In @StaticBuffer(capacity = 16) float[] values);

    void fill16(@Out @StaticBuffer(capacity = 16) int[] values);

    float sum16Tls(@In @StaticBuffer(capacity = 16) float[] values);

    int total(@In int[] values, int count);

    void squares(@Out int[] values, int count);

    void press(int x, int y, int pointer);

    int events(@Out int[] into);

    float render(Probe probe);

    String text(String s);

    int[] range(int count);

    String join(@In int[] values, String separator);

    int count16(@StaticBuffer(capacity = 16) String s);

    @StaticBuffer(capacity = 16)
    String text16(int value);

    @StaticBuffer(capacity = 4)
    float[] corners4();
}
