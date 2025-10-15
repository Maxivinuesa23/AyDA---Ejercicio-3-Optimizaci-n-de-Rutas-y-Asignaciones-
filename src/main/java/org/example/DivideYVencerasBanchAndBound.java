package org.example;

import java.util.PriorityQueue;

public class DivideYVencerasBanchAndBound {
    // Clase para representar un estado en el árbol de soluciones
    static class Node {
        int[] path;
        int level;
        int city;
        double cost;
        double bound;
        boolean[] visited;
        int[][] reducedMatrix; // Matriz de costos reducida

        Node(int level, int city, int[] path, double cost, boolean[] visited, int[][] reducedMatrix) {
            this.level = level;
            this.city = city;
            this.path = path.clone();
            this.cost = cost;
            this.visited = visited.clone();
            this.path[level] = city;
            this.visited[city] = true;
            this.reducedMatrix = new int[reducedMatrix.length][reducedMatrix[0].length];
            for (int i = 0; i < reducedMatrix.length; i++) {
                System.arraycopy(reducedMatrix[i], 0, this.reducedMatrix[i], 0, reducedMatrix.length);
            }
        }
    }

    // Comparador para la cola de prioridad
    static class NodeComparator implements java.util.Comparator<Node> {
        public int compare(Node a, Node b) {
            return Double.compare(a.bound, b.bound);
        }
    }

    // Reduce la matriz de costos y calcula el costo de reducción
    private static int reduceMatrix(int[][] matrix, int n) {
        int reductionCost = 0;

        // Reducción por filas
        for (int i = 0; i < n; i++) {
            int rowMin = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] < rowMin) {
                    rowMin = matrix[i][j];
                }
            }
            if (rowMin != Integer.MAX_VALUE) {
                reductionCost += rowMin;
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] != Integer.MAX_VALUE) {
                        matrix[i][j] -= rowMin;
                    }
                }
            }
        }

        // Reducción por columnas
        for (int j = 0; j < n; j++) {
            int colMin = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (matrix[i][j] < colMin) {
                    colMin = matrix[i][j];
                }
            }
            if (colMin != Integer.MAX_VALUE) {
                reductionCost += colMin;
                for (int i = 0; i < n; i++) {
                    if (matrix[i][j] != Integer.MAX_VALUE) {
                        matrix[i][j] -= colMin;
                    }
                }
            }
        }

        return reductionCost;
    }

    public static void solveTSP(int[][] distances) {
        int n = distances.length;
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());

        // Nodo de inicio
        int[] path = new int[n];
        boolean[] visited = new boolean[n];
        int[][] reducedMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(distances[i], 0, reducedMatrix[i], 0, n);
        }

        int initialBound = reduceMatrix(reducedMatrix, n);
        Node root = new Node(0, 0, path, 0, visited, reducedMatrix);
        root.bound = initialBound;
        pq.add(root);

        double minCost = Double.MAX_VALUE;
        int[] bestPath = null;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.bound >= minCost) {
                continue;
            }

            if (current.level == n - 1) {
                double finalCost = current.cost + distances[current.city][current.path[0]];
                if (finalCost < minCost) {
                    minCost = finalCost;
                    bestPath = current.path;
                }
                continue;
            }

            // Ramificación
            for (int i = 0; i < n; i++) {
                if (!current.visited[i]) {
                    int[][] newMatrix = new int[n][n];
                    for (int r = 0; r < n; r++) {
                        System.arraycopy(current.reducedMatrix[r], 0, newMatrix[r], 0, n);
                    }

                    // Costo de la nueva arista
                    int newCost = distances[current.city][i];

                    // Poner infinito en las aristas de retorno y de salida
                    for (int j = 0; j < n; j++) {
                        newMatrix[current.city][j] = Integer.MAX_VALUE;
                        newMatrix[j][i] = Integer.MAX_VALUE;
                    }
                    newMatrix[i][current.path[0]] = Integer.MAX_VALUE;

                    // Calcular la nueva cota
                    int reductionCost = reduceMatrix(newMatrix, n);
                    double newBound = current.bound + newCost + reductionCost;

                    if (newBound < minCost) {
                        Node child = new Node(current.level + 1, i, current.path, current.cost + newCost, current.visited, newMatrix);
                        child.bound = newBound;
                        pq.add(child);
                    }
                }
            }
        }

        System.out.println("Costo mínimo: " + minCost);
        System.out.print("Mejor ruta: ");
        for (int i = 0; i < bestPath.length; i++) {
            System.out.print(bestPath[i] + " -> ");
        }
        System.out.println(bestPath[0]);
    }
}
