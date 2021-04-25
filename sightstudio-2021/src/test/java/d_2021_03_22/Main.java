package d_2021_03_22;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

    }

    /**
     * 백준 1082 방번호
     */
    private String solution(final int N, final List<Integer> costList, final int money) {
        List<Integer> result = new ArrayList<>(costList.size());

        int  curMoney = money;
        int  minIdx   = 0;
        Integer min   = costList.get(minIdx);

        // [1] 비용이 작으면서 가장 큰 숫자를 찾음
        for (int i = 1; i < N; i++) {
            Integer cost = costList.get(i);

            if(min >= cost) {
                min = cost;
                minIdx = i;
            }
        }

        // 자리수 기록
        int position = 0;
        while(curMoney >= min) {
            result.add(minIdx);
            curMoney -= min;
            position++;
        }

        for (int i = 0; i < position; i++) {
            for (int j = N - 1; j >= 0; j--) {
                int cost = costList.get(j);

                // 바로 다음 큰값 가져오기
                if(cost <= curMoney + min) {
                    result.add(minIdx);
                    curMoney = curMoney + min - cost;
                    break;
                }

            }
        }



        return "";
    }

    @Test
    void 예제_입력_1() {

        // given
        final int N = 3;
        final List<Integer> list = Arrays.asList(6, 7, 8);
        final int money = 21;

        // when
        String solution = solution(N, list, money);

        // then
        Assertions.assertEquals("210", solution);
    }
}
