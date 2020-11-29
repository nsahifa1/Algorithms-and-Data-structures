/**
 * An implementation of the Bellman-Ford algorithm
 * A Single Source Shortest Path Algorithm with
 * a complexity of O(EV)
 * E is the number of edges and V is the number of vertices
 * Bellman-Ford can be used to detect negatives cycles
 * and determine where they occur
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */
import graph.Edge;
import graph.WeightedAdjacencyList;

import java.util.Arrays;
import java.util.List;

public class BellmanFord {

    // Complexity : O(VE)
    public double[] bellmanFord(WeightedAdjacencyList graph, int start){

        // The number of vertices
        int V = graph.getV();

        double[] dist = new double[V];
        dist[start] = 0;
        Arrays.fill(dist, Double.POSITIVE_INFINITY);

        boolean relaxedEdge = true;

        for (int i=0;i<V-1 && relaxedEdge;++i){

            // After k iteration, all shortest paths with at most k edges will be found
            // Some shortest paths with more than k edges will also be found

            // V-1 is the maximal length of a shortest path in the graph
            // otherwise the path would be a cycle

            // Optimization : if in one iteration no relaxation yields any improvement,
            // it means that we already found all shortest paths for all nodes

            // important note : The edges do not need to be processed in any specific order
            // the distance array is the same after relaxing the edges (V-1) times
            relaxedEdge = false;
            for (List<Edge> edges : graph.getAdjList()){
                for (Edge edge : edges){

                    // This verification is needed only if the graph contains negative weight edges
                    if (dist[edge.getSource()] != Double.POSITIVE_INFINITY){
                        if (dist[edge.getSource()] + edge.getWeight() < dist[edge.getDestination()]){
                            // Relax the edge by the new weight
                            dist[edge.getDestination()] = dist[edge.getSource()] + edge.getWeight();

                            relaxedEdge = true;
                        }
                    }
                }
            }
        }

        // We run the algorithm a second time to detect negative cycles
        // A negative cycle has occurred if we can find a better path
        // beyond the optimal solution

        // If a node is part of a negative cycle then the minimum cost
        // for that node is set to Double.NEGATIVE_INFINITY

        // In the dist table we do not distinguish between nodes which are
        // directly in negative cycle and nodes which are reachable by negative cycles
        relaxedEdge = true;
        for (int i=0;i<V-1 && relaxedEdge;++i){
            relaxedEdge = false;

            for (List<Edge> edges : graph.getAdjList()){
                for (Edge edge : edges){
                    if (dist[edge.getSource()] + edge.getWeight() < dist[edge.getDestination()]){
                        dist[edge.getDestination()] = Double.NEGATIVE_INFINITY;
                        relaxedEdge = true;
                    }
                }
            }
        }

        return dist;
    }
}
