package pl.nosystems.java.datastructures.list;

import org.junit.Before;
import org.junit.Test;
import pl.nosystems.java.datastructures.Iterator;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public final class DoubleLinkedListTest {
    private DoubleLinkedList<Integer> list;
    private final Integer VALUE = 1;

    @Before
    public void setUp() {
        list = new DoubleLinkedList<>();
    }


    @Test(expected = NoSuchElementException.class)
    public void givenNoElementsWhenRequestedFirstShouldThrow() {

        // Given - When - Then
        list.getFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void givenNoElementsWhenRequestedLastShouldThrow() {

        // Given - When - Then
        list.getLast();
    }

    @Test(expected = NoSuchElementException.class)
    public void givenNoElementsWhenRequestedToRemoveFirstShouldThrow() {

        // Given - When - Then
        list.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void givenNoElementsWhenRequestedToRemoveLastShouldThrow() {

        // Given - When - Then
        list.removeLast();
    }

    @Test(expected = NoSuchElementException.class)
    public void iterateAfterHasNextReturnsFalseOnEmptyListTest() {
        final Iterator<Integer> iterator = list.getIterator();
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void iterateAfterHasNextReturnsFalseOnNonEmptyListTest() {
        list.add(VALUE);
        final Iterator<Integer> iterator = list.getIterator();
        iterator.next();
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void addThreeRemoveInMiddleIterateAfterHasNextReturnedFalseTest() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.removeFirstFound(2));

        final Iterator<Integer> iterator = list.getIterator();
        iterator.next();
        iterator.next();
        iterator.next();
    }


    @Test
    public void noElementsTest() {
        assertThat(list.getSize(), is(equalTo(0)));
        final Iterator<Integer> iterator = list.getIterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void givenOneElementWhenRequestedFirstShouldReturnIt() {

        // Given
        Integer element = 1;
        list.add(element);

        // When
        Integer elementReturned = list.getFirst();

        // Then verify
        assertSame(element, elementReturned);
    }

    @Test
    public void givenOneElementWhenRequestedLastShouldReturnIt() {

        // Given
        Integer element = 1;
        list.add(element);

        // When
        Integer elementReturned = list.getLast();

        // Then verify
        assertSame(element, elementReturned);
    }

    @Test
    public void givenMultipleElementsWhenRequestedByPositionShouldReturnThem() {

        // Given
        Integer element1 = 1;
        Integer element2 = 2;
        Integer element3 = 3;

        list.add(element1);
        list.add(element2);
        list.add(element3);


        // When
        Integer first = list.getFirst();
        Integer last = list.getLast();

        // Then verify
        assertSame(element1, first);
        assertSame(element3, last);
    }

    @Test
    public void givenMultipleElementsWhenRemovedByRemoveLastShouldReallyRemoveLast() {

        // Given
        Integer element1 = 1;
        Integer element2 = 2;
        Integer element3 = 3;

        list.add(element1);
        list.add(element2);
        list.add(element3);


        // When
        Integer removed1 = list.removeLast();
        Integer removed2 = list.removeLast();
        Integer removed3 = list.removeLast();

        // Then verify
        assertSame(element3, removed1);
        assertSame(element2, removed2);
        assertSame(element1, removed3);
        assertTrue(list.isEmpty());
    }

    @Test
    public void givenMultipleElementsWhenRemovedByRemoveFirstShouldReallyRemoveFirst() {

        // Given
        Integer element1 = 1;
        Integer element2 = 2;
        Integer element3 = 3;

        list.add(element1);
        list.add(element2);
        list.add(element3);


        // When
        Integer removed1 = list.removeFirst();
        Integer removed2 = list.removeFirst();
        Integer removed3 = list.removeFirst();

        // Then verify
        assertSame(element1, removed1);
        assertSame(element2, removed2);
        assertSame(element3, removed3);
        assertTrue(list.isEmpty());
    }

    @Test
    public void addFirstShouldAddFirstTest() {

        // Given
        Integer element1 = 1;
        Integer element2 = 2;
        Integer element3 = 3;


        // When
        list.addFirst(element1);
        list.addFirst(element2);
        list.addFirst(element3);


        // Then Verify
        Integer first = list.getFirst();
        Integer last = list.getLast();

        assertSame(element3, first);
        assertSame(element1, last);
        assertFalse(list.isEmpty());
        assertEquals(3, list.getSize());

        Iterator<Integer> iterator = list.getIterator();
        assertSame(element3, iterator.next());
        assertSame(element2, iterator.next());
        assertSame(element1, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addLastShouldAddLastTest() {

        // Given
        Integer element1 = 1;
        Integer element2 = 2;
        Integer element3 = 3;


        // When
        list.addLast(element1);
        list.addLast(element2);
        list.addLast(element3);


        // Then Verify
        Integer first = list.getFirst();
        Integer last = list.getLast();

        assertSame(element1, first);
        assertSame(element3, last);
        assertFalse(list.isEmpty());
        assertEquals(3, list.getSize());

        Iterator<Integer> iterator = list.getIterator();
        assertSame(element1, iterator.next());
        assertSame(element2, iterator.next());
        assertSame(element3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addOneElementTest() {
        list.add(VALUE);
        assertThat(list.getSize(), is(equalTo(1)));
        final Iterator<Integer> iterator = list.getIterator();
        assertTrue(iterator.hasNext());
        final Integer data = iterator.next();
        assertThat(data, is(equalTo(VALUE)));
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addTwoElementsTest() {
        list.add(VALUE);
        list.add(VALUE + 1);
        assertThat(list.getSize(), is(equalTo(2)));

        final Iterator<Integer> iterator = list.getIterator();
        assertTrue(iterator.hasNext());
        Integer data = iterator.next();
        assertThat(data, is(equalTo(VALUE)));
        assertTrue(iterator.hasNext());

        data = iterator.next();
        assertThat(data, is(equalTo(VALUE + 1)));
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addHundredElementsTest() {
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        assertThat(list.getSize(), is(equalTo(100)));

        final Iterator<Integer> iterator = list.getIterator();
        for (int i = 0; i < 100; i++) {
            assertTrue(iterator.hasNext());
            final Integer data = iterator.next();
            assertThat(data, is(equalTo(i)));
        }

        assertFalse(iterator.hasNext());
    }

    @Test
    public void addNoneRemoveOneTest() {
        assertFalse(list.removeFirstFound(VALUE));
        assertThat(list.getSize(), is(equalTo(0)));
        final Iterator<Integer> iterator = list.getIterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addOneRemoveOneTest() {
        list.add(VALUE);
        assertTrue(list.removeFirstFound(VALUE));

        assertThat(list.getSize(), is(equalTo(0)));
        final Iterator<Integer> iterator = list.getIterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addTwoRemoveOneTest() {
        list.add(VALUE);
        list.add(VALUE);
        assertTrue(list.removeFirstFound(VALUE));

        assertThat(list.getSize(), is(equalTo(1)));

        Iterator<Integer> iterator = list.getIterator();
        assertTrue(iterator.hasNext());

        assertTrue(list.removeFirstFound(VALUE));
        assertThat(list.getSize(), is(equalTo(0)));

        iterator = list.getIterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addTwoRemoveNonExistentTest() {
        list.add(VALUE);
        list.add(VALUE);
        assertFalse(list.removeFirstFound(VALUE + 1));

        assertThat(list.getSize(), is(equalTo(2)));

        Iterator<Integer> iterator = list.getIterator();
        assertTrue(iterator.hasNext());

        iterator.next();
        assertTrue(iterator.hasNext());

        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addThreeRemoveNonExistentTest() {
        list.add(VALUE);
        list.add(VALUE);
        list.add(VALUE);
        assertFalse(list.removeFirstFound(VALUE + 1));

        assertThat(list.getSize(), is(equalTo(3)));

        Iterator<Integer> iterator = list.getIterator();
        assertTrue(iterator.hasNext());

        iterator.next();
        assertTrue(iterator.hasNext());

        iterator.next();
        assertTrue(iterator.hasNext());

        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addHundredRemoveHundredTest1() {
        for (int i = 0; i < 100; i++) {
            list.add(VALUE);
        }

        for (int i = 0; i < 100; i++) {
            assertTrue(list.removeFirstFound(VALUE));
        }

        assertThat(list.getSize(), is(equalTo(0)));

        final Iterator<Integer> iterator = list.getIterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addHundredRemoveHundredTest2() {
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        for (int i = 99; i >= 0; i--) {
            assertTrue(list.removeFirstFound(i));
        }

        assertThat(list.getSize(), is(equalTo(0)));

        final Iterator<Integer> iterator = list.getIterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addHundredRemoveHundredTest3() {
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        for (int i = 50; i >= 0; i--) {
            assertTrue(list.removeFirstFound(i));
        }
        for (int i = 51; i < 100; i++) {
            assertTrue(list.removeFirstFound(i));
        }

        assertThat(list.getSize(), is(equalTo(0)));

        final Iterator<Integer> iterator = list.getIterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addThreeNullsRemoveThreeNullsTest() {
        list.add(null);
        list.add(null);
        list.add(null);
        assertTrue(list.removeFirstFound(null));
        assertTrue(list.removeFirstFound(null));
        assertTrue(list.removeFirstFound(null));

        assertThat(list.getSize(), is(equalTo(0)));

        Iterator<Integer> iterator = list.getIterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void addThreeRemoveInMiddleTest() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.removeFirstFound(2));

        assertThat(list.getSize(), is(equalTo(2)));

        final Iterator<Integer> iterator = list.getIterator();

        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(equalTo(1)));

        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(equalTo(3)));

        assertFalse(iterator.hasNext());
    }

    @Test(timeout = 2000)
    public void addThreeNullsRemoveOneValueTest() {
        list.add(null);
        list.add(null);
        list.add(null);

        assertFalse(list.removeFirstFound(VALUE));

        assertThat(list.getSize(), is(equalTo(3)));

        final Iterator<Integer> iterator = list.getIterator();
        assertTrue(iterator.hasNext());

        iterator.next();
        assertTrue(iterator.hasNext());

        iterator.next();
        assertTrue(iterator.hasNext());

        iterator.next();
        assertFalse(iterator.hasNext());
    }
}
