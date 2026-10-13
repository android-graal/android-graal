package android.app.assist;

import android.os.Parcelable;

/**
 * Holds information about the content an application is viewing, to hand to an
 * assistant at the user's request.  This is filled in by
 * {@link android.app.Activity#onProvideAssistContent Activity.onProvideAssistContent}.
 */
public final class AssistContent implements Parcelable {
    /**
     * Extra for a {@link Bundle} that provides contextual AppFunction's information about the
     * content currently being viewed in the application.
     * <p>
     * This extra can be optionally supplied in the {@link AssistContent#getExtras()} bundle.
     * <p>
     * The schema of the {@link Bundle} in this extra is defined in the AppFunction SDK.
     *
     * @see android.app.appfunctions.AppFunctionManager
     */
    public static final String EXTRA_APP_FUNCTION_DATA = "android.app.assist.extra.APP_FUNCTION_DATA";

    private AssistContent() {
    }
}
