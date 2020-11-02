package _2020_10_28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Main {
    private static int answer = 0;

    private static boolean isValidate(int bits) {
        Set<Pos> set = new HashSet<>();
        int c = 1 << (arr.length - 1);
        int idx = 0;
        while (c != 0) {
            if ((c & bits) != 0) {
                set.add(arr[idx]);
            }
            idx++;
            c >>= 1;
        }

        Queue<Pos> q = new LinkedList<>();
        Pos start = set.stream().findAny().get();
        q.offer(start);
        set.remove(start);
        int somCount = map[start.r][start.c] == 'S' ? 1 : 0;

        while (!q.isEmpty()) {
            Pos cur = q.poll();

            for (int[] d : Pos.dirs) {
                int nr = cur.r + d[0];
                int nc = cur.c + d[1];
                Pos next = new Pos(nr, nc);

                if (set.contains(next)) {
                    somCount += map[next.r][next.c] == 'S' ? 1 : 0;
                    set.remove(next);
                    q.offer(next);
                }
            }
        }

        return set.isEmpty() && somCount >= 4;
    }

    private static final Pos[] arr = new Pos[25];
    private static final char[][] map = new char[5][5];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int idx = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                arr[idx++] = new Pos(i, j);
            }
        }
        int bits = (1 << 7) - 1;
        while (bits < (1 << arr.length)) {
            if (isValidate(bits)) {
                answer++;
            }
            int t = bits | ((bits & -bits) - 1);
            bits = (t + 1) | (((~t & -~t) -1) >> (Integer.numberOfTrailingZeros(bits) + 1));
        }
        System.out.println(answer);
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }


        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Pos)) {
                return false;
            }
            Pos p = (Pos)obj;
            return this.r == p.r && this.c == p.c;
        }

        @Override
        public int hashCode() {
            return r * 25 + c;
        }

        private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    }
}
