// GraphGenerator.java
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GraphGenerator {
    public static Graph generateGraph(int vertices, int edges) {
        Graph graph = new Graph(vertices);
        Random random = new Random();
        
        // Contrainte de connectivité : arêtes [0,1], [1,2], ..., [n-2,n-1]
        for (int i = 0; i < vertices - 1; i++) {
            int weight = random.nextInt(1000) + 1;
            graph.addEdge(i, i + 1, weight);
        }
        
        // Ajouter les arêtes restantes aléatoirement
        int remainingEdges = edges - (vertices - 1);
        Set<String> addedEdges = new HashSet<>();
        
        // Marquer les arêtes obligatoires
        for (int i = 0; i < vertices - 1; i++) {
            addedEdges.add(i + "-" + (i + 1));
        }
        
        while (remainingEdges > 0) {
            int src = random.nextInt(vertices);
            int dest = random.nextInt(vertices);
            if (src != dest) {
                String edgeKey = Math.min(src, dest) + "-" + Math.max(src, dest);
                if (!addedEdges.contains(edgeKey)) {
                    int weight = random.nextInt(1000) + 1;
                    graph.addEdge(src, dest, weight);
                    addedEdges.add(edgeKey);
                    remainingEdges--;
                }
            }
        }
        
        return graph;
    }
// Génère un graphe peu dense (3n arêtes)
    public static Graph generateSparseGraph(int n) {
        return generateGraph(n, 3 * n);
    }
// Génère un graphe moyennement dense (n²/10 arêtes)
    public static Graph generateDenseGraph(int n) {
        return generateGraph(n, (n * n) / 3);
    }
}