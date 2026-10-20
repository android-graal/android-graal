package android.content.res;

import android.os.Parcelable;

/**
 * This class describes all device configuration information that can
 * impact the resources the application retrieves.  This includes both
 * user-specified configuration options (locale list and scaling) as well
 * as device configurations (such as input modes, screen size and screen orientation).
 * <p>You can acquire this object from {@link Resources}, using {@link
 * Resources#getConfiguration}. Thus, from an activity, you can get it by chaining the request
 * with {@link android.app.Activity#getResources}:</p>
 * <pre>Configuration config = getResources().getConfiguration();</pre>
 */
public final class Configuration implements Parcelable {
    /**
     * Constant for {@link #colorMode}: bits that encode the dynamic range of the screen.
     */
    public static final int COLOR_MODE_HDR_MASK = 12;

    /**
     * Constant for {@link #colorMode}: a {@link #COLOR_MODE_HDR_MASK} value
     * indicating that the screen is not HDR (low/standard dynamic range).
     * <p>Corresponds to the <code>-lowdr</code> resource qualifier.</p>
     */
    public static final int COLOR_MODE_HDR_NO = 4;

    /**
     * Constant for {@link #colorMode}: bits shift to get the screen dynamic range.
     */
    public static final int COLOR_MODE_HDR_SHIFT = 2;

    /**
     * Constant for {@link #colorMode}: a {@link #COLOR_MODE_HDR_MASK} value
     * indicating that it is unknown whether or not the screen is HDR.
     */
    public static final int COLOR_MODE_HDR_UNDEFINED = 0;

    /**
     * Constant for {@link #colorMode}: a {@link #COLOR_MODE_HDR_MASK} value
     * indicating that the screen is HDR (dynamic range).
     * <p>Corresponds to the <code>-highdr</code> resource qualifier.</p>
     */
    public static final int COLOR_MODE_HDR_YES = 8;

    /**
     * Constant for {@link #colorMode}: a value indicating that the color mode is undefined
     */
    public static final int COLOR_MODE_UNDEFINED = 0;

    /**
     * Constant for {@link #colorMode}: bits that encode whether the screen is wide gamut.
     */
    public static final int COLOR_MODE_WIDE_COLOR_GAMUT_MASK = 3;

    /**
     * Constant for {@link #colorMode}: a {@link #COLOR_MODE_WIDE_COLOR_GAMUT_MASK} value
     * indicating that the screen is not wide gamut.
     * <p>Corresponds to the <code>-nowidecg</code> resource qualifier.</p>
     */
    public static final int COLOR_MODE_WIDE_COLOR_GAMUT_NO = 1;

    /**
     * Constant for {@link #colorMode}: a {@link #COLOR_MODE_WIDE_COLOR_GAMUT_MASK} value
     * indicating that it is unknown whether or not the screen is wide gamut.
     */
    public static final int COLOR_MODE_WIDE_COLOR_GAMUT_UNDEFINED = 0;

    /**
     * Constant for {@link #colorMode}: a {@link #COLOR_MODE_WIDE_COLOR_GAMUT_MASK} value
     * indicating that the screen is wide gamut.
     * <p>Corresponds to the <code>-widecg</code> resource qualifier.</p>
     */
    public static final int COLOR_MODE_WIDE_COLOR_GAMUT_YES = 2;

    /**
     * Default value for {@link #densityDpi} indicating that no width
     * has been specified.
     */
    public static final int DENSITY_DPI_UNDEFINED = 0;

    /**
     * An undefined fontWeightAdjustment.
     */
    public static final int FONT_WEIGHT_ADJUSTMENT_UNDEFINED = 2147483647;

    /**
     * Constant for grammatical gender: to indicate the terms of address the user
     * preferred in an application is feminine.
     */
    public static final int GRAMMATICAL_GENDER_FEMININE = 2;

