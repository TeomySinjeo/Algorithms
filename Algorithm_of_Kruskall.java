package Algorithm_of_Kruskall;

import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return src + " - " + dest + " (вес: " + weight + ")";
    }
}

class DSU {
    private int[] parent;
    private int[] rank;

    public DSU(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false;
        }

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }
}

class KruskalAlgorithm {

    public static List<Edge> kruskal(int vertices, List<Edge> edges) {
        List<Edge> mst = new ArrayList<>();
        Collections.sort(edges);
        DSU dsu = new DSU(vertices);

        for (Edge edge : edges) {
            if (dsu.union(edge.src, edge.dest)) {
                mst.add(edge);
                if (mst.size() == vertices - 1) {
                    break;
                }
            }
        }

        if (mst.size() != vertices - 1) {
            System.out.println("Граф несвязанный MST не существует полностью");
        }

        return mst;
    }

    public static void main(String[] args) {
        int vertices = 7;

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 7));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 2, 8));
        edges.add(new Edge(1, 3, 9));
        edges.add(new Edge(1, 4, 7));
        edges.add(new Edge(2, 4, 5));
        edges.add(new Edge(3, 4, 15));
        edges.add(new Edge(3, 5, 6));
        edges.add(new Edge(4, 5, 8));
        edges.add(new Edge(4, 6, 9));
        edges.add(new Edge(5, 6, 11));

        System.out.println("Ребра Графа");
        List<Edge> sorted = new ArrayList<>(edges);
        Collections.sort(sorted);
        for (Edge e : sorted) {
            System.out.println(e);
        }

        List<Edge> mst = kruskal(vertices, edges);

        System.out.println("\nМОД");
        int totalWeight = 0;
        for (Edge e : mst) {
            System.out.println(e);
            totalWeight += e.weight;
        }
        System.out.println("Общий вес MST: " + totalWeight);
    }
}