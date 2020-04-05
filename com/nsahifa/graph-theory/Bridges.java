import graph.AdjacencyList;

import java.util.ArrayList;
import java.util.List;

public class Bridges {

    /**
     * We perform a DFS traversal, and we keep track the id of each node
     * and the smallest low-link value
     * The low-link value of a node is defined as the smallest id reachable
     * from that node when doing a DFS (including itself)
     *
     * Bridges will be found where the id of the node your edge is
     * coming from is less than the low link value of the node your edge
     * is going to id(e.from) < lowlink(e.to)
     */

    // Implementation of the class object structure
    public static class Pair<U, V>{
        public U first;
        public V second;

        public Pair(U first, V second){
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

    private int id;
    private List<Pair<Integer, Integer> > bridges;
    private int[] ids, low;


    // Complexity : O(V+E)
    public void dfs(AdjacencyList graph, int node, int parent){
        graph.visited[node] = true;
        id = id + 1;
        ids[node] = low[node] = id;

        for (Integer child : graph.adj(node)){
            if (child == parent) continue;
            if (!graph.visited[child]){

                dfs(graph, child, node);

                // This what propagates the low-link value
                // and it happens in the callback of the dfs
                low[node] = Math.min(low[node], low[child]);

                if (ids[node] < low[child]){
                    // If the condition is true
                    // We add the edge to the bridges array
                    bridges.add(new Pair<Integer, Integer>(node, child));
                }
            }
            else {
                // We revisit a node which has a chance
                // of having a lower id than the current
                // low-link id of the current node
                low[node] = Math.min(low[node], ids[child]);
            }
        }
    }

    public List<Pair<Integer, Integer> > findBridges(AdjacencyList graph){
        bridges = new ArrayList<Pair<Integer, Integer> >();
        ids = new int[graph.V()];
        low = new int[graph.V()];
        id = 0;

        // Find all bridges in the graph across
        // various connected components
        for (int i=0;i<graph.V();++i){
            if (!graph.visited[i]){
                dfs(graph, i, -1);
            }
        }

        return bridges;
    }

}
