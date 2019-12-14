package lab_1.task_1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Please enter rows...");
        int rows = scn.nextInt();
        System.out.println("Please enter cols...");
        int cols = scn.nextInt();
        int[][] arr = getMatrix(rows, cols);
        String str = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                str += arr[i][j] + " ";
            }
            System.out.println(str);
            str = "";
        }
    }

    public static int[][] getMatrix(int rows, int cols) {
        int[][] arr = null;
        try {
            if ((rows <= 0) || (cols <= 0))
                throw new IllegalArgumentException("'rows' and 'cols' values must be more than 0");
            arr = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                arr[i][0] = 1;
                for (int j = 1; j < cols; j++) {
                    arr[i][j] = 0;
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return arr;
    }
}
