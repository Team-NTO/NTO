package _2020_10_07;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private static int n = 4;
    @Test
    void testMove1() {
        int[][] map = {
                {  0,  0,  0,  0},
                {  4,  0,  0,  8},
                {  8, 32,  4,  0},
                {  8,  8,  4,  0},
        };

        Main.moveRight(map, 4);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%2d ", map[i][j]);
            }
            System.out.println();
        }
        Main.moveDown(map, 4);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%2d ", map[i][j]);
            }
            System.out.println();
        }
        Main.moveDown(map, 4);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%2d ", map[i][j]);
            }
            System.out.println();
        }
        Main.moveRight(map, 4);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%2d ", map[i][j]);
            }
            System.out.println();
        }
        Main.moveDown(map, 4);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%2d ", map[i][j]);
            }
            System.out.println();
        }
    }
    @Test
    void testMove2() {
        int[][] map = {
                { 2,  4,  4},
                { 2,  0,  2},
                { 2, 4,  4},
        };
        Main.moveLeft(map, 3);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%2d ", map[i][j]);
            }
            System.out.println();
        }
    }
}