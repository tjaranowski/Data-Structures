package pl.nosystems.java.datastructures.arrays;


/**
 * Collection that holds specified amount of elements.
 * When read past its size, read pointer is reset to beginning.
 * When write past its size, write pointer is reset to beginning.
 * That means that given empty buffer that then is
 * populated with elements [0, 1]
 * then first read will give element 0
 * second read will give element 1
 * third read will give element 0 as read pointer will be reset to beginning.
 * Case for writing is analogous.
 *
 * @param <E> Elements that this data structure will hold
 */
public class CircularBuffer<E> {
    private final Object[] internalBuffer;
    private final MutableInteger writePointer = new MutableInteger(0);
    private final MutableInteger readPointer = new MutableInteger(0);


    /**
     * Constructor that creates circular buffer aka ring buffer of requested size.
     * If size is negative or zero, it throws java.lang.IllegalArgumentException
     *
     * @param size size of buffer
     */
    CircularBuffer(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        internalBuffer = new Object[size];
    }

    /**
     * Inserts element at write pointer.
     * If write pointer reaches end of buffer it is reset to its beginning.
     *
     * @param element Element to be added
     */
    public void put(E element) {
        internalBuffer[incrementPointerAndGet(writePointer)] = element;
    }

    /**
     * Returns element that is under read pointer and increments read pointer.
     *
     * @return element at read pointer
     */
    @SuppressWarnings("unchecked")
    public E read() {
        return (E) internalBuffer[incrementPointerAndGet(readPointer)];
    }

    /**
     * Internal function that increments given pointer and returns old value.
     * If pointer was to point outside of buffer it is reset to beginning.
     *
     * @param pointer pointer to increment
     * @return old pointer value
     */
    private int incrementPointerAndGet(MutableInteger pointer) {
        int oldPointer = pointer.getValue();
        if (pointer.incrementAndGet().getValue() >= internalBuffer.length) {
            pointer.decrementBy(internalBuffer.length);
        }
        return oldPointer;
    }


    /**
     * Class that represents Integer that can be passed by reference and modified
     */
    private static class MutableInteger {
        private int value;

        /**
         * Constructor that creates MutableInteger with given initial value
         *
         * @param initialValue initial value
         */
        MutableInteger(int initialValue) {
            value = initialValue;
        }

        /**
         * Increments integer value and returns this instance
         *
         * @return this instance
         */
        MutableInteger incrementAndGet() {
            ++value;
            return this;
        }

        /**
         * Decrements integer value by amount
         *
         * @param amount amount to decrement value
         */
        void decrementBy(int amount) {
            value -= amount;
        }

        /**
         * Returns integer value
         *
         * @return integer value
         */
        int getValue() {
            return value;
        }
    }
}
