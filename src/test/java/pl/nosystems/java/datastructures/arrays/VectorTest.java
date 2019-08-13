package pl.nosystems.java.datastructures.arrays;

import org.junit.Test;

import static org.junit.Assert.*;


public class VectorTest {

    @Test
    public void givenNewlyCreatedVectorShouldBeEmpty() {

        // Given
        Vector<Integer> vector = new Vector<>(2);

        // Then verify
        assertEquals(2, vector.getCapacity());
        assertFalse(vector.contains(0));
        assertFalse(vector.contains(1));
        assertFalse(vector.contains(Integer.MIN_VALUE));
        assertFalse(vector.contains(Integer.MAX_VALUE));

        assertNull(vector.get(0));
        assertNull(vector.get(1));
    }

    @Test
    public void givenVectorWithEnoughCapacityShouldPutElementSuccessfully() {

        // Given
        Vector<Integer> vector = new Vector<>(10);

        // When
        vector.put(1, 5);

        // Then verify
        assertEquals(Integer.valueOf(1), vector.get(5));
        assertTrue(vector.contains(1));
    }

    @Test
    public void givenNotEnoughCapacityButMethodThatEnsuresOneShouldResizeAndAddElement() {

        // Given
        Vector<Integer> vector = new Vector<>(2);

        // When
        vector.putEnsuringCapacity(2, 5);

        // Then verify
        assertTrue(vector.getCapacity() > 2);
        assertEquals(Integer.valueOf(2), vector.get(5));
        assertTrue(vector.contains(2));
    }

    @Test
    public void whenElementsAreInVectorAndResizeHappensVerifyThatPreviousElementsAreStillThere() {

        // Given
        Vector<Integer> vector = new Vector<>(2);

        // When
        vector.put(100, 0);
        vector.put(200, 1);

        // When resize happens
        vector.putEnsuringCapacity(300, 2);

        // Then verify
        assertEquals(Integer.valueOf(100), vector.get(0));
        assertEquals(Integer.valueOf(200), vector.get(1));
        assertEquals(Integer.valueOf(300), vector.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenNotEnoughCapacityShouldThrowException() {

        // Given
        Vector<Integer> vector = new Vector<>(2);

        // When
        vector.put(3, 5);

        // Then should throw
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void givenIndexOutsideOfAllowedBoundsShouldThrow() {

        // Given
        Vector<Integer> vector = new Vector<>(2);

        // When
        vector.put(0, -100);
    }
}
