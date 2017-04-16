package contests.w31;

// A Java Program to detect cycle in an undirected graph
import java.io.*;
import java.util.*;

// This class represents a directed graph using adjacency list
// representation
class VII_Helper_CountCyclesInGraph
{
    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; // Adjacency List Represntation

    // Constructor
    VII_Helper_CountCyclesInGraph(int v) {
        V = v;
        adj = new LinkedList[v];
        for(int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    // Function to add an edge into the graph
    void addEdge(int v,int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    // A recursive function that uses visited[] and parent to detect
    // cycle in subgraph reachable from vertex v.
    int isCyclicUtil(int v, Boolean visited[], int parent, int count)
    {
        // Mark the current node as visited
        visited[v] = true;
        Integer i;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext())
        {
            i = it.next();

            // If an adjacent is not visited, then recur for that
            // adjacent
            if (!visited[i])
            {
                return isCyclicUtil(i, visited, v, ++count);
            }

            // If an adjacent is visited and not parent of current
            // vertex, then there is a cycle.
            else if (i != parent) {
                return ++count;
            }
        }
        return 0;
    }

    // Returns true if the graph contains a cycle, else false.
    int isCyclic()
    {
        // Mark all the vertices as not visited and not part of
        // recursion stack
        Boolean visited[] = new Boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Call the recursive helper function to detect cycle in
        // different DFS trees
        for (int u = 0; u < V; u++)
            if (!visited[u]) // Don't recur for u if already visited
                return isCyclicUtil(u, visited, -1, 0);

        return 0;
    }


    // Driver method to test above methods
    public static void main(String args[])
    {
        // Create a graph given in the above diagram
        VII_Helper_CountCyclesInGraph g1 = new VII_Helper_CountCyclesInGraph(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 0);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        System.out.println("Graph contains cycle: "+g1.isCyclic());

        VII_Helper_CountCyclesInGraph g2 = new VII_Helper_CountCyclesInGraph(3);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        System.out.println("Graph contains cycle: "+g2.isCyclic());
    }
}