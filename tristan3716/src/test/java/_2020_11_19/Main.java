package _2020_11_19;

import java.io.*;
import java.util.*;

public class Main {
    private static final Integer INF = Integer.MAX_VALUE / 3;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());

        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                int w = Integer.parseInt(st.nextToken());
                if (w == 0) {
                    dist[i][j] = INF;
                } else {
                    dist[i][j] = w;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (dist[j][k] > dist[j][i] + dist[i][k] && j != k) {
                        System.out.print(-1);
                        System.exit(0);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (dist[j][k] == dist[j][i] + dist[i][k]) {
                        dist[j][k] = INF;
                    }
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] != INF) {
                    sum += dist[i][j];
                }
            }
        }
        System.out.print(sum / 2);
    }
}
