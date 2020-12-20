package day_2020_11_18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// feature/sdm-2020-11-18
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Arrays;

// feature/sdm-2020-11-18
public class Main {

    /**
     * 2020/11/18 백준 1445 택배
     * https://www.acmicpc.net/problem/1719
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());    // 마을의 수
        int C = Integer.parseInt(st.nextToken());    // 트럭의 용량

        int M = Integer.parseInt(br.readLine());     // 보낼 박스 정보의 개수
        Delivery[] deliveries = new Delivery[M + 1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int boxNum = Integer.parseInt(st.nextToken());

            deliveries[i] = new Delivery(start, end, boxNum);
        }

        // 택배를 받는 마을을 기준으로 오름차순 정렬을 하되, 받는 마을이 같을 경우
        // 보내는 마을을 기준으로 오름차순 정렬
        Arrays.sort(deliveries, 1, M + 1);

        // 각 마을당 보낼 수 있는 최대 용량을 설정한다.
        int[] weight = new int[N + 1];
        for (int i = 1; i < N; i++) {
            weight[i] = C;
        }

        int ans = 0;
        for (int i = 1; i <= M; i++) {
            Delivery d = deliveries[i];

            int maxBoxNum = Integer.MAX_VALUE;

            // 보내는 마을과 받는 마을 사이의 경로 마을 중에서 보낼 수 있는 최대 한도를 구함
            for (int j = d.start; j < d.end; j++) {
                maxBoxNum = Math.min(maxBoxNum, weight[j]);
            }

            // 위에서 구한 보낼 수 있는 최대 한도가 현재 보내려는 택배의 개수보다 크다면,
            // 보내는 마을과 받는 마을 사이의 경로 마을 모두 용량에서 현재 보내려는 택배의 개수를 뺀다.
            if (maxBoxNum >= d.boxNum) {
                for (int j = d.start; j < d.end; j++) {
                    weight[j] -= d.boxNum;
                }
                ans += d.boxNum;
            } else {
                // 보낼 수 있는 최대 한도보다 현재 보내려는 택배의 개수가 클 경우,
                // 현재 보내려는 택배의 개수가 아닌 위에서 구한 최대 한도를 기준으로 함
                for (int j = d.start; j < d.end; j++) {
                    weight[j] -= maxBoxNum;
                }
                ans += maxBoxNum;
            }
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}


class Delivery implements Comparable<Delivery> {
    int start; // 보내는 마을
    int end; // 받는 마을
    int boxNum; // 박스의 개수

    Delivery(int start, int end, int boxNum) {
        this.start = start;
        this.end = end;
        this.boxNum = boxNum;
    }

    @Override
    public int compareTo(Delivery arg0) {
        if (end == arg0.end) {
            return start - arg0.start;
        }
        return end - arg0.end;
    }
}

