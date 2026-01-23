package com.leetcode.practice.solution.graph;

import java.util.*;

public class MediumGraphSolution {
    /**
     * [Number of Islands]
     * <p>
     * Counts the number of islands in a 2D binary grid where '1's represent land and '0's represent water.
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
     *
     * @param grid A 2D binary grid representing land ('1') and water ('0')
     * @return The number of islands in the grid
     */
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
        Stack<int[]> stack = new Stack<>();

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

    /**
     * [Evaluate Division]
     * <p>
     * Given a list of equations and their corresponding values, evaluate division queries.
     * Builds a graph where each variable is a node and edges represent ratios between variables.
     *
     * @param equations List of variable pairs representing equations (Ai / Bi)
     * @param values Corresponding values for each equation
     * @param queries List of queries to evaluate (Xi / Yi)
     * @return Array of results for each query
     */
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

    /**
     * [Clone Graph]
     * <p>
     * Clones a connected undirected graph. Each node contains a value and a list of its neighbors.
     * Uses DFS to traverse and create a deep copy of the graph.
     *
     * @param node Reference to the starting node of the graph to be cloned
     * @return Reference to the cloned graph's starting node
     */
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

    /**
     * [Game of Life]
     */
//https://leetcode.com/problems/game-of-life/?envType=study-plan-v2&envId=top-interview-150
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;

        int[][] dirs = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        // First pass: apply rules and mark transitions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int live = 0;
                for (int[] d : dirs) {
                    int x = i + d[0], y = j + d[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && Math.abs(board[x][y]) == 1) {
                        live++;
                    }
                }

                if (board[i][j] == 1 && (live < 2 || live > 3)) board[i][j] = -1;
                if (board[i][j] == 0 && live == 3) board[i][j] = 2;
            }
        }

        // Second pass: finalize board state
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] > 0 ? 1 : 0;
            }
        }
    }

    /**
     * [Surrounded Regions]
     */
//https://leetcode.com/problems/surrounded-regions/?envType=study-plan-v2&envId=top-interview-150
    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        //step 1 mark all border 'O' cell as 'T' which represent they are safe
        for (int i = 0; i < m; i++) {
            //left most column
            if (board[i][0] == 'O') {
                queue.offer(new int[]{i, 0});
            }
            if (board[i][n - 1] == 'O') {
                //right most column
                queue.offer(new int[]{i, n - 1});
            }
        }
        //start from index 1, avoid duplicated corners
        for (int j = 1; j < n - 1; j++) {
            //top most row
            if (board[0][j] == 'O') {
                queue.offer(new int[]{0, j});

            }
            //bottom most row
            if (board[m - 1][j] == 'O') {
                queue.offer(new int[]{m - 1, j});
            }
        }
        //step 2: identify the region which is the border cell extent
        int[] dir = new int[]{0, 1, 0, -1, 0};
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0], y = pos[1];
            board[x][y] = 'T';
            for (int i = 0; i < 4; i++) {
                int row = x + dir[i];
                int col = y + dir[i + 1];
                if (row >= 0 && row < m && col >= 0 && col < n && board[row][col] == 'O') {
                    queue.offer(new int[]{row, col});
                }
            }
        }
        //step 3: convert remaining '0' cell to 'X' and 'T' cell to '0'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'T') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    /**
     * [Snakes and Ladders]
     */
