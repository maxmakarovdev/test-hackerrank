package euler;

import java.math.BigInteger;
import java.util.Scanner;

public class Euler_15 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            BigInteger result = fact(intToBig(n + m)).divide(fact(intToBig(m))).divide(fact(intToBig(n))).mod((BigInteger.valueOf(1000000007)));
            System.out.println(result.longValue());
        }
    }

    private static BigInteger fact(BigInteger n) {
        BigInteger result = BigInteger.ONE;

        while (!n.equals(BigInteger.ZERO)) {
            result = result.multiply(n);
            n = n.subtract(BigInteger.ONE);
        }

        return result;
    }

    private static BigInteger intToBig(int n) {
        return BigInteger.valueOf((long) n);
    }
}
