/*
 * Copyright (C) 2006 The Android Open Source Project
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

package android.os;

import org.androidgraal.hosted.annotate.Emulated;

/**
 * Interface for classes whose instances can be written to
 * and restored from a {@link Parcel}.  Classes implementing the Parcelable
 * interface must also have a non-null public static field called
 * <code>CREATOR</code> of a type that implements the {@link Parcelable.Creator}
 * interface.
 *
 * <p>A typical implementation of Parcelable is:</p>
 *
 * <div>
 * <div class="ds-selector-tabs"><section><h3 id="kotlin">Kotlin</h3>
 * <pre class="prettyprint lang-kotlin">
 * class MyParcelable private constructor(`in`: Parcel) : Parcelable {
 *     private val mData: Int = `in`.readInt()
 *
 *     override fun describeContents(): Int {
 *         return 0
 *     }
 *
 *     override fun writeToParcel(out: Parcel, flags: Int) {
 *         out.writeInt(mData)
 *     }
 *
 *     companion object CREATOR: Parcelable.Creator&lt;MyParcelable?&gt; {
 *         override fun createFromParcel(`in`: Parcel): MyParcelable? {
 *             return MyParcelable(`in`)
 *         }
 *
 *         override fun newArray(size: Int): Array&lt;MyParcelable?&gt; {
 *             return arrayOfNulls(size)
 *         }
 *     }
 * }
 * </pre>
 * </section><section><h3 id="java">Java</h3>
 * <pre class="prettyprint lang-java">
 * public class MyParcelable implements Parcelable {
 *     private int mData;
 *
 *     public int describeContents() {
 *         return 0;
 *     }
 *
 *     public void writeToParcel(Parcel out, int flags) {
 *         out.writeInt(mData);
 *     }
 *
 *     public static final Parcelable.Creator&lt;MyParcelable&gt; CREATOR
 *             = new Parcelable.Creator&lt;MyParcelable&gt;() {
 *         public MyParcelable createFromParcel(Parcel in) {
 *             return new MyParcelable(in);
 *         }
 *
 *         public MyParcelable[] newArray(int size) {
 *             return new MyParcelable[size];
 *         }
 *     };
 *
 *     private MyParcelable(Parcel in) {
 *         mData = in.readInt();
 *     }
 * }</pre></section></div></div>
 */
@Emulated
public interface Parcelable {
    /**
     * Flag for use with {@link #writeToParcel}: the object being written
     * is a return value, that is the result of a function such as
     * "<code>Parcelable someFunction()</code>",
     * "<code>void someFunction(out Parcelable)</code>", or
     * "<code>void someFunction(inout Parcelable)</code>".  Some implementations
     * may want to release resources at this point.
     */
    public static final int PARCELABLE_WRITE_RETURN_VALUE = 0x0001;

    /**
     * Descriptor bit used with {@link #describeContents()}: indicates that
     * the Parcelable object's flattened representation includes a file descriptor.
     *
     * @see #describeContents()
     */
    public static final int CONTENTS_FILE_DESCRIPTOR = 0x0001;
}
