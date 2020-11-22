package _2020_10_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1717 - 집합의 표현
 * @link https://www.acmicpc.net/problem/1717
 */

public class Main {
    private static int find(int[] p, int a) {
        return p[a] == a ? a : (p[a] = find(p, p[a]));
    }
    private static void union(int[] p, int[] r, int a, int b) {
        int pa = find(p, a);
        int pb = find(p, b);

        if (pa == pb) {
            return;
        }
        if (r[pa] < r[pb]) {
            p[pa] = pb;
        } else {
            p[pb] = pa;
        }
        if (r[pa] == r[pb]) {
            r[pb]++;
        }
    }
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken()) + 1;
		int m = Integer.parseInt(st.nextToken());
        int[] p = new int[n];
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			char t = st.nextToken().charAt(0);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			switch (t) {
				case '0':
                    union(p, r, a, b);
					break;
				case '1':
                    sb.append(find(p, a) == find(p, b) ? "YES" : "NO").append('\n');
			}
		}
		System.out.println(sb);
	}
}
