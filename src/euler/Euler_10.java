package euler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Euler_10 {

    private static final long[] primeSum = {};

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        //int t = scan.nextInt();
        for (int a0 = 0; a0 < 1; a0++) {

            boolean[] isPrime = new boolean[1000001];
            long[] primeSum = new long[1000001];
            long sum = 0;
            Arrays.fill(isPrime, true);
            isPrime[1] = false;
            for (int i = 2; i * i < 1000000; i++)
                if (isPrime[i])
                    for (int j = i * i; j < 1000000; j += i)
                        isPrime[j] = false;

            for (int i = 2; i <= 1000000; i++) {
                if (isPrime[i]) {
                    sum += i;
                }
                primeSum[i] = sum;
            }

            for (int i = 2; i <= 1000000; i++) {
                System.out.print(primeSum[i] + ", ");
            }

            /*
            int n = scan.nextInt();
            boolean[] numbers = new boolean[n+1];
            for (int i = 2;i<=n; i++){
                if(numbers[i]) continue;

                sum +=(long)i;
                int j = i+i;
                while(j<=n){
                    numbers[j]=true;
                    j+=i;
                }
            }

            System.out.println(sum);*/
        }
    }
}