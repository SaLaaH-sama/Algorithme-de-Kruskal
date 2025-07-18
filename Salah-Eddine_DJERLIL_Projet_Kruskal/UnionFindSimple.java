// UnionFindSimple.java (Implémentation 1)
public class UnionFindSimple implements UnionFind {
    private int[] parent;
    
    public UnionFindSimple(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }
    
    public int find(int x) {
        return parent[x];
    }
    
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return;
        
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == rootY) {
                parent[i] = rootX;
            }
        }
    }
}