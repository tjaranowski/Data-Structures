package pl.nosystems.java.datastructures.queue;

import pl.nosystems.java.datastructures.stack.LinkedListStack;

@SuppressWarnings({"unused", "WeakerAccess"})
public final class LIFOLinkedListStackQueue<T> extends LIFOStackQueue<T> {

    public LIFOLinkedListStackQueue() {
        super(new LinkedListStack<T>());
    }
}
