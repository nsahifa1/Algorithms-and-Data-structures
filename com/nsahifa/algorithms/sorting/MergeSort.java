import java.util.Arrays;

/**
 * Implementation of Merge Sort
 * Time complexity : O(nlogn) : the algorithm divides the array
 * into two halves and take linear time to merge two halves
 */
public class MergeSort {

    public static void mergeSort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = (start+end)/2;

            // sort the left subarray
            mergeSort(arr, start, mid);
            // sort the right subarray
            mergeSort(arr, mid+1, end);

            // merge the two subarrays
            merge(arr, start, mid, end);
        }
    }

    private static void merge(int[] arr, int start, int mid, int end) {

        // stores the starting positions of both subarrays
        int p = start, q = mid+1;
        int pos = 0;
        int[] tmp = new int[end-start+1];

        for (int i=start; i<=end; ++i) {
            if (p>mid) {
                // The first subarray comes to an end
                tmp[pos++] = arr[q++];
            }
            else if (q>end) {
                // The second subarray comes to an end
                tmp[pos++] = arr[p++];
            }
            else if (arr[p]<arr[q]) {
                // the left subarray has smaller element
                tmp[pos++] = arr[p++];
            }
            else {
                tmp[pos++] = arr[q++];
            }

            for (int k=0; k<pos; ++k) {
                // copy the sorted array back to the orginal one
                arr[start++] = tmp[k++];
            }
        }
    }

}
