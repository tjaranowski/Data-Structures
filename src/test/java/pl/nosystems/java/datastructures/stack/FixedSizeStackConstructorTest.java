package pl.nosystems.java.datastructures.stack;

import org.junit.Test;

public final class FixedSizeStackConstructorTest {

    @Test
    public void constructorTest() {
        new FixedSizeStack<>(new Integer[10]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullStackContainerPassedToConstructorTest() {
        new FixedSizeStack<>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroSizedContainerPassedToConstructorTest() {
        new FixedSizeStack<>(new Integer[0]);
    }

}
