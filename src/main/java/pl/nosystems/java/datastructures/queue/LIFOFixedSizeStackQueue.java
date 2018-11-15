package pl.nosystems.java.datastructures.queue;

import pl.nosystems.java.datastructures.stack.FixedSizeStack;
import pl.nosystems.java.datastructures.stack.StackEmpty;
import pl.nosystems.java.datastructures.stack.StackFull;

@SuppressWarnings({"unused", "WeakerAccess"})
public class LIFOFixedSizeStackQueue<T> implements Queue<T> {
    private final FixedSizeStack<T> stack;

    public LIFOFixedSizeStackQueue(FixedSizeStack<T> stack) {
        this.stack = stack;
    }

    @Override
    public void putOrThrow(T object) throws QueueFull {
        try {
            stack.putOrThrow(object);
        } catch (StackFull stackFull) {
            throw new QueueFull(stackFull);
        }
    }

    @Override
    public T getOrThrow() throws QueueEmpty {
        try {
            return stack.popOrThrow();
        } catch (StackEmpty stackEmpty) {
            throw new QueueEmpty(stackEmpty);
        }
    }
}
