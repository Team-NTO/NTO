package d_2021_03_24;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> trees = new LinkedList<>();

        while(true) {
            String line = br.readLine();

            if(line == null) {
                break;
            }

            trees.add(line);
        }

        List<String> solution = m.solution(trees);

        System.out.print(
                String.join("\n", solution)
        );
    }

    /**
     * 백준 / 골드4 / 4358 - 생태학
     */
    private List<String> solution(List<String> trees) {
        final int totalCnt = trees.size();

        Map<String, Integer> map = new HashMap<>();
        for (String tree : trees) {
            map.putIfAbsent(tree, 0);
            map.computeIfPresent(tree, (k, v) -> ++v);
        }

        List<String> result = new ArrayList<>(map.size());
        map.forEach((k,v) -> {
            double value = v*100 / (double) totalCnt;
            result.add(k + " " + String.format("%.4f", value));
        });

        Collections.sort(result);
        return result;
    }


    @Test
    void 예제_입력_1() {

        // given
        List<String> trees = Arrays.asList(
                "Red Alder",
                "Ash",
                "Aspen",
                "Basswood",
                "Ash",
                "Beech",
                "Yellow Birch",
                "Ash",
                "Cherry",
                "Cottonwood",
                "Ash",
                "Cypress",
                "Red Elm",
                "Gum",
                "Hackberry",
                "White Oak",
                "Hickory",
                "Pecan",
                "Hard Maple",
                "White Oak",
                "Soft Maple",
                "Red Oak",
                "Red Oak",
                "White Oak",
                "Poplan",
                "Sassafras",
                "Sycamore",
                "Black Walnut",
                "Willow"
        );

        List<String> expected = Arrays.asList(
                "Ash 13.7931",
                "Aspen 3.4483",
                "Basswood 3.4483",
                "Beech 3.4483",
                "Black Walnut 3.4483",
                "Cherry 3.4483",
                "Cottonwood 3.4483",
                "Cypress 3.4483",
                "Gum 3.4483",
                "Hackberry 3.4483",
                "Hard Maple 3.4483",
                "Hickory 3.4483",
                "Pecan 3.4483",
                "Poplan 3.4483",
                "Red Alder 3.4483",
                "Red Elm 3.4483",
                "Red Oak 6.8966",
                "Sassafras 3.4483",
                "Soft Maple 3.4483",
                "Sycamore 3.4483",
                "White Oak 10.3448",
                "Willow 3.4483",
                "Yellow Birch 3.4483"
        );

        // when
        List<String> result = solution(trees);

        Assertions.assertEquals(result, expected);
    }
}