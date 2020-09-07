package pl.nosystems.java.datastructures.list;

import pl.nosystems.java.datastructures.Iterator;

import java.util.NoSuchElementException;

@SuppressWarnings("WeakerAccess")
public final class DoubleLinkedList<T> {

    private DoubleLinkedListNode<T> root;
    private DoubleLinkedListNode<T> lastNode;
    private int size = 0;

    private void afterSuccessfullyAddedOneElement() {
        size++;
    }

    private void addFirstElement(final T data) {
        lastNode = root = new DoubleLinkedListNode<>(data);
    }

    private void addNextElement(final T data) {
        final DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(lastNode,data,null);
        lastNode.setNext(newNode);
        lastNode = newNode;
    }

    private void removeNodeThatHasOnlyNext(final DoubleLinkedListNode<T> node, final DoubleLinkedListNode<T> next) {
        next.setPrevious(null);
        node.setNext(null);
        root = next;
    }

    private void removeNodeThatHasOnlyPrevious(final DoubleLinkedListNode<T> node,final DoubleLinkedListNode<T> prev) {
        lastNode = lastNode.getPrevious();

        prev.setNext(null);
        node.setPrevious(null);
    }

    private void removeNodeThatIsInMiddle(final DoubleLinkedListNode<T> prev, final DoubleLinkedListNode<T> next) {
        prev.setNext(next);
        next.setPrevious(prev);
    }

    private void removeNode(final DoubleLinkedListNode<T> node) {
        DoubleLinkedListNode<T> prev = node.getPrevious();
        DoubleLinkedListNode<T>next = node.getNext();

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

        DoubleLinkedListNode<T> currentNode = root;
        while(currentNode != null) {
            if(currentNode.getData() == null) {
                if(data == null) {
                    removeNode(currentNode);
                    return true;
                }
            } else {
                if(currentNode.getData().equals(data)) {
                    removeNode(currentNode);
                    return true;
                }
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public Iterator<T> getIterator() {
        return new Iterator<T>() {
            private boolean isBeforeRoot = true;
            private DoubleLinkedListNode<T> currentNode = root;

            @Override
            public boolean hasNext() {

                if(isBeforeRoot) {
                    return root!=null;
                }

                return currentNode.getNext() != null;
            }

           
            @Override
            public T next() {
                if(isBeforeRoot) {
                    isBeforeRoot = false;
                    if(currentNode == null) {
                        throw new NoSuchElementException("Iterated out of bounds. Make sure that hasNext() returns true before calling this method.");
                    }
                    return currentNode.getData();
                }
                currentNode = currentNode.getNext();
                if(currentNode == null) {
                    throw new NoSuchElementException("Iterated out of bounds. Make sure that hasNext() returns true before calling this method.");
                }
                return currentNode.getData();
            }
        };
    }
}
