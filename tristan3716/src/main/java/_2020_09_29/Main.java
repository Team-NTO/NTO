package _2020_09_29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 16236 - 아기 상어
 * @link https://www.acmicpc.net/problem/16236
 */
public class Main {
    private static class Pos implements Comparable<Pos> {
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Pos o) {
            if (this.r == o.r) {
                return this.c - o.c;
            } else {
                return this.r - o.r;
            }
        }
    }
    private final static int[][] directions = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    public static int[] findNearest(final int[][] map, final boolean[][] visit, final int size, final int sr, final int sc) {
        Queue<Pos> queue = new LinkedList<>();
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        queue.offer(new Pos(sr, sc));
        visit[sr][sc] = true;


        int distance = 1;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                Pos current = queue.poll();
                int cr = current.r;
                int cc = current.c;

                for (int[] d : directions) {
                    int nr = cr + d[0];
                    int nc = cc + d[1];

                    if (visit[nr][nc]) {
                        continue;
                    }
                    if (size > map[nr][nc] && map[nr][nc] != 0) {
                        pq.offer(new Pos(nr, nc));
                    } else if (map[nr][nc] == 0 || size == map[nr][nc]) {
                        visit[nr][nc] = true;
                        queue.offer(new Pos(nr, nc));
                    }
                }
            }
            if (!pq.isEmpty()) {
                Pos next = pq.poll();
                return new int[]{next.r, next.c, distance};
            }
            distance += 1;
        }

        return null;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n+2][n+2];
        boolean[][] visit = new boolean[n+2][n+2];
        boolean[] src = new boolean[n+2];
        for (int i = 0; i < n + 2; i++) {
            map[i][0] = Integer.MAX_VALUE;
            map[0][i] = Integer.MAX_VALUE;
            map[i][n+1] = Integer.MAX_VALUE;
            map[n+1][i] = Integer.MAX_VALUE;
        }

        int sr = 0;
        int sc = 0;
        int[] t = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                int current = Integer.parseInt(st.nextToken());
                if (current == 9) {
                    sr = i + 1;
                    sc = j + 1;
                    current = 0;
                }
                t[j] = current;
            }
            System.arraycopy(t, 0, map[i + 1], 1, n);
        }

        int size = 2;
        int life = 0;
        int exp = 0;
        while (true) {
            int[] result = findNearest(map, visit, size, sr, sc);

            if (result == null) {
                break;
            }
            sr = result[0];
            sc = result[1];
            map[sr][sc] = 0;
            life += result[2];
            exp += 1;
            if (exp == size) {
                size += 1;
                exp = 0;
            }

            for (boolean[] v : visit) {
                System.arraycopy(src, 0, v, 0, n + 2);
            }
        }

        System.out.println(life);
    }
}
