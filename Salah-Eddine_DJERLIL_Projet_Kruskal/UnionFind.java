// UnionFind.java - Interface pour les implémentations Union-Find
public interface UnionFind {
    /**
     * Trouve la racine de l'élément x
     */
    int find(int x);
    
    /**
     * Unit les composantes contenant x et y
     */
    void union(int x, int y);
}
