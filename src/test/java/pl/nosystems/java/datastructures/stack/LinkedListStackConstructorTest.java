package pl.nosystems.java.datastructures.stack;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public final class LinkedListStackConstructorTest {

    @Test
    public void defaultConstructorTest() {
        final LinkedListStack<Integer> stack = new LinkedListStack<>();
        assertFalse(stack.isFull());
        assertTrue(stack.isEmpty());
    }

    @Test(expected = StackEmpty.class)
    public void emptyAfterDefaultConstructorTest() throws StackEmpty {
        final LinkedListStack<Integer> stack = new LinkedListStack<>();
        stack.popOrThrow();
    }
}
