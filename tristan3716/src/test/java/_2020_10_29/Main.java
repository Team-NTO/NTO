package _2020_10_29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        if (n % 2 != 0) {
            System.out.println(0);
        } else {
            long[] t = new long[5001];
            t[0] = 1;
            for (int i = 1; i < 5001; i++) {
                for (int j = 0; j < i; j++) {
                    t[i] += ((t[j] % 987654321L) * t[i - 1- j]) % 987654321L;
                    t[i] %= 987654321L;
                }
            }
            n /= 2;
            System.out.println(t[n]);
        }
    }
}