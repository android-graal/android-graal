package org.androidgraal.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.ref.Reference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class HandlesTest {

    private static final Handles.Release NONE = batch -> {
        throw new AssertionError("released " + batch);
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
        Reference.reachabilityFence(first);
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
    void theOwnerForceDropsAnExportOnceWhateverItsSends() {
        Handles art = new Handles(-1, NONE);
        Object display = new Object();
        long id = art.send(display);
        art.send(display);

        art.forceDropExport(id);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> art.object(id));
        assertEquals("no object exported as " + id, e.getMessage());
        assertThrows(IllegalStateException.class, () -> art.receive(id, null));
        IllegalStateException twice = assertThrows(IllegalStateException.class, () -> art.forceDropExport(id));
        assertEquals("no object exported as " + id, twice.getMessage());
        assertTrue(art.send(display) < id);
    }

    @Test
    void onlyALiveProxyThisSideReceivedIsReleasedAndOnlyItsReferencesCross() {
        List<List<ReleasedReferences>> crossed = new ArrayList<>();
        Handles image = new Handles(1, crossed::add);
        Stub known = image.receive(-9, Stub::new);
        assertSame(known, image.receive(-9, Stub::new));

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> image.release(new Object()));
        assertEquals("java.lang.Object is not a ReleasableProxy", e.getMessage());
        assertThrows(NullPointerException.class, () -> image.release(null));
        assertThrows(IllegalArgumentException.class, () -> image.release(new Stub(-10)));
        assertSame(known, image.receive(-9, Stub::new));
        assertEquals(List.of(), crossed);

        image.release(known);

        assertEquals(1, crossed.size());
        assertEquals(List.of(new ReleasedReferences(-9, 3)), crossed.get(0));
        assertEquals(0, image.proxies());
        IllegalStateException released = assertThrows(IllegalStateException.class, () -> image.send(known));
        assertEquals(Stub.class.getName() + " proxy -9 was released", released.getMessage());
        assertThrows(IllegalStateException.class, () -> image.release(known));
        assertEquals(1, crossed.size());
        assertNotSame(known, image.receive(-9, Stub::new));
    }

    @Test
    void aProxyThatIsNotReleasableCrossesAsItsIdAndIsNeverReleasedExplicitly() {
        List<List<ReleasedReferences>> crossed = new ArrayList<>();
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
        assertEquals(Handle.class.getName() + " is not a ReleasableProxy", e.getMessage());
        assertEquals(1, image.proxies());
        assertSame(handle, image.receive(-3, again));
        assertEquals(List.of(), crossed);
    }

    @Test
    void aCollectedProxyReleasesItsReferencesAndEndsTheExport() throws Exception {
        Pair pair = new Pair();
        Object display = new Object();
        long id = pair.art.send(display);
        Stub proxy = pair.image.receive(id, Stub::new);
        assertSame(proxy, pair.image.receive(pair.art.send(display), Stub::new));

        collect(pair.image, id);
        pair.image.releaseNext();

        assertEquals(1, pair.imageReleases.size());
        assertEquals(List.of(new ReleasedReferences(id, 2)), pair.imageReleases.get(0));
        assertEquals(0, pair.art.ids());
        assertEquals(0, pair.image.proxies());
        Reference.reachabilityFence(proxy);
    }

    @Test
    void aResendRacingAPendingReleaseKeepsTheExportForTheFreshProxy() throws Exception {
        Pair pair = new Pair();
        Object display = new Object();
        long id = pair.art.send(display);
        Stub dead = pair.image.receive(id, Stub::new);
        collect(pair.image, id);

        Stub fresh = pair.image.receive(pair.art.send(display), Stub::new);
        pair.image.releaseNext();

        assertNotSame(dead, fresh);
        assertEquals(List.of(new ReleasedReferences(id, 1)), pair.imageReleases.get(0));
        assertSame(display, pair.art.object(id));
        assertEquals(1, pair.art.ids());
        assertEquals(1, pair.image.proxies());
        assertSame(fresh, pair.image.receive(pair.art.send(display), Stub::new));

        collect(pair.image, id);
        pair.image.releaseNext();

        assertEquals(List.of(new ReleasedReferences(id, 2)), pair.imageReleases.get(1));
        assertEquals(0, pair.art.ids());
        Reference.reachabilityFence(fresh);
    }

    @Test
    void aFreshEntryUnderADeadEntrysIdSurvivesTheDrain() throws Exception {
        Pair pair = new Pair();
        Object display = new Object();
        long id = pair.art.send(display);
        Stub dead = pair.image.receive(id, Stub::new);
        collect(pair.image, id);
        Stub fresh = pair.image.receive(pair.art.send(display), Stub::new);
        Reference<?> freshEntry = entryOf(pair.image, id);

        pair.image.releaseNext();

        assertSame(freshEntry, entryOf(pair.image, id));
        assertSame(fresh, freshEntry.get());
        assertSame(fresh, pair.image.receive(pair.art.send(display), foreign -> {
            throw new AssertionError("built a second proxy of " + foreign);
        }));
        Reference.reachabilityFence(dead);
    }

    @Test
    void anExplicitReleaseClearsItsEntrySoItsCollectionReleasesNothing() throws Exception {
        Pair pair = new Pair();
        long released = pair.art.send(new Object());
        long collected = pair.art.send(new Object());
        Stub proxy = pair.image.receive(released, Stub::new);
        Stub dead = pair.image.receive(collected, Stub::new);
        Reference<?> entry = entryOf(pair.image, released);

        pair.image.release(proxy);
        collect(pair.image, collected);
        pair.image.releaseNext();

        assertNull(entry.get());
        assertNull(entryOf(pair.image, released));
        assertEquals(2, pair.imageReleases.size());
        assertEquals(List.of(new ReleasedReferences(released, 1)), pair.imageReleases.get(0));
        assertEquals(List.of(new ReleasedReferences(collected, 1)), pair.imageReleases.get(1));
        assertEquals(0, pair.art.ids());
        Reference.reachabilityFence(dead);
    }

    @Test
    void everyProxyCollectedByTheDrainCrossesInOneBatch() throws Exception {
        Pair pair = new Pair();
        Set<ReleasedReferences> expected = new HashSet<>();
        List<Stub> received = new ArrayList<>();
        long[] ids = new long[3];
        for (int i = 0; i < ids.length; i++) {
            Object display = new Object();
            ids[i] = pair.art.send(display);
            for (int reference = 0; reference <= i; reference++) {
                received.add(pair.image.receive(reference == 0 ? ids[i] : pair.art.send(display), Stub::new));
            }
            expected.add(new ReleasedReferences(ids[i], i + 1));
        }
        for (long id : ids) {
            collect(pair.image, id);
        }

        pair.image.releaseNext();

        assertEquals(1, pair.imageReleases.size());
        List<ReleasedReferences> batch = pair.imageReleases.get(0);
        assertEquals(3, batch.size());
        assertEquals(expected, new HashSet<>(batch));
        assertEquals(0, pair.art.ids());
        assertEquals(0, pair.image.proxies());
        Reference.reachabilityFence(received);
    }

    @Test
    void bothSidesCountsAgreeAfterAMixedSequence() throws Exception {
        Pair pair = new Pair();
        Object view = new Object();
        Object paint = new Object();
        Object window = new Object();
        Object listener = new Object();

        long viewId = pair.art.send(view);
        Stub viewProxy = pair.image.receive(viewId, Stub::new);
        pair.image.receive(pair.art.send(view), Stub::new);
        pair.image.receive(pair.art.send(view), Stub::new);
        pair.image.release(viewProxy);

        long paintId = pair.art.send(paint);
        Stub paintProxy = pair.image.receive(paintId, Stub::new);
        pair.image.receive(pair.art.send(paint), Stub::new);
        collect(pair.image, paintId);

        long windowId = pair.art.send(window);
        Stub windowProxy = pair.image.receive(windowId, Stub::new);
        collect(pair.image, windowId);
        Stub windowAgain = pair.image.receive(pair.art.send(window), Stub::new);
        pair.image.releaseNext();
        assertEquals(1, pair.art.ids());
        pair.image.release(windowAgain);

        long listenerId = pair.image.send(listener);
        Stub listenerProxy = pair.art.receive(listenerId, Stub::new);
        pair.art.receive(pair.image.send(listener), Stub::new);
        assertSame(listener, pair.image.receive(pair.art.send(listenerProxy), null));
        collect(pair.art, listenerId);
        pair.art.releaseNext();

        assertEquals(0, pair.art.ids());
        assertEquals(0, pair.image.ids());
        assertEquals(0, pair.art.proxies());
        assertEquals(0, pair.image.proxies());
        assertEquals(List.of(new ReleasedReferences(listenerId, 2)), pair.artReleases.get(0));
        Reference.reachabilityFence(paintProxy);
        Reference.reachabilityFence(windowProxy);
        Reference.reachabilityFence(listenerProxy);
    }

    /** Clears and enqueues the entry under {@code id} as a collection of its proxy does. */
    private static void collect(Handles handles, long id) throws ReflectiveOperationException {
        assertTrue(entryOf(handles, id).enqueue());
    }

    private static Reference<?> entryOf(Handles handles, long id) throws ReflectiveOperationException {
        Field imports = Handles.class.getDeclaredField("imports");
        imports.setAccessible(true);
        Field byId = Imports.class.getDeclaredField("byId");
        byId.setAccessible(true);
        return (Reference<?>) ((LongIndex<?>) byId.get(imports.get(handles))).get(id);
    }

    /** Both sides' tables, each side's release crossing to the other at once. */
    private static final class Pair {

        private final List<List<ReleasedReferences>> imageReleases = new ArrayList<>();
        private final List<List<ReleasedReferences>> artReleases = new ArrayList<>();
        private final Handles image;
        private final Handles art;

        private Pair() {
            Handles[] sides = new Handles[2];
            image = new Handles(1, batch -> {
                imageReleases.add(batch);
                sides[1].releaseExports(batch);
            });
            art = new Handles(-1, batch -> {
                artReleases.add(batch);
                sides[0].releaseExports(batch);
            });
            sides[0] = image;
            sides[1] = art;
        }
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
