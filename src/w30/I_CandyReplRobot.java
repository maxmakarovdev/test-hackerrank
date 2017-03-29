package w30;

import java.util.Scanner;

public class I_CandyReplRobot {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int t = in.nextInt();
        int[] c = new int[t];
        int curCandies = n;
        int count = 0;
        for (int c_i = 0; c_i < t; c_i++) {
            c[c_i] = in.nextInt();
            curCandies -= c[c_i];
            if (c_i < t - 1 && curCandies < 5) {
                count += (n - curCandies);
                curCandies = n;
            }
        }
        System.out.println(count);
    }
}
