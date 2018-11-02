package pl.nosystems.java.datastructures;

@SuppressWarnings("WeakerAccess")
public final class FixedSizeIntegerStack {
    private int[] stack;
    private int stackPointer = 0;

    private void internalPutAfterChecks(final int value) {
        stack[stackPointer] = value;
        stackPointer++;
    }

    public FixedSizeIntegerStack(final int stackSize) {
        if(stackSize <= 0) {
            throw new IllegalArgumentException("stackSize must be positive");
        }
        stack = new int[stackSize];
    }


    public boolean putWithResult(final int value) {
        if(isFull()) {
            return false;
        }

        internalPutAfterChecks(value);

        return true;
    }

    public void putOrThrow(final int value) throws StackFull {
        if(isFull()) {
            throw new StackFull();
        }

        internalPutAfterChecks(value);
    }

    public int popOrThrow() throws StackEmpty {
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
