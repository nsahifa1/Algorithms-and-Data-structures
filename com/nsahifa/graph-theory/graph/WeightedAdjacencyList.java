/**
 * Implementation of the weighted adjacency list
 */
package graph;

import java.util.ArrayList;
import java.util.List;

public class WeightedAdjacencyList {

    // Number of vertices
    private int V;

    // Adjacency list
    private List<Edge>[] adjList;

    // boolean array to track the visited nodes
    public boolean[] visited;

    // constructor
    public WeightedAdjacencyList(int V){
        this.V = V;

        this.visited = new boolean[V];

        this.adjList = new ArrayList[V];
        for (int v=0; v<V; ++v){
            adjList[v] = new ArrayList<Edge>();
        }
    }

    // getter for the number of vetices in the graph
    public int getV() {
        return V;
    }

    // add a directed edge into the graph
    public void addDirectedEdge(int source, int destination, int weight){
        Edge edge = new Edge(source, destination, weight);
        adjList[source].add(edge);
    }

    // Add an undirected edge into the graph
    public void addUndirectedEdge(int source, int destination, int weight){
        Edge edge = new Edge(source, destination, weight);
        adjList[source].add(edge);

        edge = new Edge(destination, source, weight);
        adjList[destination].add(edge);
    }

    // Return the children of the node v
    public List<Edge> adj(int v){
        return adjList[v];
    }

    // Method for printing the graph
    public void printGraph(){
        for (int v=0; v<V; ++v){
            if (adjList[v].size() == 0) continue;
            System.out.println("Vertex " + v + "is connected to : ");
            for (Edge child : adjList[v]){
                System.out.println(child.getDestination() + " ");
            }
        }
    }
}