package day_2020_11_09;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// feature/sdm-2020-11-09
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Main m = new Main();
        System.out.print(m.solution(br.readLine()));
    }

    /**
     * 오늘 강호는 돌을 이용해 재미있는 게임을 하려고 한다.
     *
     * 먼저, 돌 세개는 그룹으로 나누어져 있으며 각각의 그룹에는 돌이 A, B, C개가 있다. 강호는 모든 그룹에 있는 돌의 개수를 같게 만들려고 한다.
     * 강호는 돌을 단계별로 움직이며, 각 단계는 다음과 같이 이루어져 있다.
     *
     * 크기가 같지 않은 두 그룹을 고른다.
     * 그 다음, 돌의 개수가 작은 쪽을 X, 큰 쪽을 Y라고 정한다.
     * 그 다음, X에 있는 돌의 개수를 X+X개로, Y에 있는 돌의 개수를 Y-X개로 만든다.
     *
     * A, B, C가 주어졌을 때, 강호가 돌을 같은 개수로 만들 수 있으면 1을, 아니면 0을 출력하는 프로그램을 작성하시오.
     *
     * https://www.acmicpc.net/problem/12886
     * 골드 5
     *
     * 1. 3으로 안나눠 떨어지면 아예 안됨
     * 2.
     */
    @Test
    void test() {
        Assertions.assertEquals(solution("10 15 35"), 1);
        Assertions.assertEquals(solution("1 1 2"), 0);
    }

    /**
     * 작은 쪽을 X, 큰 쪽을 Y
     *
     */
    private int solution(String input) {
        int[] temp = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
        boolean[][] visited = new boolean[1501][1501];
        
        final int A = temp[0];
        final int B = temp[1];
        final int C = temp[2];
        final int SUM = A + B + C;

        if(SUM%3 != 0) return 0;

        dfs(A, B, SUM, visited);

        return visited[SUM/3][SUM/3] ? 1 : 0;
    }

    private void dfs(int a, int b, int sum, boolean[][] visited) {

        if(visited[a][b]) return;
        visited[a][b] = true;
        
        int[] rock = { a, b, sum - (a+b) };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(rock[i] < rock[j]) {

                    int[] temp = { a, b, sum - (a+b) };

                    temp[i] += rock[i]; // X + X
                    temp[j] -= rock[i]; // Y - X
                    dfs(temp[0], temp[1], sum, visited);
                }
            }
        }
    }
}
