import java.util.LinkedList;
import java.util.List;

/**
 * Eulerian Path and Circuit Problem
 * @author : Nabil Sahifa
 * @email : nabilsahifa@gmail.com
 */

public class EulerianPathAndCircuitDirectedGraph {

    /**
     * An Eulerian Path is a path of edges that visits all the edges
     * in a graph exactly once
     *
     * Conditions of existence :
     * - Undirected Graph : Either every vertex has even degree
     *                      or exactly two vertices have odd degres (start and end nodes)
     * - Directed Graph : At most one vertex has (outdegree)-(indegree)=1
     *                    and at most one vertex has (indegree)-(outdegree)=1
     *                    start node has one extra outgoing edge, and similaraly
     *                    the end node has one extra ingoing node
     *
     * An Eulerian circuit (or cycle) is an Eulerian path which starts
     * and ends on the same vertex
     *
     * Conditions of existence :
     * - Undirected Graph : Every vertex has an even degree
     * - Directed Graph : Every vertex has equal indegree and outdegree
     *
     * Note : If a graph has an Eulerian circuit, it also has an Eulerian path
     * An additional requirement when finding paths/circuits is that all vertices with nonzero
     * degree need to belong to a single connected component
     *
     * Idea of find an Eulerian path: Once we get stuck (meaning the current node has no unvisited
     * outgoing edges, we backtrack and add the current node to the solution)
     */
    // Number of nodes and edges
    private int n, m;
    private int[] in, out;
    private LinkedList<Integer> path;
    private List<List<Integer>> graph;

    public EulerianPathAndCircuitDirectedGraph(List<List<Integer>> graph){
        if (graph == null){
            throw new IllegalArgumentException("Input Graph is null");
        }
        this.n = graph.size();
        this.graph = graph;
        path = new LinkedList<Integer>();
    }

    public int[] eulerianPath(){
        setUp();
        if (m == 0) return null;

        if (!hasEulerianPath()) return null;

        // Run dfs on the starting node
        dfs(findStartNode());

        // Verify that all edges were traversed
        // graph might be disconnected
        if (path.size() != m+1) return null;

        int[] eulerianPath = new int[m+1];
        for (int i=0; !path.isEmpty(); i++){
            eulerianPath[i] = path.removeFirst();
        }
        return eulerianPath;
    }

    private void setUp(){
        // Set up indegree and outdegree arrays
        in = new int[n];
        out = new int[n];
        m = 0;

        for (int start=0; start<n; start++){
            for (int to : graph.get(start)){
                in[to]++;
                out[start]++;
                m++;
            }
        }
    }

    // Check the conditions of existence of Eulerian path for un undirected graph
    private boolean hasEulerianPath(){
        int start=0, end=0;
        for (int i=0; i<n; i++){
            if (out[i]-in[i]>1 || in[i]-out[i]>1)
                return false;

            else if (out[i]-in[i] == 1) start++;
            else if (in[i]-out[i] == 1) end++;
        }
        return ((start == 0 && end == 0) || (start == 1 || end == 1));
    }

    // find starting based on values in in and out arrays
    private int findStartNode(){
        int start = 0;
        for (int i=0; i<n; ++i){
            // Unique starting node
            if(out[i]-in[i]==1){
                start = i;
                break;
            }
            // start at a node with an outgoing edge
            if (out[i]-in[i]==1)
                start = i;
        }
        return start;
    }

    // out array is being used as both a way of determining if there are
    // any unviisted edges left at the current node as well as an index
    // for reaching the adjacency list to pick the next node to visit
    private void dfs(int at){
        while (out[at] != 0){
            int next = graph.get(at).get(--out[at]);
            dfs(next);
        }
        path.addFirst(at);
    }
}
