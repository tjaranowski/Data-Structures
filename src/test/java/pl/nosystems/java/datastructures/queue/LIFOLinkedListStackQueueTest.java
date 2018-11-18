package pl.nosystems.java.datastructures.queue;

public class LIFOLinkedListStackQueueTest extends LIFOStackQueueTest {
    @Override
    public void createNewStackQueue() {
        setQueue(new LIFOLinkedListStackQueue<>());
    }
}
