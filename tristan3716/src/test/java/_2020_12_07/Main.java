package _2020_12_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private static class Coin {
        int ar, ac;
        int br, bc;

        public Coin() {
        }

        public Coin(int ar, int ac, int br, int bc) {
            this.ar = ar;
            this.ac = ac;
            this.br = br;
            this.bc = bc;
        }

        @Override
        public String toString() {
            return "Coin{" +
                    "ar=" + ar +
                    ", ac=" + ac +
                    ", br=" + br +
                    ", bc=" + bc +
                    '}';
        }
    }
    private static void go(boolean[][][][] visit, Coin coin) {
        visit[coin.ar][coin.ac][coin.br][coin.bc] = true;
    }
    private static boolean visited(boolean[][][][] visit, Coin coin) {
        return visit[coin.ar][coin.ac][coin.br][coin.bc];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][m];
        boolean[][][][] visit = new boolean[n][m][n][m];
        Coin start = new Coin();
        int found = 0;
        for (int i = 0; i < n; i++) {
            char[] in = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = in[j];
                if (in[j] == 'o') {
                    if (found == 0) {
                        start.ar = i;
                        start.ac = j;
                        found += 1;
                    }  else {
                        start.br = i;
                        start.bc = j;
                    }
                }
            }
        }

        boolean drop = false;
        Queue<Coin> q = new LinkedList<>();
        q.add(start);
        go(visit, start);

        int time = 0;
        while (!q.isEmpty() && time < 10 && !drop) {
            int qs = q.size();
            for (int i = 0; i < qs; i++) {
                Coin current = q.poll();
                for (int[] d : directions) {
                    boolean ad = false, bd = false;
                    int nar = current.ar + d[0];
                    int nac = current.ac + d[1];
                    int nbr = current.br + d[0];
                    int nbc = current.bc + d[1];
                    if (nar < 0 || nac < 0 || nar >= n || nac >= m) {
                        ad = true;
                    }
                    if (nbr < 0 || nbc < 0 || nbr >= n || nbc >= m) {
                        bd = true;
                    }

                    if (ad && bd) {
                        continue;
                    }
                    if (ad || bd) {
                        drop = true;
                        break;
                    }
                    if (map[nar][nac] == '#' && map[nbr][nbc] == '#') {
                        continue;
                    }
                    if (map[nar][nac] == '#') {
                        nar = current.ar;
                        nac = current.ac;
                    }
                    if (map[nbr][nbc] == '#') {
                        nbr = current.br;
                        nbc = current.bc;
                    }
                    Coin next = new Coin(nar, nac, nbr, nbc);
                    if (!visited(visit, next)) {
                        go(visit, next);
                        q.offer(next);
                    }
                }
            }
            time++;
        }

        System.out.println(drop ? time : -1);
    }
}