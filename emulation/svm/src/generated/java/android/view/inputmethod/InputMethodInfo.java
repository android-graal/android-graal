package android.view.inputmethod;

import android.os.Parcelable;

/**
 * This class is used to specify meta information of an input method.
 *
 * <p>It should be defined in an XML resource file with an {@code <input-method>} element.
 * For more information, see the guide to
 * <a href="{@docRoot}guide/topics/text/creating-input-method.html">
 * Creating an Input Method</a>.</p>
 *
 * @see InputMethodSubtype
 *
 * @attr ref android.R.styleable#InputMethod_settingsActivity
 * @attr ref android.R.styleable#InputMethod_isDefault
 * @attr ref android.R.styleable#InputMethod_supportsSwitchingToNextInputMethod
 * @attr ref android.R.styleable#InputMethod_supportsInlineSuggestions
 * @attr ref android.R.styleable#InputMethod_supportsInlineSuggestionsWithTouchExploration
 * @attr ref android.R.styleable#InputMethod_suppressesSpellChecker
 * @attr ref android.R.styleable#InputMethod_showInInputMethodPicker
 * @attr ref android.R.styleable#InputMethod_configChanges
 */
public final class InputMethodInfo implements Parcelable {
    /**
     * {@link Intent#getAction() Intent action} for the IME language settings.
     *
     * @see #createImeLanguageSettingsActivityIntent()
     */
    public static final String ACTION_IME_LANGUAGE_SETTINGS = "android.view.inputmethod.action.IME_LANGUAGE_SETTINGS";

    /**
     * {@link Intent#getAction() Intent action} for IME that
     * {@link #supportsStylusHandwriting() supports stylus handwriting}.
     *
     * @see #createStylusHandwritingSettingsActivityIntent()
     */
    public static final String ACTION_STYLUS_HANDWRITING_SETTINGS = "android.view.inputmethod.action.STYLUS_HANDWRITING_SETTINGS";

    private InputMethodInfo() {
    }
}
