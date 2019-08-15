package pl.nosystems.java.datastructures.map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class HashMapTest {
    private HashMap<Integer, Integer> map;


    @Before
    public void setUp() {
        map = new HashMap<>();
    }


    @Test
    public void givenEmptyHashMapGetShouldReturnNull() {

        // Then verify
        assertNull(map.get(0));
        assertNull(map.get(1));
        assertNull(map.get(Integer.MAX_VALUE));
        assertNull(map.get(Integer.MIN_VALUE));
    }

    @Test
    public void givenEmptyHashMapPutShouldReturnNull() {

        // When
        Integer previousValue = map.put(1, 1);

        // Then verify
        assertNull(previousValue);
        assertEquals(Integer.valueOf(1), map.get(1));
    }

    @Test
    public void givenObjectThatHasIntegerMinValueHashShouldWorkAsExpected() {

        // Given
        HashMap<Object, Integer> map = new HashMap<>();
        ObjectWithSpecificHash objectWithSpecificHash = new ObjectWithSpecificHash(Integer.MIN_VALUE);

        // When
        Integer firstPrevValue = map.put(objectWithSpecificHash, 1);
        Integer secondPrevValue = map.put(objectWithSpecificHash, 2);

        // Then verify
        assertNull(firstPrevValue);

        assertEquals(Integer.valueOf(1), secondPrevValue);
        assertEquals(Integer.valueOf(2), map.get(objectWithSpecificHash));
    }

    @Test
    public void whenPutTwoValuesUnderSameKeyShouldReplaceValueUnderKey() {

        // When
        Integer firstPrevValue = map.put(1, 1);
        Integer secondPrevValue = map.put(1, 2);

        // Then verify
        assertNull(firstPrevValue);

        assertEquals(Integer.valueOf(1), secondPrevValue);
        assertEquals(Integer.valueOf(2), map.get(1));
    }

    @Test
    public void whenAddedTwoObjectsUnderDifferentKeysShouldPersistThemCorrectly() {

        // When
        Integer firstPrevValue = map.put(1, 1);
        Integer secondPrevValue = map.put(2, 2);

        // Then verify
        assertNull(firstPrevValue);
        assertNull(secondPrevValue);

        assertEquals(Integer.valueOf(1), map.get(1));
        assertEquals(Integer.valueOf(2), map.get(2));
    }

    @Test
    public void givenHashMapWithOneBucketShouldStillWork() {

        // Given
        HashMap<Integer, Integer> map = new HashMap<>(1);

        // When
        Integer i1 = map.put(1, 1);
        Integer i2 = map.put(2, 2);
        Integer i3 = map.put(3, 3);
        Integer i4 = map.put(3, 4);
        Integer i5 = map.put(Integer.MIN_VALUE, Integer.MIN_VALUE);
        Integer i6 = map.put(Integer.MAX_VALUE, Integer.MAX_VALUE);

        // Then verify
        assertNull(i1);
        assertNull(i2);
        assertNull(i3);
        assertEquals(Integer.valueOf(3), i4);
        assertNull(i5);
        assertNull(i6);

        assertEquals(Integer.valueOf(1), map.get(1));
        assertEquals(Integer.valueOf(2), map.get(2));
        assertEquals(Integer.valueOf(4), map.get(3));
        assertEquals(Integer.valueOf(Integer.MIN_VALUE), map.get(Integer.MIN_VALUE));
        assertEquals(Integer.valueOf(Integer.MAX_VALUE), map.get(Integer.MAX_VALUE));
    }


    private final static class ObjectWithSpecificHash {
        private final int hashToReturn;

        private ObjectWithSpecificHash(int hashToReturn) {
            this.hashToReturn = hashToReturn;
        }

        @Override
        public int hashCode() {
            return hashToReturn;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (obj instanceof ObjectWithSpecificHash) {
                return obj.hashCode() == hashToReturn;
            }
            return false;
        }
    }

}
