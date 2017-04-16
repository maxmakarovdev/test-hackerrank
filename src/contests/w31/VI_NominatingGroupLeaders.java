package contests.w31;

import java.util.*;
import java.io.*;

public class VI_NominatingGroupLeaders {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        StringBuilder result = new StringBuilder();
        int t = io.getInt();
        for (int a0 = 0; a0 < t; a0++) {
            int n = io.getInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = io.getInt();
            }
            int g = io.getInt();
            A:
            for (int a1 = 0; a1 < g; a1++) {
                int l = io.getInt();
                int r = io.getInt();
                int x = io.getInt();
                int[] votes = new int[n];
                for (int i = l; i <= r; i++) {
                    votes[a[i]]++;
                }
                for (int i = 0; i < n; i++) {
                    if (votes[i] == x) {
                        result.append(i).append("\n");
                        continue A;
                    }
                }
                result.append(-1).append("\n");
            }
        }
        io.println(result.toString());
        io.close();
    }

    static class Kattio extends PrintWriter {
        public Kattio(InputStream i) {
            super(new BufferedOutputStream(System.out));
            r = new BufferedReader(new InputStreamReader(i));
        }

        public Kattio(InputStream i, OutputStream o) {
            super(new BufferedOutputStream(o));
            r = new BufferedReader(new InputStreamReader(i));
        }

        public boolean hasMoreTokens() {
            return peekToken() != null;
        }

        public int getInt() {
            return Integer.parseInt(nextToken());
        }

        public double getDouble() {
            return Double.parseDouble(nextToken());
        }

        public long getLong() {
            return Long.parseLong(nextToken());
        }

        public String getWord() {
            return nextToken();
        }


        private BufferedReader r;
        private String line;
        private StringTokenizer st;
        private String token;

        private String peekToken() {
            if (token == null)
                try {
                    while (st == null || !st.hasMoreTokens()) {
                        line = r.readLine();
                        if (line == null) return null;
                        st = new StringTokenizer(line);
                    }
                    token = st.nextToken();
                } catch (IOException e) {
                }
            return token;
        }

        private String nextToken() {
            String ans = peekToken();
            token = null;
            return ans;
        }
    }
}