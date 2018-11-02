package pl.nosystems.java.datastructures;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public final class DoubleLinkedListTest {
    private DoubleLinkedList<Integer> list;
    private final Integer VALUE = 1;

    @Before
    public void setUp() {
        list = new DoubleLinkedList<>();
    }

    @Test
    public void noElementsTest() {
        assertThat(list.getSize(),is(equalTo(0)));
        final DoubleLinkedList.Iterator<Integer> iterator = list.getIterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addOneElementTest() {
        list.add(VALUE);
        assertThat(list.getSize(),is(equalTo(1)));
        final DoubleLinkedList.Iterator<Integer> iterator = list.getIterator();
        assertTrue(iterator.hasNext());
        final Integer data = iterator.getNextAndMovePointerToSubsequentElement();
        assertThat(data,is(equalTo(VALUE)));
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addTwoElementsTest() {
        list.add(VALUE);
        list.add(VALUE+1);
        assertThat(list.getSize(),is(equalTo(2)));

        final DoubleLinkedList.Iterator<Integer> iterator = list.getIterator();
        assertTrue(iterator.hasNext());
        Integer data = iterator.getNextAndMovePointerToSubsequentElement();
        assertThat(data,is(equalTo(VALUE)));
        assertTrue(iterator.hasNext());

        data = iterator.getNextAndMovePointerToSubsequentElement();
        assertThat(data,is(equalTo(VALUE+1)));
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addHundredElementsTest() {
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        assertThat(list.getSize(),is(equalTo(100)));

        final DoubleLinkedList.Iterator<Integer> iterator = list.getIterator();
        for (int i = 0; i < 100; i++) {
            assertTrue(iterator.hasNext());
            final Integer data = iterator.getNextAndMovePointerToSubsequentElement();
            assertThat(data,is(equalTo(i)));
        }

        assertFalse(iterator.hasNext());
    }

    @Test
    public void addNoneRemoveOneTest() {
        assertFalse(list.removeFirst(VALUE));
        assertThat(list.getSize(),is(equalTo(0)));
        final DoubleLinkedList.Iterator<Integer> iterator = list.getIterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addOneRemoveOneTest() {
        list.add(VALUE);
        assertTrue(list.removeFirst(VALUE));

        assertThat(list.getSize(),is(equalTo(0)));
        final DoubleLinkedList.Iterator<Integer> iterator = list.getIterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addTwoRemoveOneTest() {
        list.add(VALUE);
        list.add(VALUE);
        assertTrue(list.removeFirst(VALUE));

        assertThat(list.getSize(),is(equalTo(1)));

        DoubleLinkedList.Iterator<Integer> iterator = list.getIterator();
        assertTrue(iterator.hasNext());

        assertTrue(list.removeFirst(VALUE));
        assertThat(list.getSize(),is(equalTo(0)));

        iterator = list.getIterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addTwoRemoveNonExistentTest() {
        list.add(VALUE);
        list.add(VALUE);
        list.removeFirst(VALUE+1);

        assertThat(list.getSize(),is(equalTo(2)));

        DoubleLinkedList.Iterator<Integer> iterator = list.getIterator();
        assertTrue(iterator.hasNext());

        iterator.getNextAndMovePointerToSubsequentElement();
        assertTrue(iterator.hasNext());

        iterator.getNextAndMovePointerToSubsequentElement();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addThreeRemoveNonExistentTest() {
        list.add(VALUE);
        list.add(VALUE);
        list.add(VALUE);
        list.removeFirst(VALUE+1);

        assertThat(list.getSize(),is(equalTo(3)));

        DoubleLinkedList.Iterator<Integer> iterator = list.getIterator();
        assertTrue(iterator.hasNext());

        iterator.getNextAndMovePointerToSubsequentElement();
        assertTrue(iterator.hasNext());

        iterator.getNextAndMovePointerToSubsequentElement();
        assertTrue(iterator.hasNext());

        iterator.getNextAndMovePointerToSubsequentElement();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addHundredRemoveHundredTest1() {
        for (int i = 0; i < 100; i++) {
            list.add(VALUE);
        }

        for (int i = 0; i < 100; i++) {
            assertTrue(list.removeFirst(VALUE));
        }

        assertThat(list.getSize(),is(equalTo(0)));

        final DoubleLinkedList.Iterator<Integer> iterator = list.getIterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addHundredRemoveHundredTest2() {
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        for (int i = 99; i >= 0; i--) {
            list.removeFirst(i);
        }

        assertThat(list.getSize(),is(equalTo(0)));

        final DoubleLinkedList.Iterator<Integer> iterator = list.getIterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addHundredRemoveHundredTest3() {
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        for (int i = 50; i >= 0; i--) {
            list.removeFirst(i);
        }
        for (int i = 51; i < 100 ; i++) {
            list.removeFirst(i);
        }

        assertThat(list.getSize(),is(equalTo(0)));

        final DoubleLinkedList.Iterator<Integer> iterator = list.getIterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addThreeNullsRemoveThreeNullsTest() {
        list.add(null);
        list.add(null);
        list.add(null);
        list.removeFirst(null);
        list.removeFirst(null);
        list.removeFirst(null);

        assertThat(list.getSize(),is(equalTo(0)));

        DoubleLinkedList.Iterator<Integer> iterator = list.getIterator();
        assertFalse(iterator.hasNext());
    }

    @Test(timeout = 2000)
    public void addThreeNullsRemoveOneValueTest() {
        list.add(null);
        list.add(null);
        list.add(null);

        assertFalse(list.removeFirst(VALUE));

        assertThat(list.getSize(),is(equalTo(3)));

        final DoubleLinkedList.Iterator<Integer> iterator = list.getIterator();
        assertTrue(iterator.hasNext());

        iterator.getNextAndMovePointerToSubsequentElement();
        assertTrue(iterator.hasNext());

        iterator.getNextAndMovePointerToSubsequentElement();
        assertTrue(iterator.hasNext());

        iterator.getNextAndMovePointerToSubsequentElement();
        assertFalse(iterator.hasNext());
    }
}
