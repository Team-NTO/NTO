package _2020_10_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class DisjointSet {
        private final int n;
        private final int[] p;
        private final int[] costs;

        public DisjointSet(int n, int[] costs) {
            this.n = n;
            this.p = new int[n];
            for (int i = 0; i < n; i++) {
                this.p[i] = i;
            }
            this.costs = costs;
        }

        public int find(int x) {
            return x == this.p[x] ? x : (this.p[x] = this.find(p[x]));
        }

        public void union(int a, int b) {
            a = this.find(a);
            b = this.find(b);

            if (a != b) {
                this.p[a] = b;
                this.costs[b] = Math.min(this.costs[a], this.costs[b]);
            }
        }

        public int getSum() {
            int sum = 0;
            boolean[] checked = new boolean[n];
            for (int i = 0; i < n; i++) {
                int p = this.find(i);
                if (!checked[p]) {
                    checked[p] = true;
                    sum += costs[p];
                }
            }

            return sum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] costs =  new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }

        DisjointSet set = new DisjointSet(n, costs);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            set.union(a, b);
        }

        int output = set.getSum();

        if (output > k) {
            System.out.println("Oh no");
        } else {
            System.out.println(output);
        }
    }
}
