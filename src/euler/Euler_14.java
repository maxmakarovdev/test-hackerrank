package euler;

import java.util.Scanner;

public class Euler_14 {

    private static final int[] preresult = {4484223, 3974407, 3030267, 1676703, 910107, 511935, 410011, 230631, 216367,
            156159, 142587, 106239, 77031, 52527, 35655, 35497, 34239, 26623, 23529, 17673, 17647, 13255, 10971, 6171,
            3711, 2919, 2463, 2323, 2223, 1161, 871, 703, 667, 655, 649, 327, 313, 235, 231, 171, 129, 97, 73, 55, 27, 25, 19, 9, 7, 3, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();

        for (int i = 0; i < k; i++) {
            int n = scanner.nextInt();
            for (int j = 0; j < preresult.length; j++) {
                if (preresult[j] <= n) {
                    System.out.println(preresult[j]);
                    break;
                }
            }
        }
    }

    private static int countCollatseLen(int n) {
        int count = 1;
        while (n > 1) {
            if ((n & (n - 1)) == 0) {
                count += binlog(n);
                break;
            }
            if ((n & 1) == 0) {
                n /= 2;
            } else {
                n = n * 3 + 1;
            }
            count++;
        }
        return count;
    }

    private static int binlog(int bits) {
        int log = 0;
        if ((bits & 0xffff0000) != 0) {
            bits >>>= 16;
            log = 16;
        }
        if (bits >= 256) {
            bits >>>= 8;
            log += 8;
        }
        if (bits >= 16) {
            bits >>>= 4;
            log += 4;
        }
        if (bits >= 4) {
            bits >>>= 2;
            log += 2;
        }
        return log + (bits >>> 1);
    }
}
