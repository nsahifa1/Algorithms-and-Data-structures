/**
 * Suffix array slow implementation using sorting of suffixes
 * Complexity : O(N^2*LogN)
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */

import java.util.Arrays;

public class SuffixArraySlow extends SuffixArray {

    // Implementation of the Suffix object structure
    private static class Suffix implements Comparable<Suffix>{

        final int idx, len;
        final int[] text;

        public Suffix(int[] text, int idx){
            this.len = text.length - idx;
            this.text = text;
            this.idx = idx;
        }

        // Compare the two suffixes
        public int compareTo(Suffix o) {
            if (this == o) return 0;
            int minLen = Math.min(len, o.len);

            for (int i=0; i<minLen; ++i){
                if (text[idx + i] < o.text[o.idx + i]) return -1;
                if (text[idx + i] > o.text[o.idx + i]) return 1;
            }

            // return 1
            return len - o.len;
        }

        @Override
        public String toString() {
            return new String(text, idx, len);
        }
    }

    // Array that stores the suffixes from the original string
    private Suffix[] suffixes;

    public SuffixArraySlow(String s){
        super(toIntArray(s));
    }

    public SuffixArraySlow(int[] T) {
        super(T);
    }

    // Suffix Array implementation based on the alphabetical sort of the suffixes
    // Each comparison takes O(n) therefore an overall complexity of O(n^2log(n))
    @Override
    protected void construct() {
        SA = new int[N];
        suffixes = new Suffix[N];

        for (int i=0; i<N; ++i)
            suffixes[i] = new Suffix(T, i);

        Arrays.sort(suffixes);

        for (int i=0; i<N; ++i){
            SA[i] = suffixes[i].idx;
            suffixes[i] = null;
        }
        suffixes = null;
    }
}