package org.androidgraal.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class HandlesTest {

    private static final Handles.Release NONE = id -> {
        throw new AssertionError("released " + id);
    };

    private static final Handles.Factory<Proxy> PROXIES = Proxy::new;

    @Test
    void anOwnObjectCrossesUnderOneIdOfTheSidesSignAndComesBackAsItself() {
        Handles image = new Handles(1, NONE);
        Handles art = new Handles(-1, NONE);
        Object listener = new Object();
        Object display = new Object();

        long imageId = image.send(listener);
        long artId = art.send(display);

        assertTrue(imageId > 0 && artId < 0);
        assertEquals(imageId, image.send(listener));
        assertSame(listener, image.object(imageId));
        assertSame(listener, image.receive(imageId, null));
        assertSame(display, art.receive(artId, Proxy::new));
        assertEquals(1, image.ids());
    }

    @Test
    void aForeignIdBecomesOneProxyThatCrossesBackAsTheId() {
        Handles art = new Handles(-1, NONE);

        Proxy first = art.receive(5, Proxy::new);

        assertSame(first, art.receive(5, Proxy::new));
        assertEquals(5, art.send(first));
        assertEquals(1, art.proxies());
        assertEquals(0, art.ids());
    }

    @Test
    void withoutAFactoryAForeignIdIsRefused() {
        IllegalStateException image = assertThrows(IllegalStateException.class,
                () -> new Handles(1, NONE).receive(-2, null));
        IllegalStateException art = assertThrows(IllegalStateException.class,
                () -> new Handles(-1, NONE).receive(6, null));

        assertEquals("an object of the other side crossed as a type registered on this side only", image.getMessage());
        assertEquals("an object of the other side crossed as a type registered on this side only", art.getMessage());
    }

    @Test
    void anOwnObjectHandedBackComesOutAsTheInterfaceWithoutAProxy() {
        Handles image = new Handles(1, NONE);
        Shape own = new Square();
        long id = image.send(own);

        Shape back = image.receive(id, PROXIES);

        assertSame(own, back);
        assertSame(own, image.receive(id, foreign -> {
            throw new AssertionError("created a proxy of " + foreign);
        }));
        assertEquals(0, image.proxies());
    }

    @Test
    void nullCrossesAsZero() {
        Handles image = new Handles(1, NONE);

        assertEquals(0, image.send(null));
        assertNull(image.receive(0, Proxy::new));
    }

    @Test
    void anIdNotExportedFailsNamingIt() {
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> new Handles(1, NONE).object(Long.MAX_VALUE));

        assertEquals("no object exported as " + Long.MAX_VALUE + ": its proxy was released", e.getMessage());
    }

    @Test
    void theOtherSideDropsAnExportOnce() {
        Handles art = new Handles(-1, NONE);
        Object display = new Object();
        long id = art.send(display);

        art.drop(id);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> art.object(id));
        assertEquals("no object exported as " + id + ": its proxy was released", e.getMessage());
        assertThrows(IllegalStateException.class, () -> art.receive(id, null));
        IllegalStateException twice = assertThrows(IllegalStateException.class, () -> art.drop(id));
        assertEquals("no object exported as " + id, twice.getMessage());
        assertTrue(art.send(display) < id);
    }

    @Test
    void onlyALiveProxyThisSideReceivedIsReleasedAndOnlyItsReleaseCrosses() {
        List<Long> crossed = new ArrayList<>();
        Handles image = new Handles(1, crossed::add);
        Proxy known = image.receive(-9, Proxy::new);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> image.release(new Object()));
        assertEquals("java.lang.Object is not a proxy", e.getMessage());
        assertThrows(NullPointerException.class, () -> image.release(null));
        assertThrows(IllegalArgumentException.class, () -> image.release(new Proxy(-10)));
        assertSame(known, image.receive(-9, Proxy::new));
        assertEquals(List.of(), crossed);

        image.release(known);

        assertEquals(List.of(-9L), crossed);
        assertEquals(0, image.proxies());
        IllegalStateException released = assertThrows(IllegalStateException.class, () -> image.send(known));
        assertEquals(Proxy.class.getName() + " proxy -9 was released", released.getMessage());
        assertThrows(IllegalStateException.class, () -> image.release(known));
        assertEquals(List.of(-9L), crossed);
    }

    private interface Shape {}

    private static final class Square implements Shape {}

    private static final class Proxy extends Foreign implements Shape {

        Proxy(long id) {
            super(id);
        }
    }
}
