package _2020_09_28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 1563 - 개근상
 * @link https://www.acmicpc.net/problem/1563
 */
public class Main {
    private static final int m = 1000000;
    private static long[][][] cache;
    private static long solve(final int max, int today, int continuousAbsent, int totalLate, String path) {
        if (continuousAbsent >= 3) {
            return 0;
        }
        if (totalLate >= 2) {
            return 0;
        }
        if (today == max) {
            return 1;
        }

        if (cache[today][continuousAbsent][totalLate] != 0) {
            return cache[today][continuousAbsent][totalLate];
        }

        int sum = 0;

        sum += solve(max, today + 1, 0, totalLate, path + "O");
        sum += solve(max, today + 1, continuousAbsent + 1, totalLate, path + "L");
        sum += solve(max, today + 1, 0, totalLate + 1, path + "A");

        return cache[today][continuousAbsent][totalLate] = (sum % m);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        cache = new long[n + 1][3][2];

        long result = solve(n, 0, 0, 0, "");

        System.out.println(result);
    }
}
