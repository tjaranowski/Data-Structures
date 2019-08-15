package pl.nosystems.java.datastructures.map;

import pl.nosystems.java.datastructures.arrays.Vector;
import pl.nosystems.java.datastructures.list.ArrayList;

import java.util.Iterator;
import java.util.Objects;


public class HashMap<K, V> {
    private static final int DEFAULT_BUCKET_COUNT = 10;
    private Vector<ArrayList<Pair<K, V>>> buckets;

    public HashMap() {
        this(DEFAULT_BUCKET_COUNT);
    }

    public HashMap(int bucketsCount) {
        buckets = new Vector<>(bucketsCount);

        for (int i = 0; i < bucketsCount; i++) {
            buckets.put(new ArrayList<>(), i);
        }
    }

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
