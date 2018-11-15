package pl.nosystems.java.datastructures.queue;

import pl.nosystems.java.datastructures.stack.StackFull;
import pl.nosystems.java.datastructures.state.Full;

@SuppressWarnings("WeakerAccess")
public class QueueFull extends Full {
    public QueueFull(StackFull stackFull) {
        super(stackFull);
    }
}
