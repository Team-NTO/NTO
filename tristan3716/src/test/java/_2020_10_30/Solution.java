package _2020_10_30;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class Solution {
    @Test
    public void test() {
        Assertions.assertTrue(solution(9, new int[][]{{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}}, new int[][]{{8, 5}, {6, 7}, {4, 1}}));
    }
    @Test
    public void test2() {
        Assertions.assertFalse(solution(3, new int[][]{{0, 1}, {1, 2}}, new int[][]{{1, 2}, {2, 1}}));
        Assertions.assertTrue(solution(3, new int[][]{{0, 1}, {1, 2}}, new int[][]{{0, 1}, {1, 2}}));
    }
    @Test
    public void test3() {
        Assertions.assertFalse(solution(3, new int[][]{{0, 1}, {1, 2}}, new int[][]{{2, 1}}));
    }
    @Test
    public void test4() {
        Assertions.assertFalse(solution(2, new int[][]{{0, 1}}, new int[][]{{1, 0}}));
    }

    private ArrayList<ArrayList<Integer>> build(int n, int[][] path) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] p : path) {
            int a = p[0];
            int b = p[1];

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        return graph;
    }

    private ArrayList<ArrayList<Integer>> ordering(ArrayList<ArrayList<Integer>> graph, int[][] orders) {
        ArrayList<ArrayList<Integer>> graphOrdered = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            graphOrdered.add(new ArrayList<>());
        }
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        visited[0] = true;
        while (!q.isEmpty()) {
            int current = q.poll();
            for (int next : graph.get(current)) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                    graphOrdered.get(current).add(next);
                }
            }
        }

        for (int[] order : orders) {
            int a = order[0];
            int b = order[1];
            graphOrdered.get(a).add(b);
        }

        return graphOrdered;
    }

    private boolean hasCycle(ArrayList<ArrayList<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        boolean[] onStack = new boolean[graph.size()];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.offerLast(0);
        while (!stack.isEmpty()) {
            int current = stack.peekLast();

            if (!visited[current]) {
                visited[current] = true;
                onStack[current] = true;
            } else {
                onStack[current] = false;
                stack.pollLast();
            }

            for (int next : graph.get(current)) {
                if (!visited[next]) {
                    stack.offerLast(next);
                } else if (onStack[next]) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean solution(int n, int[][] path, int[][] order) {
        ArrayList<ArrayList<Integer>> graph = ordering(build(n, path), order);

        System.out.println(graph.toString());

        return !hasCycle(graph);
    }
}