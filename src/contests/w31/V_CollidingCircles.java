package contests.w31;

import java.util.*;

public class V_CollidingCircles {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int t = in.nextInt();
        int[] r = new int[n];
        int[] r2 = new int[n];
        ArrayList<Integer> rList = new ArrayList<>();
        int sumR = 0;
        int sumR2 = 0;
        for (int i = 0; i < n; i++) {
            r[i] = in.nextInt();
            rList.add(r[i]);
            sumR += r[i];
            r2[i] = r[i] * r[i];
            sumR2 += r2[i];
        }
        if (t == 0) {
            System.out.println(sumR2 * Math.PI);
            return;
        }
        if (t == n - 1) {
            System.out.println(sumR * sumR * Math.PI);
            return;
        }

        double result = 0;
        List<List<Set<Integer>>> subsets = getSubsets(rList, t + 1);
        System.out.println(subsets);
        for (List<Set<Integer>> indexes : subsets) {
            long sumI = 0;
            long sumNotI = 0;
            //System.out.println(indexes);
            for (int i = 0; i < n; i++) {
                if (indexes.contains(i)) {
                    //System.out.println("contains i :" + i);
                    sumI += r[i];
                } else {
                    //System.out.println("not contains i :" + i);
                    sumNotI += r2[i];
                }
            }
            result += sumI * sumI + sumNotI;
            //System.out.println("sumI:"+sumI + ", sumNotI:"+sumNotI + ", result:"+result);
        }
        //System.out.println("sum: " + result);
        result = result / subsets.size();
        //System.out.println("sum/bin: " + result);

        System.out.println(result * Math.PI);


    }

    private static int binomialCnk(int n, int k) {
        double res = 1;
        for (int i = 1; i <= k; ++i)
            res = res * (n - k + i) / i;
        return (int) Math.round(res);
    }

    private static void getSubsets(List<Integer> superSet, int k, int idx, List<Set<Integer>> current, List<List<Set<Integer>>> solution) {
        //successful stop clause
        if (current.size() == k) {
            solution.add(current);
            return;
        }
        //unseccessful stop clause
        if (idx == superSet.size()) return;
        Integer x = superSet.get(idx);
        for (Set<Integer> set : current) {
            set.add(idx);
            getSubsets(superSet, k, idx + 1, current, solution);
            set.remove(idx);
        }
        HashSet hashSet = new HashSet();
        current.add(hashSet);
        //"guess" x is in the subset
        getSubsets(superSet, k, idx + 1, current, solution);
        current.remove(hashSet);
        //"guess" x is not in the subset
        getSubsets(superSet, k, idx + 1, current, solution);
    }

    private static List<List<Set<Integer>>> getSubsets(List<Integer> superSet, int k) {
        List<List<Set<Integer>>> res = new ArrayList<>();
        getSubsets(superSet, k, 0, new ArrayList<>(), res);
        return res;
    }
}
