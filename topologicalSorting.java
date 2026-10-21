package Topological_Sorting;

import java.util.*;

public class topologicalSorting {

    public static List<Integer> topologicalSort(
            int vertices, List<List<Integer>> graph) {

        int[] inDegree = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            for (int neighbor : graph.get(i)) {
                inDegree[neighbor]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);

            for (int neighbor : graph.get(vertex)) {
                inDegree[neighbor]--;

                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        if (result.size() != vertices) {
            throw new IllegalArgumentException(
                    "Ошибка: В 1-ну ил вершин входят 2 ребра (Цикл)"
            );
        }

        return result;
    }

    public static void main(String[] args) {
        int vertices = 6;

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(5).add(2);
        graph.get(5).add(0);
        graph.get(4).add(0);
        graph.get(4).add(1);
        graph.get(2).add(3);
        graph.get(3).add(1);

        List<Integer> result = topologicalSort(vertices, graph);

        System.out.println("Топологическая сортировка:");
        System.out.println(result);
    }
}

