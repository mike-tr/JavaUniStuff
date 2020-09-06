public class Main {
    public static void main(String[] args) {
        // write your code here
        int arr[] = new int[]{ 1, 1, 2, 3, 4, 4, 5,6,7,8,9,10,4 ,4 ,5,1,2,3 };
        Range range = new Range(arr, 10);
        System.out.println(range.query(4,4));
    }
}
