package pl.nosystems.java.datastructures.set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ArraySetTest {
    private ArraySet<Integer> arraySet;

    @Before
    public void setUp() {
        arraySet = new ArraySet<>();
    }


    @Test
    public void givenEmptySetWhenAddedOneElementShouldAddItSuccessfully() {

        // When
        boolean added = arraySet.add(1);

        // Then verify
        assertTrue(added);
        assertTrue(arraySet.contains(1));

        Iterator<Integer> iterator = arraySet.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(1), iterator.next());
    }

    @Test
    public void whenAddedSameElementTwiceShouldNotAddOnSecondTry() {

        // When
        boolean added1 = arraySet.add(1);
        boolean added2 = arraySet.add(1);

        // Then verify
        assertTrue(added1);
        assertFalse(added2);
        assertTrue(arraySet.contains(1));
    }

    @Test
    public void whenAddedSameElementTwiceAndRemovedItOnceContainsShouldReturnFalse() {

        // When
        arraySet.add(1);
        arraySet.add(1);
        boolean removed = arraySet.remove(1);

        // Then verify
        assertTrue(removed);
        assertFalse(arraySet.contains(1));
    }
}
