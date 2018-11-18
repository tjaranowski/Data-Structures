package pl.nosystems.java.datastructures.queue;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public abstract class LIFOStackQueueTest {
    @SuppressWarnings("unused")
    final int MINIMUM_QUEUE_SIZE = 10;
    LIFOStackQueue<Integer> queue;

    void setQueue(final LIFOStackQueue<Integer> queue) {
        this.queue = queue;
    }

    @SuppressWarnings("unused")
    @Before
    public abstract void createNewStackQueue();

    @Test
    public void addOneElementToQueueTest() throws QueueFull, QueueEmpty {
        queue.putOrThrow(10);
        assertThat(queue.getOrThrow(),is(equalTo(10)));
    }
}
