package _2020_10_15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = Integer.MAX_VALUE / 3;

    private static void dijkstra(int[] distance, ArrayList<ArrayList<Edge>> graph, int start) {
        Arrays.fill(distance, INF);

        PriorityQueue<Vertex> q = new PriorityQueue<>();

        distance[start] = 0;
        q.offer(new Vertex(start, distance[start]));

        while (!q.isEmpty()) {
            Vertex x = q.poll();
            int v = x.v;
            int cost = x.cost;

            if (distance[v] >= cost) {
                for (Edge e : graph.get(v)) {
                    int n = e.e;
                    int w = e.c + cost;

                    if (distance[n] > w) {
                        distance[n] = w;
                        q.offer(new Vertex(n, w));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        ArrayList<ArrayList<Edge>> graphReverse = new ArrayList<>();
        graph.add(null);
        graphReverse.add(null);
        for (int i = 0; i < m; i++) {
            graph.add(new ArrayList<>());
            graphReverse.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Edge(end, cost)); // Directed
            graphReverse.get(end).add(new Edge(start, cost)); // Directed
        }

        int[] distance = new int[n + 1];
        int[] distanceReverse = new int[n + 1];

        dijkstra(distance, graph, x);
        dijkstra(distanceReverse, graphReverse, x);

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, distance[i] + distanceReverse[i]);
        }

        System.out.println(max);
    }

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
}
