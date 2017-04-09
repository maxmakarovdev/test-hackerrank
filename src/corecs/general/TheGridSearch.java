package corecs.general;

import java.util.*;

public class TheGridSearch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            int N = scanner.nextInt();
            int M = scanner.nextInt();
            char[][] matr = new char[N][M];
            for (int a1 = 0; a1 < N; a1++) {
                matr[a1] = scanner.next().toCharArray();
            }
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            char[][] pattern = new char[n][m];
            for (int a2 = 0; a2 < n; a2++) {
                pattern[a2] = scanner.next().toCharArray();
            }

            System.out.println(findPattern(matr, pattern, N, M, n, m) ? "YES" : "NO");
        }
    }

    public static boolean findPattern(char[][] matrix, char[][] pattern, int N, int M, int n, int m) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (matrix[i][j] == pattern[0][0]) {
                    int flag = 1;
                    if (M - j >= m)
                        for (int pr = 0; pr < n; pr++) {
                            if (flag == 0)
                                break;
                            for (int pc = 0; pc < m; pc++) {

                                if (matrix[i + pr][j + pc] == pattern[pr][pc]) {
                                    if (pr == n - 1 && pc == m - 1)
                                        return true;
                                    flag = 1;
                                } else {
                                    flag = 0;
                                    break;
                                }
                            }
                        }
                }
            }
        }
        return false;
    }
}
