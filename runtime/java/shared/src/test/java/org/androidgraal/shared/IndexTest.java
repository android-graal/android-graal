package org.androidgraal.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import org.junit.jupiter.api.Test;

class IndexTest {

    @Test
    void theLongIndexAgreesWithAMapUnderRandomPutsAndRemoves() {
        Random random = new Random(42);
        LongIndex<Long> index = new LongIndex<>();
        Map<Long, Long> expected = new HashMap<>();
        for (int i = 0; i < 200_000; i++) {
            long key = -2L * (1 + random.nextInt(3000));
            if (expected.containsKey(key)) {
                assertEquals(expected.remove(key), index.remove(key));
            } else {
                assertNull(index.remove(key));
                index.put(key, key * 3);
                expected.put(key, key * 3);
            }
            if (i % 1000 == 0) {
                for (int k = 1; k <= 3000; k++) {
                    assertEquals(expected.get(-2L * k), index.get(-2L * k));
                }
                List<Long> values = new ArrayList<>(expected.values());
                List<Long> iterated = new ArrayList<>();
                for (Long value : index) {
                    iterated.add(value);
                }
                Collections.sort(values);
                Collections.sort(iterated);
                assertEquals(values, iterated);
            }
        }
        assertEquals(expected.size(), index.size());
    }

    @Test
    void anEmptyIndexYieldsNothingAndNextThenThrows() {
        LongIndex<Long> index = new LongIndex<>();
        for (long key = 1; key <= 100; key++) {
            index.put(key, key);
        }
        for (long key = 1; key <= 100; key++) {
            index.remove(key);
        }

        Iterator<Long> values = index.iterator();

        assertFalse(values.hasNext());
        assertThrows(NoSuchElementException.class, values::next);
    }
}
