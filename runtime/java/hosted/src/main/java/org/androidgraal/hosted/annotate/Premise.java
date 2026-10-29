package org.androidgraal.hosted.annotate;

/**
 * A fact an {@link Adapted} difference rests on. Each constant says what is assumed and when it stops holding.
 */
public enum Premise {
    /**
     * The image holds and sends no binder, and no binder call enters it. Stops holding when a service, a provider or an
     * AIDL stub runs in the image.
     */
    NO_BINDER,

    /**
     * SubstrateVM runs cleaners, and the emulation registers no finalizer and no cleaner. Holds by design.
     */
    NO_FINALIZER,

    /**
     * The image traces nothing. Stops holding with a binding of ATrace or Perfetto.
     */
    NO_TRACING,

    /**
     * The main thread's {@code Looper} is ART's and out of the image's reach. Stops holding when the image gets a
     * {@code Handler} bound to the main thread through a proxy.
     */
    NO_MAIN_LOOPER,

    /**
     * The facility is ART's and the image reaches it over the boundary, such as the application's assets or the process
     * name. Stops holding when the image owns the facility.
     */
    HOST_OWNED,

    /**
     * A wire format or a value follows the device's API level, not the platform's text. Holds by design.
     */
    DEVICE_LEVEL,

    /**
     * File descriptors carry no fdsan owner. Stops holding with a binding of fdsan.
     */
    NO_FDSAN,

    /**
     * The framework's process bookkeeping, such as AppOps, StrictMode and RuntimeInit, is absent. Stops holding when
     * those classes are in the image.
     */
    NO_FRAMEWORK_PROCESS,

    /**
     * libprocessgroup is not bound. Stops holding when the image binds it, which the NDK does not offer.
     */
    NO_PROCESSGROUP,

    /**
     * The class library is OpenJDK's, without libcore's additions to JDK classes. Holds by design.
     */
    NO_LIBCORE_JDK,

    /**
     * ART's natives behind {@code dalvik.system}, such as those of {@code VMRuntime} and {@code VMDebug}, are absent,
     * and the copies keep the Java parts. Holds by design.
     */
    NO_ART_RUNTIME,

    /**
     * {@code Log.isLoggable} ignores the log.tag system properties, while liblog still honours them on every write.
     * Stops holding with a binding of {@code __android_log_is_loggable}.
     */
    NO_LOG_PROPERTIES,

    /**
     * The image has one class loader, so a test for the boot class loader becomes a test of the package. Holds by
     * design.
     */
    SINGLE_CLASS_LOADER,

    /**
     * A type the stock member names is not in the image. Stops holding when that type is emulated.
     */
    ABSENT_TYPE
}
