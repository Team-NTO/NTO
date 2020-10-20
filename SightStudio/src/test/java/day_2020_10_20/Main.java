package day_2020_10_20;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

//feature/sdm-2020-10-20
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Main m = new Main();

        int perfectProd = Integer.parseInt(br.readLine());
        int relationCnt = Integer.parseInt(br.readLine());

        List<String> relations = new ArrayList<>(relationCnt);
        for (int i = 0; i < relationCnt; i++) {
            relations.add(br.readLine());
        }

        Map<Integer, Integer> solution = m.solution(perfectProd, relations);

        System.out.println(solutionToFormat(solution));
    }

    public static String solutionToFormat(Map<Integer, Integer> solution) {
        return solution.keySet()
                .stream()
                .sorted().map(key -> key + " " + solution.get(key)).collect(Collectors.joining("\n"));
    }

    @Test
    void test() {
        int perfectProd = 7;
        List<String> relations = Arrays.asList(
                "5 1 2",
                "5 2 2",
                "7 5 2",
                "6 5 2",
                "6 3 3",
                "6 4 4",
                "7 6 3",
                "7 4 5"
        );

        Map<Integer, Integer> resultMap = new HashMap<>();
        resultMap.put(1, 16);
        resultMap.put(2, 16);
        resultMap.put(3, 9);
        resultMap.put(4, 17);

        assertEquals(resultMap, solution(perfectProd, relations));
    }

    /**
     * 백준 / 골드 2 / 2637 / 장난감 조립
     *
     * 기본 부품과 중간 부품을 구별해야함 -> indegree가 0인걸 찾음
     * @return
     */
    private Map<Integer, Integer> solution(int perfectProd, List<String> relations) {
        int[] indegree    = new int[perfectProd+1];                     // indegree 기록
        int[][] costGraph = new int[perfectProd+1][perfectProd+1];      // 가격 기록
        int[][] totalCost = new int[perfectProd+1][perfectProd+1];
        List<List<Integer>> adjList = new ArrayList<>();                // 관계 기록 [인접 리스트]
        Map<Integer, Integer> cost  = new HashMap<>();                  // 기본 부품 개수 기록

        init(indegree, costGraph, relations, adjList);
        List<Integer> atomProds = findAtomProds(indegree);

        Queue<Integer> queue = new LinkedList<>();
        atomProds.forEach( atomProd -> {
            queue.offer(atomProd);
            totalCost[atomProd][atomProd] = 1;
        });

        while(!queue.isEmpty()) {
            int prod = queue.poll();

            for(int superProd : adjList.get(prod)) {
                for (int i = 1; i < perfectProd; i++) {
                    if(totalCost[perfectProd][i] == 0) continue;
                    totalCost[superProd][i] += costGraph[superProd][prod] * totalCost[prod][i];
                }

                if(--indegree[superProd] == 0) {
                    queue.offer(superProd);
                }
            }
        }

        for (int i = 1; i < perfectProd; i++) {
            if(totalCost[perfectProd][i] != 0) {
                cost.put(i, totalCost[perfectProd][i]);
            }
        }

        return cost;
    }

    // 기본 부품 인덱스 찾기
    private List<Integer> findAtomProds(int[] indegree) {
        List<Integer> atomProds = new ArrayList<>(indegree.length);
        for (int i = 1; i < indegree.length; i++) {
            if(indegree[i] == 0) atomProds.add(i);
        }
        return atomProds;
    }


    private void init(int[] indegree, int[][] costGraph, List<String> relations, List<List<Integer>> adjList) {

        indegree[0] = -1; // 안쓰는 인덱스

        // ajdList init
        for (int i = 0; i < indegree.length; i++) {
            adjList.add(new ArrayList<>());
        }

        // cost init
        for (String relation: relations) {
            String[] temp = relation.split(" ");

            int superProd = Integer.parseInt(temp[0]);
            int subProd   = Integer.parseInt(temp[1]);
            int weight    = Integer.parseInt(temp[2]);

            indegree[superProd]++;
            costGraph[superProd][subProd] = weight;
            adjList.get(subProd).add(superProd);
        }
    }
}