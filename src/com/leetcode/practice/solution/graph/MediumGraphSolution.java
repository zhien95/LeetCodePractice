package com.leetcode.practice.solution.graph;

import java.util.ArrayDeque;
import java.util.Deque;

public class MediumGraphSolution {
    //https://leetcode.com/problems/number-of-islands/description/?envType=study-plan-v2&envId=top-interview-150
    public int numIslands(char[][] grid) {
        int[] dir = {0, 1, 0, -1, 0};
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        Deque<int[]> q = new ArrayDeque<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == '1') {
                    q.addLast(new int[]{r, c});
                    while (!q.isEmpty()) {
                        int[] coordinates = q.removeFirst();
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
                                q.push(new int[]{currRow, currCol});
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
}
