package dynamicarray;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T> {

    private T[] arr;
    private int length = 0; // the current length of the array
    private int capacity = 0; // the real capacity of the dynamic array

    // Constructor without arguments
    public DynamicArray() {
        this(32);
    }

    // Constructor with capacity
    public DynamicArray(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Illegal capacity : " + capacity);
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size(){
        return length;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public T getAt(int idx){
        return arr[idx];
    }

    public void set(int idx, T element){
        arr[idx] = element;
    }

    public void clear(){
        for (int i=0;i<length;++i){
            arr[i] = null;
        }
        length = 0;
    }

    public void add(T element){
        /**
         * We keep track of the number of elements.
         * If adding another element will exceed the capacity
         * we create a new array with twice the capacity
         */
        if (length+1 >= capacity){
            if (capacity == 0) capacity = 1;
            else capacity *= 2;
            T[] new_arr = (T[]) new Object[capacity];
            for (int i=0;i<length;++i)
                new_arr[i] = arr[i];
            arr = new_arr;
        }
        arr[length++] = element;
    }

    public void removeAt(int idx){
        //First we check if the index is valid
        if (idx>=length || idx<0) throw new IndexOutOfBoundsException();
        T[] new_arr = (T[]) new Object[length-1];
        int j = 0;
        for (int i=0;i<length;++i){
            if (i==idx) continue;
            new_arr[j++] = arr[i];
        }
        arr = new_arr;
        capacity = --length;
    }

    // returns the index of the element
    public int indexOf(Object obj){
        for (int i=0;i<length;++i){
            if (obj.equals(arr[i])){
                return i;
            }
        }
        return -1;
    }

    public boolean remove(Object obj){
        int index = indexOf(obj);
        if (index == -1) return false;
        removeAt(index);
        return true;
    }

    public boolean contains(Object obj){
        return indexOf(obj) != -1;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            public boolean hasNext() {
                return index < length;
            }

            public T next() {
                return arr[index++];
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        if (length == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(length).append("[");
            for (int i=0;i<length;++i) sb.append(arr[i] + ", ");
            sb.append("]");
            return sb.toString();
        }
    }
}
