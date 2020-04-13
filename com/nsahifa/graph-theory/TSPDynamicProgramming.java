/**
 * Travelling Salesman Problem algorithm
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */
import java.util.ArrayList;
import java.util.List;

public class TSPDynamicProgramming {

    /**
     * Description : given a complete weighted graph, what is the
     * Hamiltonian cycle (path that visits every node once) of minimum cost
     *
     * DP approach : compute and store the optimal value from S to each node X (!= S).
     * This will solve the problem for all paths of length n = 2
     *
     * To compute the optimal solution for paths of length 3, we need
     * to remember (store) two things from each of the n=2 cases
     * The dp state :
     * 1. The set of visited nodes in the subpath
     *      -> 2^N possible subsets of visited nodes
     *      -> 32-bit integer is used to represent the set of visited nodes
     * 2. The index of the last visited node in the path
     *      -> N possible nodes that could have visited last
     * -> space complexity needed : O(N2^N)
     *
     * To solve the rest of the states, we're going to take the solved subpath from n-1 and add another
     * edge extending to a node which has not already been visited from the last visited node
     *
     * To complete the TSP tour, we need to connect our tour back to the start node S, we loop over the end state
     * (where the binary representation is composed of N 1's) in the memo table for every possible end position
     * and minimize the lookup value plus the cost of going back to S
     */

    // Variable that holds the TSP minimum cost
    double minCost;

    // array that holds the TSP optimal tour
    int[] optimalTour;

    /**
     * Initialize the memo table by caching the optimal
     * solution from the start node to every other node
     */
    private void setup(double[][] adjMat, double[][] memo, int start, int N){
        for (int i=0;i<N;++i){
            if (i == start) continue;

            // We store the optimal value from
            // the start node to every other node
            memo[i][1<<start | 1<<i] = adjMat[start][i];
        }
    }

    // Function that generates the combinations of size K from N elements
    private void combinations(int set, int at, int N, int K, List<Integer> subsets){
        if (N-at < K) return;
        if (K == 0) subsets.add(set);
        else {
            for (int i=at; i<N; ++i){
                // We include the ith element in the set
                set ^= (1<<i);

                combinations(set, i+1, N, K-1, subsets);

                // We backtrack and continue from the position
                // where we did not include this element
                set ^= (1<<i);
            }
        }
    }

    // Helper function that make use of the previous
    // declared combinations function
    private List<Integer> combinations(int N, int K){
        List<Integer> subsets = new ArrayList<Integer>();
        combinations(0, 0, N, K, subsets);
        return subsets;
    }

    private boolean notIn(int subset, int elem){
        return ((1<<elem) & subset) == 0;
    }

    private void solve(double[][] adjMat, double[][] memo, int start, int N){

        for (int r=3;r<=N;++r){
            for (int subset : combinations(N, r)){

                if (notIn(subset, start)) continue;

                for (int next=0; next<N; ++next){
                    if (notIn(subset, next) || next == start)
                        continue;

                    // In order to being able to look back and
                    // use the state without the next
                    int prevState = subset^(1<<next);

                    double minDist = Double.POSITIVE_INFINITY;

                    // Iterate over all end nodes
                    for (int end = 0; end<N; ++end){

                        // end node cannot be the start or the next node
                        // and should be part of the subset
                        if (end == start || end == next || notIn(subset, end))
                            continue;

                        double newDist = memo[end][prevState] + adjMat[end][next];

                        if (newDist < minDist) minDist = newDist;
                    }
                    memo[next][subset] = minDist;
                }
            }
        }
    }

    private double minCostFinder(double[][] adjMat, double[][] memo, int start, int N){

        int END_STATE = (1<<N) - 1;
        double minTourCost = Double.POSITIVE_INFINITY;

        for (int end=0; end<N; ++end){
            if (end == start) continue;

            double tourCost = memo[end][END_STATE] + adjMat[end][start];
            if (tourCost < minTourCost)
                minTourCost = tourCost;
        }

        return minTourCost;
    }

    /**
     * Function that reconstructs TSP tour from memo table
     */
    private int[] optimalTourFinder(double[][] adjMat, double[][] memo, int start, int N){
        int[] tour = new int[N];

        int state = (1<<N) - 1;
        int lastIndex = start;

        for (int i=N-1;i>=1;i--){

            // index represents the node we want to go next
            int index = -1;

            // j represents all possible candidates
            // for the next node
            for (int j=0;j<N;++j){
                if (j==start || notIn(state, j)) continue;
                if (index == -1) index = j;

                double prevDist = memo[index][state] + adjMat[index][lastIndex];
                double newDist = memo[j][state] + adjMat[j][lastIndex];

                if (newDist < prevDist) index = j;
            }

            tour[i] = index;
            state ^= (1<<index);
            lastIndex = index;
        }
        tour[0] = tour[N] = start;
        return tour;
    }

    public void tspSolver(double[][] adjMat, int start){

        int N = adjMat.length;
        minCost = Double.POSITIVE_INFINITY;

        double[][] memo = new double[N][1 << N];

        setup(adjMat, memo, start, N);

        solve(adjMat, memo, start, N);

        minCost = minCostFinder(adjMat, memo, start, N);

        optimalTour = optimalTourFinder(adjMat, memo, start, N);
    }
}