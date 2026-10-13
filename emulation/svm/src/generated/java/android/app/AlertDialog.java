package android.app;

import android.content.DialogInterface;

/**
 * A subclass of Dialog that can display one, two or three buttons. If you only want to
 * display a String in this dialog box, use the setMessage() method.  If you
 * want to display a more complex view, look up the FrameLayout called "custom"
 * and add your view to it:
 *
 * <pre>
 * FrameLayout fl = findViewById(android.R.id.custom);
 * fl.addView(myView, new LayoutParams(MATCH_PARENT, WRAP_CONTENT));
 * </pre>
 *
 * <p>The AlertDialog class takes care of automatically setting
 * {@link android.view.WindowManager.LayoutParams#FLAG_ALT_FOCUSABLE_IM
 * WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM} for you based on whether
 * any views in the dialog return true from {@link View#onCheckIsTextEditor()
 * View.onCheckIsTextEditor()}.  Generally you want this set for a Dialog
 * without text editors, so that it will be placed on top of the current
 * input method UI.  You can modify this behavior by forcing the flag to your
 * desired mode after calling {@link #onCreate}.
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about creating dialogs, read the
 * <a href="{@docRoot}guide/topics/ui/dialogs.html">Dialogs</a> developer guide.</p>
 * </div>
 */
public final class AlertDialog implements DialogInterface {
    /**
     * Special theme constant for {@link #AlertDialog(Context, int)}: use
     * the device's default alert theme with a dark background.
     *
     * @deprecated Use {@link android.R.style#Theme_DeviceDefault_Dialog_Alert}.
     */
    @Deprecated
    public static final int THEME_DEVICE_DEFAULT_DARK = 4;

    /**
     * Special theme constant for {@link #AlertDialog(Context, int)}: use
     * the device's default alert theme with a light background.
     *
     * @deprecated Use {@link android.R.style#Theme_DeviceDefault_Light_Dialog_Alert}.
     */
    @Deprecated
    public static final int THEME_DEVICE_DEFAULT_LIGHT = 5;

    /**
     * Special theme constant for {@link #AlertDialog(Context, int)}: use
     * the holographic alert theme with a dark background.
     *
     * @deprecated Use {@link android.R.style#Theme_Material_Dialog_Alert}.
     */
    @Deprecated
    public static final int THEME_HOLO_DARK = 2;

    /**
     * Special theme constant for {@link #AlertDialog(Context, int)}: use
     * the holographic alert theme with a light background.
     *
     * @deprecated Use {@link android.R.style#Theme_Material_Light_Dialog_Alert}.
     */
    @Deprecated
    public static final int THEME_HOLO_LIGHT = 3;

    /**
     * Special theme constant for {@link #AlertDialog(Context, int)}: use
     * the traditional (pre-Holo) alert dialog theme.
     *
     * @deprecated Use {@link android.R.style#Theme_Material_Dialog_Alert}.
     */
    @Deprecated
    public static final int THEME_TRADITIONAL = 1;

    private AlertDialog() {
    }
}
