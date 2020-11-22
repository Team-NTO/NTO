package _2020_10_21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static final int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31, 2};
    private static final int[] daysSum = new int[14];
    static {
        for (int i = 1; i < 14; i++) {
            daysSum[i] = daysSum[i - 1] + days[i - 1];
        }
    }
    private static int toDay(int month, int day) {
        return daysSum[month] + day;
    }
    private static class Flower implements Comparable<Flower> {
        int start;
        int end;

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower other) {
            if (this.start == other.start) {
                return this.end - other.end;
            }
            return this.start - other.start;
        }
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
            list.add(new Flower(toDay(am, ad), toDay(bm, bd)));
        }
        list.sort(null);
        list.add(new Flower(toDay(13, 0), toDay(13, 1)));
        int covered = toDay(3, 1);
        int index = 0;
        int count = 0;
        int prevStart = list.get(0).start;
        int lastDay = list.get(0).end;
        while (index <= n) {
            Flower current = list.get(index);
            if (covered < current.start && prevStart <= covered) {
                covered = lastDay;
                count++;
                prevStart = current.start;
                if (covered > toDay(11, 30)) {
                    break;
                }
            } else {
                lastDay = Math.max(lastDay, current.end);
                index++;
            }
        }

        if (covered > toDay(11, 30)) {
            System.out.println(count);
        } else {
            System.out.println(0);
        }
    }
}