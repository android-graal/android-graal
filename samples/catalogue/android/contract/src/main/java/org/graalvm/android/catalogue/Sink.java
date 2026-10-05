package org.graalvm.android.catalogue;

import org.androidgraal.hosted.annotate.Art;
import org.androidgraal.hosted.annotate.In;
import org.androidgraal.hosted.annotate.Out;
import org.androidgraal.hosted.annotate.StaticBuffer;

@Art
public interface Sink {
    void line(String text);

    void level(Mode mode);

    void samples(@In float[] values, int count);

    void collect(@Out int[] values);

    int accept(Peer peer);

    void samples16(@In @StaticBuffer(capacity = 16) float[] values);

    void collect16Tls(@Out @StaticBuffer(capacity = 16) int[] values);

    int[] squares(int count);

    int width16(@StaticBuffer(capacity = 16) String text);

    @StaticBuffer(capacity = 4)
    float[] corners4();

    void place(Placement placement);

    void fail(String message) throws ArtException;

    long primitives(boolean b, byte y, char c, short s, int i, long l, float f, double d);
}
