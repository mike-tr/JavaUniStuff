import java.util.Arrays;

public class ExT {
    public static void main(String[] args) {
        // write your code here
        String path = "E:\\Projects\\Java\\Learning\\EX1\\src\\aCat";
        int[][][] cat = MyImageIO.readImageFromFile(path + ".jpg");
        int [][] var = histogram(cat);

        int[][][] smallTest =
            {{{125, 45, 186, 171, 67, 136, 186, 19, 141, 219},
            {98, 163, 81, 17, 120, 99, 16, 66, 168, 106},
            {118, 224, 65, 66, 181, 25, 103, 121, 173, 217},
            {84, 234, 154, 222, 244, 228, 197, 158, 147, 180},
            {35, 188, 154, 221, 23, 67, 64, 191, 114, 183}},

            {{241, 25, 193, 212, 190, 131, 97, 155, 98, 177},
            {87, 26, 194, 239, 238, 88, 115, 59, 34, 135},
            {183, 244, 116, 193, 189, 17, 112, 81, 63, 157},
            {47, 209, 146, 18, 129, 10, 31, 31, 215, 1},
            {195, 95, 22, 78, 254, 107, 100, 67, 7, 203}},

            {{179, 87, 179, 64, 49, 169, 244, 7, 14, 122},
            {37, 194, 24, 220, 146, 205, 74, 249, 234, 18},
            {78, 220, 226, 124, 237, 158, 36, 111, 249, 181},
            {252, 255, 179, 1, 82, 208, 222, 29, 155, 232},
            {179, 138, 18, 26, 163, 37, 30, 12, 113, 121}}};

        System.out.println(Arrays.toString(var[0]));
        System.out.println(Arrays.toString(var[1]));
        System.out.println(Arrays.toString(var[2]));

        var = histogram(smallTest);
        System.out.println(Arrays.toString(var[0]));
        System.out.println(Arrays.toString(var[1]));
        System.out.println(Arrays.toString(var[2]));
    }

    public static int [][] histogram(int [][][] img){
        int[][] histoArr = new int[3][256];
        if(img.length < 1 && img[0].length < 1 && img[0][0].length < 1)
            return histoArr;
        // populate
        for (int x = 0; x < img[0].length; x++) {
            for (int y = 0; y < img[0][0].length; y++) {
                for (int c = 0; c < img.length; c++) {
                    histoArr[c][img[c][x][y]] += 1;
                }
            }
        }
        //sort
        for (int i = 0; i < 3; i++) {
            for (int index = 0; index < 256; index++) {
                int min = index;
                for (int current = min + 1; current < 256; current++) {
                    if(histoArr[i][current] < histoArr[i][min]){
                        min = current;
                    }
                }
                // swap.
                int temp = histoArr[i][index];
                histoArr[i][index] = histoArr[i][min];
                histoArr[i][min] = temp;
            }
        }
        return histoArr;
    }
}
