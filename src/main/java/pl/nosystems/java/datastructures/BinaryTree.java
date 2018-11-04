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

    public static abstract class DataPlaceChooser<T> {
        abstract public DIR getDirection(final T currentNodeData);
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

        Node currentParent = null;
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
}
