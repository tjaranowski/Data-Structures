package pl.nosystems.java.datastructures.stack;

import org.junit.Test;

public final class FixedSizeStackConstructorTest {

    @Test
    public void constructorTest() {
        new FixedSizeStack<>(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroStackSizePassedToConstructorTest() {
        new FixedSizeStack<>(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeStackSizePassedToConstructorTest() {
        new FixedSizeStack<>(-1);
    }

}
