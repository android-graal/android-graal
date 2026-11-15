// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.content.res;

import android.annotation.AnyRes;
import android.annotation.ColorInt;
import android.annotation.Nullable;
import android.annotation.StyleableRes;
import android.compat.annotation.UnsupportedAppUsage;
import android.os.Build;
import android.ravenwood.annotation.RavenwoodKeepWholeClass;
import android.ravenwood.annotation.RavenwoodThrow;
import android.util.AttributeSet;

import dalvik.system.VMRuntime;

import org.androidgraal.android.handles.TypedArrayBinding;
import org.androidgraal.hosted.annotate.Added;
import org.androidgraal.hosted.annotate.Emulated;
import org.androidgraal.shared.Proxy;

import java.util.Arrays;

/**
 * Container for an array of values that were retrieved with
 * {@link Resources.Theme#obtainStyledAttributes(AttributeSet, int[], int, int)}
 * or {@link Resources#obtainAttributes}.  Be
 * sure to call {@link #recycle} when done with them.
 *
 * The indices used to retrieve values from this structure correspond to
 * the positions of the attributes given to obtainStyledAttributes.
 */
@RavenwoodKeepWholeClass
@Emulated
public class TypedArray implements AutoCloseable, Proxy {
    private void resize(int len) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Returns the number of values in this array.
     *
     * @throws RuntimeException if the TypedArray has already been recycled.
     */
    public int length() {
        return TypedArrayBinding.length(this);
    }

    /**
     * Returns the number of indices in the array that actually have data. Attributes with a value
     * of @empty are included, as this is an explicit indicator.
     *
     * @throws RuntimeException if the TypedArray has already been recycled.
     */
    public int getIndexCount() {
        return TypedArrayBinding.getIndexCount(this);
    }

    /**
     * Returns an index in the array that has data. Attributes with a value of @empty are included,
     * as this is an explicit indicator.
     *
     * @param at The index you would like to returned, ranging from 0 to
     *           {@link #getIndexCount()}.
     *
     * @return The index at the given offset, which can be used with
     *         {@link #getValue} and related APIs.
     * @throws RuntimeException if the TypedArray has already been recycled.
     */
    public int getIndex(int at) {
        return TypedArrayBinding.getIndex(this, at);
    }

    /**
     * Retrieves the styled string value for the attribute at <var>index</var>.
     * <p>
     * If the attribute is not a string, this method will attempt to coerce
     * it to a string.
     *
     * @param index Index of attribute to retrieve.
     *
     * @return CharSequence holding string data. May be styled. Returns
     *         {@code null} if the attribute is not defined or could not be
     *         coerced to a string.
     * @throws RuntimeException if the TypedArray has already been recycled.
     */
    public CharSequence getText(@StyleableRes int index) {
        throw new UnsupportedOperationException("getText(I)Ljava/lang/CharSequence;");
    }

    /**
     * Retrieves the string value for the attribute at <var>index</var>.
     * <p>
     * If the attribute is not a string, this method will attempt to coerce
     * it to a string.
     *
     * @param index Index of attribute to retrieve.
     *
     * @return String holding string data. Any styling information is removed.
     *         Returns {@code null} if the attribute is not defined or could
     *         not be coerced to a string.
     * @throws RuntimeException if the TypedArray has already been recycled.
     */
    @Nullable
    public String getString(@StyleableRes int index) {
        return TypedArrayBinding.getString(this, index);
    }

    /**
     * Retrieves the string value for the attribute at <var>index</var>, but
     * only if that string comes from an immediate value in an XML file.  That
     * is, this does not allow references to string resources, string
     * attributes, or conversions from other types.  As such, this method
     * will only return strings for TypedArray objects that come from
     * attributes in an XML file.
     *
     * @param index Index of attribute to retrieve.
     *
     * @return String holding string data. Any styling information is removed.
     *         Returns {@code null} if the attribute is not defined or is not
     *         an immediate string value.
     * @throws RuntimeException if the TypedArray has already been recycled.
     */
    public String getNonResourceString(@StyleableRes int index) {
        return TypedArrayBinding.getNonResourceString(this, index);
    }

