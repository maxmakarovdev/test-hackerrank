package euler;

import java.util.Scanner;

public class Euler_17 {

    static String zero = "Zero";

    static String[] units = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    static String[] tens = {"", "", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};

    static String hundred = " Hundred ";

    static String[] big = {"", " Thousand ", " Million ", " Billion ", " Trillion "};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        for (int i = 0; i < t; i++) {
            long n = scanner.nextLong();
            System.out.println(numToWords(n));
        }
    }

    public static String numToWords(long n) {
        if (n == 0) return zero;
        String result = "";
        for (int i = 0; i < big.length; i++) {
            result = parseThousand((int) ((n / (long) Math.pow(1000, i)) % 1000), big[i]) + result;
        }
        return result.trim().replaceAll("\\s+", " ");
    }

    public static String parseThousand(int n, String postfix) { //n % 1000
        String result = "";
        if (n == 0) return result;

        if (n / 100 > 0) result += units[n / 100] + hundred;
        if (n % 100 >= 20) result += tens[n % 100 / 10] + units[n % 10];
        else result += units[n % 100];

        return result + postfix;
    }
}