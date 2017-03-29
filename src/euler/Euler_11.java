package euler;

import java.util.Scanner;

public class Euler_11 {

    private static final int N = 20;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] g = new int[N][N];
        for(int i=0; i < N; i++){
            for(int j=0; j < N; j++){
                g[i][j] = scanner.nextInt();
            }
        }
        int maxMult = 0;
        int mult = 0;
        //horiz
        for(int i=0; i < N; i++) {
            for (int j = 3; j < N; j++) {
                mult = g[i][j]*g[i][j-1]*g[i][j-2]*g[i][j-3];
                if(maxMult < mult) maxMult = mult;
            }
        }
        //vert
        for(int i=3; i < N; i++) {
            for (int j = 0; j < N; j++) {
                mult = g[i][j]*g[i-1][j]*g[i-2][j]*g[i-3][j];
                if(maxMult < mult) maxMult = mult;
            }
        }
        //diag \
        for(int i=3; i < N; i++) {
            for (int j = 3; j < N; j++) {
                mult = g[i][j]*g[i-1][j-1]*g[i-2][j-2]*g[i-3][j-3];
                if(maxMult < mult) maxMult = mult;
            }
        }
        //diag /
        for(int i=3; i < N; i++) {
            for (int j = 0; j < N-3; j++) {
                mult = g[i][j]*g[i-1][j+1]*g[i-2][j+2]*g[i-3][j+3];
                if(maxMult < mult) maxMult = mult;
            }
        }
        System.out.println(maxMult);
    }
}
