public class Range {
    private int k, sorted[];

    public Range(int A[], int k){
        sorted = new int[k+1];
        for (int i = 0; i < A.length; i++) {
            sorted[A[i]] += 1;
        }

        for (int i = 1; i <= k; i++) {
            sorted[i] += sorted[i-1];
        }
        this.k = k;
    }

    public int query(int a, int b){
        if(b > k || a < 0 || a > b){
            System.out.println("a,b must be in the range 0<=a<=b<=k");
            return -1;
        }
        if(a > 0){
            return sorted[b] - sorted[a-1];
        }
        return sorted[b];
    }
}
