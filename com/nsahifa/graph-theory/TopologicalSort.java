/**
 * An implementation of the topological sorting algorithm
 * Contains also an implementation of the Singe Source Shortest Path
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */
import graph.Edge;
import graph.WeightedAdjacencyList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO : add unit tests
public class TopologicalSort {

    // Assumption : graph is DAG
    // We can use Tarjan's algorithm to check for DAG property
    public static List<Integer> topoSort(WeightedAdjacencyList graph){
        List<Integer> topoSort = topoSortUtil(graph);
        Collections.reverse(topoSort);
        return topoSort;
    }

    // The Topological Sorting algorithm
    // Complexity : O(V+E)
    public static List<Integer> topoSortUtil(WeightedAdjacencyList graph){

        List<Integer> topoList = new ArrayList<Integer>();

        for (int v=0; v<graph.getV();++v){
            if (!graph.visited[v]){
                dfs(graph, v, topoList);
            }
        }
        return topoList;
    }

    // DFS algorithm for traversing the graph
    // Complexity : O(V+E)
    public static void dfs(WeightedAdjacencyList graph, int node, List<Integer> topoList){
        graph.visited[node] = true;
        for (Edge child : graph.adj(node)){
            if(!graph.visited[child.getDestination()]){
                dfs(graph, child.getDestination(), topoList);
            }
        }
        topoList.add(node);
    }

    // Single Source Shortest Path in a DAG
    // We start by getting an ordering of the vertices
    // based on the topological sorting og the graph
    public static Double[] shortestPathDAG(WeightedAdjacencyList graph, int start){
        Double[] dist = new Double[graph.getV()];
        List<Integer> topoSort = topoSort(graph);

        dist[start] = 0.0;

        for (int i=0;i<topoSort.size();++i){
            if (dist[i]!=null){
                for (Edge child : graph.adj(i)){

                    Double newDist = dist[i] + child.getWeight();

                    // We perform a relaxation step by updating
                    // the distance to the child
                    if (dist[child.getDestination()] == null)
                        dist[child.getDestination()] = newDist;
                    else dist[child.getDestination()] = Math.min(dist[child.getDestination()], newDist);
                }
            }
        }
        return dist;
    }
}