    /**
     * Constant for grammatical gender: to indicate the terms of address the user
     * preferred in an application is masculine.
     */
    public static final int GRAMMATICAL_GENDER_MASCULINE = 3;

    /**
     * Constant for grammatical gender: to indicate the terms of address the user
     * preferred in an application is neuter.
     */
    public static final int GRAMMATICAL_GENDER_NEUTRAL = 1;

    /**
     * Constant for grammatical gender: to indicate the user has not specified the terms
     * of address for the application.
     */
    public static final int GRAMMATICAL_GENDER_NOT_SPECIFIED = 0;

    /**
     * Constant for {@link #hardKeyboardHidden}, value corresponding to the
     *  physical keyboard being exposed.
     */
    public static final int HARDKEYBOARDHIDDEN_NO = 1;

    /**
     * Constant for {@link #hardKeyboardHidden}: a value indicating that no value has been set.
     */
    public static final int HARDKEYBOARDHIDDEN_UNDEFINED = 0;

    /**
     * Constant for {@link #hardKeyboardHidden}, value corresponding to the
     *  physical keyboard being hidden.
     */
    public static final int HARDKEYBOARDHIDDEN_YES = 2;

    /**
     * Constant for {@link #keyboardHidden}, value corresponding to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#KeyboardAvailQualifier">keysexposed</a>
     *  resource qualifier.
     */
    public static final int KEYBOARDHIDDEN_NO = 1;

    /**
     * Constant for {@link #keyboardHidden}: a value indicating that no value has been set.
     */
    public static final int KEYBOARDHIDDEN_UNDEFINED = 0;

    /**
     * Constant for {@link #keyboardHidden}, value corresponding to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#KeyboardAvailQualifier">keyshidden</a>
     *  resource qualifier.
     */
    public static final int KEYBOARDHIDDEN_YES = 2;

    /**
     * Constant for {@link #keyboard}, value corresponding to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#ImeQualifier">12key</a>
     *  resource qualifier.
     */
    public static final int KEYBOARD_12KEY = 3;

    /**
     * Constant for {@link #keyboard}, value corresponding to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#ImeQualifier">nokeys</a>
     *  resource qualifier.
     */
    public static final int KEYBOARD_NOKEYS = 1;

    /**
     * Constant for {@link #keyboard}, value corresponding to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#ImeQualifier">qwerty</a>
     *  resource qualifier.
     */
    public static final int KEYBOARD_QWERTY = 2;

    /**
     * Constant for {@link #keyboard}: a value indicating that no value has been set.
     */
    public static final int KEYBOARD_UNDEFINED = 0;

    /**
     * Constant used to to represent MNC (Mobile Network Code) zero.
     * 0 cannot be used, since it is used to represent an undefined MNC.
     */
    public static final int MNC_ZERO = 65535;

    /**
     * Constant for {@link #navigationHidden}, value corresponding to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#NavAvailQualifier">navexposed</a>
     *  resource qualifier.
     */
    public static final int NAVIGATIONHIDDEN_NO = 1;

    /**
     * Constant for {@link #navigationHidden}: a value indicating that no value has been set.
     */
    public static final int NAVIGATIONHIDDEN_UNDEFINED = 0;

    /**
     * Constant for {@link #navigationHidden}, value corresponding to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#NavAvailQualifier">navhidden</a>
     *  resource qualifier.
     */
    public static final int NAVIGATIONHIDDEN_YES = 2;

    /**
     * Constant for {@link #navigation}, value corresponding to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#NavigationQualifier">dpad</a>
     *  resource qualifier.
     */
    public static final int NAVIGATION_DPAD = 2;

    /**
     * Constant for {@link #navigation}, value corresponding to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#NavigationQualifier">nonav</a>
     *  resource qualifier.
     */
    public static final int NAVIGATION_NONAV = 1;

    /**
     * Constant for {@link #navigation}, value corresponding to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#NavigationQualifier">trackball</a>
     *  resource qualifier.
     */
    public static final int NAVIGATION_TRACKBALL = 3;

