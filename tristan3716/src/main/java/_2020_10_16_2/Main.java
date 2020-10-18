package _2020_10_16_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static class Pos {
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public static boolean isIn(int r, int c, int n, int m) {
            return r >= 0 && c >= 0 && r < n && c < m;
        }

        private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    }

    public static void gravity(char[][] map, int n) {
        for (int j = 0; j < 10; j++) {
            int si = n - 1;

            for (int i = n - 1; i >= 0; i--) {
                if (map[i][j] != '0') {
                    if (i != si) {
                        map[si][j] = map[i][j];
                        map[i][j] = '0';
                    }
                    si--;
                }
            }
        }
    }

    public static void print(char[][] map, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.printf("%1c", map[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean boom(char[][] map, int n, int k) {
        boolean boomed = false;

        boolean[][] visit = new boolean[n][10];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                if (!visit[i][j] && map[i][j] != '0') {
                    char startColor = map[i][j];

                    ArrayList<Pos> list = new ArrayList<>();
                    Queue<Pos> queue = new LinkedList<>();
                    Pos start = new Pos(i, j);
                    visit[i][j] = true;
                    queue.offer(start);
                    list.add(start);

                    while (!queue.isEmpty()) {
                        Pos current = queue.poll();

                        for (int[] dir : Pos.DIRECTIONS) {
                            int nr = current.r + dir[0];
                            int nc = current.c + dir[1];

                            if (Pos.isIn(nr, nc, n, 10) && !visit[nr][nc] && map[nr][nc] == startColor) {
                                visit[nr][nc] = true;
                                Pos next = new Pos(nr, nc);
                                queue.offer(next);
                                list.add(next);
                            }
                        }
                    }

                    if (list.size() >= k) {
                        boomed = true;
                        for (Pos x : list) {
                            map[x.r][x.c] = '0';
                        }
                    }
                }
            }
        }

        return boomed;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][10];

        for (int i = 0; i < n; i++) {
            char[] arr = br.readLine().toCharArray();
            System.arraycopy(arr, 0, map[i], 0, 10);
        }

        while (true) {
            boolean boomed = boom(map, n, k);
            gravity(map, n);

            if (!boomed) {
                break;
            }
        }
        print(map, n);
    }
}
