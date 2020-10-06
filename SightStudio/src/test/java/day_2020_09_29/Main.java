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
     * 1. 상어 -> BFS 로 각 물고기들 까지의 최단경로 계산
     * 2.
     */
    static int globalTimer = 0;
    static int remainFish = 0;
    int solution(int N, List<String> list) {
        int[][] map = initMap(list);
        int[] sharkInit = getStart(map, N);

        // init shark start
        map[sharkInit[0]][sharkInit[1]] = 0;

        Shark shark = new Shark(sharkInit, N);
        int result = bfs(map, shark, N);

        return result;
    }

    private int bfs(int[][] map, Shark shark, int N) {
        Queue<Location> bfsq = new ArrayDeque<>();

        while(true) {
            bfsq.clear();
            bfsq.offer(new Location(shark.x, shark.y, 0));

            while (!bfsq.isEmpty()) {
                Location now = bfsq.poll();

                int nextX = -1;
                int nextY = -1;
                int nextSize = -1;

                // 좌
                if (now.x - 1 >= 0) {
                    nextX = now.x - 1;
                    nextY = now.y;
                    nextSize = map[nextX][nextY];

                    if (shark.isEatable(nextSize)) {
                        shark.eat(new Location(nextX, nextY, nextSize));
                        remainFish--;
                        globalTimer++;
                        break;
                    }

                    if (shark.isPassable(nextSize)) {
                        bfsq.offer(new Location(nextX, nextY, nextSize));
                        globalTimer++;
                    }
                }

                // 상
                if (now.y - 1 >= 0) {
                    nextX = now.x;
                    nextY = now.y-1;
                    nextSize = map[nextX][nextY];

                    if (shark.isEatable(nextSize)) {
                        shark.eat(new Location(nextX, nextY, nextSize));
                        remainFish--;
                        globalTimer++;
                        break;
                    }

                    if (shark.isPassable(nextSize)) {
                        bfsq.offer(new Location(nextX, nextY, nextSize));
                        globalTimer++;
                    }
                }

                // 우
                if (now.x + 1 < N) {
                    nextX = now.x + 1;
                    nextY = now.y;
                    nextSize = map[nextX][nextY];

                    if (shark.isEatable(nextSize)) {
                        shark.eat(new Location(nextX, nextY, nextSize));
                        remainFish--;
                        globalTimer++;
                        break;
                    }

                    if (shark.isPassable(nextSize)) {
                        bfsq.offer(new Location(nextX, nextY, nextSize));
                        globalTimer++;
                    }
                }

                // 하
                if (now.y + 1 < N) {
                    nextX = now.x;
                    nextY = now.y + 1;
                    nextSize = map[nextX][nextY];

                    if (shark.isEatable(nextSize)) {
                        shark.eat(new Location(nextX, nextY, nextSize));
                        remainFish--;
                        globalTimer++;
                        break;
                    }

                    if (shark.isPassable(nextSize)) {
                        bfsq.offer(new Location(nextX, nextY, nextSize));
                        globalTimer++;
                    }
                }
            }

            if(remainFish <= 0) break;
        }

        return globalTimer;
    }

    private int[] getStart(int[][] map, int N) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < N ; i++) {
            for (int j = 0; j < N ; j++) {
                if(map[i][j] == 9) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        return new int[] { x, y };
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

            for (int j = 0; j < row.length ; j++) {
                if(row[j] != 0 && row[j] != 9) {
                    remainFish++;
                }
            }
        }

        return initMap;
    }
}

class Location {
    int x;
    int y;
    int size;

    public Location(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
}


class Shark {
    int x;
    int y;
    int size;

    int mapSize;
    public Shark(int[] code, int N) {
        this.x = code[0];
        this.y = code[1];
        this.size = 2;
        this.mapSize = N;
    }

    public boolean isEatable(int locationSize) {
        return this.size > locationSize && locationSize != 0;
    }

    public boolean isPassable(int locationSize) {
        return this.size >= locationSize || locationSize == 0;
    }

    public void eat(Location location) {
        this.x = location.x;
        this.y = location.y;
        this.size += location.size;
    }
}