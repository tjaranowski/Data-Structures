package pl.nosystems.java.datastructures.tree;

import org.junit.Test;

public final class BinaryTreeConstructorTest {

    @Test
    public void noArgsConstructorTest() {
        new BinaryTree<Integer>();
    }

    @Test
    public void rootDataConstructorTest() {
        new BinaryTree<>(1);
    }

}
