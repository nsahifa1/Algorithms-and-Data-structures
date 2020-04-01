/**
 * An implementation of the Bellman-Ford algorithm
 * A Single Source Shortest Path Algorithm with
 * a complexity of O(EV)
 * E is the number of edges and V is the number of vertices
 * Bellman-Ford can be used to detect negatives cycles
 * and determine where they occur
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
        Arrays.fill(dist, Double.POSITIVE_INFINITY);

        for (int i=0;i<=V-1;++i){

            // After i iteration, all shortest paths with at most i edges will be found
            // Some shortest paths with more than i edges will also be found

            // V-1 is the maximal length of a shortest path in the graph

            for (List<Edge> edges : graph.getAdjList()){
                for (Edge edge : edges){
                    if (dist[edge.getSource()] + edge.getWeight() < dist[edge.getDestination()])

                        // Relax the edge by the new weight
                        dist[edge.getDestination()] = dist[edge.getSource()] + edge.getWeight();
                }
            }
        }

        // We run the algorithm a second time to detect negative cycles
        // In the dist table we do not distinguish between nodes which are
        // directly in negative cycle and nodes which are reachable by negative cycles
        boolean isNegativeCycle = true;
        for (int i=0;i<V-1;++i){
            isNegativeCycle = false;

            for (List<Edge> edges : graph.getAdjList()){
                for (Edge edge : edges){
                    if (dist[edge.getSource()] + edge.getWeight() < dist[edge.getDestination()){
                        dist[edge.getDestination()] = Double.NEGATIVE_INFINITY;
                        isNegativeCycle = true;
                    }
                }
            }
        }

        return dist;
    }
}
