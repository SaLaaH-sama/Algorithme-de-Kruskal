import java.io.*;
import java.util.Scanner;

public class GraphFileUtils {
    public static Graph readGraph(String filename) throws IOException {
        Scanner scanner = new Scanner(new File(filename));
        
        int vertices = scanner.nextInt();
        int edges = scanner.nextInt();
        
        Graph graph = new Graph(vertices);
        
        for (int i = 0; i < edges; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(src, dest, weight);
        }
        
        scanner.close();
        return graph;
    }
}