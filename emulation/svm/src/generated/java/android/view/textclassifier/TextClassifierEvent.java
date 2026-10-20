package android.view.textclassifier;

import android.os.Parcelable;

/**
 * This class represents events that are sent by components to the {@link TextClassifier} to report
 * something of note that relates to a feature powered by the TextClassifier. The TextClassifier may
 * log these events or use them to improve future responses to queries.
 * <p>
 * Each category of events has its their own subclass. Events of each type have an associated
 * set of related properties. You can find their specification in the subclasses.
 */
public final class TextClassifierEvent implements Parcelable {
    /**
     *  Conversation actions
     *
     * @see ConversationActionsEvent
     */
    public static final int CATEGORY_CONVERSATION_ACTIONS = 3;

    /**
     * Language detection
     *
     * @see LanguageDetectionEvent
     */
    public static final int CATEGORY_LANGUAGE_DETECTION = 4;

    /**
     * Linkify
     *
     * @see TextLinkifyEvent
     */
    public static final int CATEGORY_LINKIFY = 2;

    /**
     * Smart selection
     *
     * @see TextSelectionEvent
     */
    public static final int CATEGORY_SELECTION = 1;

    /**
     * TextClassifier generated some actions
     */
    public static final int TYPE_ACTIONS_GENERATED = 20;

    /**
     * Smart actions shown to the user.
     */
    public static final int TYPE_ACTIONS_SHOWN = 6;

    /**
     * Something else other than user or the default TextClassifier triggered a selection.
     */
    public static final int TYPE_AUTO_SELECTION = 5;

    /**
     * User clicked on Copy action.
     */
    public static final int TYPE_COPY_ACTION = 9;

    /**
     * User clicked on Cut action.
     */
    public static final int TYPE_CUT_ACTION = 11;

    /**
     * Some text links were generated.
     */
    public static final int TYPE_LINKS_GENERATED = 21;

    /**
     * User clicked a link.
     */
    public static final int TYPE_LINK_CLICKED = 7;

    /**
     * User composed a reply.
     */
    public static final int TYPE_MANUAL_REPLY = 19;

    /**
     * User clicked on a custom action.
     */
    public static final int TYPE_OTHER_ACTION = 16;

    /**
     * User typed over the selection.
     */
    public static final int TYPE_OVERTYPE = 8;

    /**
     * User clicked on Paste action.
     */
    public static final int TYPE_PASTE_ACTION = 10;

    /**
     * Selection is destroyed.
     */
    public static final int TYPE_SELECTION_DESTROYED = 15;

    /**
     * User dragged+dropped the selection.
     */
    public static final int TYPE_SELECTION_DRAG = 14;

    /**
     * User modified an existing selection.
     */
    public static final int TYPE_SELECTION_MODIFIED = 2;

    /**
     * User reset the smart selection.
     */
    public static final int TYPE_SELECTION_RESET = 18;

    /**
     * User started a new selection.
     */
    public static final int TYPE_SELECTION_STARTED = 1;

    /**
     * User clicked on Select All action
     */
    public static final int TYPE_SELECT_ALL = 17;

    /**
     * User clicked on Share action.
     */
    public static final int TYPE_SHARE_ACTION = 12;

    /**
     * User clicked on a Smart action.
     */
    public static final int TYPE_SMART_ACTION = 13;

    /**
     * Smart selection triggered spanning multiple tokens (words).
     */
    public static final int TYPE_SMART_SELECTION_MULTI = 4;

    /**
     * Smart selection triggered for a single token (word).
     */
    public static final int TYPE_SMART_SELECTION_SINGLE = 3;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private TextClassifierEvent() {
    }
}
