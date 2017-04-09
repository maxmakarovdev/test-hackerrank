package contests.euler;

import java.math.BigInteger;
import java.util.Scanner;

public class Euler_20 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scan.nextInt();
            System.out.println(countSumDigits(fact(intToBigInt(n))));
        }
    }

    private static int countSumDigits(BigInteger number) {
        String digits = number.toString();
        int sum = 0;
        for (int i = 0; i < digits.length(); i++) {
            sum += digits.charAt(i) - '0';
        }
        return sum;
    }

    private static BigInteger fact(BigInteger n) {
        BigInteger result = BigInteger.ONE;

        while (!n.equals(BigInteger.ZERO)) {
            result = result.multiply(n);
            n = n.subtract(BigInteger.ONE);
        }

        return result;
    }

    private static BigInteger intToBigInt(int n) {
        return BigInteger.valueOf((long) n);
    }
}
