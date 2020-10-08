package _2020_10_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 19236 - 청소년 상어
 * @link https://www.acmicpc.net/problem/19236
 */

public class Main {
    private static class Fish {
        int r;
        int c;
        int dir;
        boolean alive;

        public Fish(int r, int c, int dir, boolean alive) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.alive = alive;
        }
        public Fish(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.alive = true;
        }
    }
    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < 4 && c < 4;
    }
    private static final int[][] DIRECTIONS = {
            {0, 0},
            {-1,  0}, {-1, -1}, { 0, -1}, {1, -1},
            { 1,  0}, { 1,  1}, { 0,  1}, {-1, 1}
    };

    public static void copy(int[][] copied, Fish[] copiedFishes) {
        for (int i = 0; i < 4; i++) {
            System.arraycopy(map[i], 0, copied[i], 0, 4);
        }

        for (int i = 1; i < 17; i++) {
            copiedFishes[i] = new Fish(
                    fishes[i].r,
                    fishes[i].c,
                    fishes[i].dir,
                    fishes[i].alive);
        }
    }
    public static void restore(int[][] copied, Fish[] copiedFishes) {
        for (int i = 0; i < 4; i++) {
            System.arraycopy(copied[i], 0, map[i], 0, 4);
        }

        for (int i = 1; i < 17; i++) {
            fishes[i].r = copiedFishes[i].r;
            fishes[i].c = copiedFishes[i].c;
            fishes[i].dir = copiedFishes[i].dir;
            fishes[i].alive = copiedFishes[i].alive;
        }
    }

    public static void moveFish() {
        for (int i = 1; i < 17; i++) {
            Fish fish = fishes[i];

            if (!fish.alive) continue;

            int nd = fish.dir;
            int startDir = fish.dir;

            int nr = fish.r + DIRECTIONS[nd][0];
            int nc = fish.c + DIRECTIONS[nd][1];

            while (true) {
                if (isIn(nr, nc)) {
                    int next = map[nr][nc];
                    if (next != -1) {
                        if (next != 0) {
                            fishes[next].r = fish.r;
                            fishes[next].c = fish.c;
                        }
                        map[fish.r][fish.c] = next;
                        fish.r = nr;
                        fish.c = nc;
                        map[nr][nc] = i;
                        break;
                    }
                }

                nd++;
                if (nd == 9) nd = 1;
                nr = fish.r + DIRECTIONS[nd][0];
                nc = fish.c + DIRECTIONS[nd][1];
                if (nd == startDir) {
                    break;
                }
            }
            fish.dir = nd;
        }
    }

    public static int maxCount = 0;
    public static void simulate(
            final int r,
            final int c,
            final int currentDirection,
            final int eatScore) {

        Fish[] copiedFishes = new Fish[17];
        int[][] copiedMap = new int[4][4];
        copy(copiedMap, copiedFishes);

        final int here = map[r][c];
        fishes[here].alive = false;
        map[r][c] = -1; // eat

        moveFish();
        map[r][c] = 0;

        int nr = r + DIRECTIONS[currentDirection][0];
        int nc = c + DIRECTIONS[currentDirection][1];

        boolean moved = false;
        while (isIn(nr, nc)) {
            if (map[nr][nc] > 0) {
                moved = true;
                simulate(nr, nc, fishes[map[nr][nc]].dir, eatScore + here);

            }

            nr = nr + DIRECTIONS[currentDirection][0];
            nc = nc + DIRECTIONS[currentDirection][1];
        }

        if (!moved) {
            maxCount = Math.max(maxCount, eatScore + here);
        }

        restore(copiedMap, copiedFishes);
        map[r][c] = here;
        fishes[here].alive = true;
    }

    static Fish[] fishes = new Fish[17];
    static int[][] map = new int[4][4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                map[i][j] = a;

                fishes[a] = new Fish(i, j, b);
            }
        }

        simulate(0, 0, fishes[map[0][0]].dir, 0);

        System.out.println(maxCount);
    }
}
