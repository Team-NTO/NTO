package _2020_11_20;

import java.io.*;
import java.util.*;

public class Main {
    private static class Pos implements Comparable<Pos> {
        int start;
        int end;

        public Pos(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Pos pos) {
            if (this.end == pos.end) {
                return this.start - pos.start;
            }
            return this.end - pos.end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Pos> list = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (b < a) {
                int t = a;
                a = b;
                b = t;
            }
            list.add(new Pos(a, b));
        }
        list.sort(null);

        int d = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> in = new PriorityQueue<>();
        int size = 0;
        for (Pos c : list) {
            in.offer(c.start);
            while (!in.isEmpty()) {
                if (in.peek() < c.end - d) {
                    in.poll();
                } else {
                    break;
                }
            }
            size = Math.max(size, in.size());
        }

        System.out.println(size);
    }
}
