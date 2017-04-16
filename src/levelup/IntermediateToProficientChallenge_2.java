package levelup;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class IntermediateToProficientChallenge_2 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scan.nextLine();
        }
        scan.nextLine();
        String[] b = new String[n];
        for (int i = 0; i < n; i++) {
            b[i] = scan.nextLine();
        }
        String[] result = twins(a, b);
        for (int i = 0; i < n; i++) {
            System.out.println(result[i]);
        }
    }

    static String[] twins(String[] a, String[] b) {
        String[] result = new String[a.length];
        for (int i = 0; i < a.length; i++) {
            if (a[i].length() != b[i].length()) {
                result[i] = "No";
            } else {
                result[i] = isStringsTwins(a[i], b[i]) ? "Yes" : "No";
            }
        }
        return result;
    }

    static boolean isStringsTwins(String a, String b) {
        Pair[] countTable = new Pair[26]; //alphabet, x to z, with even and odd includes in string x
        for (int i = 0; i < 26; i++) {
            countTable[i] = new Pair();
        }

        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        for (int i = 0; i < a.length(); i += 2) {
            countTable[aChars[i] - 97].even++;
            countTable[bChars[i] - 97].even--;
        }
        for (int i = 1; i < a.length(); i += 2) { //optimization instead of expensive if(i % 2)
            countTable[aChars[i] - 97].odd++;
            countTable[bChars[i] - 97].odd--;
        }

        for (int i = 0; i < 26; i++) {
            if (countTable[i].even != 0 || countTable[i].odd != 0) return false;
        }
        return true;
    }

    static class Pair {
        public int even = 0;
        public int odd = 0;
    }
}
