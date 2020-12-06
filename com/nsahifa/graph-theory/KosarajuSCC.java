import graph.AdjacencyList;

import java.util.ArrayList;
import java.util.List;

/**
 * Kosaraju's algorithm for finding Strongly Connected Components
 * and Building Condensation graph
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */

// Complexity : algorithm asymptotic is O(n+m)
public class KosarajuSCC {

    private static List<Integer> component = new ArrayList<Integer>();

    // order contains vertices sorted by decreasing exit time
    private static List<Integer> order = new ArrayList<Integer>();

    private int idx = 0;

    // This function return vertices with increasing exist time in the list "order"
    public void topoSort(AdjacencyList graph, int node) {

        graph.visited[node] = true;

        for (int child : graph.adj(node)) {
            if (!graph.visited[child])
                topoSort(graph, child);
        }
        order.add(node);
    }

    // dfs function for finding the vertices of the next SCC
    public void dfs(AdjacencyList graph, int node) {

        graph.visited[node] = true;
        component.add(node);

        for (int child : graph.adjTrans(node)) {
            if (!graph.visited[child])
                dfs(graph, child);
        }
    }

    public int[] findSCC(AdjacencyList graph) {

        // Number of vertices in the graph
        int V = graph.V();

        // Array containing the component Id for each vertex
        int[] componentId = new int[V];

        for (int v=0; v<V; ++v){
            if (!graph.visited[v])
                topoSort(graph, v);
        }

        graph.clear();

        // We run dfs function in the order determined by the list "order" (in reversed order)
        // Every set of vertices reached after the search will be the part of the next SCC

        // Note : dfs function runs on the transposed graph
        for (int i=0; i<V; ++i){
            int v = order.get(V-i-1);
            if (!graph.visited[v]){
                dfs(graph, v);

                // component List will contain the vertices of condensation graph
                for (int node : component) {
                    componentId[node] = idx;
                }
                component.clear();
                idx++;
            }
        }
        return componentId;
    }
}
