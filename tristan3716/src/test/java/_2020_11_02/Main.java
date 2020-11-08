package _2020_11_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static class Relay {
        char last;
        int used;
        int score;

        public Relay(char last, int used, int score) {
            this.last = last;
            this.used = used;
            this.score = score;
        }

        public boolean isUsed(int x) {
            return (this.used & (1 << x)) > 0;
        }
    }

    private static class Word {
        char s;
        char e;
        int id;
        int length;

        public Word(char s, char e, int id, int length) {
            this.s = s;
            this.e = e;
            this.id = id;
            this.length = length;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Character, List<Word>> graph = new HashMap<>();
        Queue<Relay> q = new LinkedList<>();

        int n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            String source = br.readLine();
            char[] arr = source.toCharArray();
            char first = arr[0];
            char last = arr[arr.length - 1];
            Word word = new Word(first, last, i, arr.length);

            if (graph.containsKey(first)) {
                graph.get(first).add(word);
            } else {
                List<Word> list = new ArrayList<>();
                list.add(word);
                graph.put(first, list);
            }

            q.offer(new Relay(last, 1 << i, arr.length));
        }

        int[][] dp = new int[n + 1][(1 << (n + 1)) + 1];
        int max = 0;
        while (!q.isEmpty()) {
            Relay r = q.poll();
            max = Math.max(max, r.score);

            char last = r.last;
            if (graph.containsKey(last)) {
                for (Word w : graph.get(last)) {
                    if (!r.isUsed(w.id)) {
                        int nextUsed = r.used | (1 << w.id);
                        if (dp[w.id][nextUsed] == 0) {
                            int nextScore = r.score + w.length;
                            q.offer(new Relay(w.e, nextUsed, nextScore));
                            dp[w.id][nextUsed] = nextScore;
                        }
                    }
                }
            }
        }

        System.out.println(max);
    }
}