package org.androidgraal.shared;

/** The key 0 cannot be stored. Not thread-safe. */
// Purely AI gened, I did not care to check. Will be replaced at a later time.
final class LongIndex<V> {

    private long[] keys = new long[16];
    private Object[] values = new Object[16];
    private int size;

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public V get(long key) {
        int mask = keys.length - 1;
        for (int i = slot(key, mask);; i = (i + 1) & mask) {
            long k = keys[i];
            if (k == key) {
                return (V) values[i];
            }
            if (k == 0) {
                return null;
            }
        }
    }

    public void put(long key, V value) {
        if (2 * (size + 1) > keys.length) {
            long[] oldKeys = keys;
            Object[] oldValues = values;
            keys = new long[oldKeys.length * 2];
            values = new Object[oldKeys.length * 2];
            for (int i = 0; i < oldKeys.length; i++) {
                if (oldKeys[i] != 0) {
                    insert(oldKeys[i], oldValues[i]);
                }
            }
        }
        insert(key, value);
        size++;
    }

    @SuppressWarnings("unchecked")
    public V remove(long key) {
        int mask = keys.length - 1;
        int gap = slot(key, mask);
        while (keys[gap] != key) {
            if (keys[gap] == 0) {
                return null;
            }
            gap = (gap + 1) & mask;
        }
        V removed = (V) values[gap];
        for (int i = (gap + 1) & mask; keys[i] != 0; i = (i + 1) & mask) {
            if (((i - slot(keys[i], mask)) & mask) >= ((i - gap) & mask)) {
                keys[gap] = keys[i];
                values[gap] = values[i];
                gap = i;
            }
        }
        keys[gap] = 0;
        values[gap] = null;
        size--;
        return removed;
    }

    private void insert(long key, Object value) {
        int mask = keys.length - 1;
        int i = slot(key, mask);
        while (keys[i] != 0) {
            i = (i + 1) & mask;
        }
        keys[i] = key;
        values[i] = value;
    }

    private static int slot(long key, int mask) {
        int h = (int) (key ^ (key >>> 32)) * 0x9E3779B9;
        return (h ^ (h >>> 16)) & mask;
    }
}
