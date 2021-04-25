package d_2021_03_23;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

    }

    /**
     * 백준 / 골드 4 / 20955 - 민서의 응급 수술
     */
    private int solution(List<Edge> edges) {
        return 0;
    }

    @Test
    void 예제_입력_1() {

        // given
        final List<Edge> edges = Arrays.asList(
                new Edge(4, 2),
                new Edge(4, 2),
                new Edge(4, 2)
        );

        // when
        int minCalcTime = solution(edges);

        // then
        Assertions.assertEquals(1, minCalcTime);
    }
}

class Edge {

    int from;
    int to;

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }
}