package _2020_12_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class NaturalOrderComparator implements Comparator<String> {
        private static char order(char x) {
            if (x == 0) {
                return 0;
            }
            char ch;
            if ('A' <= x && x <= 'Z') {
                ch = (char) (x * 2);
            } else {
                ch = (char) ((x - 32) * 2 + 1);
            }
            return ch;
        }
        int compareRight(String a, String b) {
            int bias = 0, ia = 0, ib = 0;

            for (; ; ia++, ib++) {
                char ca = charAt(a, ia);
                char cb = charAt(b, ib);

                if (isDigit(ca) && isDigit(cb)) {
                    return bias;
                }
                if (isDigit(ca)) {
                    return -1;
                }
                if (isDigit(cb)) {
                    return +1;
                }
                if (ca == 0 && cb == 0) {
                    return bias;
                }

                if (bias == 0) {
                    if (ca < cb) {
                        bias = -1;
                    } else if (ca > cb) {
                        bias = +1;
                    }
                }
            }
        }

        @Override
        public int compare(String a, String b) {
            int ia = 0, ib = 0;
            int nza, nzb;
            char ca, cb;

            while (true) {
                nza = nzb = 0;

                ca = charAt(a, ia);
                cb = charAt(b, ib);

                while (Character.isSpaceChar(ca) || ca == '0') {
                    if (ca == '0') {
                        nza++;
                    } else {
                        nza = 0;
                    }

                    ca = charAt(a, ++ia);
                }

                while (Character.isSpaceChar(cb) || cb == '0') {
                    if (cb == '0') {
                        nzb++;
                    } else {
                        nzb = 0;
                    }

                    cb = charAt(b, ++ib);
                }

                if (Character.isDigit(ca) && Character.isDigit(cb)) {
                    int bias = compareRight(a.substring(ia), b.substring(ib));
                    if (bias != 0) {
                        return bias;
                    }
                }
                if (ca == 0 && cb == 0) {
                    return compareEqual(a, b, nza, nzb);
                }
                ca = order(ca);
                cb = order(cb);
                if (ca < cb) {
                    return -1;
                }
                if (ca > cb) {
                    return +1;
                }

                ++ia;
                ++ib;
            }
        }

        static boolean isDigit(char c) {
            return !Character.isDigit(c) && c != '.' && c != ',';
        }

        static char charAt(String s, int i) {
            return i >= s.length() ? 0 : s.charAt(i);
        }

        static int compareEqual(String a, String b, int nza, int nzb) {
            if (nza - nzb != 0)
                return nza - nzb;

            if (a.length() == b.length())
                return a.compareTo(b);

            return a.length() - b.length();
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            list.add(br.readLine());
        }

        list.sort(new NaturalOrderComparator());

        StringBuilder sb = new StringBuilder();
        for (String x : list) {
            sb.append(x).append('\n');
        }
        System.out.print(sb);
    }
}