package _2020_10_23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final long max = 1000000000000L;
    private static long calculate(int n, long height, long[] a, long[] b) {
        int mi = n / 2;
        int left = mi - 1;
        int right = mi + 1;

        long blockCount = Math.abs(a[mi] - height) + Math.abs(b[mi] - height);
        while (0 <= left && right < n) {
            height++;

            blockCount += (Math.abs(a[left] - height) + Math.abs(b[left] - height));
            blockCount += (Math.abs(a[right] - height) + Math.abs(b[right] - height));

            left--;
            right++;
        }

        return blockCount;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] map = new long[n];
        long[] map2 = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            map[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            map2[i] = Long.parseLong(st.nextToken());
        }

        long min_low  = 0;
        long max_low = max - n / 2;
        long min_block = calculate(n, min_low, map, map2);
        long max_block = calculate(n, max_low, map, map2);

        while (min_low <= max_low) {
            long mid = (min_low + max_low) / 2;

            if (min_block < max_block) {
                max_low = mid - 1;
                max_block = calculate(n, max_low, map, map2);
            } else {
                min_low = mid + 1;
                min_block = calculate(n, min_low, map, map2);
            }
        }

        System.out.println(Math.min(min_block, max_block));
    }
}
