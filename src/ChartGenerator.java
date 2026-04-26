import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.imageio.ImageIO;
import java.io.File;

public class ChartGenerator {


    public static void generateRuntimeChart(int[] sizes, long[] dijkstraTimes, long[] bellmanTimes) throws Exception {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < sizes.length; i++) {
            dataset.addValue(dijkstraTimes[i] / 1000.0, "Dijkstra", String.valueOf(sizes[i]));
            dataset.addValue(bellmanTimes[i] / 1000.0, "Bellman-Ford", String.valueOf(sizes[i]));
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Runtime Comparison",
                "Number of Nodes",
                "Time (ms)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        ImageIO.write(chart.createBufferedImage(800, 600), "PNG", new File("results/runtime.png"));
        System.out.println("✅Runtime graph recorded: results/runtime.png");
    }


    public static void generateMemoryChart(int[] sizes, long[] dijkstraMem, long[] bellmanMem) throws Exception {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < sizes.length; i++) {
            dataset.addValue(dijkstraMem[i], "Dijkstra", String.valueOf(sizes[i]));
            dataset.addValue(bellmanMem[i], "Bellman-Ford", String.valueOf(sizes[i]));
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Memory Usage Comparison",
                "Number of Nodes",
                "Memory (KB)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        ImageIO.write(chart.createBufferedImage(800, 600), "PNG", new File("results/memory.png"));
        System.out.println("✅ Memory graph recorded: results/memory.png");
    }


    public static void generateScalabilityChart(int[] sizes, long[] dijkstraTimes, long[] bellmanTimes) throws Exception {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < sizes.length; i++) {
            double ratio = dijkstraTimes[i] == 0 ? bellmanTimes[i] : (double) bellmanTimes[i] / dijkstraTimes[i];
            dataset.addValue(ratio, "BF / Dijkstra Ratio", String.valueOf(sizes[i]));
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Scalability: Bellman-Ford vs Dijkstra",
                "Number of Nodes",
                "BF Time / Dijkstra Time",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        ImageIO.write(chart.createBufferedImage(800, 600), "PNG", new File("results/scalability.png"));
        System.out.println("✅ Scalability graph recorded: results/scalability.png");
    }
}