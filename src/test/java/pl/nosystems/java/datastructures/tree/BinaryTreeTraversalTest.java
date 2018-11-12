package pl.nosystems.java.datastructures.tree;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public final class BinaryTreeTraversalTest {

    private BinaryTree<Integer> binaryTree;

    @Before
    public void setUp() {
        binaryTree = new BinaryTree<>();
    }

    @Test
    public void nullTraversalIfRootIsNullTest() {
        assertNull(binaryTree.getTraversal());
    }

    @Test
    public void nonNullTraversalIfRootIsNotNullTest() {
        binaryTree.put(0,null);

        final BinaryTree.BinaryTreeTraversal<Integer> traversal = binaryTree.getTraversal();
        assertNotNull(traversal);
        assertFalse(traversal.hasLeftChild());
        assertFalse(traversal.hasRightChild());
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = RuntimeException.class)
    public void throwIfLeftChildNotAvailableTest() {
        binaryTree.put(0,null);

        final BinaryTree.BinaryTreeTraversal<Integer> traversal = binaryTree.getTraversal();
        traversal.moveToLeftChildOrThrow();
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = RuntimeException.class)
    public void throwIfRightChildNotAvailableTest() {
        binaryTree.put(0,null);

        final BinaryTree.BinaryTreeTraversal<Integer> traversal = binaryTree.getTraversal();
        traversal.moveToRightChildOrThrow();
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void moveToLeftChildTest() {
        binaryTree.put(0,null);
        binaryTree.put(1, new BinaryTree.DataPlaceChooser<Integer>() {
            @Override
            public BinaryTree.DIR getDirection(Integer currentNodeData) {
                return BinaryTree.DIR.LEFT;
            }
        });
        binaryTree.put(2, new BinaryTree.DataPlaceChooser<Integer>() {
            @Override
            public BinaryTree.DIR getDirection(Integer currentNodeData) {
                return BinaryTree.DIR.RIGHT;
            }
        });

        final BinaryTree.BinaryTreeTraversal<Integer> traversal = binaryTree.getTraversal();
        assertTrue(traversal.hasRightChild());
        assertTrue(traversal.hasLeftChild());
        assertThat(traversal.getData(),is(equalTo(0)));

        traversal.moveToLeftChildOrThrow();

        assertThat(traversal.getData(),is(equalTo(1)));
        assertFalse(traversal.hasLeftChild());
        assertFalse(traversal.hasRightChild());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void moveToRightChildTest() {
        binaryTree.put(0,null);
        binaryTree.put(1, new BinaryTree.DataPlaceChooser<Integer>() {
            @Override
            public BinaryTree.DIR getDirection(Integer currentNodeData) {
                return BinaryTree.DIR.LEFT;
            }
        });
        binaryTree.put(2, new BinaryTree.DataPlaceChooser<Integer>() {
            @Override
            public BinaryTree.DIR getDirection(Integer currentNodeData) {
                return BinaryTree.DIR.RIGHT;
            }
        });

        final BinaryTree.BinaryTreeTraversal<Integer> traversal = binaryTree.getTraversal();
        assertTrue(traversal.hasRightChild());
        assertTrue(traversal.hasLeftChild());
        assertThat(traversal.getData(),is(equalTo(0)));

        traversal.moveToRightChildOrThrow();

        assertThat(traversal.getData(),is(equalTo(2)));
        assertFalse(traversal.hasLeftChild());
        assertFalse(traversal.hasRightChild());
    }
}
