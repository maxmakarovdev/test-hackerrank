package contests.euler;

import java.util.Calendar;
import java.util.Scanner;

public class Euler_19 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for (int i = 0; i < t; i++) {
            int y1 = scan.nextInt();
            int m1 = scan.nextInt();
            int d1 = scan.nextInt();
            int y2 = scan.nextInt();
            int m2 = scan.nextInt();
            int d2 = scan.nextInt();
            System.out.println(getNumberOfSundays(y1, m1, d1, y2, m2, d2));
        }
    }

    private static int getNumberOfSundays(int y1, int m1, int d1, int y2, int m2, int d2) {

        Calendar cal1 = Calendar.getInstance();
        cal1.set(y1, m1, d1);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(y2, m2, d2);
        int sundays = 0;
        while (!cal2.before(cal1)) {
            if (cal1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && (cal1.get(Calendar.DAY_OF_MONTH) == 1)) {
                sundays++;
            }
            cal1.add(Calendar.DATE, 1);
        }
        return sundays;
    }
}
