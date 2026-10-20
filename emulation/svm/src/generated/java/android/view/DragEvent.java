package android.view;

import android.os.Parcelable;

/**
 * Represents an event that is sent out by the system at various times during a drag and drop
 * operation. It is a data structure that contains several important pieces of data about
 * the operation and the underlying data.
 * <p>
 *  View objects that receive a DragEvent call {@link #getAction()}, which returns
 *  an action type that indicates the state of the drag and drop operation. This allows a View
 *  object to react to a change in state by changing its appearance or performing other actions.
 *  For example, a View can react to the {@link #ACTION_DRAG_ENTERED} action type by
 *  by changing one or more colors in its displayed image.
 * </p>
 * <p>
 *  During a drag and drop operation, the system displays an image that the user drags. This image
 *  is called a drag shadow. Several action types reflect the position of the drag shadow relative
 *  to the View receiving the event.
 * </p>
 * <p>
 *  Most methods return valid data only for certain event actions. This is summarized in the
 *  following table. Each possible {@link #getAction()} value is listed in the first column. The
 *  other columns indicate which method or methods return valid data for that getAction() value:
 * </p>
 * <table>
 *  <tr>
 *      <th scope="col">getAction() Value</th>
 *      <th scope="col">getClipDescription()</th>
 *      <th scope="col">getLocalState()</th>
 *      <th scope="col">getX()</th>
 *      <th scope="col">getY()</th>
 *      <th scope="col">getClipData()</th>
 *      <th scope="col">getResult()</th>
 *  </tr>
 *  <tr>
 *      <td>ACTION_DRAG_STARTED</td>
 *      <td style="text-align: center;">X</td>
 *      <td style="text-align: center;">X</td>
 *      <td style="text-align: center;">X</td>
 *      <td style="text-align: center;">X</td>
 *      <td style="text-align: center;">&nbsp;</td>
 *      <td style="text-align: center;">&nbsp;</td>
 *  </tr>
 *  <tr>
 *      <td>ACTION_DRAG_ENTERED</td>
 *      <td style="text-align: center;">X</td>
 *      <td style="text-align: center;">X</td>
 *      <td style="text-align: center;">&nbsp;</td>
 *      <td style="text-align: center;">&nbsp;</td>
 *      <td style="text-align: center;">&nbsp;</td>
 *      <td style="text-align: center;">&nbsp;</td>
 *  </tr>
 *  <tr>
 *      <td>ACTION_DRAG_LOCATION</td>
 *      <td style="text-align: center;">X</td>
 *      <td style="text-align: center;">X</td>
 *      <td style="text-align: center;">X</td>
 *      <td style="text-align: center;">X</td>
 *      <td style="text-align: center;">&nbsp;</td>
 *      <td style="text-align: center;">&nbsp;</td>
 *  </tr>
 *  <tr>
 *      <td>ACTION_DRAG_EXITED</td>
 *      <td style="text-align: center;">X</td>
 *      <td style="text-align: center;">X</td>
 *      <td style="text-align: center;">&nbsp;</td>
 *      <td style="text-align: center;">&nbsp;</td>
 *      <td style="text-align: center;">&nbsp;</td>
 *      <td style="text-align: center;">&nbsp;</td>
 *  </tr>
 *  <tr>
 *      <td>ACTION_DROP</td>
 *      <td style="text-align: center;">X</td>
 *      <td style="text-align: center;">X</td>
 *      <td style="text-align: center;">X</td>
 *      <td style="text-align: center;">X</td>
 *      <td style="text-align: center;">X</td>
 *      <td style="text-align: center;">&nbsp;</td>
 *  </tr>
 *  <tr>
 *      <td>ACTION_DRAG_ENDED</td>
 *      <td style="text-align: center;">&nbsp;</td>
 *      <td style="text-align: center;">X</td>
 *      <td style="text-align: center;">&nbsp;</td>
 *      <td style="text-align: center;">&nbsp;</td>
 *      <td style="text-align: center;">&nbsp;</td>
 *      <td style="text-align: center;">X</td>
 *  </tr>
 * </table>
 * <p>
 *  The {@link android.view.DragEvent#getAction()},
 *  {@link android.view.DragEvent#getLocalState()}
 *  {@link android.view.DragEvent#describeContents()},
 *  {@link android.view.DragEvent#writeToParcel(Parcel,int)}, and
 *  {@link android.view.DragEvent#toString()} methods always return valid data.
 * </p>
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For a guide to implementing drag and drop features, read the
 * <a href="{@docRoot}guide/topics/ui/drag-drop.html">Drag and Drop</a> developer guide.</p>
 * </div>
 */
public final class DragEvent implements Parcelable {
    /**
     * Action constant returned by {@link #getAction()}:  Signals to a View that the drag and drop
     * operation has concluded.  A View that changed its appearance during the operation should
     * return to its usual drawing state in response to this event.
     * <p>
     *  All views with listeners that returned boolean <code>true</code> for the ACTION_DRAG_STARTED
     *  event will receive the ACTION_DRAG_ENDED event even if they are not currently visible when
     *  the drag ends. Views removed during the drag operation won't receive the ACTION_DRAG_ENDED
     *  event.
     * </p>
     * <p>
     *  The View object can call {@link #getResult()} to see the result of the operation.
     *  If a View returned {@code true} in response to {@link #ACTION_DROP}, then
     *  getResult() returns {@code true}, otherwise it returns {@code false}.
     * </p>
     * @see #ACTION_DRAG_STARTED
     * @see #getResult()
     */
    public static final int ACTION_DRAG_ENDED = 4;

