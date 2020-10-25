package _2020_10_19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Edge {
        int e, c;

        public Edge(int e, int c) {
            this.e = e;
            this.c = c;
        }
    }

    private static class Vertex implements Comparable<Vertex> {
        int v;
        int cost;

        public Vertex(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Vertex vertex) {
            return this.cost - vertex.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int k = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            graph.add(null);
            for (int i = 0; i < m; i++) {
                graph.add(new ArrayList<>());
            }

            int[] max = new int[m + 1];
            int[] count = new int[m + 1];
            int[] inDegree = new int[m + 1];
            for (int i = 0; i < p; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                inDegree[b]++;
                graph.get(a).add(b); // Directed
            }

            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i < m + 1; i++) {
                if (inDegree[i] == 0) {
                    q.offer(i);
                    max[i] = 1;
                }
            }

            int last = 0;
            while (!q.isEmpty()) {
                int current = q.poll();
                last = current;
                for (int next : graph.get(current)) {
                    int i = max[current];
                    count[next]++;
                    if (max[next] < i) {
                        max[next] = i;
                        count[next] = 1;
                    }
                    if (--inDegree[next] == 0) {
                        if (count[next] > 1) {
                            max[next]++;
                        }
                        q.offer(next);
                    }
                }
            }

            System.out.printf("%d %d\n", k, max[last]);
        }
    }
}
