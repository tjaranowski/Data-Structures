package pl.nosystems.java.datastructures.queue;

import pl.nosystems.java.datastructures.stack.FixedSizeStack;

@SuppressWarnings({"unused", "WeakerAccess"})
public final class LIFOFixedSizeStackQueue<T> extends LIFOStackQueue<T> {

    public LIFOFixedSizeStackQueue(FixedSizeStack<T> stack) {
        super(stack);
    }
}
