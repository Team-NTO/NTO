package day_2020_09_29;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        int N  = sc.nextInt();

        List<String> list = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            list.add(sc.nextLine());
        }

        System.out.print( main.solution(N, list) );
    }

    @Test
    void test() {
        // given

        // [tc - 1]
        int N_1 = 3;
        List<String> list_1 = Arrays.asList(
                "0 0 0",
                "0 0 0",
                "0 9 0"
        );

        // [tc - 2]
        int N_2 = 3;
        List<String> list_2 = Arrays.asList(
                "0 0 1" +
                "0 0 0" +
                "0 9 0"
        );

        // [tc - 3]
        int N_3 = 4;
        List<String> list_3 = Arrays.asList(
            "4 3 2 1",
            "0 0 0 0",
            "0 0 9 0",
            "1 2 3 4"
        );

        // [tc - 4]
        // when
//        int result  = solution(N_1, list_1);
//        int result2 = solution(N_2, list_2);
        int result3 = solution(N_3, list_3);

        // then
//        assertEquals(0 , result);
//        assertEquals(3 , result2);
        assertEquals(14, result3);
    }

    /**
     * 문제  : https://www.acmicpc.net/problem/16236 - 아기 상어
     * 난이도 : 골드 4
     *
     * BFS 사용
     *
     * 공간의 상태가 주어졌을 때, 아기 상어가 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지 구하는 프로그램을 작성
     *
     * 룰
     *
     * 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다. => 같은 크기 X,
     * 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없다.  => 같은 크기 통과 가능
     *
     * 조건
     * 1. 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청
     * 2. 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
     *
     * 3. 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
     *     거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
     *
     *     거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
     * 풀이
     * 1. 상어 -> BFS로 각 물고기들 까지의 최단경로 계산
     * 2.
     */
    int solution(int N, List<String> list) {
        int[][] map = initMap(list);
        boolean[][] visited = new boolean[N][N];

        int[] babySharkIdx = getBabySharkIdx(map);

        int startX = babySharkIdx[0];
        int startY = babySharkIdx[1];
        Location start = new Location(startX, startY, map);

        map[startX][startY] = 0;

        bfs(start, map, visited);

        return moveCnt;
    }

    static int moveCnt = 0;
    private void bfs(Location start, int[][] map, boolean[][] visited) {

        Queue<Location> bfsQ = new ArrayDeque<>();
        bfsQ.offer(start);
        Shark shark = new Shark(start.x, start.y, 2);

        while(!bfsQ.isEmpty()) {
            Location now = bfsQ.poll();

            // 위에 부터 확인
            if(now.isMobile(map, shark, "UPPER", visited)) {
                int x = now.x;
                int y = now.y-1;

                bfsQ.offer(new Location(x, y, map));
                shark.eatIfSmall(map);
                visited[x][y] = true;
                moveCnt++;
            }

            if(now.isMobile(map, shark, "LEFT", visited)) {
                int x = now.x-1;
                int y = now.y;

                bfsQ.offer(new Location(x, y, map));
                shark.eatIfSmall(map);
                visited[x][y] = true;
                moveCnt++;
            }

            if(now.isMobile(map, shark, "LOWER", visited)) {
                int x = now.x;
                int y = now.y+1;

                bfsQ.offer(new Location(x, y, map));
                shark.eatIfSmall(map);
                visited[x][y] = true;
                moveCnt++;
            }


            if(now.isMobile(map, shark, "RIGHT", visited)) {
                int x = now.x+1;
                int y = now.y;

                bfsQ.offer(new Location(x, y, map));
                shark.eatIfSmall(map);
                visited[x][y] = true;
                moveCnt++;
            }
        }
    }

    /**
     * 입력값 map 초기화
     */
    private int[][] initMap(List<String> list) {
        int N = list.size();
        int[][] initMap =  new int[N][N];

        for (int i = 0; i < list.size(); i++) {
            int[] row = Arrays.stream(list.get(i).split(" "))
                              .mapToInt(Integer::parseInt)
                              .toArray();
            initMap[i] = row;
        }

        return initMap;
    }

    /**
     * 아기 상어 시작위치 확인
     */
    private int[] getBabySharkIdx(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            int[] row = map[i];

            for (int j = 0; j < row.length ; j++) {
                if(row[j] == 9) {
                    return new int[] {j, i};
                }
            }
        }
        return new int[] { 0, 0 };
    }
}

class Location {
    int x;
    int y;
    int size;

    public Location(int x, int y, int[][] map) {
        this.x = x;
        this.y = y;
        this.size = map[x][y];
    }

    /**
     * 지정한 방향으로 움직일 수 있는지 여부
     */
    public boolean isMobile(int[][] map, Shark shark, String direct, boolean[][] visited) {
        int maxLength = map.length;

        System.out.println(this);
        if("UPPER".equals(direct) && !visited[x][y]) {
            int next = y - 1;
            System.out.println("UPPER");
            return next >= 0 && shark.isByPassable(x, y, map);
        }

        if("LEFT".equals(direct) && !visited[x][y]) {
            int next = x - 1;
            System.out.println("LEFT");
            return next >= 0 && shark.isByPassable(x, y, map);
        }

        if("RIGHT".equals(direct) && !visited[x][y]) {
            int next = x + 1;
            System.out.println("RIGHT");
            return next < maxLength && shark.isByPassable(x, y, map);
        }

        if("LOWER".equals(direct) && !visited[x][y]) {
            int next = y + 1;
            System.out.println("LOWER");
            return next < maxLength && shark.isByPassable(x, y, map);
        }

        return false;
    }
    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", size=" + size +
                '}';
    }
}

class Shark {
    int x;
    int y;
    int size;

    public Shark(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void eatIfSmall(int[][] map) {
        int cmpSize =  map[x][y];
        if(this.size > cmpSize) {
            this.size += map[x][y];
            map[x][y] = 0;
        }
    }

    public boolean moveIfPossible(int x, int y, int[][] map) {
        int cmpSize =  map[x][y];
        if(this.size >= cmpSize || cmpSize == 0) {
            this.x = x;
            this.y = y;
            return true;
        }
        return false;
    }

    /**
     * 통과 가능한지만 체크 여부
     */
    public boolean isByPassable(int x, int y, int[][] map) {
        int cmpSize = map[x][y];
        return cmpSize >= this.size || cmpSize == 0;
    }

    @Override
    public String toString() {
        return "Shark{ x = " + x + ", y = " + y + ", size = " + size + '}';
    }
}