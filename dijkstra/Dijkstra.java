import java.util.*;

public class Dijkstra {
    static class Node {
        int vertex;
        int distance;
        
        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        // Exemple d'utilisation
        int n = 5;
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        // Construction du graphe (exemple)
        graph.get(0).add(new int[]{1, 4});
        graph.get(0).add(new int[]{2, 1});
        graph.get(1).add(new int[]{3, 1});
        graph.get(2).add(new int[]{1, 2});
        graph.get(2).add(new int[]{3, 5});
        graph.get(3).add(new int[]{4, 3});
        
        int start = 0;
        DijkstraResult result = dijkstra(graph, start);
        
        // Affichage des résultats
        System.out.println("Distances depuis le sommet " + start + ":");
        for (int i = 0; i < result.distances.length; i++) {
            System.out.println("Sommet " + i + ": " + result.distances[i]);
        }
    }

    static class DijkstraResult {
        int[] distances;
        int[] parents;
        
        public DijkstraResult(int[] distances, int[] parents) {
            this.distances = distances;
            this.parents = parents;
        }
    }

    public static DijkstraResult dijkstra(List<List<int[]>> graph, int start) {
        int n = graph.size();
        int[] distances = new int[n];
        int[] parents = new int[n];
        
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(parents, -1);
        
        distances[start] = 0;
        
        // Tas min basé sur la distance
        PriorityQueue<Node> heap = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        heap.add(new Node(start, 0));
        
        while (!heap.isEmpty()) {
            Node node = heap.poll();
            int currentVertex = node.vertex;
            int currentDist = node.distance;
            
            // Ignorer les entrées obsolètes
            if (currentDist > distances[currentVertex]) {
                continue;
            }
            
            // Parcourir les voisins
            for (int[] edge : graph.get(currentVertex)) {
                int neighbor = edge[0];
                int weight = edge[1];
                int newDist = currentDist + weight;
                
                // Relaxation
                if (newDist < distances[neighbor]) {
                    distances[neighbor] = newDist;
                    parents[neighbor] = currentVertex;
                    heap.add(new Node(neighbor, newDist));
                }
            }
        }
        
        return new DijkstraResult(distances, parents);
    }
}