import java.util.*;

public class BellmanFord {
    public static long[] run(Graph g, int source) {
        long[] dist = new long[g.vertices];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[source] = 0;

        for (int i = 0; i < g.vertices - 1; i++) {
            for (int u = 0; u < g.vertices; u++) {
                if (dist[u] == Long.MAX_VALUE) continue;
                for (int[] edge : g.adjList[u]) {
                    int v = edge[0], w = edge[1];
                    if (dist[u] + w < dist[v])
                        dist[v] = dist[u] + w;
                }
            }
        }
        return dist;
    }
}