    /**
     * Retrieves the string value for the attribute at <var>index</var> that is
     * not allowed to change with the given configurations.
     *
     * @param index Index of attribute to retrieve.
     * @param allowedChangingConfigs Bit mask of configurations from
     *        {@link Configuration}.NATIVE_CONFIG_* that are allowed to change.
     *
     * @return String holding string data. Any styling information is removed.
     *         Returns {@code null} if the attribute is not defined.
     * @throws RuntimeException if the TypedArray has already been recycled.
     * @hide
     */
    @UnsupportedAppUsage
    public String getNonConfigurationString(@StyleableRes int index,
            int allowedChangingConfigs) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Retrieve the boolean value for the attribute at <var>index</var>.
     * <p>
     * If the attribute is an integer value, this method returns false if the
     * attribute is equal to zero, and true otherwise.
     * If the attribute is not a boolean or integer value,
     * this method will attempt to coerce it to an integer using
     * {@link Integer#decode(String)} and return whether it is equal to zero.
     *
     * @param index Index of attribute to retrieve.
     * @param defValue Value to return if the attribute is not defined or
     *                 cannot be coerced to an integer.
     *
     * @return Boolean value of the attribute, or defValue if the attribute was
     *         not defined or could not be coerced to an integer.
     * @throws RuntimeException if the TypedArray has already been recycled.
     */
    public boolean getBoolean(@StyleableRes int index, boolean defValue) {
        return TypedArrayBinding.getBoolean(this, index, defValue);
    }

    /**
     * Retrieve the integer value for the attribute at <var>index</var>.
     * <p>
     * If the attribute is not an integer, this method will attempt to coerce
     * it to an integer using {@link Integer#decode(String)}.
     *
     * @param index Index of attribute to retrieve.
     * @param defValue Value to return if the attribute is not defined or
     *                 cannot be coerced to an integer.
     *
     * @return Integer value of the attribute, or defValue if the attribute was
     *         not defined or could not be coerced to an integer.
     * @throws RuntimeException if the TypedArray has already been recycled.
     */
    public int getInt(@StyleableRes int index, int defValue) {
        return TypedArrayBinding.getInt(this, index, defValue);
    }

    /**
     * Retrieve the float value for the attribute at <var>index</var>.
     * <p>
     * If the attribute is not a float or an integer, this method will attempt
     * to coerce it to a float using {@link Float#parseFloat(String)}.
     *
     * @param index Index of attribute to retrieve.
     *
     * @return Attribute float value, or defValue if the attribute was
     *         not defined or could not be coerced to a float.
     * @throws RuntimeException if the TypedArray has already been recycled.
     */
    public float getFloat(@StyleableRes int index, float defValue) {
        return TypedArrayBinding.getFloat(this, index, defValue);
    }

    /**
     * Retrieve the color value for the attribute at <var>index</var>.  If
     * the attribute references a color resource holding a complex
     * {@link android.content.res.ColorStateList}, then the default color from
     * the set is returned.
     * <p>
     * This method will throw an exception if the attribute is defined but is
     * not an integer color or color state list.
     *
     * @param index Index of attribute to retrieve.
     * @param defValue Value to return if the attribute is not defined or
     *                 not a resource.
     *
     * @return Attribute color value, or defValue if not defined.
     * @throws RuntimeException if the TypedArray has already been recycled.
     * @throws UnsupportedOperationException if the attribute is defined but is
     *         not an integer color or color state list.
     */
    @ColorInt
    public int getColor(@StyleableRes int index, @ColorInt int defValue) {
        return TypedArrayBinding.getColor(this, index, defValue);
    }

    /**
     * Retrieve the integer value for the attribute at <var>index</var>.
     * <p>
     * Unlike {@link #getInt(int, int)}, this method will throw an exception if
     * the attribute is defined but is not an integer.
     *
     * @param index Index of attribute to retrieve.
     * @param defValue Value to return if the attribute is not defined or
     *                 not a resource.
     *
     * @return Attribute integer value, or defValue if not defined.
     * @throws RuntimeException if the TypedArray has already been recycled.
     * @throws UnsupportedOperationException if the attribute is defined but is
     *         not an integer.
     */
    public int getInteger(@StyleableRes int index, int defValue) {
        return TypedArrayBinding.getInteger(this, index, defValue);
    }

    /**
     * Retrieve a dimensional unit attribute at <var>index</var>. Unit
     * conversions are based on the current {@link DisplayMetrics}
     * associated with the resources this {@link TypedArray} object
     * came from.
     * <p>
     * This method will throw an exception if the attribute is defined but is
     * not a dimension.
     *
     * @param index Index of attribute to retrieve.
     * @param defValue Value to return if the attribute is not defined or
     *                 not a resource.
     *
     * @return Attribute dimension value multiplied by the appropriate
     *         metric, or defValue if not defined.
     * @throws RuntimeException if the TypedArray has already been recycled.
     * @throws UnsupportedOperationException if the attribute is defined but is
     *         not an integer.
     *
     * @see #getDimensionPixelOffset
     * @see #getDimensionPixelSize
     */
    public float getDimension(@StyleableRes int index, float defValue) {
        return TypedArrayBinding.getDimension(this, index, defValue);
    }

    /**
     * Retrieve a dimensional unit attribute at <var>index</var> for use
     * as an offset in raw pixels.  This is the same as
     * {@link #getDimension}, except the returned value is converted to
     * integer pixels for you.  An offset conversion involves simply
     * truncating the base value to an integer.
     * <p>
     * This method will throw an exception if the attribute is defined but is
     * not a dimension.
     *
     * @param index Index of attribute to retrieve.
     * @param defValue Value to return if the attribute is not defined or
     *                 not a resource.
     *
     * @return Attribute dimension value multiplied by the appropriate
     *         metric and truncated to integer pixels, or defValue if not defined.
     * @throws RuntimeException if the TypedArray has already been recycled.
     * @throws UnsupportedOperationException if the attribute is defined but is
     *         not an integer.
     *
     * @see #getDimension
     * @see #getDimensionPixelSize
     */
    public int getDimensionPixelOffset(@StyleableRes int index, int defValue) {
        return TypedArrayBinding.getDimensionPixelOffset(this, index, defValue);
    }

    /**
     * Retrieve a dimensional unit attribute at <var>index</var> for use
     * as a size in raw pixels.  This is the same as
     * {@link #getDimension}, except the returned value is converted to
     * integer pixels for use as a size.  A size conversion involves
     * rounding the base value, and ensuring that a non-zero base value
     * is at least one pixel in size.
     * <p>
     * This method will throw an exception if the attribute is defined but is
     * not a dimension.
     *
     * @param index Index of attribute to retrieve.
     * @param defValue Value to return if the attribute is not defined or
     *                 not a resource.
     *
     * @return Attribute dimension value multiplied by the appropriate
     *         metric and truncated to integer pixels, or defValue if not defined.
     * @throws RuntimeException if the TypedArray has already been recycled.
     * @throws UnsupportedOperationException if the attribute is defined but is
     *         not a dimension.
     *
     * @see #getDimension
     * @see #getDimensionPixelOffset
     */
    public int getDimensionPixelSize(@StyleableRes int index, int defValue) {
        return TypedArrayBinding.getDimensionPixelSize(this, index, defValue);
    }

    /**
     * Special version of {@link #getDimensionPixelSize} for retrieving
     * {@link android.view.ViewGroup}'s layout_width and layout_height
     * attributes.  This is only here for performance reasons; applications
     * should use {@link #getDimensionPixelSize}.
     * <p>
     * This method will throw an exception if the attribute is defined but is
     * not a dimension or integer (enum).
     *
     * @param index Index of the attribute to retrieve.
     * @param name Textual name of attribute for error reporting.
     *
     * @return Attribute dimension value multiplied by the appropriate
     *         metric and truncated to integer pixels.
     * @throws RuntimeException if the TypedArray has already been recycled.
     * @throws UnsupportedOperationException if the attribute is defined but is
     *         not a dimension or integer (enum).
     */
    public int getLayoutDimension(@StyleableRes int index, String name) {
        return TypedArrayBinding.getLayoutDimension$1(this, index, name);
    }

    /**
     * Special version of {@link #getDimensionPixelSize} for retrieving
     * {@link android.view.ViewGroup}'s layout_width and layout_height
     * attributes.  This is only here for performance reasons; applications
     * should use {@link #getDimensionPixelSize}.
     *
     * @param index Index of the attribute to retrieve.
     * @param defValue The default value to return if this attribute is not
     *                 default or contains the wrong type of data.
     *
     * @return Attribute dimension value multiplied by the appropriate
     *         metric and truncated to integer pixels.
     * @throws RuntimeException if the TypedArray has already been recycled.
     */
    public int getLayoutDimension(@StyleableRes int index, int defValue) {
        return TypedArrayBinding.getLayoutDimension$0(this, index, defValue);
    }

    /**
     * Retrieves a fractional unit attribute at <var>index</var>.
     *
     * @param index Index of attribute to retrieve.
     * @param base The base value of this fraction.  In other words, a
     *             standard fraction is multiplied by this value.
     * @param pbase The parent base value of this fraction.  In other
     *             words, a parent fraction (nn%p) is multiplied by this
     *             value.
     * @param defValue Value to return if the attribute is not defined or
     *                 not a resource.
     *
     * @return Attribute fractional value multiplied by the appropriate
     *         base value, or defValue if not defined.
     * @throws RuntimeException if the TypedArray has already been recycled.
     * @throws UnsupportedOperationException if the attribute is defined but is
     *         not a fraction.
     */
    public float getFraction(@StyleableRes int index, int base, int pbase, float defValue) {
        return TypedArrayBinding.getFraction(this, index, base, pbase, defValue);
    }

    /**
     * Retrieves the resource identifier for the attribute at
     * <var>index</var>.  Note that attribute resource as resolved when
     * the overall {@link TypedArray} object is retrieved.  As a
     * result, this function will return the resource identifier of the
     * final resource value that was found, <em>not</em> necessarily the
     * original resource that was specified by the attribute.
     *
     * @param index Index of attribute to retrieve.
     * @param defValue Value to return if the attribute is not defined or
     *                 not a resource.
     *
     * @return Attribute resource identifier, or defValue if not defined.
     * @throws RuntimeException if the TypedArray has already been recycled.
     */
    @AnyRes
    public int getResourceId(@StyleableRes int index, int defValue) {
        return TypedArrayBinding.getResourceId(this, index, defValue);
    }

    /**
     * Retrieves the theme attribute resource identifier for the attribute at
     * <var>index</var>.
     *
     * @param index Index of attribute to retrieve.
     * @param defValue Value to return if the attribute is not defined or not a
     *                 resource.
     *
     * @return Theme attribute resource identifier, or defValue if not defined.
     * @throws RuntimeException if the TypedArray has already been recycled.
     * @hide
     */
    public int getThemeAttributeId(@StyleableRes int index, int defValue) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Retrieve the CharSequence[] for the attribute at <var>index</var>.
     * This gets the resource ID of the selected attribute, and uses
     * {@link Resources#getTextArray Resources.getTextArray} of the owning
     * Resources object to retrieve its String[].
     * <p>
     * This method will throw an exception if the attribute is defined but is
     * not a text array resource.
     *
     * @param index Index of attribute to retrieve.
     *
     * @return CharSequence[] for the attribute, or {@code null} if not
     *         defined.
     * @throws RuntimeException if the TypedArray has already been recycled.
     */
    public CharSequence[] getTextArray(@StyleableRes int index) {
        throw new UnsupportedOperationException("getTextArray(I)[Ljava/lang/CharSequence;");
    }

    /**
     * Returns the type of attribute at the specified index.
     *
     * @param index Index of attribute whose type to retrieve.
     *
     * @return Attribute type.
     * @throws RuntimeException if the TypedArray has already been recycled.
     */
    public int getType(@StyleableRes int index) {
        return TypedArrayBinding.getType(this, index);
    }

    /**
     * Returns the resource ID of the style or layout against which the specified attribute was
     * resolved, otherwise returns defValue.
     *
     * For example, if you we resolving two attributes {@code android:attribute1} and
     * {@code android:attribute2} and you were inflating a {@link android.view.View} from
     * {@code layout/my_layout.xml}:
     * <pre>
     *     &lt;View
     *         style="@style/viewStyle"
     *         android:layout_width="wrap_content"
     *         android:layout_height="wrap_content"
     *         android:attribute1="foo"/&gt;
     * </pre>
     *
     * and {@code @style/viewStyle} is:
     * <pre>
     *     &lt;style android:name="viewStyle"&gt;
     *         &lt;item name="android:attribute2"&gt;bar&lt;item/&gt;
     *     &lt;style/&gt;
     * </pre>
     *
     * then resolved {@link TypedArray} will have values that return source resource ID of
     * {@code R.layout.my_layout} for {@code android:attribute1} and {@code R.style.viewStyle} for
     * {@code android:attribute2}.
     *
     * @param index Index of attribute whose source style to retrieve.
     * @param defaultValue Value to return if the attribute is not defined or
     *                     not a resource.
     *
     * @return Either a style resource ID, layout resource ID, or defaultValue if it was not
     * resolved in a style or layout.
     * @throws RuntimeException if the TypedArray has already been recycled.
     */
    @AnyRes
    public int getSourceResourceId(@StyleableRes int index, @AnyRes int defaultValue) {
        if (Build.VERSION.SDK_INT < 29) {
            throw new NoSuchMethodError("getSourceResourceId(II)I");
        }
        return TypedArrayBinding.getSourceResourceId(this, index, defaultValue);
    }

    /**
     * Determines whether there is an attribute at <var>index</var>.
     * <p>
     * <strong>Note:</strong> If the attribute was set to {@code @empty} or
     * {@code @undefined}, this method returns {@code false}.
     *
     * @param index Index of attribute to retrieve.
     *
     * @return True if the attribute has a value, false otherwise.
     * @throws RuntimeException if the TypedArray has already been recycled.
     */
    public boolean hasValue(@StyleableRes int index) {
        return TypedArrayBinding.hasValue(this, index);
    }

    /**
     * Determines whether there is an attribute at <var>index</var>, returning
     * {@code true} if the attribute was explicitly set to {@code @empty} and
     * {@code false} only if the attribute was undefined.
     *
     * @param index Index of attribute to retrieve.
     *
     * @return True if the attribute has a value or is empty, false otherwise.
     * @throws RuntimeException if the TypedArray has already been recycled.
     */
    public boolean hasValueOrEmpty(@StyleableRes int index) {
        if (Build.VERSION.SDK_INT < 22) {
            throw new NoSuchMethodError("hasValueOrEmpty(I)Z");
        }
        return TypedArrayBinding.hasValueOrEmpty(this, index);
    }

    /**
     * Returns a message about the parser state suitable for printing error messages.
     *
     * @return Human-readable description of current parser state.
     * @throws RuntimeException if the TypedArray has already been recycled.
     */
    public String getPositionDescription() {
        return TypedArrayBinding.getPositionDescription(this);
    }

    /**
     * Recycles the TypedArray, to be re-used by a later caller. After calling
     * this function you must not ever touch the typed array again.
     *
     * @throws RuntimeException if the TypedArray has already been recycled.
     */
    public void recycle() {
        TypedArrayBinding.recycle(this);
    }

    /**
     * Recycles the TypedArray, to be re-used by a later caller. After calling
     * this function you must not ever touch the typed array again.
     *
     * @see #recycle()
     * @throws RuntimeException if the TypedArray has already been recycled.
     */
    public void close() {
        if (Build.VERSION.SDK_INT < 31) {
            throw new NoSuchMethodError("close()V");
        }
        TypedArrayBinding.close(this);
    }

    /**
     * Extracts theme attributes from a typed array for later resolution using
     * {@link android.content.res.Resources.Theme#resolveAttributes(int[], int[])}.
     * Removes the entries from the typed array so that subsequent calls to typed
     * getters will return the default value without crashing.
     *
     * @return an array of length {@link #getIndexCount()} populated with theme
     *         attributes, or null if there are no theme attributes in the typed
     *         array
     * @throws RuntimeException if the TypedArray has already been recycled.
     * @hide
     */
    @Nullable
    @UnsupportedAppUsage
    public int[] extractThemeAttrs() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * @hide
     */
    @Nullable
    @UnsupportedAppUsage
    public int[] extractThemeAttrs(@Nullable int[] scrap) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Return a mask of the configuration parameters for which the values in
     * this typed array may change.
     *
     * @return Returns a mask of the changing configuration parameters, as
     *         defined by {@link android.content.pm.ActivityInfo}.
     * @throws RuntimeException if the TypedArray has already been recycled.
     * @see android.content.pm.ActivityInfo
     */
    public int getChangingConfigurations() {
        return TypedArrayBinding.getChangingConfigurations(this);
    }

    @Nullable
    private CharSequence loadStringValueAt(int index) {
        throw new UnsupportedOperationException("hidden on ART");
    }

    @Override
    public String toString() {
        return TypedArrayBinding.toString(this);
    }

    @Added(reason = "the id of the ART object this handle forwards to")
    private final long id;

    @Added(reason = "the handle of the ART object received under its id")
    public TypedArray(long id) {
        this.id = id;
    }

    @Added(reason = "the id this handle crosses to ART as")
    @Override
    public long foreignId() {
        return id;
    }
}
