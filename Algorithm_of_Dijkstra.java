package Algorithm_of_Dijkstra;

import java.util.*;

class Main {

    static final int INF = Integer.MAX_VALUE;

    public static void dijkstra(
            List<List<int[]>> graph,
            int start,
            int n) {

        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        queue.add(new int[]{start, 0});

        while (!queue.isEmpty()) {

            int[] current = queue.poll();
            int vertex = current[0];

            if (visited[vertex]) {
                continue;
            }

            visited[vertex] = true;

            for (int[] edge : graph.get(vertex)) {

                int neighbor = edge[0];
                int weight = edge[1];

                if (dist[vertex] != INF && dist[vertex] + weight < dist[neighbor]) {

                    dist[neighbor] = dist[vertex] + weight;

                    queue.add(new int[]{neighbor, dist[neighbor]}
                    );
                }
            }
        }

        System.out.println("Кратчайшие расстояния от вершины " + start + ":");

        for (int i = 0; i < n; i++) {
            if (dist[i] == INF) {
                System.out.println("До вершины " + i + ": нет пути");
            } else {
                System.out.println("До вершины " + i + ": " + dist[i]);
            }
        }
    }

    public static void main(String[] args) {

        int n = 5;

        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new int[]{1, 4});
        graph.get(0).add(new int[]{2, 1});

        graph.get(1).add(new int[]{3, 1});

        graph.get(2).add(new int[]{1, 2});
        graph.get(2).add(new int[]{3, 5});
        graph.get(2).add(new int[]{4, 8});

        graph.get(3).add(new int[]{4, 3});

        dijkstra(graph, 0, n);
    }
}
