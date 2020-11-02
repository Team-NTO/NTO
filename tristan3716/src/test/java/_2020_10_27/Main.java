package _2020_10_27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    private static int[][] directions = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());
        int w = r2 - r1 + 1;
        int h = c2 - c1 + 1;
        int[][] map = new int[w][h];
        int count = w * h;
        int current = 1;
        int cr = 0;
        int cc = 0;
        int cd = 0;
        int length = 1;
        while (count > 0) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < length; j++) {
                    if (cr >= r1 && cr <= r2 && cc >= c1 && cc <= c2) {
                        count--;
                        map[cr - r1][cc - c1] = current;
                    }
                    current++;
                    cr += directions[cd][0];
                    cc += directions[cd][1];
                }
                cd = (cd + 1) % 4;
            }
            length++;
        }

        int maxValue = 0;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                maxValue = Math.max(maxValue, map[i][j]);
            }
        }
        int log = (int) (Math.log10(maxValue)) + 1;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                System.out.printf(String.format("%%%dd ", log), map[i][j]);
            }
            System.out.println();
        }
    }
}
