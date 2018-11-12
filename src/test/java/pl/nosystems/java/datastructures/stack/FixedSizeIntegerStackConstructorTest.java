package pl.nosystems.java.datastructures.stack;

import org.junit.Test;

public final class FixedSizeIntegerStackConstructorTest {

    @Test
    public void constructorWithValidParameterTest() {
        new FixedSizeIntegerStack(100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithInvalidParameterTest1() {
        new FixedSizeIntegerStack(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithInvalidParameterTest2() {
        new FixedSizeIntegerStack(-100);
    }
}
