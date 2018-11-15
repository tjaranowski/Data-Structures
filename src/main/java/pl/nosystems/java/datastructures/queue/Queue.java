package pl.nosystems.java.datastructures.queue;

@SuppressWarnings("unused")
public interface Queue<T> {

    void putOrThrow(T object) throws QueueFull;
    T getOrThrow() throws QueueEmpty;
}
