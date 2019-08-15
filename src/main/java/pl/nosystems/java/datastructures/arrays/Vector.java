package pl.nosystems.java.datastructures.arrays;

import java.util.Objects;


/**
 * Collection that holds elements under indexes. Capacity of
 * underlying collection can be changed only by explicit user action
 * f.e calling a method that has contract of shrinking/growing underlying collection.
 * Such contracts may include conditions as to when capacity change happens.
 *
 * @param <E> Elements that this data structure will hold
 */
public class Vector<E> {
    private Object[] objectArray;

    /**
     * Constructor that creates underlying collection with capacity equal to param initialCapacity.
     * That means that without explicit user action this Vector will have valid indexes of
     * [0 (inclusive), initialCapacity (exclusive) )
     *
     * @param initialCapacity initial capacity
     */
    public Vector(int initialCapacity) {
        objectArray = new Object[initialCapacity];
    }

    /**
     * Inserts element at requested index.
     * If index is outside of allowed bounds that is outside of [0 (inclusive), getCapacity (exclusive) )
     * method throws java.lang.IndexOutOfBoundsException
     *
     * @param element element to be inserted
     * @param index   index at which element is to be inserted
     */
    public void put(E element, int index) {
        objectArray[index] = element;
    }

    /**
     * Inserts element at requested index if underlying collection is big enough.
     * If underlying collection is not big enough then method attempts to increase its size
     * until it becomes big enough or method fails with java.lang.OutOfMemoryError
     *
     * @param element element to be inserted
     * @param index   at which element is to be inserted
     */
    public void putEnsuringCapacity(E element, int index) {
        while (objectArray.length <= index) {
            growInternalObjectArray();
        }
        put(element, index);
    }

    /**
     * Returns element at given index if index is in allowed bounds.
     * If index is outside of allowed bounds throws java.lang.IndexOutOfBoundsException
     *
     * @param index index from which to return element from
     * @return element under given index
     */
    public E get(int index) {
        return (E) objectArray[index];
    }

    /**
     * Method checks if vector contains given element.
     * It is not taken into consideration if,when and who added elements to this Vector.
     * Especially that means checking if collection contains null after creating it will return true
     * if user did not add elements that are not null to all indexes.
     * This is because Vector initially starts with null values for all indexes and
     * inserts null values for each new index after growing underlying collection.
     *
     * @param element element to be checked if Vector contains it
     * @return true it Vector contains element passed, false otherwise
     */
    @SuppressWarnings("unchecked")
    public boolean contains(E element) {
        for (Object o : objectArray) {
            E elementFromArray = (E) o;
            if (Objects.equals(o, element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns capacity of underlying collection.
     * That means that valid indexes are [0 (inclusive), getCapacity() (exclusive) )
     *
     * @return capacity of underlying collection
     */
    public int getCapacity() {
        return objectArray.length;
    }

    /**
     * Internal method for increasing capacity of underlying collection.
     * Can change at any time.
     */
    private void growInternalObjectArray() {
        Object[] newInternalObjectArray = new Object[objectArray.length * 2];
        System.arraycopy(objectArray, 0, newInternalObjectArray, 0, objectArray.length);
        objectArray = newInternalObjectArray;
    }
}
