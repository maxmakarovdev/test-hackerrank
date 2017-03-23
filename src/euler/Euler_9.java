package euler;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Euler_9 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            int n = in.nextInt();
            System.out.println(calcSumPifagorThree(n));
        }
    }

    public static int calcSumPifagorThree(int num) {
        int max = 0;
        for (int m = 1; m < num / 2; m++) {
            for (int n = 1; n <= m; n++) {
                if ((m * m + m * n) == num) {
                    max = ((m * m + n * n) * (m * m - n * n) * m * n) / 4;
                }
            }
        }
        return max == 0 ? -1 : max;
    }
}
