package pl.nosystems.java.datastructures.queue;

import org.junit.Test;
import pl.nosystems.java.datastructures.stack.FixedSizeStack;

public class LIFOFixedSizeStackQueueTest extends LIFOStackQueueTest {
    private static int QUEUE_SIZE = 10;

    @Override
    public void createNewStackQueue() {
        if(MINIMUM_QUEUE_SIZE>QUEUE_SIZE) {
            QUEUE_SIZE = MINIMUM_QUEUE_SIZE;
        }
        setQueue(new LIFOFixedSizeStackQueue<>(new FixedSizeStack<>(new Integer[QUEUE_SIZE])));
    }

    @Test(expected = QueueFull.class)
    public void addTooManyElementsTest() throws QueueFull {
        for (int i = 0; i < QUEUE_SIZE+1; i++) {
            queue.putOrThrow(i);
        }
    }
}
