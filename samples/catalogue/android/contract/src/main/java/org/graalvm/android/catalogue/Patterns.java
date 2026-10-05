package org.graalvm.android.catalogue;

import org.androidgraal.hosted.annotate.In;
import org.androidgraal.hosted.annotate.Out;
import org.androidgraal.hosted.annotate.Root;
import org.androidgraal.hosted.annotate.StaticBuffer;
import org.androidgraal.hosted.annotate.Svm;

@Root
@Svm
public interface Patterns {
    boolean not(boolean v);

    byte negate(byte v);

    char upper(char v);

    short twice(short v);

    int square(int v);

    long cube(long v);

    float half(float v);

    double root(double v);

    Mode next(Mode mode);

    String echo(String text);

    String place(Placement placement);

    int sum(@In int[] values, int count);

    void fill(@Out float[] values);

    long first(@In long[] values);

    void bytes(@Out byte[] values);

    void fill16(@Out @StaticBuffer(capacity = 16) float[] values);

    int sum16Tls(@In @StaticBuffer(capacity = 16) int[] values, int count);

    int[] range(int count);

    int length16(@StaticBuffer(capacity = 16) String text);

    @StaticBuffer(capacity = 16)
    String label16(int value);

    default String greet(String name) {
        return "hello " + name;
    }

    static String tag(int value) {
        return "#" + value;
    }

    void show(Sink sink);

    Peer swap(Peer peer);

    Patterns self();

    void fail(String message) throws ImageException;

    void read(String name);
}