//https://leetcode.com/problems/snakes-and-ladders/description/?envType=study-plan-v2&envId=top-interview-150
    public int snakesAndLadders(int[][] board) {
        //BFS approach
        //initialise step as 1
        int n = board.length;
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        int step = 0;

        queue.offer(1);

        //visit each position, push next steps into queue
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                //reach the end
                if (curr == n * n) {
                    return step;
                }
                for (int move = 1; move <= 6 && curr + move <= n * n; move++) {
                    int next = curr + move;
                    int row = (next - 1) / n;
                    int col = (next - 1) % n;
                    //invert column for odd row
                    col = (row % 2 == 1) ? n - 1 - col : col;
                    row = n - 1 - row; // Adjust to top-down indexing
                    //if snake or ladder -> set next to the destination
                    if (board[row][col] != -1) {
                        next = board[row][col];
                    }

                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    /**
     * [Minimum Genetic Mutation]
     */
//https://leetcode.com/problems/minimum-genetic-mutation/?envType=study-plan-v2&envId=top-interview-150
    public int minMutation(String start, String end, String[] bank) {
        Set<String> geneBank = new HashSet<>(Arrays.asList(bank));
        if (!geneBank.contains(end)) return -1;

        char[] genes = {'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String curr = queue.poll();
                if (curr.equals(end)) return steps;

                char[] currArr = curr.toCharArray();
                for (int i = 0; i < currArr.length; i++) {
                    char old = currArr[i];
                    for (char g : genes) {
                        if (g == old) continue;
                        currArr[i] = g;
                        String mutated = new String(currArr);
                        if (geneBank.contains(mutated)) {
                            queue.offer(mutated);
                            geneBank.remove(mutated); // mark as visited
                        }
                    }
                    currArr[i] = old; // restore for next loop
                }
            }
            steps++;
        }

        return -1;
    }

    //https://leetcode.com/problems/course-schedule/submissions/1655737065/?envType=study-plan-v2&envId=top-interview-150
    static class CourseScheduleDfs {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            //build graph
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                graph.add(new ArrayList<>());
            }
            //[0,1] = 1 -> 0
            for (int[] r : prerequisites) {
                graph.get(r[1]).add(r[0]);
            }
            //start from vertex with no inward edges, traverse graph
            boolean[] visited = new boolean[numCourses];
            boolean[] recStack = new boolean[numCourses];

            for (int i = 0; i < numCourses; i++) {
                if (!visited[i] && isCyclic(i, visited, recStack, graph)) {
                    return false;

                }
            }

            return true;
        }

        public boolean isCyclic(int node, boolean[] visited, boolean[] recStack, List<List<Integer>> graph) {
            visited[node] = true;
            recStack[node] = true;
            for (int neighbour : graph.get(node)) {
                if (!visited[neighbour]) {
                    if (isCyclic(neighbour, visited, recStack, graph)) {
                        return true;
                    }
                } else if (recStack[neighbour]) {
                    return true; //back edge -> cycle
                }
            }

            recStack[node] = false; // done exploring node
            return false;
        }
    }

    static class CourseScheduleBfs {
        /**
         * Solves the Course Schedule problem using topological sorting.
         * <p>
         * Model:
         * - Courses are nodes
         * - Prerequisites form directed edges (pre → course)
         * <p>
         * Approach:
         * - Build graph and indegree array
         * - Use Kahn’s Algorithm to remove courses with no prerequisites
         * <p>
         * Cycle Detection:
         * - If all courses are processed, schedule is possible
         * <p>
         * Time Complexity: O(V + E)
         * Space Complexity: O(V + E)
         */
        //https://leetcode.com/problems/course-schedule/submissions/1655737065/?envType=study-plan-v2&envId=top-interview-150
        // 1 <- 0, 2 <- 1 == [[], [1], [2]]
        //  indegrees = [0, 1, 1]
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            List<List<Integer>> graph = new ArrayList<>();

            for (int i = 0; i < numCourses; i++) {
                graph.add(new ArrayList<>());
            }

            int[] indegree = new int[numCourses];

            for (int[] prerequisite : prerequisites) {
                int course = prerequisite[0];
                int pre = prerequisite[1];
                graph.get(pre).add(course);
                indegree[course]++;
            }

            Queue<Integer> q = new ArrayDeque<>();

            for (int course = 0; course < numCourses; course++) {
                if (indegree[course] == 0) {
                    q.offer(course);
                }
            }
            int taken = 0;

            while (!q.isEmpty()) {
                int course = q.poll();
                taken++;
                for (int neighbour : graph.get(course)) {
                    if (--indegree[neighbour] == 0) {
                        q.offer(neighbour);
                    }
                }
            }

            return taken == numCourses;
        }

        //https://leetcode.com/problems/course-schedule-ii/description/
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            //use graph to create a course to an adjacent list
            Map<Integer, List<Integer>> graph = new HashMap<>();

            //init empty adj list
            for (int i = 0; i < numCourses; i++) {
                graph.put(i, new ArrayList<>());
            }

            int[] indegree = new int[numCourses];

            //build graph and indegree
            for (int[] p : prerequisites) {
                int course = p[0];
                int pre = p[1];
                graph.get(pre).add(course);
                indegree[course]++;
            }

            //Queue to add courses with 0 indegree
            Queue<Integer> q = new ArrayDeque<>();
            for (int course = 0; course < numCourses; course++) {
                if (indegree[course] == 0) {
                    q.offer(course);
                }
            }

            int taken = 0;
            int[] order = new int[numCourses];

            while (!q.isEmpty()) {
                int course = q.poll();
                order[taken] = course;

                for (int neighbour : graph.get(course)) {
                    if (--indegree[neighbour] == 0) {
                        q.offer(neighbour);
                    }
                }
            }

            return taken == numCourses ? order : new int[0];
        }

    }

    public static class AccountsMergeSolution {
        //disjoint union set
        //use first email as anchor (parent/root) for each list of accounts
        //do a union to accounts for each grow
        //union find parent to establish all the emails based on anchor
        //merge the name and all the emails tgt as output

        private Map<String, String> parent = new HashMap<>();

        //method to find parent
        private String find(String x) {
            if (!parent.get(x).equals(x)) {
                parent.put(x, find(parent.get(x)));
            }

            return parent.get(x);
        }


        private void union(String a, String b) {
            String rootA = find(a);
            String rootB = find(b);

            if (!rootA.equals(rootB)) {
                parent.put(rootB, rootA);
            }

        }

        /**
         *
         * Disjoined set union
         *
         * @param accounts list of accounts, where account[0] = name and the rest are emails
         * @return merged accounts where account[0] = name, and remaining are email sorted lexigraphically
         */
        //https://leetcode.com/problems/accounts-merge/
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            Map<String, String> emailToName = new HashMap<>();

            // Step 1: Initialize Union Find
            for (List<String> account : accounts) {
                String name = account.get(0);
                for (int i = 1; i < account.size(); i++) {
                    String email = account.get(i);

                    parent.put(email, email); //init email as parents of itself
                    emailToName.put(email, name); //record email and owner
                }
            }

            // step 2: union emails of the same account
            for (List<String> account : accounts) {
                String firstEmail = account.get(1);
                for (int i = 2; i < account.size(); i++) {
                    String email = account.get(i);
                    union(firstEmail, email);
                }
            }

            Map<String, List<String>> groups = new HashMap<>();
            //step 3 group emails by parent
            for (String email : parent.keySet()) {
                String root = find(email);
                groups.computeIfAbsent(root, k -> new ArrayList<>()).add(email);
            }

            //step 4 form result
            List<List<String>> result = new ArrayList<>();

            for (String key : groups.keySet()) {
                List<String> merged = new ArrayList<>();
                List<String> emails = groups.get(key);
                Collections.sort(emails);
                String name = emailToName.get(emails.get(0));
                merged.add(name);
                merged.addAll(emails);

                result.add(merged);
            }

            return result;
        }
    }


    public static class CountComponentSolution {
        private int[] parent;
        private int[] size;

        /**
         * [Number of Connected Components in an Undirected Graph]
         * <p>
         * Counts the number of connected components in an undirected graph with n nodes.
         * A connected component is a subset of nodes where each node is reachable from every other node in the subset.
         *
         * @param n     Number of nodes in the graph
         * @param edges List of edges connecting the nodes
         * @return The number of connected components in the graph
         */
        public int countComponent(int n, int[][] edges) {
            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i; // Initialize each node to be its own parent
                size[i] = 1;
            }

            int res = n;
            for (int[] edge : edges) {
                int n1 = edge[0];
                int n2 = edge[1];

                res -= union(n1, n2);
            }

            return res;
        }

        private int find(int node) {
            if (parent[node] != node) {
                parent[node] = find(parent[node]);
            }

            return parent[node];
        }

        /**
         * union node 1 and node 2, assigning higher rank node as parent and update rank
         *
         * @param n1
         * @param n2
         * @return return 1 if successful else 0
         */
        private int union(int n1, int n2) {
            int root1 = find(n1);
            int root2 = find(n2);

            if (root1 == root2) {
                return 0;
            }

            if (size[root2] > size[root1]) {
                parent[root1] = root2;
                size[root2] += size[root1];

            } else {
                parent[root2] = root1;
                size[root1] += size[root2];
            }

            return 1;
        }
    }

    //https://leetcode.com/problems/count-the-number-of-complete-components/description/
    class CountCompleteComponentSolution {
        int[] parent;
        int[] rank;

        // Find with path compression
        private int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        // Union by rank
        private void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) return;

            if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA;
            } else {
                parent[rootB] = rootA;
                rank[rootA]++;
            }
        }

        /**
         * Counts the number of complete connected components in an undirected graph.
         * A connected component is complete if there is an edge between every pair of its vertices.
         * <p>
         * Problem: Given an integer n and a 2D array edges representing connections between vertices,
         * return the number of complete connected components in the graph.
         * <p>
         * A complete component of size k has exactly k*(k-1)/2 edges between its vertices.
         *
         * @param n     Number of vertices in the graph
         * @param edges Array of edges where each edge connects two vertices
         * @return The number of complete connected components in the graph
         */
        public int countCompleteComponents(int n, int[][] edges) {
            parent = new int[n];
            rank = new int[n];

            //step1 init parent and rank
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }

            //step2 union edges
            for (int[] edge : edges) {
                union(edge[0], edge[1]);
            }

            //step3 count component
            Map<Integer, Integer> componentCount = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int root = find(i);
                componentCount.put(root, componentCount.getOrDefault(root, 0) + 1);
            }

            //step4 count edges
            Map<Integer, Integer> edgeCount = new HashMap<>();
            for (int[] edge : edges) {
                int root = find(edge[0]);
                edgeCount.put(root, edgeCount.getOrDefault(root, 0) + 1);
            }

            //step5 count complete components
            int result = 0;
            for (int node : componentCount.keySet()) {
                int nodes = componentCount.get(node);
                int edgeNum = edgeCount.getOrDefault(node, 0);

                if (edgeNum == nodes * (nodes - 1) / 2) {
                    result++;
                }
            }

            return result;
        }
    }
}
