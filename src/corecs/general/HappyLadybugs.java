package corecs.general;

import java.util.Scanner;

public class HappyLadybugs {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            int n = scanner.nextInt();
            char[] b = scanner.next().toCharArray();
            System.out.println(makeLadybugsHappy(n, b) ? "YES" : "NO");
        }
    }

    private static boolean makeLadybugsHappy(int n, char[] b) {
        for (char i = 'A'; i <= 'Z'; i++) {
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (b[j] == i) c++;
            }
            if (c == 1) return false;

        }
        int c = 0;
        for (int i = 0; i < n; i++) {
            if (b[i] == '_') c++;
        }
        if (c == 0) {
            for (int i = 1; i < (n - 1); i++) {
                if (b[i] != b[i + 1] && b[i] != b[i - 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
