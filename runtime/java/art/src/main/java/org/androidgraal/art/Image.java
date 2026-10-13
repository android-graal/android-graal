package org.androidgraal.art;

import android.content.Context;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.List;
import java.util.ServiceConfigurationError;
import org.androidgraal.shared.Blocks;
import org.androidgraal.shared.Handles;
import org.androidgraal.shared.MarshalledException;
import org.androidgraal.shared.Services;
import org.androidgraal.shared.ThrowableMarshaller;

public final class Image {

    private static final ThreadLocal<ThreadData> THREADS = new ThreadLocal<>();
    private static final Handles HANDLES = new Handles(-1, Image::onProxyReleased);
    private static final HashMap<Class<?>, Contract> ROOTS = new HashMap<>();
    private static volatile long isolate;
    private static volatile boolean started;
    private static volatile Context application;

    private Image() {
    }

    /**
     * Loads {@code lib<name>.so}, creates the isolate, initialises every {@link Contract} the app's class loader
     * provides and runs every {@link Startup}, once per process: a second start after one that reached the startup
     * services throws an {@link IllegalStateException}, every failure is an {@link ImageStartException}. Needs the
     * application context the art plugin's provider hands over, so it runs in the app's main process, after the
     * providers are created.
     */
    public static synchronized void start(String name) {
        if (started) {
            throw new IllegalStateException("the image has already started");
        }
        load(name);
        loadContracts();
        started = true;
        startServices();
    }

    public static void application(Context context) {
        application = context;
    }

    public static Context application() {
        Context context = application;
        if (context == null) {
            throw new IllegalStateException("no application: the provider of the art plugin's manifest did not run");
        }
        return context;
    }

    public static boolean started() {
        return started;
    }

    /** A new image object implementing {@code root}, a {@code @Root} interface of a loaded contract. */
    public static <T> T createRoot(Class<T> root) {
        checkStarted();
        Contract contract = ROOTS.get(root);
        if (contract == null) {
            throw new IllegalArgumentException(
                    root.getName() + " is no root of a loaded contract. Loaded roots: " + ROOTS.keySet());
        }
        return contract.create(root);
    }

    public static Handles handles() {
        return HANDLES;
    }

    /** The calling thread's data, attached on the first call and detached when the thread exits. */
    public static ThreadData thread() {
        ThreadData thread = THREADS.get();
        return thread != null ? thread : attachCurrentThread();
    }

    /**
     * Raises {@code t} on the image side, once the ART side returns, or hands it to the calling thread's uncaught
     * exception handler when it cannot. Never throws.
     */
    public static void raiseException(Throwable t) {
        try {
            byte[] bytes = ThrowableMarshaller.write(t);
            ThreadData thread = thread();
            ByteBuffer block = Transport.allocBlock(thread, bytes.length, 1);
            block.position(Blocks.HEADER);
            block.put(bytes);
            setPendingException(thread.isolateThread, block);
            thread.throwPendingException();
        } catch (Throwable e) {
            handleUncaughtException(t);
        }
    }

    private static void load(String name) {
        String library = "lib" + name + ".so";
        try {
            System.loadLibrary(name);
        } catch (UnsatisfiedLinkError e) {
            throw new ImageStartException("loading " + library + " failed", e);
        }
        long thread = createIsolate();
        if (thread == 0L) {
            throw new ImageStartException("creating the isolate of " + library + " failed");
        }
        ThreadData data = new ThreadData(thread);
        THREADS.set(data);
        try {
            initializeIsolate(thread);
            data.throwPendingException();
        } catch (Throwable t) {
            throw new ImageStartException("initialising the isolate of " + library + " failed", t);
        }
        detachThreadOnExit(thread);
        isolate = getIsolate(thread);
    }

