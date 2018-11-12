package pl.nosystems.java.datastructures.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public final class BareDoubleLinkedListOperationsTest {

    private BareDoubleLinkedList<Integer> list;

    @Before
    public void setUp() {
        list = new BareDoubleLinkedList<>();
    }

    private <T> void assertListContainsOnlyTwoElements(final BareDoubleLinkedList<T> list) {
        assertNotEquals(list.getFirstNode(),list.getLastNode());

        assertEquals(list.getFirstNode().getNext(),list.getLastNode());
        assertEquals(list.getLastNode().getPrevious(),list.getFirstNode());
    }

    @Test
    public void cleanListTest() {
        assertNull(list.getFirstNode());
        assertNull(list.getLastNode());
    }

    @Test
    public void removeOnEmptyTest() {
        assertFalse(BareDoubleLinkedListOperations.removeFirst(list,1));

        assertNull(list.getFirstNode());
        assertNull(list.getLastNode());
    }

    @Test
    public void addNullInMiddleRemoveNullTest() {
        BareDoubleLinkedListOperations.add(list,1);
        BareDoubleLinkedListOperations.add(list,null);
        BareDoubleLinkedListOperations.add(list,2);

        assertTrue(BareDoubleLinkedListOperations.removeFirst(list,null));

        assertListContainsOnlyTwoElements(list);

        assertNull(list.getFirstNode().getPrevious());
        assertNull(list.getLastNode().getNext());

        assertThat(list.getFirstNode().getData(),is(equalTo(1)));
        assertThat(list.getLastNode().getData(),is(equalTo(2)));
    }

    @Test
    public void addNullInMiddleRemoveLastTest() {
        BareDoubleLinkedListOperations.add(list,1);
        BareDoubleLinkedListOperations.add(list,null);
        BareDoubleLinkedListOperations.add(list,2);

        assertTrue(BareDoubleLinkedListOperations.removeFirst(list,2));

        assertListContainsOnlyTwoElements(list);

        assertNull(list.getFirstNode().getPrevious());
        assertNull(list.getLastNode().getNext());

        assertThat(list.getFirstNode().getData(),is(equalTo(1)));
        assertThat(list.getLastNode().getData(),is(equalTo(null)));
    }

    @Test
    public void addOneTest() {
        BareDoubleLinkedListOperations.add(list,1);

        assertEquals(list.getFirstNode(),list.getLastNode());
        assertNull(list.getFirstNode().getPrevious());
        assertNull(list.getFirstNode().getNext());
    }

    @Test
    public void addTwoTest() {
        BareDoubleLinkedListOperations.add(list,1);
        BareDoubleLinkedListOperations.add(list,2);

        assertNotEquals(list.getFirstNode(),list.getLastNode());

        assertNull(list.getFirstNode().getPrevious());
        assertNull(list.getLastNode().getNext());

        assertEquals(list.getFirstNode().getNext(),list.getLastNode());
        assertEquals(list.getLastNode().getPrevious(),list.getFirstNode());
    }

    @Test
    public void addThreeTest() {
        BareDoubleLinkedListOperations.add(list,1);
        BareDoubleLinkedListOperations.add(list,2);
        BareDoubleLinkedListOperations.add(list,3);

        assertNotEquals(list.getFirstNode(),list.getLastNode());

        assertNull(list.getFirstNode().getPrevious());
        assertNull(list.getLastNode().getNext());

        assertEquals(list.getFirstNode().getNext(),list.getLastNode().getPrevious());

        assertEquals(list.getFirstNode().getNext().getNext(),list.getLastNode());
        assertEquals(list.getLastNode().getPrevious().getPrevious(),list.getFirstNode());

        assertThat(list.getFirstNode().getData(),is(equalTo(1)));
        assertThat(list.getFirstNode().getNext().getData(),is(equalTo(2)));
        assertThat(list.getLastNode().getData(),is(equalTo(3)));
    }

    @Test
    public void addThreeRemoveNonExistentTest() {
        BareDoubleLinkedListOperations.add(list,1);
        BareDoubleLinkedListOperations.add(list,2);
        BareDoubleLinkedListOperations.add(list,3);

        assertFalse(BareDoubleLinkedListOperations.removeFirst(list, 4));
    }

    @Test
    public void addOneRemoveOneTest() {
        BareDoubleLinkedListOperations.add(list, 1);
        assertTrue(BareDoubleLinkedListOperations.removeFirst(list, 1));

        assertNull(list.getFirstNode());
        assertNull(list.getLastNode());
    }

    @Test
    public void addOneRemoveNoneTest() {
        BareDoubleLinkedListOperations.add(list, 1);
        assertFalse(BareDoubleLinkedListOperations.removeFirst(list, 2));

        assertNotNull(list.getFirstNode());

        assertThat(list.getFirstNode().getData(),is(equalTo(1)));

        assertEquals(list.getFirstNode(),list.getLastNode());
        assertNull(list.getFirstNode().getPrevious());
        assertNull(list.getFirstNode().getNext());
    }

    @Test
    public void addTwoRemoveLaterTest() {
        BareDoubleLinkedListOperations.add(list, 1);
        BareDoubleLinkedListOperations.add(list, 2);

        assertTrue(BareDoubleLinkedListOperations.removeFirst(list, 2));

        assertThat(list.getFirstNode().getData(),is(equalTo(1)));

        assertEquals(list.getFirstNode(),list.getLastNode());
        assertNull(list.getFirstNode().getPrevious());
        assertNull(list.getFirstNode().getNext());
    }


    @Test
    public void addTwoRemoveFormerTest() {
        BareDoubleLinkedListOperations.add(list, 1);
        BareDoubleLinkedListOperations.add(list, 2);

        assertTrue(BareDoubleLinkedListOperations.removeFirst(list, 1));

        assertThat(list.getFirstNode().getData(),is(equalTo(2)));

        assertEquals(list.getFirstNode(),list.getLastNode());
        assertNull(list.getFirstNode().getPrevious());
        assertNull(list.getFirstNode().getNext());
    }

    @Test
    public void addThreeRemoveLastTest() {
        BareDoubleLinkedListOperations.add(list, 1);
        BareDoubleLinkedListOperations.add(list, 2);
        BareDoubleLinkedListOperations.add(list, 3);

        assertTrue(BareDoubleLinkedListOperations.removeFirst(list, 3));

        assertThat(list.getFirstNode().getData(),is(equalTo(1)));
        assertThat(list.getFirstNode().getNext().getData(),is(equalTo(2)));

        assertNull(list.getFirstNode().getPrevious());
        assertNull(list.getLastNode().getNext());

        assertEquals(list.getFirstNode().getNext(),list.getLastNode());
        assertEquals(list.getLastNode().getPrevious(),list.getFirstNode());
    }

    @Test
    public void addThreeRemoveEdgesTest() {
        BareDoubleLinkedListOperations.add(list, 1);
        BareDoubleLinkedListOperations.add(list, 2);
        BareDoubleLinkedListOperations.add(list, 3);

        assertTrue(BareDoubleLinkedListOperations.removeFirst(list, 3));
        assertTrue(BareDoubleLinkedListOperations.removeFirst(list, 1));

        assertThat(list.getFirstNode().getData(),is(equalTo(2)));

        assertEquals(list.getFirstNode(),list.getLastNode());
        assertNull(list.getFirstNode().getPrevious());
        assertNull(list.getFirstNode().getNext());
    }

    @Test
    public void addThreeRemoveMiddleTest() {
        BareDoubleLinkedListOperations.add(list, 1);
        BareDoubleLinkedListOperations.add(list, 2);
        BareDoubleLinkedListOperations.add(list, 3);

        assertTrue(BareDoubleLinkedListOperations.removeFirst(list, 2));

        assertThat(list.getFirstNode().getData(),is(equalTo(1)));
        assertThat(list.getFirstNode().getNext().getData(),is(equalTo(3)));

        assertNull(list.getFirstNode().getPrevious());
        assertNull(list.getLastNode().getNext());

        assertEquals(list.getFirstNode().getNext(),list.getLastNode());
        assertEquals(list.getLastNode().getPrevious(),list.getFirstNode());
    }
}
