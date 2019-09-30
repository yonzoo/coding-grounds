package Challenge2;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        String[] arr = splitString("bbbcccaaa");
        for (String str : arr) System.out.println(str);
    }

    public static String[] splitString(String str) {
        String[] arr = null;
        try {
            if (str.length() % 3 != 0)
                throw new IllegalArgumentException("String length must be divided by 3 without reminder");
            if (str.length() <= 0)
                throw new IllegalArgumentException("String length must be more than 0");
            arr = str.split("(?<=\\G...)");
            Random r = new Random();
            for (int i = 0; i < arr.length; i++) {
                char randomSymbol = (char) (r.nextInt(26) + 'a');
                while (str.indexOf(randomSymbol) != -1)
                    randomSymbol = (char) (r.nextInt(26) + 'a');
                arr[i] = arr[i].substring(0, 1) + randomSymbol + arr[i].substring(2);
            }
            Arrays.sort(arr);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return arr;
    }
}
