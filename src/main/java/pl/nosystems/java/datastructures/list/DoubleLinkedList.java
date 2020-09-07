package pl.nosystems.java.datastructures.list;

import pl.nosystems.java.datastructures.Iterator;

import java.util.NoSuchElementException;

@SuppressWarnings("WeakerAccess")
public final class DoubleLinkedList<T> {

    private DoubleLinkedListNode<T> root;
    private DoubleLinkedListNode<T> lastNode;
    private int size = 0;

    /**
     * Adds element to collection.
     * Equal to calling {@link #addLast(Object)}
     *
     * @param data element to be added
     */
    public void add(final T data) {
        addLast(data);
    }

    /**
     * Adds element to collection as first one.
     * This means that calling {@link #getFirst()} immediately after this method will
     * return element passed here and iterator returned by {@link #getIterator()}
     * will return element passed here first if state of collection didn't change.
     *
     * @param data element to be added
     */
    public void addFirst(final T data) {
        if (root == null) {
            addFirstElement(data);
        } else {
            addElementAsFirst(data);
        }
        afterSuccessfullyAddedOneElement();
    }

    /**
     * Adds element to collection as last one.
     * This means that calling {@link #getLast()} immediately after this method will
     * return element passed here and iterator returned by {@link #getIterator()}
     * will return element passed here last if state of collection didn't change.
     *
     * @param data element to be added
     */
    public void addLast(final T data) {
        if (root == null) {
            addFirstElement(data);
        } else {
            addNextElement(data);
        }
        afterSuccessfullyAddedOneElement();
    }

    /**
     * If collection is not empty then returns first element.
     * If collection is empty throws NoSuchElementException
     *
     * @return first element in collection
     * @throws NoSuchElementException if collection is empty
     */
    public T getFirst() {
        if (root == null) {
            throw new NoSuchElementException("Empty!");
        }
        return root.getData();
    }

    /**
     * If collection is not empty then returns last element.
     * If collection is empty throws NoSuchElementException
     *
     * @return last element in collection
     * @throws NoSuchElementException if collection is empty
     */
    public T getLast() {
        if (root == null) {
            throw new NoSuchElementException("Empty!");
        }
        return lastNode.getData();
    }

    /**
     * Removes first element from collection.
     * Returns removed element.
     *
     * @return Element removed
     * @throws NoSuchElementException if collection is empty
     */
    public T removeFirst() {
        if (root == null) {
            throw new NoSuchElementException("Empty!");
        }

        if (lastNode == root) {
            return removeOnlyNode();
        }

        // Retrieve nodes to work on
        DoubleLinkedListNode<T> currentFirst = root;
        DoubleLinkedListNode<T> next = root.getNext();

        // Unlink nodes
        next.setPrevious(null);
        currentFirst.setNext(null);

        // Remove first
        root = next;

        // Change state - removed one node
        size--;

        // Return data from previously first node
        return currentFirst.getData();
    }

    /**
     * Removes last element from collection.
     * Returns removed element.
     *
     * @return Element removed
     * @throws NoSuchElementException if collection is empty
     */
    public T removeLast() {
        if (root == null) {
            throw new NoSuchElementException("Empty!");
        }

        if (lastNode == root) {
            return removeOnlyNode();
        }

        // Retrieve nodes to work on
        DoubleLinkedListNode<T> currentLast = lastNode;
        DoubleLinkedListNode<T> previous = lastNode.getPrevious();

        // Unlink nodes
        previous.setNext(null);
        currentLast.setPrevious(null);

        // Remove first
        lastNode = previous;

        // Change state - removed one node
        size--;

        // Return data from previously last node
        return currentLast.getData();
    }

