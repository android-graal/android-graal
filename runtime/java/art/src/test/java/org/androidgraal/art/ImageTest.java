package org.androidgraal.art;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.androidgraal.shared.Blocks;
import org.androidgraal.shared.MarshalledException;
import org.androidgraal.shared.ThrowableMarshaller;
import org.junit.jupiter.api.Test;

class ImageTest {

    @Test
    void aStartFailsNamingTheLibraryItCouldNotLoad() {
        ImageStartException e = assertThrows(ImageStartException.class, () -> Image.start("no-such-image"));

        assertEquals("loading libno-such-image.so failed", e.getMessage());
        assertInstanceOf(UnsatisfiedLinkError.class, e.getCause());
    }

    @Test
    void aCrossingBeforeTheImageStartedFailsNamingStart() {
        IllegalStateException e = assertThrows(IllegalStateException.class, Image::thread);
        assertEquals("the image has not started: call NativeImage.start() first", e.getMessage());
    }

    @Test
    void createBeforeTheImageStartedFailsNamingStart() {
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> Image.createRoot(Runnable.class));
        assertEquals("the image has not started: call NativeImage.start() first", e.getMessage());
    }

    @Test
    void createDelegatesToTheContractDeclaringTheRootAndNamesTheRootsOtherwise() throws ReflectiveOperationException {
        Field started = Image.class.getDeclaredField("started");
        started.setAccessible(true);
        Map<Class<?>, Contract> roots = roots();
        started.setBoolean(null, true);
        try {
            assertEquals("org.androidgraal.art.ImageTest$Root is no root of a loaded contract. Loaded roots: []",
                    assertThrows(IllegalArgumentException.class, () -> Image.createRoot(Root.class)).getMessage());

            roots.put(Root.class, new First());

            assertInstanceOf(Root.class, Image.createRoot(Root.class));
            assertEquals(
                    "java.lang.Runnable is no root of a loaded contract."
                            + " Loaded roots: [interface org.androidgraal.art.ImageTest$Root]",
                    assertThrows(IllegalArgumentException.class, () -> Image.createRoot(Runnable.class)).getMessage());
        } finally {
            started.setBoolean(null, false);
            roots.clear();
        }
    }

    @Test
    void aRootTwoContractsDeclareFailsTheStartNamingBoth() throws ReflectiveOperationException {
        Method loadContracts = Image.class.getDeclaredMethod("loadContracts");
        loadContracts.setAccessible(true);
        try {
            Throwable e = assertThrows(InvocationTargetException.class, () -> loadContracts.invoke(null))
                    .getTargetException();

            assertInstanceOf(ImageStartException.class, e);
            assertEquals("root org.androidgraal.art.ImageTest$Root is declared by org.androidgraal.art.ImageTest$First"
                    + " and org.androidgraal.art.ImageTest$Second", e.getMessage());
        } finally {
            roots().clear();
        }
    }

    @Test
    void aSecondStartFails() throws ReflectiveOperationException {
        Field started = Image.class.getDeclaredField("started");
        started.setAccessible(true);
        started.setBoolean(null, true);
        try {
            IllegalStateException e = assertThrows(IllegalStateException.class, () -> Image.start("no-such-image"));

            assertEquals("the image has already started", e.getMessage());
        } finally {
            started.setBoolean(null, false);
        }
    }

    @Test
    void handleUncaughtExceptionReadsTheLengthThenTheWireFromOffset8InNativeOrder()
            throws ReflectiveOperationException {
        Thread thread = Thread.currentThread();
        Thread.UncaughtExceptionHandler previous = thread.getUncaughtExceptionHandler();
        AtomicReference<Throwable> received = new AtomicReference<>();
        thread.setUncaughtExceptionHandler((t, e) -> received.set(e));
        try {
            endPoint("handleUncaughtException", ByteBuffer.class).invoke(null,
                    block(new IllegalStateException("lost")));
        } finally {
            thread.setUncaughtExceptionHandler(previous);
        }

        MarshalledException carrier = assertInstanceOf(MarshalledException.class, received.get());
        assertEquals("java.lang.IllegalStateException", carrier.getForeignExceptionClassName());
        assertEquals("lost", carrier.getMessage());
    }

    @Test
    void raiseExceptionBeforeStartHandsTheThrowableToTheThreadsHandler() {
        Thread thread = Thread.currentThread();
        Thread.UncaughtExceptionHandler previous = thread.getUncaughtExceptionHandler();
        AtomicReference<Throwable> received = new AtomicReference<>();
        thread.setUncaughtExceptionHandler((t, e) -> received.set(e));
        RuntimeException thrown = new RuntimeException("x");
        try {
            Image.raiseException(thrown);
        } finally {
            thread.setUncaughtExceptionHandler(previous);
        }

        assertSame(thrown, received.get());
    }

    @Test
    void throwPendingExceptionThrowsNothingWhenNothingIsPending() throws ReflectiveOperationException {
        Constructor<Image.ThreadData> constructor = Image.ThreadData.class.getDeclaredConstructor(long.class);
        constructor.setAccessible(true);

        assertDoesNotThrow(constructor.newInstance(1L)::throwPendingException);
    }

    @Test
    void throwPendingExceptionClearsTheSlotThenReachesTheRawFree() throws ReflectiveOperationException {
        Constructor<Image.ThreadData> constructor = Image.ThreadData.class.getDeclaredConstructor(long.class);
        constructor.setAccessible(true);
        Image.ThreadData thread = constructor.newInstance(1L);
        thread.pendingException = block(new IllegalStateException("lost"));

        Throwable caught = assertThrows(Throwable.class, thread::throwPendingException);

        assertNull(thread.pendingException);
        assertInstanceOf(UnsatisfiedLinkError.class, caught);
        assertTrue(caught.getMessage().contains("Transport.free(long, java.nio.ByteBuffer)"));
    }

    /** A block as the image writes it, with a byte past the wire, in the byte order a view from JNI has. */
    private static ByteBuffer block(Throwable t) {
        byte[] wire = ThrowableMarshaller.write(t);
        int size = Blocks.size(wire.length, 1);
        ByteBuffer block = ByteBuffer.allocate(size + 1).order(ByteOrder.nativeOrder());
        block.putInt(wire.length).putInt(size).put(wire).put((byte) 7);
        return block.order(ByteOrder.BIG_ENDIAN);
    }

    @SuppressWarnings("unchecked")
    private static Map<Class<?>, Contract> roots() throws ReflectiveOperationException {
        Field roots = Image.class.getDeclaredField("ROOTS");
        roots.setAccessible(true);
        return (Map<Class<?>, Contract>) roots.get(null);
    }

    private static Method endPoint(String name, Class<?> parameter) throws NoSuchMethodException {
        Method method = Image.class.getDeclaredMethod(name, parameter);
        method.setAccessible(true);
        return method;
    }

    public interface Root {}

    public static class First implements Contract {

        @Override
        public void initialize() {
        }

        @Override
        public Class<?>[] roots() {
            return new Class<?>[] {Root.class};
        }

        @Override
        public <T> T create(Class<T> root) {
            return root.cast(new Root() {
            });
        }
    }

    public static final class Second extends First {}
}
