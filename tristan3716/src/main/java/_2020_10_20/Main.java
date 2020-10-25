package _2020_10_20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        graph.add(null);
        for (int i = 0; i < m; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inDegree = new int[n + 1];
        int[][] require = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            require[x][y] = k;
            inDegree[x] += 1;
            graph.get(y).add(x); // Directed
        }
        ArrayList<Integer> basic = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < n + 1; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
                basic.add(i);
            }
        }

        while (!q.isEmpty()) {
            int current = q.poll();
            for (int next : graph.get(current)) {
                if (require[next][current] != 0) {
                    for (int j = 1; j < n + 1; j++) {
                        require[next][j] += require[next][current] * require[current][j];
                    }
                }
                inDegree[next] -= 1;
                if (inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        for (int i = 0; i < n + 1; i++) {
            assert inDegree[i] == 0;
        }
        for (int x : basic) {
            System.out.printf("%d %d\n", x, require[n][x]);
        }
    }
}
