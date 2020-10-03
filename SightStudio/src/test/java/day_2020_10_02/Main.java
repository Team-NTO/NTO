package day_2020_10_02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// feature/sdm-2020-10-02
public class Main {

    // https://www.acmicpc.net/problem/15961

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Main m = new Main();

        String firstStr = br.readLine();
        int[]  first    = Arrays.stream(firstStr.split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Integer> nList = new ArrayList<>(first[0]);

        for (int i = 0; i < first[0]; i++) {
            nList.add(Integer.parseInt(br.readLine()));
        }

        System.out.println(m.solution(first, nList));
    }

    /**
     * line 1 : N [접시 수], d [초밥 가지 수], k [먹은 접시 수], C [쿠폰번호]
     * line 2 : row N개 => 초밥 종류 번호
     *
     * 1. 벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 제공
     * 2. 각 고객에게 초밥의 종류 하나가 쓰인 쿠폰을 발행하고,
     *   1번 행사에 참가할 경우 이 쿠폰에 적혀진 종류의 초밥 하나를 추가로 무료로 제공한다.
     *   만약 이 번호에 적혀진 초밥이 현재 벨트 위에 없을 경우, 요리사가 새로 만들어 손님에게 제공한다.
     *
     * 3.
     */
    @Test
    void test() {

        // given
        String firstStr = "8 30 4 30";
        int[] first     = Arrays.stream(firstStr.split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> nList = Stream.of("7", "9", "7", "30", "2", "7", "9", "25")
                                    .map(Integer::parseInt)
                                    .collect(Collectors.toList());

        // when
        int result = solution(first, nList);

        // then
        Assertions.assertEquals(5 , result);
    }

    @Test
    void test2() {

        // given
        String firstStr = "8 50 4 7";
        int[] first     = Arrays.stream(firstStr.split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> nList = Stream.of("2", "7", "9", "25", "7", "9", "7", "30")
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // when
        int result = solution(first, nList);

        // then
        Assertions.assertEquals(4 , result);
    }

    private int solution(int[] first, List<Integer> nList) {

        int N = first[0];
        int d = first[1];
        int k = first[2];
        int c = first[3];

        int fromIdx  = 0;
        int toIdx    = k-1;
        int[] mark   = new int[3001];
        int result   = 0;
        int initDistinctCnt = 0;

        // [0] init
        Deque<Integer> deque = new LinkedList<>();
        for (int i = fromIdx; i <= toIdx; i++) {
            int val = nList.get(i);
            deque.add(val);

            if(mark[val] == 0) {
                initDistinctCnt++;
            }
            mark[val]++;
        }
        result = Math.min(deque.size(), initDistinctCnt);

        // init move
        fromIdx++;
        toIdx++;

        int distinctCnt = initDistinctCnt;
        int limit = nList.size() + k - 1;

        while(toIdx < limit) {
            int distinctWithC = 0;

            // 뒷 부분 제거
            int removeIdx = deque.pollFirst();
            mark[removeIdx]--;
            if(mark[removeIdx] <= 0) {
                distinctCnt--;
            }

            // 앞 부분 추가
            int next = nList.get(toIdx % nList.size());
            if(mark[next] <= 0) {
                distinctCnt++;
            }
            deque.offerLast(next);
            mark[next]++;

            // 쿠폰
            distinctWithC = mark[c] == 0 ? distinctCnt + 1 : distinctCnt;

            if(distinctWithC > result) {
                result = distinctWithC;
            }

            fromIdx++;
            toIdx++;
        }
        return result;
    }
}
