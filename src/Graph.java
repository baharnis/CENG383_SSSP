import java.util.*;

public class Graph {
    public int vertices;
    public List<int[]>[] adjList;

    @SuppressWarnings("unchecked")
    public Graph(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++)
            adjList[i] = new ArrayList<>();
    }

    public void addEdge(int u, int v, int weight) {
        adjList[u].add(new int[]{v, weight});
    }
}