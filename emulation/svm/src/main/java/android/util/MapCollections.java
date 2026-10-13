/*
 * Copyright (C) 2013 The Android Open Source Project
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

package android.util;

import androidx.annotation.Nullable;

import org.androidgraal.hosted.annotate.Emulated;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Helper for writing standard Java collection interfaces to a data
 * structure like {@link ArrayMap}.
 * @hide
 */
@Emulated
abstract class MapCollections<K, V> {
    private KeySet mKeySet;

    private final class ArrayIterator<T> implements Iterator<T> {
        private final int mOffset;
        private int mSize;
        private int mIndex;
        private boolean mCanRemove = false;

        private ArrayIterator(int offset) {
            mOffset = offset;
            mSize = colGetSize();
        }

        @Override
        public boolean hasNext() {
            return mIndex < mSize;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            Object res = colGetEntry(mIndex, mOffset);
            mIndex++;
            mCanRemove = true;
            return (T)res;
        }

        @Override
        public void remove() {
            if (!mCanRemove) {
                throw new IllegalStateException();
            }
            mIndex--;
            mSize--;
            mCanRemove = false;
            colRemoveAt(mIndex);
        }
    }

    private final class KeySet implements Set<K> {

        @Override
        public boolean add(K object) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            colClear();
        }

        @Override
        public boolean contains(Object object) {
            return colIndexOfKey(object) >= 0;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            return containsAllHelper(colGetMap(), collection);
        }

        @Override
        public boolean isEmpty() {
            return colGetSize() == 0;
        }

        @Override
        public Iterator<K> iterator() {
            return new ArrayIterator<K>(0);
        }

        @Override
        public boolean remove(Object object) {
            int index = colIndexOfKey(object);
            if (index >= 0) {
                colRemoveAt(index);
                return true;
            }
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            return removeAllHelper(colGetMap(), collection);
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            return retainAllHelper(colGetMap(), collection);
        }

        @Override
        public int size() {
            return colGetSize();
        }

        @Override
        public Object[] toArray() {
            return toArrayHelper(0);
        }

        @Override
        public <T> T[] toArray(T[] array) {
            return toArrayHelper(array, 0);
        }

        @Override
        public boolean equals(@Nullable Object object) {
            return equalsSetHelper(this, object);
        }

        @Override
        public int hashCode() {
            int result = 0;
            for (int i=colGetSize()-1; i>=0; i--) {
                Object obj = colGetEntry(i, 0);
                result += obj == null ? 0 : obj.hashCode();
            }
            return result;
        }

        @Override
        public String toString() {
            return toStringHelper(0, this, "KeySet");
        }
    }

    public static <K, V> boolean containsAllHelper(Map<K, V> map, Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!map.containsKey(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static <K, V> boolean removeAllHelper(Map<K, V> map, Collection<?> collection) {
        int oldSize = map.size();
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            map.remove(it.next());
        }
        return oldSize != map.size();
    }

    public static <K, V> boolean retainAllHelper(Map<K, V> map, Collection<?> collection) {
        int oldSize = map.size();
        Iterator<K> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return oldSize != map.size();
    }

    public Object[] toArrayHelper(int offset) {
        final int N = colGetSize();
        Object[] result = new Object[N];
        for (int i=0; i<N; i++) {
            result[i] = colGetEntry(i, offset);
        }
        return result;
    }

    public <T> T[] toArrayHelper(T[] array, int offset) {
        final int N  = colGetSize();
        if (array.length < N) {
            @SuppressWarnings("unchecked") T[] newArray
                = (T[]) Array.newInstance(array.getClass().getComponentType(), N);
            array = newArray;
        }
        for (int i=0; i<N; i++) {
            array[i] = (T)colGetEntry(i, offset);
        }
        if (array.length > N) {
            array[N] = null;
        }
        return array;
    }

    private String toStringHelper(int offset, Object thing, String thingName) {
        int size = colGetSize();
        if (size == 0) {
            return "[]";
        }

        StringBuilder buffer = new StringBuilder(size * 14);
        buffer.append('[');
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                buffer.append(", ");
            }
            Object entry = colGetEntry(i, offset);
            if (entry != thing) {
                buffer.append(entry);
            } else {
                buffer.append("(this ").append(thingName).append(")");
            }
        }
        buffer.append(']');
        return buffer.toString();
    }

    public static <T> boolean equalsSetHelper(Set<T> set, Object object) {
        if (set == object) {
            return true;
        }
        if (object instanceof Set) {
            Set<?> s = (Set<?>) object;

            try {
                return set.size() == s.size() && set.containsAll(s);
            } catch (NullPointerException ignored) {
                return false;
            } catch (ClassCastException ignored) {
                return false;
            }
        }
        return false;
    }

    public Set<K> getKeySet() {
        if (mKeySet == null) {
            mKeySet = new KeySet();
        }
        return mKeySet;
    }

    protected abstract int colGetSize();
    protected abstract Object colGetEntry(int index, int offset);
    protected abstract int colIndexOfKey(Object key);
    protected abstract int colIndexOfValue(Object key);
    protected abstract Map<K, V> colGetMap();
    protected abstract void colPut(K key, V value);
    protected abstract V colSetValue(int index, V value);
    protected abstract void colRemoveAt(int index);
    protected abstract void colClear();
}
