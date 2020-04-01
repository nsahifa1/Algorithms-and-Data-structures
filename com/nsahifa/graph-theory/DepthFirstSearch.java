/**
 * An implementation of the depth first search algorithm
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */

import graph.AdjacencyList;

public class DepthFirstSearch {

    // DFS algorithm for travesing the graph
    // Complexity : O(V+E)
    public static int dfs(AdjacencyList graph, int node){

        graph.visited[node] = true;
        int nodeCount = 1;

        for (int child : graph.adj(node)){
            if(!graph.visited[child]){
                nodeCount += dfs(graph, child);
            }
        }
        return nodeCount;
    }

    // Algorithm that returns the number of
    // connected components
    // Complexity : O(V+E)
    public static int connectedComponents(AdjacencyList graph){
        int V = graph.V();
        int count = 0;

        for (int v=0;v<V;++v){
            if (!graph.visited[v]){
                count++;
                dfs(graph, v);
            }
        }
        return count;
    }
}