import java.util.Arrays;

public class ExTest2 {
    public static int Binary2Dec(String S) {
        int num = 0 , x = 0 , y = 0 ;
        for (int i = 0; i < S.length(); i++) {
            String temp = S.charAt(i) +"";// המרת כל איבר מהמחרוזת למספר כדי לכפול ב2 בחזקה המתאימה
            x = (int)(Math.pow(2, S.length()-1-i)); //2 בחזקה המתאימה
            y = Integer.parseInt(temp); // המרת הchar במחרוזת למספר
            num = num + y*x;

        }
        return num ;
    }

    //question 4:
    public static void Psort(Point[] p) {
        Point zero = new Point();
        for (int i = 0; i < p.length; i++) {
            double currentDistance = p[i].distance(zero);
            int swap = i;

            for (int j = i+1; j < p.length; j++) {
                double distance = p[j].distance(zero);
                if(distance < currentDistance){
                    swap = j;
                    currentDistance = distance;
                }
            }
            if(swap > i){
                Point temp = p[i];
                p[i] = p[swap];
                p[swap] = temp;
            }
        }
    }

    public static int[] interlace(int[] a, int[] b) {
        int Asize = a.length;
        int Bsize = b.length;
        if (Asize==0){
            return b;
        }
        if (Bsize==0){
            return a;
        }
        int[] result = new int[Asize + Bsize];
        if (result.length==0){
            return result;
        }
        if (Asize < Bsize) {
            int j = 0;
            for (int i = 0; i < a.length; i++) {
                result[j] = b[i];
                j++;
                result[j] = a[i];
                j++;
            }
            while (j < result.length) {
                result[j] = b[Asize];
                j++;
                Asize++;
            }
        } else {
            int j = 0;
            for (int i = 0; i < b.length; i++) {
                result[j] = a[i];
                j++;
                result[j] = b[i];
                j++;
            }
            while (j < result.length) {
                result[j] = a[Bsize];
                j++;
                Bsize++;
            }
        }
        return result;
    }


    public static void main(String[] args) {

        //question 4
        Point p1 = new Point();
        Point p2 = new Point(1,200);
        Point p3 = new Point(1,54);
        Point p4 = new Point(7.5,800);
        Point[] p = {p1,p2,p3,p4};


        Psort(p);

        for (int i = 0; i < p.length; i++) {
            System.out.println(p[i].toString());
        }
    }
}
