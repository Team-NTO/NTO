package day_2020_10_06;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Main {

    @Test
    void test() {
        int N = 4;
        List<String> list = Arrays.asList(
                "0 1 2 3",
                "4 0 5 6",
                "7 1 0 2",
                "3 4 5 0"
        );

        assertEquals(0, solution(N, list));
    }

    @Test
    void test2() {
        int N = 6;
        List<String> list = Arrays.asList(
                "0 1 2 3 4 5",
                "1 0 2 3 4 5",
                "1 2 0 3 4 5",
                "1 2 3 0 4 5",
                "1 2 3 4 0 5",
                "1 2 3 4 5 0"
        );

        assertEquals(2, solution(N, list));
    }

    @Test
    void test3() {
        int N = 8;
        List<String> list = Arrays.asList(
                "0 5 4 5 4 5 4 5",
                "4 0 5 1 2 3 4 5",
                "9 8 0 1 2 3 1 2",
                "9 9 9 0 9 9 9 9",
                "1 1 1 1 0 1 1 1",
                "8 7 6 5 4 0 3 2",
                "9 1 9 1 9 1 0 9",
                "6 5 4 3 2 1 9 0"
        );

        assertEquals(1, solution(N, list));
    }

    /**
     * https://www.acmicpc.net/problem/14889
     * 스타트와 링크
     *
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Main m = new Main();

        int N = Integer.parseInt(br.readLine());
        List<String> list = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            list.add(br.readLine());
        }

        System.out.print(m.solution(N, list));
    }

    private int solution(int N, List<String> list) {
        int[] min = new int[] {999999};
        int[][] table = getInputAsMap(list);
        List<Integer> team = new ArrayList<>(N/2);

        backtracking(team, N, 0, table, min);
        return min[0];
    }

    private void backtracking(List<Integer> team, int N, int now, int[][] table, int[] min) {

        // 기저 사례 1. 팀이 완성됫을 때
        if(team.size() >= N/2) {
            int diff = calcBetweenTeam(team, N, table);

            if(min[0] > diff) {
                min[0] = diff;
            }
            return;
        }

        for (int i = now; i < N; i++) {
            if(team.contains(i)) continue;
            team.add(i);
            backtracking(team, N, i,table, min);
            team.remove(team.size()-1);

            if(min[0] == 0) break;
        }
    }

    private int calcBetweenTeam(List<Integer> team1, int N, int[][] table) {
        List<Integer> team2 = new ArrayList<>(N/2);

        for (int i = 0; i < N; i++) {
            if(!team1.contains(i)) team2.add(i);
        }

        int team1Total = getStat(team1, table);
        int team2Total = getStat(team2, table);

        return Math.abs(team1Total - team2Total);
    }

    private int getStat(List<Integer> team, int[][] table) {
        int stat = 0;
        for (int i = 0; i < team.size(); i++) {
            for (int j = i+1; j < team.size(); j++) {
                stat+= table[team.get(i)][team.get(j)];
                stat+= table[team.get(j)][team.get(i)];
            }
        }
        return stat;
    }

    private int[][] getInputAsMap(List<String> list) {
        int[][] map = new int[list.size()][list.size()];

        for (int i = 0; i < list.size(); i++) {
            String[] row = list .get(i).split(" ");
            int[] rowToInt = new int[row.length];

            for (int j = 0; j < rowToInt.length; j++) {
                rowToInt[j] = Integer.parseInt(row[j]);
            }
            map[i] = rowToInt;
        }

        return map;
    }
}
