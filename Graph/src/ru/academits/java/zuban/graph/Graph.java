package ru.academits.java.zuban.graph;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Graph {
    private final int[][] adjacencyMatrix;

    public Graph(int[][] matrix) {
        if (matrix == null) {
            throw new NullPointerException("Двухмерный массив не должен быть null.");
        }

        for (int[] line : matrix) {
            if (matrix.length != line.length) {
                throw new IllegalArgumentException("Матрица не является квадратной");
            }
        }

        adjacencyMatrix = matrix;
    }

    public void bypassInWidth(int startNode, IntConsumer consumer) {
        validateNode(startNode);

        boolean[] visited = new boolean[adjacencyMatrix.length];

        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(startNode);
        visited[startNode] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            consumer.accept(node);

            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[node][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    public void bypassInDepthRecursion(int startNode, IntConsumer consumer) {
        validateNode(startNode);
        boolean[] visited = new boolean[adjacencyMatrix.length];

        recursiveBypassInDepth(startNode, visited, consumer);
    }

    private void recursiveBypassInDepth(int node, boolean[] visited, IntConsumer consumer) {
        visited[node] = true;
        consumer.accept(node);

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[node][i] == 1 && !visited[i]) {
                recursiveBypassInDepth(i, visited, consumer);
            }
        }
    }

    public void bypassInDepth(int startNode, IntConsumer consumer) {
        validateNode(startNode);

        boolean[] visited = new boolean[adjacencyMatrix.length];

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(startNode);

        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (!visited[node]) {
                consumer.accept(node);
                visited[node] = true;

                for (int i = adjacencyMatrix.length - 1; i >= 0; i--) {
                    if (adjacencyMatrix[node][i] == 1 && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }

    }

    private void validateNode(int node) {
        if (node < 0 || node >= adjacencyMatrix.length) {
            throw new NoSuchElementException("Узел с номером " + node + " не найден в графе");
        }
    }
}