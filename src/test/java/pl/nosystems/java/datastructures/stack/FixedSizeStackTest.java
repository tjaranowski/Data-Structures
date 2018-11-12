package pl.nosystems.java.datastructures.stack;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public final class FixedSizeStackTest {
    private final int STACK_SIZE = 10;

    private FixedSizeStack<Integer> stack;

    @Before
    public void setUp() {
        stack = new FixedSizeStack<>(new Integer[STACK_SIZE]);
    }

    @Test
    public void putWithResultOkTest() {
        assertTrue(stack.putWithResult(10));
    }

    @Test
    public void putWithResultFalseTest() {
        for (int i = 0; i < STACK_SIZE; i++)
            assertTrue(stack.putWithResult(i));

        assertFalse(stack.putWithResult(10));
    }

    @Test
    public void putOrThrowOkTest() throws StackFull {
        stack.putOrThrow(10);
    }

    @Test(expected = StackFull.class)
    public void putOrThrowFullTest() throws StackFull {
        for (int i = 0; i < STACK_SIZE; i++)
            stack.putOrThrow(i);

        stack.putOrThrow(10);
    }

    @Test
    public void popOkTest() throws StackEmpty {
        stack.putWithResult(10);
        assertThat(stack.popOrThrow(),is(equalTo(10)));
    }

    @Test(expected = StackEmpty.class)
    public void popEmptyTest() throws StackEmpty {
        stack.popOrThrow();
    }

    @Test
    public void popOrderTest() throws StackEmpty {
        for (int i = 0; i < STACK_SIZE; i++) {
            assertTrue(stack.putWithResult(i));
        }

        for (int i = STACK_SIZE-1; i >=0 ; i--) {
            assertThat(stack.popOrThrow(),is(equalTo(i)));
        }
    }
}
