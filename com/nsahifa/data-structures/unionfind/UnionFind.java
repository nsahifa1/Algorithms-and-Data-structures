/**
 * Union find implementation
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */
package unionfind;

public class UnionFind {

    // the total number of elements
    // in the union find data structure
    private int size;

    // the number of components
    private int nbrComponenents;

    // Track the size of each of the components
    private int[] sz;

    // id[i] represents the parent of i
    // if id[i] = i; then i is a root
    private int[] id;

    // Constructor with the argument size
    public UnionFind(int size){
        if (size <= 0) throw new IllegalArgumentException("Size cannot be negative");

        this.size = this.nbrComponenents = 0;
        sz = new int[size];
        id = new int[size];

        for (int i=0;i<size;++i){
            id[i] = i; // Self root
            sz[i] = 1; // each component is of size one
        }
    }

    // Find which component/set the element 'e' belongs to
    // Complexity : amortized time
    public int find (int e) {
        int root = e;
        // find the root of the current element
        while (root != id[root]){
            root = id[root];
        }

        // Path compression
        // it's what give us amortized constant time complexity
        while (e != root) {
            int parent = id[e];
            id[e] = root;
            e = parent;
        }
        return root;
    }

    // Return whether or not the element 'u' and 'v' are connected
    public boolean connected(int u, int v) {
        return find(u) == find(v);
    }

    // Return the size of the component
    public int componenentSize(int e) {
        return sz[find(e)];
    }

    // Return the number of elements in the disjoint set
    public int size(){
        return size;
    }

    // Return the number of components
    public int component(){
        return nbrComponenents;
    }

    public void merge (int u, int v) {
        u = find(u);
        v = find(v);

        // the two elements are already connected
        if (u == v) return;

        // Merge two components together
        // Merge smaller components into the larger one
        if (sz[u] < sz[v]){
            sz[v] += sz[u];
            id[u] = v;
        }
        else {
            sz[u] += sz[v];
            id[v] = u;
        }
        nbrComponenents--;
    }
}
