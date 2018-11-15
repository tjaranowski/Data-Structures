package pl.nosystems.java.datastructures.queue;

import org.junit.Before;
import org.junit.Test;
import pl.nosystems.java.datastructures.stack.FixedSizeStack;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public final class LIFOFixedSizeStackQueueTest {
    private static final int QUEUE_SIZE = 10;
    private LIFOFixedSizeStackQueue<Integer> queue;

    @Before
    public void setUp() {
        queue = new LIFOFixedSizeStackQueue<>(new FixedSizeStack<>(new Integer[QUEUE_SIZE]));
    }

    @Test
    public void addOneElementToQueueTest() throws QueueFull, QueueEmpty {
        queue.putOrThrow(10);
        assertThat(queue.getOrThrow(),is(equalTo(10)));
    }

    @Test(expected = QueueFull.class)
    public void addTooManyElementsTest() throws QueueFull {
        for (int i = 0; i < QUEUE_SIZE+1; i++) {
            queue.putOrThrow(i);
        }
    }

}
