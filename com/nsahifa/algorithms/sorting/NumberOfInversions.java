/**
 * Implementation of the algorithm for counting the number of inversions
 *
 * Reminder : Let A[0 ... n-1] be an array of n distinct positive integers.
 * If i < j and A[i] > A[j] then the pair (i, j) is called an inversion of A
 *
 * the number of inversions is the required number of swaps of adjacent
 * elements to sort a permutation
 */
public class NumberOfInversions {

    public static int mergeSort(int[] arr, int start, int end) {

        // Inversion count will be the sum of the inversions in the left subarray,
        // the right subarray and the inversions in merging
        int inversion_cnt = 0;
        if (start < end) {

            // divide the array into two parts
            int mid = (start + end)/2;

            // count the inversions in the left subarray
            inversion_cnt += mergeSort(arr, start, mid);

            // count the inversions in the right subarray
            inversion_cnt += mergeSort(arr, mid+1, end);

            // count the inversions while merging the two parts
            inversion_cnt += merge(arr, start, mid, end);
        }
        return inversion_cnt;
    }

    // This function merges two sorted arrays and returns
    // the inversion count in the merged array
    private static int merge(int[] arr, int start, int mid, int end) {

        int p = start, q = mid+1;
        int pos = 0, inversion_cnt = 0;
        int[] tmp = new int[end-start+1];

        while ((p<=mid) && (q<=end)) {
            if (arr[p] <= arr[q]){
                tmp[pos++] = arr[p++];
            }
            else {
                tmp[pos++] = arr[q++];

                // there is (mid-p) inversions, because left and right subarray
                // are sorted. So all the remaining elements in left subarray
                // (arr[p+1),...,arr[mid]) will be greater than arr[q]
                inversion_cnt += (mid - p);
            }
        }
        while (p<=mid) {
            tmp[pos++] = arr[p++];
        }
        while (q<=end) {
            tmp[pos++] = arr[q++];
        }

        // copy back the merged elements to the original array
        for (int k=start;k<=end;++k)
            arr[k] =  tmp[k];

        return inversion_cnt;
    }

}
