package ai;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class StackExchange {

    static String[] topics = {"gis", "security", "photo", "mathematica", "unix", "wordpress", "scifi", "electronics", "android", "apple"};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        String data = "";
        int count = 0;
        while(count < n) {
            String s = in.next();
            data += s;
            count += s.length() - s.replace("}", "").length();
        }

        int point = 0;
        Random r = new Random();
        while(data.indexOf("{", point) != -1) {
            String json = data.substring(data.indexOf("{", point), data.indexOf("}", point));
            point = data.indexOf("}", point) + 1;
            json = json.toLowerCase();
            if(!containsAny(json, "gis", new String[]{" gis ", "qgis", " map ", "arcgis", "polygon", "postgis", "geoserver", "arcmap", " maps ", "wms", "area", "coordinate", "grid", "distance", "geometr", "geo", "gps", "grass"})

                    && !containsAny(json, "security", new String[]{"secur", "server", "password", "secur", "network", "encrypt", "information", "decrypt", "certificate", "authenticat", "attack", "http", "database", "vulnerab", "browser"})

                    && !containsAny(json, "photo", new String[]{"photo", "lens", "camera", "canon", "nikon", "image", "lightroom", "aperture", "focus", "shutter", "shoot", "exposure", "macro", "50mm", "portrait", "jpg", "jpeg", "png"})

                    && !containsAny(json, "mathematica", new String[]{"function", "plot", " x ", " y ", " z ", "matrix", "equation", "solve", "graph", "variable", "value", "expression", "solution", "integral", "differential", "math"})

                    && !containsAny(json, "unix", new String[]{"unix", "linux", "script", "bash", "command", "ubuntu", "arch ", "debian", "gentoo", "bsd", "ssh", "kernel", "shell", "package", "partition", "fedora"})

                    && !containsAny(json, "wordpress", new String[]{"wordpress", "post", "page", "php", "plugin", "category", " wp ", "template", "taxonomy", "form"})

                    && !containsAny(json, "scifi", new String[]{"scifi", "movie", "book", "film", "harry", "episode", "universe", "space", "alien", "season", "planet", "human", "potter", "force", "death", "magic", "dead", "voldemort", "fantas", "comic"})

                    && !containsAny(json, "electronics", new String[]{"electron", "power", "circuit", "voltage", "arduino", "output", " dc ", "battery", "signal", "input", "microcontroller", "motor", "sensor", "5v"})

                    && !containsAny(json, "android", new String[]{"droid", "galaxy", "samsung", " lg ", "google", "nexus", "tablet", "root", "jelly", "lollipop", "adb", "gmail", "cyanogenmod", "xperia", "external", "motorola", "kernel"})

                    && !containsAny(json, "apple", new String[]{"apple", "iphone", "ipad", "macbook", " mac ", "itunes", "lion", "mountain", "ios", " imac ", "osx", "ipod", "mini", "icloud", "safari"})
                    ){
                System.out.println(topics[r.nextInt(10)]);
            }
        }
    }

    private static boolean containsAny(String json, String type, String[] subs){
        for (String sub : subs) {
            if (json.contains(sub)) {
                System.out.println(json + " contains "+sub);
                System.out.println(type);
                return true;
            }
        }
        System.out.println(json + " not contains any from"+type);
        return false;
    }
}