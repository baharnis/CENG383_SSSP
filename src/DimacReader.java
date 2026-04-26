import java.io.*;

public class DimacReader {
    public static Graph read(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        Graph g = null;

        while ((line = br.readLine()) != null) {
            if (line.startsWith("c")) continue;

            if (line.startsWith("p")) {
                String[] parts = line.split(" ");
                int vertices = Integer.parseInt(parts[2]);
                g = new Graph(vertices);
            }

            if (line.startsWith("a") && g != null) {
                String[] parts = line.split(" ");
                int u = Integer.parseInt(parts[1]) - 1;
                int v = Integer.parseInt(parts[2]) - 1;
                int w = Integer.parseInt(parts[3]);
                g.addEdge(u, v, w);
            }
        }
        br.close();
        return g;
    }
}