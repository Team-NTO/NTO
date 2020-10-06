package day_2020_10_08;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// feature/sdm-2020-10-08
public class Main {

    @Test
    void test() {
        String first  = "5 3 20";           // 학생 수 | 친구 관계 수 | 가지고 있는 돈
        String second = "10 10 20 20 30";   // 각 학생들의 친구비

        List<String> list = Arrays.asList("1 3", "2 4", "5 4");

        assertEquals("20", solution(first, second, list));
    }

    @Test
    void test2() {
        String first  = "5 3 10";           // 학생 수 | 친구 관계 수 | 가지고 있는 돈
        String second = "10 10 20 20 30";   // 각 학생들의 친구비

        List<String> list = Arrays.asList("1 3", "2 4", "5 4");

        assertEquals("Oh no", solution(first, second, list));
    }

    @Test
    void test3() {
        String first  = "6 5 10";              // 학생 수 | 친구 관계 수 | 가지고 있는 돈
        String second = "10 10 20 20 30 40";   // 각 학생들의 친구비

        List<String> list = Arrays.asList("1 3", "3 1", "4 5", "2 4", "5 6");

        assertEquals("Oh no", solution(first, second, list));
    }

    @Test
    void test4() {
        String first  = "6 5 10";              // 학생 수 | 친구 관계 수 | 가지고 있는 돈
        String second = "30 40 10 10 20 20";   // 각 학생들의 친구비

        List<String> list = Arrays.asList("1 3", "3 1", "4 5", "2 4", "5 6");

        assertEquals("Oh no", solution(first, second, list));
    }

    @Test
    void test5() {
        String first  = "7 6 60";              // 학생 수 | 친구 관계 수 | 가지고 있는 돈
        String second = "30 40 10 10 20 20 50";   // 각 학생들의 친구비

        List<String> list = Arrays.asList("1 3", "3 1", "4 5", "2 4", "5 6", "1 2");

        assertEquals("60", solution(first, second, list));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Main m = new Main();

        String first  = br.readLine();
        String second = br.readLine();

        int cnt = Integer.parseInt(first.split(" ")[1]);
        List<String> groupList = new ArrayList<>(cnt);

        for (int i = 0; i < cnt; i++) {
            groupList.add(br.readLine());
        }

        System.out.print(
                m.solution(first, second, groupList)
        );
    }

    /**
     * 백준 / 골드 3 / 친구비
     * https://www.acmicpc.net/problem/16562
     *
     */
    static int[] DP;
    private String solution(String first, String second, List<String> groupList) {
        int[]  firstArr = Arrays.stream(first.split(" ")).mapToInt(Integer::parseInt).toArray();
        int[]  feeArr   = Arrays.stream(second.split(" ")).mapToInt(Integer::parseInt).toArray();

        int studentCnt    = firstArr[0]; // 학생 수
        int budget        = firstArr[2]; // 가지고 있는 돈
        int[] disjointSet = new int[studentCnt+1];

        DP = new int[studentCnt+1];
        for (String group: groupList) {
            String[] xy = group.split(" ");
            union(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]), disjointSet, feeArr);
        }

        int totalFee = getTotalFee(disjointSet, feeArr);
        return totalFee > budget ? "Oh no" : String.valueOf(totalFee);
    }

    private void union(int x, int y, int[] disjointSet, int[] feeArr) {
        x = find(x, disjointSet);
        y = find(y, disjointSet);

        if(feeArr[x-1] > feeArr[y-1]) {
            disjointSet[x] = y;
            disjointSet[y] = y;
        } else {
            disjointSet[y] = x;
            disjointSet[x] = x;
        }
    }

    private int find(int now, int[] disjointSet) {

        // 루트 노드 일때 반환
        if(now == disjointSet[now] || disjointSet[now] == 0) {
            return now;
        }

        return find(disjointSet[now], disjointSet);
    }

    private int getTotalFee(int[] disjointSet, int[] feeArr) {
        int totalFee = 0;
        for (int i = 1; i < disjointSet.length; i++) {

            // 1. root case 일때
            if (disjointSet[i] == i || disjointSet[i] == 0) {
                totalFee += feeArr[i-1];
            }
        }
        return totalFee;
    }
}