/**
 * Prim's Minimum Spanning Tree greedy algorithm
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */

import graph.Edge;
import graph.WeightedAdjacencyList;

import java.util.PriorityQueue;
import java.util.Queue;

public class PrimsMST {

    /**
     * Description : Given an undirected weighted graph, MST is a subset of the edges that connects
     * all vertices together (without creating any cycles) while minimizing the total cost
     *
     * - The lazy version of Prim's has a runtime of O(E*log(E)) : inserts up to E edges into the PQ
     * - but the eager version has a better runtime of O(E*log(V)) : we track (node, edge) key value
     *   key-value pairs that can easily be updated and polled to determine the next best edge to add to the MST.
     *   Each node is paired with exactly one of its incoming edges (except for the start node).
     *   Insted of adding edges to the PQ as we iterate over the edges of node, we're going to relax the
     *   destination node's most promising incoming edge
     *
     * Steps of the algorithm :
     * 1. Maintain a min Priority Queue (PQ) that sorts edges based on min edge cost.
     * 2. start the algorithm on any node and mark it as visited, iterate over
     *    all edges of s and add them to the PQ
     * 3. While the PQ is not empty and a MST has not been formed, dequeue the next cheapest edge
     *    from the PQ, if the node it points to has already been visited, then skip it and poll again.
     *    Otherwise, mark the current node as visited and add the selected edge to the MST
     * 4. Iterate over the new current node's edges and add all its edges which doesn't point
     *    to already visited nodes
     */

    // DS that stores {start node, end node, edge cost} tuples
    private Queue<Edge> pq;

    private boolean[] visited;

    public Edge[] mstEdges;

    public int mstCost;

    // Lazy implementation of Prim's MST algorithm
    // complexity : O(E*Log(E))
    private void MSTLazysolver(WeightedAdjacencyList graph, int start){

        int n = graph.getV();
        int m = n-1;

        int edgeCount = 0;

        addEdges(graph, start);

        while (!pq.isEmpty() && edgeCount != m){
            Edge edge = pq.poll();
            int node = edge.getDestination();

            if (visited[node]) continue;

            mstEdges[edgeCount++] = edge;
            mstCost += edge.getWeight();

            addEdges(graph, node);
        }

        if (edgeCount != m){
            // No MST tree found
            mstCost = -1;
            mstEdges = new Edge[n];
        }
    }

    public void PrimsMST(WeightedAdjacencyList graph){
        int n = graph.getV();

        pq = new PriorityQueue<Edge>();
        visited = new boolean[n];
        mstCost = 0;
        mstEdges = new Edge[n];

        MSTLazysolver(graph, 0);
    }

    private void addEdges(WeightedAdjacencyList graph, int node){
        visited[node] = true;
        for (Edge edge : graph.adj(node)){
            if (!visited[edge.getDestination()]){
                pq.add(edge);
            }
        }
    }
}
