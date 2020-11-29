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

    // dp[k][i][j] = shortest path from i to j routing through node {0,1,...k-1,k}
    // dp[k][i][j] = min(dp[k-1][i][j], dp[k-1][i][k]+dp[k-1][k][j]
    // dp[k-1][i][j] : the best distance from i to j with values
    // routing through nodes {0,1,...,k-1}
    // dp[k][i][j] = min(dp[k-1][i][j], dp[k-1][i][k]+dp[k-1][k][j])

    // We can compute the solution for k in-place saving us a dimension
    // of memory and reducing the space complexity to O(V^2)
    // The recurrence is as follow :
    // dp[i][j] = m[i][j] if k=0 : the base case
    // dp[i][j] = min(dp[i][j], dp[i][k]+dp[k][j]) otherwise

    // Complexity : O(V^3)
    public void floydWarshall(double[][] matrix){

        V = matrix.length;
        dp = new double[V][V];
        next = new Integer[V][V];

        // The base case, corresponds to k=0
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
                    if (dp[i][k]!=Double.POSITIVE_INFINITY && dp[k][j]!=Double.POSITIVE_INFINITY){
                        // Avoid using transitions using paths that doesn't exist in the
                        // case of the presence og negative weight edgers in the graph
                        if (dp[i][k] + dp[k][j] < dp[i][j]){
                            dp[i][j] = dp[i][k] + dp[k][j];
                            next[i][j] = next[i][k];
                        }
                    }
                }
            }
        }

        // Detect and propagate negative cycles
        // The important thing to ask ourselves is does the optimal path from node i to j
        // goes through a negative cycle, if so, the path is affected and is compromised

        // After running FW one more time, if the distance can be improved, we set the optimal distance to -oo
        // Each edge marked with -oo is either part of or reaches into a negative cycle
        for (int k=0;k<V;++k){
            for (int i=0;i<V;++i){
                for (int j=0;j<V;++j){
                    if(dp[i][k]!=Double.POSITIVE_INFINITY && dp[k][j]!=Double.POSITIVE_INFINITY){
                        if (dp[i][k] + dp[k][j] < dp[i][j]){
                            dp[i][j] = Double.NEGATIVE_INFINITY;
                            next[i][j] = -1;
                        }
                    }
                }
            }
        }

        // Second approach for detecting negative weight cycles :
        // initially dp[v][v]=0, but after running the algorithm, dp[v][v] will be < 0
        // if there exists a negative length path from v to v
        // We iterate over all paris of vertices (i, j) and check whether
        // one og the intermediate vertices t has dp[t][t]<0
        for (int i=0;i<V;++i){
            for (int j=0;j<V;++j){
                for (int t=0;t<V;++t){
                    if (dp[i][t]!=Double.POSITIVE_INFINITY && dp[t][t]<0
                            && dp[t][j]!=Double.POSITIVE_INFINITY){
                        // vertex t is part of a cycle of negative weight and
                        // it can be reached from i and j can be reached from t
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