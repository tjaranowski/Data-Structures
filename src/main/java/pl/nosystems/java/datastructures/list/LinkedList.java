package pl.nosystems.java.datastructures.list;

import java.util.Objects;


public class LinkedList<E> {
    private LinkedListNode<E> firstNode;

    public void add(E element) {
        if (firstNode == null) {
            firstNode = new LinkedListNode<>(element);
            return;
        }

        firstNode = new LinkedListNode<>(element, firstNode);
    }

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

    public boolean isEmpty() {
        return firstNode == null;
    }

    private static class LinkedListNode<E> {
        private E data;
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
