package android.service.autofill;

import android.os.Parcelable;

/**
 * Response for an {@link
 * AutofillService#onFillRequest(FillRequest, android.os.CancellationSignal, FillCallback)}.
 *
 * <p>See the main {@link AutofillService} documentation for more details and examples.
 */
public final class FillResponse implements Parcelable {
    /**
     * Flag used to request to wait for a delayed fill from the remote Autofill service if it's
     * passed to {@link Builder#setFlags(int)}.
     *
     * <p>Some datasets (i.e. OTP) take time to produce. This flags allows remote service to send
     * a {@link FillResponse} to the latest {@link FillRequest} via
     * {@link FillRequest#getDelayedFillIntentSender()} even if the original {@link FillCallback}
     * has timed out.
     */
    public static final int FLAG_DELAY_FILL = 4;

    /**
     * Flag used to change the behavior of {@link FillResponse.Builder#disableAutofill(long)}&mdash;
     * when this flag is passed to {@link Builder#setFlags(int)}, autofill is disabled only for the
     * activiy that generated the {@link FillRequest}, not the whole app.
     */
    public static final int FLAG_DISABLE_ACTIVITY_ONLY = 2;

    /**
     * Flag used to generate {@link FillEventHistory.Event events} of type
     * {@link FillEventHistory.Event#TYPE_CONTEXT_COMMITTED}&mdash;if this flag is not passed to
     * {@link Builder#setFlags(int)}, these events are not generated.
     */
    public static final int FLAG_TRACK_CONTEXT_COMMITED = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private FillResponse() {
    }
}
