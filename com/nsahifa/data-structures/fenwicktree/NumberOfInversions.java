package fenwicktree;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Implementation of the algorithm for counting
 * the number of inversions using Fenwick Tree
 *
 * Approach : Traverse through the array and for each element
 * we find the number of smaller elements on its right side of the array
 * We get this sum from the sum method of BIT
 *
 */
public class NumberOfInversions {

    // We convert the elements in the array due to one of the following reasons :
    // 1. Elements of the array are negative
    // 2. Elements of the array are not uniformly distributed
    // Complexity : O(nlog(n))
    public static void convert(int [] arr) {
        int[] temp = arr.clone();
        Arrays.sort(temp);
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        int value = 1;
        for (int e : temp) {
            map.put(e, value++);
        }

        for (int i=0; i<arr.length; ++i) {
            arr[i] = map.get(arr[i]);
        }
    }

    public static int inversions(int[] arr) {

        int N = arr.length;
        int inversion_cnt = 0;
        convert(arr);

        int maximum = 0;
        for (int e : arr) {
            maximum = Math.max(maximum, e);
        }

        // Create a Fenwick Tree with size equal to maximum+1
        FenwickTree fenwickTree = new FenwickTree(maximum);

        // Traverse the array from right
        for (int i=N-1; i>=0; --i) {

            // Count the elements smaller than arr[i]
            inversion_cnt += fenwickTree.prefixSum(arr[i]-1);

            // Add arr[i] to Fenwick Tree
            fenwickTree.add(arr[i], 1);
        }

        return inversion_cnt;
    }
}
