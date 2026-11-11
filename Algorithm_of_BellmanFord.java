package Algorithm_of_BellmanFord;

import java.util.*;

class Edge {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return src + " -> " + dest + " (вес: " + weight + ")";
    }
}

class BellmanFord {

    public static final int INF = Integer.MAX_VALUE;

    public static void bellmanFord(int vertices, List<Edge> edges, int source) {
        int[] dist = new int[vertices];
        int[] parent = new int[vertices];

        Arrays.fill(dist, INF);
        Arrays.fill(parent, -1);
        dist[source] = 0;

        for (int i = 0; i < vertices - 1; i++) {
            for (Edge edge : edges) {
                if (dist[edge.src] != INF &&
                        dist[edge.src] + edge.weight < dist[edge.dest]) {
                    dist[edge.dest] = dist[edge.src] + edge.weight;
                    parent[edge.dest] = edge.src;
                }
            }
        }

        boolean hasNegativeCycle = false;
        for (Edge edge : edges) {
            if (dist[edge.src] != INF &&
                    dist[edge.src] + edge.weight < dist[edge.dest]) {
                hasNegativeCycle = true;
                System.out.println("Отрицательный цикл с ребром: " + edge);
                break;
            }
        }

        System.out.println("\nКратчайшие расстояния от вершины: " + source);
        for (int i = 0; i < vertices; i++) {
            if (dist[i] == INF) {
                System.out.println("Вершина " + i + ": Невозможно добраться");
            } else {
                System.out.println("Вершина " + i + ": " + dist[i] + " / путь: " + buildPath(parent, i));
            }
        }

        if (hasNegativeCycle) {
            System.out.println("\nГраф содержит отрицательный цикл");
        }
    }

    private static String buildPath(int[] parent, int vertex) {
        List<Integer> path = new ArrayList<>();
        int current = vertex;

        while (current != -1) {
            path.add(current);
            current = parent[current];
        }

        Collections.reverse(path);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i));
            if (i < path.size() - 1) {
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int vertices = 5;

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 6));
        edges.add(new Edge(0, 2, 7));
        edges.add(new Edge(1, 2, 8));
        edges.add(new Edge(1, 3, 5));
        edges.add(new Edge(1, 4, -4));
        edges.add(new Edge(2, 3, -3));
        edges.add(new Edge(2, 4, 9));
        edges.add(new Edge(3, 1, -2));
        edges.add(new Edge(4, 0, 2));
        edges.add(new Edge(4, 3, 7));

        System.out.println("Граф");
        for (Edge e : edges) {
            System.out.println(e);
        }

        bellmanFord(vertices, edges, 0);

        System.out.println("Граф с отрицательным циклом:");

        int vertices2 = 4;
        List<Edge> edges2 = new ArrayList<>();
        edges2.add(new Edge(0, 1, 1));
        edges2.add(new Edge(1, 2, -1));
        edges2.add(new Edge(2, 3, -1));
        edges2.add(new Edge(3, 1, -1));

        System.out.println("Граф");
        for (Edge e : edges2) {
            System.out.println(e);
        }

        bellmanFord(vertices2, edges2, 0);
    }
}