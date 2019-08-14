package pl.nosystems.java.datastructures.list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class LinkedListTest {
    private LinkedList<Integer> linkedList;

    @Before
    public void setUp() {
        linkedList = new LinkedList<>();
    }

    @Test
    public void givenNewlyCreatedLinkedListShouldBeEmpty() {

        // Then verify
        assertTrue(linkedList.isEmpty());

        assertFalse(linkedList.contains(0));
        assertFalse(linkedList.contains(1));
        assertFalse(linkedList.contains(Integer.MAX_VALUE));
        assertFalse(linkedList.contains(Integer.MIN_VALUE));

    }

    @Test
    public void whenElementAddedContainsOnSameElementShouldReturnTrue() {

        // When
        linkedList.add(1);

        // Then verify
        assertTrue(linkedList.contains(1));

        assertFalse(linkedList.isEmpty());
    }

    @Test
    public void whenAddedSeveralElementsContainsShouldReturnTrueForEachOfThem() {

        // When
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(Integer.MIN_VALUE);
        linkedList.add(Integer.MAX_VALUE);

        // Then verify
        assertTrue(linkedList.contains(1));
        assertTrue(linkedList.contains(2));
        assertTrue(linkedList.contains(Integer.MAX_VALUE));
        assertTrue(linkedList.contains(Integer.MIN_VALUE));

        assertFalse(linkedList.isEmpty());
    }

    @Test
    public void whenAddedAndRemovedSameElementContainsShouldReturnFalse() {

        // When
        linkedList.add(1);
        boolean removed = linkedList.remove(1);

        // Then verify
        assertTrue(removed);
        assertFalse(linkedList.contains(1));
        assertTrue(linkedList.isEmpty());
    }

    @Test
    public void whenRemovingElementThatIsNotThereRemoveShouldReturnFalse() {

        // When
        boolean removed = linkedList.remove(0);

        // Then verify
        assertFalse(removed);
    }

    @Test
    public void whenAddedAndRemovedSeveralElementsStateShouldBeEmpty() {

        // When
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(Integer.MIN_VALUE);
        linkedList.add(Integer.MAX_VALUE);

        boolean removed1 = linkedList.remove(1);
        boolean removed2 = linkedList.remove(2);
        boolean removed3 = linkedList.remove(Integer.MIN_VALUE);
        boolean removed4 = linkedList.remove(Integer.MAX_VALUE);

        // Then verify
        assertTrue(linkedList.isEmpty());

        assertTrue(removed1);
        assertTrue(removed2);
        assertTrue(removed3);
        assertTrue(removed4);

        assertFalse(linkedList.contains(1));
        assertFalse(linkedList.contains(2));
        assertFalse(linkedList.contains(Integer.MAX_VALUE));
        assertFalse(linkedList.contains(Integer.MIN_VALUE));
    }
}
