package day_2020_09_25;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @See https://leetcode.com/problems/permutations/description/
 *
 * 46. 순열 문제
 * 백트래킹 풀이
 */
class Solution {

    @Test
    public void test() {

        // given
        int[] nums = new int[]{1, 2, 3, 4, 5};

        // when
        List<List<Integer>> permute = permute(nums);

        // then
        Assertions.assertEquals(120, permute.size());

//        확인
//        String resultStr = permute.stream()
//                                 .map(Object::toString)
//                                 .collect(Collectors.joining("\n"));
//        System.out.println(resultStr);
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        
        backtrack(nums, ret, tmp);

        return ret;
    }

    private void backtrack(int[] nums, List<List<Integer>> ret, List<Integer> tmp) {
        if(tmp.size() == nums.length) {
            ret.add(new ArrayList<>(tmp));
            return;
        }

        for (int num : nums) {
            if(tmp.contains(num)) continue;
            tmp.add(num);
            backtrack(nums, ret, tmp);
            tmp.remove(tmp.size() - 1 );
        }
    }
}