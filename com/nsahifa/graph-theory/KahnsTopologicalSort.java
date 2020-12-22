import graph.AdjacencyList;
import java.util.*;

/**
 * Kahn's algorithm for Topological Sorting for DAG
 * This algorithm is based on the fact that a DAG has at least
 * one vertex with in-degree 0 and one out-degree 0
 */
public class KahnsTopologicalSort {

    public static List<Integer> topologicalSort(AdjacencyList graph) {

        int V = graph.V();

        // Create an array to store indegrees of all vertices
        int inDegree[] = new int[V];
        Arrays.fill(inDegree, 0);

        // Traverse adjacency lists to fill indegrees of vertices
        for (int i=0; i<V; ++i){
            for (int node : graph.adj(i)) {
                inDegree[node]++;
            }
        }

        // Create a queue and enqueue all vertices with indegree 0
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i=0; i<V; ++i){
            if (inDegree[i] == 0)
                q.add(i);
        }

        // count of visited vertices
        int cnt = 0;

        // List to store the topological ordering of the vertices
        List<Integer> topoOrder = new ArrayList<Integer>();
        while (!q.isEmpty()) {

            // Extract front of queue and add it to topoOrder
            int u = q.poll();
            topoOrder.add(u);

            // Iterate through all the neighbouring nodes
            // and decrease their in-degree by 1
            for (int node : graph.adj(u)){

                // if inDegree becomes 0, add it to queue
                if (--inDegree[node] == 0)
                    q.add(node);
            }

            // Increase the count of visited vertices
            cnt++;
        }

        if (cnt != V) {
            // There is a cycle in the graph
            topoOrder.clear();
        }

        return topoOrder;
    }

}
