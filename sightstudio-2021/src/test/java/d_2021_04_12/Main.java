package d_2021_04_12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Main m = new Main();
        int  N = Integer.parseInt(br.readLine());
        List<Integer> result = new ArrayList<>((int) Math.pow(N, 2));

        for (int i = 0; i < N; i++) {
            result.addAll(m.getIntList(br.readLine().split(" ")));
        }

        System.out.print(m.solution(N, result));
    }

    int solution(int N, List<Integer> list) {
        list.sort(Comparator.reverseOrder());
        return list.get(N-1);
    }

    private List<Integer> getIntList(String[] line) {
        return Arrays.asList(line)
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @Test
    void test() {

        // given
        int N = 5;
        List<Integer> rows = Arrays.asList(
            12,  7,  9, 15,  5,
            13,  8, 11, 19,  6,
            21, 10, 26, 31, 16,
            48, 14 ,28, 35, 25,
            52, 20, 32, 41, 49
        );

        // when
        int solution = solution(N, rows);

        // then
        Assertions.assertEquals(35, solution);
    }
}
