package pl.nosystems.java.datastructures.map;

import pl.nosystems.java.datastructures.Iterator;
import pl.nosystems.java.datastructures.arrays.Vector;
import pl.nosystems.java.datastructures.list.ArrayList;

import java.util.Objects;


/**
 * Collection that holds Key-Value pairs and relies on hash value
 * to quickly find Value for a given Key.
 * While it will store {@code null} values under keys it is not advised to do so
 * since some methods use {@code null} as special return value.
 *
 * @param <K> Type of Keys in this collection
 * @param <V> Type of Values in this collection
 */
public class HashMap<K, V> {
    private static final int DEFAULT_BUCKET_COUNT = 10;
    private final Vector<ArrayList<Pair<K, V>>> buckets;

    /**
     * Constructor that creates HashMap with default bucket count.
     */
    public HashMap() {
        this(DEFAULT_BUCKET_COUNT);
    }

    /**
     * Constructor that creates HashMap with provided bucket count.
     *
     * @param bucketsCount bucket count to use for this instance of HashMap
     * @throws IllegalArgumentException when bucketsCount is not positive
     */
    public HashMap(int bucketsCount) {
        buckets = new Vector<>(bucketsCount);

        for (int i = 0; i < bucketsCount; i++) {
            buckets.put(new ArrayList<>(), i);
        }
    }

    /**
     * Inserts Key-Value pair into collection.
     * If under given Key value exists, it will be returned.
     * If under given Key there was no Value then null will be returned.
     *
     * @param key   Key to be put into collection associated with given Value
     * @param value Value to be put into collection for given Key
     * @return Old value under given Key if any, null otherwise
     * @throws NullPointerException if key is null
     */
    public V put(K key, V value) {
        int bucket = computeBucketForKey(key);
        ArrayList<Pair<K, V>> bucketList = buckets.get(bucket);
        Iterator<Pair<K, V>> iterator = bucketList.iterator();
        while (iterator.hasNext()) {
            Pair<K, V> candidate = iterator.next();
            if (Objects.equals(candidate.key, key)) {
                V oldValue = candidate.value;
                candidate.value = value;
                return oldValue;
            }
        }
        bucketList.add(new Pair<>(key, value));
        return null;
    }

    /**
     * If under given Key value exists, it will be returned.
     * If there is no such Key in collection null will be returned.
     *
     * @param key Key for which to return value for
     * @return Value for given Key if any, null otherwise
     * @throws NullPointerException if key is null
     */
    public V get(K key) {
        int bucket = computeBucketForKey(key);
        ArrayList<Pair<K, V>> bucketList = buckets.get(bucket);
        Iterator<Pair<K, V>> iterator = bucketList.iterator();
        while (iterator.hasNext()) {
            Pair<K, V> candidate = iterator.next();
            if (Objects.equals(candidate.key, key)) {
                return candidate.value;
            }
        }
        return null;
    }


    private int computeBucketForKey(K key) {
        int keyHashCode = key.hashCode();
        if (keyHashCode == Integer.MIN_VALUE) {
            return 0;
        }
        return Math.abs(key.hashCode()) % buckets.getCapacity();
    }


    private static class Pair<K, V> {
        K key;
        V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
