package android.app;

/**
 * A fragment that displays a dialog window, floating on top of its
 * activity's window.  This fragment contains a Dialog object, which it
 * displays as appropriate based on the fragment's state.  Control of
 * the dialog (deciding when to show, hide, dismiss it) should be done through
 * the API here, not with direct calls on the dialog.
 *
 * <p>Implementations should override this class and implement
 * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} to supply the
 * content of the dialog.  Alternatively, they can override
 * {@link #onCreateDialog(Bundle)} to create an entirely custom dialog, such
 * as an AlertDialog, with its own content.
 *
 * <p>Topics covered here:
 * <ol>
 * <li><a href="#Lifecycle">Lifecycle</a>
 * <li><a href="#BasicDialog">Basic Dialog</a>
 * <li><a href="#AlertDialog">Alert Dialog</a>
 * <li><a href="#DialogOrEmbed">Selecting Between Dialog or Embedding</a>
 * </ol>
 *
 * <a name="Lifecycle"></a>
 * <h3>Lifecycle</h3>
 *
 * <p>DialogFragment does various things to keep the fragment's lifecycle
 * driving it, instead of the Dialog.  Note that dialogs are generally
 * autonomous entities -- they are their own window, receiving their own
 * input events, and often deciding on their own when to disappear (by
 * receiving a back key event or the user clicking on a button).
 *
 * <p>DialogFragment needs to ensure that what is happening with the Fragment
 * and Dialog states remains consistent.  To do this, it watches for dismiss
 * events from the dialog and takes care of removing its own state when they
 * happen.  This means you should use {@link #show(FragmentManager, String)}
 * or {@link #show(FragmentTransaction, String)} to add an instance of
 * DialogFragment to your UI, as these keep track of how DialogFragment should
 * remove itself when the dialog is dismissed.
 *
 * <a name="BasicDialog"></a>
 * <h3>Basic Dialog</h3>
 *
 * <p>The simplest use of DialogFragment is as a floating container for the
 * fragment's view hierarchy.  A simple implementation may look like this:
 *
 * {@sample development/samples/ApiDemos/src/com/example/android/apis/app/FragmentDialog.java
 *      dialog}
 *
 * <p>An example showDialog() method on the Activity could be:
 *
 * {@sample development/samples/ApiDemos/src/com/example/android/apis/app/FragmentDialog.java
 *      add_dialog}
 *
 * <p>This removes any currently shown dialog, creates a new DialogFragment
 * with an argument, and shows it as a new state on the back stack.  When the
 * transaction is popped, the current DialogFragment and its Dialog will be
 * destroyed, and the previous one (if any) re-shown.  Note that in this case
 * DialogFragment will take care of popping the transaction of the Dialog
 * is dismissed separately from it.
 *
 * <a name="AlertDialog"></a>
 * <h3>Alert Dialog</h3>
 *
 * <p>Instead of (or in addition to) implementing {@link #onCreateView} to
 * generate the view hierarchy inside of a dialog, you may implement
 * {@link #onCreateDialog(Bundle)} to create your own custom Dialog object.
 *
 * <p>This is most useful for creating an {@link AlertDialog}, allowing you
 * to display standard alerts to the user that are managed by a fragment.
 * A simple example implementation of this is:
 *
 * {@sample development/samples/ApiDemos/src/com/example/android/apis/app/FragmentAlertDialog.java
 *      dialog}
 *
 * <p>The activity creating this fragment may have the following methods to
 * show the dialog and receive results from it:
 *
 * {@sample development/samples/ApiDemos/src/com/example/android/apis/app/FragmentAlertDialog.java
 *      activity}
 *
 * <p>Note that in this case the fragment is not placed on the back stack, it
 * is just added as an indefinitely running fragment.  Because dialogs normally
 * are modal, this will still operate as a back stack, since the dialog will
 * capture user input until it is dismissed.  When it is dismissed, DialogFragment
 * will take care of removing itself from its fragment manager.
 *
 * <a name="DialogOrEmbed"></a>
 * <h3>Selecting Between Dialog or Embedding</h3>
 *
 * <p>A DialogFragment can still optionally be used as a normal fragment, if
 * desired.  This is useful if you have a fragment that in some cases should
 * be shown as a dialog and others embedded in a larger UI.  This behavior
 * will normally be automatically selected for you based on how you are using
 * the fragment, but can be customized with {@link #setShowsDialog(boolean)}.
 *
 * <p>For example, here is a simple dialog fragment:
 *
 * {@sample development/samples/ApiDemos/src/com/example/android/apis/app/FragmentDialogOrActivity.java
 *      dialog}
 *
 * <p>An instance of this fragment can be created and shown as a dialog:
 *
 * {@sample development/samples/ApiDemos/src/com/example/android/apis/app/FragmentDialogOrActivity.java
 *      show_dialog}
 *
 * <p>It can also be added as content in a view hierarchy:
 *
 * {@sample development/samples/ApiDemos/src/com/example/android/apis/app/FragmentDialogOrActivity.java
 *      embed}
 *
 * @deprecated Use the <a href="{@docRoot}tools/extras/support-library.html">Support Library</a>
 *      {@link androidx.fragment.app.DialogFragment} for consistent behavior across all devices
 *      and access to <a href="{@docRoot}topic/libraries/architecture/lifecycle.html">Lifecycle</a>.
 */
@Deprecated
public final class DialogFragment {
    /**
     * Style for {@link #setStyle(int, int)}: a basic,
     * normal dialog.
     */
    @Deprecated
    public static final int STYLE_NORMAL = 0;

    /**
     * Style for {@link #setStyle(int, int)}: don't draw
     * any frame at all; the view hierarchy returned by {@link #onCreateView}
     * is entirely responsible for drawing the dialog.
     */
    @Deprecated
    public static final int STYLE_NO_FRAME = 2;

    /**
     * Style for {@link #setStyle(int, int)}: like
     * {@link #STYLE_NO_FRAME}, but also disables all input to the dialog.
     * The user can not touch it, and its window will not receive input focus.
     */
    @Deprecated
    public static final int STYLE_NO_INPUT = 3;

    /**
     * Style for {@link #setStyle(int, int)}: don't include
     * a title area.
     */
    @Deprecated
    public static final int STYLE_NO_TITLE = 1;

    private DialogFragment() {
    }
}
