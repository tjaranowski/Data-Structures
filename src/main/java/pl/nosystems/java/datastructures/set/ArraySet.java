package pl.nosystems.java.datastructures.set;

import pl.nosystems.java.datastructures.list.ArrayList;

import java.util.Iterator;


public class ArraySet<E> implements Iterable<E> {
    private final ArrayList<E> arrayList = new ArrayList<>();

    public boolean add(E element) {

        if(arrayList.contains(element)) {
            return false;
        }

        arrayList.add(element);
        return true;
    }

    public boolean remove(E element) {
        return arrayList.remove(element);
    }

    public boolean contains(E element) {
        return arrayList.contains(element);
    }

    @Override
    public Iterator<E> iterator() {
        return arrayList.iterator();
    }
}
