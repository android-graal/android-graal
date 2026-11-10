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

    private static final Handles.Factory<Stub> PROXIES = Stub::new;

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
        assertSame(display, art.receive(artId, Stub::new));
        assertEquals(1, image.ids());
    }

    @Test
    void aForeignIdBecomesOneProxyThatCrossesBackAsTheId() {
        Handles art = new Handles(-1, NONE);

        Stub first = art.receive(5, Stub::new);

        assertSame(first, art.receive(5, Stub::new));
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
        assertNull(image.receive(0, Stub::new));
    }

    @Test
    void anIdNotExportedFailsNamingIt() {
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> new Handles(1, NONE).object(Long.MAX_VALUE));

        assertEquals("no object exported as " + Long.MAX_VALUE, e.getMessage());
    }

    @Test
    void theOtherSideDropsAnExportOnce() {
        Handles art = new Handles(-1, NONE);
        Object display = new Object();
        long id = art.send(display);

        art.drop(id);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> art.object(id));
        assertEquals("no object exported as " + id, e.getMessage());
        assertThrows(IllegalStateException.class, () -> art.receive(id, null));
        IllegalStateException twice = assertThrows(IllegalStateException.class, () -> art.drop(id));
        assertEquals("no object exported as " + id, twice.getMessage());
        assertTrue(art.send(display) < id);
    }

    @Test
    void onlyALiveProxyThisSideReceivedIsReleasedAndOnlyItsReleaseCrosses() {
        List<Long> crossed = new ArrayList<>();
        Handles image = new Handles(1, crossed::add);
        Stub known = image.receive(-9, Stub::new);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> image.release(new Object()));
        assertEquals("java.lang.Object is not a ReleasableProxy, the one kind of proxy that is released",
                e.getMessage());
        assertThrows(NullPointerException.class, () -> image.release(null));
        assertThrows(IllegalArgumentException.class, () -> image.release(new Stub(-10)));
        assertSame(known, image.receive(-9, Stub::new));
        assertEquals(List.of(), crossed);

        image.release(known);

        assertEquals(List.of(-9L), crossed);
        assertEquals(0, image.proxies());
        IllegalStateException released = assertThrows(IllegalStateException.class, () -> image.send(known));
        assertEquals(Stub.class.getName() + " proxy -9 was released", released.getMessage());
        assertThrows(IllegalStateException.class, () -> image.release(known));
        assertEquals(List.of(-9L), crossed);
    }

    @Test
    void aProxyThatIsNoForeignCrossesAsItsIdAndIsNeverReleased() {
        List<Long> crossed = new ArrayList<>();
        Handles image = new Handles(1, crossed::add);
        List<Handle> built = new ArrayList<>();
        Handles.Factory<Handle> again = id -> {
            throw new AssertionError("created a second proxy of " + id);
        };

        Handle handle = image.receive(-3, id -> {
            Handle created = new Handle(id);
            built.add(created);
            return created;
        });

        assertEquals(1, built.size());
        assertSame(built.get(0), handle);
        assertEquals(-3, image.send(handle));
        assertSame(handle, image.receive(-3, again));
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> image.release(handle));
        assertEquals(Handle.class.getName() + " is not a ReleasableProxy, the one kind of proxy that is released",
                e.getMessage());
        assertEquals(1, image.proxies());
        assertSame(handle, image.receive(-3, again));
        assertEquals(List.of(), crossed);
    }

    @Test
    void aTagGivesBackItsIdWithTheSignAndItsOrdinal() {
        long art = Handles.tagOf(-7, 0xFFFF);
        long image = Handles.tagOf(7, 1);

        assertEquals(-7, Handles.idOf(art));
        assertEquals(0xFFFF, Handles.ordinalOf(art));
        assertEquals(7, Handles.idOf(image));
        assertEquals(1, Handles.ordinalOf(image));
    }

    private interface Shape {}

    private static final class Square implements Shape {}

    private static final class Stub extends ReleasableProxy implements Shape {

        Stub(long id) {
            super(id);
        }
    }

    private record Handle(long foreignId) implements Proxy {
    }
}
