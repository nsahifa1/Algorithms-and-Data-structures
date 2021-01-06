import java.util.Arrays;

/**
 * Problem : Number of increasing sub-sequences of length L
 *
 *
 * Approach : for each i=1,2,...,N, and each j=1,2,...,K, we try to find the number of
 * increasing subsequences of length j that end at index i. Call this dp[j][i]
 * Base case : dp[1][i] = 1 for all i. For j>1, to form an increasing subsequence of this length
 * ending at index i, we must use the element at index i and some preceding increasing subsequence
 * of length (j-1). So we need to sum dp[j-1][k] where k<i and A[k]<A[i].
 *
 * To do this efficiently, for each j, we keep the values in a a binary indexed tree in the form of BIT[j][m]
 * that represent the number of increasing subsequences ending on a element of value m of length j.
 *
 * So, we compute the values dp[j][i] by summing over BIT[j-1][m] where m<A[i].
 * Then, we add the newly computed value of dp[j][i] to the BIT at entry BIT[j][A[i]].
 *
 * Finally, at the very end, we just sum over all the value BIT[L][A[i]] over all values of i
 *
 * Complexity of the naive dynammic programming appraoch : O(n^2. K)
 * Complexity of the dynamic programming approach using BIT : O(n.k.log(n))
 * Space complexity for both approaches : O(n.k)
 *
 * Note : the same solution can be used to solve the problem of count inversions of size L in a array
 */
public class NumberOfIncreasingSubsequences {

    /**
     * Naive dynamic programming approach
     * for i=1 to n do
     *    dp[i][1] = 1
     * for i=1 to n do
     *   for j=1 to i-1 do
     *     if A[i] > A[j]
     *       for k=2 to L do
     *         dp[i][k] += dp[j][k-1]
     *
     * The answer is dp[1][L] + dp[2][L] + ... + dp[n][L]
     */

    // Max values for K and N
    static int K=80, N=100000;
    static int BIT[][] = new int[K][N];

    // Function to convert an array to an array with values from 1 to n
    static void convert (int arr[], int n) {
        int temp[] = new int[n];
        for (int i=0;i<n;++i)
            temp[i] = arr[i];
        Arrays.sort(temp);

        // Make use of the lower_bound function
        // to return the converted value of arr[i]
        for (int i=0;i<n;++i) {
            arr[i] = Arrays.binarySearch(temp, arr[i]) + 1;
        }
    }

    // Update function of the BIT, the parameter k denotes the k'th BIT
    static void add(int k, int idx, int val) {
        while(idx <= N) {
            BIT[k][idx] += val;
            idx = idx + (idx & (-idx));
        }
    }

    // Function to get the prefix sum at index 'idx' of the k'th BIT
    static int prefixSum(int k, int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += BIT[k][idx];
            idx = idx - (idx & (-idx));
        }
        return sum;
    }

    // Returns the count of number of increasing sub-sequences
    static int solve(int arr[], int N, int K) {

        convert(arr, N);

        for (int i=0; i<N; ++i) {
            int x = arr[i];

            add(1, x, 1);

            // This step correspond to summing over BIT[m][j-1] where m < x=arr[i]
            // and updating of the BIT at entry BIT[k][x]
            for (int k=2; k<=K; ++k) {
                add(k, x, prefixSum(k-1, x-1));
            }
        }

        // Finally, we just sum over all the value BIT[K][A[i]] over all values of i
        // in the naive dp approach the result is equal to dp[L][1] + dp[L][2] + ... + dp[L][N]
        return prefixSum(K, N);
    }
}