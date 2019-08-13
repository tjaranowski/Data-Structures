package pl.nosystems.java.datastructures.arrays;

import java.util.Objects;


public class Vector<E> {
    private Object[] objectArray;

    public Vector(int initialCapacity) {
        objectArray = new Object[initialCapacity];
    }

    public void put(E element, int index) {
        objectArray[index] = element;
    }

    public void putEnsuringCapacity(E element, int index) {
        while(objectArray.length <= index) {
            growInternalObjectArray();
        }
        put(element, index);
    }

    public Integer get(int index) {
        return (Integer) objectArray[index];
    }

    @SuppressWarnings("unchecked")
    public boolean contains(E element) {
        for(Object o: objectArray) {
            E elementFromArray = (E) o;
            if(Objects.equals(o, element)) {
                return true;
            }
        }
        return false;
    }

    private void growInternalObjectArray() {
        Object[] newInternalObjectArray = new Object[objectArray.length * 2];
        System.arraycopy(objectArray, 0, newInternalObjectArray, 0 ,objectArray.length);
        objectArray = newInternalObjectArray;
    }
}
