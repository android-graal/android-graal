package android.app.appfunctions;

/**
 * Provides access to App Functions. App Functions is currently a
 * beta/experimental preview feature.
 *
 * <p>An app function is a piece of functionality that apps expose to the system for cross-app
 * orchestration.
 *
 * <h3>Building App Functions</h3>
 *
 * <p>Most developers should build app functions through the AppFunctions SDK. This SDK library
 * offers a more convenient and type-safe way to build app functions. The SDK provides predefined
 * function schemas for common use cases and associated data classes for function parameters and
 * return values. Apps only have to implement the provided interfaces. Internally, the SDK converts
 * these data classes into {@link ExecuteAppFunctionRequest#getParameters()} and {@link
 * ExecuteAppFunctionResponse#getResultDocument()}.
 *
 * <h3>Discovering App Functions</h3>
 *
 * <p>When there is a package change or the device starts up, the metadata of available functions is
 * indexed on-device by {@link AppSearchManager}. AppSearch stores the indexed information as an
 * {@code AppFunctionStaticMetadata} document. This document contains the {@code functionIdentifier}
 * and the schema information that the app function implements. This allows other apps and the app
 * itself to discover these functions using the AppSearch search APIs. Visibility to this metadata
 * document is based on the packages that have visibility to the app providing the app functions.
 * AppFunction SDK provides a convenient way to achieve this and is the preferred method.
 *
 * <h3>Executing App Functions</h3>
 *
 * <p>To execute an app function, the caller app can retrieve the {@code functionIdentifier} from
 * the {@code AppFunctionStaticMetadata} document and use it to build an {@link
 * ExecuteAppFunctionRequest}. Then, invoke {@link #executeAppFunction} with the request to execute
 * the app function. Callers need the {@code android.permission.EXECUTE_APP_FUNCTIONS} permission to
 * execute app functions from other apps. An app can always execute its own app functions and
 * doesn't need these permissions. AppFunction SDK provides a convenient way to achieve this and
 * is the preferred method.
 *
 * <h3>Example</h3>
 *
 * <p>An assistant app is trying to fulfill the user request "Save XYZ into my note". The assistant
 * app should first list all available app functions as {@code AppFunctionStaticMetadata} documents
 * from AppSearch. Then, it should identify an app function that implements the {@code CreateNote}
 * schema. Finally, the assistant app can invoke {@link #executeAppFunction} with the {@code
 * functionIdentifier} of the chosen function.
 */
public final class AppFunctionManager {
    /**
     * The default state of the app function. Call {@link #setAppFunctionEnabled} with this to reset
     * enabled state to the default value.
     */
    public static final int APP_FUNCTION_STATE_DEFAULT = 0;

    /**
     * The app function is disabled. To disable an app function, call {@link #setAppFunctionEnabled}
     * with this value.
     */
    public static final int APP_FUNCTION_STATE_DISABLED = 2;

    /**
     * The app function is enabled. To enable an app function, call {@link #setAppFunctionEnabled}
     * with this value.
     */
    public static final int APP_FUNCTION_STATE_ENABLED = 1;

    private AppFunctionManager() {
    }
}
