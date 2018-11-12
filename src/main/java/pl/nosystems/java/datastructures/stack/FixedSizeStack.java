package pl.nosystems.java.datastructures.stack;

@SuppressWarnings("WeakerAccess")
public final class FixedSizeStack<T> {
    private T[] stack;
    private int stackPointer = 0;

    private void internalPutAfterChecks(final T value) {
        stack[stackPointer] = value;
        stackPointer++;
    }

    public FixedSizeStack(final T[] stackContainer) {
        if(stackContainer == null || stackContainer.length <= 0) {
            throw new IllegalArgumentException("stackSize must be positive");
        }
        stack = stackContainer;
    }

    public boolean putWithResult(final T value) {
        if(isFull()) {
            return false;
        }

        internalPutAfterChecks(value);

        return true;
    }

    public void putOrThrow(final T value) throws StackFull {
        if(isFull()) {
            throw new StackFull();
        }

        internalPutAfterChecks(value);
    }

    public T popOrThrow() throws StackEmpty {
        if(isEmpty()) {
            throw new StackEmpty();
        }

        return stack[--stackPointer];
    }

    public boolean isFull() {
        return stackPointer == stack.length;
    }

    public boolean isEmpty() {
        return stackPointer == 0;
    }
}
