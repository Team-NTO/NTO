package _2020_10_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static class DisjointSet {
        private final int[] p;

        public DisjointSet(int n) {
            this.p = new int[n];
            for (int i = 0; i < n; i++) {
                this.p[i] = i;
            }
        }

        public int find(int x) {
            return x == this.p[x] ? x : (this.p[x] = this.find(p[x]));
        }

        public void union(int a, int b) {
            a = this.find(a);
            b = this.find(b);

            if (a != b) {
                this.p[a] = b;
            }
        }
    }

    static double getDistance(int a, int b, int c, int d) {
        long w = Math.abs(a - c);
        long h = Math.abs(b - d);
        return Math.sqrt(w * w + h * h);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        int[] xs = new int[n];
        int[] ys = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            xs[i] = Integer.parseInt(st.nextToken());
            ys[i] = Integer.parseInt(st.nextToken());

            for (int j = 0; j < i; j++) {
                double distance = getDistance(xs[i], ys[i], xs[j], ys[j]);
                edges.add(new Edge(i, j, distance));
            }
        }

        DisjointSet set = new DisjointSet(n);

        int edgeCount = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            int pa = set.find(a);
            int pb = set.find(b);
            if (pa != pb) {
                edgeCount += 1;
                set.union(pa, pb);
            }
        }

        double sum = 0;
        if (edgeCount < n - 1) {
            while (!edges.isEmpty()) {
                Edge edge = edges.poll();
                int a = edge.s;
                int b = edge.e;
                double w = edge.w;

                int pa = set.find(a);
                int pb = set.find(b);
                if (pa != pb) {
                    sum += w;
                    edgeCount += 1;
                    if (edgeCount == n - 1) break;
                    set.union(pa, pb);
                }
            }
        }

        System.out.printf("%.2f", sum);
    }

    private static class Edge implements Comparable<Edge> {
        int s;
        int e;
        Double w;

        public Edge(int s, int e, double w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.w.compareTo(edge.w);
        }
    }
}
