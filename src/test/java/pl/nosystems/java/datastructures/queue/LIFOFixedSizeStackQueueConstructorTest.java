package pl.nosystems.java.datastructures.queue;

import org.junit.Test;
import pl.nosystems.java.datastructures.stack.FixedSizeStack;

public final class LIFOFixedSizeStackQueueConstructorTest {

    @Test
    public void constructorTest() {
        new LIFOFixedSizeStackQueue<>(new FixedSizeStack<>(new Integer[10]));
    }

    @Test(expected = QueueEmpty.class)
    public void afterConstructionEmptyTest() throws QueueEmpty {
        final LIFOFixedSizeStackQueue<Integer> queue = new LIFOFixedSizeStackQueue<>(new FixedSizeStack<>(new Integer[10]));
        queue.getOrThrow();
    }
}
