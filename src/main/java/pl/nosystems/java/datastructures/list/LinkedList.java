package pl.nosystems.java.datastructures.list;

import java.util.Objects;

/**
 * Collection that holds items in linked list.
 *
 * @param <E> Elements that this data structure will hold
 */
public class LinkedList<E> {
    private LinkedListNode<E> firstNode;

    /**
     * Adds element to collection.
     *
     * @param element Element to be added.
     */
    public void add(E element) {
        if (firstNode == null) {
            firstNode = new LinkedListNode<>(element);
            return;
        }

        firstNode = new LinkedListNode<>(element, firstNode);
    }

    /**
     * Removes element from collection if found.
     *
     * @param element element to be removed.
     * @return true if element was found and removed, false otherwise
     */
    public boolean remove(E element) {
        LinkedListNode<E> previousNode = null;
        LinkedListNode<E> currentNode = firstNode;

        while (currentNode != null) {

            E currentNodeData = currentNode.getData();

            if (Objects.equals(currentNodeData, element)) {

                if (previousNode == null) {
                    firstNode = firstNode.getNextNode();
                    return true;
                }

                previousNode.setNextNode(currentNode.getNextNode());
                return true;
            }

            previousNode = currentNode;
            currentNode = currentNode.getNextNode();
        }
        return false;
    }

    /**
     * Checks if element is in collection.
     *
     * @param element Element to be searched for.
     * @return true if element is found in collection, false otherwise
     */
    public boolean contains(E element) {

        LinkedListNode<E> currentNode = firstNode;

        while (currentNode != null) {

            E currentNodeData = currentNode.getData();

            if (Objects.equals(currentNodeData, element)) {
                return true;
            }

            currentNode = currentNode.getNextNode();
        }
        return false;
    }

    /**
     * Tells if collection is empty.
     *
     * @return true if collection is empty, false otherwise
     */
    public boolean isEmpty() {
        return firstNode == null;
    }

    /**
     * Internal class that represents node for this linked list.
     *
     * @param <E> Elements that this data structure will hold
     */
    private static class LinkedListNode<E> {
        private final E data;
        private LinkedListNode<E> nextNode;

        LinkedListNode(E data) {
            this(data, null);
        }

        LinkedListNode(E data, LinkedListNode<E> nextNode) {
            this.data = data;
            this.nextNode = nextNode;
        }

        E getData() {
            return data;
        }

        LinkedListNode<E> getNextNode() {
            return nextNode;
        }

        void setNextNode(LinkedListNode<E> nextNode) {
            this.nextNode = nextNode;
        }
    }
}
