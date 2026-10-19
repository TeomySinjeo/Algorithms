package Breadth_and_Depth_Search;

import java.util.*;

public class Breadth_and_Depth_Search {

    static class Graph {
        private final Map<Integer, List<Integer>> adjList =
                new LinkedHashMap<>();

        public void addVertex(int vertex) {
            adjList.putIfAbsent(vertex, new ArrayList<>());
        }

        public void addEdge(int v1, int v2) {
            addVertex(v1);
            addVertex(v2);

            adjList.get(v1).add(v2);
            adjList.get(v2).add(v1);
        }

        public void Breadth(int start) {
            if (!adjList.containsKey(start)) {
                System.out.println("Вершина не найдена");
                return;
            }

            Set<Integer> visited = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();

            visited.add(start);
            queue.add(start);

            System.out.print("Поиск в ширину: ");

            while (!queue.isEmpty()) {
                int vertex = queue.poll();
                System.out.print(vertex + " ");

                for (int neighbor : adjList.get(vertex)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }

            System.out.println();
        }

        public void Depth(int start) {
            if (!adjList.containsKey(start)) {
                System.out.println("Вершина не найдена");
                return;
            }

            Set<Integer> visited = new HashSet<>();

            System.out.print("Поиск в глубину: ");
            dfsRecursive(start, visited);
            System.out.println();
        }

        private void dfsRecursive(int vertex, Set<Integer> visited) {
            visited.add(vertex);
            System.out.print(vertex + " ");

            for (int neighbor : adjList.get(vertex)) {
                if (!visited.contains(neighbor)) {
                    dfsRecursive(neighbor, visited);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);
        graph.addEdge(3, 7);

        graph.Breadth(1);
        graph.Depth(1);
    }
}

