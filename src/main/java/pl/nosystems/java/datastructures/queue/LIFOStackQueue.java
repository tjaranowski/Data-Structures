package pl.nosystems.java.datastructures.queue;

import pl.nosystems.java.datastructures.stack.Stack;
import pl.nosystems.java.datastructures.stack.StackEmpty;
import pl.nosystems.java.datastructures.stack.StackFull;

import java.util.Objects;

@SuppressWarnings("WeakerAccess")
public class LIFOStackQueue<T> implements Queue<T> {
    private final Stack<T> stack;

    public LIFOStackQueue(Stack<T> stack) {
        Objects.requireNonNull(stack);
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
