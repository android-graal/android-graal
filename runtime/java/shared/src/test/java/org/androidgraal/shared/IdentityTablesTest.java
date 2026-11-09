package org.androidgraal.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        assertSame(a, exports.remove(first));

        assertNull(exports.object(first));
        assertNull(exports.remove(first));
        long second = exports.send(a);
        assertNotEquals(first, second);
        assertEquals(-2, second);
        assertEquals(1, exports.size());
    }

    @Test
    void anIdHasOneProxyTheFirstToArrive() {
        Imports imports = new Imports();
        Stub first = new Stub(7);

        assertSame(first, imports.getOrAdd(7, id -> first));
        assertSame(first, imports.getOrAdd(7, id -> {
            throw new AssertionError("created a second proxy of " + id);
        }));
        assertEquals(1, imports.size());
    }

    @Test
    void aReleasedProxyLeavesTheTableAndFailsEveryLaterUse() {
        Imports imports = new Imports();
        ReleasableProxy proxy = (ReleasableProxy) imports.getOrAdd(7, Stub::new);

        assertEquals(7, imports.release(proxy));

        assertEquals(0, imports.size());
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
        assertSame(known, imports.getOrAdd(7, Stub::new));
        assertEquals(1, imports.size());
    }

    private static final class Stub extends ReleasableProxy {

        Stub(long id) {
            super(id);
        }
    }
}
