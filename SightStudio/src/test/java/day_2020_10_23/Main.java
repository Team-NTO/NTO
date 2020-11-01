package day_2020_10_23;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//feature/sdm-2020-10-21
public class Main {

    // https://www.acmicpc.net/problem/1516
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Main m = new Main();

        int techCnt = Integer.parseInt(br.readLine());
        List<String> techs = new ArrayList<>(techCnt);

        for (int i = 0; i < techCnt; i++) {
            techs.add(br.readLine());
        }
        System.out.print(m.solution(techs));
    }

    @Test
    void test() {
        List<String> techs = Arrays.asList(
                "10 -1"     ,
                "10 1 -1"   ,
                "4 1 -1"    ,
                "4 3 1 -1"  ,
                "3 3 -1"
        );

//        "1 | 10 | -1"     ,
//        "2 | 10 |  1 -1"   ,
//        "3 | 4  |  1 -1"    ,
//        "4 | 4  |  3 1 -1"  ,
//        "5 | 3  |  3 -1"

        Assertions.assertEquals("10\n20\n14\n18\n17", solution(techs));
    }


    /**
     * - 문제 : https://www.acmicpc.net/problem/1516
     *
     * - 백준 / 골드 3 / 게임 개발
     * - 강의 수강과 마찬가지로 위상정렬 문제인듯
     */
    private String solution(List<String> techs) {

        int[] indegree = new int[techs.size()];
        int[] cost     = new int[techs.size()];
        int[] result   = new int[techs.size()];

        int cnt = techs.size();
        List<List<Integer>> adjList = new ArrayList<>(techs.size());

        for (int i = 0; i < techs.size(); i++) {
            adjList.add(new ArrayList<>());
        }
        
        // init indegree && cost && adjList
        for (int i = 0; i < techs.size(); i++) {
            int[] rows = Arrays.stream(techs.get(i).split(" ")).mapToInt(Integer::parseInt).toArray();

            // [1] 가격
            cost[i] = rows[0];

            // [2] indegree
            for (int j = 1; j < rows.length; j++) {
                if(rows[j] == -1) break;
                indegree[rows[j]]++;
                adjList.get(i).add(j);
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        List<Integer> atomIndegree = getAtomIndegrees(indegree);
        atomIndegree.forEach( idx-> {
            queue.offer(idx);
            result[idx] = cost[idx];
        } );

        while(!queue.isEmpty()) {
            int target = queue.poll();

            for (int adj : adjList.get(target)) {
                if(--indegree[adj] == 0) {
                    queue.offer(adj);
                    result[adj] = result[target] + cost[adj];
                }
            }
        }

        return "";
    }

    private List<Integer> getAtomIndegrees(int[] indegree) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                result.add(i);
            }
        }
        return result;
    }
}