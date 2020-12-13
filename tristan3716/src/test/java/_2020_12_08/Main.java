package _2020_12_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 동현이는 정신을 잃었다
 */
public class Main {
    private static long nCr(long n) {
        return n * (n - 1) * (n - 2) / 6;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] connect = new long[n + 1];
        int[] parent = new int[n + 1];
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        graph.add(null);
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
            connect[u] += 1;
            connect[v] += 1;
        }
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        while (!q.isEmpty()) {
            int current = q.poll();
            for (int next : graph.get(current)) {
                if (parent[next] == 0) {
                    parent[next] = current;
                    q.offer(next);
                }
            }
        }
        parent[1] = 0;

        long d = 0;
        long g = 0;
        for (int i = 1; i < n + 1; i++) {
            if (parent[i] != 0) {
                d += (connect[parent[i]] - 1) * (connect[i] - 1);
            }
            g += nCr(connect[i]);
        }
        g *= 3;

        if (d == g) {
            System.out.println("DUDUDUNGA");
        } else if (d > g) {
            System.out.println("D");
        } else {
            System.out.println("G");
        }
    }
}