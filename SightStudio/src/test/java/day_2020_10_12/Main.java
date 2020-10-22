package day_2020_10_12;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// feature/sdm-2020-10-12
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Main m = new Main();

        String first = br.readLine();
        String second = br.readLine();

        int cnt = Integer.parseInt(first.split(" ")[1]);
        List<String> groupList = new ArrayList<>(cnt);

        for (int i = 0; i < cnt; i++) {
            groupList.add(br.readLine());
        }

        System.out.print(
                m.solution(first, second, groupList)
        );
    }

    /**
     * 백준 / 골드 3 / 친구비
     * https://www.acmicpc.net/problem/16562
     */
    private String solution(String first, String second, List<String> groupList) {
        return "";
    }
}