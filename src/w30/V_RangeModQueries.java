package w30;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class V_RangeModQueries {

    private static int PRECALC_SIZE = 1000;
    private static int[][] preCalcMods; //number_index, mods (x : y)

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        int[] a = new int[n];
        int[] mods;
        preCalcMods = new int[n][PRECALC_SIZE];
        for (int a_i = 0; a_i < n; a_i++) {
            a[a_i] = scanner.nextInt();
            mods = new int[PRECALC_SIZE];
            for (int j = 1; j < PRECALC_SIZE; j++) {
                if ((j & (j - 1)) != 0) {
                    mods[j] = a[a_i] % j;
                } else {
                    mods[j] = (a[a_i] & (j - 1));
                }
            }
            preCalcMods[a_i] = mods;
        }
        //long curTime = System.currentTimeMillis();
        int left, right, x, y, i, count;
        OutputStream out = new BufferedOutputStream(System.out);
        for (int a0 = 0; a0 < q; a0++) {
            left = scanner.nextInt();
            right = scanner.nextInt();
            x = scanner.nextInt();
            y = scanner.nextInt();

            count = 0;
            if (x < PRECALC_SIZE) {
                for (i = left; i <= right; i++) {
                    if (preCalcMods[i][x] == y) {
                        count++;
                    }
                }
            } else {
                for (i = left; i <= right; i++) {
                    if (((x & (x - 1)) == 0) && ((a[i] & (x - 1)) == y)) { //x - power of two
                        count++;
                    } else if (a[i] % x == y) {
                        count++;
                    }
                }
            }
            out.write((count + "\n").getBytes());
            //System.out.println(count);
        }
        out.flush();
        //System.out.println((System.currentTimeMillis() - curTime) + " ms");
    }
}