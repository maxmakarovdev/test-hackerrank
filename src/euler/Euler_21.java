package euler;

import java.util.Scanner;

public class Euler_21 {

    private static final int[] amicable = {220, 284, 1184, 1210, 2620, 2924, 5020, 5564, 6232, 6368, 10744, 10856,
            12285, 14595, 17296, 18416, 63020, 66928, 66992, 67095, 69615, 71145, 76084, 79750, 87633, 88730};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();

        for (int i = 0; i < k; i++) {
            int n = scanner.nextInt();
            int sum = 0;
            for (int j = 0; j < amicable.length; j++) {
                if (amicable[j] < n) {
                    sum += amicable[j];
                } else {
                    break;
                }
            }
            System.out.println(sum);
        }
    }
}
