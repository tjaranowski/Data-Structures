package pl.nosystems.java.datastructures;

@SuppressWarnings("WeakerAccess")
public final class DoubleLinkedList<T> {
    @SuppressWarnings("SameParameterValue")
    private class Node {
        private Node previous;
        private final T data;
        private Node next;

        Node(final T data) {
            previous = next = null;
            this.data = data;
        }

        Node(final Node previous, final T data, final Node next) {
            this.previous = previous;
            this.data = data;
            this.next = next;
        }
    }

    public interface Iterator<T> {
        boolean hasNext();
        T getNextAndMovePointerToSubsequentElement();
    }

    private Node root;
    private Node lastNode;
    private int size = 0;

    private void afterSuccessfullyAddedOneElement() {
        size++;
    }

    private void addFirstElement(final T data) {
        lastNode = root = new Node(data);
    }

    private void addNextElement(final T data) {
        final Node newNode = new Node(lastNode,data,null);
        lastNode.next = newNode;
        lastNode = newNode;
    }

    private void removeNodeThatHasOnlyNext(final Node node, final Node next) {
        next.previous = null;
        node.next = null;
        root = next;
    }

    private void removeNodeThatHasOnlyPrevious(final Node node,final Node prev) {
        lastNode = lastNode.previous;

        prev.next = null;
        node.previous = null;
    }

    private void removeNodeThatIsInMiddle(final Node prev, final Node next) {
        prev.next = next;
        next.previous = prev;
    }

    private void removeNode(final Node node) {
        Node prev = node.previous;
        Node next = node.next;

        size--;

        if(prev == null && next == null) {
            root = null;
            return;
        }

        if(prev == null) {
            removeNodeThatHasOnlyNext(node,next);

        } else if(next == null) {
            removeNodeThatHasOnlyPrevious(node,prev);

        } else {
            removeNodeThatIsInMiddle(prev,next);

        }
    }

    public void add(final T data) {
        if(root == null) {
            addFirstElement(data);
        } else {
            addNextElement(data);
        }
        afterSuccessfullyAddedOneElement();
    }

    public boolean removeFirst(final T data) {
        if(root == null) {
            return false;
        }

        Node currentNode = root;
        while(currentNode != null) {
            if(currentNode.data == null) {
                if(data == null) {
                    removeNode(currentNode);
                    return true;
                }
            } else {
                if(currentNode.data.equals(data)) {
                    removeNode(currentNode);
                    return true;
                }
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public Iterator<T> getIterator() {
        return new Iterator<T>() {
            private boolean isBeforeRoot = true;
            private Node currentNode = root;

            @Override
            public boolean hasNext() {

                if(isBeforeRoot) {
                    return root!=null;
                }

                return currentNode.next != null;
            }

           
            @Override
            public T getNextAndMovePointerToSubsequentElement() {
                if(isBeforeRoot) {
                    isBeforeRoot = false;
                    return currentNode.data;
                }
                currentNode = currentNode.next;
                return currentNode.data;
            }
        };
    }
}
