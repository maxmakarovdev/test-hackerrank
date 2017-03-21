package ai;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class CleanRobot {

    static void displayPathtoPrincess(Point bot, List<Point> dirty){
        Point nearest = new Point();
        int neasertDist = Integer.MAX_VALUE;
        for(Point p : dirty){
            int dx = bot.x - p.x;
            int dy = bot.y - p.y;
            if(dx == 0 && dy == 0){
                System.out.println("CLEAN");
                return;
            }
            if(Math.abs(dx) + Math.abs(dy) < neasertDist){
                neasertDist = Math.abs(dx) + Math.abs(dy);
                nearest = p;
            }
        }
        int dx = bot.x - nearest.x;
        int dy = bot.y - nearest.y;
        if(Math.abs(dx) > Math.abs(dy)){
            if(dx > 0) System.out.println("LEFT");
            else System.out.println("RIGHT");
        } else {
            if(dy > 0) System.out.println("UP");
            else System.out.println("DOWN");
        }
        /*if(dx > 0) for(int i=0; i<dx; i++) System.out.println("LEFT");
        if(dx < 0) for(int i=0; i<-dx; i++) System.out.println("RIGHT");
        if(dy > 0) for(int i=0; i<dy; i++) System.out.println("UP");
        if(dy < 0) for(int i=0; i<-dy; i++) System.out.println("DOWN");*/
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 5;
        //n = in.nextInt();
        Point bot = new Point();
        bot.y = in.nextInt();
        bot.x = in.nextInt();
        List<Point> dirty = new ArrayList<>();
        String grid[] = new String[n];
        for(int i = 0; i < n; i++) {
            grid[i] = in.next();
            for(int k = 0; k<grid[i].length(); k++){
                if(grid[i].charAt(k) == 'd'){
                    dirty.add(new Point(k, i));
                }
            }
        }

        displayPathtoPrincess(bot, dirty);
    }

    static class Point {

        public int x;
        public int y;

        public Point(){}

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}