package pl.nosystems.java.datastructures.arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CircularBufferTest {

    @Test
    public void whenPutOneElementReadReturnsSameElement() {

        // Given
        CircularBuffer<Integer> circularBuffer = new CircularBuffer<>(10);

        // When
        circularBuffer.put(1);
        Integer readResult = circularBuffer.read();

        // Then verify
        assertEquals(Integer.valueOf(1), readResult);
    }

    @Test
    public void whenOverflowHappensShouldWrapCorrectly() {

        // Given
        CircularBuffer<Integer> circularBuffer = new CircularBuffer<>(2);

        // When
        circularBuffer.put(1);
        circularBuffer.put(2);
        circularBuffer.put(3);

        // Then verify
        assertEquals(Integer.valueOf(3), circularBuffer.read());
        assertEquals(Integer.valueOf(2), circularBuffer.read());
        assertEquals(Integer.valueOf(3), circularBuffer.read());
    }

    @Test
    public void whenReadOverflowHappensShouldReadCorrectly() {

        // Given
        CircularBuffer<Integer> circularBuffer = new CircularBuffer<>(3);

        // When
        circularBuffer.put(1);

        circularBuffer.read();  // EL 1 - (1)
        circularBuffer.read();  // EL 2 - (?)
        circularBuffer.read();  // EL 3 - (?)

        Integer readValue = circularBuffer.read();  // EL 1 - (1)

        // Then verify
        assertEquals(Integer.valueOf(1), readValue);
    }

    @Test
    public void whenOverflowHappensSeveralTimesShouldReadCorrectly() {
        int FULL_OVERFLOW_COUNT = 10 + 1; // +1 for non-overflow pass
        int CIRCULAR_BUFFER_SIZE = 2;

        // Given
        CircularBuffer<Integer> circularBuffer = new CircularBuffer<>(CIRCULAR_BUFFER_SIZE);

        // When
        for (int i = 1; i <= FULL_OVERFLOW_COUNT * CIRCULAR_BUFFER_SIZE; i++) {
            circularBuffer.put(i);
        }

        // Then verify
        assertEquals(Integer.valueOf(FULL_OVERFLOW_COUNT * CIRCULAR_BUFFER_SIZE - 1), circularBuffer.read());
        assertEquals(Integer.valueOf(FULL_OVERFLOW_COUNT * CIRCULAR_BUFFER_SIZE), circularBuffer.read());
    }
}
