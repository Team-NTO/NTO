package _2020_10_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] dist = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            dist[i][i] = 0;
            for (int j = i + 1; j < n + 1; j++) {
                dist[i][j] = 0x3ffffff;
                dist[j][i] = 0x3ffffff;
            }
        }
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == -1) {
                break;
            }
            dist[a][b] = 1;
            dist[b][a] = 1;
        }
        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        int[] max = new  int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (dist[i][j] != 0x3ffffff && dist[i][j] > max[i]) {
                    max[i] = dist[i][j];
                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            min = Math.min(min, max[i]);
        }
        List<Integer> answer = new ArrayList<>();
        for (int i = 1; i < n + 1; i++) {
            if (max[i] == min) {
                answer.add(i);
            }
        }
        System.out.println(min + " " + answer.size());
        for (int x : answer) {
            System.out.print(x + " ");
        }
    }
}
