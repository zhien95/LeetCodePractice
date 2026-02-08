package com.leetcode.practice.solution.graph;

import java.util.*;

public class HardGraphSolution {
    //https://leetcode.com/problems/parallel-courses-iii/
    public int minimumTime(int n, int[][] relations, int[] time) {

        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[n];

        for (int[] r : relations) {
            int u = r[0] - 1;
            int v = r[1] - 1;

            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            indegree[v]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        int[] finishTime = new int[n];

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                finishTime[i] = time[i];
            }
        }

        int processed = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            processed++;

            for (Integer next : graph.getOrDefault(curr, Collections.emptyList())) {
                finishTime[next] = Math.max(finishTime[next], finishTime[curr] + time[next]);

                if (--indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        if (processed != n) return -1;

        return Arrays.stream(finishTime).max().orElse(0);
    }

}
