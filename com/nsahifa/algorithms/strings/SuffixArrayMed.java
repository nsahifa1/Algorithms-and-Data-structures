/**
 * Suffix array medium speed implementation using rank sorting algorithm
 * Complexity : O(N*LogN*LogN)
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */

import java.util.Arrays;

public class SuffixArrayMed extends SuffixArray {

    // Implementation of the SuffixTuple object structure
    private static class SuffixTuple implements Comparable<SuffixTuple> {

        // index of the suffix
        int idx;

        // the rank and nxtRank of the suffix
        int rank, nxtRank;

        // sort suffixes bases on the rank and the nxtRank
        public int compareTo(SuffixTuple o) {
            if (rank != o.rank) return Integer.compare(rank, o.rank);
            return Integer.compare(nxtRank, o.nxtRank);
        }

        @Override
        public String toString() {
            return idx + " ( " + rank + ", " + nxtRank + " ) ";
        }
    }

    public SuffixArrayMed(String s) {
        super(toIntArray(s));
    }

    public SuffixArrayMed(int[] T) {
        super(T);
    }

    /**
     * The main idea: if we have sorted suffixes according to first 2^i char
     * then we can sort suffixes according to first 2^(i+1) char in O(nlogn)
     * using a O(nLogn) sorting algorithm like merge sort as two suffixes
     * can be compared in O(1) time. The sort function is called (Logn) times.
     * Therefore the overall compexity is O(n*Logn*Logn)
     */
    @Override
    protected void construct() {
        SA = new int[N];

        // 2D containing the current rank and the last rank information as well
        int[][] suffixRank = new int[2][N];

        // array mainting the temporary sorted SuffixTuple (rank, nxtRank, idx)
        SuffixTuple[] ranks = new SuffixTuple[N];

        for (int i=0; i<N; ++i) {
            suffixRank[0][i] = T[i];
            ranks[i] = new SuffixTuple();
        }

        for (int pos=1; pos<N; pos*=2) {

            for (int i=0; i<N; ++i){
                SuffixTuple suffixTuple = ranks[i];
                suffixTuple.idx = i;
                suffixTuple.rank = suffixRank[0][i];
                suffixTuple.nxtRank = i+pos<N ? suffixRank[0][i+pos] : -1;
            }

            // O(nLogn)
            Arrays.sort(ranks);

            int newRank = 0;
            suffixRank[1][ranks[0].rank] = 0;

            for (int i=1; i<N; ++i){
                SuffixTuple last = ranks[i-1];
                SuffixTuple current = ranks[i];

                // If the rank pair of the last and the current Suffix Tuple
                // increment the newRank value
                if (last.rank != current.rank || last.nxtRank != last.nxtRank)
                    newRank++;

                suffixRank[1][current.idx] = newRank;
            }

            suffixRank[0] = suffixRank[1];

            // Optimization to stop early
            if (newRank == N-1) break;
        }

        for (int i=0; i<N; ++i){
            SA[i] = ranks[i].idx;
            ranks[i] = null;
        }

        suffixRank[0] = suffixRank[1] = null;
        suffixRank = null;
        ranks = null;
    }
}