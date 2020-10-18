package day_2020_10_15;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//feature/sdm-2020-10-15
public class Main {

    /**
     * N = 학생
     * M = 단방향 도로
     * X = x 번 마을
     * <p>
     * 첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 10,000), X가 공백으로 구분되어 입력
     * <p>
     * 두 번째 줄부터 M+1번째 줄까지 i번째 도로의 시작점, 끝점, 그리고 이 도로를 지나는데 필요한 소요시간 Ti
     * [ 시작, 끝, 소요시간 ]
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Main m = new Main();

        int[] first = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int roadCnt = first[1];
        List<String> roadList = new ArrayList<>(roadCnt);

        for (int i = 0; i < roadCnt; i++) {
            roadList.add(br.readLine());
        }

        System.out.print(m.solution(first, roadList));
    }

    @Test
    void test2() {
        int[] info = { 4, 8, 2 };
        List<String> roadList = Arrays.asList(
                "1 2 4",
                "1 3 2",
                "1 4 7",
                "2 1 1",
                "2 3 5",
                "3 1 2",
                "3 4 4",
                "4 2 3"
        );

        Assertions.assertEquals(10, solution(info, roadList));
    }

    /**
     * https://www.acmicpc.net/problem/1238
     * 파티 / 백준 / 골드 3
     *
     * 다익스트라 문제
     */
    static final int INFINITE = 1000*100+1;
    private int solution(int[] info, List<String> roadList) {
        int studentCnt = info[0];
        int partyTown  = info[2];

        int[][] map        = new int[studentCnt+1][studentCnt+1];
        int[][] reverseMap = new int[studentCnt+1][studentCnt+1];

        int[] dist        = new int[studentCnt+1];
        int[] reverseDist = new int[studentCnt+1];

        initMap(map, reverseMap, dist, reverseDist, studentCnt);
        initRoad(map, reverseMap, roadList);

        dijikstra(partyTown,        map,        dist, studentCnt); // 파티 마을에서 각 마을까지
        dijikstra(partyTown, reverseMap, reverseDist, studentCnt); // 각 마을에서 파티까지

        int maxDist = 0;
        for (int i = 1; i <= studentCnt; i++) {
            if(i == partyTown) continue;
            maxDist = Math.max(maxDist, dist[i] + reverseDist[i]);
        }
        return maxDist;
    }

    private void dijikstra(int startIdx, int[][] map, int[] dist, int studentCnt) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(startIdx);
        map[startIdx][startIdx] = 0;
        dist[startIdx]          = 0;

        while(!pq.isEmpty()) {
            int x = pq.poll();

            for (int i = 1; i <= studentCnt; i++) {
                if(dist[i]  > map[x][i] + dist[x]) {
                    dist[i] = map[x][i] + dist[x];
                    pq.offer(i);
                }
            }
        }
    }

    private void initMap(int[][] map, int[][] reverseMap, int[] dist, int[] reverseDist, int studentCnt) {

        for (int i = 1; i <= studentCnt; i++) {
            dist[i]        = INFINITE;
            reverseDist[i] = INFINITE;

            for (int j = 1; j <= studentCnt; j++) {
                map[i][j]        = INFINITE;
                reverseMap[i][j] = INFINITE;
            }
        }
    }

    private void initRoad(int[][] map, int[][] reverseMap, List<String> roadList) {
        for (String road: roadList) {
            int[] roadInfo = Arrays.stream(road.split(" ")).mapToInt(Integer::parseInt).toArray();

            int from   = roadInfo[0];
            int   to   = roadInfo[1];
            int weight = roadInfo[2];

            map[from][to]        = weight;
            reverseMap[to][from] = weight;
        }
    }
}