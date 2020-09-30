package day_2020_09_28;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main m = new Main();

        System.out.print(
                m.solution(sc.nextInt())
        );
    }

    @Test
    void test() {
        // given
        int N = 4;

        // when
        int result = solution(N);

        // then
        assertEquals(43, result);
    }

    /**
     * 문제  : https://www.acmicpc.net/problem/1563
     * 난이도 : 골드 4
     * 개근상 못받는 경우 : 2번 지각, 결석 연속 3번
     *
     * O를 출석, L을 지각, A를 결석
     *
     * N : 한 학기 일수
     * N < 1,000
     *
     * DP : 탑다운 방식으로 풀었습니다.
     */

    static final int MAX = 1001;
    static final int MOD = 1_000_000;
    int solution(int N) {
        int[][][][] DP = new int[MAX+1][MAX+1][3][4]; // 메모제이션 : [day][출석][지각][결석]

        return combination(N, DP,0, 0, 0, 0) % MOD;
    }

    private int combination(int n, int[][][][] DP, int day, int attendance, int late, int absent) {

        // 기저 사례 [1] : 지각 2회 && 결석 3회
        if(late >= 2 || absent >= 3) {
            return 0;
        }

        // 기저 사례 [2] : n까지 개근조건 유지
        if(day == n) {
            return 1;
        }
        int memo = DP[day][attendance][late][absent];
        if(memo == 0) {
            DP[day][attendance][late][absent] =
                (
                        combination(n, DP,day + 1, attendance + 1, late, 0) % MOD+
                        combination(n, DP,day + 1, attendance, late + 1, 0)       % MOD +
                        combination(n, DP,day + 1, attendance, late, absent + 1)      % MOD
                );
        }

        // 기저 사례 : [3] 위의 case 경우의 수
        return DP[day][attendance][late][absent];
    }
}
