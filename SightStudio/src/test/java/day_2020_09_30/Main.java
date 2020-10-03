package day_2020_09_30;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

// feature/sdm-2020-09-30
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        System.out.println(main.solution(sc.nextLine()));
    }

    @Test
    void test() {

        assertEquals(19, solution("33(562(71(9)))")); // len = 14

        //     33(562(71(9)))  | 71(9)    => 79
        // ->  33(562(79))     | 562(79)  => 567979
        // ->  33(567979)
        // - > 3_567979_567979_567979  19개

        assertEquals(16, solution("33(62(71(9)))"));

        //     33(62(71(9))) | 71(9)      => 내부 길이 = 2  | 79
        // ->  33(62(79))    | 33(62(79)) => 내부 길이 = 5  | 67979
        // ->  33(67979)     | 33(67979)  => 내부 길이 = 16 | 3_67979_67979_67979 => 16

        assertEquals(108, solution("3(3(3(2(2)2(2))))"));

        //     3(3(3(2(2)2(2))))       |
        // =>  3(3(3(2222))))          | 내부 길이 = 4
        // =>  3(3(2222_2222_2222)))   | 내부 길이 = 12
        // =>  3(2222_2222_2222*3)     | 내부 길이 = 36
        // =>  (2222_2222_2222*3*3)    | 내부 길이 = 108
    }

    /**
     * 압축되지 않은 문자열 S가 주어졌을 때,
     * 이 문자열중 어떤 부분 문자열은 K(Q)와 같이 압축 할 수 있다.
     * K는 한자리 정수이고, Q는 0자리 이상의 문자열이다.
     *
     * 이 Q라는 문자열이 K번 반복된다는 뜻이다.
     * 압축된 문자열이 주어졌을 때, 이 문자열을 다시 압축을 푸는 프로그램을 작성하시오.
     */
    int solution(String input) {
        char[] cArr = input.toCharArray();
        int[]  mark = new int[50];

        Deque<Integer> s1 = new ArrayDeque<>(cArr.length);

        for (int i = 0; i < cArr.length; i++) {
            char c = cArr[i];
            if( c == '(' ) s1.push(i);
            if( c == ')' ) mark[s1.pop()] = i;      // mark[openIdx] = closeIdx
        }

        return decode(0, cArr.length, cArr, mark);
    }

    private int decode(int start, int end, char[] cArr, int[] mark) {
        int size = 0;

        for (int i = start; i < end; i++) {

            // 하위 괄호 탐색
            if(cArr[i] == '(') {
                int multiply = Character.digit(cArr[i-1], 10);
                size += multiply * decode(i+1, mark[i], cArr, mark) - 1; // 앞의 개수 * 내부 괄호 문자 수 - 1 || -1은 곱해진 수 때문에 뺀다
                i = mark[i];

            // BC - 1 | 더이상 내부에 괄호가 없을 때
            } else {
                 size++;
            }
        }

        // BC - 2 | 모든 내부 길이 계산 완료
        return size;
    }
}
