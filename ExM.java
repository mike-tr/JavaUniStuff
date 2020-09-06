import java.util.*;

public class ExM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter a number : ");
        int number = scanner.nextInt();
        String[][] names = new String[number][2];
        for (int i = 0; i < number; i++) {
            System.out.println("enter first and last name " + i);
            names[i][0] = scanner.next();
            names[i][1] = scanner.next();
        }

        if(number > 0) {
            int current = -1;
            int max = -1;
            for (int i = 0; i < number; i++) {
                int val = calcNameValue(names[i][0] + names[i][1]);
                if(val > max){
                    max = val;
                    current = i;
                }
                System.out.println(names[i][0] + " " + names[i][1]);
            }
            System.out.println(names[current][0] + " " +  names[current][1] + " has the max numeorology value");
        }
    }

    public static int sumToOneDigit(int num){
        int sum = 0;
        while (num > 0){
            sum += num % 10;
            num /= 10;
        }
        if(sum > 9){
            return  sumToOneDigit(sum);
        }
        return sum;
    }

    public static int convertLetterToDigit(char c){
        return sumToOneDigit(Character.getNumericValue(c));
    }

    public static int calcNameValue(String str){
        int val = 0;
        for (int i = 0; i < str.length(); i++) {
            int v = convertLetterToDigit(str.charAt(i));
            val += v;
        }
        return sumToOneDigit(val);
    }
}
