package w30;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Scanner;

public class VII_SubstringQueries_DP {

    public static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        String[] s = new String[n];
        for (int s_i = 0; s_i < n; s_i++) {
            s[s_i] = in.next();
        }

        int x;
        int y;
        Integer i;
        int result;
        OutputStream out = new BufferedOutputStream(System.out);

        for (int a0 = 0; a0 < q; a0++) {
            x = in.nextInt();
            y = in.nextInt();

            i = map.get(x * 100000 + y);
            if (i == null) {
                result = longestSubstr(s[x], s[y]);
                map.put(x * 100000 + y, result);
                out.write((result + "\n").getBytes());
            } else {
                out.write((i + "\n").getBytes());
            }
        }
        out.flush();
    }

    private static int longestCS(String a, String b) {
        if (a.length() == 0 || b.length() == 0) {
            return 0;
        }
        if (a.equals(b)) {
            return a.length();
        }

        int[][] matrix = new int[a.length()][b.length()];

        int maxLength = 0;
        //int maxI = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    if (i != 0 && j != 0) {
                        matrix[i][j] = matrix[i - 1][j - 1] + 1;
                    } else {
                        matrix[i][j] = 1;
                    }
                    if (matrix[i][j] > maxLength) {
                        maxLength = matrix[i][j];
                        //maxI = i;
                    }
                }
            }
        }
        return maxLength;
    }

    public static int longestSubstr(String first, String second) {
        int maxLen = 0;
        int fl = first.length();
        int sl = second.length();
        int[][] table = new int[fl + 1][sl + 1];

        for (int i = 1; i <= fl; i++) {
            for (int j = 1; j <= sl; j++) {
                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                    if (table[i][j] > maxLen)
                        maxLen = table[i][j];
                }
            }
        }
        return maxLen;
    }
}