package contests.w31;

import java.util.*;

public class IV_SpanningTreeFraction {

    public static int sumForAllA = 0;
    public static int sumForAllB = 0;
    public static double diffForAll = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Graph.Edge> edges = new ArrayList<>();
        for (int a0 = 0; a0 < m; a0++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if (u == v) continue;
            sumForAllA += a;
            sumForAllB += b;
            edges.add(new Graph.Edge(u, v, a, b));
        }
        diffForAll = (double) sumForAllA / sumForAllB;
        Graph.Edge edge[] = new Graph.Edge[edges.size()];
        Graph graph = new Graph(n, edges.size(), edges.toArray(edge));
        graph.kruskalMST();
    }

    static class Graph {
        // A class to represent x graph edge
        public static class Edge implements Comparable<Edge> {
            int u, v, a, b;

            public Edge(int u, int v, int a, int b) {
                this.u = u;
                this.v = v;
                this.a = a;
                this.b = b;
            }

            // Comparator function used for sorting edges based on their weight
            public int compareTo(Edge other) {
                double fractionThis = (double) (sumForAllA - this.a) / (sumForAllB - this.b);
                double fractionOther = (double) (sumForAllA - other.a) / (sumForAllB - other.b);
                return (fractionThis - fractionOther) >= 0 ? 1 : -1;
            }
        }

        // A class to represent x Subset for union-find
        class Subset {
            int parent, rank;
        }

        int V, E;    // V-> no. of vertices & E->no.of edges
        Edge edge[]; // collection of all edges

        // Creates x graph with V vertices and E edges
        Graph(int v, int e, Edge[] edges) {
            V = v;
            E = e;
            edge = edges;
        }

        // A utility function to find set of an element i
        // (uses path compression technique)
        int find(Subset subsets[], int i) {
            // find root and make root as parent of i (path compression)
            if (subsets[i].parent != i)
                subsets[i].parent = find(subsets, subsets[i].parent);

            return subsets[i].parent;
        }

        // A function that does union of two sets of x and y
        // (uses union by rank)
        void union(Subset subsets[], int x, int y) {
            int xroot = find(subsets, x);
            int yroot = find(subsets, y);

            // Attach smaller rank tree under root of high rank tree
            // (union by Rank)
            if (subsets[xroot].rank < subsets[yroot].rank)
                subsets[xroot].parent = yroot;
            else if (subsets[xroot].rank > subsets[yroot].rank)
                subsets[yroot].parent = xroot;

                // If ranks are same, then make one as root and increment
                // its rank by one
            else {
                subsets[yroot].parent = xroot;
                subsets[xroot].rank++;
            }
        }

        // The main function to construct MST using Kruskal's algorithm
        void kruskalMST() {
            Arrays.sort(edge);

            // Allocate memory for creating V ssubsets
            Subset subsets[] = new Subset[V];
            for (int i = 0; i < V; ++i)
                subsets[i] = new Subset();

            // Create V subsets with single elements
            for (int v = 0; v < V; ++v) {
                subsets[v].parent = v;
                subsets[v].rank = 0;
            }

            int e = 0, i = 0;  // Index used to pick next edge

            int sumA = 0;
            int sumB = 0;
            // Number of edges to be taken is equal to V-1
            while (i < E) {
                Edge next_edge = edge[i++];

                int x = find(subsets, next_edge.u);
                int y = find(subsets, next_edge.v);

                // If including this edge does't cause cycle, include it
                // in result and increment the index of result for next edge
                if (x != y) {

                    e++;
                    sumA += next_edge.a;
                    sumB += next_edge.b;
                    union(subsets, x, y);
                }
                // Else discard the next_edge
            }

            System.out.println(asFraction(sumA, sumB));
        }
    }

    static long gcm(long a, long b) {
        return b == 0 ? a : gcm(b, a % b);
    }

    static String asFraction(long a, long b) {
        long gcm = gcm(a, b);
        if (gcm == 0) return a + "/" + b;
        return (a / gcm) + "/" + (b / gcm);
    }
}
