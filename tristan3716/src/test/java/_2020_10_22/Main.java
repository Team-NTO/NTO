package _2020_10_22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        int[] weight = new int[n+1];
        int[] inDegree = new int[n+1];
        int[] time = new int[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            weight[i] = Integer.parseInt(st.nextToken());

            while (true) {
                int c = Integer.parseInt(st.nextToken());
                if (c == -1) break;
                graph[c].add(i);
                inDegree[i]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
                time[i] = weight[i];
            }
        }

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : graph[current]) {
                inDegree[next]--;
                time[next] = Math.max(time[next], time[current] + weight[next]);
                if (inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            sb.append(time[i]).append('\n');
        }
        System.out.println(sb);
    }
}