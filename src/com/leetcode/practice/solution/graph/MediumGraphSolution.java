package com.leetcode.practice.solution.graph;

import com.leetcode.practice.solution.linkedList.MediumLinkedListSolution;

import java.util.*;

public class MediumGraphSolution {
    //https://leetcode.com/problems/number-of-islands/description/?envType=study-plan-v2&envId=top-interview-150
    public int numIslands(char[][] grid) {
        int[] dir = {0, 1, 0, -1, 0};
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        Queue<int[]> q = new ArrayDeque<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == '1') {
                    q.offer(new int[]{r, c});
                    while (!q.isEmpty()) {
                        int[] coordinates = q.poll();
                        int row = coordinates[0];
                        int col = coordinates[1];
                        //mark grid as visited
                        grid[row][col] = '2';
                        for (int i = 0; i < 4; i++) {
                            int currRow = row + dir[i];
                            int currCol = col + dir[i + 1];
                            if (currRow < 0 || currRow >= m || currCol < 0 || currCol >= n) {
                                continue;
                            }
                            if (grid[currRow][currCol] == '1') {
                                q.offer(new int[]{currRow, currCol});
                            }
                        }
                    }
                    count++;
                }
            }
        }

        return count;
    }

    public int numIslandDfs(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char cell = grid[r][c];
                if (cell == '1') {
                    count++;
                    dfs(grid, r, c, m, n);
                }
            }
        }

        return count;

    }

    public void dfs(char[][] grid, int row, int col, int m, int n) {
        if (row < 0 || row >= m || col < 0 || col >= n || grid[row][col] != '1') {
            return;
        }

        grid[row][col] = '2';
        dfs(grid, row + 1, col, m, n);
        dfs(grid, row - 1, col, m, n);
        dfs(grid, row, col + 1, m, n);
        dfs(grid, row, col - 1, m, n);
    }

    public int numIslandsByDfsStack(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Deque<int[]> stack = new ArrayDeque<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == '1') {
                    stack.push(new int[]{r, c});
                    while (!stack.isEmpty()) {
                        int[] cell = stack.pop();
                        int x = cell[0], y = cell[1];
                        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != '1') {
                            continue;
                        }
                        grid[x][y] = '2'; // Mark as visited

                        for (int[] dir : directions) {
                            int nx = x + dir[0];
                            int ny = y + dir[1];
                            stack.push(new int[]{nx, ny});
                        }
                    }
                    count++;
                }
            }
        }

        return count;
    }

    //https://leetcode.com/problems/evaluate-division/?envType=study-plan-v2&envId=top-interview-150
    // Graph: variable -> list of neighbors and weights
    private Map<String, Map<String, Double>> graph = new HashMap<>();
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // Build the graph
        for (int i = 0; i < equations.size(); i++) {
            String A = equations.get(i).get(0);
            String B = equations.get(i).get(1);
            double k = values[i];

            graph.putIfAbsent(A, new HashMap<>());
            graph.putIfAbsent(B, new HashMap<>());
            graph.get(A).put(B, k);
            graph.get(B).put(A, 1.0 / k);
        }

        // Answer each query using DFS
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String X = queries.get(i).get(0);
            String Y = queries.get(i).get(1);
            Set<String> visited = new HashSet<>();

            results[i] = dfs(X, Y, visited);
        }

        return results;
    }

    // DFS helper function
    private double dfs(String current, String target, Set<String> visited) {
        if (!graph.containsKey(current) || !graph.containsKey(target)) {
            return -1.0;
        }
        if (current.equals(target)) {
            return 1.0;
        }

        visited.add(current);

        for (Map.Entry<String, Double> neighbor : graph.get(current).entrySet()) {
            String next = neighbor.getKey();
            if (visited.contains(next)) continue;

            double result = dfs(next, target, visited);
            if (result != -1.0) {
                return neighbor.getValue() * result;
            }
        }

        return -1.0;
    }

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node next;
        public Node random;

        public Node() {
            neighbors = new ArrayList<>();
        }

        public Node(int val) {
            this.val = val;
            neighbors = new ArrayList<>();
        }

        public Node(int val, List<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }

    // Recursive helper function to clone nodes using DFS
    private Node clone(Node root, Map<Integer, Node> map) {
        // If the node has not been cloned yet
        if (!map.containsKey(root.val)) {
            // Create a new node with the same value
            Node newNode = new Node(root.val);

            // Store it in the map to avoid re-cloning
            map.put(root.val, newNode);

            // Recursively clone and add all neighbors
            for (Node neighbor : root.neighbors) {
                newNode.neighbors.add(clone(neighbor, map));
            }
        }

        // Return the cloned node from the map
        return map.get(root.val);
    }

    //https://leetcode.com/problems/clone-graph/description/?envType=study-plan-v2&envId=top-interview-150
    // Main method to clone the graph starting from the given node
    public Node cloneGraph(Node node) {
        if (node == null) {
            // If the input node is null, return null (empty graph)
            return null;
        }

        // A map to keep track of already copied nodes using their values
        Map<Integer, Node> map = new HashMap<>();

        // Start DFS cloning from the given node
        return clone(node, map);
    }
}
