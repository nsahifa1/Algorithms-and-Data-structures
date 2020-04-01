/**
 * An implementation of the Breadth First Search algorithm
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */

import graph.AdjacencyList;

import java.util.*;

public class BreadthFirstSearch {

    private static int[] prev;

    // BFS algorithm
    // Complexity : O(V+E)
    // V is the number of vertices and E is the number of edges
    public static void bfs(AdjacencyList graph, int start){
        Queue<Integer> q = new LinkedList<Integer>();
        prev = new int[graph.V()];
        q.add(start);

        graph.visited[start] = true;

        while (!q.isEmpty()){
            int node = q.peek();
            q.poll();

            for(int child : graph.adj(node)){
                if (!graph.visited[child]){
                    q.add(child);
                    graph.visited[child] = true;
                    prev[child] = node;
                }
            }
        }
    }

    // TODO : solve the issue with infinite loop

    // Reconstruct the path based on the result
    // of the BFS algorithm
    // Complexity : O(V)
    public static List<Integer> reconstructPath(AdjacencyList graph, int start , int end){
        bfs(graph, start);

        List<Integer> path = new ArrayList<Integer>();

        for (Integer v = end; v != null; v = prev[v]){
            path.add(v);
        }

        Collections.reverse(path);
        if (path.get(0) == start) return path;

        path.clear();
        return path;
    }
}