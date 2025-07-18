// PerformanceTest.java - Tests de performance conformes aux consignes
import java.io.IOException;

public class PerformanceTest {
    public static void main(String[] args) throws IOException {
        System.out.println("=== TESTS DE PERFORMANCE KRUSKALGraphFileUtils.java ===\n");
        
        // 1. Tests des 6 instances générées
        testGeneratedInstances();
        
        // 2. Test avec fichier graphe.txt (REQUIS)
        testFileInstance();
    }
    
    private static void testGeneratedInstances() {
        System.out.println("=== TESTS DES 6 INSTANCES GÉNÉRÉES ===\n");
        
        // Tailles exactes demandées dans les consignes
        int[] sizes = {10, 1000, 10000};
        
        for (int n : sizes) {
            System.out.println("--- Tests avec n = " + n + " ---");
            
            // Graphe peu dense (3n arêtes) 
            testGraphType(n, 3 * n, "Peu dense");
            
            // Graphe dense (n²/3 arêtes) 
            testGraphType(n, (n * n) / 3, "Dense");
            
            System.out.println();
        }
    }
    
    private static void testFileInstance() throws IOException {
        System.out.println("=== TEST AVEC FICHIER graphe.txt ===\n");
        
        try {
            Graph fileGraph = GraphFileUtils.readGraph("graphe.txt");
            System.out.println("Graphe lu depuis fichier : " + fileGraph.vertices + " sommets, " + fileGraph.edges.size() + " arêtes\n");
            
            // Test avec affichage détaillé (REQUIS)
            testGraphWithDetailedOutput(fileGraph);
            
        } catch (Exception e) {
            System.out.println("Erreur lecture fichier graphe.txt: " + e.getMessage());
            System.out.println("Assurez-vous que le fichier existe et respecte le format requis.\n");
        }
    }
    
    private static void testGraphType(int vertices, int edges, String type) {
        System.out.println("Graphe " + type + " : " + vertices + " sommets, " + edges + " arêtes");
        
        try {
            Graph graph = GraphGenerator.generateGraph(vertices, edges);
            testGraphWithSeparateTimes(graph, type);
        } catch (OutOfMemoryError e) {
            System.out.println("  ERREUR: Pas assez de mémoire pour cette taille");
        }
    }
    
    // MÉTHODE avec mesures séparées (tri + Union-Find)
    private static void testGraphWithSeparateTimes(Graph graph, String testName) {
        for (Kruskal.UnionFindType type : Kruskal.UnionFindType.values()) {
            Kruskal.KruskalResult result = Kruskal.kruskalMSTWithTimes(graph, type);
            
            int weight = result.mst.stream().mapToInt(e -> e.weight).sum();
            
            System.out.printf("  %s: Tri=%dms, Union-Find=%dms, Total=%dms, Poids MST=%d%n", 
                            type, result.getSortTimeMs(), result.getUnionFindTimeMs(), 
                            result.getTotalTimeMs(), weight);
        }
        System.out.println();
    }
    
    // MÉTHODE avec affichage détaillé (REQUIS par consignes)
    private static void testGraphWithDetailedOutput(Graph graph) {
        for (Kruskal.UnionFindType type : Kruskal.UnionFindType.values()) {
            System.out.println("=== ALGORITHME " + type + " ===");
            
            Kruskal.KruskalResult result = Kruskal.kruskalMSTWithTimes(graph, type);
            
            // AFFICHAGE REQUIS : Poids de l'arbre couvrant
            int totalWeight = result.mst.stream().mapToInt(e -> e.weight).sum();
            System.out.println("Poids de l'arbre couvrant minimal: " + totalWeight);
            
            // AFFICHAGE REQUIS : Liste des arêtes sélectionnées
            System.out.println("Arêtes sélectionnées:");
            for (Edge edge : result.mst) {
                System.out.printf("  (%d, %d) poids: %d%n", edge.src, edge.dest, edge.weight);
            }
            
            System.out.printf("Temps d'exécution - Tri: %dms, Union-Find: %dms, Total: %dms%n", 
                            result.getSortTimeMs(), result.getUnionFindTimeMs(), result.getTotalTimeMs());
            
            System.out.println();
        }
    }
}
