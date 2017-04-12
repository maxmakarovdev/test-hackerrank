package contests.w31;

import java.util.Scanner;

public class I_BeautifulWord {
    public static char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] word = scanner.next().toCharArray();

        for (int i = 1; i < word.length; i++) {
            if (word[i - 1] == word[i] || (containsIn(word[i - 1], vowels) && containsIn(word[i], vowels))) {
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }

    private static boolean containsIn(char ch, char[] arr) {
        for (char a : arr) {
            if (a == ch) return true;
        }
        return false;
    }
}
