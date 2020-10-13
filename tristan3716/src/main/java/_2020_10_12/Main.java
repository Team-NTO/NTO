package _2020_10_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Pos implements Comparable<Pos>{
        int r;
        int c;
        int index;
        int dist;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
        public Pos(int r, int c, int index, int dist) {
            this.r = r;
            this.c = c;
            this.index = index;
            this.dist = dist;
        }

        private static boolean isIn(int r, int c, int n) {
            return r < n && c < n && r >= 0 && c >= 0;
        }
        private static int[][] DIRECTIONS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        @Override
        public int compareTo(Pos pos) {
            if (this.dist == pos.dist) {
                if (this.r == pos.r) {
                    return this.c - pos.c;
                }
                return this.r - pos.r;
            } else {
                return this.dist - pos.dist;
            }
        }
    }

    private static int[][][][] buildDistance(
            int[][] map,
            int n) {
        int[][][][] dist = new int[n][n][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n; l++) {
                        dist[i][j][k][l] = -1;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean[][] visited = new boolean[n][n];

                Queue<Pos> q = new LinkedList<>();
                q.offer(new Pos(i, j));
                visited[i][j] = true;
                dist[i][j][i][j] = 0;

                int distance = 1;
                while (!q.isEmpty()) {
                    int qs = q.size();
                    for (int k = 0; k < qs; k++) {
                        Pos c = q.poll();

                        for (int[] DIR : Pos.DIRECTIONS) {
                            assert c != null;
                            int nr = c.r + DIR[0];
                            int nc = c.c + DIR[1];

                            if (Pos.isIn(nr, nc, n)
                                    && map[nr][nc] != 1
                                    && !visited[nr][nc]) {
                                visited[nr][nc] = true;
                                dist[i][j][nr][nc] = distance;
                                q.offer(new Pos(nr, nc));
                            }
                        }
                    }

                    distance += 1;
                }
            }
        }

        return dist;
    }

    public static int simulate(int k, int cr, int cc, int[][] customers, int[][][][] dist) {
        int customerCount = customers.length;
        boolean[] moved = new boolean[customerCount];
        for (int i = 0; i < customerCount; i++) {
            PriorityQueue<Pos> cs = new PriorityQueue<>();
            for (int j = 0; j < customerCount; j++) {
                if (moved[j]) continue;

                int sr = customers[j][0];
                int sc = customers[j][1];

                cs.offer(new Pos(sr, sc, j, dist[cr][cc][sr][sc]));
            }

            Pos c = cs.poll();
            assert c != null;
            int index = c.index;
            moved[index] = true;
            int sr = customers[index][0];
            int sc = customers[index][1];
            int dr = customers[index][2];
            int dc = customers[index][3];

            int taxi2customer = dist[cr][cc][sr][sc];
            int customer2destination = dist[sr][sc][dr][dc];
            if (taxi2customer == -1 || customer2destination == -1) {
                return -1;
            }

            int total = taxi2customer + customer2destination;
            if (total > k) {
                return -1;
            }

            cr = dr;
            cc = dc;
            k = k - taxi2customer + customer2destination;

            if (i + 1 == customerCount)
                return k;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        int sr = Integer.parseInt(st.nextToken()) - 1;
        int sc = Integer.parseInt(st.nextToken()) - 1;

        int[][] customers = new int[m][4];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            customers[i][0] = Integer.parseInt(st.nextToken()) - 1;
            customers[i][1] = Integer.parseInt(st.nextToken()) - 1;
            customers[i][2] = Integer.parseInt(st.nextToken()) - 1;
            customers[i][3] = Integer.parseInt(st.nextToken()) - 1;
        }

        int[][][][] dist = buildDistance(map, n);

        int answer = simulate(k, sr, sc, customers, dist);

        System.out.println(answer);
    }
}
