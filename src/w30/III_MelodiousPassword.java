package w30;

import java.util.*;

public class III_MelodiousPassword {

    public static char[] vowels = {'a', 'e', 'i', 'o', 'u'};
    public static char[] consonants = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z'};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        long curTime = System.currentTimeMillis();

        generate(n, true);
        generate(n, false);

        System.out.println((System.currentTimeMillis() - curTime) + " ms");
    }

    private static void generate(int n, boolean firstVowel) {
        int[] helper = new int[n]; //[0,0] -> [0,1] -> [1,0] -> [1,1] for 2x2
        boolean stop = false;
        while (!stop) {
            StringBuilder s = new StringBuilder(); //build string
            for (int i = 0; i < n; i++) {
                if (firstVowel) s.append((i % 2 == 0) ? vowels[helper[i]] : consonants[helper[i]]);
                if (!firstVowel) s.append((i % 2 == 0) ? consonants[helper[i]] : vowels[helper[i]]);
            }
            //System.out.println(s);

            helper[helper.length - 1]++;
            stop = normalizeArray(helper, firstVowel);
        }
    }

    private static boolean normalizeArray(int[] arr, boolean firstVowel) { //true means stop (overflow)
        if (arr[0] >= (firstVowel ? vowels.length : consonants.length)) return true;
        for (int i = 1; i < arr.length; i++) {
            int cellLimit = 0;
            if (firstVowel) cellLimit = i % 2 == 0 ? vowels.length : consonants.length;
            if (!firstVowel) cellLimit = i % 2 == 0 ? consonants.length : vowels.length;

            if (arr[i] >= cellLimit) {
                arr[i] = 0;
                arr[i - 1]++;
                return normalizeArray(arr, firstVowel);
            }
        }
        return false;
    }
}