    /**
     * Constant for {@link #navigation}: a value indicating that no value has been set.
     */
    public static final int NAVIGATION_UNDEFINED = 0;

    /**
     * Constant for {@link #navigation}, value corresponding to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#NavigationQualifier">wheel</a>
     *  resource qualifier.
     */
    public static final int NAVIGATION_WHEEL = 4;

    /**
     * Constant for {@link #orientation}, value corresponding to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#OrientationQualifier">land</a>
     *  resource qualifier.
     */
    public static final int ORIENTATION_LANDSCAPE = 2;

    /**
     * Constant for {@link #orientation}, value corresponding to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#OrientationQualifier">port</a>
     *  resource qualifier.
     */
    public static final int ORIENTATION_PORTRAIT = 1;

    /**
     * @deprecated Not currently supported or used.
     */
    @Deprecated
    public static final int ORIENTATION_SQUARE = 3;

    /**
     * Constant for {@link #orientation}: a value indicating that no value has been set.
     */
    public static final int ORIENTATION_UNDEFINED = 0;

    /**
     * Constant for {@link #screenLayout}: a {@link #SCREENLAYOUT_LAYOUTDIR_MASK}
     *  value indicating that a layout dir has been set to LTR.
     */
    public static final int SCREENLAYOUT_LAYOUTDIR_LTR = 64;

    /**
     * Constant for {@link #screenLayout}: bits that encode the layout direction.
     */
    public static final int SCREENLAYOUT_LAYOUTDIR_MASK = 192;

    /**
     * Constant for {@link #screenLayout}: a {@link #SCREENLAYOUT_LAYOUTDIR_MASK}
     *  value indicating that a layout dir has been set to RTL.
     */
    public static final int SCREENLAYOUT_LAYOUTDIR_RTL = 128;

    /**
     * Constant for {@link #screenLayout}: bits shift to get the layout direction.
     */
    public static final int SCREENLAYOUT_LAYOUTDIR_SHIFT = 6;

    /**
     * Constant for {@link #screenLayout}: a {@link #SCREENLAYOUT_LAYOUTDIR_MASK}
     *  value indicating that no layout dir has been set.
     */
    public static final int SCREENLAYOUT_LAYOUTDIR_UNDEFINED = 0;

    /**
     * Constant for {@link #screenLayout}: bits that encode the aspect ratio.
     */
    public static final int SCREENLAYOUT_LONG_MASK = 48;

    /**
     * Constant for {@link #screenLayout}: a {@link #SCREENLAYOUT_LONG_MASK}
     *  value that corresponds to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#ScreenAspectQualifier">notlong</a>
     *  resource qualifier.
     */
    public static final int SCREENLAYOUT_LONG_NO = 16;

    /**
     * Constant for {@link #screenLayout}: a {@link #SCREENLAYOUT_LONG_MASK}
     *  value indicating that no size has been set.
     */
    public static final int SCREENLAYOUT_LONG_UNDEFINED = 0;

    /**
     * Constant for {@link #screenLayout}: a {@link #SCREENLAYOUT_LONG_MASK}
     *  value that corresponds to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#ScreenAspectQualifier">long</a>
     *  resource qualifier.
     */
    public static final int SCREENLAYOUT_LONG_YES = 32;

    /**
     * Constant for {@link #screenLayout}: bits that encode roundness of the screen.
     */
    public static final int SCREENLAYOUT_ROUND_MASK = 768;

    /**
     * Constant for {@link #screenLayout}: a {@link #SCREENLAYOUT_ROUND_MASK} value indicating
     * that the screen does not have a rounded shape.
     */
    public static final int SCREENLAYOUT_ROUND_NO = 256;

    /**
     * Constant for {@link #screenLayout}: a {@link #SCREENLAYOUT_ROUND_MASK} value indicating
     * that it is unknown whether or not the screen has a round shape.
     */
    public static final int SCREENLAYOUT_ROUND_UNDEFINED = 0;

