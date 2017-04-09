package contests.mailru;

import java.util.Scanner;

public class First {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for (int i = 0; i < t; i++) {
            int k = scan.nextInt();
            int x = scan.nextInt();
            int y = scan.nextInt();
            System.out.println(calc(k, x, y));
        }
    }

    private static int calc(int k, int x, int y) {
        int count = 0;
        if (x >= y) {
            if (x - y < 2) {
                count += 2 - (x - y);
                x += 2 - (x - y);
            }
            if (x < k) {
                count += (k - x);
            }
        } else {
            if (y - x < 2) {
                count += 2 - (y - x);
                y += 2 - (y - x);
            }
            if (y < k) {
                count += (k - y);
            }
        }
        return count;
    }
}
