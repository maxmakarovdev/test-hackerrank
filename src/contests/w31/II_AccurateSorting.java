package contests.w31;

import java.util.Scanner;

public class II_AccurateSorting {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        A:
        for (int a0 = 0; a0 < t; a0++) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int a_i = 0; a_i < n; a_i++) {
                a[a_i] = in.nextInt();
            }

            for (int i = 0; i < n; i++) {
                if (Math.abs(a[i] - i) > 1 || Math.abs(a[a[i]] - a[i]) > 1) {
                    System.out.println("No");
                    continue A;
                }
            }
            System.out.println("Yes");
        }
    }
}