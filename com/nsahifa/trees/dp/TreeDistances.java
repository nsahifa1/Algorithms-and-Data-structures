package dp;

import graph.AdjacencyList;

/**
 * Class to find out for each node the maximum
 * distance to another node
 *
 * Optimal substructure : When node i is considered we calculate
 * in[i] : the maximum height of tree when we travel downwards via its sub-trees and leaves
 * in[i] = max(in[i], 1+in[child])
 *
 * out[i] : the maximum height of the tree while traveling upwards via its parent
 * out[i] = 1 + max(out[parent], 1 + longest path of all branches of parent of i
 *                                   (excluding the branch in which the node lies)
 */
public class TreeDistances {

    private static final int MAX_NODES = 100;
    private static int[] in = new int[MAX_NODES];
    private static int[] out = new int[MAX_NODES];

    // pre-calculate the array in[] which stores the maximum
    // height when travelled downwards via branches
    private static void dfs1(AdjacencyList graph, int u, int parent) {

        // Initially every node has a height of 0
        in[u] = 0;

        for (int child : graph.adj(u)) {
            if (child == parent)
                continue;

            dfs1(graph, child, u);

            // Recursively calculates the max height
            in[u] = Math.max(in[u], 1 + in[child]);
        }
    }

    // pre-calculate the array out[] which stores the maximum
    // height when traveled via parent
    private static void dfs2(AdjacencyList graph, int u, int parent) {

        // variables used to store the longest
        // and second longest branches
        int max1 = -1, max2 = -1;

        // Traverse in the subtree of u
        for (int child : graph.adj(u)) {
            if (child == parent)
                continue;

            // Compare and store the longest
            // an second longest
            if (in[child] >= max1){
                max2 = max1;
                max1 = in[child];
            }
            else if (in[child] > max2) {
                max2 = in[child];
            }
        }

        // Traverse a second time the subtree of u
        for (int child : graph.adj(u)) {
            if (child == parent)
                continue;

            // Find the longest branch
            int longest = max1;

            // If the longest branch has the node
            // then consider the second longest branch
            if (longest == max1)
                longest = max2;

            // Recursively calculates ou[i]
            out[child] = 1 + Math.max(out[u], 1 + longest);

            // dfs function
            dfs2(graph, child, u);
        }
    }

    // Helper function to find all the maximum heights
    // for all the nodes
    public static int[] treeDistances(AdjacencyList graph) {
        int V = graph.V();

        int[] ans = new int[V];
        // Traverse the tree rooted at node 0 to calculate in[] array
        dfs1(graph, 0, -1);

        // Traverse the tree rootes at node 0 to calculate out[] array
        dfs2(graph, 0, -1);

        // Store the maximum height for each node
        for (int i=0; i<V; ++i){
            ans[i] = Math.max(in[i], out[i]);
        }

        return ans;
    }

}
