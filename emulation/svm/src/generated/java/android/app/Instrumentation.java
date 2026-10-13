package android.app;

/**
 * Base class for implementing application instrumentation code.  When running
 * with instrumentation turned on, this class will be instantiated for you
 * before any of the application code, allowing you to monitor all of the
 * interaction the system has with the application.  An Instrumentation
 * implementation is described to the system through an AndroidManifest.xml's
 * &lt;instrumentation&gt; tag.
 */
public final class Instrumentation {
    /**
     * If included in the status or final bundle sent to an IInstrumentationWatcher, this key 
     * identifies the class that is writing the report.  This can be used to provide more structured
     * logging or reporting capabilities in the IInstrumentationWatcher.
     */
    public static final String REPORT_KEY_IDENTIFIER = "id";

    /**
     * If included in the status or final bundle sent to an IInstrumentationWatcher, this key 
     * identifies a string which can simply be printed to the output stream.  Using these streams
     * provides a "pretty printer" version of the status & final packets.  Any bundles including 
     * this key should also include the complete set of raw key/value pairs, so that the
     * instrumentation can also be launched, and results collected, by an automated system.
     */
    public static final String REPORT_KEY_STREAMRESULT = "stream";

    private Instrumentation() {
    }
}
