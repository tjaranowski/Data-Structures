package pl.nosystems.java.datastructures.set;


/**
 * Interface describing Set.
 *
 * @param <E> Elements that set holds.
 */
public interface Set<E> {

    /**
     * Adds element to set if element is not already there.
     * Returns true if element was added, false otherwise.
     *
     * @param element element to be added
     * @return true if element was added, false if it was not because it is already there
     */
    boolean add(E element);

    /**
     * Removes element from set if set contains one that matches.
     * Returns true if element was removed, false otherwise.
     *
     * @param element element to be removed
     * @return true if element was removed, false otherwise
     */
    boolean remove(E element);

    /**
     * Checks if set contains element that matches.
     * Returns true if element is in set, false otherwise.
     *
     * @param element element to be searched for
     * @return true if element is in set, false otherwise
     */
    boolean contains(E element);
}
