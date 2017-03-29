package w30;

import java.util.*;

public class IV_Poles {

    public static void main(String[] args) {
        List<Pole> poles = new ArrayList<>();

        //read data
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        for (int a0 = 0; a0 < n; a0++) {
            int a = scanner.nextInt();
            int w = scanner.nextInt();
            poles.add(new Pole(a, w));
        }

        //compress and calc cost - greedy algorithm
        long totalCost = 0;
        List<Integer> deltaW = new ArrayList<>(); //cost moving from i+1 to i: 1..2, 2..3, 3..4, etc
        while (poles.size() > k) {
            deltaW.clear();
            for (int i = 1; i < poles.size(); i++) {
                Pole cur = poles.get(i);
                Pole prev = poles.get(i - 1);
                deltaW.add((cur.a - prev.a) * cur.w);
            }
            int minDeltaW = Collections.min(deltaW);
            int index = deltaW.indexOf(minDeltaW);

            Pole toRemove = poles.get(index + 1);
            Pole sum = poles.get(index);
            totalCost += (toRemove.a - sum.a) * toRemove.w;
            sum.addW(toRemove.w);
            poles.remove(index + 1);
        }

        System.out.println(totalCost);
    }

    static class Pole {
        public int a = 0;
        public int w = 0;

        public Pole(int a, int w) {
            this.a = a;
            this.w = w;
        }

        public void addW(int w) {
            this.w += w;
        }
    }
}