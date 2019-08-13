package pl.nosystems.java.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class ArrayList<E> {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    private Object[] elementArray;
    private boolean[] indexInElementArrayContainsObject;

    public ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }


    public ArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive. Capacity requested: " + initialCapacity);
        }

        elementArray = new Object[initialCapacity];
        indexInElementArrayContainsObject = new boolean[initialCapacity];
    }

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

    public boolean contains(E element) {
        for (Object o : elementArray) {
            if (Objects.equals(o, element)) {
                return true;
            }
        }

        return false;
    }

    private void growInternalObjectArray() {
        Object[] newInternalObjectArray = new Object[elementArray.length * 2];
        System.arraycopy(elementArray, 0, newInternalObjectArray, 0, elementArray.length);

        boolean[] newInternalOccupyArray = new boolean[indexInElementArrayContainsObject.length * 2];
        System.arraycopy(indexInElementArrayContainsObject, 0, newInternalOccupyArray, 0, indexInElementArrayContainsObject.length);

        this.elementArray = newInternalObjectArray;
        this.indexInElementArrayContainsObject = newInternalOccupyArray;
    }


    public Iterator<E> iterator() {
        return new ArrayListIterator<>(this);
    }

    private static class ArrayListIterator<E> implements Iterator<E> {
        private final Object[] elementArray;
        private final boolean[] indexInElementArrayContainsObject;

        private final int NOT_COMPUTED_INDEX = -300;
        private final int NO_VALUE_INDEX = -100;

        private int currentIndex = -1;
        private int nextIndexThatContains = NOT_COMPUTED_INDEX;


        ArrayListIterator(ArrayList<E> arrayList) {
            Objects.requireNonNull(arrayList);

            elementArray = arrayList.elementArray;
            indexInElementArrayContainsObject = arrayList.indexInElementArrayContainsObject;
        }


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

        @Override
        public boolean hasNext() {

            if (nextIndexThatContains == NOT_COMPUTED_INDEX) {
                computeNextIndex();
            }

            return nextIndexThatContains != NO_VALUE_INDEX;
        }

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
    }
}