    /**
     * Constant for {@link #screenLayout}: a {@link #SCREENLAYOUT_ROUND_MASK} value indicating
     * that the screen has a rounded shape. Corners may not be visible to the user;
     * developers should pay special attention to the {@link android.view.WindowInsets} delivered
     * to views for more information about ensuring content is not obscured.
     *
     * <p>Corresponds to the <code>-round</code> resource qualifier.</p>
     */
    public static final int SCREENLAYOUT_ROUND_YES = 512;

    /**
     * Constant for {@link #screenLayout}: a {@link #SCREENLAYOUT_SIZE_MASK}
     *  value indicating the screen is at least approximately 480x640 dp units,
     *  corresponds to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#ScreenSizeQualifier">large</a>
     *  resource qualifier.
     *  See <a href="{@docRoot}guide/practices/screens_support.html">Supporting
     *  Multiple Screens</a> for more information.
     */
    public static final int SCREENLAYOUT_SIZE_LARGE = 3;

    /**
     * Constant for {@link #screenLayout}: bits that encode the size.
     */
    public static final int SCREENLAYOUT_SIZE_MASK = 15;

    /**
     * Constant for {@link #screenLayout}: a {@link #SCREENLAYOUT_SIZE_MASK}
     *  value indicating the screen is at least approximately 320x470 dp units,
     *  corresponds to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#ScreenSizeQualifier">normal</a>
     *  resource qualifier.
     *  See <a href="{@docRoot}guide/practices/screens_support.html">Supporting
     *  Multiple Screens</a> for more information.
     */
    public static final int SCREENLAYOUT_SIZE_NORMAL = 2;

    /**
     * Constant for {@link #screenLayout}: a {@link #SCREENLAYOUT_SIZE_MASK}
     *  value indicating the screen is at least approximately 320x426 dp units,
     *  corresponds to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#ScreenSizeQualifier">small</a>
     *  resource qualifier.
     *  See <a href="{@docRoot}guide/practices/screens_support.html">Supporting
     *  Multiple Screens</a> for more information.
     */
    public static final int SCREENLAYOUT_SIZE_SMALL = 1;

    /**
     * Constant for {@link #screenLayout}: a {@link #SCREENLAYOUT_SIZE_MASK}
     *  value indicating that no size has been set.
     */
    public static final int SCREENLAYOUT_SIZE_UNDEFINED = 0;

    /**
     * Constant for {@link #screenLayout}: a {@link #SCREENLAYOUT_SIZE_MASK}
     *  value indicating the screen is at least approximately 720x960 dp units,
     *  corresponds to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#ScreenSizeQualifier">xlarge</a>
     *  resource qualifier.
     *  See <a href="{@docRoot}guide/practices/screens_support.html">Supporting
     *  Multiple Screens</a> for more information.
     */
    public static final int SCREENLAYOUT_SIZE_XLARGE = 4;

    /**
     * Constant for {@link #screenLayout}: a value indicating that screenLayout is undefined
     */
    public static final int SCREENLAYOUT_UNDEFINED = 0;

    /**
     * Default value for {@link #screenHeightDp} indicating that no width
     * has been specified.
     */
    public static final int SCREEN_HEIGHT_DP_UNDEFINED = 0;

    /**
     * Default value for {@link #screenWidthDp} indicating that no width
     * has been specified.
     */
    public static final int SCREEN_WIDTH_DP_UNDEFINED = 0;

    /**
     * Default value for {@link #smallestScreenWidthDp} indicating that no width
     * has been specified.
     */
    public static final int SMALLEST_SCREEN_WIDTH_DP_UNDEFINED = 0;

    /**
     * Constant for {@link #touchscreen}, value corresponding to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#TouchscreenQualifier">finger</a>
     *  resource qualifier.
     */
    public static final int TOUCHSCREEN_FINGER = 3;

