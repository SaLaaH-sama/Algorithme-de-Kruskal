// Graph.java
import java.util.ArrayList;
import java.util.List;

public class Graph {
    int vertices;
    List<Edge> edges;
    
    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
    }
    
    public void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }
}