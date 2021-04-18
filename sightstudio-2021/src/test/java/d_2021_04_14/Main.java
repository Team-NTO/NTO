package d_2021_04_14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    @Test
    void test() {

        // given
        int N = 7;
        List<Integer> nums = Arrays.asList(3,1,6,2,7,30,1);

        // when
        int solution = solution(N,  nums);

        // then
        Assertions.assertEquals(21, solution);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Main m = new Main();
        int  N = Integer.parseInt(br.readLine());
        List<Integer> result = new ArrayList<>(N);

        result.addAll(m.getIntList(br.readLine().split(" ")));
        System.out.print(m.solution(N, result));
    }

    int solution(int N, List<Integer> weights) {
        Collections.sort(weights);

        if(weights.get(0) != 1) {
            return 1;
        }

        int sum = weights.get(0);
        int result = 0;
        for (int i = 1; i < N; i++) {
            if(sum + 1 < weights.get(i)) {
                result = sum + 1;
                break;
            }
            sum += weights.get(i);
        }

        if(result == 0) {
            return sum + 1;
        }

        return result;
    }

    private List<Integer> getIntList(String[] line) {
        return Arrays.stream(line)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
