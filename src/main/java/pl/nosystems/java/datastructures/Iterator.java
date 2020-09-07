package pl.nosystems.java.datastructures;

/**
 * Interface describing iterator that does support forward iteration only.
 *
 * @param <T>
 */
public interface Iterator<T> {

    /**
     * Tells if there is next element to retrieve.
     *
     * @return true if there is next element, false otherwise
     */
    boolean hasNext();

    /**
     * Returns next element and moves iteration pointer one tick forward.
     *
     * @return Returns next element
     * @throws java.util.NoSuchElementException if there is no next element.
     */
    T next();
}
