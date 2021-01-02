package fenwicktree;

import java.util.Arrays;

/**
 * Implementation of The Fenwick Tree Data structures and its operations :
 * Range/point queries and updates
 *
 * Note : Fenwick Tree data structure can be modified to support any invertible function
 * In the current implementation, we demonstrates the usage of the summation function
 */
public class FenwickTree {

    // The size of the array holding the Fenwick Tree values
    private int N;

    private long[] tree;

    // Create an empty Fenwick Tree with
    public FenwickTree(int size) {
        this.N = size + 1;

        tree = new long[N];
        Arrays.fill(tree, 0);
    }

    // Construct a Fenwick tree with an initial set of values
    // The algorithm is one-based, meaning that values[0] is ignored
    public FenwickTree(long[] values) {
        N = values.length;
        values[0] = 0L;

        // Make a clone of the original array
        tree = values.clone();

        for (int i=0; i<N; ++i) {
            int parent = i + lsb(i);
            if (parent < N) tree[parent] += tree[i];
        }
    }

    private int lsb(int i) {

        // Isolate the lowest one bit
        return i & (-i);
    }

    // Calculate the sum of the interval [1, idx]
    // Complexity : O(log(n))
    public long prefixSum(int idx) {
        long sum = 0;
        while (idx != 0) {
            sum += tree[idx];
            idx &= ~lsb(idx);
        }
        return sum;
    }

    // returns the sum of the interval [l, r]
    // Complexity : O(log(n))
    public long sum (int l, int r) {
        return prefixSum(r) - prefixSum(l-1);
    }

    // get the element at the idx position
    public long get(int idx){
        return sum(idx, idx);
    }

    // add the value e to index 'idx'
    // Complexity : O(log(n))
    public void add(int idx, long e) {
        while (idx < N) {
            tree[idx] += e;
            idx += lsb(idx);
        }
    }

    // set index 'idx' to be equal to the value of 'e'
    public void set(int idx, long e) {
        add(idx, e - sum(idx, idx));
    }

    // Update the interval [l, r] with the value of 'e'
    // Complexity : O(log(n))
    public void updateRange(int l, int r, int e) {
        add(l, e);
        add(r+1, -e);
    }

}
