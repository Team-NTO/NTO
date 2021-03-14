package d_2021_03_11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private final int MAX_DAY_CNT = 1001;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main m = new Main();
        
        int cnt = Integer.parseInt(sc.nextLine());
        List<String> row = new ArrayList<>(cnt);

        for (int i = 0; i < cnt; i++) {
            row.add(sc.nextLine());
        }
        
        System.out.print(m.solution(row));
    }

    @Test
    void 테스트케이스_1 () {

        // given
        List<String> tc = Arrays.asList(
                "4 60",
                "4 40",
                "1 20",
                "2 50",
                "3 30",
                "4 10",
                "6 5"
        );

        // when
        int result = solution(tc);

        //then
        Assertions.assertEquals(185, result);
    }

    private int solution(List<String> tc) {
        int N = tc.size();

        List<Homework> hwList = tc.stream()
                .map(Homework::new)
                .sorted( (h1, h2) -> h2.point - h1.point)
                .collect(Collectors.toList());

        int[] scores = new int[MAX_DAY_CNT];

        for (int i = 0; i < N; i++) {
            Homework hw = hwList.get(i);

            // 마감 날부터 시작 날까지 역순 순회
            for (int j = hw.deadline; j > 0 ; j--) {
                if(scores[j] == 0) {
                    scores[j] = hw.point;
                    break;
                }
            }
        }

        return IntStream.of(scores)
                .sum();
    }
}

class Homework {
    public int deadline;
    public int point;

    public Homework(String line) {
        String[] str = line.split(" ");
        this.deadline = Integer.parseInt(str[0]);
        this.point    = Integer.parseInt(str[1]);
    }
}