    /**
     * Action constant returned by {@link #getAction()}: Signals to a View that the drag point has
     * entered the bounding box of the View.
     * <p>
     *  If the View can accept a drop, it can react to ACTION_DRAG_ENTERED
     *  by changing its appearance in a way that tells the user that the View is the current
     *  drop target.
     * </p>
     * The system stops sending ACTION_DRAG_LOCATION events to a View once the user moves the
     * drag shadow out of the View object's bounding box or into a descendant view that can accept
     * the data. If the user moves the drag shadow back into the View object's bounding box or out
     * of a descendant view that can accept the data, the View receives an ACTION_DRAG_ENTERED again
     * before receiving any more ACTION_DRAG_LOCATION events.
     * </p>
     * @see #ACTION_DRAG_ENTERED
     * @see #ACTION_DRAG_LOCATION
     */
    public static final int ACTION_DRAG_ENTERED = 5;

    /**
     * Action constant returned by {@link #getAction()}: Signals that the user has moved the
     * drag shadow out of the bounding box of the View or into a descendant view that can accept
     * the data.
     * The View can react by changing its appearance in a way that tells the user that
     * View is no longer the immediate drop target.
     * <p>
     *  After the system sends an ACTION_DRAG_EXITED event to the View, the View receives no more
     *  ACTION_DRAG_LOCATION events until the user drags the drag shadow back over the View.
     * </p>
     */
    public static final int ACTION_DRAG_EXITED = 6;

    /**
     * Action constant returned by {@link #getAction()}: Sent to a View after
     * {@link #ACTION_DRAG_ENTERED} while the drag shadow is still within the View object's bounding
     * box, but not within a descendant view that can accept the data. The {@link #getX()} and
     * {@link #getY()} methods supply
     * the X and Y position of the drag point within the View object's bounding box.
     * <p>
     * A View receives an {@link #ACTION_DRAG_ENTERED} event before receiving any
     * ACTION_DRAG_LOCATION events.
     * </p>
     * <p>
     * The system stops sending ACTION_DRAG_LOCATION events to a View once the user moves the
     * drag shadow out of the View object's bounding box or into a descendant view that can accept
     * the data. If the user moves the drag shadow back into the View object's bounding box or out
     * of a descendant view that can accept the data, the View receives an ACTION_DRAG_ENTERED again
     * before receiving any more ACTION_DRAG_LOCATION events.
     * </p>
     * @see #ACTION_DRAG_ENTERED
     * @see #getX()
     * @see #getY()
     */
    public static final int ACTION_DRAG_LOCATION = 2;

    /**
     * Action constant returned by {@link #getAction()}: Signals the start of a
     * drag and drop operation. The View should return {@code true} from its
     * {@link View#onDragEvent(DragEvent) onDragEvent()} handler method or
     * {@link View.OnDragListener#onDrag(View,DragEvent) OnDragListener.onDrag()} listener
     * if it can accept a drop. The onDragEvent() or onDrag() methods usually inspect the metadata
     * from {@link #getClipDescription()} to determine if they can accept the data contained in
     * this drag. For an operation that doesn't represent data transfer, these methods may
     * perform other actions to determine whether or not the View should accept the data.
     * If the View wants to indicate that it is a valid drop target, it can also react by
     * changing its appearance.
     * <p>
     *  Views added or becoming visible for the first time during a drag operation receive this
     *  event when they are added or becoming visible.
     * </p>
     * <p>
     *  A View only receives further drag events for the drag operation if it returns {@code true}
     *  in response to ACTION_DRAG_STARTED.
     * </p>
     * @see #ACTION_DRAG_ENDED
     * @see #getX()
     * @see #getY()
     */
    public static final int ACTION_DRAG_STARTED = 1;

    /**
     * Action constant returned by {@link #getAction()}: Signals to a View that the user
     * has released the drag shadow, and the drag point is within the bounding box of the View and
     * not within a descendant view that can accept the data.
     * The View should retrieve the data from the DragEvent by calling {@link #getClipData()}.
     * The methods {@link #getX()} and {@link #getY()} return the X and Y position of the drop point
     * within the View object's bounding box.
     * <p>
     * The View should return {@code true} from its {@link View#onDragEvent(DragEvent)}
     * handler or {@link View.OnDragListener#onDrag(View,DragEvent) OnDragListener.onDrag()}
     * listener if it accepted the drop, and {@code false} if it ignored the drop.
     * </p>
     * <p>
     * The View can also react to this action by changing its appearance.
     * </p>
     * @see #getClipData()
     * @see #getX()
     * @see #getY()
     */
    public static final int ACTION_DROP = 3;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private DragEvent() {
    }
}
