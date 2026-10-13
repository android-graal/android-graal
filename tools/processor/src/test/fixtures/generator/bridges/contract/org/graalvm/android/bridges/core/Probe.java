package org.graalvm.android.bridges.core;

import org.androidgraal.hosted.annotate.Art;
import org.androidgraal.hosted.annotate.In;
import org.androidgraal.hosted.annotate.Out;
import org.androidgraal.hosted.annotate.StaticBuffer;

@Art
public interface Probe {
    int step(int value);

    Mode mode(Mode mode);

    Probe self();

    int frame(Frame frame);

    float sum(@In float[] values);

    void fill(@Out int[] values);

    float sum16(@In @StaticBuffer(capacity = 16) float[] values);

    void fill16(@Out @StaticBuffer(capacity = 16) int[] values);

    void fill16Tls(@Out @StaticBuffer(capacity = 16) int[] values);

    int total(@In int[] values, int count);

    void squares(@Out int[] values, int count);

    float x();

    float y();

    boolean pressed(int key);

    String text(String s);

    int[] range(int count);

    String join(@In int[] values, String separator);

    String[] texts(String[] a);

    int count16(@StaticBuffer(capacity = 16) String s);

    @StaticBuffer(capacity = 16)
    String text16(int value);

    @StaticBuffer(capacity = 4)
    float[] corners4();
}