    /**
     * Removes element that matches one given if there is one in collection.
     *
     * @param data element to be removed from collection
     * @return true if element was found and removed, false otherwise
     */
    public boolean removeFirstFound(final T data) {
        if (root == null) {
            return false;
        }

        DoubleLinkedListNode<T> currentNode = root;
        while (currentNode != null) {
            if (currentNode.getData() == null) {
                if (data == null) {
                    removeNode(currentNode);
                    return true;
                }
            } else {
                if (currentNode.getData().equals(data)) {
                    removeNode(currentNode);
                    return true;
                }
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    /**
     * Returns number of elements held in collection.
     *
     * @return number of elements held in collection
     */
    public int getSize() {
        return size;
    }

    /**
     * Tells if collection holds any elements.
     *
     * @return true if collection has at least one element, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns iterator that iterates forward from first element to last
     *
     * @return iterator
     */
    public Iterator<T> getIterator() {
        return new Iterator<T>() {
            private boolean isBeforeRoot = true;
            private DoubleLinkedListNode<T> currentNode = root;

            @Override
            public boolean hasNext() {

                if (isBeforeRoot) {
                    return root != null;
                }

                return currentNode.getNext() != null;
            }


            @Override
            public T next() {
                if (isBeforeRoot) {
                    isBeforeRoot = false;
                    if (currentNode == null) {
                        throw new NoSuchElementException("Iterated out of bounds. Make sure that hasNext() returns true before calling this method.");
                    }
                    return currentNode.getData();
                }
                currentNode = currentNode.getNext();
                if (currentNode == null) {
                    throw new NoSuchElementException("Iterated out of bounds. Make sure that hasNext() returns true before calling this method.");
                }
                return currentNode.getData();
            }
        };
    }


    private T removeOnlyNode() {

        // Save node to temporary variable
        DoubleLinkedListNode<T> node = root;

        // Clear only one node
        root = lastNode = null;

        // Change state - removed one node
        size--;

        // Return value
        return node.getData();
    }

    private void afterSuccessfullyAddedOneElement() {
        size++;
    }

    private void addFirstElement(final T data) {
        lastNode = root = new DoubleLinkedListNode<>(data);
    }

    private void addNextElement(final T data) {
        final DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(lastNode, data, null);
        lastNode.setNext(newNode);
        lastNode = newNode;
    }

    private void addElementAsFirst(T data) {
        final DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(null, data, root);
        root.setPrevious(newNode);
        root = newNode;
    }

    private void removeNodeThatHasOnlyNext(final DoubleLinkedListNode<T> node, final DoubleLinkedListNode<T> next) {
        next.setPrevious(null);
        node.setNext(null);
        root = next;
    }

    private void removeNodeThatHasOnlyPrevious(final DoubleLinkedListNode<T> node, final DoubleLinkedListNode<T> prev) {
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
        DoubleLinkedListNode<T> next = node.getNext();

        size--;

        if (prev == null && next == null) {
            root = null;
            return;
        }

        if (prev == null) {
            removeNodeThatHasOnlyNext(node, next);

        } else if (next == null) {
            removeNodeThatHasOnlyPrevious(node, prev);

        } else {
            removeNodeThatIsInMiddle(prev, next);

        }
    }

    private static class DoubleLinkedListNode<T> {
        private DoubleLinkedListNode<T> previous;
        private final T data;
        private DoubleLinkedListNode<T> next;

        private DoubleLinkedListNode(T data) {
            this.data = data;
        }

        private DoubleLinkedListNode(DoubleLinkedListNode<T> previous, T data, DoubleLinkedListNode<T> next) {
            this.previous = previous;
            this.data = data;
            this.next = next;
        }

        private DoubleLinkedListNode<T> getPrevious() {
            return previous;
        }

        private void setPrevious(DoubleLinkedListNode<T> previous) {
            this.previous = previous;
        }

        private T getData() {
            return data;
        }

        private DoubleLinkedListNode<T> getNext() {
            return next;
        }

        private void setNext(DoubleLinkedListNode<T> next) {
            this.next = next;
        }
    }
}
