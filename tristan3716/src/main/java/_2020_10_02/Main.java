package _2020_10_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 15961 - 회전초밥
 * @link https://www.acmicpc.net/problem/15961
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] eat = new int[d + 1];
        int[] list = new int[n + k];
        for (int i = 0; i < n; i++) {
            list[i] = Integer.parseInt(br.readLine());
        }
        System.arraycopy(list, 0, list, n, k - 1);

        int count = 0;
        int left = 0;
        for (int i = 0; i < k; i++) {
            int t = list[i];

            if (eat[t] == 0) count += 1;
            eat[t] += 1;
        }

        if (eat[c] == 0) count += 1;
        eat[c] += 1;

        int max = 0;
        for (int i = k; i < n + k; i++) {
            max = Math.max(max, count);

            int t = list[i];
            int r = list[left++];
            eat[r] -= 1;
            if (eat[r] == 0) count -= 1;
            if (eat[t] == 0) count += 1;
            eat[t] += 1;
        }

        System.out.println(max);
    }
}
