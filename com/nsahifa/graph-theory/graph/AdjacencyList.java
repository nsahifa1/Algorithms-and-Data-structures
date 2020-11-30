/**
 * Implementation of the adjacency list
 */
package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdjacencyList {

  // Number of vertices
  private int V;

  // Adjacencey List
  private List<Integer>[] adjList;

  // boolean array to track the visited nodes
  public boolean[] visited;

  // Constructor
  public AdjacencyList(int V) {
    this.V = V;

    visited = new boolean[V];

    adjList = new ArrayList[V];
    for (int v = 0; v < V; ++v) {
      adjList[v] = new ArrayList<Integer>();
    }
  }

  // return the number of vertices
  public int V() {
    return V;
  }

  // Add edge between v and w
  public void addUndirectedEdge(int v, int w) {
    adjList[v].add(w);
    adjList[w].add(v);
  }

  // Add directed edge between v and w
  public void addDirectedEdge(int v, int w) {
    adjList[v].add(w);
  }

  // return the child of v from the adjacency list
  public List<Integer> adj(int v) {
    return adjList[v];
  }

  // method to unmark all vertices
  public void clear() {
    Arrays.fill(visited, false);
  }

  // Method for printing the Adjacency List
  public void printGraph() {
    for (int v = 0; v < V; ++v) {
      if (adjList[v].size() == 0) continue;
      System.out.println("Vertex " + v + " is connected to : ");
      for (int child : adjList[v]) {
        System.out.println(child + " ");
      }
      System.out.println();
    }
  }
}