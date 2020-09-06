import java.util.Arrays;

public class Ex1 {
    private static int FindMax(int[] arr, int l, int r){
        if(l >= arr.length)
            return -1;
        int max = l;
        for (int i = l + 1; i < r; i++) {
            if(arr[i] > arr[max]){
                max = i;
            }
        }
        return max;
    }


    // Question 2:
    public static void squarerootSort(int[] A1){
        // i use 2 arrays, one for indexing, so i know what index in A1, is the value in A2,
        // and that saves me one more FindMax call, on the other hand it takes lil bit more memory.

        double root = Math.sqrt(A1.length);
        int items = (int)(root + 0.5f);
        int a2Size = (int)(Math.ceil(root));
        int[] A2toA1 = new int[a2Size]; // here ill save what index is the max value in A1
        int[] A2 = new int[a2Size];
        for (int i = 0; i < A2.length; i++) {
            // get the upperbound of A2[i] group
            int max = i + 1 < A2.length ? (i + 1)* items : A1.length;

            int cm = FindMax(A1, i*(items), max); // get the max INDEX
            A2toA1[i] = cm; //  save the max INDEX
            A2[i] = A1[cm]; // save the max VALUE in A2
        }

        int[] sorted = new int[A1.length]; // create the final sorted array
        for (int i = 0; i < sorted.length; i++) {
            System.out.println("n :" + i);
            //print A2, A1
            System.out.println("A2 : " + Arrays.toString(A2));
            System.out.println("A1 : " + Arrays.toString(A1));

            // find the next biggest and save in sorted[i]
            // also print A1, A2 before change
            if(i + 1 == sorted.length){
                // if its the last INDEX we dont need to update the arrays.
                sorted[i] = resort(A1, A2, A2toA1, items, false);
                continue;
            }
            sorted[i] = resort(A1, A2, A2toA1, items, true);
        }

        System.out.println("Sorted array : ");
        for (int i = 0; i < sorted.length; i++) {
            System.out.print(sorted[i] + " ");
        }
        System.out.println();
    }

    private static int resort(int[] A1, int[] A2, int[] A2toA1, int items, boolean sort){
        // Find the maximum of A2, and replace the VALUE with -1
        int maxA2 = FindMax(A2, 0, A2.length);
        int maxA1 = A2toA1[maxA2];
        int oldMaxA1 = A1[maxA1];

        if(sort) {
            A1[maxA1] = -1;
            int upper = maxA2 + 1 < A2.length ? (maxA2 + 1) * items : A1.length;
            A2toA1[maxA2] = FindMax(A1, maxA2 * items, upper);
            A2[maxA2] = A1[A2toA1[maxA2]];
        }

        // return the maximum at each point. (before change)
        return oldMaxA1;
    }

    // Question 3:
    static final  int INDEX = 0;
    static final int VALUE = 1;

    public static void thirdrootSort(int[] A1){
        double acc = Math.pow( A1.length, (1.0 /3.0));
        int itemsA2 = (int)(acc + 0.5f);
        //int numA3 = (int)(acc + 0.5f);
        //int numA2 = (int)(acc * acc);

        int numA2 = (int)(Math.ceil(A1.length * 1f / itemsA2));
        int numA3 = (int)(Math.ceil(numA2 * 1f / itemsA2));

        int A2[][] = new int[2][numA2]; // A2[0] -> indecies , A2[1] -> values
        int A3[][] = new int[2][numA3]; // A3[0] -> indecies , A3[1] -> values

        for (int i = 0; i < A2[INDEX].length; i++) { // o( sqrt3(N)^2 * (sqrt3(N) - 1)))
            int upper = (i + 1) < numA2 ? (i + 1)*itemsA2 : A1.length;
            A2[INDEX][i] = FindMax(A1, i * itemsA2, upper); // o( sqrt3(N))
            A2[VALUE][i] = A1[A2[INDEX][i]];
        }
        for (int i = 0; i < A3[INDEX].length; i++) { // o (sqrt3(N) * (sqrt3(N)-1)
            int upper = (i + 1) < numA3 ? (i + 1)*itemsA2 : numA2;
            A3[INDEX][i] = FindMax(A2[VALUE], i * itemsA2, upper); // o (sqrt3(N)-1)
            A3[VALUE][i] = A2[VALUE][A3[INDEX][i]];
        }

        // all in all n-1 iterations


        int sortedArr[] = new int[A1.length];
        for (int i = 0; i < A1.length; i++) {
            System.out.println("n : " + i);
            System.out.println("A3 : " + Arrays.toString(A3[1]));
            System.out.println("A2 : " + Arrays.toString(A2[1]));
            System.out.println("A1 : " + Arrays.toString(A1));
            if(i + 1 < A1.length) {
                sortedArr[i] = getMaxTRS(A1, A2, A3, itemsA2, true);
                continue;
            }
            int max = getMaxTRS(A1, A2, A3, itemsA2, false);
            System.out.println("max( " + i + " ) : " + max);
            sortedArr[i] = max;
        }

        System.out.println("Sorted array : ");
        for (int i = 0; i < sortedArr.length; i++) {
            System.out.print(sortedArr[i] + " ");
        }
        System.out.println();
    }

    private static int getMaxTRS(int[] A1, int[][] A2, int[][] A3, int itemsA2, boolean update){
        int maxA3I = FindMax(A3[VALUE], 0, A3[INDEX].length); // INDEX of max in A3  // sqrt 3 (n) - 1
        int maxA2I = A3[INDEX][maxA3I]; // INDEX of max in A2
        int maxOld = A3[VALUE][maxA3I]; // old Max

        if(update) {
            A1[A2[INDEX][maxA2I]] = -1;

            int upper = (maxA2I + 1) < A2[INDEX].length ? (maxA2I + 1) * itemsA2 : A1.length;
            A2[INDEX][maxA2I] = FindMax(A1, maxA2I * itemsA2, upper); // sqrt 3 (n) - 1
            A2[VALUE][maxA2I] = A1[A2[INDEX][maxA2I]];

            upper = (maxA3I + 1) < A3[INDEX].length ? (maxA3I + 1) * itemsA2 : A2[INDEX].length;
            A3[INDEX][maxA3I] = FindMax(A2[VALUE], maxA3I * itemsA2, upper); // sqrt 3 (n) - 1
            A3[VALUE][maxA3I] = A2[VALUE][A3[INDEX][maxA3I]];
        }
        return maxOld;
    }
}
