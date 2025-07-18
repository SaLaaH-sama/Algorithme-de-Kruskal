// Kruskal.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {
    public static List<Edge> kruskalMST(Graph graph, UnionFindType ufType) {
        List<Edge> result = new ArrayList<>();
        Collections.sort(graph.edges);
        
        UnionFind uf;
        switch(ufType) {
            case SIMPLE: uf = new UnionFindSimple(graph.vertices); break;
            case WEIGHTED: uf = new UnionFindWeighted(graph.vertices); break;
            case FOREST: uf = new UnionFindForest(graph.vertices); break;
            default: throw new IllegalArgumentException("Type Union-Find invalide");
        }
        
        for (Edge edge : graph.edges) {
            int rootSrc = uf.find(edge.src);
            int rootDest = uf.find(edge.dest);
            if (rootSrc != rootDest) {
                result.add(edge);
                uf.union(edge.src, edge.dest);
            }
        }
        return result;
    }
    
    public static KruskalResult kruskalMSTWithTimes(Graph graph, UnionFindType ufType) {
        // Copier le graphe pour ne pas modifier l'original
        Graph graphCopy = new Graph(graph.vertices);
        for (Edge e : graph.edges) {
            graphCopy.addEdge(e.src, e.dest, e.weight);
        }
        
        // Mesurer temps de tri
        long startSort = System.nanoTime();
        Collections.sort(graphCopy.edges);
        long sortTime = System.nanoTime() - startSort;
        
        // Mesurer temps Union-Find
        long startUF = System.nanoTime();
        
        UnionFind uf;
        switch(ufType) {
            case SIMPLE: uf = new UnionFindSimple(graphCopy.vertices); break;
            case WEIGHTED: uf = new UnionFindWeighted(graphCopy.vertices); break;
            case FOREST: uf = new UnionFindForest(graphCopy.vertices); break;
            default: throw new IllegalArgumentException("Type Union-Find invalide");
        }
        
        List<Edge> result = new ArrayList<>();
        for (Edge edge : graphCopy.edges) {
            int rootSrc = uf.find(edge.src);
            int rootDest = uf.find(edge.dest);
            if (rootSrc != rootDest) {
                result.add(edge);
                uf.union(edge.src, edge.dest);
            }
        }
        
        long ufTime = System.nanoTime() - startUF;
        
        return new KruskalResult(result, sortTime, ufTime);
    }
    
    public enum UnionFindType { SIMPLE, WEIGHTED, FOREST }
    
    // Classe pour encapsuler les résultats avec temps
    public static class KruskalResult {
        public final List<Edge> mst;
        public final long sortTimeNanos;
        public final long unionFindTimeNanos;
        public final long totalTimeNanos;
        
        public KruskalResult(List<Edge> mst, long sortTime, long ufTime) {
            this.mst = mst;
            this.sortTimeNanos = sortTime;
            this.unionFindTimeNanos = ufTime;
            this.totalTimeNanos = sortTime + ufTime;
        }
        
        public long getSortTimeMs() { return sortTimeNanos / 1_000_000; }
        public long getUnionFindTimeMs() { return unionFindTimeNanos / 1_000_000; }
        public long getTotalTimeMs() { return totalTimeNanos / 1_000_000; }
    }
}