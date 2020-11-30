/**
 * An implementation of the articulations points algorithm
 * on an undirected graph
 * @author : nabil sahifa
 * @email: nabilsahifa@gmail.com
 */
import graph.AdjacencyList;
import java.util.Arrays;

public class ArticulationsPoints {
    /**
     * Observations :
     * On a connected component, if an edge(u, v) is a bridge
     * then either u or v is an articulation point, this condition
     * is not sufficient to capture all articulation points, there
     * exist cases where there is an articulation point without a bridge
     *
     * The indication of a cycle back to the original node implies
     * an articulation point. On the callback from the starting node
     * if id(e.from) == lowlink(e.to) then there was a cycle
     *
     * The only time id(e.from) == lowlin(e.to) fails is when
     * the starting node has 0 or 1 outgoing directed edges.
     * This is because either the node is a singleton (0 case) or
     * the node is trapped in a cycle (1 case).
     *
     */

    int id, outDegree;
    int[] ids, low;
    boolean[] isArticulation;

    // Complexity : O(V+E)
    public void dfs(AdjacencyList graph, int root, int node, int parent){
        // We increment the number of outcoming
        // edges from the starting node
        if (parent == root) outDegree++;

        ids[node] = low[node] = id;
        id++;
        graph.visited[node] = true;

        for (int child : graph.adj(node)){
            if (child == parent) continue;

            if (!graph.visited[child]){
                dfs(graph, root, child, node);

                // propagation of the low-link value
                low[node] = Math.min(low[node], low[child]);

                if (ids[node] < low[child]){
                    // Articulation point found via bridge
                    isArticulation[node] = true;
                }
                if (ids[node] == low[child]){
                    // Articulation point found via cycle
                    // node is part of a cycle
                    isArticulation[node] = true;
                }
            }
            else {
                // We revisit the node which has the chance
                // of having a lower id than the current low-link value
                low[node] = Math.min(low[node], ids[child]);
            }
        }
    }

    public boolean[] findArtPoints(AdjacencyList graph){
        ids = new int[graph.V()];
        low = new int[graph.V()];
        isArticulation = new boolean[graph.V()];
        Arrays.fill(isArticulation, false);
        id = 0;

        // Find all articulation points across various
        // connected components in the graph
        for(int i=0;i<graph.V();++i){
            if (!graph.visited[i]){
                outDegree = 0;
                dfs(graph, i, i, -1);

                // When the primary DFS call return back, it updates
                // the articulation point for the node it started with
                isArticulation[i] = isArticulation[i] && (outDegree > 1);
            }
        }
        return isArticulation;
    }
}