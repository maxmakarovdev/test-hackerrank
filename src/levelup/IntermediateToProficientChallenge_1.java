package levelup;

import java.util.Scanner;

public class IntermediateToProficientChallenge_1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scan.nextInt();
        }
        int m = scan.nextInt();
        int[] rotate = new int[m];
        for (int i = 0; i < m; i++) {
            rotate[i] = scan.nextInt();
        }
        int[] result = getMaxElementIndices(a, rotate);
        for (int i = 0; i < m; i++) {
            System.out.println(result[i]);
        }
    }

    static int[] getMaxElementIndices(int[] a, int[] rotate) {
        int[] result = new int[rotate.length];
        int maxIndex = findMaxIndex(a);
        for (int i = 0; i < rotate.length; i++) {
            result[i] = maxIndex - rotate[i] % a.length;
            if (result[i] < 0) {
                result[i] += a.length;
            }
        }
        return result;
    }

    static int findMaxIndex(int[] array) {
        int max = array[0];
        int maxIndex = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
