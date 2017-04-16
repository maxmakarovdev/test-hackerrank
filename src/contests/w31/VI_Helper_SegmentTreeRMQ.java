package contests.w31;

import java.util.Scanner;

// Program for range minimum query using segment tree
class VI_Helper_SegmentTreeRMQ {
    int st[][]; //array to store segment tree (arrays of votes)
    int n;

    int[] votesFor(int[] x, int[] y) { // A utility function to get sum votes array of two
        //System.out.println("votesFor");
        //print(x);
        //print(y);
        int[] votes = new int[n];
        System.arraycopy(x, 0, votes, 0, n);
        for (int i = 0; i < n; i++) {
            votes[i] += y[i];
        }
        //print(votes);
        return votes;
    }

    // A utility function to get the middle index from corner indexes.
    int getMid(int s, int e) {
        return s + (e - s) / 2;
    }

    /*  A recursive function to get the minimum value in x given range of array indexes. The following are parameters for
        this function.

        st    --> Pointer to segment tree
        index --> Index of current node in the segment tree. Initially 0 is passed as root is always at index 0
        ss & se  --> Starting and ending indexes of the segment represented by current node, i.e., st[index]
        qs & qe  --> Starting and ending indexes of query range */
    int[] RMQUtil(int ss, int se, int qs, int qe, int index) {
        // If segment of this node is x part of given range, then return the min of the segment
        if (qs <= ss && qe >= se) return st[index];

        // If segment of this node is outside the given range
        if (se < qs || ss > qe) return new int[n];

        // If x part of this segment overlaps with the given range
        int mid = getMid(ss, se);
        return votesFor(RMQUtil(ss, mid, qs, qe, 2 * index + 1), RMQUtil(mid + 1, se, qs, qe, 2 * index + 2));
    }

    // Return minimum of elements in range from index qs (query start) to qe (query end).  It mainly uses RMQUtil()
    int RMQ(int n, int qs, int qe, int x) {
        // Check for erroneous input values
        /*if (qs < 0 || qe > n - 1 || qs > qe) {
            System.out.println("Invalid Input");
            return -1;
        } */
        int[] result = RMQUtil(0, n - 1, qs, qe, 0);
        for (int i = 0; i < n; i++) {
            if (result[i] == x) {
                return i;
            }
        }
        return -1;
    }

    // A recursive function that constructs Segment Tree for array[ss..se]. si is index of current node in segment tree st
    int[] constructSTUtil(int arr[], int ss, int se, int si) {
        // If there is one element in array, store it in current
        //  node of segment tree and return
        if (ss == se) {
            int[] votes = new int[n];
            votes[arr[ss]]++;
            st[si] = votes;
            return votes;
        }

        // If there are more than one elements, then recur for left and right subtrees and store the minimum of two values in this node
        int mid = getMid(ss, se);
        st[si] = votesFor(constructSTUtil(arr, ss, mid, si * 2 + 1), constructSTUtil(arr, mid + 1, se, si * 2 + 2));
        return st[si];
    }

    /* Function to construct segment tree from given array. This function allocates memory for segment tree and calls constructSTUtil() to
       fill the allocated memory */
    void constructST(int arr[], int n) {
        // Allocate memory for segment tree
        this.n = n;

        //Height of segment tree
        int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));

        //Maximum size of segment tree
        int max_size = 2 * (int) Math.pow(2, x) - 1;
        st = new int[max_size][n]; // allocate memory

        // Fill the allocated memory st
        constructSTUtil(arr, 0, n - 1, 0);
    }

    void printSt() {
        for (int i = 0; i < st.length; i++) {
            for (int j = 0; j < st[i].length; j++) {
                System.out.print(st[i][j] + " ");
            }
            System.out.println();
        }
    }
    void print(int[] arr){
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // Driver program to test above functions
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            int n = in.nextInt();
            int[] v = new int[n];
            for (int i = 0; i < n; i++) {
                v[i] = in.nextInt();
            }
            VI_Helper_SegmentTreeRMQ tree = new VI_Helper_SegmentTreeRMQ();
            tree.constructST(v, n); // Build segment tree from given array
            //tree.printSt();

            int g = in.nextInt();
            for (int a1 = 0; a1 < g; a1++) {
                int l = in.nextInt();
                int r = in.nextInt();
                int x = in.nextInt();

                System.out.println(tree.RMQ(n, l, r, x));
            }
        }
    }
}