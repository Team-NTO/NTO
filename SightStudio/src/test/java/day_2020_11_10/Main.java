package day_2020_11_10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// feature/sdm-2020-11-10
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Main m = new Main();

        List<String> list = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            list.add(br.readLine());
        }

        System.out.print(m.solution(list));
    }

    @Test
    void test() {
        Assertions.assertEquals(solution(Arrays.asList(
                "1 2 0", "1 1 2", "0 0 2"
        )), "W");

        Assertions.assertEquals(solution(Arrays.asList(
                "1 2 0", "0 1 0", "0 0 0"
        )), "L");
    }

    /**
     * 알파 틱텍토
     * https://www.acmicpc.net/problem/16571
     */
    private String solution(List<String> input) {
        int[][] map = new int[][]{
            Arrays.stream(input.get(0).split(" ")).mapToInt(Integer::parseInt).toArray(),
            Arrays.stream(input.get(1).split(" ")).mapToInt(Integer::parseInt).toArray(),
            Arrays.stream(input.get(2).split(" ")).mapToInt(Integer::parseInt).toArray()
        };

        boolean[][] visited = new boolean[3][3];

        List<int[]> remainPoints = new ArrayList<>(27);
        int curUser = getCurrentTurnUser(map, remainPoints, visited);
        GAME curUserResult = GAME.LOSE;

        for (int i = 0; i < remainPoints.size(); i++) {
            dfs(curUser, visited, remainPoints, i, curUserResult);
        }

        return curUserResult.getFlag();
    }

    private void dfs(int curUser, boolean[][] visited, List<int[]> remainPoints, int i, GAME curUserResult) {

    }

    private int getCurrentTurnUser(int[][] map, List<int[]> remainPoints, boolean[][] visited) {
        int OCnt = 0;
        int XCnt = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(map[i][j] == 1) { XCnt++; visited[i][j] = true; }
                if(map[i][j] == 2) { OCnt++; visited[i][j] = true; }
                if(map[i][j] == 0) { remainPoints.add(new int[] { i, j }); }
            }
        }
        return OCnt > XCnt ? 1 : 2;
    }
}

enum GAME {
    WIN("W"), DRAW("D"), LOSE("L");

    private final String flag;
    GAME(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }
}