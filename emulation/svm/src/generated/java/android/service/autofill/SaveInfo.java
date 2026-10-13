package android.service.autofill;

import android.os.Parcelable;

/**
 * Information used to indicate that an {@link AutofillService} is interested on saving the
 * user-inputed data for future use, through a
 * {@link AutofillService#onSaveRequest(SaveRequest, SaveCallback)}
 * call.
 *
 * <p>A {@link SaveInfo} is always associated with a {@link FillResponse}, and it contains at least
 * two pieces of information:
 *
 * <ol>
 *   <li>The type(s) of user data (like password or credit card info) that would be saved.
 *   <li>The minimum set of views (represented by their {@link AutofillId}) that need to be changed
 *       to trigger a save request.
 * </ol>
 *
 * <p>Typically, the {@link SaveInfo} contains the same {@code id}s as the {@link Dataset}:
 *
 * <pre class="prettyprint">
 *   new FillResponse.Builder()
 *       .addDataset(new Dataset.Builder()
 *           .setValue(id1, AutofillValue.forText("homer"), createPresentation("homer")) // username
 *           .setValue(id2, AutofillValue.forText("D'OH!"), createPresentation("password for homer")) // password
 *           .build())
 *       .setSaveInfo(new SaveInfo.Builder(
 *           SaveInfo.SAVE_DATA_TYPE_USERNAME | SaveInfo.SAVE_DATA_TYPE_PASSWORD,
 *           new AutofillId[] { id1, id2 }).build())
 *       .build();
 * </pre>
 *
 * <p>The save type flags are used to display the appropriate strings in the autofill save UI.
 * You can pass multiple values, but try to keep it short if possible. In the above example, just
 * {@code SaveInfo.SAVE_DATA_TYPE_PASSWORD} would be enough.
 *
 * <p>There might be cases where the {@link AutofillService} knows how to fill the screen,
 * but the user has no data for it. In that case, the {@link FillResponse} should contain just the
 * {@link SaveInfo}, but no {@link Dataset Datasets}:
 *
 * <pre class="prettyprint">
 *   new FillResponse.Builder()
 *       .setSaveInfo(new SaveInfo.Builder(SaveInfo.SAVE_DATA_TYPE_PASSWORD,
 *           new AutofillId[] { id1, id2 }).build())
 *       .build();
 * </pre>
 *
 * <p>There might be cases where the user data in the {@link AutofillService} is enough
 * to populate some fields but not all, and the service would still be interested on saving the
 * other fields. In that case, the service could set the
 * {@link SaveInfo.Builder#setOptionalIds(AutofillId[])} as well:
 *
 * <pre class="prettyprint">
 *   new FillResponse.Builder()
 *       .addDataset(new Dataset.Builder()
 *           .setValue(id1, AutofillValue.forText("742 Evergreen Terrace"),
 *               createPresentation("742 Evergreen Terrace")) // street
 *           .setValue(id2, AutofillValue.forText("Springfield"),
 *               createPresentation("Springfield")) // city
 *           .build())
 *       .setSaveInfo(new SaveInfo.Builder(SaveInfo.SAVE_DATA_TYPE_ADDRESS,
 *           new AutofillId[] { id1, id2 }) // street and  city
 *           .setOptionalIds(new AutofillId[] { id3, id4 }) // state and zipcode
 *           .build())
 *       .build();
 * </pre>
 *
 * <a name="TriggeringSaveRequest"></a>
 * <h3>Triggering a save request</h3>
 *
 * <p>The {@link AutofillService#onSaveRequest(SaveRequest, SaveCallback)} can be triggered after
 * any of the following events:
 * <ul>
 *   <li>The {@link Activity} finishes.
 *   <li>The app explicitly calls {@link AutofillManager#commit()}.
 *   <li>All required views become invisible (if the {@link SaveInfo} was created with the
 *       {@link #FLAG_SAVE_ON_ALL_VIEWS_INVISIBLE} flag).
 *   <li>The user clicks a specific view (defined by {@link Builder#setTriggerId(AutofillId)}.
 * </ul>
 *
 * <p>But it is only triggered when all conditions below are met:
 * <ul>
 *   <li>The {@link SaveInfo} associated with the {@link FillResponse} is not {@code null} neither
 *       has the {@link #FLAG_DELAY_SAVE} flag.
 *   <li>The {@link AutofillValue}s of all required views (as set by the {@code requiredIds} passed
 *       to the {@link SaveInfo.Builder} constructor are not empty.
 *   <li>The {@link AutofillValue} of at least one view (be it required or optional) has changed
 *       (i.e., it's neither the same value passed in a {@link Dataset}, nor the initial value
 *       presented in the view).
 *   <li>There is no {@link Dataset} in the last {@link FillResponse} that completely matches the
 *       screen state (i.e., all required and optional fields in the dataset have the same value as
 *       the fields in the screen).
 *   <li>The user explicitly tapped the autofill save UI asking to save data for autofill.
 * </ul>
 *
 * <a name="CustomizingSaveUI"></a>
 * <h3>Customizing the autofill save UI</h3>
 *
 * <p>The service can also customize some aspects of the autofill save UI:
 * <ul>
 *   <li>Add a simple subtitle by calling {@link Builder#setDescription(CharSequence)}.
 *   <li>Add a customized subtitle by calling
 *       {@link Builder#setCustomDescription(CustomDescription)}.
 *   <li>Customize the button used to reject the save request by calling
 *       {@link Builder#setNegativeAction(int, IntentSender)}.
 *   <li>Decide whether the UI should be shown based on the user input validation by calling
 *       {@link Builder#setValidator(Validator)}.
 * </ul>
 */
