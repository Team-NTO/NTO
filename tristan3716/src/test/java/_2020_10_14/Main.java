package _2020_10_14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static class Pos {
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public final static int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        public static int getDirection(int r, int c, int n) {
            if (r == 0) { // 아래로
                return 2;
            } else if (r == n + 1) { // 위로
                return 0;
            } else if (c == 0) { // 오른쪽으로
                return 1;
            } else {
                return 3;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        while (t-- != 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            boolean[][] isMirror = new boolean[n + 2][n + 2];

            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int mirrorR = Integer.parseInt(st.nextToken());
                int mirrorC = Integer.parseInt(st.nextToken());

                isMirror[mirrorR][mirrorC] = true;
            }

            st = new StringTokenizer(br.readLine(), " ");
            int laserR = Integer.parseInt(st.nextToken());
            int laserC = Integer.parseInt(st.nextToken());
            int laserDirection = Pos.getDirection(laserR, laserC, n);

            while (true) {
                laserR += Pos.DIRECTIONS[laserDirection][0];
                laserC += Pos.DIRECTIONS[laserDirection][1];

                if (laserR == 0 || laserR == n + 1 || laserC == 0 || laserC == n + 1) {
                    break;
                }

                if (isMirror[laserR][laserC]) {
                    laserDirection = (laserDirection + 1) % 4;
                }
            }

            System.out.println(laserR + " " + laserC);
        }
    }
}
