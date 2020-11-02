package day_2020_10_26;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//feature/sdm-2020-10-26
public class Main {

    // https://www.acmicpc.net/problem/1759
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Main m = new Main();
        List<String> result = m.solution(br.readLine(), br.readLine());

        System.out.print(
                String.join("\n", result)
        );
    }

    @Test
    void test() {
        String line1 = "4 6";
        String line2 = "a t c i s w";

        List<String> result = Arrays.asList(
                "acis",
                "acit",
                "aciw",
                "acst",
                "acsw",
                "actw",
                "aist",
                "aisw",
                "aitw",
                "astw",
                "cist",
                "cisw",
                "citw",
                "istw"
        );


        Assertions.assertEquals(result, solution(line1, line2));
    }


    /**
     * - 문제 : https://www.acmicpc.net/problem/1759
     * - 백준 / 골드 5 / 암호 만들기
     */
    private List<String> solution(String line1, String line2) {
        String[] temp = line1.split(" " );
        String[] kind = line2.split(" " );

        List<String> totalResult = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        int seqCnt = Integer.parseInt(temp[0]);

        backtrack(seqCnt, kind, sb, totalResult);

        Collections.sort(totalResult);
        return totalResult;
    }

    private void backtrack(int seqCnt, String[] kind, StringBuilder sb, List<String> totalResult) {

        if (sb.length() == seqCnt) {
            String result = sb.toString();
            if(hasVowel(result) && hasConsonant(result) && !totalResult.contains(result)) {
                totalResult.add(result);
            }
            return;
        }

        for (int i = 0; i < kind.length; i++) {
            if (sbContains(sb, kind[i])) continue;
            sb.append(kind[i]);
            backtrack(seqCnt, kind, sb, totalResult);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private boolean hasConsonant(String result) {
        int cnt = 0;
        for (int i = 0; i < result.length(); i++) {
            if(
                result.charAt(i) != 'a' &&
                result.charAt(i) != 'e' &&
                result.charAt(i) != 'i' &&
                result.charAt(i) != 'o' &&
                result.charAt(i) != 'u') {

                cnt++;
            }

            if(cnt == 2) return true;
        }
        return false;
    }

    private boolean hasVowel(String result) {
        for (int i = 0; i < result.length(); i++) {
            if(result.charAt(i) == 'a' ||
               result.charAt(i) == 'e' ||
               result.charAt(i) == 'i' ||
               result.charAt(i) == 'o' ||
               result.charAt(i) == 'u') {
                return true;
            }
        }
        return false;
    }

    private boolean sbContains(StringBuilder sb, String ch) {
        char target = ch.toCharArray()[0];
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == target || (int) c > (int) target) return true;
        }
        return false;
    }
}