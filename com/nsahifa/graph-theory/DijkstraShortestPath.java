/**
 * An implementation of the Dijkstra's Single Source Shortest Path
 * algorithm for graphs with non-negative edge weights
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */
import graph.Edge;
import graph.WeightedAdjacencyList;

import java.util.*;

public class DijkstraShortestPath {

    private final double EPS = 1e-6;

    private Integer[] prev;

    // The Edge object structure
    public static class Node {
        int id;
        double dist;

        public Node (int id, double dist){
            this.id = id;
            this.dist = dist;
        }
    }

    // A comparison function for the object structure Node
    private Comparator<Node> comparator = new Comparator<Node>() {
        public int compare(Node o1, Node o2) {
            if (Math.abs(o1.dist-o2.dist)<EPS) return 0;
            return ((o1.dist-o2.dist)>0 ? 1 : -1);
        }
    };

    // Dijkstra algorithm applied to DAG graph
    // Return an array containing dist to each node from start
    // Complexity : O(E*log(V))
    // E : the number of edges / V : the number of vertices
    public double[] dijkstraSSSP(WeightedAdjacencyList graph, int start){

        int V = graph.getV();

        double[] dist = new double[V];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[start] = 0;

        boolean[] visited = new boolean[V];

        // Array that stores the optimal path
        prev = new Integer[V];

        // PriorityQueue to tell which node
        // to visit next based on sorted min value
        PriorityQueue<Node> pq = new PriorityQueue<Node>(2*V, comparator);
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()){
            Node node = pq.poll();
            visited[node.id] = true;

            // We have already found a better path routing
            // through others nodes before we got
            // to processing this node
            if (dist[node.id] > node.dist) continue;

            for (Edge child : graph.adj(node.id)){

                if (visited[child.getDestination()]) continue;
                double newDist = dist[node.id] + child.getWeight();

                // Relax the edge by updating the cost if applicable
                if (newDist < dist[child.getDestination()]){
                    dist[child.getDestination()] = newDist;

                    // O(log(V)) is the cost of insert into the PQ
                    pq.add(new Node(child.getDestination(), newDist));

                    // node.id = child.getSource
                    // track the path to child.getDestination
                    prev[child.getDestination()] = node.id;
                }
            }
        }
        return dist;
    }

    // Reconstruct the shortest path from the start node to the end node
    public List<Integer> reconstructPath(WeightedAdjacencyList graph, int start, int end){

        List<Integer> path = new ArrayList<Integer>();
        double dist = dijkstraSSSP(graph, start)[end];

        if (dist == Double.POSITIVE_INFINITY) return path;
        for (Integer v = end; v != null; v=prev[v]){
            path.add(v);
        }
        return path;
    }
}