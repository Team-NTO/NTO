package _2020_10_26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static int max = 0;
    private static void solve(final int[][] map, boolean[] used, int currentPosition, int sum) {
        if (currentPosition == 11) {
            max = Math.max(max, sum);
        }
        for (int i = 0; i < 11; i++) {
            if (!used[i] && map[i][currentPosition] != 0) {
                used[i] = true;
                solve(map, used, currentPosition + 1, sum + map[i][currentPosition]);
                used[i] = false;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- != 0) {
            int[][] map = new int[11][11];
            for (int i = 0; i < 11; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            max = 0;
            solve(map, new boolean[11], 0, 0);
            System.out.println(max);
        }
    }
}
