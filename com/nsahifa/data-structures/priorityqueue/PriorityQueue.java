/**
 * Min Priority Queue implementation  using binary heap
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */
package priorityqueue;

import java.util.*;

public class PriorityQueue<T extends Comparable<T> > {

    // The number of elements currently inside the heap
    private int heapSize = 0;

    // The capacity of the binary heap
    private int heapCapacity = 0;

    // A list to track the elements inside the heap
    private List<T> heap = null;

    // A map that keeps track of the possible indices of a particular
    // node value in the binary heap. This map allows us to perform
    // O(log(n)) removals and O(1) containment check at the cost of
    // some additional space and minor overhead
    private Map<T, TreeSet<Integer>> indexTree = new HashMap<T, TreeSet<Integer>>();

    // Constructor without argument
    // Initialize an empty priority queue
    public PriorityQueue() {
        this(1);
    }

    // Constructor with initial capacity
    public PriorityQueue(int size){
        heap = new ArrayList<T>(size);
    }

    // Constructor using heapify process in O(n)
    public PriorityQueue(T[] elems) {
        heapSize = heapCapacity = elems.length;
        heap = new ArrayList<T>(heapCapacity);

        for (int i = 0; i<heapSize; ++i){
            // Add the value and its index to
            // the map containing the indices
            // for each value
            addIndexTree(elems[i], i);
            heap.add(elems[i]);
        }

        // Heapify process, complexity : O(n)
        for (int i = Math.max(0, (heapSize/2)-1); i>=0; i--)
            sink(i);
    }

    // Priority Queue Constructor in O(nlog(n))
    public PriorityQueue(Collection<T> elems){
        this(elems.size());
        for (T elem : elems)
            add(elem);
    }

    // Checks if the PQ is empty
    public boolean isEmpty() {
        return heapSize == 0;
    }

    // Clears the heap and the indexTree in O(n)
    public void clear() {
        for (int i=0; i<heapSize; ++i)
            heap.set(i, null);
        heapSize = 0;
        indexTree.clear();
    }

    // Returns the size of the heap
    public int size() {
        return heapSize;
    }

    // Return the value of the element with the
    // lowest priority
    public T top() {
        // Check if the PQ is empty
        if (isEmpty()) return null;
        return heap.get(0);
    }

    // Remove the element with the lowest priority
    // Complexity : O(nlog(n))
    public T pop() {
        return removeAt(0);
    }

    // Check if an element is in heap in O(1)
    // using the indexTree Map
    public boolean contains(T elem){
        if (elem == null) return false;
        return indexTree.containsKey(elem);
    }

    public void add(T elem){
        if (elem == null) throw new IllegalArgumentException();

        if (heapSize < heapCapacity){
            heap.set(heapSize, elem);
        }
        else{
            heap.add(elem);
            heapCapacity++;
        }

        // Add the element and its index
        // to the map containing the indexes
        // for all elements
        addIndexTree(elem, heapSize);

        // Adjust the element inserted inside the heap
        swim(heapSize);
        heapSize++;
    }

    // Tests if the value i is <= node j
    // Complexity : O(1)
    private boolean less(int i, int j){
        T node1 = heap.get(i);
        T node2 = heap.get(j);

        // Possible because T extends Comparable
        return node1.compareTo(node2) <= 0;
    }

    // Bottom up node k swim in O(log(n))
    private void swim(int k) {

        // the index of the parent inside the heap
        int parent = (k-1)/2;

        while (k>0 && less(k, parent)) {

            // perform swap operation between k and its parent
            swap(parent, k);

            k = parent;
            parent = (k-1)/2;
        }
    }

    // Bottom down node k sink in O(log(n))
    private void sink(int k) {
        while (true) {
            int left = 2*k + 1;
            int right = 2*k + 2;

            // Initialize the smallest with the left node
            int smallest = left;
            if (right < heapSize && less(right, left))
                smallest = right;

            if (left >= heapSize || less(k, smallest))
                break;

            // Perform the swap to move down the tree
            // following the smallest node
            swap(smallest, k);
            k = smallest;
        }
    }

    // Swap two nodes inside the heap in O(1)
    private void swap(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);

        heap.set(i, node2);
        heap.set(j, node1);

        indexTreeSwap(node1, node2, i, j);
    }

    // Remove a particular element in the heap in O(log(n))
    public boolean remove(T elem){
        if (elem == null) return false;

        Integer index = mapGet(elem);
        if (index != null) removeAt(index);
        return index != null;
    }

    private T removeAt(int i) {
        if (isEmpty()) return null;

        T node = heap.get(i);
        heapSize--;

        // swap the node at i and the last node
        swap(i, heapSize);

        heap.set(heapSize, null);
        mapRemove(node, heapSize);

        // if node is last element
        if (i==heapSize) return node;

        T elem = heap.get(i);

        // Try sinking the element
        sink(i);
        if (heap.get(i).equals(elem)){
            // perform a swim after checking
            swim(i);
        }

        return node;
    }

    // Boolean function that checks if the Heap is
    // a valid MinHeap, the current node is less both
    // of its children
    // return false otherwise
    public boolean isMinHeap(int k) {
        if (k >= heapSize) return true;

        int left = 2*k + 1;
        int right = 2*k + 2;

        if (left < heapSize && !less(k, left)) return false;
        if (right < heapSize && !less(k, right)) return false;

        // Perform recursive calls to check for the children of node k
        return isMinHeap(left) && isMinHeap(right);
    }

    // Add a node and its index to the map containing
    // the indices of each node
    private void addIndexTree(T value, int index) {

        TreeSet<Integer> set = indexTree.get(index);

        if (set == null) {
            set = new TreeSet<Integer>();
            set.add(index);
            indexTree.put(value, set);
        }
        else set.add(index);
    }

    // Remove the index at a given value in O(log(n))
    private void mapRemove(T value, int index) {
        TreeSet<Integer> set = indexTree.get(value);
        if (set != null) {
            set.remove(index);
            if (set.size() == 0) indexTree.remove(value);
        }
    }

    // Extract an index position for the given value
    // from the indexTree map
    private Integer mapGet(T value) {
        TreeSet<Integer> set = indexTree.get(value);
        if (set != null) return set.last();
        return null;
    }

    // Exchange the index of the two nodes withing the indexTree Map
    private void indexTreeSwap(T node1, T node2, int index1, int index2) {
        TreeSet<Integer> set1 = indexTree.get(node1);
        TreeSet<Integer> set2 = indexTree.get(node2);

        if (set1 == null){
            set1 = new TreeSet<Integer>();
            set1.add(index2);
            indexTree.put(node2, set1);
        }
        else {
            set1.remove(index1);
            set1.add(index1);
        }

        if (set2 == null){
            set2 = new TreeSet<Integer>();
            set2.add(index1);
            indexTree.put(node1, set2);
        }
        else {
            set2.remove(index2);
            set1.add(index1);
        }
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
