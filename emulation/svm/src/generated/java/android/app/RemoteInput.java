package android.app;

import android.os.Parcelable;

/**
 * A {@code RemoteInput} object specifies input to be collected from a user to be passed along with
 * an intent inside a {@link android.app.PendingIntent} that is sent.
 * Always use {@link RemoteInput.Builder} to create instances of this class.
 * <p class="note"> See
 * <a href="{@docRoot}guide/topics/ui/notifiers/notifications.html#direct">Replying
 * to notifications</a> for more information on how to use this class.
 *
 * <p>The following example adds a {@code RemoteInput} to a {@link Notification.Action},
 * sets the result key as {@code quick_reply}, and sets the label as {@code Quick reply}.
 * Users are prompted to input a response when they trigger the action. The results are sent along
 * with the intent and can be retrieved with the result key (provided to the {@link Builder}
 * constructor) from the Bundle returned by {@link #getResultsFromIntent}.
 *
 * <pre class="prettyprint">
 * public static final String KEY_QUICK_REPLY_TEXT = "quick_reply";
 * Notification.Action action = new Notification.Action.Builder(
 *         R.drawable.reply, &quot;Reply&quot;, actionIntent)
 *         <b>.addRemoteInput(new RemoteInput.Builder(KEY_QUICK_REPLY_TEXT)
 *                 .setLabel("Quick reply").build()</b>)
 *         .build();</pre>
 *
 * <p>When the {@link android.app.PendingIntent} is fired, the intent inside will contain the
 * input results if collected. To access these results, use the {@link #getResultsFromIntent}
 * function. The result values will present under the result key passed to the {@link Builder}
 * constructor.
 *
 * <pre class="prettyprint">
 * public static final String KEY_QUICK_REPLY_TEXT = "quick_reply";
 * Bundle results = RemoteInput.getResultsFromIntent(intent);
 * if (results != null) {
 *     CharSequence quickReplyResult = results.getCharSequence(KEY_QUICK_REPLY_TEXT);
 * }</pre>
 */
public final class RemoteInput implements Parcelable {
    /**
     * The platform will determine whether choices will be edited before being sent to the app.
     */
    public static final int EDIT_CHOICES_BEFORE_SENDING_AUTO = 0;

    /**
     * Tapping on a choice should send the input immediately, without letting the user edit it.
     */
    public static final int EDIT_CHOICES_BEFORE_SENDING_DISABLED = 1;

    /**
     * Tapping on a choice should let the user edit the input before it is sent to the app.
     */
    public static final int EDIT_CHOICES_BEFORE_SENDING_ENABLED = 2;

    /**
     * Extra added to a clip data intent object to hold the text results bundle.
     */
    public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";

    /**
     * Label used to denote the clip data type used for remote input transport
     */
    public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";

    /**
     * The user selected one of the choices from {@link #getChoices}.
     */
    public static final int SOURCE_CHOICE = 1;

    /**
     * The user manually entered the data.
     */
    public static final int SOURCE_FREE_FORM_INPUT = 0;

    private RemoteInput() {
    }
}
