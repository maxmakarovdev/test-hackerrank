package contests.w31;

import java.util.Scanner;

public class III_ZeroOneGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            int n = scanner.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }
            int movesCounter = 0;
            int zerosCounter = 0;

            if (a[0] == 0) zerosCounter++;

            for (int i = 1; i < n - 1; i++) {
                if (a[i] == 0) {
                    ++zerosCounter;
                } else {
                    if (a[i - 1] == 0 && a[i + 1] == 0) {
                        ++movesCounter; //remove 1 from 010
                    } else {
                        if (zerosCounter > 2) {
                            movesCounter += (zerosCounter - 2); //remove 0 from 000...00
                        }
                        zerosCounter = 0;
                    }
                }
            }

            if (a[n - 1] == 0) zerosCounter++;
            if (zerosCounter > 2) {
                movesCounter += (zerosCounter - 2); //remove 0 from 000...00
            }

            System.out.println(movesCounter % 2 == 0 ? "Bob" : "Alice");
        }
    }
}