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
@Deprecated
public class Main_Deprecated {

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
        String first  = "5 3 10";           // 학생 수 | 친구 관계 수 | 가지고 있는 돈
        String second = "10 10 20 20 30";   // 각 학생들의 친구비

        List<String> list = Arrays.asList("1 3", "3 4", "4 5");

        assertEquals("Oh no", solution(first, second, list));
    }

    /**
     * 타임 아웃.. 파기
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Main_Deprecated m = new Main_Deprecated();

        String first  = br.readLine();
        String second = br.readLine();

        int cnt = Integer.parseInt(first.split(" ")[2]);
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
    private String solution(String first, String second, List<String> list) {
        int[]  firstArr = Arrays.stream(first.split(" ")).mapToInt(Integer::parseInt).toArray();
        int[]  feeArr   = Arrays.stream(second.split(" ")).mapToInt(Integer::parseInt).toArray();

        int studentCnt = firstArr[0]; // 학생 수
        int budget     = firstArr[2]; // 가지고 있는 돈
        DP = new int[studentCnt+1];
        Arrays.fill(DP, -1);

        int[] disjointSet = initDisjointSet(list, studentCnt);
        boolean[] visited = new boolean[studentCnt+1];

        for (int i = 1; i <= studentCnt; i++) {
            union(i, disjointSet, feeArr, visited);
        }

        int totalFee = getTotalFee(disjointSet, feeArr);
        return totalFee > budget ? "Oh no" : String.valueOf(totalFee);
    }

    private void union(int now, int[] disjointSet, int[] feeArr, boolean[] visited) {
        int pairIdx = disjointSet[now];

        // 기존 값이 더 적음
        if(feeArr[pairIdx-1] > feeArr[now-1]) {
            int rootIdx = find(pairIdx, disjointSet);
            disjointSet[rootIdx] = now;
            disjointSet[now]     = now; // root

            DP[rootIdx] = now;
            DP[now] = now;
        } else {
            int rootIdx = find(now, disjointSet);
            disjointSet[pairIdx] = rootIdx;
            disjointSet[now]     = rootIdx;

            DP[pairIdx] = rootIdx;
            DP[now] = rootIdx;
        }
    }

    private int find(int now, int[] disjointSet) {
        // 루트 노드 일때 반환
        if(now == disjointSet[now]) {
            return now;
        }

        if(DP[now] != -1) {
            return DP[now];
        }

        return find(disjointSet[now], disjointSet);
    }

    private int getTotalFee(int[] disjointSet, int[] feeArr) {
        int totalFee = 0;
        for (int i = 1; i < disjointSet.length; i++) {

            // 1. root case 일때
            if (disjointSet[i] == i) {
                totalFee += feeArr[i-1];
            }
        }
        return totalFee;
    }

    private int[] initDisjointSet(List<String> list, int studentCnt) {
        int[] result = new int[studentCnt+1];

        for (int i = 1; i <= studentCnt; i++) {
            result[i] = i;
        }
        
        for (String subset : list) {
            String[] temp = subset.split(" ");
            int from = Integer.parseInt(temp[0]);
            int   to = Integer.parseInt(temp[1]);

            result[from] = to;
        }
        return result;
    }
}