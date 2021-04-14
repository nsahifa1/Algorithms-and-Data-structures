/**
 * Implementation of QuickSort algorithm using Divide and Conquer approach
 * Different approaches of picking pivot element are implemented above
 * 1. first element as pivot
 * 2. random element as pivot
 * 3. median as pivot
 * 4. 3-Way QuickSort (Dutch National Flag Algorithm)
 *
 * Complexity analysis : Time taken by the QuickSort is :
 * T(n) = T(k) + T(n-k-1) + O(n)
 * k is the number of elements which are smaller than pivot
 * O(n) is for processing the current partition
 * Worst case : T(n) = T(0)+T(n-1)+O(n)=T(n-1)+O(n) === O(n^2)
 * Best case : T(n) = 2T(n/2)+O(n) === solution of the recurrence is O(nlogn)
 */
public class QuickSort {

    private int partition(int arr[], int start, int end) {
        // i represents the boundary between the elements that are
        // less than pivot and those greater than pivot
        int i = start+1;

        // We make the first element as pivot element
        int pivot = arr[start];
        for (int j=start+1; j<=end; ++j){
            // We rearrange the array by puttin elements which are less than pivot
            // on one side and which are greater on other
            if (arr[j] < pivot){
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
            }
        }

        // We put the pivot in its proper place
        int tmp = arr[start];
        arr[start] = arr[i-1];
        arr[i-1] = tmp;

        // return the position of the pivot
        return i-1;
    }

    public void sort(int arr[], int start, int end) {
        if (start < end) {

            // Retrieve the position of the pivot element
            int pivot = partition(arr, start, end);

            // sorts both sides of the pivot element
            sort(arr, start, pivot-1);
            sort(arr, pivot+1, end);
        }
    }
}
