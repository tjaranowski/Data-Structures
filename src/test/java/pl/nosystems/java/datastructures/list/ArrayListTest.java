package pl.nosystems.java.datastructures.list;

import org.junit.Before;
import org.junit.Test;
import pl.nosystems.java.datastructures.Iterator;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class ArrayListTest {
    private ArrayList<Integer> arrayList;

    @Before
    public void setUp() {
        arrayList = new ArrayList<>();
    }

    @Test
    public void givenJustCreatedShouldBeEmpty() {

        // Then verify
        assertEmpty(arrayList);
    }

    @Test
    public void whenElementAddedShouldNotBeEmpty() {

        // When
        arrayList.add(10);

        // Then verify
        Iterator<Integer> iterator = arrayList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(10), iterator.next());
    }

    @Test
    public void givenOneElementInListWhenQueriedIfContainsShouldReturnTrue() {

        // Given
        arrayList.add(10);

        // Then verify
        assertTrue(arrayList.contains(10));
    }

    @Test
    public void givenSmallInitialCapacityShouldExpandIfNeeded() {

        // Given
        ArrayList<Integer> arrayList = new ArrayList<>(1);

        // When
        arrayList.add(1);
        arrayList.add(2);

        // Then verify
        assertTrue(arrayList.contains(1));
        assertTrue(arrayList.contains(2));

        Iterator<Integer> iterator = arrayList.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), anyOf(is(1), is(2)));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), anyOf(is(1), is(2)));

        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenAddedAndRemovedElementShouldBeEmpty() {

        // When
        arrayList.add(1);
        boolean removed = arrayList.remove(1);

        // Then verify
        assertEmpty(arrayList);
        assertTrue(removed);
    }

    @Test
    public void givenEmptyWhenRemoveCalledShouldReturnFalse() {

        // When
        boolean removed = arrayList.remove(0);

        // Then verify
        assertFalse(removed);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInitialCapacityPassedIsZeroShouldThrow() {

        // When
        new ArrayList<>(0);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextCalledOnIteratorThatNasNoNextExceptionShouldBeThrown() {

        // When
        arrayList.iterator().next();
    }


    private void assertEmpty(ArrayList<Integer> arrayList) {
        assertFalse(arrayList.iterator().hasNext());
        assertFalse(arrayList.contains(0));
        assertFalse(arrayList.contains(10));
        assertFalse(arrayList.contains(Integer.MAX_VALUE));
        assertFalse(arrayList.contains(Integer.MIN_VALUE));
    }
}
