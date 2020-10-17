package day_2020_10_13;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

// feature/sdm-2020-10-13
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Main m = new Main();

        String memberCntStr = br.readLine();
        List<String> list = new LinkedList<>();

        while(true) {
            String line = br.readLine();
            if(line.contains("-1")) {
                break;
            }
            list.add(line);
        }
        System.out.print(m.solution(memberCntStr, list));
    }

    /**
     * 백준 / 골드 5 / 회장뽑기
     * https://www.acmicpc.net/problem/2660
     *
     * 위상정렬 ?   -> 근데 이거 무방향 그래프임, 시작점 없음 | 불가능
     * 플로이드 와샬 ->
     */
    @Test
    void test() {
        String memberCntStr = "5";
        List<String> relList = Arrays.asList(
                "1 2", "2 3", "3 4",
                "4 5", "2 4", "5 3"
        );

        Assertions.assertEquals("2 3\n2 3 4", solution(memberCntStr, relList));
    }

    /**
     *  어느 회원이 다른 모든 회원과 친구이면,  이 회원의 점수는 1점.
     *  어느 회원의 다른 모든 회원과 친구이거나, 친구의 친구이면 2점
     *
     *  회원 수 중에서 제일 점수가 낮은 사람 : 회장
     *  회장의 점수와 회장이 될 수 있는 모든 사람을 찾는 프로그램을 작성
     */
    static final int INFINITE = 10000000;
    static int  memberCnt = 0;
    private String solution(String memberCntStr, List<String> relList) {
        memberCnt = Integer.parseInt(memberCntStr);
        final int[][] MATRIX = new int[memberCnt+1][memberCnt+1];

        initMatrix(MATRIX);
        initRel(MATRIX, relList);

        // [1] 현재 값과 경유하는 값중 뭐가 더 최소 비용인지 비교
        for (int k = 1; k <= memberCnt ; k++) {
            for (int i = 1; i <= memberCnt ; i++) {
                for (int j = 1; j <= memberCnt; j++) {
                    MATRIX[i][j] = Math.min( MATRIX[i][j], MATRIX[i][k] + MATRIX[k][j] );
                }
            }
        }

        // [2] 회장 점수 계산
        Map<Integer, Integer> scoreMap = new HashMap<>();
        int minVal = INFINITE;
        for (int i = 1; i <= memberCnt ; i++) {

            // [2 - 1] 노드에서 다른 노드까지 최대 비용 계산
            int maxVal = -INFINITE;
            for (int j = 1; j <= memberCnt ; j++) {
                maxVal = Math.max(maxVal, MATRIX[i][j]);
            }

            // [2 - 2] 가장 먼 노드까지의 최소 비용 계산
            scoreMap.put(i, maxVal);
            minVal = Math.min(minVal, maxVal);
        }

        // [3] 나머지 처리
        for (int i = 1; i <= memberCnt; i++) {

            // 최소 값만 남기고 지움
            if(scoreMap.get(i) != null && scoreMap.get(i) != minVal) {
                scoreMap.remove(i);
            }
        }

        return new ResultWrapper(minVal, scoreMap).toString();
    }

    private void initMatrix(int[][] matrix) {
        for (int i = 1; i <= memberCnt; i++) {
            for (int j = 1; j <= memberCnt ; j++) {
                if(i == j)
                    matrix[i][j] = 0;
                else
                    matrix[i][j] = INFINITE;
            }
        }
    }

    private void initRel(int[][] MATRIX, List<String> relList) {
        for (String relation : relList) {
            String[] temp = relation.split(" ");

            int from = Integer.parseInt(temp[0]);
            int   to = Integer.parseInt(temp[1]);

            MATRIX[from][to] = 1;  // 양방향 가중치 1로 설정
            MATRIX[to][from] = 1;
        }
    }
}

class ResultWrapper {

    int minScore;
    Map<Integer, Integer> scoreMap;  // 회장 리스트

    public ResultWrapper(int minScore, Map<Integer, Integer> map) {
        this.minScore = minScore;
        this.scoreMap = map;
    }

    @Override
    public String toString() {

        String hubo = scoreMap.keySet().stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        return String.format(
                "%d %d\n%s", minScore, scoreMap.size(), hubo
        );
    }
}