package org.androidgraal.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.ref.Reference;
import java.lang.reflect.Field;
import java.util.List;
import org.junit.jupiter.api.Test;

class IdentityTablesTest {

    @Test
    void anObjectIsSentUnderOneIdWhileItIsExported() {
        Exports exports = new Exports(1);
        Object a = new Object();
        Object b = new Object();

        long id = exports.send(a);

        assertEquals(1, id);
        assertEquals(id, exports.send(a));
        assertEquals(2, exports.send(b));
        assertSame(a, exports.object(id));
        assertEquals(2, exports.size());
    }

    @Test
    void anIdIsNeverGivenAgainAfterItsExportEnded() {
        Exports exports = new Exports(-1);
        Object a = new Object();
        long first = exports.send(a);

        assertSame(a, exports.forceRemove(first));

        assertNull(exports.object(first));
        assertNull(exports.forceRemove(first));
        long second = exports.send(a);
        assertNotEquals(first, second);
        assertEquals(-2, second);
        assertEquals(1, exports.size());
    }

    @Test
    void anExportEndsWhenEverySendIsReleased() {
        Exports exports = new Exports(1);
        Object a = new Object();
        long id = exports.send(a);
        exports.send(a);
        exports.send(a);

        exports.release(new ReleasedReferences(id, 2));
        assertSame(a, exports.object(id));
        exports.release(new ReleasedReferences(id, 1));

        assertNull(exports.object(id));
        assertEquals(0, exports.size());
        assertNotEquals(id, exports.send(a));
    }

    @Test
    void aReleaseOfMoreThanWasSentOrOfNoExportFailsAndChangesNothing() {
        Exports exports = new Exports(1);
        Object a = new Object();
        long id = exports.send(a);

        IllegalStateException more = assertThrows(IllegalStateException.class,
                () -> exports.release(new ReleasedReferences(id, 2)));
        IllegalStateException none = assertThrows(IllegalStateException.class,
                () -> exports.release(new ReleasedReferences(id, 0)));
        IllegalStateException unknown = assertThrows(IllegalStateException.class,
                () -> exports.release(new ReleasedReferences(7, 1)));

        assertEquals("released 2 references of 1, sent 1", more.getMessage());
        assertEquals("released 0 references of 1, sent 1", none.getMessage());
        assertEquals("released 1 references of 7, no object exported as 7", unknown.getMessage());
        assertSame(a, exports.object(id));
    }

    @Test
    void anIdHasOneProxyWhileItLivesAndCountsItsReferences() throws Exception {
        Imports imports = new Imports();
        Stub first = new Stub(7);

        assertSame(first, imports.getOrAdd(7, id -> first));
        assertSame(first, imports.getOrAdd(7, id -> {
            throw new AssertionError("created a second proxy of " + id);
        }));
        assertEquals(1, imports.size());
        assertTrue(entryOf(imports, 7).enqueue());

        assertEquals(List.of(new ReleasedReferences(7, 2)), imports.nextCollected());
        assertEquals(0, imports.size());
        assertNotSame(first, imports.getOrAdd(7, Stub::new));
    }

    @Test
    void aReleasedProxyLeavesTheTableAndFailsEveryLaterUse() throws Exception {
        Imports imports = new Imports();
        ReleasableProxy proxy = (ReleasableProxy) imports.getOrAdd(7, Stub::new);
        imports.getOrAdd(7, Stub::new);
        Reference<?> entry = entryOf(imports, 7);

        assertEquals(new ReleasedReferences(7, 2), imports.release(proxy));

        assertEquals(0, imports.size());
        assertNull(entry.get());
        IllegalStateException e = assertThrows(IllegalStateException.class, proxy::foreignId);
        assertEquals(Stub.class.getName() + " proxy 7 was released", e.getMessage());
        assertThrows(IllegalStateException.class, () -> imports.release(proxy));
        Stub fresh = new Stub(7);
        assertSame(fresh, imports.getOrAdd(7, id -> fresh));
    }

    @Test
    void onlyTheProxyInTheTableIsReleased() {
        Imports imports = new Imports();
        ReleasableProxy known = (ReleasableProxy) imports.getOrAdd(7, Stub::new);

        assertThrows(IllegalArgumentException.class, () -> imports.release(new Stub(8)));
        assertThrows(IllegalArgumentException.class, () -> imports.release(new Stub(7)));
        assertNotSame(known, imports.getOrAdd(7, Stub::new));
        assertEquals(1, imports.size());
        Reference.reachabilityFence(known);
    }

    private static Reference<?> entryOf(Imports imports, long id) throws ReflectiveOperationException {
        Field byId = Imports.class.getDeclaredField("byId");
        byId.setAccessible(true);
        return (Reference<?>) ((LongIndex<?>) byId.get(imports)).get(id);
    }

    private static final class Stub extends ReleasableProxy {

        Stub(long id) {
            super(id);
        }
    }
}
