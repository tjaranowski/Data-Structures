package pl.nosystems.java.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class ArrayList<E> {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    private Object[] elementArray;
    private boolean[] indexInElementArrayContainsObject;


    /**
     * Constructor that creates ArrayList with default initial capacity
     */
    public ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * Constructor that creates ArrayList with provided default initial capacity.
     * Capacity provided must be positive. If user provides zero or negative capacity
     * java.lang.IllegalArgumentException will be thrown.
     *
     * @param initialCapacity initial capacity
     */
    public ArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive. Capacity requested: " + initialCapacity);
        }

        elementArray = new Object[initialCapacity];
        indexInElementArrayContainsObject = new boolean[initialCapacity];
    }

    /**
     * Adds element to list. If there is not enough capacity to add element
     * this method will resize ArrayList and attempt to add element again until either it succeeds
     * or java.lang.OutOfMemoryError will be thrown.
     *
     * @param element element to be added to list
     */
    public void add(E element) {
        boolean added = false;
        for (int i = 0; i < indexInElementArrayContainsObject.length; i++) {
            if (!indexInElementArrayContainsObject[i]) {
                elementArray[i] = element;
                indexInElementArrayContainsObject[i] = true;
                added = true;
                break;
            }
        }
        if (!added) {
            growInternalObjectArray();
            add(element);
        }
    }

    /**
     * Removes first element from list that matches if list contains at least one matching element.
     * Returns true if element was removed from the list, false otherwise.
     *
     * @param element element to be removed from list
     * @return true if element was removed, false otherwise
     */
    public boolean remove(E element) {

        for (int i = 0; i < elementArray.length; i++) {
            if (Objects.equals(elementArray[i], element)) {
                elementArray[i] = null;
                indexInElementArrayContainsObject[i] = false;
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if element is stored in list.
     * Returns true if at least one element in list matches provided element.
     *
     * @param element element to be searched for
     * @return true if at least one element that matches is in list, false otherwise
     */
    public boolean contains(E element) {
        for (Object o : elementArray) {
            if (Objects.equals(o, element)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns default iterator.
     *
     * @return iterator
     */
    public Iterator<E> iterator() {
        return new ArrayListIterator<>(this);
    }

    /**
     * Internal method that does resize underlying array(s) to be able to fit more elements
     */
    private void growInternalObjectArray() {
        Object[] newInternalObjectArray = new Object[elementArray.length * 2];
        System.arraycopy(elementArray, 0, newInternalObjectArray, 0, elementArray.length);

        boolean[] newInternalOccupyArray = new boolean[indexInElementArrayContainsObject.length * 2];
        System.arraycopy(indexInElementArrayContainsObject, 0, newInternalOccupyArray, 0, indexInElementArrayContainsObject.length);

        this.elementArray = newInternalObjectArray;
        this.indexInElementArrayContainsObject = newInternalOccupyArray;
    }


    /**
     * Iterator implementation that skips indexes that are not valid.
     * Valid index is if and only if element was added under such index AND it was not removed afterwards
     * without subsequent add operation.
     *
     * @param <E> Elements that ArrayList holds
     */
    private static class ArrayListIterator<E> implements Iterator<E> {
        private final Object[] elementArray;
        private final boolean[] indexInElementArrayContainsObject;

        private static final int NOT_COMPUTED_INDEX = -300;
        private static final int NO_VALUE_INDEX = -100;

        private int currentIndex = -1;
        private int nextIndexThatContains = NOT_COMPUTED_INDEX;


        /**
         * Constructor that creates Iterator for given array list
         *
         * @param arrayList ArrayList that this iterator will iterate through
         */
        ArrayListIterator(ArrayList<E> arrayList) {
            Objects.requireNonNull(arrayList);

            elementArray = arrayList.elementArray;
            indexInElementArrayContainsObject = arrayList.indexInElementArrayContainsObject;
        }


        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {

            if (nextIndexThatContains == NOT_COMPUTED_INDEX) {
                computeNextIndex();
            }

            return nextIndexThatContains != NO_VALUE_INDEX;
        }

        /**
         * {@inheritDoc}
         */
        @SuppressWarnings("unchecked")
        @Override
        public E next() {

            if (nextIndexThatContains == NOT_COMPUTED_INDEX) {
                computeNextIndex();
            }

            if (nextIndexThatContains == NO_VALUE_INDEX) {
                throw new NoSuchElementException();
            }

            currentIndex = nextIndexThatContains;
            nextIndexThatContains = NOT_COMPUTED_INDEX;

            return (E) elementArray[currentIndex];
        }

        /**
         * Currently not supported - will throw java.lang.UnsupportedOperationException
         * <p>
         * {@inheritDoc}
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * Internal method that computes next index by iterating
         * over array that indicates if under certain index there is a valid value
         */
        private void computeNextIndex() {
            int temporaryIndex = currentIndex + 1;
            for (; temporaryIndex < elementArray.length; temporaryIndex++) {
                if (indexInElementArrayContainsObject[temporaryIndex]) {
                    nextIndexThatContains = temporaryIndex;
                    break;
                }
            }
            if (temporaryIndex == elementArray.length) {
                nextIndexThatContains = NO_VALUE_INDEX;
            }
        }
    }
}

