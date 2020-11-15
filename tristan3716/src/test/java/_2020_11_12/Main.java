import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		int[] inDegree = new int[n+1];

		graph.add(null);
		for (int i = 1; i < n+1; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken());
			for (int j = 0; j < k; j++) {
				int b = Integer.parseInt(st.nextToken());
				graph.get(a).add(b);
				inDegree[b]++;
				a = b;
			}
		}

		Queue<Integer> q = new LinkedList<>();

		for (int i = 1; i < n+1; i++) {
			if (inDegree[i] == 0) {
				q.offer(i);
			}
		}

		int visit = 0;
		StringBuilder sb = new StringBuilder();
		while (!q.isEmpty()) {
			int c = q.poll();

			visit++;
			sb.append(c).append('\n');

			for (int next : graph.get(c)) {
				inDegree[next]--;
				if (inDegree[next] == 0) {
					q.offer(next);
				}
			}
		}

		if (visit == n) {
			System.out.println(sb);
		} else {
			System.out.println(0);
		}
	}
}