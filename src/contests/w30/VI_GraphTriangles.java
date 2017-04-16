package contests.w30;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class VI_GraphTriangles {

    private static ArrayList<SubG> subGraphs;
    private static OutputStream out;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = in.nextInt();
            }
        }

        subGraphs = new ArrayList<>(n);
        int visit[][] = new int[n][n];
        int i, j, k;
        //int triangle_Number = 0;//

        //find all triangles
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (g[i][j] == 1 && (visit[i][j] != 1 && visit[j][i] != 1)) {
                    visit[i][j] = 1;
                    for (k = 0; k < n; k++) {
                        if (g[j][k] == 1 && k != i) {
                            if (g[k][i] == 1 && visit[i][k] != 1) {
                                checkAndAddVerticles(i, j, k);
                                //System.out.println("i: " + (i + 1) + " j: " + (j + 1) + " k:" + (k + 1));//
                                //triangle_Number++;
                            }
                        }
                    }
                }
                visit[i][j] = 1;
            }
        }

        out = new BufferedOutputStream(System.out);

        if (subGraphs.size() > 0) {
            collapse();
            findMax();
        } else {
            out.write((n + "\n").getBytes());
            for (int a = 0; a < n; a++) {
                out.write(((a + 1) + " ").getBytes());
            }
        }

        out.flush();
        //System.out.println("Number of triangles: " + triangle_Number);
    }

    private static void checkAndAddVerticles(int a, int b, int c) { //add if 2 v is exist
        boolean isa;
        boolean isb;
        boolean isc;
        SubG subg;
        for (int i = 0; i < subGraphs.size(); i++) { //if someone contains 2 of 3 v
            subg = subGraphs.get(i);
            isa = subg.v.contains(a);
            isb = subg.v.contains(b);
            isc = subg.v.contains(c);
            if (isa && isb) {
                subg.addVerticle(c);
                return;
            } else if (isb && isc) {
                subg.addVerticle(a);
                return;
            } else if (isa && isc) {
                subg.addVerticle(b);
                return;
            }
        }
        //otherwise create new
        subGraphs.add(new SubG(a, b, c));
    }

    private static void collapse() throws IOException {
        int ag = -1;
        int bg = -1;
        double maxScore = -1;

        SubG a;
        SubG b;
        Set<Integer> sum = new HashSet<>();
        double aScore;
        double bScore;
        double sumScore;

        for (int i = 0; i < subGraphs.size(); i++) {
            for (int j = i + 1; j < subGraphs.size(); j++) {
                a = subGraphs.get(i);
                b = subGraphs.get(j);
                sum.clear();
                sum.addAll(a.v);
                sum.addAll(b.v);

                aScore = (double) a.triangles / a.v.size();
                bScore = (double) b.triangles / b.v.size();
                sumScore = (double) (a.triangles + b.triangles) / sum.size();

                //System.out.println("x: "+x.getV() + " b: " + b.getV() + " scoreA: "+ aScore + " scoreB: " + bScore + " sumScore: "+ sumScore);

                if (sum.size() < (a.v.size() + b.v.size()) && sumScore >= aScore && sumScore >= bScore && sumScore >= maxScore) {
                    maxScore = sumScore;
                    ag = i;
                    bg = j;
                }
            }
        }

        if (maxScore >= 0) {
            a = subGraphs.get(ag);
            b = subGraphs.get(bg);

            //System.out.println("collapse with max score:" + maxScore + " : "+x.getV() + " into " + b.getV());
            //System.out.println("atriangles"+x.triangles);
            //System.out.println("btriangles"+b.triangles);

            a.v.addAll(b.v);
            a.triangles += b.triangles;
            //System.out.println("sumtriangles"+x.triangles);
            subGraphs.remove(bg);
            collapse();
        }
    }

    private static void findMax() throws IOException {
        int index = 0;
        int max = 0;
        for (int i = 0; i < subGraphs.size(); i++) {
            if (subGraphs.get(i).triangles > max) {
                index = i;
                max = subGraphs.get(i).triangles;
            }
        }
        subGraphs.get(index).printGraph();
    }

    static class SubG {
        Set<Integer> v;
        int triangles;

        SubG(int a, int b, int c) {
            v = new HashSet<>();
            v.add(a);
            v.add(b);
            v.add(c);
            triangles = 1;
        }

        void addVerticle(int d) {
            v.add(d);
            triangles++;
        }

        void printGraph() throws IOException {
            out.write((v.size() + "\n").getBytes());
            for (Integer val : v) {
                out.write(((val + 1) + " ").getBytes());
            }
        }

        String getV() {
            String str = "";
            for (Integer integer : v) {
                str += integer + " ";
            }
            return str;
        }
    }
}