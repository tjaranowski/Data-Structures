package pl.nosystems.java.datastructures.stack;

import pl.nosystems.java.datastructures.list.DoubleLinkedListNode;

@SuppressWarnings({"WeakerAccess", "unused"})
public class LinkedListStack<T> {
    private DoubleLinkedListNode<T> stackHead;
    private DoubleLinkedListNode<T> stackTail;

    private T internalPopAfterChecks() {
        final T data = stackTail.getData();
        if(stackTail == stackHead) {
            stackTail = stackHead = null;
            return data;
        }
        stackTail = stackTail.getPrevious();
        stackTail.setNext(null);
        return data;
    }

    private void internalPutAfterChecks(T data) {
        if(stackHead == null) {
            stackHead = stackTail = new DoubleLinkedListNode<>(data);
            return;
        }
        final DoubleLinkedListNode<T> previousTail = stackTail;
        stackTail = new DoubleLinkedListNode<>(previousTail,data,null);
        previousTail.setNext(stackTail);
    }

    @SuppressWarnings("SameReturnValue")
    public boolean putWithResult(final T value) {
        internalPutAfterChecks(value);
        return true;
    }

    @SuppressWarnings("RedundantThrows")
    public void putOrThrow(final T value) throws StackFull {
        internalPutAfterChecks(value);
    }

    public T popOrThrow() throws StackEmpty {
        if(isEmpty()) {
            throw new StackEmpty();
        }

        return internalPopAfterChecks();
    }

    @SuppressWarnings("SameReturnValue")
    public boolean isFull() {
        return false;
    }

    public boolean isEmpty() {
        return stackHead==null;
    }

}
