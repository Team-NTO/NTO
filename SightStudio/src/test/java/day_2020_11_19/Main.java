package day_2020_11_19;

import java.util.Scanner;

// feature/sdm-2020-11-19
public class Main {

    /**
     * 2020/11/19 백준 1507 궁금한 민호
     * https://www.acmicpc.net/problem/1507
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[][] map = new int[num][num];
        int[][] newMap = new int[num][num];

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                map[i][j] = newMap[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                for (int k = 0; k < num; k++) {
                    if (i != j && j != k) {
                        if (map[i][k] == map[i][j] + map[j][k]) {
                            newMap[i][k] = 0;
                        } else if (map[k][j] > map[k][i] + map[i][j]) {    // 최소값 보다 더 최소값이 나오면 에러
                            System.out.println("-1");
                            return;
                        }
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < num; i++) {
            for (int j = i + 1; j < num; j++) {
                if (newMap[i][j] != 0) {
                    result += newMap[i][j];
                }
            }
        }
        System.out.println(result);
    }
}