    private static void loadContracts() {
        try {
            for (Contract contract : Services.all(Contract.class)) {
                try {
                    contract.initialize();
                } catch (Throwable t) {
                    throw new ImageStartException("initialising contract " + contract.getClass().getName() + " failed",
                            t);
                }
                for (Class<?> root : contract.roots()) {
                    Contract owner = ROOTS.get(root);
                    if (owner != null) {
                        throw new ImageStartException("root " + root.getName() + " is declared by "
                                + owner.getClass().getName() + " and " + contract.getClass().getName());
                    }
                    ROOTS.put(root, contract);
                }
            }
        } catch (ServiceConfigurationError e) {
            throw new ImageStartException("loading a contract failed", e);
        }
    }

    private static void startServices() {
        List<Startup> services;
        try {
            services = Services.all(Startup.class);
        } catch (ServiceConfigurationError e) {
            throw new ImageStartException("loading a startup service failed", e);
        }
        for (Startup service : services) {
            try {
                service.prepare();
            } catch (Throwable t) {
                throw new ImageStartException("preparing " + service.getClass().getName() + " failed", t);
            }
        }
        for (Startup service : services) {
            try {
                service.start();
            } catch (Throwable t) {
                throw new ImageStartException("starting " + service.getClass().getName() + " failed", t);
            }
        }
    }

    private static ThreadData attachCurrentThread() {
        checkStarted();
        long thread = currentThread(isolate);
        if (thread != 0L) {
            ThreadData data = new ThreadData(thread);
            THREADS.set(data);
            return data;
        }
        thread = attachThread(isolate);
        if (thread == 0L) {
            throw new IllegalStateException("attaching " + Thread.currentThread().getName() + " to the image failed");
        }
        ThreadData data = new ThreadData(thread);
        THREADS.set(data);
        detachThreadOnExit(thread);
        return data;
    }

    private static void checkStarted() {
        if (!started) {
            throw new IllegalStateException("the image has not started: call NativeImage.start() first");
        }
    }

    @EndPoint
    private static void dropExport(long id) {
        try {
            HANDLES.drop(id);
        } catch (Throwable t) {
            raiseException(t);
        }
    }

    @EndPoint
    private static void setPendingException(ByteBuffer block) {
        thread().pendingException = block;
    }

    @EndPoint
    private static void handleUncaughtException(ByteBuffer block) {
        Throwable thrown;
        try {
            thrown = ThrowableMarshaller.read(Transport.readBlock(block.order(ByteOrder.nativeOrder())), Image.class,
                    ThreadData.class);
        } catch (Throwable t) {
            t.printStackTrace();
            return;
        }
        handleUncaughtException(thrown);
    }

    private static void handleUncaughtException(Throwable thrown) {
        thrown.printStackTrace();
        try {
            Thread thread = Thread.currentThread();
            thread.getUncaughtExceptionHandler().uncaughtException(thread, thrown);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static native long createIsolate();

    private static native long getIsolate(long thread);

    private static native void initializeIsolate(long thread);

    private static native long attachThread(long isolate);

    private static native long currentThread(long isolate);

    private static native void detachThreadOnExit(long thread);

    private static void onProxyReleased(long id) {
        ThreadData thread = thread();
        dropExport(thread.isolateThread, id);
        thread.throwPendingException();
    }

    private static native void dropExport(long thread, long id);

    private static native void setPendingException(long thread, ByteBuffer block);

    public static final class ThreadData {

        public final long isolateThread;

        /** The serialized exception the image threw on this thread, null when none is pendin. ART frees it. */
        public ByteBuffer pendingException;

        private ThreadData(long isolateThread) {
            this.isolateThread = isolateThread;
        }

        /** Throws what the image threw during the last crossing of this thread, as a {@link MarshalledException}. */
        public void throwPendingException() {
            ByteBuffer block = pendingException;
            if (block == null) {
                return;
            }
            pendingException = null;
            byte[] bytes = Transport.readBlock(block.order(ByteOrder.nativeOrder()));
            Transport.freeNoException(this, block);
            throw ThrowableMarshaller.read(bytes, Image.class, ThreadData.class, Transport.class);
        }
    }
}
