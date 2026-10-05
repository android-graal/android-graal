package org.androidgraal.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;
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
            }
        }
        assertEquals(expected.size(), index.size());
    }
}
