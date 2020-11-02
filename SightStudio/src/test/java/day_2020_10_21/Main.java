package day_2020_10_21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//feature/sdm-2020-10-21
public class Main {

    // https://www.acmicpc.net/problem/2457
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Main m = new Main();

        int flowerCnt = Integer.parseInt(br.readLine());
        List<String> flowers = new ArrayList<>(flowerCnt);

        for (int i = 0; i < flowerCnt; i++) {
            flowers.add(br.readLine());
        }

        System.out.print(m.solution(flowers));
    }

    @Test
    void test() {
        List<String> flowers = Arrays.asList(
                "1 1 5 31",
                "1 1 6 30",
                "5 15 8 31",
                "6 10 12 10"
        );

        Assertions.assertEquals(2, solution(flowers));
    }

    /**
     * - 문제 : https://www.acmicpc.net/problem/2457
     *
     * - 백준 / 골드 4 / 공주님의 정원
     *
     * - 조건
     * 공주가 가장 좋아하는 계절인 3월 1일부터 11월 30일까지 매일 꽃이 한 가지 이상 피어 있도록 한다.
     * 정원이 넓지 않으므로 정원에 심는 꽃들의 수를 가능한 적게 한다.
     */
    int solution(List<String> flowerStr) {

        List<Flower> flowers = flowerStr.stream()
                .map(Flower::new)
                .sorted((f1, f2) -> f1.start - f2.start)
                .collect(Collectors.toList());

        int now = 301;
        int max = 0;
        int cnt = 0;
        int idx = 0;
        for(int i = 0 ; i < flowers.size(); i++) {

            // [1] 11월 30일 이후
            if(now > 1130) {
                break;
            }

            // 오늘을 포함한 꽃들 중 가장 오래심을 수 있는 꽃을 찾는다
            if(max < flowers.get(i).end && flowers.get(i).start <= now) {
                idx = i;
                max = flowers.get(i).end;
            }

            // [2] 꽃이 오늘을 포함하지 않을 경우
            if(flowers.get(i).start > now && max != 0) {
                now = flowers.get(idx).end;
                cnt++;
                i--;
                max = 0;
            }

            // [3] 모든 꽃 확인
            else if(max != 0 && i == flowers.size() -1) {
                now = flowers.get(idx).end;
                cnt++;
            }

            else if(max == 0) {
                cnt = 0;
                break;
            }

        }

        if(now <= 1130) {
            return 0;
        }
        return cnt;
    }
}

class Flower {
    int start;
    int end;
    public Flower(String flowerStr) {
        String[] temp = flowerStr.split(" ");

        this.start = Integer.parseInt(temp[0]) * 100 + Integer.parseInt(temp[1]);
        this.end   = Integer.parseInt(temp[2]) * 100 + Integer.parseInt(temp[3]);
    }
}