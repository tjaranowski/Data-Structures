package pl.nosystems.java.datastructures.list;

@SuppressWarnings("unused")
public final class BareDoubleLinkedList<T> {
    @SuppressWarnings("SameParameterValue")
    public static class Node<T> {
        private Node previous;
        private final T data;
        private Node next;

        public Node(final T data) {
            previous = next = null;
            this.data = data;
        }

        public Node(final Node previous, final T data, final Node next) {
            this.previous = previous;
            this.data = data;
            this.next = next;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public T getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private Node firstNode;
    private Node lastNode;

    public Node getFirstNode() {
        return firstNode;
    }
    public void setFirstNode(Node firstNode) {
        this.firstNode = firstNode;
    }
    public Node getLastNode() {
        return lastNode;
    }
    public void setLastNode(Node lastNode) {
        this.lastNode = lastNode;
    }
}
