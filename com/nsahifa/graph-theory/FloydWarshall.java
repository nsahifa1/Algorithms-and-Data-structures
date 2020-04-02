/**
 * An implementation of the Floyd-Warshall algorithm
 * it returns the shortest path between all pairs of nodes
 * this class contains also an algorithm for detecting negative
 * cycles and an algorithm for reconstructing the shortest path
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */
import java.util.ArrayList;
import java.util.List;

public class FloydWarshall {

    // The number of vertices
    int V;

    double[][] dp;
    Integer[][] next;

    // We gradually build up all intermediate routes
    // between nodes i and j to find the optimal path

    // dp[k][i][j] = min(dp[k-1][i][j], dp[k-1][i][k]+dp[k-1][k][j])
    // dp[k][i][j] = shortest path from i to j routing through node {0,1,...k-1,k}
    // We can compute the solution for k in-place saving us a dimension
    // of memory and reducing the space complexity to O(V^2)
    // The recurrence is as follow :
    // dp[i][j] = m[i][j] if k=0
    // dp[i][j} = min(dp[i][j], dp[i][k]+dp[k][j]) otherwise

    // Complexity : O(V^3)
    public void floydWarshall(double[][] matrix){

        V = matrix.length;
        dp = new double[V][V];
        next = new Integer[V][V];

        for (int i=0;i<V;++i){
            for (int j=0;j<V;++j){
                dp[i][j] = matrix[i][j];
                if (matrix[i][j] != Double.POSITIVE_INFINITY)
                    next[i][j] = j;
            }
        }

        // Find all pairs shortest paths
        for (int k=0;k<V;++k){
            for (int i=0;i<V;++i){
                for (int j=0;j<V;++j){
                    if (dp[i][k] + dp[k][j] < dp[i][j]){
                        dp[i][j] = dp[i][k] + dp[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        // Negative cycle
        // The important thing to ask ourselves is does the optimal path
        // from node i to node j go through a negative cyle, if so
        // the path is affected and is compromised

        // After running FW one more time, if the distance can be improved
        // we set the optimal distance to -oo
        // Each edge marked with -oo is either part of or reaches
        // into a negative cycle
        for (int k=0;k<V;++k){
            for (int i=0;i<V;++i){
                for (int j=0;j<V;++j){
                    if (dp[i][k] + dp[k][j] < dp[i][j]){
                        dp[i][j] = Double.NEGATIVE_INFINITY;
                        next[i][j] = -1;
                    }
                }
            }
        }
    }

    public List<Integer> reconstructPath(int start, int end){
        List<Integer> path = new ArrayList<Integer>();

        // We start by checking if there exists a path
        // between the start node and the end node
        if (dp[start][end] == Double.POSITIVE_INFINITY)
            return null;

        int v = start;

        // Reconstruct the path from the next matrix
        for (; v != end; v = next[v][end]){
            if (v == -1) return null;
            path.add(v);
        }

        if (next[v][end] == -1) return null;
        path.add(v);

        return path;
    }
}