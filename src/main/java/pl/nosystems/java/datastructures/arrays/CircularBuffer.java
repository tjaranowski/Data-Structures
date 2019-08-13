package pl.nosystems.java.datastructures.arrays;


public class CircularBuffer<E> {
    private final Object[] internalBuffer;
    private MutableInteger writePointer = new MutableInteger(0);
    private MutableInteger readPointer = new MutableInteger(0);


    CircularBuffer(int size) {
        internalBuffer = new Object[size];
    }


    public void put(E element) {
        internalBuffer[incrementPointerAndGet(writePointer).getValue()] = element;
    }

    @SuppressWarnings("unchecked")
    public E read() {
        return (E) internalBuffer[incrementPointerAndGet(readPointer).getValue()];
    }


    private MutableInteger incrementPointerAndGet(MutableInteger pointer) {
        MutableInteger oldPointer = pointer.getDeepCopy();
        if(pointer.incrementAndGet().getValue() >= internalBuffer.length) {
            pointer.decrementBy(internalBuffer.length);
        }
        return oldPointer;
    }

    private static class MutableInteger {
        private int value;

        MutableInteger(int initialValue) {
            value = initialValue;
        }

        MutableInteger incrementAndGet() {
            ++value;
            return this;
        }

        void decrementBy(int amount) {
            value -= amount;
        }

        int getValue() {
            return value;
        }

        public MutableInteger getDeepCopy() {
            return new MutableInteger(getValue());
        }
    }
}
