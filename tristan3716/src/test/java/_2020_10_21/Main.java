package _2020_10_21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static int[] daysSum = new int[13];
    static {
        for (int i = 1; i < 13; i++) {
            daysSum[i] = daysSum[i - 1] + days[i - 1];
        }
    }
    private static int toDay(int month, int day) {
        return daysSum[month] + day;
    }
    private static class Flower implements Comparable<Flower> {
        Integer start;
        Integer end;

        public Flower(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower other) {
            if (this.start.equals(other.start)) {
                return this.end.compareTo(other.end);
            }
            return this.start.compareTo(other.start);
        }

        @Override
        public String toString() {
            return "Flower{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
    private static int solve(PriorityQueue<Flower> pq) {
        System.out.println(pq.toString());
        int count = 0;
        int lastDay = 0;

        while (!pq.isEmpty()) {
            Flower top = pq.peek();
            while (true) {
                if (pq.isEmpty()) {
                    return 0;
                }
                if (top.start < lastDay) {
                    return 0;
                }
                top = pq.peek();
            }

            Flower current = pq.poll();
            if (current.start < lastDay) {
                continue;
            } else {
                lastDay = current.end;
                count += 1;
            }

            if (lastDay >= toDay(11, 30)) {
                System.out.println(count);
                System.exit(0);
            }
        }
        System.out.println(0);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Flower> pq = new PriorityQueue<>();
        ArrayList<Flower> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int am = Integer.parseInt(st.nextToken());
            int ad = Integer.parseInt(st.nextToken());
            int bm = Integer.parseInt(st.nextToken());
            int bd = Integer.parseInt(st.nextToken());
            pq.offer(new Flower(toDay(am, ad), toDay(bm, bd)));
        }
    }
}