    /**
     * Constant for {@link #touchscreen}, value corresponding to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#TouchscreenQualifier">notouch</a>
     *  resource qualifier.
     */
    public static final int TOUCHSCREEN_NOTOUCH = 1;

    /**
     * @deprecated Not currently supported or used.
     */
    @Deprecated
    public static final int TOUCHSCREEN_STYLUS = 2;

    /**
     * Constant for {@link #touchscreen}: a value indicating that no value has been set.
     */
    public static final int TOUCHSCREEN_UNDEFINED = 0;

    /**
     * Constant for {@link #uiMode}: bits that encode the night mode.
     */
    public static final int UI_MODE_NIGHT_MASK = 48;

    /**
     * Constant for {@link #uiMode}: a {@link #UI_MODE_NIGHT_MASK}
     *  value that corresponds to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#NightQualifier">notnight</a>
     *  resource qualifier.
     */
    public static final int UI_MODE_NIGHT_NO = 16;

    /**
     * Constant for {@link #uiMode}: a {@link #UI_MODE_NIGHT_MASK}
     *  value indicating that no mode type has been set.
     */
    public static final int UI_MODE_NIGHT_UNDEFINED = 0;

    /**
     * Constant for {@link #uiMode}: a {@link #UI_MODE_NIGHT_MASK}
     *  value that corresponds to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#NightQualifier">night</a>
     *  resource qualifier.
     */
    public static final int UI_MODE_NIGHT_YES = 32;

    /**
     * Constant for {@link #uiMode}: a {@link #UI_MODE_TYPE_MASK}
     *  value that corresponds to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#UiModeQualifier">appliance</a>
     *  resource qualifier.
     */
    public static final int UI_MODE_TYPE_APPLIANCE = 5;

    /**
     * Constant for {@link #uiMode}: a {@link #UI_MODE_TYPE_MASK}
     *  value that corresponds to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#UiModeQualifier">car</a>
     *  resource qualifier.
     */
    public static final int UI_MODE_TYPE_CAR = 3;

    /**
     * Constant for {@link #uiMode}: a {@link #UI_MODE_TYPE_MASK}
     *  value that corresponds to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#UiModeQualifier">desk</a>
     *  resource qualifier.
     */
    public static final int UI_MODE_TYPE_DESK = 2;

    /**
     * Constant for {@link #uiMode}: bits that encode the mode type.
     */
    public static final int UI_MODE_TYPE_MASK = 15;

    /**
     * Constant for {@link #uiMode}: a {@link #UI_MODE_TYPE_MASK}
     *  value that corresponds to
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#UiModeQualifier">no
     *  UI mode</a> resource qualifier specified.
     */
    public static final int UI_MODE_TYPE_NORMAL = 1;

    /**
     * Constant for {@link #uiMode}: a {@link #UI_MODE_TYPE_MASK}
     *  value that corresponds to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#UiModeQualifier">television</a>
     *  resource qualifier.
     */
    public static final int UI_MODE_TYPE_TELEVISION = 4;

    /**
     * Constant for {@link #uiMode}: a {@link #UI_MODE_TYPE_MASK}
     *  value indicating that no mode type has been set.
     */
    public static final int UI_MODE_TYPE_UNDEFINED = 0;

    /**
     * Constant for {@link #uiMode}: a {@link #UI_MODE_TYPE_MASK}
     *  value that corresponds to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#UiModeQualifier">vrheadset</a>
     *  resource qualifier.
     */
    public static final int UI_MODE_TYPE_VR_HEADSET = 7;

    /**
     * Constant for {@link #uiMode}: a {@link #UI_MODE_TYPE_MASK}
     *  value that corresponds to the
     *  <a href="{@docRoot}guide/topics/resources/providing-resources.html#UiModeQualifier">watch</a>
     *  resource qualifier.
     */
    public static final int UI_MODE_TYPE_WATCH = 6;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private Configuration() {
    }
}
