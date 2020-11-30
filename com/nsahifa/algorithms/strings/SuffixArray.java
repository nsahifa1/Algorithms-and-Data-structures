/**
 * Abstract method for suffix array implementation
 * @author : nabil sahifa
 * @email : nabilsahifa@gmail.com
 */

public abstract class SuffixArray {

    // The length of the string
    protected int N;

    // the converted string
    protected int[] T;

    // The sorted suffix array
    protected int[] SA;

    // the LCP array
    protected int[] LCP;

    private boolean SAConstructed = false;
    private boolean LCPConstructed = false;

    // Constructor with the converted arrat as an argument
    public SuffixArray(int[] T){
        if (T==null) throw new IllegalArgumentException("Text cannot be null");
        this.T = T;
        this.N = T.length;
    }

    public int getLength(){
        return T.length;
    }

    public int[] getSA() {
        buildSuffixArray();
        return SA;
    }

    public int[] getLCP(){
        buildLCPArray();
        return LCP;
    }

    protected static int[] toIntArray(String s){
        if (s == null) return null;
        int[] T = new int[s.length()];
        for (int i=0; i<s.length();++i) T[i] = s.charAt(i);
        return T;
    }

    // Abstract method as there are multiple wats to construct a Suffix array
    // Detailed implementation are included in SuffixArraySlow, SuffixArrayMed and SuffixArrayFast
    protected abstract void construct();

    // Kasai algorithm for building the LCP array
    private void kasaiAlgorithm(){

    }

    // Builds the suffix array
    protected void buildSuffixArray(){
        if (SAConstructed) return;
        construct();
        LCPConstructed = true;
    }

    // builds the LCP array
    protected void buildLCPArray(){
        if (LCPConstructed) return;
        buildSuffixArray();
        kasaiAlgorithm();
        LCPConstructed = true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<N; ++i){
            int suffixLen = N - SA[i];
            char[] suffixArray = new char[suffixLen];

            for (int j=SA[i],k=0; j<N; j++,k++) suffixArray[k] = (char)T[j];

            String suffix = new String(suffixArray);
            String formatted = String.format("% 7d % 7d % 7d %s \n", i, SA[i], LCP[i], suffix);
            sb.append(formatted);
        }
        return sb.toString();
    }
}