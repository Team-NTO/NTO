package day_2020_10_14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//feature/sdm-2020-10-14
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Main m = new Main();
        int tcCnt = Integer.parseInt(br.readLine());

        for (int i = 1; i <= tcCnt; i++) {
            int[] firstLine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int boardSize = firstLine[0];
            int mirrorCnt = firstLine[1];

            List<String> mirrors = new ArrayList<>(mirrorCnt);
            for (int j = 0; j < mirrorCnt; j++) {
                mirrors.add(br.readLine());
            }

            int[] startPoint = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(
                    m.solution(boardSize, mirrors, startPoint)
            );
        }
    }

    @Test
    void test() {
        int boardSize    = 2;
        int[] startPoint = {3, 1};
        List<String> mirrors = Arrays.asList(
                "1 1",
                "1 2",
                "2 2"
        );

        Assertions.assertEquals("2 0", solution(boardSize, mirrors, startPoint));
    }

    @Test
    void test2() {
        int boardSize    = 3;
        int[] startPoint = {2, 0};
        List<String> mirrors = Arrays.asList(
                "1 1",
                "1 3",
                "2 2",
                "2 3",
                "3 1",
                "3 2"
        );

        Assertions.assertEquals("0 2", solution(boardSize, mirrors, startPoint));
    }

    /**
     * https://www.acmicpc.net/problem/3709
     * 레이저 빔은 어디로 / 백준 / 골드 5
     *
     * 우향우 거울 : 진입한 방향과는 관계 없이 오른쪽으로 90도를 꺾어 다시 나아간다. ??
     */
    private  String solution(int boardSize, List<String> mirrors, int[] startPoint) {

        // [1] 초기화
        int[][] board       = new int[boardSize+1][boardSize+1];
        boolean[][] visited = new boolean[boardSize+1][boardSize+1];
        initBoardWithMirror(board, mirrors);

        // 방향 계산
        DIRECTION currentDir = getStartDir(startPoint, boardSize);
        int[] now = {startPoint[0], startPoint[1]};

        move(now, currentDir);
        markVisit(now, visited);

        while(true) {

            if(isOnMirror(now, board)) {
                currentDir = moveWithMirror(now, currentDir);
            } else {
                move(now, currentDir);
            }

            if(isEscape(now, boardSize)) {
                break;
            }

            if(isVisited(now, visited, board)) {
                return "0 0"; // 싸이클 발생
            } else {
                markVisit(now, visited);
            }
        }

        return String.format(
                "%d %d", now[0], now[1]
        );
    }


    // 시작 방향 계산
    private DIRECTION getStartDir(int[] startPoint, int boardSize) {
        final int X = startPoint[0];
        final int Y = startPoint[1];
        final int end = boardSize + 1;

        if(Y == end) {
            return DIRECTION.UP;
        } else if(Y == 0) {
            return DIRECTION.DOWN;
        } else if(X == end) {
            return DIRECTION.LEFT;
        } else if(X == 0) {
            return DIRECTION.RIGHT;
        }

        return null;
    }

    // 거울 만났을 때, 방향전환, 오른쪽으로 90도를 꺾는다....?
    private DIRECTION moveWithMirror(int[] now, DIRECTION currentDir) {

        if(currentDir == DIRECTION.DOWN) {
            return move(now, DIRECTION.LEFT);
        }

        if(currentDir == DIRECTION.UP) {
            return move(now, DIRECTION.RIGHT);
        }

        if(currentDir == DIRECTION.LEFT) {
            return move(now, DIRECTION.UP);
        }

        if(currentDir == DIRECTION.RIGHT) {
            return move(now, DIRECTION.DOWN);
        }

        return null;
    }

    // 이동
    private DIRECTION move(int[] now, DIRECTION currentDir) {

        if(currentDir == DIRECTION.UP) {
            now[1]--;
            return currentDir;
        }

        if(currentDir == DIRECTION.DOWN) {
            now[1]++;
            return currentDir;
        }

        if(currentDir == DIRECTION.RIGHT) {
            now[0]++;
            return currentDir;
        }

        if(currentDir == DIRECTION.LEFT) {
            now[0]--;
            return currentDir;
        }

        return null;
    }

    // 거울 위에 있는지
    private boolean isOnMirror(int[] now, int[][] board) {
        if(board[now[0]][now[1]] == 1) {
            return true;
        }
        return false;
    }

    // 탈출 했는지
    private boolean isEscape(int[] now, int boardSize) {
        final int end = boardSize+1;
        final int X = now[0];
        final int Y = now[1];

        if(X == end || X == 0) {
            return true;
        }

        if (Y == end || Y == 0) {
            return true;
        }
        return false;
    }

    private void markVisit(int[] now, boolean[][] visited) {
        visited[now[0]][now[1]] = true;
    }

    private boolean isVisited(int[] now, boolean[][] visited, int[][] board) {
        return visited[now[0]][now[1]] && board[now[0]][now[1]] == 1;
    }

    // 보드 거울 초기화
    private void initBoardWithMirror(int[][] board, List<String> mirrors) {

        for (String mirror : mirrors) {
            String[] mirrorXY = mirror.split(" ");
            int x = Integer.parseInt(mirrorXY[1]);
            int y = Integer.parseInt(mirrorXY[0]);

            board[x][y] = 1;
        }
    }
}

enum DIRECTION {
    UP, DOWN, LEFT, RIGHT
}
// 2        TC 개수

// 2 3      보드 크기 / 거울 개수
// 1 1
// 1 2
// 2 2
// 3 1      레이저 진입점

// 3 6      보드 크기 / 거울 개수
// 1 1
// 1 3
// 2 2
// 2 3
// 3 1
// 3 2
// 2 0      레이저 진입점