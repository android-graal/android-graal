package android.app.appfunctions;

import android.os.Parcelable;

/**
 * The response to an app function execution.
 *
 * <p>The {@link ExecuteAppFunctionResponse#getResultDocument()} contains the function's return
 * value as a GenericDocument. This can be converted back into a structured class using the
 * AppFunction SDK.
 *
 * <p>The {@link ExecuteAppFunctionResponse#getExtras()} provides any extra metadata returned by the
 * function. The AppFunction SDK can expose structured APIs by packing and unpacking this Bundle.
 */
public final class ExecuteAppFunctionResponse implements Parcelable {
    /**
     * The name of the property that stores the function return value within the {@code
     * resultDocument}.
     *
     * <p>See {@link GenericDocument#getProperty(String)} for more information.
     *
     * <p>If the function returns {@code void} or throws an error, the {@code resultDocument} will
     * be empty {@link GenericDocument}.
     *
     * <p>If the {@code resultDocument} is empty, {@link GenericDocument#getProperty(String)} will
     * return {@code null}.
     *
     * <p>See {@link #getResultDocument} for more information on extracting the return value.
     */
    public static final String PROPERTY_RETURN_VALUE = "androidAppfunctionsReturnValue";

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private ExecuteAppFunctionResponse() {
    }
}
