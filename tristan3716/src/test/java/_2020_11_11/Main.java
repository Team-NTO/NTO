import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int t = Integer.parseInt(br.readLine());
		while (t-- != 0) {
			int n = Integer.parseInt(br.readLine());
			boolean[][] graph = new boolean[n + 1][n + 1];
			int[] in = new int[n + 1];
			int[] order = new int[n + 1];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= n; i++) {
				order[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 1; i <= n; i++) {
				for (int j = i + 1; j <= n; j++) {
					graph[order[i]][order[j]] = true;
					in[order[j]] += 1;
				}
			}
			int changed = Integer.parseInt(br.readLine());
			for (int i = 1; i <= changed; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (graph[a][b]) {
					graph[a][b] = false;
					graph[b][a] = true;
					in[b] -= 1;
					in[a] += 1;
				} else {
					graph[a][b] = true;
					graph[b][a] = false;
					in[b] += 1;
					in[a] -= 1;
				}
			}

			Queue<Integer> q = new LinkedList<>();
			for (int i = 1; i <= n; i++) {
				if (in[i] == 0) {
					q.offer(i);
				}
			}

			boolean ps = true;
			ArrayList<Integer> answer = new ArrayList<>();
			while (!q.isEmpty()) {
				if (q.size() > 1) {
					ps = false;
					break;
				}
				int current = q.poll();
				answer.add(current);

				for (int next = 1; next <= n; next++) {
					if (graph[current][next]) {
						in[next] -= 1;
						if (in[next] == 0) {
							q.offer(next);
						}
					}
				}
			}

			if (!ps) {
				System.out.println("?");
			} else if (answer.size() == n) {
				StringBuilder sb = new StringBuilder();
				for (int x : answer) {
					sb.append(x).append(' ');
				}
				System.out.println(sb);
			} else {
				System.out.println("IMPOSSIBLE");
			}
		}
	}
}