/**
 * Tarjan's Strongly Connected Components algorithm
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */

import graph.AdjacencyList;
import graph.WeightedAdjacencyList;

import java.util.Arrays;
import java.util.Stack;

public class TarjansSCC {

    /**
     * Tarjan's algorithm make use of The Stack Invariant by maintaining
     * a set (often as a stack) of valid nodes from which to update
     * low-link values from
     *
     * the new low-link update condition : (u, v) are nodes in the graph,
     * we're currently exploring u then our new low-link update condition is that :
     * To update node u's low-link value to node v's low-link value there has to be
     * a path of edges from u to v and node v must be on the stack
     *
     * A node started a connected component if its ids[node] = low[node]
     */

    private int[] ids, low;
    private boolean[] onStack;
    private Stack<Integer> stack;

    private static final int UNVISITED = -1;
    private int id, sccCount;

    public void dfs(AdjacencyList graph, int node){

        // Upon visiting a node we assign it an id and a low-link
        // value. We also mark the current node as visited and
        // we add it to a seen stack
        stack.push(node);
        onStack[node] = true;
        ids[node] = low[node] = id++;

        // We visit all the neighbours of the current node
        // on the callback from the DFS we min
        // the low-link value of the current node
        for (int child : graph.adj(node)){
            if (ids[child] == UNVISITED) dfs(graph, child);

            // If the previous node is on the stack, we min
            // the low-link value of the current node
            if (onStack[child]) low[node] = Math.min(low[node], low[child]);
        }

        // After visiting all the neighbours of the current node
        // if we're at the start of a SCC, we empty the seen stack until
        // we're back to the start of the SCC
        if (ids[node] == low[node]){
            for (int v = stack.pop(); ;v=stack.pop()){
                onStack[v] = false;
                low[v] = ids[node];
                if (v == node) break;
            }
            sccCount++;
        }
    }

    public int[] findSCC(AdjacencyList g){
        int V = g.V();

        ids = new int[V];
        low = new int[V];
        onStack = new boolean[V];
        Arrays.fill(onStack, false);
        stack = new Stack<Integer>();

        id = 0; sccCount = 0;

        // We mark the id of each node as unvisited
        for (int i=0;i<V;++i) ids[i] = UNVISITED;
        for (int i=0;i<V;++i){
            if (ids[i] == UNVISITED){
                dfs(g, i);
            }
        }
        return low;
    }
}