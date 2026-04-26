import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Graph dimacGraph = DimacReader.read("data/USA-road-d.W.gr");
        System.out.println("DIMACS graph loaded: " + dimacGraph.vertices + " node");
        long[] dimacBenchResult = Benchmark.measureDijkstra(dimacGraph, 0);
        System.out.println("DIMACS Dijkstra: " + dimacBenchResult[0] / 1000.0 + " ms");

        int[] sizes = {1000, 2000, 5000, 8000};

        long[] dijkstraTimes = new long[sizes.length];
        long[] bellmanTimes  = new long[sizes.length];

        System.out.printf("%-10s %-15s %-15s %-15s %-15s%n",
                "Nodes", "Dijkstra(ms)", "BF(ms)", "Dijkstra(KB)", "BF(KB)");

        for (int i = 0; i < sizes.length; i++) {
            Graph g = generateRandomGraph(sizes[i], sizes[i] * 3);

            long[] dimacResult = Benchmark.measureDijkstra(g, 0);
            long[] bResult = Benchmark.measureBellmanFord(g, 0);

            dijkstraTimes[i] = dimacResult[0];
            bellmanTimes[i]  = bResult[0];

            System.out.printf("%-10d %-15.3f %-15.3f %-15d %-15d%n",
                    sizes[i], dimacResult[0] / 1000.0, bResult[0] / 1000.0,
                    dimacResult[1], bResult[1] / 1024);
        }


        new java.io.File("results").mkdirs();


        ChartGenerator.generateRuntimeChart(sizes, dijkstraTimes, bellmanTimes);
        long[] dijkstraMem = new long[sizes.length];
        long[] bellmanMem  = new long[sizes.length];

        for (int i = 0; i < sizes.length; i++) {
            Graph g = generateRandomGraph(sizes[i], sizes[i] * 3);
            dijkstraMem[i] = Benchmark.measureDijkstra(g, 0)[1] / 1024;
            bellmanMem[i]  = Benchmark.measureBellmanFord(g, 0)[1] / 1024;
        }

        ChartGenerator.generateMemoryChart(sizes, dijkstraMem, bellmanMem);
        ChartGenerator.generateScalabilityChart(sizes, dijkstraTimes, bellmanTimes);
    }

    static Graph generateRandomGraph(int n, int edges) {
        Graph g = new Graph(n);
        Random rand = new Random(42);
        for (int i = 0; i < edges; i++) {
            int u = rand.nextInt(n);
            int v = rand.nextInt(n);
            int w = rand.nextInt(100) + 1;
            g.addEdge(u, v, w);
        }
        return g;
    }
}
