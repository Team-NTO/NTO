package day_2020_10_19;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

//feature/sdm-2020-10-19
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Main m = new Main();

        int tcCnt = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= tcCnt ; tc++)
        {
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int N = temp[0]; // 건물 개수
            int K = temp[1]; // 건설 규칙 개수

            int[] buildTime = Arrays.stream(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
            List<String> rules = new ArrayList<>(K);

            for (int i = 0; i < K ; i++) rules.add(br.readLine());

            int targetBuilding = Integer.parseInt(br.readLine());

            System.out.print(m.solution(N, buildTime, rules, targetBuilding));

            if(tc != tcCnt) {
                System.out.println();
            }
        }
    }

    @Test
    void test() {
        int N = 4;
        int[] buildTime = {0, 10, 1, 100, 10};
        List<String> rules = Arrays.asList(
                "1 2",
                "1 3",
                "2 4",
                "3 4"
        );
        int targetBuilding = 4;

        assertEquals(120, solution(N, buildTime, rules, targetBuilding));
    }

    @Test
    void test2() {
        int N = 8;
        int[] buildTime = {0, 10, 20, 1, 5, 8, 7, 1, 43};
        List<String> rules = Arrays.asList(
                "1 2",
                "1 3",
                "2 4",
                "2 5",
                "3 6",
                "5 7",
                "6 7",
                "7 8"
        );
        int targetBuilding = 7;

        assertEquals(39, solution(N, buildTime, rules, targetBuilding));
    }

    @Test
    void test3() {
        int N = 10;
        int[] buildTime = { 0, 10, 1, 1, 100, 10, 10, 100, 1, 1, 1 };
        List<String> rules = Arrays.asList(
                "1 2",
                "2 3",
                "3 6",
                "4 3",
                "4 7",
                "4 9",
                "5 4",
                "6 9",
                "7 8",
                "8 9",
                "10 7"
        );
        int targetBuilding = 9;

        assertEquals(212, solution(N, buildTime, rules, targetBuilding));
    }
    /**
     * 백준 / 골드 3 / 1005 / ACM Craft
     *
     * 반례
     *  1. 시작 단말 노드들 중 목표 건물이 있을때
     *  2.
     */
    private int solution(int N, int[] buildTime, List<String> rules, int targetBuilding) {
        List<List<Integer>> adjList = new ArrayList<>(N+1);
        int[] indegree = new int[N+1];
        initAdjList(adjList, rules, N, indegree);

        return topologicalSortUntil(adjList, buildTime, indegree, targetBuilding);
    }

    private int topologicalSortUntil(List<List<Integer>> adjList, int[] buildTime, int[] indegree, int targetBuilding) {
        int[]   buildCost = new int[buildTime.length];

        System.arraycopy(buildTime,0 , buildCost, 0, buildTime.length);

        List<Integer> startIdxs = getZeroIndegrees(indegree);
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        startIdxs.forEach(queue::offer);

        while(!queue.isEmpty()) {
            int edgePoints = queue.poll();

            for (int to: adjList.get(edgePoints)) {
                if(--indegree[to] == 0) {
                    queue.offer(to);
                }
                buildCost[to] = Math.max(buildCost[to], buildCost[edgePoints] + buildTime[to]);
            }
        }
        return buildCost[targetBuilding];
    }

    // 제일 가까운 indegree가 0인거 반환
    // 없으면 -1
    private List<Integer> getZeroIndegrees(int[] indegree) {
        List<Integer> zeroIndegrees = new ArrayList<>(indegree.length);
        for (int i = 1 ; i < indegree.length ; i++) {
            if(indegree[i] == 0) zeroIndegrees.add(i);
        }
        return zeroIndegrees;
    }

    private void initAdjList(List<List<Integer>> adjList, List<String> rules, int N, int[] indegree) {

        // 인접 리스트 초기화
        for (int i = 0; i <= N ; i++) {
            adjList.add(new LinkedList<>());
        }

        // rules 초기화
        for (String rule: rules) {
            int[] temp = Arrays.stream(rule.split(" ")).mapToInt(Integer::parseInt).toArray();
            int from = temp[0];
            int   to = temp[1];
            adjList.get(from).add(to);

            indegree[to]++; // 진입차수 추가
        }
    }
}


/*

19
3 2
100 100 100
1 2
2 3
1

3 2
100 100 100
1 2
2 3
2

11 10
10 100 1 1 1 5 8 9 7 1000 10
1 2
2 3
3 4
4 5
5 6
6 7
5 8
8 9
10 11
11 3
3

11 10
10 100 1 1 1 5 8 9 7 1000 10
1 2
2 3
3 4
4 5
5 6
6 7
5 8
8 9
10 11
11 3
4

11 10
10 100 1 1 1 5 8 9 7 1000 10
1 2
2 3
3 4
4 5
5 6
6 7
5 8
8 9
10 11
11 3
6

4 4
10 1 100 10
1 2
1 3
2 4
3 4
4

8 8
10 20 1 5 8 7 1 43
1 2
1 3
2 4
2 5
3 6
5 7
6 7
7 8
7

10 11
10 1 1 100 10 10 100 1 1 1
1 2
2 3
3 6
4 3
4 7
4 9
5 4
6 9
7 8
8 9
10 7
9

5 4
10 1 100 10 1000
5 2
1 3
2 4
3 4
4
7 6
1 100 10 1000 10 1 1
1 2
2 3
3 7
4 5
5 6
6 7
7
7 6
1 10 1 100 1 5 8
1 2
2 3
4 5
5 3
3 6
3 7
3
7 6
1 10 1 100 1 5 8
1 2
2 3
4 5
5 3
3 6
3 7
7
5 3
1 3 10 10 5
1 4
2 3
3 5
2
5 3
1 3 10 10 5
1 4
2 3
3 5
3
5 3
1 3 10 10 5
1 4
2 3
3 5
5
5 3
1 3 10 10 5
1 4
2 3
3 5
4
5 3
1 3 10 10 5
1 4
2 3
3 5
1
2 1
10
5
2 1
2
2 1
10
5
2 1
1

 */



/*
100
200
1011
1012
1018
120
39
212
1011
1012
102
110
3
13
18
11
1
5
15
 */