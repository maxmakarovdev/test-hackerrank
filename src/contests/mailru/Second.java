package contests.mailru;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Second {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for (int a = 0; a < t; a++) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            int k = scan.nextInt();
            int[][] matr = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    matr[i][j] = scan.nextInt();
                }
            }
            calc(n, m, k, matr);
        }
    }

    private static void calc(int n, int m, int k, int[][] a) {
        Set<Integer> colors = new HashSet<>();
        for (int i = 1; i < n; i++) {
            if (a[i][0] == 0) continue;
            if (a[i - 1][0] > 0) a[i][0] += a[i - 1][0];
        }
        for (int j = 1; j < m; j++) {
            if (a[0][j] == 0) {
                colors.clear();
                continue;
            }
            if (a[0][j - 1] > 0) {
                colors.add(a[0][j - 1]);
                a[0][j] = getNewColorEnd(k, colors);
            }
        }
        printMatrix(n, m, a);

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                colors.clear();
                if (a[i][j] > 0) {
                    int ii = i - 1;
                    int jj = j - 1;
                    while (ii >= 0) {
                        if (a[ii][j] > 0) colors.add(a[ii--][j]);
                        else break;
                    }
                    while (jj >= 0) {
                        if (a[i][jj] > 0) colors.add(a[i][jj--]);
                        else break;
                    }
                    int c = getNewColor(k, colors);
                    if (c > k) {
                        System.out.println("NO");
                        return;
                    } else {
                        a[i][j] = c;
                    }
                }
                //printMatrix(n, m, x);
                //System.out.println();
            }
        }
        System.out.println("YES");
        printMatrix(n, m, a);
    }

    private static void printMatrix(int n, int m, int[][] matr) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int getNewColor(int k, Set<Integer> colors) {
        int c = 1;
        while (c <= k) {
            if (colors.contains(c)) {
                c++;
            } else break;
        }
        //System.out.println(colors.toString()+ " c: "+c);
        return c;
    }

    private static int getNewColorEnd(int k, Set<Integer> colors) {
        int c = k;
        while (c > 0) {
            if (colors.contains(c)) {
                c--;
            } else break;
        }
        //System.out.println(colors.toString()+ " c: "+c);
        return c;
    }

    private static int getNewColorMiddle(int k, Set<Integer> colors) {
        int c = k/2 + 1;
        while (c > 0) {
            if (colors.contains(c)) {
                c--;
            } else break;
        }
        if(c == 0){
            c = k/2 + 1;
            while (c <= k) {
                if (colors.contains(c)) {
                    c++;
                } else break;
            }
        }
        //System.out.println(colors.toString()+ " c: "+c);
        return c;
    }
}
