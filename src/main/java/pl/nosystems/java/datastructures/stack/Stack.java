package pl.nosystems.java.datastructures.stack;

@SuppressWarnings("unused")
public interface Stack<T> {

    void putOrThrow(final T value) throws StackFull;
    T popOrThrow() throws StackEmpty;

    boolean isFull();
    boolean isEmpty();
}
