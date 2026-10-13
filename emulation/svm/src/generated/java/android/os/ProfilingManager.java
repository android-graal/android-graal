package android.os;

/**
 * <p>
 * This class allows the caller to:
 * <ul>
 * <li>Request profiling and listen for results. Profiling types supported are: system traces,
 *     java heap dumps, heap profiles, and stack traces.</li>
 * <li>Register triggers for the system to capture profiling on the apps behalf.</li>
 * </ul>
 * </p>
 *
 * <p>
 * The {@link #requestProfiling} API can be used to begin profiling. Profiling may be ended manually
 * using the CancellationSignal provided in the request, or as a result of a timeout. The timeout
 * may be either the system default or caller defined in the parameter bundle for select types.
 * </p>
 *
 * <p>
 * The profiling results are delivered to the requesting app's data directory and a pointer to the
 * file will be received using the app provided listeners.
 * </p>
 *
 * <p>
 * Apps can provide listeners in one or both of two ways:
 * <ul>
 * <li>A request-specific listener included with the request. This will trigger only with a result
 *     from the request it was provided with.</li>
 * <li>A global listener provided by {@link #registerForAllProfilingResults}. This will be triggered
 *     for all results belonging to your app. This listener is the only way to receive results from
 *     system triggered profiling instances set up with {@link #addProfilingTriggers}.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Requests are rate limited and not guaranteed to be filled. Rate limiting can be disabled for
 * local testing of {@link #requestProfiling} using the shell command
 * {@code device_config put profiling_testing rate_limiter.disabled true}
 * </p>
 *
 * <p>
 * For local testing, profiling results can be accessed more easily by enabling debug mode. This
 * will retain output files in a temporary system directory. The locations of the retained files
 * will be available in logcat. The behavior and command varies by version:
 * <ul>
 * <li>For Android versions 16 and above, debug mode will retain both unredacted (where applicable)
 * and redacted results in the temporary directory. It can be enabled with the shell command
 * {@code device_config put profiling_testing delete_temporary_results.disabled true} and disabled
 * by setting that same value back to false. Retained results are accessible on all build types.
 * </li>
 * <li>For Android version 15, debug mode will retain only the unredacted result (where applicable)
 * in the temporary directory. It can be enabled with the shell command
 * {@code device_config put profiling_testing delete_unredacted_trace.disabled true} and disabled
 * by setting that same value back to false. The retained unredacted file can only be accessed on
 * builds with root access. To access the redacted output file on an unrooted device, apps can copy
 * the file from {@code /pkg/files/profiling/file.type} to {@code /pkg/cache/file.type}.
 * </li>
 * </ul>
 * </p>
 *
 * <p>
 * In order to test profiling triggers, enable testing mode for your app with the shell command
 * {@code device_config put profiling_testing system_triggered_profiling.testing_package_name
 * com.your.app} which will:
 * <ul>
 * <li>Ensure that a background trace is running.</li>
 * <li>Allow all triggers for the provided package name to pass the system level rate limiter.
 *     This mode will continue until manually stopped with the shell command
 *     {@code device_config delete profiling_testing
 *     system_triggered_profiling.testing_package_name}.
 *     </li>
 * </ul>
 * </p>
 *
 * <p>
 * Results are redacted and contain specific information about the requesting process only.
 * </p>
 */
public final class ProfilingManager {
    /**
     * Profiling type for {@link #requestProfiling} to request a heap profile.
     */
    public static final int PROFILING_TYPE_HEAP_PROFILE = 2;

    /**
     * Profiling type for {@link #requestProfiling} to request a java heap dump.
     */
    public static final int PROFILING_TYPE_JAVA_HEAP_DUMP = 1;

    /**
     * Profiling type for {@link #requestProfiling} to request a stack sample.
     */
    public static final int PROFILING_TYPE_STACK_SAMPLING = 3;

    /**
     * Profiling type for {@link #requestProfiling} to request a system trace.
     */
    public static final int PROFILING_TYPE_SYSTEM_TRACE = 4;

    private ProfilingManager() {
    }
}
