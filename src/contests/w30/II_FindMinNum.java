package contests.w30;

import java.util.Scanner;

public class II_FindMinNum {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String result = "min(int, int)";
        String prefix = "min(int, ";
        String postfix = ")";
        for (int i = 0; i < n - 2; i++) {
            result = prefix + result + postfix;
        }
        System.out.println(result);
    }
}
