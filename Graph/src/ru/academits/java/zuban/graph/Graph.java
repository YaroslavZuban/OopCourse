package ru.academits.java.zuban.graph;

import java.util.*;
import java.util.function.IntConsumer;

public class Graph {
    private final int[][] adjacencyMatrix;

    public Graph(int[][] matrix) {
        if (matrix == null) {
            throw new NullPointerException("Двухмерный массив не должен быть null.");
        }

        for (int[] row : matrix) {
            if (matrix.length != row.length) {
                throw new IllegalArgumentException("Матрица не является квадратной");
            }
        }

        adjacencyMatrix = matrix;
    }

    public void bypassInWidth(IntConsumer consumer) {
        if (adjacencyMatrix.length == 0) {
            return;
        }

        boolean[] visited = new boolean[adjacencyMatrix.length];

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (!visited[i]) {
                Queue<Integer> queue = new ArrayDeque<>();

                queue.add(i);
                visited[i] = true;

                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    consumer.accept(node);

                    for (int j = 0; j < adjacencyMatrix.length; j++) {
                        if (adjacencyMatrix[node][j] == 1 && !visited[j]) {
                            queue.add(j);
                            visited[j] = true;
                        }
                    }
                }
            }
        }
    }

    public void traverseInDepth(IntConsumer consumer) {
        if (adjacencyMatrix.length == 0) {
            return;
        }

        boolean[] visited = new boolean[adjacencyMatrix.length];

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (!visited[i]) {
                traverseInDepth(i, visited, consumer);
            }
        }
    }

    private void traverseInDepth(int node, boolean[] visited, IntConsumer consumer) {
        visited[node] = true;
        consumer.accept(node);

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[node][i] == 1 && !visited[i]) {
                traverseInDepth(i, visited, consumer);
            }
        }
    }

    public void bypassInDepth(IntConsumer consumer) {
        if (adjacencyMatrix.length == 0) {
            return;
        }

        boolean[] visited = new boolean[adjacencyMatrix.length];

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (!visited[i]) {
                Deque<Integer> stack = new ArrayDeque<>();
                stack.push(i);

                while (!stack.isEmpty()) {
                    int node = stack.pop();

                    if (!visited[node]) {
                        consumer.accept(node);
                        visited[node] = true;

                        for (int j = adjacencyMatrix.length - 1; j >= 0; j--) {
                            if (adjacencyMatrix[node][j] == 1 && !visited[j]) {
                                stack.push(j);
                            }
                        }
                    }
                }
            }
        }
    }
}