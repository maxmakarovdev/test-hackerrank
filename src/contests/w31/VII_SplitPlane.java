package contests.w31;

import java.util.*;

public class VII_SplitPlane {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int a0 = 0; a0 < q; a0++) {
            int n = in.nextInt();
            if (n < 4) {
                System.out.println(1);
                continue;
            }
            Line[] lines = new Line[n];
            Integer nodes[] = new Integer[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = i;
            }
            boolean adjMatrix[][] = new boolean[n][n];
            HashMap<Long, Point> interceptPoints = new HashMap<>();

            for (int a1 = 0; a1 < n; a1++) {
                int x1 = in.nextInt();
                int y1 = in.nextInt();
                int x2 = in.nextInt();
                int y2 = in.nextInt();
                // Write Your Code Here
                lines[a1] = new Line(x1, y1, x2, y2);
                for (int i = 0; i < a1; i++) {
                    Point intercept = intersect(lines[i], lines[a1]);
                    if (intercept != null && interceptPoints.get(intercept.x * 10000000000L + intercept.y) == null) {
                        interceptPoints.put(intercept.x * 10000000000L + intercept.y, intercept);
                        adjMatrix[i][a1] = true;
                        adjMatrix[a1][i] = true;
                        //System.out.println(intersect(lines[i], lines[a1]));
                    }
                }
            }
            ElementaryCyclesSearch ecs = new ElementaryCyclesSearch(adjMatrix, nodes);
            List<List<Integer>> cycles = ecs.getElementaryCycles();
            List<List<Integer>> filtered = new ArrayList<>();
            int count = 0;
            for (int i = 0; i < cycles.size(); i++) {
                List<Integer> cycle = cycles.get(i);
                if (cycle.size() == 2) continue;
                if (!findArray(filtered, cycle)) {
                    filtered.add(cycle);
                    count++;
                }
                /*for (int j = 0; j < cycle.size(); j++) {
                    Integer node = (Integer) cycle.get(j);
                    if (j < cycle.size() - 1) {
                        System.out.print(node + " -> ");
                    } else {
                        System.out.print(node);
                    }
                }
                System.out.print("\n");*/
            }
            System.out.println(count + 1);
        }
    }

    public static boolean findArray(List<List<Integer>> lists, List list) {
        Collections.sort(list);
        for (List l : lists) {
            Collections.sort(l);
            if (Collections.indexOfSubList(list, l) != -1) {
                //System.out.println("sub for:");
                //System.out.println(l);
                //System.out.println(list);
                return true;
            }
        }
        return false;
    }

    public static Point intersect(Line line1, Line line2) {

        double a1 = line1.y2 - line1.y1;
        double b1 = line1.x1 - line1.x2;
        double c1 = a1 * line1.x1 + b1 * line1.y1;

        double a2 = line2.y2 - line2.y1;
        double b2 = line2.x1 - line2.x2;
        double c2 = a2 * line2.x1 + b2 * line2.y1;

        double det = a1 * b2 - a2 * b1;
        if (det == 0) { // lines are parallel
            return null;
        }

        double x = (b2 * c1 - b1 * c2) / det;
        double y = (a1 * c2 - a2 * c1) / det;

        if (Math.min(line1.x1, line1.x2) <= x && x <= Math.max(line1.x1, line1.x2)
                && Math.min(line2.x1, line2.x2) <= x && x <= Math.max(line2.x1, line2.x2)
                && Math.min(line1.y1, line1.y2) <= y && y <= Math.max(line1.y1, line1.y2)
                && Math.min(line2.y1, line2.y2) <= y && y <= Math.max(line2.y1, line2.y2)) { //todo optimize
            return new Point((int) x, (int) y);
        } else {
            return null;
        }
    }

    static class Point {
        public int x = 0;
        public int y = 0;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            return this.x == ((Point) obj).x && this.y == ((Point) obj).y;
        }

        @Override
        public String toString() {
            return "{" + "x=" + x + ", y=" + y + '}';
        }
    }

    static class Line {
        public int x1;
        public int y1;
        public int x2;
        public int y2;

        public Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    static class ElementaryCyclesSearch {
        /**
         * List of cycles
         */
        private List<List<Integer>> cycles = null;

        /**
         * Adjacency-list of graph
         */
        private int[][] adjList = null;

        /**
         * Graphnodes
         */
        private Integer[] graphNodes = null;

        /**
         * Blocked nodes, used by the algorithm of Johnson
         */
        private boolean[] blocked = null;

        /**
         * B-Lists, used by the algorithm of Johnson
         */
        private ArrayList[] B = null;

        /**
         * Stack for nodes, used by the algorithm of Johnson
         */
        private ArrayList stack = null;

        /**
         * Constructor.
         *
         * @param matrix     adjacency-matrix of the graph
         * @param graphNodes array of the graphnodes of the graph; this is used to
         *                   build sets of the elementary cycles containing the objects of the original
         *                   graph-representation
         */
        public ElementaryCyclesSearch(boolean[][] matrix, Integer[] graphNodes) {
            this.graphNodes = graphNodes;
            this.adjList = AdjacencyList.getAdjacencyList(matrix);
        }

        /**
         * Returns List::List::Object with the Lists of nodes of all elementary
         * cycles in the graph.
         *
         * @return List::List::Object with the Lists of the elementary cycles.
         */
        public List<List<Integer>> getElementaryCycles() {
            this.cycles = new ArrayList<>();
            this.blocked = new boolean[this.adjList.length];
            this.B = new ArrayList[this.adjList.length];
            this.stack = new ArrayList();
            StrongConnectedComponents sccs = new StrongConnectedComponents(this.adjList);
            int s = 0;

            while (true) {
                SCCResult sccResult = sccs.getAdjacencyList(s);
                if (sccResult != null && sccResult.getAdjList() != null) {
                    ArrayList[] scc = sccResult.getAdjList();
                    s = sccResult.getLowestNodeId();
                    for (int j = 0; j < scc.length; j++) {
                        if ((scc[j] != null) && (scc[j].size() > 0)) {
                            this.blocked[j] = false;
                            this.B[j] = new ArrayList();
                        }
                    }

                    this.findCycles(s, s, scc);
                    s++;
                } else {
                    break;
                }
            }

            return this.cycles;
        }

        /**
         * Calculates the cycles containing a given node in a strongly connected
         * component. The method calls itself recursivly.
         *
         * @param v
         * @param s
         * @param adjList adjacency-list with the subgraph of the strongly
         *                connected component s is part of.
         * @return true, if cycle found; false otherwise
         */
        private boolean findCycles(int v, int s, ArrayList[] adjList) {
            boolean f = false;
            this.stack.add(new Integer(v));
            this.blocked[v] = true;

            for (int i = 0; i < adjList[v].size(); i++) {
                int w = ((Integer) adjList[v].get(i)).intValue();
                // found cycle
                if (w == s) {
                    ArrayList cycle = new ArrayList();
                    for (int j = 0; j < this.stack.size(); j++) {
                        int index = ((Integer) this.stack.get(j)).intValue();
                        cycle.add(this.graphNodes[index]);
                    }
                    this.cycles.add(cycle);
                    f = true;
                } else if (!this.blocked[w]) {
                    if (this.findCycles(w, s, adjList)) {
                        f = true;
                    }
                }
            }

            if (f) {
                this.unblock(v);
            } else {
                for (int i = 0; i < adjList[v].size(); i++) {
                    int w = ((Integer) adjList[v].get(i)).intValue();
                    if (!this.B[w].contains(new Integer(v))) {
                        this.B[w].add(new Integer(v));
                    }
                }
            }

            this.stack.remove(new Integer(v));
            return f;
        }

        /**
         * Unblocks recursivly all blocked nodes, starting with a given node.
         *
         * @param node node to unblock
         */
        private void unblock(int node) {
            this.blocked[node] = false;
            ArrayList Bnode = this.B[node];
            while (Bnode.size() > 0) {
                Integer w = (Integer) Bnode.get(0);
                Bnode.remove(0);
                if (this.blocked[w.intValue()]) {
                    this.unblock(w.intValue());
                }
            }
        }
    }

    static class AdjacencyList {
        /**
         * Calculates a adjacency-list for a given array of an adjacency-matrix.
         *
         * @param adjacencyMatrix array with the adjacency-matrix that represents
         *                        the graph
         * @return int[][]-array of the adjacency-list of given nodes. The first
         * dimension in the array represents the same node as in the given
         * adjacency, the second dimension represents the indicies of those nodes,
         * that are direct successornodes of the node.
         */
        public static int[][] getAdjacencyList(boolean[][] adjacencyMatrix) {
            int[][] list = new int[adjacencyMatrix.length][];

            for (int i = 0; i < adjacencyMatrix.length; i++) {
                ArrayList v = new ArrayList();
                for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                    if (adjacencyMatrix[i][j]) {
                        v.add(new Integer(j));
                    }
                }

                list[i] = new int[v.size()];
                for (int j = 0; j < v.size(); j++) {
                    Integer in = (Integer) v.get(j);
                    list[i][j] = in.intValue();
                }
            }

            return list;
        }
    }

    static class SCCResult {
        private Set nodeIDsOfSCC = null;
        private ArrayList[] adjList = null;
        private int lowestNodeId = -1;

        public SCCResult(ArrayList[] adjList, int lowestNodeId) {
            this.adjList = adjList;
            this.lowestNodeId = lowestNodeId;
            this.nodeIDsOfSCC = new HashSet();
            if (this.adjList != null) {
                for (int i = this.lowestNodeId; i < this.adjList.length; i++) {
                    if (this.adjList[i].size() > 0) {
                        this.nodeIDsOfSCC.add(new Integer(i));
                    }
                }
            }
        }

        public ArrayList[] getAdjList() {
            return adjList;
        }

        public int getLowestNodeId() {
            return lowestNodeId;
        }
    }

    static class StrongConnectedComponents {
        /**
         * Adjacency-list of original graph
         */
        private int[][] adjListOriginal = null;

        /**
         * Adjacency-list of currently viewed subgraph
         */
        private int[][] adjList = null;

        /**
         * Helpattribute for finding scc's
         */
        private boolean[] visited = null;

        /**
         * Helpattribute for finding scc's
         */
        private ArrayList stack = null;

        /**
         * Helpattribute for finding scc's
         */
        private int[] lowlink = null;

        /**
         * Helpattribute for finding scc's
         */
        private int[] number = null;

        /**
         * Helpattribute for finding scc's
         */
        private int sccCounter = 0;

        /**
         * Helpattribute for finding scc's
         */
        private ArrayList currentSCCs = null;

        /**
         * Constructor.
         *
         * @param adjList adjacency-list of the graph
         */
        public StrongConnectedComponents(int[][] adjList) {
            this.adjListOriginal = adjList;
        }

        /**
         * This method returns the adjacency-structure of the strong connected
         * component with the least vertex in a subgraph of the original graph
         * induced by the nodes {s, s + 1, ..., n}, where s is a given node. Note
         * that trivial strong connected components with just one node will not
         * be returned.
         *
         * @param node node s
         * @return SCCResult with adjacency-structure of the strong
         * connected component; null, if no such component exists
         */
        public SCCResult getAdjacencyList(int node) {
            this.visited = new boolean[this.adjListOriginal.length];
            this.lowlink = new int[this.adjListOriginal.length];
            this.number = new int[this.adjListOriginal.length];
            this.visited = new boolean[this.adjListOriginal.length];
            this.stack = new ArrayList();
            this.currentSCCs = new ArrayList();

            this.makeAdjListSubgraph(node);

            for (int i = node; i < this.adjListOriginal.length; i++) {
                if (!this.visited[i]) {
                    this.getStrongConnectedComponents(i);
                    ArrayList nodes = this.getLowestIdComponent();
                    if (nodes != null && !nodes.contains(new Integer(node)) && !nodes.contains(new Integer(node + 1))) {
                        return this.getAdjacencyList(node + 1);
                    } else {
                        ArrayList[] adjacencyList = this.getAdjList(nodes);
                        if (adjacencyList != null) {
                            for (int j = 0; j < this.adjListOriginal.length; j++) {
                                if (adjacencyList[j].size() > 0) {
                                    return new SCCResult(adjacencyList, j);
                                }
                            }
                        }
                    }
                }
            }

            return null;
        }

        /**
         * Builds the adjacency-list for a subgraph containing just nodes
         * >= a given index.
         *
         * @param node Node with lowest index in the subgraph
         */
        private void makeAdjListSubgraph(int node) {
            this.adjList = new int[this.adjListOriginal.length][0];

            for (int i = node; i < this.adjList.length; i++) {
                ArrayList successors = new ArrayList();
                for (int j = 0; j < this.adjListOriginal[i].length; j++) {
                    if (this.adjListOriginal[i][j] >= node) {
                        successors.add(new Integer(this.adjListOriginal[i][j]));
                    }
                }
                if (successors.size() > 0) {
                    this.adjList[i] = new int[successors.size()];
                    for (int j = 0; j < successors.size(); j++) {
                        Integer succ = (Integer) successors.get(j);
                        this.adjList[i][j] = succ.intValue();
                    }
                }
            }
        }

        /**
         * Calculates the strong connected component out of a set of scc's, that
         * contains the node with the lowest index.
         *
         * @return Vector::Integer of the scc containing the lowest nodenumber
         */
        private ArrayList getLowestIdComponent() {
            int min = this.adjList.length;
            ArrayList currScc = null;

            for (int i = 0; i < this.currentSCCs.size(); i++) {
                ArrayList scc = (ArrayList) this.currentSCCs.get(i);
                for (int j = 0; j < scc.size(); j++) {
                    Integer node = (Integer) scc.get(j);
                    if (node.intValue() < min) {
                        currScc = scc;
                        min = node.intValue();
                    }
                }
            }

            return currScc;
        }

        /**
         * @return Vector[]::Integer representing the adjacency-structure of the
         * strong connected component with least vertex in the currently viewed
         * subgraph
         */
        private ArrayList[] getAdjList(ArrayList nodes) {
            ArrayList[] lowestIdAdjacencyList = null;

            if (nodes != null) {
                lowestIdAdjacencyList = new ArrayList[this.adjList.length];
                for (int i = 0; i < lowestIdAdjacencyList.length; i++) {
                    lowestIdAdjacencyList[i] = new ArrayList();
                }
                for (int i = 0; i < nodes.size(); i++) {
                    int node = ((Integer) nodes.get(i)).intValue();
                    for (int j = 0; j < this.adjList[node].length; j++) {
                        int succ = this.adjList[node][j];
                        if (nodes.contains(new Integer(succ))) {
                            lowestIdAdjacencyList[node].add(new Integer(succ));
                        }
                    }
                }
            }

            return lowestIdAdjacencyList;
        }

        /**
         * Searchs for strong connected components reachable from a given node.
         *
         * @param root node to start from.
         */
        private void getStrongConnectedComponents(int root) {
            this.sccCounter++;
            this.lowlink[root] = this.sccCounter;
            this.number[root] = this.sccCounter;
            this.visited[root] = true;
            this.stack.add(new Integer(root));

            for (int i = 0; i < this.adjList[root].length; i++) {
                int w = this.adjList[root][i];
                if (!this.visited[w]) {
                    this.getStrongConnectedComponents(w);
                    this.lowlink[root] = Math.min(lowlink[root], lowlink[w]);
                } else if (this.number[w] < this.number[root]) {
                    if (this.stack.contains(new Integer(w))) {
                        lowlink[root] = Math.min(this.lowlink[root], this.number[w]);
                    }
                }
            }

            // found scc
            if ((lowlink[root] == number[root]) && (stack.size() > 0)) {
                int next = -1;
                ArrayList scc = new ArrayList();

                do {
                    next = ((Integer) this.stack.get(stack.size() - 1)).intValue();
                    this.stack.remove(stack.size() - 1);
                    scc.add(new Integer(next));
                } while (this.number[next] > this.number[root]);

                // simple scc's with just one node will not be added
                if (scc.size() > 1) {
                    this.currentSCCs.add(scc);
                }
            }
        }
    }
}
