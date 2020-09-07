package pl.nosystems.java.datastructures.stack;

import pl.nosystems.java.datastructures.list.DoubleLinkedList;


@SuppressWarnings({"WeakerAccess"})
public class LinkedListStack<T> implements Stack<T> {
    private final DoubleLinkedList<T> doubleLinkedList = new DoubleLinkedList<>();

    @SuppressWarnings("SameReturnValue")
    public boolean putWithResult(final T value) {
        internalPutAfterChecks(value);
        return true;
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    public void putOrThrow(final T value) throws StackFull {
        internalPutAfterChecks(value);
    }

    @Override
    public T popOrThrow() throws StackEmpty {
        if (isEmpty()) {
            throw new StackEmpty();
        }

        return internalPopAfterChecks();
    }

    @SuppressWarnings("SameReturnValue")
    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return doubleLinkedList.isEmpty();
    }


    private T internalPopAfterChecks() {
        return doubleLinkedList.removeLast();
    }

    private void internalPutAfterChecks(T data) {
        doubleLinkedList.addLast(data);
    }
}
