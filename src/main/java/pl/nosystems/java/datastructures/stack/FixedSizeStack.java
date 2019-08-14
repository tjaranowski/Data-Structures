package pl.nosystems.java.datastructures.stack;

@SuppressWarnings("WeakerAccess")
public final class FixedSizeStack<T> implements Stack<T> {
    private Object[] stack;
    private int stackPointer = 0;

    private void internalPutAfterChecks(final T value) {
        stack[stackPointer] = value;
        stackPointer++;
    }

    public FixedSizeStack(int size) {
        if(size <= 0) {
            throw new IllegalArgumentException("Stack size must be positive. Size passed: " + size);
        }
        stack = new Object[size];
    }

    public boolean putWithResult(final T value) {
        if(isFull()) {
            return false;
        }

        internalPutAfterChecks(value);

        return true;
    }

    @Override
    public void putOrThrow(final T value) throws StackFull {
        if(isFull()) {
            throw new StackFull();
        }

        internalPutAfterChecks(value);
    }

    @Override
    public T popOrThrow() throws StackEmpty {
        if(isEmpty()) {
            throw new StackEmpty();
        }

        //noinspection unchecked
        return (T) stack[--stackPointer];
    }

    @Override
    public boolean isFull() {
        return stackPointer == stack.length;
    }

    @Override
    public boolean isEmpty() {
        return stackPointer == 0;
    }
}
