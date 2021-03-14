package _2020_12_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Word implements Comparable<Word> {
        List<String> list;

        public Word(String str) {
            boolean isNumber = false;
            this.list = new ArrayList<>();
            StringBuilder subWord = new StringBuilder();
            for (char x : str.toCharArray()) {
                if (Character.isDigit(x)) {
                    if (!isNumber && subWord.length() > 0) {
                        list.add(subWord.toString());
                        subWord = new StringBuilder();
                    }
                    subWord.append(x);
                    isNumber = true;
                } else {
                    if (isNumber && subWord.length() > 0) {
                        list.add(subWord.toString());
                        subWord = new StringBuilder();
                    }
                    subWord.append(x);
                    isNumber = false;
                }
            }
            if (subWord.length() > 0) {
                list.add(subWord.toString());
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (String x : this.list) {
                sb.append(x);
            }
            return sb.toString();
        }

        @Override
        public int compareTo(Word other) {
            int length = Math.min(this.list.size(), other.list.size());
            for (int i = 0; i < length; i++) {
                int t = compare(this.list.get(i), other.list.get(i));
                if (t != 0) {
                    return t;
                }
            }
            return this.list.size() - other.list.size();
        }

        private int compare(String a, String b) {
            char[] arr = a.toCharArray();
            char[] brr = b.toCharArray();
            if (Character.isDigit(arr[0])) {
                if (Character.isDigit(brr[0])) { // 00
                    int ac = 0;
                    int bc = 0;
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i] != '0') {
                            break;
                        }
                        ac++;
                    }
                    for (int i = 0; i < brr.length; i++) {
                        if (brr[i] != '0') {
                            break;
                        }
                        bc++;
                    }
                    String aa = a.substring(ac);
                    String bb = b.substring(bc);

                    if (aa.length() < bb.length()) {
                        return -1;
                    } else if (aa.length() > bb.length()) {
                        return 1;
                    }

                    int c = aa.compareTo(bb);
                    if (c == 0) {
                        return ac - bc;
                    }

                    return c;
                } else { // 0A
                    return -1;
                }
            } else {
                if (Character.isDigit(brr[0])) { // A0
                    return 1;
                } else { // AA
                    int length = Math.min(arr.length, brr.length);
                    for (int i = 0; i < length; i++) {
                        int ca = arr[i];
                        int cb = brr[i];
                        if (ca >= 'a') {
                            ca -= 'a';
                            ca = ca * 2 + 1;
                        } else {
                            ca -= 'A';
                            ca = ca * 2;
                        }
                        if (cb >= 'a') {
                            cb -= 'a';
                            cb = cb * 2 + 1;
                        } else {
                            cb -= 'A';
                            cb = cb * 2;
                        }

                        if (ca < cb) return -1;
                        else if (ca > cb) return 1;
                    }
                }
            }

            return Integer.compare(a.length(), b.length());
        }
    }

    public static void main(String[] args) throws IOException {
        List<Word> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            list.add(new Word(br.readLine()));
        }

        list.sort(null);
        StringBuilder sb = new StringBuilder();
        for (Word x : list) {
            sb.append(x.toString()).append('\n');
        }

        System.out.println(sb);
    }
}
