package pl.nosystems.java.datastructures.stack;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public final class LinkedListStackTest {

    private LinkedListStack<Integer> stack;

    @Before
    public void setUp() {
        stack = new LinkedListStack<>();
    }

    @Test
    public void pushOneElementTest() throws StackFull, StackEmpty {
        stack.putOrThrow(10);

        assertFalse(stack.isFull());
        assertFalse(stack.isEmpty());

        assertThat(stack.popOrThrow(),is(equalTo(10)));

        assertFalse(stack.isFull());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void pushTwoElementsTest() throws StackFull, StackEmpty {
        stack.putOrThrow(10);
        stack.putOrThrow(20);

        assertFalse(stack.isFull());
        assertFalse(stack.isEmpty());

        assertThat(stack.popOrThrow(),is(equalTo(20)));

        assertFalse(stack.isFull());
        assertFalse(stack.isEmpty());

        assertThat(stack.popOrThrow(),is(equalTo(10)));

        assertFalse(stack.isFull());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void putWithResultOneElementTest() throws StackEmpty {
        assertTrue(stack.putWithResult(10));

        assertFalse(stack.isFull());
        assertFalse(stack.isEmpty());

        assertThat(stack.popOrThrow(),is(equalTo(10)));

        assertFalse(stack.isFull());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void putWithResultTwoElementsTest() throws StackEmpty {
        assertTrue(stack.putWithResult(10));
        assertTrue(stack.putWithResult(20));

        assertFalse(stack.isFull());
        assertFalse(stack.isEmpty());

        assertThat(stack.popOrThrow(),is(equalTo(20)));

        assertFalse(stack.isFull());
        assertFalse(stack.isEmpty());

        assertThat(stack.popOrThrow(),is(equalTo(10)));

        assertFalse(stack.isFull());
        assertTrue(stack.isEmpty());
    }
}
