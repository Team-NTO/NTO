package _2020_11_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int[] arr = new int[n+2];
        arr[n+1] = l;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int left = 0;
        int right = 0;

        for(int i = 1; i < n + 2; i++) {
            right = Math.max(right, arr[i] - arr[i-1] + 1);
        }

        while(left <= right) {
            int mid = (left + right) / 2;
            int sum = 0;

            for(int i = 1; i < n + 2; i++)
                if(arr[i] > arr[i - 1])
                    sum += (arr[i] - arr[i - 1] - 1)/mid;

            if(sum > m)
                left = mid+1;
            else
                right = mid-1;
        }

        System.out.println(left);
    }

}