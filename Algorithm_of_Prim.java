package Algorithm_of_Prim;

import java.util.*;

public class Algorithm_of_Prim {

    static class Edge {
        String from;
        String to;
        int weight;

        Edge(String from, String to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static List<Edge> primMST(Map<String, Map<String, Integer>> graph, String startVertex) {

        Set<String> visited = new HashSet<>();

        List<Edge> mstEdges = new ArrayList<>();

        PriorityQueue<Edge> heap = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));

        visited.add(startVertex);

        for (Map.Entry<String, Integer> entry : graph.get(startVertex).entrySet()) {

            heap.add(new Edge(startVertex, entry.getKey(), entry.getValue()));
        }

        while (visited.size() < graph.size() && !heap.isEmpty()) {

            Edge edge = heap.poll();

            if (visited.contains(edge.to)) {
                continue;
            }

            visited.add(edge.to);

            mstEdges.add(edge);

            for (Map.Entry<String, Integer> entry : graph.get(edge.to).entrySet()) {

                String neighbor = entry.getKey();
                int weight = entry.getValue();

                if (!visited.contains(neighbor)) {
                    heap.add(new Edge(edge.to, neighbor, weight));
                }
            }
        }

        if (visited.size() != graph.size()) {
            throw new IllegalArgumentException(
                    "Ошибка: У графа нет циклов!"
            );
        }

        return mstEdges;
    }

    public static void main(String[] args) {

        Map<String, Map<String, Integer>> graph = new HashMap<>();

        graph.put("A", new HashMap<>());
        graph.put("B", new HashMap<>());
        graph.put("C", new HashMap<>());
        graph.put("D", new HashMap<>());
        graph.put("E", new HashMap<>());

        addEdge(graph, "A", "B", 7);
        addEdge(graph, "A", "C", 3);
        addEdge(graph, "B", "C", 2);
        addEdge(graph, "B", "D", 4);
        addEdge(graph, "C", "D", 5);
        addEdge(graph, "C", "E", 6);
        addEdge(graph, "D", "E", 1);

        List<Edge> mst = primMST(graph, "A");

        int totalWeight = 0;

        System.out.println("Ребра MST:");

        for (Edge edge : mst) {
            System.out.println("(" + edge.from + "," + edge.to + ")" + ": " + edge.weight);

            totalWeight += edge.weight;
        }

        System.out.println("Вес MST: " + totalWeight);
    }

    static void addEdge(Map<String, Map<String, Integer>> graph, String a, String b, int weight) {
        graph.get(a).put(b, weight);
        graph.get(b).put(a, weight);
    }
}
