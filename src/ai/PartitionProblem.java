package ai;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class PartitionProblem {

    private static long sum = 0;
    private static int arr[];
    private static int wrr[];

    public static void main(String[] args) {
        //read data
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        arr = new int[n + 1];
        wrr = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            int w = in.nextInt();
            arr[i + 1] = a;
            wrr[i + 1] = w;
        }

        partition(arr, wrr, n, k);
        System.out.println(sum);
    }

    static void partition(int a[], int w[], int n, int k) {
        int m[][] = new int[n + 1][k + 1]; /* DP table for values */
        int d[][] = new int[n + 1][k + 1]; /* DP table for dividers */
        int p[] = new int[n + 1]; /* prefix sums array */
        int cost; /* test split cost */
        int i, j, x; /* counters */
        p[0] = 0; /* construct prefix sums */
        p[1] = 0;
        for (i = 2; i <= n; i++) p[i] = p[i - 1] + (a[i] - a[i - 1]) * w[i];
        for (i = 1; i <= n; i++) m[i][1] = p[i]; /* initialize boundaries */
        for (j = 1; j <= k; j++) m[1][j] = (a[2] - a[1]) * w[2];
        for (i = 2; i <= n; i++) /* evaluate main recurrence */
            for (j = 2; j <= k; j++) {
                m[i][j] = Integer.MAX_VALUE;
                for (x = 1; x <= (i - 1); x++) {
                    cost = Math.max(m[x][j - 1], p[i] - p[x]);
                    if (m[i][j] > cost) {
                        m[i][j] = cost;
                        d[i][j] = x;
                    }
                }
            }
        reconstruct_partition(a, w, d, n, k); /* print book partition */
    }

    static void reconstruct_partition(int a[], int w[], int d[][], int n, int k) {
        if (k == 1)
            print_books(a, w, 1, n);
        else {
            reconstruct_partition(a, w, d, d[n][k], k - 1);
            print_books(a, w, d[n][k] + 1, n);
        }
    }

    static void print_books(int a[], int w[], int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.print(" " + a[i] + " ");
            sum += (a[i] - a[start]) * w[i];
        }
        System.out.println();
    }

    static void print(int matrix[][]) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void print(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}