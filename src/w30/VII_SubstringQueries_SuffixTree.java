package w30;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class VII_SubstringQueries_SuffixTree {

    public static HashMap<Integer, Integer> resultsMap = new HashMap<>();
    public static HashMap<char[], Suffix[]> suffixTrees = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        char[][] s = new char[n][];
        char[] str;
        int len;
        for (int s_i = 0; s_i < n; s_i++) {
            str = in.next().toCharArray();
            s[s_i] = str;

            len = str.length;
            Suffix[] suffixes = new Suffix[len];
            for (int i = 0; i < len; i++) suffixes[i] = new Suffix(str, i);
            Arrays.sort(suffixes);

            suffixTrees.put(str, suffixes);
        }

        int x, y;
        Integer result;
        OutputStream out = new BufferedOutputStream(System.out);

        for (int a0 = 0; a0 < q; a0++) {
            x = in.nextInt();
            y = in.nextInt();

            result = resultsMap.get(x * 100000 + y);
            if (result == null) {
                result = lcs(s[x], s[y]);
                resultsMap.put(x * 100000 + y, result);
            }
            out.write((result.toString().concat("\n")).getBytes());
        }
        out.flush();
    }

    private static int lcp(char[] s, int p, char[] t, int q) {
        int n = Math.min(s.length - p, t.length - q);
        for (int i = 0; i < n; i++) {
            if (s[p + i] != t[q + i])
                return i;
        }
        return n;
    }

    // compare suffix s[p..] and suffix t[q..]
    private static int compare(char[] s, int p, char[] t, int q) {
        int n = Math.min(s.length - p, t.length - q);
        for (int i = 0; i < n; i++) {
            if (s[p + i] != t[q + i])
                return s[p + i] - t[q + i];
        }
        if (s.length - p < t.length - q) return -1;
            //else if (s.length - p > t.length - q) return +1; //optimization
        else return 0;
    }

    public static Integer lcs(char[] s, char[] t) {
        Suffix[] suffix1 = suffixTrees.get(s);
        Suffix[] suffix2 = suffixTrees.get(t);

        // find longest common substring by "merging" sorted suffixes 
        int lcs = 0;
        int i = 0, j = 0;
        int p, q, x;
        while (i < s.length && j < t.length) {
            p = suffix1[i].index;
            q = suffix2[j].index;
            x = lcp(s, p, t, q);
            if (x > lcs) lcs = x;
            if (compare(s, p, t, q) < 0) i++;
            else j++;
        }
        return lcs;
    }

    static class Suffix implements Comparable<Suffix> {
        public final char[] text;
        public final int index;

        private Suffix(char[] text, int index) {
            this.text = text;
            this.index = index;
        }

        public int compareTo(Suffix that) {
            if (this == that) return 0;
            int n = Math.min(text.length - index, that.text.length - that.index);
            for (int i = 0; i < n; i++) {
                if (text[index + i] < that.text[that.index + i]) return -1;
                if (text[index + i] > that.text[that.index + i]) return +1;
            }
            return text.length - index - (that.text.length - that.index);
        }
    }
}