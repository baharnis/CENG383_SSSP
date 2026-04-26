public class Benchmark {

    public static long[] measureDijkstra(Graph g, int source) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        runtime.gc();
        long timeBefore = System.nanoTime();

        Dijkstra.run(g, source);

        long timeAfter = System.nanoTime();

        return new long[]{
                (timeAfter - timeBefore) / 1_000,
                Dijkstra.lastMemoryUsed
        };
    }

    public static long[] measureBellmanFord(Graph g, int source) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        runtime.gc();
        long memBefore = runtime.totalMemory() - runtime.freeMemory();
        long timeBefore = System.nanoTime();

        BellmanFord.run(g, source);

        long timeAfter = System.nanoTime();
        long memAfter = runtime.totalMemory() - runtime.freeMemory();

        return new long[]{
                (timeAfter - timeBefore) / 1_000,
                Math.abs(memAfter - memBefore)
        };
    }
}