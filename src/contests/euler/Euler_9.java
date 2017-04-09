package contests.euler;

import java.util.Scanner;

public class Euler_9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
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