public final class SaveInfo implements Parcelable {
    /**
     * Postpone the autofill save UI.
     *
     * <p>If flag is set, the autofill save UI is not triggered when the
     * autofill context associated with the response associated with this {@link SaveInfo} is
     * committed (with {@link AutofillManager#commit()}). Instead, the {@link FillContext}
     * is delivered in future fill requests (with {@link
     * AutofillService#onFillRequest(FillRequest, android.os.CancellationSignal, FillCallback)})
     * and save request (with {@link AutofillService#onSaveRequest(SaveRequest, SaveCallback)})
     * of an activity belonging to the same task.
     *
     * <p>This flag should be used when the service detects that the application uses
     * multiple screens to implement an autofillable workflow (for example, one screen for the
     * username field, another for password).
     */
    public static final int FLAG_DELAY_SAVE = 4;

    /**
     * By default, a save request is automatically <a href="#TriggeringSaveRequest">triggered</a>
     * once the {@link Activity} finishes. If this flag is set, finishing the activity doesn't
     * trigger a save request.
     *
     * <p>This flag is typically used in conjunction with {@link Builder#setTriggerId(AutofillId)}.
     */
    public static final int FLAG_DONT_SAVE_ON_FINISH = 2;

    /**
     * Usually, a save request is only automatically <a href="#TriggeringSaveRequest">triggered</a>
     * once the {@link Activity} finishes. If this flag is set, it is triggered once all saved views
     * become invisible.
     */
    public static final int FLAG_SAVE_ON_ALL_VIEWS_INVISIBLE = 1;

    /**
     * Style for the negative button of the save UI to cancel the
     * save operation. In this case, the user tapping the negative
     * button signals that they would prefer to not save the filled
     * content.
     */
    public static final int NEGATIVE_BUTTON_STYLE_CANCEL = 0;

    /**
     * Style for the negative button of the save UI to never do the
     * save operation. This means that the user does not need to save
     * any data on this activity or application. Once the user tapping
     * the negative button, the service should never trigger the save
     * UI again. In addition to this, must consider providing restore
     * options for the user.
     */
    public static final int NEGATIVE_BUTTON_STYLE_NEVER = 2;

    /**
     * Style for the negative button of the save UI to reject the
     * save operation. This could be useful if the user needs to
     * opt-in your service and the save prompt is an advertisement
     * of the potential value you can add to the user. In this
     * case, the user tapping the negative button sends a strong
     * signal that the feature may not be useful and you may
     * consider some backoff strategy.
     */
    public static final int NEGATIVE_BUTTON_STYLE_REJECT = 1;

    /**
     * Style for the positive button of save UI to have next action before the save operation.
     * This could be useful if the filled content contains sensitive personally identifiable
     * information and then requires user confirmation or verification. In this case, the user
     * tapping the positive button signals that they would complete the next required action
     * to save the filled content.
     */
    public static final int POSITIVE_BUTTON_STYLE_CONTINUE = 1;

    /**
     * Style for the positive button of save UI to request the save operation.
     * In this case, the user tapping the positive button signals that they
     * agrees to save the filled content.
     */
    public static final int POSITIVE_BUTTON_STYLE_SAVE = 0;

    /**
     * Type used on when the {@link FillResponse} represents a physical address (such as street,
     * city, state, etc).
     */
    public static final int SAVE_DATA_TYPE_ADDRESS = 2;

    /**
     * Type used when the {@link FillResponse} represents a credit card.
     */
    public static final int SAVE_DATA_TYPE_CREDIT_CARD = 4;

    /**
     * Type used when the {@link FillResponse} represents a debit card.
     */
    public static final int SAVE_DATA_TYPE_DEBIT_CARD = 32;

    /**
     * Type used when the {@link FillResponse} represents just an email address, without a password.
     */
    public static final int SAVE_DATA_TYPE_EMAIL_ADDRESS = 16;

    /**
     * Type used when the service can save the contents of a screen, but cannot describe what
     * the content is for.
     */
    public static final int SAVE_DATA_TYPE_GENERIC = 0;

    /**
     * Type used when the {@link FillResponse} represents a card that does not a specified card or
     * cannot identify what the card is for.
     */
    public static final int SAVE_DATA_TYPE_GENERIC_CARD = 128;

    /**
     * Type used when the {@link FillResponse} represents user credentials that have a password.
     */
    public static final int SAVE_DATA_TYPE_PASSWORD = 1;

    /**
     * Type used when the {@link FillResponse} represents a payment card except for credit and
     * debit cards.
     */
    public static final int SAVE_DATA_TYPE_PAYMENT_CARD = 64;

    /**
     * Type used when the {@link FillResponse} represents just an username, without a password.
     */
    public static final int SAVE_DATA_TYPE_USERNAME = 8;

    private SaveInfo() {
    }
}
