package org.androidgraal.art;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
        VarHandle started = started();
        Map<Class<?>, Contract> roots = roots();
        started.set(true);
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
            started.set(false);
            roots.clear();
        }
    }

    @Test
    void aRootTwoContractsDeclareFailsTheStartNamingBoth() throws ReflectiveOperationException {
        MethodHandle loadContracts = endPoint("loadContracts", MethodType.methodType(void.class));
        try {
            ImageStartException e = assertThrows(ImageStartException.class, () -> loadContracts.invoke());

            assertEquals("root org.androidgraal.art.ImageTest$Root is declared by org.androidgraal.art.ImageTest$First"
                    + " and org.androidgraal.art.ImageTest$Second", e.getMessage());
        } finally {
            roots().clear();
        }
    }

    @Test
    void aThrowingStartupFailsTheStartNamingItAndThePhase() throws ReflectiveOperationException {
        MethodHandle startServices = endPoint("startServices", MethodType.methodType(void.class));
        try {
            Failing.phase = "prepare";
            ImageStartException prepare = assertThrows(ImageStartException.class, () -> startServices.invoke());
            Failing.phase = "start";
            ImageStartException start = assertThrows(ImageStartException.class, () -> startServices.invoke());

            assertEquals("preparing org.androidgraal.art.ImageTest$Failing failed", prepare.getMessage());
            assertEquals("failing", assertInstanceOf(IllegalStateException.class, prepare.getCause()).getMessage());
            assertEquals("starting org.androidgraal.art.ImageTest$Failing failed", start.getMessage());
            assertEquals("failing", assertInstanceOf(IllegalStateException.class, start.getCause()).getMessage());
        } finally {
            Failing.phase = null;
        }
    }

    @Test
    void everyStartupPreparesBeforeAnyStarts() throws Throwable {
        Recording.CALLS.clear();

        endPoint("startServices", MethodType.methodType(void.class)).invoke();

        Set<String> prepared = new HashSet<>(
                Arrays.asList(Recording.class.getName() + ".prepare", SecondRecording.class.getName() + ".prepare"));
        Set<String> started = new HashSet<>(
                Arrays.asList(Recording.class.getName() + ".start", SecondRecording.class.getName() + ".start"));
        assertEquals(4, Recording.CALLS.size());
        assertEquals(prepared, new HashSet<>(Recording.CALLS.subList(0, 2)));
        assertEquals(started, new HashSet<>(Recording.CALLS.subList(2, 4)));
    }

    @Test
    void aSecondStartFails() throws ReflectiveOperationException {
        VarHandle started = started();
        started.set(true);
        try {
            IllegalStateException e = assertThrows(IllegalStateException.class, () -> Image.start("no-such-image"));

            assertEquals("the image has already started", e.getMessage());
        } finally {
            started.set(false);
        }
    }

    @Test
    void handleUncaughtExceptionReadsTheLengthThenTheWireFromOffset8InNativeOrder() throws Throwable {
        Thread thread = Thread.currentThread();
        Thread.UncaughtExceptionHandler previous = thread.getUncaughtExceptionHandler();
        AtomicReference<Throwable> received = new AtomicReference<>();
        thread.setUncaughtExceptionHandler((t, e) -> received.set(e));
        try {
            endPoint("handleUncaughtException", MethodType.methodType(void.class, ByteBuffer.class))
                    .invoke(block(new IllegalStateException("lost")));
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

    /** Image's private members by lookup, which resolves only their own types, never android.jar's. */
    private static MethodHandles.Lookup image() throws IllegalAccessException {
        return MethodHandles.privateLookupIn(Image.class, MethodHandles.lookup());
    }

    private static VarHandle started() throws ReflectiveOperationException {
        return image().findStaticVarHandle(Image.class, "started", boolean.class);
    }

    @SuppressWarnings("unchecked")
    private static Map<Class<?>, Contract> roots() throws ReflectiveOperationException {
        return (Map<Class<?>, Contract>) image().findStaticVarHandle(Image.class, "ROOTS", HashMap.class).get();
    }

    private static MethodHandle endPoint(String name, MethodType type) throws ReflectiveOperationException {
        return image().findStatic(Image.class, name, type);
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

    public static final class Failing implements Startup {

        private static String phase;

        @Override
        public void prepare() {
            failIn("prepare");
        }

        @Override
        public void start() {
            failIn("start");
        }

        private static void failIn(String current) {
            if (current.equals(phase)) {
                throw new IllegalStateException("failing");
            }
        }
    }

    public static class Recording implements Startup {

        private static final List<String> CALLS = new ArrayList<>();

        @Override
        public void prepare() {
            CALLS.add(getClass().getName() + ".prepare");
        }

        @Override
        public void start() {
            CALLS.add(getClass().getName() + ".start");
        }
    }

    public static final class SecondRecording extends Recording {}
}
