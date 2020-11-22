package _2020_11_17;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static class Pos implements Comparable<Pos> {
        int r;
        int c;

        int near;
        int on;

        private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public Pos(int r, int c, int on, int near) {
            this.r = r;
            this.c = c;
            this.on = on;
            this.near = near;
        }

        private static boolean isIn(int r, int c, int n, int m) {
            return r >= 0 && c >= 0 && r < n && c < m;
        }

        @Override
        public int compareTo(Pos other) {
            if (this.on == other.on) {
                return this.near - other.near;
            }
            return this.on - other.on;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        Pos f = null;
        int[][] graph = new int[n + 2][m + 2];
        for (int i = 1; i <= n; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 1; j <= m; j++) {
                char ch = arr[j - 1];
                switch (ch) {
                case 'g':
                    graph[i][j] = 2;
                    for (int[] dir : Pos.directions) {
                        int nr = i + dir[0];
                        int nc = j + dir[1];

                        if (graph[nr][nc] == 0) {
                            graph[nr][nc] = 1;
                        }
                    }
                    break;
                case 'F':
                    f = new Pos(i, j, 0, 0);
                    break;
                case 'S':
                    pq.offer(new Pos(i, j, 0, 0));
                    break;
                }
            }
        }
        boolean[][] visited = new boolean[n + 2][m + 2];
        for (int i = 1; i <= n; i++) {
            visited[i][0] = true;
            visited[i][m + 1] = true;
        }
        for (int i = 1; i <= m; i++) {
            visited[0][i] = true;
            visited[n + 1][i] = true;
        }

        while (!pq.isEmpty()) {
            Pos current = pq.poll();
            for (int[] dir : Pos.directions) {
                int nr = current.r + dir[0];
                int nc = current.c + dir[1];

                if (!visited[nr][nc]) {
                    int no = current.on;
                    int nn = current.near;
                    switch (graph[nr][nc]) {
                        case 1:
                            nn += 1;
                            break;
                        case 2:
                            no += 1;
                            break;
                    }
                    pq.offer(new Pos(nr, nc, no, nn));
                    visited[nr][nc] = true;
                    if (f.r == nr && f.c == nc) {
                        System.out.printf("%d %d", current.on, current.near);
                        System.exit(0);
                    }
                }
            }
        }
    }
}
