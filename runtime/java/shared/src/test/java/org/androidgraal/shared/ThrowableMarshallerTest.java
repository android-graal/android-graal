package org.androidgraal.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ThrowableMarshallerTest {

    static final class Unrelated extends RuntimeException {
        private static final long serialVersionUID = 1L;

        Unrelated(String message) {
            super(message);
        }
    }

    static final class Holder {

        static MarshalledException rethrown(byte[] wire) {
            return ThrowableMarshaller.read(wire, Holder.class, Slot.class);
        }

        static final class Slot {

            static MarshalledException check(byte[] wire) {
                return Holder.rethrown(wire);
            }
        }
    }

    @Test
    void aThrowableArrivesAsACarrierNamingItsClassWithItsMessageAndTrace() {
        IllegalArgumentException thrown = new IllegalArgumentException("boom");

        MarshalledException read = cross(thrown);

        assertEquals("java.lang.IllegalArgumentException", read.getForeignExceptionClassName());
        assertEquals("boom", read.getMessage());
        assertEquals("java.lang.IllegalArgumentException: boom", read.toString());
        assertNull(read.getCause());
        assertEquals(frames(thrown.getStackTrace()), foreign(read.getStackTrace(), thrown));
    }

    @Test
    void aCheckedThrowableArrivesAsACarrierToo() {
        MarshalledException read = cross(new IOException("io"));

        assertEquals("java.io.IOException", read.getForeignExceptionClassName());
        assertEquals("io", read.getMessage());
    }

    @Test
    void aNullMessageStaysNull() {
        MarshalledException read = cross(new IllegalStateException());

        assertNull(read.getMessage());
        assertEquals("java.lang.IllegalStateException", read.toString());
    }

    @Test
    void aCarrierCrossingAgainKeepsItsForeignClassNameAndTrace() {
        MarshalledException first = cross(new SecurityException("no"));

        MarshalledException again = cross(first);

        assertEquals("java.lang.SecurityException", again.getForeignExceptionClassName());
        assertEquals("no", again.getMessage());
        assertEquals(frames(first.getStackTrace()), foreign(again.getStackTrace(), first));
    }

    @Test
    void aChainArrivesLinkByLink() {
        NullPointerException inner = new NullPointerException("inner");
        Unrelated middle = new Unrelated("middle");
        middle.initCause(inner);
        IllegalArgumentException outer = new IllegalArgumentException("outer", middle);

        MarshalledException read = cross(outer);

        assertEquals("java.lang.IllegalArgumentException", read.getForeignExceptionClassName());
        assertEquals("outer", read.getMessage());
        MarshalledException second = (MarshalledException) read.getCause();
        assertEquals(Unrelated.class.getName(), second.getForeignExceptionClassName());
        assertEquals("middle", second.getMessage());
        MarshalledException third = (MarshalledException) second.getCause();
        assertEquals("java.lang.NullPointerException", third.getForeignExceptionClassName());
        assertEquals("inner", third.getMessage());
        assertNull(third.getCause());
    }

    @Test
    void theOutermostLinkCarriesTheReadersFramesAndAnInnerLinkItsOwnOnly() {
        IllegalStateException innermost = new IllegalStateException("innermost");
        Unrelated inner = new Unrelated("inner");
        inner.initCause(innermost);
        IllegalArgumentException outer = new IllegalArgumentException("outer", inner);

        MarshalledException read = cross(outer);

        assertEquals(frames(outer.getStackTrace()), foreign(read.getStackTrace(), outer));
        assertEquals(frames(inner.getStackTrace()), frames(read.getCause().getStackTrace()));
        assertEquals(frames(innermost.getStackTrace()), frames(read.getCause().getCause().getStackTrace()));
    }

    @Test
    void theReadersFramesStartAfterTheMarshallerAndTheSkippedClasses() {
        IllegalStateException thrown = new IllegalStateException("skipped");

        MarshalledException read = Holder.Slot.check(ThrowableMarshaller.write(thrown));

        StackTraceElement local = read.getStackTrace()[thrown.getStackTrace().length];
        assertEquals(ThrowableMarshallerTest.class.getName(), local.getClassName());
        assertEquals("theReadersFramesStartAfterTheMarshallerAndTheSkippedClasses", local.getMethodName());
    }

    @Test
    void aCycleEndsAtTheFirstRepeatedLink() {
        Unrelated a = new Unrelated("a");
        Unrelated b = new Unrelated("b");
        a.initCause(b);
        b.initCause(a);

        MarshalledException read = cross(a);

        assertEquals("a", read.getMessage());
        assertEquals("b", read.getCause().getMessage());
        assertNull(read.getCause().getCause());
    }

    @Test
    void suppressedThrowablesDoNotCross() {
        IllegalStateException thrown = new IllegalStateException("main");
        thrown.addSuppressed(new IllegalArgumentException("suppressed"));

        MarshalledException read = cross(thrown);

        assertEquals(0, read.getSuppressed().length);
        assertNull(read.getCause());
    }

    @Test
    void aCarrierChainCrossingAgainKeepsEveryForeignClassName() {
        MarshalledException first = cross(new Unrelated("outer").initCause(new SecurityException("inner")));

        MarshalledException again = cross(first);

        assertEquals(Unrelated.class.getName(), again.getForeignExceptionClassName());
        MarshalledException inner = (MarshalledException) again.getCause();
        assertEquals("java.lang.SecurityException", inner.getForeignExceptionClassName());
        assertEquals("inner", inner.getMessage());
    }

    @Test
    void aLongMessageWithSupplementaryCharactersCrossesWhole() {
        StringBuilder message = new StringBuilder();
        while (message.length() < 100_000) {
            message.append("ä😀");
        }

        MarshalledException read = cross(new IllegalStateException(message.toString()));

        assertEquals(message.toString(), read.getMessage());
    }

    private static List<String> frames(StackTraceElement[] stack) {
        List<String> frames = new ArrayList<>();
        for (StackTraceElement e : stack) {
            frames.add(
                    e.getClassName() + "." + e.getMethodName() + "(" + e.getFileName() + ":" + e.getLineNumber() + ")");
        }
        return frames;
    }

    private static List<String> foreign(StackTraceElement[] stack, Throwable thrown) {
        List<String> frames = frames(stack);
        int n = thrown.getStackTrace().length;
        assertTrue(frames.size() > n, frames + " carries no local frame after " + n + " foreign ones");
        assertTrue(frames.get(n).startsWith(ThrowableMarshallerTest.class.getName() + ".cross("), frames.get(n));
        return frames.subList(0, n);
    }

    private static MarshalledException cross(Throwable thrown) {
        return ThrowableMarshaller.read(ThrowableMarshaller.write(thrown));
    }
}
