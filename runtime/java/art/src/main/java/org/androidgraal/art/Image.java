package org.androidgraal.art;

import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import java.lang.ref.Reference;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.TimeZone;
import org.androidgraal.shared.Blocks;
import org.androidgraal.shared.Handles;
import org.androidgraal.shared.MarshalledException;
import org.androidgraal.shared.Proxy;
import org.androidgraal.shared.ReleasedReferences;
import org.androidgraal.shared.Services;
import org.androidgraal.shared.ThrowableMarshaller;

public final class Image {

    private static final ThreadLocal<ThreadData> THREADS = new ThreadLocal<>();
    private static final Handles HANDLES = new Handles(-1, Image::releaseImageExports);
    private static final HashMap<Class<?>, Contract> ROOTS = new HashMap<>();
    private static volatile long isolate;
    private static volatile boolean started;
    private static volatile Context application;
    private static volatile Object fenceSink;

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
        startReleaseThread();
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

    /**
     * Associates the result object with an id for returning the object. A proxy is made reachable until the calling
     * thread's next crossing into the image returns.
     */
    public static long sendResult(Object result) {
        if (result instanceof Proxy) {
            thread().handedBack = result;
        }
        return HANDLES.send(result);
    }

    /**
     * Keeps {@code object} reachable up to this call. Below API 28, which lacks {@code Reference.reachabilityFence}, a
     * store into a volatile field does it: ART's compilers keep every volatile store.
     */
    public static void reachabilityFence(Object object) {
        if (Build.VERSION.SDK_INT >= 28) {
            Fence28.reachabilityFence(object);
        } else {
            fenceSink = object;
            fenceSink = null;
        }
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
    public static void raiseImageException(Throwable t) {
        try {
            byte[] bytes = ThrowableMarshaller.write(t);
            ThreadData thread = thread();
            ByteBuffer block = Transport.allocBlock(thread, bytes.length, 1);
            block.position(Blocks.HEADER);
            block.put(bytes);
            setImagePendingException(thread.isolateThread, block);
            thread.afterCrossing();
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
        long thread = createIsolate(getIsolateOptions());
        if (thread == 0L) {
            throw new ImageStartException("creating the isolate of " + library + " failed");
        }
        ThreadData data = new ThreadData(thread);
        THREADS.set(data);
        try {
            initializeIsolate(thread);
            data.afterCrossing();
        } catch (Throwable t) {
            throw new ImageStartException("initialising the isolate of " + library + " failed", t);
        }
        detachThreadOnExit(thread);
        isolate = getIsolate(thread);
    }

    private static String[] getIsolateOptions() {
        List<String> options = new ArrayList<>();
        String tmpdir = System.getProperty("java.io.tmpdir");
        if (tmpdir != null) {
            options.add("-Djava.io.tmpdir=" + tmpdir);
        }
        options.add("-Duser.timezone=" + TimeZone.getDefault().getID());
        return options.toArray(new String[0]);
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

    private static void startReleaseThread() {
        Thread releases = new Thread(HANDLES::releaseForever, "androidgraal-art-releases");
        releases.setDaemon(true);
        releases.start();
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
    private static void releaseArtExports(ByteBuffer block) {
        try {
            block.order(ByteOrder.nativeOrder());
            long count = block.getLong();
            List<ReleasedReferences> batch = new ArrayList<>();
            for (long i = 0; i < count; i++) {
                batch.add(new ReleasedReferences(block.getLong(), block.getLong()));
            }
            HANDLES.releaseExports(batch);
        } catch (Throwable t) {
            raiseImageException(t);
        }
    }

    @EndPoint
    private static void setArtPendingException(ByteBuffer block) {
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

    private static native long createIsolate(String[] options);

    private static native long getIsolate(long thread);

    private static native void initializeIsolate(long thread);

    private static native long attachThread(long isolate);

    private static native long currentThread(long isolate);

    private static native void detachThreadOnExit(long thread);

    private static void releaseImageExports(List<ReleasedReferences> batch) {
        ByteBuffer block = ByteBuffer.allocateDirect(8 + 16 * batch.size()).order(ByteOrder.nativeOrder());
        block.putLong(batch.size());
        for (ReleasedReferences released : batch) {
            block.putLong(released.id());
            block.putLong(released.referenceCount());
        }
        ThreadData thread = thread();
        releaseImageExports(thread.isolateThread, block);
        thread.afterCrossing();
    }

    private static native void releaseImageExports(long thread, ByteBuffer block);

    private static native void setImagePendingException(long thread, ByteBuffer block);

    @RequiresApi(28)
    private static final class Fence28 {

        public static void reachabilityFence(Object object) {
            Reference.reachabilityFence(object);
        }
    }

    public static final class ThreadData {

        public final long isolateThread;

        /** The serialized exception the image threw on this thread, null when none is pending. ART frees it. */
        public ByteBuffer pendingException;

        /** Keeps the proxy this thread last returned to the image reachable. */
        public Object handedBack;

        private ThreadData(long isolateThread) {
            this.isolateThread = isolateThread;
        }

        /**
         * Runs right after every crossing this thread makes into the image: lets go of the proxy handed back and throws
         * what the image threw, as a {@link MarshalledException}.
         */
        public void afterCrossing() {
            handedBack = null;
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
