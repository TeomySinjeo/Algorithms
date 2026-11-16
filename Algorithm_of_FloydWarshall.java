package Algorithm_of_FloydWarshall;

import java.util.*;

class FloydWarshall {

    public static final int INF = 1_000_000_000;

    public static void floydWarshall(int[][] graph) {
        int n = graph.length;

        int[][] dist = new int[n][n];
        int[][] next = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = graph[i][j];
                if (graph[i][j] != INF && i != j) {
                    next[i][j] = j;
                } else {
                    next[i][j] = -1;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        boolean hasNegativeCycle = false;
        for (int i = 0; i < n; i++) {
            if (dist[i][i] < 0) {
                hasNegativeCycle = true;
                System.out.println("Отрицательный цикл" + i);
            }
        }

        System.out.println("\nМатрица кратчайших расстояний");
        printMatrix(dist, n);

        System.out.println("\nМатрица");
        printMatrix(next, n);

        System.out.println("\nВсе кратчайшие пути");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (dist[i][j] == INF) {
                    System.out.println(i + " -> " + j + ": пути нет");
                } else {
                    System.out.println(i + " -> " + j + ": " + dist[i][j] +
                            " / путь: " + buildPath(next, i, j));
                }
            }
        }

        if (hasNegativeCycle) {
            System.out.println("\nОшибка: Отрицательный цикл!");
        }
    }

    private static String buildPath(int[][] next, int i, int j) {
        if (next[i][j] == -1) return "пути нет";

        StringBuilder sb = new StringBuilder();
        sb.append(i);
        int current = i;
        while (current != j) {
            current = next[current][j];
            if (current == -1) return "пути нет";
            sb.append(" -> ").append(current);
        }
        return sb.toString();
    }

    private static void printMatrix(int[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == INF) {
                    System.out.printf("%6s", "INF");
                } else {
                    System.out.printf("%6d", matrix[i][j]);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        graph[0][1] = 5;
        graph[0][3] = 10;
        graph[1][2] = 3;
        graph[2][3] = 1;

        System.out.println("Смежности");
        printMatrix(graph, n);

        floydWarshall(graph);
    }
}