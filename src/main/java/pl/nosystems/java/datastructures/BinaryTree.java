package pl.nosystems.java.datastructures;

@SuppressWarnings("WeakerAccess")
public final class BinaryTree<T> {
    private class Node {
        private T data;
        private Node left;
        private Node right;

        Node(final T data) {
            this.data = data;
            left = right = null;
        }
    }
    @SuppressWarnings("unused")
    public enum DIR {LEFT,RIGHT}

    @SuppressWarnings("unused")
    public static abstract class DataPlaceChooser<T> {
        abstract public DIR getDirection(final T currentNodeData);
    }

    interface BinaryTreeTraversal<T> {
        boolean hasLeftChild();
        boolean hasRightChild();

        T getData();

        void moveToLeftChildOrThrow();
        void moveToRightChildOrThrow();
    }

    private Node root;

    public BinaryTree() {}

    public BinaryTree(final T root) {
        this.root = new Node(root);
    }

    public void put(final T data, final DataPlaceChooser<T> placeChooser) {

        if(root == null) {
            root = new Node(data);
            return;
        }

        Node currentParent;
        Node current = root;
        while(true) {
            final DIR dir = placeChooser.getDirection(current.data);

            currentParent = current;
            if(dir == DIR.LEFT) {
                current = current.left;
            } else {
                current = current.right;
            }

            if(current == null) {
                final Node newNode = new Node(data);
                if(dir == DIR.LEFT) {
                    currentParent.left = newNode;
                } else {
                    currentParent.right = newNode;
                }
                return;
            }
        }

    }

    public BinaryTreeTraversal<T> getTraversal() {
        if(root == null) {
            return null;
        }

        return new BinaryTreeTraversal<T>() {
            private Node current = root;

            @Override
            public boolean hasLeftChild() {
                return current.left != null;
            }

            @Override
            public boolean hasRightChild() {
                return current.right != null;
            }

            @Override
            public T getData() {
                return current.data;
            }

            @Override
            public void moveToLeftChildOrThrow() {
                if(!hasLeftChild()) {
                    throw new RuntimeException("Left child does not exist!");
                }
                current = current.left;
            }

            @Override
            public void moveToRightChildOrThrow() {
                if(!hasRightChild()) {
                    throw new RuntimeException("Right child does not exist!");
                }
                current = current.right;
            }
        };
    }
}
