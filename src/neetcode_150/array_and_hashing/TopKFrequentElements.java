package neetcode_150.array_and_hashing;

import leetcode.common.LeetCodeUtils;

import java.util.HashMap;
import java.util.Map;

public class TopKFrequentElements {

    public static void main(String[] args) {
        var solution = new TopKFrequentElements();
        int[] nums = {1,2,2,3,3,3};
        int k = 2;
        int[] output = {2,3};

        LeetCodeUtils.runTest(
                output,
                () -> solution.topKFrequent(nums, k)
        );
    }

    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }


        for (int i = 0; i < k; i++) {
            res[i] = map.getOrDefault(nums[i], 0);
        }
        return res;
    }
}
